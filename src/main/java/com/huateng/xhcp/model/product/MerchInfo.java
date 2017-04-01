/**
 * 
 */
package com.huateng.xhcp.model.product;

import com.huateng.xhcp.model.BaseModel;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 产品信息Bean
 * @author sam.pan
 *
 */
public class MerchInfo extends BaseModel{
	
	private @Setter @Getter String merch_id;
	private @Setter @Getter String name;
	private @Setter @Getter String desc;
	private @Setter @Getter String classify_id;
	private @Setter @Getter String pcls_id;
	private @Setter @Getter String price;
	private @Setter @Getter String in_stock;
	private @Setter @Getter String published_date;
	private @Setter @Getter String out_published;
	private @Setter @Getter String update_time;
	private @Setter @Getter String create_time;
	private @Setter @Getter String sm_recommend;
	private @Setter @Getter String free_shipping;
	private @Setter @Getter String unit;
	private @Setter @Getter String weight;
	private @Setter @Getter String standard;
	private @Setter @Getter String hits;
}
