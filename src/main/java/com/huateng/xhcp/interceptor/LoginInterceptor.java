package com.huateng.xhcp.interceptor;

import com.huateng.xhcp.model.system.Account;
import com.huateng.xhcp.security.SecurityContext;
import com.huateng.xhcp.util.PropertiesReader;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;

/**
 * HandlerInterceptor 接口中定义了三个方法，我们就是通过这三个方法来对用户的请求进行拦截处理的。

 preHandle()： 这个方法在业务处理器处理请求之前被调用，SpringMVC 中的Interceptor 是链式的调用的，在一个应用中或者说是在一个请求中可以同时存在多个Interceptor 。每个Interceptor 的调用会依据它的声明顺序依次执行，而且最先执行的都是Interceptor 中的preHandle 方法，所以可以在这个方法中进行一些前置初始化操作或者是对当前请求的一个预处理，也可以在这个方法中进行一些判断来决定请求是否要继续进行下去。该方法的返回值是布尔值Boolean 类型的，当它返回为false 时，表示请求结束，后续的Interceptor 和Controller 都不会再执行；当返回值为true 时就会继续调用下一个Interceptor 的preHandle 方法，如果已经是最后一个Interceptor 的时候就会是调用当前请求的Controller 方法。

 postHandle()：这个方法在当前请求进行处理之后，也就是Controller 方法调用之后执行，但是它会在DispatcherServlet 进行视图返回渲染之前被调用，所以我们可以在这个方法中对Controller 处理之后的ModelAndView 对象进行操作。postHandle 方法被调用的方向跟preHandle 是相反的，也就是说先声明的Interceptor 的postHandle 方法反而会后执行。

 afterCompletion()：该方法也是需要当前对应的Interceptor 的preHandle 方法的返回值为true 时才会执行。顾名思义，该方法将在整个请求结束之后，也就是在DispatcherServlet 渲染了对应的视图之后执行。这个方法的主要作用是用于进行资源清理工作的。
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

		if(set.isEmpty()){
			set.add("/usercenter.html");
			set.add("/forget.html");
			set.add("/address.html");
			set.add("/order.html");
			set.add("/shopping_checkout.html");
			set.add("/ordercancel.html");
			set.add("/orderview.html");
			set.add("/shopping_confirm.html");
		}


		final HttpSession session = request.getSession();
		final Object develop_mode = session.getAttribute("develop_mode");
		if(develop_mode == null){
			boolean developMode = PropertiesReader.getBoolean("develop_mode", true);
			session.setAttribute("develop_mode", developMode);
		}

		String contextPath = request.getContextPath();

		String requestURI = request.getRequestURI();
		String url = requestURI.replace(contextPath, "");

		LOGGER.info("url==>" + url);
		if(StringUtils.contains(url, "/css/")
				|| StringUtils.contains(url, "/js/")){
			return true;
		}


		// || StringUtils.contains(url, "/mgr")
		if(StringUtils.startsWith(url, "/mgr")){

			if(StringUtils.contains(url, "/mgr/tologin.do")
					|| StringUtils.contains(url, "/mgr/login")){
				return true;
			}

			final Account loginAccount = SecurityContext.getLoginAccount();
			if(loginAccount == null){
				request.setAttribute("toBlackUrl", url.concat("?").concat(StringUtils.trimToEmpty(request.getQueryString())));
				request.getRequestDispatcher("/WEB-INF/mgr/jsp/system/Login.jsp").forward(request, response);
				return false;
			}
		}else{
			if(set.contains(url)){
				final Account frontAccount = SecurityContext.getFrontAccount();
				if(frontAccount == null){
					request.setAttribute("blackUrl", url.concat("?").concat(StringUtils.trimToEmpty(request.getQueryString())));
					request.getRequestDispatcher("/WEB-INF/mgr/jsp/login.jsp").forward(request, response);
					return false;
				}
			}
		}


		return true;
	}


	private final HashSet<String> set = new HashSet<String>();

}
