package com.huateng.xhcp.service.imp.check;

import com.huateng.xhcp.model.ParamType;
import com.huateng.xhcp.model.ResponseInfo;
import com.huateng.xhcp.model.system.ParamChk;
import com.huateng.xhcp.service.check.ParamCheckService;
import com.huateng.xhcp.util.BeanUtil;
import com.huateng.xhcp.util.JsonUtil;
import com.huateng.xhcp.util.ReflectUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author shijunkai
 *
 */
@Service
public class ParamCheckServiceImpl implements ParamCheckService {
	private static final Log LOGGER = LogFactory.getLog(ParamCheckServiceImpl.class);

	@Override
	public ResponseInfo updateExecute(ParamChk paramChk,ParamType paramType) {
		Object result = null;

		String service_name = paramChk.getService_name();
		String method_name = paramChk.getMethod_name();
		String parameter_type = paramChk.getParameter_type();
		Object params = paramChk.getParams();

		List<Map> paramList;
		if (params instanceof String) {
			paramList = JsonUtil.readJsonToList((String) params);
		} else {
			paramList = (List<Map>) params;
		}
		Map<String, Object> paramMap = paramListToParamMap(paramList);
		if (paramMap != null && !paramMap.isEmpty()) {
			try {
				Class<?> parameterType = Class.forName(parameter_type);
				Object param = BeanUtil.map2Bean(paramMap, parameterType);
				if (param != null) {
					paramType.setParamType(parameterType);
					result = ReflectUtil.doReflectMethod(service_name, method_name, paramType, param);
				}
			} catch (Exception e) {
				LOGGER.error("复核通用方法执行操作失败：", e);
				return ResponseInfo.fail(paramChk.getOper_desc() + "失败！");
			}
		}

		if (result != null) {
			if (result instanceof ResponseInfo) {
				return (ResponseInfo) result;
			}
			if (result instanceof ResponseEntity<?>) {
				return (ResponseInfo) ((ResponseEntity) result).getBody();
			}
		}
		return ResponseInfo.success(paramChk.getOper_desc() + "成功！");
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Map<String, Object> paramListToParamMap(List<Map> paramList) {
		if (paramList != null && !paramList.isEmpty()) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			for (Map map : paramList) {
				Object input_value = map.get("input_value");
				if (input_value instanceof String) {
					if (((String) input_value).startsWith("[") && ((String) input_value).endsWith("]")) {
						input_value = JsonUtil.readJsonToList((String) input_value);
					} else if (((String) input_value).startsWith("{") && ((String) input_value).endsWith("}")) {
						input_value = JsonUtil.readJsonToMap((String) input_value);
					}
				}
				paramMap.put((String) map.get("input_en"), input_value);
			}
			return paramMap;
		}
		return null;
	}
}
