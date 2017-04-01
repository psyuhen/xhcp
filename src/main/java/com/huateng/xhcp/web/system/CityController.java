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
import com.huateng.xhcp.model.system.City;
import com.huateng.xhcp.service.system.CityService;
import com.huateng.xhcp.util.HttpUtil;

/**
 * 国家城市信息 Controller类
 * @author sam.pan
 *
 */
@Controller
public class CityController implements com.huateng.xhcp.service.upload.Validator<City>{
	private static final Log LOGGER = LogFactory.getLog(CityController.class);
	private @Autowired @Setter @Getter CityService cityService;
	/**
	 * 初始化页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/city/listmgr")
	public String toListPage(HttpServletRequest request){
		request.setAttribute("page", "mgr");
		request.setAttribute("module_id", "citymgr");
		return "system/CityList";
	}

	/**
	 * query页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/city/query")
	public String toQueryPage(HttpServletRequest request){
		request.setAttribute("page", "query");
		request.setAttribute("module_id", "citymgr");
		return "system/CityList";
	}
	
	/**
	 * 跳转新增页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/city/add")
	public String toAddPage(HttpServletRequest request){
		request.setAttribute("page", "add");
		request.setAttribute("module_id", "citymgr");
		return "system/CityAdd";
	}
	
	/**
	 * 跳转到更新页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/city/update")
	public String toUpdatePage(HttpServletRequest request){
		request.setAttribute("page", "update");
		request.setAttribute("module_id", "citymgr");
		return "system/CityAdd";
	}
	/**
	 * 跳转到查看页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/city/view")
	public String toViewPage(HttpServletRequest request){
		request.setAttribute("page", "view");
		request.setAttribute("module_id", request.getParameter("module_id"));
		return "system/CityAdd";
	}
	
	/**
	 * 查询国家城市信息信息(分页)
	 * @param city
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/city/queryCityPage", method = RequestMethod.POST)
	public com.huateng.xhcp.web.page.Page queryCity(City city){
		Page<City> list = (Page<City>)this.cityService.queryCity(city);
		
		return new com.huateng.xhcp.web.page.Page(list);
	}
	/**
	 * 查询国家城市信息信息
	 * @param city
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/city/queryBy", method = RequestMethod.POST)
	public List<City> queryBy(City city){
		return this.cityService.queryBy(city);
	}
	
	/**
	 * 根据Key查询国家城市信息信息
	 * @param city_code
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/city/queryByKey", method = RequestMethod.GET)
	public City queryByKey(String city_code){
		return this.cityService.queryByKey(city_code);
	}
	/**
	 * 新增国家城市信息信息
	 * @param city
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/city/addCity", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> addCity(City city){
		try{
			ResponseInfo responseInfo = validate(city);
			if(ResponseInfo.FAILURE.equals(responseInfo.getStatus())){
				return HttpUtil.failure(responseInfo.getDesc());
			}
			
			int c = this.cityService.addCity(city);
			if(c==0){
				return HttpUtil.failure("新增国家城市信息失败!");
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("新增国家城市信息失败!");
		}
		
		return HttpUtil.success("新增国家城市信息成功!");
	}
	
	/*校验*/
	public ResponseInfo validate(City city){
		if(city == null){
			return ResponseInfo.fail("国家城市信息信息为空!");
		}
		
		String city_code = city.getCity_code();
		int city_codeLength = com.huateng.xhcp.util.Validator.mysql(city_code);
		if(city_codeLength > 10){
			return ResponseInfo.fail("城市编码必须少于10 个字符!");
		}

		String province_code = city.getProvince_code();
		int province_codeLength = com.huateng.xhcp.util.Validator.mysql(province_code);
		if(province_codeLength > 10){
			return ResponseInfo.fail("省份编码必须少于10 个字符!");
		}

		String city_name = city.getCity_name();
		int city_nameLength = com.huateng.xhcp.util.Validator.mysql(city_name);
		if(city_nameLength > 50){
			return ResponseInfo.fail("城市名称必须少于50 个字符!");
		}

		String city_zone = city.getCity_zone();
		int city_zoneLength = com.huateng.xhcp.util.Validator.mysql(city_zone);
		if(city_zoneLength > 10){
			return ResponseInfo.fail("区号必须少于10 个字符!");
		}


		
		return ResponseInfo.success("校验成功");
	}
	
	
	/**
	 * 更新国家城市信息信息
	 * @param city
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/city/updateCity", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> updateCity(City city){
		try{
			ResponseInfo responseInfo = validate(city);
			if(ResponseInfo.FAILURE.equals(responseInfo.getStatus())){
				return HttpUtil.failure(responseInfo.getDesc());
			}
			
			int c = this.cityService.updateCity(city);
			if(c == 0){
				return HttpUtil.failure("更新国家城市信息失败!");
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("更新国家城市信息失败!");
		}
		
		return HttpUtil.success("更新国家城市信息成功!");
	}
	
	/**
	 * 删除国家城市信息信息
	 * @param city
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/city/deleteCity", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> deleteCity(City city){
		try{
			int c = this.cityService.deleteCity(city);
			if(c == 0){
				return HttpUtil.failure("删除国家城市信息失败!");
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("删除国家城市信息失败!");
		}
		
		return HttpUtil.success("删除国家城市信息成功!");
	}
	/**
	 * 批量删除国家城市信息信息
	 * @param city
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/city/deleteBatchCity", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> deleteBatchCity(List<City> city){
		try{
			this.cityService.deleteBatchCity(city);
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("批量删除国家城市信息失败!");
		}
		
		return HttpUtil.success("批量删除国家城市信息成功!");
	}
}
