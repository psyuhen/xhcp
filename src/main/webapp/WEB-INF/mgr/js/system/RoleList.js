/**
 * function: RoleList.js
 * 
 * @author chengz
 * @createTime 2016-01-06
 */
var RoleList = function(options) {
        var $this = this;
        var $dataTable = null;
        var _user = options.user || "";
        var roleAccount = {};/* 当前用户可支配角色 用于用户维护 */
        var _module_id = options.module_id
        /* 初始化数据表格 */
        this.initTable = function() {
            var t = new Table(
            {
                "ordering" : false,
                "download" : {enabled:false},
                "autoLoad" : true,
                "table_id" : "roleList",
                "formId" : "conditionForm",
                "service_name" : "com.huateng.xhcp.web.system.RoleController",
                "method_name" : "queryRole",
                "param_type" : "com.huateng.xhcp.model.system.Role",
                "module_name" : "角色维护",
                "row_btn_enabled"       : true,
                "row_btn"       : "011",
                "btn_edit_callback"     : btnEditEvent,
                "btn_del_callback"      : btnDelEvent,
                "row_other_btn_enabled" : true,
                "row_other_btns"        : [{name : "btn_account", text : "用户维护", event: btnAccountEvent},
                                           {name : "btn_module", text : "模块维护", event: btnModuleEvent}],
                "url" : ctx + "/mgr/role/queryRolePage",
                "columns" : [
                              {"data" : "role_id"},
                              {"data" : "role_name"},
                              {"data" : "role_desc"}]
            });
            $dataTable = t.getDataTable();
            $table = t;
        };
 
        /* 查询按钮事件初始化 */
        this.initBtn = function() {
                /* 清空 */
                $("#btn_reset").on("click", function() {
                        $("#conditionForm").find(":input").val("");
                });
                $("#btn_search").on("click", function() {
                        $dataTable.ajax.reload();
                });
        };
        
        /* 用户维护 */
        function btnAccountEvent($rowData){
                window.location.href = ctx+ "/mgr/role/account?role_id=" + $rowData.role_id+ "&module_id=" + _module_id;
        }
        /* 模块维护 */
        function btnModuleEvent($rowData){
                window.location.href = ctx+ "/mgr/role/module?role_id=" + $rowData.role_id+ "&module_id=" + _module_id;
        }
        /*编辑*/
        function btnEditEvent($rowData){
                window.location.href = ctx+ "/mgr/role/update?role_id=" + $rowData.role_id+ 
                "&module_id=" + _module_id + "&page=update";
        }
        /*删除*/
        function btnDelEvent($rowData){
                var options = {
                                service_name : "com.huateng.xhcp.service.system.RoleService",
                                method_name : "deleteRole",
                                parameter_type : "com.huateng.xhcp.model.system.Role",
                                oper_type : "3",
                                oper_desc : "删除角色",
                                switch_name : "roleSwitch",
                                check_fields : [ "role_id" ],
                                module_id 		: "RoleMgr",
                                params : [ ParamCheck.initChkObj("role_id", "角色ID", $rowData.role_id) ],
                                success : function(){
                                        $("#btn_search").click();
                                }
                        };
                ParamCheck.commonSubmit(options);
        }
        /* 获取当前用户可支配角色 */
        this.getRoleAccount = function() {
                tableSupport.synchPost(ctx + "/mgr/accountRole/queryAccountRoleConf",{"account_id" : _user}, function(list) {
                        for ( var i = 0; i < list.length; i++) {
                                roleAccount[list[i].role_id] = _user;
                        }
                });
        };
 
        /* 初始化 */
        this.init = function() {
                $this.getRoleAccount();
                $this.initTable();
                $this.initBtn();
        };
        /* 默默初始化 */
        $this.init();
};