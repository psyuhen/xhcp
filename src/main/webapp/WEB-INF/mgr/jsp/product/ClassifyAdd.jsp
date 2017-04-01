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
	        <% //request.setAttribute("module_id", "classifyList"); %>
	 		<%@include file="/WEB-INF/mgr/jsp/main/nav.jsp" %>
	 
	 			<%-- 新增的内容 --%>
	 			<form id="classifyForm">
					<div class="row">
				        <div class="box col-md-12">
				            <div class="box-inner">
				                <div class="box-content">
				                	<div class="row">
										<div class="col-md-4">
											<%-- 父分类ID --%>
											<div class="form-group">
												<label class="control-label" for="pcls_id"><spring:message code="父分类ID" /></label>
												<select class="form-control" id="pcls_id" name="pcls_id">
													<option value="-1">主分类</option>
													<c:forEach items="${PCLSIDS}" var="pcls">
														<option value="${pcls.classify_id}">${pcls.name}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="col-md-4">
											<%-- 分类名称 --%>
											<div class="form-group">
												<label class="control-label" for="name"><spring:message code="分类名称" /></label>
												<input type="text" name="name" class="form-control" id="name" placeholder="<spring:message code="分类名称" />">
											</div>
										</div>
										<div class="col-md-4">
											<%-- 分类描述 --%>
											<div class="form-group">
												<label class="control-label" for="desc"><spring:message code="分类描述" /></label>
												<input type="text" name="desc" class="form-control" id="desc" placeholder="<spring:message code="分类描述" />">
											</div>
										</div>
									</div>

				                </div>
				            </div>
				        </div>
				        <!--/span-->
				    </div><!--/row-->

					<input type="hidden" name="classify_id" id="classify_id" value="${param.classify_id}"/>
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
				    	<a href="${ctx}/mgr/classify/listmgr?module_id=${requestScope.module_id}" class="btn btn-primary btn-sm">
					    	<i class="glyphicon glyphicon-chevron-left"></i>
					    	<spring:message code="btn_back" />
					    </a>
				    </c:if>
				    <c:if test="${param.page eq 'query' }">
				    	<a href="${ctx}/mgr/classify/query?module_id=${requestScope.module_id}" class="btn btn-primary btn-sm">
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
	<script src="${ctx}/js/product/ClassifyAdd.${__min}js" ></script>
	<script type="text/javascript">
		$(document).ready(function() {
			new Classify({
				"oper" : "${requestScope.page}",
				"classify_id" : "${param.classify_id}"
			});
		});
	</script>
</body>
</html>
