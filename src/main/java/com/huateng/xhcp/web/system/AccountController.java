/**
 * 
 */
package com.huateng.xhcp.web.system;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.huateng.xhcp.util.SecureUtil;
import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.huateng.xhcp.model.ResponseInfo;
import com.huateng.xhcp.model.system.Account;
import com.huateng.xhcp.security.SecurityContext;
import com.huateng.xhcp.service.system.AccountService;
import com.huateng.xhcp.util.DateUtil;
import com.huateng.xhcp.util.HttpUtil;
import com.huateng.xhcp.util.Validator;

/**
 * 账户信息管理
 * @author pansen
 *
 */
@Controller
public class AccountController {
	private static final Log LOGGER = LogFactory.getLog(AccountController.class);
	private @Autowired @Setter @Getter AccountService accountService;
	/**
	 * 初始化登录页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/user")
	public String initPage(){
		return "system/AccountList";
	}
	
	/**
	 * 跳转到账号新增页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/user/add")
	public String toAddPage(HttpServletRequest request){
		request.setAttribute("page", "add");
		return "system/AccountAdd";
	}
	
	/**
	 * 跳转到账号更新页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/user/update")
	public String toUpdatePage(HttpServletRequest request){
		request.setAttribute("page", "update");
		return "system/AccountAdd";
	}
	/**
	 * 跳转到账号查看页面
	 * @return
	 */
	@RequestMapping(value = "/mgr/user/view")
	public String toViewPage(HttpServletRequest request){
		request.setAttribute("page", "view");
		return "system/AccountAdd";
	}
	/**
	 * 查询用户信息(分页)
	 * @param account
	 * @param start
	 * @param limit
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/user/queryAccountPage", method = RequestMethod.POST)
	public com.huateng.xhcp.web.page.Page queryAccount(Account account, @RequestParam int start,@RequestParam int limit){
		Page<Account> list = (Page<Account>)this.accountService.queryAccount(account, start, limit);
		
		return new com.huateng.xhcp.web.page.Page(list);
	}
	/**
	 * 查询用户信息(不分页)
	 * @param account_id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/user/id", method = RequestMethod.GET)
	public Account queryAccountById(String account_id){
		return this.accountService.queryAccountById(account_id);
	}
	/**
	 * 查询用户信息(不分页)
	 * @param account
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/user/queryAccount", method = RequestMethod.POST)
	public List<Account> queryAccount(Account account){
		return this.accountService.queryAccount(account);
	}
	
	/**
	 * 检查account_id是否存在
	 * @param account_id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/user/isAccountExists", method = RequestMethod.GET)
	public boolean isAccountExists(@RequestParam String account_id){
		Account account = this.accountService.queryAccountById(account_id);
		
		if(account == null){
			return false;
		}
		
		return true;
	}

	/**
	 * 判断当前用户是否是超级管理员
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/user/isSuperUser")
	public boolean isSuperUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Account user = (Account) session.getAttribute("user");
		if(user == null){
			return false;
		}
		
		return SecurityContext.isSuperUser(user.getAccount_id());
	}
	
	/**
	 * 新增账号
	 * @param account
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/user/addAccount", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> addAccount(Account account){
		try{
			ResponseInfo responseInfo = validator(account);
			if(ResponseInfo.FAILURE.equals(responseInfo.getStatus())){
				return HttpUtil.failure(responseInfo.getDesc());
			}
			/*检查account_id是否已存在*/
			Account tmp = this.accountService.queryAccountById(account.getAccount_id());
			if(tmp != null){
				return HttpUtil.failure("账号ID已存在!");
			}
			/*检查mobile是否已存在*/
			tmp = this.accountService.queryByMobile(account.getMobile());
			if(tmp != null){
				return HttpUtil.failure("手机号已存在!");
			}
			/*密码需要加密保存*/
			account.setAccount_password(SecureUtil.shaEncode(account.getAccount_password()));
			
			String accountInvDate = account.getAccount_inv_date();
			if(StringUtils.isBlank(accountInvDate)){//为空时，默认是永久
				accountInvDate = "99991231";
				account.setInvalid_date(accountInvDate);
			}

			int c = this.accountService.addAccount(account);
			if(c==0){
				return HttpUtil.failure("新增账号失败!");
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("新增账号失败!");
		}
		
