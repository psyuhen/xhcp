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
	var mgr_path = ctx+'/mgr';
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
<![endif]-->
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
	String importStr = (String) request.getAttribute("import");
	if (importStr != null && importStr.length() > 0) {
		String[] imports = importStr.split(",");
		for (String i : imports) {
			request.setAttribute(i, true);
		}
	}
%>

<%-- HTML5 兼容IE6-8 The HTML5 shim, for IE6-8 support of HTML5 elements--%>
<c:if test="${html5js eq 'true'}">
    <!--[if lt IE 9]>
    	<script src="${ctx}/js/html5/html5.js"></script>
    <![endif]-->
</c:if>

<%-- 解决IE9下出现的问题 --%>
<c:if test="${'true' eq 'true'}">
	<%-- bootstrap --%>
	<script src="${ctx}/js/bootstrap/bootstrap-3.2.0.${__min}js" async></script>
	<link id="css_theme" href="${ctx}/css/bootstrap/bootstrap.${__min}css" rel="stylesheet" media="none" onload="if(media!='all')media='all'"/>
	<link href="${ctx}/css/bootstrap/charisma-app.css" rel="stylesheet">
	<link id="css_theme" href="${ctx}/css/bootstrap/bootstrap-cerulean.min.css" rel="stylesheet">

	<%-- 日期工具类 --%>
	<script src="${ctx}/js/common/DateUtil.${__min}js"></script>
	<script src="${ctx}/js/common/StringUtil.${__min}js"></script>
	<script src="${ctx}/js/common/RenderUtil.${__min}js"></script>
	<script src="${ctx}/js/common/TableSupport.${__min}js"></script>
	<script src="${ctx}/js/html5/Html5Util.${__min}js"></script>
	
	<%-- cookie和theme --%>
	<script src="${ctx}/js/cookie/jquery.cookie.${__min}js"></script>
	<script src="${ctx}/js/common/Theme.${__min}js"></script>
	
	<%-- 扩展Array --%>
	<script src="${ctx}/js/common/Array.${__min}js"></script>
	
	<%-- Html方法 --%>
	<script src="${ctx}/js/common/Html.${__min}js"></script>
	<%-- 下拉框的方法 --%>
	<script src="${ctx}/js/common/Option.${__min}js"></script>
</c:if>

<c:if test="${bootstrap_cerulean eq 'true'}">
	<link id="css_theme" href="${ctx}/css/bootstrap/bootstrap-cerulean.min.css" rel="stylesheet">
</c:if>

<%-- notification plugin --%>
<c:if test="${jquery_noty eq 'true'}">
    <link href='${ctx}/css/noty/jquery.noty.css' rel='stylesheet'>
    <link href='${ctx}/css/noty/noty_theme_default.css' rel='stylesheet'>
	<script src="${ctx}/js/noty/jquery.noty-1.2.1.${__min}js"></script>
	<script src="${ctx}/js/noty/Noty.${__min}js"></script>
</c:if>

<%-- data table plugin --%>
<c:if test="${jquery_dataTables eq 'true'}">
	<link href='${ctx}/css/datatable/dataTables.bootstrap.${__min}css' rel='stylesheet'>
	<script src='${ctx}/js/datatable/jquery.dataTables.${__min}js'></script>
	<script src='${ctx}/js/datatable/dataTables.bootstrap.${__min}js'></script>
	<script>
		<%-- 默认屏蔽搜索框 ，使用自定义的查询条件--%>
		$.extend( $.fn.dataTable.defaults, {
			searching: false
		} );
		<%-- 默认屏蔽Datatables弹出的错误提示--%>
		$.fn.dataTable.ext.errMode = function(s,h,m){}
	</script>
	<script src="${ctx}/js/common/Table.${__min}js" ></script>
	<script src="${ctx}/js/common/Download.${__min}js" ></script>
</c:if>

<%-- FORM --%>
<c:if test="${form eq 'true'}">
	<script src="${ctx}/js/common/Form.${__min}js"></script>
</c:if>

