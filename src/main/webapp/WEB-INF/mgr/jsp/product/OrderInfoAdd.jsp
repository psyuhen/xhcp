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
	        <% //request.setAttribute("module_id", "ordermgr"); %>
	 		<%@include file="/WEB-INF/mgr/jsp/main/nav.jsp" %>
	 
	 			<%-- 新增的内容 --%>
	 			<form id="orderInfoForm">
					<div class="row">
				        <div class="box col-md-12">
				            <div class="box-inner">
				                <div class="box-content">
				                	<div class="row">
			                	    <div class="col-md-6">
			                	    	<%-- 订单ID --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="order_id"><spring:message code="订单ID" /></label>
			                		        <input type="text" name="order_id" class="form-control" id="order_id" placeholder="<spring:message code="订单ID" />">
			                		    </div>
			                		</div>
			                	    <div class="col-md-6">
			                	    	<%-- 成交金额 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="amount_money"><spring:message code="成交金额" /></label>
			                		        <input type="text" name="amount_money" class="form-control" id="amount_money" placeholder="<spring:message code="成交金额" />">
			                		    </div>
			                		</div>
			                	    <div class="col-md-6">
			                	    	<%-- 买家Id --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="buyer_user_id"><spring:message code="买家Id" /></label>
			                		        <input type="text" name="buyer_user_id" class="form-control" id="buyer_user_id" placeholder="<spring:message code="买家Id" />">
			                		    </div>
			                		</div>
			                	</div>
			                	<div class="row">
			                	    <div class="col-md-6">
			                	    	<%-- 买家名称 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="buyer_user_name"><spring:message code="买家名称" /></label>
			                		        <input type="text" name="buyer_user_name" class="form-control" id="buyer_user_name" placeholder="<spring:message code="买家名称" />">
			                		    </div>
			                		</div>
			                	    <div class="col-md-6">
			                	    	<%-- 卖家Id --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="seller_user_id"><spring:message code="卖家Id" /></label>
			                		        <input type="text" name="seller_user_id" class="form-control" id="seller_user_id" placeholder="<spring:message code="卖家Id" />">
			                		    </div>
			                		</div>
			                	</div>
			                	<div class="row">
			                	    <div class="col-md-6">
			                	    	<%-- 卖家名称 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="seller_user_name"><spring:message code="卖家名称" /></label>
			                		        <input type="text" name="seller_user_name" class="form-control" id="seller_user_name" placeholder="<spring:message code="卖家名称" />">
			                		    </div>
			                		</div>
			                	    <div class="col-md-6">
			                	    	<%-- 货币单位 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="currency_unit"><spring:message code="货币单位" /></label>
			                		        <input type="text" name="currency_unit" class="form-control" id="currency_unit" placeholder="<spring:message code="货币单位" />">
			                		    </div>
			                		</div>
			                	</div>
			                	<div class="row">
			                	    <div class="col-md-6">
			                	    	<%-- 收货人 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="buyer_name"><spring:message code="收货人" /></label>
			                		        <input type="text" name="buyer_name" class="form-control" id="buyer_name" placeholder="<spring:message code="收货人" />">
			                		    </div>
			                		</div>
			                	    <div class="col-md-6">
			                	    	<%-- 收货人电话 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="buyer_phone"><spring:message code="收货人电话" /></label>
			                		        <input type="text" name="buyer_phone" class="form-control" id="buyer_phone" placeholder="<spring:message code="收货人电话" />">
			                		    </div>
			                		</div>
			                	</div>
			                	<div class="row">
			                	    <div class="col-md-6">
			                	    	<%-- 收货人手机号码 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="buyer_mobile"><spring:message code="收货人手机号码" /></label>
			                		        <input type="text" name="buyer_mobile" class="form-control" id="buyer_mobile" placeholder="<spring:message code="收货人手机号码" />">
			                		    </div>
			                		</div>
			                	    <div class="col-md-6">
			                	    	<%-- 送货方式 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="send_type"><spring:message code="送货方式" /></label>
			                		        <input type="text" name="send_type" class="form-control" id="send_type" placeholder="<spring:message code="送货方式" />">
			                		    </div>
			                		</div>
			                	</div>
			                	<div class="row">
			                	    <div class="col-md-6">
			                	    	<%-- 送货单号 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="send_no"><spring:message code="送货单号" /></label>
			                		        <input type="text" name="send_no" class="form-control" id="send_no" placeholder="<spring:message code="送货单号" />">
			                		    </div>
			                		</div>
			                	    <div class="col-md-6">
			                	    	<%-- 送货时间 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="send_time"><spring:message code="送货时间" /></label>
			                		        <input type="text" name="send_time" class="form-control" id="send_time" placeholder="<spring:message code="送货时间" />">
			                		    </div>
			                		</div>
			                	</div>
			                	<div class="row">
			                	    <div class="col-md-6">
			                	    	<%-- 合计运费 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="freight"><spring:message code="合计运费" /></label>
			                		        <input type="text" name="freight" class="form-control" id="freight" placeholder="<spring:message code="合计运费" />">
			                		    </div>
			                		</div>
			                	    <div class="col-md-6">
			                	    	<%-- 是否需要发票 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="invoice_need"><spring:message code="是否需要发票" /></label>
			                		        <input type="text" name="invoice_need" class="form-control" id="invoice_need" placeholder="<spring:message code="是否需要发票" />">
			                		    </div>
			                		</div>
			                	</div>
			                	<div class="row">
			                	    <div class="col-md-6">
			                	    	<%-- 发票抬头 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="invoice_title"><spring:message code="发票抬头" /></label>
			                		        <input type="text" name="invoice_title" class="form-control" id="invoice_title" placeholder="<spring:message code="发票抬头" />">
			                		    </div>
			                		</div>
			                	    <div class="col-md-6">
			                	    	<%-- 支付方式 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="pay_type"><spring:message code="支付方式" /></label>
			                		        <input type="text" name="pay_type" class="form-control" id="pay_type" placeholder="<spring:message code="支付方式" />">
			                		    </div>
			                		</div>
			                	</div>
			                	<div class="row">
			                	    <div class="col-md-6">
			                	    	<%-- 买方付款时间 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="buyer_pay_time"><spring:message code="买方付款时间" /></label>
			                		        <input type="text" name="buyer_pay_time" class="form-control" id="buyer_pay_time" placeholder="<spring:message code="买方付款时间" />">
			                		    </div>
			                		</div>
			                	    <div class="col-md-6">
			                	    	<%-- 交易发起时间 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="trad_time"><spring:message code="交易发起时间" /></label>
			                		        <input type="text" name="trad_time" class="form-control" id="trad_time" placeholder="<spring:message code="交易发起时间" />">
			                		    </div>
			                		</div>
			                	</div>
			                	<div class="row">
			                	    <div class="col-md-6">
			                	    	<%-- 交易完成时间 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="trad_finish_time"><spring:message code="交易完成时间" /></label>
			                		        <input type="text" name="trad_finish_time" class="form-control" id="trad_finish_time" placeholder="<spring:message code="交易完成时间" />">
			                		    </div>
			                		</div>
			                	    <div class="col-md-6">
			                	    	<%-- 最后更新时间 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="update_time"><spring:message code="最后更新时间" /></label>
			                		        <input type="text" name="update_time" class="form-control" id="update_time" placeholder="<spring:message code="最后更新时间" />">
			                		    </div>
			                		</div>
			                	</div>
			                	<div class="row">
			                	    <div class="col-md-6">
			                	    	<%-- 卖方发货时间 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="seller_deliver_time"><spring:message code="卖方发货时间" /></label>
			                		        <input type="text" name="seller_deliver_time" class="form-control" id="seller_deliver_time" placeholder="<spring:message code="卖方发货时间" />">
			                		    </div>
			                		</div>
			                	    <div class="col-md-6">
			                	    	<%-- 买方确认收货时间 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="buyer_confirm_time"><spring:message code="买方确认收货时间" /></label>
			                		        <input type="text" name="buyer_confirm_time" class="form-control" id="buyer_confirm_time" placeholder="<spring:message code="买方确认收货时间" />">
			                		    </div>
			                		</div>
			                	</div>
			                	<div class="row">
			                	    <div class="col-md-6">
			                	    	<%-- 买家是否已删除 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="seller_del"><spring:message code="买家是否已删除" /></label>
			                		        <input type="text" name="seller_del" class="form-control" id="seller_del" placeholder="<spring:message code="买家是否已删除" />">
			                		    </div>
			                		</div>
			                	    <div class="col-md-6">
			                	    	<%-- 卖家是否已删除 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="buyer_del"><spring:message code="卖家是否已删除" /></label>
			                		        <input type="text" name="buyer_del" class="form-control" id="buyer_del" placeholder="<spring:message code="卖家是否已删除" />">
			                		    </div>
			                		</div>
			                	</div>
			                	<div class="row">
			                	    <div class="col-md-6">
			                	    	<%-- 买家删除时间 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="buyer_del_time"><spring:message code="买家删除时间" /></label>
			                		        <input type="text" name="buyer_del_time" class="form-control" id="buyer_del_time" placeholder="<spring:message code="买家删除时间" />">
			                		    </div>
			                		</div>
			                	    <div class="col-md-6">
			                	    	<%-- 卖家删除时间 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="seller_del_time"><spring:message code="卖家删除时间" /></label>
			                		        <input type="text" name="seller_del_time" class="form-control" id="seller_del_time" placeholder="<spring:message code="卖家删除时间" />">
			                		    </div>
			                		</div>
			                	</div>
			                	<div class="row">
			                	    <div class="col-md-6">
			                	    	<%-- 买家打分 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="buyer_score"><spring:message code="买家打分" /></label>
			                		        <input type="text" name="buyer_score" class="form-control" id="buyer_score" placeholder="<spring:message code="买家打分" />">
			                		    </div>
			                		</div>
			                	    <div class="col-md-6">
			                	    	<%-- 卖家打分 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="seller_score"><spring:message code="卖家打分" /></label>
			                		        <input type="text" name="seller_score" class="form-control" id="seller_score" placeholder="<spring:message code="卖家打分" />">
			                		    </div>
			                		</div>
			                	</div>
			                	<div class="row">
			                	    <div class="col-md-6">
			                	    	<%-- 交易状态 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="status"><spring:message code="交易状态" /></label>
			                		        <input type="text" name="status" class="form-control" id="status" placeholder="<spring:message code="交易状态" />">
			                		    </div>
			                		</div>
			                	    <div class="col-md-6">
			                	    	<%-- 省份编码 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="province_code"><spring:message code="省份编码" /></label>
			                		        <input type="text" name="province_code" class="form-control" id="province_code" placeholder="<spring:message code="省份编码" />">
			                		    </div>
			                		</div>
			                	</div>
			                	<div class="row">
			                	    <div class="col-md-6">
			                	    	<%-- 城市编码 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="city_code"><spring:message code="城市编码" /></label>
			                		        <input type="text" name="city_code" class="form-control" id="city_code" placeholder="<spring:message code="城市编码" />">
			                		    </div>
			                		</div>
			                	    <div class="col-md-6">
			                	    	<%-- 城区ID --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="town_id"><spring:message code="城区ID" /></label>
			                		        <input type="text" name="town_id" class="form-control" id="town_id" placeholder="<spring:message code="城区ID" />">
			                		    </div>
			                		</div>
			                	</div>
			                	<div class="row">
			                	    <div class="col-md-6">
			                	    	<%-- 详细地址 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="address"><spring:message code="详细地址" /></label>
			                		        <input type="text" name="address" class="form-control" id="address" placeholder="<spring:message code="详细地址" />">
			                		    </div>
			                		</div>
			                	    <div class="col-md-6">
			                	    	<%-- 买家评价 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="buyer_advise"><spring:message code="买家评价" /></label>
			                		        <input type="text" name="buyer_advise" class="form-control" id="buyer_advise" placeholder="<spring:message code="买家评价" />">
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
				    	<a href="${ctx}/mgr/mgr/product/order/listmgr?module_id=${requestScope.module_id}" class="btn btn-primary btn-sm">
					    	<i class="glyphicon glyphicon-chevron-left"></i>
					    	<spring:message code="btn_back" />
					    </a>
				    </c:if>
				    <c:if test="${param.page eq 'query' }">
				    	<a href="${ctx}/mgr/mgr/product/order/query?module_id=${requestScope.module_id}" class="btn btn-primary btn-sm">
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
	<script src="${ctx}/js/product/OrderInfoAdd.${__min}js" ></script>
	<script type="text/javascript">
		$(document).ready(function() {
			new OrderInfo({
				"oper" : "${requestScope.page}",
				"order_id" : "${param.order_id}"
			});
		});
	</script>
</body>
</html>
