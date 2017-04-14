/**
 * 
 */
package com.huateng.xhcp.service.imp.product;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huateng.xhcp.mapper.product.OrderDetailMapper;
import com.huateng.xhcp.model.product.OrderDetail;
import com.huateng.xhcp.service.product.OrderDetailService;
import com.huateng.xhcp.web.page.PageHelper;

/**
 * 订单信息服务实现类
 * @author sam.pan
 *
 */
@Service
public class OrderDetailServiceImp implements OrderDetailService {

	private @Autowired @Setter @Getter OrderDetailMapper orderDetailMapper;
	/**
	 * 查询订单信息信息
	 * @param orderDetail
	 * @return
	 */
	public List<OrderDetail> queryOrderDetail(OrderDetail orderDetail){
		int start = orderDetail.getStart();
		int limit = orderDetail.getLimit();
		/* 分页  */
		PageHelper.startPage(start, limit);
		return this.orderDetailMapper.queryOrderDetail(orderDetail);
	}
	/**
	 * 查询订单信息信息
	 * @param orderDetail
	 * @return
	 */
	public List<OrderDetail> queryBy(OrderDetail orderDetail){
		return this.orderDetailMapper.queryOrderDetail(orderDetail);
	}
	/**
	 * 根据Key查询订单信息信息
	 * @param order_id
	 * @return
	 */
	public OrderDetail queryByKey(String order_id){
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setOrder_id(order_id);
		List<OrderDetail> list = this.orderDetailMapper.queryOrderDetail(orderDetail);
		if(list == null || list.isEmpty()){
			return null;
		}
		
		return list.get(0);
	}
	/**
	 * 新增订单信息信息
	 * @param orderDetail
	 */
	public int addOrderDetail(OrderDetail orderDetail){
		return this.orderDetailMapper.addOrderDetail(orderDetail);
	}
	/**
	 * 批量新增订单信息信息
	 * @param orderDetail
	 */
	public int addBatchOrderDetail(List<OrderDetail> orderDetail){
		if(orderDetail == null || orderDetail.isEmpty()){
			return 0;
		}
		return this.orderDetailMapper.addBatchOrderDetail(orderDetail);
	}
	/**
	 * 更新订单信息信息
	 * @param orderDetail
	 */
	public int updateOrderDetail(OrderDetail orderDetail){
		return this.orderDetailMapper.updateOrderDetail(orderDetail);
	}
	/**
	 * 根据order_id删除订单信息信息
	 * @param orderDetail
	 */
	public int deleteOrderDetail(OrderDetail orderDetail){
		return this.orderDetailMapper.deleteOrderDetail(orderDetail);
	}
	/**
	 * 根据order_id批量删除订单信息信息
	 * @param orderDetail
	 */
	public int deleteBatchOrderDetail(List<OrderDetail> orderDetail){
		if(orderDetail == null || orderDetail.isEmpty()){
			return 0;
		}
		return this.orderDetailMapper.deleteBatchOrderDetail(orderDetail);
	}
}
