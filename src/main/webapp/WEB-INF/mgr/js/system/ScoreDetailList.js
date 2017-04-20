/**
 * function:
 * ScoreDetailList.js
 * @author sam.pan
 * @createTime 2017-04-20 15:44:15
 */
var ScoreDetailList = function (options){
	var $this = this;
	var $dataTable = null;
	var $rowData = null;
	var $table = null;
	var _service_name = "com.huateng.xhcp.web.system.ScoreDetailController";
	var _param_type = "com.huateng.xhcp.model.system.ScoreDetail";
	var _page = options.page;
	var _module_id = options.module_id;
	
	/*初始化数据表格*/
	this.initTable = function(){
		var t = new Table({
			"table_id"			: "scoreDetailList",
			"ordering"			: false,
			"download"			: {enabled:false},
			"row_btn_enabled"	: true,
			"btn_view_callback"	: btnViewEvent,
			"btn_edit_callback"	: btnEditEvent,
			"btn_del_callback"	: btnDelEvent,
			"service_name"		: _service_name,
			"method_name"		: "queryScoreDetail",
			"param_type"		: _param_type,
			"module_name"		: "会员积分明细维护",
			"url"				: ctx + "/mgr/system/score/queryScoreDetailPage",
			"formId"			: "conditionForm",
			"tableHeaders"		: ["积分ID","用户ID","积分","原因","创建时间"],
			"columnNames"		: ["score_id","account_id","score","reason","create_time"]
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
		var score_id = $rowData.score_id;
		window.location.href = ctx + "/mgr/system/score/view?score_id=" + score_id + "&page=" + _page + "&module_id=" + _module_id;
	}
	
	/* 编辑*/
	function btnEditEvent($rowData){
		var score_id = $rowData.score_id;
		window.location.href = ctx + "/mgr/system/score/update?page=mgr&score_id=" + score_id + "&module_id=" + _module_id;
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
		    ParamCheck.initChkObj("score_id", "会员积分明细ID", rowdata.score_id)
		];
		
		ParamCheck.commonSubmit({
			service_name : _service_name,
			method_name : "deleteScoreDetail",
			parameter_type : _param_type,
			oper_type : "delete",
			oper_desc : "删除会员积分明细信息",
			switch_name : "scoreDetailSwitch",
			module_id : "scoremgr",
			check_fields : ["score_id"],
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


