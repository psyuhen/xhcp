<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="ccb erm system">
    <meta name="author" content="ccb">
    <%--可引入文件(请在Attribute中填写，多个用逗号分开)--%>
	<%request.setAttribute("import", "html5js,bootstrap,bootstrap_cerulean,charisma_app,jquery_dataTables,datetimepicker,jquery_noty,dateUtil,tableSupport,dialog,jquery_cookie,stringutil,form"); %>
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
	        <% request.setAttribute("module_id", "msgmgr"); %>
	 		<%@include file="/WEB-INF/mgr/jsp/main/nav.jsp" %>
	 		
	 		<%-- 查询条件 --%>
			<div class="row">
		        <div class="box col-md-12">
		            <div class="box-inner">
						<form id="conditionForm">
	                       	<%-- 查询条件输入 --%>
			                <div class="box-content">
			                	<div class="row">
			                	    <div class="col-md-6">
			                	    	<%-- 姓名 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="name"><spring:message code="姓名" /></label>
			                		        <input type="text" name="name" class="form-control" id="name" placeholder="<spring:message code="姓名" />">
			                		    </div>
			                		</div>
			                	    <div class="col-md-6">
			                	    	<%-- 电话 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="phone"><spring:message code="电话" /></label>
			                		        <input type="text" name="phone" class="form-control" id="phone" placeholder="<spring:message code="电话" />">
			                		    </div>
			                		</div>
			                	    <div class="col-md-6">
			                	    	<%-- 邮箱 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="email"><spring:message code="邮箱" /></label>
			                		        <input type="text" name="email" class="form-control" id="email" placeholder="<spring:message code="邮箱" />">
			                		    </div>
			                		</div>
			                	</div>
			                	<div class="row">
			                	    <div class="col-md-6">
			                	    	<%-- 地址 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="address"><spring:message code="地址" /></label>
			                		        <input type="text" name="address" class="form-control" id="address" placeholder="<spring:message code="地址" />">
			                		    </div>
			                		</div>
			                	    <div class="col-md-6">
			                	    	<%-- 留言 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="msg_info"><spring:message code="留言" /></label>
			                		        <input type="text" name="msg_info" class="form-control" id="msg_info" placeholder="<spring:message code="留言" />">
			                		    </div>
			                		</div>
			                	</div>
			                	<div class="row">
			                	</div>

			                </div>
		                </form>
		                
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
						    <c:if test="${requestScope.page eq 'mgr'}">
							    <a href="${ctx}/mgr/system/guest/add?module_id=${requestScope.module_id}&page=${requestScope.page}" class="btn btn-primary btn-sm">
							    	<i class="glyphicon glyphicon-plus"></i>
							    	<spring:message code="btn_add" />
							    </a>
						    </c:if>
				        </div>
		            </div>
		        </div>
		        <!--/span-->
		    </div><!--/row-->
		    <div class="row">
		    	<div class="box col-md-12">
		            <div class="box-inner">
		            	<div class="box-content">
			            	<%-- 列表 --%>
							<table id="guestBookList" class="table table-striped table-bordered" cellspacing="0" width="100%">
							    <thead>
							        <tr>
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
	<script src="${ctx}/js/system/GuestBookList.${__min}js"></script>
	
	<script type="text/javascript">
	$(document).ready(function(){
		new GuestBookList({
			"page" : "${requestScope.page}",
			"module_id" : "${requestScope.module_id}"
		});
	});
	</script>
</body>
</html>
