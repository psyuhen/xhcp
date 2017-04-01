/**
 * 
 */
package com.huateng.xhcp.service.system;

import java.util.List;

import com.huateng.xhcp.model.system.Module;

/**
 * @author pansen
 *
 */
public interface ModuleService {

	/**
	 * 根据module里面的条件查询菜单(数据列表查询)
	 * @param module
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<Module> queryModule(Module module, int start, int limit);
	/**
	 * 根据module里面的条件查询菜单
	 * @param module
	 * @return
	 */
	public List<Module> findModuleBy(Module module);
	/**
	 * 根据account_id里面的条件查询菜单
	 * @param account_id
	 * @return
	 */
	public List<Module> findModuleByAccount(String account_id);
	/**
	 * 查询所有状态不为3而且不隐藏的菜单
	 * @return
	 */
	public List<Module> findModuleSuperUser();
	/**
	 * 首页的菜单查询
	 * @param module
	 * @return
	 */
	public List<Module> findMenuBy(Module module);
	/**
	 * 首页的树菜单查询
	 * @param module
	 * @return
	 */
	public List<Module> findTreeMenuBy(Module module);
	
	/**
	 * 根据module_id查找出所有父节点id
	 * @param module_id
	 * @return
	 */
	public List<Module> getModuleParents(String module_id);
	/**
	 * 根据菜单Id获取菜单名称
	 * @param module_id
	 * @return
	 */
	public String getModuleNameById(String module_id);
	/**
	 * 根据菜单Id获取菜单url
	 * @param module_id
	 * @return
	 */
	public String getModuleEntryById(String module_id);
	/**
	 * 新增菜单(如果是菜单的话，会生成一条功能的菜单信息)
	 * @param module
	 */
	public void insertModule(Module module);
	/**
	 * 根据Id更新菜单信息
	 * @param module
	 */
	public void updateModuleById(Module module);
	/**
	 * 根据Id删除菜单信息
	 * @param module_id
	 */
	public void deleteModuleById(String module_id);
}
