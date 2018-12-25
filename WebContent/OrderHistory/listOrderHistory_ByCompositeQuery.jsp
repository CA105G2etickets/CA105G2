<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.ORDER_HISTORY.model.*"%>

<%-- 萬用複合查詢-可由客戶端select_page.jsp隨意增減任何想查詢的欄位 --%>
<%-- 此頁只作為複合查詢時之結果練習，可視需要再增加分頁、送出修改、刪除之功能--%>

<jsp:useBean id="listOrderHistory_ByCompositeQuery" scope="request" type="java.util.List<OrderHistoryVO>" /> <!-- 於EL此行可省略 -->
<jsp:useBean id="OrderHistorySvc" scope="page" class="com.ORDER_HISTORY.model.OrderHistoryService" />


<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<title>萬用複合查詢</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
		<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
		
		<div>                   
			<c:import url="/navbar_back-end.html" charEncoding="UTF-8"/>
		</div>
		
		<style>
			table {
	 			margin-top: 1px;
				margin-bottom: 1px;
				font-size: 10px;
				table-layout: auto;
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
							<h3 class="panel-title">萬用複合查詢</h3><font color=red>已增加分頁、送出修改、刪除之功能</font>
					  		<%@ include file="pages/page1_ByCompositeQuery.file" %>
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
								<c:forEach var="orderHistoryVO" items="${listOrderHistory_ByCompositeQuery}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
									<tr align='center' valign='middle' ${(orderHistoryVO.order_no==param.order_no) ? 'bgcolor=#CCCCFF':''}><!--將修改的那一筆加入對比色而已-->
								 		<td>${orderHistoryVO.order_no}</td>
										<td>${orderHistoryVO.member_no}</td>
										<td>${orderHistoryVO.order_price}</td>
										<td>${orderHistoryVO.pay_methods}</td>
										<td>${orderHistoryVO.shipping_methods}</td>
										<td><fmt:formatDate value="${orderHistoryVO.order_date}" pattern="yyyy-MM-dd"/></td>
										<td><fmt:formatDate value="${orderHistoryVO.order_etd}" pattern="yyyy-MM-dd"/></td>
										<td><fmt:formatDate value="${orderHistoryVO.pickup_date}" pattern="yyyy-MM-dd"/></td>
										<td>${orderHistoryVO.receiver_add}</td>
										<td>${orderHistoryVO.receiver_name}</td> 
										<td>${orderHistoryVO.receiver_tel}</td>
										<td>${orderHistoryVO.order_status}</td> 			

										<td>
										  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/OrderHistory/OrderHistory.do" style="margin-bottom: 0px;">
										     <input type="submit" value="修改" class="btn btn-warning"> 
										     <input type="hidden" name="order_no"      value="${orderHistoryVO.order_no}">
										     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
										     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
										     <input type="hidden" name="action"	    value="getOne_For_Update"></FORM>
										</td>
										<td>
										  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/OrderHistory/OrderHistory.do" style="margin-bottom: 0px;">
										     <input type="submit" value="刪除" class="btn btn-danger">
										     <input type="hidden" name="order_no"      value="${orderHistoryVO.order_no}">
										     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
										     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
										     <input type="hidden" name="action"     value="delete"></FORM>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<%@ include file="pages/page2_ByCompositeQuery.file" %>
				</div>
			</div>
		</div>

</body>
</html>