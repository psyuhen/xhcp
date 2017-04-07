/**
 * function:
 * MerchCarList.js
 * @author sam.pan
 * @createTime 2017-04-07 16:13:37
 */
var MerchCarList = function (options){
	var $this = this;
	var $dataTable = null;
	var $rowData = null;
	var $table = null;
	var _service_name = "com.huateng.xhcp.web.product.MerchCarController";
	var _param_type = "com.huateng.xhcp.model.product.MerchCar";
	var _page = options.page;
	var _module_id = options.module_id;
	
	/*初始化数据表格*/
	this.initTable = function(){
		var t = new Table({
			"table_id"			: "merchCarList",
			"ordering"			: false,
			"download"			: {enabled:false},
			"row_btn_enabled"	: true,
			"btn_view_callback"	: btnViewEvent,
			"btn_edit_callback"	: btnEditEvent,
			"btn_del_callback"	: btnDelEvent,
			"service_name"		: _service_name,
			"method_name"		: "queryMerchCar",
			"param_type"		: _param_type,
			"module_name"		: "购物车维护",
			"url"				: ctx + "/mgr/mgr/product/car/queryMerchCarPage",
			"formId"			: "conditionForm",
			"tableHeaders"		: ["购物车ID","商品ID","购买数量","用户ID","创建时间"],
			"columnNames"		: ["car_id","merch_id","buy_num","account_id","create_time"]
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
		var car_id = $rowData.car_id;
		window.location.href = mgr_path + "/mgr/product/car/view?car_id=" + car_id + "&page=" + _page + "&module_id=" + _module_id;
	}
	
	/* 编辑*/
	function btnEditEvent($rowData){
		var car_id = $rowData.car_id;
		window.location.href = mgr_path + "/mgr/product/car/update?page=mgr&car_id=" + car_id + "&module_id=" + _module_id;
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
		    ParamCheck.initChkObj("car_id", "购物车ID", rowdata.car_id)
		];
		
		ParamCheck.commonSubmit({
			service_name : _service_name,
			method_name : "deleteMerchCar",
			parameter_type : _param_type,
			oper_type : "delete",
			oper_desc : "删除购物车信息",
			switch_name : "merchCarSwitch",
			module_id : "carmgr",
			check_fields : ["car_id"],
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


