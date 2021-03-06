<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
 
<%
	MemberVO member = (MemberVO) request.getAttribute("member");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>

<title>ETIckeTs後台 - 新增會員</title>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

<style>
table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}
th, td {
	padding: 5px;
	text-align: center;
}
img {
	border-radius: 90%;
}
</style>

<script>
function readURL(input){
  if(input.files && input.files[0]){
    var imageID = input.getAttribute("targetID");
    var reader = new FileReader();
    reader.onload = function (e) {
       var img = document.getElementById(imageID);
       img.setAttribute("src", e.target.result)
       img.setAttribute("height", 100)
       img.setAttribute("width", 100)
    }
    reader.readAsDataURL(input.files[0]);
  }
}
</script>

</head>

<jsp:include page="/backend/navbar_back-end.jsp" flush="true"/> 

<div class="container table-responsive-md">
<div class="row">

<h3 align="center">新增會員</h3>

<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="member.do" name="form1" enctype="multipart/form-data">
<table class="table">
	<tr>
		<td>會員姓名</td>
		<td><input type="TEXT" placeholder="吳永志" name="name" size="45"></td>
	</tr>
	<tr>
		<td>會員電子郵件</td>
		<td><input type="email" placeholder="xxx@gmail.com"name="email" size="45"></td>
	</tr>
	<tr>
		<td>會員手機號碼</td>
		<td><input type="TEXT" placeholder="0923" name="phone1" size="10">&nbsp;-&nbsp;<input type="TEXT" placeholder="123456" name="phone2" size="15"></td>
	</tr>
	<tr>
		<td>會員身分證字號</td>
		<td><input type="TEXT" placeholder="T100123456" name="idcard" size="45"></td>
	</tr>
	<tr>
		<td>會員帳號</td>
		<td><input type="TEXT" placeholder="eticketsTest" name="account" size="45"></td>
	</tr>
	<tr>
		<td>會員密碼</td>
		<td><input type="TEXT" placeholder="123456" name="password" size="45"></td>
	</tr>
	<tr>
		<td>會員電子錢包餘額</td>
		<td>0</td>
	</tr>
	<tr>
		<td>帳號建立日期時間</td>
		<td>系統新增時間</td>
	</tr>
	<tr height="145">
		<td>會員大頭貼</td>
		<td>
		<input type="file" accept="image/jpeg, image/png" name="picture" onchange="readURL(this)" targetID="previewImg">
		<img id="previewImg" src="" height="" width="">
		</td>
	</tr>
	<tr>
		<td>會員狀態</td>
		<td>normal</td>
	</tr>
	<tr>
		<td>會員第三方登入UID</td>
		<td>625549954</td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="hidden" name="thirduid" value="625549954">
<input type="submit" value="送出新增" >
</FORM>

</div>
</div>
</body>

<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<% 
  java.sql.Timestamp creationDate = null;
  try {
		creationDate = member.getCreationDate();
   } catch (Exception e) {
		creationDate = new java.sql.Timestamp(System.currentTimeMillis());
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
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=creationDate%>', // value:   new Date(),
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