<%@ page import="com.huateng.xhcp.model.nav.NavInfo" %>
<%@ page import="java.util.List" %>
<%@ page import="com.huateng.xhcp.util.ApplicationContextUtil" %>
<%@ page import="com.huateng.xhcp.service.nav.NavInfoService" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="com.huateng.xhcp.service.system.FileInfoService" %>
<%@ page import="com.huateng.xhcp.model.system.FileInfo" %><%--
  Created by IntelliJ IDEA.
  User: sam.pan
  Date: 2017/3/9
  Time: 10:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<%-- 第一部分 浏览器提示 --%>
<div class="notice">
    <div class="maxsize">
        <p class="sy">为了更好的浏览效果，建议使用Chrome浏览器查看。</p>
        <a href="javascript:void(0); " class="btn animate sy" onclick="$('.notice').slideUp();" >知道了</a>
    </div>
</div>
<%-- 第一部分 结束 --%>

<%-- 第二部分 导航部分 --%>
<header class="header clear">
    <div class="maxsize posr clear">
        <%-- LOGO 部分 --%>
        <%
            String indexlogo = (String)session.getAttribute("indexlogo");
            if(StringUtils.isBlank(indexlogo)){
                FileInfoService fileInfoService = ApplicationContextUtil.getBeanByClassType(FileInfoService.class);
                FileInfo fileInfo = new FileInfo();
                fileInfo.setFile_type("0");
                fileInfo.setFile_suffix("0");
                List<FileInfo> fileInfos = fileInfoService.queryBy(fileInfo);
                if(!fileInfos.isEmpty()){
                    FileInfo info = fileInfos.get(0);
                    String filePath = info.getFile_path();
                    String fileName = info.getName();
                    indexlogo = filePath + fileName;

                    session.setAttribute("indexlogo", indexlogo);
                }
            }
        %>
        <a href="${ctx}/index" class="logo" title="佰瑞林陈皮,佰瑞林,陈皮">
            <img src="${ctx}/${indexlogo}" alt="佰瑞林陈皮,佰瑞林,陈皮" />
        </a>
        <div class="category menubtn"><i class="iconfont"></i></div>

        <%
            List<NavInfo> navInfos = null;
            List<NavInfo> navSubInfos = null;
            navInfos = (List<NavInfo>)session.getAttribute("navInfoList");

            NavInfoService navInfoService = ApplicationContextUtil.getBeanByClassType(NavInfoService.class);
            if(navInfos == null){
                NavInfo navInfo = new NavInfo();
                navInfo.setPnav_id("-1");
                navInfo.setIs_out_link("0");
                navInfos = navInfoService.queryBy(navInfo);
                navSubInfos = navInfoService.querySubNav();

                session.setAttribute("navInfoList", navInfos);
                session.setAttribute("navSubInfoList", navSubInfos);
            }

            //查询外链接
            List<NavInfo> outLinkList = (List<NavInfo>)session.getAttribute("outLinkList");
            if(outLinkList == null){
                NavInfo navInfo = new NavInfo();
                navInfo.setPnav_id("-1");
                navInfo.setIs_out_link("1");
                outLinkList = navInfoService.queryBy(navInfo);
                session.setAttribute("outLinkList", outLinkList);
            }

        %>

        <%-- 导航 部分 --%>
        <div class="menu clear animateall">
            <dl class="nav">
                <%--<dd>
                    <a href="http://www.yanzhiwu.com/brand.html">品牌</a>
                    <ul class="sub animated fadeInDown">
                        <li><a href="http://www.yanzhiwu.com/brand.html">公司概况</a></li>
                        <li><a href="http://www.yanzhiwu.com/news.html">企业新闻</a></li>
                        <li><a href="http://www.yanzhiwu.com/video.html">视频中心</a></li>
                    </ul>
                </dd>--%>
                <c:forEach items="${navInfoList}" var="nav">
                    <dd>
                        <a href="${nav.url}" >${nav.name}</a>
                        <c:if test="${navSubInfoList != null && fn:length(navSubInfoList) > 0}">
                        <ul class="sub animated fadeInDown">
                            <c:forEach items="${navSubInfoList}" var="sub">
                                <c:if test="${nav.nav_id == sub.pnav_id}">
                                    <li>
                                        <c:choose>
                                            <c:when test="${fn:startsWith(sub.url, 'http://')}">
                                                <a href="${sub.url}">${sub.name}</a>
                                            </c:when>
                                            <c:when test="${fn:startsWith(sub.url, 'https://')}">
                                                <a href="${sub.url}">${sub.name}</a>
                                            </c:when>
                                            <c:otherwise>
                                                <a href="${ctx}/${sub.url}">${sub.name}</a>
                                            </c:otherwise>
                                        </c:choose>
                                    </li>
                                </c:if>
                            </c:forEach>
                        </ul>
                        </c:if>
                    </dd>
                </c:forEach>
            </dl>
            <%-- 导航 右边按钮部分 --%>
            <div class="btns">
                <ul class="clear">
                    <li class="shop">
                        <c:choose>
                            <c:when test="${outLinkList != null && fn:length(outLinkList) > 0}">
                                <a href="${outLinkList.get(0).url}" title="${outLinkList.get(0).name}" target="_blank"><i class="iconfont">&#xe620;</i></a>
                            </c:when>
                            <c:otherwise>
                                <a href="javascript:void(0)" title="外连接" target="_blank"><i class="iconfont">&#xe620;</i></a>
                            </c:otherwise>
                        </c:choose>
                        <div class="sub animated fadeInDown">
                            <c:forEach items="${outLinkList}" var="link">
                                <a href="${link.url}" target="_blank">${link.name}</a>
                            </c:forEach>
                        </div>
                    </li>
                    <%-- 搜索 --%>
                    <li class="search"><a href="javascript:void(0);" class="search" onclick="$('.searchinput').slideToggle();"><i class="iconfont">&#xe617;</i></a></li>
                    <%-- 登录或注册 --%>
                    <li>
                        <c:choose>
                            <c:when test="${Front_Account != null}">
                                <a href="${ctx}/usercenter.html">用户中心</a> | <a href="${ctx}/logout.do">退出</a>
                            </c:when>
                            <c:otherwise>
                                <a href="${ctx}/login.html">登录</a> | <a href="${ctx}/register.html">注册</a>
                            </c:otherwise>
                        </c:choose>
                    </li>
                    <%-- 购物车 --%>
                    <li class="cart">
                        <a href="${ctx}/shopping.html" class="clear">
                            <i class="iconfont fl">&#xe6ed;</i>
                            <span class="fl txt">购物车</span>
                            <c:choose>
                                <c:when test="${sessionScope.Shopping_Car != null}">
                                    <font class="hl fl">(${fn:length(sessionScope.Shopping_Car)})</font>
                                </c:when>
                                <c:otherwise>
                                    <font class="hl fl">(0)</font>
                                </c:otherwise>
                            </c:choose>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <%-- 需要显示的搜索框 --%>
    <div class="searchinput">
        <a href="javascript:void(0);" class="close" onclick="$('.searchinput').slideUp();"><i class="iconfont">&#xe605;</i></a>
        <div class="tac">
            <div class="inb posr">
                <form class="search" method="get" action="${ctx}/search.html">
                    <input type="text" placeholder="搜索产品/新闻" name="keywords" value=""><input type="submit" value="&#xe617;" class="iconfont">
                </form>
            </div>
        </div>
    </div>
</header>
<%-- 第二部分 导航部分 结束--%>