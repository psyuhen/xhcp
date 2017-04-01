package com.huateng.xhcp.web.check;

import com.huateng.xhcp.model.ParamType;
import com.huateng.xhcp.model.ResponseInfo;
import com.huateng.xhcp.model.system.ParamChk;
import com.huateng.xhcp.service.check.ParamCheckService;
import com.huateng.xhcp.util.HttpUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 参数复核操纵动作的Controller
 * 
 * @author shijunkai
 *
 */
@Controller
@RequestMapping("/mgr/paramChk")
public class ParamCheckController {
	private static final Log LOGGER = LogFactory.getLog(ParamCheckController.class);
	private @Autowired ParamCheckService paramCheckService;

	/**
	 * 参数复核通用提交方法
	 * 
	 * @param paramChk
	 *            参数复核信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/commonCheckSubmit", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> commonCheckSubmit(ParamChk paramChk,HttpServletRequest request,HttpServletResponse response) {
		ResponseInfo responseInfo;
		try {
			ParamType paramType = new ParamType(null, request, response);
			responseInfo = paramCheckService.updateExecute(paramChk, paramType);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure(paramChk.getOper_desc() + "提交失败！");
		}

		return HttpUtil.createOkResponse(responseInfo);
	}
}
