/**
 * 
 */
package com.huateng.xhcp.model.system;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * @author ps
 *
 */
@ToString
public class UserLoginHist {
	private @Getter @Setter String login_id;
	private @Getter @Setter String account_id;
	private @Getter @Setter String account_name;
	private @Getter @Setter String login_info;
	private @Getter @Setter String login_time;
}
