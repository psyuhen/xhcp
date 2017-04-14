/**
 * 
 */
package com.huateng.xhcp.service.product;

import java.util.List;

import com.huateng.xhcp.model.product.OrderDetail;
import com.huateng.xhcp.model.product.OrderInfo;

/**
 * 订单信息服务类
 * @author sam.pan
 *
 */
public interface OrderInfoService {
	/**
	 * 查询订单信息信息
	 * @param orderInfo
	 * @return
	 */
	List<OrderInfo> queryOrderInfo(OrderInfo orderInfo);
	/**
	 * 查询订单信息信息
	 * @param orderInfo
	 * @return
	 */
	List<OrderInfo> queryBy(OrderInfo orderInfo);
	/**
	 * 根据Key查询订单信息信息
	 * @param order_id
	 * @return
	 */
	OrderInfo queryByKey(String order_id);

	/**
	 * 新增订单信息
	 * @param orderInfo
	 * @param details
	 * @return
	 */
	int addOrder(OrderInfo orderInfo, List<OrderDetail> details);
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
