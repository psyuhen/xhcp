/**
 * function:
 * MerchGalleryList.js
 * @author sam.pan
 * @createTime 2017-04-03 20:11:21
 */
var MerchGalleryList = function (options){
	var $this = this;
	var $dataTable = null;
	var $rowData = null;
	var $table = null;
	var _service_name = "com.huateng.xhcp.web.product.MerchGalleryController";
	var _param_type = "com.huateng.xhcp.model.product.MerchGallery";
	var _page = options.page;
	var _module_id = options.module_id;
	
	/*初始化数据表格*/
	this.initTable = function(){
		var t = new Table({
			"table_id"			: "merchGalleryList",
			"ordering"			: false,
			"download"			: {enabled:false},
			"row_btn_enabled"	: true,
			"row_btn"			: "101",
			"btn_view_callback"	: btnViewEvent,
			"btn_edit_callback"	: btnEditEvent,
			"btn_del_callback"	: btnDelEvent,
			"service_name"		: _service_name,
			"method_name"		: "queryMerchGallery",
			"param_type"		: _param_type,
			"module_name"		: "产品维护",
			"url"				: ctx + "/mgr/product/gallery/queryMerchGalleryPage",
			"formId"			: "conditionForm",
			"tableHeaders"		: ["图片ID","商品ID","商品名称","图片名称","图片文件名称","文件路径","图片类型"],
			"columnNames"		: ["gallery_id","merch_id","merch_name","name","file_name","path",{"file_type":fileType}]
		});
		$dataTable = t.getDataTable();
		$table = t;
	};

	function fileType(data, type, row) {
		if(data == "0"){
			return "<span class='label label-success'>商品展示图片</span>";
		}

		return "<span class='label label-info'>商品详情图片</span>";
    }
	/*初始化日期控件*/
	this.initDateTimePicker = function(){
		//Form.dateTime("div_start_date", "div_end_date", 'YYYY-MM-DD');
	};
	
	/* 查看*/
	function btnViewEvent($rowData){
		var gallery_id = $rowData.gallery_id;
		window.location.href = mgr_path + "/product/gallery/view?gallery_id=" + gallery_id + "&page=" + _page + "&module_id=" + _module_id;
	}
	
	/* 编辑*/
	function btnEditEvent($rowData){
		var gallery_id = $rowData.gallery_id;
		window.location.href = mgr_path + "/product/gallery/update?page=mgr&gallery_id=" + gallery_id + "&module_id=" + _module_id;
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
		    ParamCheck.initChkObj("gallery_id", "图片ID", rowdata.gallery_id)
		];
		
		ParamCheck.commonSubmit({
			service_name : _service_name,
			method_name : "deleteMerchGallery",
			parameter_type : _param_type,
			oper_type : "delete",
			oper_desc : "删除产品信息",
			switch_name : "merchGallerySwitch",
			module_id : "gallerymgr",
			check_fields : ["gallery_id"],
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


