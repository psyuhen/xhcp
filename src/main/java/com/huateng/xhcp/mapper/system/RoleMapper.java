package com.huateng.xhcp.mapper.system;

import java.util.List;

import com.huateng.xhcp.model.system.Role;

/**
 * 角色Mapper
 * @author chengz
 * */
public interface RoleMapper {
	/**
	 * 查询角色
	 * @param role
	 * @return
	 */
	List<Role> queryRole(Role role);
	/**
	 * 查询用户支配的角色
	 * @param account_id 用户Id
	 * @return
	 */
	List<Role> queryAssignRoleByAccountId(String account_id);
	/**
	 * 查询用户拥有的角色
	 * @param account_id 用户Id
	 * @return
	 */
	List<Role> queryBelongRoleByAccountId(String account_id);

	/**
	 * 新增角色
	 * @param role
	 */
	int addRole(Role role);
	
	/**
	 * 编辑角色
	 * @param role
	 */
	int updateRole(Role role);
	
	/**
	 * 删除角色
	 * @param role
	 */
	int deleteRole(Role role);
	/**
	 * 新增用户角色
	 * @param role
	 */
	int addAccountRole(Role role);
	/**
	 * 删除用户角色
	 * @param role
	 */
	int deleteAccountRole(Role role);
	/**
	 * 新增用户支配角色
	 * @param role
	 */
	int addAccountRoleConf(Role role);
	/**
	 * 删除用户支配角色
	 * @param role
	 */
	int deleteAccountRoleConf(Role role);
}
