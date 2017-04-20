/**
 * function:
 * VipLevelList.js
 * @author sam.pan
 * @createTime 2017-04-20 10:18:58
 */
var VipLevelList = function (options){
	var $this = this;
	var $dataTable = null;
	var $rowData = null;
	var $table = null;
	var _service_name = "com.huateng.xhcp.web.system.VipLevelController";
	var _param_type = "com.huateng.xhcp.model.system.VipLevel";
	var _page = options.page;
	var _module_id = options.module_id;
	
	/*初始化数据表格*/
	this.initTable = function(){
		var t = new Table({
			"table_id"			: "vipLevelList",
			"ordering"			: false,
			"download"			: {enabled:false},
			"row_btn_enabled"	: true,
			"btn_view_callback"	: btnViewEvent,
			"btn_edit_callback"	: btnEditEvent,
			"btn_del_callback"	: btnDelEvent,
			"service_name"		: _service_name,
			"method_name"		: "queryVipLevel",
			"param_type"		: _param_type,
			"module_name"		: "会员等级维护",
			"url"				: ctx + "/mgr/system/vip/queryVipLevelPage",
			"formId"			: "conditionForm",
			"tableHeaders"		: ["会员等级ID","等级名称","积分要求","享受折扣","创建时间"],
			"columnNames"		: ["vip_id","name","score","discount","create_time"]
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
		var vip_id = $rowData.vip_id;
		window.location.href = ctx + "/mgr/system/vip/view?vip_id=" + vip_id + "&page=" + _page + "&module_id=" + _module_id;
	}
	
	/* 编辑*/
	function btnEditEvent($rowData){
		var vip_id = $rowData.vip_id;
		window.location.href = ctx + "/mgr/system/vip/update?page=mgr&vip_id=" + vip_id + "&module_id=" + _module_id;
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
		    ParamCheck.initChkObj("vip_id", "会员等级ID", rowdata.vip_id)
		];
		
		ParamCheck.commonSubmit({
			service_name : _service_name,
			method_name : "deleteVipLevel",
			parameter_type : _param_type,
			oper_type : "delete",
			oper_desc : "删除会员等级信息",
			switch_name : "vipLevelSwitch",
			module_id : "vipmgr",
			check_fields : ["vip_id"],
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


