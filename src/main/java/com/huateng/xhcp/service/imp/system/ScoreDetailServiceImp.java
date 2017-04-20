/**
 * 
 */
package com.huateng.xhcp.service.imp.system;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huateng.xhcp.mapper.system.ScoreDetailMapper;
import com.huateng.xhcp.model.system.ScoreDetail;
import com.huateng.xhcp.service.system.ScoreDetailService;
import com.huateng.xhcp.web.page.PageHelper;

/**
 * 会员积分明细服务实现类
 * @author sam.pan
 *
 */
@Service
public class ScoreDetailServiceImp implements ScoreDetailService {

	private @Autowired @Setter @Getter ScoreDetailMapper scoreDetailMapper;
	/**
	 * 查询会员积分明细信息
	 * @param scoreDetail
	 * @return
	 */
	public List<ScoreDetail> queryScoreDetail(ScoreDetail scoreDetail){
		int start = scoreDetail.getStart();
		int limit = scoreDetail.getLimit();
		/* 分页  */
		PageHelper.startPage(start, limit);
		return this.scoreDetailMapper.queryScoreDetail(scoreDetail);
	}
	/**
	 * 查询会员积分明细信息
	 * @param scoreDetail
	 * @return
	 */
	public List<ScoreDetail> queryBy(ScoreDetail scoreDetail){
		return this.scoreDetailMapper.queryScoreDetail(scoreDetail);
	}
	/**
	 * 根据Key查询会员积分明细信息
	 * @param score_id
	 * @return
	 */
	public ScoreDetail queryByKey(String score_id){
		ScoreDetail scoreDetail = new ScoreDetail();
		scoreDetail.setScore_id(score_id);
		List<ScoreDetail> list = this.scoreDetailMapper.queryScoreDetail(scoreDetail);
		if(list == null || list.isEmpty()){
			return null;
		}
		
		return list.get(0);
	}
	/**
	 * 新增会员积分明细信息
	 * @param scoreDetail
	 */
	public int addScoreDetail(ScoreDetail scoreDetail){
		return this.scoreDetailMapper.addScoreDetail(scoreDetail);
	}
	/**
	 * 批量新增会员积分明细信息
	 * @param scoreDetail
	 */
	public int addBatchScoreDetail(List<ScoreDetail> scoreDetail){
		if(scoreDetail == null || scoreDetail.isEmpty()){
			return 0;
		}
		return this.scoreDetailMapper.addBatchScoreDetail(scoreDetail);
	}
	/**
	 * 更新会员积分明细信息
	 * @param scoreDetail
	 */
	public int updateScoreDetail(ScoreDetail scoreDetail){
		return this.scoreDetailMapper.updateScoreDetail(scoreDetail);
	}
	/**
	 * 根据score_id删除会员积分明细信息
	 * @param scoreDetail
	 */
	public int deleteScoreDetail(ScoreDetail scoreDetail){
		return this.scoreDetailMapper.deleteScoreDetail(scoreDetail);
	}
	/**
	 * 根据score_id批量删除会员积分明细信息
	 * @param scoreDetail
	 */
	public int deleteBatchScoreDetail(List<ScoreDetail> scoreDetail){
		if(scoreDetail == null || scoreDetail.isEmpty()){
			return 0;
		}
		return this.scoreDetailMapper.deleteBatchScoreDetail(scoreDetail);
	}
}
