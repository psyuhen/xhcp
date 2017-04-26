<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="ccb erm system">
    <meta name="author" content="ccb">
    <%--可引入文件(请在Attribute中填写，多个用逗号分开)--%>
	<%request.setAttribute("import", "html5js,bootstrap_cerulean,charisma_app,bootstrap,formvalidation,jquery_noty,dateUtil,tableSupport,jquery_cookie,form,validator,paramCheck,datetimepicker"); %>
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
			                	    <div class="col-md-4">
			                	    	<%-- 订单ID --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="order_id"><spring:message code="订单ID" /></label>
			                		        <input type="text" name="order_id" class="form-control" id="order_id" placeholder="<spring:message code="订单ID" />">
			                		    </div>
			                		</div>
			                	    <div class="col-md-4">
			                	    	<%-- 成交金额 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="amount_money"><spring:message code="成交金额" /></label>
			                		        <input type="text" name="amount_money" class="form-control" id="amount_money" placeholder="<spring:message code="成交金额" />">
			                		    </div>
			                		</div>
			                	    <div class="col-md-4">
			                	    	<%-- 买家Id --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="buyer_account_id"><spring:message code="买家Id" /></label>
			                		        <input type="text" name="buyer_account_id" class="form-control" id="buyer_account_id" placeholder="<spring:message code="买家Id" />">
			                		    </div>
			                		</div>
			                	</div>
			                	<div class="row">
			                	    <div class="col-md-4">
			                	    	<%-- 买家名称 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="buyer_user_name"><spring:message code="买家名称" /></label>
			                		        <input type="text" name="buyer_user_name" class="form-control" id="buyer_user_name" placeholder="<spring:message code="买家名称" />">
			                		    </div>
			                		</div>
			                	    <div class="col-md-4">
			                	    	<%-- 卖家Id --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="seller_account_id"><spring:message code="卖家Id" /></label>
			                		        <input type="text" name="seller_account_id" class="form-control" id="seller_account_id" placeholder="<spring:message code="卖家Id" />">
			                		    </div>
			                		</div>
			                	    <div class="col-md-4">
			                	    	<%-- 卖家名称 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="seller_user_name"><spring:message code="卖家名称" /></label>
			                		        <input type="text" name="seller_user_name" class="form-control" id="seller_user_name" placeholder="<spring:message code="卖家名称" />">
			                		    </div>
			                		</div>
								</div>
								<div class="row">
			                	    <div class="col-md-4">
			                	    	<%-- 货币单位 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="currency_unit"><spring:message code="货币单位" /></label>
			                		        <input type="text" name="currency_unit" class="form-control" id="currency_unit" placeholder="<spring:message code="货币单位" />">
			                		    </div>
			                		</div>
			                	    <div class="col-md-4">
			                	    	<%-- 收货人 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="buyer_name"><spring:message code="收货人" /></label>
			                		        <input type="text" name="buyer_name" class="form-control" id="buyer_name" placeholder="<spring:message code="收货人" />">
			                		    </div>
			                		</div>
			                	    <div class="col-md-4">
			                	    	<%-- 收货人电话 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="buyer_phone"><spring:message code="收货人电话" /></label>
			                		        <input type="text" name="buyer_phone" class="form-control" id="buyer_phone" placeholder="<spring:message code="收货人电话" />">
			                		    </div>
			                		</div>
			                	</div>
			                	<div class="row">
			                	    <div class="col-md-4">
			                	    	<%-- 收货人手机号码 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="buyer_mobile"><spring:message code="收货人手机号码" /></label>
			                		        <input type="text" name="buyer_mobile" class="form-control" id="buyer_mobile" placeholder="<spring:message code="收货人手机号码" />">
			                		    </div>
			                		</div>
			                	    <div class="col-md-4">
			                	    	<%-- 送货方式 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="send_type"><spring:message code="送货方式" /></label>
			                		        <input type="text" name="send_type" class="form-control" id="send_type" placeholder="<spring:message code="送货方式" />">
			                		    </div>
			                		</div>
			                	    <div class="col-md-4">
			                	    	<%-- 送货单号 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="send_no"><spring:message code="送货单号" /></label>
			                		        <input type="text" name="send_no" class="form-control" id="send_no" placeholder="<spring:message code="送货单号" />">
			                		    </div>
			                		</div>
								</div>
								<div class="row">
			                	    <div class="col-md-4">
			                	    	<%-- 送货时间 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="send_time"><spring:message code="送货时间" /></label>
			                		        <input type="text" name="send_time" class="form-control" id="send_time" placeholder="<spring:message code="送货时间" />">
			                		    </div>
			                		</div>
			                	    <div class="col-md-4">
			                	    	<%-- 合计运费 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="freight"><spring:message code="合计运费" /></label>
			                		        <input type="text" name="freight" class="form-control" id="freight" placeholder="<spring:message code="合计运费" />">
			                		    </div>
			                		</div>
			                	    <div class="col-md-4">
			                	    	<%-- 是否需要发票 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="invoice_need"><spring:message code="是否需要发票" /></label>
											<select id="invoice_need" class="form-control" name="invoice_need" >
												<option value="" selected="selected">请选择...</option>
												<option value="0" >不需要</option>
												<option value="1" >需要</option>
											</select>
			                		    </div>
			                		</div>
			                	</div>
			                	<div class="row">
			                	    <div class="col-md-4">
			                	    	<%-- 发票抬头 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="invoice_title"><spring:message code="发票抬头" /></label>
			                		        <input type="text" name="invoice_title" class="form-control" id="invoice_title" placeholder="<spring:message code="发票抬头" />">
			                		    </div>
			                		</div>
			                	    <div class="col-md-4">
			                	    	<%-- 支付方式 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="pay_type"><spring:message code="支付方式" /></label>
											<select id="pay_type" class="form-control" name="pay_type" >
												<option value="" selected="selected">请选择...</option>
												<option value="0" >支付宝</option>
												<option value="1" >微信</option>
												<option value="2" >现金交易</option>
											</select>
			                		    </div>
			                		</div>
			                	    <div class="col-md-4">
			                	    	<%-- 买方付款时间 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="buyer_pay_time"><spring:message code="买方付款时间" /></label>
			                		        <input type="text" name="buyer_pay_time" class="form-control" id="buyer_pay_time" placeholder="<spring:message code="买方付款时间" />">
			                		    </div>
			                		</div>
								</div>
								<div class="row">
			                	    <div class="col-md-4">
			                	    	<%-- 交易发起时间 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="trad_time"><spring:message code="交易发起时间" /></label>
			                		        <input type="text" name="trad_time" class="form-control" id="trad_time" placeholder="<spring:message code="交易发起时间" />">
			                		    </div>
			                		</div>
			                	    <div class="col-md-4">
			                	    	<%-- 交易完成时间 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="trad_finish_time"><spring:message code="交易完成时间" /></label>
			                		        <input type="text" name="trad_finish_time" class="form-control" id="trad_finish_time" placeholder="<spring:message code="交易完成时间" />">
			                		    </div>
			                		</div>
			                	    <div class="col-md-4">
			                	    	<%-- 最后更新时间 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="update_time"><spring:message code="最后更新时间" /></label>
			                		        <input type="text" name="update_time" class="form-control" id="update_time" placeholder="<spring:message code="最后更新时间" />">
			                		    </div>
			                		</div>
			                	</div>
			                	<div class="row">
			                	    <div class="col-md-4">
			                	    	<%-- 卖方发货时间 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="seller_deliver_time"><spring:message code="卖方发货时间" /></label>
			                		        <input type="text" name="seller_deliver_time" class="form-control" id="seller_deliver_time" placeholder="<spring:message code="卖方发货时间" />">
			                		    </div>
			                		</div>
			                	    <div class="col-md-4">
			                	    	<%-- 买方确认收货时间 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="buyer_confirm_time"><spring:message code="买方确认收货时间" /></label>
			                		        <input type="text" name="buyer_confirm_time" class="form-control" id="buyer_confirm_time" placeholder="<spring:message code="买方确认收货时间" />">
			                		    </div>
			                		</div>
			                	    <div class="col-md-4">
			                	    	<%-- 买家是否已删除 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="buyer_del"><spring:message code="买家是否已删除" /></label>
											<select id="buyer_del" class="form-control" name="buyer_del" >
												<option value="" selected="selected">请选择...</option>
												<option value="0" >否</option>
												<option value="1" >是</option>
											</select>
			                		    </div>
			                		</div>
								</div>
								<div class="row">
			                	    <div class="col-md-4">
			                	    	<%-- 卖家是否已删除 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="seller_del"><spring:message code="卖家是否已删除" /></label>
											<select id="seller_del" class="form-control" name="seller_del" >
												<option value="" selected="selected">请选择...</option>
												<option value="0" >否</option>
												<option value="1" >是</option>
											</select>
			                		    </div>
			                		</div>
			                	    <div class="col-md-4">
			                	    	<%-- 买家删除时间 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="buyer_del_time"><spring:message code="买家删除时间" /></label>
			                		        <input type="text" name="buyer_del_time" class="form-control" id="buyer_del_time" placeholder="<spring:message code="买家删除时间" />">
			                		    </div>
			                		</div>
			                	    <div class="col-md-4">
			                	    	<%-- 卖家删除时间 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="seller_del_time"><spring:message code="卖家删除时间" /></label>
			                		        <input type="text" name="seller_del_time" class="form-control" id="seller_del_time" placeholder="<spring:message code="卖家删除时间" />">
			                		    </div>
			                		</div>
			                	</div>
			                	<div class="row">
			                	    <div class="col-md-4">
			                	    	<%-- 买家打分 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="buyer_score"><spring:message code="买家打分" /></label>
			                		        <input type="text" name="buyer_score" class="form-control" id="buyer_score" placeholder="<spring:message code="买家打分" />">
			                		    </div>
			                		</div>
			                	    <div class="col-md-4">
			                	    	<%-- 卖家打分 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="seller_score"><spring:message code="卖家打分" /></label>
			                		        <input type="text" name="seller_score" class="form-control" id="seller_score" placeholder="<spring:message code="卖家打分" />">
			                		    </div>
			                		</div>
			                	    <div class="col-md-4">
			                	    	<%-- 交易状态 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="status"><spring:message code="交易状态" /></label>
											<select id="status" class="form-control" name="status" >
												<option value="" selected="selected">请选择...</option>
												<option value="0" >买方待付款</option>
												<option value="1" >卖方确认订单</option>
												<option value="2" >买方已付款</option>
												<option value="3" >卖方已发货</option>
												<option value="4" >交易完成/买方确认收货</option>
												<option value="5" >交易取消</option>
												<option value="6" >交易关闭</option>
											</select>
			                		    </div>
			                		</div>
								</div>
								<div class="row">
			                	    <div class="col-md-4">
			                	    	<%-- 省份编码 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="province_code"><spring:message code="省份编码" /></label>
											<select id="province_code" class="form-control" name="province_code">
												<option value="" selected="selected">请选择...</option>
												<c:forEach items="${provinces}" var="pro">
													<option value="${pro.province_code}">${pro.province_name}</option>
												</c:forEach>
											</select>
			                		    </div>
			                		</div>
			                	    <div class="col-md-4">
			                	    	<%-- 城市编码 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="city_code"><spring:message code="城市编码" /></label>
											<select id="city_code" class="form-control" name="city_code">

											</select>
			                		    </div>
			                		</div>
			                	    <div class="col-md-4">
			                	    	<%-- 城区ID --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="town_code"><spring:message code="城区ID" /></label>
											<select id="town_code" class="form-control" name="town_code">

											</select>
			                		    </div>
			                		</div>
			                	</div>
			                	<div class="row">
			                	    <div class="col-md-4">
			                	    	<%-- 详细地址 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="address"><spring:message code="详细地址" /></label>
			                		        <input type="text" name="address" class="form-control" id="address" placeholder="<spring:message code="详细地址" />">
			                		    </div>
			                		</div>
			                	    <div class="col-md-4">
			                	    	<%-- 买家评价 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="buyer_advise"><spring:message code="买家评价" /></label>
			                		        <input type="text" name="buyer_advise" class="form-control" id="buyer_advise" placeholder="<spring:message code="买家评价" />">
			                		    </div>
			                		</div>
			                	    <div class="col-md-4">
			                	    	<%-- 备注 --%>
			                		    <div class="form-group">
			                		        <label class="control-label" for="remark"><spring:message code="备注" /></label>
			                		        <input type="text" name="remark" class="form-control" id="remark" placeholder="<spring:message code="备注" />">
			                		    </div>
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
				    	<a href="${ctx}/mgr/product/order/listmgr?module_id=${requestScope.module_id}" class="btn btn-primary btn-sm">
					    	<i class="glyphicon glyphicon-chevron-left"></i>
					    	<spring:message code="btn_back" />
					    </a>
				    </c:if>
				    <c:if test="${param.page eq 'query' }">
				    	<a href="${ctx}/mgr/product/order/query?module_id=${requestScope.module_id}" class="btn btn-primary btn-sm">
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
