/**
 * function:
 * GuestBookAdd.js
 * @author sam.pan
 * @createTime 2017-04-20 17:53:20
 */
var GuestBook = function (options){
	var $this = this;
	var _oper = options.oper || "add";
	var _msg_id = options.msg_id;
	var _controller_name = "com.huateng.xhcp.web.system.GuestBookController";
	var _param_type = "com.huateng.xhcp.model.system.GuestBook";
	
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

		_field.msg_id = Validator.validate(false, {max:10});
		_field.name = Validator.validate(false, {max:60});
		_field.phone = Validator.validate(false, {max:60});
		_field.email = Validator.validate(false, {max:96});
		_field.address = Validator.validate(false, {max:200});
		_field.msg_info = Validator.validate(false, {max:500});
	
		
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
			method_name 	: ("add" === type ? "addGuestBook" : "updateGuestBook"),
			parameter_type  : _param_type,
			oper_type 		: type,
			oper_desc 		: ("add" === type ? "新增" : "编辑") + "访客留言簿信息信息",
			switch_name 	: "guestBookSwitch",
			module_id 		: "msgmgr",
			check_fields 	: ["msg_id"],
			form_id			: "guestBookForm",
			oparams			: {}
		});
	};
	
	/* form 校验 */
	this.formvalidator = function(){
		var options={
			formId				: "guestBookForm",
	        fields				: __fieldValidator(),
	        successFormFv		: __formCommit
		};
		
		new FormValidator(options);
	};
	/* 查询访客留言簿信息信息*/
	this.queryByKey = function(){
		tableSupport.get(ctx + "/mgr/system/guest/queryByKey", {"msg_id" : _msg_id}, function(guestBook){
			Form.setValue(guestBook);
		});
	};
	
	function init(){
		//Form.rptBank("rpt_bank");
		$this.initBtn();
		$this.formvalidator();
		
		if("add" !== _oper){
			$this.queryByKey();
		}else{
            $("#msg_id").attr("readonly", true);
		}

		if("update" === _oper){
			$("#msg_id").attr("readonly", true);
		}else if("view" === _oper){
			Form.setDisabled("guestBookForm");
		}
	}
	
	init();
};

