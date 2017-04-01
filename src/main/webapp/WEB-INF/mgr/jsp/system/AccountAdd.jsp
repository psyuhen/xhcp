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
		#accountForm .form-control-feedback {
		    pointer-events: auto;
		}
		#accountForm .form-control-feedback:hover {
		    cursor: pointer;
		}
		<%--显示密码强度的--%>
		.password-meter {
		    margin-top: 5px;
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
	        <% request.setAttribute("module_id", "UserMgr"); %>
	 		<%@include file="/WEB-INF/mgr/jsp/main/nav.jsp" %>
	 
	 			<%-- 新增的内容 --%>
	 			<form id="accountForm">
					<div class="row">
				        <div class="box col-md-12">
				            <div class="box-inner">
				                <div class="box-content">
				                    <div class="row">
				                        <div class="col-md-6">
				                        	<%-- 账户ID --%>
										    <div class="form-group">
										        <label class="control-label" for="account_id"><spring:message code="account_id" /></label>
										        <input type="text" name="account_id" class="form-control" id="account_id" placeholder="<spring:message code="account_id" />">
										    </div>
										</div>
				                        <div class="col-md-6">
				                        	<%-- 账户名称 --%>
										    <div class="form-group">
										        <label class="control-label" for="account_name"><spring:message code="account_name" /></label>
										        <input type="text" name="account_name" class="form-control" id="account_name" placeholder="<spring:message code="account_name" />">
										    </div>
				                        </div>
				                    </div>
				                    <div class="row">
				                        <div class="col-md-6">
				                        	<%-- 密码 --%>
										    <div class="form-group">
										        <label class="control-label" for="account_password"><spring:message code="password" /></label>
										        <input type="password" name="account_password" class="form-control" id="account_password" placeholder="<spring:message code="password" />">
										        <div class="progress password-meter" id="passwordMeter">
									                <div class="progress-bar"></div>
									            </div>
										    </div>
										</div>
				                        <div class="col-md-6">
				                        	<%-- 账户状态 --%>
										    <div class="form-group">
										    	<label class="control-label" for="account_status"><spring:message code="account_status" /></label>
										    	<select id="account_status" class="form-control" name="account_status" >
													<option value="" selected="selected">请选择...</option>
													<option value="1" >可用</option>
													<option value="0" >锁定</option>
									            </select>
										    </div>
				                        </div>
				                    </div>
				                    <div class="row">
				                        <div class="col-md-6">
				                        	<%-- 失效日期 --%>
										    <div class="form-group">
										    	<%-- 使用bootstrap datatimepicker --%>
											    <label class="control-label" for="account_inv_date"><spring:message code="account_invalid_date" /></label>
										    	<div class='input-group date' id='div_account_inv_date'>
											        <input type="text" name="account_inv_date" class="form-control" id="account_inv_date" placeholder="<spring:message code="account_invalid_date" />">
								                    <span class="input-group-addon">
								                        <span class="glyphicon glyphicon-calendar"></span>
								                    </span>
								                </div>
										    </div>
										</div>
				                        <div class="col-md-6">
											<%-- 手机号码 --%>
											<div class="form-group">
												<label class="control-label" for="mobile"><spring:message code="mobile" /></label>
												<input type="text" name="mobile" class="form-control" id="mobile" placeholder="<spring:message code="mobile" />">
											</div>
				                        </div>
				                    </div>
									<div class="row">
										<div class="col-md-6">
											<%-- QQ --%>
											<div class="form-group">
												<label class="control-label" for="qq"><spring:message code="qq" /></label>
												<input type="text" name="qq" class="form-control" id="qq" placeholder="<spring:message code="qq" />">
											</div>
										</div>
										<div class="col-md-6">
											<%-- WeChat --%>
											<div class="form-group">
												<label class="control-label" for="wechat"><spring:message code="wechat" /></label>
												<input type="text" name="wechat" class="form-control" id="wechat" placeholder="<spring:message code="wechat" />">
											</div>
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
				    <a href="${ctx}/mgr/user?module_id=UserMgr" class="btn btn-primary btn-sm">
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
	<script src="${ctx}/js/system/AccountAdd.${__min}js"></script>
	<script type="text/javascript">
	$(document).ready(function(){
		var account = new Account({
				"oper" : "${requestScope.page}",
				"account_id" : "${param.account_id}",
			});
	});
	</script>
</body>
</html>