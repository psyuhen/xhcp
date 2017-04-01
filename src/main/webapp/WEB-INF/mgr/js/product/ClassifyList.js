/**
 * function:
 * ClassifyList.js
 * @author sam.pan
 * @createTime 2017-03-13 23:33:35
 */
var ClassifyList = function (options){
	var $this = this;
	var $dataTable = null;
	var $rowData = null;
	var $table = null;
	var _service_name = "com.huateng.xhcp.web.product.ClassifyController";
	var _param_type = "com.huateng.xhcp.model.product.Classify";
	var _page = options.page;
	var _module_id = options.module_id;
	
	/*初始化数据表格*/
	this.initTable = function(){
		var t = new Table({
			"table_id"			: "classifyList",
			"download"			: {enabled:false},
			"ordering"			: false,
			"row_btn_enabled"	: true,
			"btn_view_callback"	: btnViewEvent,
			"btn_edit_callback"	: btnEditEvent,
			"btn_del_callback"	: btnDelEvent,
			"service_name"		: _service_name,
			"method_name"		: "queryClassify",
			"param_type"		: _param_type,
			"module_name"		: "产品分类信息维护",
			"url"				: ctx + "/mgr/classify/queryClassifyPage",
			"formId"			: "conditionForm",
			"tableHeaders"		: ["分类ID","分类名称","分类描述","父分类ID"],
			"columnNames"		: ["classify_id","name","desc","pcls_id"]
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
		var classify_id = $rowData.classify_id;
		window.location.href = mgr_path + "/classify/view?classify_id=" + classify_id + "&page=" + _page + "&module_id=" + _module_id;
	}
	
	/* 编辑*/
	function btnEditEvent($rowData){
		var classify_id = $rowData.classify_id;
		window.location.href = mgr_path + "/classify/update?page=mgr&classify_id=" + classify_id + "&module_id=" + _module_id;
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
		    ParamCheck.initChkObj("classify_id", "服务器名称", rowdata.classify_id)
		];
		
		ParamCheck.commonSubmit({
			service_name : _service_name,
			method_name : "deleteClassify",
			parameter_type : _param_type,
			oper_type : "delete",
			oper_desc : "删除产品分类信息信息",
			switch_name : "classifySwitch",
			module_id : "classifyList",
			check_fields : ["classify_id"],
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


