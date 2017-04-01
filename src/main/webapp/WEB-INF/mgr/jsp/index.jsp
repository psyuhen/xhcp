<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
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

<article class="index">
    <%-- 图片展示区，多个图片展示 --%>
    <section class="banner">
        <ul class="slider" animate="fadeInUp,fadeInDown,fadeInLeft,fadeInRight" effect=".text">
            <%-- 图片展示区，需要动态加载 --%>
            <c:forEach items="${photosList}" var="photo" >
                <li style="background-image: url(${photo.file_path}${photo.name})">
                </li>
            </c:forEach>
        </ul>
        <div class="arrows">
            <div class="arrow prev"></div>
            <div class="arrow next"></div>
        </div>
    </section>

    <%-- 图片展示区，产品图片展示 --%>
    <section class="product clear animateall" reach="fadeInUpBig" effect=".anim" eachdelay=".anim" delaytime="150" >
        <div class="md6 l anim">
            <div class="item" style="background-image: url(upfiles/onepage/1484089890205565745.jpg);" >
                <div class="text">
                    <h1>尊享款碗燕</h1>
                    <h3>￥4536.00</h3>
                    <a href="products-1.html">点击购买</a>
                </div>
            </div>
        </div>
        <div class="md6 r">
            <div class="t anim">
                <div class="item" style="background-image: url(upfiles/onepage/1482345925995272712.jpg);">
                    <div class="text">
                        <h1>孕智款碗燕</h1>
                        <h3>￥2180.00</h3>
                        <a href="products-13.html">点击购买</a>
                    </div>
                </div>
            </div>
            <div class="b anim">
                <div class="item" style="background-image: url(upfiles/onepage/1482345925426456266.jpg);">
                    <div class="text">
                        <h1>经典款碗燕</h1>
                        <h3>￥3180.00</h3>
                        <a href="products-18.html">点击购买</a>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <%-- 视频展示区 可放视频介绍陈皮或店铺 --%>
    <section class="video indexvideo">
        <video loop="loop">
            <source type="video/mp4"
                    -webkit-playsinline="true" webkit-playsinline="true" src="${ctx}/${indexvideo.file_path}${indexvideo.name}"
            />
        </video>
        <div class="playbtn">
            <i class="iconfont">&#xe78a;</i>
            <h2 class="num" breakas="span" eachdelay="span" delaytime="150" reach="fadeInUp" effect="span">yan palace video</h2>
        </div>
    </section>

    <%-- 新闻资讯，左手边设置图片固定，右手可摆放行业行文或者是企业新闻 --%>
    <section class="news" style="background: #f6f6f6;">
        <div class="bg posa"><img src="${ctx}/${indexnewsphoto.file_path}${indexnewsphoto.name}"></div>
        <div class="maxsize posr">
            <div class="arrows">
                <a href="javascript:void(0);" class="prev arrow"><i class="iconfont">&#xe83a;</i></a>
                <a href="javascript:void(0);" class="next arrow"><i class="iconfont">&#xe839;</i></a>
            </div>
            <div class="list">
                <h1>新闻资讯</h1>
                <ul eachdelay="li" delaytime="150" effect="li" reach="fadeInUp">
                    <c:forEach items="${newArticleList}" var="var">
                        <li>
                            <a href="${ctx}/article/news-${var.article_id}.html">
                                <h2>${var.title}</h2>
                                <span>${var.article_date}</span>
                                <p id="artice_id_${var.article_id}">${var.contents}</p>
                            </a>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <script>
                //$jsonFormat = [{"url":"news-124.html","title":"\u91cd\u78c5 | \u71d5\u4e4b\u5c4b\u559c\u83b7\u201c\u4e2d\u56fd\u54c1\u724c\u521b\u65b0\u5956\u201d","date":"2017-02-28","digest":"2017\u5e742\u670827\u65e5\u4e0b\u5348\uff0c\u201c\u805a\u529b\u4e2d\u56fd\uff0c\u5171\u8d62\u592e\u5e7f\u201d\u2014\u2014\u4e2d\u592e\u4eba\u6c11\u5e7f\u64ad\u7535\u53f02017\u5e74\u4e2d\u56fd\u54c1\u724c\u96c6\u7ed3\u884c\u52a8\u542f\u52a8\u4eea\u5f0f\u5728\u53a6\u95e8\u9686\u91cd\u4e3e\u884c\u3002\u71d5\u4e4b\u5c4b\u4f5c\u4e3a\u77e5\u540d\u4f01\u4e1a\u4ee3\u8868\u5e94\u9080\u51fa\u5e2d\u3002"},{"url":"news-123.html","title":"\u71d5\u4e4b\u5c4b\u71d5\u7a9d\u901a\u8fc7\u5168\u7403\u98df\u54c1\u5b89\u5168\u6807\u51c6BRC\u8ba4\u8bc1","date":"2017-02-16","digest":"\u8fd1\u65e5\uff0c\u71d5\u4e4b\u5c4b\u901a\u8fc7BRC\u5168\u7403\u98df\u54c1\u6280\u672f\u6807\u51c6\uff08BRC Food Technical Standard\uff09\u8ba4\u8bc1\uff0c\u5f70\u663e\u4e86\u71d5\u4e4b\u5c4b\u71d5\u7a9d\u54c1\u8d28\u548c\u5b9e\u529b\u5904\u4e8e\u5168\u7403\u6807\u51c6\u6c34\u5e73\u3002"},{"url":"news-121.html","title":"\u9648\u7d2b\u51fd\u6234\u5411\u5b87\u5a5a\u5bb4\u4e92\u5582\u7897\u71d5 \u4f17\u660e\u661f\u7b11\u50ac\u65e9\u751f\u8d35\u5b50","date":"2016-12-22","digest":"12\u670819\u65e5\uff0c\u9648\u7d2b\u51fd\u548c\u6234\u5411\u5b87\u5728\u5317\u4eac\u4e3e\u529e\u4e86\u5a5a\u793c\u7b54\u8c22\u5bb4\u3002\u5bb4\u4f1a\u73b0\u573a\u4ee5\u4f18\u96c5\u7684\u6de1\u7d2b\u548c\u767d\u8272\u4e3a\u4e3b\uff0c\u5e03\u7f6e\u5341\u5206\u552f\u7f8e\u6d6a\u6f2b\uff1b\u5bb4\u5e2d\u9009\u7528\u9876\u7ea7\u54c1\u724c\u2014\u2014\u71d5\u4e4b\u5c4b\u00b7\u7897\u71d5\uff0c\u9ad8\u96c5\u5927\u6c14\u3001\u65f6\u5c1a\u559c\u5e86\u3002"},{"url":"news-122.html","title":"\u71d5\u4e4b\u5c4b\u00b7\u7897\u71d5\u5e7f\u5dde\u9996\u5bb6\u65d7\u8230\u5e97\u76db\u5927\u5f00\u4e1a","date":"2016-12-22","digest":"12\u670821\u65e5\uff0c\u71d5\u4e4b\u5c4b\u00b7\u7897\u71d5\u65d7\u8230\u5e97\u5728\u5e7f\u5dde\u73e0\u6c5f\u65b0\u57ce\u534e\u7a57\u8def\u76db\u5927\u5f00\u4e1a\uff0c\u8fd9\u4e5f\u662f\u71d5\u4e4b\u5c4b\u00b7\u7897\u71d5\u9996\u6b21\u8fdb\u9a7b\u5e7f\u5dde\u3002"},{"url":"news-120.html","title":"\u71d5\u4e4b\u5c4b\u8d70\u8fdb\u53a6\u95e8\u5468\u8fb9\u793e\u533a \u9f50\u5fc3\u534f\u529b\u91cd\u5efa\u5bb6\u56ed","date":"2016-09-26","digest":"9\u670825\u65e5\uff0c\u71d5\u4e4b\u5c4b\u8463\u4e8b\u957f\u3001\u603b\u88c1\u3001\u603b\u7ecf\u7406\u7b49\u516c\u53f8\u9886\u5bfc\u4eb2\u81ea\u7387\u961f\uff0c\u5e26\u9886\u71d5\u4e4b\u5c4b50\u591a\u540d\u5458\u5de5\u8d70\u8fdb\u53a6\u95e8\u5468\u8fb9\u793e\u533a\uff0c\u534f\u52a9\u707e\u540e\u6e05\u7406\u5de5\u4f5c\uff0c\u9f50\u5fc3\u534f\u529b\u91cd\u5efa\u5bb6\u56ed\u3002"},{"url":"news-119.html","title":"\u71d5\u4e4b\u5c4b\u00b7\u7897\u71d5\u6210\u4e3aCCTV\u5965\u8fd0\u680f\u76ee\u6307\u5b9a\u793c\u54c1","date":"2016-08-10","digest":"\u71d5\u4e4b\u5c4b\u52a9\u529b\u91cc\u7ea6\u5965\u8fd0\uff0c\u7275\u624bCCTV-1\u300a\u76f8\u7ea6\u91cc\u7ea6\u300b\uff0c\u4ece8\u67086\u65e5\u81f38\u670821\u65e5\uff0c\u4e3a\u5e7f\u5927\u89c2\u4f17\u6253\u9020\u4e3a\u671f16\u671f\u7684\u4f53\u80b2\u5927\u9910\u3002"}];
            </script>
        </div>
    </section>
</article>


<%@include file="/WEB-INF/mgr/jsp/main/footer1.jsp"%>
</body>
</html>
