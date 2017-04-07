<%--
  Created by IntelliJ IDEA.
  User: sam.pan
  Date: 2017/3/13
  Time: 22:18
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
<article class="pros clear">
    <section class="top">
        <div class="maxsize posr">
            <div class="text tac">
                <h1>产品中心</h1>
                <h3>PRODUCTS</h3>
                <p>专注燕窝19年</p>
            </div>
            <%-- 各种系列数据 --%>
            <div class="filter clear">
                <dl>
                    <c:forEach items="${RootClassify}" var="rootcls">
                        <dd>
                            <div class="box hover">
                                <a href="${ctx}/products-top-${rootcls.classify_id}.html#sort">
                                    <i class="iconfont"></i>
                                    <p>${rootcls.name}</p>
                                </a>
                                <i class="more"></i>
                                <div class="sel_box animated fadeInDown">
                                    <ul>
                                        <c:forEach items="${SubClassify}" var="subcls">
                                            <c:if test="${subcls.pcls_id == rootcls.classify_id}">
                                                <li><a href="${ctx}/products-list-${subcls.classify_id}.html#sort">${subcls.name}</a></li>
                                            </c:if>
                                        </c:forEach>
                                    </ul>
                                </div>
                            </div>
                        </dd>
                    </c:forEach>
                </dl>
            </div>
        </div>
    </section>
    <section class="list" id="sort">
        <div class="maxsize">
            <%-- 导航条 --%>
            <div class="breadcrumb animateall">
                <a href="javascript:void(0);">产品</a>
                <c:if test="${curNavClassify != null}" >
                    - <a href="javascript:void(0);">${curNavClassify}</a>
                </c:if>
                <c:if test="${nextNavClassify != null}" >
                    - <a href="javascript:void(0);">${nextNavClassify}</a>
                </c:if>
            </div>

            <%-- 动态数据 --%>
            <div class="nmg clear animateall" eachdelay=".md4" delaytime="150" effect=".md4" reach="fadeInUp">

                <c:forEach items="${merchInfos}" var="merch">
                    <div class="md4 fadeInUp animated" style="animation-delay: 0s; visibility: visible;">
                        <a href="products-${merch.merch_id}.html">
                            <c:choose>
                                <c:when test="${merch.merch_photo != null}">
                                    <div class="face" style="background-image: url(${ctx}/${merch.merch_photo})"></div>
                                </c:when>
                                <c:otherwise>
                                    <div class="face" style="background-image: url(${ctx})"></div>
                                </c:otherwise>
                            </c:choose>
                            <div class="text">
                                <h2>${merch.name}</h2>
                                <p>${merch.desc}</p>
                                <div class="price">
                                    <font>￥</font><strong>${merch.price}</strong>&nbsp;&nbsp;&nbsp;<input type="button" value="立即购买">
                                </div>
                            </div>
                        </a>
                    </div>
                </c:forEach>

            </div>
        </div>
    </section>
</article>

<%@include file="/WEB-INF/mgr/jsp/main/footer1.jsp"%>
</body>
</html>
