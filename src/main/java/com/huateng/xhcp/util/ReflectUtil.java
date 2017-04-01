package com.huateng.xhcp.util;

import com.huateng.xhcp.model.ParamType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 反射工具类
 * 
 * @author shijunkai
 *
 */
public class ReflectUtil {
	/**
	 * 执行反射方法
	 * 
	 * @param className
	 *            反射方法所在类名全称
	 * @param methodName
	 *            方法名称
	 * @param paramType
	 *            参数类型
	 * @param parameter
	 *            参数
	 * @return
	 * @throws Exception
	 */
	public static Object doReflectMethod(String className, String methodName, ParamType paramType, Object parameter)
			throws Exception {
		Object object = ApplicationContextUtil.getBeanByClassName(className);
		Class<?> classType = (object != null) ? object.getClass() : Class.forName(className);
		Object instance = (object != null) ? object : classType.newInstance();
		Method method = null;
		try{
			method = classType.getMethod(methodName, paramType.getParamType());
			return method.invoke(instance, parameter);
		}catch(NoSuchMethodException e){}

		try{
			method = classType.getMethod(methodName, paramType.getParamType(), HttpServletRequest.class);
			return method.invoke(instance, parameter, paramType.getRequest());
		}catch (NoSuchMethodException e){
		}

		try{
			method = classType.getMethod(methodName, paramType.getParamType(), HttpServletRequest.class, HttpServletResponse.class);
			return method.invoke(instance, parameter, paramType.getRequest(), paramType.getResponse());
		}catch (NoSuchMethodException e){
		}

		throw new NoSuchMethodException("没有这样的方法执行");
	}

	/**
	 * 根据方法名称取得反射方法的参数类型(没有考虑同名重载方法使用时注意)
	 * @param classInstance         类实例
	 * @param methodName  方法名
	 * @return
	 * @throws ClassNotFoundException
	 */
	public static Class[]  getMethodParamTypes(Object classInstance,
											   String methodName) throws ClassNotFoundException{
		Class[] paramTypes = null;
		Method[]  methods = classInstance.getClass().getMethods();//全部方法
		for (int  i = 0;  i< methods.length; i++) {
			if(methodName.equals(methods[i].getName())){//和传入方法名匹配
				Class[] params = methods[i].getParameterTypes();
				paramTypes = new Class[ params.length] ;
				for (int j = 0; j < params.length; j++) {
					paramTypes[j] = Class.forName(params[j].getName());
				}
				break;
			}
		}
		return paramTypes;
	}
}
