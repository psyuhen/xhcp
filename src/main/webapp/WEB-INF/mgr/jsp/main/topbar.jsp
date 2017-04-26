<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<style>
#topmenu{
	color: #ffffff;
}
#topmenu a{
	color: #ffffff;	
}

#topmenu a:hover{
	font-size:20px;
}
.imgDiv{
 	display : inline-block;
 	position : relative;
	margin-right: 10px;
	margin-bottom: 10px;
}

.imgDiv .delete{
	position : absolute;
	top : -5px;
	right : -3px;
	width : 10px;
	height : 10px;
	border: 2px;
}
.line_01 {
	padding: 0 20px 0;
	margin: 20px 0;
	line-height: 1px;
	border-left: 500px solid #ddd;
	border-right: 500px solid #ddd;
	font-weight: bold;
	font-style: italic;
	text-align: center;
}

</style>
	<%-- topbar starts --%>
    <div class="navbar navbar-default" role="navigation">

        <div class="navbar-inner">
            <button type="button" class="navbar-toggle pull-left animated flip">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" title="佰瑞林陈皮皇"
            		style="width: 400px;font-size: 32px;font-family: 隶书" href="${ctx}/mgr/main"> 
            	<img alt="Charisma Logo" src="${cfrm_path}/css/images/img_user2.jpg" class="hidden-xs"/>
                <span><spring:message code="佰瑞林陈皮皇后台管理" /></span>
            </a>

            <div class="pull-right" style="padding-right: 15px;">
            	<label style="color: #ffffff;font-size: 10px;">
            		欢迎您:${Back_Account.account_id}(${Back_Account.account_name})
	            	|日期:<span id="now_time"></span>
            	</label><br/>
            	<label class="pull-right" id="topmenu">
            		<a href="${ctx}/mgr"><spring:message code="main_page" /></a>
            		<%-- |<a href="${ctx}/mgr/logout" title="更好体验的浏览器chrome"><spring:message code="下载" /></a> --%>
            		|<a href="${ctx}/mgr/logout" ><spring:message code="logout" /></a>
            	</label>
            </div>
            <%-- user dropdown ends --%>
        </div>
    </div>
    <%-- topbar ends --%>