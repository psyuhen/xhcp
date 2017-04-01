/**
 * 
 */
package com.huateng.xhcp.service.nav;

import java.util.List;

import com.huateng.xhcp.model.nav.NavInfo;

/**
 * 导航信息服务类
 * @author sam.pan
 *
 */
public interface NavInfoService {
	/**
	 * 查询导航信息信息
	 * @param navInfo
	 * @return
	 */
	List<NavInfo> queryNavInfo(NavInfo navInfo);
	/**
	 * 查询导航信息信息
	 * @param navInfo
	 * @return
	 */
	List<NavInfo> queryBy(NavInfo navInfo);
	/**
	 * 查询所有子导航信息信息
	 * @return
	 */
	List<NavInfo> querySubNav();

	/**
	 * 根据Key查询导航信息信息
	 * @param nav_id
	 * @return
	 */
	NavInfo queryByKey(String nav_id);
	/**
	 * 新增导航信息信息
	 * @param navInfo
	 */
	int addNavInfo(NavInfo navInfo);
	/**
	 * 批量新增导航信息信息
	 * @param navInfo
	 */
	void addBatchNavInfo(List<NavInfo> navInfo);
	/**
	 * 更新导航信息信息
	 * @param navInfo
	 */
	int updateNavInfo(NavInfo navInfo);
	/**
	 * 根据nav_id删除导航信息信息
	 * @param navInfo
	 */
	int deleteNavInfo(NavInfo navInfo);
	/**
	 * 根据nav_id批量删除导航信息信息
	 * @param navInfo
	 */
	void deleteBatchNavInfo(List<NavInfo> navInfo);
}
