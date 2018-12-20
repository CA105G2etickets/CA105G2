<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ticketorder.model.*"%>


<%
TicketOrderVO toVO = (TicketOrderVO) request.getAttribute("toVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>訂票訂單資料新增 - addTicketOrder.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

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

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>訂票訂單資料新增 - addTicketOrder.jsp</h3></td><td>
		 <h4><a href="select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="ticketorder.do" name="form1">
<table>
	<tr>
		<td>會員編號:</td>
		<td><input type="TEXT" name="member_no" size="45"
			 value="<%= (toVO==null)? "M000001" : toVO.getMember_no()%>" /></td>
	</tr>
	<tr>
		<td>座位區編號:</td>
		<td><input type="TEXT" name="ticarea_no" size="45"
			 value="<%= (toVO==null)? "E000101A01" : toVO.getTicarea_no()%>" /></td>
	</tr>
	<tr>
		<td>總價:</td>
		<td><input type="TEXT" name="total_price" size="45"
			 value="<%= (toVO==null)? "900" : toVO.getTotal_price()%>" /></td>
	</tr>
	<tr>
		<td>總張數:</td>
		<td><input type="TEXT" name="total_amount" size="45"
			 value="<%= (toVO==null)? "2" : toVO.getTotal_amount()%>" /></td>
	</tr>
	<tr>
		<td>訂票訂單成立時間:</td>
		<td><input name="ticket_order_time" id="f_date1" type="text"></td>
	</tr>
	<tr>
		<td>付款方式:</td>
		<td><input type="TEXT" name="payment_method" size="45"
			 value="<%= (toVO==null)? "creditcard" : toVO.getPayment_method()%>" /></td>
	</tr>
	<tr>
		<td>訂票訂單狀態:</td>
		<td><input type="TEXT" name="ticket_order_status" size="45"
			 value="<%= (toVO==null)? "waitForPay1" : toVO.getTicket_order_status()%>" /></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<% 
java.sql.Timestamp ticket_order_time = null;
java.util.Date pickStartDate = new java.sql.Date(System.currentTimeMillis());
  try {
	  ticket_order_time = toVO.getTicket_order_time();
   } catch (Exception e) {
	   ticket_order_time = new java.sql.Timestamp(System.currentTimeMillis());
   }
%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
		<%--var now = new Date();
		var startDate = now.getFullYear() + "-" + (now.getMonth()+1) + "-" + now.getDate();
		console.log(startDate);--%>
		<%%>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:true,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d H:i:s',   //format:'Y-m-d H:i:s',
		   value: '<%=ticket_order_time%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:<%=new java.sql.Date(pickStartDate.getTime())%>,  // 起始日
           //minDate:'<%=new java.sql.Date(pickStartDate.getTime())%>', // 去除今日(不含)之前
           minDate:'<%=ticket_order_time%>', // 去除今日(不含)之前
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