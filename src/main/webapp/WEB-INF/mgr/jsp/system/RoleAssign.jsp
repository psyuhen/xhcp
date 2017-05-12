<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLDecoder" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="ccb erm system">
    <meta name="author" content="ccb">
    <%--可引入文件(请在Attribute中填写，多个用逗号分开)--%>
	<%request.setAttribute("import", "html5js,bootstrap_cerulean,charisma_app,bootstrap,formvalidation,jquery_noty,datetimepicker,tableSupport,jquery_cookie,form"); %>
	<%@include file="/WEB-INF/mgr/jsp/inc/import.jsp" %>
    <title><spring:message code="xhcp_name" /></title>
    <style type="text/css">
    	.divul{
    		max-height: 300px;
    		overflow: auto;
    	}
    </style>
</head>

<%
	String account_name = request.getParameter("account_name");
	account_name = account_name == null ? "" : account_name;
	//account_name = new String(account_name.getBytes("iso8859-1"), "utf8");
	account_name = URLDecoder.decode(account_name, "utf-8");
%>

<body>
	<%@include file="/WEB-INF/mgr/jsp/main/topbar.jsp" %>
	<div class="ch-container">
	    <div class="row">
	        <%@include file="/WEB-INF/mgr/jsp/main/left_menu.jsp" %>
			<%-- 主页开始 --%>
	        <div id="content" class="col-lg-10 col-sm-10">
	        <%-- content starts --%>
	        <% request.setAttribute("module_id", "UserMgr"); %>
	 		<%@include file="/WEB-INF/mgr/jsp/main/nav.jsp" %>
	 
	 			<%-- 新增的内容 --%>
	 			<form id="roleAssignForm">
 				    <div class="row">
				        <div class="box col-md-12">
				            <div class="box-inner">
				                <div class="box-header well" data-original-title="">
				                    <h2><i class="glyphicon glyphicon-th"></i> 拥有的角色</h2>
				                </div>
				                <div class="box-content">
				                    <div class="row">
				                        <div class="col-md-6">
				                        	<h5>登录用户<strong title="${sessionScope.user.account_name}">[${sessionScope.user.account_id}(${sessionScope.user.account_name})]</strong>拥有的角色</h5>
				                        	<div class="well">
								                <ul class="nav nav-pills nav-stacked main_menu nav-list divul" id="canBelongRole">
								                </ul>
								            </div>
				                        </div>
				                        <div class="col-md-1">
				                        	<br/><br/>
				                        	<div class="row"><a id="belongRoleAuth" class='btn btn-primary btn-sm' href='javascript:void(0)'><i class='glyphicon glyphicon-arrow-right'></i> 授 权</a></div><br/>
				                        	<div class="row"><a id="belongRoleDeny" class='btn btn-primary btn-sm' href='javascript:void(0)'><i class='glyphicon glyphicon-arrow-left'></i> 撤 消</a></div><br/>
				                        	<div class="row"><a id="allBelongRoleAuth" class='btn btn-primary btn-sm' href='javascript:void(0)'><i class='i'></i> 全部授权</a></div><br/>
				                        	<div class="row"><a id="allBelongRoleDeny" class='btn btn-primary btn-sm' href='javascript:void(0)'><i class='i'></i> 撤消全部</a></div><br/>
				                        </div>
				                        <div class="col-md-5">
				                        	<h5><strong title="<%=account_name%>">[${param.account_id}(<%=account_name%>)]</strong>用户拥有的角色</h5>
				                        	<div class="well">
								                <ul class="nav nav-pills nav-stacked main_menu nav-list divul" id="hasBelongRole">
								                </ul>
								            </div>
				                        </div>
				                    </div>
		                        	<div class="row">
		                        		<div class="col-md-12">
			                        		<a id="saveBelongRole" class='btn btn-primary' href='javascript:void(0)'><i class='glyphicon glyphicon-hdd'></i> 保存</a>
		                        		</div>
		                        	</div>
				                </div>
				            </div>
				        </div>
				        <!--/span-->
				    </div><!--/row-->
 				    <div class="row">
				        <div class="box col-md-12">
				            <div class="box-inner">
				                <div class="box-header well" data-original-title="">
				                    <h2><i class="glyphicon glyphicon-th"></i> 支配的角色</h2>
				                </div>
				                <div class="box-content">
				                    <div class="row">
				                        <div class="col-md-6">
				                        	<h5>登录用户<strong title="${sessionScope.user.account_name}">[${sessionScope.user.account_id}(${sessionScope.user.account_name})]</strong>能支配的角色</h5>
				                        	<div class="well">
								                <ul class="nav nav-pills nav-stacked main_menu nav-list divul" id="canAssignRole">
								                </ul>
								            </div>
				                        </div>
				                        <div class="col-md-1">
				                        	<br/><br/>
				                        	<div class="row"><a id="assignRoleAuth" class='btn btn-primary btn-sm' href='javascript:void(0)'><i class='glyphicon glyphicon-arrow-right'></i> 授权</a></div><br/>
				                        	<div class="row"><a id="assignRoleDeny" class='btn btn-primary btn-sm' href='javascript:void(0)'><i class='glyphicon glyphicon-arrow-left'></i> 撤消</a></div><br/>
				                        	<div class="row"><a id="allAssignRoleAuth" class='btn btn-primary btn-sm' href='javascript:void(0)'><i class='i'></i> 全部授权</a></div><br/>
				                        	<div class="row"><a id="allAssignRoleDeny" class='btn btn-primary btn-sm' href='javascript:void(0)'><i class='i'></i> 撤消全部</a></div><br/>
				                        </div>
				                        <div class="col-md-5">
				                        	<h5><strong title="<%=account_name%>">[${param.account_id}(<%=account_name%>)]</strong>用户能支配的角色</h5>
				                        	<div class="well">
								                <ul class="nav nav-pills nav-stacked main_menu nav-list divul"  id="hasAssignRole">
								                </ul>
								            </div>
				                        </div>
				                    </div>
				                    <div class="row">
		                        		<div class="col-md-12">
			                        		<a id="saveAssignRole" class='btn btn-primary' href='javascript:void(0)'><i class='glyphicon glyphicon-hdd'></i> 保存</a>
			                        		<a class='btn btn-primary' href="${ctx}/mgr/user?module_id=UserMgr"'><i class='glyphicon glyphicon-chevron-left'></i> 返回</a>
		                        		</div>
		                        	</div>
				                </div>
				            </div>
				        </div>
				        <!--/span-->
				    </div><!--/row-->
				</form>
	 
	    	<%-- content ends --%>
	    	</div><%--/#content.col-md-0--%>
		</div><%--/fluid-row--%>
	
	    <hr>
		<%@include file="/WEB-INF/mgr/jsp/main/footer.jsp" %>
	
	</div><%--/.fluid-container--%>

	<%-- 加载JS --%>
	<script src="${ctx}/js/system/RoleAssign.${__min}js"></script>
	<script type="text/javascript">
	$(document).ready(function(){
		new RoleAssign({
			"account_id" : "${param.account_id}"
		});
	});
	</script>
</body>
</html>