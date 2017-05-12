/**
 * 
 */
package com.huateng.xhcp.mapper.system;

import java.util.List;

import com.huateng.xhcp.model.system.AccountRole;

/**
 * 用户权限Mapper
 * @author sam.pan
 *
 */
public interface AccountRoleMapper {
	/**
	 * 查询用户权限信息
	 * @param accountRole
	 * @return
	 */
	List<AccountRole> queryAccountRole(AccountRole accountRole);
	/**
	 * 新增用户权限信息
	 * @param accountRole
	 */
	int addAccountRole(AccountRole accountRole);
	/**
	 * 更新用户权限信息
	 * @param accountRole
	 */
	int updateAccountRole(AccountRole accountRole);
	/**
	 * 删除用户权限信息
	 * @param accountRole
	 */
	int deleteAccountRole(AccountRole accountRole);
	
	/**
	 * 查询支配角色的用户
	 * @param accountRole
	 * @author chengz
	 * @return
	 */
	List<AccountRole> queryAccountRoleConf(AccountRole accountRole);
	/**
	 * 新增支配角色的用户
	 * @param accountRole
	 * @author chengz
	 */
	int addAccountRoleConf(AccountRole accountRole);
	/**
	 * 删除支配角色的用户
	 * @param accountRole
	 * @author chengz
	 */
	int deleteAccountRoleConf(AccountRole accountRole);
}
