<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>resaleorder_home</title>
</head>
<body bgcolor='white'>

<p>it's resaleord's home-page</p>

<h3>資料查詢:</h3>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<%-- 
<jsp:useBean id="TicketSvc" scope="page" class="com.ticket.model.TicketService" />
<jsp:useBean id="EventSvc" scope="page" class="com.ticket.model.TicketService" />
--%>


<%-- 萬用複合查詢-以下欄位-可隨意增減 --%>
<ul>  
  <li>   
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/frontend/resaleorder/resaleorder.do">
        <b><font color=blue>請根據下列任一條件去，複合查詢後，搜尋出你想要買的轉讓票:</font></b> <br>
        
        <!--  
        <b>輸入活動名稱，找目標活動有幾張轉售票</b>
        <input type="text" name="" value=""><br>
        -->
       
       <!--  
       <b>輸入賣家的會員編號:(目前僅能是會員編號，未來希望能進化成查某會員的email或姓名)</b>
       <input type="text" name="" value=""><br>
       -->
       
       <b>輸入轉讓票券的成交賣價(目前僅能查是不是 <= 這個價錢，並列出所有的轉讓票):</b>
       <input type="text" name="ticket_resale_price" value=""><br>
       
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
        <input type="hidden" name="ticket_resale_status" value="SELLING2">
        <input type="hidden" name="action" value="listResaleOrder_ByCompositeQuery">
     </FORM>
  </li>
  <li>
  	<FORM>
  		<b>temporarily no content</b>
  	</FORM>
  </li>
</ul>

<!--  
<h3>員工管理</h3>
<ul>
  <li><a href='<%=request.getContextPath()%>/emp/addEmp.jsp'>Add</a> a new Emp.</li>
</ul>

<h3><font color=orange>部門管理</font></h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/dept/listAllDept.jsp'>List</a> all Depts. </li>
</ul>
-->
<h3><font color=orange>Show Route of 'a href'</font></h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/frontend/resaleorder/resaleorder.do'>Online after delete this</a></li>
</ul>

</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/vendor/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/vendor/datetimepicker/jquery.datetimepicker.css"></script>
<script src="<%=request.getContextPath()%>/vendor/datetimepicker/jquery.datetimepicker.css"></script>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
 	       theme: '',              //theme: 'dark',
	       timepicker:true,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
	       
	       <% 
	       java.util.Date pickStartDate = new java.sql.Date(System.currentTimeMillis());
	       %>
		   value: '<%=pickStartDate%>',              // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        
   
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

        //      1.以下為某一天之前的日期無法選擇
        //      var somedate1 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});

        
        //      2.以下為某一天之後的日期無法選擇
        //      var somedate2 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});


        //      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
        //      var somedate1 = new Date('2017-06-15');
        //      var somedate2 = new Date('2017-06-25');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //		             ||
        //		            date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});
        
</script>
</html>