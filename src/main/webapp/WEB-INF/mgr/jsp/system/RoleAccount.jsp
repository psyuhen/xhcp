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
	<%request.setAttribute("import", "html5js,bootstrap_cerulean,charisma_app,tableSupport,jquery_noty,jquery_cookie,paramCheck"); %>
	<%@include file="/WEB-INF/mgr/jsp/inc/import.jsp" %>
    <title><spring:message code="xhcp_name" /></title>
    <style type="text/css">
    	.divul{
    		max-height: 300px;
    		overflow: auto;
    	}
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
	 		<%@include file="/WEB-INF/mgr/jsp/main/nav.jsp" %>
	 
	 			<%-- 操作的内容 --%>
	 			<form id="roleForm">
				    <div class="row">
				        <div class="box col-md-12">
				            <div class="box-inner">
				            	 <div class="box-header well" data-original-title="" >
				                    <h2 id="ownTitle"><i class="glyphicon glyphicon-th"></i></h2>
				                </div>
				                <div class="box-content">
				                	<div class="row">
				                		<div class="col-md-6">
				                			<div align="right" >
				                				<h5 style="display: inline-block;">拥有角色的用户</h5>
				                        		<a style="display: inline-block;" title="取消选择所有拥有角色的用户" id="allFromhasOwnAccount" class='btn btn-primary btn-sm' ><i class='glyphicon glyphicon-fast-forward'></i> </a>
				                			</div>
				                        	<div class="well">
								                <ul class="nav nav-pills nav-stacked main_menu nav-list divul" id="hasOwnAccount">
								                </ul>
								            </div>
				                        </div>
				                        <div class="col-md-6">
				                        	<div align="left">
				                        		<a style="display: inline-block;" title="选择所有未拥有角色的用户" id="allFromnotOwnAccount" class='btn btn-primary btn-sm' ><i class='glyphicon glyphicon-fast-backward'></i> </a>
				                        		<h5 style="display: inline-block;">可选择的用户</h5>
				                        	</div>
				                        	<div class="well">
								                <ul class="nav nav-pills nav-stacked main_menu nav-list divul" id="notOwnAccount">
								                </ul>
								            </div>
				                        </div>
				                	</div>
			                    	<div class="row">
		                        		<div class="col-md-12">
			                        		<button type="button" class="btn btn-primary btn-sm" id="btn_update_own">
										    	<i class="glyphicon glyphicon-edit"></i>
										    	保存
										    </button>
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
				            	<div class="box-header well" data-original-title="" >
				                    <h2 id="governTitle"><i class="glyphicon glyphicon-th"></i></h2>
				                </div>
				                <div class="box-content">
			                    	<div class="row">
				                		<div class="col-md-6">
				                        	<div align="right" >
				                				<h5 style="display: inline-block;">支配角色的用户</h5>
				                        		<a style="display: inline-block;" title="取消选择所有支配角色的用户" id="allFromhasGovernAccount" class='btn btn-primary btn-sm' ><i class='glyphicon glyphicon-fast-forward'></i> </a>
				                			</div>
				                        	<div class="well">
								                <ul class="nav nav-pills nav-stacked main_menu nav-list divul" id="hasGovernAccount">
								                </ul>
								            </div>
				                        </div>
				                        <div class="col-md-6">
				                        	<div align="left">
				                        		<a style="display: inline-block;" title="选择所有未支配角色的用户" id="allFromnotGovernAccount" class='btn btn-primary btn-sm' ><i class='glyphicon glyphicon-fast-backward'></i> </a>
				                        		<h5 style="display: inline-block;">可选择的用户</h5>
				                        	</div>
				                        	<div class="well">
								                <ul class="nav nav-pills nav-stacked main_menu nav-list divul" id="notGovernAccount">
								                </ul>
								            </div>
				                        </div>
				                	</div>
			                    	<div class="row">
		                        		<div class="col-md-12">
			                        		<button type="button" class="btn btn-primary btn-sm" id="btn_update_govern">
										    	<i class="glyphicon glyphicon-edit"></i>
										    	保存
										    </button>
										    <a href="${ctx}/mgr/role?module_id=${requestScope.module_id}" class="btn btn-primary btn-sm" >
										    	<i class="glyphicon glyphicon-chevron-left"></i>
										    	<spring:message code="btn_back" />
										    </a>
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
	<script src="${ctx}/js/common/Dictionary.${__min}js"></script>
	<script src="${ctx}/js/system/RoleAccount.${__min}js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			new RoleAccount({
				"role_id" : "${param.role_id}"
			}); 
		});
	</script>
</body>
</html>