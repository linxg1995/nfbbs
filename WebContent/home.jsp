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
	<title>南方论坛 - 首页</title>

	<!-- Bootstrap CSS -->
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<!-- offcanvas CSS -->
	<link href="css/offcanvas.min.css" rel="stylesheet">
	<!-- swiper CSS -->
	<link href="css/swiper.min.css" rel="stylesheet">
	<!-- user-defined CSS -->
	<link href="css/home.css" rel="stylesheet">
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
					<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span
						class="icon-bar"></span> <span class="icon-bar"></span>
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

	<div class="container">
		<div class="row-offcanvas row-offcanvas-left">
			<div class="row">
				<!-- 轮播swiper -->
				<div id="content-swiper" class="col-md-8 col-sm-7">
					<div class="swiper-container">
						<div class="swiper-wrapper">
							<div class="swiper-slide">
								<img src="/nfbbs/img/swiper_1.jfif" class="img-responsive" alt="swiper_1">
							</div>
							<div class="swiper-slide">
								<img src="/nfbbs/img/swiper_2.jfif" class="img-responsive" alt="swiper_2">
							</div>
							<div class="swiper-slide">
								<img src="/nfbbs/img/swiper_3.jfif" class="img-responsive" alt="swiper_3">
							</div>
						</div>
						<div class="swiper-pagination"></div>
						<div class="swiper-button-prev"></div>
						<div class="swiper-button-next"></div>
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
						<li><a href="SearchServlet?post=all"> <img src="img/icon/all.png" class="img-responsive"
									alt="所有帖子">
								<span>所有帖子</span>
							</a></li>
						<li><a href="post.jsp"> <img src="img/icon/post.png" class="img-responsive" alt="发帖子">
								<span>发帖子</span>
							</a>
						</li>
						<li><a href="InfoServlet"> <img src="img/icon/user.png" class="img-responsive" alt="个人中心">
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
						<div class="col-md-5">
							<h4>发帖数</h4>
							<span>${sessionScope.user.uPost}</span>
						</div>
						<div class="col-md-7">
							<h4>回帖数</h4>
							<span>${sessionScope.user.uRepost}</span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div id="content-post" class="container">
		<div class="row">
			<div class="col-sm-6">
				<div class="page-header">
					<h3>
						<a href="SearchServlet?post=syzg">术业专攻</a>
					</h3>
				</div>
				<ul>
					<c:forEach items="${requestScope.syzgHomePostList}" var="post">
						<li><a href="DetailServlet?pId=${post.pId}">${post.pTitle}</a></li>
					</c:forEach>
				</ul>
			</div>
			<div class="col-sm-6">
				<div class="page-header">
					<h3>
						<a href="SearchServlet?post=sdrc">沙雕日常</a>
					</h3>
				</div>
				<ul>
					<c:forEach items="${requestScope.sdrcHomePostList}" var="post">
						<li><a href="DetailServlet?pId=${post.pId}">${post.pTitle}</a></li>
					</c:forEach>
				</ul>
			</div>
		</div>

		<div class="row">
			<div class="col-sm-6">
				<div class="page-header">
					<h3>
						<a href="SearchServlet?post=xytc">校园吐槽</a>
					</h3>
				</div>
				<ul>
					<c:forEach items="${requestScope.xytcHomePostList}" var="post">
						<li><a href="DetailServlet?pId=${post.pId}">${post.pTitle}</a></li>
					</c:forEach>
				</ul>
			</div>
			<div class="col-sm-6">
				<div class="page-header">
					<h3>
						<a href="SearchServlet?post=lans">恋爱那事</a>
					</h3>
				</div>
				<ul>
					<c:forEach items="${requestScope.lansHomePostList}" var="post">
						<li><a href="DetailServlet?pId=${post.pId}">${post.pTitle}</a></li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>

	<div id="content-copyright" class="container">
		<hr>
		<span>Copyright©2019- 南方论坛 All rights reserved.</span> <span>LXG
			版权所有</span>
	</div>

	<!-- offcanvas JavaScript -->
	<script src="js/offcanvas.js"></script>
	<!-- swiper JavaScript -->
	<script src="js/swiper.min.js"></script>
	<!-- user-defined JavaScript -->
	<script src="js/home.js"></script>
</body>

</html>