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
	        <% //request.setAttribute("module_id", "navmgr"); %>
	 		<%@include file="/WEB-INF/mgr/jsp/main/nav.jsp" %>
	 
	 			<%-- 新增的内容 --%>
	 			<form id="navInfoForm">
					<div class="row">
				        <div class="box col-md-12">
				            <div class="box-inner">
				                <div class="box-content">
				                	<div class="row">
			                	    <div class="col-md-4">
			                	    	<%-- 导航ID --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="nav_id"><spring:message code="导航ID" /></label>
			                		        <input type="text" name="nav_id" class="form-control" id="nav_id" placeholder="<spring:message code="导航ID" />">
			                		    </div>
			                		</div>
			                	    <div class="col-md-4">
			                	    	<%-- 导航父ID --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="pnav_id"><spring:message code="导航父ID" /></label>
			                		        <input type="text" name="pnav_id" class="form-control" id="pnav_id" placeholder="<spring:message code="导航父ID" />">
			                		    </div>
			                		</div>
			                	    <div class="col-md-4">
			                	    	<%-- 导航名称 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="name"><spring:message code="导航名称" /></label>
			                		        <input type="text" name="name" class="form-control" id="name" placeholder="<spring:message code="导航名称" />">
			                		    </div>
			                		</div>
			                	</div>
			                	<div class="row">
			                	    <div class="col-md-6">
			                	    	<%-- 导航地址 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="url"><spring:message code="导航地址" /></label>
			                		        <input type="text" name="url" class="form-control" id="url" placeholder="<spring:message code="导航地址" />">
			                		    </div>
			                		</div>
			                	    <div class="col-md-6">
			                	    	<%-- 是否为外连导航 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="is_out_link"><spring:message code="是否为外连导航" /></label>
			                		        <select name="is_out_link" id="is_out_link" class="form-control" placeholder="<spring:message code="是否为外连导航" />">
                                                <option value="0" selected>否</option>
                                                <option value="1">是</option>
                                            </select>
                                        </div>
			                		</div>
			                	</div>
			                	<div class="row">
			                	    <div class="col-md-6">
			                	    	<%-- 是否为默认显示 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="is_default"><spring:message code="是否为默认显示" /></label>
                                            <select name="is_default" id="is_default" class="form-control" placeholder="<spring:message code="是否为默认显示" />">
                                                <option value="0" selected>否</option>
                                                <option value="1">是</option>
                                            </select>
			                		    </div>
			                		</div>
			                	    <div class="col-md-6">
			                	    	<%-- 排序 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="order_id"><spring:message code="排序" /></label>
			                		        <input type="text" name="order_id" class="form-control" id="order_id" placeholder="<spring:message code="排序" />">
			                		    </div>
			                		</div>
			                	</div>
			                	<div class="row">
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
				    	<a href="${ctx}/mgr/nav/listmgr?module_id=${requestScope.module_id}" class="btn btn-primary btn-sm">
					    	<i class="glyphicon glyphicon-chevron-left"></i>
					    	<spring:message code="btn_back" />
					    </a>
				    </c:if>
				    <c:if test="${param.page eq 'query' }">
				    	<a href="${ctx}/mgr/nav/query?module_id=${requestScope.module_id}" class="btn btn-primary btn-sm">
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
	<script src="${ctx}/js/nav/NavInfoAdd.${__min}js" ></script>
	<script type="text/javascript">
		$(document).ready(function() {
			new NavInfo({
				"oper" : "${requestScope.page}",
				"nav_id" : "${param.nav_id}"
			});
		});
	</script>
</body>
</html>
