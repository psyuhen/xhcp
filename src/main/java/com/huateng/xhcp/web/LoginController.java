package com.huateng.xhcp.web;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huateng.xhcp.event.ScoreDetailEvent;
import com.huateng.xhcp.model.ResponseInfo;
import com.huateng.xhcp.model.product.FreqAddr;
import com.huateng.xhcp.model.product.OrderInfo;
import com.huateng.xhcp.model.system.Account;
import com.huateng.xhcp.model.system.AccountStatus;
import com.huateng.xhcp.model.system.AccountType;
import com.huateng.xhcp.model.system.Module;
import com.huateng.xhcp.model.system.Province;
import com.huateng.xhcp.model.system.Role;
import com.huateng.xhcp.model.system.ScoreDetail;
import com.huateng.xhcp.model.system.UserLoginHist;
import com.huateng.xhcp.security.RandomValidateCode;
import com.huateng.xhcp.security.SecurityContext;
import com.huateng.xhcp.service.product.FreqAddrService;
import com.huateng.xhcp.service.product.OrderInfoService;
import com.huateng.xhcp.service.system.AccountService;
import com.huateng.xhcp.service.system.ModuleService;
import com.huateng.xhcp.service.system.ProvinceService;
import com.huateng.xhcp.service.system.UserLoginHistService;
import com.huateng.xhcp.util.DateUtil;
import com.huateng.xhcp.util.HttpUtil;
import com.huateng.xhcp.util.SecureUtil;

/**
 * Created by sam.pan on 2017/3/23.
 */
@Controller
public class LoginController {
    private static final Log LOGGER = LogFactory.getLog(LoginController.class);
    private @Autowired AccountService accountService;
    private @Autowired ProvinceService provinceService;
    private @Autowired FreqAddrService freqAddrService;
    private @Autowired UserLoginHistService userLoginHistService;
    private @Autowired OrderInfoService orderInfoService;
    private @Autowired ModuleService moduleService;
    private @Autowired ApplicationEventPublisher publisher;

    @RequestMapping(value="/login.html")
    public String loginPage(HttpServletRequest request){
        final HttpSession session = request.getSession();
        String referer = request.getHeader("Referer");
        session.setAttribute("blackUrl", referer);

        return "login";
    }

    @RequestMapping(value="/mgr/login")
    public String tologinPage(HttpServletRequest request, HttpServletResponse response){
        return "system/Login";
    }

    @RequestMapping(value="/register.html")
    public String registerPage(){
        return "register";
    }

