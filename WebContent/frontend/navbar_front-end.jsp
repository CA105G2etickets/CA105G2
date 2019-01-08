<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<title>ETIckeTs娛樂</title>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<!--[if lt IE 9]>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->

<style>
.memberphoto {
	border-radius: 50px;
	margin-top: 20px;
}

.membermenu {
	margin-top: 100px;
	margin-left: 200px;
}
.topnav {
	list-style-type: none;
	margin: 0;
	padding: 0;
	overflow: hidden;
	display: block;
	color: black;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
}
</style>


</head>
<body>

<nav class="navbar navbar-default" role="navigation">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
						<span class="sr-only">選單切換</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a href="/CA105G2/frontend/index.jsp">
					<img src="/CA105G2/frontend/LOGO_front-end.png" alt="LOGO" width="202.25px" height="165.5px">
					</a>
				</div>
				
				<!-- 手機隱藏選單區 -->
				
				<div class="collapse navbar-collapse navbar-ex1-collapse">
					<!-- 右選單 -->
					<img src="https://scontent-hkg3-1.xx.fbcdn.net/v/t1.0-1/c53.53.662.662a/s160x160/996812_623306544360262_513913499_n.jpg?_nc_cat=109&_nc_eui2=AeEvi_vj3AZ5wk2s31mtunvrLPbVPtJK2jf7uWRYtFCuPw_M1yTd23yuh2AGeVu5aGSm_1aLOh_81tqazaXh-ECnpuFl77aq8E38y3WIOxRGcA&_nc_ht=scontent-hkg3-1.xx&oh=c8b216f2429b70114bdb941b525f73cf&oe=5CA0CFE7" class="memberphoto" href="#"  alt="LOGO" style="float:right" width="80px" height="80px">
				
					<ul class="nav navbar-nav navbar-right membermenu">
						<li><a href="#">登出</a></li>
						<li><a href="#">個人設定</a></li>
<!-- 						<li class="dropdown"> -->
<!-- 							<a href="#" class="dropdown-toggle" data-toggle="dropdown">繁體中文 <b class="caret"></b></a> -->
<!-- 							<ul class="dropdown-menu"> -->
<!-- 								<li><a href="#">繁體中文</a></li> -->
<!-- 								<li><a href="#">English</a></li> -->
<!-- 								<li><a href="#">日本語</a></li> -->
<!-- 							</ul> -->
<!-- 						</li> -->
					</ul>
				</div>
				<!-- 手機隱藏選單區結束 -->
			</div>
			<div class="container">
			<div class="topnav">
			<font size="4">
			<div class="col-xs-12 col-sm-6 col-md-4 col-lg-2">
				<div>
					<a href="/CA105G2/frontend/event_title/selectEventTitle.jsp" class="topnav" align="center">所有活動</a>
				</div>
			</div>
			<div class="col-xs-12 col-sm-6 col-md-4 col-lg-2">
				<div>
					<a href="#" class="topnav" align="center">轉讓票券</a>
				</div>
			</div>
			<div class="col-xs-12 col-sm-6 col-md-4 col-lg-2">
				<div>
					<a href="#" class="topnav" align="center">周邊商品</a>
				</div>
			</div>
			<div class="col-xs-12 col-sm-6 col-md-4 col-lg-2">
				<div>
					<a href="#" class="topnav" align="center">所有團購</a>
				</div>
			</div>
			<div class="col-xs-12 col-sm-6 col-md-4 col-lg-2">
				<div>
					<a href="/CA105G2/frontend/faq/listAllFaq.jsp" class="topnav" align="center">常見問題</a>
				</div>
			</div>
			<div class="col-xs-12 col-sm-6 col-md-4 col-lg-2">
				<div>
					<a href="#" class="topnav" align="center">訂單查詢</a>
				</div>
			</div>
			</font>
		</div>
		</div>
		</nav>


</body>
</html>