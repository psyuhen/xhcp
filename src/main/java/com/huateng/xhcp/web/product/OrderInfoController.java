/**
 * 
 */
package com.huateng.xhcp.web.product;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.huateng.xhcp.model.product.FreqAddr;
import com.huateng.xhcp.model.product.MerchCar;
import com.huateng.xhcp.model.product.OrderDetail;
import com.huateng.xhcp.model.system.Account;
import com.huateng.xhcp.model.system.Province;
import com.huateng.xhcp.security.SecurityContext;
import com.huateng.xhcp.service.product.FreqAddrService;
import com.huateng.xhcp.service.product.OrderDetailService;
import com.huateng.xhcp.service.system.ProvinceService;
import com.huateng.xhcp.util.DateUtil;
import com.huateng.xhcp.util.StringUtil;
import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.huateng.xhcp.model.ResponseInfo;
import com.huateng.xhcp.model.product.OrderInfo;
import com.huateng.xhcp.service.product.OrderInfoService;
import com.huateng.xhcp.util.HttpUtil;

/**
 * 订单信息 Controller类
 * @author sam.pan
 *
 */
@Controller
public class OrderInfoController implements com.huateng.xhcp.service.upload.Validator<OrderInfo>{
	private static final Log LOGGER = LogFactory.getLog(OrderInfoController.class);
	private @Autowired @Setter @Getter OrderInfoService orderInfoService;
	private @Autowired @Setter @Getter FreqAddrService freqAddrService;
	private @Autowired @Setter @Getter OrderDetailService orderDetailService;
	private @Autowired @Setter @Getter ProvinceService provinceService;

