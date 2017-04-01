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

<article class="videocenter">
    <section class="main">
        <a href="javascript:void(0);" class="close iconfont" onclick="$(this).siblings('.cover').fadeIn();$('#videocontainer[0]').stop();">&#xe605;</a>
        <div class="video">
            <video src="video.html" controls id="videocontainer"></video>
        </div>
        <c:choose>
            <c:when test="${videocenter != null && fn:length(videocenter) > 0}">
                <div class="cover" style="background-image: url(video.html);" onclick="playvideo('${videocenter[0].video_file_url}');">
                    <div class="btn">
                        <i class="iconfont">&#xe78a;</i>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <div class="cover" style="background-image: url(video.html);" onclick="playvideo('');">
                    <div class="btn">
                        <i class="iconfont">&#xe78a;</i>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
    </section>
    <section class="content">
        <div class="maxsize clear posr">
            <div class="arrows">
                <div class="arrow prev"></div>
                <div class="arrow next"></div>
            </div>
            <div class="con">
                <ul class="slider light ">

                    <c:forEach items="${videocenter}" var="video" >
                        <div class="item">
                            <a href="javascript:void(0);" onclick="playvideo('${ctx}/${video.video_file_url}');">
                                <div class="face" style="background-image: url(${ctx}/${video.img_file_url});"></div>
                                <p>${video.title}</p>
                            </a>
                        </div>
                    </c:forEach>

                </ul>
            </div>
        </div>
    </section>
</article>

<script type="text/javascript" src="${ctx}/js/system/check.js"></script>
<%@include file="/WEB-INF/mgr/jsp/main/footer1.jsp"%>
</body>
</html>