    @RequestMapping(value="/forget.html")
    public String forgetPage(HttpServletRequest request){
        request.setAttribute("step1", "true");
        return "forget";
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
    public String orderPage(HttpServletRequest request){

        final Account frontAccount = SecurityContext.getFrontAccount();
        if(frontAccount == null){
            return "login";
        }


        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setB_order_name("trad_time");
        orderInfo.setB_order_asc("desc");
        orderInfo.setBuyer_account_id(frontAccount.getAccount_id());
        orderInfo.setLimit(15);
        orderInfo.setStart(1);
        final List<OrderInfo> orderInfos = orderInfoService.queryOrderInfo(orderInfo);

        request.setAttribute("orderlist", orderInfos);

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
    @RequestMapping(value="/mgr/tologin.do", method = RequestMethod.POST)
    public String loginMgr(Account account, @RequestParam  String toBlackUrl, HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("toBlackUrl", toBlackUrl);
        if(account == null){
            LOGGER.info("account is null");
            return "system/Login";
        }

        String account_id = account.getAccount_id();
        String mobile = account.getMobile();
        String account_password = account.getAccount_password();

        if(StringUtils.isBlank(account_id) && StringUtils.isBlank(mobile)){
            LOGGER.info("用户账号和手机号码不能同时为空");
            request.setAttribute("msg", "用户账号和手机号码不能同时为空");
            return "system/Login";
        }

        if(StringUtils.isBlank(account_password)){
            LOGGER.info("密码不能为空！");
            request.setAttribute("msg", "密码不能为空！");
            return "system/Login";
        }

        if(account_password.length() < 40){
            account_password = SecureUtil.shaEncode(account_password);
        }
        Account acc = new Account();
        acc.setAccount_id(account_id);
        acc.setAccount_name(account_id);
        acc.setMobile(mobile);
        acc.setAccount_password(account_password);
        Account accounts = accountService.queryAccountByIdAndPwd(acc);
        if(accounts == null){
            LOGGER.info("用户/密码错误");
            request.setAttribute("msg", "用户/密码错误");
            return "system/Login";
        }

        if("0".equals(accounts.getAccount_status())){
            LOGGER.info("用户已被锁定");
            request.setAttribute("msg", "用户已被锁定");
            return "system/Login";
        }

        String invDate = accounts.getAccount_inv_date();
        if(!StringUtils.isBlank(invDate) && invDate.compareTo(DateUtil.today("yyyyMMdd")) < 0){
            LOGGER.info("用户已失效");
            request.setAttribute("msg", "用户已失效");
            return "system/Login";
        }

        if(StringUtils.equals(AccountType.MEMBER.toString(), accounts.getAccount_type())){
            LOGGER.info("会员用户不能登录");
            request.setAttribute("msg", "会员用户不能登录后台管理");
            return "system/Login";
        }

        //保存用户密码到cookie中
        String remember = request.getParameter("remember");
        LOGGER.info("remember :" + remember);
        if("1".equals(remember)){//写入cookie
            Cookie nameCookie = new Cookie("account_id", account.getAccount_id());
            Cookie pwdCookie = new Cookie("account_password", account_password);
            Cookie rmCookie = new Cookie("remember",remember);
            nameCookie.setMaxAge(24 * 60 * 60);
            pwdCookie.setMaxAge(24 * 60 * 60);
            rmCookie.setMaxAge(24 * 60 * 60);
            response.addCookie(nameCookie);
            response.addCookie(pwdCookie);
            response.addCookie(rmCookie);
        }else{//不保存cookie，要清空
            Cookie[] cookies = request.getCookies();
            for(Cookie cookie : cookies){
                if("account_id".equals(cookie.getName()) || "account_password".equals(cookie.getName())
                        || "remember".equals(cookie.getName())){
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }

        /*登录信息*/
        UserLoginHist userLoginHist = new UserLoginHist();
        userLoginHist.setAccount_id(account_id);
        userLoginHist.setAccount_name(account_id);
        userLoginHistService.addUserLoginHist(userLoginHist);

        HttpSession session = request.getSession();

        /* 超级用户的判断 */
        accounts.setSuperUser(SecurityContext.isSuperUser(account_id));
        /* 查询用户角色*/
        List<Role> roleList = accountService.queryBelongRoleByAccountId(account_id);
        if(roleList != null && !roleList.isEmpty()){
            //写入session
            session.setAttribute("user_role", roleList);
        }

        //超级用户不查资源
        if(!accounts.isSuperUser()){
            List<Module> moduleList = moduleService.findModuleByAccount(account_id);
            if(moduleList != null){
                int length = moduleList.size();
                Set<String> urlSet = new HashSet<String>(length, 1);
                for (int i = 0; i < length; i++) {
                    Module m = moduleList.get(i);
                    String module_entry = m.getModule_entry();
                    if(module_entry == null){
                        continue;
                    }

                    urlSet.add("/mgr" + module_entry);
                }
                session.setAttribute("urlSet", urlSet);
            }
        }


        session.setAttribute(SecurityContext.BACK_ACCOUNT, accounts);

        /* 返回之前的url*/
        if(!StringUtils.isBlank(toBlackUrl)){
            return "redirect:" + toBlackUrl;
        }

        return "main";
    }
        /**
         * 根据手机号码和密码登录
         * @param account 用户 信息
         * @return 查询到返回用户 信息否则返回null
         */
    @RequestMapping(value="/login.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseInfo> login(Account account, @RequestParam  String blackUrl, HttpServletRequest request) {
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
        acc.setAccount_name(account_id);
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

        /*登录信息*/
        UserLoginHist userLoginHist = new UserLoginHist();
        userLoginHist.setAccount_id(account_id);
        userLoginHist.setAccount_name(account_id);
        userLoginHistService.addUserLoginHist(userLoginHist);

        session.setAttribute(SecurityContext.FRONT_ACCOUNT, accounts);

        final ResponseEntity<ResponseInfo> success = HttpUtil.success("登录成功", blackUrl);

        session.setAttribute("blackUrl", "");
        return success;
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
        account.setAccount_name(mobile);
        account.setAccount_status(AccountStatus.ACTIVE.toString());
        account.setAccount_password(account_password);
        account.setAccount_type(AccountType.MEMBER.toString());
        account.setTotal_score("800");

        int c = accountService.addAccount(account);
        if(c == 0){
            return HttpUtil.failure("用户注册失败！");
        }

        //
        ScoreDetail sd = new ScoreDetail();
        sd.setAccount_id(account.getAccount_id());
        sd.setScore("800");
        sd.setReason("新会员注册");
        sd.setScore_bal("800");
        publisher.publishEvent(new ScoreDetailEvent(sd));


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
     * 后端用户退出
     * @return
     */
    @RequestMapping(value="/mgr/logout", method = RequestMethod.GET)
    public String tologout(HttpServletRequest request) {
        request.getSession().setAttribute(SecurityContext.BACK_ACCOUNT, null);
        request.getSession().invalidate();
        return "forward:/mgr/login";
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


    /**
     * 忘记密码
     * @param account 手机号
     * @return 查询到返回用户 信息否则返回null
     */
    @RequestMapping(value="/forget.do", method = RequestMethod.POST)
    public String forget(Account account,String step, HttpServletRequest request) {

        if(StringUtils.isBlank(step)){
            String mobile = account.getMobile();
            String validate_code = account.getValidate_code();

            if(StringUtils.isBlank(mobile)){
                request.setAttribute("step1", "true");
                request.setAttribute("mobileError", "手机号不能为空！");
                return "forget";
            }

            if(StringUtils.isBlank(validate_code)){
                request.setAttribute("step1", "true");
                request.setAttribute("validateError", "验证码不能为空！");
                return "forget";
            }

            HttpSession session = request.getSession();
            String code = (String)session.getAttribute(RandomValidateCode.RANDOMCODEKEY);
            if(!StringUtils.equals(validate_code, code)){
                request.setAttribute("step1", "true");
                request.setAttribute("validateError", "验证码不正确！");
                return "forget";
            }

            Account tmp = new Account();
            tmp.setMobile(mobile);
            Account tmp1 = accountService.queryByMobile(mobile);

            if(tmp1 == null){
                request.setAttribute("step1", "true");
                request.setAttribute("mobileError", "手机号码不存在！");
                return "forget";
            }

            request.setAttribute("mobile", mobile);
            request.setAttribute("step2", "true");
            return "forget";
        }


        if(StringUtils.equals(step, "2")){
            String old_pwd = account.getOld_password();
            String new_pwd = account.getAccount_password();
            String validate_code = account.getValidate_code();

            if(StringUtils.isBlank(new_pwd)){
                request.setAttribute("step2", "true");
                request.setAttribute("pwdError", "新密码不能为空！");
                return "forget";
            }

            if(new_pwd.length() < 6){
                request.setAttribute("step2", "true");
                request.setAttribute("pwdError", "新密码长度不能少于6位！");
                return "forget";
            }

            if(StringUtils.isBlank(old_pwd)){
                request.setAttribute("step2", "true");
                request.setAttribute("confirmPwdError", "确认密码不能为空！");
                return "forget";
            }

            if(!StringUtils.equals(new_pwd, old_pwd)){
                request.setAttribute("step2", "true");
                request.setAttribute("confirmPwdError", "新密码与确认密码不相等！");
                return "forget";
            }

            HttpSession session = request.getSession();
            String code = (String)session.getAttribute(RandomValidateCode.RANDOMCODEKEY);
            if(!StringUtils.equals(validate_code, code)){
                request.setAttribute("step2", "true");
                request.setAttribute("validateError", "验证码不正确！");
                return "forget";
            }

            final String mobile = account.getMobile();
            Account tmp1 = accountService.queryByMobile(mobile);
            if(tmp1 == null){
                request.setAttribute("step1", "true");
                request.setAttribute("mobileError", "手机号码不存在！");
                return "forget";
            }

            Account tmp = new Account();
            tmp.setAccount_id(tmp1.getAccount_id());
            tmp.setAccount_password(SecureUtil.shaEncode(new_pwd));
            int c = accountService.updateAccount(tmp);
            if(c == 0){
                request.setAttribute("step2", "true");
                request.setAttribute("pwdError", "修改密码失败！");
                return "forget";
            }

            return "login";
        }


        return "forget";
    }

}
