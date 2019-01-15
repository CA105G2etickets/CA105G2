<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.resaleorder.model.*"%>

<%-- 萬用複合查詢-可由客戶端select_page.jsp隨意增減任何想查詢的欄位 --%>
<%-- 此頁只作為複合查詢時之結果練習，可視需要再增加分頁、送出修改、刪除之功能--%>

<%-- <jsp:useBean id="listEmps_ByCompositeQuery" scope="request" type="java.util.List<EmpVO>" /> --%> <!-- 於EL此行可省略 -->
<%-- <jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" /> --%>                <!-- 於Hibernate環境此行已可省略 -->

<%--maybe add javaBean here, add SeatingAreaH5 and Event_H5_Service --%>
<jsp:useBean id="eh5Svc" scope="page" class="com.event.model.Event_H5_Service" />
<jsp:useBean id="sh5Svc" scope="page" class="com.seating_area.model.SeatingArea_H5_Service" />

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

<h4>
☆萬用複合查詢  - 可由客戶端 select_page.jsp 隨意增減任何想查詢的欄位<br>
☆此頁只作為複合查詢時之結果練習，可視需要再增加分頁、送出修改、刪除之功能</h4>

<table>
	<tr>
		<th>兜售此票的會員</th>
		<!-- <th>賣家賣出此票的日期時間</th> -->
		<th>座位區名稱</th>
		<th>出售價格</th>
		
		<%-- 
		<th>原本價格</th>
		<th>此票連結到的活動頁資訊</th>
		<th>此票連結到的活動地點</th>
		<th>此票連結到的活動的舉辦時間</th>
		<th>此票販賣截止日期</th>
		--%>
		<th>購買</th>
		
	</tr>
	<c:forEach var="ticketVO" items="${listTicketsResaled_ByCompositeQuery}">
		<tr>
			<td>${ticketVO.member_no}</td>
			  
			<%-- <td><font color = orange>
				<c:forEach var="resaleorderVO" items="${ticketVO.resaleords}">
					<td>${resaleorderVO.resale_ord_createtime}</td> 
					maybe add if condition to get the latest created resaleord
				</c:forEach></font></td> --%>
				
			<td><font color = orange>
			${ticketVO.seatingarea_h5VO.ticarea_name}</font></td>
			
			<td>${ticketVO.ticket_resale_price}</td>
			
			<%-- <td><c:forEach var=seatingareaVO items="${sh5Svc.all}">
				<c:if test="${ticketVO.seatingarea_h5VO.ticarea_no==seatingareaVO.ticarea_no}">
					 ${seatingareaVO.tickettype_h5VO.tictype_price} 
				</c:if>
			</c:forEach></td> //failed, this should be error,cause the pattern is implemented by teacher's example
			或許根本不需要用javabean做，控制器端用原始第一個VO中的外來建去取別張table的資料即可 --%>
			
			
			
			<td>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/frontend/resaleorder/resaleorder.do" style="margin-bottom: 0px;">
			     <input type="submit" value="購買">
			     <input type="hidden" name="ticket_no"  value="${ticketVO.ticket_no}">
			     <input type="hidden" name="action"	value="getOne_To_Buy"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>

</body>
</html>