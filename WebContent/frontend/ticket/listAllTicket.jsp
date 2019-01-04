<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ticket.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%-- 此頁練習採用 EL 的寫法取值--%> 

<%
	TicketService tSvc = new TicketService();
    List<TicketVO> list = tSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有票券資料 - listAllTicket.jsp</title>

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

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有票券資料 - listAllTicket.jsp</h3>
		 <h4><a href="select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

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
		<th>座位區編號</th>
		<th>訂票訂單編號</th>
		<th>會員編號</th>
		<th>票券狀態</th>
		<th>票券成立時間</th>
		<th>票券轉讓狀態</th>
		<th>票券轉讓價格</th>
		<th>是否來自轉讓</th>
	</tr>
	<%@ include file="page1.file" %>
	<c:forEach var="TicketVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
	<tr>
		<td>${TicketVO.ticket_no}</td>
		<td>${TicketVO.ticarea_no}</td>
		<td>${TicketVO.ticket_order_no}</td>
		<td>${TicketVO.member_no}</td>
		<td>${TicketVO.ticket_status}</td>
		<td><fmt:formatDate value="${TicketVO.ticket_create_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		<td>${TicketVO.ticket_resale_status}</td>
		<td>${TicketVO.ticket_resale_price}</td>
		<td>${TicketVO.is_from_resale}</td>
			<td>
			  <FORM METHOD="post" ACTION="ticket.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="ticket_no"  value="${TicketVO.ticket_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="ticket.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="ticket_no"  value="${TicketVO.ticket_no}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>