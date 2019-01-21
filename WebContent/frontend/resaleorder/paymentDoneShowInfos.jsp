<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@ page import="com.seating_area.model.*"%> --%>
<%@ page import="com.resaleorder.model.*"%>
<%@ page import="com.ticket.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
String member_no = (String)request.getAttribute("member_no");
pageContext.setAttribute("member_no",member_no);
String resale_ordno = (String)request.getAttribute("resale_ordno");
pageContext.setAttribute("resale_ordno",resale_ordno);
ResaleOrderVO revo = (ResaleOrderVO)request.getAttribute("revo");
pageContext.setAttribute("revo",revo);
%>

<!-- ======================================== DAI:::begin ================================================== -->
<%-- <jsp:useBean id="seatingAreaService" scope="page" class="com.seating_area.model.SeatingAreaService" />
<jsp:useBean id="Event_H5_Service" scope="page" class="com.event.model.Event_H5_Service" /> --%>
<jsp:useBean id="resaleorderService" scope="page" class="com.resaleorder.model.ResaleOrderService" />
<jsp:useBean id="ticketService" scope="page" class="com.ticket.model.TicketService" />
<jsp:useBean id="memberService" scope="page" class="com.member.model.MemberService" />
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

	<%-- 
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
	--%>

	<%-- 
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
	--%>


	<!-- ------------------------------ memberInfo ::: start ------------------------------ -->
	<div class="container" style="margin-bottom:15px;">
		<table class="table table-hover table-bordered">
			<tr class="info">
				<th colspan="2">會員資料</th>
			</tr>
			<tr>
				<th style="width:20%;">會員姓名</th>
				<td>${memberService.getOneMember(member_no).memberFullname}</td>
			</tr>
			<tr>
				<th>電子信箱</th>
				<td>${memberService.getOneMember(member_no).email}</td>
			</tr>
			<tr>
				<th>電話號碼</th>
				<td>${memberService.getOneMember(member_no).phone}</td>
			</tr>
		</table>
	</div>
	<!-- ------------------------------ memberInfo ::: end ------------------------------ -->



	<!-- It's better to use 'toVO.ticket_order_no' than 'param.ticarea_no' -->
	<!-- agree and thanks sincerely -->
	<!-- ------------------------------ comfirmTickets ::: start ------------------------------ -->
	<div class="container" style="margin-bottom:15px;">
		<table class="table table-hover table-bordered" id="comfirmTicketsForm">
			<thead>
				<tr class="info">
					<th>轉讓訂單編號</th>
					<th>票券編號</th>
					<th>票種名稱</th>
					<th>買家姓名</th>
					<th>賣家姓名</th>
					
					<th>轉讓價格</th>
					<th>付款方式</th>
					<th>轉讓訂單狀態</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td style="width:20%;">${resale_ordno}</td>
					<td>${resaleorderService.getOneResaleOrd(resale_ordno).ticketVO.ticket_no}</td>
					<%-- <td>${SeatingArea_H5_Service.getOneSeatingArea_H5(ticketService.getOneTicket(ticketVO.ticket_no).seatingarea_h5VO.ticarea_no).ticarea_name}</td> --%>
					<td><font color="red">${ticketService.getOneTicket(resaleorderService.getOneResaleOrd(resale_ordno).ticketVO.ticket_no).seatingarea_h5VO.ticarea_name}</font></td>
					<td>${memberService.getOneMember(member_no).memberFullname}</td>
					<td>${memberService.getOneMember(resaleorderService.getOneResaleOrd(resale_ordno).member_seller_no).memberFullname}</td>

					<td>
						<fmt:formatNumber type="number" value="${resaleorderService.getOneResaleOrd(resale_ordno).resale_ordprice}" /> 元
					</td>
					<td>
						<c:if test="${resaleorderService.getOneResaleOrd(resale_ordno).payment_method == 'NOTYET'}">
							尚未選擇
						</c:if>
						<c:if test="${resaleorderService.getOneResaleOrd(resale_ordno).payment_method == 'CREDITCARD'}">
							信用卡
						</c:if>
						<c:if test="${resaleorderService.getOneResaleOrd(resale_ordno).payment_method == 'EWALLET'}">
							電子錢包
						</c:if>
					</td>
					
					<td>
						<c:if test="${resaleorderService.getOneResaleOrd(resale_ordno).resale_ordstatus == 'WAITTOPAY1'}">
							尚未付款
						</c:if>
						<c:if test="${resaleorderService.getOneResaleOrd(resale_ordno).resale_ordstatus == 'COMPLETE2'}">
							完成付款
						</c:if>
						<c:if test="${resaleorderService.getOneResaleOrd(resale_ordno).resale_ordstatus == 'CANCEL3'}">
							已取消
						</c:if>
						<c:if test="${resaleorderService.getOneResaleOrd(resale_ordno).resale_ordstatus == 'OUTDATE4'}">
							逾時未付
						</c:if>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<!-- ------------------------------ comfirmTickets ::: end ------------------------------ -->



	<!-- ------------------------------ showTicketsDetails ::: begin ------------------------------ -->
	<!-- temperarily block it because this might block information below. -->
	<%-- <div class="container" style="margin-bottom:15px;">
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
	</div> --%>
	<!-- ------------------------------ showTicketsDetails ::: end ------------------------------ -->
	
	
	<!-- ------------------------------ toListTicketsByMember ::: begin ------------------------------ -->
	<div class="container" style="margin-bottom:30px;">
		<form method="post" action="<%=request.getContextPath()%>/frontend/resaleorder/resaleorder.do">
			<input type="hidden" name="action" value="member_select_resaleorders">
			<input type="hidden" name="member_no" value="${member_no}">
			<input type="hidden" name="resale_ordno" value="${resale_ordno}">
			<input type="hidden" name="ticket_no" value="${resaleorderService.getOneResaleOrd(resale_ordno).ticketVO.ticket_no}">
			  
			<input type="submit" value="前往會員的轉讓訂單查詢" class="btn btn-primary" style="float:right;">
		</form>
	</div>
	<!-- ------------------------------ toListTicketsByMember ::: end ------------------------------ -->

	
	
	<!-- Basic -->
    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<jsp:include page="/frontend/footer_front-end.jsp" flush="true" />
<!-- ---------------------------------------------------DAI:::end--------------------------------------------------- -->




</body>
</html>