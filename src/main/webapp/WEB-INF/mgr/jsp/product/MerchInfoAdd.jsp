<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="ccb erm system">
    <meta name="author" content="ccb">
    <%--可引入文件(请在Attribute中填写，多个用逗号分开)--%>
	<%request.setAttribute("import", "html5js,bootstrap_cerulean,charisma_app,bootstrap,formvalidation,jquery_noty,dateUtil,tableSupport,jquery_cookie,form,validator,paramCheck,datetimepicker,fileupload,dialog"); %>
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
		<% //request.setAttribute("module_id", "productsmgr"); %>
		<%@include file="/WEB-INF/mgr/jsp/main/nav.jsp" %>

		<%-- 新增的内容 --%>
		<form id="merchInfoForm" method="post" action="${ctx}/mgr/product/addMerchInfo" enctype="multipart/form-data">
		<div class="row">
			<div class="box col-md-12">
				<div class="box-inner">
					<div class="box-content">
						<div class="row">
							<div class="col-md-4">
								<%-- 商品ID --%>
								<div class="form-group">
									<label class="control-label" for="merch_id"><spring:message code="商品ID" /></label>
									<input type="text" name="merch_id" readonly class="form-control" id="merch_id" placeholder="<spring:message code="商品ID" />">
								</div>
							</div>
							<div class="col-md-4">
								<%-- 商品名称 --%>
								<div class="form-group">
									<label class="control-label" for="name"><spring:message code="商品名称" /></label>
									<input type="text" name="name" class="form-control" id="name" placeholder="<spring:message code="商品名称" />">
								</div>
							</div>
							<div class="col-md-4">
								<%-- 商品描述 --%>
								<div class="form-group">
									<label class="control-label" for="desc"><spring:message code="商品描述" /></label>
									<input type="text" name="desc" class="form-control" id="desc" placeholder="<spring:message code="商品描述" />">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-4">
								<%-- 分类ID --%>
								<div class="row">
									<div class="col-md-4">
										<div class="form-group">
											<label class="control-label" for="classify_id"><spring:message code="陈皮系列" /></label>
											<select class="form-control" id="classify_root">
												<option value="">---请选择---</option>
												<c:forEach items="${RootClassify}" var="clsroot">
													<option value="${clsroot.classify_id}" title="${clsroot.desc}">${clsroot.name}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="col-md-8">
										<div class="form-group">
											<label class="control-label" for="classify_id"><spring:message code="分类ID" /></label>
											<select name="classify_id" class="form-control" id="classify_id" placeholder="<spring:message code="分类ID" />">
												<option value="">---请选择---</option>
											</select>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<%-- 单价 --%>
								<div class="form-group">
									<label class="control-label" for="price"><spring:message code="单价" /></label>
									<input type="text" name="price" class="form-control" id="price" placeholder="<spring:message code="单价" />">
								</div>
							</div>
							<div class="col-md-4">
								<%-- 库存 --%>
								<div class="form-group">
									<label class="control-label" for="in_stock"><spring:message code="库存" /></label>
									<input type="text" name="in_stock" class="form-control" id="in_stock" placeholder="<spring:message code="库存" />">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-4">
								<%-- 上架时间 --%>
								<div class="form-group">
									<label class="control-label" for="published_date"><spring:message code="上架时间" /></label>
									<input type="text" name="published_date" class="form-control" id="published_date" placeholder="<spring:message code="上架时间" />">
								</div>
							</div>
							<div class="col-md-4">
								<%-- 是否下架 --%>
								<div class="form-group">
									<label class="control-label" for="out_published"><spring:message code="是否下架" /></label>
									<select name="out_published" class="form-control" id="out_published">
										<option value="">---请选择---</option>
										<option value="0">未下架</option>
										<option value="1">下架</option>
									</select>
								</div>
							</div>
							<div class="col-md-4">
								<%-- 店长推荐 --%>
								<div class="form-group">
									<label class="control-label" for="sm_recommend"><spring:message code="店长推荐" /></label>
									<select name="sm_recommend" class="form-control" id="sm_recommend">
										<option value="">---请选择---</option>
										<option value="0">未推荐</option>
										<option value="1">推荐</option>
									</select>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-4">
								<%-- 是否包邮 --%>
								<div class="form-group">
									<label class="control-label" for="free_shipping"><spring:message code="是否包邮" /></label>
									<select name="free_shipping" class="form-control" id="free_shipping">
										<option value="">---请选择---</option>
										<option value="0">不包邮</option>
										<option value="1">包邮</option>
									</select>
								</div>
							</div>
							<div class="col-md-4">
								<%-- 单位 --%>
								<div class="form-group">
									<label class="control-label" for="unit"><spring:message code="单位" /></label>
									<input type="text" name="unit" class="form-control" id="unit" placeholder="<spring:message code="单位" />">
								</div>
							</div>
							<div class="col-md-4">
								<%-- 重量 --%>
								<div class="form-group">
									<label class="control-label" for="weight"><spring:message code="重量" /></label>
									<input type="text" name="weight" class="form-control" id="weight" placeholder="<spring:message code="重量" />">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-4">
								<%-- 规格 --%>
								<div class="form-group">
									<label class="control-label" for="standard"><spring:message code="规格" /></label>
									<input type="text" name="standard" class="form-control" id="standard" placeholder="<spring:message code="规格" />">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<%-- 内容 --%>
								<div class="form-inline">
									<div id="imgfilesDiv">

									</div>
									<a style="background:url(${ctx}/css/images/content_add_image_icon.png)"
									   title="添加详情图片" href="javascript:void(0);" id="addImg">
										<img class="grayscale" src="${ctx}/css/images/content_add_image_icon.png" style="width:108px;height:108px;" alt="添加详情图片">
									</a>
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
			<a href="${ctx}/mgr/product/listmgr?module_id=${requestScope.module_id}" class="btn btn-primary btn-sm">
				<i class="glyphicon glyphicon-chevron-left"></i>
				<spring:message code="btn_back" />
			</a>
		</c:if>
		<c:if test="${param.page eq 'query' }">
			<a href="${ctx}/mgr/product/query?module_id=${requestScope.module_id}" class="btn btn-primary btn-sm">
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
<script src="${ctx}/js/product/MerchInfoAdd.${__min}js" ></script>
<script type="text/javascript">
	$(document).ready(function() {
		new MerchInfo({
			"oper" : "${requestScope.page}",
			"merch_id" : "${param.merch_id}"
		});
	});
</script>
</body>
</html>
