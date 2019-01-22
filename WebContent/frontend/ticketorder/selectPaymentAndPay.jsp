<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ticketorder.model.*"%>
<%@ page import="com.seating_area.model.*"%>
<%@ page import="com.event.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
Integer sessionLiveTime = session.getMaxInactiveInterval();

TicketOrderVO toVO = (TicketOrderVO)request.getAttribute("toVO");
pageContext.setAttribute("toVO",toVO);

String eve_no = (String)request.getAttribute("eve_no");
String member_no = (String)request.getAttribute("member_no");
pageContext.setAttribute("eve_no",eve_no);
pageContext.setAttribute("member_no",member_no);
%>

<!-- ======================================== DAI:::begin ================================================== -->
<%-- <jsp:useBean id="eventService" scope="page" class="com.event.model.EventService" /> --%>
<jsp:useBean id="seatingAreaService" scope="page" class="com.seating_area.model.SeatingAreaService" />
<jsp:useBean id="memberService" scope="page" class="com.member.model.MemberService" />
<jsp:useBean id="Event_H5_Service" scope="page" class="com.event.model.Event_H5_Service" />
<!-- ======================================== DAI:::end ================================================== -->




<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>



<!-- <title>模擬訂票訂單資料附帶票券的新增 - selectPayment.jsp</title> -->

<!-- ======================================== DAI:::begin ================================================== -->
<title>Step2 : 選擇付款</title>
<!-- basic -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<!-- progress css -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/frontend/ticketorder/css/progress.css">
<!-- ======================================== DAI:::end ================================================== -->



<style>
  table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

