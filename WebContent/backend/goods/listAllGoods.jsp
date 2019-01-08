<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.goods.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	GoodsService goodsSvc = new GoodsService();
	List<GoodsVO> list = goodsSvc.getAll();
	pageContext.setAttribute("list", list);
%>


<html>
<head>
<title>所有商品資料 - listAllGoods.jsp</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">

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
</style>

<style>
table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
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
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color: red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color: red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table class="table">


	<tr>
		<th>商品編號</th>
		<th>活動編號</th>
		<th>商品名稱</th>
		<th>售價</th>
		<th>圖1</th>
		<th>圖2</th>
		<th>圖3</th>
		<th>商品介紹</th>
		<th>促銷價</th>
		<th>喜愛人數</th>
		<th>商品狀態</th>
		<th>上架日期</th>
		<th>下架日期</th>
		<th>開團數</th>
		<th>許願數</th>
		<th>銷售量</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="page1.file"%>
	<c:forEach var="goodsVO" items="${list}" begin="<%=pageIndex%>"
		end="<%=pageIndex+rowsPerPage-1%>">

		<tr>
			<td>${goodsVO.goods_no}</td>
			<td>${goodsVO.evetit_no}</td>
			<td>${goodsVO.goods_name}</td>
			<td>${goodsVO.goods_price}</td>
			<td><img
				src="<%=request.getContextPath()%>/goods/goodsImg1.do?goods_no=${goodsVO.goods_no}"
				width=50% /></td>
			<td><img
				src="<%=request.getContextPath()%>/goods/goodsImg2.do?goods_no=${goodsVO.goods_no}"
				width=50% /></td>
			<td><img
				src="<%=request.getContextPath()%>/goods/goodsImg3.do?goods_no=${goodsVO.goods_no}"
				width=50% /></td>
			<td>${goodsVO.goods_introduction}</td>
			<td>${goodsVO.forsales_a}</td>
			<td>${goodsVO.favorite_count}</td>
			<td>${goodsVO.goods_status}</td>
			<td><fmt:formatDate value="${goodsVO.launchdate}"
					pattern="yyyy-MM-dd HH:mm:ss" /></td>

			<td><fmt:formatDate value="${goodsVO.offdate}"
					pattern="yyyy-MM-dd HH:mm:ss" /></td>

			<td>${goodsVO.goods_group_count}</td>
			<td>${goodsVO.goods_want_count}</td>
			<td>${goodsVO.goods_sales_count}</td>
			<td>
<!-- 				<FORM METHOD="post" -->
<%-- 					ACTION="<%=request.getContextPath()%>/goods/GoodsServlet.do" --%>
<!-- 					style="margin-bottom: 0px;"> -->
<!-- 					<input type="submit" value="修改"> <input type="hidden" -->
<%-- 						name="goods_no" value="${goodsVO.goods_no}"> <input --%>
<!-- 						type="hidden" name="action" value="getOne_For_Update"> -->
<!-- 				</FORM> -->
				<form method="post" action="<%=request.getContextPath()%>/goods/GoodsServlet.do" class="actionForm" target="_blank">								
								    <input type="hidden" name="goods_no"         value="${goodsVO.goods_no}">
								    <input type="hidden" name="requestURL"	      value="<%=request.getServletPath()%>">
								    <input type="hidden" name="return_goods_no"  value="${param.goods_no}">
								    <input type="hidden" name="action"	          value="getOne_For_Update">
								    <input type="submit" value="修改" class="btn btn-warning btn-sm"> 							
								</form>		
			</td>
			<td>
<form method="post" action="<%=request.getContextPath()%>/goods/GoodsServlet.do" class="actionForm" target="_blank">								
								    <input type="hidden" name="goods_no"         value="${goodsVO.goods_no}">
								    <input type="hidden" name="requestURL"	      value="<%=request.getServletPath()%>">
								    
								    <input type="hidden" name="action"	    value="delete">
								    <input type="submit" value="刪除" class="btn btn-danger btn-sm">	
								</form>
<!-- 				<FORM METHOD="post" -->
<%-- 					ACTION="<%=request.getContextPath()%>/goods/GoodsServlet.do" --%>
<!-- 					style="margin-bottom: 0px;"> -->
<!-- 					<input type="submit" value="刪除"> <input type="hidden" -->
<%-- 						name="goods_no" value="${goodsVO.goods_no}"> <input --%>
<!-- 						type="hidden" name="action" value="delete"> -->
<!-- 				</FORM> -->
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file"%>
<a href="select_page.jsp"><img src="images/back1.png" width="186"
	height="81" border="0"></a>
</body>
</html>