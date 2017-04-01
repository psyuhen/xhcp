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
import com.huateng.xhcp.model.system.CityTown;
import com.huateng.xhcp.service.system.CityTownService;
import com.huateng.xhcp.util.HttpUtil;

/**
 * 城市城区信息 Controller类
 * @author sam.pan
 *
 */
@Controller
public class CityTownController implements com.huateng.xhcp.service.upload.Validator<CityTown>{
	private static final Log LOGGER = LogFactory.getLog(CityTownController.class);
	private @Autowired @Setter @Getter CityTownService cityTownService;
	/**
	 * 初始化页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/citytown/listmgr")
	public String toListPage(HttpServletRequest request){
		request.setAttribute("page", "mgr");
		request.setAttribute("module_id", "citytownmgr");
		return "system/CityTownList";
	}

	/**
	 * query页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/citytown/query")
	public String toQueryPage(HttpServletRequest request){
		request.setAttribute("page", "query");
		request.setAttribute("module_id", "citytownmgr");
		return "system/CityTownList";
	}
	
	/**
	 * 跳转新增页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/citytown/add")
	public String toAddPage(HttpServletRequest request){
		request.setAttribute("page", "add");
		request.setAttribute("module_id", "citytownmgr");
		return "system/CityTownAdd";
	}
	
	/**
	 * 跳转到更新页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/citytown/update")
	public String toUpdatePage(HttpServletRequest request){
		request.setAttribute("page", "update");
		request.setAttribute("module_id", "citytownmgr");
		return "system/CityTownAdd";
	}
	/**
	 * 跳转到查看页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/citytown/view")
	public String toViewPage(HttpServletRequest request){
		request.setAttribute("page", "view");
		request.setAttribute("module_id", request.getParameter("module_id"));
		return "system/CityTownAdd";
	}
	
	/**
	 * 查询城市城区信息信息(分页)
	 * @param cityTown
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/citytown/queryCityTownPage", method = RequestMethod.POST)
	public com.huateng.xhcp.web.page.Page queryCityTown(CityTown cityTown){
		Page<CityTown> list = (Page<CityTown>)this.cityTownService.queryCityTown(cityTown);
		
		return new com.huateng.xhcp.web.page.Page(list);
	}
	/**
	 * 查询城市城区信息信息
	 * @param cityTown
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/citytown/queryBy", method = RequestMethod.POST)
	public List<CityTown> queryBy(CityTown cityTown){
		return this.cityTownService.queryBy(cityTown);
	}
	
	/**
	 * 根据Key查询城市城区信息信息
	 * @param town_code
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/citytown/queryByKey", method = RequestMethod.GET)
	public CityTown queryByKey(String town_code){
		return this.cityTownService.queryByKey(town_code);
	}
	/**
	 * 新增城市城区信息信息
	 * @param cityTown
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/citytown/addCityTown", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> addCityTown(CityTown cityTown){
		try{
			ResponseInfo responseInfo = validate(cityTown);
			if(ResponseInfo.FAILURE.equals(responseInfo.getStatus())){
				return HttpUtil.failure(responseInfo.getDesc());
			}
			
			int c = this.cityTownService.addCityTown(cityTown);
			if(c==0){
				return HttpUtil.failure("新增城市城区信息失败!");
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("新增城市城区信息失败!");
		}
		
		return HttpUtil.success("新增城市城区信息成功!");
	}
	
	/*校验*/
	public ResponseInfo validate(CityTown cityTown){
		if(cityTown == null){
			return ResponseInfo.fail("城市城区信息信息为空!");
		}
		
		String town_code = cityTown.getTown_code();
		int town_codeLength = com.huateng.xhcp.util.Validator.mysql(town_code);
		if(town_codeLength > 10){
			return ResponseInfo.fail("城区编码必须少于10 个字符!");
		}

		String city_code = cityTown.getCity_code();
		int city_codeLength = com.huateng.xhcp.util.Validator.mysql(city_code);
		if(city_codeLength > 10){
			return ResponseInfo.fail("城市编码必须少于10 个字符!");
		}

		String town_name = cityTown.getTown_name();
		int town_nameLength = com.huateng.xhcp.util.Validator.mysql(town_name);
		if(town_nameLength > 50){
			return ResponseInfo.fail("城区名称必须少于50 个字符!");
		}

		String post_code = cityTown.getPost_code();
		int post_codeLength = com.huateng.xhcp.util.Validator.mysql(post_code);
		if(post_codeLength > 10){
			return ResponseInfo.fail("邮政编码必须少于10 个字符!");
		}


		
		return ResponseInfo.success("校验成功");
	}
	
	
	/**
	 * 更新城市城区信息信息
	 * @param cityTown
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/citytown/updateCityTown", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> updateCityTown(CityTown cityTown){
		try{
			ResponseInfo responseInfo = validate(cityTown);
			if(ResponseInfo.FAILURE.equals(responseInfo.getStatus())){
				return HttpUtil.failure(responseInfo.getDesc());
			}
			
			int c = this.cityTownService.updateCityTown(cityTown);
			if(c == 0){
				return HttpUtil.failure("更新城市城区信息失败!");
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("更新城市城区信息失败!");
		}
		
		return HttpUtil.success("更新城市城区信息成功!");
	}
	
	/**
	 * 删除城市城区信息信息
	 * @param cityTown
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/citytown/deleteCityTown", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> deleteCityTown(CityTown cityTown){
		try{
			int c = this.cityTownService.deleteCityTown(cityTown);
			if(c == 0){
				return HttpUtil.failure("删除城市城区信息失败!");
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("删除城市城区信息失败!");
		}
		
		return HttpUtil.success("删除城市城区信息成功!");
	}
	/**
	 * 批量删除城市城区信息信息
	 * @param cityTown
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/citytown/deleteBatchCityTown", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> deleteBatchCityTown(List<CityTown> cityTown){
		try{
			this.cityTownService.deleteBatchCityTown(cityTown);
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("批量删除城市城区信息失败!");
		}
		
		return HttpUtil.success("批量删除城市城区信息成功!");
	}
}
