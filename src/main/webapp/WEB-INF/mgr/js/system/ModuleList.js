/**
 * function:
 * ModuleList.js
 * @author pansen
 * @createTime 2015-12-23 16:17:25
 */
var ModuleList = function (options){
	var $this = this;
	var $dataTable = null;
	var $rowData = null;
	var $table = null;
	var _module_id = options.module_id;
	
	/*初始化数据表格*/
	this.initTable = function(){
		var t = new Table({
			"table_id"	: "moduleList",
			"download"			: {enabled:false},
			"row_btn_enabled"	: true,
			"btn_view_callback"	: btnViewEvent,
			"btn_edit_callback"	: btnEditEvent,
			"btn_del_callback"	: btnDelEvent,
			"service_name"	: "com.huateng.xhcp.service.system.ModuleService",
			"method_name"	: "queryModule",
			"param_type"	: "com.huateng.xhcp.model.system.Module",
			"module_name"	: "菜单管理",
			"url"		: ctx + "/mgr/module/queryModulePage",
			"listMap"	: $("#conditionForm").serializeArray(),
			"columns"	: [
		                    { "data": "module_id" },
		                    { "data": "module_name" },
		                    { "data": "pmodule_id" },
		                    { "data": "module_entry" },
		                    { "data": "module_type" },
		                    { "data": "module_valid" },
		                    { "data": "module_pic_big" },
		                    { "data": "module_pic_small" },
		                    { "data": "help_page" },
		                    { "data": "module_order" },
		                    { "data": "module_hide" },
		                    { "data": "module_version" },
		                    { "data": "module_target" }
		                ],
			"columnDefs": [ 
					        {
				                "render": function ( data, type, row ) {
				                	if(data == "1"){
				                		return "<span><i class='glyphicon glyphicon-folder-close'></i>目录</span>";
				                	}else if(data == "2"){
				                		return "<span><i class='glyphicon glyphicon-file'></i>菜单</span>";
				                	}
				                	
				                	return "<span><i class='glyphicon glyphicon-file'></i>功能</span>";
				                },
				                "targets": 4
				            },
				            {
				            	"render": function ( data, type, row ) {
				            		if(data == "1"){
				            			return "<span class='label-success label label-default'>有效</span>";
				            		}
				            		
				            		return "<span class='label-default label label-danger'>无效</span>";
				            	},
				            	"targets": 5
				            },
				            {
				            	"render": function ( data, type, row ) {
				            		if($.trim(data) == "" || data == "0"){
				            			return "<span class='label-success label label-default'>显示</span>";
				            		}
				            		
				            		return "<span class='label-default label label-danger'>隐藏</span>";
				            	},
				            	"targets": 10
				            },
				            {/*module_target*/
				            	"render": function ( data, type, row ) {
				            		if($.trim(data) == "" || data == "_self"){
				            			return "当前页面打开";
				            		}
				            		
				            		return "新页面打开";
				            	},
				            	"targets": 12
				            }
			           ]
		});
		$dataTable = t.getDataTable();
		$table = t;
	};
	
	/* 查看*/
	function btnViewEvent($rowData){
		var module_id = $rowData.module_id;
		window.location.href = mgr_path + "/module/view?module_id=" + _module_id + "&mId=" + module_id;
	}
	
	/* 编辑*/
	function btnEditEvent($rowData){
		var module_id = $rowData.module_id;
		window.location.href = mgr_path + "/module/update?module_id=" + _module_id + "&mId=" + module_id;
	}
	
	/* 删除*/
	function btnDelEvent($rowData){
		delAccount($rowData.module_id);
	}
	
	/*查询按钮事件初始化*/
	this.initBtn = function(){
		/*查询*/
		$("#btn_search").on("click", function(){
			$table.setListMap($("#conditionForm").serializeArray());
			$dataTable.ajax.reload();
		});
		
		/*清空*/
		$("#btn_reset").on("click", function(){
			$("#conditionForm").find(":input").val("");
		});
	};
	
	/*根据module_id删除数据*/
	function delAccount(module_id){
		/*获取Id，调用后台删除数据*/
		tableSupport.removeById(mgr_path + "/module/deleteModuleById", {"module_id" : module_id}, function(responseInfo){
			if(responseInfo.status == "0"){
				Noty.success(responseInfo.desc, "top");
				$dataTable.ajax.reload();/*重新刷新数据*/
			}else{
				Noty.warning(responseInfo.desc, "top");
			}
		});
	}
	
	function init(){
		$this.initTable();
		$this.initBtn();
	}
	
	init();
};


