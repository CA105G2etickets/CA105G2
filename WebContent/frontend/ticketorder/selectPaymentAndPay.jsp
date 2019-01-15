<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ticketorder.model.*"%>
<%@ page import="com.seating_area.model.*"%>
<%@ page import="com.event.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
Integer sessionLiveTime = session.getMaxInactiveInterval();

TicketOrderVO toVO = (TicketOrderVO)request.getAttribute("toVO");
pageContext.setAttribute("toVO",toVO);

List<String> list4PassValue = (List<String>)request.getAttribute("list4PassValue");
pageContext.setAttribute("list4PassValue",list4PassValue);
String eve_no = (String)request.getAttribute("eve_no");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>模擬訂票訂單資料附帶票券的新增 - selectPayment.jsp</title>

<style>
  table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<c:if test="${not empty list4PassValue}">
	<font style="color:blue">此訂票訂單的相關資訊:</font>
	<ul>
		<c:forEach var="message" items="${list4PassValue}">
			<li style="color:blue">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>訂票訂單編號</th>
		<th>會員編號</th>
		
		<th>總價</th>
		<th>總張數</th>
		<th>訂票訂單成立時間</th>
		<th>付款方式</th>
		<th>訂票訂單狀態</th>
		<th><font color="red">繳費期限時間</font></th>
		<!-- <th>進行付款</th> -->
	</tr>
	<tr>
		<td>${toVO.ticket_order_no}</td>
		<td>${toVO.member_no}</td>
		
		<td>${toVO.total_price}</td>
		<td>${toVO.total_amount}</td>
		<td><fmt:formatDate value="${toVO.ticket_order_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		<td>${toVO.payment_method}</td>
		<td>${toVO.ticket_order_status}</td>
<% 
java.sql.Timestamp ticket_order_payment_deadline = null;
Long ticketOrderCreatedTimeInLongInt = null;
Long deadLineTime = null;
String strError = "";
try {
	ticketOrderCreatedTimeInLongInt = toVO.getTicket_order_time().getTime();
	deadLineTime = ticketOrderCreatedTimeInLongInt+sessionLiveTime*1000;
	ticket_order_payment_deadline = new java.sql.Timestamp(deadLineTime);
 } catch (Exception e) {
	 System.out.println("ticket_order_payment_deadline error");
	 strError = "ticket_order_payment_deadline_error";
	 ticket_order_payment_deadline = new java.sql.Timestamp(System.currentTimeMillis());
 }
%>
		<td><fmt:formatDate value="<%=ticket_order_payment_deadline%>" pattern="yyyy-MM-dd HH:mm:ss"/><b><%=strError%></b></td>
		
		<%-- <td>
			<FORM METHOD="post" ACTION="ticketorder.do">
				<input type="submit" value="pay">
				<input type="hidden" name="ticket_order_no"  value="${toVO.ticket_order_no}">
				<input type="hidden" name="action"	value="userWantToPay"></FORM>
		</td> --%>
	</tr>
</table>

<!-- here's payment input -->
<FORM METHOD="post" action="<%=request.getContextPath()%>/frontend/ticketorder/ticketorder.do">
	<table>
		<tr>
			<td>信用卡卡號:</td>
			<td><input type="TEXT" name="creditCardNumber" size="45" value=""/></td>
		</tr>
		<tr>
			<td>信用卡驗證碼:</td> <!-- teperorilly use post form to send -->
			<td><input type="TEXT" name="creditCardVerificationNumber" size="9" value=""/></td>
		</tr>
	</table>
	<br>
<input type="hidden" name="action" value="userPaying">
<input type="hidden" name="ticket_order_no"  value="${toVO.ticket_order_no}">
<input type="submit" value="進行付款">
</FORM>

</body>
</html>