/**
 * 
 */
package com.huateng.xhcp.model.system;

import com.huateng.xhcp.model.BaseModel;

import lombok.Getter;
import lombok.Setter;

/**
 * 会员等级信息Bean
 * @author sam.pan
 *
 */
public class VipLevel extends BaseModel{
	
	private @Setter @Getter String vip_id;
	private @Setter @Getter String name;
	private @Setter @Getter String score;
	private @Setter @Getter String discount;
	private @Setter @Getter String create_time;
	
	
}
