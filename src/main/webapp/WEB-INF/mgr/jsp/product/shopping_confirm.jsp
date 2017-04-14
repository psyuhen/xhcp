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

<article class="buyliucheng">
    <section class="steps tac">
        <ul class="inb clear">
            <li>
                <i class="iconfont"></i>
                <p>购物车结算</p>
            </li>
            <li>
                <i class="iconfont"></i>
                <p>填写订单信息</p>
            </li>
            <li class="active">
                <i class="iconfont"></i>
                <p>支付/确认订单</p>
            </li>
        </ul>
    </section>
    <section class="confirm">
        <div class="maxsize">
            <div class="title">支付 / 确认订单</div>
            <div class="cont">
                <p>
                    订单号：${OrderInfo.order_id} <br>
                    金额：￥${OrderInfo.amount_money}元<br>
                    支付方式：
                    <c:choose>
                        <c:when test="${OrderInfo.pay_type eq '0'}">
                            <img src="${ctx}/css/themes/default/images/zhifubao.jpg">
                        </c:when>
                        <c:when test="${OrderInfo.pay_type eq '1'}">
                            <img src="${ctx}/css/themes/default/images/weixin.jpg">
                        </c:when>
                    </c:choose>
                    <br>
                    ${OrderInfo.buyer_name} / ${OrderInfo.buyer_mobile} /${OrderInfo.province_name}${OrderInfo.city_name}${OrderInfo.town_name}${OrderInfo.address}
                </p>
            </div>
            <div class="tar">
                <input class="common_btn" type="button" onclick="window.open('https://mapi.alipay.com/gateway.do?_input_charset=utf-8&amp;extend_param=isv%5Esh22&amp;logistics_fee=0&amp;logistics_payment=BUYER_PAY_AFTER_RECEIVE&amp;logistics_type=EXPRESS&amp;notify_url=http%3A%2F%2Fshop.yanzhiwu.com%2Frespond.php%3Fcode%3Dalipay&amp;out_trade_no=2017041093198655&amp;partner=2088011586071179&amp;payment_type=1&amp;price=3094.00&amp;quantity=1&amp;return_url=http%3A%2F%2Fshop.yanzhiwu.com%2Frespond.php%3Fcode%3Dalipay&amp;seller_email=yanwozhiwu%40sina.com&amp;service=create_direct_pay_by_user&amp;subject=2017041093198&amp;sign=ce65a9412229ef1003c6364b73a8b1ba&amp;sign_type=MD5')" value="立即支付">				</div>
        </div>
    </section>
</article>
<script src="${ctx}/css/themes/default/script/shopping.js"></script>
<%@include file="/WEB-INF/mgr/jsp/main/footer1.jsp"%>
</body>
</html>
