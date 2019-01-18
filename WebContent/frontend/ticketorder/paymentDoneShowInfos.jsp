<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ticket.model.*"%>
<%@ page import="com.ticketorder.model.*"%>
<%@ page import="com.event.model.*"%>

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

TicketOrderVO toVO = (TicketOrderVO)request.getAttribute("updated_tovo");
List<ShowTicketVO> stvos = (List<ShowTicketVO>)request.getAttribute("listShow");

pageContext.setAttribute("toVO",toVO);
pageContext.setAttribute("stvos", stvos);
%>

<!-- ======================================== DAI:::begin ================================================== -->
<jsp:useBean id="eventService" scope="page" class="com.event.model.EventService" />
<jsp:useBean id="seatingAreaService" scope="page" class="com.seating_area.model.SeatingAreaService" />
<jsp:useBean id="memberService" scope="page" class="com.member.model.MemberService" />
<jsp:useBean id="Event_H5_Service" scope="page" class="com.event.model.Event_H5_Service" />
<!-- ======================================== DAI:::end ================================================== -->


<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>

<!-- <title>paymentDoneShowInfos.jsp</title> -->
<!-- ======================================== DAI:::begin ================================================== -->
<title>Step3 : 完成訂購</title>
<!-- basic -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<!-- progress css -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/frontend/ticketorder/css/progress.css">
<!-- ======================================== DAI:::end ================================================== -->


<style>
/*   table { */
/* 	width: 450px; */
/* 	background-color: white; */
/* 	margin-top: 1px; */
/* 	margin-bottom: 1px; */
/*   } */
/*   table, th, td { */
/*     border: 0px solid #CCCCFF; */
/*   } */
/*   th, td { */
/*     padding: 1px; */
/*   } */
</style>
<!-- ======================================== DAI:::begin ================================================== -->
<style>
body {
	font-family:微軟正黑體!important;
}	
.bs-wizard{
	margin-top: 10px;
}
#allTicketsForm th{
	text-align: center;
	vertical-align: middle;
}
#allTicketsForm td{
	text-align: center;
	vertical-align: middle;
}
</style>
<!-- ======================================== DAI:::end ================================================== -->



</head>
<body bgcolor='white'>



