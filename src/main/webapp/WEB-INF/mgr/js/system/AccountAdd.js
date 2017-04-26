/**
 * function:
 * AccountAdd.js
 * @author pansen
 * @createTime 2015-12-23 16:17:25
 */
var Account = function (options){
	var $this = this;
	var _oper = options.oper || "add";
	var _account_id = options.account_id || "";

	/*同步判断account_id是否在数据库中存在了*/
	this.isAccountExists = function(account_id, callback){
		tableSupport.synchGet(mgr_path + "/user/isAccountExists", {"account_id" : account_id}, callback);
	}
	
	/* 新增账号 */
	this.addAccount = function(callback){
		tableSupport.insert(mgr_path + "/user/addAccount", $("#accountForm").serialize(), callback);
	}
	
	/* 更新账号信息 */
	this.updateAccount = function(callback){
		tableSupport.update(mgr_path + "/user/updateAccount", $("#accountForm").serialize(), callback);
	}
	
	/* 根据Id查询Account信息 */
	this.queryAccountById = function(callback){
		tableSupport.get(mgr_path + "/user/id", {"account_id" : _account_id}, callback);
	}
	
	/* 显示密码强度*/
	function __pwdProgress(e, data) {
    	/*只有当password通过callback验证后*/
        if (data.field === 'account_password' && data.validator === 'callback') {
        	/*获取分值*/
            var score = data.result.score,
                $bar  = $('#passwordMeter').find('.progress-bar');

            switch (true) {
                case (score === null):
                    $bar.html('').css('width', '0%').removeClass().addClass('progress-bar');
                    break;

                case (score <= 0):
                    $bar.html('非常弱').css('width', '25%').removeClass().addClass('progress-bar progress-bar-danger');
                    break;

                case (score > 0 && score <= 2):
                    $bar.html('弱').css('width', '50%').removeClass().addClass('progress-bar progress-bar-warning');
                    break;

                case (score > 2 && score <= 4):
                    $bar.html('中等').css('width', '75%').removeClass().addClass('progress-bar progress-bar-info');
                    break;

                case (score > 4):
                    $bar.html('强').css('width', '100%').removeClass().addClass('progress-bar progress-bar-success');
                    break;

                default:
                    break;
            }
        }
    }
	
	/* 提交Form数据*/
	function __formCommit(e) {
    	/*阻止默认的form submit*/
        e.preventDefault();
        
        if("add" == _oper){
        	/*新增账号*/
            $this.addAccount(Noty.response);
        }else if("update" == _oper){
        	/*更新账号信息*/
            $this.updateAccount(Noty.response);
        }
    }
	/* account_id大于3位才开始检查，从数据库检查account_id是否存在*/
	function validateAccountExist(){
		return {
        	message: "此账号ID已存在",
        	callback: function(value, validator, $field){
        		/*少于3位不作检验*/
        		if(value.length < 3){
        			return {
						valid : true
					};
        		}
        		
        		/*account_id校验通过后，作数据库检验*/
        		var checkpass = true;
        		if("add" == _oper){
        			$this.isAccountExists(value, function(exists){
    	    			if(exists){
    	    				checkpass = false;
    	    			}
    	    		});
        		}
        		
        		return {
					message: "此账号ID已存在",
					valid:checkpass
				};
        	}
		};
	}

	/* 字段校验*/
	function __fieldValidator(){
		var _field = {};
		_field.account_id = Validator.validate(true, {min:3,max:96},{r:/^[a-zA-Z0-9_]+$/},validateAccountExist());

		_field.account_name = Validator.validate(false, {max:64});
		_field.account_password = Validator.validate(true, {min: 6,max: 192},null,Validator.pwdScore());
		_field.account_status = Validator.validate(true);
		_field.account_type = Validator.validate(true);
		_field.account_inv_date = Validator.validate(false, {max:8});


		return _field;
	}

	/* form 校验 */
	this.formvalidator = function(){
		var options={
			formId				:"accountForm",
	        fields				: __fieldValidator(),
	        successValidatorFv	: __pwdProgress,
	        successFormFv		: __formCommit
		};
		
		new FormValidator(options);
	};
	/*初始化*/
	this.init = function(){

		$("#div_account_inv_date").datetimepicker({
			locale: lang,
			format: 'YYYYMMDD'
		});

		$this.formvalidator();
		if("update" === _oper){
			$("#account_id").attr("readonly", true);
		}
		if("view" === _oper){
			Form.setDisabled("accountForm");
		}
		if("add" !== _oper){
			/*查询Account信息*/
			$this.queryAccountById(function(account){
				Form.setValue(account);

				//失效日期作特殊处理
				var invDt = account.account_inv_date;
				if(!StringUtil.isBlank(invDt)){
					$("#account_inv_date").val(invDt.split(" ")[0]);
				}
			});
		}
	};
	
	//默认初始化了吧
	$this.init();
};

