/**
 * function:
 * StoreAdd.js
 * @author sam.pan
 * @createTime 2017-06-06 17:18:15
 */
var Store = function (options){
	var $this = this;
	var _oper = options.oper || "add";
	var _store_id = options.store_id;
	var _controller_name = "com.huateng.xhcp.web.system.StoreController";
	var _param_type = "com.huateng.xhcp.model.system.Store";
	
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
		/*清空*/
		$("#btn_reset").on("click", function(){
			Form.reset("conditionForm");
		});
	};
	
	/* 字段校验*/
	function __fieldValidator(){
		var _field = {};

		_field.store_id = Validator.validate(true, {max:10});
		_field.store_name = Validator.validate(false, {max:200});
		_field.contents = Validator.validate(false, {max:10000});
		_field.is_use = Validator.validate(false, {max:1});
	
		
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
			method_name 	: ("add" === type ? "addStore" : "updateStore"),
			parameter_type  : _param_type,
			oper_type 		: type,
			oper_desc 		: ("add" === type ? "新增" : "编辑") + "店铺信息",
			switch_name 	: "storeSwitch",
			module_id 		: "storemgr",
			check_fields 	: ["store_id"],
//			form_id			: "storeForm",
			params			: getFieldValue(),
			oparams			: {}
		});
	};
	
	function getFieldValue() {
		var list = [];
		list.push(ParamCheck.initChkObj("store_id","store_id", Form.id("store_id")));
		list.push(ParamCheck.initChkObj("store_name","店铺名称", Form.id("store_name")));
		list.push(ParamCheck.initChkObj("is_use","是否使用", Form.id("is_use")));
		list.push(ParamCheck.initChkObj("contents","店铺详细内容", _KE.html()));

		return list;
	}
	
	/* form 校验 */
	this.formvalidator = function(){
		var options={
			formId				: "storeForm",
	        fields				: __fieldValidator(),
	        successFormFv		: __formCommit
		};
		
		new FormValidator(options);
	};
	/* 查询店铺信息信息*/
	this.queryByKey = function(){
		tableSupport.get(ctx + "/mgr/store/queryByKey", {"store_id" : _store_id}, function(store){
			Form.setValue(store);
			_KE.html(store.contents);
		});
	};
	
	function init(){
		initEditor();
		//Form.rptBank("rpt_bank");
		setTimeout(function(){
			$this.initBtn();
			$this.formvalidator();

			if("add" !== _oper){
				$this.queryByKey();
			}

			if("update" === _oper){
				$("#store_id").attr("readonly", true);
			}else if("view" === _oper){
				Form.setDisabled("storeForm");
				_KE.readonly();
			}
		},200);
		
	}
	
	init();
};

