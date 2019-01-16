<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ticket.model.*"%>
<%@ page import="com.ticketorder.model.*"%>

<%@ page import="java.util.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
List<ShowTicketVO> listShow = (List<ShowTicketVO>)request.getAttribute("listShow");
pageContext.setAttribute("listShow", listShow);
%>

<html>
<head>
<title>member_select_ticketorders.jsp</title>

<style>
  table {
	width: 800px;
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
		<th>票券編號</th>
		<th>持票人會員編號</th>
		<th>票券狀態</th>
		<th>活動座位區名稱</th>
		<th>活動座位區顏色代碼</th>
		
		<th>票種名稱</th>
		<th>票種價格</th>
		<th>場次名稱</th>
		<th>活動開始日期</th>
		<th>活動結束日期</th>
		<th>售票結束日期</th>
		
		<th>活動主題名稱</th>
		<th>場地名稱</th>
		<th>場地地址</th>
		
		<th>欲轉讓狀態</th>
		<th>欲轉讓金額</th>
		<th>我要轉賣此票</th>
	</tr>
	<c:forEach var="showticketVO" items="${listShow}">
		<tr>
			<td>${showticketVO.ticket_no}</td>
			<td>${showticketVO.member_no}</td>
			<td>${showticketVO.ticket_status}</td>
			<td>${showticketVO.ticarea_name}</td>
			<td>${showticketVO.ticarea_color}</td>
			
			<td>${showticketVO.tictype_name}</td>
			<td>${showticketVO.tictype_price}</td>
			<td>${showticketVO.eve_sessionname}</td>
			<td>${showticketVO.eve_startdate}</td>
			<td>${showticketVO.eve_enddate}</td>
			<td>${showticketVO.eve_offsaledate}</td>
			
			<td>${showticketVO.evetit_name}</td>
			<td>${showticketVO.venue_name}</td>
			<td>${showticketVO.address}</td>
			
			<td>${showticketVO.ticket_resale_status}</td>
			<td>${showticketVO.ticket_resale_price}</td>
			
			
			<td>
				<form method="post" action="<%=request.getContextPath()%>/frontend/ticketorder/ticketorder.do">
					<input type="number" name="ticket_resale_price" value="${showticketVO.tictype_price}">
					<input type="hidden" name="action" value="member_sell_targetTicket">
					<input type="hidden" name="ticket_no" value="${showticketVO.ticket_no}"> <!--Member not done yet, only been set as M000001 -->
					<input type="hidden" name="member_no" value="${showticketVO.member_no}">
					<input type="hidden" name="ticket_order_no" value="${showticketVO.ticket_order_no}">
					<input type="submit" value="轉售此票">
				</form>
			</td>
			
			
		</tr>
	</c:forEach>
</table>

<form method="post" action="<%=request.getContextPath()%>/frontend/ticketorder/ticketorder.do">
	<input type="hidden" name="action" value="member_select_ticketorders">
	<input type="hidden" name="member_no" value="${showticketVO.member_no}"> <!--Member not done yet, only been set as M000001 -->
	<input type="submit" value="回去會員的訂單查詢">
</form>

</body>
</html>