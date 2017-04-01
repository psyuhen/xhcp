/**
 * 
 */
package com.huateng.xhcp.model.video;

import com.huateng.xhcp.model.BaseModel;

import lombok.Getter;
import lombok.Setter;

/**
 * 视频信息信息Bean
 * @author sam.pan
 *
 */
public class VideoInfo extends BaseModel{
	
	private @Setter @Getter String video_id;
	private @Setter @Getter String title;
	private @Setter @Getter String img_file_id;
	private @Setter @Getter String img_file_url;
	private @Setter @Getter String video_file_id;
	private @Setter @Getter String video_file_url;
	private @Setter @Getter String create_time;
	
	
}
