<%-- <%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> --%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<%-- <%@ page import="com.ticketorder.model.*"%> --%>
<%-- <%@ page import="com.seating_area.model.*"%> --%>
<%-- <%@ page import="com.event.model.*"%> --%>
<%-- <%@ page import="com.ticket_type.model.*"%> --%>
<%-- <%@ page import="java.util.*"%> --%>
<%-- <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> --%>
<%
// List<SeatingArea_H5_VO> slist = (List<SeatingArea_H5_VO>)request.getAttribute("slist");
// Event_H5_VO eh5vo = (Event_H5_VO)request.getAttribute("eh5vo");

// /*maybe use requestScope can solve it?*/
// pageContext.setAttribute("slist",slist);
// pageContext.setAttribute("eh5vo",eh5vo);

String member_no = (String)request.getAttribute("member_no");
pageContext.setAttribute("member_no",member_no);
String eve_no = (String)request.getAttribute("eve_no");
pageContext.setAttribute("eve_no",eve_no);
%>

<!-- ======================================== DAI:::begin ================================================== -->
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page import="java.util.*"%>
<%@ page import="com.event_title.model.*"%>

<%-- <jsp:useBean id="eventTitleService" scope="page" class="com.event_title.model.EventTitleService" /> --%>
<jsp:useBean id="eventService" scope="page" class="com.event.model.EventService" />
<jsp:useBean id="ticketTypeService" scope="page" class="com.ticket_type.model.TicketTypeService" />
<jsp:useBean id="Event_H5_Service" scope="page" class="com.event.model.Event_H5_Service" />
<!-- ======================================== DAI:::end ================================================== -->



<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>



<!-- <title>模擬訂票訂單資料附帶票券的新增 - eve_no_SelectedReady2Next.jsp</title> -->

<!-- ======================================== DAI:::begin ================================================== -->
<title>Step1 : 選擇張數</title>
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
#seatmap{
	width:100%;
}
body {
	font-family:微軟正黑體!important;
}	
.colorarea{
	height: 13px;
	width: 13px;
	display: inline-block;
	border-radius: 2px;
}
.ticketsLeft{
	cursor: pointer;
}
.howManyTicketsTable th{
	text-align:center;
}
.bs-wizard{
	margin-top: 10px;
}
h4{
	display: inline;
}
</style>
<!-- ======================================== DAI:::end ================================================== -->



