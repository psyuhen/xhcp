/**
 * function:
 * StoreList.js
 * @author sam.pan
 * @createTime 2017-06-06 17:18:15
 */
var StoreList = function (options){
	var $this = this;
	var $dataTable = null;
	var $rowData = null;
	var $table = null;
	var _service_name = "com.huateng.xhcp.web.system.StoreController";
	var _param_type = "com.huateng.xhcp.model.system.Store";
	var _page = options.page;
	var _module_id = options.module_id;
	
	/*初始化数据表格*/
	this.initTable = function(){
		var t = new Table({
			"table_id"			: "storeList",
			"ordering"			: false,
			"download"			: {enabled:false},
			"row_btn_enabled"	: true,
			"btn_view_callback"	: btnViewEvent,
			"btn_edit_callback"	: btnEditEvent,
			"btn_del_callback"	: btnDelEvent,
			"service_name"		: _service_name,
			"method_name"		: "queryStore",
			"param_type"		: _param_type,
			"module_name"		: "店铺信息维护",
			"url"				: ctx + "/mgr/store/queryStorePage",
			"formId"			: "conditionForm",
			"tableHeaders"		: ["店铺ID","店铺名称","店铺内容","是否使用","更新时间","创建时间"],
			"columnNames"		: ["store_id","store_name",{"contents":_ellipsis},{"is_use":RenderUtil.yesOrNo},"update_time",{"create_time":RenderUtil.time}]
		});
		$dataTable = t.getDataTable();
		$table = t;
	};
	
	function _ellipsis(data, type, row) {
        if(data == null || data == undefined){
            return "";
        }

        var text = StringUtil.delHtmlTag(data);
        return StringUtil.ellipsis(text, 100);
    }
	
	/*初始化日期控件*/
	this.initDateTimePicker = function(){
		Form.dateTime("b_start_date", "b_end_date");
		//Form.dateTime("div_start_date", "div_end_date", 'YYYY-MM-DD');
	};
	
	/* 查看*/
	function btnViewEvent($rowData){
		var store_id = $rowData.store_id;
		window.location.href = ctx + "/mgr/store/view?store_id=" + store_id + "&page=" + _page + "&module_id=" + _module_id;
	}
	
	/* 编辑*/
	function btnEditEvent($rowData){
		var store_id = $rowData.store_id;
		window.location.href = ctx + "/mgr/store/update?page=mgr&store_id=" + store_id + "&module_id=" + _module_id;
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
		    ParamCheck.initChkObj("store_id", "店铺ID", rowdata.store_id)
		];
		
		ParamCheck.commonSubmit({
			service_name : _service_name,
			method_name : "deleteStore",
			parameter_type : _param_type,
			oper_type : "delete",
			oper_desc : "删除店铺信息信息",
			switch_name : "storeSwitch",
			module_id : "storemgr",
			check_fields : ["store_id"],
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


