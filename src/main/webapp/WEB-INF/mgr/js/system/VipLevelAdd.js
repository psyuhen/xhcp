/**
 * function:
 * VipLevelAdd.js
 * @author sam.pan
 * @createTime 2017-04-20 10:18:58
 */
var VipLevel = function (options){
	var $this = this;
	var _oper = options.oper || "add";
	var _vip_id = options.vip_id;
	var _controller_name = "com.huateng.xhcp.web.system.VipLevelController";
	var _param_type = "com.huateng.xhcp.model.system.VipLevel";
	
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

		_field.vip_id = Validator.validate(false, {max:10});
		_field.name = Validator.validate(false, {max:10});
		_field.score = Validator.validate(false, {max:10}, {r:/^\d{1,10}$/,m:"请输入整数"});
		_field.discount = Validator.validate(false, {max:6}, null, Validator.d(6,2));
	
		
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
			method_name 	: ("add" === type ? "addVipLevel" : "updateVipLevel"),
			parameter_type  : _param_type,
			oper_type 		: type,
			oper_desc 		: ("add" === type ? "新增" : "编辑") + "会员等级信息",
			switch_name 	: "vipLevelSwitch",
			module_id 		: "vipmgr",
			check_fields 	: ["vip_id"],
			form_id			: "vipLevelForm",
			oparams			: {}
		});
	};
	
	/* form 校验 */
	this.formvalidator = function(){
		var options={
			formId				: "vipLevelForm",
	        fields				: __fieldValidator(),
	        successFormFv		: __formCommit
		};
		
		new FormValidator(options);
	};
	/* 查询会员等级信息*/
	this.queryByKey = function(){
		tableSupport.get(ctx + "/mgr/system/vip/queryByKey", {"vip_id" : _vip_id}, function(vipLevel){
			Form.setValue(vipLevel);
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
			$("#vip_id").attr("readonly", true);
		}else if("view" === _oper){
			Form.setDisabled("vipLevelForm");
		}
	}
	
	init();
};