</head>
<body>



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
    <div class="container" style="margin-bottom:30px;">
        <div class="row bs-wizard" style="border-bottom:0;">
            <!-- ========== Step1: active ========== -->
            <div class="col-xs-4 bs-wizard-step active">
                <div class="text-center bs-wizard-stepnum">Step 1</div>
                <div class="progress">
                    <div class="progress-bar"></div>
                </div>
                <a href="#" class="bs-wizard-dot"></a>
                <div class="bs-wizard-info text-center">選擇張數</div>
            </div>
            <!-- ========== Step2: disabled ========== -->
            <div class="col-xs-4 bs-wizard-step disabled">
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



	<!-- I couldn't use the hibernate for ticket type and seating area. -->
	<!-- ------------------------------ selectSeatingArea ::: start ------------------------------ -->
	<div class="container" style="margin-bottom:30px;">
		<div class="row">
			<div class="col-xs-12 col-sm-6">
				<div class="form-group eve_seatmap_area">
                 	<img src="<%= request.getContextPath()%>/event/EventGifReader?scaleSize=850&eve_no=${eve_no}" id="seatmap">
             	</div>
			</div>                	
			<div class="col-xs-12 col-sm-6">
				<div class="form-group"> 
				<h3 class="text-primary" style="margin-top:0px;">請選擇區域(點擊剩餘張數)</h3>
				<c:forEach var="ticketTypeVO" items="${eventService.getTicketTypesByEvent(eve_no)}">
				<div class="panel-group">
		            <div class="panel panel-info">
		                <div class="panel-heading" data-toggle="collapse" aria-expanded="false" href="#${ticketTypeVO.tictype_no}">
		                    <div class="panel-title">
		                    	<div class="colorarea" style="background-color:${ticketTypeVO.tictype_color};"></div>
								<h4>
									${ticketTypeVO.tictype_name}&nbsp;&nbsp;
									票價 : ${ticketTypeVO.tictype_price} 元 / 張
								</h4>
		                    </div>
		                </div>		                
		                <div id="${ticketTypeVO.tictype_no}" class="panel-collapse collapse in">
		                    <div class="panel-body">
		                    	<c:forEach var="SeatingAreaVO" items="${ticketTypeService.getSeatingAreasByTicketType(ticketTypeVO.tictype_no)}"> 
		                    		<div>
		                    			<div class="colorarea" style="background-color:${SeatingAreaVO.ticarea_color};"></div>			                           
		                           		<h4>${SeatingAreaVO.ticarea_name}&nbsp;&nbsp;</h4>
		                           		<c:if test="${(SeatingAreaVO.tictotalnumber - SeatingAreaVO.ticbookednumber) != 0 }">
		                           			<input type="hidden" class="hidden_tictype_price" value="${ticketTypeVO.tictype_price}">
		                           			<input type="hidden" class="hidden_ticarea_name"  value="${SeatingAreaVO.ticarea_name}">
		                           			<input type="hidden" class="hidden_tictype_no" value="${SeatingAreaVO.tictype_no}">
		                           			<input type="hidden" class="hidden_ticarea_no" value="${SeatingAreaVO.ticarea_no}">
		                           			<input type="hidden" class="hidden_ticketsLeft" value="${SeatingAreaVO.tictotalnumber - SeatingAreaVO.ticbookednumber}">
		                           			<input type="hidden" class="hidden_ticketsLimit" value="${Event_H5_Service.getOneEvent_H5(eve_no).ticlimit}">
											<h4 class="ticketsLeft text-danger">剩餘 : ${SeatingAreaVO.tictotalnumber - SeatingAreaVO.ticbookednumber} 張</h4>
										</c:if>
										<c:if test="${(SeatingAreaVO.tictotalnumber - SeatingAreaVO.ticbookednumber) == 0 }">
											<h4 class="text-muted">已售完</h4>
										</c:if>
		                            </div>
		                   		</c:forEach>
		                    </div>
		                </div>
		            </div>
		        </div>
				</c:forEach>
				</div>                    
             </div>
		</div> 
	</div>
	<!-- ------------------------------ selectSeatingArea ::: end ------------------------------ -->
	
	
	
	<!-- ------------------------------ howManyTickets ::: start ------------------------------ -->
	<div class="container" style="margin-bottom:30px;display:none;" id="howManyTicketsArea">
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/frontend/ticketorder/ticketorder.do">
			<table class="table table-hover table-bordered howManyTicketsTable">
				<thead>
					<tr class="info">
						<th>票區 / 票價(元)</th>
						<th>張數選擇</th>
						<th>動作</th>
					</tr>
				</thead>
				<tbody>
					<tr>						
						<th id="ticInfo" style="vertical-align:middle;">
						</th>
						<th>
							<div class="form-group" style="margin:0px;">
				                <select class="form-control" name="ticketsNum" id="ticketsNum">
				                </select>
			            	</div>
						</th>
						<th>
							<input class="btn btn-primary" type="submit" value="購買">
						</th>
					</tr>
				</tbody>
			</table>
			
			<input type="hidden" name="tictype_no" id="tictype_no" value="">
			<input type="hidden" name="ticarea_no" id="ticarea_no" value="">
			<input type="hidden" name="eve_no" value="${eve_no}"> <!-- from above line pageContext.set -->
			<input type="hidden" name="member_no" value="${member_no}"> <!-- from above line pageContext.set -->
			<input type="hidden" name="action"	value="ticketNumSelected_buyTickets">
			
			<%-- 下列傳過去的值都是公開資料，因為用附在parameter上節省下一頁或下下頁要用到顯示的資料，節省資料庫連線 
			<input type="hidden" name="evetit_nameForShow" value="${eh5vo.eventtitle_h5VO.evetit_name}">
			<input type="hidden" name="eve_sessionnameForShow" value="${eh5vo.eve_sessionname}">
			<input type="hidden" name="eve_startdateForShow" value="<fmt:formatDate value="${eh5vo.eve_startdate}" pattern="yyyy-MM-dd HH:mm"/>">
			<input type="hidden" name="eve_enddateForShow" value="<fmt:formatDate value="${eh5vo.eve_enddate}" pattern="yyyy-MM-dd HH:mm"/>">
			<input type="hidden" name="venue_nameForShow" value="${eh5vo.venue_h5VO.venue_name}">
			<input type="hidden" name="addressForShow" value="${eh5vo.venue_h5VO.address}">
			
			<input type="hidden" name="tictype_nameForShow" value="${SeatingArea_H5_VO.tickettype_h5VO.tictype_name}">
			 --%>
				
		</FORM>
	</div>
	<!-- ------------------------------ howManyTickets ::: end ------------------------------ -->



	<!-- Basic -->
    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    
	<jsp:include page="/frontend/footer_front-end.jsp" flush="true" />
	
	<script type="text/javascript">
	$(document).ready(function(){
		$(".ticketsLeft").click(function(){
			var tictype_price = $(this).siblings(".hidden_tictype_price").val();
			var ticarea_name = $(this).siblings(".hidden_ticarea_name").val();
			var tictype_no = $(this).siblings(".hidden_tictype_no").val();
			var ticarea_no = $(this).siblings(".hidden_ticarea_no").val();
			var ticketsLeft = parseInt($(this).siblings(".hidden_ticketsLeft").val());            
			var ticketsLimit = parseInt($(this).siblings(".hidden_ticketsLimit").val());   
			$("#tictype_no").val(tictype_no);
			$("#ticarea_no").val(ticarea_no);
			$("#ticInfo").html(ticarea_name + " / " + tictype_price + " 元 ");
			console.log("ticketsLeft : " + ticketsLeft);
			console.log("ticketsLimit : " + ticketsLimit);
			if(ticketsLeft >= ticketsLimit){
				console.log("ticketsLeft >= ticketsLimit");
				$("#ticketsNum").empty();
				for(var i = 1; i < 5 ; i++){
					$("#ticketsNum").append("<option value='" + i + "'>" + i + "</option>");
				}
			} else {
				console.log("ticketsLeft < ticketsLimit");
				$("#ticketsNum").empty();
				for(var i = 1; i <= ticketsLeft ; i++){
					$("#ticketsNum").append("<option value='" + i + "'>" + i + "</option>");
				}
			}
			$("#howManyTicketsArea").css("display", "block");
			$('html,body').animate({ scrollTop: $(document).height() }, 1500);
		});
	});
	</script>
