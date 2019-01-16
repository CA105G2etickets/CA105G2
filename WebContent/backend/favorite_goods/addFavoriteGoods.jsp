<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.favorite_goods.model.*"%>
<%
	FavoriteGoodsVO favoriteGoodsVO = (FavoriteGoodsVO) request.getAttribute("favoriteGoodsVO");
%>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<title>ETIckeTs - 新增最愛商品</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
	<div><c:import url="/backend/navbar_back-end.jsp" charEncoding="UTF-8"/></div>
	</head>
	<body>
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12 col-sm-3"></div>
				<div class="col-xs-12 col-sm-6">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">新增最愛商品</h3>
						</div>
						<c:if test="${not empty errorMsgs}">
							<font style="color:red">請修正以下錯誤：</font>
							<ul>
								<c:forEach var="message" items="${errorMsgs}">
									<li style="color:red">${message}</li>
								</c:forEach>
							</ul>
						</c:if>
							<div class="panel-body">
								<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/favorite_goods/FavoriteGoods.do" name="form1">
								
									<div class="form-group">
										<label>會員編號：</label>
										<input type="TEXT" name="member_no" placeholder="請輸入會員編號" class="form-control" value="<%=(favoriteGoodsVO==null)? "" : favoriteGoodsVO.getMember_no()%>" style="width:30%" >
									</div>
									<div class="form-group">
										<label>商品編號：</label>
										<input type="TEXT" name="goods_no" placeholder="請輸入商品編號" class="form-control" value="<%=(favoriteGoodsVO==null)? "" : favoriteGoodsVO.getGoods_no()%>" style="width:30%" >
									</div>
								<br>
								<input type="hidden" name="action" value="insert">
								<input type="submit" value="送出新增" class="btn btn-default">
								<input type="button" value="返回 " class="btn btn-default" onclick="location.href='<%=request.getContextPath()%>/backend/favorite_goods/selectFavoriteGoods.jsp'" >
								</FORM>
							</div>
					</div>
				</div>
			</div>
		</div>
		
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	</body>
</html>