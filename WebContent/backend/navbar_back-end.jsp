<%@	page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%@	page import="com.administrator.model.*"%>
<%@	page import="com.permission.model.*"%>

<%
if (session.getAttribute("administratorVO") != null) {
	PermissionService permissionService = new PermissionService();
	List<String> theAdministratorPermission = permissionService.findPermissionByAdministratorNo(((AdministratorVO) session.getAttribute("administratorVO")).getAdministrator_no());
	pageContext.setAttribute("theAdministratorPermission", theAdministratorPermission);
%>
<%
	for (String permission : theAdministratorPermission) {
		if (permission.equals("PL05")) {
%>
		return true;
<% } %>
		return false;
<% }
}
%>

<c:forEach var="permission" items="${theAdministratorPermission}">
	${permission}
</c:forEach>

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
	position: absolute;
	margin-top: 30;
	z-index:1;
}
.administratormenu {
	margin-top: 8em;
	margin-left: 20%;
}
.administratorPhotoEdit {
	position: absolute;
	margin-top: 30;
	visibility: hidden;
}
.navbaradministratorbtn {
	position: absolute;
	margin-bottom: 0;
	padding: 1px;
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
.topnav :hover {
    text-decoration: none;
    color: white;
}
.topnavbtn a:after {
    content: ' ';
    position: absolute;
    z-index: 2;
    bottom: 0;
    left: 50%;
    display: block;
    width: 90%;
    transform: translate(-50%);
}
.topnavbtn a:hover:after {
    height: 4px;
    animation: ad_width .2s linear forwards;
    background: #d0d0d0;
}
@keyframes ad_width {
    from {
        width: 0
    }
    to {
        width: 90%
    }
}
body{
	font-family:微軟正黑體!important;
}
</style>

</head>

<body>

<nav class="navbar navbar-inverse" role="navigation">
	<div class="container">
		<div class="row">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-ex1-collapse">
					<span class="sr-only">選單切換</span> <span class="icon-bar"></span> <span
						class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<a href="<%=request.getContextPath()%>/backend/index.jsp">
				<img src="<%=request.getContextPath()%>/backend/LOGO_back-end.png" alt="back-endLOGO"
					width="202.25px" height="165.5px">
				</a>
			</div>

			<!-- 手機隱藏選單區  -->

			<div class="collapse navbar-collapse navbar-ex1-collapse">
				<!-- 右選單 -->
				<%if (session.getAttribute("administratorVO") == null) {%>
					<a href="<%=request.getContextPath()%>/backend/login_back-end.jsp">
					<div style="float:right; margin-right: 5%">
					<img src="https://a.wattpad.com/useravatar/user50478190.256.326933.jpg" class="administratorphoto" alt="administratorphoto" width="80px" height="80px">
					</div>
					</a>
				<% } else {%>
					<a href="<%=request.getContextPath()%>/backend/administrator/administrator_profile.jsp">
					<div style="float:right; margin-right: 5%">
					<img src="<%=request.getContextPath()%>/administrator/administratorImg.do?administrator_no=${administratorVO.administrator_no}" class="administratorphoto" alt="administratorphoto" width="80px" height="80px">
					<img src="<%=request.getContextPath()%>/frontend/member/images/編輯.png" class="administratorPhotoEdit" width="80px" height="80px">
					</div>
					</a>
				<%}%>
				
				<ul class="nav navbar-nav navbar-right administratormenu">
<%-- 					<%if (session.getAttribute("member") == null) {%> --%>
						
<%-- 					<% } else {%> --%>
<%-- 						<li><a href="#">電子錢包餘額<font color="orange">${member.ewalletBalance}</font></a></li> --%>
<%-- 					<%}%> --%>
						
						
						<li style="padding: 0; margin-top: 0">
						<%if (session.getAttribute("administratorVO") == null) {%>
						<a href="<%=request.getContextPath()%>/backend/login_back-end.jsp" class="navbaradministratorbtn" style="padding-bottom: 0; padding-top: 30"><i class="glyphicon glyphicon-user"></i>&nbsp;&nbsp;&nbsp;管理員登入</font></a>
						<% } else {%>
						<a class="navbarmemberbtn" style="padding-bottom: 0; padding-top: 30">
						<form  METHOD="post" ACTION="/CA105G2/administrator/administrator.do" style="margin: 0">
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
	</div>
		<div class="container">
		<div class="topnav row">
		<font size="4">
			<div class="col-xs-12 col-sm-6 col-md-4 col-lg-2 topnavbtn">
				<div class="dropdown">
					<a class="dropdown-toggle topnav" id="eventManagement" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true" align="center" style="text-decoration: none; color: white;">活動管理
						<span class="caret"></span>
					</a>
			        <ul class="dropdown-menu eventManagementMenu" aria-labelledby="eventManagement" style="text-align:center;background-color:rgba(255, 255, 255, 0.9);">
			            <li><a href="<%=request.getContextPath()%>/backend/event_title/listAllEventTitleRelatives.jsp" target="_blank">活動管理</a></li>
			            <li><a href="<%=request.getContextPath()%>/backend/venue/listAllVenue.jsp" target="_blank">場地管理</a></li>
			            <li><a href="<%=request.getContextPath()%>/backend/advertisement/listAllAdvertisement.jsp" target="_blank">廣告管理</a></li>
			            <li><a href="<%=request.getContextPath()%>/backend/event/changeNotice.jsp" target="_blank">訊息通知</a></li>
			        </ul>
				</div>			
			</div>
			<div class="col-xs-12 col-sm-6 col-md-4 col-lg-2 topnavbtn">
				<div>
					<a href="<%=request.getContextPath()%>/backend/ticket/select_page.jsp" class="topnav" align="center">票券管理</a>
				</div>
			</div>
			<div class="col-xs-12 col-sm-6 col-md-4 col-lg-2 topnavbtn">
				<div class="dropdown">
					<a class="dropdown-toggle topnav" id="GoodsManagement" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true" align="center" style="text-decoration: none; color: white;">商品管理
						<span class="caret"></span>
					</a>
			        <ul class="dropdown-menu eventManagementMenu" aria-labelledby="eventManagement" style="text-align:center;background-color:rgba(255, 255, 255, 0.9);">
			            <li><a href="<%=request.getContextPath()%>/backend/goods/select_page.jsp" align="center">商品紀錄查詢</a></li>
			            <li><a href="<%=request.getContextPath()%>/backend/favorite_goods/selectFavoriteGoods.jsp" align="center">最愛商品管理</a></li>
			            <li><a href="<%=request.getContextPath()%>/backend/order_history/selectOrder.jsp" align="center">訂單紀錄管理</a></li>
			        </ul>
				</div>			
			</div>
			<div class="col-xs-12 col-sm-6 col-md-4 col-lg-2 topnavbtn">
				<div class="dropdown">
    				<a class="dropdown-toggle topnav" data-toggle="dropdown" style="text-decoration: none; color: white;">其他
    				<span class="caret"></span>
    				</a>
    					<ul class="dropdown-menu" style="text-align: center;">
      						<li class="dropdown-header">公告管理</li>
      						<li><a href="<%=request.getContextPath()%>/backend/news/allNews.jsp">查詢/修改公告</a></li>
     						<li><a href="<%=request.getContextPath()%>/backend/news/addNews.jsp">新增公告</a></li>
      						<li class="divider"></li>
      						<li class="dropdown-header">常見問題管理</li>
      						<li><a href="<%=request.getContextPath()%>/backend/faq/allFaq.jsp">查詢/修改常見問題</a></li>
      						<li><a href="<%=request.getContextPath()%>/backend/faq/addFaq.jsp">新增常見問題</a></li>
    					</ul>
 	 			</div>
			</div>
			<div class="col-xs-12 col-sm-6 col-md-4 col-lg-2 topnavbtn">
				<div class="dropdown">
    				<a class="dropdown-toggle topnav" data-toggle="dropdown" style="text-decoration: none; color: white;">會員管理
    				<span class="caret"></span>
    				</a>
    					<ul class="dropdown-menu" style="text-align: center;">
      						<li><a href="<%=request.getContextPath()%>/backend/member/allMember.jsp">查詢/修改會員資料</a></li>
     						<li><a href="<%=request.getContextPath()%>/backend/member/addMember.jsp">新增會員</a></li>
    					</ul>
 	 			</div>
			</div>
			<div class="col-xs-12 col-sm-6 col-md-4 col-lg-2 topnavbtn">
				<div class="dropdown">
    				<a class="dropdown-toggle topnav" data-toggle="dropdown" style="text-decoration: none; color: white;">管理員管理
    				<span class="caret"></span>
    				</a>
    					<ul class="dropdown-menu" style="text-align: center;">
      						<li><a href="<%=request.getContextPath()%>/backend/administrator/allAdministrator.jsp">查詢/修改管理員資料</a></li>
     						<li><a href="<%=request.getContextPath()%>/backend/administrator/addAdministrator.jsp">新增管理員</a></li>
    					</ul>
 	 			</div>
			</div>
			</font>
		</div>
		</div>
	</nav>

<script>
$(document).ready(function() {
		$(".administratorphoto").hover(function() {
			$(this).css("opacity","0.4");
			$(".administratorPhotoEdit").css("visibility","visible");
		},function() {
			$(this).css("opacity","1");
			$(".administratorPhotoEdit").css("visibility","hidden");
		});
});
</script>

<!-- <script src="https://code.jquery.com/jquery.js"></script> -->
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>	 -->
</body>
</html>