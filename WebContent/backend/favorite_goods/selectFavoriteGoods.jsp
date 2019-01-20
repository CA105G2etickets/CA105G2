<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.favorite_goods.model.*"%>

<%
	FavoriteGoodsService favoriteGoodsSvc = new FavoriteGoodsService();
    List<FavoriteGoodsVO> list = favoriteGoodsSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<jsp:useBean id="goodsSvc" scope="page" class="com.goods.model.GoodsService" />
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">	
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
	<title>ETIckeTs - 所有最愛商品查詢</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
</head>
<div><c:import url="/backend/navbar_back-end.jsp" charEncoding="UTF-8"/></div>

<body>

	<div class="container-fluid" style="margin-bottom: 400px">
		<div class="row">
			<div class="col-xs-12 col-sm-3"></div>
			<div class="col-xs-12 col-sm-6">
<!-- 				<ol class="breadcrumb"> -->
<!-- 					<li> -->
<!-- 						<a href="#">首頁</a> -->
<!-- 					</li> -->
<!-- 					<li> -->
<!-- 						<a href="#">商品管理</a> -->
<!-- 					</li> -->
<!-- 					<li class="active">最愛商品管理</li> -->
<!-- 				</ol> -->
<%-- 				<hr><input type="button" class="btn btn-default" value="新增一筆最愛商品" onclick="location.href='<%=request.getContextPath()%>/backend/favorite_goods/addFavoriteGoods.jsp'"> --%>
				<hr>
				<table id="example" class="display" style="width:100%; font-size:8px">
					<thead>
						<tr>
							<th>會員編號</th>
							<th>商品編號</th>
							<th>商品名稱</th>
							<th>商品價格</th>
							<th>商品圖片</th>
<!-- 							<th>修改</th> -->
							<th>刪除</th>
						</tr>
					</thead>
						
					<tbody>
						<c:forEach var="favoriteGoodsVO" items="${list}">							
							<tr>
								<td>
									${favoriteGoodsVO.member_no}
								</td>
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
<!-- 								<td> -->
<%-- 								  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/favorite_goods/FavoriteGoods.do" style="margin-bottom: 0px;"> --%>
<!-- 								     <input type="submit" value="修改" class="btn btn-warning"> -->
<%-- 								     <input type="hidden" name="member_no"  value="${favoriteGoodsVO.member_no}"> --%>
<%-- 								     <input type="hidden" name="goods_no" value="${favoriteGoodsVO.goods_no}"> --%>
<!-- 								     <input type="hidden" name="action"	value="getOne_For_Update"></FORM> -->
<!-- 								</td> -->
								<td>
								  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/favorite_goods/FavoriteGoods.do" style="margin-bottom: 0px;">
								     <input type="submit" value="刪除" class="btn btn-danger">
								     <input type="hidden" name="member_no"  value="${favoriteGoodsVO.member_no}">
								     <input type="hidden" name="goods_no"  value="${favoriteGoodsVO.goods_no}">
								     <input type="hidden" name="action" value="delete"></FORM>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
	        </div>
	    </div>
	</div>


	<script src="https://code.jquery.com/jquery.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
	<script>
        var table;
        $(document).ready(function() {
        	table = $('#example').DataTable();
        });
    </script>
</body>
</html>