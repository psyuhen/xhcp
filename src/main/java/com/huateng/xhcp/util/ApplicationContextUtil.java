package com.huateng.xhcp.util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 * Spring容器工具类
 * 
 * @author shijunkai
 *
 */
public class ApplicationContextUtil {
	public static final String ROOT_CONTEXT_NAME = "Root WebApplicationContext";

	private static final Log LOGGER = LogFactory.getLog(ApplicationContextUtil.class);
	private static ApplicationContext context;
	private static Object lock = new Object();

	private static ApplicationContext getApplicationContext() {
		if (context == null || ROOT_CONTEXT_NAME.equals(context.getDisplayName())) {
			synchronized (lock){
				if (context == null || ROOT_CONTEXT_NAME.equals(context.getDisplayName())) {
					ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
							.getRequestAttributes();
					if (servletRequestAttributes == null) {
						context = ContextLoader.getCurrentWebApplicationContext();
					} else {
						HttpServletRequest request = servletRequestAttributes.getRequest();
						ServletContext servletContext = request.getSession().getServletContext();
						context = RequestContextUtils.findWebApplicationContext(request, servletContext);
					}
				}
			}
		}
		return context;
	}

	/**
	 * 根据BeanId从Spring容器中获取实例
	 * 
	 * @param beanId
	 * @return
	 */
	public static Object getBeanByBeanId(String beanId) {
		return getApplicationContext().getBean(beanId);
	}

	/**
	 * 根据类名从Spring容器中获取实例
	 * 
	 * @param className
	 * @return
	 */
	public static Object getBeanByClassName(String className) {
		try {
			Class<?> classType = Class.forName(className);
			return getApplicationContext().getBean(classType);
		} catch (ClassNotFoundException e) {
			LOGGER.error("根据类名从Spring容器中获取对象出错：", e);
		}

		return null;
	}

	/**
	 * 根据类类型从Spring容器中获取实例
	 * 
	 * @param classType
	 * @return
	 */
	public static <T> T getBeanByClassType(Class<T> classType) {
		return getApplicationContext().getBean(classType);
	}
	
	/**
	 * 从WebApplicationContext中获取相应的类对象
	 * @param servletContext
	 * @param clsName
	 * @return
	 */
	public static <T> T getBean(ServletContext servletContext, Class<T> clsName){
		WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		return context.getBean(clsName);
	}
}
