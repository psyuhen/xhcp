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

<div class="member">
    <div class="my">
        <div class="top">
            <div class="maxsize">
                <div class="row clear">
                    <div class="md3">
                        <h1>会员中心</h1>
                    </div>
                    <div class="md9">
                        <h1>全部订单</h1>
                    </div>
                </div>
            </div>
        </div>
        <div class="bottom">
            <div class="maxsize">
                <div class="row clear">
                    <div class="md3">
                        <ul>
                            <li class="active"><a href="${ctx}/order.html"><i class="iconfont"></i>全部订单</a></li>
                            <li><a href="usercenter.html"><i class="iconfont"></i>账户信息</a></li>
                            <li><a href="${ctx}/address.html"><i class="iconfont"></i>地址管理</a></li>
                        </ul>
                    </div>
                    <div class="md9">
                        <div class="table shuTable normal hengTable" responsetable="true"><div class="tr tr0"><div class="th th0"><span>订单号</span></div><div class="th th1"><span>订单金额</span></div><div class="th th2"><span>订单状态</span></div><div class="th th3"><span>操作</span></div></div></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="${ctx}/js/system/check.js"></script>
<%@include file="/WEB-INF/mgr/jsp/main/footer1.jsp"%>
</body>
</html>
