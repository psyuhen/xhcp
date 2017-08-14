/**
 * 
 */
package com.huateng.xhcp.service.imp.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huateng.xhcp.mapper.product.MerchCarMapper;
import com.huateng.xhcp.mapper.product.OrderDetailMapper;
import com.huateng.xhcp.mapper.product.OrderInfoMapper;
import com.huateng.xhcp.model.product.OrderDetail;
import com.huateng.xhcp.model.product.OrderInfo;
import com.huateng.xhcp.service.product.OrderInfoService;
import com.huateng.xhcp.web.page.PageHelper;

import lombok.Getter;
import lombok.Setter;

/**
 * 订单信息服务实现类
 * @author sam.pan
 *
 */
@Service
public class OrderInfoServiceImp implements OrderInfoService {

	private @Autowired @Setter @Getter OrderInfoMapper orderInfoMapper;
	private @Autowired @Setter @Getter OrderDetailMapper orderDetailMapper;
	private @Autowired @Setter @Getter MerchCarMapper merchCarMapper;
	/**
	 * 查询订单信息信息
	 * @param orderInfo
	 * @return
	 */
	public List<OrderInfo> queryOrderInfo(OrderInfo orderInfo){
		int start = orderInfo.getStart();
		int limit = orderInfo.getLimit();
		/* 分页  */
		PageHelper.startPage(start, limit);
		return this.orderInfoMapper.queryOrderInfo(orderInfo);
	}
	/**
	 * 查询订单信息信息
	 * @param orderInfo
	 * @return
	 */
	public List<OrderInfo> queryBy(OrderInfo orderInfo){
		return this.orderInfoMapper.queryOrderInfo(orderInfo);
	}
	/**
	 * 根据Key查询订单信息信息
	 * @param order_id
	 * @return
	 */
	public OrderInfo queryByKey(String order_id){
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setOrder_id(order_id);
		List<OrderInfo> list = this.orderInfoMapper.queryOrderInfo(orderInfo);
		if(list == null || list.isEmpty()){
			return null;
		}
		
		return list.get(0);
	}
	/**
	 * 新增订单信息
	 * @param orderInfo
	 * @param details
	 * @return
	 */
	public int addOrder(OrderInfo orderInfo, List<OrderDetail> details){

		int c = this.orderInfoMapper.addOrderInfo(orderInfo);

		if(c == 0){
			return c;
		}

		if(details != null && !details.isEmpty()){
			this.orderDetailMapper.addBatchOrderDetail(details);
		}

		//删除购物车信息
		this.merchCarMapper.deleteByAccount(orderInfo.getBuyer_account_id());


		return c;
	}
	/**
	 * 新增订单信息信息
	 * @param orderInfo
	 */
	public int addOrderInfo(OrderInfo orderInfo){
		return this.orderInfoMapper.addOrderInfo(orderInfo);
	}
	/**
	 * 批量新增订单信息信息
	 * @param orderInfo
	 */
	public int addBatchOrderInfo(List<OrderInfo> orderInfo){
		if(orderInfo == null || orderInfo.isEmpty()){
			return 0;
		}
		return this.orderInfoMapper.addBatchOrderInfo(orderInfo);
	}
	/**
	 * 更新订单信息信息
	 * @param orderInfo
	 */
	public int updateOrderInfo(OrderInfo orderInfo){
		return this.orderInfoMapper.updateOrderInfo(orderInfo);
	}
	/**
	 * 根据order_id删除订单信息信息
	 * @param orderInfo
	 */
	public int deleteOrderInfo(OrderInfo orderInfo){
		return this.orderInfoMapper.deleteOrderInfo(orderInfo);
	}
	/**
	 * 根据order_id批量删除订单信息信息
	 * @param orderInfo
	 */
	public int deleteBatchOrderInfo(List<OrderInfo> orderInfo){
		if(orderInfo == null || orderInfo.isEmpty()){
			return 0;
		}
		return this.orderInfoMapper.deleteBatchOrderInfo(orderInfo);
	}
}
