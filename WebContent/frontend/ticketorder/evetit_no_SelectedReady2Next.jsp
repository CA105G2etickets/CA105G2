<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ticketorder.model.*"%>
<%@ page import="com.seating_area.model.*"%>
<%@ page import="com.event.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%-- <%
/*
String strEvetitNo = "E0001"; 
System.out.println("string="+strEvetitNo);
EventService eSvc = new EventService();
List<EventVO> list = eSvc.findByEveTit_no(strEvetitNo);
*/

//original .jsp active-method
/* List<EventVO> list = (List<EventVO>)request.getAttribute("elist"); 
pageContext.setAttribute("elist",list);
 */
/*
This .jsp is missing EVE_SEATMAP AND EVETIT_POSTER.
*/

/*
SeatingAreaService saSvc = new SeatingAreaService();
List<SeatingAreaVO> saList = saSvc.getAll();
pageContext.setAttribute("saList",saList);
*/
%> --%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>模擬訂票訂單資料附帶票券的新增 - evetit_no_SelectedReady2Next.jsp</title>

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

<table id="table-1">
	<tr><td>
		 <h3>模擬訂票訂單資料附帶票券的新增 - evetit_no_SelectedReady2Next.jsp</h3></td><td>
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
<%-- <table>
	<tr>
		<th>活動場次名稱</th>
		<th>活動開始日期</th>
		<th>活動結束日期</th>
		<th>活動開始販賣票券日期</th>
		<th>活動停止販賣票券日期</th>
		<th>活動狀態</th>
		<th>買票按鈕</th>
		
	</tr>
	<c:forEach var="EventVO" items="${elist}">
		<tr>
			<td>${EventVO.eve_sessionname}</td>
			<td>${EventVO.eve_startdate}</td>
			<td>${EventVO.eve_enddate}</td>
			<td>${EventVO.eve_onsaledate}</td>
			<td>${EventVO.eve_offsaledate}</td>
			<td>${EventVO.eve_status}</td>
			<td>
				<FORM METHOD="post" ACTION="ticketorder.do">
				<input type="submit" value="選擇此場次">
				<input type="hidden" name="eve_no"  value="${EventVO.eve_no}">
				<input type="hidden" name="action"	value="select_EVE_NO_toBuyTickets"></FORM>
			</td>
		</tr>
	</c:forEach>
</table> --%>
<form method="post" action="<%=request.getContextPath()%>/frontend/ticketorder/ticketorder.do" >
	<table>
		<tr>
			<td>input eve_no here:</td>
			<td><input type="TEXT" name="eve_no" size="10"
			 value="EV00001" /></td>
			 <td><input type="hidden" name="action"	value="select_EVE_NO_toBuyTickets"></td>
			 <td><input type="submit" value="send"></td>
		</tr>
	</table>
</form>
<h4><a href="<%=request.getContextPath()%>/frontend/ticketorder/ticketorder.do">SnoopTheLink</a></h4>

<form action="post" action="<%=request.getContextPath()%>/frontend/ticketorder/ticketorder.do">
	<font>目前此按鈕會轉移到會員編號M000001的持有訂票訂單查詢，M000001是寫死的</font>
	<font>get 404 but dont know why...別按</font>
	<input type="hidden" name="action"	value="member_select_ticketorders">
	<input type="hidden" name="member_no"	value="M000001">
	<input type="submit" value="send">	
</form>

</body>
</html>