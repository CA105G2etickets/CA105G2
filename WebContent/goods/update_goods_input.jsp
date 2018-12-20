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
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>商品資料修改 - update_goods_input.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

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
			<tr>
				<td>商品編號:<font color=red><b>*</b></font></td>
				<td><%=goodsVO.getGoods_no()%></td>
			</tr>
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
					value="<%=goodsVO.getGoods_price()%> " /></td>
			</tr>
			<tr>
				<td>圖1</td>
				<td><input type="TEXT" name="goods_picture1" size="45"
					value="<%=goodsVO.getGoods_picture1()%>" /></td>
			</tr>
			<tr>
				<td>圖2</td>
				<td><input type="TEXT" name="goods_picture2" size="45"
					value="<%=goodsVO.getGoods_picture2()%>" /></td>
			</tr>
			<tr>
				<td>圖3</td>
				<td><input type="TEXT" name="goods_picture3" size="45"
					value="<%=goodsVO.getGoods_picture3()%>" /></td>
			</tr>
			<tr>
				<td>商品介紹:</td>
				<td><input type="TEXT" name="goods_introduction" size="45"
					value="<%=goodsVO.getGoods_introduction()%>" /></td>
			</tr>
			<tr>
				<td>促銷價</td>
				<td><input type="TEXT" name="forsales_a" size="45"
					value="<%=goodsVO.getForsales_a()%>" /></td>
			</tr>
			<tr>
				<td>喜愛人數:</td>
				<td><input type="TEXT" name="favorite_count" size="45"
					value="<%=goodsVO.getFavorite_count()%> " /></td>
			</tr>
			<tr>
				<td>商品狀態</td>
				<td><input type="TEXT" name="goods_status" size="45"
					value="<%=goodsVO.getGoods_status()%>" /></td>
			</tr>
			<tr>
				<td>上架日期:</td>
				<td><input type="datetime-local" name="launchdate" id=""></td>
			</tr>
			<tr>
				<td>下架日期:</td>
				<td><input type="datetime-local" name="offdate" id=""></td>
			</tr>
			<tr>
				<td>開團數:</td>
				<td><input type="TEXT" name="goods_group_count" size="45"
					value="<%=goodsVO.getGoods_group_count()%>" /></td>
			</tr>
			<tr>
				<td>許願數</td>
				<td><input type="TEXT" name="goods_want_count" size="45"
					value="<%=goodsVO.getGoods_want_count()%>" /></td>
			</tr>
					<tr>
				<td>銷售量</td>
				<td><input type="TEXT" name="goods_sales_count" size="45"
					value="<%=goodsVO.getGoods_sales_count()%>" /></td>
			</tr>

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
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=goodsVO.getLaunchdate()%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        </script>
        <link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=goodsVO.getOffdate()%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        </script>
</html>