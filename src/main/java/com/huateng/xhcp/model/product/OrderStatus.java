/**
 * 
 */
package com.huateng.xhcp.model.product;

import lombok.Getter;

/**
 * 账号状态
 * 0-买方待付款，
 * 1-卖方确认订单，
 * 2-买方已付款，
 * 3-卖方已发货，
 * 4-交易完成/买方确认收货，
 * 5-交易取消，
 * 6-交易关闭
 * @author sam.pan
 *
 */
public enum OrderStatus {
	NOPAY(0, "买方待付款"),SELLERCONFIRM(1, "可用")
	,PAYED(2,"卖方确认订单"),DELIVERGOODS(3,"卖方已发货"),TRADFINISH(4,"交易完成/买方确认收货")
	,TRADCANCEL(5,"交易取消"),TRADCLOSED(6,"交易关闭");
	
	private final int value;
	private final @Getter String reasonPhrase;

	private OrderStatus(int value, String reasonPhrase) {
		this.value = value;
		this.reasonPhrase = reasonPhrase;
	}

	/**
	 * 返回状态值
	 */
	public int value() {
		return this.value;
	}
	
	/**
	 * 返回状态值的字符串
	 */
	@Override
	public String toString() {
		return Integer.toString(value);
	}
	
	/**
	 * 根据对应的状态值返回枚举类型
	 * @param statusCode 要返回枚举类型的状态值
	 * @return 返回对应状态值的枚举类型
	 * @throws IllegalArgumentException 如果没有找到对应的状态值，即抛出异常
	 */
	public static OrderStatus valueOf(int statusCode) {
		for (OrderStatus status : values()) {
			if (status.value == statusCode) {
				return status;
			}
		}
		throw new IllegalArgumentException("No matching constant for [" + statusCode + "]");
	}
}
