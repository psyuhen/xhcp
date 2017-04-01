/**
 * function:
 * AccountList.js
 * @author pansen
 * @createTime 2015-12-23 16:17:25
 */
var AccountList = function (options){
	var $this = this;
	var $dataTable = null;
	var $table = null;
	var _module_id = options.module_id
	
	/*初始化数据表格*/
	this.initTable = function(){
		var t = new Table({
			"table_id"	: "accountList",
			"download"			: {enabled:false},
			"row_btn_enabled"	: true,
			"btn_view_callback"	: btnViewEvent,
			"btn_edit_callback"	: btnEditEvent,
			"btn_del_callback"	: btnDelEvent,
			"service_name"	: "com.huateng.xhcp.service.system.AccountService",
			"method_name"	: "queryAccount",
			"param_type"	: "com.huateng.xhcp.model.system.Account",
			"module_name"	: "用户管理",
			"url"		: ctx + "/mgr/user/queryAccountPage",
			"formId"			: "conditionForm",
			"tableHeaders"		: ["账号","名称","密码","状态","可用日期","手机号码","qq","微信"],
			"columnNames"		: ["account_id","account_name",{"account_password":renderPwd},
				{"account_status":renderStatus},{"account_inv_date":renderDate},"mobile","qq","wechat"]
		});
		$dataTable = t.getDataTable();
		$table = t;
	};

	function renderStatus(data, type, row){
		if(data == "1"){
			return "<span class='label-success label label-default'>可用</span>";
		}
		return "<span class='label-default label label-danger'>锁定</span>";
	}
	function renderDate(data, type, row){
		var tmp = $.trim(data);
		if(tmp == ""){
			return "永久";
		}
		return DateUtil.dateFormat(data, "yyyy年MM月dd日");
	}
	function renderPwd(data, type, row){
		return StringUtil.ellipsis(data, 20);
	}
	/*初始化日期控件*/
	this.initDateTimePicker = function(){
		Form.dateTime("div_start_date", "div_end_date", 'YYYY-MM-DD');
	};
	
	/* 查看*/
	function btnViewEvent($rowData){
		var account_id = $rowData.account_id;
		window.location.href = mgr_path + "/user/view?module_id=" +_module_id+ "&account_id=" + account_id;
	}
	
	/* 编辑*/
	function btnEditEvent($rowData){
		var account_id = $rowData.account_id;
		window.location.href = mgr_path + "/user/update?module_id=" +_module_id+ "&account_id=" + account_id;
	}
	
	/* 删除*/
	function btnDelEvent($rowData){
		delAccount($rowData.account_id);
	}
	
	/*查询按钮事件初始化*/
	this.initBtn = function(){
		/*查询*/
		$("#btn_search").on("click", function(){
			$table.setListMap($("#conditionForm").serializeArray());
			$dataTable.ajax.reload();
		});
		
		/*清空*/
		$("#btn_reset").on("click", function(){
			$("#conditionForm").find(":input").val("");
		});
	};
	
	/*根据account_id删除数据*/
	function delAccount(account_id){
		/*获取Id，调用后台删除数据*/
		tableSupport.removeById(mgr_path + "/user/deleteAccount", {"account_id" : account_id}, function(responseInfo){
			if(responseInfo.status == "0"){
				Noty.success(responseInfo.desc);
				$dataTable.ajax.reload();/*重新刷新数据*/
			}else{
				Noty.warning(responseInfo.desc);
			}
		});
	}
	
	function init(){
		$this.initTable();
		$this.initDateTimePicker();
		$this.initBtn();
	}
	
	init();
};

