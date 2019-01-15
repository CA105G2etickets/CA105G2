<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.goods.model.*"%>
<%
	GoodsService GoodsSvc = new GoodsService();
	List<GoodsVO> list = GoodsSvc.getAll();
	pageContext.setAttribute("goodsList", list);
%>
<jsp:useBean id="goodsSvc" scope="page" class="com.goods.model.GoodsService" />
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>瀏覽所有商品</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<style>
.imgBigger img{ 
 	position: absolute; 
	left: 50%;
	top: 50%;
	height: 100%;
	width: auto;
	-webkit-transform: translate(-50%,-50%);
	-ms-transform: translate(-50%,-50%);
	transform: translate(-50%,-50%);
} 
.imgBigger{
	position: relative;
	width: 200px;
	height: 200px;
	overflow: hidden;
	margin-left: auto;
    margin-right: auto;
}
.picAreaThumbnail h4{ 
	height: 50px; 
} 
.picAreaThumbnail{  
	height:320px;  
} 
body {
	font-family:微軟正黑體!important;
}
</style>
</head>
<body>



	<jsp:include page="/frontend/navbar_front-end.jsp" flush="true" />



    <div class="container">
        <div>
			<c:forEach var="goodsVO" items="${goodsList}">
				<form name="shoppingForm" action="<%=request.getContextPath()%>/shopping_cart/ShoppingCart.do" method="POST">
	                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
	                    <div class="thumbnail picAreaThumbnail">
							<div class="imgBigger">
								<a href="<%= request.getContextPath()%>/frontend/goods2/listOneGoods.jsp?goods_no=${goodsVO.goods_no}">
									<img src="<%=request.getContextPath()%>/goods/goodsImg1.do?goods_no=${goodsVO.goods_no}">
								</a>    
							</div>
	                        <div class="caption">
								<h4>
	                            	<a href="<%= request.getContextPath()%>/frontend/goods2/listOneGoods.jsp?goods_no=${goodsVO.goods_no}">
	                            		${goodsVO.goods_name}                
	                            	</a>
	                            </h4>
	                            <div>
									數量：
									<input class="min" name="min" type="button" value="-" />
									<input type="text" class="ordernum" name="goods_quantity" size="2" value=1>
									<input class="add" name="add" type="button" value="+" />
									<input type="submit" name="Submit" value="放入購物車" style="float:right;">
								</div>
	                        </div>                   
	                    </div>
	                </div>
	                <input type="hidden" name="goods_no" value="${goodsVO.goods_no}">
	                <input type="hidden" name="evetit_no" value="${goodsVO.evetit_no}">
					<input type="hidden" name="goods_name" value="${goodsVO.goods_name}">
					<input type="hidden" name="goods_price" value="${goodsVO.goods_price}">
					<input type="hidden" name="forsales_a" value="${goodsVO.forsales_a}">
					<input type="hidden" name="goods_status" value="${goodsVO.goods_status}">
					<input type="hidden" name="old_price" value="${goodsVO.goods_price}">
					<input type="hidden" name="action" value="ADD">	
	           </form>
			</c:forEach>
        </div>
    </div>



	<script src="https://code.jquery.com/jquery.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
	<jsp:include page="/frontend/footer_front-end.jsp" flush="true" />
	
	<script>
		$(function() {
			$(".add").click(function() {
				var t = $(this).prev();
				if (t.val() == "" || undefined || null) {
					t.val(0);
				}
				t.val(parseInt(t.val()) + 1)
			})
			$(".min").click(function() {
				var t = $(this).next();
				if (t.val() == "" || undefined || null) {
					t.val(0);
				}
				t.val(parseInt(t.val()) - 1)
				if (parseInt(t.val()) < 1) {
					t.val(1);
				}
			})
		})
	</script>
</body>
</html>