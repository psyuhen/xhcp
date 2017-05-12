/**
 * function:
 * RoleAccount.js
 * @author chengz
 * @createTime 2016-01-29
 */
var RoleAccount = function (options){
	var $this = this;
	var $form = $("#roleForm");
	var _role_id = options.role_id || "";/*角色ID*/
	
	/*加载当前角色*/
	this.queryRole = function(role_id){
		if("" != role_id){
			tableSupport.post(ctx + "/mgr/role/queryRole", {"role_id":role_id}, function(list){
				if(list.length > 0){
					var obj = list[0];
					var roleStr = "[" + obj.role_id + " | " + obj.role_name + "]的用户";
					$form.find("#ownTitle").append("拥有角色"+roleStr);
					$form.find("#governTitle").append("支配角色"+roleStr);
				}
			});
		}
	};
	/*加载所有用户*/
	function queryAccount(){
		var accounts = {};/*所有后台用户*/
		tableSupport.synchPost(ctx + "/mgr/user/queryAccount", {"account_type":"0","b_order_name" : "account_id"}, function(list){
			for ( var i = 0; i < list.length; i++) {
				var map = list[i];
				var account_id = map.account_id;
				var account_name = map.account_name;
				/*生成 {用户ID : '用户ID | 用户名'}对象*/
				accounts[account_id] = account_id  + " | " + account_name;
			}
		});
		return accounts;
	};
	/*加载拥有当前角色的用户*/
	function queryAccountRole(role_id){
		var accountRoles = {};
		if("" != role_id){
			tableSupport.synchPost(ctx + "/mgr/accountRole/queryAccountRole", {"role_id":role_id}, function(list){
				for ( var i = 0; i < list.length; i++) {
					var map = list[i];
					var account_id = map.account_id;
					/*生成 {用户ID : 用户ID}对象*/
					accountRoles[account_id] = account_id;
				}
			});
		}
		return accountRoles;
	};
	/*加载支配当前角色的用户*/
	function queryAccountRoleConf(role_id){
		var accountRoleConfs = {};
		if("" != role_id){
			tableSupport.synchPost(ctx + "/mgr/accountRole/queryAccountRoleConf", {"role_id":role_id}, function(list){
				for ( var i = 0; i < list.length; i++) {
					var map = list[i];
					var account_id = map.account_id;
					/*生成 {用户ID : 用户ID}对象*/
					accountRoleConfs[account_id] = account_id;
				}
			});
		}
		return accountRoleConfs;
	};

	/*初始化Ul*/
	this.initUl = function(){
		/*生成标题*/
		$this.queryRole(_role_id);
		/*加载可选择的用户*/
		var accounts = queryAccount();
		var allAccount = "";
		for(var id in accounts){
			allAccount += '<li title="双击选择" id="'+ id + '"><a href="javascript:void(0)"><i class="glyphicon glyphicon-user"></i>  '+ accounts[id] +'</a></li>';
		}
		$form.find("ul[id$=Account]").append(allAccount);
		$form.find("ul[id^=has]").find("li").hide();
		/*加载拥有该角色的用户*/
		var accountRoles = queryAccountRole(_role_id);
		for(var id in accountRoles){
			$form.find("#notOwnAccount > li[id="+id+"]").dblclick();
			$form.find("#hasOwnAccount > li[id="+id+"]").attr("isExist","1");
		}
		/*加载支配该角色的用户*/
		var accountRoleConfs = queryAccountRoleConf(_role_id);
		for(var id in accountRoleConfs){
			$form.find("#notGovernAccount > li[id="+id+"]").dblclick();
			$form.find("#hasGovernAccount > li[id="+id+"]").attr("isExist","1");
		}
	};
	
	/*初始化事件*/
	this.initEvent = function(){
		/*单击*/
		$form.find("ul[id$=Account]").on("click","li",function(){
			$(this).toggleClass('active');
			$(this).siblings().removeClass('active');
		});
		/*双击 选择/取消选择*/
		$form.find("ul[id$=Account]").on("dblclick","li",function(){
			var $li = $(this);
			$li.fadeOut();
			
			var $ul = $li.parent();
			var _li_id = $li.attr("id");
			var ul_id = $ul.attr("id");
			var _ul_id = ((/has/g.test(ul_id)) ? "not" : "has")+ul_id.substring(3);
			
			var $_li = $form.find("ul[id="+_ul_id+"] > li[id="+_li_id+"]");
			$_li.fadeIn();
		});
		/*全选/取消全选*/
		$form.find("a[id^=allFrom]").on("click",function(){
			var ul_id = $(this).attr("id").replace("allFrom","");
			$form.find("ul[id="+ul_id+"] > li:visible").dblclick();
		});
		/*提交*/
		$form.on("click","button[id^=btn_update_]",function(){
			/*获取数据*/
			var addList = [];
			var delList = [];
			
			var ul = ($(this).attr("id") == "btn_update_own") ? "hasOwnAccount" : "hasGovernAccount";
			$form.find("#"+ul+" > li").each(function(){
				var account_id = $(this).attr("id");
				if($(this).is(":visible") && "1" != $(this).attr("isExist")){
					addList.push({"role_id" : _role_id , "account_id" : account_id});
				}
				if(!$(this).is(":visible") && "1" == $(this).attr("isExist")){
					delList.push({"role_id" : _role_id , "account_id" : account_id});
				}
			});
			/*提交更新*/
			tableSupport.postJson(ctx + "/mgr/accountRole/updateAccountRole"+
					(("hasGovernAccount" == ul) ? "Conf" : ""),JSON.stringify([addList,delList]), Noty.response);
			$(this).attr("disabled",true);
		});
	};
	/*初始化*/
	this.init = function(){
		$this.initEvent();
		$this.initUl();
	};
	/*默默初始化*/
	$this.init();
};
