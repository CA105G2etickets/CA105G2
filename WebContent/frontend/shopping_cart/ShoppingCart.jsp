<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*" %>
<%@ page import="com.shopping_cart.model.ShoppingCart" %>
<%@ page import="com.goods.model.*"%>

<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
	<title>購物車內容</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
	<div><c:import url="/frontend/navbar_front-end.jsp" charEncoding="UTF-8"/></div>
</head>
<body>
<div class="container-fluid">
	<div class="row">
		<div class="col-xs-12 col-sm-2"></div>
		
		<div class="col-xs-12 col-sm-8">
			

			<%Vector<ShoppingCart> buylist = (Vector<ShoppingCart>) session.getAttribute("shoppingcart");%>
			<%if (buylist != null && (buylist.size() > 0)) {%>

				<form name="checkoutForm" class="test2" action="<%=request.getContextPath()%>/shopping_cart/ShoppingCart.do" method="POST">	
					<div class="panel panel-default">
						<div class="panel-heading">
						<h2 class="panel-title"><b>目前您購物車的內容如下</b></h2>
						</div>
						<table id="tab" class="table table-bordered table-striped table-hover"> 
							<thead>
								<tr>
									<th>商品編號</th>
									<th>活動主題編號</th>
									<th>商品名稱</th>
									<th>商品圖片</th>
									<th>商品單價</th>
									<th>促銷方案</th>
									<th>商品數量</th>
									<th>刪除</th>
								</tr>
							</thead>
					
							<%
							 for (int index = 0; index < buylist.size(); index++) {
								 ShoppingCart order = buylist.get(index);
							%>
								<tbody>
									<tr>
										<td><%=order.getGoods_no()%></td>
										<td><%=order.getEvetit_no()%></td>
										<td><%=order.getGoods_name()%></td>
										<td><img src="<%=request.getContextPath()%>/goods/goodsImg1.do?goods_no=<%=order.getGoods_no()%>" width=50px height= auto /></td>
										<td class="price"><%=order.getOld_price()%></td>
										<td><%=order.getForsales_a()%></td>
										<td>
											<div class="input-group">
												<div class="input-group-btn min"><input class="btn btn-default" type="button" value="-"/></div>
												<input type="text" class="ordernum form-control" name="goods_quantity" size="1" value=<%=order.getGoods_quantity()%>>
												<div class="input-group-btn add" ><input class="btn btn-default" type="button" value="+"/></div>
											</div>
										</td>
										<td>
										<a href="<%=request.getContextPath()%>/shopping_cart/ShoppingCart.do?action=DELETE&del=<%= index %>" class="btn btn-danger"><b>刪除</b></a>
								        	
								        </td>
									</tr>
								</tbody>
								<input type="hidden" name="goods_no" value=<%=order.getGoods_no()%>>
								<input type="hidden" name="evetit_no" value=<%=order.getGoods_name()%>>
								<input type="hidden" name="goods_name" value=<%=order.getGoods_no()%>>
								<input type="hidden" name="goods_price" value=<%=order.getGoods_price()%>>
								<input type="hidden" name="old_price" value=<%=order.getGoods_price()%>>
								<input type="hidden" name="forsales_a" value=<%=order.getForsales_a()%>>
							<%}%>
						</table>
					</div>
				<input type="hidden" name="action" value="CHECKOUT" > 
				<div class="container-fluid" style="margin-bottom: 400px">
					<div class="row">
						<div class="col-xs-12 col-sm-9"></div>
						<div class="col-xs-12 col-sm-1">
							<a href="<%=request.getContextPath()%>/shopping_cart/ShoppingCart.do?action=CLEAN" class="btn btn-default">清空購物車</a>
						</div>
						<div class="col-xs-12 col-sm-1">
							<input type="button" value="返回商品 " class="btn btn-default" onclick="location.href='<%=request.getContextPath()%>/frontend/shopping_cart/EShop.jsp'" >
						</div>
						<div class="col-xs-12 col-sm-1">
							<input type="submit" class="btn btn-default" value="付款結帳">
						</div>
					</div>
				</div>
				</form>  

<!-- 			<p>總價：<label id="total"></label></p> -->
			<%} else {%>
			
			<div class="container-fluid" style="margin-bottom: 400px">
				<div class="row">
					<div class="col-xs-12 col-sm-3"></div>
					<div class="col-xs-12 col-sm-6">
						<p><font color="red" size="7"><b>　　購物車內尚無商品</b></font><p>
						<input type="button" value="返回商品" style="display:block; margin:auto;" class="btn btn-default" onclick="location.href='<%=request.getContextPath()%>/frontend/shopping_cart/EShop.jsp'" >
					</div>
				</div>
			</div>
			<%}%>


		</div>
	</div>
</div>
	<script src="https://code.jquery.com/jquery.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script>
	    $(function() {  
	        $(".add").click(function() {  
	            var t = $(this).parent().find('input[name*=goods_quantity]');  
	            if(t.val()==""||undefined||null){  
	                t.val(0);  
	            }  
	            t.val(parseInt(t.val()) + 1)  
// 	            setTotal();  
	        })  
	        $(".min").click(function() {  
	            var t = $(this).parent().find('input[name*=goods_quantity]');  
	            if(t.val()==""||undefined||null){  
	                t.val(0);  
	            }  
	            t.val(parseInt(t.val()) - 1)  
	            if(parseInt(t.val()) < 1) {  
	                t.val(1);  
	            }  
// 	            setTotal();  
	        })  
// 	        $(".ordernum").keyup(function(){  
// 	            var t = $(this).parent().find('input[class*=ordernum]');  
// 	            if(parseInt(t.val())==""||undefined||null || isNaN(t.val())) {  
// 	                t.val(0);  
// 	            }  
// 	            setTotal();  
// 	        })  
// 	        function setTotal() {  
// 	            var s = 0;  
// 	            $("#tab td").each(function() {  
// 	                var t = $(this).find('input[class*=ordernum]').val();  
// 	                var p = $(this).find('td[class*=price]').text();  
// 	                if(parseInt(t)==""||undefined||null || isNaN(t) || isNaN(parseInt(t))){  
// 	                    t=0;  
// 	                }  
// 	                s += parseInt(t) * parseFloat(p);  
// 	            });  
// 	            $("#total").html(s.toFixed(2));  
// 	        }  
// 	        setTotal();  
	    })  

	</script>  

</body>
	<div><c:import url="/frontend/footer_front-end.jsp" charEncoding="UTF-8"/></div>

</html>