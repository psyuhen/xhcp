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
	        <% request.setAttribute("module_id", "UserMgr"); %>
	 		<%@include file="/WEB-INF/mgr/jsp/main/nav.jsp" %>

	 		<%-- 查询条件 --%>
			<div class="row">
		        <div class="box col-md-12">
		            <div class="box-inner">
						<form id="conditionForm">
	                       	<%-- 查询条件输入 --%>
			                <div class="box-content">
			                    <div class="row">
			                        <div class="col-md-4">
			                        	<%-- 账户ID --%>
									    <div class="form-group">
									        <label class="control-label" for="account_id"><spring:message code="account_id" /></label>
									        <input type="text" name="account_id" class="form-control" id="account_id" placeholder="<spring:message code="account_id" />">
									    </div>
									</div>
			                        <div class="col-md-4">
			                        	<%-- 账户名称 --%>
									    <div class="form-group">
									        <label class="control-label" for="account_name"><spring:message code="account_name" /></label>
									        <input type="text" name="account_name" class="form-control" id="account_name" placeholder="<spring:message code="account_name" />">
									    </div>
			                        </div>
			                        <div class="col-md-4">
			                        	<%-- 账户类型 --%>
									    <div class="form-group">
									        <label class="control-label" for="account_type"><spring:message code="account_type" /></label>
											<select id="account_type" class="form-control" name="account_type" >
												<option value="" selected="selected">请选择...</option>
												<option value="0" >后台用户</option>
												<option value="1" >会员</option>
											</select>
									    </div>
									</div>
			                    </div>
			                    <div class="row">
			                        <div class="col-md-4">
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
			                        <div class="col-md-4">
		                        		<%-- 失效日期开始日期 --%>
									    <label class="control-label" >失效<spring:message code="text_start_date" /></label>
									    <div class="form-group">
									    	<%-- 使用bootstrap datatimepicker --%>
									        <input type="text" name="b_start_date" class="form-control" id="div_start_date" placeholder="<spring:message code="account_invalid_date" />">
									    </div>
									</div>
			                        <div class="col-md-4">
			                        	<div class="form-group">
			                        		<label class="control-label" >失效<spring:message code="text_end_date" /></label>
										    <input type="text" name="b_end_date" class="form-control" id="div_end_date" placeholder="<spring:message code="account_invalid_date" />">
									    </div>
			                        </div>
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
						    <a href="${ctx}/mgr/user/add?module_id=usermgr" class="btn btn-primary btn-sm">
						    	<i class="glyphicon glyphicon-plus"></i>
						    	<spring:message code="btn_add" />
						    </a>
						    <button type="button" id="btn_back" class="btn btn-primary btn-sm">
						    	<i class="glyphicon glyphicon-chevron-left"></i>
						    	<spring:message code="btn_back" />
						    </button>
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
							<table id="accountList" class="table table-striped table-bordered" cellspacing="0" width="100%">
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
	<script src="${ctx}/js/common/Dictionary.${__min}js"></script>
	<script src="${ctx}/js/system/AccountList.${__min}js"></script>
	<script type="text/javascript">
	$(document).ready(function(){
		var accountList = new AccountList({
			"module_id" : "${param.module_id}"
		});
	});
	</script>
</body>
</html>