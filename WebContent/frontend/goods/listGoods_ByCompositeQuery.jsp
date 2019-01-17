<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page import="java.util.*"%>
<%@ page import="com.goods.model.*"%>
<%@ page import="com.goods.controller.*"%>

<%
	if(request.getAttribute("listGoods_ByCompositeQuery") == null){
		GoodsService goodsService = new GoodsService();
		List<GoodsVO> list = goodsService.getAllLaunched();
		pageContext.setAttribute("listGoods_ByCompositeQuery", list); 
	}
%>
 
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>瀏覽所有商品</title>
    <!-- Basic -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <style>
	   screen and (min-width : 481px) {
		    .flex-row.row {
		        display: flex;
		        flex-wrap: wrap;
		    }
		    .flex-row.row>[class*='col-'] {
		        display: flex;
		        flex-direction: column;
		    }
		    .flex-row.row:after, .flex-row.row:before {
		        display: flex;
		    }
		}
		.flex-row .thumbnail, .flex-row .caption {
		    flex: 1 0 auto;
		    flex-direction: column;
		}
		.flex-text {
		    flex-grow: 1
		}
		.flex-row img {
		    height: auto;
		    width: 100%
		}
</style>
</head>

<body>



    <div class="container">
        <div class="row flex-row">
            <c:forEach var="goodsVO" items="${listGoods_ByCompositeQuery}">
                <div class="col-xs-12 col-sm-4">
                    <div class="thumbnail">
                        <a href="<%= request.getContextPath()%>/frontend/goods/listOneGoods.jsp?goods_no=${goodsVO.goods_no}" target="_blank">
                            <img src="<%= request.getContextPath()%>/goods/GoodsGifReader?scaleSize=425&goods_no=${goodsVO.goods_no}" alt="">
                        </a>                       
                        <div class="caption">
                            <p class="flex-text">${goodsVO.launchdate}
                                ${(goodsVO.launchdate == goodsVO.offdate) ? '' : ' ~ '}
                                ${(goodsVO.launchdate == goodsVO.offdate) ? '' : goodsVO.offdate}
                            </p>
                        <h4><a href="<%= request.getContextPath()%>/frontend/goods/listOneGoods.jsp?goods_no=${goodsVO.goods_no}" target="_blank">
                            	${goodsVO.goods_name}                   
                            </a></h4>
                              <h4> 售價：  ${goodsVO.goods_price}</h4>
                         <h4> 促銷價：<font color="red">    ${goodsVO.forsales_a}</font></h4>
                         
                        <form name="shoppingForm" action="<%=request.getContextPath()%>/shopping_cart/ShoppingCart.do" method="POST">
							數量：<input class="min" name="min" type="button" value="-" />  
							<input type="text" class="ordernum" name="goods_quantity" size="2" value=1>
							<input class="add" name="add" type="button" value="+" /> 
						    <input type="submit" name="Submit" value="放入購物車">
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
            </c:forEach>
        </div>
    </div>
    
                               
                            

    
    
    <!-- Basic -->
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
</html>