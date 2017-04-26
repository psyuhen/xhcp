/**
 * function:
 * OrderInfoList.js
 * @author sam.pan
 * @createTime 2017-04-11 11:04:09
 */
var OrderDetailList = function (options){
	var $this = this;
	var $dataTable = null;
	var $table = null;
	var _service_name = "com.huateng.xhcp.web.product.OrderInfoController";
	var _param_type = "com.huateng.xhcp.model.product.OrderInfo";
	var _page = options.page;
	var _module_id = options.module_id;
	
	/*初始化数据表格*/
	this.initTable = function(){
		var t = new Table({
			"table_id"			: "orderDetailList",
			"ordering"			: false,
			"download"			: {enabled:false},
			"row_btn_enabled"	: false,
			"service_name"		: _service_name,
			"method_name"		: "queryOrderInfo",
			"param_type"		: _param_type,
			"module_name"		: "订单信息维护",
			"url"				: ctx + "/mgr/product/order/queryOrderDetailInfo",
			"formId"			: "conditionForm",
			"tableHeaders"		: ["订单ID","商品ID","商品名称","成交数量","计量单位","商品单价","创建时间"],
			"columnNames"		: ["order_id","merch_id","merch_name","amount","unit","price", {"create_time":RenderUtil.time}]
		});
		$dataTable = t.getDataTable();
		$table = t;
	};
	
	function init(){
		$this.initTable();
	}
	
	init();
};


