<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ticketorder.model.*"%>
<%@ page import="com.seating_area.model.*"%>
<%@ page import="com.event.model.*"%>
<%@ page import="com.ticket_type.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
List<SeatingAreaVO> slist = (List<SeatingAreaVO>)request.getAttribute("slist");
pageContext.setAttribute("slist",slist);
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>模擬訂票訂單資料附帶票券的新增 - eve_no_SelectedReady2Next.jsp</title>

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
<script>
	var e = document.getElementById("ddlViewBy");
	var strUser = e.options[e.selectedIndex].value;
</script>
<table id="table-1">
	<tr><td>
		 <h3>模擬訂票訂單資料附帶票券的新增 - eve_no_SelectedReady2Next.jsp</h3></td><td>
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
		<th>活動座位區編號</th>
		<th>活動座位區名稱</th>
		<th>總容許銷售張數</th>
		<th>已訂的票券張數</th>
		<th>單張票價</th>
		<th>要買的張數</th>
		<th>送出買票請求</th>
	</tr>
	<!--  
	<c:forEach var="SeatingAreaVO" items="${slist}">
		<tr>
			<td>${SeatingAreaVO.ticarea_no}</td>
			<td>${SeatingAreaVO.ticarea_name}</td>
			<td>${SeatingAreaVO.tictotalnumber}</td>
			<td>${SeatingAreaVO.ticbookednumber}</td>
			<td><select id = "ticketsCount">
					<option value="1" selected="selected">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
				</select></td>
			<td>
				<FORM METHOD="post" ACTION="ticketorder.do">
				<input type="submit" value="send">
				<input type="hidden" name="ticarea_no"  value="${SeatingAreaVO.ticarea_no}">
				<input type="hidden" name="ticketsNum"  value="2">
				<input type="hidden" name="action"	value="buyTickets"></FORM>
			</td>
		</tr>
	</c:forEach>
	-->
<jsp:useBean id="TicketTypeSvc" scope="page" class="com.ticket_type.model.TicketTypeService" />
	<c:forEach var="SeatingAreaVO" items="${slist}">
		<FORM METHOD="post" ACTION="ticketorder.do">
			<table>
				<tr>
					<td>${SeatingAreaVO.ticarea_no}</td>
					<td>${SeatingAreaVO.ticarea_name}</td>
					<td>${SeatingAreaVO.tictotalnumber}</td>
					<td>${SeatingAreaVO.ticbookednumber}</td>
					<td>
						<c:forEach var="TicketTypeVO" items="${TicketTypeSvc.all}">
							<c:if test="${SeatingAreaVO.tictype_no==TicketTypeVO.tictype_no}">
								${TicketTypeVO.tictype_price}
							</c:if>
						</c:forEach>
					</td>
					<td>
						<select name = "ticketsNum">
						<option value="1" selected="selected">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						</select>
					</td>
					<td>
						<input type="submit" value="send">
						<input type="hidden" name="tictype_no"  value="${SeatingAreaVO.tictype_no}">
						<input type="hidden" name="ticarea_no"  value="${SeatingAreaVO.ticarea_no}">
						<input type="hidden" name="action"	value="buyTickets">
						<%String str = (String)request.getAttribute("eve_no");%>
						<input type="hidden" name="eve_no" value="<%=str%>">
					</td>
				</tr>
			</table>
		</FORM>
	</c:forEach>
</table>
</body>
</html>