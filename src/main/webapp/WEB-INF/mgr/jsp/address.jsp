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

<div class="member">
    <div class="my">
        <div class="top">
            <div class="maxsize">
                <div class="row clear">
                    <div class="md3">
                        <h1>会员中心</h1>
                    </div>
                    <div class="md9">
                        <h1>收货地址</h1>
                    </div>
                </div>
            </div>
        </div>
        <div class="bottom">
            <div class="maxsize">
                <div class="row clear">
                    <div class="md3">
                        <ul>
                            <li><a href="${ctx}/order.html"><i class="iconfont"></i>全部订单</a></li>
                            <li><a href="${ctx}/usercenter.html"><i class="iconfont"></i>账户信息</a></li>
                            <li class="active"><a href="${ctx}/address.html"><i class="iconfont"></i>地址管理</a></li>
                        </ul>
                    </div>
                    <div class="md9">
                        <div class="tb_box con">
                            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                <tbody>
                                    <tr>
                                        <th width="150">收货人姓名</th>
                                        <th width="350">详细地址</th>
                                        <th width="150">联系电话</th>
                                        <th width="150">默认地址</th>
                                        <th class="cart_list_right">操作</th>
                                    </tr>
                                    <c:if test="${UserFreqAddrs != null && fn:length(UserFreqAddrs) > 0}">
                                        <tr>
                                            <td colspan="5">
                                                <table width="100%" height="45" border="0" cellspacing="0" cellpadding="0"
                                                       style="background:#faf7f4; text-align:center;">
                                                    <tbody>
                                                    <c:forEach items="${UserFreqAddrs}" var="freqAddrs">
                                                        <tr id="tr${freqAddrs.freq_id}">
                                                            <td width="150" class="cart_list">${freqAddrs.user_name}</td>
                                                            <td width="350" class="cart_list_mid">
                                                            ${freqAddrs.province_name}${freqAddrs.city_name}${freqAddrs.town_name}${freqAddrs.address}
                                                            </td>
                                                            <td width="150" class="cart_list_mid">${freqAddrs.mobile}</td>
                                                            <td width="150" class="cart_list_mid">
                                                                <c:choose>
                                                                    <c:when test="${freqAddrs.default_addr eq '0'}">
                                                                        <input type="radio" name="isdefault" checked onclick="chang_default('${freqAddrs.freq_id}')">
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <input type="radio" name="isdefault" onclick="chang_default('${freqAddrs.freq_id}')">
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </td>
                                                            <td class="cart_list_right">
                                                                <a onclick="delete_address('${freqAddrs.freq_id}')">删除</a>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                    </tbody>
                                                </table>
                                            </td>
                                        </tr>
                                    </c:if>
                                </tbody>
                            </table>

                        </div>
                        <div class="con row clear address">
                            <h1>添加地址</h1>
                            <table border="0" cellspacing="0" cellpadding="0" width="100%" class="tb_from">
                                <tbody><tr>
                                    <td>真实姓名:</td>
                                    <td><label for="textfield"></label>
                                        <input type="text" name="consignee" id="consignee" class="input_box"></td>
                                </tr>
                                <tr>
                                    <td>联系电话:</td>
                                    <td><label for="textfield3"></label>
                                        <input type="text" name="mobile" id="mobile" class="input_box"></td>
                                </tr>
                                <tr>
                                </tr><tr>
                                    <td>地     址:</td>
                                    <td><table border="0" cellspacing="0" cellpadding="0">
                                        <tbody><tr>
                                            <td>
                                                <input type="hidden" value="1" name="country">
                                                <label for="select"></label>
                                                <select name="province" id="province" onchange="ChangeProvince(this.value)">
                                                    <option value="">省份</option>
                                                    <c:forEach items="${provinces}" var="prov">
                                                        <option value="${prov.province_code}">${prov.province_name}</option>
                                                    </c:forEach>
                                                </select></td>
                                            <td class="city_box"><label for="select2"></label>
                                                <select name="city" id="city" onchange="ChangeCity(this.value)"><option value="">城市/地区/自治区</option></select>
                                            </td>
                                            <td><label for="select3"></label>
                                                <select name="district" id="district"><option value="">区/县</option></select>
                                            </td>
                                        </tr>
                                        </tbody></table></td>
                                </tr>
                                <tr>
                                    <td>详细地址:</td>
                                    <td><label for="textarea"></label>
                                        <input name="address" id="address" class="input_box"></td>
                                </tr>


                                <tr><td>&nbsp;</td>
                                    <td><div class="add"><a onclick="javascript:check_address(1)" class="common_btn">添加</a></div></td>
                                </tr>
                                </tbody></table>



                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>

<script type="text/javascript" src="${ctx}/js/system/check_address.js"></script>
<%@include file="/WEB-INF/mgr/jsp/main/footer1.jsp"%>
</body>
</html>
