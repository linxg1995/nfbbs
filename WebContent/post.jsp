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
	<title>南方论坛 - 发帖</title>

	<!-- Bootstrap CSS -->
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<!-- offcanvas CSS -->
	<link href="css/offcanvas.min.css" rel="stylesheet">
	<!-- user-defined CSS -->
	<link href="css/post.css" rel="stylesheet">
	<!-- jQuery -->
	<script src="js/jquery-3.3.1.min.js"></script>
	<!-- Bootstrap JavaScript -->
	<script src="js/bootstrap.min.js"></script>
</head>

<body>
	<!-- 用于mobile的侧边栏容器 -->
	<div class="overlay"></div>

	<!-- 导航栏 -->
	<nav class="navbar navbar-inverse navbar-static-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed pull-left" data-toggle="offcanvas">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand">南方论坛</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse sidebar-offcanvas">
				<ul class="nav navbar-nav">
					<li class="active"><a href="HomeServlet">论坛首页</a></li>
					<li><a href="SearchServlet?post=syzg">术业专攻</a></li>
					<li><a href="SearchServlet?post=sdrc">沙雕日常</a></li>
					<li><a href="SearchServlet?post=xytc">校园吐槽</a></li>
					<li><a href="SearchServlet?post=lans">恋爱那事</a></li>
					<li><a href="LogServlet?log=out">退出登录</a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</nav>

	<!-- 移动端小面板 -->
	<div id="content-mobilePanel" class="container">
		<div class="btn-group">
			<button type="button" class="btn btn-default"
				onclick="window.location.href='SearchServlet?post=all'">所有帖子</button>
			<button type="button" class="btn btn-default" onclick="window.location.href='post.jsp'">发帖子</button>
			<button type="button" class="btn btn-default" onclick="window.location.href='InfoServlet'">个人中心</button>
		</div>
	</div>

	<div class="container">
		<div class="row">
			<!-- 发帖栏 -->
			<div id="content-post" class="col-md-8 col-sm-7">
				<form action="PostServlet?post=post" method="POST" class="form-horizontal" role="form" onsubmit="beforeSubmit()">
					<!-- 标题 -->
					<div class="form-group">
						<label class="col-sm-2 control-label">标题</label>
						<div class="col-sm-10">
							<input name="pTitle" type="text" class="form-control" maxlength="20" required="required"
								placeholder="不超过20字">
						</div>
					</div>
					<!-- 类别 -->
					<div class="form-group">
						<label class="col-sm-2 control-label">类别</label>
						<div class="col-sm-10">
							<select name="pType" class="form-control" required="required">
								<option value="术业专攻">术业专攻</option>
								<option value="沙雕日常">沙雕日常</option>
								<option value="校园吐槽">校园吐槽</option>
								<option value="恋爱那事">恋爱那事</option>
							</select>
						</div>
					</div>
					<!-- 内容 -->
					<div class="form-group">
						<label class="col-sm-2 control-label">内容</label>
						<div class="col-sm-10">
							<textarea name="pContent" class="form-control" rows="10" maxlength="400" required="required"
								placeholder="不超过400字"></textarea>
						</div>
					</div>
					<!-- 提交按钮 -->
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn btn-default">发帖</button>
						</div>
					</div>
				</form>
			</div>

			<!-- 小面板 -->
			<div id="content-panel" class="col-md-4 col-sm-5">
				<form action="SearchServlet" method="POST" role="form">
					<div class="form-group">
						<input name="post" type="text" class="form-control" placeholder="帖子关键词">
					</div>
					<button type="submit" class="btn btn-primary">Squirtle!</button>
				</form>
				<ul>
					<li><a href="SearchServlet?post=all">
							<img src="img/icon/all.png" class="img-responsive" alt="所有帖子">
							<span>所有帖子</span>
						</a>
					</li>
					<li><a href="post.jsp">
							<img src="img/icon/post.png" class="img-responsive" alt="发帖子">
							<span>发帖子</span>
						</a>
					</li>
					<li><a href="InfoServlet">
							<img src="img/icon/user.png" class="img-responsive" alt="个人中心">
							<span>个人中心</span>
						</a>
					</li>
				</ul>
				<div id="panel-headAndName" class="row">
					<div class="col-md-5 col-sm-4">
						<img src="img/head/${sessionScope.user.uHead}" class="img-responsive" alt="头像" width="40px">
					</div>
					<div class="col-md-7 col-sm-8">
						<h4>${sessionScope.user.uName}</h4>
					</div>
				</div>
				<div id="panel-postAndRepost" class="row">
					<div class="col-md-5 col-sm-4">
						<h4>发帖数</h4>
						<span>${sessionScope.user.uPost}</span>
					</div>
					<div class="col-md-7 col-sm-8">
						<h4>回帖数</h4>
						<span>${sessionScope.user.uRepost}</span>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- 底部版权 -->
	<div id="content-copyright" class="container">
		<hr>
		<span>Copyright©2019- 南方论坛 All rights reserved.</span>
		<span>LXG 版权所有</span>
	</div>

	<!-- offcanvas JavaScript -->
	<script src="js/offcanvas.js"></script>
	<!-- user-defined JavaScript -->
	<script src="js/post.js"></script>
</body>

</html>