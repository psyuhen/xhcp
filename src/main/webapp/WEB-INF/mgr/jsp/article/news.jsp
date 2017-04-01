<%--
  Created by IntelliJ IDEA.
  User: sam.pan
  Date: 2017/3/16
  Time: 22:03
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

<article class="news">
    <section class="title">
        <div class="maxsize clear">
            <h1>企业新闻</h1>
            <div class="top">
                <a href="javascript:void(0);" class="arrow prev iconfont">&#xe6b2;</a>
            </div>
            <div class="select">
                <div class="inb">
                    <select name="year">
                        <option value="">请选择年份</option>
                        <c:forEach items="${newsYears}" var="var">
                            <option value="${var}">${var}年</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="inb">
                    <select name="month">
                        <option value="">请选择月份</option>
                        <option value="12">12月</option>
                        <option value="11">11月</option>
                        <option value="10">10月</option>
                        <option value="09">9月</option>
                        <option value="08">8月</option>
                        <option value="07">7月</option>
                        <option value="06">6月</option>
                        <option value="05">5月</option>
                        <option value="04">4月</option>
                        <option value="03">3月</option>
                        <option value="02">2月</option>
                        <option value="01">1月</option>
                    </select>
                </div>
            </div>
        </div>
    </section>
    <section class="list light ">
        <dl firstactive="dd">

        </dl>
    </section>
    <section class="end">
        <div class="maxsize posr">
            <div class="bottom">
                <a href="javascript:void(0);" class="arrow iconfont next">&#xe6b1;</a>
            </div>
        </div>
    </section>
</article>

<script type="text/javascript">
    $(function() {
        $('.select select[name=year],.select select[name=month]').change(function() {

            renderlist();
        });
        $('a.arrow').click(function() {
            $eq = $('.news .list .active').index();
            $max = $('.news .list dd').size() - 1;
            $(this).hasClass('prev') ? $eq-=1 : $eq+=1;
            $eq<=0 ? $eq=0 : null;
            $eq >= $max ? $eq = $max : null;
            $('.news .list dd').removeClass('active').eq($eq).addClass('active');
        });
    });



    renderlist();
</script>

<%@include file="/WEB-INF/mgr/jsp/main/footer1.jsp"%>
</body>
</html>