<!-- ======================================== DAI:::begin ================================================== -->

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

	<!-- ------------------------------ progress bar ::: start ------------------------------ -->
    <div class="container">
        <div class="row bs-wizard" style="border-bottom:0;">
            <!-- ========== Step1: complete ========== -->
            <div class="col-xs-4 bs-wizard-step complete">
                <div class="text-center bs-wizard-stepnum">Step 1</div>
                <div class="progress">
                    <div class="progress-bar"></div>
                </div>
                <a href="#" class="bs-wizard-dot"></a>
                <div class="bs-wizard-info text-center">選擇張數</div>
            </div>
            <!-- ========== Step2: complete ========== -->
            <div class="col-xs-4 bs-wizard-step complete">
                <div class="text-center bs-wizard-stepnum">Step 2</div>
                <div class="progress">
                    <div class="progress-bar"></div>
                </div>
                <a href="#" class="bs-wizard-dot"></a>
                <div class="bs-wizard-info text-center">選擇付款</div>
            </div>
            <!-- ========== Step3: active ========== -->
            <div class="col-xs-4 bs-wizard-step active">
                <div class="text-center bs-wizard-stepnum">Step 3</div>
                <div class="progress">
                    <div class="progress-bar"></div>
                </div>
                <a href="#" class="bs-wizard-dot"></a>
                <div class="bs-wizard-info text-center">完成訂購</div>
            </div>
        </div>
    </div>
	<!-- ------------------------------ progress bar ::: end ------------------------------ -->



	<!-- when returning, no eh5vo attribute -->
	<!-- ------------------------------ whichEventTitle&Event ::: start ------------------------------ -->	
	<div class="container" style="margin-bottom:30px;margin-top:15px;">
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-3">
				<img src="<%= request.getContextPath()%>/event_title/EventTitleGifReader?scaleSize=850&evetit_no=${Event_H5_Service.getOneEvent_H5(eve_no).eventtitle_h5VO.evetit_no}" style="width:100%;">
			</div>
			<div class="col-xs-12 col-sm-12 col-md-9">
				<h3 class="text-danger" style="margin-top:0px;">${Event_H5_Service.getOneEvent_H5(eve_no).eventtitle_h5VO.evetit_name}</h3>
				<h3 style="margin-top:15px;">
					活動時間 : 
					<fmt:formatDate value="${Event_H5_Service.getOneEvent_H5(eve_no).eve_startdate}" pattern="yyyy-MM-dd HH:mm"/>
					 至 
					<fmt:formatDate value="${Event_H5_Service.getOneEvent_H5(eve_no).eve_enddate}" pattern="yyyy-MM-dd HH:mm"/>
				</h3>
				<h3 style="margin-top:15px;">
					活動地點 : 
					${Event_H5_Service.getOneEvent_H5(eve_no).venue_h5VO.venue_name}
				</h3>
			</div>
		</div>
	</div>
	<!-- ------------------------------ whichEventTitle&Event ::: end ------------------------------ -->



	<!-- ------------------------------ memberInfo ::: start ------------------------------ -->
	<div class="container" style="margin-bottom:15px;">
		<table class="table table-hover table-bordered">
			<tr class="info">
				<th colspan="2">會員資料</th>
			</tr>
			<tr>
				<th style="width:20%;">會員姓名</th>
				<td>${memberService.getOneMember(toVO.member_no).memberFullname}</td>
			</tr>
			<tr>
				<th>電子信箱</th>
				<td>${memberService.getOneMember(toVO.member_no).email}</td>
			</tr>
			<tr>
				<th>電話號碼</th>
				<td>${memberService.getOneMember(toVO.member_no).phone}</td>
			</tr>
		</table>
	</div>
	<!-- ------------------------------ memberInfo ::: end ------------------------------ -->



	<!-- It's better to use 'toVO.ticket_order_no' than 'param.ticarea_no' -->
	<!-- ------------------------------ comfirmTickets ::: start ------------------------------ -->
	<div class="container" style="margin-bottom:15px;">
		<table class="table table-hover table-bordered" id="comfirmTicketsForm">
			<thead>
				<tr class="info">
					<th>訂票訂單編號</th>
					<th>座位區名稱</th>
					<th>單價</th>
					<th>總張數</th>
					<th>總價</th>
					<th>訂票訂單成立時間</th>
					<th>付款方式</th>
					<th>訂票訂單狀態</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td style="width:20%;">${toVO.ticket_order_no}</td>
					<td>${seatingAreaService.getOneSeatingArea(param.ticarea_no).ticarea_name}</td>
					<td>
						<fmt:formatNumber type="number" value="${toVO.total_price / toVO.total_amount}" /> 元
					</td>
					<td>${toVO.total_amount} 張</td>
					<td>
						<fmt:formatNumber type="number" value="${toVO.total_price}" /> 元
					</td>
					<td>
						<fmt:formatDate value="${toVO.ticket_order_time}" pattern="yyyy-MM-dd HH:mm"/>
					</td>
					<td>
						${(toVO.payment_method == 'NOTYET') ? '尚未選擇' : ''}
						${(toVO.payment_method == 'CREDITCARD') ? '信用卡' : ''}
						${(toVO.payment_method == 'EWALLET') ? '電子錢包' : ''}
					</td>
					<td>
						${(toVO.ticket_order_status == 'WAITTOPAY1') ? '尚未付款' : ''}
						${(toVO.ticket_order_status == 'COMPLETE2') ? '完成付款' : ''}
						${(toVO.ticket_order_status == 'CANCEL3') ? '已取消' : ''}
						${(toVO.ticket_order_status == 'OUTDATE4') ? '逾時未付' : ''}
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<!-- ------------------------------ comfirmTickets ::: end ------------------------------ -->



	<!-- ------------------------------ showTicketsDetails ::: begin ------------------------------ -->
	<div class="container" style="margin-bottom:15px;">
		<table class="table table-hover table-bordered" id="comfirmTicketsForm">
			<tr class="info">
				<th>票券編號</th>
				<th>票種名稱</th>
				<th>票種價格</th>
				<th>座位區名稱</th>
				<th>票券狀態</th>
			</tr>
			<c:forEach var="showticketVO" items="${stvos}">
				<tr>
					<td style="width:20%;">${showticketVO.ticket_no}</td>
					<td>${showticketVO.tictype_name}</td>
					<td><fmt:formatNumber type="number" value="${toVO.total_price / toVO.total_amount}" /> 元</td>
					<td>${showticketVO.ticarea_name}</td>
					<td>
						${(showticketVO.ticket_status == 'ACTIVE1') ? '未使用' : ''}
						${(showticketVO.ticket_status == '') ? '' : ''}
						${(showticketVO.ticket_status == '') ? '' : ''}
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<!-- ------------------------------ showTicketsDetails ::: end ------------------------------ -->
	
	
	<!-- ------------------------------ toListTicketsByMember ::: begin ------------------------------ -->
	<div class="container" style="margin-bottom:30px;">
		<form method="post" action="<%=request.getContextPath()%>/frontend/ticketorder/ticketorder.do">
			<input type="hidden" name="action" value="member_select_ticketorders">
			<input type="hidden" name="member_no" value="${toVO.member_no}"> 
			<input type="submit" value="前往會員的訂單查詢" class="btn btn-primary" style="float:right;">
		</form>
	</div>
	<!-- ------------------------------ toListTicketsByMember ::: end ------------------------------ -->
	
	
	<!-- Basic -->
    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<jsp:include page="/frontend/footer_front-end.jsp" flush="true" />
