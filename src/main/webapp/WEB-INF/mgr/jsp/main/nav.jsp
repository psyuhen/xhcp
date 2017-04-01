<div>
    <ul class="breadcrumb">
        <li>
            <a href="${ctx}/mgr/main"><spring:message code="main_page" /></a>
        </li>
    </ul>
    <script type="text/javascript">
    	$(document).ready(function(){
	        var module_id="${module_id}";
            var moduleList = [];
	    	if(module_id != ""){
                findInMenu(module_id);
                nav(moduleList);
	    		//先从本地缓存中获取，能获取到就取本地缓存的数据
	    		/*var _key = "module_id_" + module_id;
	    		var tmp = Storage.getItem(_key);
	    		tmp = $.trim(tmp);
	    		if(tmp !== ""){
	    			var list = JSON.parse(tmp);
	    			nav(list);
	    		}else{

	    			//本地没有先查询数据库
	    			/!*tableSupport.get(mgr_path + "/module/findNav", {"module_id":module_id}, function(list){
	    				_list = list;
	    				Storage.addItem(_key, JSON.stringify(list));
	    				nav(list);
	    			});*!/
	    		}*/
	    	}

	    	function findInMenu(moduleId){
                if(moduleId == "Module" || moduleId == undefined){
                    return;
                }

                var _moduleIdObj = $("#" + moduleId);
                var _moduleName = _moduleIdObj.find("a").eq(0).text();

                var obj={};
                obj.module_name = _moduleName;
                moduleList.push(obj);

                var _pModuleId = _moduleIdObj.parent().parent("li").attr("id");

                findInMenu(_pModuleId);
            }
	    	
	    	function nav(list){
	    		if(list.length == 0){
	    			return;
	    		}
	    		/*添加nav*/
	    		var $li = $('<li><a href="javascript:void(0);" ></a></li>');
	    		var $ul = $('.breadcrumb');
	    		for (var index = list.length - 1; index >= 0; index--) {
	    			$li.find("a").text(list[index].module_name);
	    			var _clone = $li.clone();
	    			$ul.append(_clone);
				}
	    	}
    	});
    </script>
</div>