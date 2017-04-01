<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>禁止操作!!</title>
		<%@include file="/WEB-INF/mgr/jsp/inc/import.jsp" %>
		<style type="text/css">
			body {
	            font-family: arial, helvetica, sans-serif;
	            background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAoAAAAKCAYAAACNMs+9AAAAUElEQVQYV2NkYGAwBuKzQAwDID4IoIgxIikAMZE1oRiArBDdZBSNMIXoJiFbDZYDKcSmCOYimDuNSVKIzRNYrUYOFuQgweoZbIoxgoeoAAcAEckW11HVTfcAAAAASUVORK5CYII=) repeat;
	            background-color: #212121;
	            color: white;
	            font-size: 28px;
	            padding-bottom: 20px;
	        }
			.error-code {
	            font-family: 'Creepster', cursive, arial, helvetica, sans-serif;
	            font-size: 70px;
	            color: white;
	            color: rgba(255, 255, 255, 0.98);
	            width: 90%;
	            text-align: right;
	            margin-top: 5%;
	            text-shadow: 5px 5px hsl(0, 0%, 25%);
	            float: left;
	        }
	         .content {
	            text-align: center;
	            line-height: 30px;
	        }
	        .clear {
	            float: none;
	            clear: both;
	        }
	        a {
	            text-decoration: none;
	            color: #9ECDFF;
	            text-shadow: 0px 0px 2px white;
	        }
	
	        a:hover {
	            color: white;
	        }
		</style>
	</head>
	<body>
		<p class="error-code">
			您无权查看此页面或进行此操作！
		</p>
		<div class="clear"></div>
		<div class="content">
			<div class="reindex">
				<a href="${ctx}" >请登录</a>
			</div>
		</div>
	</body>
</html>