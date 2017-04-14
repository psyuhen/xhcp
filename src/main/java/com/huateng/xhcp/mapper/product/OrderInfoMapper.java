/**
 * 
 */
package com.huateng.xhcp.mapper.product;

import java.util.List;

import com.huateng.xhcp.model.product.OrderInfo;

/**
 * 订单信息Mapper
 * @author sam.pan
 *
 */
public interface OrderInfoMapper {
	/**
	 * 查询订单信息信息
	 * @param orderInfo
	 * @return
	 */
	List<OrderInfo> queryOrderInfo(OrderInfo orderInfo);
	/**
	 * 新增订单信息信息
	 * @param orderInfo
	 */
	int addOrderInfo(OrderInfo orderInfo);
	/**
	 * 批量新增订单信息信息
	 * @param orderInfo
	 */
	int addBatchOrderInfo(List<OrderInfo> orderInfo);
	/**
	 * 更新订单信息信息
	 * @param orderInfo
	 */
	int updateOrderInfo(OrderInfo orderInfo);
	/**
	 * 根据order_id删除订单信息信息
	 * @param orderInfo
	 */
	int deleteOrderInfo(OrderInfo orderInfo);
	/**
	 * 根据order_id批量删除订单信息信息
	 * @param orderInfo
	 */
	int deleteBatchOrderInfo(List<OrderInfo> orderInfo);
}
