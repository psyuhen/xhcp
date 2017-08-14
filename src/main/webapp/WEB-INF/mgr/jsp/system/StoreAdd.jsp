<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="ccb erm system">
    <meta name="author" content="ccb">
    <%--可引入文件(请在Attribute中填写，多个用逗号分开)--%>
	<%request.setAttribute("import", "html5js,bootstrap_cerulean,charisma_app,bootstrap,formvalidation,jquery_noty,dateUtil,tableSupport,jquery_cookie,form,validator,paramCheck,kindeditor"); %>
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
	        <% //request.setAttribute("module_id", "storemgr"); %>
	 		<%@include file="/WEB-INF/mgr/jsp/main/nav.jsp" %>
	 
	 			<%-- 新增的内容 --%>
	 			<form id="storeForm">
					<div class="row">
				        <div class="box col-md-12">
				            <div class="box-inner">
				                <div class="box-content">
				                	<div class="row">
				                	    <div class="col-md-6">
				                	    	<%-- 店铺名称 --%>
				                		    <div class="form-group">
				                		        <label class="control-label" for="store_name"><spring:message code="店铺名称" /></label>
				                		        <input type="text" name="store_name" class="form-control" id="store_name" placeholder="<spring:message code="店铺名称" />">
				                		    </div>
				                		</div>
				                		<div class="col-md-6">
				                	    	<%-- 是否使用 --%>
				                		    <div class="form-group">
				                		        <label class="control-label" for="is_use"><spring:message code="是否使用" /></label>
				                		        <select id="is_use" class="form-control" name="is_use" >
													<option value="0" selected="selected">否</option>
													<option value="1" >是</option>
									            </select>
				                		    </div>
				                		</div>
				                	</div>
				                	<div class="row">
				                	    <div class="col-md-12">
				                	    	<%-- 店铺内容 --%>
				                		    <div class="form-group">
				                		        <label class="control-label" for="contents"><spring:message code="店铺内容" /></label>
				                		        <textarea name="contents" id="contents" style="width:100%;"></textarea>
				                		    </div>
				                		</div>
				                	</div>

				                </div>
				            </div>
				        </div>
				        <!--/span-->
				    </div><!--/row-->
				
				    <input type="hidden" name="store_id" id="store_id" value="${param.store_id}"/>
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
				    	<a href="${ctx}/mgr/store/listmgr?module_id=${requestScope.module_id}" class="btn btn-primary btn-sm">
					    	<i class="glyphicon glyphicon-chevron-left"></i>
					    	<spring:message code="btn_back" />
					    </a>
				    </c:if>
				    <c:if test="${param.page eq 'query' }">
				    	<a href="${ctx}/mgr/store/query?module_id=${requestScope.module_id}" class="btn btn-primary btn-sm">
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
	<script src="${ctx}/js/system/StoreAdd.${__min}js" ></script>
	<script type="text/javascript">
		$(document).ready(function() {
			new Store({
				"oper" : "${requestScope.page}",
				"store_id" : "${param.store_id}"
			});
		});
	</script>
</body>
</html>
