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

<article class="login">
    <section class="top" style="background-image: url(${ctx}/css/themes/default/images/login-top-bg.jpg);"></section>
    <section class="form">
        <c:choose>
            <c:when test="${step1 eq 'true'}" >
                <div class="win">
                    <div class="title">
                        <h1>找回密码 - 步骤1</h1>
                    </div>
                    <div class="inputs">
                        <form id="myform" name="myform" action="${ctx}/forget.do" method="post" onsubmit="return forget('step1');">
                            <div class="line">
                                <input type="text" id="mobile" name="mobile" value="" placeholder="请输入手机号码">
                                <p class="info" id="mobile_phone_info" >${mobileError}</p>
                            </div>
                                <%--<div class="line">
                                    <input type="text" id="checkcode" name="smscode" placeholder="请输入短信验证码">
                                    <p class="info" id="checkcode_info"></p>
                                    <a id="getcode_forget">点击获取验证码</a>
                                </div>--%>
                            <div class="line">
                                <input type="text" name="validate_code" id="validate_code" placeholder="请输入验证码" style="width: 80%"/>
                                <p class="info" id="checkcode_info" >${validateError}</p>
                                <img src="${ctx}/validatecode" title="看不清，请点我"  id="validateCode" style="cursor:pointer;width:100px;height: 40px;float:right;" onclick="refreshCode(this);"/>
                            </div>
                            <input type="submit" class="submitbtn" value="下一步" >
                        </form>
                    </div>
                </div>
            </c:when>

            <c:when test="${step2 eq 'true'}">
                <div class="win">
                    <div class="title">
                        <h1>找回密码 - 步骤2</h1>
                    </div>
                    <div class="inputs">
                        <form id="myform" name="myform" action="${ctx}/forget.do?step=2" method="post" onsubmit="return forget('step2');">
                            <div class="line" style="display: none;">
                                <input type="hidden" id="mobile" name="mobile" value="${mobile}">
                            </div>
                            <div class="line">
                                <input type="password" id="account_password" name="account_password" placeholder="请输入你的密码" onblur="check_password()" />
                                <p class="info" id="password_info">${pwdError}</p>
                            </div>
                            <div class="line">
                                <input type="password" id="password_confirm" placeholder="确认密码" name="old_password">
                                <p class="info" id="password_confirm_info">${confirmPwdError}</p>
                            </div>
                            <div class="line">
                                <input type="text" name="validate_code" id="validate_code" placeholder="请输入验证码" style="width: 80%"/>
                                <p class="info" id="checkcode_info" >${validateError}</p>
                                <img src="${ctx}/validatecode" title="看不清，请点我"  id="validateCode" style="cursor:pointer;width:100px;height: 40px;float:right;" onclick="refreshCode(this);"/>
                            </div>
                            <input type="submit" class="submitbtn" value="下一步">
                        </form>
                    </div>
                </div>
            </c:when>
        </c:choose>


    </section>
</article>



<script type="text/javascript" src="${ctx}/js/system/check.js"></script>
<%@include file="/WEB-INF/mgr/jsp/main/footer1.jsp"%>
</body>
</html>
