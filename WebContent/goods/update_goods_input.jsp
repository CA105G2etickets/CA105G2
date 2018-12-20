<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.goods.model.*"%>

<%
	GoodsVO goodsVO = (GoodsVO) request.getAttribute("goodsVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>商品資料修改 - update_goods_input.jsp</title>
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
<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color: red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color: red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="goods.do" name="form1">
	<table>
<!-- 		<tr> -->
<!-- 			<td>商品編號:<font color=red></font></td> -->
<%-- 			<td><%=goodsVO.getGoods_no()%></td> --%>
<!-- 		</tr> -->
		<tr>
			<td>活動編號:</td>
			<td><input type="TEXT" name="evetit_no" size="45"
				value="<%=goodsVO.getEvetit_no()%>" /></td>
		</tr>
		<tr>
			<td>商品名稱</td>
			<td><input type="TEXT" name="goods_name" size="45"
				value="<%=goodsVO.getGoods_name()%>" /></td>
		</tr>
		<tr>
			<td>售價:</td>
			<td><input type="TEXT" name="goods_price" size="45"
				value="<%=goodsVO.getGoods_price()%>" /></td>
		</tr>
		<tr>
<!-- 		<tr> -->
<!-- 			<td>圖1</td> -->
<!-- 			<td><input type="file" accept="image/jpeg, image/png" -->
<!-- 				name="picture1" size="45" -->
<%-- 				value="<%=(goodsVO == null) ? "10000" : goodsVO.getGoods_picture1()%>" /></td> --%>
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td>圖2</td> -->
<!-- 			<td><input type="file" accept="image/jpeg, image/png" -->
<!-- 				name="picture2" size="45" -->
<%-- 				value="<%=(goodsVO == null) ? "10000" : goodsVO.getGoods_picture2()%>" /></td> --%>
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td>圖3</td> -->
<!-- 			<td><input type="file" accept="image/jpeg, image/png" -->
<!-- 				name="picture3" size="45" -->
<%-- 				value="<%=(goodsVO == null) ? "10000" : goodsVO.getGoods_picture3()%>" /></td> --%>
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td>促銷價</td> -->
<!-- 			<td><input type="TEXT" name="forsales_a" size="45" -->
<%-- 				value="<%=goodsVO.getForsales_a()%>" /></td> --%>
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td>喜愛人數:</td> -->
<!-- 			<td><input type="TEXT" name="favorite_count" size="45" -->
<%-- 				value="<%=goodsVO.getFavorite_count()%> " /></td> --%>
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td>商品狀態</td> -->
<!-- 			<td><input type="TEXT" name="goods_status" size="45" -->
<%-- 				value="<%=goodsVO.getGoods_status()%>" /></td> --%>
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td>上架日期:</td> -->
<!-- 			<td><input type="datetime-local" name="launchdate" id='#f_date1'></td> -->
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td>下架日期:</td> -->
<!-- 			<td><input type="datetime-local" name="offdate" id='#f_date2'></td> -->
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td>開團數:</td> -->
<!-- 			<td><input type="TEXT" name="goods_group_count" size="45" -->
<%-- 				value="<%=goodsVO.getGoods_group_count()%>" /></td> --%>
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td>許願數</td> -->
<!-- 			<td><input type="TEXT" name="goods_want_count" size="45" -->
<%-- 				value="<%=goodsVO.getGoods_want_count()%>" /></td> --%>
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td>銷售量</td> -->
<!-- 			<td><input type="TEXT" name="goods_sales_count" size="45" -->
<%-- 				value="<%=goodsVO.getGoods_sales_count()%>" /></td> --%>
<!-- 		</tr> -->

		<%--<jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" />
	<tr>
		<td>部門:<font color=red><b>*</b></font></td>
		<td><select size="1" name="deptno">
			<c:forEach var="deptVO" items="${deptSvc.all}">
				<option value="${deptVO.deptno}" ${(empVO.deptno==deptVO.deptno)?'selected':'' } >${deptVO.dname}
			</c:forEach>
		</select></td>
	</tr>--%>

	</table>
	<br> <input type="hidden" name="action" value="update"> <input
		type="hidden" name="goods_no" value="<%=goodsVO.getGoods_no()%>">
	<input type="submit" value="送出修改">
</FORM>
<a href="listAllGoods.jsp"><img src="images/back1.png" width="186"
	height="81" border="0"></a>
</body>


<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->
<!-- <link rel="stylesheet" type="text/css" -->
<%-- 	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" /> --%>
<%-- <script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script> --%>
<!-- <script -->
<%-- 	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script> --%>

<!-- <style> -->
/* .xdsoft_datetimepicker .xdsoft_datepicker { */
/* 	width: 300px; /* width:  300px; */ */
/* } */

/* .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box { */
/* 	height: 151px; /* height:  151px; */ */
/* } */
<!-- </style> -->

<!-- <script> -->
//         $.datetimepicker.setLocale('zh');
//         $('#f_date1').datetimepicker({
// 	       theme: '',              //theme: 'dark',
// 	       timepicker:true,       //timepicker:true,
// 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
// 	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
<%-- 		   value: '<%=goodsVO.getLaunchdate()%>', // value:   new Date(), --%>
//            //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
//            //startDate:	            '2017/07/10',  // 起始日
//            //minDate:               '-1970-01-01', // 去除今日(不含)之前
//            //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
//         });
<!--         </script> -->
<!-- <link rel="stylesheet" type="text/css" -->
<%-- 	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" /> --%>
<%-- <script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script> --%>
<!-- <script -->
<%-- 	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script> --%>

<!-- <style> -->
/* .xdsoft_datetimepicker .xdsoft_datepicker { */
/* 	width: 300px; /* width:  300px; */ */
/* } */

/* .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box { */
/* 	height: 151px; /* height:  151px; */ */
/* } */
<!-- </style> -->

<!-- <script> -->
//         $.datetimepicker.setLocale('zh');
//         $('#f_date2').datetimepicker({
// 	       theme: '',              //theme: 'dark',
// 	       timepicker:true,       //timepicker:true,
// 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
// 	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
<!-- // 	', // value:   new Date(), -->
<!-- 	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含 -->
<!-- 	//startDate:	            '2017/07/10',  // 起始日 -->
<!-- 	//minDate:               '-1970-01-01', // 去除今日(不含)之前 -->
<!-- 	//maxDate:               '+1970-01-01'  // 去除今日(不含)之後 -->
<!-- 	}); -->
</script>
</html>