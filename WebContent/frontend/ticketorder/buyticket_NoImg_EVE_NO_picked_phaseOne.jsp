<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ticketorder.model.*"%>
<%@ page import="com.seating_area.model.*"%>
<%@ page import="com.event.model.*"%>
<%@ page import="com.ticket_type.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
List<SeatingArea_H5_VO> slist = (List<SeatingArea_H5_VO>)request.getAttribute("slist");
Event_H5_VO eh5vo = (Event_H5_VO)request.getAttribute("eh5vo");

pageContext.setAttribute("slist",slist);
pageContext.setAttribute("eh5vo",eh5vo);

String member_no = (String)request.getAttribute("member_no");
pageContext.setAttribute("member_no",member_no);
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>第一步:選擇座位區與票數</title>

<style>
  table {
	width: 900px;
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
		<th>活動主題名稱</th>
		<!-- <th>活動主題主海報</th> -->
		<th>場次名稱</th>
		<!-- <th>座位配置圖</th> -->
		<th>活動開始日期</th>
		<th>活動結束日期</th>
		<th>售票結束日期</th>
		<!-- <th>單一會員購買張數限制</th>
		<th>可全額退款到期日的日期</th> -->
		<th>場地名稱</th>
		<th>場地地址</th>
	</tr>
	<tr>
		<td>${eh5vo.eventtitle_h5VO.evetit_name}</td>
		<td></td>
		<td>${eh5vo.eve_sessionname}</td>
		<td></td>
		<td><fmt:formatDate value="${eh5vo.eve_startdate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		<td><fmt:formatDate value="${eh5vo.eve_enddate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		<td><fmt:formatDate value="${eh5vo.eve_offsaledate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		<%-- <td>${eh5vo.ticlimit}</td>
		<td><fmt:formatDate value="${eh5vo.fullrefundenddate}" pattern="yyyy-MM-dd HH:mm:ss"/></td> --%>
		<td>${eh5vo.venue_h5VO.venue_name}</td>
		<td>${eh5vo.venue_h5VO.address}</td>
	</tr>
	
</table>

<table>
	<tr>
		<th>票種名稱</th>
		<th>票種代表顏色</th>
		<!-- <th>活動座位區名稱</th> -->
		<th>總容許銷售張數</th>
		<th>剩餘可買張數</th>
		<th>單張票價</th>
		<th>請選要買的張數</th>
		<th>送出購票請求</th>
	</tr>
	<c:forEach var="SeatingArea_H5_VO" items="${slist}">
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/frontend/ticketorder/ticketorder.do">
			<table>
				<tr>
					<td>${SeatingArea_H5_VO.tickettype_h5VO.tictype_name}</td>
					<td>${SeatingArea_H5_VO.tickettype_h5VO.tictype_color}</td>
					<%-- <td>${SeatingArea_H5_VO.ticarea_name}</td> --%>
					<td>${SeatingArea_H5_VO.tictotalnumber}</td>
					<td><font color="red">${SeatingArea_H5_VO.tictotalnumber - SeatingArea_H5_VO.ticbookednumber}</font></td>
					<td>${SeatingArea_H5_VO.tickettype_h5VO.tictype_price}</td>
					<td>
						<%-- <b>${eh5vo.ticlimit}</b>
						<select name = "ticketsNum">
						<option value="1" selected="selected">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						</select> --%>
						
						<%-- <b>${eh5vo.ticlimit}</b> --%>
						<select name = "ticketsNum" id="mySelect">
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
						</select>
						
					</td>
					<td>
						<input type="submit" value="購買">
						<%-- <font>tictype_no=${SeatingArea_H5_VO.tickettype_h5VO.tictype_no}</font>
						<font>ticarea_no=${SeatingArea_H5_VO.ticarea_no}</font>
						<font>eve_no=${eh5vo.eve_no}</font> --%>
						<input type="hidden" name="tictype_no"  value="${SeatingArea_H5_VO.tickettype_h5VO.tictype_no}">
						<input type="hidden" name="ticarea_no"  value="${SeatingArea_H5_VO.ticarea_no}">
						<input type="hidden" name="eve_no" value="${eh5vo.eve_no}">
						<input type="hidden" name="action"	value="buyticket_NoImg_TICKET_ORDER_created_phaseTwo">
						<input type="hidden" name="member_no"  value="${member_no}"><font>${member_no}</font>
					</td>
				</tr>
			</table>
		</FORM>
	</c:forEach>
</table>
<jsp:include page="/frontend/footer_front-end.jsp" flush="true" />
<script>
</script>

</body>
</html>