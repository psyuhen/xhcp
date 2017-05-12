package com.huateng.xhcp.service.imp.system;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huateng.xhcp.mapper.system.RoleMapper;
import com.huateng.xhcp.model.system.AccountRole;
import com.huateng.xhcp.model.system.Role;
import com.huateng.xhcp.model.system.RoleModule;
import com.huateng.xhcp.service.system.AccountRoleService;
import com.huateng.xhcp.service.system.RoleModuleService;
import com.huateng.xhcp.service.system.RoleService;
import com.huateng.xhcp.web.page.PageHelper;

/**
 * @author chengz
 * 
 * */
@Service
public class RoleServiceImp implements RoleService {
	private static final Log LOGGER =  LogFactory.getLog(RoleServiceImp.class);
	private @Autowired @Setter @Getter RoleMapper roleMapper;
	private @Autowired @Setter @Getter AccountRoleService accountRoleService;/*角色权限Service*/
	private @Autowired @Setter @Getter RoleModuleService roleModuleService;/*角色模块Service*/ 

	/**
	 * 查询角色
	 * 
	 * @param role
	 * @param start
	 * @param limit
	 * @return
	 */
	@Override
	public List<Role> queryRole(Role role, int start, int limit) {
		/* 分页 */
		PageHelper.startPage(start, limit);
		return this.roleMapper.queryRole(role);
	}

	/**
	 * 查询角色
	 * 
	 * @param role
	 * @return
	 */
	@Override
	public List<Role> queryRole(Role role) {
		return this.roleMapper.queryRole(role);
	}
	/**
	 * 新增角色
	 * @param role
	 */
	@Override
	public int addRole(Role role) {
		return this.roleMapper.addRole(role);
	}
	
	/**
	 * 编辑角色
	 * @param role
	 */
	@Override
	public int updateRole(Role role) {
		return this.roleMapper.updateRole(role);
	}
	
	/**
	 * 删除角色
	 * @param role
	 */
	@Override
	public int deleteRole(Role role) {
		/*删除角色权限配置*/
		AccountRole accountRole = new AccountRole();
		accountRole.setRole_id(role.getRole_id());
		this.accountRoleService.deleteAccountRole(accountRole);
		/*删除角色模块*/
		RoleModule roleModule = new RoleModule();
		roleModule.setRole_id(role.getRole_id());
		this.roleModuleService.deleteRoleModule(roleModule);
		
		return this.roleMapper.deleteRole(role);
	}
	
	
	
	/**
	 *判断角色是否存在
	 * @param role
	 * @return
	 */
	private boolean isExist(Role role){
		String role_id = role.getRole_id();
		if(null == role_id || "".equals(role_id)){
			return false;
		}
		List<Role> list = null;
		try {
			Role _role = new Role();
			_role.setRole_id(role_id);
			list = queryRole(_role);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		
		return !list.isEmpty();
	}
}
