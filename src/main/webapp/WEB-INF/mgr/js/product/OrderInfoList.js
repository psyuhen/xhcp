/**
 * function:
 * OrderInfoList.js
 * @author sam.pan
 * @createTime 2017-04-11 11:04:09
 */
var OrderInfoList = function (options){
	var $this = this;
	var $dataTable = null;
	var $rowData = null;
	var $table = null;
	var _service_name = "com.huateng.xhcp.web.product.OrderInfoController";
	var _param_type = "com.huateng.xhcp.model.product.OrderInfo";
	var _page = options.page;
	var _module_id = options.module_id;
	
	/*初始化数据表格*/
	this.initTable = function(){
		var t = new Table({
			"table_id"			: "orderInfoList",
			"ordering"			: false,
			"download"			: {enabled:false},
			"row_btn_enabled"	: true,
			"btn_view_callback"	: btnViewEvent,
			"btn_edit_callback"	: btnEditEvent,
			"btn_del_callback"	: btnDelEvent,
            "row_other_btn_enabled" : true,
            "row_other_btns"	: [{
                name : "btn_belong_role",
                text : "订单详情",
                event: btnDetailEvent
            }],
			"service_name"		: _service_name,
			"method_name"		: "queryOrderInfo",
			"param_type"		: _param_type,
			"module_name"		: "订单信息维护",
			"url"				: ctx + "/mgr/product/order/queryOrderInfoPage",
			"formId"			: "conditionForm",
			"tableHeaders"		: ["订单ID","买家用户账号","买家用户名","收货人","成交金额","交易状态"],
			"columnNames"		: ["order_id","buyer_account_id","buyer_user_name","buyer_name","amount_money",{"status":RenderUtil.trad_status}]
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
		var order_id = $rowData.order_id;
		window.location.href = ctx + "/mgr/product/order/view?order_id=" + order_id + "&page=" + _page + "&module_id=" + _module_id;
	}
	
	/* 编辑*/
	function btnEditEvent($rowData){
		var order_id = $rowData.order_id;
		window.location.href = ctx + "/mgr/product/order/update?page=mgr&order_id=" + order_id + "&module_id=" + _module_id;
	}
	
	/* 删除*/
	function btnDelEvent($rowData){
		btnDelCallback($rowData);
	}

	/* 订单详情*/
    function btnDetailEvent($rowData){
        var order_id = $rowData.order_id;
        window.location.href = ctx + "/mgr/product/order/detail?module_id=" +_module_id+ "&order_id=" + order_id+ "&page=" + _page;
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
		    ParamCheck.initChkObj("order_id", "订单ID", rowdata.order_id)
		];
		
		ParamCheck.commonSubmit({
			service_name : _service_name,
			method_name : "deleteOrderInfo",
			parameter_type : _param_type,
			oper_type : "delete",
			oper_desc : "删除订单信息信息",
			switch_name : "orderInfoSwitch",
			module_id : "ordermgr",
			check_fields : ["order_id"],
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


