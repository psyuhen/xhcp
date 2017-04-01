/**
 * function:
 * NavInfoAdd.js
 * @author sam.pan
 * @createTime 2017-03-22 23:56:36
 */
var NavInfo = function (options){
	var $this = this;
	var _oper = options.oper || "add";
	var _nav_id = options.nav_id;
	var _controller_name = "com.huateng.xhcp.web.nav.NavInfoController";
	var _param_type = "com.huateng.xhcp.model.nav.NavInfo";
	
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
		_field.nav_id = Validator.validate(false, {max:10});
		_field.pnav_id = Validator.validate(false, {max:10});
		_field.name = Validator.validate(false, {max:30});
		_field.url = Validator.validate(false, {max:500});
		_field.is_out_link = Validator.validate(false, {max:1});
		_field.is_default = Validator.validate(false, {max:1});
		_field.order_id = Validator.validate(false, {max:10});
	
		
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
			method_name 	: ("add" === type ? "addNavInfo" : "updateNavInfo"),
			parameter_type  : _param_type,
			oper_type 		: type,
			oper_desc 		: ("add" === type ? "新增" : "编辑") + "导航信息信息",
			switch_name 	: "navInfoSwitch",
			module_id 		: "navmgr",
			check_fields 	: ["nav_id"],
			form_id			: "navInfoForm",
			oparams			: {}
		});
	};
	
	/* form 校验 */
	this.formvalidator = function(){
		var options={
			formId				: "navInfoForm",
	        fields				: __fieldValidator(),
	        successFormFv		: __formCommit
		};
		
		new FormValidator(options);
	};
	/* 查询导航信息信息*/
	this.queryByKey = function(){
		tableSupport.get(mgr_path + "/nav/queryByKey", {"nav_id" : _nav_id}, function(navInfo){
			Form.setValue(navInfo);
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
			$("#nav_id").attr("readonly", true);
		}else if("view" === _oper){
			Form.setDisabled("navInfoForm");
		}
	}
	
	init();
};

