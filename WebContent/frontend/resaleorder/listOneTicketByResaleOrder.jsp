<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ticket.model.*"%>
<%@ page import="com.resaleorder.model.*"%>

<%@ page import="java.util.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
String resale_ordno = (String)request.getAttribute("resale_ordno");
pageContext.setAttribute("resale_ordno", resale_ordno);
TicketVO tvo = (TicketVO)request.getAttribute("tvo");
pageContext.setAttribute("tvo", tvo);
%>

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>listOneTicketByResaleOrder.jsp</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
</head>

<%--<jsp:useBean id="ticketService" scope="page" class="com.ticket.model.TicketService" />--%>
<jsp:useBean id="SeatingArea_H5_Service" scope="page" class="com.seating_area.model.SeatingArea_H5_Service" />
<jsp:useBean id="Event_H5_Service" scope="page" class="com.event.model.Event_H5_Service" /> 
<jsp:useBean id="resaleorderService" scope="page" class="com.resaleorder.model.ResaleOrderService" /> 
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

    <div class="container">
    	<div class="row">
    		<table id="example" class="display" style="width:100%">
        <thead>
            <tr>
                <th>票券編號</th>
                <th>持票人</th>
                <!-- <th>票券成立時間</th> -->
                <th>狀態</th>
                <th>活動開始時間</th>
                <th>活動的地址</th>
                <th>活動主題名稱</th>
                <th>轉讓成交價</th>
                <th>是否由轉讓取得</th>
                
            </tr>
        </thead>
        <tbody>
        	<tr>
        		<td>${tvo.ticket_no}</td>
        		<td>${memberService.getOneMember(tvo.member_no).memberFullname}</td>
        		<%-- <td><fmt:formatDate value="${tvo.ticket_create_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td> --%>
        		<td>
        			${(tvo.ticket_status == 'ACTIVE1') ? '未使用' : ''}
					${(tvo.ticket_status == 'USED2') ? '已使用' : ''}
					${(tvo.ticket_status == 'OUTDATE3') ? '已過期' : ''}
					${(tvo.ticket_status == 'REFUND4') ? '已退票' : ''}
        		</td>
        		<td><fmt:formatDate value="${Event_H5_Service.getOneEvent_H5(SeatingArea_H5_Service.getOneSeatingArea_H5(tvo.seatingarea_h5VO.ticarea_no).eve_h5VO.eve_no).eve_startdate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
        		<td>${Event_H5_Service.getOneEvent_H5(SeatingArea_H5_Service.getOneSeatingArea_H5(tvo.seatingarea_h5VO.ticarea_no).eve_h5VO.eve_no).venue_h5VO.address}</td>
        		<td>${Event_H5_Service.getOneEvent_H5(SeatingArea_H5_Service.getOneSeatingArea_H5(tvo.seatingarea_h5VO.ticarea_no).eve_h5VO.eve_no).eventtitle_h5VO.evetit_name}</td>
        		
				<td>${resaleorderService.getOneResaleOrd(resale_ordno).resale_ordprice} 元</td>
				<%-- <td>
        			${(TicketVO.ticket_resale_status == 'NONE1') ? '無' : ''}
					${(TicketVO.ticket_resale_status == 'SELLING2') ? '欲轉讓' : ''}
					${(TicketVO.ticket_resale_status == 'CHECKING3') ? '有人想要' : ''}
        		</td> --%>
        		<td>
        			<%-- <c:if test="(${resaleorderService.getOneResaleOrd(resale_ordno).ticketVO.is_from_resale} == 'YES')">
        				是
        			</c:if>
        			<c:if test="(${resaleorderService.getOneResaleOrd(resale_ordno).ticketVO.is_from_resale} == 'NO')">
        				否
        			</c:if> --%>
        			是
        		</td>
        		</tr>
        </tbody>
        
    </table>
    	</div>
    </div>
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