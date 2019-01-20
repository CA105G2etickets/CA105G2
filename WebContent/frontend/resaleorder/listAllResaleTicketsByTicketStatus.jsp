<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<%
String member_no = (String)request.getAttribute("member_no");
if(member_no == null || (member_no.trim()).length() == 0){
	MemberVO member = (MemberVO) session.getAttribute("member");
	member_no = member.getMemberNo();
}
pageContext.setAttribute("member_no",member_no);
%>

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>listAllResaleTicketsByTicketStatus.jsp</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
</head>
<jsp:useBean id="ticketService" scope="page" class="com.ticket.model.TicketService" />
<jsp:useBean id="SeatingArea_H5_Service" scope="page" class="com.seating_area.model.SeatingArea_H5_Service" />
<jsp:useBean id="Event_H5_Service" scope="page" class="com.event.model.Event_H5_Service" />
<jsp:useBean id="memberService" scope="page" class="com.member.model.MemberService" />
<body>
<jsp:include page="/frontend/navbar_front-end.jsp" flush="true" />
<%-- 錯誤表列 --%>
	<div class="container">
		<c:if test="${not empty errorMsgs}">
			<font style="color:red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color:red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
	</div>

    <table id="example" class="display" style="width:100%">
        <thead>
            <tr>
                <th>票券編號</th>
                <th>賣票者電子信箱</th>
                <th>賣價</th>
                <th>原票價與票種名稱</th>
                <th>活動主題名稱</th>
                <th>活動開始時間</th>
                <th>活動地點</th>
                <th>購買</th>
            </tr>
        </thead>
        <tbody>
        	<c:forEach var="TicketVO" items="${ticketService.getTicketsOnResale()}">
        		<tr>
        			<td>${TicketVO.ticket_no}</td>
        			<td>${memberService.getOneMember(TicketVO.member_no).email}</td>
        			<%-- <td><fmt:formatDate value="${TicketVO.ticket_create_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td> --%>
        			<td>${TicketVO.ticket_resale_price}</td>
        			<td>原價:${SeatingArea_H5_Service.getOneSeatingArea_H5(TicketVO.seatingarea_h5VO.ticarea_no).tickettype_h5VO.tictype_price}, ${SeatingArea_H5_Service.getOneSeatingArea_H5(TicketVO.seatingarea_h5VO.ticarea_no).tickettype_h5VO.tictype_name}</td>
        			<td>${Event_H5_Service.getOneEvent_H5(SeatingArea_H5_Service.getOneSeatingArea_H5(TicketVO.seatingarea_h5VO.ticarea_no).eve_h5VO.eve_no).eventtitle_h5VO.evetit_name}</td>
        			<td><fmt:formatDate value="${Event_H5_Service.getOneEvent_H5(SeatingArea_H5_Service.getOneSeatingArea_H5(TicketVO.seatingarea_h5VO.ticarea_no).eve_h5VO.eve_no).eve_startdate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
        			<td>${Event_H5_Service.getOneEvent_H5(SeatingArea_H5_Service.getOneSeatingArea_H5(TicketVO.seatingarea_h5VO.ticarea_no).eve_h5VO.eve_no).venue_h5VO.venue_name}</td>
        			<td>
        				<c:if test="${TicketVO.ticket_resale_status == 'SELLING2'}">
        					<c:if test="${TicketVO.member_no != member_no}">
        						<form method="post" action="<%=request.getContextPath()%>/frontend/resaleorder/resaleorder.do">
									<input type="hidden" name="action" value="member_buy_One_Resale_ticket">
									<input type="hidden" name="member_seller_no" value="${TicketVO.member_no}">
									<input type="hidden" name="member_buyer_no" value="${member_no}">
									<input type="hidden" name="ticket_no" value="${TicketVO.ticket_no}">
									<input type="hidden" name="resale_ordprice" value="${TicketVO.ticket_resale_price}">
									<input type="submit" value="購買" class="btn btn-primary" style="float:right;">
								</form>
        					</c:if>
						</c:if>
        			</td>
        		</tr>
        	</c:forEach>
        </tbody>
        <tfoot>
           <tr>
                <th>票券編號</th>
                <!-- <th>賣票者電子信箱</th> -->
                <th>賣價</th>
                <th>原票價與票種名稱</th>
                <th>活動主題名稱</th>
                <th>活動開始時間</th>
                <th>活動地點</th>
                <th>購買</th>
            </tr>
        </tfoot>
    </table>
<jsp:include page="/frontend/footer_front-end.jsp" flush="true" />
    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>


	<script src="https://cdn.datatables.net/plug-ins/1.10.19/api/page.jumpToData().js"></script>

    <script>
    var table;
    var info;
    
    $(document).ready(function() {

    	var table = $('#example').DataTable();
    	
    });
    </script>
</body>

</html>