package com.huateng.xhcp.service.upload;

import com.huateng.xhcp.model.BaseModel;
import com.huateng.xhcp.model.ResponseInfo;

/**
 * 批量导入校验接口
 * 
 * @author shijunkai
 *
 */
public interface Validator<T extends BaseModel> {

	/**
	 * 批量导入校验方法
	 * 
	 * @param model
	 *            校验的数据
	 * @return 校验错误提示信息
	 */
	public ResponseInfo validate(T model);

}
