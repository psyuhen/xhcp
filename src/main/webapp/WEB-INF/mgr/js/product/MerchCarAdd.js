/**
 * function:
 * MerchCarAdd.js
 * @author sam.pan
 * @createTime 2017-04-07 16:13:37
 */
var MerchCar = function (options){
	var $this = this;
	var _oper = options.oper || "add";
	var _car_id = options.car_id;
	var _controller_name = "com.huateng.xhcp.web.product.MerchCarController";
	var _param_type = "com.huateng.xhcp.model.product.MerchCar";
	
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

		_field.car_id = Validator.validate(false, {max:10});
		_field.merch_id = Validator.validate(false, {max:10});
		_field.buy_num = Validator.validate(false, {max:10});
		_field.account_id = Validator.validate(false, {max:96});
	
		
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
			method_name 	: ("add" === type ? "addMerchCar" : "updateMerchCar"),
			parameter_type  : _param_type,
			oper_type 		: type,
			oper_desc 		: ("add" === type ? "新增" : "编辑") + "购物车信息",
			switch_name 	: "merchCarSwitch",
			module_id 		: "carmgr",
			check_fields 	: ["car_id"],
			form_id			: "merchCarForm",
			oparams			: {}
		});
	};
	
	/* form 校验 */
	this.formvalidator = function(){
		var options={
			formId				: "merchCarForm",
	        fields				: __fieldValidator(),
	        successFormFv		: __formCommit
		};
		
		new FormValidator(options);
	};
	/* 查询购物车信息*/
	this.queryByKey = function(){
		tableSupport.get(mgr_path + "/mgr/product/car/queryByKey", {"car_id" : _car_id}, function(merchCar){
			Form.setValue(merchCar);
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
			$("#car_id").attr("readonly", true);
		}else if("view" === _oper){
			Form.setDisabled("merchCarForm");
		}
	}
	
	init();
};

