<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ticket.model.*"%>
<%@ page import="com.resaleorder.model.*"%>

<%@ page import="java.util.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
List<ResaleOrderVO> list_revos = (List<ResaleOrderVO>)request.getAttribute("list_revos");
pageContext.setAttribute("list_revos", list_revos);
%>

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>listAllResaleOrdersByMemberNo.jsp</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
</head>

<%-- <jsp:useBean id="ticketService" scope="page" class="com.ticket.model.TicketService" />
<jsp:useBean id="SeatingArea_H5_Service" scope="page" class="com.seating_area.model.SeatingArea_H5_Service" />
<jsp:useBean id="Event_H5_Service" scope="page" class="com.event.model.Event_H5_Service" /> --%>
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

    <table id="example" class="display" style="width:100%">
        <thead>
            <tr>
                <th>轉售訂單編號</th>
                <th>賣票者姓名</th>
                <th>買票者姓名</th>
                <th>轉售價格</th>
                
            </tr>
        </thead>
        <tbody>
        	<c:forEach var="resaleordervo" items="${list_revos}">
        		<tr>
        			<td>${resaleordervo.resale_ordno}</td>
        			<td>${memberService.getOneMember(resaleordervo.member_seller_no).memberFullname}</td>
        			<td>${memberService.getOneMember(resaleordervo.member_buyer_no).memberFullname}</td>
        			<%-- <td><fmt:formatDate value="${TicketVO.ticket_create_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td> --%>
        			<td>${resaleordervo.resale_ordprice}</td>
        			<td>
        				<c:if test="${resaleordervo.resale_ordstatus == 'COMPLETE2'}">
        					<form method="post" action="<%=request.getContextPath()%>/frontend/resaleorder/resaleorder.do">
								<input type="hidden" name="action" value="member_select_One_resaleorder_fromListAll">
								<!-- <font>member_select_One_resaleorder</font> -->
								<input type="hidden" name="member_buyer_no" value="${resaleordervo.member_buyer_no}">
								<input type="hidden" name="ticket_no" value="${resaleordervo.ticketVO.ticket_no}">
								<input type="submit" value="票券明細" class="btn btn-primary" style="float:right;">
							</form>
						</c:if>
						<c:if test="${resaleordervo.resale_ordstatus == 'WAITOPAY1'}">
							未完成付款
						</c:if>
						<c:if test="${resaleordervo.resale_ordstatus == 'CANCEL3'}">
							已取消
						</c:if>
						<c:if test="${resaleordervo.resale_ordstatus == 'OUTDATE4'}">
							逾時未付
						</c:if>
        			</td>
        		</tr>
        	</c:forEach>
        </tbody>
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