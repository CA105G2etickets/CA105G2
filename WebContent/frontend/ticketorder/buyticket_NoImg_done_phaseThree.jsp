<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ticketorder.model.*"%>
<%@ page import="com.seating_area.model.*"%>
<%@ page import="com.event.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
TicketOrderVO toVO = (TicketOrderVO)request.getAttribute("updated_tovo");
pageContext.setAttribute("toVO",toVO);
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
	</tr>
	<tr>
		<td>${toVO.ticket_order_no}</td>
		
		<td>${memberService.getOneMember(toVO.member_no).memberFullname}</td>
		<td>${memberService.getOneMember(toVO.member_no).email}</td>
		<td>${memberService.getOneMember(toVO.member_no).phone}</td>
		
		<td>${toVO.total_price}</td>
		<td>${toVO.total_amount}</td>
		<td><fmt:formatDate value="${toVO.ticket_order_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		<td>
			${(toVO.payment_method == 'NOTYET') ? '尚未選擇' : ''}
			${(toVO.payment_method == 'CREDITCARD') ? '信用卡' : ''}
			${(toVO.payment_method == 'EWALLET') ? '電子錢包' : ''}
		</td>
		<td>
			${(toVO.ticket_order_status == 'WAITTOPAY1') ? '尚未付款' : ''}
			${(toVO.ticket_order_status == 'COMPLETE2') ? '完成付款' : ''}
			${(toVO.ticket_order_status == 'CANCEL3') ? '已取消' : ''}
			${(toVO.ticket_order_status == 'OUTDATE4') ? '逾時未付' : ''}
		</td>
	</tr>
</table>


<FORM METHOD="post" action="<%=request.getContextPath()%>/frontend/ticketorder/ticketorder.do">
	<table>
		<tr>
			<td>查詢我的訂票訂單</td>
		</tr>
	</table>
	<br>
<input type="hidden" name="action" value="ticketorder_select_by_member_no">
<input type="hidden" name="member_no" value="${toVO.member_no}">
<input type="submit" value="查詢訂票訂單">
</FORM>
<br>
<FORM METHOD="post" action="<%=request.getContextPath()%>/frontend/ticket/ticket.do">
	<table>
		<tr>
			<td>查詢我的票券</td>
		</tr>
	</table>
	<br>
<input type="hidden" name="action" value="ticket_select_by_member_no">
<input type="hidden" name="member_no" value="${toVO.member_no}">
<input type="submit" value="查詢票券">
</FORM>
<br>
<FORM METHOD="post" action="<%=request.getContextPath()%>/frontend/resaleorder/resaleorder.do">
	<table>
		<tr>
			<td>查詢我的轉售訂單</td>
		</tr>
	</table>
	<br>
<input type="hidden" name="action" value="resaleorder_select_by_member_no">
<input type="hidden" name="member_no" value="${toVO.member_no}">
<input type="submit" value="查詢轉售訂單">
</FORM>
<br>
 
 <jsp:include page="/frontend/footer_front-end.jsp" flush="true" />
</body>
</html>