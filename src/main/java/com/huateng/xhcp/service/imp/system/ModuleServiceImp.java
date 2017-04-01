/**
 * 
 */
package com.huateng.xhcp.service.imp.system;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huateng.xhcp.mapper.system.ModuleMapper;
import com.huateng.xhcp.model.system.Module;
import com.huateng.xhcp.service.system.ModuleService;
import com.huateng.xhcp.web.page.PageHelper;

/**
 * @author pansen
 *
 */
@Service
public class ModuleServiceImp implements ModuleService {
	private static final Log LOGGER = LogFactory.getLog(ModuleServiceImp.class);

	private @Autowired @Setter @Getter ModuleMapper moduleMapper;
	/**
	 * 根据module里面的条件查询菜单(数据列表查询)
	 * @param module
	 * @return
	 */
	public List<Module> queryModule(Module module, int start, int limit){
		/* 分页  */
		PageHelper.startPage(start, limit);
		return this.moduleMapper.queryModule(module);
	}
	
	/**
	 * 根据account_id里面的条件查询菜单
	 * @param account_id
	 * @return
	 */
	public List<Module> findModuleByAccount(String account_id){
		return this.moduleMapper.findModuleByAccount(account_id);
	}
	/**
	 * 查询所有状态不为3而且不隐藏的菜单
	 * @return
	 */
	public List<Module> findModuleSuperUser(){
		return this.moduleMapper.findModuleSuperUser();
	}
	/**
	 * 根据module里面的条件查询菜单
	 * @param module
	 * @return
	 */
	@Override
	public List<Module> findModuleBy(Module module){
		return this.moduleMapper.findModuleBy(module);
	}
	
	/**
	 * 首页的菜单查询
	 * @param module
	 * @return
	 */
	@Override
	public List<Module> findMenuBy(Module module){
		return this.moduleMapper.findMenuBy(module);
	}
	/**
	 * 首页的树菜单查询
	 * @param module
	 * @return
	 */
	@Override
	public List<Module> findTreeMenuBy(Module module){
		return this.moduleMapper.findTreeMenuBy(module);
	}
	
	/**
	 * 根据module_id查找出所有父节点id
	 * @param module_id
	 * @return
	 */
	public List<Module> getModuleParents(String module_id){
		return this.moduleMapper.getModuleParents(module_id);
	}

	/**
	 * 根据菜单Id获取菜单名称
	 * @param module_id
	 * @return
	 */
	@Override
	public String getModuleNameById(String module_id) {
		Module module = new Module();
		module.setModule_id(module_id);
		List<Module> list = this.moduleMapper.findModuleBy(module);
		if (list != null && !list.isEmpty()) {
			return list.get(0).getModule_name();
		}
		return null;
	}
	
	/**
	 * 根据菜单Id获取菜单url
	 * @param module_id
	 * @return
	 */
	@Override
	public String getModuleEntryById(String module_id) {
		Module module = new Module();
		module.setModule_id(module_id);
		List<Module> list = this.moduleMapper.findModuleBy(module);
		if (list != null && !list.isEmpty()) {
			return list.get(0).getModule_entry();
		}
		return null;
	}
	
	/**
	 * 新增菜单(如果是菜单的话，会生成一条功能的菜单信息)
	 * @param module
	 */
	public void insertModule(Module module){
		
		String subModuleId = module.getModule_id();
		String modulePid = module.getPmodule_id();
		Integer moduleType = module.getModule_type();
		
		/* 增加一条菜单功能信息*/
		if(2 == moduleType.intValue()){
			module.setModule_id(subModuleId + "Query");
			module.setPmodule_id(subModuleId);
			module.setModule_type(3);
			this.moduleMapper.insertModule(module);
		}
		
		module.setModule_id(subModuleId);
		module.setPmodule_id(modulePid);
		module.setModule_type(moduleType);
		this.moduleMapper.insertModule(module);
	}
	/**
	 * 根据Id更新菜单信息
	 * @param module
	 */
	public void updateModuleById(Module module){
		this.moduleMapper.updateModuleById(module);
	}
	/**
	 * 根据Id删除菜单信息
	 * @param module_id
	 */
	public void deleteModuleById(String module_id){
		this.moduleMapper.deleteModuleById(module_id);
	}
}
