<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="ccb erm system">
    <meta name="author" content="ccb">
    <%--可引入文件(请在Attribute中填写，多个用逗号分开)--%>
	<%request.setAttribute("import", "html5js,bootstrap_cerulean,charisma_app,bootstrap,formvalidation,jquery_noty,dateUtil,tableSupport,jquery_cookie,form,validator,paramCheck"); %>
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
	        <% //request.setAttribute("module_id", "msgmgr"); %>
	 		<%@include file="/WEB-INF/mgr/jsp/main/nav.jsp" %>
	 
	 			<%-- 新增的内容 --%>
	 			<form id="guestBookForm">
					<div class="row">
				        <div class="box col-md-12">
				            <div class="box-inner">
				                <div class="box-content">
				                	<div class="row">
			                	    <div class="col-md-4">
			                	    	<%-- 留言ID --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="msg_id"><spring:message code="留言ID" /></label>
			                		        <input type="text" name="msg_id" class="form-control" id="msg_id" placeholder="<spring:message code="留言ID" />">
			                		    </div>
			                		</div>
			                	    <div class="col-md-4">
			                	    	<%-- 姓名 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="name"><spring:message code="姓名" /></label>
			                		        <input type="text" name="name" class="form-control" id="name" placeholder="<spring:message code="姓名" />">
			                		    </div>
			                		</div>
			                	    <div class="col-md-4">
			                	    	<%-- 电话 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="phone"><spring:message code="电话" /></label>
			                		        <input type="text" name="phone" class="form-control" id="phone" placeholder="<spring:message code="电话" />">
			                		    </div>
			                		</div>
			                	</div>
			                	<div class="row">
			                	    <div class="col-md-4">
			                	    	<%-- 邮箱 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="email"><spring:message code="邮箱" /></label>
			                		        <input type="text" name="email" class="form-control" id="email" placeholder="<spring:message code="邮箱" />">
			                		    </div>
			                		</div>
			                	    <div class="col-md-4">
			                	    	<%-- 地址 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="address"><spring:message code="地址" /></label>
			                		        <input type="text" name="address" class="form-control" id="address" placeholder="<spring:message code="地址" />">
			                		    </div>
			                		</div>
			                	    <div class="col-md-4">
			                	    	<%-- 留言 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="msg_info"><spring:message code="留言" /></label>
			                		        <input type="text" name="msg_info" class="form-control" id="msg_info" placeholder="<spring:message code="留言" />">
			                		    </div>
			                		</div>
			                	</div>

				                </div>
				            </div>
				        </div>
				        <!--/span-->
				    </div><!--/row-->
				
				    
				    <%-- 提交和返回按钮 --%>
				    <c:if test="${requestScope.page ne 'view' }">
					    <button type="submit" class="btn btn-primary btn-sm">
					    	<c:if test="${requestScope.page eq 'add' }">
						    	<i class="glyphicon glyphicon-plus-sign"></i>
						    	<spring:message code="btn_add" />
					    	</c:if>
					    	<c:if test="${requestScope.page eq 'update' }">
						    	<i class="glyphicon glyphicon-edit"></i>
						    	<spring:message code="btn_update" />
					    	</c:if>
					    </button>
				    </c:if>
				    <c:if test="${param.page eq 'mgr' }">
				    	<a href="${ctx}/mgr/system/guest/listmgr?module_id=${requestScope.module_id}" class="btn btn-primary btn-sm">
					    	<i class="glyphicon glyphicon-chevron-left"></i>
					    	<spring:message code="btn_back" />
					    </a>
				    </c:if>
				    <c:if test="${param.page eq 'query' }">
				    	<a href="${ctx}/mgr/system/guest/query?module_id=${requestScope.module_id}" class="btn btn-primary btn-sm">
					    	<i class="glyphicon glyphicon-chevron-left"></i>
					    	<spring:message code="btn_back" />
					    </a>
				    </c:if>
				    
				</form>
	 
	    	<%-- content ends --%>
	    	</div><%--/#content.col-md-0--%>
		</div><%--/fluid-row--%>
	
	    <hr>
		<%@include file="/WEB-INF/mgr/jsp/main/footer.jsp" %>
	
	</div><%--/.fluid-container--%>

	<%-- 加载JS --%>
	<script src="${ctx}/js/system/GuestBookAdd.${__min}js" ></script>
	<script type="text/javascript">
		$(document).ready(function() {
			new GuestBook({
				"oper" : "${requestScope.page}",
				"msg_id" : "${param.msg_id}"
			});
		});
	</script>
</body>
</html>
