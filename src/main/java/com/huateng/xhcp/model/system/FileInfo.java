/**
 * 
 */
package com.huateng.xhcp.model.system;

import com.huateng.xhcp.model.BaseModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 文件信息信息Bean
 * @author sam.pan
 *
 */
@ToString
public class FileInfo extends BaseModel{
	
	private @Setter @Getter String file_id;
	private @Setter @Getter String name;
	private @Setter @Getter String file_name;
	private @Setter @Getter String file_path;
	private @Setter @Getter String create_time;
	private @Setter @Getter String file_type;
	private @Setter @Getter String file_suffix;
	private @Setter @Getter String url;

	
}
