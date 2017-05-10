/**
 * 
 */
package com.huateng.xhcp.util;

import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.huateng.xhcp.model.article.Article;
import com.huateng.xhcp.model.kindeditor.KEMsg;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import static org.apache.commons.lang3.StringUtils.defaultIfBlank;
import static org.apache.commons.lang3.StringUtils.trimToEmpty;

/**
 * 字符串操作工具类
 * 
 * @author sam.pan
 *
 */
public class StringUtil {
	private static final Log LOGGER = LogFactory.getLog(StringUtil.class);
	/**
	 * 替换{i} i=0,1,2,....
	 * @param source
	 * @param args
	 * @return
	 */
	public static String format(String source, String... args) {
		for (int i = 0; i < args.length; i++) {
			source = StringUtils.replaceOnce(source, "{" + i + "}", args[i]);
		}
		return source;
	}
	
	/**
	 * 解析如：USERID=999990061&PARAM=20131108142213301&CHNAL_NO=10&ectip_ip=11.156.109.220&ectip_port=7001
	 * @param urlQuery
	 * @return
	 */
	public static Map<String, String> splitUrl(String urlQuery){
		String []params = StringUtils.split(urlQuery, "&");
		
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < params.length; i++) {
			String param = params[i];
			
			String []keyValues = StringUtils.split(param, "=");
			if(keyValues.length > 1){
				map.put(keyValues[0], keyValues[1]);
			}else{
				map.put(keyValues[0], "");
			}
		}
		
		return map;
	}
	
	/**
	 * 如果source大于4000个字节，则超过部分被截取掉
	 * @param source
	 * @return
	 */
	public static String less4000(String source){
		int length = Validator.charLength(source);
    	if(length > 4000){
    		
    		byte[] tmp = new byte[4000];
    		try {
				System.arraycopy(source.getBytes("UTF-8"), 0, tmp, 0, tmp.length);
				source = new String(tmp, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				LOGGER.error("截取字符错误:" + e.getMessage());
			}
    	}
    	return source;
	}
	
	/**
	 * 遍历list数组，根据指定的key\value转换为map对象
	 * @param list
	 * @param keyCol
	 * @param valueCol
	 * @return
	 */
	public static Map  list2Map(List<Map> list, String keyCol, String valueCol){
		Map reMap = new HashMap();
		for (Map map : list) {
			if(map.containsKey(keyCol)){
				reMap.put(map.get(keyCol), map.get(valueCol));
			}
		}
		return reMap;
	}
	
	/**
	 * 判断输入的字符串是否为数字，字符串包含 0-9的数字,或者有一个"."或者有一个",",但是","必须在"."的前面
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str){
		final String NUMBER_RULE = "([0-9,\\,]*\\.?[0-9]*)";
		Pattern pattern = Pattern.compile(NUMBER_RULE);
		Matcher mch = pattern.matcher(str);
		return mch.matches();
	}

	/**
	 * 删除Html标签
	 * @param str
	 * @return
	 */
	public static String delHtml(String str){
		if(StringUtils.isBlank(str)){
			return "";
		}
		return str.replaceAll("</?[^>]+>", "");
	}

	/**
	 * 判断字符串是否大于len，大于就截取
	 * @param value
	 * @param len
	 * @return
	 */
	public static String ellipsis(String value, int len){
		if(value.length() > len){
			return StringUtils.substring(value, 0, len - 3 )+"...";
		}
		return value;
	}
	final static char []chars={
			'0','1','2','3','4','5','6','7','8','9'
	};
	/**
	 * 生成几位的随机数
	 * @param size 多少位
	 * @return 随机数
	 */
	public static String random(int size){
		SecureRandom sr = new SecureRandom();
		char[] c = new char[size];
		for (int i = 0; i < c.length; i++) {
			c[i] = chars[sr.nextInt(chars.length)];
		}

		return String.valueOf(c);
	}
	/**
	 * 生成订单号
	 * @return 订单号
	 */
	public static String genOrderId(){
		StringBuilder sb = new StringBuilder();
		sb.append(DateUtil.currentTime());
		sb.append(random(11));

		return sb.toString();
	}


	/**
	 * 返回错误信息
	 * @param msg
	 * @return
	 */
	public static KEMsg error(String msg){
		KEMsg keMsg = new KEMsg();
		keMsg.setError(1);
		keMsg.setMessage(msg);
		return keMsg;
	}

	/**
	 * 返回成功信息
	 * @param msg
	 * @return
	 */
	public static KEMsg success(String msg){
		KEMsg keMsg = new KEMsg();
		keMsg.setError(0);
		keMsg.setMessage(msg);
		return keMsg;
	}


	/**
	 * 去掉contents的html，并保留100个字
	 * @param articles
	 */
	public static void rmContentsHtml(List<Article> articles){
		if(articles != null){
			for(Article a : articles){
				final String contents1 = a.getContents();
				a.setArticle_photo(getSrcUrl(contents1));
				String contents = delHtml(contents1);
				contents = ellipsis(contents, 100);
				a.setContents(contents);
			}
		}
	}

	/**
	 * 获取url
	 * @param contents
	 * @return
	 */
	public static String getSrcUrl(String contents){
		String regex = "src=\".*[.jpg|.png|.bmp|.jpeg|.gif]\"";

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(contents);
		if(matcher.find()){
			String src = matcher.group();

			src = StringUtils.removeStartIgnoreCase(src, "src=\"");
			src = StringUtils.removeEnd(src, "\"");
			return src;
		}

		return "";
	}

	/**
	 * 两数相加
	 * @param num1
	 * @param num2
	 * @return
	 */
	public static int add(String num1, String num2){
		num1 = defaultIfBlank(trimToEmpty(num1),"0");
		num2 = defaultIfBlank(trimToEmpty(num2),"0");

		return Integer.parseInt(num1) + Integer.parseInt(num2);
	}
	/**
	 * 两数相加
	 * @param num1
	 * @param num2
	 * @return
	 */
	public static int add(String num1, Integer num2){
		num1 = defaultIfBlank(trimToEmpty(num1),"0");

		return Integer.parseInt(num1) + (num2 == null ? 0 : num2);
	}
}
