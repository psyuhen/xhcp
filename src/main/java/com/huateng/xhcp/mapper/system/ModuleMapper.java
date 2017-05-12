/**
 * 
 */
package com.huateng.xhcp.mapper.system;

import java.util.List;

import com.huateng.xhcp.model.system.Module;

/**
 * @author pansen
 *
 */
public interface ModuleMapper {

	/**
	 * 根据module里面的条件查询菜单(数据列表查询)
	 * @param module
	 * @return
	 */
	List<Module> queryModule(Module module);
	/**
	 * 根据module里面的条件查询菜单
	 * @param module
	 * @return
	 */
	List<Module> findModuleBy(Module module);
	/**
	 * 首页的菜单查询
	 * @param module
	 * @return
	 */
	List<Module> findMenuBy(Module module);
	/**
	 * 首页的树菜单查询
	 * @param module
	 * @return
	 */
	List<Module> findTreeMenuBy(Module module);
	/**
	 * 根据module_id查找出所有父节点id
	 * @param module_id
	 * @return
	 */
	List<Module> getModuleParents(String module_id);
	/**
	 * 根据account_id里面的条件查询菜单
	 * @param account_id
	 * @return
	 */
	/*List<Module> findModuleByAccount(String account_id);*/

	/**
	 * 根据account_id里面的条件查询菜单
	 * @param account_id
	 * @return
	 */
	List<Module> findLeafByAccount(String account_id);
	/**
	 * 查询所有状态不为3而且不隐藏的菜单
	 * @return
	 */
	List<Module> findModuleSuperUser();
	
	/**
	 * 新增菜单
	 * @param module
	 */
	void insertModule(Module module);
	/**
	 * 根据Id更新菜单信息
	 * @param module
	 */
	void updateModuleById(Module module);
	/**
	 * 根据Id删除菜单信息
	 * @param module_id
	 */
	void deleteModuleById(String module_id);

	/**
	 * 根据传入的父节点ID查询菜单
	 * @param list
	 * @return
	 */
	List<Module> queryByPModuleId(List<Module> list);
}
