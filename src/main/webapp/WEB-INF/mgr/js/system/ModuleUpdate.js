/**
 * function:
 * ModuleUpdate.js
 * @author sam.pan
 * @createTime 2016-01-25 11:41:20
 */
var Module = function (options){
	var $this = this;
	var _oper = options.oper || "add";
	var _module_id = options.mId || "";
	
	/*同步判断module_id是否在数据库中存在了*/
	this.isModuleExists = function(module_id, callback){
		tableSupport.synchGet(mgr_path + "/module/isModuleExists", {"module_id" : module_id}, callback);
	}
	
	/* 新增菜单 */
	this.addModule = function(callback){
		tableSupport.insert(mgr_path + "/module/insertModule", $("#moduleForm").serialize(), callback);
	}
	
	/* 更新菜单信息 */
	this.updateModule = function(callback){
		tableSupport.update(mgr_path + "/module/updateModuleById", $("#moduleForm").serialize(), callback);
	}
	
	/* 根据Id查询Module信息 */
	this.queryModuleById = function(callback){
		tableSupport.post(mgr_path + "/module/find", {"module_id" : _module_id}, callback);
	}
	
	/* 提交Form数据*/
	function __formCommit(e) {
    	/*阻止默认的form submit*/
        e.preventDefault();
        
        if("add" == _oper){
        	/*新增菜单*/
            $this.addModule(Noty.response);
        }else if("update" == _oper){
        	/*更新菜单信息*/
            $this.updateModule(Noty.response);
        }
    }
	
	/* 字段校验*/
	function __fieldValidator(){
		var _validators = Validator.notEmpty();
		_validators.callback = Validator.lte(64);
		
		var _field = {
	        	module_id: {
	                validators: {
	                    notEmpty: {},
	                    stringLength: {max: 128},
	                    regexp: {
	                        regexp: /^[a-zA-Z0-9_]+$/
	                    },
	                    callback:{
	                    	message: "此菜单ID已存在",
	                    	callback: function(value, validator, $field){
	                    		/*少于2位不作检验*/
	                    		if(value.length < 2){
	                    			return true;
	                    		}
	                    		
	                    		/*account_id校验通过后，作数据库检验*/
	                    		var checkpass = true;
	                    		if("add" == _oper){
	                    			$this.isModuleExists(value, function(exists){
	                	    			if(exists){
	                	    				checkpass = false;
	                	    			}
	                	    		});
	                    		}
	                    		
	                    		return checkpass;
	                    	}
	                    }
	                }
	            },
	            module_name: {
	                validators: _validators
	            },
	            pmodule_id: {
	            	validators: Validator.notEmpty()
	            },
	            module_entry: {
	            	validators: Validator.notEmpty()
	            },
	            module_type: {
	            	validators: Validator.notEmpty()
	            }
	        };
		return _field;
	}
	
	/* form 校验 */
	this.formvalidator = function(){
		var options={
			formId				: "moduleForm",
	        fields				: __fieldValidator(),
	        successFormFv		: __formCommit
		};
		
		new FormValidator(options);
	};
	
	/*初始化*/
	this.init = function(){
		if("update" == _oper){
			$("#module_id").attr("readonly", true);
		}
		if("view" == _oper){
			Form.setDisabled("moduleForm");
		}
		
		if(_module_id != ""){
			/*根据ID查询菜单信息*/
			$this.queryModuleById(function(list){
				if(list.length == 0){
					return;
				}
				/*设置值*/
				var module = list[0];
				Form.setValue(module);
			});
		}
		
		$this.formvalidator();
	};
	
	//默认初始化了吧
	$this.init();
};

