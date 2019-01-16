<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.order_detail.model.*"%>
<%@ page import="com.order_history.model.*"%>
<%@ page import="com.goods.model.*"%>
<%@ page import="com.member.model.*"%>
<%
List<OrderDetailVO> detaillist = (List<OrderDetailVO>)request.getAttribute("orderDetailVO");
pageContext.setAttribute("detaillist",detaillist);
List<OrderHistoryVO> historylist = (List<OrderHistoryVO>)request.getAttribute("orderHistoryVO");
pageContext.setAttribute("historylist",historylist);
%>

<jsp:useBean id="goodsSvc" scope="page" class="com.goods.model.GoodsService" />
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<title>ETIckeTs - 訂單明細查詢</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
	</head>
	<jsp:include page="/frontend/navbar_front-end.jsp" flush="true"/>
	<style>
	body{
		font-family:微軟正黑體!important;
	}
	</style>
	<body>
		<c:if test="${not empty errorMsgs}">
		<font style="color:red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color:red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
		<div class="container-fluid" style="margin-bottom: 400px">
			<div class="row">
				<div class="col-xs-12 col-sm-2"></div>
				<div class="col-xs-12 col-sm-8">
					  	<h2>訂單紀錄查詢</h2>
					  	<hr>
						<table id="example" class="display" style="width:100%; font-size:14px">
							<thead>
								<tr>
									<th>訂單編號</th>
									<th>商品編號</th>
									<th>商品名稱</th>
									<th>商品圖片</th>
									<th>實際交易單價</th>
									<th>商品數量</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="orderDetailVO" items="${detaillist}">
									<tr>
										<td>
<%-- 											<a href="<%=request.getContextPath()%>/frontend/order_history/oneMemberIsOrder.jsp?member_no=${memberVO.memberNo}">${orderDetailVO.order_no}</a> --%>
										${orderDetailVO.order_no}
										</td>
										<td>
											<a href="<%=request.getContextPath()%>/frontend/goods2/listOneGoods.jsp?goods_no=${orderDetailVO.goods_no}">${orderDetailVO.goods_no}</a>
										</td>
										<td>	
											<c:forEach var="goodsVO" items="${goodsSvc.all}">
												<c:if test="${orderDetailVO.goods_no == goodsVO.goods_no}">
													<a href="<%=request.getContextPath()%>/frontend/goods2/listOneGoods.jsp?goods_no=${orderDetailVO.goods_no}">${goodsVO.goods_name}</a>
												</c:if>
											</c:forEach>
										</td>
										<td>	
											<c:forEach var="goodsVO" items="${goodsSvc.all}">
												<c:if test="${orderDetailVO.goods_no == goodsVO.goods_no}">
													<a href="<%=request.getContextPath()%>/frontend/goods2/listOneGoods.jsp?goods_no=${orderDetailVO.goods_no}">
														<img src="<%=request.getContextPath()%>/goods/goodsImg1.do?goods_no=${goodsVO.goods_no}" width=50px height= auto />
													</a>
												</c:if>
											</c:forEach>
										</td>
										<td>${orderDetailVO.goods_bonus}</td>
										<td>${orderDetailVO.goods_pc}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<hr>
				</div>
			</div>
		</div>
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
		<script>
        var table;
        $(document).ready(function() {
			$('#example').DataTable(); 	
        });
        </script>
	</body>
	jsp:include page="/frontend/footer_front-end.jsp" flush="true"/> 
</html>