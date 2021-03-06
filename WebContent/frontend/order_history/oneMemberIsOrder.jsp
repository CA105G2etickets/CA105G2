<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.order_history.model.*"%>
<%@ page import="com.member.model.*"%>
<%
	List<OrderHistoryVO> list = (List<OrderHistoryVO>)request.getAttribute("orderHistoryVO");
	pageContext.setAttribute("list",list);
%>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<title>ETIckeTs - 訂單紀錄查詢</title>
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
		<div class="container-fluid" style="margin-bottom: 200px">
			<div class="row">
				<div class="col-xs-12 col-sm-2"></div>
				<div class="col-xs-12 col-sm-8">
					<h2>訂單紀錄查詢</h2><hr>
					<% if (list != null && (list.size() > 0)) {%>
				        <table id="example" class="display" style="width:100%; font-size:8px">
							<thead>
								<tr>
									<th>訂單編號</th>
	<!-- 								<th>會員編號</th> -->
									<th>訂單總金額</th>
									<th>付款方式</th>
									<th>出貨方式</th>
									<th>訂購日期</th>
									<th>出貨日期</th>
									<th>取貨日期</th>
									<th>送貨地址</th>
									<th>收件人名稱</th>
									<th>收件人電話</th>
									<th>訂單狀態</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="orderHistoryVO" items="${list}">
									<tr>
										<td><a href="<%=request.getContextPath()%>/order_detail/OrderDetail.do?action=getAll_OrderDetail_For_A_OrderNo_Frontend&order_no=${orderHistoryVO.order_no}">${orderHistoryVO.order_no}</a></td>
										<td>${orderHistoryVO.order_price}</td>
										<td>
											${(orderHistoryVO.pay_methods == "CREDITCARD") ? '信用卡' : '' }
											${(orderHistoryVO.pay_methods == "EWALLET") ? '電子錢包' : '' }
										</td>
										<td>
											${(orderHistoryVO.shipping_methods == "STOREPICKUP") ? '超商取貨' : '' }
											${(orderHistoryVO.shipping_methods == "HOMEDELIVERY") ? '宅配' : '' }
										</td>
										<td><fmt:formatDate value="${orderHistoryVO.order_date}" pattern="yyyy-MM-dd"/></td>
										<td><fmt:formatDate value="${orderHistoryVO.order_etd}" pattern="yyyy-MM-dd"/></td>
										<td><fmt:formatDate value="${orderHistoryVO.pickup_date}" pattern="yyyy-MM-dd"/></td>
										<td>${orderHistoryVO.receiver_add}</td>
										<td>${orderHistoryVO.receiver_name}</td> 
										<td>${orderHistoryVO.receiver_tel}</td>
										<td>
											${(orderHistoryVO.order_status == "PAYMENT1") ? '已付款' : '' }
											${(orderHistoryVO.order_status == "SHIPPING2") ? '出貨中' : '' }
											${(orderHistoryVO.order_status == "SHIPMENT3") ? '已出貨' : '' }
											${(orderHistoryVO.order_status == "COMPLETE4") ? '已完成' : '' }
											${(orderHistoryVO.order_status == "CANCEL5") ? '已取消' : '' }
										</td>
										<c:if test="${(orderHistoryVO.order_status == 'PAYMENT1') || (orderHistoryVO.order_status == 'SHIPPING2')}">
											<td>
											  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/order_history/OrderHistory.do" style="margin-bottom: 0px;">
												<input type="hidden" name="order_no" value="${orderHistoryVO.order_no}">
												<input type="hidden" name="member_no" value="${orderHistoryVO.member_no}">
												<input type="hidden" name="order_price" value="${orderHistoryVO.order_price}">
												<input type="hidden" name="pay_methods" value="${orderHistoryVO.pay_methods}">
												<input type="hidden" name="shipping_methods" value="${orderHistoryVO.shipping_methods}">
												<input type="hidden" name="order_date" value="${orderHistoryVO.order_date}">
												<input type="hidden" name="order_etd" value="${orderHistoryVO.order_etd}">
												<input type="hidden" name="pickup_date" value="${orderHistoryVO.pickup_date}">
												<input type="hidden" name="receiver_add" value="${orderHistoryVO.receiver_add}">
												<input type="hidden" name="receiver_name" value="${orderHistoryVO.receiver_name}">
												<input type="hidden" name="receiver_tel" value="${orderHistoryVO.receiver_tel}">
												<input type="hidden" name="order_status" value="${orderHistoryVO.order_status}">
												<input type="hidden" name="action" value="update_Front">
												<input type="submit" value="取消訂單" class="btn btn-danger">
											  </FORM>
											</td>
										</c:if>
										<c:if test="${(orderHistoryVO.order_status != 'PAYMENT1') || (orderHistoryVO.order_status != 'SHIPPING2')}">
											<td></td>
										</c:if>
									</tr>
								</c:forEach>
							</tbody>
							
						</table>
					<hr>
					
					<%} else {%>
					<div class="container-fluid" style="margin-bottom: 200px">
						<div class="row">
							<div class="col-xs-12 col-sm-3"></div>
							<div class="col-xs-12 col-sm-6">
								<p style="text-align:center;"><font color="red" size="7"><b>訂單尚無紀錄</b></font><p>
								<input type="button" value="返回首頁" style="display:block; margin:auto;" class="btn btn-default" onclick="location.href='<%=request.getContextPath()%>/frontend/index.jsp'" >
							</div>
						</div>
					</div>
					<%}%>
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
	<jsp:include page="/frontend/footer_front-end.jsp" flush="true"/> 
</html>