		return HttpUtil.success("新增账号成功!");
	}
	
	/*校验*/
	private ResponseInfo validator(Account account){
		if(account == null){
			return ResponseInfo.fail("账号信息为空!");
		}
		
		/*检查account_id为空判断*/
		String account_id = account.getAccount_id();
		if(StringUtils.isBlank(account_id)){
			return ResponseInfo.fail("账号ID为空!");
		}
		
		/*检查account_id长度为3-64个字符*/
		int accountIdLength = Validator.mysql(account_id);
		if(accountIdLength < 3 || accountIdLength > 96){
			return ResponseInfo.fail("账号ID必须为3 至 96 个字符!");
		}
		
		/*检查account_id是否包含特殊字符*/
		if(!account_id.matches("^[a-zA-Z0-9_]+$")){
			return ResponseInfo.fail("账号ID只能输入a-zA-Z0-9_字符!");
		}
		
		/*检查account_name为空判断*/
		String account_name = account.getAccount_name();
		if(StringUtils.isBlank(account_name)){
			return ResponseInfo.fail("账号名称为空!");
		}
		
		/*检查account_name长度为48个字符*/
		int accountNameLength = Validator.mysql(account_name);
		if(accountNameLength > 48){
			return ResponseInfo.fail("账号名称必须少于48 个字符!");
		}
		
		
		/*检查account_password为空判断*/
		String account_password = account.getAccount_password();
		if(StringUtils.isBlank(account_password)){
			return ResponseInfo.fail("密码为空!");
		}
		
		/*检查account_password长度为6-192个字符*/
		if(account_password.length() < 6 || account_password.length() > 192){
			return ResponseInfo.fail("密码必须为6 至 192个字符!");
		}

		String qq = account.getQq();
		int qqLength = Validator.mysql(qq);
		if(qqLength > 15){
			return ResponseInfo.fail("qq必须少于15 个字符!");
		}

		String mobile = account.getMobile();
		int mobileLength = Validator.mysql(mobile);
		if(mobileLength > 11){
			return ResponseInfo.fail("手机号必须少于11 个字符!");
		}

		String wechat = account.getWechat();
		int wechatLength = Validator.mysql(wechat);
		if(wechatLength > 50){
			return ResponseInfo.fail("微信号必须少于50 个字符!");
		}

		return ResponseInfo.success("校验成功");
	}
	
	
	/**
	 * 更新账号信息
	 * @param account
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/user/updateAccount", method = RequestMethod.POST)
	public ResponseEntity<ResponseInfo> updateAccount(Account account){
		try{
			ResponseInfo responseInfo = validator(account);
			if(ResponseInfo.FAILURE.equals(responseInfo.getStatus())){
				return HttpUtil.failure(responseInfo.getDesc());
			}

			/*检查account_id是否已存在*/
			Account tmp = this.accountService.queryAccountById(account.getAccount_id());
			if(tmp == null){
				return HttpUtil.failure("账号不存在,更新失败!");
			}

			/*密码需要加密保存,如果传入的本身是加密的，就不作处理*/
			String password = StringUtils.trimToEmpty(account.getAccount_password());
			String pwd = StringUtils.trimToEmpty(tmp.getAccount_password());
			if(!StringUtils.equals(password, pwd)){
				account.setAccount_password(SecureUtil.shaEncode(password));
			}

			
			String accountInvDate = account.getAccount_inv_date();
			if(StringUtils.isBlank(accountInvDate)){/*为空时，默认是永久*/
				accountInvDate = "99991231";
				account.setInvalid_date(accountInvDate);
			}
			int c = this.accountService.updateAccount(account);
			if(c == 0){
				return HttpUtil.failure("更新账号失败!");
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("更新账号失败!");
		}
		
		return HttpUtil.success("更新账号成功!");
	}
	
	/**
	 * 删除账号信息
	 * @param account_id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/user/deleteAccount", method = RequestMethod.GET)
	public ResponseEntity<ResponseInfo> deleteAccount(@RequestParam String account_id){
		try{
			int c = this.accountService.deleteAccount(account_id);
			if(c == 0){
				return HttpUtil.failure("删除账号失败!");
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("删除账号失败!");
		}
		
		return HttpUtil.success("删除账号成功!");
	}
	/**
	 * 删除账号信息
	 * @param mobile
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgr/user/deleteByMobile", method = RequestMethod.GET)
	public ResponseEntity<ResponseInfo> deleteByMobile(@RequestParam String mobile){
		try{
			int c = this.accountService.deleteByMobile(mobile);
			if(c == 0){
				return HttpUtil.failure("删除账号失败!");
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
			return HttpUtil.failure("删除账号失败!");
		}

		return HttpUtil.success("删除账号成功!");
	}

}