<!-- ======================================== DAI:::end ================================================== -->





<!-- <h3>購票流程開始:</h3> -->

<%-- 錯誤表列 --%>
<%-- <c:if test="${not empty errorMsgs}"> --%>
<!-- 	<font style="color:red">請修正以下錯誤:</font> -->
<!-- 	<ul> -->
<%-- 		<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 			<li style="color:red">${message}</li> --%>
<%-- 		</c:forEach> --%>
<!-- 	</ul> -->
<%-- </c:if> --%>
<!-- <table> -->
<!-- 	<tr> -->
<!-- 		<th>活動主題名稱</th> -->
<!-- 		<th>活動主題主海報</th> -->
<!-- 		<th>場次名稱</th> -->
<!-- 		<th>座位配置圖</th> -->
<!-- 		<th>活動開始日期</th> -->
<!-- 		<th>活動結束日期</th> -->
<!-- 		<th>售票結束日期</th> -->
<!-- 		<th>單一會員購買張數限制</th> -->
<!-- 		<th>可全額退款到期日的日期</th> -->
<!-- 		<th>場地名稱</th> -->
<!-- 		<th>場地地址</th> -->
<!-- 	</tr> -->
<!-- 	<tr> -->
<%-- 		<td>${eh5vo.eventtitle_h5VO.evetit_name}</td> --%>
<!-- 		<td></td> -->
<%-- 		<td>${eh5vo.eve_sessionname}</td> --%>
<!-- 		<td></td> -->
<%-- 		<td><fmt:formatDate value="${eh5vo.eve_startdate}" pattern="yyyy-MM-dd HH:mm:ss"/></td> --%>
<%-- 		<td><fmt:formatDate value="${eh5vo.eve_enddate}" pattern="yyyy-MM-dd HH:mm:ss"/></td> --%>
<%-- 		<td><fmt:formatDate value="${eh5vo.eve_offsaledate}" pattern="yyyy-MM-dd HH:mm:ss"/></td> --%>
<%-- 		<td>${eh5vo.ticlimit}</td> --%>
<%-- 		<td><fmt:formatDate value="${eh5vo.fullrefundenddate}" pattern="yyyy-MM-dd HH:mm:ss"/></td> --%>
<%-- 		<td>${eh5vo.venue_h5VO.venue_name}</td> --%>
<%-- 		<td>${eh5vo.venue_h5VO.address}</td> --%>
<!-- 	</tr> -->
	
