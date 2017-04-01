/**
 * function:
 * VideoInfoList.js
 * @author sam.pan
 * @createTime 2017-03-27 10:43:38
 */
var VideoInfoList = function (options){
	var $this = this;
	var $dataTable = null;
	var $rowData = null;
	var $table = null;
	var _service_name = "com.huateng.xhcp.web.video.VideoInfoController";
	var _param_type = "com.huateng.xhcp.model.video.VideoInfo";
	var _page = options.page;
	var _module_id = options.module_id;
	
	/*初始化数据表格*/
	this.initTable = function(){
		var t = new Table({
			"table_id"			: "videoInfoList",
			"ordering"			: false,
			"download"			: {enabled:false},
			"row_btn_enabled"	: true,
			"btn_view_callback"	: btnViewEvent,
			"btn_edit_callback"	: btnEditEvent,
			"btn_del_callback"	: btnDelEvent,
			"service_name"		: _service_name,
			"method_name"		: "queryVideoInfo",
			"param_type"		: _param_type,
			"module_name"		: "视频信息维护",
			"url"				: ctx + "/mgr/video/queryVideoInfoPage",
			"formId"			: "conditionForm",
			"tableHeaders"		: ["视频信息id","标题","背景图片ID","背景图片","视频文件ID","视频"],
			"columnNames"		: ["video_id","title","img_file_id",{"img_file_url":img},"video_file_id",{"video_file_url":video}]
		});
		$dataTable = t.getDataTable();
		$table = t;
	};
	
	function img(data, type, row) {
		if($.trim(data) == ""){
			return "";
		}
		return "<img src='" +ctx+ "/"+ data +"' style='width: 30px;height: 30px;'/>"
	}
	function video(data, type, row) {
		if($.trim(data) == ""){
			return "";
		}
		return '<video src="'+ctx+'/'+data+'"  width="320" height="240" controls="controls">	Your browser does not support the video tag.</video>';
	}
	
	/*初始化日期控件*/
	this.initDateTimePicker = function(){
		//Form.dateTime("div_start_date", "div_end_date", 'YYYY-MM-DD');
	};
	
	/* 查看*/
	function btnViewEvent($rowData){
		var video_id = $rowData.video_id;
		window.location.href = mgr_path + "/video/view?video_id=" + video_id + "&page=" + _page + "&module_id=" + _module_id;
	}
	
	/* 编辑*/
	function btnEditEvent($rowData){
		var video_id = $rowData.video_id;
		window.location.href = mgr_path + "/video/update?page=mgr&video_id=" + video_id + "&module_id=" + _module_id;
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
		    ParamCheck.initChkObj("video_id", "视频信息id", rowdata.video_id)
		];
		
		ParamCheck.commonSubmit({
			service_name : _service_name,
			method_name : "deleteVideoInfo",
			parameter_type : _param_type,
			oper_type : "delete",
			oper_desc : "删除视频信息信息",
			switch_name : "videoInfoSwitch",
			module_id : "videomgr",
			check_fields : ["video_id"],
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


