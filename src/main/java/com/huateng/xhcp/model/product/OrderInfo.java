/**
 * 
 */
package com.huateng.xhcp.model.product;

import com.huateng.xhcp.model.BaseModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 订单信息信息Bean
 * @author sam.pan
 *
 */
@ToString
public class OrderInfo extends BaseModel{
	
	private @Setter @Getter String order_id;
	private @Setter @Getter String amount_money;
	private @Setter @Getter String buyer_account_id;
	private @Setter @Getter String buyer_user_name;
	private @Setter @Getter String seller_account_id;
	private @Setter @Getter String seller_user_name;
	private @Setter @Getter String currency_unit;
	private @Setter @Getter String buyer_name;
	private @Setter @Getter String buyer_phone;
	private @Setter @Getter String buyer_mobile;
	private @Setter @Getter String send_type;
	private @Setter @Getter String send_no;
	private @Setter @Getter String send_time;
	private @Setter @Getter String freight;
	private @Setter @Getter String invoice_need;
	private @Setter @Getter String invoice_title;
	private @Setter @Getter String pay_type;
	private @Setter @Getter String buyer_pay_time;
	private @Setter @Getter String trad_time;
	private @Setter @Getter String trad_finish_time;
	private @Setter @Getter String update_time;
	private @Setter @Getter String seller_deliver_time;
	private @Setter @Getter String buyer_confirm_time;
	private @Setter @Getter String seller_del;
	private @Setter @Getter String buyer_del;
	private @Setter @Getter String buyer_del_time;
	private @Setter @Getter String seller_del_time;
	private @Setter @Getter String buyer_score;
	private @Setter @Getter String seller_score;
	private @Setter @Getter String status;
	private @Setter @Getter String province_code;
	private @Setter @Getter String province_name;
	private @Setter @Getter String city_code;
	private @Setter @Getter String city_name;
	private @Setter @Getter String town_code;
	private @Setter @Getter String town_name;
	private @Setter @Getter String address;
	private @Setter @Getter String buyer_advise;
	private @Setter @Getter String remark;

	
}
