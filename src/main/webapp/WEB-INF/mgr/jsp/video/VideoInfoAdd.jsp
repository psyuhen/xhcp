<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="ccb erm system">
    <meta name="author" content="ccb">
    <%--可引入文件(请在Attribute中填写，多个用逗号分开)--%>
	<%request.setAttribute("import", "html5js,bootstrap_cerulean,charisma_app,bootstrap,formvalidation,jquery_noty,dateUtil,tableSupport,jquery_cookie,form,validator,paramCheck,fileupload,dialog"); %>
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
	        <% //request.setAttribute("module_id", "videomgr"); %>
	 		<%@include file="/WEB-INF/mgr/jsp/main/nav.jsp" %>
	 
	 			<%-- 新增的内容 --%>
	 			<form id="videoInfoForm" method="post" enctype="multipart/form-data" action="${ctx}/mgr/video/addVideoInfo">
					<div class="row">
				        <div class="box col-md-12">
				            <div class="box-inner">
				                <div class="box-content">
				                	<div class="row">
										<div class="col-md-6">
											<%-- 视频信息id --%>
											<div class="form-group">
												<label class="control-label" for="video_id"><spring:message code="视频信息id" /></label>
												<input type="text" name="video_id" class="form-control" id="video_id" placeholder="<spring:message code="视频信息id" />">
											</div>
										</div>
										<div class="col-md-6">
											<%-- 标题 --%>
											<div class="form-group">
												<label class="control-label" for="title"><spring:message code="标题" /></label>
												<input type="text" name="title" class="form-control" id="title" placeholder="<spring:message code="标题" />">
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6">
											<%-- 背景图片ID --%>
											<div class="form-group">

												<label class="control-label" for="select_file"><spring:message code="背景图片ID" /></label>
												<input type="file" name="img_file" class="form-control" id="select_file" >
												<input type="hidden" name="img_file_id" id="img_file_id" />
												<input type="hidden" name="img_file_file_type" value="5"/>
												<input type="hidden" name="img_file_file_suffix" value="0"/>
											</div>
										</div>
										<div class="col-md-6">
											<%-- 视频文件ID --%>
											<div class="form-group">
												<label class="control-label" for="select_video"><spring:message code="视频文件ID" /></label>
												<input type="file" name="video_file" class="form-control" id="select_video" >
												<input type="hidden" name="video_file_id" id="video_file_id" />
												<input type="hidden" name="video_file_file_type" value="4"/>
												<input type="hidden" name="video_file_file_suffix" value="1"/>
											</div>
										</div>
									</div>
									<div class="row">
										<div id="progress" class="progress progress-striped progress-success active" style="display:none;">
											<div class="progress-bar" style="width: 0%;" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100"></div>
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
				    	<a href="${ctx}/mgr/video/listmgr?module_id=${requestScope.module_id}" class="btn btn-primary btn-sm">
					    	<i class="glyphicon glyphicon-chevron-left"></i>
					    	<spring:message code="btn_back" />
					    </a>
				    </c:if>
				    <c:if test="${param.page eq 'query' }">
				    	<a href="${ctx}/mgr/video/query?module_id=${requestScope.module_id}" class="btn btn-primary btn-sm">
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
	<script src="${ctx}/js/video/VideoInfoAdd.${__min}js" ></script>
	<script type="text/javascript">
		$(document).ready(function() {
			var $v = new VideoInfo({
				"oper" : "${requestScope.page}",
				"video_id" : "${param.video_id}"
			});
		});
	</script>
</body>
</html>
