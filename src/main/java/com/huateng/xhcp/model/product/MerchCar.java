/**
 * 
 */
package com.huateng.xhcp.model.product;

import com.huateng.xhcp.model.BaseModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 购物车信息Bean
 * @author sam.pan
 *
 */
@ToString
public class MerchCar extends BaseModel{
	
	private @Setter @Getter String car_id;
	private @Setter @Getter String merch_id;
	private @Setter @Getter String merch_name;
	private @Setter @Getter String price;
	private @Setter @Getter String unit;
	private @Setter @Getter String buy_num;
	private @Setter @Getter String account_id;
	private @Setter @Getter String create_time;
	
	
}