<!-- ---------------------------------------------------DAI:::end--------------------------------------------------- -->





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

<!-- <table> -->
<!-- 	<tr> -->
<!-- 		<th>訂票訂單編號</th> -->
<!-- 		<th>會員編號</th> -->
		
<!-- 		<th>總價</th> -->
<!-- 		<th>總張數</th> -->
<!-- 		<th>訂票訂單成立時間</th> -->
<!-- 		<th>付款方式</th> -->
<!-- 		<th>訂票訂單狀態</th> -->
<!-- 		<!-- <th>進行付款</th> --> 
<!-- 	</tr> -->
<!-- 	<tr> -->
<%-- 		<td>${toVO.ticket_order_no}</td> --%>
<%-- 		<td>${toVO.member_no}</td> --%>
		
<%-- 		<td>${toVO.total_price}</td> --%>
<%-- 		<td>${toVO.total_amount}</td> --%>
<%-- 		<td><fmt:formatDate value="${toVO.ticket_order_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td> --%>
<%-- 		<td><font color="green">${toVO.payment_method}</font></td> --%>
<%-- 		<td><font color="green">${toVO.ticket_order_status}</font></td> --%>
		<%-- <td>
			<FORM METHOD="post" ACTION="ticketorder.do">
				<input type="submit" value="pay">
				<input type="hidden" name="ticket_order_no"  value="${toVO.ticket_order_no}">
				<input type="hidden" name="action"	value="userWantToPay"></FORM>
		</td> --%>
<!-- 	</tr> -->
<!-- </table> -->

<!-- <h3>列出該訂單的所有票券</h3> -->
<!-- <table> -->
<!-- 	<tr> -->
<!-- 		<th>票券編號</th> -->
<!-- 		<th>持票人會員編號</th> -->
<!-- 		<th>票券狀態</th> -->
<!-- 		<th>活動座位區名稱</th> -->
<!-- 		<th>活動座位區顏色代碼</th> -->
		
<!-- 		<th>票種名稱</th> -->
<!-- 		<th>票種價格</th> -->
<!-- 		<th>場次名稱</th> -->
<!-- 		<th>活動開始日期</th> -->
<!-- 		<th>活動結束日期</th> -->
<!-- 		<th>售票結束日期</th> -->
<!-- 		<th>活動主題名稱</th> -->
<!-- 		<th>場地名稱</th> -->
<!-- 		<th>場地地址</th> -->
		
<!-- 	</tr> -->
<%-- 	<c:forEach var="showticketVO" items="${stvos}"> --%>
<!-- 		<tr> -->
<%-- 			<td>${showticketVO.ticket_no}</td> --%>
<%-- 			<td>${showticketVO.member_no}</td> --%>
<%-- 			<td>${showticketVO.ticket_status}</td> --%>
<%-- 			<td>${showticketVO.ticarea_name}</td> --%>
<%-- 			<td>${showticketVO.ticarea_color}</td> --%>
			
<%-- 			<td>${showticketVO.tictype_name}</td> --%>
<%-- 			<td>${showticketVO.tictype_price}</td> --%>
<%-- 			<td>${showticketVO.eve_sessionname}</td> --%>
<%-- 			<td>${showticketVO.eve_startdate}</td> --%>
<%-- 			<td>${showticketVO.eve_enddate}</td> --%>
<%-- 			<td>${showticketVO.eve_offsaledate}</td> --%>
<%-- 			<td>${showticketVO.evetit_name}</td> --%>
<%-- 			<td>${showticketVO.venue_name}</td> --%>
<%-- 			<td>${showticketVO.address}</td> --%>
<!-- 		</tr> -->
<%-- 	</c:forEach> --%>
<!-- </table> -->



<%-- <h4><a href="<%=request.getContextPath()%>/frontend/ticketorder/select_page.jsp">前往會員的訂單查詢</a></h4> --%>
<%-- <form method="post" action="<%=request.getContextPath()%>/frontend/ticketorder/ticketorder.do"> --%>
<!-- 	<input type="hidden" name="action" value="member_select_ticketorders"> -->
<%-- 	<input type="hidden" name="member_no" value="${toVO.member_no}"> <!--Member not done yet, only been set as M000001 --> --%>
<!-- 	<input type="submit" value="前往會員的訂單查詢"> -->
<!-- </form> -->

</body>
</html>