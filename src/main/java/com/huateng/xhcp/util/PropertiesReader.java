/**
 * 
 */
package com.huateng.xhcp.util;

import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.ConversionException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 配置文件类，使用commons
 * @author sam.pan
 * @see PropertiesConfiguration
 */
public class PropertiesReader {
	private static final Log LOGGER = LogFactory.getLog(PropertiesReader.class);
	private static PropertiesConfiguration prop;
	
	private PropertiesReader(){}
	
	static{
		try {
			prop = new PropertiesConfiguration("xhcp.properties");
			FileChangedReloadingStrategy fcrs = new FileChangedReloadingStrategy();
			fcrs.setConfiguration(prop);
		} catch (ConfigurationException e) {
			LOGGER.error("读取xhcp.properties失败",e);
		}
	}
	
	public static PropertiesConfiguration getReader(){
		return prop;
	}

	/**
	 * Get a string associated with the given configuration key.
	 * @param key The configuration key.
	 * @return The associated string.
	 */
	public static String getString(String key){
		return prop.getString(key);
	}
	/**
	 * Get a string associated with the given configuration key. If the key doesn't map to an existing object, the default value is returned.
	 * @param key The configuration key.
	 * @param defaultValue The default value.
	 * @return The associated string.
	 */
	public static String getString(String key, String defaultValue){
		return prop.getString(key, defaultValue);
	}
	/**
	 * Get a boolean associated with the given configuration key. If the key doesn't map to an existing object, the default value is returned.
	 * @param key The configuration key.
	 * @param defaultValue The default value.
	 * @return The associated boolean.
	 */
	public static boolean getBoolean(String key, boolean defaultValue){
		return prop.getBoolean(key, defaultValue);
	}
	/**
	 * Get a boolean associated with the given configuration key.
	 * @param key The configuration key.
	 * @return The associated boolean.
	 */
	public static boolean getBoolean(String key){
		return prop.getBoolean(key);
	}

	/**
	 * Get a int associated with the given configuration key.
	 * @param key The configuration key.
	 * @return The associated int.
	 */
	public static int getInt(String key){
		return prop.getInt(key);
	}
	/**
	 * Get a int associated with the given configuration key.
	 * @param key The configuration key.
	 * @param defaultValue The default value.
	 * @return The associated int.
	 */
	public static int getInt(String key, int defaultValue){
		return prop.getInt(key, defaultValue);
	}
	/**
	 * Get a long associated with the given configuration key.
	 * @param key The configuration key.
	 * @param defaultValue The default value.
	 * @return The associated long.
	 */
	public static long getLong(String key, long defaultValue){
		return prop.getLong(key, defaultValue);
	}
	/**
	 * Get a long associated with the given configuration key.
	 * @param key The configuration key.
	 * @return The associated long.
	 */
	public static long getLong(String key){
		return prop.getLong(key);
	}

	/**
	 * Get an array of strings associated with the given configuration key.
	 * If the key doesn't map to an existing object, an empty array is returned.
	 * If a property is added to a configuration, it is checked whether it
	 * contains multiple values. This is obvious if the added object is a list
	 * or an array. For strings it is checked whether the string contains the
	 * list delimiter character that can be specified using the
	 * {@code setListDelimiter()} method. If this is the case, the string
	 * is split at these positions resulting in a property with multiple
	 * values.
	 *
	 * @param key The configuration key.
	 * @return The associated string array if key is found.
	 *
	 * @throws ConversionException is thrown if the key maps to an
	 *         object that is not a String/List of Strings.
	 */
	public static String[] getStringArray(String key){
		return prop.getStringArray(key);
	}
	
	/**
	 * Get a List of strings associated with the given configuration key. 
	 * If the key doesn't map to an existing object an empty List is returned.
	 * @param key The configuration key.
	 * @return The associated List.
	 */
	public static List<Object> getList(String key){
		return prop.getList(key);
	}
}
