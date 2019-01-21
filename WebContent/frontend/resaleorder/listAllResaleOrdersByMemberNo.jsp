<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ticket.model.*"%>
<%@ page import="com.resaleorder.model.*"%>

<%@ page import="java.util.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
List<ResaleOrderVO> list_revos = (List<ResaleOrderVO>)request.getAttribute("list_revos");
pageContext.setAttribute("list_revos", list_revos);
%>
<jsp:useBean id="memberService" scope="page" class="com.member.model.MemberService" />
<jsp:useBean id="resaleorderService" scope="page" class="com.resaleorder.model.ResaleOrderService" />
<html>
<head>
<title>listAllResaleOrdersByMemberNo.jsp</title>

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
		<th>轉讓訂單編號</th>
		<th>買家姓名</th>
		<th>賣家姓名</th>
		<th>成交價</th>
		
		<th>目標轉讓票券編號</th>
	</tr>
	<c:forEach var="resaleorderVO" items="${list_revos}">
		<tr>
			<td>${resaleorderVO.resale_ordno}</td>
			<td>${memberService.getOneMember(resaleorderVO.member_buyer_no).memberFullname}</td>
			<td>${memberService.getOneMember(resaleorderVO.member_seller_no).memberFullname}</td>
			<td>${resaleorderVO.resale_ordprice}</td>
			
			<td>${resaleorderVO.ticketVO.ticket_no}</td>
			
			
			<%-- <td>
			${(showticketVO.ticket_order_status == 'ACTIVE1') ? '尚未付款' : ''}
			${(showticketVO.ticket_order_status == 'USED2') ? '完成付款' : ''}
			${(showticketVO.ticket_order_status == 'OUTDATE3') ? '已取消' : ''}
			${(showticketVO.ticket_order_status == 'REFUND4') ? '逾時未付' : ''}
			</td>
			<td>${showticketVO.ticarea_name}</td>
			<td>${showticketVO.ticarea_color}</td>
			
			
			<td>${showticketVO.tictype_price}</td>
			
			<td>${showticketVO.eve_sessionname}</td>
			<td><fmt:formatDate value="${showticketVO.eve_startdate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			
			<td>${showticketVO.evetit_name}</td>
			<td>${showticketVO.venue_name}</td>
			<td>${showticketVO.address}</td>
			
			<td>${showticketVO.ticket_resale_status}</td>
			<td>${showticketVO.ticket_resale_price}</td> --%>
		</tr>
	</c:forEach>
</table>

<jsp:include page="/frontend/footer_front-end.jsp" flush="true" />
</body>
</html>