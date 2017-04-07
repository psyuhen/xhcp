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
		<% //request.setAttribute("module_id", "gallerymgr"); %>
		<%@include file="/WEB-INF/mgr/jsp/main/nav.jsp" %>

		<%-- 新增的内容 --%>
		<form id="merchGalleryForm">
			<div class="row">
				<div class="box col-md-12">
					<div class="box-inner">
						<div class="box-content">
							<div class="row">
								<div class="col-md-4">
									<%-- 图片ID --%>
									<div class="form-group">
										<label class="control-label" for="gallery_id"><spring:message code="图片ID" /></label>
										<input type="text" name="gallery_id" class="form-control" id="gallery_id" placeholder="<spring:message code="图片ID" />">
									</div>
								</div>
								<div class="col-md-4">
									<%-- 商品ID --%>
									<div class="form-group">
										<label class="control-label" for="merch_id"><spring:message code="商品ID" /></label>
										<input type="text" name="merch_id" class="form-control" id="merch_id" placeholder="<spring:message code="商品ID" />">
									</div>
								</div>
								<div class="col-md-4">
									<%-- 图片名称 --%>
									<div class="form-group">
										<label class="control-label" for="name"><spring:message code="图片名称" /></label>
										<input type="text" name="name" class="form-control" id="name" placeholder="<spring:message code="图片名称" />">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-4">
									<%-- 图片文件名称 --%>
									<div class="form-group">
										<label class="control-label" for="file_name"><spring:message code="图片文件名称" /></label>
										<input type="text" name="file_name" class="form-control" id="file_name" placeholder="<spring:message code="图片文件名称" />">
									</div>
								</div>
								<div class="col-md-4">
									<%-- 文件路径 --%>
									<div class="form-group">
										<label class="control-label" for="path"><spring:message code="文件路径" /></label>
										<input type="text" name="path" class="form-control" id="path" placeholder="<spring:message code="文件路径" />">
									</div>
								</div>
								<div class="col-md-4">
									<%-- 图片类型 --%>
									<div class="form-group">
										<label class="control-label" for="file_type"><spring:message code="图片类型" /></label>
										<select name="file_type" class="form-control" id="file_type">
											<option value="">---请选择---</option>
											<option value="0">商品展示图片</option>
											<option value="1">商品详情图片</option>
										</select>
									</div>
								</div>
							</div>
							<div class="line_01">商品详情图片</div>
							<div class="row">
								<div class="col-md-12">
									<img id="photo" src="" style="width:108px;height:108px;"/>
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
				<a href="${ctx}/mgr/product/gallery/listmgr?module_id=${requestScope.module_id}" class="btn btn-primary btn-sm">
					<i class="glyphicon glyphicon-chevron-left"></i>
					<spring:message code="btn_back" />
				</a>
			</c:if>
			<c:if test="${param.page eq 'query' }">
				<a href="${ctx}/mgr/product/gallery/query?module_id=${requestScope.module_id}" class="btn btn-primary btn-sm">
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
<script src="${ctx}/js/product/MerchGalleryAdd.${__min}js" ></script>
<script type="text/javascript">
	$(document).ready(function() {
		new MerchGallery({
			"oper" : "${requestScope.page}",
			"gallery_id" : "${param.gallery_id}"
		});
	});
</script>
</body>
</html>
