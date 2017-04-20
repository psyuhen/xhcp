/**
 * 
 */
package com.huateng.xhcp.mapper.system;

import java.util.List;

import com.huateng.xhcp.model.system.ScoreDetail;

/**
 * 会员积分明细Mapper
 * @author sam.pan
 *
 */
public interface ScoreDetailMapper {
	/**
	 * 查询会员积分明细信息
	 * @param scoreDetail
	 * @return
	 */
	List<ScoreDetail> queryScoreDetail(ScoreDetail scoreDetail);
	/**
	 * 新增会员积分明细信息
	 * @param scoreDetail
	 */
	int addScoreDetail(ScoreDetail scoreDetail);
	/**
	 * 批量新增会员积分明细信息
	 * @param scoreDetail
	 */
	int addBatchScoreDetail(List<ScoreDetail> scoreDetail);
	/**
	 * 更新会员积分明细信息
	 * @param scoreDetail
	 */
	int updateScoreDetail(ScoreDetail scoreDetail);
	/**
	 * 根据score_id删除会员积分明细信息
	 * @param scoreDetail
	 */
	int deleteScoreDetail(ScoreDetail scoreDetail);
	/**
	 * 根据score_id批量删除会员积分明细信息
	 * @param scoreDetail
	 */
	int deleteBatchScoreDetail(List<ScoreDetail> scoreDetail);
}
