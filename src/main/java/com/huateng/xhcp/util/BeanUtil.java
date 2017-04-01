package com.huateng.xhcp.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * JavaBean工具类
 * 
 * @author shijunkai
 *
 */
public class BeanUtil {
	private static final Log LOGGER = LogFactory.getLog(BeanUtil.class);

	/**
	 * 将JavaBean转化为Map对象
	 * 
	 * @param object
	 *            JavaBean对象
	 * @return
	 */
	public static Map<String, Object> bean2Map(Object object) {
		if (null == object || "".equals(object)) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// 获取JavaBean信息
			BeanInfo beanInfo = Introspector.getBeanInfo(object.getClass());
			// 获取JavaBean属性列表
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			// 遍历属性并取值放入map中
			for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
				String key = propertyDescriptor.getName();
				// 过滤class属性
				if ("class".equals(key)) {
					continue;
				}
				// 获取属性对应的getter方法
				Method getter = propertyDescriptor.getReadMethod();
				// 运用反射技术得到属性值
				Object value = getter.invoke(object, new Object[] {});
				// 将键值对放入map中
				map.put(key, value);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return null;
		}

		return map;
	}

	/**
	 * 将Map对象转化为JavaBean
	 * 
	 * @param <T>
	 * 
	 * @param map
	 *            Map对象
	 * @param clazz
	 *            要转化的对象的类类型
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T map2Bean(Map<String, Object> map, Class<T> clazz) {
		/* Map对象为空时，直接返回空值 */
		if (map == null || map.size() == 0) {
			return null;
		}
		/* 要返回的JavaBean对象 */
		T object;
		try {
			/* 实例化JavaBean对象 */
			object = clazz.newInstance();
			/* 获取JavaBean的类信息 */
			BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
			/* 获取JavaBean属性列表 */
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			/* 遍历属性列表赋值 */
			for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
				/* 获取属性名称 */
				String key = propertyDescriptor.getName();
				/* 当Map对象中包含该属性的值时，给该属性赋值 */
				if (map.containsKey(key)) {
					/* 获取该属性的set方法 */
					Method setter = propertyDescriptor.getWriteMethod();
					Class<?> clz = setter.getParameterTypes()[0];
					Object param = map.get(key);
					if (!"java.lang.String".equals(clz.getName())) {
						if (param instanceof String) {
							String className = clz.getName();
							if ("java.lang.Integer".equals(className) || "int".equals(className)) {
								param = Integer.parseInt((String) param);
							} else if ("java.lang.Float".equals(className) || "float".equals(className)) {
								param = Float.parseFloat((String) param);
							} else if ("java.lang.Double".equals(className) || "double".equals(className)) {
								param = Double.parseDouble((String) param);
							} else if ("java.lang.Long".equals(className) || "long".equals(className)) {
								param = Long.parseLong((String) param);
							} else {
								LOGGER.info("The className is " + className);
							}
						} else if (param instanceof Map) {
							param = map2Bean((Map<String, Object>) param, clz);
						} else if (param instanceof List) {
							if (!((List<?>) param).isEmpty() && ((List<?>) param).get(0) instanceof Map) {
								List<Map<String, Object>> paramList = (List<Map<String, Object>>) param;
								ParameterizedType type = (ParameterizedType) setter.getGenericParameterTypes()[0];
								clz = (Class<?>) type.getActualTypeArguments()[0];
								List<Object> list = new ArrayList<Object>();
								for (Map<String, Object> map2 : paramList) {
									list.add(map2Bean(map2, clz));
								}
								param = list;
							}
						}
					} else {
						param = param == null ? "" : String.valueOf(param);
					}
					/* 运用反射技术赋值 */
					setter.invoke(object, new Object[] { param });
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return null;
		}

		return object;
	}

}
