package com.huateng.xhcp.service.system;

import java.util.List;

import com.huateng.xhcp.model.system.Role;

public interface RoleService {
	/**
	 * 查询角色信息
	 * @param role
	 * @param start
	 * @param limit
	 * @return
	 */
	List<Role> queryRole(Role role, int start, int limit);
	/**
	 * 查询角色信息
	 * @param role
	 * @return
	 */
	List<Role> queryRole(Role role);
	
	/**
	 * 新增角色信息
	 * @param role
	 */
	int addRole(Role role);
	
	/**
	 * 编辑角色信息
	 * @param role
	 */
	int updateRole(Role role);
	
	/**
	 * 删除角色
	 * @param role
	 */
	int deleteRole(Role role);
}
