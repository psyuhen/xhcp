<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="ccb erm system">
    <meta name="author" content="ccb">
    <%--可引入文件(请在Attribute中填写，多个用逗号分开)--%>
    <%request.setAttribute("import", "html5js,bootstrap,bootstrap_cerulean,charisma_app,jquery_dataTables,datetimepicker,jquery_noty,dateUtil,tableSupport,dialog,jquery_cookie,stringutil,form,fileupload,colorbox"); %>
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
            <% request.setAttribute("module_id", "indexmgr"); %>
            <%@include file="/WEB-INF/mgr/jsp/main/nav.jsp" %>

            <%-- 首页LOGO --%>
            <form id="importForm" action="${ctx}/mgr/index/indexupload" enctype="multipart/form-data" method="post">
            <div class="row">
                <div class="box col-md-12">
                    <div class="box-inner">
                        <div class="box-content">
                            <div class="page-header">
                                <h4>首页LOGO
                                    <small>可以在此更换logo(图片大小:100 × 53)</small>
                                </h4>
                            </div>
                            <div class="row" id="logooper">
                                <div class="col-md-4">
                                    <ul id="logoul" class="thumbnails gallery">
                                        <li id="logophoto" class="thumbnail">
                                            <c:choose>
                                                <c:when test="${logofile != null}">
                                                    <a id="${logofile.file_id}" style="background:url(${ctx}/${logofile.file_path}${logofile.name})"
                                                       title="佰瑞林陈皮,佰瑞林,陈皮" href="${ctx}/${logofile.file_path}${logofile.name}">
                                                        <img class="grayscale" src="${ctx}/${logofile.file_path}${logofile.name}" alt="佰瑞林陈皮,佰瑞林,陈皮">
                                                    </a>
                                                </c:when>
                                                <c:otherwise>
                                                    <a style="background:url(${ctx}/css/images/content_add_image_icon.png)"
                                                       title="添加图片" href="${ctx}/css/images/content_add_image_icon.png">
                                                        <img class="grayscale" src="${ctx}/css/images/content_add_image_icon.png" alt="添加图片">
                                                    </a>
                                                </c:otherwise>
                                            </c:choose>
                                        </li>
                                    </ul>
                                    <div class="input-group">
                                        <input type="file" name="uploadFile" id="uploadFile" class="form-control" style="display:none;" />
                                        <input type="hidden" name="uploadFile_file_type" value="0"/>
                                        <input type="hidden" name="uploadFile_file_suffix" value="0"/>
                                    </div>
                                    <div id="progress" class="progress progress-striped progress-success active" style="display:none;">
                                        <div class="progress-bar" style="width: 0%;" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100"></div>
                                    </div>
                                    <input type="button" style="display: none" id="upload" value="upload"/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            </form>
            <%-- 首页图片 --%>
            <form id="indexphotoForm" action="${ctx}/mgr/index/indexupload" enctype="multipart/form-data" method="post">
            <div class="row">
                <div class="box col-md-12">
                    <div class="box-inner">
                        <div class="box-content">
                            <div class="page-header">
                                <h4>首页轮换图片
                                    <small>可以在此更换首页显示的轮换图片(1920 × 868)</small>
                                </h4>
                            </div>
                            <div class="row" id="photochange">
                                <div class="col-md-12">
                                    <ul id="indexphotoul" class="thumbnails gallery">
                                        <%-- 加载已存在的图片 --%>
                                        <c:forEach items="${indexphotos}" var="ph">
                                            <li id="image-1" class="thumbnail">
                                                <a id="${ph.file_id}" style="background:url(${ctx}/${ph.file_path}${ph.name})"
                                                   title="佰瑞林陈皮,佰瑞林,陈皮" href="${ctx}/${ph.file_path}${ph.name}">
                                                    <img class="grayscale" src="${ctx}/${ph.file_path}${ph.name}" alt="佰瑞林陈皮,佰瑞林,陈皮">
                                                </a>
                                            </li>
                                        </c:forEach>
                                        <%-- 循环几个空的 --%>
                                        <c:forEach begin="1" end="${indexphotoslength}" step="1" var="x">
                                            <li id="image-${x}" class="thumbnail">
                                                <a style="background:url(${ctx}/css/images/content_add_image_icon.png)"
                                                   title="添加图片" href="${ctx}/css/images/content_add_image_icon.png">
                                                    <img class="grayscale" src="${ctx}/css/images/content_add_image_icon.png" alt="添加图片">
                                                </a>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                    <div class="input-group">
                                        <input type="file" name="uploadFile" id="indexphotofile" class="form-control" style="display:none;" />
                                        <input type="hidden" name="uploadFile_file_type" value="1"/>
                                        <input type="hidden" name="uploadFile_file_suffix" value="0"/>
                                    </div>
                                    <div id="progress1" class="progress progress-striped progress-success active" style="display:none;">
                                        <div class="progress-bar" style="width: 0%;" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100"></div>
                                    </div>
                                    <input type="button" style="display: none" id="indexphotobtn" value="upload"/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            </form>

            <%-- 首页视频 --%>
            <form id="videoForm" action="${ctx}/mgr/index/indexupload" enctype="multipart/form-data" method="post">
            <div class="row">
                <div class="box col-md-12">
                    <div class="box-inner">
                        <div class="box-content">
                            <div class="page-header">
                                <h4>首页视频
                                    <small>可以在此更换首页视频(文件大小不能过大(100MB)</small>
                                </h4>
                            </div>
                            <div class="row" id="videomgr">
                                <div class="col-md-4">
                                    <video id="videoinfo" file_id="${videoinfo.file_id}" src="${ctx}/${videoinfo.file_path}/${videoinfo.name}"  width="320" height="240" controls="controls">
                                        Your browser does not support the video tag.
                                    </video>
                                    <div class="input-group">
                                        <input type="file" name="uploadFile" id="videofile" class="form-control" style="display:none;" />
                                        <input type="hidden" name="uploadFile_file_type" value="2"/>
                                        <input type="hidden" name="uploadFile_file_suffix" value="1"/>
                                    </div>
                                    <div id="progress2" class="progress progress-striped progress-success active" style="display:none;">
                                        <div class="progress-bar" style="width: 0%;" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100"></div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <input type="button" id="videobtn" value="更换" class="btn btn-primary btn-sm"/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            </form>

            <%-- 资讯图片 --%>
            <form id="newsForm" action="${ctx}/mgr/index/indexupload" enctype="multipart/form-data" method="post">
            <div class="row">
                <div class="box col-md-12">
                    <div class="box-inner">
                        <div class="box-content">
                            <div class="page-header">
                                <h4>首页资讯
                                    <small>可以在此更换资讯图片(图片大小:692 × 783)</small>
                                </h4>
                            </div>
                            <div class="row" id="newsoper">
                                <div class="col-md-4">
                                    <ul id="newsul" class="thumbnails gallery">
                                        <li id="newsphoto" class="thumbnail">
                                            <c:choose>
                                                <c:when test="${newsphoto != null}">
                                                    <a id="${newsphoto.file_id}" style="background:url(${ctx}/${newsphoto.file_path}${newsphoto.name})"
                                                       title="佰瑞林陈皮,佰瑞林,陈皮" href="${ctx}/${newsphoto.file_path}${newsphoto.name}">
                                                        <img class="grayscale" src="${ctx}/${newsphoto.file_path}${newsphoto.name}" alt="佰瑞林陈皮,佰瑞林,陈皮">
                                                    </a>
                                                </c:when>
                                                <c:otherwise>
                                                    <a style="background:url(${ctx}/css/images/content_add_image_icon.png)"
                                                       title="添加图片" href="${ctx}/css/images/content_add_image_icon.png">
                                                        <img class="grayscale" src="${ctx}/css/images/content_add_image_icon.png" alt="添加图片">
                                                    </a>
                                                </c:otherwise>
                                            </c:choose>
                                        </li>
                                    </ul>
                                    <div class="input-group">
                                        <input type="file" name="uploadFile" id="newsFile" class="form-control" style="display:none;" />
                                        <input type="hidden" name="uploadFile_file_type" value="3"/>
                                        <input type="hidden" name="uploadFile_file_suffix" value="0"/>
                                    </div>
                                    <div id="progress3" class="progress progress-striped progress-success active" style="display:none;">
                                        <div class="progress-bar" style="width: 0%;" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100"></div>
                                    </div>
                                    <input type="button" style="display: none" id="newsupload" value="upload"/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            </form>
            <%-- content ends --%>
        </div><%--/#content.col-md-0--%>
    </div><%--/fluid-row--%>

    <hr>
    <%@include file="/WEB-INF/mgr/jsp/main/footer.jsp" %>

</div><%--/.fluid-container--%>


<%-- 加载JS --%>
<script src="${ctx}/js/system/IndexMgr.${__min}js"></script>
<script type="text/javascript">
    $(document).ready(function(){
        var indexMgr = new IndexMgr();
    });
</script>
</body>
</html>