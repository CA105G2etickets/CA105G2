<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%-- <%@ page import="com.member.model.*"%> --%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<%
//String member_no = (String)request.getAttribute("member_no");
//if(member_no == null || (member_no.trim()).length() == 0){
	//MemberVO member = (MemberVO) session.getAttribute("member");
	//member_no = member.getMemberNo();
//}
//pageContext.setAttribute("member_no",member_no);

List<String> listEveNo = (List<String>)request.getAttribute("listEveNo");
pageContext.setAttribute("listEveNo",listEveNo);
%>

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>listAllEvevoByCompositeQueryWithListEveNo.jsp</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
</head>
<%-- <jsp:useBean id="ticketService" scope="page" class="com.ticket.model.TicketService" /> --%>
<jsp:useBean id="SeatingArea_H5_Service" scope="page" class="com.seating_area.model.SeatingArea_H5_Service" />
<jsp:useBean id="Event_H5_Service" scope="page" class="com.event.model.Event_H5_Service" />

<%-- <jsp:useBean id="memberService" scope="page" class="com.member.model.MemberService" /> --%>

<body>
/>
<jsp:include page="/backend/navbar_back-end.jsp" flush="true" />
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
                <!-- <th>活動編號</th> -->
                <!-- <th>送出此查詢者的會員姓名</th> -->
                <th>活動主題熱度</th>
                <th>活動主題名稱</th>
                <th>活動開始時間</th>
                <th>活動結束時間</th>
                <!-- <th>售票開始時間</th> -->
                <th>活動地點</th>
                <th>地址</th>
                <th>單一會員購票上限張數</th>
                <!-- <th>活動狀態</th> -->
                <th>查看座位區販售情況</th>
            </tr>
        </thead>
        <tbody>
        	<c:forEach var="string_eve_no" items="${listEveNo}">
        		<tr>
        			<%-- <td>${string_eve_no}</td> --%>
        			
        			<td>
        				${Event_H5_Service.getOneEvent_H5(string_eve_no).eventtitle_h5VO.promotionranking}
        			</td>
        			
        			<%-- <td>${memberService.getOneMember(member_no).email}</td> --%>
        			<%-- <td>${memberService.getOneMember(member_no).memberFullname}</td> --%>
        			
        			<%-- <td><fmt:formatDate value="${TicketVO.ticket_create_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td> --%>
        			
        			<%-- <td> This cause illegalStateException so use next page to show seatingAreaVO.
        				<c:forEach var = "seatingarea_h5_vo" items="${SeatingArea_H5_Service.get_SeatingArea_H5_VOs_ByEveNo(string_eve_no)}">
        					<font>everySeatingAreaH5VoPirntALineText.</font>
        				</c:forEach>
        			</td> --%>
        			
        			<%-- <td>原價:${SeatingArea_H5_Service.getOneSeatingArea_H5(TicketVO.seatingarea_h5VO.ticarea_no).tickettype_h5VO.tictype_price}, ${SeatingArea_H5_Service.getOneSeatingArea_H5(TicketVO.seatingarea_h5VO.ticarea_no).tickettype_h5VO.tictype_name}</td> --%>
        			
        			<td>${Event_H5_Service.getOneEvent_H5(string_eve_no).eventtitle_h5VO.evetit_name}</td>
        			<td><fmt:formatDate value="${Event_H5_Service.getOneEvent_H5(string_eve_no).eve_startdate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
        			<td><fmt:formatDate value="${Event_H5_Service.getOneEvent_H5(string_eve_no).eve_enddate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
        			<%-- <td><fmt:formatDate value="${Event_H5_Service.getOneEvent_H5(string_eve_no).eve_onsaledate}" pattern="yyyy-MM-dd HH:mm:ss"/></td> --%>
        			
        			<td>${Event_H5_Service.getOneEvent_H5(string_eve_no).venue_h5VO.venue_name}</td>
        			<td>${Event_H5_Service.getOneEvent_H5(string_eve_no).venue_h5VO.address}</td>
        			
        			<td>${Event_H5_Service.getOneEvent_H5(string_eve_no).ticlimit}</td>
        			
        			<%-- <td>
        				<c:if test="(${Event_H5_Service.getOneEvent_H5(string_eve_no).eve_status} == 'normal')">
        					正常販售
        				</c:if>
        				<c:if test="(${Event_H5_Service.getOneEvent_H5(string_eve_no).eve_status} == 'cancel')">
        					取消販售
        				</c:if>
        			</td> 
        			
        			<td>${Event_H5_Service.getOneEvent_H5(string_eve_no).eve_status}</td> --%>
        			
        			<td>
        				<form method="post" action="<%=request.getContextPath()%>/frontend/ticket/ticket.do">
							<input type="hidden" name="action" value="member_select_One_Evevo_From_listAllEvevoByCompositeQueryWithListEveNo">
							<%-- <input type="hidden" name="member_no" value="${member_no}"> --%>
							<input type="hidden" name="eve_no" value="${string_eve_no}">
							<input type="submit" value="查詢" class="btn btn-primary" style="float:right;">
						</form>
        			</td>
        			
        		</tr>
        	</c:forEach>
        </tbody>
       <!--  <tfoot>
           <tr>
                <th>活動編號</th>
                <th>送出此查詢者的會員姓名</th>
                
                <th>活動主題名稱</th>
                <th>活動開始時間</th>
                <th>活動結束時間</th>
                <th>售票開始時間</th>
                <th>活動地點</th>
                <th>地址</th>
                <th>單一會員購票上限</th>
                <th>活動狀態</th>
                <th>查看座位區販售情況</th>
            </tr>
        </tfoot> -->
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