<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.ORDER_HISTORY.model.*"%>

<%
    OrderHistoryService orderHistorySvc = new OrderHistoryService();
    List<OrderHistoryVO> list = orderHistorySvc.getAll();
    pageContext.setAttribute("list",list);
%>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<title>訂單紀錄新增</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
		<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
		
		<style>
			table {
	 			margin-top: 1px;
				margin-bottom: 1px;
				font-size: 10px;
			}

		</style>	
	</head>
	<body>
	
		<c:if test="${not empty errorMsgs}">
		<font style="color:red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color:red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>

		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-12">
					<h4><a href="select_page.jsp"><img src="images/LOGO1.png" width="70" height="50" border="0"><b>首頁</b></a></h4>
					<div class="panel panel-info">
						<div class="panel-heading">
					  		
					  		<h3 class="panel-title">所有訂單紀錄查詢</h3><%@ include file="page1.file" %>
						</div>

						<table class="table table-bordered table-striped table-hover">

							<thead>
								<tr>
									<th>訂單編號</th>
									<th>會員編號</th>
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
									<th>修改</th>
									<th>刪除</th>
								</tr>
							</thead>
							
								
								
								
							<tbody>
								<c:forEach var="orderHistoryVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">							
									<tr>
										<td>${orderHistoryVO.orderNo}</td>
										<td>${orderHistoryVO.memberNo}</td>
										<td>${orderHistoryVO.orderPrice}</td>
										<td>${orderHistoryVO.payMethods}</td>
										<td>${orderHistoryVO.shippingMethods}</td>
										<td><fmt:formatDate value="${orderHistoryVO.orderDate}" pattern="yyyy-MM-dd"/></td>
										<td><fmt:formatDate value="${orderHistoryVO.orderEtd}" pattern="yyyy-MM-dd"/></td>
										<td><fmt:formatDate value="${orderHistoryVO.pickupDate}" pattern="yyyy-MM-dd"/></td>
										<td>${orderHistoryVO.receiverAdd}</td>
										<td>${orderHistoryVO.receiverName}</td> 
										<td>${orderHistoryVO.receiverTel}</td>
										<td>${orderHistoryVO.orderStatus}</td>
										
										<td>
										  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/OrderHistory/OrderHistory.do" style="margin-bottom: 0px;">
										     <input type="submit" value="修改">
										     <input type="hidden" name="orderNo"  value="${orderHistoryVO.orderNo}">
										     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
										</td>
										<td>
										  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/OrderHistory/OrderHistory.do" style="margin-bottom: 0px;">
										     <input type="submit" value="刪除">
										     <input type="hidden" name="orderNo"  value="${orderHistoryVO.orderNo}">
										     <input type="hidden" name="action" value="delete"></FORM>
										</td>
									</tr>
								</c:forEach>
							</tbody>
							
						</table>
						
					</div>
					
					<%@ include file="page2.file" %>
				</div>
			</div>
		</div>
		
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	</body>
</html>