/**
 * 
 */
package com.huateng.xhcp.util;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Json工具类
 * @author sam.pan
 *
 */
public class JsonUtil {
	private static final Log LOGGER = LogFactory.getLog(JsonUtil.class);

    /**
	 * 把对象转换为json字符串
	 * @param valueType
	 * @return
	 */
	public static String  formatObject(Object valueType){
		JsonFactory factory = new JsonFactory();
		ObjectMapper mapper = new ObjectMapper(factory);
		String json = "";
		try {
			json = mapper.writeValueAsString(valueType);
		} catch (JsonProcessingException e) {
			LOGGER.error("json转换出错", e);
		}
		
		return json;
	}


	/**
	 * 将JSON字符串转换为List
	 * @param json
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static List readJsonToList(String json) {
		return readJson(json, List.class);
	}

	/**
	 * json字符串转换为Map对象
	 * @param json
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Map readJsonToMap(String json) {
		return readJson(json, Map.class);
	}

	/**
	 * json字符串转换为Java对象
	 * @param content
	 * @param valueType
	 * @return
	 */
	public static <T> T readJson(String content, Class<T> valueType) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(content, valueType);
		} catch (IOException e) {
			LOGGER.error("json字符串转换为Java对象出错", e);
		}

		return null;
	}
}
