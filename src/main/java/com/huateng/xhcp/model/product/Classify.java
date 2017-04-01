package com.huateng.xhcp.model.product;

import com.huateng.xhcp.model.BaseModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by sam.pan on 2017/3/13.
 */
@ToString
public class Classify extends BaseModel{
    private @Setter @Getter String classify_id;
    private @Setter @Getter String name;
    private @Setter @Getter String desc;
    private @Setter @Getter String pcls_id;
}
