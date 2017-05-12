/**
 * function
 * RoleAssign.js
 * @author pansen
 * @createTime 2016-02-23 14:54:41
 */
var RoleAssign = function (options){
	var _account_id = options.account_id;
	var _assign_role = null;
	
	/* 生成li元素*/
	function liHtml(list, whichId){
		var length = list.length;
		for (var i = 0; i < length; i++) {
			var role = list[i];
			var li = '<li id="'+ whichId +'_' + role.role_id + '" name="'+ role.role_id +'"><a href="javascript:void(0);"><i class="glyphicon glyphicon-user"></i>  '+ role.role_name +'</a></li>';
			$li = $(li);
			/* 增加点击事件*/
			$li.bind({
				click : function(){
					$(this).toggleClass('active');
					$(this).siblings().each(function(){
						$(this).removeClass('active');
					});
					
					enableOrNot();
				},
				dblclick:function(){/*双击事件*/
					var $ul = $(this).parent("ul");
					$(this).removeClass('active');
					var clone=$(this).clone(true);
					
					switch ($ul.attr("id")){
					case "canBelongRole" : 
						$("#hasBelongRole").append(clone);
						break;
					case "hasBelongRole" : 
						$("#canBelongRole").append(clone);
						break;
					case "canAssignRole" : 
						$("#hasAssignRole").append(clone);
						break;
					case "hasAssignRole" : 
						$("#canAssignRole").append(clone);
						break;
					}
					$(this).remove();
					
					enableOrNot();
				}
			});
			$("#"+whichId).append($li);
		}
	}
	
	/* 删除li元素*/
	function reduceLi(list, canWhichId, hasWhichId){
		var length = list.length;
		for (var i = 0; i < length; i++) {
			var role = list[i];
			$("#"+canWhichId+"_"+role.role_id).remove();
			
			/* 删除登录用户不能支配的角色，即使是该用户(非登录用户)拥有该角色*/
			var flag = false;
			for (var j = 0; j < _assign_role.length; j++) {
				var _role = _assign_role[j];
				if(role.role_id == _role.role_id){
					flag = true;
					break;
				}
			}
			
			/* 只有当没有在支配角色中找到时才删除*/
			if(!flag){
				$("#"+hasWhichId+"_"+role.role_id).remove();
			}
		}
	}
	
	/* 根据用户查询支配的角色*/
	function queryAssignRoleByAccountId(){
		tableSupport.get(cfrm_path + "/user/queryAssignRoleByAccountId",{"account_id" : _account_id},function(list){
			
			liHtml(list, "hasAssignRole");
			reduceLi(list, "canAssignRole", "hasAssignRole");
			enableOrNot();
		});
	}
	/* 根据用户查询拥有的角色*/
	function queryBelongRoleByAccountId(){
		tableSupport.get(cfrm_path + "/user/queryBelongRoleByAccountId",{"account_id" : _account_id},function(list){
			
			liHtml(list, "hasBelongRole");
			reduceLi(list, "canBelongRole", "hasBelongRole");
			enableOrNot();
		});
	}
	
	/* 查询登录用户所能支配的角色*/
	function queryRoleByLoginAccount(){
		tableSupport.get(cfrm_path + "/user/queryRoleByLoginAccount",{},function(list){
			_assign_role = list;
			
			liHtml(list, "canBelongRole");
			liHtml(list, "canAssignRole");
			
			queryAssignRoleByAccountId();
			queryBelongRoleByAccountId();
		});
	}
	
	/* 控制八个按钮是否disabled*/
	function enableOrNot(){
		var size0 = $("#canBelongRole > li").size();
		var size00 = $("#canBelongRole > li.active").size();
		$("#belongRoleAuth").attr("disabled", size00 > 0 ? false : true);
		$("#allBelongRoleAuth").attr("disabled", size0 > 0 ? false : true);
		
		var size1 = $("#hasBelongRole > li").size();
		var size11 = $("#hasBelongRole > li.active").size();
		$("#belongRoleDeny").attr("disabled", size11 > 0 ? false : true);
		$("#allBelongRoleDeny").attr("disabled", size1 > 0 ? false : true);
		
		var size2 = $("#canAssignRole > li").size();
		var size22 = $("#canAssignRole > li.active").size();
		$("#assignRoleAuth").attr("disabled", size22 > 0 ? false : true);
		$("#allAssignRoleAuth").attr("disabled", size2 > 0 ? false : true);
		
		var size3 = $("#hasAssignRole > li").size();
		var size33 = $("#hasAssignRole > li.active").size();
		$("#assignRoleDeny").attr("disabled", size33 > 0 ? false : true);
		$("#allAssignRoleDeny").attr("disabled", size3 > 0 ? false : true);
		
	}
	
	/* 删除li元素*/
	function removeLi(leftId, rightId){
		var $li = $("#" + leftId + " > li.active");
		$li.removeClass('active');
		$("#" + rightId).append($li.clone(true));
		$li.remove();
		enableOrNot();
	}
	/* 删除所有li元素*/
	function removeAllLi(leftId, rightId){
		var $li = $("#" + leftId + " > li");
		$li.removeClass('active');
		$("#" + rightId).append($li.clone(true));
		$li.remove();
		enableOrNot();
	}
	
	/* 增加按钮事件*/
	function initBtnEvent(){
		/* 拥有角色的四个按钮事件*/
		$("#belongRoleAuth").click(function(){
			removeLi("canBelongRole", "hasBelongRole");
		});
		$("#belongRoleDeny").click(function(){
			removeLi("hasBelongRole", "canBelongRole");
		});
		$("#allBelongRoleAuth").click(function(){
			removeAllLi("canBelongRole", "hasBelongRole")
		});
		$("#allBelongRoleDeny").click(function(){
			removeAllLi("hasBelongRole", "canBelongRole");
		});
		/* 支配角色的四个按钮事件*/
		$("#assignRoleAuth").click(function(){
			removeLi("canAssignRole", "hasAssignRole");
		});
		$("#assignRoleDeny").click(function(){
			removeLi("hasAssignRole", "canAssignRole");
		});
		$("#allAssignRoleAuth").click(function(){
			removeAllLi("canAssignRole", "hasAssignRole");
		});
		$("#allAssignRoleDeny").click(function(){
			removeAllLi("hasAssignRole", "canAssignRole");
		});
		
		/* 保存*/
		$("#saveBelongRole").click(function(){
			var data = {"account_id": _account_id};
			
			$("#hasBelongRole li").each(function(index){
				data["roleIdList[" + index + "]"] = $(this).attr("name");
			});
			
			tableSupport.post(cfrm_path + "/user/saveBelongRole", data, Noty.response);
		});
		$("#saveAssignRole").click(function(){
			var data = {"account_id": _account_id};
			
			$("#hasAssignRole li").each(function(index){
				data["roleIdList[" + index + "]"] = $(this).attr("name");
			});
			
			tableSupport.post(cfrm_path + "/user/saveAssignRole", data, Noty.response);
		});
	}
	
	initBtnEvent();
	queryRoleByLoginAccount();
};