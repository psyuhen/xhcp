/**
 * 
 */
package com.huateng.xhcp.service.imp.system;

import java.util.ArrayList;
import java.util.List;

import com.huateng.xhcp.mapper.system.RoleMapper;
import com.huateng.xhcp.model.system.Role;
import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huateng.xhcp.mapper.system.AccountMapper;
import com.huateng.xhcp.model.system.Account;
import com.huateng.xhcp.service.system.AccountService;
import com.huateng.xhcp.web.page.PageHelper;

/**
 * @author pansen
 *
 */
@Service
public class AccountServiceImp implements AccountService {

	private @Autowired @Setter @Getter AccountMapper accountMapper;
	private @Autowired @Setter @Getter RoleMapper roleMapper;
	/**
	 * 查询用户信息
	 * @param account
	 * @return
	 */
	@Override
	public List<Account> queryAccount(Account account, int start, int limit){
		/* 分页  */
		PageHelper.startPage(start, limit);
		return this.accountMapper.queryAccount(account);
	}
	/**
	 * 查询用户信息
	 * @param account
	 * @return
	 */
	@Override
	public List<Account> queryAccount(Account account){
		return this.accountMapper.queryAccount(account);
	}
	/**
	 * 根据ID查询用户信息
	 * @param account_id
	 * @return
	 */
	@Override
	public Account queryAccountById(String account_id){
		return this.accountMapper.queryAccountById(account_id);
	}

	@Override
	public Account queryByMobile(String mobile) {
		return this.accountMapper.queryByMobile(mobile);
	}

	/**
	 * 根据用户名和密码查询用户信息
	 * @param account
	 * @return
	 */
	@Override
	public Account queryAccountByIdAndPwd(Account account){
		return this.accountMapper.queryAccountByIdAndPwd(account);
	}

	/**
	 * 新增账号信息
	 * @param account
	 */
	public int addAccount(Account account){
		return this.accountMapper.addAccount(account);
	}
	/**
	 * 更新账号信息
	 * @param account
	 */
	public int updateAccount(Account account){
		return this.accountMapper.updateAccount(account);
	}
	/**
	 * 根据account_id删除账号信息
	 * @param account_id
	 */
	public int deleteAccount(String account_id){
		return this.accountMapper.deleteAccount(account_id);
	}

	@Override
	public int deleteByMobile(String mobile) {
		return this.accountMapper.deleteByMobile(mobile);
	}

	/**
	 * 查询用户拥有的角色
	 * @param account_id 用户Id
	 * @return
	 */
	public List<Role> queryBelongRoleByAccountId(String account_id){
		return this.roleMapper.queryBelongRoleByAccountId(account_id);
	}
}
