/**
 * 
 */
package com.huateng.xhcp.web.system;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.huateng.xhcp.model.ResponseInfo;
import com.huateng.xhcp.model.system.Province;
import com.huateng.xhcp.service.system.ProvinceService;
import com.huateng.xhcp.util.HttpUtil;

/**
 * 国家省份信息 Controller类
 * @author sam.pan
 *
 */
@Controller
public class ProvinceController implements com.huateng.xhcp.service.upload.Validator<Province>{
	private static final Log LOGGER = LogFactory.getLog(ProvinceController.class);
	private @Autowired @Setter @Getter ProvinceService provinceService;
	/**
	 * 初始化页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/province/listmgr")
	public String toListPage(HttpServletRequest request){
		request.setAttribute("page", "mgr");
		request.setAttribute("module_id", "provincemgr");
		return "system/ProvinceList";
	}

	/**
	 * query页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/province/query")
	public String toQueryPage(HttpServletRequest request){
		request.setAttribute("page", "query");
		request.setAttribute("module_id", "provincemgr");
		return "system/ProvinceList";
	}
	
	/**
	 * 跳转新增页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/province/add")
	public String toAddPage(HttpServletRequest request){
		request.setAttribute("page", "add");
		request.setAttribute("module_id", "provincemgr");
		return "system/ProvinceAdd";
	}
	
	/**
	 * 跳转到更新页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/province/update")
	public String toUpdatePage(HttpServletRequest request){
		request.setAttribute("page", "update");
		request.setAttribute("module_id", "provincemgr");
		return "system/ProvinceAdd";
	}
	/**
	 * 跳转到查看页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/province/view")
	public String toViewPage(HttpServletRequest request){
		request.setAttribute("page", "view");
		request.setAttribute("module_id", request.getParameter("module_id"));
		return "system/ProvinceAdd";
	}
	
	/**
	 * 查询国家省份信息信息(分页)
	 * @param province
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/province/queryProvincePage", method = RequestMethod.POST)
	public com.huateng.xhcp.web.page.Page queryProvince(Province province){
		Page<Province> list = (Page<Province>)this.provinceService.queryProvince(province);
		
		return new com.huateng.xhcp.web.page.Page(list);
	}
	
	/**
	 * 根据Key查询国家省份信息信息
	 * @param province_code
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/province/queryByKey", method = RequestMethod.GET)
	public Province queryByKey(String province_code){
		return this.provinceService.queryByKey(province_code);
	}
	/**
	 * 新增国家省份信息信息
	 * @param province
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/province/addProvince", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> addProvince(Province province){
		try{
			ResponseInfo responseInfo = validate(province);
			if(ResponseInfo.FAILURE.equals(responseInfo.getStatus())){
				return HttpUtil.failure(responseInfo.getDesc());
			}
			
			int c = this.provinceService.addProvince(province);
			if(c==0){
				return HttpUtil.failure("新增国家省份信息失败!");
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("新增国家省份信息失败!");
		}
		
		return HttpUtil.success("新增国家省份信息成功!");
	}
	
	/*校验*/
	public ResponseInfo validate(Province province){
		if(province == null){
			return ResponseInfo.fail("国家省份信息信息为空!");
		}
		
		String province_code = province.getProvince_code();
		int province_codeLength = com.huateng.xhcp.util.Validator.mysql(province_code);
		if(province_codeLength > 10){
			return ResponseInfo.fail("省份编码必须少于10 个字符!");
		}

		String province_name = province.getProvince_name();
		int province_nameLength = com.huateng.xhcp.util.Validator.mysql(province_name);
		if(province_nameLength > 50){
			return ResponseInfo.fail("省份名称必须少于50 个字符!");
		}


		
		return ResponseInfo.success("校验成功");
	}
	
	
	/**
	 * 更新国家省份信息信息
	 * @param province
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/province/updateProvince", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> updateProvince(Province province){
		try{
			ResponseInfo responseInfo = validate(province);
			if(ResponseInfo.FAILURE.equals(responseInfo.getStatus())){
				return HttpUtil.failure(responseInfo.getDesc());
			}
			
			int c = this.provinceService.updateProvince(province);
			if(c == 0){
				return HttpUtil.failure("更新国家省份信息失败!");
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("更新国家省份信息失败!");
		}
		
		return HttpUtil.success("更新国家省份信息成功!");
	}
	
	/**
	 * 删除国家省份信息信息
	 * @param province
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/province/deleteProvince", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> deleteProvince(Province province){
		try{
			int c = this.provinceService.deleteProvince(province);
			if(c == 0){
				return HttpUtil.failure("删除国家省份信息失败!");
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("删除国家省份信息失败!");
		}
		
		return HttpUtil.success("删除国家省份信息成功!");
	}
	/**
	 * 批量删除国家省份信息信息
	 * @param province
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/province/deleteBatchProvince", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> deleteBatchProvince(List<Province> province){
		try{
			this.provinceService.deleteBatchProvince(province);
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("批量删除国家省份信息失败!");
		}
		
		return HttpUtil.success("批量删除国家省份信息成功!");
	}
}
