<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.administrator.model.*"%>

<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<title>ETIckeTs娛樂後台管理</title>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<!--[if lt IE 9]>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->

<style>
.administratorphoto {
	border-radius: 50px;
	margin-top: 20px;
}
.administratormenu {
	margin-top: 100px;
	margin-left: 200px;
}
.topnav {
	list-style-type: none;
	margin: 0;
	padding: 0;
	overflow: visible;
	display: block;
	color: white;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
	cursor: pointer;
}
body{
	font-family:微軟正黑體!important;
}
</style>
</head>
<body>
	<nav class="navbar navbar-inverse" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-ex1-collapse">
					<span class="sr-only">選單切換</span> <span class="icon-bar"></span> <span
						class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<a href="<%=request.getContextPath()%>/backend/index.jsp">
				<img src="/CA105G2/backend/LOGO_back-end.png" href="#" alt="LOGO"
					width="202.25px" height="165.5px">
				</a>
			</div>

			<!-- 手機隱藏選單區  -->

			<div class="collapse navbar-collapse navbar-ex1-collapse">
				<!-- 右選單 -->
				<a href="#">
					<%if (session.getAttribute("administratorVO") == null) {%>
						<img src="https://a.wattpad.com/useravatar/user50478190.256.326933.jpg" class="administratorphoto" alt="administratorphoto" style="float:right" width="80px" height="80px">
					<% } else {%>
						<img src="<%=request.getContextPath()%>/administrator/administratorImg.do?administrator_no=${administratorVO.administrator_no}" class="administratorphoto" alt="administratorphoto" style="float:right" width="80px" height="80px">
					<%}%>
<%-- 					<img src="${member eq null ? "https://a.wattpad.com/useravatar/user50478190.256.326933.jpg" : "<%=request.getContextPath()%>/member/memberImg.do?memberno=<%=request.getContextPath()%>"}" class="memberphoto" alt="memberphoto" style="float:right" width="80px" height="80px"> --%>
				</a>
				
				<ul class="nav navbar-nav navbar-right administratormenu">
<%-- 					<%if (session.getAttribute("member") == null) {%> --%>
						
<%-- 					<% } else {%> --%>
<%-- 						<li><a href="#">電子錢包餘額<font color="orange">${member.ewalletBalance}</font></a></li> --%>
<%-- 					<%}%> --%>
						
						
						<li>
						<%if (session.getAttribute("administratorVO") == null) {%>
						<a href="/CA105G2/backend/login_back-end.jsp"><i class="glyphicon glyphicon-user"></i>&nbsp;&nbsp;&nbsp;管理員登入</font></a>
						<% } else {%>
						<a>
						<form  METHOD="post" ACTION="/CA105G2/administrator/administrator.do">
                                <input type="hidden" name="action" value="administrator_Logout">
								<i class="glyphicon glyphicon-log-out"></i>
								<input type="submit" value="管理員登出" style="border: none ; background: none">
                        </form>
						</a>
						<%}%>
<%-- 						<a href="${member eq null ? "/CA105G2/frontend/login_front-end.jsp" : "/CA105G2/frontend/index.jsp"}"><input type="hidden" name="action" value="member_Logout"> ${member eq null ? "登入" : "登出"} </a> --%>
						</li>
					
					<!-- 				<li class="dropdown"><a href="#" class="dropdown-toggle" -->
					<!-- 					data-toggle="dropdown">繁體中文 <b class="caret"></b></a> -->
					<!-- 					<ul class="dropdown-menu"> -->
					<!-- 						<li><a href="#">繁體中文</a></li> -->
					<!-- 						<li><a href="#">English</a></li> -->
					<!-- 						<li><a href="#">日本語</a></li> -->
					<!-- 					</ul> -->
					<!-- 				</li> -->
				</ul>
			</div>
			<!-- 手機隱藏選單區結束 -->
		</div>
		<div class="container">
		<div class="topnav row">
		<font size="4">
			<div class="col-xs-12 col-sm-6 col-md-4 col-lg-2">
				<div class="dropdown">
					<a class="dropdown-toggle topnav" id="eventManagement" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true" align="center">活動管理
						<span class="caret"></span>
					</a>
			        <ul class="dropdown-menu eventManagementMenu" aria-labelledby="eventManagement" style="text-align:center;background-color:rgba(255, 255, 255, 0.9);">
			            <li><a href="<%=request.getContextPath()%>/backend/event_title/listAllEventTitleRelatives.jsp" target="_blank">活動管理</a></li>
			            <li><a href="<%=request.getContextPath()%>/backend/venue/listAllVenue.jsp" target="_blank">場地管理</a></li>
			            <li><a href="<%=request.getContextPath()%>/backend/advertisement/listAllAdvertisement.jsp" target="_blank">廣告管理</a></li>
			            <li><a href="<%=request.getContextPath()%>/backend/event/changeNotice.jsp" target="_blank">異動通知</a></li>
			        </ul>
				</div>			
			</div>
			<div class="col-xs-12 col-sm-6 col-md-4 col-lg-2">
				<div>
					<a href="#" class="topnav" align="center">票券管理</a>
				</div>
			</div>
			<div class="col-xs-12 col-sm-6 col-md-4 col-lg-2">
				<div>
					<a href="#" class="topnav" align="center">商品管理</a>
				</div>
			</div>
			<div class="col-xs-12 col-sm-6 col-md-4 col-lg-2">
				<div>
					<a href="#" class="topnav" align="center">團購管理</a>
				</div>
			</div>
			<div class="col-xs-12 col-sm-6 col-md-4 col-lg-2">
				<div>
					<a href="<%=request.getContextPath()%>/backend/faq/select_page.jsp" class="topnav" align="center">常見問題管理</a>
				</div>
			</div>
			<div class="col-xs-12 col-sm-6 col-md-4 col-lg-2">
				<div>
					<a href="<%=request.getContextPath()%>/backend/news/select_page.jsp" class="topnav" align="center">公告管理</a>
				</div>
			</div>
			</font>
		</div>
		</div>
	</nav>
<!-- <script src="https://code.jquery.com/jquery.js"></script> -->
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>	 -->
</body>
</html>