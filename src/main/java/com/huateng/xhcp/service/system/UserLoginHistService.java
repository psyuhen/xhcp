package com.huateng.xhcp.service.system;

import com.huateng.xhcp.model.system.UserLoginHist;

/**
 * Created by pansen on 2017/4/14.
 */
public interface UserLoginHistService {
    /**
     * 增加用户登录信息
     * @param userLoginHist
     */
    void addUserLoginHist(UserLoginHist userLoginHist);
}
