<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="ccb erm system">
    <meta name="author" content="ccb">
    <%--可引入文件(请在Attribute中填写，多个用逗号分开)--%>
    <%request.setAttribute("import", "html5js,bootstrap,bootstrap_cerulean,charisma_app,jquery_dataTables,datetimepicker,jquery_noty,dateUtil,tableSupport,dialog,jquery_cookie,stringutil,form,fileupload"); %>
    <%@include file="/WEB-INF/mgr/jsp/inc/import.jsp" %>
    <title><spring:message code="xhcp_name" /></title>
</head>
<body>

<%@include file="/WEB-INF/mgr/jsp/main/topbar.jsp" %>
<div class="ch-container">
    <div class="row">
        <%@include file="/WEB-INF/mgr/jsp/main/left_menu.jsp" %>
        <%-- 主页开始 --%>
        <div id="content" class="col-lg-10 col-sm-10">
            <%-- content starts --%>
            <% request.setAttribute("module_id", "navmgr"); %>
            <%@include file="/WEB-INF/mgr/jsp/main/nav.jsp" %>

            <%-- 首页LOGO --%>
            <div class="row">
                <div class="box col-md-12">
                    <div class="box-inner">
                        <div class="box-content">
                            <div class="page-header">
                                <h4>首页导航
                                    <small>添加、编辑、删除首页导航</small>
                                </h4>
                            </div>

                            <div class="row">
                                <div class="col-md-12">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <%-- content ends --%>
        </div><%--/#content.col-md-0--%>
    </div><%--/fluid-row--%>

    <hr>
    <%@include file="/WEB-INF/mgr/jsp/main/footer.jsp" %>

</div><%--/.fluid-container--%>


<%-- 加载JS --%>
<script src="${ctx}/js/common/Dictionary.${__min}js"></script>
<script src="${ctx}/js/system/AccountList.${__min}js"></script>
<script type="text/javascript">
    $(document).ready(function(){
        var accountList = new AccountList({
            "module_id" : "${param.module_id}"
        });
    });
</script>
</body>
</html>