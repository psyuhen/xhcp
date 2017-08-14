/**
 * 
 */
package com.huateng.xhcp.model.system;

import lombok.Getter;

/**
 * 是与否的状态
 * @author sam.pan
 *
 */
public enum StoreStatus {
	NO(0, "否"),YES(1, "是");
	
	private final int value;
	private final @Getter String reasonPhrase;

	private StoreStatus(int value, String reasonPhrase) {
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
	public static StoreStatus valueOf(int statusCode) {
		for (StoreStatus status : values()) {
			if (status.value == statusCode) {
				return status;
			}
		}
		throw new IllegalArgumentException("No matching constant for [" + statusCode + "]");
	}
}
