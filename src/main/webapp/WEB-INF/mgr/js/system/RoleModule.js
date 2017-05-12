/**
 * function:
 * RoleModule.js
 * @author chengz
 * @createTime 2016-02-01
 */
var RoleModule = function (options){
	var $this = this;
	var _role_id = options.role_id || "";/*角色ID*/
	var _user = options.user || "";/*当前用户*/

	var menuTree = {};/*菜单树*/
	var roleModule = {};/*角色拥有模块*/
	
	/*获取当前角色拥有模块*/
	this.getRoleModule = function(){
		if("" != _role_id){
			tableSupport.synchPost(ctx + "/mgr/roleModule/queryRoleModule", {"role_id" : _role_id}, function(list){
				roleModule = list || {};
			});
		}
	};
	
	/*赋值当前角色拥有模块*/
	function initRoleModule(){
		for ( var i = 0; i < roleModule.length; i++) {
			var $i = $("[module_id="+roleModule[i].module_id+"]>a>[class=div_check]").find("i");/*对应选项*/
			$i.attr("class","glyphicon glyphicon-ok");/*选择对应选项*/
			$i.attr("isExist","1");/*添加标识 用于判断是否存在*/
		}
		/*勾选各级目录*/
		checkMenu();
	}
	
	/*勾选各级目录*/
	function checkMenu(){
		$("#nav_content").find("li:not([id=file_item])").each(function(){
			var isAllCheck = true;
			var isAllNotCheck = true;
			var $i_menu_check = $(this).find("[class=div_check]:eq(0)").find("i");
			var $div_file = $(this).find("[class=div_file]");
			$.each($div_file,function(){
				var i_class = $(this).next().find("i").attr("class");
				if(null == i_class.match("ok")){
					isAllCheck = false;
				}
				if(null == i_class.match("remove")){
					isAllNotCheck = false;
				}
			});
			/*若子项全选，则父项显示全选；若子项部分选，则父项显示部分选；若子项不选，则父项显示不选；*/
			if(isAllCheck){
				$i_menu_check.attr("class","glyphicon glyphicon-ok");
			}
			if(isAllNotCheck){
				$i_menu_check.attr("class","glyphicon glyphicon-remove");
			}
			if(!isAllCheck && !isAllNotCheck){
				$i_menu_check.attr("class","glyphicon glyphicon-minus");
			}
		});
	}
	
	 /*获取当前用户可支配角色的所有模块*/
	this.getMenuTree = function(){
		tableSupport.synchPost(ctx + "/mgr/roleModule/findModuleByAccount", {account_id : _user}, function(list){
			if(null == list || list.length < 1){
				menuTree = null;
				return;
			}
			/*菜单树添加所有节点，所有节点添加子节点属性*/
			$.each(list,function(){
				this.children = [];
				menuTree[this.module_id] = this;
			});
			/*所有节点附在对应父节点的子节点上*/
			$.each(list,function(){
				if(this.pmodule_id != null){
					menuTree[this.pmodule_id].children.push(this);
				}
			});
			/*取主节点*/
			menuTree = menuTree.Module || {};
		});
	};
	
	/*生成菜单树*/
	function buildTree(node,parent){
		/*生成选项*/
		var $li = $('<li module_id="'+node.module_id+'" module_name="'+node.module_name+'" class="accordion"></li>');
		var $item = $('<a></a>');
		var $file = $('<div class="div_folder"><i class="glyphicon glyphicon-folder-close"></i> '+node.module_name+'</div>');
		var $check = $('<div class="div_check"><span>&nbsp;<i class="glyphicon glyphicon-remove"></i>&nbsp;</span></div>');
		$item.append($file);
		$item.append("&nbsp;&nbsp;&nbsp;&nbsp;");/*图标与选项之间添加空格，避免误点*/
		$item.append($check);
		$li.append($item);
		parent.append($li);
		/*若当前为目录，则新增ul*/
		if(node.children.length > 0){
			var $ul = $('<ul pmodule_id="'+node.module_id+'" class="nav nav-pills nav-stacked"></ul>');
			$.each(node.children,function(){
				buildTree(this,$ul);/*递归*/
			});
			$li.append($ul);
			
			$check.attr("title","全选");
		}else{
			$li.attr("id","file_item");
			$file.attr("class","div_file");
			$file.find("i").attr("class","glyphicon glyphicon-file");
		}
	}
	
	/*生成模块选择*/
	this.initSelectModal = function(){
		$.each(menuTree.children,function(){
			var option = $("<option modal='"+this.module_id+"'>" + this.module_name + "</option>");
			$("#select_modal").append(option);
		});
	};
	
	/*生成菜单选择*/
	function initSelectFile(module_id){
		$("#div_select_file").empty();
		var select_file = 
			'<label class="control-label" for="select_file">菜单选择</label>'+
			'<select id="select_file" class="form-control">'+
				'<option>请选择</option>'+
			'</select>';
		$("#div_select_file").append(select_file);
		module_id = module_id || "Module";
		$("[module_id="+module_id+"]").find("[class=div_file]").each(function(){
			var $li = $(this).parent().parent();
			var option = '<option file="'+$li.attr("module_id")+'">'+$li.attr("module_name")+'</option>';
			$("#select_file").append(option);
		});
		$("#select_file").chosen();
	}
	
	/*初始化事件*/
	this.initEvent = function(){
		/*选择节点*/
		$("#nav_content").on("click","[id=file_item]",function(){
			$("#nav_content").find("li").removeClass("active");
			$(this).addClass("active");
		});
		
		/*折叠菜单*/
		$("#nav_content").on("click","[class=div_folder]",function(){
			/*若打开，则折叠；若折叠，则打开*/
			var $ul = $(this).parent().siblings();
			if($ul.is(":visible")){
				$(this).find("i").attr("class","glyphicon glyphicon-folder-close");
				$ul.slideUp();
			}else{
				$(this).find("i").attr("class","glyphicon glyphicon-folder-open");
				$ul.slideDown();
			}
		});
		
		/*选择菜单*/
		$("#nav_content").on("click","[class=div_check]",function(){
			/*勾选当前项与子项*/
			var $i = $(this).find("i");
			var i_class = (null == $i.attr("class").match("ok")) ? 
					"glyphicon glyphicon-ok" : "glyphicon glyphicon-remove";
			$(this).parent().parent().find("[class=div_check]").find("i").attr("class" , i_class);
			/*勾选各级目录*/
			checkMenu();
		});
		
		/*模块选择*/
		$("#select_modal").on("change",function(){
			/*获取所选模块  若无选择，则默认为所有模块*/
			$("#nav_content").empty();
			var module_id = $.trim($(this).find("option:checked").attr("modal"));
			var _menuTree = {
					"module_id" : menuTree.module_id,
					"module_name" : menuTree.module_name,
					"children" : []
			};
			if("" == module_id){
				_menuTree.children = menuTree.children;
			}else{
				for(var i = 0 ; i < menuTree.children.length ; i++){
					if(module_id == menuTree.children[i].module_id){
						_menuTree.children.push(menuTree.children[i]);
					}
				}
			}
			/*生成菜单树*/
			buildTree(_menuTree,$("#nav_content"));
			/*赋值当前角色可支配的模块*/
			initRoleModule();
			/*初始化菜单选择*/
			initSelectFile(module_id);
		});
		
		/*菜单选择*/
		$("#div_select_file").on("change","[id=select_file]",function(){
			var module_id = $.trim($(this).find("option:checked").attr("file"));
			/*若module_id不为空*/
			if("" != module_id){
				/*打开折叠菜单*/
				var $li = $("[module_id="+module_id+"]");
				var $_ul = $li.parent();
				while(!$_ul.is(":visible")){
					$("[module_id="+$_ul.attr("pmodule_id")+"]>a>[class=div_folder]").click();
					$_ul = $("[module_id="+$_ul.attr("pmodule_id")+"]").parent();
				}
				/*移至所选节点*/
				setTimeout(function(){ 
					var selectObj = $("#nav_scroll");
					selectObj.scrollTop($li.offset().top - selectObj.offset().top + selectObj.scrollTop());
					selectObj.animate({scrollTop: $li.offset().top - selectObj.offset().top + selectObj.scrollTop()}, "slow");
					/*选择节点*/
					$li.click();
				}, 300);
			}
		});
		
		/*提交*/
		$("#btn_update").on("click",function(){
			var addRoleModuleList = [];
			var delRoleModuleList = [];
			/*获取被选择菜单*/
			$("#nav_content").find("[class=div_file]").each(function(){
				var module_id = $(this).parent().parent().attr("module_id");
				var $i = $(this).next().find("i");
				var isExist = (null != $i.attr("isExist"));
				var isCheck = (null != $i.attr("class").match("ok"));
				if(!isExist && isCheck){
					addRoleModuleList.push({"role_id" : _role_id , "module_id" : module_id});
				}
				if(isExist && !isCheck){
					delRoleModuleList.push({"role_id" : _role_id , "module_id" : module_id});
				}
			});
			/*提交更新*/
			tableSupport.postJson(ctx + "/mgr/roleModule/updateRoleModule", JSON.stringify([addRoleModuleList,delRoleModuleList]),Noty.response);
			$("#moduleForm").find("#btn_update").attr("disabled",true);
		});
	};
	
	/*初始化*/
	this.init = function(){
		$this.initEvent();
		$this.getMenuTree();
		/*若菜单为空，即当前用户无权限*/
		if(null == menuTree){
			Noty.alert("当前用户没有权限！");
			$("#btn_update").remove();
			return;
		}
		$this.getRoleModule();
		$this.initSelectModal();
		$("#select_modal").change();
	};
	
	/*默默初始化*/
	$this.init();
};