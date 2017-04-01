/**
 * 
 */
package com.huateng.xhcp.model.system;

import com.huateng.xhcp.model.BaseModel;

import lombok.Getter;
import lombok.Setter;

/**
 * 国家城市信息信息Bean
 * @author sam.pan
 *
 */
public class City extends BaseModel{
	
	private @Setter @Getter String city_code;
	private @Setter @Getter String province_code;
	private @Setter @Getter String city_name;
	private @Setter @Getter String city_zone;
	
	
}
