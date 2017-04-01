/**
 * 
 */
package com.huateng.xhcp.web.nav;

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
import com.huateng.xhcp.model.nav.NavInfo;
import com.huateng.xhcp.service.nav.NavInfoService;
import com.huateng.xhcp.util.HttpUtil;

/**
 * 导航信息 Controller类
 * @author sam.pan
 *
 */
@Controller
public class NavInfoController implements com.huateng.xhcp.service.upload.Validator<NavInfo>{
	private static final Log LOGGER = LogFactory.getLog(NavInfoController.class);
	private @Autowired @Setter @Getter NavInfoService navInfoService;
	/**
	 * 初始化页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/nav/listmgr")
	public String toListPage(HttpServletRequest request){
		request.setAttribute("page", "mgr");
		request.setAttribute("module_id", "navmgr");
		return "nav/NavInfoList";
	}

	/**
	 * query页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/nav/query")
	public String toQueryPage(HttpServletRequest request){
		request.setAttribute("page", "query");
		request.setAttribute("module_id", "navmgr");
		return "nav/NavInfoList";
	}
	
	/**
	 * 跳转新增页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/nav/add")
	public String toAddPage(HttpServletRequest request){
		request.setAttribute("page", "add");
		request.setAttribute("module_id", "navmgr");
		return "nav/NavInfoAdd";
	}
	
	/**
	 * 跳转到更新页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/nav/update")
	public String toUpdatePage(HttpServletRequest request){
		request.setAttribute("page", "update");
		request.setAttribute("module_id", "navmgr");
		return "nav/NavInfoAdd";
	}
	/**
	 * 跳转到查看页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/nav/view")
	public String toViewPage(HttpServletRequest request){
		request.setAttribute("page", "view");
		request.setAttribute("module_id", request.getParameter("module_id"));
		return "nav/NavInfoAdd";
	}
	
	/**
	 * 查询导航信息信息(分页)
	 * @param navInfo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/nav/queryNavInfoPage", method = RequestMethod.POST)
	public com.huateng.xhcp.web.page.Page queryNavInfo(NavInfo navInfo){
		Page<NavInfo> list = (Page<NavInfo>)this.navInfoService.queryNavInfo(navInfo);
		
		return new com.huateng.xhcp.web.page.Page(list);
	}
	
	/**
	 * 根据Key查询导航信息信息
	 * @param nav_id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/nav/queryByKey", method = RequestMethod.GET)
	public NavInfo queryByKey(String nav_id){
		return this.navInfoService.queryByKey(nav_id);
	}
	/**
	 * 新增导航信息信息
	 * @param navInfo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/nav/addNavInfo", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> addNavInfo(NavInfo navInfo){
		try{
			ResponseInfo responseInfo = validate(navInfo);
			if(ResponseInfo.FAILURE.equals(responseInfo.getStatus())){
				return HttpUtil.failure(responseInfo.getDesc());
			}
			
			int c = this.navInfoService.addNavInfo(navInfo);
			if(c==0){
				return HttpUtil.failure("新增导航信息失败!");
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("新增导航信息失败!");
		}
		
		return HttpUtil.success("新增导航信息成功!");
	}
	
	/*校验*/
	public ResponseInfo validate(NavInfo navInfo){
		if(navInfo == null){
			return ResponseInfo.fail("导航信息信息为空!");
		}
		
		String nav_id = navInfo.getNav_id();
		int nav_idLength = com.huateng.xhcp.util.Validator.charLength(nav_id);
		if(nav_idLength > 10){
			return ResponseInfo.fail("导航ID必须少于10 个字符!");
		}

		String pnav_id = navInfo.getPnav_id();
		int pnav_idLength = com.huateng.xhcp.util.Validator.charLength(pnav_id);
		if(pnav_idLength > 10){
			return ResponseInfo.fail("导航父ID必须少于10 个字符!");
		}

		String name = navInfo.getName();
		int nameLength = com.huateng.xhcp.util.Validator.charLength(name);
		if(nameLength > 30){
			return ResponseInfo.fail("导航名称必须少于30 个字符!");
		}

		String url = navInfo.getUrl();
		int urlLength = com.huateng.xhcp.util.Validator.charLength(url);
		if(urlLength > 500){
			return ResponseInfo.fail("导航地址必须少于500 个字符!");
		}

		String is_out_link = navInfo.getIs_out_link();
		int is_out_linkLength = com.huateng.xhcp.util.Validator.charLength(is_out_link);
		if(is_out_linkLength > 1){
			return ResponseInfo.fail("是否为外连导航必须少于1 个字符!");
		}

		String is_default = navInfo.getIs_default();
		int is_defaultLength = com.huateng.xhcp.util.Validator.charLength(is_default);
		if(is_defaultLength > 1){
			return ResponseInfo.fail("是否为默认显示必须少于1 个字符!");
		}

		String order_id = navInfo.getOrder_id();
		int order_idLength = com.huateng.xhcp.util.Validator.charLength(order_id);
		if(order_idLength > 10){
			return ResponseInfo.fail("排序必须少于10 个字符!");
		}


		
		return ResponseInfo.success("校验成功");
	}
	
	
	/**
	 * 更新导航信息信息
	 * @param navInfo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/nav/updateNavInfo", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> updateNavInfo(NavInfo navInfo){
		try{
			ResponseInfo responseInfo = validate(navInfo);
			if(ResponseInfo.FAILURE.equals(responseInfo.getStatus())){
				return HttpUtil.failure(responseInfo.getDesc());
			}
			
			int c = this.navInfoService.updateNavInfo(navInfo);
			if(c == 0){
				return HttpUtil.failure("更新导航信息失败!");
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("更新导航信息失败!");
		}
		
		return HttpUtil.success("更新导航信息成功!");
	}
	
	/**
	 * 删除导航信息信息
	 * @param navInfo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/nav/deleteNavInfo", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> deleteNavInfo(NavInfo navInfo){
		try{
			int c = this.navInfoService.deleteNavInfo(navInfo);
			if(c == 0){
				return HttpUtil.failure("删除导航信息失败!");
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("删除导航信息失败!");
		}
		
		return HttpUtil.success("删除导航信息成功!");
	}
	/**
	 * 批量删除导航信息信息
	 * @param navInfo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/nav/deleteBatchNavInfo", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> deleteBatchNavInfo(List<NavInfo> navInfo){
		try{
			this.navInfoService.deleteBatchNavInfo(navInfo);
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("批量删除导航信息失败!");
		}
		
		return HttpUtil.success("批量删除导航信息成功!");
	}
}
