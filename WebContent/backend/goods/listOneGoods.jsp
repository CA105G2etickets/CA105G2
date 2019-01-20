<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.goods.model.*"%>
GoodsVO goodsVO = (GoodsVO) request.getAttribute("goodsVO");
//EmpServlet.java(Concroller), 存入req的empVO物件 %>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>${goodsVO.goods_name}</title>
<!-- Basic -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<!-- Noto Sans TC -->
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+TC"
	rel="stylesheet">
<style>
.evetit_poster_area {
	width: 100%;
}

.actionForm {
	display: inline;
}

body {
	font-family: 微軟正黑體 !important;
}
</style>
</head>

<body>



	<jsp:include page="/backend/navbar_back-end.jsp" flush="true" />



	<div class="container" style="margin-bottom: 30px;">

		<div class="form-group">
			<label for="goods_name">商品名稱</label> <span class="text-danger">${goodsErrorMsgs.goods_name}</span>
			<input type="text" name="goods_name" id="goods_name"
				class="form-control" value="${goodsVO.goods_name}">
		</div>

		<div class="row">
			<div class="col-xs-12 col-sm-3">
				<jsp:useBean id="EventTitleService" scope="page"
					class="com.event_title.model.EventTitleService" />
				<div class="form-group">
					<label>活動編號</label> <select class="form-control" name="evetit_no">
						<c:forEach var="eventTitleVO" items="${EventTitleService.all}">
							<option value="${eventTitleVO.evetit_no}"
								${(eventTitleVO.evetit_no == goodsVO.evetit_no) ? 'selected' : '' }>
								${eventTitleVO.evetit_name}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="col-xs-12 col-sm-3">
				<div class="form-group">
					<label>商品售價</label> <input type="text" name="goods_price"
						id="goods_price" class="form-control"
						value="${goodsVO.goods_price}">
				</div>
			</div>
			<div class="col-xs-12 col-sm-3">
				<div class="form-group">
					<label>優惠價</label> <input type="text" name="forsales_a"
						id="forsales_a" class="form-control" value="${goodsVO.forsales_a}">
				</div>
			</div>

			<div class="col-xs-12 col-sm-3">
				<div class="form-group">
					<label>商品狀態</label> <select class="form-control" name=goods_status>
						<option value="confirmed"
							${(goodsVO.goods_status == 'confirmed') ? 'selected' : '' }>已上架</option>
						<option value="temporary"
							${(goodsVO.goods_status == 'temporary') ? 'selected' : '' }>未上架</option>
						<option value="cancel"
							${(goodsVO.goods_status == 'cancel') ? 'selected' : '' }>已下架</option>
					</select>
				</div>
			</div>

		</div>

		<div class="row">
			<div class="col-xs-12 col-sm-6">
				<div class="form-group">
					<label for="launchdate">上架日期</label> <input type="text"
						id="launchdate" name="launchdate" class="form-control"
						value="<fmt:formatDate value="${goodsVO.launchdate}" pattern="yyyy-MM-dd"/>"
						readonly>
				</div>
			</div>
			<div class="col-xs-12 col-sm-6">
				<div class="form-group">
					<label for="offdate">下架日期</label> <input type="text" id="offdate"
						name="offdate" class="form-control"
						value="<fmt:formatDate value="${goodsVO.offdate}" pattern="yyyy-MM-dd"/>"
						readonly>
				</div>
			</div>
		</div>
		<div class="tab-content">
			<div class="tab-pane active" id="infoTab">${goodsVO.goods_introduction}</div>
			<form method="post"
				action="<%=request.getContextPath()%>/goods/GoodsServlet.do"
				class="actionForm">
				<input type="hidden" name="goods_no"
					value="${goodsVO.goods_no}"> <input type="hidden"
					name="requestURL" value="<%=request.getServletPath()%>"> <input
					type="hidden" name="action" value="getOne_For_Update">
				<input type="submit" value="修改" class="btn btn-warning"
					style="margin-top: 15px;">
			</form>
			<span class="form-group"> <a class="btn btn-info" href="<%=request.getContextPath()%>/backend/goods/listAllGoods.jsp" style="margin-top:15px;">回商品總攬</a>
				
			</span>

		</div>
		<!-- Basic -->
		<script src="https://code.jquery.com/jquery.js"></script>
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script type="text/javascript">
			$(document).ready(function() {
				$("#flip").click(function() {
					$("#panel").slideToggle("fast");
				});
			});
		</script>
</body>

</html>





















