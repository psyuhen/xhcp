/**
 * 
 */
package com.huateng.xhcp.model.product;

import com.huateng.xhcp.model.BaseModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 产品信息Bean
 * @author sam.pan
 *
 */
@ToString
public class MerchGallery extends BaseModel{
	
	private @Setter @Getter String gallery_id;
	private @Setter @Getter String merch_id;
	private @Setter @Getter String name;
	private @Setter @Getter String file_name;
	private @Setter @Getter String path;
	private @Setter @Getter String create_time;
	private @Setter @Getter String url;
}
