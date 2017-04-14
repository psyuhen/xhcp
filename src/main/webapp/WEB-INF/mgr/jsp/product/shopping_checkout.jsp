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

<article class="buyliucheng">
    <section class="steps tac">
        <ul class="inb clear">
            <li>
                <i class="iconfont"></i>
                <p>购物车结算</p>
            </li>
            <li class="active">
                <i class="iconfont"></i>
                <p>填写订单信息</p>
            </li>
            <li>
                <i class="iconfont"></i>
                <p>支付/确认订单</p>
            </li>
        </ul>
    </section>
    <section class="odi">
        <form action="${ctx}/shopping_confirm.html" method="post" name="theForm" id="theForm" onsubmit="return checkorder()">

            <div class="maxsize">
                <div class="block address">
                    <h1>填写和提交订单信息</h1>
                    <div class="addnew">
                        <div class="title"><a href="javascript:void(0);"> + 新增地址</a></div>
                        <div class="cont">
                            <div class="clear">
                                <div class="md6">
                                    <p>收货人：</p>
                                    <div class="input">
                                        <input type="text" name="consignee" id="consignee">
                                    </div>
                                </div>
                                <div class="md6">
                                    <p>联系电话：</p>
                                    <div class="input">
                                        <input type="text" name="mobile" id="mobile">
                                    </div>
                                </div>
                                <div class="md6">
                                    <p>地址：</p>
                                    <div class="input ovh">
                                        <div class="nmg">
                                            <div class="md4">
                                                <div class="con">
                                                    <input type="hidden" value="1" name="country">
                                                    <select name="province" id="province" onchange="ChangeProvince(this.value)">
                                                        <option value="">请选择省</option>
                                                        <c:forEach items="${provinces}" var="prov">
                                                            <option value="${prov.province_code}">${prov.province_name}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="md4">
                                                <div class="con">
                                                    <select name="city" id="city" onchange="ChangeCity(this.value)">
                                                        <option value="">请选择市</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="md4">
                                                <div class="con">
                                                    <select name="district" id="district">
                                                        <option value="">请选择县</option>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="md6">
                                    <p>详细地址：</p>
                                    <div class="input">
                                        <input type="text" id="address">
                                    </div>
                                </div>
                            </div>
                            <div class="tac">
                                <input type="button" class="save" value="保存信息" onclick="javascript:check_address()">&nbsp;&nbsp;&nbsp;&nbsp;<label><input type="checkbox"> 设为默认地址</label>
                            </div>
                        </div>
                    </div>
                    <div class="choose">
                        <table>
                            <tbody>
                                <tr>
                                    <th>收货人姓名</th>
                                    <th>详细地址</th>
                                    <th>联系电话</th>
                                    <th>选择收货地址</th>
                                </tr>
                                <c:forEach items="${freqAddrs}" var="addr">
                                    <tr>
                                        <td>${addr.user_name}</td>
                                        <td>${addr.province_name}${addr.city_name}${addr.town_name}${addr.address}</td>
                                        <td>${addr.mobile}</td>
                                        <c:choose>
                                            <c:when test="${addr.default_addr eq '0'}" >
                                                <td><input type="radio" value="${addr.freq_id}" name="address_id" checked="checked"></td></tr>
                                            </c:when>
                                            <c:otherwise>
                                                <td><input type="radio" value="${addr.freq_id}" name="address_id"></td></tr>
                                            </c:otherwise>
                                        </c:choose>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="block payway">
                    <div class="title">支付方式：</div>
                    <div class="cont clear">
                        <label>  <input type="radio" checked="checked" name="payment"  value="0" iscod="0">
                            <img src="${ctx}/css/themes/default/images/zhifubao.jpg">
                        </label>
                        <label>  <input type="radio" checked="checked" name="payment"  value="1" iscod="0">
                            <img src="${ctx}/css/themes/default/images/weixin.jpg">
                        </label>
                        <label>  <input type="radio" name="payment"  value="3" iscod="0">
                            <img src="${ctx}/css/themes/default/images/zhaoshang.jpg">
                        </label>
                        <label>  <input type="radio" name="payment"  value="4" iscod="0">
                            <img src="${ctx}/css/themes/default/images/gongshang.jpg">
                        </label>
                        <label>  <input type="radio" name="payment"  value="5" iscod="0">
                            <img src="${ctx}/css/themes/default/images/jianshe.jpg">
                        </label>
                        <label>  <input type="radio" name="payment"  value="6" iscod="0">
                            <img src="${ctx}/css/themes/default/images/zhongguo.jpg">
                        </label><br/>
                        <label>  <input type="radio" name="payment"  value="7" iscod="0">
                            <img src="${ctx}/css/themes/default/images/nongye.jpg">
                        </label>
                        <label>  <input type="radio" name="payment"  value="8" iscod="0">
                            <img src="${ctx}/css/themes/default/images/jiaotong.jpg">
                        </label>
                        <label>  <input type="radio" name="payment"  value="9" iscod="0">
                            <img src="${ctx}/css/themes/default/images/youzheng.jpg">
                        </label>
                        <label>  <input type="radio" name="payment"  value="11" iscod="0">
                            <img src="${ctx}/css/themes/default/images/xingye.jpg">
                        </label>
                        <label>  <input type="radio" name="payment"  value="12" iscod="0">
                            <img src="${ctx}/css/themes/default/images/zhongxin.jpg">
                        </label>

                    </div>
                </div>
                <div class="block list">
                    <table>
                        <tbody>
                            <tr>
                                <th>商品清单</th>
                                <th>单价(元)</th>
                                <th>数量</th>
                                <th>小计(元)</th>
                            </tr>
                        <c:forEach items="${sessionScope.Shopping_Car}" var="car">
                            <tr>
                                <td>${car.merch_name}</td>
                                <td>${car.price}</td>
                                <td>${car.buy_num}</td>
                                <td>${car.price * car.buy_num}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div class="total tar">
                        <div class="tar">
                            <textarea placeholder="备注" name="remark"></textarea>
                        </div>
                        <font class="inb">￥${TotalPrice}</font><!--<a href="javascript:void(0);" class="inb">去结算</a>-->
                        <input type="submit" class="inb common_btn" value="去付款">
                    </div>
                </div>
            </div>
        </form>
    </section>
</article>
<script src="${ctx}/css/themes/default/script/shopping_checkout.js"></script>
<%@include file="/WEB-INF/mgr/jsp/main/footer1.jsp"%>
</body>
</html>
