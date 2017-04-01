/**
 * 
 */
package com.huateng.xhcp.model.system;

import lombok.Getter;

/**
 * 账号类型
 * @author sam.pan
 *
 */
public enum AccountType{
	LOCAL(0, "本系统"),ECTIP(1, "ECTIP系统");
	
	private final int value;
	private final @Getter String reasonPhrase;

	private AccountType(int value, String reasonPhrase) {
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
	public static AccountType valueOf(int statusCode) {
		for (AccountType status : values()) {
			if (status.value == statusCode) {
				return status;
			}
		}
		throw new IllegalArgumentException("No matching constant for [" + statusCode + "]");
	}
}
