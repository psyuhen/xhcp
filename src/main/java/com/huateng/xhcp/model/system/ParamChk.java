package com.huateng.xhcp.model.system;

import java.util.List;

import com.huateng.xhcp.model.BaseModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 复核参数信息Bean  
 * @see 参数复核表PARAM_CHK、参数复核历史表TBL_PARAM_CHK_HIST
 * @author chengz
 *  
 */
@ToString
public class ParamChk extends BaseModel{
	//表字段
	private @Setter @Getter String check_flow_no;//复核流水号 | 主键
	private @Setter @Getter String submit_oper;//提交人
	private @Setter @Getter String submit_date;//提交日期
	private @Setter @Getter String submit_time;//提交时间
	private @Setter @Getter String oper_type;//操作类型
	private @Setter @Getter String oper_desc;//操作描述
	private @Setter @Getter String oper_trad;//操作交易
	private @Setter @Getter String pre_trad;//前缀交易
	private @Setter @Getter String post_trad;//交易位置
	private @Setter @Getter String remark;//备注 
	private @Setter @Getter String status;//状态：0.未复核；1.复核锁定；2.复核拒绝；3.复核通过；
	private @Setter @Getter String chk_no;//复核人
	private @Setter @Getter String chk_date;//复核日期
	private @Setter @Getter String chk_time;//复核时间
	private @Setter @Getter String chk_inst_no;//复核机构名
	private @Setter @Getter String chk_option;//复核选项
	private @Setter @Getter String rpt_bank;//上报分行
	/* add by shijunkai */
	private @Setter @Getter String service_name;
	private @Setter @Getter String method_name;
	private @Setter @Getter String parameter_type;
	private @Setter @Getter String submit_no;

	//自定义字段
	private @Setter @Getter String submit_date_start;//开始提交日期
	private @Setter @Getter String submit_date_end;//结束提交日期
	private @Setter @Getter String chk_date_start;//开始复核日期
	private @Setter @Getter String chk_date_end;//结束复核日期
	private @Setter @Getter String user;//当前用户ID
	private @Setter @Getter List<String> roleList;//当前用户可复核角色
	private @Setter @Getter List<String> userList;//当前用户可复核用户
	/* add by shijunkai */
	private @Setter @Getter Object params;
	private @Setter @Getter String switch_name;
	private @Setter @Getter String module_id;
	private @Setter @Getter String check_fields;
	private @Setter @Getter String exist_continue;

	public ParamChk(String check_flow_no, String status, String remark, String chk_no, String chk_date, String chk_time) {
		super();
		this.check_flow_no = check_flow_no;
		this.status = status;
		this.remark = remark;
		this.chk_no = chk_no;
		this.chk_date = chk_date;
		this.chk_time = chk_time;
	}

	public ParamChk(String service_name, String method_name, String parameter_type, String oper_type, String oper_desc) {
		super();
		this.service_name = service_name;
		this.method_name = method_name;
		this.parameter_type = parameter_type;
		this.oper_type = oper_type;
		this.oper_desc = oper_desc;
	}

	public ParamChk() {
		super();
	}
}
