package com.huateng.xhcp.interceptor;

import com.huateng.xhcp.util.PropertiesReader;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录拦截，主要是判断用户有没有登录
 * @author sam.pan
 *
 */
public class LoginInterceptor implements HandlerInterceptor {
	private static final Log LOGGER = LogFactory.getLog(LoginInterceptor.class);
	
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {

		final HttpSession session = request.getSession();
		final Object develop_mode = session.getAttribute("develop_mode");
		if(develop_mode == null){
			boolean developMode = PropertiesReader.getBoolean("develop_mode", true);
			session.setAttribute("develop_mode", developMode);
		}

		String contextPath = request.getContextPath();

		String requestURI = request.getRequestURI();
		String url = requestURI.replace(contextPath, "");



		return true;
	}
	
}
