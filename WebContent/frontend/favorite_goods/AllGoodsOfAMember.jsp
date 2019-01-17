<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.favorite_goods.model.*"%>
<%@ page import="com.goods.model.*"%>
<%@ page import="com.member.model.*"%>
<%
List<FavoriteGoodsVO> list = (List<FavoriteGoodsVO>)request.getAttribute("favoriteGoodsVO");
pageContext.setAttribute("list",list);

MemberVO memberVO = (MemberVO) session.getAttribute("member");
%>
<jsp:useBean id="goodsSvc" scope="page" class="com.goods.model.GoodsService" />
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<title>ETIckeTs - 最愛商品新增</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
	</head>
	<jsp:include page="/frontend/navbar_front-end.jsp" flush="true"/>
	<style>
	body{
		font-family:微軟正黑體!important;
	}
	</style>
	<body>
		<c:if test="${not empty errorMsgs}">
			<font style="color:red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color:red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
		<div class="container-fluid" style="margin-bottom: 400px">
			<div class="row">
			<div class="col-xs-12 col-sm-2"></div>
				<div class="col-xs-12 col-sm-8">
					<h2>最愛商品查詢</h2><hr>
					<% if (list != null && (list.size() > 0)) {%>
						<table id="example" class="display" style="width:100%">
								<thead>
									<tr>
	<!-- 									<th>會員編號</th> -->
										<th>商品編號</th>
										<th>商品名稱</th>
										<th>商品價格</th>
										<th>商品圖片</th>
										<th>移除最愛</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="favoriteGoodsVO" items="${list}">
										<tr>
											<td>
												<a href="<%=request.getContextPath()%>/frontend/goods/listOneGoods.jsp?goods_no=${favoriteGoodsVO.goods_no}">${favoriteGoodsVO.goods_no}</a>
											</td>
											<td>	
												<c:forEach var="goodsVO" items="${goodsSvc.all}">
													<c:if test="${favoriteGoodsVO.goods_no == goodsVO.goods_no}">
														<a href="<%=request.getContextPath()%>/frontend/goods/listOneGoods.jsp?goods_no=${goodsVO.goods_no}">${goodsVO.goods_name}</a>
													</c:if>
												</c:forEach>
											</td>
											<td>	
												<c:forEach var="goodsVO" items="${goodsSvc.all}">
													<c:if test="${favoriteGoodsVO.goods_no == goodsVO.goods_no}">${goodsVO.goods_price}</c:if>
												</c:forEach>
											</td>
											<td>	
												<c:forEach var="goodsVO" items="${goodsSvc.all}">
													<c:if test="${favoriteGoodsVO.goods_no == goodsVO.goods_no}">
														<a href="<%=request.getContextPath()%>/frontend/goods/listOneGoods.jsp?goods_no=${goodsVO.goods_no}">
															<img src="<%=request.getContextPath()%>/goods/goodsImg1.do?goods_no=${goodsVO.goods_no}" width=50px height= auto />
														</a>
													</c:if>
												</c:forEach>
											</td>
											<td>
											  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/favorite_goods/FavoriteGoods.do" style="margin-bottom: 0px;">
											     <input type="submit" value="移除最愛" class="btn btn-danger">
											     <input type="hidden" name="member_no"  value="${favoriteGoodsVO.member_no}">
											     <input type="hidden" name="goods_no" value="${favoriteGoodsVO.goods_no}">
											     <input type="hidden" name="action" value="delete_Front"></FORM>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						<hr>
						<%} else {%>
							<div class="container-fluid" style="margin-bottom: 400px">
								<div class="row">
									<div class="col-xs-12 col-sm-3"></div>
									<div class="col-xs-12 col-sm-6">
										<p style="text-align:center;"><font color="red" size="7"><b>尚無最愛商品</b></font><p>
										<input type="button" value="返回首頁" style="display:block; margin:auto;" class="btn btn-default" onclick="location.href='<%=request.getContextPath()%>/frontend/index.jsp'" >
									</div>
								</div>
							</div>
						<%}%>					
						
						<input type="button" value="返回首頁 " class="btn btn-default" style="float:right;" onclick="location.href='<%=request.getContextPath()%>/frontend/index.jsp'" >
					</div>
				</div>
			</div>
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
		<script>
	        var table;
	        $(document).ready(function() {
				$('#example').DataTable(); 	
	        });
        </script>
	</body>
	<jsp:include page="/frontend/footer_front-end.jsp" flush="true"/> 
</html>