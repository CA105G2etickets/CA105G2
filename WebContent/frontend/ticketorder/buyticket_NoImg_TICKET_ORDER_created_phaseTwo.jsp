<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ticketorder.model.*"%>
<%@ page import="com.seating_area.model.*"%>
<%@ page import="com.event.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
Integer sessionLiveTime = session.getMaxInactiveInterval();

ShowTicketOrderVO shtovo = (ShowTicketOrderVO)request.getAttribute("shtovo");
pageContext.setAttribute("shtovo",shtovo);
%>
<!-- it's DAI's javabean, not mine -->
<jsp:useBean id="memberService" scope="page" class="com.member.model.MemberService" />

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>第二步:付款</title>

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
<jsp:include page="/frontend/navbar_front-end.jsp" flush="true" />

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
		
		<th>會員姓名</th>
		<th>電子信箱</th>
		<th>電話號碼</th>
		
		<th>總價</th>
		<th>總張數</th>
		<th>訂票訂單成立時間</th>
		<th>付款方式</th>
		<th>訂票訂單狀態</th>
		<th><font color="red">繳費期限時間</font></th>
	</tr>
	<tr>
		<td>${shtovo.ticket_order_no}</td>
		
		<td>${memberService.getOneMember(shtovo.member_no).memberFullname}</td>
		<td>${memberService.getOneMember(shtovo.member_no).email}</td>
		<td>${memberService.getOneMember(shtovo.member_no).phone}</td>
		
		<td>${shtovo.total_price}</td>
		<td>${shtovo.total_amount}</td>
		<td><fmt:formatDate value="${shtovo.ticket_order_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		<td>
			${(shtovo.payment_method == 'NOTYET') ? '尚未選擇' : ''}
			${(shtovo.payment_method == 'CREDITCARD') ? '信用卡' : ''}
			${(shtovo.payment_method == 'EWALLET') ? '電子錢包' : ''}
		</td>
		<td>
			${(shtovo.ticket_order_status == 'WAITTOPAY1') ? '尚未付款' : ''}
			${(shtovo.ticket_order_status == 'COMPLETE2') ? '完成付款' : ''}
			${(shtovo.ticket_order_status == 'CANCEL3') ? '已取消' : ''}
			${(shtovo.ticket_order_status == 'OUTDATE4') ? '逾時未付' : ''}
		</td>
<% 
java.sql.Timestamp ticket_order_payment_deadline = null;
Long ticketOrderCreatedTimeInLongInt = null;
Long deadLineTime = null;
String strError = "";
try {
	ticketOrderCreatedTimeInLongInt = shtovo.getTicket_order_time().getTime();
	deadLineTime = ticketOrderCreatedTimeInLongInt+sessionLiveTime*1000;
	ticket_order_payment_deadline = new java.sql.Timestamp(deadLineTime);
 } catch (Exception e) {
	 System.out.println("ticket_order_payment_deadline error");
	 strError = "ticket_order_payment_deadline_error";
	 ticket_order_payment_deadline = new java.sql.Timestamp(System.currentTimeMillis());
 }
%>
		<td><fmt:formatDate value="<%=ticket_order_payment_deadline%>" pattern="yyyy-MM-dd HH:mm:ss"/><b><%=strError%></b></td>
	</tr>
	<tr>
		<th>座位區名稱</th>
		<th>活動名稱</th>
		<th>場次名稱</th>
		<th>活動開始日期</th>
		<th>活動地點</th>
	</tr>
	<tr>
		<td>${shtovo.ticarea_name}</td>
		<td>${shtovo.evetit_name}</td>
		<td>${shtovo.eve_sessionname}</td>
		<td><fmt:formatDate value="${shtovo.eve_startdate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		<td>${shtovo.venue_name}</td>
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
<input type="hidden" name="action" value="buyticket_NoImg_done_phaseThree">
<input type="hidden" name="member_no" value="${shtovo.member_no}">
<input type="hidden" name="ticket_order_no"  value="${shtovo.ticket_order_no}">
<input type="submit" value="進行付款">
</FORM>

<jsp:include page="/frontend/footer_front-end.jsp" flush="true" />
</body>
</html>