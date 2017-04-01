/**
 * function:
 * MerchInfoList.js
 * @author sam.pan
 * @createTime 2017-03-29 15:20:01
 */
var MerchInfoList = function (options){
	var $this = this;
	var $dataTable = null;
	var $rowData = null;
	var $table = null;
	var _service_name = "com.huateng.xhcp.web.product.MerchInfoController";
	var _param_type = "com.huateng.xhcp.model.product.MerchInfo";
	var _page = options.page;
	var _module_id = options.module_id;
	
	/*初始化数据表格*/
	this.initTable = function(){
		var t = new Table({
			"table_id"			: "merchInfoList",
			"ordering"			: false,
			"download"			: {enabled:false},
			"row_btn_enabled"	: true,
			"btn_view_callback"	: btnViewEvent,
			"btn_edit_callback"	: btnEditEvent,
			"btn_del_callback"	: btnDelEvent,
			"service_name"		: _service_name,
			"method_name"		: "queryMerchInfo",
			"param_type"		: _param_type,
			"module_name"		: "产品维护",
			"url"				: ctx + "/mgr/product/queryMerchInfoPage",
			"formId"			: "conditionForm",
			"tableHeaders"		: ["商品ID","商品名称","商品描述","分类ID","单价","库存","上架时间","是否下架","修改时间","创建时间","店长推荐","是否包邮","单位","重量","规格","点击数"],
			"columnNames"		: ["merch_id","name","desc","classify_id","price","in_stock","published_date","out_published","update_time","create_time","sm_recommend","free_shipping","unit","weight","standard","hits"]
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
		var merch_id = $rowData.merch_id;
		window.location.href = mgr_path + "/product/view?merch_id=" + merch_id + "&page=" + _page + "&module_id=" + _module_id;
	}
	
	/* 编辑*/
	function btnEditEvent($rowData){
		var merch_id = $rowData.merch_id;
		window.location.href = mgr_path + "/product/update?page=mgr&merch_id=" + merch_id + "&module_id=" + _module_id;
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
		    ParamCheck.initChkObj("merch_id", "产品ID", rowdata.merch_id)
		];
		
		ParamCheck.commonSubmit({
			service_name : _service_name,
			method_name : "deleteMerchInfo",
			parameter_type : _param_type,
			oper_type : "delete",
			oper_desc : "删除产品信息",
			switch_name : "merchInfoSwitch",
			module_id : "productmgr",
			check_fields : ["merch_id"],
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


