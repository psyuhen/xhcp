/**
 * function:
 * ArticleList.js
 * @author sam.pan
 * @createTime 2017-03-14 23:40:33
 */
var ArticleList = function (options){
	var $this = this;
	var $dataTable = null;
	var $rowData = null;
	var $table = null;
	var _service_name = "com.huateng.xhcp.web.article.ArticleController";
	var _param_type = "com.huateng.xhcp.model.article.Article";
	var _page = options.page;
	var _module_id = options.module_id;
	
	/*初始化数据表格*/
	this.initTable = function(){
		var t = new Table({
			"table_id"			: "articleList",
			"download"			: {enabled:false},
			"ordering"			: false,
			"row_btn_enabled"	: true,
			"btn_view_callback"	: btnViewEvent,
			"btn_edit_callback"	: btnEditEvent,
			"btn_del_callback"	: btnDelEvent,
			"service_name"		: _service_name,
			"method_name"		: "queryArticle",
			"param_type"		: _param_type,
			"module_name"		: "资讯信息维护",
			"url"				: ctx + "/mgr/article/queryArticlePage",
			"formId"			: "conditionForm",
			"tableHeaders"		: ["文章ID","标题","日期","内容"],
			"columnNames"		: ["article_id","title","article_date",{"contents":_ellipsis}]
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
		//Form.dateTime("div_start_date", "div_end_date", 'YYYY-MM-DD');
	};
	
	/* 查看*/
	function btnViewEvent($rowData){
		var article_id = $rowData.article_id;
		window.location.href = mgr_path + "/article/view?article_id=" + article_id + "&page=" + _page + "&module_id=" + _module_id;
	}
	
	/* 编辑*/
	function btnEditEvent($rowData){
		var article_id = $rowData.article_id;
		window.location.href = mgr_path + "/article/update?page=mgr&article_id=" + article_id;
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
		    ParamCheck.initChkObj("article_id", "文章ID", rowdata.article_id)
		];
		
		ParamCheck.commonSubmit({
			service_name : _service_name,
			method_name : "deleteArticle",
			parameter_type : _param_type,
			oper_type : "delete",
			oper_desc : "删除资讯信息信息",
			switch_name : "articleSwitch",
			module_id : "articleList",
			check_fields : ["article_id"],
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


