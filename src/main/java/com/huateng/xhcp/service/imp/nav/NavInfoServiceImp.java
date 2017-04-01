/**
 * 
 */
package com.huateng.xhcp.service.imp.nav;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huateng.xhcp.mapper.nav.NavInfoMapper;
import com.huateng.xhcp.model.nav.NavInfo;
import com.huateng.xhcp.service.nav.NavInfoService;
import com.huateng.xhcp.web.page.PageHelper;

/**
 * 导航信息服务实现类
 * @author sam.pan
 *
 */
@Service
public class NavInfoServiceImp implements NavInfoService {

	private @Autowired @Setter @Getter NavInfoMapper navInfoMapper;
	/**
	 * 查询导航信息信息
	 * @param navInfo
	 * @return
	 */
	public List<NavInfo> queryNavInfo(NavInfo navInfo){
		int start = navInfo.getStart();
		int limit = navInfo.getLimit();
		/* 分页  */
		PageHelper.startPage(start, limit);
		return this.navInfoMapper.queryNavInfo(navInfo);
	}

	@Override
	public List<NavInfo> queryBy(NavInfo navInfo) {
		return this.navInfoMapper.queryNavInfo(navInfo);
	}

	@Override
	public List<NavInfo> querySubNav() {
		return this.navInfoMapper.querySubNav();
	}

	/**
	 * 根据Key查询导航信息信息
	 * @param nav_id
	 * @return
	 */
	public NavInfo queryByKey(String nav_id){
		NavInfo navInfo = new NavInfo();
		navInfo.setNav_id(nav_id);
		List<NavInfo> list = this.navInfoMapper.queryNavInfo(navInfo);
		if(list == null || list.isEmpty()){
			return null;
		}
		
		return list.get(0);
	}
	/**
	 * 新增导航信息信息
	 * @param navInfo
	 */
	public int addNavInfo(NavInfo navInfo){
		return this.navInfoMapper.addNavInfo(navInfo);
	}
	/**
	 * 批量新增导航信息信息
	 * @param navInfo
	 */
	public void addBatchNavInfo(List<NavInfo> navInfo){
		if(navInfo == null || navInfo.isEmpty()){
			return;
		}
		this.navInfoMapper.addBatchNavInfo(navInfo);
	}
	/**
	 * 更新导航信息信息
	 * @param navInfo
	 */
	public int updateNavInfo(NavInfo navInfo){
		return this.navInfoMapper.updateNavInfo(navInfo);
	}
	/**
	 * 根据nav_id删除导航信息信息
	 * @param navInfo
	 */
	public int deleteNavInfo(NavInfo navInfo){
		return this.navInfoMapper.deleteNavInfo(navInfo);
	}
	/**
	 * 根据nav_id批量删除导航信息信息
	 * @param navInfo
	 */
	public void deleteBatchNavInfo(List<NavInfo> navInfo){
		if(navInfo == null || navInfo.isEmpty()){
			return;
		}
		this.navInfoMapper.deleteBatchNavInfo(navInfo);
	}
}
