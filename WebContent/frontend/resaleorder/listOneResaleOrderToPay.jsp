<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<%
String member_no = (String)request.getAttribute("member_no");
if(member_no == null || (member_no.trim()).length() == 0){
	MemberVO member = (MemberVO) session.getAttribute("member");
	member_no = member.getMemberNo();
}
pageContext.setAttribute("member_no",member_no);

String resale_ordno = (String)request.getAttribute("resale_ordno");
pageContext.setAttribute("resale_ordno",resale_ordno);
%>

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>listOneResaleOrderToPay.jsp</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
</head>
<%-- <jsp:useBean id="ticketService" scope="page" class="com.ticket.model.TicketService" />
<jsp:useBean id="SeatingArea_H5_Service" scope="page" class="com.seating_area.model.SeatingArea_H5_Service" />
<jsp:useBean id="Event_H5_Service" scope="page" class="com.event.model.Event_H5_Service" />
 --%>
 <jsp:useBean id="memberService" scope="page" class="com.member.model.MemberService" />
<jsp:useBean id="resaleorderService" scope="page" class="com.resaleorder.model.ResaleOrderService" />
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
                <th>買家姓名</th>
                <th>賣票者姓名</th>
                <th>價格</th>
                <th>票券逼號</th>
                
                <th>轉讓訂單狀態</th>
                <th>轉讓訂單成立日期</th>
                <!-- <th>付款方式</th> -->
                
                <!-- <th>主題名稱</th>
                <th>開始日期</th>
                <th>場地名稱</th>
                
                <th>付款方式</th>
                <th>訂單狀態</th> -->
                
            </tr>
        </thead>
        <tbody>
        	<tr>
        		<td>${resaleorderService.getOneResaleOrd(resale_ordno).member_buyer_no}</td>
        		<td>${memberService.getOneMember(resaleorderService.getOneResaleOrd(resale_ordno).member_seller_no).email}</td>
        		<%-- <td><fmt:formatDate value="${TicketVO.ticket_create_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td> --%>
        		<td>${resaleorderService.getOneResaleOrd(resale_ordno).resale_ordprice}</td>
        		<td>${resaleorderService.getOneResaleOrd(resale_ordno).resale_ordno}</td>
        		
        		<td>${resaleorderService.getOneResaleOrd(resale_ordno).resale_ordstatus}</td>
        		<td>${resaleorderService.getOneResaleOrd(resale_ordno).resale_ord_createtime}</td>
        		
        		<%-- <td>${Event_H5_Service.getOneEvent_H5(SeatingArea_H5_Service.getOneSeatingArea_H5(ticketService.getOneTicket(resaleorderService.getOneResaleOrd(resale_ordno).ticketVO.ticket_no).seatingarea_h5VO.ticarea_no).eve_h5VO.eve_no).eventtitle_h5VO.evetit_name}</td>
        		<td><fmt:formatDate value="${Event_H5_Service.getOneEvent_H5(SeatingArea_H5_Service.getOneSeatingArea_H5(ticketService.getOneTicket(resaleorderService.getOneResaleOrd(resale_ordno).ticketVO.ticket_no).seatingarea_h5VO.ticarea_no).eve_h5VO.eve_no).eve_startdate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
        		<td>${Event_H5_Service.getOneEvent_H5(SeatingArea_H5_Service.getOneSeatingArea_H5(ticketService.getOneTicket(resaleorderService.getOneResaleOrd(resale_ordno).ticketVO.ticket_no).seatingarea_h5VO.ticarea_no).eve_h5VO.eve_no).venue_h5VO.venue_name}</td> --%>
        	</tr>
        </tbody>
        <tfoot>
        	<tr>
          		<th>買家姓名</th>
            	<th>賣票者姓名</th>
                <th>價格</th>
                <th>票券逼號</th>
                <th>轉讓訂單狀態</th>
                <th>轉讓訂單成立日期</th>
            </tr>
        </tfoot>
    </table>
    
    <div class="container" style="margin-bottom:30px;">
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/frontend/resaleorder/resaleorder.do" name="form1" id="payForm">
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
							<input type="radio" name="payment" id="ewalletRadio" value="ewallet">電子錢包(未實作)
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
			<input type="hidden" name="action" value="pay_One_resaleorder_by_member">
			<%-- <input type="hidden" name="ticket_order_no"  value="${toVO.ticket_order_no}">
			<input type="hidden" name="eve_no"  value="${eve_no}"> --%>
			<input type="button" value="進行付款" class="btn btn-primary" id="wannaPay">
		</FORM>
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