<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page import="java.util.*"%>
<%@ page import="com.goods.model.*"%>

<%
	String goods_no = request.getParameter("goods_no");
	
	GoodsService goodsService = new GoodsService();
	GoodsVO goodsVO = goodsService.getOneGoods(goods_no);
	pageContext.setAttribute("goodsVO", goodsVO);
%>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>${aEventTitle.evetit_name}</title>
    <!-- Basic -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <style>
		body {
			font-family:微軟正黑體!important;
		}	    
		.right .carousel-control{
			background-color:white;
		}
		.left .carousel-control{
			background-color:white;
		}
    </style>
</head>

<body>



	<jsp:include page="/frontend/navbar_front-end.jsp" flush="true" />
	
	
	<div class="container" style="margin-bottom:10px;">
		<a href="#" class="btn btn-warning" style="float:right;">發起揪團</a>
		<a href="#" class="btn btn-info" style="float:right;margin-right:10px;">查看揪團</a>
	</div>
	
	

	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-6">
				<div id="carousel-id" class="carousel slide" data-ride="carousel">
				    <!-- 幻燈片小圓點區 -->
				    <ol class="carousel-indicators">
				        <li data-target="#carousel-id" data-slide-to="0" class="active"></li>
				        <c:if test="${goodsVO.goods_picture2 != null}">
				        	<li data-target="#carousel-id" data-slide-to="1" class=""></li>
				        </c:if>
				        <c:if test="${goodsVO.goods_picture3 != null}">
				       		<li data-target="#carousel-id" data-slide-to="2" class=""></li>
				        </c:if>
				    </ol>
				    <!-- 幻燈片主圖區 -->
				    <div class="carousel-inner">
				        <div class="item active">
				            <img src="<%=request.getContextPath()%>/goods/goodsImg1.do?goods_no=${goodsVO.goods_no}" style="height:300px;width:auto;margin-left:auto;margin-right:auto;">
				        </div>
				        <c:if test="${goodsVO.goods_picture2 != null}">
					        <div class="item">
								<img src="<%=request.getContextPath()%>/goods/goodsImg2.do?goods_no=${goodsVO.goods_no}" style="height:300px;width:auto;margin-left:auto;margin-right:auto;">
					        </div>
				        </c:if>
				        <c:if test="${goodsVO.goods_picture3 != null}">
					        <div class="item">
					            <img src="<%=request.getContextPath()%>/goods/goodsImg3.do?goods_no=${goodsVO.goods_no}" style="height:300px;width:auto;margin-left:auto;margin-right:auto;">
					        </div>
				        </c:if>
				    </div>
				    <!-- 上下頁控制區 -->
				    <a class="left carousel-control" href="#carousel-id" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a>
				    <a class="right carousel-control" href="#carousel-id" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a>
				</div>
			</div>
			<div class="col-xs-12 col-sm-6">
				<h3 style="margin-top:0px;" class="alert alert-info" role="alert">${goodsVO.goods_name}</h3>
				<h3>原價 : ${goodsVO.goods_price} 元 / 個</h3>
				<h3>促銷價 :<font color="red">  ${goodsVO.forsales_a} </font></h3>
				<h3>銷售量 : ${goodsVO.goods_sales_count}  個</h3>
				<form name="shoppingForm" action="<%=request.getContextPath()%>/shopping_cart/ShoppingCart.do" method="POST">
					<h3 style="text-align:right;">
						數量：				
						<input class="min" name="min" type="button" value="-" />
						<input type="text" class="ordernum" name="goods_quantity" size="2" value=1>
						<input class="add" name="add" type="button" value="+" />
					</h3>
					<h3 style="text-align:right;">
						<input type="submit" name="Submit" value="放入購物車" style="float:right;">
						<input type="hidden" name="goods_no" value="${goodsVO.goods_no}">
					</h3>
					<input type="hidden" name="goods_no" value="${goodsVO.goods_no}">
	                <input type="hidden" name="evetit_no" value="${goodsVO.evetit_no}">
					<input type="hidden" name="goods_name" value="${goodsVO.goods_name}">
					<input type="hidden" name="goods_price" value="${goodsVO.goods_price}">
					<input type="hidden" name="forsales_a" value="${goodsVO.forsales_a}">
					<input type="hidden" name="goods_status" value="${goodsVO.goods_status}">
					<input type="hidden" name="old_price" value="${goodsVO.goods_price}">
					<input type="hidden" name="action" value="ADD">	
				</form>
        	</div>
		</div>
	</div>

    <div class="container" style="margin-bottom:200px;">
	    <h3>《商品介紹》</h3>
	    <h4 style="margin-top:25px;">${goodsVO.goods_introduction}</h4>
    </div>	
	
    
    
	<jsp:include page="/frontend/footer_front-end.jsp" flush="true" />

    
    
    <!-- Basic -->
    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
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