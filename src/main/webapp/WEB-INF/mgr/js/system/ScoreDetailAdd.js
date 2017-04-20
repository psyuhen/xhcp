/**
 * function:
 * ScoreDetailAdd.js
 * @author sam.pan
 * @createTime 2017-04-20 15:44:15
 */
var ScoreDetail = function (options){
	var $this = this;
	var _oper = options.oper || "add";
	var _score_id = options.score_id;
	var _controller_name = "com.huateng.xhcp.web.system.ScoreDetailController";
	var _param_type = "com.huateng.xhcp.model.system.ScoreDetail";
	
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

		_field.score_id = Validator.validate(false, {max:10});
		_field.account_id = Validator.validate(false, {max:96});
		_field.score = Validator.validate(false, {max:10});
		_field.reason = Validator.validate(false, {max:96});
	
		
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
			method_name 	: ("add" === type ? "addScoreDetail" : "updateScoreDetail"),
			parameter_type  : _param_type,
			oper_type 		: type,
			oper_desc 		: ("add" === type ? "新增" : "编辑") + "会员积分明细信息",
			switch_name 	: "scoreDetailSwitch",
			module_id 		: "scoremgr",
			check_fields 	: ["score_id"],
			form_id			: "scoreDetailForm",
			oparams			: {}
		});
	};
	
	/* form 校验 */
	this.formvalidator = function(){
		var options={
			formId				: "scoreDetailForm",
	        fields				: __fieldValidator(),
	        successFormFv		: __formCommit
		};
		
		new FormValidator(options);
	};
	/* 查询会员积分明细信息*/
	this.queryByKey = function(){
		tableSupport.get(ctx + "/mgr/system/score/queryByKey", {"score_id" : _score_id}, function(scoreDetail){
			Form.setValue(scoreDetail);
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
			$("#score_id").attr("readonly", true);
		}else if("view" === _oper){
			Form.setDisabled("scoreDetailForm");
		}
	}
	
	init();
};

