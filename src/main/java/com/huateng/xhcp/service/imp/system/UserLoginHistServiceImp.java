package com.huateng.xhcp.service.imp.system;

import com.huateng.xhcp.mapper.system.UserLoginHistMapper;
import com.huateng.xhcp.model.system.UserLoginHist;
import com.huateng.xhcp.service.system.UserLoginHistService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by pansen on 2017/4/14.
 */
@Service
public class UserLoginHistServiceImp implements UserLoginHistService{
    private @Autowired @Setter @Getter UserLoginHistMapper userLoginHistMapper;
    /**
     * 增加用户登录信息
     * @param userLoginHist
     */
    public void addUserLoginHist(UserLoginHist userLoginHist){
        this.userLoginHistMapper.addUserLoginHist(userLoginHist);
    }
}
