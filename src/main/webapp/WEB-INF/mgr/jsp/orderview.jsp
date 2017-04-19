<%--
  Created by IntelliJ IDEA.
  User: sam.pan
  Date: 2017/3/23
  Time: 15:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta name="format-detection" content="telephone=no" />
    <title>佰瑞林陈皮,佰瑞林,陈皮</title>
    <meta name="author" content="sam.pan">
    <meta name="copyright" content="佰瑞林">
    <meta name="keywords" content="佰瑞林,佰瑞林陈皮,陈皮">
    <meta name="description" content="佰瑞林陈皮专注陈皮几千年,订购热线:xxxx" />
    <%@include file="/WEB-INF/mgr/jsp/inc/import1.jsp" %>
</head>
<body>
<%@include file="/WEB-INF/mgr/jsp/main/header.jsp"%>

<article class="od">
    <section class="maxsize">
        <div class="block">
            <div class="title">
                我的订单
            </div>
            <div class="cont">
                <table>
                    <tbody><tr>
                        <td>收货人： ${order.buyer_name}</td>
                        <td>收货地址：${order.province_name}${order.city_name}${order.town_name}${order.address}</td>
                    </tr>
                    <tr>
                        <td>联系电话：${order.buyer_mobile}</td>
                        <td>付款状态：
                            <c:choose>
                                <c:when test="${order.status eq '0'}">
                                    未付款
                                    <input type="button" onclick="window.open('https://www.alipay.com/cooperate/gateway.do?_input_charset=utf-8&amp;agent=C4335319945672464113&amp;defaultbank=CMB&amp;logistics_fee=0&amp;logistics_payment=BUYER_PAY_AFTER_RECEIVE&amp;logistics_type=EXPRESS&amp;notify_url=http%3A%2F%2Fshop.yanzhiwu.com%2Frespond.php%3Fcode%3Dalipay_cmb&amp;out_trade_no=2017041152699658&amp;partner=2088011586071179&amp;payment_type=1&amp;price=238.00&amp;quantity=1&amp;return_url=http%3A%2F%2Fshop.yanzhiwu.com%2Frespond.php%3Fcode%3Dalipay_cmb&amp;seller_email=yanwozhiwu%40sina.com&amp;service=create_direct_pay_by_user&amp;subject=2017041152699&amp;sign=d842e1bcf8cb44b1377d720f7d4d5d48&amp;sign_type=MD5')" value="立即支付">
                                </c:when>
                                <c:when test="${order.status eq '1'}" >
                                    <span>卖方确认订单</span>
                                </c:when>
                                <c:when test="${order.status eq '2'}" >
                                    <span>买方已付款</span>
                                </c:when>
                                <c:when test="${order.status eq '3'}" >
                                    <span>卖方已发货</span>
                                </c:when>
                                <c:when test="${order.status eq '4'}" >
                                    <span>交易完成/买方确认收货</span>
                                </c:when>
                                <c:when test="${order.status eq '5'}" >
                                    <span>交易取消</span>
                                </c:when>
                                <c:when test="${order.status eq '6'}" >
                                    <span>交易关闭</span>
                                </c:when>
                            </c:choose>

                        </td>
                    </tr>

                    </tbody>
                </table>
                <table class="result-info2">
                </table>
            </div>
        </div>
        <div class="block">
            <table class="order">
                <tbody>
                    <tr>
                        <th>商品清单</th>
                        <th>单价(元)</th>
                        <th>数量</th>
                        <th>小计(元)</th>
                    </tr>

                    <c:forEach items="${details}" var="dtl">
                        <tr>
                            <td>
                                <a href="${ctx}/products-${dtl.merch_id}.html" target="_blank">
                                    <p>孕智款单碗装</p>
                                </a>
                            </td>
                            <td>￥${dtl.price}元</td>
                            <td>${dtl.amount}</td>
                            <td>￥${dtl.amount * dtl.price}元</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </section>
</article>

<script type="text/javascript" src="${ctx}/js/system/check.js"></script>
<%@include file="/WEB-INF/mgr/jsp/main/footer1.jsp"%>
</body>
</html>
