<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.resaleorder.model.*"%>

<%@ page import="java.util.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%-- <%
TicketOrderVO toVO = (TicketOrderVO)request.getAttribute("updated_tovo");
Event_H5_VO eh5VO = (Event_H5_VO)request.getAttribute("eh5vo");
List<TicketVO> tVOs = (List<TicketVO>)request.getAttribute("memberListTVO");
List<SeatingArea_H5_VO> sVOs = (List<SeatingArea_H5_VO>)request.getAttribute("slist");
pageContext.setAttribute("toVO",toVO);
pageContext.setAttribute("eh5VO",eh5VO);
pageContext.setAttribute("tVOs",tVOs);
pageContext.setAttribute("sVOs",sVOs);
String eve_noFromPage = request.getParameter("eve_no");
%> --%>

<%
ShowResaleTicketVO svo = (ShowResaleTicketVO)request.getAttribute("svo");
pageContext.setAttribute("svo",svo);
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>paymentDoneShowInfos.jsp</title>

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

<%--  錯誤表列 
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
</c:if> --%>

<table>
	<tr>
		<th>票券編號</th>
		<th>轉讓訂單編號</th>
		<th>賣家會員編號</th>
		<th>買家編號</th>
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
		<th>此轉售訂單完成的時間</th>
		<!-- <th>進行付款</th> -->
	</tr>
	<tr>
		<td>${svo.ticket_no}</td>
		<td>${svo.resale_ordno}</td>
		<td>${svo.member_seller_no}</td>
		<td>${svo.member_buyer_no}</td>
		<td>${svo.ticket_resale_price}</td>
		
		<td>${svo.tictype_name}</td>
		
		<td>${svo.eve_sessionname}</td>
		<td>${svo.venue_name}</td>
		<td>${svo.address}</td>
		<td>${svo.eve_startdate}</td>
		<td>${svo.evetit_name}</td>
		
		<td><fmt:formatDate value="${svo.resale_ord_createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		<td><fmt:formatDate value="${svo.resale_ord_completetime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		
		<%-- <td>
			<FORM METHOD="post" ACTION="ticketorder.do">
				<input type="submit" value="pay">
				<input type="hidden" name="ticket_order_no"  value="${toVO.ticket_order_no}">
				<input type="hidden" name="action"	value="userWantToPay"></FORM>
		</td> --%>
	</tr>
</table>


<h4><a href="<%=request.getContextPath()%>/frontend/resaleorder/select_page.jsp">前往查詢頁</a></h4>
<%-- <form method="post" action="<%=request.getContextPath()%>/frontend/resaleorder/resaleorder.do">
	<input type="hidden" name="action" value="member_select_ticketorders">
	<input type="hidden" name="member_no" value="${toVO.member_no}"> <!--Member not done yet, only been set as M000001 -->
	<input type="submit" value="前往初始的查詢頁面">
</form> --%>

</body>
</html>