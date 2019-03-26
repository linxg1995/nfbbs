<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>

<head>
	<base href="<%=basePath%>">
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>南方论坛 - 登录</title>

	<!-- Bootstrap CSS -->
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<!-- bootstrapValidator CSS -->
	<link href="css/bootstrapValidator.css" rel="stylesheet">
	<!-- user-defined CSS -->
	<link href="css/index.css" rel="stylesheet">
	<!-- jQuery -->
	<script src="js/jquery-3.3.1.min.js"></script>
	<!-- Bootstrap JavaScript -->
	<script src="js/bootstrap.min.js"></script>

</head>

<body>
	<div id="content-login" class="container">
		<!-- 文字logo -->
		<img src="img/index_logo.png" class="img-responsive" alt="index_logo" width="50%">

		<!-- 吉祥物logo -->
		<img src="img/jng.jfif" class="img-responsive" alt="Image" width="50%">

		<!-- 登录失败时返回警告 -->
		<%
			if (request.getParameter("log") != null) {
				if ("error".equals(request.getParameter("log"))) {
		%>
		<div class="alert alert-danger">学号或密码错误！请进行更改。</div>
		<%
			}
			}
		%>

		<!-- 登录表单 -->
		<form action="LogServlet?log=in" method="POST" role="form">
			<div class="form-group">
				<input name="uId" type="text" class="form-control" placeholder="学号">
			</div>
			<div class="form-group">
				<input name="uPassword" type="password" class="form-control" placeholder="密码">
			</div>
			<button type="submit" class="btn btn-primary btn-block">登录</button>
		</form>

	</div>

	<!-- bootstrapValidator JavaScript -->
	<script src="js/bootstrapValidator.js"></script>
	<!-- user-defined JavaScript -->
	<script src="js/index.js"></script>
</body>

</html>