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

<body>
<jsp:include page="/backend/navbar_back-end.jsp" flush="true" />
    <table id="example" class="display" style="width:100%">
        <thead>
            <tr>
                <th>活動編號</th>
                <th>場次名稱</th>
                <th>活動座位區編號</th>
                <th>活動座位區名稱</th>
                <!-- <th>該區票價</th> -->
                <th>該區容許銷售張數</th>
                <th>該區已訂張數</th>
            </tr>
        </thead>
        <tbody>
        	<c:forEach var="seatingareavo" items="${SeatingAreaService.getAllSeatingAreaByEveNo(eve_no)}">
        		<tr>
        			<td>${eve_no}</td>
        			<td>${Event_H5_Service.getOneEvent_H5(eve_no).eve_sessionname}</td>
        			<td>${seatingareavo.ticarea_no}</td>
        			<td>${seatingareavo.ticarea_name}</td>
        			<%-- <td>${seatingareavo.tickettype_h5VO.tictype_price}</td> --%>
        			<td>${seatingareavo.tictotalnumber}</td>
        			<td><font color="red">${seatingareavo.ticbookednumber}</font></td>
        		</tr>
        	</c:forEach>
        </tbody>
        <tfoot>
            <tr>
            	<th>活動編號</th>
                <th>場次名稱</th>
                <th>活動座位區編號</th>
                <th>活動座位區名稱</th>
                <!-- <th>該區票價</th> -->
                <th>該區容許銷售張數</th>
                <th>該區已訂張數</th>
            </tr>
        </tfoot>
    </table>
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