<%-- formvalidation --%>
<c:if test="${formvalidation eq 'true'}">
    <link rel="stylesheet" href="${ctx}/css/formvalidation/formValidation.min.css">
    <script src="${ctx}/js/formvalidation/formValidation.min.js"></script>
    <%-- <script src="${ctx}/js/formvalidation/formValidation-0.6.1.js"></script> --%>
    <script src="${ctx}/js/formvalidation/bootstrap.min.js"></script>
    <script>
        $(document).ready(function(){
            var script = $("<script/>");
            if("en_US" === lang){
                script.attr("src", "${ctx}/js/formvalidation/en_US.${__min}js");
            }else if("zh_CN" === lang){
                script.attr("src", "${ctx}/js/formvalidation/zh_CN.${__min}js");
            }
            $("body").append(script);
        })
    </script>
    <script src="${ctx}/js/formvalidation/FormValidator.${__min}js"></script>
</c:if>

<%-- validator --%>
<c:if test="${validator eq 'true'}">
    <script src="${ctx}/js/common/Validator.${__min}js"></script>
</c:if>
<%-- json --%>
<c:if test="${json eq 'true'}">
	<script src="${ctx}/js/common/json.${__min}js"></script>
</c:if>
<%-- 解决IE9下出现的问题 --%>
<c:if test="${'true' eq 'true'}">
	<link rel="stylesheet" href="${ctx}/css/common/common.css">
</c:if>
<%-- ParamCheck --%>
<c:if test="${paramCheck eq 'true'}">
    <script src="${ctx}/js/common/ParamCheck.${__min}js"></script>
</c:if>
<%-- Dialog类 --%>
<c:if test="${dialog eq 'true'}">
	<script src="${ctx}/js/common/Dialog.${__min}js"></script>
</c:if>
<%-- kindeditor --%>
<c:if test="${kindeditor eq 'true'}">
	<script src="${ctx}/js/kindeditor/kindeditor-all.${__min}js"></script>
	<script src="${ctx}/js/kindeditor/zh_CN.js"></script>
	<link rel="stylesheet" href="${ctx}/js/kindeditor/themes/default/default.css">
</c:if>
<%-- bootstrap-datetimepicker --%>
<c:if test="${datetimepicker eq 'true'}">
	<script src='${ctx}/js/moment/moment.min.js'></script>
	<script src='${ctx}/js/moment/zh-cn.${__min}js'></script>
	<script src='${ctx}/js/bootstrap-datetimepicker/bootstrap-datetimepicker.${__min}js'></script>
	<link href='${ctx}/css/bootstrap-datetimepicker/bootstrap-datetimepicker.${__min}css' rel='stylesheet' >
	<script>
		$.fn.datetimepicker.defaults.tooltips = {
			today: '回到今天',
			clear: '清除选择',
			close: '关闭',
			selectMonth: '选择月份',
			prevMonth: '上一月',
			nextMonth: '下一月',
			selectYear: '选择年份',
			prevYear: '上一年',
			nextYear: '下一年',
			selectDecade: '选择十年',
			prevDecade: '上一十年',
			nextDecade: '下一十年',
			prevCentury: '上一世纪',
			nextCentury: '下一世纪',
			pickHour: '选择小时',
			incrementHour: '增加小时',
			decrementHour: '减小小时',
			pickMinute: '选择分钟',
			incrementMinute: '增加分钟',
			decrementMinute: '减小分钟',
			pickSecond: '选择秒',
			incrementSecond: '增加秒',
			decrementSecond: '减小秒',
			togglePeriod: '切换世纪',
			selectTime: '选择时间'
		};
	</script>
</c:if>
<%-- fileupload --%>
<c:if test="${fileupload eq 'true'}">
    <%--<script src="${ctx}/js/common/Upload.${__min}js"></script>--%>
    <script src="${ctx}/js/html5/Html5Util.${__min}js"></script>
    <script src="${ctx}/js/common/FileUpload.${__min}js"></script>
</c:if>
<%-- colorbox --%>
<c:if test="${colorbox eq 'true'}">
    <link rel="stylesheet" href="${ctx}/css/colorbox/colorbox.css">
    <script src="${ctx}/js/colorbox/jquery.colorbox.${__min}js"></script>
    <script src="${ctx}/js/colorbox/jquery.colorbox-zh-CN.js"></script>
</c:if>