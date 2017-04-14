package com.huateng.xhcp.model.product;

import com.huateng.xhcp.model.BaseModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by pansen on 2017/4/11.
 */
@ToString
public class OrderDetail extends BaseModel{

    private @Setter @Getter String order_id;
    private @Setter @Getter String merch_id;
    private @Setter @Getter String merch_name;
    private @Setter @Getter String amount;
    private @Setter @Getter String unit;
    private @Setter @Getter String price;
    private @Setter @Getter String create_time;
}
