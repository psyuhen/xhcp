/**
 * 
 */
package com.huateng.xhcp.service.imp.system;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huateng.xhcp.mapper.system.AccountRoleMapper;
import com.huateng.xhcp.model.system.AccountRole;
import com.huateng.xhcp.service.system.AccountRoleService;

/**
 * @author sam.pan
 *
 */
@Service
public class AccountRoleServiceImp implements AccountRoleService{
	
	private @Autowired @Setter @Getter AccountRoleMapper accountRoleMapper;
	/**
	 * 查询用户权限信息
	 * @param accountRole
	 * @return
	 */
	@Override
	public List<AccountRole> queryAccountRole(AccountRole accountRole){
		return this.accountRoleMapper.queryAccountRole(accountRole);
	}
	/**
	 * 新增用户权限信息
	 * @param accountRole
	 */
	@Override
	public int addAccountRole(AccountRole accountRole){
		return this.accountRoleMapper.addAccountRole(accountRole);
	}
	/**
	 * 更新用户权限信息
	 * @param accountRole
	 */
	@Override
	public int updateAccountRole(AccountRole accountRole){
		return this.accountRoleMapper.updateAccountRole(accountRole);
	}
	@Override
	public int deleteAccountRole(AccountRole accountRole) {
		return this.accountRoleMapper.deleteAccountRole(accountRole);
	}
	
	/**
	 * 更新用户权限信息
	 * @param accountRoleList
	 * @author chengz
	 */
	@Override
	public int updateAccountRole2(ArrayList<ArrayList<AccountRole>> accountRoleList){
		ArrayList<AccountRole> addList = accountRoleList.get(0);
		ArrayList<AccountRole> delList = accountRoleList.get(1);
		if(null == addList || addList.size() < 1){
			addList = new ArrayList<AccountRole>();
		}
		if(null == delList || delList.size() < 1){
			delList = new ArrayList<AccountRole>();
		}
		
		for(AccountRole accountRole : delList){
			this.accountRoleMapper.deleteAccountRole(accountRole);
		}
		/*新增角色信息*/
		for(AccountRole accountRole : addList){
			this.accountRoleMapper.addAccountRole(accountRole);
		}

		return 1;
	}
	/**
	 * 查询支配角色的用户
	 * @param accountRole
	 * @author chengz
	 * @return
	 */
	public List<AccountRole> queryAccountRoleConf(AccountRole accountRole){
		return this.accountRoleMapper.queryAccountRoleConf(accountRole);
	}
	/**
	 * 新增支配角色的用户
	 * @param accountRole
	 * @author chengz
	 */
	public int addAccountRoleConf(AccountRole accountRole){
		return this.accountRoleMapper.addAccountRoleConf(accountRole);
	}
	/**
	 * 删除支配角色的用户
	 * @param accountRole
	 * @author chengz
	 */
	public int deleteAccountRoleConf(AccountRole accountRole){
		return this.accountRoleMapper.deleteAccountRoleConf(accountRole);
	}
	/**
	 * 更新支配角色的用户
	 * @param accountRoleList
	 * @author chengz
	 */
	public int updateAccountRoleConf(ArrayList<ArrayList<AccountRole>> accountRoleList){
		ArrayList<AccountRole> addList = accountRoleList.get(0);
		ArrayList<AccountRole> delList = accountRoleList.get(1);
		if(null == addList || addList.size() < 1){
			addList = new ArrayList<AccountRole>();
		}
		if(null == delList || delList.size() < 1){
			delList = new ArrayList<AccountRole>();
		}
		
		for(AccountRole accountRole : delList){
			this.accountRoleMapper.deleteAccountRoleConf(accountRole);
		}
		/*新增角色信息*/
		for(AccountRole accountRole : addList){
			this.accountRoleMapper.addAccountRoleConf(accountRole);
		}

		return 1;
	}
}
