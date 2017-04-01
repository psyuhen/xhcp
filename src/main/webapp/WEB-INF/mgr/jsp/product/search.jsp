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

<article class="sr">
    <section class="top" style="background-image: url(themes/default/images/search-top.jpg);"></section>
    <section class="bottom">
        <div class="maxsize">
            <div class="win">
                <div class="filter">
                    搜索结果：<a href="javascript:void(0);" class="active">新闻（）</a> | <a href="javascript:void(0);">产品（）</a>
                </div>
                <div class="pages" firstactive=".page">
                    <div class="page fadeInUp animated news">
                        <div class="list">


                        </div>
                    </div>
                    <div class="page fadeInUp animated pros">
                        <div class="list clear">


                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</article>
<script>
    $(function() {
        $('.sr .filter a').click(function() {
            $eq = $(this).index();
            $(this).addClass('active').siblings().removeClass('active');
            $('.sr .pages .page').hide().eq($eq).show();
        });
    });
</script>

<%@include file="/WEB-INF/mgr/jsp/main/footer1.jsp"%>
</body>
</html>
