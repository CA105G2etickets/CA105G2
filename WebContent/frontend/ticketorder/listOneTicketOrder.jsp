<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.ticketorder.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
TicketOrderVO toVO = (TicketOrderVO) request.getAttribute("toVO");
pageContext.setAttribute("toVO",toVO);
%>

<html>
<head>
<title>訂票訂單資料 - listOneTicketOrder.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>訂票訂單資料 - listOneTicketOrder.jsp</h3>
		 <h4><a href="select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>訂票訂單編號</th>
		<th>會員編號</th>
		<th>座位區編號</th>
		<th>總價</th>
		<th>總張數</th>
		<th>訂票訂單成立時間</th>
		<th>付款方式</th>
		<th>訂票訂單狀態</th>
	</tr>
	<tr>
	 
		<td>${toVO.ticket_order_no}</td>
		<td>${toVO.member_no}</td>
		<td>${toVO.ticarea_no}</td>
		<td>${toVO.total_price}</td>
		<td>${toVO.total_amount}</td>
		<td><fmt:formatDate value="${toVO.ticket_order_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		<td>${toVO.payment_method}</td>
		<td>${toVO.ticket_order_status}</td>
		
		<%-- 
		<td><%=toVO.getTicket_order_no() %></td>
		<td><%=toVO.getMember_no()%></td>
		<td><%=toVO.getTicarea_no() %></td>
		<td><%=toVO.getTotal_price() %></td>
		<td><%=toVO.getTotal_amount() %></td>
		<td><%=toVO.getTicket_order_time() %></td>
		<td><%=toVO.getPayment_method() %></td>
		<td><%=toVO.getTicket_order_status() %></td>
		--%>
	</tr>
</table>

</body>
</html>