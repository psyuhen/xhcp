/**
 * 
 */
package com.huateng.xhcp.mapper.product;

import java.util.List;

import com.huateng.xhcp.model.product.OrderDetail;

/**
 * 订单信息Mapper
 * @author sam.pan
 *
 */
public interface OrderDetailMapper {
	/**
	 * 查询订单信息信息
	 * @param orderDetail
	 * @return
	 */
	List<OrderDetail> queryOrderDetail(OrderDetail orderDetail);
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
