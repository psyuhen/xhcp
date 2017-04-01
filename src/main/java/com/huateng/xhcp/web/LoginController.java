package com.huateng.xhcp.web;

import com.huateng.xhcp.model.ResponseInfo;
import com.huateng.xhcp.model.product.FreqAddr;
import com.huateng.xhcp.model.system.Account;
import com.huateng.xhcp.model.system.Province;
import com.huateng.xhcp.security.RandomValidateCode;
import com.huateng.xhcp.security.SecurityContext;
import com.huateng.xhcp.service.product.FreqAddrService;
import com.huateng.xhcp.service.system.AccountService;
import com.huateng.xhcp.service.system.ProvinceService;
import com.huateng.xhcp.util.DateUtil;
import com.huateng.xhcp.util.HttpUtil;
import com.huateng.xhcp.util.SecureUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by sam.pan on 2017/3/23.
 */
@Controller
public class LoginController {
    private @Autowired AccountService accountService;
    private @Autowired ProvinceService provinceService;
    private @Autowired FreqAddrService freqAddrService;

    @RequestMapping(value="/login.html")
    public String loginPage(){
        return "login";
    }

    @RequestMapping(value="/register.html")
    public String registerPage(){
        return "register";
    }

    @RequestMapping(value="/usercenter.html")
    public String usercenterPage(){
        return "usercenter";
    }

    @RequestMapping(value="/address.html")
    public String addressPage(HttpServletRequest request){
        Province province = new Province();
        List<Province> provinces = this.provinceService.queryBy(province);
        request.setAttribute("provinces", provinces);

        HttpSession session = request.getSession();
        Account account = (Account)session.getAttribute(SecurityContext.FRONT_ACCOUNT);
        List<FreqAddr> freqAddrs = this.freqAddrService.queryByAccountId(account.getAccount_id());
        request.setAttribute("UserFreqAddrs", freqAddrs);

        return "address";
    }

    @RequestMapping(value="/order.html")
    public String orderPage(){
        return "order";
    }

    @RequestMapping(value="/validatecode")
    public void validateCode(HttpServletRequest request, HttpServletResponse response){
        RandomValidateCode.getInstance().getRandcode(request, response);
    }


    /**
     * 根据手机号码和密码登录
     * @param account 用户 信息
     * @return 查询到返回用户 信息否则返回null
     */
    @RequestMapping(value="/login.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseInfo> login(Account account, HttpServletRequest request) {
        String account_id = account.getAccount_id();
        String mobile = account.getMobile();
        String validate_code = account.getValidate_code();
        String account_password = account.getAccount_password();

        if(StringUtils.isBlank(account_id) && StringUtils.isBlank(mobile)){
            return HttpUtil.failure("手机号/用户不能为空！");
        }

        if(StringUtils.isBlank(account_password)){
            return HttpUtil.failure("密码不能为空！");
        }

        if(StringUtils.isBlank(validate_code)){
            return HttpUtil.failure("验证码不能为空！");
        }

        HttpSession session = request.getSession();
        String code = (String)session.getAttribute(RandomValidateCode.RANDOMCODEKEY);
        if(!StringUtils.equals(validate_code, code)){
            return HttpUtil.failure("验证码不正确！");
        }

        account_password = SecureUtil.shaEncode(account_password);
        Account acc = new Account();
        acc.setAccount_id(account_id);
        acc.setMobile(mobile);
        acc.setAccount_password(account_password);
        Account accounts = accountService.queryAccountByIdAndPwd(acc);
        if(accounts == null){
            return HttpUtil.failure("用户/密码错误");
        }

        if("0".equals(accounts.getAccount_status())){
            return HttpUtil.failure("用户已被锁定！");
        }

        String invDate = accounts.getAccount_inv_date();
        if(!StringUtils.isBlank(invDate) && invDate.compareTo(DateUtil.today("yyyyMMdd")) < 0){
            return HttpUtil.failure("用户已失效！");
        }

        session.setAttribute(SecurityContext.FRONT_ACCOUNT, accounts);

        return HttpUtil.success("登录成功", accounts);
    }

    /**
     * 注册用户
     * @param account 用户 信息
     * @return 查询到返回用户 信息否则返回null
     */
    @RequestMapping(value="/register.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseInfo> register(Account account, HttpServletRequest request) {
        String mobile = account.getMobile();
        String validate_code = account.getValidate_code();
        String account_password = account.getAccount_password();

        if(StringUtils.isBlank(mobile)){
            return HttpUtil.failure("手机号不能为空！");
        }

        if(StringUtils.isBlank(account_password)){
            return HttpUtil.failure("密码不能为空！");
        }

        if(StringUtils.isBlank(validate_code)){
            return HttpUtil.failure("验证码不能为空！");
        }

        HttpSession session = request.getSession();
        String code = (String)session.getAttribute(RandomValidateCode.RANDOMCODEKEY);
        if(!StringUtils.equals(validate_code, code)){
            return HttpUtil.failure("验证码不正确！");
        }

        Account account1 = accountService.queryByMobile(mobile);
        if(account1 != null){
            return HttpUtil.failure("手机号已被注册！");
        }

        account_password = SecureUtil.shaEncode(account_password);
        account.setAccount_id(mobile);
        account.setAccount_status("1");
        account.setAccount_password(account_password);

        int c = accountService.addAccount(account);
        if(c == 0){
            return HttpUtil.failure("用户注册失败！");
        }

        return HttpUtil.success("注册成功", account);
    }

    /**
     * 检查手机号是否被注册
     * @param mobile 手机号
     * @return 查询到返回用户 信息否则返回null
     */
    @RequestMapping(value="/checkmobile.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseInfo> checkMobile(String mobile, HttpServletRequest request) {
        final Account account = accountService.queryByMobile(mobile);
        if(account == null){
            return HttpUtil.success("该手机号可以注册", account);
        }
        return HttpUtil.failure("该手机号已被注册", account);
    }

    /**
     * 前端用户退出
     * @return
     */
    @RequestMapping(value="/logout.do", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        request.getSession().setAttribute(SecurityContext.FRONT_ACCOUNT, null);
        return "redirect:/index";
    }

    /**
     * 修改密码
     * @param account 手机号
     * @return 查询到返回用户 信息否则返回null
     */
    @RequestMapping(value="/changepwd.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseInfo> changePassword(Account account, HttpServletRequest request) {
        String old_pwd = account.getOld_password();
        String new_pwd = account.getAccount_password();

        if(StringUtils.isBlank(old_pwd)){
            return HttpUtil.failure("原密码不能为空！");
        }

        if(StringUtils.isBlank(new_pwd)){
            return HttpUtil.failure("新密码不能为空！");
        }

        if(new_pwd.length() < 6){
            return HttpUtil.failure("新密码长度不能少于6位！");
        }

        HttpSession session = request.getSession();
        Account acc = (Account)session.getAttribute(SecurityContext.FRONT_ACCOUNT);

        Account tmp = new Account();
        tmp.setAccount_id(acc.getAccount_id());
        tmp.setAccount_password(SecureUtil.shaEncode(old_pwd));
        Account existAcc = accountService.queryAccountByIdAndPwd(tmp);
        if(existAcc == null){
            return HttpUtil.failure("原密码输入错误！");
        }

        tmp.setAccount_password(SecureUtil.shaEncode(new_pwd));
        int c = accountService.updateAccount(tmp);

        if(c==0){
            return HttpUtil.failure("密码修改失败！");
        }
        acc.setAccount_password(tmp.getAccount_password());
        return HttpUtil.success("密码已修改", account);
    }
}
