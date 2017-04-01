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
                        <h1>账户信息</h1>
                    </div>
                </div>
            </div>
        </div>
        <div class="bottom">
            <div class="maxsize">
                <div class="row clear">
                    <div class="md3">
                        <ul>
                            <li><a href="${ctx}/order.html"><i class="iconfont"></i>全部订单</a></li>
                            <li class="active"><a href="${ctx}/usercenter.html"><i class="iconfont"></i>账户信息</a></li>
                            <li><a href="${ctx}/address.html"><i class="iconfont"></i>地址管理</a></li>
                        </ul>
                    </div>
                    <div class="md9">
                        <div class="con row clear">
                            <div class="md6">
                                <p>用户：${Front_Account.account_id}</p>
                            </div>


                        </div>
                        <div class="con row clear">
                            <div class="md12">
                                <h1>修改密码：</h1>
                            </div>
                            <div class="md12">
                                <p>旧密码：<input type="password" name="old_password" id="old_password"></p>
                            </div>
                            <div class="md6">
                                <p>新密码：<input type="password" name="account_password" id="new_password"></p>
                            </div>
                            <div class="md6">
                                <p>确认新密码：<input type="password" name="comfirm_password" id="comfirm_password"></p>
                            </div>
                            <div class="md12">
                                <p><input type="button" onclick="change_password()" value="确认"></p>
                                <p id="response_info"></p>
                            </div>
                        </div>
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
