/**
 * 
 */
package com.huateng.xhcp.web.system;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.huateng.xhcp.model.ResponseInfo;
import com.huateng.xhcp.model.system.ScoreDetail;
import com.huateng.xhcp.service.system.ScoreDetailService;
import com.huateng.xhcp.util.HttpUtil;

/**
 * 会员积分明细 Controller类
 * @author sam.pan
 *
 */
@Controller
public class ScoreDetailController implements com.huateng.xhcp.service.upload.Validator<ScoreDetail>{
	private static final Log LOGGER = LogFactory.getLog(ScoreDetailController.class);
	private @Autowired @Setter @Getter ScoreDetailService scoreDetailService;
	/**
	 * 初始化页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/system/score/listmgr")
	public String toListPage(HttpServletRequest request){
		request.setAttribute("page", "mgr");
		request.setAttribute("module_id", "scoremgr");
		return "system/ScoreDetailList";
	}

	/**
	 * query页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/system/score/query")
	public String toQueryPage(HttpServletRequest request){
		request.setAttribute("page", "query");
		request.setAttribute("module_id", "scoremgr");
		return "system/ScoreDetailList";
	}
	
	/**
	 * 跳转新增页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/system/score/add")
	public String toAddPage(HttpServletRequest request){
		request.setAttribute("page", "add");
		request.setAttribute("module_id", "scoremgr");
		return "system/ScoreDetailAdd";
	}
	
	/**
	 * 跳转到更新页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/system/score/update")
	public String toUpdatePage(HttpServletRequest request){
		request.setAttribute("page", "update");
		request.setAttribute("module_id", "scoremgr");
		return "system/ScoreDetailAdd";
	}
	/**
	 * 跳转到查看页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/system/score/view")
	public String toViewPage(HttpServletRequest request){
		request.setAttribute("page", "view");
		request.setAttribute("module_id", request.getParameter("module_id"));
		return "system/ScoreDetailAdd";
	}
	
	/**
	 * 查询会员积分明细信息(分页)
	 * @param scoreDetail
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/system/score/queryScoreDetailPage", method = RequestMethod.POST)
	public com.huateng.xhcp.web.page.Page queryScoreDetail(ScoreDetail scoreDetail){
		Page<ScoreDetail> list = (Page<ScoreDetail>)this.scoreDetailService.queryScoreDetail(scoreDetail);
		
		return new com.huateng.xhcp.web.page.Page(list);
	}
	
	/**
	 * 根据Key查询会员积分明细信息
	 * @param score_id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/system/score/queryByKey", method = RequestMethod.GET)
	public ScoreDetail queryByKey(String score_id){
		return this.scoreDetailService.queryByKey(score_id);
	}
	/**
	 * 新增会员积分明细信息
	 * @param scoreDetail
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/system/score/addScoreDetail", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> addScoreDetail(ScoreDetail scoreDetail){
		try{
			ResponseInfo responseInfo = validate(scoreDetail);
			if(ResponseInfo.FAILURE.equals(responseInfo.getStatus())){
				return HttpUtil.failure(responseInfo.getDesc());
			}
			
			int c = this.scoreDetailService.addScoreDetail(scoreDetail);
			if(c==0){
				return HttpUtil.failure("新增会员积分明细失败!");
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("新增会员积分明细失败!");
		}
		
		return HttpUtil.success("新增会员积分明细成功!");
	}
	
	/*校验*/
	public ResponseInfo validate(ScoreDetail scoreDetail){
		if(scoreDetail == null){
			return ResponseInfo.fail("会员积分明细信息为空!");
		}
		
		String score_id = scoreDetail.getScore_id();
		int score_idLength = com.huateng.xhcp.util.Validator.mysql(score_id);
		if(score_idLength > 10){
			return ResponseInfo.fail("积分ID必须少于10 个字符!");
		}

		String account_id = scoreDetail.getAccount_id();
		int account_idLength = com.huateng.xhcp.util.Validator.mysql(account_id);
		if(account_idLength > 96){
			return ResponseInfo.fail("用户ID必须少于96 个字符!");
		}

		String score = scoreDetail.getScore();
		int scoreLength = com.huateng.xhcp.util.Validator.mysql(score);
		if(scoreLength > 10){
			return ResponseInfo.fail("积分必须少于10 个字符!");
		}

		String reason = scoreDetail.getReason();
		int reasonLength = com.huateng.xhcp.util.Validator.mysql(reason);
		if(reasonLength > 96){
			return ResponseInfo.fail("原因必须少于96 个字符!");
		}


		
		return ResponseInfo.success("校验成功");
	}
	
	
	/**
	 * 更新会员积分明细信息
	 * @param scoreDetail
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/system/score/updateScoreDetail", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> updateScoreDetail(ScoreDetail scoreDetail){
		try{
			ResponseInfo responseInfo = validate(scoreDetail);
			if(ResponseInfo.FAILURE.equals(responseInfo.getStatus())){
				return HttpUtil.failure(responseInfo.getDesc());
			}
			
			int c = this.scoreDetailService.updateScoreDetail(scoreDetail);
			if(c == 0){
				return HttpUtil.failure("更新会员积分明细失败!");
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("更新会员积分明细失败!");
		}
		
		return HttpUtil.success("更新会员积分明细成功!");
	}
	
	/**
	 * 删除会员积分明细信息
	 * @param scoreDetail
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/system/score/deleteScoreDetail", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> deleteScoreDetail(ScoreDetail scoreDetail){
		try{
			int c = this.scoreDetailService.deleteScoreDetail(scoreDetail);
			if(c == 0){
				return HttpUtil.failure("删除会员积分明细失败!");
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("删除会员积分明细失败!");
		}
		
		return HttpUtil.success("删除会员积分明细成功!");
	}
	/**
	 * 批量删除会员积分明细信息
	 * @param scoreDetail
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/system/score/deleteBatchScoreDetail", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> deleteBatchScoreDetail(List<ScoreDetail> scoreDetail){
		try{
			this.scoreDetailService.deleteBatchScoreDetail(scoreDetail);
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("批量删除会员积分明细失败!");
		}
		
		return HttpUtil.success("批量删除会员积分明细成功!");
	}
}
