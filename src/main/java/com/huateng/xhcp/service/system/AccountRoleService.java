/**
 * 
 */
package com.huateng.xhcp.service.system;

import java.util.ArrayList;
import java.util.List;

import com.huateng.xhcp.model.system.AccountRole;

/**
 * 用户权限Mapper
 * @author sam.pan
 *
 */
public interface AccountRoleService {
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
	 * 更新用户权限信息
	 * @param accountRoleList
	 */
	int updateAccountRole2(ArrayList<ArrayList<AccountRole>> accountRoleList);
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
	/**
	 * 更新支配角色的用户
	 * @param accountRoleList
	 * @author chengz
	 */
	int updateAccountRoleConf(ArrayList<ArrayList<AccountRole>> accountRoleList);
}
