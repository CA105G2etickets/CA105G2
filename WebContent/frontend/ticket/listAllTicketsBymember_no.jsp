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
%>

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>listAllTicketsBymember_no.jsp</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
</head>
<jsp:useBean id="ticketService" scope="page" class="com.ticket.model.TicketService" />
<jsp:useBean id="SeatingArea_H5_Service" scope="page" class="com.seating_area.model.SeatingArea_H5_Service" />
<jsp:useBean id="Event_H5_Service" scope="page" class="com.event.model.Event_H5_Service" />
<%-- <jsp:useBean id="memberService" scope="page" class="com.member.model.MemberService" /> --%>
<body>
<jsp:include page="/frontend/navbar_front-end.jsp" flush="true" />
    <table id="example" class="display" style="width:100%">
        <thead>
            <tr>
                <th>票券編號</th>
                <th>持票人</th>
                <th>票券成立時間</th>
                <th>狀態</th>
                <th>活動的地址</th>
                <th>活動開始時間</th>
                <th>活動主題名稱</th>
                <th>票種名稱與票價</th>
            </tr>
        </thead>
        <tbody>
        	<c:forEach var="TicketOrderVO" items="${ticketorderService.getTicketOrdersByMemberNo(member_no)}">
        		<tr>
        			<td>${TicketOrderVO.ticket_order_no}</td>
        			<%-- <td>${memberService.getOneMember(TicketOrderVO.member_no).memberFullname}</td> --%>
        			<td>${TicketOrderVO.total_price}元, ${TicketOrderVO.total_amount} 張</td>
        			<td><fmt:formatDate value="${TicketOrderVO.ticket_order_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
        			<td>
        				${(TicketOrderVO.payment_method == 'NOTYET') ? '尚未選擇' : ''}
						${(TicketOrderVO.payment_method == 'CREDITCARD') ? '信用卡' : ''}
						${(TicketOrderVO.payment_method == 'EWALLET') ? '電子錢包' : ''}
        			</td>
        			<td>
        				${(TicketOrderVO.ticket_order_status == 'WAITTOPAY1') ? '尚未付款' : ''}
						${(TicketOrderVO.ticket_order_status == 'COMPLETE2') ? '完成付款' : ''}
						${(TicketOrderVO.ticket_order_status == 'CANCEL3') ? '已取消' : ''}
						${(TicketOrderVO.ticket_order_status == 'OUTDATE4') ? '逾時未付' : ''}
					</td>
					<td>${Event_H5_Service.getOneEvent_H5(SeatingArea_H5_Service.getOneSeatingArea_H5(TicketOrderVO.seatingarea_h5VO.ticarea_no).eve_h5VO.eve_no).eventtitle_h5VO.evetit_name}</td>
					<td>${Event_H5_Service.getOneEvent_H5(SeatingArea_H5_Service.getOneSeatingArea_H5(TicketOrderVO.seatingarea_h5VO.ticarea_no).eve_h5VO.eve_no).venue_h5VO.venue_name}</td>
        		</tr>
        	</c:forEach>
        </tbody>
        <tfoot>
            <tr>
            	<th>訂票訂單編號</th>
                <!-- <th>會員姓名</th> -->
                <th>總價與張數</th>
                <th>訂票訂單成立時間</th>
                <th>付款方式</th>
                <th>狀態</th>
                <th>活動主題名稱</th>
                <th>舉辦場地名稱</th>
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