<!-- ======================================== DAI:::begin ================================================== -->
<style>
body {
	font-family:微軟正黑體!important;
}	
.bs-wizard{
	margin-top: 10px;
}
#comfirmTicketsForm th{
	text-align: center;
	vertical-align: middle;
}
#comfirmTicketsForm td{
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
            <!-- ========== Step2: active ========== -->
            <div class="col-xs-4 bs-wizard-step active">
                <div class="text-center bs-wizard-stepnum">Step 2</div>
                <div class="progress">
                    <div class="progress-bar"></div>
                </div>
                <a href="#" class="bs-wizard-dot"></a>
                <div class="bs-wizard-info text-center">選擇付款</div>
            </div>
            <!-- ========== Step3: disabled ========== -->
            <div class="col-xs-4 bs-wizard-step disabled">
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
	--%>


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
					<th><font color="red">繳費期限時間</font></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>${toVO.ticket_order_no}</td>
					<td>${seatingAreaService.getOneSeatingArea(toVO.seatingarea_h5VO.ticarea_no).ticarea_name}</td>
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

					<td>
						<% 
							java.sql.Timestamp ticket_order_payment_deadline = null;
							Long ticketOrderCreatedTimeInLongInt = null;
							Long deadLineTime = null;
							String strError = "";
							try {
								ticketOrderCreatedTimeInLongInt = toVO.getTicket_order_time().getTime();
								deadLineTime = ticketOrderCreatedTimeInLongInt+(sessionLiveTime+60)*1000;
								ticket_order_payment_deadline = new java.sql.Timestamp(deadLineTime);
							 } catch (Exception e) {
								 System.out.println("ticket_order_payment_deadline error");
								 strError = "ticket_order_payment_deadline_error";
								 ticket_order_payment_deadline = new java.sql.Timestamp(System.currentTimeMillis());
							 }
						%>
						<c:if test="${(toVO.ticket_order_status == 'WAITTOPAY1')}">
							<fmt:formatDate value="<%=ticket_order_payment_deadline%>" pattern="yyyy-MM-dd HH:mm"/>
						</c:if>
						<c:if test="${(toVO.ticket_order_status == 'CANCEL3')}">
							無
						</c:if>
						<c:if test="${(toVO.ticket_order_status == 'OUTDATE4')}">
							無
						</c:if>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<!-- ------------------------------ comfirmTickets ::: end ------------------------------ -->


	<!-- ------------------------------ payForTickets ::: start ------------------------------ -->
	<div class="container" style="margin-bottom:30px;">
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/frontend/ticketorder/ticketorder.do" name="form1" id="payForm">
			<table class="table table-hover table-bordered">
				<tr class="info">
					<th>選擇付款方式</th>
				</tr>
				<tr>
					<td>
						<label class="radio-inline">
							<input type="radio" name="payment" id="creditCardRadio" checked value="creditCard">信用卡
						</label>
					    <label class="radio-inline">
							<input type="radio" name="payment" id="ewalletRadio" value="ewallet">電子錢包
					    </label>
					    
					    <div id="creditCardArea" style="margin-top:15px;">
							<div class="form-group">
								<label for="creditCardNumber">信用卡卡號：</label>
								<input type="TEXT" name="creditCardNumber" id="creditCardNumber" size="20" value="" maxlength="16"/>
							</div>
							<div class="form-group">
								<label>卡片到期日：</label>
								西元 <input type="TEXT" name="creditCardMonth" id="creditCardMonth" size="4" value="" maxlength="4"/>年
								<input type="TEXT" name="creditCardYear" id="creditCardYear" size="4" value="" maxlength="2"/>月
							</div>
							<div class="form-group">
								<label for="creditCardVerificationNumber">卡片背面後三碼 : </label>
								<input type="TEXT" name="creditCardVerificationNumber" id="creditCardVerificationNumber" size="4" value="" maxlength="3"/>
							</div>
						</div>
						
						<div id="ewalletArea" style="margin-top:15px;display:none;">
							<label>電子錢包餘額：</label>
							<fmt:formatNumber type="number" value="${member.ewalletBalance}"/> 元
							<input type="hidden" id="hidden_ewalletBalance" size="500" value="${member.ewalletBalance}"/>
							<input type="hidden" id="hidden_totalprice" size="500" value="${toVO.total_price}"/>
							<p class="text-danger" id="notEnoughtMoney" style="display:none;">餘額不足，請至會員專區儲值。</p>
						</div>
					</td>
				</tr>
			</table>
			<input type="hidden" name="action" value="userPaying">
			<input type="hidden" name="ticket_order_no"  value="${toVO.ticket_order_no}">
			<input type="hidden" name="eve_no"  value="${eve_no}">
			<input type="button" value="進行付款" class="btn btn-primary" id="wannaPay">
		</FORM>
	</div>
	<!-- ------------------------------ payForTickets ::: end ------------------------------ -->



	<!-- Basic -->
    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    
	<jsp:include page="/frontend/footer_front-end.jsp" flush="true" />
	<script type="text/javascript">
		$(document).ready(function(){
			$("#creditCardRadio").click(function(){
				console.log("creditCardRadio");
				$("#ewalletArea").hide();
				$("#creditCardArea").show();
				$("#wannaPay").prop("disabled", false);
			});
			
			$("#ewalletRadio").click(function(){
				console.log("ewalletRadio");
				
				var ewalletBalance = parseInt($("#hidden_ewalletBalance").val());
				var totalprice = parseInt($("#hidden_totalprice").val());
				
				if(ewalletBalance < totalprice){
					$("#notEnoughtMoney").show();
					$("#wannaPay").prop("disabled", true);
				}
				
				$("#creditCardArea").hide();
				$("#ewalletArea").show();
			});
			
			$("#wannaPay").click(function(){
				var payment = $("input[name=payment]:checked").val();
				if(payment == "creditCard"){
					console.log("creditCard");
					
					var creditCardNumber = $("#creditCardNumber").val();
					var creditCardMonth = $("#creditCardMonth").val();
					var creditCardYear = $("#creditCardYear").val();
					var creditCardVerificationNumber = $("#creditCardVerificationNumber").val();
					
					if(creditCardNumber.trim().length == 0 || creditCardMonth.trim().length == 0 || 
							creditCardYear.trim().length == 0 || creditCardVerificationNumber.trim().length == 0){
						window.alert("請輸入信用卡資料");
						return;
					}
					
					$("#payForm").submit();
				}
			});
		});
	</script>

<!-- ======================================== DAI:::end ================================================== -->


</body>
</html>