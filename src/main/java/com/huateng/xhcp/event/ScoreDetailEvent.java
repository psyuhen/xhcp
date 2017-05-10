package com.huateng.xhcp.event;

import org.springframework.context.ApplicationEvent;

/**
 * Created by pansen on 2017/5/9.
 */
public class ScoreDetailEvent extends ApplicationEvent{

    public ScoreDetailEvent(Object source){
        super(source);
    }
}
