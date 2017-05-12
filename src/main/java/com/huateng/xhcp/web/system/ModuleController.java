/**
 * 
 */
package com.huateng.xhcp.web.system;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.huateng.xhcp.security.SecurityContext;
import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.huateng.xhcp.model.ResponseInfo;
import com.huateng.xhcp.model.system.Account;
import com.huateng.xhcp.model.system.Module;
import com.huateng.xhcp.service.system.ModuleService;
import com.huateng.xhcp.util.HttpUtil;
import com.huateng.xhcp.util.Validator;

/**
 * 菜单Web Controller
 * @author sam.pan
 *
 */
@Controller
public class ModuleController {
	private static final Log LOGGER = LogFactory.getLog(ModuleController.class);
	
	private @Autowired @Setter @Getter ModuleService moduleService;
	
	/**
	 * 初始化菜单页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/module")
	public String initPage(){
		return "system/ModuleList";
	}
	
	/**
	 * 跳转到菜单新增页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/module/add")
	public String toAddPage(HttpServletRequest request){
		request.setAttribute("page", "add");
		return "system/ModuleUpdate";
	}
	
	/**
	 * 跳转到菜单更新页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/module/update")
	public String toUpdatePage(HttpServletRequest request){
		request.setAttribute("page", "update");
		return "system/ModuleUpdate";
	}
	/**
	 * 跳转到菜单查看页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/module/view")
	public String toViewPage(HttpServletRequest request){
		request.setAttribute("page", "view");
		return "system/ModuleUpdate";
	}
	/**
	 * 查询菜单信息(分页)
	 * @param module
	 * @param start
	 * @param limit
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/module/queryModulePage", method = RequestMethod.POST)
	public com.huateng.xhcp.web.page.Page queryModule(Module module, @RequestParam int start,@RequestParam int limit){
		Page<Module> list = (Page<Module>)this.moduleService.queryModule(module, start, limit);
		
		return new com.huateng.xhcp.web.page.Page(list);
	}

	/**
	 * 检查module_id是否存在
	 * @param module_id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/module/isModuleExists", method = RequestMethod.GET)
	public boolean isAccountExists(@RequestParam String module_id){
		Module module = new Module();
		module.setModule_id(module_id);
		List<Module> moduleList = this.moduleService.findModuleBy(module);
		
		if(moduleList == null || moduleList.isEmpty()){
			return false;
		}
		
		return true;
	}
	
	/**
	 * 查询菜单信息
	 * @param module
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/mgr/module/find", method = RequestMethod.POST)
	public List<Module> findModuleBy(Module module){
		return this.moduleService.findModuleBy(module);
	}
	
	/**
	 * 首页的菜单查询
	 * @param module
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/module/findMenu", method = RequestMethod.POST)
	public List<Module> findMenuBy(Module module){
		return this.moduleService.findMenuBy(module);
	}
	/**
	 * 首页的菜单查询
	 * @param module
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/module/findTreeMenu", method = RequestMethod.POST)
	public List<Module> findTreeMenuBy(Module module){
		return this.moduleService.findTreeMenuBy(module);
	}
	
	/**
	 * 根据module_id查找出所有父节点id
	 * @param module_id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/module/findNav", method = RequestMethod.GET)
	public List<Module> getModuleParents(@RequestParam String module_id){
		List<Module> moduleLists = this.moduleService.getModuleParents(module_id);
		Collections.reverse(moduleLists);
		return moduleLists;
	}
	
	/**
	 * 根据Account_id里面的条件查询菜单
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/mgr/module/findRoleMenu", method = RequestMethod.GET)
	public List<Module> findModuleByAccount(HttpServletRequest request){
		HttpSession session = request.getSession();
		Account account = (Account)session.getAttribute(SecurityContext.BACK_ACCOUNT);
		
		/* 把菜单保存到session中*/
		@SuppressWarnings("unchecked")
		List<Module> menuTree = (List<Module>)session.getAttribute("MenuTree");
		if(menuTree != null){
			return menuTree;
		}
		
		List<Module> tmpMenuTree = null;
        tmpMenuTree = this.moduleService.findModuleSuperUser();

		if(account.isSuperUser()){/*超级管理员不受权限控制的*/
			tmpMenuTree = this.moduleService.findModuleSuperUser();
		}else{
			tmpMenuTree = this.moduleService.findModuleByAccount(account.getAccount_id());
		}
		
