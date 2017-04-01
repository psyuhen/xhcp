package com.huateng.xhcp.service.check;

import com.huateng.xhcp.model.ParamType;
import com.huateng.xhcp.model.ResponseInfo;
import com.huateng.xhcp.model.system.ParamChk;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author shijunkai
 *
 */
public interface ParamCheckService {

	/**
	 * 不经复核执行操作
	 * 
	 * @param paramChk
	 *            复核参数信息
	 * @return
	 */
	public ResponseInfo updateExecute(ParamChk paramChk,ParamType paramType);

	/**
	 * 将复核参数详细信息列表转换为参数对象Map
	 * 
	 * @param paramList
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map<String, Object> paramListToParamMap(List<Map> paramList);

}
