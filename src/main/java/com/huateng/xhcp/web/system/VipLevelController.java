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
import com.huateng.xhcp.model.system.VipLevel;
import com.huateng.xhcp.service.system.VipLevelService;
import com.huateng.xhcp.util.HttpUtil;

/**
 * 会员等级 Controller类
 * @author sam.pan
 *
 */
@Controller
public class VipLevelController implements com.huateng.xhcp.service.upload.Validator<VipLevel>{
	private static final Log LOGGER = LogFactory.getLog(VipLevelController.class);
	private @Autowired @Setter @Getter VipLevelService vipLevelService;
	/**
	 * 初始化页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/system/vip/listmgr")
	public String toListPage(HttpServletRequest request){
		request.setAttribute("page", "mgr");
		request.setAttribute("module_id", "vipmgr");
		return "system/VipLevelList";
	}

	/**
	 * query页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/system/vip/query")
	public String toQueryPage(HttpServletRequest request){
		request.setAttribute("page", "query");
		request.setAttribute("module_id", "vipmgr");
		return "system/VipLevelList";
	}
	
	/**
	 * 跳转新增页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/system/vip/add")
	public String toAddPage(HttpServletRequest request){
		request.setAttribute("page", "add");
		request.setAttribute("module_id", "vipmgr");
		return "system/VipLevelAdd";
	}
	
	/**
	 * 跳转到更新页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/system/vip/update")
	public String toUpdatePage(HttpServletRequest request){
		request.setAttribute("page", "update");
		request.setAttribute("module_id", "vipmgr");
		return "system/VipLevelAdd";
	}
	/**
	 * 跳转到查看页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/system/vip/view")
	public String toViewPage(HttpServletRequest request){
		request.setAttribute("page", "view");
		request.setAttribute("module_id", request.getParameter("module_id"));
		return "system/VipLevelAdd";
	}
	
	/**
	 * 查询会员等级信息(分页)
	 * @param vipLevel
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/system/vip/queryVipLevelPage", method = RequestMethod.POST)
	public com.huateng.xhcp.web.page.Page queryVipLevel(VipLevel vipLevel){
		Page<VipLevel> list = (Page<VipLevel>)this.vipLevelService.queryVipLevel(vipLevel);
		
		return new com.huateng.xhcp.web.page.Page(list);
	}
	
	/**
	 * 根据Key查询会员等级信息
	 * @param vip_id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/system/vip/queryByKey", method = RequestMethod.GET)
	public VipLevel queryByKey(String vip_id){
		return this.vipLevelService.queryByKey(vip_id);
	}
	/**
	 * 新增会员等级信息
	 * @param vipLevel
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/system/vip/addVipLevel", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> addVipLevel(VipLevel vipLevel){
		try{
			ResponseInfo responseInfo = validate(vipLevel);
			if(ResponseInfo.FAILURE.equals(responseInfo.getStatus())){
				return HttpUtil.failure(responseInfo.getDesc());
			}
			
			int c = this.vipLevelService.addVipLevel(vipLevel);
			if(c==0){
				return HttpUtil.failure("新增会员等级失败!");
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("新增会员等级失败!");
		}
		
		return HttpUtil.success("新增会员等级成功!");
	}
	
	/*校验*/
	public ResponseInfo validate(VipLevel vipLevel){
		if(vipLevel == null){
			return ResponseInfo.fail("会员等级信息为空!");
		}
		
		String vip_id = vipLevel.getVip_id();
		int vip_idLength = com.huateng.xhcp.util.Validator.mysql(vip_id);
		if(vip_idLength > 10){
			return ResponseInfo.fail("会员等级ID必须少于10 个字符!");
		}

		String name = vipLevel.getName();
		int nameLength = com.huateng.xhcp.util.Validator.mysql(name);
		if(nameLength > 10){
			return ResponseInfo.fail("等级名称必须少于10 个字符!");
		}

		String score = vipLevel.getScore();
		int scoreLength = com.huateng.xhcp.util.Validator.mysql(score);
		if(scoreLength > 10){
			return ResponseInfo.fail("积分要求必须少于10 个字符!");
		}

		String discount = vipLevel.getDiscount();
		int discountLength = com.huateng.xhcp.util.Validator.mysql(discount);
		if(discountLength > 6){
			return ResponseInfo.fail("享受折扣必须少于6 个字符!");
		}


		
		return ResponseInfo.success("校验成功");
	}
	
	
	/**
	 * 更新会员等级信息
	 * @param vipLevel
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/system/vip/updateVipLevel", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> updateVipLevel(VipLevel vipLevel){
		try{
			ResponseInfo responseInfo = validate(vipLevel);
			if(ResponseInfo.FAILURE.equals(responseInfo.getStatus())){
				return HttpUtil.failure(responseInfo.getDesc());
			}
			
			int c = this.vipLevelService.updateVipLevel(vipLevel);
			if(c == 0){
				return HttpUtil.failure("更新会员等级失败!");
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("更新会员等级失败!");
		}
		
		return HttpUtil.success("更新会员等级成功!");
	}
	
	/**
	 * 删除会员等级信息
	 * @param vipLevel
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/system/vip/deleteVipLevel", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> deleteVipLevel(VipLevel vipLevel){
		try{
			int c = this.vipLevelService.deleteVipLevel(vipLevel);
			if(c == 0){
				return HttpUtil.failure("删除会员等级失败!");
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("删除会员等级失败!");
		}
		
		return HttpUtil.success("删除会员等级成功!");
	}
	/**
	 * 批量删除会员等级信息
	 * @param vipLevel
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/system/vip/deleteBatchVipLevel", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> deleteBatchVipLevel(List<VipLevel> vipLevel){
		try{
			this.vipLevelService.deleteBatchVipLevel(vipLevel);
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("批量删除会员等级失败!");
		}
		
		return HttpUtil.success("批量删除会员等级成功!");
	}
}
