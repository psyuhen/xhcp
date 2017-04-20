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
        <div class="win">
            <div class="title">
                <h1>会员登录</h1>
                <a href="register.html">会员注册 <i class="iconfont">&#xe635;</i></a>
            </div>
            <div class="inputs">
                <form onSubmit="return check_login()">
                    <input type="hidden" id="blackUrl" name="blackUrl" value="${blackUrl}"/>
                    <div class="line error">
                        <input type="text" placeholder="手机号/用户" id="username_login">
                        <p class="info"><i class="iconfont" id="username_login_icon">&#xe6be;</i><font id="username_login_info"></font></p>
                    </div>
                    <div class="line">
                        <input type="password" placeholder="密码" id="password_login">
                        <p class="info"><i class="iconfont" id="password_login_icon">&#xe6be;</i><font id="password_login_info"></font></p>
                    </div>
                    <div class="line">
                        <input type="text" name="validate_code" id="validate_code" placeholder="验证码" style="width: 80%;"/>
                        <p class="info"><i class="iconfont" id="validate_code_icon">&#xe6be;</i><font id="validate_code_info"></font></p>
                        <img src="${ctx}/validatecode" title="看不清，请点我"  id="validateCode" style="cursor:pointer;width:100px;height: 40px;float: right" onclick="refreshCode(this);"/>
                    </div>
                    <div class="line">
                        <a href="${ctx}/forget.html" class="forget">忘记密码？</a>
                        <%--<h5>使用以下帐号登录：</h5>--%>
                        <h5></h5>
                        <ul class="sns">
                            <%--<li class="wechat"><a href="https://open.weixin.qq.com/connect/qrconnect?appid=wx0fe8337735a0afb5&redirect_uri=http://shop.yanzhiwu.com/callback_wechat.php?shopping&
response_type=code&scope=snsapi_login&state=STATE#wechat_redirect"><i class="iconfont">&#xe600;</i></a></li>
                            <li class="weibo"><a href="https://api.weibo.com/oauth2/authorize?client_id=3047596405&amp;redirect_uri=http://shop.yanzhiwu.com/callback.php?shopping&amp;response_type=code"><i class="iconfont">&#xe661;</i></a></li>
                            <li class="qq"><a href="https://graph.qq.com/oauth2.0/authorize?response_type=code&client_id=101217306&redirect_uri=http%3A%2F%2Fshop.yanzhiwu.com%2Fcallback_qq.php?shopping"><i class="iconfont">&#xe60f;</i></a></li>
                        --%></ul>
                    </div>
                    <input type="submit" class="submitbtn" value="登录">
                </form>
            </div>
        </div>
    </section>
</article>

<script type="text/javascript" src="${ctx}/js/system/check.js"></script>
<%@include file="/WEB-INF/mgr/jsp/main/footer1.jsp"%>
</body>
</html>
