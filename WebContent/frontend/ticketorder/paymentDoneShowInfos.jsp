<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ticketorder.model.*"%>
<%@ page import="com.ticket.model.*"%>
<%@ page import="com.seating_area.model.*"%>
<%@ page import="com.event.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
TicketOrderVO toVO = (TicketOrderVO)request.getAttribute("updated_tovo");
List<TicketVO> tVO = (List<TicketVO>)request.getAttribute("memberListTVO");
pageContext.setAttribute("toVO",toVO);
pageContext.setAttribute("tVO",tVO);
String eve_noFromPage = request.getParameter("eve_no");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>模擬訂票訂單資料附帶票券的新增 - selectPayment.jsp</title><h3><%=eve_noFromPage%></h3>

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
		<th>員工編號</th>
		<th>員工姓名</th>
		<th>職位</th>
		<th>雇用日期</th>
		<th>薪水</th>
		<th>獎金</th>
		<th>部門</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<c:forEach var="empVO" items="${list}">
		<tr>
			<td>${empVO.empno}</td>
			<td>${empVO.ename}</td>
			<td>${empVO.job}</td>
			<td>${empVO.hiredate}</td>
			<td>${empVO.sal}</td>
			<td>${empVO.comm}</td>			
			<td>
			  ${empVO.deptVO.deptno}【<font color=orange>${empVO.deptVO.dname}</font> - ${empVO.deptVO.loc}】
			</td>
			<td></td>
			<td></td>
		</tr>
	</c:forEach>
</table>



<h4><a href="<%=request.getContextPath()%>/frontend/ticketorder/select_page.jsp">前往會員的訂單查詢</a></h4>


</body>
</html>