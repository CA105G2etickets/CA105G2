<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.member.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  MemberVO member = (MemberVO) request.getAttribute("member"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>會員資料 - listOneMember.jsp</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
</style>
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
	background-color: #333;
}

.topnav {
	display: block;
	color: white;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
}

</style>
<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
  img {
  border-radius: 90%;
}
</style>

<style>
  table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>
</head>
<nav class="navbar navbar-inverse" role="navigation">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-ex1-collapse">
				<span class="sr-only">選單切換</span> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
			</button>
			<img src="images/LOGO_back-end.png" href="#" alt="LOGO"
				width="202.25px" height="165.5px">
		</div>

		<!-- 手機隱藏選單區  -->

		<div class="collapse navbar-collapse navbar-ex1-collapse">
			<!-- 右選單 -->
			<img
				src="https://scontent-hkg3-1.xx.fbcdn.net/v/t1.0-1/c53.53.662.662a/s160x160/996812_623306544360262_513913499_n.jpg?_nc_cat=109&_nc_eui2=AeEvi_vj3AZ5wk2s31mtunvrLPbVPtJK2jf7uWRYtFCuPw_M1yTd23yuh2AGeVu5aGSm_1aLOh_81tqazaXh-ECnpuFl77aq8E38y3WIOxRGcA&_nc_ht=scontent-hkg3-1.xx&oh=c8b216f2429b70114bdb941b525f73cf&oe=5CA0CFE7"
				class="memberphoto" href="#" alt="LOGO" style="float: right"
				width="80px" height="80px">

			<ul class="nav navbar-nav navbar-right membermenu">
				<li><a href="#">管理員登出</a></li>
				<li><a href="#">設定</a></li>
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
<div class="topnav">
	<div class="col-xs-12 col-sm-2">
		<div>
			<a href="#" class="topnav" align="center">活動管理</a>
		</div>
	</div>
	<div class="col-xs-12 col-sm-2">
		<div>
			<a href="#" class="topnav" align="center">票券管理</a>
		</div>
	</div>
	<div class="col-xs-12 col-sm-2">
		<div>
			<a href="#" class="topnav" align="center">商品管理</a>
		</div>
	</div>
	<div class="col-xs-12 col-sm-2">
		<div>
			<a href="#" class="topnav" align="center">團購管理</a>
		</div>
	</div>
	<div class="col-xs-12 col-sm-2">
		<div>
			<a href="#" class="topnav" align="center">常見問題管理</a>
		</div>
	</div>
	<div class="col-xs-12 col-sm-2">
		<div>
			<a href="#" class="topnav" align="center">公告管理</a>
		</div>
	</div>
</div>
</nav>

<table class="table">
	<tr>
		<th>會員編號</th>
		<th>會員姓名</th>
		<th>會員Email</th>
		<th>會員電話</th>
		<th>會員身分證字號</th>
		<th>會員帳號</th>
		<th>會員密碼</th>
		<th>會員電子錢包餘額</th>
		<th>會員帳號建立日期</th>
		<th>會員大頭貼</th>
		<th>會員狀態</th>
		<th>會員第三方登入UID</th>
	</tr>
	<tr>
		<td><%=member.getMemberNo()%></td>
		<td><%=member.getMemberFullname()%></td>
		<td><%=member.getEmail()%></td>
		<td><%=member.getPhone()%></td>
		<td><%=member.getIdcard()%></td>
		<td><%=member.getMemberAccount()%></td>
		<td><%=member.getMemberPassword()%></td>
		<td><%=member.getEwalletBalance()%></td>
		<td><%=member.getCreationDate()%></td>
		<td><img src="<%=request.getContextPath()%>/member/memberImg.do?memberno=${member.memberNo}" height="50" width="50"></td>
		<td><%=member.getMemberStatus()%></td>
		<td><%=member.getThirduid()%></td>
	</tr>
</table>
<a href="select_page.jsp"><img src="images/back1.png" width="186" height="81" border="0">
</body>
</html>