/**
 * 
 */
package com.huateng.xhcp.web.product;

import com.github.pagehelper.Page;
import com.huateng.xhcp.model.ResponseInfo;
import com.huateng.xhcp.model.product.FreqAddr;
import com.huateng.xhcp.model.product.MerchCar;
import com.huateng.xhcp.model.product.MerchInfo;
import com.huateng.xhcp.model.system.Account;
import com.huateng.xhcp.model.system.Province;
import com.huateng.xhcp.security.SecurityContext;
import com.huateng.xhcp.service.product.FreqAddrService;
import com.huateng.xhcp.service.product.MerchCarService;
import com.huateng.xhcp.service.product.MerchInfoService;
import com.huateng.xhcp.service.system.ProvinceService;
import com.huateng.xhcp.util.HttpUtil;
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
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 购物车 Controller类
 * @author sam.pan
 *
 */
@Controller
public class MerchCarController implements com.huateng.xhcp.service.upload.Validator<MerchCar>{
	private static final Log LOGGER = LogFactory.getLog(MerchCarController.class);
	private @Autowired @Setter @Getter MerchCarService merchCarService;
	private @Autowired @Setter @Getter MerchInfoService merchInfoService;
	private @Autowired @Setter @Getter ProvinceService provinceService;
	private @Autowired @Setter @Getter FreqAddrService freqAddrService;

	/**
	 * 初始化页面
	 * @return
	 */
	@RequestMapping(value = "/shopping_checkout.html")
	public String toShoppingCheckout(HttpServletRequest request, HttpServletResponse response){

		Province province = new Province();
		List<Province> provinces = this.provinceService.queryBy(province);
		request.setAttribute("provinces", provinces);


		final Account frontAccount = SecurityContext.getFrontAccount();

		//没有登录要先登录才能结算
		if(frontAccount == null){
			return "forward:/login.html";
		}

		final List<FreqAddr> freqAddrs = this.freqAddrService.queryByAccountId(frontAccount.getAccount_id());

		request.setAttribute("freqAddrs", freqAddrs);

		return "product/shopping_checkout";
	}
	/**
	 * 初始化页面
	 * @return
	 */
	@RequestMapping(value = "/shopping.html")
	public String toShopping(HttpServletRequest request){
		final HttpSession session = request.getSession();
		List<MerchCar> carList = (List<MerchCar>)session.getAttribute("Shopping_Car");


		final Account frontAccount = SecurityContext.getFrontAccount();
		if(frontAccount != null && carList == null){
			String account_id = frontAccount.getAccount_id();
			MerchCar merchCar = new MerchCar();
			merchCar.setAccount_id(account_id);
			final List<MerchCar> merchCars = this.merchCarService.queryBy(merchCar);
			session.setAttribute("Shopping_Car", merchCars);
		}

		float total = 0.0f;
		if(carList != null){
			for(MerchCar mc : carList){
				int buyNum = Integer.parseInt(StringUtils.defaultIfBlank(mc.getBuy_num(), "0"));
				float price = Float.parseFloat(StringUtils.defaultIfBlank(mc.getPrice(), "0.0"));

				total += (buyNum * price);
			}
		}
		session.setAttribute("TotalPrice", total);
		return "product/shopping";
	}

