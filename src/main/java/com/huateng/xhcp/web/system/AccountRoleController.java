package com.huateng.xhcp.web.system;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huateng.xhcp.model.ResponseInfo;
import com.huateng.xhcp.model.system.AccountRole;
import com.huateng.xhcp.service.system.AccountRoleService;
import com.huateng.xhcp.util.HttpUtil;

/**
 *
 * @author chengz
 * */
@Controller
public class AccountRoleController {
	private @Autowired @Setter @Getter AccountRoleService accountRoleService;
	
	/**
	 * 查询拥有当前角色的用户
	 * @param accountRole
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/accountRole/queryAccountRole", method = RequestMethod.POST)
	public List<AccountRole> queryAccountRole(AccountRole accountRole){
		return this.accountRoleService.queryAccountRole(accountRole);
	}
	/**
	 * 查询可支配当前角色的用户
	 * @param accountRole
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/accountRole/queryAccountRoleConf", method = RequestMethod.POST)
	public List<AccountRole> queryAccountRoleConf(AccountRole accountRole){
		return this.accountRoleService.queryAccountRoleConf(accountRole);
	}
	/**
	 * 刷新拥有当前角色的用户(先删除后新增)
	 * @param List
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/accountRole/updateAccountRole", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> updateAccountRole(@RequestBody ArrayList<ArrayList<AccountRole>> accountRoleList){
		try{
			this.accountRoleService.updateAccountRole2(accountRoleList);
		}catch(Exception e){
			return HttpUtil.failure("更新失败!");
		}
		
		return HttpUtil.success("更新成功!");
	}
	
	/**
	 * 刷新可支配当前角色的用户(先删除后新增)
	 * @param List
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/accountRole/updateAccountRoleConf", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> updateAccountRoleConf(@RequestBody ArrayList<ArrayList<AccountRole>> accountRoleList){
		try{
			this.accountRoleService.updateAccountRoleConf(accountRoleList);
		}catch(Exception e){
			return HttpUtil.failure("更新失败!");
		}
		
		return HttpUtil.success("更新成功!");
	}
}
