<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.goods.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	GoodsService goodsSvc = new GoodsService();
	List<GoodsVO> list = goodsSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">


<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>活動管理</title>
<!-- Basic -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<!-- dataTables -->
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
</head>

<style>
.actionForm {
	display: inline;
}

body {
	font-family: 微軟正黑體 !important;
}
</style>

<body>



	<jsp:include page="/backend/navbar_back-end.jsp" flush="true" />

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
			<th>促銷價</th>




			<th>商品狀態</th>
			<th>上架日期</th>
			<th>下架日期</th>

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
				<td>${goodsVO.forsales_a}</td>
				<td>${goodsVO.goods_status}</td>
				<td><fmt:formatDate value="${goodsVO.launchdate}"
						pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td><fmt:formatDate value="${goodsVO.offdate}"
						pattern="yyyy-MM-dd HH:mm:ss" /></td>


				<td>
					<!-- 				<FORM METHOD="post" --> <%-- 					ACTION="<%=request.getContextPath()%>/goods/GoodsServlet.do" --%>
					<!-- 					style="margin-bottom: 0px;"> --> <!-- 					<input type="submit" value="修改"> <input type="hidden" -->
					<%-- 						name="goods_no" value="${goodsVO.goods_no}"> <input --%>
					<!-- 						type="hidden" name="action" value="getOne_For_Update"> -->
					<!-- 				</FORM> -->
					<form method="post"
						action="<%=request.getContextPath()%>/goods/GoodsServlet.do"
						class="actionForm" target="_blank">
						<input type="hidden" name="goods_no" value="${goodsVO.goods_no}">
						<input type="hidden" name="requestURL"
							value="<%=request.getServletPath()%>"> <input
							type="hidden" name="return_goods_no" value="${param.goods_no}">
						<input type="hidden" name="action" value="getOne_For_Update">
						<input type="submit" value="修改" class="btn btn-warning btn-sm">
					</form>
				</td>
				<td>
					<form method="post"
						action="<%=request.getContextPath()%>/goods/GoodsServlet.do"
						class="actionForm">
						<input type="hidden" name="goods_no" value="${goodsVO.goods_no}">
						<input type="hidden" name="action" value="deleteGoods"> <input
							type="hidden" name="requestURL"
							value="<%=request.getServletPath()%>"> <input
							type="submit" value="刪除" class="btn btn-danger btn-sm">
					</form> <!-- 				<FORM METHOD="post" --> <%-- 					ACTION="<%=request.getContextPath()%>/goods/GoodsServlet.do" --%>
					<!-- 					style="margin-bottom: 0px;"> --> <!-- 					<input type="submit" value="刪除"> <input type="hidden" -->
					<%-- 						name="goods_no" value="${goodsVO.goods_no}"> <input --%>
					<!-- 						type="hidden" name="action" value="delete"> --> <!-- 				</FORM> -->
				</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file"%>
	<a href="select_page.jsp"><img src="images/back1.png" width="186"
		height="81" border="0"></a>
</body>
</html>