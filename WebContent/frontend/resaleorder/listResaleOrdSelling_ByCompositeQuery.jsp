<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.resaleorder.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%-- 萬用複合查詢-可由客戶端select_page.jsp隨意增減任何想查詢的欄位 --%>
<%-- 此頁只作為複合查詢時之結果練習，可視需要再增加分頁、送出修改、刪除之功能--%>

<%-- <jsp:useBean id="listEmps_ByCompositeQuery" scope="request" type="java.util.List<EmpVO>" /> --%> <!-- 於EL此行可省略 -->
<%-- <jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" /> --%>                <!-- 於Hibernate環境此行已可省略 -->

<%--maybe add javaBean here, add SeatingAreaH5 and Event_H5_Service --%>
<%-- <jsp:useBean id="eh5Svc" scope="page" class="com.event.model.Event_H5_Service" />
<jsp:useBean id="sh5Svc" scope="page" class="com.seating_area.model.SeatingArea_H5_Service" /> --%>

<%
List<ShowResaleTicketVO> listShow = (List<ShowResaleTicketVO>)request.getAttribute("listShow");
pageContext.setAttribute("listShow", listShow);
%>

<html>
<head><title>複合查詢的結果: - listSellingTickets_ByCompositeQuery.jsp</title>


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
<h3>此購買轉售票的流程中，按下購買轉售票，會先寫死買家是M000002，於控制器內，去買M00001在他的列出票券頁面所賣出的票，因此這個購買流程每一次購買成功後，用當前的"瀏覽我的票券"去看就會少一張</h3>
<table>
	<tr>
		<th>票券編號</th>
		<!-- <th>販賣的會員編號</th> -->
		<th>賣價</th>
		<!-- <th>此轉讓單的創建時間</th> -->
		<th>座位區名稱</th>
		<!-- <th>座位區的顏色色碼</th> -->
		
		<th>票種名稱</th>
		<th>原價</th>
		
		<th>場次名稱</th>
		<th>場地名稱</th>
		<th>場地地址</th>
		
		<th>活動開始日期</th>
		
		<th>活動主題名稱</th>
		<th>購買</th>
	</tr> 
	<c:forEach var="ShowResaleTicketVO" items="${listShow}">
		
	<tr>
		<td>${ShowResaleTicketVO.ticket_no}</td>
		
		<td>${ShowResaleTicketVO.ticket_resale_price}</td>
		<td>${ShowResaleTicketVO.ticarea_name}</td>
		<td>${ShowResaleTicketVO.tictype_name}</td>
		<td>${ShowResaleTicketVO.tictype_price}</td>
		
		<td>${ShowResaleTicketVO.eve_sessionname}</td>
		<td>${ShowResaleTicketVO.venue_name}</td>
		<td>${ShowResaleTicketVO.address}</td>
		<td><fmt:formatDate value="${ShowResaleTicketVO.eve_startdate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		<td>${ShowResaleTicketVO.evetit_name}</td>
		<td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/frontend/resaleorder/resaleorder.do">
			<input type="submit" value="購買">
			
			<input type="hidden" name="ticket_no"  value="${ShowResaleTicketVO.ticket_no}">
			<input type="hidden" name="ticket_resale_price"  value="${ShowResaleTicketVO.ticket_resale_price}">
			<input type="hidden" name="action" value="selected_targetResaleTicketToBuy"></FORM>
		</td>
	</tr>
	</c:forEach>
</table>

</body>
</html>