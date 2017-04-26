<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="ccb erm system">
    <meta name="author" content="ccb">
    <%--可引入文件(请在Attribute中填写，多个用逗号分开)--%>
	<%request.setAttribute("import", "html5js,bootstrap,bootstrap_cerulean,charisma_app,jquery_dataTables,datetimepicker,jquery_noty,dateUtil,tableSupport,dialog,jquery_cookie,stringutil,form,RenderUtil"); %>
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
	        <% request.setAttribute("module_id", "ordermgr"); %>
	 		<%@include file="/WEB-INF/mgr/jsp/main/nav.jsp" %>
	 		
	 		<%-- 列表 --%>
			<form id="conditionForm">
				<input type="hidden" name="order_id" value="${param.order_id}" />
			</form>
		    <div class="row">
		    	<div class="box col-md-12">
		            <div class="box-inner">
		            	<div class="box-content">
			            	<%-- 列表 --%>
							<table id="orderDetailList" class="table table-striped table-bordered" cellspacing="0" width="100%">
							    <thead>
							        <tr>
							        </tr>
							    </thead>
							</table>
		            	</div>
		            </div><!--/box-inner-->
		        </div>
		    </div><!--/row-->

			<c:if test="${param.page eq 'mgr' }">
				<a href="${ctx}/mgr/product/order/listmgr?module_id=${requestScope.module_id}" class="btn btn-primary btn-sm">
					<i class="glyphicon glyphicon-chevron-left"></i>
					<spring:message code="btn_back" />
				</a>
			</c:if>
			<c:if test="${param.page eq 'query' }">
				<a href="${ctx}/mgr/product/order/query?module_id=${requestScope.module_id}" class="btn btn-primary btn-sm">
					<i class="glyphicon glyphicon-chevron-left"></i>
					<spring:message code="btn_back" />
				</a>
			</c:if>
	    	<%-- content ends --%>
	    </div><%--/#content.col-md-0--%>
		</div><%--/fluid-row--%>
	
	    <hr>
		<%@include file="/WEB-INF/mgr/jsp/main/footer.jsp" %>
	
	</div><%--/.fluid-container--%>
	
	
	<%-- 加载JS --%>
	<script src="${ctx}/js/product/OrderDetailList.${__min}js"></script>
	
	<script type="text/javascript">
	$(document).ready(function(){
		new OrderDetailList({
			"page" : "${requestScope.page}",
			"module_id" : "${requestScope.module_id}"
		});
	});
	</script>
</body>
</html>
