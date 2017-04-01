/**
 * 
 */
package com.huateng.xhcp.model.system;

import java.io.Serializable;

import com.huateng.xhcp.model.BaseModel;

import lombok.Getter;
import lombok.Setter;

/**
 * 菜单Bean
 * @author pansen
 *
 */
public class Module extends BaseModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private @Getter @Setter String module_id;
	private @Getter @Setter String module_opcode;
	private @Getter @Setter String module_name;
	private @Getter @Setter String pmodule_id;
	private @Getter @Setter String module_entry;
	private @Getter @Setter Integer module_type;
	private @Getter @Setter Integer module_valid;
	private @Getter @Setter String module_pic_big;
	private @Getter @Setter String module_pic_small;
	private @Getter @Setter String help_page;
	private @Getter @Setter Integer module_order;
	private @Getter @Setter Integer module_hide;
	private @Getter @Setter String module_version;
	private @Getter @Setter String module_target;
	private @Getter @Setter Integer module_level;
	
}
