/**
 * 
 */
package com.huateng.xhcp.model.article;

import com.huateng.xhcp.model.BaseModel;

import lombok.Getter;
import lombok.Setter;

/**
 * 资讯信息信息Bean
 * @author sam.pan
 *
 */
public class Article extends BaseModel{
	
	private @Setter @Getter String article_id;
	private @Setter @Getter String title;
	private @Setter @Getter String article_date;
	private @Setter @Getter String article_y;
	private @Setter @Getter String article_m;
	private @Setter @Getter String contents;
	private @Setter @Getter String hits;
	private @Setter @Getter String update_time;
	private @Setter @Getter String create_time;
	
	
}
