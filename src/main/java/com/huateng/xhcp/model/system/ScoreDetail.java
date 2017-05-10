/**
 * 
 */
package com.huateng.xhcp.model.system;

import com.huateng.xhcp.model.BaseModel;

import lombok.Getter;
import lombok.Setter;

/**
 * 会员积分明细信息Bean
 * @author sam.pan
 *
 */
public class ScoreDetail extends BaseModel{
	
	private @Setter @Getter String score_id;
	private @Setter @Getter String account_id;
	private @Setter @Getter String score;
	private @Setter @Getter String reason;
	private @Setter @Getter String score_bal;
	private @Setter @Getter String create_time;
	
	
}
