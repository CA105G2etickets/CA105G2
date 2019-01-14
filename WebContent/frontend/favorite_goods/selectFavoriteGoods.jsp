<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">	
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
	<title>最愛商品查詢</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<jsp:include page="/frontend/navbar_front-end.jsp" flush="true"/>
<style>
body{
	font-family:微軟正黑體!important;
}
</style>
<body>
	<div class="container-fluid" style="margin-bottom: 400px">
	<div class="row">
		<div class="col-xs-12 col-sm-3"></div>
		
			<div class="col-xs-12 col-sm-6">
				<div class="panel panel-info">
					<div class="panel-heading">
						<h3 class="panel-title">最愛商品查詢</h3>
					</div>
					<div class="panel-body">
					<c:if test="${not empty errorMsgs}">
						<font style="color:red">請修正以下錯誤:</font>
							<ul>
							    <c:forEach var="message" items="${errorMsgs}">
									<li style="color:red">${message}</li>
								</c:forEach>
							</ul>
					</c:if>
					<jsp:useBean id="FavoriteGoodsSvc" scope="page" class="com.favorite_goods.model.FavoriteGoodsService" />
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/favorite_goods/FavoriteGoods.do" >
						<b>選擇會員編號:</b>
						<select size="1" name="member_no">
							<c:forEach var="FavoriteGoodsVO" items="${FavoriteGoodsSvc.allMemberNo}" > 
								<option value="${FavoriteGoodsVO}">${FavoriteGoodsVO}
							</c:forEach>   
						</select>
						<input type="hidden" name="action" value="getAll_Goods_Of_A_Member_Front">
						<input type="submit" value="送出" class="btn btn-info">
					</FORM>
					</div>
				</div>
			</div>		
		</div>
	</div>
	<script src="https://code.jquery.com/jquery.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
<jsp:include page="/frontend/footer_front-end.jsp" flush="true"/> 
</html>