/**
 * 
 */
package com.huateng.xhcp.service.product;

import java.util.List;

import com.huateng.xhcp.model.product.OrderDetail;

/**
 * 订单信息服务类
 * @author sam.pan
 *
 */
public interface OrderDetailService {
	/**
	 * 查询订单信息信息
	 * @param orderDetail
	 * @return
	 */
	List<OrderDetail> queryOrderDetail(OrderDetail orderDetail);
	/**
	 * 查询订单信息信息
	 * @param orderDetail
	 * @return
	 */
	List<OrderDetail> queryBy(OrderDetail orderDetail);
	/**
	 * 根据Key查询订单信息信息
	 * @param order_id
	 * @return
	 */
	OrderDetail queryByKey(String order_id);
	/**
	 * 新增订单信息信息
	 * @param orderDetail
	 */
	int addOrderDetail(OrderDetail orderDetail);
	/**
	 * 批量新增订单信息信息
	 * @param orderDetail
	 */
	int addBatchOrderDetail(List<OrderDetail> orderDetail);
	/**
	 * 更新订单信息信息
	 * @param orderDetail
	 */
	int updateOrderDetail(OrderDetail orderDetail);
	/**
	 * 根据order_id删除订单信息信息
	 * @param orderDetail
	 */
	int deleteOrderDetail(OrderDetail orderDetail);
	/**
	 * 根据order_id批量删除订单信息信息
	 * @param orderDetail
	 */
	int deleteBatchOrderDetail(List<OrderDetail> orderDetail);
}
