<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ticketorder.model.*"%>
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
//pageContext.setAttribute("member_no","M000001");

String ticket_order_no = (String)request.getAttribute("ticket_order_no");
if(ticket_order_no == null || (ticket_order_no.trim()).length() == 0){
	pageContext.setAttribute("ticket_order_no","T_20181225_000001");
}else{
	pageContext.setAttribute("ticket_order_no",ticket_order_no);
	//pageContext.setAttribute("ticket_order_no","TO_20181225_000001");
}

%>

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>listAllTicketsBymember_noAndticket_order_no.jsp</title>
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
                <th>持票人</th>
                <th>票券成立時間</th>
                <th>狀態</th>
                <th>活動開始時間</th>
                <th>活動的地址</th>
                <th>活動主題名稱</th>
                <th>票種名稱與票價</th>
                <%-- <th>轉售狀態</th>--%>
                <th>是否要轉售</th>
                
            </tr>
        </thead>
        <tbody>
        	<c:forEach var="TicketVO" items="${ticketService.getTicketsByTicketOrderNoAndMemberNo(ticket_order_no,member_no)}">
        		<tr>
        			<td>${TicketVO.ticket_no}</td>
        			<td>${memberService.getOneMember(member_no).memberFullname}</td>
        			<td><fmt:formatDate value="${TicketVO.ticket_create_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
        			<td>
        				${(TicketVO.ticket_status == 'ACTIVE1') ? '未使用' : ''}
						${(TicketVO.ticket_status == 'USED2') ? '已使用' : ''}
						${(TicketVO.ticket_status == 'OUTDATE3') ? '已過期' : ''}
						${(TicketVO.ticket_status == 'REFUND4') ? '已退票' : ''}
        			</td>
        			<td><fmt:formatDate value="${Event_H5_Service.getOneEvent_H5(SeatingArea_H5_Service.getOneSeatingArea_H5(TicketVO.seatingarea_h5VO.ticarea_no).eve_h5VO.eve_no).eve_startdate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
        			<td>${Event_H5_Service.getOneEvent_H5(SeatingArea_H5_Service.getOneSeatingArea_H5(TicketVO.seatingarea_h5VO.ticarea_no).eve_h5VO.eve_no).venue_h5VO.address}</td>
        			<td>${Event_H5_Service.getOneEvent_H5(SeatingArea_H5_Service.getOneSeatingArea_H5(TicketVO.seatingarea_h5VO.ticarea_no).eve_h5VO.eve_no).eventtitle_h5VO.evetit_name}</td>
					<td>${SeatingArea_H5_Service.getOneSeatingArea_H5(TicketVO.seatingarea_h5VO.ticarea_no).tickettype_h5VO.tictype_name}, ${SeatingArea_H5_Service.getOneSeatingArea_H5(TicketVO.seatingarea_h5VO.ticarea_no).tickettype_h5VO.tictype_price} 元</td>
					<%-- <td>
        				${(TicketVO.ticket_resale_status == 'NONE1') ? '無' : ''}
						${(TicketVO.ticket_resale_status == 'SELLING2') ? '欲轉讓' : ''}
						${(TicketVO.ticket_resale_status == 'CHECKING3') ? '有人想要' : ''}
        			</td> --%>
        			<td>
        				<c:if test="${TicketVO.ticket_resale_status == 'NONE1'}">
							<form method="post" action="<%=request.getContextPath()%>/frontend/ticket/ticket.do">
								<input type="hidden" name="action" value="member_sell_One_ticket">
								<input type="hidden" name="member_no" value="${member_no}">
								<input type="hidden" name="ticket_no" value="${TicketVO.ticket_no}">
								<input type="hidden" name="ticket_order_no" value="${ticket_order_no}">
								我要賣<input type="number" style="width: 5em" name="ticket_resale_price" value="${SeatingArea_H5_Service.getOneSeatingArea_H5(TicketVO.seatingarea_h5VO.ticarea_no).tickettype_h5VO.tictype_price}">元
								<input type="submit" value="轉售此票" class="btn btn-primary" style="float:right;">
							</form>
						</c:if>
						<c:if test="${TicketVO.ticket_resale_status == 'SELLING2'}">
							<form method="post" action="<%=request.getContextPath()%>/frontend/ticket/ticket.do">
								<input type="hidden" name="action" value="member_cancel_One_resale_ticket">
								<input type="hidden" name="member_no" value="${member_no}">
								<input type="hidden" name="ticket_no" value="${TicketVO.ticket_no}">
								<input type="hidden" name="ticket_order_no" value="${ticket_order_no}">
								<input type="submit" value="取消轉售" class="btn btn-primary" style="float:right;">
							</form>
						</c:if>
						<c:if test="${TicketVO.ticket_resale_status == 'CHECKING3'}">
							有人想要，等待付款中
						</c:if>
        			</td>
        		</tr>
        	</c:forEach>
        </tbody>
        <tfoot>
            <tr>
                <th>票券編號</th>
                <th>持票人</th>
                <th>票券成立時間</th>
                <th>狀態</th>
                <th>活動的地址</th>
                <th>活動開始時間</th>
                <th>活動主題名稱</th>
                <th>票種名稱與票價</th>
                <th>轉售狀態</th>
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