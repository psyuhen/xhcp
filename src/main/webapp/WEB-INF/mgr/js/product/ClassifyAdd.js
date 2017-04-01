/**
 * function:
 * ClassifyAdd.js
 * @author sam.pan
 * @createTime 2017-03-13 23:33:35
 */
var Classify = function (options){
	var $this = this;
	var _oper = options.oper || "add";
	var _classify_id = options.classify_id;
	var _controller_name = "com.huateng.xhcp.web.product.ClassifyController";
	var _param_type = "com.huateng.xhcp.model.product.Classify";
	
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
		_field.classify_id = Validator.validate(true, {max:10});
		
		_field.name = Validator.validate(false, {max:50});
		_field.desc = Validator.validate(false, {max:200});
		_field.pcls_id = Validator.validate(false,{max:10});
	
		
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
			method_name 	: ("add" === type ? "addClassify" : "updateClassify"),
			parameter_type  : _param_type,
			oper_type 		: type,
			oper_desc 		: ("add" === type ? "新增" : "编辑") + "产品分类信息信息",
			switch_name 	: "classifySwitch",
			module_id 		: "classifyList",
			check_fields 	: ["classify_id"],
			form_id			: "classifyForm",
			oparams			: {}
		});
	};
	
	/* form 校验 */
	this.formvalidator = function(){
		var options={
			formId				: "classifyForm",
	        fields				: __fieldValidator(),
	        successFormFv		: __formCommit
		};
		
		new FormValidator(options);
	};
	/* 查询产品分类信息信息*/
	this.queryByKey = function(){
		tableSupport.get(mgr_path + "/classify/queryByKey", {"classify_id" : _classify_id}, function(classify){
			Form.setValue(classify);
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
			$("#classify_id").attr("readonly", true);
		}else if("view" === _oper){
			Form.setDisabled("classifyForm");
		}
	}
	
	init();
};

