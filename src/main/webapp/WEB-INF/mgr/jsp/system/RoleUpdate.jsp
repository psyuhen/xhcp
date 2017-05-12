<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="ccb erm system">
    <meta name="author" content="ccb">
    <%--可引入文件(请在Attribute中填写，多个用逗号分开)--%>
	<%request.setAttribute("import", "html5js,bootstrap_cerulean,charisma_app,jquery_noty,tableSupport,jquery_cookie,validator,paramCheck,formvalidation,form"); %>
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
	        <% request.setAttribute("module_id", "RoleMgr"); %>
	 		<%@include file="/WEB-INF/mgr/jsp/main/nav.jsp" %>
	 
	 			<%-- 操作的内容 --%>
	 			<form id="roleForm">
					<div class="row">
				        <div class="box col-md-12">
				            <div class="box-inner">
				                <div class="box-content">
				                    <div class="row">
				                    	<div class="col-md-6" style="display: none;">
				                        	<%-- 角色ID --%>
										    <div class="form-group">
										        <label class="control-label" for="role_id"><spring:message code="角色ID" /></label>
										        <input type="text" name="role_id" class="form-control" id="role_id" placeholder="<spring:message code="角色ID" />">
										    </div>
										</div>
				                        <div class="col-md-6">
				                        	<%-- 角色名称 --%>
										    <div class="form-group">
										        <label class="control-label" for="role_name"><spring:message code="角色名称" /></label>
										        <input type="text" name="role_name" class="form-control" id="role_name" placeholder="<spring:message code="角色名称" />">
										    </div>
										</div>
				                        <div class="col-md-6">
				                        	<%-- 角色描述 --%>
										    <div class="form-group">
										        <label class="control-label" for="role_desc"><spring:message code="角色描述" /></label>
										        <input type="text" name="role_desc" class="form-control" id="role_desc" placeholder="<spring:message code="角色描述" />">
										    </div>
				                        </div>
				                    </div>
				                </div>
				            </div>
				        </div>
				        <!--/span-->
				    </div><!--/row-->
				
				    <%-- 提交和返回按钮 --%>
				    <c:if test="${param.page eq 'add'}">
				    	<button type="submit" id="btn_update" class="btn btn-primary btn-sm">
					    	<i class="glyphicon glyphicon-plus-sign"></i>
					    	<spring:message code="btn_add" />
					    </button>
				    </c:if>
				    <c:if test="${param.page eq 'update'}">
				    	<button type="submit" id="btn_update" class="btn btn-primary btn-sm">
					    	<i class="glyphicon glyphicon-edit"></i>
					    	<spring:message code="btn_update" />
					    </button>
				    </c:if>
				    <a href="${ctx}/mgr/role?module_id=${requestScope.module_id}" class="btn btn-primary btn-sm">
				    	<i class="glyphicon glyphicon-chevron-left"></i>
				    	<spring:message code="btn_back" />
				    </a>
				</form>
	 
	    	<%-- content ends --%>
	    	</div><%--/#content.col-md-0--%>
		</div><%--/fluid-row--%>
	
	    <hr>
		<%@include file="/WEB-INF/mgr/jsp/main/footer.jsp" %>
	
	</div><%--/.fluid-container--%>

	<%-- 加载JS --%>
	<script src="${ctx}/js/system/RoleUpdate.${__min}js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			new Role({
				"role_id" : "${param.role_id}",
				"page" : "${param.page}",
			});
		});
	</script>
</body>
</html>