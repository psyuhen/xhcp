package com.huateng.xhcp.mapper.system;

import java.util.List;

import com.huateng.xhcp.model.system.Module;
import com.huateng.xhcp.model.system.RoleModule;

/**
 * 角色模块Mapper
 * @author chengz
 * */
public interface RoleModuleMapper {
	/**
	 * 查询角色
	 * @param roleModule
	 * @return
	 */
	List<RoleModule> queryRoleModule(RoleModule roleModule);

	/**
	 * 新增角色
	 * @param roleModule
	 */
	int addRoleModule(RoleModule roleModule);
	/**
	 * 删除角色
	 * @param roleModule
	 */
	int deleteRoleModule(RoleModule roleModule);
	
	/**
	 * 只查询用户可支配的菜单
	 * @param account_id
	 * @return
	 */
	/*List<Module> findModuleByAccount(String account_id);*/
	/**
	 * 只查询用户可支配的菜单节点
	 * @param account_id
	 * @return
	 */
	List<Module> findLeafNodeByAccount(String account_id);
}
