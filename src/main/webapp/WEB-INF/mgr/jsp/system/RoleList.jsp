<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="ccb erm system">
    <meta name="author" content="ccb">
    <%--可引入文件(请在Attribute中填写，多个用逗号分开)--%>
    <% request.setAttribute("module_id", "RoleMgr"); %>
	<%request.setAttribute("import", "html5js,bootstrap,bootstrap_cerulean,charisma_app,jquery_dataTables,jquery_noty,tableSupport,dialog,jquery_cookie,paramCheck,formvalidation,validator,form"); %>
	<%@include file="/WEB-INF/mgr/jsp/inc/import.jsp" %>
    <title><spring:message code="xhcp_name" /></title>
</head>
<body>

	<%@include file="/WEB-INF/mgr/jsp/main/topbar.jsp" %>
	<div class="ch-container">
	    <div class="row">
	        <%@include file="/WEB-INF/mgr/jsp/main/left_menu.jsp" %>
			<%-- 主页开始 --%>
	        <div id="content" class="col-lg-10 col-sm-10">
	        <%-- content starts --%>
	 		<%@include file="/WEB-INF/mgr/jsp/main/nav.jsp" %>
	 		
	 		<%-- 查询条件 --%>
			<div class="row">
		        <div class="box col-md-12">
		            <div class="box-inner">
						<form id="conditionForm">
	                       	<%-- 查询条件输入 --%>
			                <div class="box-content">
			                    <div class="row">
			                        <div class="col-md-4">
			                        	<%-- 角色ID --%>
									    <div class="form-group">
									        <label class="control-label" for="role_id"><spring:message code="角色ID" /></label>
									        <input type="text" name="role_id" class="form-control" id="role_id" placeholder="<spring:message code="角色ID" />">
									    </div>
									</div>
			                        <div class="col-md-4">
			                        	<%-- 角色名称 --%>
									    <div class="form-group">
									        <label class="control-label" for="role_name"><spring:message code="角色名称" /></label>
									        <input type="text" name="role_name" class="form-control" id="role_name" placeholder="<spring:message code="角色名称" />">
									    </div>
			                        </div>
			                        <div class="col-md-4">
			                        	<%-- 角色描述 --%>
									    <div class="form-group">
									        <label class="control-label" for="role_desc"><spring:message code="角色描述" /></label>
									        <input type="text" name="role_desc" class="form-control" id="role_desc" placeholder="<spring:message code="角色描述" />">
									    </div>
			                        </div>
			                    </div>
			                </div>
			            	<div class="modal-footer">
					            <%-- 查询和返回按钮 --%>
							    <button type="button" id="btn_search" class="btn btn-primary btn-sm">
							    	<i class="glyphicon glyphicon-search"></i>
							    	<spring:message code="btn_search" />
							    </button>
							    <button type="button" id="btn_reset" class="btn btn-primary btn-sm">
							    	<i class="glyphicon glyphicon-remove"></i>
							    	<spring:message code="btn_reset" />
							    </button>
							    <a href="${ctx}/mgr/role/add?page=add&module_id=${requestScope.module_id}" class="btn btn-primary btn-sm">
							    	<i class="glyphicon glyphicon-plus"></i>
							    	<spring:message code="btn_add" />
							    </a>
					        </div>
				        </form>
		            </div><!-- inner -->
		        </div>
		        <!--/span-->
		    </div><!--/row-->
		    <div class="row">
		    	<div class="box col-md-12">
		            <div class="box-inner">
		            	<div class="box-content">
			            	<%-- 列表 --%>
							<table id="roleList" class="hover table table-striped table-bordered bootstrap-datatable datatable responsive" cellspacing="0" width="100%">
							    <thead>
							        <tr>
							            <th><spring:message code="角色ID" /></th>
							            <th><spring:message code="角色名称" /></th>
							            <th><spring:message code="角色描述" /></th>
							        </tr>
							    </thead>
							</table>
		            	</div>
		            </div><!--/box-inner-->
		        </div>
		    </div><!--/row-->
	    	<%-- content ends --%>
	    </div><%--/#content.col-md-0--%>
		</div><%--/fluid-row--%>
	
	    <hr>
		<%@include file="/WEB-INF/mgr/jsp/main/footer.jsp" %>
	
	</div><%--/.fluid-container--%>
	
	
	
	<%-- 加载JS --%>
	<script src="${ctx}/js/system/RoleList.${__min}js"></script>
	<script type="text/javascript">
	$(document).ready(function() {
		new RoleList({
			"user" : "${sessionScope.user.account_id}",
			"module_id" : "${param.module_id}"
		});
	});
	</script>
</body>
</html>