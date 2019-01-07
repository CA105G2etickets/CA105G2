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
<title>訂票訂單資料 - userStartPaying.jsp</title>

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
		 <h3>訂票訂單資料 - userStartPaying.jsp</h3>
		 <h4><a href="select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

<FORM METHOD="post" ACTION="ticketorder.do" name="form1">
<table>
	<tr>
		<td>訂票訂單編號:</td>
		<td>${toVO.ticket_order_no}</td>
	</tr>
	<tr>
		<td>會員編號:</td>
		<td>${toVO.member_no}</td>
	</tr>
	<tr>
		<td>總價:</td>
		<td>${toVO.total_price}</td>
	</tr>
	<tr>
		<td>總張數:</td>
		<td>${toVO.total_amount}</td>
	</tr>
	<tr>
		<td>繳費期限時間:</td>
<% 
Integer sessionLiveTime = session.getMaxInactiveInterval();
java.sql.Timestamp ticket_order_payment_deadline = null;
Long ticketOrderCreatedTimeInLongInt = null;
Long deadLineTime = null;
try {
	ticketOrderCreatedTimeInLongInt = toVO.getTicket_order_time().getTime();
	deadLineTime = ticketOrderCreatedTimeInLongInt+sessionLiveTime*1000;
	ticket_order_payment_deadline = new java.sql.Timestamp(deadLineTime);
 } catch (Exception e) {
	 System.out.println("ticket_order_payment_deadline error");
	 ticket_order_payment_deadline = new java.sql.Timestamp(System.currentTimeMillis());
 }
%>
		<td><fmt:formatDate value="<%=ticket_order_payment_deadline%>" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	</tr>
	<tr>
		<td>信用卡卡號:</td>
		<td><input type="TEXT" name="creditCardNumber" size="45" value=""/></td>
	</tr>
	<tr>
		<td>信用卡驗證碼:</td>
		<td><input type="TEXT" name="creditCardVerificationNumber" size="45" value=""/></td>
	</tr>
</table>
<br>
<input type="hidden" name="action" value="userPaying">
<input type="hidden" name="ticket_order_no"  value="${toVO.ticket_order_no}">
<input type="submit" value="pay">
</FORM>
</body>
</html>