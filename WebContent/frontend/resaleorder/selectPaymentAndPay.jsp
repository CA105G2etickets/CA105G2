<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.resaleorder.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
Integer sessionLiveTime = session.getMaxInactiveInterval();
ShowResaleTicketVO svo = (ShowResaleTicketVO)request.getAttribute("svo");
pageContext.setAttribute("svo",svo);
String member_no = (String)request.getAttribute("member_no");
pageContext.setAttribute("member_no",member_no);

%>
<jsp:useBean id="memberService" scope="page" class="com.member.model.MemberService" />
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>selectPaymentAndPay.jsp at resaleorder</title>

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

<table>
	<tr>
		<th>票券編號</th>
		<th>轉讓訂單編號</th>
		<th>賣家會員姓名</th>
		<th>賣價</th>
		<!-- <th>座位區名稱</th> -->
		<th>票種名稱</th>
		<!-- <th>付款方式</th> -->
		<!-- <th>原價</th> -->
		<th>場次名稱</th>
		<th>場地名稱</th>
		<th>場地地址</th>
		<th>活動開始日期</th>
		<th>活動主題名稱</th>
		<th>此轉售訂單成立時間</th>
		<th><font color="red">繳費期限時間</font></th>
		<!-- <th>進行付款</th> -->
	</tr>
	<tr>
		<td>${svo.ticket_no}</td>
		<td>${svo.resale_ordno}</td>
		<td>${memberService.getOneMember(svo.member_seller_no).memberFullname}</td>
		<td>${svo.ticket_resale_price}</td>
		
		<td>${svo.tictype_name}</td>
		
		<td>${svo.eve_sessionname}</td>
		<td>${svo.venue_name}</td>
		<td>${svo.address}</td>
		<td>${svo.eve_startdate}</td>
		<td>${svo.evetit_name}</td>
		
		<td><fmt:formatDate value="${svo.resale_ord_createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
<% 
java.sql.Timestamp resale_order_payment_deadline = null;
Long ticketOrderCreatedTimeInLongInt = null;
Long deadLineTime = null;
String strError = "";
try {
	ticketOrderCreatedTimeInLongInt = svo.getResale_ord_createtime().getTime();
	deadLineTime = ticketOrderCreatedTimeInLongInt+sessionLiveTime*1000;
	resale_order_payment_deadline = new java.sql.Timestamp(deadLineTime);
 } catch (Exception e) {
	 System.out.println("resale_order_payment_deadline error");
	 strError = "resale_order_payment_deadline";
	 resale_order_payment_deadline = new java.sql.Timestamp(System.currentTimeMillis());
 }
%>
		<td><fmt:formatDate value="<%=resale_order_payment_deadline%>" pattern="yyyy-MM-dd HH:mm:ss"/><b>這裡的繳費期限到了會被取消的監聽器還沒做</b></td>
		
		<%-- <td>
			<FORM METHOD="post" ACTION="ticketorder.do">
				<input type="submit" value="pay">
				<input type="hidden" name="ticket_order_no"  value="${toVO.ticket_order_no}">
				<input type="hidden" name="action"	value="userWantToPay"></FORM>
		</td> --%>
	</tr>
</table>

<!-- here's payment input -->
<FORM METHOD="post" action="<%=request.getContextPath()%>/frontend/resaleorder/resaleorder.do">
	<table>
		<tr>
			<td>信用卡卡號:</td>
			<td><input type="TEXT" name="creditCardNumber" size="16" value=""/></td>
		</tr>
		<tr>
			<td>信用卡驗證碼:</td> <!-- teperorilly use post form to send -->
			<td><input type="TEXT" name="creditCardVerificationNumber" size="9" value=""/></td>
		</tr>
	</table>
	<br>
<input type="hidden" name="action" value="userPaying_from_resaleorder_phase3">
<%-- <font>resale_ordno=${svo.resale_ordno}</font>
<font>member_buyer_no=${svo.member_buyer_no}</font> --%>
<input type="hidden" name="resale_ordno"  value="${svo.resale_ordno}">
<input type="hidden" name="member_buyer_no"  value="${svo.member_buyer_no}">
<input type="submit" value="進行付款">
</FORM>

</body>
</html>