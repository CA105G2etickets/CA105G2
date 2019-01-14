<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.goods.model.*"%>
<%
GoodsService GoodsSvc = new GoodsService();
List<GoodsVO> list = GoodsSvc.getAll();
pageContext.setAttribute("list",list);
%>
<jsp:useBean id="goodsSvc" scope="page" class="com.goods.model.GoodsService" />
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
	<title>商品頁面</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
	<div><c:import url="/frontend/navbar_front-end.jsp" charEncoding="UTF-8"/></div>
</head>
	<body>
	<hr>
	<table class="table table-bordered table-striped table-hover"> 
		<thead>
		
			<tr>
				<th>商品編號</th>
				<th>活動主題編號</th>
				<th>商品名稱</th>
				<th>商品圖片</th>
				<th>商品單價</th>
				<th>促銷方案</th>
				<th>商品數量</th>
				<th>商品狀態</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="goodsVO" items="${list}">
				<form name="shoppingForm" action="<%=request.getContextPath()%>/shopping_cart/ShoppingCart.do" method="POST">
					<tr>
						<td>${goodsVO.goods_no}</td>
						<td>${goodsVO.evetit_no}</td>
						<td>${goodsVO.goods_name}</td>
						<td><img src="<%=request.getContextPath()%>/goods/goodsImg1.do?goods_no=${goodsVO.goods_no}" width=100px height= auto /></td>
						<td>${goodsVO.goods_price}</td>
						<td>${goodsVO.forsales_a}</td>
						<td>
							數量：<input class="min" name="min" type="button" value="-" />  
							<input type="text" class="ordernum" name="goods_quantity" size="2" value=1>
							<input class="add" name="add" type="button" value="+" /> 
						</td>
						<td>      <input type="submit" name="Submit" value="放入購物車"></td>
					</tr>
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
		</tbody>
	</table>
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script>
		    $(function() {  
		        $(".add").click(function() {  
		            var t = $(this).parent().find('input[class*=ordernum]');  
		            if(t.val()==""||undefined||null){  
		                t.val(0);  
		            }  
		            t.val(parseInt(t.val()) + 1)
		        })  
		        $(".min").click(function() {  
		            var t = $(this).parent().find('input[class*=ordernum]');  
		            if(t.val()==""||undefined||null){  
		                t.val(0);  
		            }  
		            t.val(parseInt(t.val()) - 1)  
		            if(parseInt(t.val()) < 1) {  
		                t.val(1);  
		            }  
		        }) 
		    })  
		</script>  
	</body>
	<div><c:import url="/frontend/footer_front-end.jsp" charEncoding="UTF-8"/></div>
</html>