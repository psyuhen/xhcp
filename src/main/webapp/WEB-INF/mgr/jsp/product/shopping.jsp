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
            <li class="active">
                <i class="iconfont">&#xe626;</i>
                <p>购物车结算</p>
            </li>
            <li>
                <i class="iconfont">&#xe63b;</i>
                <p>填写订单信息</p>
            </li>
            <li>
                <i class="iconfont">&#xe601;</i>
                <p>支付/确认订单</p>
            </li>
        </ul>
    </section>
    <section class="gouwuche" >
        <div class="maxsize">
            <h1>您购物车中的商品</h1>
            <table responseTable="1" condition="$(window).width()<=860">
                <tr>
                    <th>商品清单</th>
                    <th>单价(元)</th>
                    <th>数量</th>
                    <th>小计(元)</th>
                    <th>操作</th>
                </tr>

                <tr><td>您的购物车没有商品...</td></tr>


            </table>


            <div class="total tar">
            </div>

        </div>
    </section>
</article>

<%@include file="/WEB-INF/mgr/jsp/main/footer1.jsp"%>
</body>
</html>
