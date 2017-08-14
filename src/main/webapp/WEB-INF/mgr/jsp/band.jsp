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

<article class="pinpai">

    <section class="top" style="background-image: url(upfiles/onepage/1484345848567383522.jpg);">
        <div class="text tac" reach="fadeInUp" effect="h1,h3,p" eachdelay="h1,h3,p" delaytime="50">
            <h1 style="animation-delay: 0s; visibility: visible;" class="fadeInUp animated">公司概况</h1>
            <h3 style="animation-delay: 0.05s; visibility: visible;" class="fadeInUp animated">COMPANY PROFILE</h3>
            <p style="animation-delay: 0.1s; visibility: visible;" class="fadeInUp animated">厦门燕之屋生物工程发展有限公司</p>
        </div>
    </section>
    <section class="cont">
        <div class="maxsize clear">
            <c:choose>
            	<c:when test="${store != null }">
            		${store.contents }
            	</c:when>
            	<c:otherwise>
            		<article>
            			<h3>正在建设中...</h3>
            		</article>
            	</c:otherwise>
            </c:choose>
        </div>

    </section>
</article>

<script type="text/javascript" src="${ctx}/js/system/check_address.js"></script>
<%@include file="/WEB-INF/mgr/jsp/main/footer1.jsp"%>
</body>
</html>
