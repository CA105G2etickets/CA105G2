<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>購票狀況查詢</title>
</head>
<body bgcolor='white'>

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
<jsp:include page="/backend/navbar_back-end.jsp" flush="true" />
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>


<%-- 萬用複合查詢-以下欄位-可隨意增減 --%>
<ul>  
  <li>   
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/backend/ticket/ticket.do">
        <b><font color=blue>請選取下列一種條件，再輸入你想搜尋的條件，搜尋出你想要買的轉讓票: 文字為模糊搜尋，數字可選'>' '<' '='</font></b> <br>
        <b><font color=blue>選取條件的map put判斷條件還沒有做，只能查全部</font></b> <br>
        <select name=selectBar_Choice>
        	<option value="ticket_resale_price" selected="selected">轉售票的價錢</option>
        	<option value="member_no" >會員編號</option>
        	<option value="ticarea_name">座位區名稱</option>
        	<option value="tictype_name">票種名稱</option>
        	<option value="tictype_price">原價格</option>
        	<option value="evetit_name">活動主題名稱</option>
        	<option value="venue_name">場地名稱</option>
        	<option value="address">場地地址</option>
        </select>
        <!--  
        <b>輸入活動名稱，找目標活動有幾張轉售票</b>
        <input type="text" name="" value=""><br>
        -->
       
       <!--  
       <b>輸入賣家的會員編號:(目前僅能是會員編號，未來希望能進化成查某會員的email或姓名)</b>
       <input type="text" name="" value=""><br>
       -->
       
       <!-- <b>輸入轉讓票券的成交賣價(目前僅能查是不是 <= 這個價錢，並列出所有的轉讓票):</b>
       <input type="text" name="ticket_resale_price" value=""><br> -->
       
       <!--  
       <b>選擇票券編號，查詢與這張票券相關的轉售訂單:(理論上是給管理者看)</b>
       <select size="1" name="ticket_no" >
          <option value="">
         <c:forEach var="ticketVO" items="${TicketSvc.all}" > 
          <option value="${ticketVO.ticket_no}">${ticketVO.ticket_no}
         </c:forEach>   
       </select><br>
       -->
        
        <!--  this function seems can do with jQuery
       <b>選擇轉售訂單產生的日期:(理論上要進化成能選擇某段區間的時間，並根據時間排序)</b>
	   <input name="resale_ord_createtime" id="f_date1" type="text">
		        
        -->
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="listResaleOrder_ByCompositeQuery">
        <!-- <input type="hidden" name="resale_ordstatus" value="SELLING1"> -->
     </FORM>

<h3><a href='<%=request.getContextPath()%>/frontend/resaleorder/resaleorder.do'>偷看一下FROM表單送到哪裡</a></h3>

</body>
</html>