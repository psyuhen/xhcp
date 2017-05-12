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
	<%request.setAttribute("import", "html5js,bootstrap_cerulean,charisma_app,jquery_noty,jquery_cookie,tableSupport,json,chosen,stringutil"); %>
	<%@include file="/WEB-INF/mgr/jsp/inc/import.jsp" %>
    <title><spring:message code="xhcp_name" /></title>
    <style type="text/css">
    	.div_folder{display: inline-block;cursor: pointer;}
    	.div_file{display: inline-block;}
    	.div_check{cursor: pointer;display: inline-block;}
    	.glyphicon-remove{color: red;}
    	.glyphicon-minus{color: yellow;}
    	.glyphicon-ok{color: green;}
    </style>
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
	 			<form id="moduleForm">
					<div class="row">
				        <div class="box col-md-12">
				            <div class="box-inner">
				                <div class="box-content">
				                	<div class="row">
				                		<div class="col-md-6" >
										    <div class="form-group" >
										        <label class="control-label" for="select_modal">模块选择</label>
										    	<select id="select_modal" class="form-control">
													<option>请选择</option>
												</select>
										    </div>
										</div>
										<div class="col-md-6" >
										    <div class="form-group" id="div_select_file"></div>
										</div>
				                	</div>
									<div class="row" >
										<div class="col-lg-12" style="overflow: auto;font-size: 10px;max-height: 500px;" id="nav_scroll">
											<div class="sidebar-nav">
												<div class="nav-canvas" >
													<ul class="nav nav-pills nav-stacked" id="nav_content"></ul>
												</div>
											</div>
										</div>
									</div>
				                </div>
				            </div>
				        </div>
				    </div><!--/row-->
				    <%-- 提交按钮 --%>
				    <button type="button" class="btn btn-primary btn-sm" id="btn_update">
				    	<i class="glyphicon glyphicon-edit"></i>
				    	<spring:message code="btn_update" />
				    </button>
				    <a href="${ctx}/mgr/role?module_id=${requestScope.module_id}" class="btn btn-primary btn-sm" >
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
	<script src="${ctx}/js/common/Dictionary.${__min}js"></script>
	<script src="${ctx}/js/system/RoleModule.${__min}js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			new RoleModule({
				"role_id" : "${param.role_id}",
				"user" : "${sessionScope.Back_Account.account_id}"
			}); 
		});
	</script>
</body>
</html>