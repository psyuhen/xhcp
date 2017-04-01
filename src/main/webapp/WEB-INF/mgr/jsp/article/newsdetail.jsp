<%--
  Created by IntelliJ IDEA.
  User: sam.pan
  Date: 2017/3/16
  Time: 10:02
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

<div class="nd">
    <div class="maxsize">
        <div class="page">
            <div class="title">
                <h1>${articleNew.title}</h1>
                <small>${articleNew.article_date}</small>
            </div>
            <div class="cont">
                <div>
                    ${articleNew.contents}
                </div>
                <button class="back" onClick="window.location.href=ctx + '/article/allnews.html'">返回列表</button>
            </div>
            <ul class="list">
                <c:if test="${articlePAndN != null && fn:length(articlePAndN) == 1}">
                    <li>
                        <a href="${ctx}/article/news-${articlePAndN[0].article_id}.html">
                        <c:choose>
                            <c:when test="${articlePAndN[0].article_id gt articleNew.article_id}">
                                <b>下一篇：</b>
                            </c:when>
                            <c:otherwise>
                                <b>上一篇：</b>
                            </c:otherwise>
                        </c:choose>
                        ${articlePAndN[0].title}</a>
                    </li>
                </c:if>
                <c:if test="${articlePAndN != null && fn:length(articlePAndN) == 2}">
                    <li><a href="${ctx}/article/news-${articlePAndN[0].article_id}.html"><b>上一篇：</b>${articlePAndN[0].title}</a></li>
                    <li><a href="${ctx}/article/news-${articlePAndN[1].article_id}.html"><b>下一篇：</b>${articlePAndN[1].title}</a></li>
                </c:if>
            </ul>
        </div>
    </div>

<%@include file="/WEB-INF/mgr/jsp/main/footer1.jsp"%>
</body>
</html>
