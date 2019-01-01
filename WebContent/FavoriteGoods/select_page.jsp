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

<div>                   
	<c:import url="/navbar_back-end.html" charEncoding="UTF-8"/>
</div>

<body>

	<div class="container-fluid">
		<div class="col-xs-12 col-sm-1"></div>
		<div class="row">
			<div class="col-xs-12 col-sm-10">
<!-- 			<h4><a href="select_page.jsp"><img src="images/LOGO1.png" width="70" height="50" border="0"><b>首頁</b></a></h4> -->
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
	
				<a href='listAllFavoriteGoods.jsp'>查詢全部最愛商品</a><br><br>  						
	
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/FavoriteGoods/FavoriteGoods.do" >
				    <b>輸入會員編號 (如M000001):</b>
				    <input type="text" name="member_no">
				    <input type="hidden" name="action" value="getAll_Goods_Of_A_Member">
				    <input type="submit" value="送出" class="btn btn-info">
				</FORM>
	
				<jsp:useBean id="FavoriteGoodsSvc" scope="page" class="com.favorite_goods.model.FavoriteGoodsService" />
				
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/FavoriteGoods/FavoriteGoods.do" >
					<b>選擇會員編號:</b>
					<select size="1" name="member_no">
						<c:forEach var="FavoriteGoodsVO" items="${FavoriteGoodsSvc.allMemberNo}" > 
							<option value="${FavoriteGoodsVO}">${FavoriteGoodsVO}
						</c:forEach>   
					</select>
					<input type="hidden" name="action" value="getAll_Goods_Of_A_Member">
					<input type="submit" value="送出" class="btn btn-info">
				</FORM>
				
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/FavoriteGoods/FavoriteGoods.do" >
				    <b>輸入商品編號 (如P0000001):</b>
				    <input type="text" name="goods_no">
				    <input type="hidden" name="action" value="getAll_Member_Of_A_Goods">
				    <input type="submit" value="送出" class="btn btn-info">
				</FORM>
				
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/FavoriteGoods/FavoriteGoods.do" >
					<b>選擇商品編號:</b>
					<select size="1" name="goods_no">
						<c:forEach var="FavoriteGoodsVO" items="${FavoriteGoodsSvc.allGoodsNo}" > 
							<option value="${FavoriteGoodsVO}">${FavoriteGoodsVO}
						</c:forEach>   
					</select>
					<input type="hidden" name="action" value="getAll_Member_Of_A_Goods">
					<input type="submit" value="送出" class="btn btn-info">
				</FORM>
				
<%-- 				萬用複合查詢-以下欄位-可隨意增減 --%>
 
<%-- 				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/OrderHistory/OrderHistory.do" name="form1"> --%>
<!-- 					<b><font color=blue>萬用複合查詢：</font></b><br> -->
<!-- 					<b>輸入訂單編號：</b> -->
<!-- 					<input type="text" name="order_no" value="O2018122410001"><br><br> -->
				    
<!-- 				    <b>選擇會員編號：</b> -->
<!-- 					<select size="1" name="member_no"> -->
<%-- 						<c:forEach var="OrderHistoryVO" items="${OrderHistorySvc.allMemberNo}" >  --%>
<%-- 							<option value="${OrderHistoryVO}">${OrderHistoryVO} --%>
<%-- 						</c:forEach>    --%>
<!-- 					</select><br><br> -->
				    
<!-- 					<b>訂購日期：</b> -->
<!-- 					<input name="order_date" id="f_date1" type="text" size="10"><br><br> -->
				    
<!-- 					<b>出貨日期：</b> -->
<!-- 					<input name="order_etd" id="f_date2" type="text" size="10"><br><br> -->
			
<!-- 					<b>取貨日期：</b> -->
<!-- 					<input name="pickup_date" id="f_date3" type="text" size="10"><br><br> -->
					
<!-- 				   	<b>輸入送貨地址：</b> -->
<!-- 					<input type="text" name="receiver_add" size="40" value="320桃園市中壢區福德一路177巷60弄2號"><br><br> -->
				    
<!-- 					<b>輸入收件人名稱：</b> -->
<!-- 					<input type="text" name="receiver_name" size="8" value="Peter"><br><br> -->
				
<!-- 					<b>輸入收件人電話：</b> -->
<!-- 					<input type="text" name="receiver_tel" size="8" value="0912345678"><br><br> -->
				      
<!-- 				    <input type="submit" value="送出" class="btn btn-info"> -->
<!-- 				    <input type="hidden" name="action" value="listOrderHistory_ByCompositeQuery"> -->
<!-- 				 </FORM> -->
				
				</div>
			</div>
			</div>		
		</div>
		
		<div class="col-xs-12 col-sm-1"></div>
		<div class="row">	
			<div class="col-xs-12 col-sm-10">				
				<div class="panel panel-info">
					<div class="panel-heading">
						<h3 class="panel-title">最愛商品管理</h3>
					</div>
					<div class="panel-body">
						<a href='addFavoriteGoods.jsp'>新增一筆最愛商品</a>
					</div>
				</div>
			</div>
		</div>
		
	</div>

	<script src="https://code.jquery.com/jquery.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>