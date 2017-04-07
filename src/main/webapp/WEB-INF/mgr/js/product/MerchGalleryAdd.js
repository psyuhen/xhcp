/**
 * function:
 * MerchGalleryAdd.js
 * @author sam.pan
 * @createTime 2017-04-03 20:11:21
 */
var MerchGallery = function (options){
	var $this = this;
	var _oper = options.oper || "add";
	var _gallery_id = options.gallery_id;
	var _controller_name = "com.huateng.xhcp.web.product.MerchGalleryController";
	var _param_type = "com.huateng.xhcp.model.product.MerchGallery";
	
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

		_field.gallery_id = Validator.validate(false, {max:10});
		_field.merch_id = Validator.validate(false, {max:10});
		_field.name = Validator.validate(false, {max:100});
		_field.file_name = Validator.validate(false, {max:100});
		_field.path = Validator.validate(false, {max:500});
		_field.file_type = Validator.validate(false, {max:1});
	
		
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
			method_name 	: ("add" === type ? "addMerchGallery" : "updateMerchGallery"),
			parameter_type  : _param_type,
			oper_type 		: type,
			oper_desc 		: ("add" === type ? "新增" : "编辑") + "产品信息",
			switch_name 	: "merchGallerySwitch",
			module_id 		: "gallerymgr",
			check_fields 	: ["gallery_id"],
			form_id			: "merchGalleryForm",
			oparams			: {}
		});
	};
	
	/* form 校验 */
	this.formvalidator = function(){
		var options={
			formId				: "merchGalleryForm",
	        fields				: __fieldValidator(),
	        successFormFv		: __formCommit
		};
		
		new FormValidator(options);
	};
	/* 查询产品信息*/
	this.queryByKey = function(){
		tableSupport.get(mgr_path + "/product/gallery/queryByKey", {"gallery_id" : _gallery_id}, function(merchGallery){
			Form.setValue(merchGallery);
			$("#photo").attr("src", ctx + "/" + merchGallery.path + merchGallery.name);
		});
	};
	
	function init(){
		//Form.rptBank("rpt_bank");
		$this.initBtn();
		$this.formvalidator();
		
		if("add" !== _oper){
			$this.queryByKey();
		}
		
		if("update" === _oper){
			$("#gallery_id").attr("readonly", true);
		}else if("view" === _oper){
			Form.setDisabled("merchGalleryForm");
		}
	}
	
	init();
};