	/**
	 * 初始化页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/product/car/listmgr")
	public String toListPage(HttpServletRequest request){
		request.setAttribute("page", "mgr");
		request.setAttribute("module_id", "carmgr");
		return "product/MerchCarList";
	}

	/**
	 * query页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/product/car/query")
	public String toQueryPage(HttpServletRequest request){
		request.setAttribute("page", "query");
		request.setAttribute("module_id", "carmgr");
		return "product/MerchCarList";
	}
	
	/**
	 * 跳转新增页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/product/car/add")
	public String toAddPage(HttpServletRequest request){
		request.setAttribute("page", "add");
		request.setAttribute("module_id", "carmgr");
		return "product/MerchCarAdd";
	}
	
	/**
	 * 跳转到更新页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/product/car/update")
	public String toUpdatePage(HttpServletRequest request){
		request.setAttribute("page", "update");
		request.setAttribute("module_id", "carmgr");
		return "product/MerchCarAdd";
	}
	/**
	 * 跳转到查看页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/product/car/view")
	public String toViewPage(HttpServletRequest request){
		request.setAttribute("page", "view");
		request.setAttribute("module_id", request.getParameter("module_id"));
		return "product/MerchCarAdd";
	}
	
	/**
	 * 查询购物车信息(分页)
	 * @param merchCar
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/product/car/queryMerchCarPage", method = RequestMethod.POST)
	public com.huateng.xhcp.web.page.Page queryMerchCar(MerchCar merchCar){
		Page<MerchCar> list = (Page<MerchCar>)this.merchCarService.queryMerchCar(merchCar);
		
		return new com.huateng.xhcp.web.page.Page(list);
	}
	
	/**
	 * 根据Key查询购物车信息
	 * @param car_id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/product/car/queryByKey", method = RequestMethod.GET)
	public MerchCar queryByKey(String car_id){
		return this.merchCarService.queryByKey(car_id);
	}

	/**
	 * 新增购物车信息
	 * @param merchCar
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/product/car/addbuynum", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> addBuyNum(MerchCar merchCar, HttpServletRequest request){
		final HttpSession session = request.getSession();
		List<MerchCar> carList = (List<MerchCar>)session.getAttribute("Shopping_Car");

		float total = 0.0f;
		if(carList != null){
			for(MerchCar mc : carList){
				if(StringUtils.equals(merchCar.getMerch_id(), mc.getMerch_id())){
					int tbn = Integer.parseInt(StringUtils.defaultIfBlank(merchCar.getBuy_num(), "0"));
					mc.setBuy_num("" + tbn);
				}
				int bn = Integer.parseInt(StringUtils.defaultIfBlank(mc.getBuy_num(), "0"));
				float price = Float.parseFloat(StringUtils.defaultIfBlank(mc.getPrice(), "0.0"));

				total += (bn * price);
			}
		}

		return HttpUtil.success("更新数量成功!", total);
	}

	/**
	 * 新增购物车信息
	 * @param merchCar
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/product/car/add", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> addByMerchId(MerchCar merchCar, HttpServletRequest request){
		final String merch_id = merchCar.getMerch_id();
		if(StringUtils.isBlank(merch_id)){
			return HttpUtil.failure("添加到购物车失败!");
		}

		final Account frontAccount = SecurityContext.getFrontAccount();
		final HttpSession session = request.getSession();
		List<MerchCar> carList = (List<MerchCar>)session.getAttribute("Shopping_Car");

		MerchInfo merchInfo = this.merchInfoService.queryByKey(merch_id);
		merchCar.setMerch_name(merchInfo.getName());
		merchCar.setPrice(merchInfo.getPrice());
		if(frontAccount != null){
			merchCar.setAccount_id(frontAccount.getAccount_id());
			int c = this.merchCarService.addMerchCar(merchCar);
			if(c == 0){
				return HttpUtil.failure("添加到购物车失败!");
			}

			MerchCar mc = new MerchCar();
			mc.setAccount_id(frontAccount.getAccount_id());
			final List<MerchCar> merchCars = this.merchCarService.queryBy(mc);
			session.setAttribute("Shopping_Car", merchCars);
		}else {
			if(carList == null){
				carList = new ArrayList<MerchCar>();
				carList.add(merchCar);
				session.setAttribute("Shopping_Car", carList);
			}else{
				boolean exist = false;
				for(MerchCar mc : carList){
					if(StringUtils.equals(merch_id, mc.getMerch_id())){
						exist = true;

						int bn = Integer.parseInt(StringUtils.defaultIfBlank(mc.getBuy_num(), "0"));
						int tbn = Integer.parseInt(StringUtils.defaultIfBlank(merchCar.getBuy_num(), "0"));
						mc.setBuy_num("" + (bn + tbn));
						break;
					}
				}
				if(!exist){
					carList.add(merchCar);
				}
			}
		}

		return HttpUtil.success("添加到购物车成功!");
	}
	/**
	 * 新增购物车信息
	 * @param merchCar
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/product/car/addMerchCar", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> addMerchCar(MerchCar merchCar){
		try{
			ResponseInfo responseInfo = validate(merchCar);
			if(ResponseInfo.FAILURE.equals(responseInfo.getStatus())){
				return HttpUtil.failure(responseInfo.getDesc());
			}
			
			int c = this.merchCarService.addMerchCar(merchCar);
			if(c==0){
				return HttpUtil.failure("新增购物车失败!");
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("新增购物车失败!");
		}
		
		return HttpUtil.success("新增购物车成功!");
	}
	
	/*校验*/
	public ResponseInfo validate(MerchCar merchCar){
		if(merchCar == null){
			return ResponseInfo.fail("购物车信息为空!");
		}
		
		String car_id = merchCar.getCar_id();
		int car_idLength = com.huateng.xhcp.util.Validator.mysql(car_id);
		if(car_idLength > 10){
			return ResponseInfo.fail("购物车ID必须少于10 个字符!");
		}

		String merch_id = merchCar.getMerch_id();
		int merch_idLength = com.huateng.xhcp.util.Validator.mysql(merch_id);
		if(merch_idLength > 10){
			return ResponseInfo.fail("商品ID必须少于10 个字符!");
		}

		String buy_num = merchCar.getBuy_num();
		int buy_numLength = com.huateng.xhcp.util.Validator.mysql(buy_num);
		if(buy_numLength > 10){
			return ResponseInfo.fail("购买数量必须少于10 个字符!");
		}

		String account_id = merchCar.getAccount_id();
		int account_idLength = com.huateng.xhcp.util.Validator.mysql(account_id);
		if(account_idLength > 96){
			return ResponseInfo.fail("用户ID必须少于96 个字符!");
		}


		
		return ResponseInfo.success("校验成功");
	}
	
	
	/**
	 * 更新购物车信息
	 * @param merchCar
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/product/car/updateMerchCar", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> updateMerchCar(MerchCar merchCar){
		try{
			ResponseInfo responseInfo = validate(merchCar);
			if(ResponseInfo.FAILURE.equals(responseInfo.getStatus())){
				return HttpUtil.failure(responseInfo.getDesc());
			}
			
			int c = this.merchCarService.updateMerchCar(merchCar);
			if(c == 0){
				return HttpUtil.failure("更新购物车失败!");
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("更新购物车失败!");
		}
		
		return HttpUtil.success("更新购物车成功!");
	}

	/**
	 * 删除购物车信息
	 * @param car_id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/product/car/delete", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> deleteById(String car_id,String merch_id, HttpServletRequest request){
		final HttpSession session = request.getSession();
		List<MerchCar> carList = (List<MerchCar>)session.getAttribute("Shopping_Car");

		final Account frontAccount = SecurityContext.getFrontAccount();
		boolean delete = true;
		if(frontAccount != null && StringUtils.isNotBlank(car_id)){
			MerchCar merchCar = new MerchCar();
			merchCar.setCar_id(car_id);
			ResponseEntity<ResponseInfo> responseEntity = deleteMerchCar(merchCar);
			ResponseInfo responseInfo = responseEntity.getBody();
			if(ResponseInfo.FAILURE.equals(responseInfo.getStatus())){
				delete = false;
			}
			if(!delete){
				HttpUtil.failure("删除购物车信息失败!");
			}

			if(carList != null){
				MerchCar merchCar1 = null;
				for(MerchCar mc : carList){
					if(StringUtils.equals(car_id, mc.getCar_id())){
						merchCar1 = mc;
						break;
					}
				}

				if(merchCar1 != null){
					carList.remove(merchCar1);
				}
				session.setAttribute("Shopping_Car" , carList);
			}
		}else{
			if(carList != null){
				MerchCar merchCar1 = null;
				for(MerchCar mc : carList){
					if(StringUtils.equals(merch_id, mc.getMerch_id())){
						merchCar1 = mc;
						break;
					}
				}

				if(merchCar1 != null){
					carList.remove(merchCar1);
				}
				session.setAttribute("Shopping_Car" , carList);
			}
		}

		return HttpUtil.success("删除购物车信息成功!");
	}
	/**
	 * 删除购物车信息
	 * @param merchCar
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/product/car/deleteMerchCar", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> deleteMerchCar(MerchCar merchCar){
		try{
			int c = this.merchCarService.deleteMerchCar(merchCar);
			if(c == 0){
				return HttpUtil.failure("删除购物车失败!");
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("删除购物车失败!");
		}
		
		return HttpUtil.success("删除购物车成功!");
	}
	/**
	 * 批量删除购物车信息
	 * @param merchCar
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/product/car/deleteBatchMerchCar", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> deleteBatchMerchCar(List<MerchCar> merchCar){
		try{
			this.merchCarService.deleteBatchMerchCar(merchCar);
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("批量删除购物车失败!");
		}
		
		return HttpUtil.success("批量删除购物车成功!");
	}
}
