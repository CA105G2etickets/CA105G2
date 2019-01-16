<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>TicketOrder_select_page</title>

</head>
<body bgcolor='white'>

<p>進行查詢的會員編號，目前寫死為M000001</p>

<h3>會員的訂票訂單查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllTicketOrder.jsp'>List</a> all TicketOrders.  <br><br></li>
  
  <li>
    <FORM METHOD="post" ACTION="ticketorder.do" >
        <b>輸入訂票訂單編號 (如TO_20181225_000001):</b>
        <input type="text" name="ticket_order_no">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>
  <!--  
  <jsp:useBean id="TicketOrderSvc" scope="page" class="com.ticketorder.model.TicketOrderService" />
  
  <li>
     <FORM METHOD="post" ACTION="ticketorder.do" >
       <b>選擇某一張訂票訂單的編號:</b>
       <select size="1" name="ticket_order_no">
         <c:forEach var="TicketOrderVO" items="${TicketOrderSvc.all}" > 
          <option value="${TicketOrderVO.ticket_order_no}">${TicketOrderVO.ticket_order_no}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  <li>
     <FORM METHOD="post" ACTION="ticketorder.do" >
       <b>選擇某一張訂票訂單的狀態:</b>
       <select size="1" name="ticket_order_no">
         <c:forEach var="TicketOrderVO" items="${TicketOrderSvc.all}" > 
          <option value="${TicketOrderVO.ticket_order_no}">${TicketOrderVO.ticket_order_status}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
  -->
</ul>


<h3>購票</h3>
<jsp:useBean id="EveTitSvc" scope="page" class="com.event_title.model.EventTitleService" />
<ul> 
  <li>
     <FORM METHOD="post" ACTION="ticketorder.do" >
       <b>選擇某一個活動的活動場次名稱，來進行購票:</b>
       <select size="1" name="evetit_no">
         <c:forEach var="EventTitleVO" items="${EveTitSvc.all}" > 
          <option value="${EventTitleVO.evetit_no}">${EventTitleVO.evetit_name}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="get_EventTitleVO_InfoToBuyTickets">
       <input type="submit" value="送出">
    </FORM>
  </li>
</ul>

</body>
</html>