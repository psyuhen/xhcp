/**
 * 
 */
package com.huateng.xhcp.model.system;

import com.huateng.xhcp.model.BaseModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 访客留言簿信息信息Bean
 * @author sam.pan
 *
 */
@ToString
public class GuestBook extends BaseModel{
	
	private @Setter @Getter String msg_id;
	private @Setter @Getter String msg_info;
	private @Setter @Getter String name;
	private @Setter @Getter String phone;
	private @Setter @Getter String email;
	private @Setter @Getter String address;
	private @Setter @Getter String create_time;
	
	
}