<!-- </table> -->

<!-- <table> -->
<!-- 	<tr> -->
<!-- 		<th>該票種名稱</th> -->
<!-- 		<th>該票種代表顏色</th> -->
<!-- 		<th>活動座位區名稱</th> -->
<!-- 		<th>總容許銷售張數</th> -->
<!-- 		<th>已訂的票券張數</th> -->
<!-- 		<th>單張票價</th> -->
<!-- 		<th>請選要買的張數</th> -->
<!-- 		<th>送出購票請求</th> -->
<!-- 	</tr> -->
<%-- 	<c:forEach var="SeatingArea_H5_VO" items="${slist}"> --%>
<%-- 		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/frontend/ticketorder/ticketorder.do"> --%>
<!-- 			<table> -->
<!-- 				<tr> -->
<%-- 					<td>${SeatingArea_H5_VO.tickettype_h5VO.tictype_name}</td> --%>
<%-- 					<td>${SeatingArea_H5_VO.tickettype_h5VO.tictype_color}</td> --%>
<%-- 					<td>${SeatingArea_H5_VO.ticarea_name}</td> --%>
<%-- 					<td>${SeatingArea_H5_VO.tictotalnumber}</td> --%>
<%-- 					<td><font color="red">${SeatingArea_H5_VO.ticbookednumber}</font></td> --%>
<%-- 					<td>${SeatingArea_H5_VO.tickettype_h5VO.tictype_price}</td> --%>
<!-- 					<td> -->
<%-- 						<b>${eh5vo.ticlimit}</b> --%>
<!-- 						<select name = "ticketsNum"> -->
<!-- 						<option value="1" selected="selected">1</option> -->
<!-- 						<option value="2">2</option> -->
<!-- 						<option value="3">3</option> -->
<!-- 						</select> -->
<!-- 					</td> -->
<!-- 					<td> -->
<!-- 						<input type="submit" value="購買"> -->
<%-- 						<input type="hidden" name="tictype_no"  value="${SeatingArea_H5_VO.tickettype_h5VO.tictype_no}"> --%>
<%-- 						<input type="hidden" name="ticarea_no"  value="${SeatingArea_H5_VO.ticarea_no}"> --%>
<%-- 						<input type="hidden" name="eve_no" value="${eh5vo.eve_no}"> --%>
<!-- 						<input type="hidden" name="action"	value="ticketNumSelected_buyTickets"> -->
						
<!-- 						下列傳過去的值都是公開資料，因為用附在parameter上節省下一頁或下下頁要用到顯示的資料，節省資料庫連線 -->
<%-- 						<input type="hidden" name="evetit_nameForShow" value="${eh5vo.eventtitle_h5VO.evetit_name}"> --%>
<%-- 						<input type="hidden" name="eve_sessionnameForShow" value="${eh5vo.eve_sessionname}"> --%>
<%-- 						<input type="hidden" name="eve_startdateForShow" value="<fmt:formatDate value="${eh5vo.eve_startdate}" pattern="yyyy-MM-dd HH:mm:ss"/>"> --%>
<%-- 						<input type="hidden" name="eve_enddateForShow" value="<fmt:formatDate value="${eh5vo.eve_enddate}" pattern="yyyy-MM-dd HH:mm:ss"/>"> --%>
<%-- 						<input type="hidden" name="venue_nameForShow" value="${eh5vo.venue_h5VO.venue_name}"> --%>
<%-- 						<input type="hidden" name="addressForShow" value="${eh5vo.venue_h5VO.address}"> --%>
						
<%-- 						<input type="hidden" name="tictype_nameForShow" value="${SeatingArea_H5_VO.tickettype_h5VO.tictype_name}"> --%>
						
<!-- 					</td> -->
<!-- 				</tr> -->
<!-- 			</table> -->
<!-- 		</FORM> -->
<%-- 	</c:forEach> --%>
<!-- </table> -->



</body>
</html>