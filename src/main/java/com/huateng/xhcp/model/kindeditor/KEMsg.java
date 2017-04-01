package com.huateng.xhcp.model.kindeditor;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by sam.pan on 2017/3/16.
 */
@ToString
public class KEMsg {
    private @Getter @Setter int error;
    private @Getter @Setter String url;
    private @Getter @Setter String message;
    private @Getter @Setter String data;
}
