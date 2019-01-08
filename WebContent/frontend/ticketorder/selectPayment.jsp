<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ticketorder.model.*"%>
<%@ page import="com.seating_area.model.*"%>
<%@ page import="com.event.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
Integer sessionLiveTime = session.getMaxInactiveInterval();
TicketOrderVO toVO = new TicketOrderVO();
String str = (String)session.getAttribute("CreatedTicketOrderNo");
if(str != null && !str.isEmpty()){
	TicketOrderService toSvc = new TicketOrderService();
	toVO = toSvc.getOneTicketOrder(str);
	pageContext.setAttribute("toVO",toVO);
}else{
	String str2 = (String)request.getAttribute("CreatedTicketOrderNo");
	TicketOrderService toSvc = new TicketOrderService();
	toVO = toSvc.getOneTicketOrder(str);
	pageContext.setAttribute("toVO",toVO);
}
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>模擬訂票訂單資料附帶票券的新增 - selectPayment.jsp</title>

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

<table id="table-1">
	<tr><td>
		 <h3>模擬訂票訂單資料附帶票券的新增 - selectPayment.jsp</h3></td><td>
		 <h4><a href="select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
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
		<th><font color="red">繳費期限時間</font></th>
		<th>進行付款</th>
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
<% 
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
		<td>
			<FORM METHOD="post" ACTION="ticketorder.do">
				<input type="submit" value="pay">
				<input type="hidden" name="ticket_order_no"  value="${toVO.ticket_order_no}">
				<input type="hidden" name="action"	value="userWantToPay"></FORM>
		</td>
	</tr>
</table>
</body>


</html>