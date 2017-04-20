/**
 * 
 */
package com.huateng.xhcp.model.system;

import com.huateng.xhcp.model.BaseModel;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户信息Bean
 * @author pansen
 *
 */
public class Account extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1745842747942923776L;
	private @Setter @Getter String account_id;
	private @Setter @Getter String account_password;
	private @Setter @Getter String account_status;
	private @Setter @Getter String account_inv_date;
	private @Setter @Getter String invalid_date;
	private @Setter @Getter String account_name;
	private @Setter @Getter String mobile;
	private @Setter @Getter String qq;
	private @Setter @Getter String wechat;
	private @Setter @Getter String create_time;
	private @Setter @Getter String old_password;
	private @Setter @Getter String validate_code;
	private @Setter @Getter String account_type;
	private @Setter @Getter String total_score;

	/* 获取本系统账号(0)与其他系统账号ectip(1)*/
	//private @Setter @Getter Integer client = AccountType.LOCAL.value();
	private @Setter @Getter boolean isSuperUser;
}
