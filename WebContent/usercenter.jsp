<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<title>南方论坛 - 个人中心</title>

	<!-- Bootstrap CSS -->
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<!-- offcanvas CSS -->
	<link href="css/offcanvas.min.css" rel="stylesheet">
	<!-- user-defined CSS -->
	<link href="css/usercenter.css" rel="stylesheet">
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
			<div id="content-usercenter" class="col-md-8 col-sm-7">
				<img src="img/head/${sessionScope.user.uHead}" class="img-responsive" alt="头像" width="100px">
				<h3>${sessionScope.user.uName}</h3>
				<div role="tabpanel">
					<!-- Nav tabs -->
					<ul class="nav nav-tabs" role="tablist">
						<li role="presentation" class="active">
							<a href="#userInfo" aria-controls="userInfo" role="tab" data-toggle="tab">个人信息</a>
						</li>
						<li role="presentation">
							<a href="#userPost" aria-controls="userPost" role="tab" data-toggle="tab">发帖
								${sessionScope.user.uPost}</a>
						</li>
						<li role="presentation">
							<a href="#userRepost" aria-controls="userRepost" role="tab" data-toggle="tab">回帖
								${sessionScope.user.uRepost}</a>
						</li>
					</ul>
					<!-- Tab panes -->
					<div class="tab-content">
						<!-- 个人信息 -->
						<div role="tabpanel" class="tab-pane active" id="userInfo">
							<form action="InfoServlet?info=basic" method="POST" class="form-horizontal" role="form">
								<div class="form-group">
									<label class="col-xs-2 control-label">学号</label>
									<div class="col-xs-10 col-sm-7">
										<input name="uId" type="text" class="form-control" disabled
											value="${sessionScope.user.uId}">
									</div>
								</div>
								<div class="form-group">
									<label class="col-xs-2 control-label">密码</label>
									<div class="col-xs-10 col-sm-7">
										<input name="uPassword" type="password" class="form-control"
											value="${sessionScope.user.uPassword}" maxlength="20" required="required" placeholder="不超过20字符">
									</div>
								</div>
								<div class="form-group">
									<label class="col-xs-2 control-label">昵称</label>
									<div class="col-xs-10 col-sm-7">
										<input name="uName" type="text" class="form-control"
											value="${sessionScope.user.uName}" required="required">
									</div>
								</div>
								<div class="form-group">
									<div class="col-xs-offset-2 col-xs-2">
										<button type="submit" class="btn btn-default">保存修改</button>
									</div>
								</div>
							</form>
							<hr>
							<form action="InfoServlet?info=head" method="POST" class="form-horizontal" role="form" enctype="multipart/form-data">
								<div class="form-group">
									<label class="col-xs-2 control-label">头像</label>
									<div class="col-xs-10 col-sm-7">
										<input name="uHead" type="file" class="form-control" required="required">
									</div>
								</div>
								<div class="form-group">
									<div class="col-xs-offset-2 col-xs-2">
										<button type="submit" class="btn btn-default">保存修改</button>
									</div>
								</div>
							</form>
							<hr>
						</div>

						<!-- 发帖 -->
						<div role="tabpanel" class="tab-pane" id="userPost">
							<c:forEach items="${requestScope.postList}" var="post">
								<div class="userPost-item">
									<h4><a href="DetailServlet?pId=${post.pId}"><strong>${post.pTitle}</strong></a></h4>
									<h4 class="item-nameAndTime"><small>${post.pTime}</small></h4>
									<span class="item-text">${post.pContent}</span>
									<div class="item-delete">
										<a href="#">删除</a>
									</div>
								</div>
								<hr>
							</c:forEach>
						</div>

						<!-- 回帖 -->
						<div role="tabpanel" class="tab-pane" id="userRepost">
							<c:forEach items="${requestScope.repostList}" var="repost" varStatus="loop">
								<div class="userRepost-item">
									<h4><a
											href="DetailServlet?pId=${repost.rpPid}"><strong>${requestScope.repostListTitle[loop.count-1]}</strong></a>
									</h4>
									<h4 class="item-nameAndTime"><small>${repost.rpTime}</small></h4>
									<span class="item-text">${repost.rpContent}</span>
									<div class="item-delete">
										<a href="#">删除</a>
									</div>
								</div>
								<hr>
							</c:forEach>
						</div>
					</div>
				</div>
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
			</div>
		</div>
	</div>

	<!-- 底部版权 -->
	<div id="content-copyright" class="container">
		<span>Copyright©2019- 南方论坛 All rights reserved.</span>
		<span>LXG 版权所有</span>
	</div>

	<!-- offcanvas JavaScript -->
	<script src="js/offcanvas.js"></script>
	<!-- user-defined JavaScript -->
	<script src="js/usercenter.js"></script>
</body>

</html>