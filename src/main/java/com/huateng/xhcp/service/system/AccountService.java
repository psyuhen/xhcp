/**
 * 
 */
package com.huateng.xhcp.service.system;

import java.util.List;

import com.huateng.xhcp.model.system.Account;

/**
 * 用户服务类
 * @author pansen
 *
 */
public interface AccountService {
	/**
	 * 查询用户信息
	 * @param account
	 * @param start
	 * @param limit
	 * @return
	 */
	List<Account> queryAccount(Account account, int start, int limit);
	/**
	 * 查询用户信息
	 * @param account
	 * @return
	 */
	List<Account> queryAccount(Account account);
	/**
	 * 根据ID查询用户信息
	 * @param account_id
	 * @return
	 */
	Account queryAccountById(String account_id);
	/**
	 * 根据mobile查询用户信息
	 * @param mobile
	 * @return
	 */
	Account queryByMobile(String mobile);
	/**
	 * 根据用户名和密码查询用户信息
	 * @param account
	 * @return
	 */
	Account queryAccountByIdAndPwd(Account account);
	
	/**
	 * 新增账号信息
	 * @param account
	 */
	int addAccount(Account account);
	/**
	 * 更新账号信息
	 * @param account
	 */
	int updateAccount(Account account);
	/**
	 * 根据account_id删除账号信息
	 * @param account_id
	 */
	int deleteAccount(String account_id);
	/**
	 * 根据mobile删除账号信息
	 * @param mobile
	 */
	int deleteByMobile(String mobile);
}
