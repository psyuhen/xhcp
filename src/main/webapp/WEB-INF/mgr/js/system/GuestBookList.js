/**
 * function:
 * GuestBookList.js
 * @author sam.pan
 * @createTime 2017-04-20 17:53:20
 */
var GuestBookList = function (options){
	var $this = this;
	var $dataTable = null;
	var $rowData = null;
	var $table = null;
	var _service_name = "com.huateng.xhcp.web.system.GuestBookController";
	var _param_type = "com.huateng.xhcp.model.system.GuestBook";
	var _page = options.page;
	var _module_id = options.module_id;
	
	/*初始化数据表格*/
	this.initTable = function(){
		var t = new Table({
			"table_id"			: "guestBookList",
			"ordering"			: false,
			"download"			: {enabled:false},
			"row_btn_enabled"	: true,
			"btn_view_callback"	: btnViewEvent,
			"btn_edit_callback"	: btnEditEvent,
			"btn_del_callback"	: btnDelEvent,
			"service_name"		: _service_name,
			"method_name"		: "queryGuestBook",
			"param_type"		: _param_type,
			"module_name"		: "访客留言簿信息维护",
			"url"				: ctx + "/mgr/system/guest/queryGuestBookPage",
			"formId"			: "conditionForm",
			"tableHeaders"		: ["留言ID","姓名","电话","邮箱","地址","留言","创建时间"],
			"columnNames"		: ["msg_id","name","phone","email","address","msg_info","create_time"]
		});
		$dataTable = t.getDataTable();
		$table = t;
	};
	
	/*初始化日期控件*/
	this.initDateTimePicker = function(){
		//Form.dateTime("div_start_date", "div_end_date", 'YYYY-MM-DD');
	};
	
	/* 查看*/
	function btnViewEvent($rowData){
		var msg_id = $rowData.msg_id;
		window.location.href = ctx + "/mgr/system/guest/view?msg_id=" + msg_id + "&page=" + _page + "&module_id=" + _module_id;
	}
	
	/* 编辑*/
	function btnEditEvent($rowData){
		var msg_id = $rowData.msg_id;
		window.location.href = ctx + "/mgr/system/guest/update?page=mgr&msg_id=" + msg_id + "&module_id=" + _module_id;
	}
	
	/* 删除*/
	function btnDelEvent($rowData){
		btnDelCallback($rowData);
	}
	
	/*查询按钮事件初始化*/
	this.initBtn = function(){
		/*查询*/
		$("#btn_search").on("click", function(){
			$dataTable.ajax.reload();
		});
		
		/*清空*/
		$("#btn_reset").on("click", function(){
			Form.reset("conditionForm");
		});
	};
	
	/* 删除*/
	function btnDelCallback(rowdata){
		var params = [
		    ParamCheck.initChkObj("msg_id", "信息ID", rowdata.msg_id)
		];
		
		ParamCheck.commonSubmit({
			service_name : _service_name,
			method_name : "deleteGuestBook",
			parameter_type : _param_type,
			oper_type : "delete",
			oper_desc : "删除访客留言簿信息信息",
			switch_name : "guestBookSwitch",
			module_id : "msgmgr",
			check_fields : ["msg_id"],
			params : params,
			success : function(){
				$("#btn_search").click();
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


