<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.event_title.model.*"%>

<%
//temperalily disable for test
//String member_no = (String)request.getAttribute("member_no");
//pageContext.setAttribute("member_no",member_no);
%>

<!-- ======================================== DAI:::begin ================================================== -->
<%-- <jsp:useBean id="eventService" scope="page" class="com.event.model.EventService" /> 
<jsp:useBean id="memberService" scope="page" class="com.member.model.MemberService" /> 
<jsp:useBean id="seatingAreaService" scope="page" class="com.seating_area.model.SeatingAreaService" />
<jsp:useBean id="Event_H5_Service" scope="page" class="com.event.model.Event_H5_Service" />--%>
<!-- ======================================== DAI:::end ================================================== -->




<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>



<!-- <title>模擬訂票訂單資料附帶票券的新增 - selectPayment.jsp</title> -->

<!-- ======================================== DAI:::begin ================================================== -->
<title>backend_ticket_selling_select_page</title>
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

	<jsp:include page="/backend/navbar_back-end.jsp" flush="true" />
	
	<div class="container">
		<span class="text-danger">${eventTitleErrorMsgs.Exception}</span>
	</div>

	<%-- 
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
	--%>
	<div class="container">
		<h2>購票狀況查詢</h2>
	</div>
	<br>

	<!-- ======================================== DAI:::begin ================================================== -->
	<div class="container">
		<form method="post" action="<%=request.getContextPath()%>/frontend/ticket/ticket.do">
            <div class="form-group">
                <label for="evetit_name">活動主題名稱:</label>
                <input type="text" name="evetit_name" id="evetit_name" placeholder="請輸入活動主題名稱，來查詢出相關的活動售票狀況" class="form-control" value="">
            </div>
            
            <%-- 
            <div class="row">
                <div class="col-xs-12 col-sm-6">
                    <div class="form-group">
                        <label for="evetit_startdate">開始日期</label>
                        <input type="text" name="evetit_startdate" id="evetit_startdate" placeholder="請選擇開始日期" class="form-control" value="">
                    </div>
                </div>
                <div class="col-xs-12 col-sm-6">
                    <div class="form-group">
                        <label for="evetit_enddate">結束日期</label>
                        <input type="text" name="evetit_enddate" id="evetit_enddate" placeholder="請選擇結束日期" class="form-control" value="">
                    </div>
                </div>
            </div>
            --%>
            
            <div class="row">
                <div class="col-xs-12 col-sm-12 col-md-11 hidden-xs hidden-sm">                          
                	<jsp:useBean id="eventClassificationService" scope="page" class="com.event_classification.model.EventClassificationService" />
                	<c:forEach var="eventClassificationVO" items="${eventClassificationService.all}">
	       				<div class="checkbox-inline checkboxCusStyle">
	            			<label>
	                			<input type="checkbox" name="eveclass_no" value="${eventClassificationVO.eveclass_no}" id="${eventClassificationVO.eveclass_no}" checked>
	       						${eventClassificationVO.eveclass_name}
	            			</label>
	       				</div>
                	</c:forEach>
                </div>               
                <div class="col-xs-12 col-sm-12 col-md-1">
                    <div class="form-group text-right">
                    	<input type="hidden" name="action" value="listEventTitle_ByCompositeQuery_from_backend_ticket">
                    	<%-- <input type="hidden" name="member_no" value="${member_no}">  <!--in backend there's no member here, have to check in this action's target controller --> --%>
                        <button type="submit" class="btn btn-primary" id="search">查詢</button>
                    </div>
                </div>
            </div>
		</form>
    </div>
    <!-- ======================================== DAI:::end ================================================== -->

</body>
</html>