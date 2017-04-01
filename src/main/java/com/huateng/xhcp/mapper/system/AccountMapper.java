/**
 * 
 */
package com.huateng.xhcp.mapper.system;

import java.util.List;

import com.huateng.xhcp.model.system.Account;

/**
 * 用户Mapper
 * @author pansen
 *
 */
public interface AccountMapper {
	/**
	 * 查询用户信息
	 * @param account
	 * @return
	 */
	public List<Account> queryAccount(Account account);
	/**
	 * 根据ID查询用户信息
	 * @param account_id
	 * @return
	 */
	public Account queryAccountById(String account_id);
	/**
	 * 根据mobile查询用户信息
	 * @param mobile
	 * @return
	 */
	public Account queryByMobile(String mobile);
	/**
	 * 根据用户名和密码查询用户信息
	 * @param account
	 * @return
	 */
	public Account queryAccountByIdAndPwd(Account account);
	/**
	 * 新增账号信息
	 * @param account
	 */
	public int addAccount(Account account);
	/**
	 * 更新账号信息
	 * @param account
	 */
	public int updateAccount(Account account);
	/**
	 * 根据account_id删除账号信息
	 * @param account_id
	 */
	public int deleteAccount(String account_id);
	/**
	 * 根据mobile删除账号信息
	 * @param mobile
	 */
	public int deleteByMobile(String mobile);
}
