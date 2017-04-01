/**
 * function:
 * ArticleAdd.js
 * @author sam.pan
 * @createTime 2017-03-14 23:40:33
 */
var Article = function (options){
	var $this = this;
	var _oper = options.oper || "add";
	var _article_id = options.article_id;
	var _controller_name = "com.huateng.xhcp.web.article.ArticleController";
	var _param_type = "com.huateng.xhcp.model.article.Article";
	var _KE;

	function initEditor(){
		KindEditor.ready(function (K) {
			_KE = K.create('#contents', {
				resizeType : 1,
				allowPreviewEmoticons : false,
				allowImageUpload : true,
				autoHeightMode : true,
				uploadJson : ctx + '/mgr/article/uploadFile',
				items : [
					'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
					'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
					'insertunorderedlist', '|', 'emoticons', 'image', 'link'],
				afterCreate : function() {
					this.loadPlugin('autoheight');
				}
			});
		});
	}

	/*查询按钮事件初始化*/
	this.initBtn = function(){
		Form.dateTime("article_date", null, 'YYYYMMDD');
		/*清空*/
		$("#btn_reset").on("click", function(){
			Form.reset("conditionForm");
		});
	};
	
	/* 字段校验*/
	function __fieldValidator(){
		var _field = {};
		_field.article_id = Validator.validate(true, {max:30});

		_field.article_date = Validator.validate(false, {max:8});
		_field.title = Validator.validate(false, {max:200});
		_field.contents = Validator.validate(false, {max:10000});
	
		
		return _field;
	}
	
	/* 提交Form数据*/
	function __formCommit(e) {
    	/*阻止默认的form submit*/
        e.preventDefault();
        
        $this.submit(_oper);
    }
	
	//提交
	this.submit = function(type){
		ParamCheck.commonSubmit({
			service_name 	: _controller_name,
			method_name 	: ("add" === type ? "addArticle" : "updateArticle"),
			parameter_type  : _param_type,
			oper_type 		: type,
			oper_desc 		: ("add" === type ? "新增" : "编辑") + "资讯信息信息",
			switch_name 	: "articleSwitch",
			module_id 		: "articleList",
			check_fields 	: ["article_id"],
			//form_id			: "articleForm",
			params			: getFieldValue(),
			oparams			: {}
		});
	};

	function getFieldValue() {
		var list = [];
		list.push(ParamCheck.initChkObj("article_id","article_id", Form.id("article_id")));
		list.push(ParamCheck.initChkObj("title","标题", Form.id("title")));
		list.push(ParamCheck.initChkObj("article_date","日期", Form.id("article_date")));
		list.push(ParamCheck.initChkObj("contents","内容", _KE.html()));

		return list;
	}
	
	/* form 校验 */
	this.formvalidator = function(){
		var options={
			formId				: "articleForm",
	        fields				: __fieldValidator(),
	        successFormFv		: __formCommit
		};
		
		new FormValidator(options);
	};
	/* 查询资讯信息信息*/
	this.queryByKey = function(){
		tableSupport.get(mgr_path + "/article/queryByKey", {"article_id" : _article_id}, function(article){
			Form.setValue(article);
            _KE.html(article.contents);
		});
	};
	
	function init(){
		initEditor();

		setTimeout(function(){
			$this.initBtn();
			$this.formvalidator();

			if("add" !== _oper){
				$this.queryByKey();
			}

			if("update" === _oper){
				$("#article_id").attr("readonly", true);
			}else if("view" === _oper){
				Form.setDisabled("articleForm");
				_KE.readonly();
			}
		},200);
	}
	
	init();
};

