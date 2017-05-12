package com.huateng.xhcp.service.system;

import java.util.ArrayList;
import java.util.List;

import com.huateng.xhcp.model.system.Module;
import com.huateng.xhcp.model.system.RoleModule;
/**
 * @author chengz
 * */
public interface RoleModuleService {
	/**
	 * 查询角色模块
	 * @param roleModule
	 * @return
	 */
	List<RoleModule> queryRoleModule(RoleModule roleModule);
	
	/**
	 * 新增角色模块
	 * @param roleModule
	 */
	int addRoleModule(RoleModule roleModule);
	
	/**
	 * 编辑角色模块
	 * @param roleModuleList
	 */
	int updateRoleModule(ArrayList<ArrayList<RoleModule>> roleModuleList);
	
	/**
	 * 删除角色模块
	 * @param roleModule
	 */
	int deleteRoleModule(RoleModule roleModule);
	
	/**
	 * 只查询用户可支配的菜单
	 * @param account_id
	 * @return
	 */
	List<Module> findModuleByAccount(String account_id);
}
