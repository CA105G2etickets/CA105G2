<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%@page import="com.member.model.MemberVO"%>
<%@ page import="com.shopping_cart.model.ShoppingCart" %>

<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<title>ETIckeTs娛樂</title>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<!-- <script src="http://code.jquery.com/jquery-1.12.4.min.js"></script> -->
<!--[if lt IE 9]>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->

<style>
.memberphoto {
	border-radius: 50px;
	position: absolute;
	margin-top: 30;
	z-index:1;
}
.memberPhotoEdit {
	position: absolute;
	margin-top: 30;
	visibility: hidden;
}
.membermenu {
	margin-top: 8em;
	margin-left: 20%;
}
.navbarmemberbtn {
	position: absolute;
	margin-bottom: 0;
	padding: 1px;
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
.topnav :hover {
    text-decoration: none;
}
.topnavbtn a:after {
    content: ' ';
    position: absolute;
    z-index: 2;
    bottom: 0;
    left: 50%;
    display: block;
    width: 165px;
    height: 2px;
    transform: translate(-50%);
}
.topnavbtn a:hover:after {
    height: 2px;
    animation: ad_width .2s linear forwards;
    background: #3399ff;
}
@keyframes ad_width {
    from {
        width: 0
    }
    to {
        width: 100%
    }
}
.shoppingAndFavorite {
	font-family:微軟正黑體!important;
}
/****************************************** 通知部分 ******************************************/
.badge-pill {
    padding: 0.2em;
    display: inline-block;
    padding: 0.25em 0.4em;
    font-size: 75%;
    font-weight: 700;
    line-height: 1;
    text-align: center;
    white-space: nowrap;
    vertical-align: baseline;
}
.badge-primary {
    color: white;
    background-color: #3399ff;
}
/****************************************** 通知結束 ******************************************/
</style>

</head>
<body>

<nav class="navbar navbar-default" role="navigation">
			<div class="container">
			<div class="row">
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
					<%if (session.getAttribute("member") == null) {%>
					<a href="/CA105G2/frontend/login_front-end.jsp">
					<div style="float:right; margin-right: 5%">
					<img src="https://a.wattpad.com/useravatar/user50478190.256.326933.jpg" class="memberphoto" alt="memberphoto" width="80px" height="80px">
					</div>
					</a>
					<% } else {%>
					<a href="<%=request.getContextPath()%>/frontend/member/member_profile.jsp">
					<div style="float:right; margin-right: 5%">
					<img src="<%=request.getContextPath()%>/member/memberImg.do?memberno=${member.memberNo}" class="memberphoto" alt="memberphoto" width="80px" height="80px">
					<img src="<%=request.getContextPath()%>/frontend/member/images/編輯.png" class="memberPhotoEdit" width="80px" height="80px">
					</div>
					</a>
					<%}%>
<%-- 					<img src="${member eq null ? "https://a.wattpad.com/useravatar/user50478190.256.326933.jpg" : "<%=request.getContextPath()%>/member/memberImg.do?memberno=<%=request.getContextPath()%>"}" class="memberphoto" alt="memberphoto" style="float:right" width="80px" height="80px"> --%>
					<ul class="nav navbar-nav navbar-right membermenu">
					
					<%Vector<ShoppingCart> buylist = (Vector<ShoppingCart>) session.getAttribute("shoppingcart");%>
					
						  <li style="padding: 0; margin-top: 0">
						  	<a href="<%=request.getContextPath()%>/frontend/shopping_cart/ShoppingCart.jsp" class="shoppingAndFavorite navbarmemberbtn" style="padding-bottom: 0; padding-top: 30">
						  		<i class="glyphicon glyphicon-shopping-cart"></i>
						    	<span class="badge badge-primary badge-pill">
							    	<%if (buylist != null) {%>
							    		<%=buylist.size()%>
							    	<%} else {%>
							    		0
							    	<%}%>
						    	</span>
						    	結帳
						    </a>
						  </li>
						  
						<li style="padding: 0; margin-top: 0">
						<a href="<%=request.getContextPath()%>/frontend/favorite/selectFavorite.jsp" class="shoppingAndFavorite navbarmemberbtn" style="padding-bottom: 0; padding-top: 30">
						<font color="red">
						<i class="glyphicon glyphicon-heart"></i>
						</font>
						我的最愛
						</a>
						</li>
					
						<%if (session.getAttribute("member") == null) {%>
						
						<% } else {%>
						<li style="padding: 0; margin-top: 0"><a href="<%=request.getContextPath()%>/frontend/ewallet/memberEwallet.jsp" class="navbarmemberbtn" style="padding-bottom: 0; padding-top: 30">電子錢包餘額<font color="orange">${member.ewalletBalance}</font></a></li>
						<%}%>
						
						
						<li style="padding: 0; margin-top: 0">
						<%if (session.getAttribute("member") == null) {%>
						<a href="/CA105G2/frontend/login_front-end.jsp" class="navbarmemberbtn" style="padding-bottom: 0; padding-top: 30"><i class="glyphicon glyphicon-user"></i>&nbsp;&nbsp;&nbsp;登入</font></a>
						<% } else {%>
						<a class="navbarmemberbtn" style="padding-bottom: 0; padding-top: 30">
						<form  METHOD="post" ACTION="/CA105G2/member/member.do" style="margin: 0">
                                <input type="hidden" name="action" value="member_Logout">
								<i class="glyphicon glyphicon-log-out"></i>
								<input type="submit" value="登出" style="border: none ; background: none ">
                        </form>
						</a>
						<%}%>
<%-- 						<a href="${member eq null ? "/CA105G2/frontend/login_front-end.jsp" : "/CA105G2/frontend/index.jsp"}"><input type="hidden" name="action" value="member_Logout"> ${member eq null ? "登入" : "登出"} </a> --%>
						</li>
						
						
<!-- 						<li><a href="#">個人設定</a></li> -->
<%-- 						<li>${member.memberFullname} , 您好</li> --%>
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
			</div>
<div class="container">
	<div class="row">
		<div class="topnav">
			<font size="4">
			<div class="col-xs-12 col-sm-6 col-md-4 col-lg-2 topnavbtn">
				<div>
					<a href="/CA105G2/frontend/event_title/selectEventTitle.jsp" class="topnav" align="center">所有活動</a>
				</div>
			</div>
			<div class="col-xs-12 col-sm-6 col-md-4 col-lg-2 topnavbtn">
				<div>
					<a href="#" class="topnav" align="center">轉讓票券</a>
				</div>
			</div>
			<div class="col-xs-12 col-sm-6 col-md-4 col-lg-2 topnavbtn">
				<div>
					<a href="/CA105G2/frontend/goods/selectGoods.jsp" class="topnav" align="center">周邊商品</a>
				</div>
			</div>
			<div class="col-xs-12 col-sm-6 col-md-4 col-lg-2 topnavbtn">
				<div>
					<a href="/CA105G2/frontend/group_open/group_open_index.jsp" class="topnav" align="center">所有團購</a>
				</div>
			</div>
			<div class="col-xs-12 col-sm-6 col-md-4 col-lg-2 topnavbtn" style="an">
				<div>
					<a href="/CA105G2/frontend/faq/listAllFaq.jsp" class="topnav" align="center">常見問題</a>
				</div>
			</div>
			<div class="col-xs-12 col-sm-6 col-md-4 col-lg-2 topnavbtn">
				<div>
					<a href="<%=request.getContextPath()%>/frontend/selectOrder.jsp" class="topnav" align="center">訂單查詢</a>
				</div>
			</div>
			</font>
		</div>
	</div>
</div>
		</nav>
		
<script>
$(document).ready(function() {
		$(".memberphoto").hover(function() {
			$(this).css("opacity","0.4");
			$(".memberPhotoEdit").css("visibility","visible");
		},function() {
			$(this).css("opacity","1");
			$(".memberPhotoEdit").css("visibility","hidden");
		});
});
</script>

</body>
</html>