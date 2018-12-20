<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.goods.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	GoodsService goodsSvc = new GoodsService();
	List<GoodsVO> list = goodsSvc.getAll();
	pageContext.setAttribute("list", list);
%>


<html>
<head>
<title>所有商品資料 - listAllGoods.jsp</title>

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
<body bgcolor='white'>

	<h4>此頁練習採用 EL 的寫法取值:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>所有商品資料 - listAllGoods.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<table>
		<tr>
			<th>商品編號</th>
			<th>活動</th>
			<th>商品名稱</th>
			<th>售價</th>
			<th>下架日期</th>
			<th>圖1</th>
			<th>圖2</th>
			<th>圖3</th>
			<th>商品介紹</th>
			<th>售價</th>
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
				<td>${goodsVO.goods_picture1}</td>
				<td>${goodsVO.goods_picture2}</td>
				<td>${goodsVO.goods_picture3}</td>
				<td>${goodsVO.goods_introduction}</td>
				<td>${goodsVO.forsales_a}</td>
				<td>${goodsVO.favorite_count}</td>
				<td>${goodsVO.goods_status}</td>
				<td>${goodsVO.launchdate}</td>
				<td>${goodsVO.offdate}</td>
				<td>${goodsVO.goods_group_count}</td>
				<td>${goodsVO.goods_want_count}</td>
				<td>${goodsVO.goods_sales_count}</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/goods/goods.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改"> <input type="hidden"
							name="goods_no" value="${goodsVO.goods_no}"> <input
							type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/goods/goods.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="刪除"> <input type="hidden"
							name="goods_no" value="${goodsVO.goods_no}"> <input
							type="hidden" name="action" value="delete">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file"%>

</body>
</html>