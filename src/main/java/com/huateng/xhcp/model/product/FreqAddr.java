/**
 * 
 */
package com.huateng.xhcp.model.product;

import com.huateng.xhcp.model.BaseModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 常用地址信息信息Bean
 * @author sam.pan
 *
 */
@ToString
public class FreqAddr extends BaseModel{
	
	private @Setter @Getter String freq_id;
	private @Setter @Getter String account_id;
	private @Setter @Getter String province_code;
	private @Setter @Getter String province_name;
	private @Setter @Getter String city_code;
	private @Setter @Getter String city_name;
	private @Setter @Getter String town_code;
	private @Setter @Getter String town_name;
	private @Setter @Getter String address;
	private @Setter @Getter String mobile;
	private @Setter @Getter String user_name;
	private @Setter @Getter String default_addr;
	private @Setter @Getter String create_time;
	
	
}