	@RequestMapping(value="/ordercancel.html")
	public String orderCancelPage(@RequestParam String order_id, HttpServletRequest request){
		final Account frontAccount = SecurityContext.getFrontAccount();
		if(frontAccount == null){
			LOGGER.warn("用户还没登录");
			return "forward:/login.html";
		}
		if(StringUtils.isBlank(order_id)){
			return "forward:/order.html";
		}

		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setOrder_id(order_id);
		orderInfo.setStatus("5");
		orderInfo.setTrad_finish_time(DateUtil.currentTime());

		orderInfoService.updateOrderInfo(orderInfo);

		return "forward:/order.html";
	}
	@RequestMapping(value="/orderview.html")
	public String orderViewPage(@RequestParam String order_id, HttpServletRequest request){
		final Account frontAccount = SecurityContext.getFrontAccount();
		if(frontAccount == null){
			LOGGER.warn("用户还没登录");
			return "forward:/login.html";
		}

		if(StringUtils.isBlank(order_id)){
			return "forward:/order.html";
		}

		final OrderInfo orderInfo = orderInfoService.queryByKey(order_id);
		request.setAttribute("order", orderInfo);

		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setOrder_id(order_id);
		final List<OrderDetail> details = orderDetailService.queryBy(orderDetail);
		request.setAttribute("details", details);

		return "orderview";
	}
	/**
	 * 初始化页面
	 * @return
	 */
	@RequestMapping(value = "/shopping_confirm.html")
	public String checkoutPage(String address_id, String payment, String remark, HttpSession session, HttpServletRequest request){
		final Account frontAccount = SecurityContext.getFrontAccount();
		if(frontAccount == null){
			LOGGER.warn("用户还没登录");
			return "forward:/login.html";
		}

		if(StringUtils.isBlank(address_id) || StringUtils.isBlank(payment)){
			return "forward:/shopping_checkout.html";
		}

		final FreqAddr freqAddr = this.freqAddrService.queryByKey(address_id);
		if(freqAddr == null){
			LOGGER.warn("用户没有收货地址");
			return "forward:/shopping_checkout.html";
		}

		final List<MerchCar> shoppingCars = (List<MerchCar>)session.getAttribute("Shopping_Car");
		if(shoppingCars == null){
			LOGGER.warn("购物车里面没有商品，不需要创建订单");
			return "forward:/shopping_checkout.html";
		}

		//创建订单
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setOrder_id(StringUtil.genOrderId());

		//收货信息
		orderInfo.setProvince_code(freqAddr.getProvince_code());
		orderInfo.setProvince_name(freqAddr.getProvince_name());
		orderInfo.setCity_code(freqAddr.getCity_code());
		orderInfo.setCity_name(freqAddr.getCity_name());
		orderInfo.setTown_code(freqAddr.getTown_code());
		orderInfo.setTown_name(freqAddr.getTown_name());
		orderInfo.setAddress(freqAddr.getAddress());
		orderInfo.setRemark(remark);
		orderInfo.setBuyer_name(freqAddr.getUser_name());
		orderInfo.setBuyer_mobile(freqAddr.getMobile());

		//买家用户信息
		orderInfo.setBuyer_account_id(frontAccount.getAccount_id());
		orderInfo.setBuyer_user_name(frontAccount.getAccount_name());

		//卖家信息
		orderInfo.setSeller_account_id("xhcp");
		orderInfo.setSeller_user_name("陈皮皇");

		//金额信息
		orderInfo.setAmount_money(Float.toString((Float) session.getAttribute("TotalPrice")));
		orderInfo.setCurrency_unit("人民币");
		orderInfo.setPay_type(payment);
//		orderInfo.setTrad_time(DateUtil.currentTime());
		orderInfo.setStatus("0");//0-买方待付款


		List<OrderDetail> details = new ArrayList<OrderDetail>();
		for(MerchCar mc : shoppingCars){
			OrderDetail od = new OrderDetail();
			od.setOrder_id(orderInfo.getOrder_id());
			od.setMerch_id(mc.getMerch_id());
			od.setAmount(mc.getBuy_num());
			od.setMerch_name(mc.getMerch_name());
			od.setUnit(mc.getUnit());
			od.setPrice(mc.getPrice());
			//od.setCreate_time(DateUtil.currentTime());

			details.add(od);
		}


		int c = this.orderInfoService.addOrder(orderInfo, details);
		if(c == 0){
			LOGGER.warn("创建订单失败!");
			return "forward:/shopping_checkout.html";
		}


		request.setAttribute("OrderInfo", orderInfo);

		return "product/shopping_confirm";
	}
	/**
	 * 初始化页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/product/order/listmgr")
	public String toListPage(HttpServletRequest request){
		request.setAttribute("page", "mgr");
		request.setAttribute("module_id", "ordermgr");
		return "product/OrderInfoList";
	}

	/**
	 * query页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/product/order/query")
	public String toQueryPage(HttpServletRequest request){
		request.setAttribute("page", "query");
		request.setAttribute("module_id", "ordermgr");
		return "product/OrderInfoList";
	}
	
	/**
	 * 跳转新增页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/product/order/add")
	public String toAddPage(HttpServletRequest request){
		request.setAttribute("page", "add");
		request.setAttribute("module_id", "ordermgr");

		Province province = new Province();
		List<Province> provinces = this.provinceService.queryBy(province);
		request.setAttribute("provinces", provinces);

		return "product/OrderInfoAdd";
	}
	
	/**
	 * 跳转到更新页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/product/order/update")
	public String toUpdatePage(HttpServletRequest request){
		request.setAttribute("page", "update");
		request.setAttribute("module_id", "ordermgr");

		Province province = new Province();
		List<Province> provinces = this.provinceService.queryBy(province);
		request.setAttribute("provinces", provinces);
		return "product/OrderInfoAdd";
	}
	/**
	 * 跳转到查看页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/product/order/view")
	public String toViewPage(HttpServletRequest request){
		request.setAttribute("page", "view");
		request.setAttribute("module_id", request.getParameter("module_id"));

		Province province = new Province();
		List<Province> provinces = this.provinceService.queryBy(province);
		request.setAttribute("provinces", provinces);
		return "product/OrderInfoAdd";
	}

	/**
	 * 跳转到查看页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/product/order/detail")
	public String toOrderDetailPage(HttpServletRequest request){
		request.setAttribute("page", "view");
		request.setAttribute("module_id", request.getParameter("module_id"));

		return "product/OrderDetailList";
	}

	/**
	 * 查询订单详情信息(分页)
	 * @param orderDetail
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/product/order/queryOrderDetailInfo", method = RequestMethod.POST)
	public com.huateng.xhcp.web.page.Page queryOrderDetailInfo(OrderDetail orderDetail){
		Page<OrderDetail> list = (Page<OrderDetail>)this.orderDetailService.queryOrderDetail(orderDetail);

		return new com.huateng.xhcp.web.page.Page(list);
	}

	/**
	 * 查询订单信息信息(分页)
	 * @param orderInfo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/product/order/queryOrderInfoPage", method = RequestMethod.POST)
	public com.huateng.xhcp.web.page.Page queryOrderInfo(OrderInfo orderInfo){
		Page<OrderInfo> list = (Page<OrderInfo>)this.orderInfoService.queryOrderInfo(orderInfo);
		
		return new com.huateng.xhcp.web.page.Page(list);
	}
	
	/**
	 * 根据Key查询订单信息信息
	 * @param order_id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/product/order/queryByKey", method = RequestMethod.GET)
	public OrderInfo queryByKey(String order_id){
		return this.orderInfoService.queryByKey(order_id);
	}
	/**
	 * 新增订单信息信息
	 * @param orderInfo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/product/order/addOrderInfo", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> addOrderInfo(OrderInfo orderInfo){
		try{
			ResponseInfo responseInfo = validate(orderInfo);
			if(ResponseInfo.FAILURE.equals(responseInfo.getStatus())){
				return HttpUtil.failure(responseInfo.getDesc());
			}
			
			int c = this.orderInfoService.addOrderInfo(orderInfo);
			if(c==0){
				return HttpUtil.failure("新增订单信息失败!");
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("新增订单信息失败!");
		}
		
		return HttpUtil.success("新增订单信息成功!");
	}
	
	/*校验*/
	public ResponseInfo validate(OrderInfo orderInfo){
		if(orderInfo == null){
			return ResponseInfo.fail("订单信息信息为空!");
		}
		
		String order_id = orderInfo.getOrder_id();
		int order_idLength = com.huateng.xhcp.util.Validator.mysql(order_id);
		if(order_idLength > 25){
			return ResponseInfo.fail("订单ID必须少于25 个字符!");
		}

		String amount_money = orderInfo.getAmount_money();
		int amount_moneyLength = com.huateng.xhcp.util.Validator.mysql(amount_money);
		if(amount_moneyLength > 11){
			return ResponseInfo.fail("成交金额必须少于11 个字符!");
		}

		String buyer_account_id = orderInfo.getBuyer_account_id();
		int buyer_account_idLength = com.huateng.xhcp.util.Validator.mysql(buyer_account_id);
		if(buyer_account_idLength > 10){
			return ResponseInfo.fail("买家Id必须少于10 个字符!");
		}

		String buyer_user_name = orderInfo.getBuyer_user_name();
		int buyer_user_nameLength = com.huateng.xhcp.util.Validator.mysql(buyer_user_name);
		if(buyer_user_nameLength > 50){
			return ResponseInfo.fail("买家名称必须少于50 个字符!");
		}

		String seller_account_id = orderInfo.getSeller_account_id();
		int seller_account_idLength = com.huateng.xhcp.util.Validator.mysql(seller_account_id);
		if(seller_account_idLength > 10){
			return ResponseInfo.fail("卖家Id必须少于10 个字符!");
		}

		String seller_user_name = orderInfo.getSeller_user_name();
		int seller_user_nameLength = com.huateng.xhcp.util.Validator.mysql(seller_user_name);
		if(seller_user_nameLength > 50){
			return ResponseInfo.fail("卖家名称必须少于50 个字符!");
		}

		String currency_unit = orderInfo.getCurrency_unit();
		int currency_unitLength = com.huateng.xhcp.util.Validator.mysql(currency_unit);
		if(currency_unitLength > 15){
			return ResponseInfo.fail("货币单位必须少于15 个字符!");
		}

		String buyer_name = orderInfo.getBuyer_name();
		int buyer_nameLength = com.huateng.xhcp.util.Validator.mysql(buyer_name);
		if(buyer_nameLength > 20){
			return ResponseInfo.fail("收货人必须少于20 个字符!");
		}

		String buyer_phone = orderInfo.getBuyer_phone();
		int buyer_phoneLength = com.huateng.xhcp.util.Validator.mysql(buyer_phone);
		if(buyer_phoneLength > 20){
			return ResponseInfo.fail("收货人电话必须少于20 个字符!");
		}

		String buyer_mobile = orderInfo.getBuyer_mobile();
		int buyer_mobileLength = com.huateng.xhcp.util.Validator.mysql(buyer_mobile);
		if(buyer_mobileLength > 20){
			return ResponseInfo.fail("收货人手机号码必须少于20 个字符!");
		}

		String send_type = orderInfo.getSend_type();
		int send_typeLength = com.huateng.xhcp.util.Validator.mysql(send_type);
		if(send_typeLength > 50){
			return ResponseInfo.fail("送货方式必须少于50 个字符!");
		}

		String send_no = orderInfo.getSend_no();
		int send_noLength = com.huateng.xhcp.util.Validator.mysql(send_no);
		if(send_noLength > 50){
			return ResponseInfo.fail("送货单号必须少于50 个字符!");
		}

		String send_time = orderInfo.getSend_time();
		int send_timeLength = com.huateng.xhcp.util.Validator.mysql(send_time);
		if(send_timeLength > 14){
			return ResponseInfo.fail("送货时间必须少于14 个字符!");
		}

		String freight = orderInfo.getFreight();
		int freightLength = com.huateng.xhcp.util.Validator.mysql(freight);
		if(freightLength > 11){
			return ResponseInfo.fail("合计运费必须少于11 个字符!");
		}

		String invoice_need = orderInfo.getInvoice_need();
		int invoice_needLength = com.huateng.xhcp.util.Validator.mysql(invoice_need);
		if(invoice_needLength > 1){
			return ResponseInfo.fail("是否需要发票必须少于1 个字符!");
		}

		String invoice_title = orderInfo.getInvoice_title();
		int invoice_titleLength = com.huateng.xhcp.util.Validator.mysql(invoice_title);
		if(invoice_titleLength > 100){
			return ResponseInfo.fail("发票抬头必须少于100 个字符!");
		}

		String pay_type = orderInfo.getPay_type();
		int pay_typeLength = com.huateng.xhcp.util.Validator.mysql(pay_type);
		if(pay_typeLength > 1){
			return ResponseInfo.fail("支付方式必须少于1 个字符!");
		}

		String buyer_pay_time = orderInfo.getBuyer_pay_time();
		int buyer_pay_timeLength = com.huateng.xhcp.util.Validator.mysql(buyer_pay_time);
		if(buyer_pay_timeLength > 14){
			return ResponseInfo.fail("买方付款时间必须少于14 个字符!");
		}

		String trad_time = orderInfo.getTrad_time();
		int trad_timeLength = com.huateng.xhcp.util.Validator.mysql(trad_time);
		if(trad_timeLength > 14){
			return ResponseInfo.fail("交易发起时间必须少于14 个字符!");
		}

		String trad_finish_time = orderInfo.getTrad_finish_time();
		int trad_finish_timeLength = com.huateng.xhcp.util.Validator.mysql(trad_finish_time);
		if(trad_finish_timeLength > 14){
			return ResponseInfo.fail("交易完成时间必须少于14 个字符!");
		}

		String update_time = orderInfo.getUpdate_time();
		int update_timeLength = com.huateng.xhcp.util.Validator.mysql(update_time);
		if(update_timeLength > 14){
			return ResponseInfo.fail("最后更新时间必须少于14 个字符!");
		}

		String seller_deliver_time = orderInfo.getSeller_deliver_time();
		int seller_deliver_timeLength = com.huateng.xhcp.util.Validator.mysql(seller_deliver_time);
		if(seller_deliver_timeLength > 14){
			return ResponseInfo.fail("卖方发货时间必须少于14 个字符!");
		}

		String buyer_confirm_time = orderInfo.getBuyer_confirm_time();
		int buyer_confirm_timeLength = com.huateng.xhcp.util.Validator.mysql(buyer_confirm_time);
		if(buyer_confirm_timeLength > 14){
			return ResponseInfo.fail("买方确认收货时间必须少于14 个字符!");
		}

		String seller_del = orderInfo.getSeller_del();
		int seller_delLength = com.huateng.xhcp.util.Validator.mysql(seller_del);
		if(seller_delLength > 1){
			return ResponseInfo.fail("买家是否已删除必须少于1 个字符!");
		}

		String buyer_del = orderInfo.getBuyer_del();
		int buyer_delLength = com.huateng.xhcp.util.Validator.mysql(buyer_del);
		if(buyer_delLength > 1){
			return ResponseInfo.fail("卖家是否已删除必须少于1 个字符!");
		}

		String buyer_del_time = orderInfo.getBuyer_del_time();
		int buyer_del_timeLength = com.huateng.xhcp.util.Validator.mysql(buyer_del_time);
		if(buyer_del_timeLength > 14){
			return ResponseInfo.fail("买家删除时间必须少于14 个字符!");
		}

		String seller_del_time = orderInfo.getSeller_del_time();
		int seller_del_timeLength = com.huateng.xhcp.util.Validator.mysql(seller_del_time);
		if(seller_del_timeLength > 14){
			return ResponseInfo.fail("卖家删除时间必须少于14 个字符!");
		}

		String buyer_score = orderInfo.getBuyer_score();
		int buyer_scoreLength = com.huateng.xhcp.util.Validator.mysql(buyer_score);
		if(buyer_scoreLength > 1){
			return ResponseInfo.fail("买家打分必须少于1 个字符!");
		}

		String seller_score = orderInfo.getSeller_score();
		int seller_scoreLength = com.huateng.xhcp.util.Validator.mysql(seller_score);
		if(seller_scoreLength > 1){
			return ResponseInfo.fail("卖家打分必须少于1 个字符!");
		}

		String status = orderInfo.getStatus();
		int statusLength = com.huateng.xhcp.util.Validator.mysql(status);
		if(statusLength > 1){
			return ResponseInfo.fail("交易状态必须少于1 个字符!");
		}

		String province_code = orderInfo.getProvince_code();
		int province_codeLength = com.huateng.xhcp.util.Validator.mysql(province_code);
		if(province_codeLength > 10){
			return ResponseInfo.fail("省份编码必须少于10 个字符!");
		}

		String city_code = orderInfo.getCity_code();
		int city_codeLength = com.huateng.xhcp.util.Validator.mysql(city_code);
		if(city_codeLength > 10){
			return ResponseInfo.fail("城市编码必须少于10 个字符!");
		}

		String town_code = orderInfo.getTown_code();
		int town_codeLength = com.huateng.xhcp.util.Validator.mysql(town_code);
		if(town_codeLength > 10){
			return ResponseInfo.fail("城区ID必须少于10 个字符!");
		}

		String address = orderInfo.getAddress();
		int addressLength = com.huateng.xhcp.util.Validator.mysql(address);
		if(addressLength > 1000){
			return ResponseInfo.fail("详细地址必须少于1000 个字符!");
		}

		String buyer_advise = orderInfo.getBuyer_advise();
		int buyer_adviseLength = com.huateng.xhcp.util.Validator.mysql(buyer_advise);
		if(buyer_adviseLength > 400){
			return ResponseInfo.fail("买家评价必须少于400 个字符!");
		}


		
		return ResponseInfo.success("校验成功");
	}
	
	
	/**
	 * 更新订单信息信息
	 * @param orderInfo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/product/order/updateOrderInfo", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> updateOrderInfo(OrderInfo orderInfo){
		try{
			ResponseInfo responseInfo = validate(orderInfo);
			if(ResponseInfo.FAILURE.equals(responseInfo.getStatus())){
				return HttpUtil.failure(responseInfo.getDesc());
			}
			
			int c = this.orderInfoService.updateOrderInfo(orderInfo);
			if(c == 0){
				return HttpUtil.failure("更新订单信息失败!");
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("更新订单信息失败!");
		}
		
		return HttpUtil.success("更新订单信息成功!");
	}
	
	/**
	 * 删除订单信息信息
	 * @param orderInfo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/product/order/deleteOrderInfo", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> deleteOrderInfo(OrderInfo orderInfo){
		try{
			int c = this.orderInfoService.deleteOrderInfo(orderInfo);
			if(c == 0){
				return HttpUtil.failure("删除订单信息失败!");
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("删除订单信息失败!");
		}
		
		return HttpUtil.success("删除订单信息成功!");
	}
	/**
	 * 批量删除订单信息信息
	 * @param orderInfo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/product/order/deleteBatchOrderInfo", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> deleteBatchOrderInfo(List<OrderInfo> orderInfo){
		try{
			this.orderInfoService.deleteBatchOrderInfo(orderInfo);
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("批量删除订单信息失败!");
		}
		
		return HttpUtil.success("批量删除订单信息成功!");
	}
}
