/**
 * 
 */
package com.huateng.xhcp.mapper.system;

import java.util.List;

import com.huateng.xhcp.model.system.VipLevel;

/**
 * 会员等级Mapper
 * @author sam.pan
 *
 */
public interface VipLevelMapper {
	/**
	 * 查询会员等级信息
	 * @param vipLevel
	 * @return
	 */
	List<VipLevel> queryVipLevel(VipLevel vipLevel);
	/**
	 * 新增会员等级信息
	 * @param vipLevel
	 */
	int addVipLevel(VipLevel vipLevel);
	/**
	 * 批量新增会员等级信息
	 * @param vipLevel
	 */
	int addBatchVipLevel(List<VipLevel> vipLevel);
	/**
	 * 更新会员等级信息
	 * @param vipLevel
	 */
	int updateVipLevel(VipLevel vipLevel);
	/**
	 * 根据vip_id删除会员等级信息
	 * @param vipLevel
	 */
	int deleteVipLevel(VipLevel vipLevel);
	/**
	 * 根据vip_id批量删除会员等级信息
	 * @param vipLevel
	 */
	int deleteBatchVipLevel(List<VipLevel> vipLevel);
}
