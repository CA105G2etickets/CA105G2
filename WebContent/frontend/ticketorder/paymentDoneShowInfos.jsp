<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ticket.model.*"%>
<%@ page import="com.ticketorder.model.*"%>

<%@ page import="java.util.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%-- <%
TicketOrderVO toVO = (TicketOrderVO)request.getAttribute("updated_tovo");
Event_H5_VO eh5VO = (Event_H5_VO)request.getAttribute("eh5vo");
List<TicketVO> tVOs = (List<TicketVO>)request.getAttribute("memberListTVO");
List<SeatingArea_H5_VO> sVOs = (List<SeatingArea_H5_VO>)request.getAttribute("slist");
pageContext.setAttribute("toVO",toVO);
pageContext.setAttribute("eh5VO",eh5VO);
pageContext.setAttribute("tVOs",tVOs);
pageContext.setAttribute("sVOs",sVOs);
String eve_noFromPage = request.getParameter("eve_no");
%> --%>

<%
TicketOrderVO toVO = (TicketOrderVO)request.getAttribute("updated_tovo");
List<ShowTicketVO> stvos = (List<ShowTicketVO>)request.getAttribute("listShow");
pageContext.setAttribute("toVO",toVO);
pageContext.setAttribute("stvos", stvos);
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>paymentDoneShowInfos.jsp</title>

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

<%--  錯誤表列 
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
</c:if> --%>

<table>
	<tr>
		<th>訂票訂單編號</th>
		<th>會員編號</th>
		
		<th>總價</th>
		<th>總張數</th>
		<th>訂票訂單成立時間</th>
		<th>付款方式</th>
		<th>訂票訂單狀態</th>
		<!-- <th>進行付款</th> -->
	</tr>
	<tr>
		<td>${toVO.ticket_order_no}</td>
		<td>${toVO.member_no}</td>
		
		<td>${toVO.total_price}</td>
		<td>${toVO.total_amount}</td>
		<td><fmt:formatDate value="${toVO.ticket_order_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		<td><font color="green">${toVO.payment_method}</font></td>
		<td><font color="green">${toVO.ticket_order_status}</font></td>
		<%-- <td>
			<FORM METHOD="post" ACTION="ticketorder.do">
				<input type="submit" value="pay">
				<input type="hidden" name="ticket_order_no"  value="${toVO.ticket_order_no}">
				<input type="hidden" name="action"	value="userWantToPay"></FORM>
		</td> --%>
	</tr>
</table>

<h3>列出該訂單的所有票券</h3>
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
		
	</tr>
	<c:forEach var="showticketVO" items="${stvos}">
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
		</tr>
	</c:forEach>
</table>



<%-- <h4><a href="<%=request.getContextPath()%>/frontend/ticketorder/select_page.jsp">前往會員的訂單查詢</a></h4> --%>
<form method="post" action="<%=request.getContextPath()%>/frontend/ticketorder/ticketorder.do">
	<input type="hidden" name="action" value="member_select_ticketorders">
	<input type="hidden" name="member_no" value="${toVO.member_no}"> <!--Member not done yet, only been set as M000001 -->
	<input type="submit" value="前往會員的訂單查詢">
</form>

</body>
</html>