		/* 保存菜单节点，方便操作*/
		Map<String, Module> map = new HashMap<String, Module>();
		for (int i = 0, length = tmpMenuTree.size(); i < length; i++) {
			Module module = tmpMenuTree.get(i);
			Integer type = module.getModule_type();
			
			if(type != null && type.intValue() == 2){
				map.put(module.getModule_id(), module);
			}
		}
		
		session.setAttribute("MenuTree", tmpMenuTree);
		session.setAttribute("LeafMenu", map);
		return tmpMenuTree;
	}
	
	/**
	 * 新增菜单(如果是菜单的话，会生成一条功能的菜单信息)
	 * @param module
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/module/insertModule", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> insertModule(Module module){
		
		try{
			ResponseInfo responseInfo = validator(module);
			if(ResponseInfo.FAILURE.equals(responseInfo.getStatus())){
				return HttpUtil.failure(responseInfo.getDesc());
			}
			
			this.moduleService.insertModule(module);
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("新增菜单失败!");
		}
		
		return HttpUtil.success("新增菜单成功!");
		
	}
	/*校验*/
	private ResponseInfo validator(Module module){
		if(module == null){
			return new ResponseInfo(ResponseInfo.FAILURE, "菜单信息为空!");
		}
		
		/*检查module_id为空判断*/
		String module_id = module.getModule_id();
		if(StringUtils.isBlank(module_id)){
			return new ResponseInfo(ResponseInfo.FAILURE, "菜单ID为空!");
		}
		
		/*检查module_id长度少于128个字符*/
		int moduleIdLength = Validator.charLength(module_id);
		if(moduleIdLength > 128){
			return new ResponseInfo(ResponseInfo.FAILURE, "菜单ID必须少于128 个字符!");
		}
		
		/*检查module_id是否包含特殊字符*/
		if(!module_id.matches("^[a-zA-Z0-9_]+$")){
			return new ResponseInfo(ResponseInfo.FAILURE, "菜单ID只能输入a-zA-Z0-9_字符!");
		}
		
		/*检查module_name为空判断*/
		String module_name = module.getModule_name();
		if(StringUtils.isBlank(module_name)){
			return new ResponseInfo(ResponseInfo.FAILURE, "菜单名称为空!");
		}
		
		/*检查module_name长度为96个字符*/
		int moduleNameLength = Validator.charLength(module_name);
		if(moduleNameLength > 96){
			return new ResponseInfo(ResponseInfo.FAILURE, "菜单名称必须少于96个字符!");
		}
		
		
		/*检查pmodule_id为空判断*/
		String pmodule_id = module.getPmodule_id();
		if(StringUtils.isBlank(pmodule_id)){
			return new ResponseInfo(ResponseInfo.FAILURE, "父菜单ID为空!");
		}
		
		/*检查pmodule_id长度少于128个字符*/
		int pmoduleIdLength = Validator.charLength(pmodule_id);
		if(pmoduleIdLength > 128){
			return new ResponseInfo(ResponseInfo.FAILURE, "父菜单ID必须少于128 个字符!");
		}
		
		/*检查pmodule_id是否包含特殊字符*/
		if(!pmodule_id.matches("^[a-zA-Z0-9_]+$")){
			return new ResponseInfo(ResponseInfo.FAILURE, "父菜单ID只能输入a-zA-Z0-9_字符!");
		}
		
		
		/*检查module_entry长度少于128个字符*/
		String module_entry = module.getModule_entry();
		int moduleEntryLength = Validator.charLength(module_entry);
		if(moduleEntryLength > 192){
			return new ResponseInfo(ResponseInfo.FAILURE, "菜单路径必须少于192 个字符!");
		}
		
		
		/*===其他字段都可以为空====*/
		
		return new ResponseInfo(ResponseInfo.SUCCESS, "校验成功");
	}
	/**
	 * 根据Id更新菜单信息
	 * @param module
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/module/updateModuleById", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> updateModuleById(Module module){
		try{
			ResponseInfo responseInfo = validator(module);
			if(ResponseInfo.FAILURE.equals(responseInfo.getStatus())){
				return HttpUtil.failure(responseInfo.getDesc());
			}
			
			this.moduleService.updateModuleById(module);
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("更新菜单失败!");
		}
		
		return HttpUtil.success("更新菜单成功!");
	}
	/**
	 * 根据Id删除菜单信息
	 * @param module_id
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/module/deleteModuleById", method = RequestMethod.GET)
	public ResponseEntity<ResponseInfo> deleteModuleById(String module_id){
		try{
			this.moduleService.deleteModuleById(module_id);
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("删除菜单失败!");
		}
		
		return HttpUtil.success("删除菜单成功!");
	}
}
