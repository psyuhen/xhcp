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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.huateng.xhcp.model.ResponseInfo;
import com.huateng.xhcp.model.system.Role;
import com.huateng.xhcp.service.system.RoleService;
import com.huateng.xhcp.util.HttpUtil;

/**
 * 角色信息管理
 * @author chengz
 * */
@Controller
public class RoleController implements com.huateng.xhcp.service.upload.Validator<Role>{
	private static final Log LOGGER = LogFactory.getLog(RoleController.class);
	private @Autowired @Setter @Getter RoleService roleService;
	
	
	/**
	 * 初始化登录页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/role")
	public String initPage(){
		return "system/RoleList";
	}
	
	/**
	 * 跳转到模块维护页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/role/account")
	public String toAccountPage(HttpServletRequest request){
		return "system/RoleAccount";
	}
	
	/**
	 * 跳转到用户维护页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/role/module")
	public String toModulePage(HttpServletRequest request){
		return "system/RoleModule";
	}
	
	/**
	 * 跳转到角色新增页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/role/add")
	public String toAddPage(HttpServletRequest request){
		return "system/RoleUpdate";
	}
	
	/**
	 * 跳转到角色编辑页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/role/update")
	public String toUpdatePage(HttpServletRequest request){
		return "system/RoleUpdate";
	}
	
	/**
	 * 查询角色信息(分页)
	 * @param role
	 * @param start
	 * @param limit
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/role/queryRolePage", method = RequestMethod.POST)
	public com.huateng.xhcp.web.page.Page queryRole(Role role, @RequestParam int start,@RequestParam int limit){
		Page<Role> list = (Page<Role>)this.roleService.queryRole(role, start, limit);
		
		return new com.huateng.xhcp.web.page.Page(list);
	}
	
	/**
	 * 查询角色信息(不分页)
	 * @param role
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/role/queryRole", method = RequestMethod.POST)
	public List<Role> queryRole(Role role){
		return this.roleService.queryRole(role);
	}
	
	/**
	 * 新增角色信息
	 * @param role
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/role/addRole", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> addRole(Role role){
		try{
			ResponseInfo responseInfo = validate(role);
			if(ResponseInfo.FAILURE.equals(responseInfo.getStatus())){
				return HttpUtil.failure(responseInfo.getDesc());
			}
			
			this.roleService.addRole(role);
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("新增角色失败!");
		}
		
		return HttpUtil.success("新增角色成功!");
	}
	
	/**
	 * 编辑角色信息
	 * @param role
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/role/updateRole", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> updateRole(Role role){
		try{
			ResponseInfo responseInfo = validate(role);
			if(ResponseInfo.FAILURE.equals(responseInfo.getStatus())){
				return HttpUtil.failure(responseInfo.getDesc());
			}
			
			this.roleService.updateRole(role);
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("编辑角色失败!");
		}
		
		return HttpUtil.success("编辑角色成功!");
	}
	
	/**
	 * 删除角色信息
	 * @param role
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/role/deleteRole", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> deleteRole(Role role){
		try{
			this.roleService.deleteRole(role);
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("删除角色信息!");
		}
		
		return HttpUtil.success("删除角色信息!");
	}
	
	/**
	 *验证角色信息
	 * @param role
	 * @return
	 */
	public ResponseInfo validate(Role role){
		String role_name = role.getRole_name();
		String role_desc = role.getRole_desc();
		
		if(null == role_name || "".equals(role_name)){
			return ResponseInfo.fail("角色名称不为空！");
		}
		if(role_name.length() >96){
			return ResponseInfo.fail("角色名称长度不大于96！");
		}
		if(null != role_desc && role_desc.length() >192){
			return ResponseInfo.fail("角色描述长度不大于192！");
		}
		
		return ResponseInfo.success("验证通过！");
	}
}
