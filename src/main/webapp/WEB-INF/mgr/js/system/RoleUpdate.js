/**
 * function:
 * RoleUpdate.js
 * @author chengz
 * @createTime 2016-01-07
 */
var Role = function (options){
	var $this = this;
	var _role_id = options.role_id || "";/*角色ID*/
	var _page = options.page || "add";/*操作类型*/
	
	/*加载数据*/
	this.loadRole = function(){
		if("" != _role_id){
			tableSupport.post(ctx + "/mgr/role/queryRole", {"role_id" : _role_id}, function(list){
				if(list.length > 0){
					Form.setValue(list[0]);
				}
			});
		}
	};
	/* 新增/编辑 */
	this.update = function(){
		var options = {
				switch_name : "roleSwitch",
				service_name : "com.huateng.xhcp.web.system.RoleController",
				parameter_type : "com.huateng.xhcp.model.system.Role",
				method_name : _page+"Role",
				oper_type : {"add" : "1","update" : "2"}[_page],
				oper_desc : {"add" : "新增","update" : "编辑"}[_page]+"角色",
				form_id : "roleForm",
            	module_id 		: "RoleMgr",
				check_fields : ["role_id"]
			};
		ParamCheck.commonSubmit(options);
	};

	/* form 校验 */
	this.formvalidator = function(){
		new FormValidator({
			formId : "roleForm",
			fields : {
				role_name: Validator.validate(true , {max:96}),
				role_desc: Validator.validate(false , {max:192})
			},
	        successFormFv : function(e){
	        	/*阻止默认的form submit*/
	        	e.preventDefault();
	        	
	        	$this.update();
	            /*更新按钮失效，避免重复点击*/
	            $('#roleForm').data('formValidation').disableSubmitButtons(true);
	        }
		});
	};
	
	/*初始化*/
	this.init = function(){
		if("update" == _page){
			$this.loadRole();
		}
		$this.formvalidator();
	};
	/*默默初始化*/
	$this.init();
};