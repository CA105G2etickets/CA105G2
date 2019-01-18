<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ticketorder.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%-- 此頁練習採用 EL 的寫法取值--%> 

<%
List<TicketOrderVO> listShow = (List<TicketOrderVO>)request.getAttribute("listShow");
pageContext.setAttribute("listShow",listShow);
%>

<html>
<head>
<title>member_select_ticketorders.jsp</title>
<jsp:useBean id="memberService" scope="page" class="com.member.model.MemberService" />
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
		<th>總價</th>
		<th>總張數</th>
		<th>訂票訂單成立時間</th>
		
		<th>付款方式</th>
		<th>訂票訂單狀態</th>
		
		<th>場次名稱</th>
		<th>活動開始日期</th>
		<th>活動結束日期</th>
		<th>活動主題名稱</th>
		
		<th>場地名稱</th>
		<th>場地地址</th>
		
		<!-- <th>顯示該筆訂單的票券</th> -->
		
	</tr> 
	<c:forEach var="showticketorderVO" items="${listShow}">
		
	<tr>
		<td>${showticketorderVO.ticket_order_no}</td>
		<td>${memberService.getOneMember(showticketorderVO.member_no).memberFullname}</td>
		<td>${showticketorderVO.total_price}</td>
		<td>${showticketorderVO.total_amount}</td>
		<td><fmt:formatDate value="${showticketorderVO.ticket_order_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		
		<td>${showticketorderVO.payment_method}</td>
		<td>${showticketorderVO.ticket_order_status}</td>
		<td>${showticketorderVO.eve_sessionname}</td>
		<td>${showticketorderVO.eve_startdate}</td>
		<td>${showticketorderVO.eve_enddate}</td>
		<td>${showticketorderVO.evetit_name}</td>
		<td>${showticketorderVO.venue_name}</td>
		<td>${showticketorderVO.address}</td>
		<td>
			<%-- <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/frontend/ticketorder/ticketorder.do">
			<input type="submit" value="顯示此訂票訂單的票券">
			<input type="hidden" name="ticket_order_no"  value="${showticketorderVO.ticket_order_no}">
			<input type="hidden" name="member_no"  value="${showticketorderVO.member_no}">
			<input type="hidden" name="action" value="selected_targetTicketOrder_showTickets"></FORM> --%>
		</td>
	</tr>
	</c:forEach>
</table>
<jsp:include page="/frontend/footer_front-end.jsp" flush="true" />
</body>
</html>