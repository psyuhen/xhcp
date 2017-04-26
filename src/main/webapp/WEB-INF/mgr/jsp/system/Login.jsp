<%--
  Created by IntelliJ IDEA.
  User: pansen
  Date: 2017/4/25
  Time: 10:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="keywords" content="陈武越家的陈皮,武越陈皮,陈皮,武越">
    <meta name="description" content="陈武越家的陈皮,武越陈皮,陈皮,武越">
    <title>陈武越家的陈皮,武越陈皮,陈皮,武越</title>
    <%@include file="/WEB-INF/mgr/jsp/inc/import.jsp" %>
</head>
<body>
<div class="ch-container">
    <div class="row">

        <div class="row">
            <div class="col-md-12 center login-header">
                <h2><spring:message code="xhcp_name" /></h2>
            </div>
            <!--/span-->
        </div><!--/row-->

        <div class="row">
            <div class="well col-md-5 center login-box">
                <div class="alert alert-info">
                    <c:choose>
                        <c:when test="${not empty requestScope.msg}">
                            <font style="font-family:微软雅黑;font-size:14px;color:#CC0000"><spring:message code="${requestScope.msg}" /></font>
                        </c:when>
                        <c:otherwise>
                            <spring:message code="login_info" />
                        </c:otherwise>
                    </c:choose>
                </div>
                <form class="form-horizontal" action="${ctx}/mgr/tologin.do" method="post">
                    <input type="hidden" name="toBlackUrl" value="${requestScope.toBlackUrl}">
                    <fieldset>
                        <div class="input-group input-group-lg">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-user red"></i></span>
                            <input name="account_id" type="text" value="${cookie.account_id.value}" class="form-control" placeholder="<spring:message code="username" />">
                        </div>
                        <div class="clearfix"></div><br>

                        <div class="input-group input-group-lg">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-lock red"></i></span>
                            <input name="account_password" type="password" value="${cookie.account_password.value}" class="form-control" placeholder="<spring:message code="password" />">
                        </div>
                        <div class="clearfix"></div>

                        <div class="input-prepend">
                            <label class="remember" for="remember">
                                <input name="remember" value="1" type="checkbox" id="remember" <c:if test="${cookie.remember.value eq \"1\"}">checked="checked"</c:if>> <spring:message code="remember_me" />
                            </label>
                        </div>
                        <div class="clearfix"></div>

                        <p class="center col-md-5">
                            <button type="submit" class="btn btn-primary"><spring:message code="btn_login_name" /></button>
                        </p>
                    </fieldset>
                </form>
            </div>
            <!--/span-->
        </div><!--/row-->
    </div><!--/fluid-row-->
</div><!--/.fluid-container-->
</body>
</html>

