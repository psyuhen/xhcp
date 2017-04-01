//Html5Util.js
/**
 * HTML5的工具类
 */
var Html5Util = function(){
};

/**
 * 判断浏览器是否支持FormData对象
 * @returns
 */
Html5Util.supports_formdata = function(){
	return !!window.FormData;
};

/* HTML5的本地存储 */
var Storage = function(){};
/**
 * 判断浏览器是否支持localStorage对象
 * @returns
 */
Storage.supports = function(){
	return !!window.localStorage;
};

/**
 * 在localStorage中增加对象,但要在浏览器支持的情况下
 * @param key 名称，如果有重名，会被覆盖
 * @param value 值
 */
Storage.addItem = function(key, value){
	if(!Storage.supports()){
		return;
	}
	
	localStorage.setItem(key, value); 
};

/**
 * 获取localStorage中的key值，如果浏览器不支持，返回""
 * @param key
 * @returns
 */
Storage.getItem = function(key){
	if(!Storage.supports()){
		return "";
	}
	
	return localStorage.getItem(key); 
};
