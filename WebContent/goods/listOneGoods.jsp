<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.goods.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	GoodsVO goodsVO = (GoodsVO) request.getAttribute("goodsVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>商品資料 - listOneGoods.jsp</title>

<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center; %> table #table-1 h4 { color : red;
	display: block;
	margin-bottom: 1px; %> h4 { color : blue;
	display: inline;
}
%>
</style>

<style>
table {
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px; %> table , th, td { border : 1px solid #CCCCFF; %>
	th , td { padding : 5px;
	text-align: center;
}
%>
</style>

</head>
<body bgcolor='white'>

	<h4>此頁暫練習採用 Script 的寫法取值:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>商品資料 - ListOneGoods.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
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
		</tr>
		<tr>
			<td><%=goodsVO.getGoods_no()%></td>
			<td><%=goodsVO.getEvetit_no()%></td>
			<td><%=goodsVO.getGoods_name()%></td>
			<td><%=goodsVO.getGoods_price()%></td>
			<td><%=goodsVO.getGoods_picture1()%></td>
			<td><%=goodsVO.getGoods_picture2()%></td>
			<td><%=goodsVO.getGoods_picture3()%></td>
			<td><%=goodsVO.getGoods_introduction()%></td>
			<td><%=goodsVO.getForsales_a()%></td>
			<td><%=goodsVO.getFavorite_count()%></td>
			<td><%=goodsVO.getGoods_status()%></td>
			<td><%=goodsVO.getLaunchdate()%></td>
			<td><%=goodsVO.getOffdate()%></td>
			<td><%=goodsVO.getGoods_group_count()%></td>
			<td><%=goodsVO.getGoods_want_count()%></td>
			<td><%=goodsVO.getGoods_sales_count()%></td>

		</tr>
	</table>

</body>
</html>