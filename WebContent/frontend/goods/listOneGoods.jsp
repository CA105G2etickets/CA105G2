<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.goods.model.*"%>
<%@ page import="com.goods_qa.model.*"%>
<%@ page import="com.favorite_goods.model.*"%>
<%
	GoodsQaVO goodsqaVO = (GoodsQaVO) request.getAttribute("goodsQaVO");
	String goods_no = request.getParameter("goods_no");
	GoodsService goodsService = new GoodsService();
	GoodsVO goodsVO = goodsService.getOneGoods(goods_no);
	pageContext.setAttribute("goodsVO", goodsVO);
%>
<%		
	session.getAttribute("member");
%>

<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>瀏覽商品詳情</title>
<!-- Basic -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<style>
body {
	font-family: 微軟正黑體 !important;
}

.right .carousel-control {
	background-color: white;
}

.left .carousel-control {
	background-color: white;
}
</style>
</head>

<body>

	<jsp:include page="/frontend/navbar_front-end.jsp" flush="true" />
	<input type="hidden" name="member_no" id="member_no" value="${member.memberNo}">
	<input type="hidden" name="projectName" id="projectName" value="<%=request.getContextPath() %>">


	<div class="container" style="margin-bottom: 10px;">
		<a href="#" class="btn btn-warning" style="float: right;">發起揪團</a> <a
			href="#" class="btn btn-info"
			style="float: right; margin-right: 10px;">查看揪團</a>
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
							<img
								src="<%=request.getContextPath()%>/goods/goodsImg1.do?goods_no=${goodsVO.goods_no}"
								style="height: 300px; width: auto; margin-left: auto; margin-right: auto;">
						</div>
						<c:if test="${goodsVO.goods_picture2 != null}">
							<div class="item">
								<img
									src="<%=request.getContextPath()%>/goods/goodsImg2.do?goods_no=${goodsVO.goods_no}"
									style="height: 300px; width: auto; margin-left: auto; margin-right: auto;">
							</div>
						</c:if>
						<c:if test="${goodsVO.goods_picture3 != null}">
							<div class="item">
								<img
									src="<%=request.getContextPath()%>/goods/goodsImg3.do?goods_no=${goodsVO.goods_no}"
									style="height: 300px; width: auto; margin-left: auto; margin-right: auto;">
							</div>
						</c:if>
					</div>
					<!-- 上下頁控制區 -->
					<a class="left carousel-control" href="#carousel-id"
						data-slide="prev"><span
						class="glyphicon glyphicon-chevron-left"></span></a> <a
						class="right carousel-control" href="#carousel-id"
						data-slide="next"><span
						class="glyphicon glyphicon-chevron-right"></span></a>
				</div>
			</div>
			<div class="col-xs-12 col-sm-6">
				<h3 style="margin-top: 0px;" class="alert alert-info" role="alert">${goodsVO.goods_name}</h3>
				<div id="toggleFavoriteGoods" style="color:red;text-align:right">
					<h3><i class="glyphicon glyphicon-heart-empty " id="clickFavoriteGoods">加入最愛</i></h3>
					<input type="hidden" id="favoriteGoodsStatus" value="outTheFavoriteGoods">
				</div>
				<h3>原價 : ${goodsVO.goods_price} 元 / 個</h3>
				<h3>促銷價 :<font color="red">  ${goodsVO.forsales_a} <font size="2">（購買十個以上即享有促銷價）</font></font></h3>
				<h3>銷售量 : ${goodsVO.goods_sales_count}  個</h3>
				<form name="shoppingForm" action="<%=request.getContextPath()%>/shopping_cart/ShoppingCart.do" method="POST">
					<h3 style="text-align:right;">數量：
						<div class="input-group" style="width:25%;float:right">
							<div class="input-group-btn min"><input class="btn btn-default" type="button" value="-"/></div>
							<input type="text" class="input-group form-control" name="goods_quantity" size="1" value=1>
							<div class="input-group-btn add" ><input class="btn btn-default" type="button" value="+"/></div>
						</div>
					</h3>
						<div>
							<input type="submit" name="Submit" class="btn btn-default" value="放入購物車" style="float:right;font-size:large">
							<input type="hidden" name="goods_no" id="goods_no"  value="${goodsVO.goods_no}">
						</div>
						
					<input type="hidden" name="goods_no" value="${goodsVO.goods_no}">
					<input type="hidden" name="evetit_no" value="${goodsVO.evetit_no}">
					<input type="hidden" name="goods_name"
						value="${goodsVO.goods_name}"> <input type="hidden"
						name="goods_price" value="${goodsVO.goods_price}"> <input
						type="hidden" name="forsales_a" value="${goodsVO.forsales_a}">
					<input type="hidden" name="goods_status"
						value="${goodsVO.goods_status}"> <input type="hidden"
						name="old_price" value="${goodsVO.goods_price}"> <input
						type="hidden" name="action" value="ADD">
				</form>
			</div>
		</div>
	</div>

	<div role="tabpanel">
		<!-- 標籤面板：標籤區 -->
		<ul class="nav nav-tabs" role="tablist">
			<li role="presentation" class="active"><a href="#tab1"
				aria-controls="tab1" role="tab" data-toggle="tab">熱門</a></li>
			<li role="presentation"><a href="#tab2" aria-controls="tab2"
				role="tab" data-toggle="tab">商品問答區</a></li>
		</ul>

		<!-- 標籤面板：內容區 -->
		<div class="tab-content">
			<div role="tabpanel" class="tab-pane active" id="tab1">
				<h4 style="margin-top: 25px;">${goodsVO.goods_introduction}</h4>
			</div>
			<div role="tabpanel" class="tab-pane" id="tab2">
				<form id="qna_question_form" action="qna_ins.php" method="post"
					class="rt-panel rt-panel-default rt-panel-header rt-form rt-mb-4x">
					<h6 class="rt-panel-title">提出問題</h6>
					<p class="rt-panel-text">
						為保障會員交易安全，留言請勿填寫個人資料、外部連結或任何導私下交易之內容，否則您送出的內容可能無法正常顯示。</p>
					<div class="rt-panel-inner qna-post-area">
						<div class="qna-ask-alert rt-alert rt-alert-danger"
							style="display: none;">
							<p>
								<span class="fa fa-exclamation-triangle rt-text-isolated"></span>
								<span class="rt-alert-content"></span>
							</p>
						</div>

						<textarea name="content" rows="10" id="questionTextarea"
							class="rt-form-textarea "></textarea>
						<div class="rt-mt rt-mb text-right">
							<span class="rt-text-notice rt-text-medium">(250個中文字以內)</span>
						</div>
						<input type="hidden" name="action" value="insert"> <input
							type="submit" value="送出新增">
						<div class="text-center">
							<input class="rt-button rt-button-submit" type="submit"
								name="button" value="提出問題" data-type="submit-question-button">

						</div>
					</div>

				</form>
			</div>
			<br> <br> <br> <br> <br>
		</div>




		<jsp:include page="/frontend/footer_front-end.jsp" flush="true" />



		<!-- Basic -->
		<script src="https://code.jquery.com/jquery.js"></script>
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
		<script>
	
		    $(document).ready(function() {
		    	
		    	console.log('${member.memberNo}');
		    	
				$("#toggleFavoriteGoods").click(function(){
		        	console.log("in the toggleFavoriteGoods");
		        	// checkFavoriteGoodsData
		        	var member_no = $("#member_no").val();
		        	console.log($("#member_no").val());
		        	if(member_no == null || member_no.trim().length != 7){
		        		window.alert("請先登入");
		        		return;
		        	}
		        	var goods_no = $("#goods_no").val();
		        	if(goods_no.trim().length != 8){
		        		window.alert("找不到商品編號");
		        		return;
		        	}
		        	// deleteFavoriteGoods
		        	if($("#favoriteGoodsStatus").val() == "inTheFavoriteGoods"){
		        		console.log("in the inTheFavoriteGoods");
		        		var url = $("#projectName").val();
		                var data = '';
		                url += '/favorite_goods/FavoriteGoods.do';
		              	data += 'member_no=';
		               	data += member_no;
		               	data += '&goods_no=';
		               	data += goods_no;
		               	data += '&';
		                data += 'action=deleteFavoriteGoods_Front';
		                console.log(data);
		                $.ajax({
		                    type: 'post',
		                    url: url,
		                    data: data,
		                    success: function(data) {    
// 			                   	if(data.indexOf("成") != -1){
// 			                		$("#clickFavoriteGoods").toggleClass("glyphicon-heart glyphicon-heart-empty");
// 			                		$("#favoriteGoodsStatus").val("inTheFavoriteGoods");
// 			                		$("#clickFavoriteGoods").html("加入最愛");
// 			                	} else {
// 			                		window.alert(data);
// 			                	}
			                   		$("#clickFavoriteGoods").toggleClass("glyphicon-heart glyphicon-heart-empty");
			                   		$("#favoriteGoodsStatus").val("outTheFavoriteGoods");
			                   		$("#clickFavoriteGoods").html("加入最愛");
		                    }
		                });	
		        	}
		        	// addFavoriteGoods
		        	if($("#favoriteGoodsStatus").val() == "outTheFavoriteGoods"){
		        		console.log("in the outTheFavoriteGoods");
		        		var url = $("#projectName").val();
		                url += '/favorite_goods/FavoriteGoods.do';
		                var data = '';
		               	data += 'member_no=';
		               	data += member_no;
		               	data += '&goods_no=';
		               	data += goods_no;
		               	data += '&';
		                data += 'action=addFavoriteGoods_Front';
		                console.log(data);
		                $.ajax({
		                    type: 'post',
		                    url: url,
		                    data: data,
		                    success: function(data) {              	
// 	                    	if(data.indexOf("成") != -1){
// 	                    		$("#clickFavoriteGoods").toggleClass("glyphicon-heart glyphicon-heart-empty");
// 	                    		$("#favoriteGoodsStatus").val("inTheFavoriteGoods");
// 	                    		$("#clickFavoriteGoods").html("取消最愛");
// 	                    	} else {
// 	                    		window.alert(data);
// 	                    	}
	                    		$("#clickFavoriteGoods").toggleClass("glyphicon-heart glyphicon-heart-empty");
	                    		$("#favoriteGoodsStatus").val("inTheFavoriteGoods");
	                    		$("#clickFavoriteGoods").html("取消最愛");
	                    	}
	                	});
		        	}
		        });
			        
			        console.log($("#member_no").val());
			        console.log("in the init favorite goods state");
			     	// the init favorite event state
			     	if($("#member_no").val() != null && $("#member_no").val().trim().length == 7 && $("#goods_no").val().trim().length == 8){
			        	var member_no = $("#member_no").val();
			        	var goods_no = $("#goods_no").val();
			     		var url = $("#projectName").val();
			            url += '/favorite_goods/FavoriteGoods.do';
			            var data = '';
			           	data += 'member_no=';
			           	data += member_no;
			           	data += '&goods_no=';
			           	data += goods_no;
			           	data += '&';
			            data += 'action=getOneFavoriteEvent_For_Display_Front';
			            console.log(data);
			            $.ajax({
			                type: 'post',
			                url: url,
			                data: data,
			                success: function(data) {   
			                	if(data.indexOf("true") != -1){
			                		$("#clickFavoriteGoods").toggleClass("glyphicon-heart glyphicon-heart-empty");
			                		$("#favoriteGoodsStatus").val("inTheFavoriteGoods");
			                		$("#clickFavoriteGoods").html("取消最愛");
			                	}
			                	else if(data.indexOf("false") != -1){
									//do nothing if not in the record
			                	} else {
			                		window.alert(data);
			                	}
			                }
			            });
			     	}
			     	
			    });
	</script>
</body>

</html>