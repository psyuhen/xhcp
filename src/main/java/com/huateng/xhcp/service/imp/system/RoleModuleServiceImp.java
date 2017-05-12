package com.huateng.xhcp.service.imp.system;

import java.util.ArrayList;
import java.util.List;

import com.huateng.xhcp.security.SecurityContext;
import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huateng.xhcp.mapper.system.RoleModuleMapper;
import com.huateng.xhcp.model.system.Module;
import com.huateng.xhcp.model.system.RoleModule;
import com.huateng.xhcp.service.system.ModuleService;
import com.huateng.xhcp.service.system.RoleModuleService;
/**
 * @author chengz
 * 
 * */
@Service
public class RoleModuleServiceImp implements RoleModuleService{
	private @Autowired @Setter @Getter RoleModuleMapper roleModuleMapper;
	private @Autowired @Setter @Getter ModuleService moduleService;
	/**
	 * 查询角色模块
	 * 
	 * @param roleModule
	 * @return
	 */
	@Override
	public List<RoleModule> queryRoleModule(RoleModule roleModule) {
		return this.roleModuleMapper.queryRoleModule(roleModule);
	}
	/**
	 * 新增角色模块
	 * @param roleModule
	 */
	@Override
	public int addRoleModule(RoleModule roleModule) {
		return this.roleModuleMapper.addRoleModule(roleModule);
	}
	
	/**
	 * 删除角色模块
	 * @param roleModule
	 */
	@Override
	public int deleteRoleModule(RoleModule roleModule) {
		return this.roleModuleMapper.deleteRoleModule(roleModule);
	}
	/**
	 * 编辑角色
	 * @param roleModuleList
	 * 				新增的模块List
	 * @param roleModuleList
	 * 				删除的模块List
	 */
	@Override
	public int updateRoleModule(ArrayList<ArrayList<RoleModule>> roleModuleList) {
		/*获取需要新增或删除的模块*/
		ArrayList<RoleModule> addRoleModuleList = roleModuleList.get(0);
		ArrayList<RoleModule> delRoleModuleList = roleModuleList.get(1);
		
		if(null == addRoleModuleList){
			addRoleModuleList = new ArrayList<RoleModule>();
		}
		if(null == delRoleModuleList){
			delRoleModuleList = new ArrayList<RoleModule>();
		}
		/*遍历删除*/
		for(RoleModule roleModule : delRoleModuleList){
			this.roleModuleMapper.deleteRoleModule(roleModule);
		}
		/*遍历新增*/
		for(RoleModule roleModule : addRoleModuleList){
			this.roleModuleMapper.addRoleModule(roleModule);
		}
		return 1;
	}
	
	/**
	 * 只查询用户可支配的菜单
	 * @param account_id
	 * 				用户
	 * @return
	 */
	public List<Module> findModuleByAccount(String account_id){
		if(SecurityContext.isSuperUser(account_id)){
			return this.moduleService.findModuleSuperUser();
		}

		/*根据用户信息查询所有子节点菜单*/
		final List<Module> leafNodeByAccount = this.roleModuleMapper.findLeafNodeByAccount(account_id);
		if(leafNodeByAccount == null || leafNodeByAccount.isEmpty()){
			return null;
		}

		List<Module> allList = new ArrayList<Module>();
		allList.addAll(leafNodeByAccount);

		queryModuleByPModuleId(allList, leafNodeByAccount);
		return allList;
	}


	/**
	 * 递归查询菜单信息
	 * @param allList
	 * @param list
	 */
	private void queryModuleByPModuleId(List<Module> allList, List<Module> list){
		final List<Module> modules = this.moduleService.queryByPModuleId(list);
		if(modules == null || modules.isEmpty()){
			return;
		}

		allList.addAll(modules);

		queryModuleByPModuleId(allList, modules);
	}
}
