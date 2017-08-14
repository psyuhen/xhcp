/**
 * 
 */
package com.huateng.xhcp.model.system;

import com.huateng.xhcp.model.BaseModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 店铺信息信息Bean
 * @author sam.pan
 *
 */
@ToString
public class Store extends BaseModel{
	
	private @Setter @Getter String store_id;
	private @Setter @Getter String store_name;
	private @Setter @Getter String contents;
	private @Setter @Getter String is_use;
	private @Setter @Getter String update_time;
	private @Setter @Getter String create_time;
	
	
}
