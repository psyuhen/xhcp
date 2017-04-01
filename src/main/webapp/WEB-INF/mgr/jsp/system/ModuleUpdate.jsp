<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="ccb erm system">
    <meta name="author" content="ccb">
    <%--可引入文件(请在Attribute中填写，多个用逗号分开)--%>
	<%request.setAttribute("import", "html5js,bootstrap_cerulean,charisma_app,bootstrap,formvalidation,jquery_noty,datetimepicker,tableSupport,jquery_cookie,form,validator"); %>
	<%@include file="/WEB-INF/mgr/jsp/inc/import.jsp" %>
    <title><spring:message code="xhcp_name" /></title>
    <style type="text/css">
    	<%--在使用formvalidation时，需要点击X清除值时使用--%>
		#moduleForm .form-control-feedback {
		    pointer-events: auto;
		}
		#moduleForm .form-control-feedback:hover {
		    cursor: pointer;
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
	        <% request.setAttribute("module_id", "menuMgr"); %>
	 		<%@include file="/WEB-INF/mgr/jsp/main/nav.jsp" %>
	 
	 			<%-- 新增的内容 --%>
	 			<form id="moduleForm">
					<div class="row">
				        <div class="box col-md-12">
				            <div class="box-inner">
				                <div class="box-content">
				                    <div class="row">
				                        <div class="col-md-3">
				                        	<%-- 菜单ID --%>
										    <div class="form-group">
										        <label class="control-label" for="module_id"><spring:message code="module_id" /></label>
										        <input type="text" name="module_id" class="form-control" id="module_id" placeholder="<spring:message code="module_id" />">
										    </div>
										</div>
				                        <div class="col-md-3">
				                        	<%-- 菜单名称 --%>
										    <div class="form-group">
										        <label class="control-label" for="module_name"><spring:message code="module_name" /></label>
										        <input type="text" name="module_name" class="form-control" id="module_name" placeholder="<spring:message code="module_name" />">
										    </div>
				                        </div>
				                        <div class="col-md-3">
				                        	<%-- 父菜单ID--%>
										    <div class="form-group">
										        <label class="control-label" for="pmodule_id"><spring:message code="module_pid" /></label>
										        <input type="text" name="pmodule_id" class="form-control" id="pmodule_id" placeholder="<spring:message code="module_pid" />">
										    </div>
				                        </div>
				                        <div class="col-md-3">
				                        	<%-- 菜单路径 --%>
										    <div class="form-group">
										    	<label class="control-label" for="module_entry"><spring:message code="module_entry" /></label>
										    	<input type="text" name="module_entry" class="form-control" id="module_entry" placeholder="<spring:message code="module_entry" />">
										    </div>
				                        </div>
				                    </div>
				                    <div class="row">
				                        <div class="col-md-3">
			                        		<%-- 菜单类型 --%>
			                        		<div class="form-group">
											    <label class="control-label" for="module_type"><spring:message code="module_type" /></label>
										    	<select id="module_type" class="form-control" name="module_type" >
									           	    <option value="" selected="selected">请选择...</option>
									           	    <option value="1" >目录</option>
									           	    <option value="2" >菜单</option>
									           	    <option value="3" >功能</option>
									            </select>
			                        		</div>
										</div>
				                        <div class="col-md-3">
				                        	<%-- 菜单是否可用 --%>
				                        	<div class="form-group">
											    <label class="control-label" for="module_valid"><spring:message code="module_valid" /></label>
										    	<select id="module_valid" class="form-control" name="module_valid" >
									           	    <option value="" selected="selected">请选择...</option>
									           	    <option value="0" >无效</option>
									           	    <option value="1" >有效</option>
									            </select>
				                        	</div>
				                        </div>
				                         <div class="col-md-3">
				                        	<%-- 菜单帮助信息 --%>
										    <div class="form-group">
										    	<label class="control-label" for="help_page"><spring:message code="module_help_page" /></label>
										    	<input type="text" name="help_page" class="form-control" id="help_page" placeholder="<spring:message code="module_help_page" />">
										    </div>
				                        </div>
				                        <div class="col-md-3">
				                        	<%-- 菜单是否隐藏 --%>
										    <div class="form-group">
										    	<label class="control-label" for="module_hide"><spring:message code="module_hide" /></label>
										    	<select id="module_hide" class="form-control" name="module_hide" >
									           	    <option value="" selected="selected">请选择...</option>
									           	    <option value="0" >显示</option>
									           	    <option value="1" >隐藏</option>
									            </select>
										    </div>
				                        </div>
				                    </div>
				                    <div class="row">
				                        <div class="col-md-3">
				                        	<%-- 顺序 --%>
										    <div class="form-group">
										        <label class="control-label" for="module_order"><spring:message code="module_order" /></label>
										        <input type="text" name="module_order" class="form-control" id="module_order" placeholder="<spring:message code="module_id" />">
										    </div>
										</div>
				                        <div class="col-md-3">
				                        	<%-- 打开方式 --%>
										    <div class="form-group">
										        <label class="control-label" for="module_target"><spring:message code="module_target" /></label>
										        <select id="module_target" class="form-control" name="module_target" >
									           	    <option value="" selected="selected">请选择...</option>
									           	    <option value="_self">当前页面打开</option>
					    							<option value="_blank">新页面打开</option>
									            </select>
										    </div>
				                        </div>
				                        <div class="col-md-3">
				                        </div>
				                        <div class="col-md-3">
				                        </div>
				                    </div>
				                </div>
				            </div>
				        </div>
				        <!--/span-->
				    </div><!--/row-->
				
				    <%-- 提交和返回按钮 --%>
				    <button type="submit" class="btn btn-primary btn-sm">
				    	<c:if test="${requestScope.page eq 'add' }">
					    	<i class="glyphicon glyphicon-plus-sign"></i>
					    	<spring:message code="btn_add" />
				    	</c:if>
				    	<c:if test="${requestScope.page eq 'update' }">
					    	<i class="glyphicon glyphicon-edit"></i>
					    	<spring:message code="btn_update" />
				    	</c:if>
				    	<c:if test="${requestScope.page eq 'view' }">
					    	<i class="glyphicon glyphicon-zoom-in"></i>
					    	<spring:message code="btn_view" />
				    	</c:if>
				    </button>
				    <a href="${ctx}/mgr/module?module_id=${param.module_id}" class="btn btn-primary btn-sm">
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
	<script src="${ctx}/js/system/ModuleUpdate.${__min}js"></script>
	<script type="text/javascript">
	$(document).ready(function(){
		var module = new Module({
				"oper" : "${requestScope.page}",
				"module_id" : "${param.module_id}",
				"mId" : "${param.mId}",
			});
	});
	</script>
</body>
</html>