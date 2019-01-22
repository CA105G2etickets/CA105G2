<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%-- <%@ page import="com.member.model.*"%> --%>
<%@ page import="com.seating_area.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<%
//String member_no = (String)request.getAttribute("member_no");
//pageContext.setAttribute("member_no",member_no);
String eve_no = (String)request.getAttribute("eve_no");
pageContext.setAttribute("eve_no",eve_no);
%>

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>listAllSeatingAreaByEveNo.jsp</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
</head>
<%-- <jsp:useBean id="memberService" scope="page" class="com.member.model.MemberService" />  
<jsp:useBean id="ticketService" scope="page" class="com.ticket.model.TicketService" /> 
<jsp:useBean id="SeatingArea_H5_Service" scope="page" class="com.seating_area.model.SeatingArea_H5_Service" /> --%>
<jsp:useBean id="Event_H5_Service" scope="page" class="com.event.model.Event_H5_Service" />
<jsp:useBean id="SeatingAreaService" scope="page" class="com.seating_area.model.SeatingAreaService" />
<jsp:useBean id="TicketTypeService" scope="page" class="com.ticket_type.model.TicketTypeService" />

<body>
<jsp:include page="/backend/navbar_back-end.jsp" flush="true" />
    <table id="example" class="display" style="width:100%">
        <thead>
            <tr>
                <th>活動主題名稱</th>
                <th>場次名稱</th>
                <!-- <th>活動座位區編號</th> -->
                <th>活動座位區名稱</th>
                <!-- <th>該區票價</th> -->
                <th>該區容許銷售張數</th>
                <th>該區剩餘張數</th>
                <th>該區票價</th>
                <th>售票開始日期時間</th>
                <th>活動開始日期時間</th>
            </tr>
        </thead>
        <tbody>
        	<c:forEach var="seatingareavo" items="${SeatingAreaService.getAllSeatingAreaByEveNo(eve_no)}">
        		<tr>
        			<td>${Event_H5_Service.getOneEvent_H5(eve_no).eventtitle_h5VO.evetit_name}</td>
        			<td>${Event_H5_Service.getOneEvent_H5(eve_no).eve_sessionname}</td>
        			<%-- <td>${seatingareavo.ticarea_no}</td> --%>
        			<td>${seatingareavo.ticarea_name}</td>
        			<%-- <td>${seatingareavo.tickettype_h5VO.tictype_price}</td> --%>
        			<td>${seatingareavo.tictotalnumber}</td>
        			<td><font color="red">${seatingareavo.tictotalnumber - seatingareavo.ticbookednumber}</font></td>
        			<td>${TicketTypeService.getOneTicketType(seatingareavo.tictype_no).tictype_price}</td>
        			<td><fmt:formatDate value="${Event_H5_Service.getOneEvent_H5(eve_no).eve_onsaledate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
        			<td><fmt:formatDate value="${Event_H5_Service.getOneEvent_H5(eve_no).eve_startdate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
        		</tr>
        	</c:forEach>
        </tbody>
        <!-- <tfoot>
            <tr>
            	<th>活動編號</th>
                <th>場次名稱</th>
                <th>活動座位區編號</th>
                <th>活動座位區名稱</th>
                <th>該區票價</th>
                <th>該區容許銷售張數</th>
                <th>該區已訂張數</th>
            </tr>
        </tfoot> -->
    </table>
    
    <div class="container" style="margin-bottom:30px;">
		<div class="row">
			<div class="col-xs-12 col-sm-6">
				<div class="form-group eve_seatmap_area">
                 	<img src="<%= request.getContextPath()%>/event/EventGifReader?scaleSize=850&eve_no=${eve_no}" id="seatmap" style="width:100%;">
             	</div>
			</div> 
			<div class="col-xs-12 col-sm-6">
				<div class="form-group evetit_poster">
					<img src="<%= request.getContextPath()%>/event_title/EventTitleGifReader?scaleSize=850&evetit_no=${Event_H5_Service.getOneEvent_H5(eve_no).eventtitle_h5VO.evetit_no}" style="width:100%;">
				</div>
			</div> 
		</div> 
	</div>
    
    
    
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