package com.huateng.xhcp.model.system;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 角色模块Bean
 * @see 角色模块表：sys_rolemodule
 * @author chengz
 *
 */
@ToString
public class RoleModule {
	private @Setter @Getter  String role_id;//角色ID
	private @Setter @Getter  String module_id;//模块ID
}
