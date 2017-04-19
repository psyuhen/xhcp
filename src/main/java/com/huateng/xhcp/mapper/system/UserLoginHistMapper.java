/**
 * 
 */
package com.huateng.xhcp.mapper.system;


import com.huateng.xhcp.model.system.UserLoginHist;

/**
 * 用户登录历史
 * @author ps
 *
 */
public interface UserLoginHistMapper {
	/**
	 * 增加用户登录信息
	 * @param userLoginHist
	 */
	void addUserLoginHist(UserLoginHist userLoginHist);
}
