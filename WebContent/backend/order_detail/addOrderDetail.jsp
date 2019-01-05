<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.order_detail.model.*"%>

<%
	OrderDetailVO orderDetailVO = (OrderDetailVO) request.getAttribute("orderDetailVO");
%>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<title>新增訂單紀錄</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
		<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
		
		<div>                   
			<c:import url="/navbar_back-end.html" charEncoding="UTF-8"/>
		</div>
		
<style type="text/css">
	tr{
		height : 30px;
	}
</style>
	</head>
	<body>
		<div class="container-fluid">
		<div class="col-xs-12 col-sm-1"></div>
			<div class="row">
				<div class="col-xs-12 col-sm-10">
	<!-- 					<h4><a href="select_page.jsp"><img src="images/LOGO1.png" width="70" height="50" border="0"><b>首頁</b></a></h4> -->
					<div class="panel panel-info">
							
						<div class="panel-heading">
							<h3 class="panel-title">新增訂單明細</h3>
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
								<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/order_detail/OrderDetail.do" name="form1">
								
									<jsp:useBean id="OrderHistorySvc" scope="page" class="com.order_history.model.OrderHistoryService" />
									<jsp:useBean id="OrderDetailSvc" scope="page" class="com.order_detail.model.OrderDetailService" />
									<jsp:useBean id="GoodsSvc" scope="page" class="com.goods.model.GoodsService" />
			
									<table>
										<tr>
											<td>訂單編號：</td>
											<td>
												<select size="1" name="order_no">
													<c:forEach var="OrderDetailVO" items="${OrderDetailSvc.allOrderNo}" > 
														<option value="${OrderDetailVO}">${OrderDetailVO}
													</c:forEach>   
												</select>
											</td>
										</tr>
										<tr>
											<td>商品編號：</td>
											<td>
												<select size="1" name="goods_no">
													<c:forEach var="GoodsVO" items="${GoodsSvc.all}" > 
														<option value="${GoodsVO.goods_no}">${GoodsVO.goods_no}
													</c:forEach>   
												</select>
											</td>
										</tr>
											
										<tr>
											<td>實際交易單價：</td>
											<td>
												<input type="TEXT" name="goods_bonus" size="10" value="<%=(orderDetailVO==null)? "0.0" : orderDetailVO.getGoods_bonus()%>" />
											</td><br>
										</tr>
										<tr>
											<td>商品數量：</td>
											<td><input type="TEXT" name="goods_pc" size="10" value="<%=(orderDetailVO==null)? "0.0" : orderDetailVO.getGoods_pc()%>" /></td>
										</tr>
									</table>
								<br>
								<input type="hidden" name="action" value="insert">
								<input type="submit" value="送出新增" class="btn btn-primary">
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