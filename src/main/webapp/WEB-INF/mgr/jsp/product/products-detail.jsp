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
<article class="product">
    <div class="detial">
        <div class="maxsize">
            <section class="main clear">
                <div class="md6 left">
                    <ul class="slider">
                        <li lockscale="3/4" style="background-image: url(images/201601/source_img/1_P_1452279466367.jpg);"></li>
                        <li lockscale="3/4" style="background-image: url(images/201601/source_img/1_P_1452279450053.jpg);"></li>
                        <li lockscale="3/4" style="background-image: url(images/201601/source_img/1_P_1452279430459.jpg);"></li>
                        <li lockscale="3/4" style="background-image: url(images/201601/source_img/1_P_1452279410481.jpg);"></li>
                        <li lockscale="3/4" style="background-image: url(images/201601/source_img/1_P_1452279389097.jpg);"></li>
                        <li lockscale="3/4" style="background-image: url(images/201409/source_img/1_P_1410485474146.jpg);"></li>
                        <li lockscale="3/4" style="background-image: url(images/201601/source_img/1_P_1452279484787.jpg);"></li>
                    </ul>
                    <div class="preview">
                        <ul class="nmg clear">
                            <li><div class="con" lockscale="3/4" style="background-image: url(images/201601/source_img/1_P_1452279466367.jpg)"></div></li>
                            <li><div class="con" lockscale="3/4" style="background-image: url(images/201601/source_img/1_P_1452279450053.jpg)"></div></li>
                            <li><div class="con" lockscale="3/4" style="background-image: url(images/201601/source_img/1_P_1452279430459.jpg)"></div></li>
                            <li><div class="con" lockscale="3/4" style="background-image: url(images/201601/source_img/1_P_1452279410481.jpg)"></div></li>
                            <li><div class="con" lockscale="3/4" style="background-image: url(images/201601/source_img/1_P_1452279389097.jpg)"></div></li>
                            <li><div class="con" lockscale="3/4" style="background-image: url(images/201409/source_img/1_P_1410485474146.jpg)"></div></li>
                            <li><div class="con" lockscale="3/4" style="background-image: url(images/201601/source_img/1_P_1452279484787.jpg)"></div></li>
                        </ul>
                    </div>
                </div>
                <div class="md6 right light">
                    <div class="con">
                        <div class="title">
                            <h1>${merchInfo.name}</h1>
                        </div>
                        <div class="price">
                            <p>价格：<em>￥<strong>${merchInfo.price}</strong></em></p>
                            <dl class="options">
                                <dt>产品规格：</dt>
                                <dd>
                                    <p>
                                        <a href="javascript:void(0);" class="active" onclick="change_price(53)">${merchInfo.standard}</a>
                                    </p>
                                </dd>
                            </dl>
                        </div>
                        <div class="amount">
                            <div class="shuliang">
                                <p>数量：</p>
                                <div class="uikit_amount" max="10" min="1">
                                    <a href="javascript:void(0);" class="add iconfont" id="add">&#xe6b2;</a>
                                    <a href="javascript:void(0);" class="min iconfont" id="min">&#xe6b1;</a>
                                    <input type="text" id="count" value="1" max="10" min="1">
                                </div>
                            </div>
                            <p>服务：由燕之屋直接销售和供货，并提供售后服务。</p>

                            <input type="hidden" id="price_value" value="4536" />
                            <input type="hidden" id="specificationsid" value="53" />
                            <a href="javascript:void(0);" class="buybtn" onclick="return add_cart(1)">立即购买</a>
                        </div>
                    </div>
                </div>
            </section>
            <section class="desc">
                <div class="title light tac">
                    <h1>产品详情</h1>
                </div>
                <div class="imgs tac">
                    <p style="text-align: center;">
                        <img alt="" src="http://www.yanzhiwu.com/upfiles/images/20160108184608.jpg" style="width: 790px; height: 840px;" /><br />
                        <img alt="" src="http://www.yanzhiwu.com/upfiles/images/20160108184628.jpg" style="width: 790px; height: 505px;" /><br />
                        <img alt="" src="http://www.yanzhiwu.com/upfiles/images/20160108184703.jpg" style="width: 790px; height: 727px;" /><br />
                        <img alt="" src="http://www.yanzhiwu.com/upfiles/images/20160528094036.jpg" style="width: 790px; height: 209px;" /><br />
                        <img alt="" src="http://www.yanzhiwu.com/upfiles/images/20160108184736.jpg" style="width: 790px; height: 660px;" /><br />
                        <img alt="" src="http://www.yanzhiwu.com/upfiles/images/20160108184753.jpg" style="width: 790px; height: 747px;" /><br />
                        <img alt="" src="http://www.yanzhiwu.com/upfiles/images/20160108184809.jpg" style="width: 790px; height: 646px;" /><br />
                        <img alt="" src="http://www.yanzhiwu.com/upfiles/images/20160108184826.jpg" style="width: 790px; height: 682px;" /><br />
                        <img alt="" src="http://www.yanzhiwu.com/upfiles/images/20160108184842.jpg" style="width: 790px; height: 529px;" /><br />
                        <img alt="" src="http://www.yanzhiwu.com/upfiles/images/20160108184902.jpg" style="width: 790px; height: 379px;" /><br />
                        <img alt="" src="http://www.yanzhiwu.com/upfiles/images/20160108184921.jpg" style="width: 790px; height: 747px;" /><br />
                        <img alt="" src="http://www.yanzhiwu.com/upfiles/images/20160108184938.jpg" style="width: 790px; height: 661px;" /><br />
                        <img alt="" src="http://www.yanzhiwu.com/upfiles/images/20160108184955.jpg" style="width: 790px; height: 715px;" /><br />
                        <img alt="" src="http://www.yanzhiwu.com/upfiles/images/20160108185010.jpg" style="width: 790px; height: 801px;" /></p>
                </div>
            </section>
        </div>
    </div>
</article>

<%@include file="/WEB-INF/mgr/jsp/main/footer1.jsp"%>
</body>
</html>
