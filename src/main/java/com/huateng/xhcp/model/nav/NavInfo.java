/**
 * 
 */
package com.huateng.xhcp.model.nav;

import com.huateng.xhcp.model.BaseModel;

import lombok.Getter;
import lombok.Setter;

/**
 * 导航信息信息Bean
 * @author sam.pan
 *
 */
public class NavInfo extends BaseModel{
	
	private @Setter @Getter String nav_id;
	private @Setter @Getter String pnav_id;
	private @Setter @Getter String name;
	private @Setter @Getter String url;
	private @Setter @Getter String is_out_link;
	private @Setter @Getter String is_default;
	private @Setter @Getter String order_id;
	private @Setter @Getter String create_time;
	
	
}
