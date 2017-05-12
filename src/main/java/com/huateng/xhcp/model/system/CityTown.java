/**
 * 
 */
package com.huateng.xhcp.model.system;

import com.huateng.xhcp.model.BaseModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 城市城区信息信息Bean
 * @author sam.pan
 *
 */
@ToString
public class CityTown extends BaseModel{
	
	private @Setter @Getter String town_code;
	private @Setter @Getter String city_code;
	private @Setter @Getter String town_name;
	private @Setter @Getter String post_code;
	
	
}
