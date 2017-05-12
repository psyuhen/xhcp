package com.huateng.xhcp.model.system;

import java.io.Serializable;
import java.util.List;

import com.huateng.xhcp.model.BaseModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 角色信息Bean
 * 
 * @author chengz
 *
 */
@ToString
public class Role extends BaseModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private @Setter @Getter String role_id;// 角色ID
	private @Setter @Getter String role_name;// 角色名称
	private @Setter @Getter String role_desc;// 角色描述

	private @Setter @Getter String account_id;// 用户ID
	private @Setter @Getter List<String> roleIdList;// 角色IDList 
}
