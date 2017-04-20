/**
 * 
 */
package com.huateng.xhcp.service.imp.system;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huateng.xhcp.mapper.system.VipLevelMapper;
import com.huateng.xhcp.model.system.VipLevel;
import com.huateng.xhcp.service.system.VipLevelService;
import com.huateng.xhcp.web.page.PageHelper;

/**
 * 会员等级服务实现类
 * @author sam.pan
 *
 */
@Service
public class VipLevelServiceImp implements VipLevelService {

	private @Autowired @Setter @Getter VipLevelMapper vipLevelMapper;
	/**
	 * 查询会员等级信息
	 * @param vipLevel
	 * @return
	 */
	public List<VipLevel> queryVipLevel(VipLevel vipLevel){
		int start = vipLevel.getStart();
		int limit = vipLevel.getLimit();
		/* 分页  */
		PageHelper.startPage(start, limit);
		return this.vipLevelMapper.queryVipLevel(vipLevel);
	}
	/**
	 * 查询会员等级信息
	 * @param vipLevel
	 * @return
	 */
	public List<VipLevel> queryBy(VipLevel vipLevel){
		return this.vipLevelMapper.queryVipLevel(vipLevel);
	}
	/**
	 * 根据Key查询会员等级信息
	 * @param vip_id
	 * @return
	 */
	public VipLevel queryByKey(String vip_id){
		VipLevel vipLevel = new VipLevel();
		vipLevel.setVip_id(vip_id);
		List<VipLevel> list = this.vipLevelMapper.queryVipLevel(vipLevel);
		if(list == null || list.isEmpty()){
			return null;
		}
		
		return list.get(0);
	}
	/**
	 * 新增会员等级信息
	 * @param vipLevel
	 */
	public int addVipLevel(VipLevel vipLevel){
		return this.vipLevelMapper.addVipLevel(vipLevel);
	}
	/**
	 * 批量新增会员等级信息
	 * @param vipLevel
	 */
	public int addBatchVipLevel(List<VipLevel> vipLevel){
		if(vipLevel == null || vipLevel.isEmpty()){
			return 0;
		}
		return this.vipLevelMapper.addBatchVipLevel(vipLevel);
	}
	/**
	 * 更新会员等级信息
	 * @param vipLevel
	 */
	public int updateVipLevel(VipLevel vipLevel){
		return this.vipLevelMapper.updateVipLevel(vipLevel);
	}
	/**
	 * 根据vip_id删除会员等级信息
	 * @param vipLevel
	 */
	public int deleteVipLevel(VipLevel vipLevel){
		return this.vipLevelMapper.deleteVipLevel(vipLevel);
	}
	/**
	 * 根据vip_id批量删除会员等级信息
	 * @param vipLevel
	 */
	public int deleteBatchVipLevel(List<VipLevel> vipLevel){
		if(vipLevel == null || vipLevel.isEmpty()){
			return 0;
		}
		return this.vipLevelMapper.deleteBatchVipLevel(vipLevel);
	}
}
