/**
 * 
 */
package com.huateng.xhcp.model.system;

import com.huateng.xhcp.model.BaseModel;

import lombok.Getter;
import lombok.Setter;

/**
 * 国家省份信息信息Bean
 * @author sam.pan
 *
 */
public class Province extends BaseModel{
	
	private @Setter @Getter String province_code;
	private @Setter @Getter String province_name;
	
	
}
