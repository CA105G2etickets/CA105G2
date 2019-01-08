<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import ="com.goods.model.*"%>
<html>
<head>
<title>ETIckeTs Goods</title>
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
/head>
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

	<!-- <table id="table-1"> -->
	<!--    <tr><td><h3>ETIckeTs Member</h3><h4>( MVC )</h4></td></tr> -->
	<!-- </table> -->
	<div class="container">
	<div class="col-xs-12 col-sm-12">
		<p>ETIckeTs Member管理頁面</p>
	</div>
	</div>
	<div class="container">
	<div class="col-xs-12 col-sm-12">
		會員查詢

		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}"><br>
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<p style="color: red">${message}</p>
				</c:forEach>
			</ul>
		</c:if>

		<ul>

			<li>
    <FORM METHOD="post" ACTION="goods.do" >
        <b>輸入商品編號 (如P0000001):</b>
        <input type="text" name="goods_no">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>
<a href='listAllGoods.jsp'>列出所有商品</a>
  <jsp:useBean id="goodsSvc" scope="page" class="com.goods.model.GoodsService" />
 
   
  <li>
     <FORM METHOD="post" ACTION="goods.do" >
       <b>選擇商品編號:</b>
       <select size="1" name="goods_no">
       
         <c:forEach var="goodsVO"  items="${goodsSvc.all}" > 
          <option value="${goodsVO.goods_no}">${goodsVO.goods_no}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="goods.do" >
       <b>選擇商品名稱:</b>
       <select size="1" name=goods_no>
         <c:forEach var="goodsVO" items="${goodsSvc.all}" > 
          <option value="${goodsVO.goods_no}">${goodsVO.goods_name}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
	</ul>
		</div>
	</div>
	<div class="container">
	<div class="col-xs-12 col-sm-12">
		會員管理

		<ul>
			<li><a href='addGoods.jsp'>新增商品</a></li>
		</ul>
	</div>
	</div>
	</body>
</html>