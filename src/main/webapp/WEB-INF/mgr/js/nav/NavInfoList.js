/**
 * function:
 * NavInfoList.js
 * @author sam.pan
 * @createTime 2017-03-22 23:56:36
 */
var NavInfoList = function (options){
	var $this = this;
	var $dataTable = null;
	var $rowData = null;
	var $table = null;
	var _service_name = "com.huateng.xhcp.web.nav.NavInfoController";
	var _param_type = "com.huateng.xhcp.model.nav.NavInfo";
	var _page = options.page;
	var _module_id = options.module_id;
	
	/*初始化数据表格*/
	this.initTable = function(){
		var t = new Table({
			"table_id"			: "navInfoList",
			"ordering"			: false,
			"download"			: {enabled:false},
			"row_btn_enabled"	: true,
			"btn_view_callback"	: btnViewEvent,
			"btn_edit_callback"	: btnEditEvent,
			"btn_del_callback"	: btnDelEvent,
			"service_name"		: _service_name,
			"method_name"		: "queryNavInfo",
			"param_type"		: _param_type,
			"module_name"		: "导航信息维护",
			"url"				: ctx + "/mgr/nav/queryNavInfoPage",
			"formId"			: "conditionForm",
			"tableHeaders"		: ["导航ID","导航父ID","导航名称","导航地址","是否为外连导航","是否为默认显示","排序"],
			"columnNames"		: ["nav_id","pnav_id","name","url","is_out_link","is_default","order_id"]
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
		var nav_id = $rowData.nav_id;
		window.location.href = mgr_path + "/nav/view?nav_id=" + nav_id + "&page=" + _page + "&module_id=" + _module_id;
	}
	
	/* 编辑*/
	function btnEditEvent($rowData){
		var nav_id = $rowData.nav_id;
		window.location.href = mgr_path + "/nav/update?page=mgr&nav_id=" + nav_id;
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
		    ParamCheck.initChkObj("nav_id", "导航ID", rowdata.nav_id)
		];
		
		ParamCheck.commonSubmit({
			service_name : _service_name,
			method_name : "deleteNavInfo",
			parameter_type : _param_type,
			oper_type : "delete",
			oper_desc : "删除导航信息信息",
			switch_name : "navInfoSwitch",
			module_id : "navmgr",
			check_fields : ["nav_id"],
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


