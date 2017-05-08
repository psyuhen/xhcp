/**
 * 
 */
package com.huateng.xhcp.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.huateng.xhcp.model.system.Account;
import com.huateng.xhcp.util.PropertiesReader;

/**
 * @author sam.pan
 *
 */
public class SecurityContext {
	private static final Log LOGGER = LogFactory.getLog(SecurityContext.class);
	public static final String FRONT_ACCOUNT = "Front_Account";
	public static final String BACK_ACCOUNT = "Back_Account";

	/**
	 * 获取登录用户
	 * @return
	 */
	public static Account getLoginAccount(){
		/* 获取当前请求的登录用户*/
		final HttpSession httpSession = getHttpSession();
		if(httpSession == null) return null;

		Account account = (Account) httpSession.getAttribute(BACK_ACCOUNT);
		
		LOGGER.debug("获取当前后台登录用户信息:" + account);
		
		return account;
	}

	/**
	 * 获取登录用户
	 * @return
	 */
	public static Account getFrontAccount(){
		final HttpSession httpSession = getHttpSession();
		if(httpSession == null) return null;

		/* 获取当前请求的登录用户*/
		Account account = (Account)httpSession.getAttribute(FRONT_ACCOUNT);

		LOGGER.debug("获取当前登录用户信息:" + account);

		return account;
	}

	private static HttpSession getHttpSession(){
		/* 获取request*/
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)requestAttributes;

		/*如果为空时，肯定是没登录的*/
		if(servletRequestAttributes == null){
			return null;
		}

		HttpServletRequest request = servletRequestAttributes.getRequest();

		/* 获取当前请求的session*/
		HttpSession session = request.getSession();

		return session;
	}

	/**
	 * 判断用户是否为超级管理员
	 * @param account_id 用户ID
	 * @return
	 */
	public static boolean isSuperUser(String account_id){
		String supervisor = PropertiesReader.getString("supervisor", "admin");
		boolean isSuperUser = supervisor.equals(StringUtils.trimToEmpty(account_id)) ? true : false;

		return isSuperUser;
	}
}
