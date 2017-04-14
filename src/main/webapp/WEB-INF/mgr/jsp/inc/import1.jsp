<%--
  Created by IntelliJ IDEA.
  User: sam.pan
  Date: 2017/3/9
  Time: 10:42
  To change this template use File | Settings | File Templates.
--%>
<%--*****************************************--%>
<%--************  默   认   引   入    ****************--%>
<%--*****************************************--%>
<%--JSTL tag--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%--服务器环境--%>
<c:set var="ctx" value='${pageContext.request.contextPath}' scope="request"/>
<c:set var="cfrm_path" value='${pageContext.request.contextPath}' scope="request"/>
<%-- 开发或者生产模式 --%>
<c:choose>
    <c:when test="${sessionScope[\"develop_mode\"] eq 'true'}">
        <c:set var="__min" value='' scope="request"/>
    </c:when>
    <c:otherwise>
        <c:set var="__min" value='min.' scope="request"/>
    </c:otherwise>
</c:choose>
<script type="text/javascript">
    <!--
    var _user = '${sessionScope["user"].account_id}';
    var ctx = '${pageContext.request.contextPath}';
    var cfrm_path = ctx+'/mgr';
    <%-- 动态增加对应的语言(从spring mvc中的session中获取) --%>
    var lang = '${sessionScope["org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE"]}';
    lang = lang === "" ? "zh_CN" : lang;
    //-->
</script>

<%--基础JS与CSS--%>
<!--[if lte IE 9]>
<script src="${ctx}/js/jquery/jquery-1.9.1.${__min}js"></script>
<script src="${ctx}/js/jquery/jquery-migrate-1.3.0.${__min}js"></script>
<![endif]-->
<!--[if gt IE 9]>
<script src="${ctx}/js/jquery/jquery-2.0.3.${__min}js"></script>
<script src="${ctx}/css/themes/default/script/smooth.js"></script>
<![endif]-->
<![if !IE]><script src="${ctx}/css/themes/default/script/smooth.js"></script><![endif]>
<script type="text/javascript">
<!--
if(typeof jQuery === 'undefined'){
    document.write('<script src="${ctx}/js/jquery/jquery-2.0.3.${__min}js"></script>');
}
//-->
</script>

<%--*****************************************--%>
<%--**************    按   需   引   入    ************--%>
<%--*****************************************--%>
<%
    String importStr = (String) request.getAttribute("imp");
    if (importStr != null && importStr.length() > 0) {
        String[] imports = importStr.split(",");
        for (String i : imports) {
            request.setAttribute(i, true);
        }
    }
%>

<link rel="stylesheet" href="${ctx}/css/themes/default/style/animate.css" />
<link rel="stylesheet" href="${ctx}/css/themes/default/style/main.css" />
<link rel="stylesheet" href="${ctx}/css/themes/default/style/responces.css" />
<link rel="stylesheet" href="${ctx}/css/themes/default/style/fonts.css" />

<script src="${ctx}/css/themes/default/script/bxslider.js"></script>
<script src="${ctx}/css/themes/default/script/pubuliu.js"></script>
<script src="${ctx}/css/themes/default/script/main.js"></script>
<script src="${ctx}/js/common/StringUtil.${__min}js"></script>