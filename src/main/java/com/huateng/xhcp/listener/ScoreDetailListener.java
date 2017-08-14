package com.huateng.xhcp.listener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import com.huateng.xhcp.event.ScoreDetailEvent;
import com.huateng.xhcp.model.system.Account;
import com.huateng.xhcp.model.system.ScoreDetail;
import com.huateng.xhcp.security.SecurityContext;
import com.huateng.xhcp.service.system.ScoreDetailService;
import com.huateng.xhcp.util.StringUtil;

/**
 * Created by pansen on 2017/5/9.
 */
@EnableAsync
@Service
public class ScoreDetailListener {
    private static final Log LOGGER = LogFactory.getLog(ScoreDetailListener.class);
    private @Autowired ScoreDetailService scoreDetailService;

    @Async
    @EventListener
    public void handleLoginEvent(ScoreDetailEvent scoreDetailEvent) {
        Object obj = scoreDetailEvent.getSource();
        if(obj instanceof ScoreDetail){
            LOGGER.debug("增加积分明细");
            ScoreDetail sd = (ScoreDetail)obj;

            //获取用户的最新余额
            Integer bal = scoreDetailService.queryScoreBal(sd.getAccount_id());
            sd.setScore_bal(String.valueOf(StringUtil.add(sd.getScore(), bal)));

            final int c = scoreDetailService.addScoreDetail(sd);
            if(c > 0){
                final Account frontAccount = SecurityContext.getFrontAccount();
                frontAccount.setTotal_score(String.valueOf(StringUtil.add(frontAccount.getTotal_score(), sd.getScore())));
            }
        }
    }
}
