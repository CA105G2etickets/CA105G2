<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%@ page import="com.administrator.model.*"%>
<%@	page import="com.permission.model.*"%>
<%@ page import="com.permission_list.model.*"%>
 
<%
	AdministratorVO administratorVO = (AdministratorVO) request.getAttribute("administratorVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>

<title>ETIckeTs後台 - 新增管理員</title>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

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
  img {
  border-radius: 90%;
}
/* Customize the label (the container) */
.labelcontainerforadd {
  position: relative;
  padding-left: 25px;
  margin-bottom: 5px;
  cursor: pointer;
  font-size: 16px;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
}
/* Hide the browser's default checkbox */
.labelcontainerforadd input {
  position: absolute;
  opacity: 0;
  cursor: pointer;
  height: 0;
  width: 0;
}
/* Create a custom checkbox */
.checkmark {
  position: absolute;
  top: 0;
  left: 0;
  height: 20px;
  width: 20px;
  background-color: #eee;
}
/* On mouse-over, add a grey background color */
.labelcontainerforadd:hover input ~ .checkmark {
  background-color: #ccc;
}
/* When the checkbox is checked, add a blue background */
.labelcontainerforadd input:checked ~ .checkmark {
  background-color: #3399ff;
}
/* Create the checkmark/indicator (hidden when not checked) */
.checkmark:after {
  content: "";
  position: absolute;
  display: none;
}
/* Style the checkmark/indicator */
.labelcontainerforadd .checkmark:after {
  left: 9px;
  top: 5px;
  width: 5px;
  height: 10px;
  border: solid white;
  border-width: 0 3px 3px 0;
  -webkit-transform: rotate(45deg);
  -ms-transform: rotate(45deg);
  transform: rotate(45deg);
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

<%
if (session.getAttribute("administratorVO") != null) {
	PermissionService permissionService = new PermissionService();
	List<String> theAdministratorPermission = permissionService.findPermissionByAdministratorNo(((AdministratorVO) session.getAttribute("administratorVO")).getAdministrator_no());
	pageContext.setAttribute("theAdministratorPermission", theAdministratorPermission);
%>
<%
for (String permission : theAdministratorPermission) {
if (permission.equals("PL08")) {
%>
<div class="container table-responsive-md">
<div class="row">

<h3 align="center">新增管理員</h3>

<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/administrator/administrator.do" name="form1" enctype="multipart/form-data">
<table class="table">
	<tr>
		<td>管理員姓名</td>
		<td><input type="TEXT" placeholder="吳永志" name="administrator_name" size="45"></td>
	</tr>
	<tr>
		<td>管理員帳號</td>
		<td><input type="TEXT" placeholder="eticketsTest" name="administrator_account" size="45"></td>
	</tr>
	<tr>
		<td>管理員密碼</td>
		<td><input type="password" placeholder="123456" name="administrator_password" size="45"></td>
	</tr>
	<tr height="145">
		<td>管理員大頭貼</td>
		<td>
		<input type="file" accept="image/jpeg, image/png" name="administrator_picture" onchange="readURL(this)" targetID="previewImg">
		<img id="previewImg" src="" height="" width="">
		</td>
	</tr>
	<tr>
		<td>管理員權限</td>
		<td>
		<label class="labelcontainerforadd">
  		<input type="checkbox" name="permission" value="PL05">活動管理
  		<span class="checkmark"></span>
		</label>
		<label class="labelcontainerforadd">
  		<input type="checkbox" name="permission" value="PL06">票券管理
  		<span class="checkmark"></span>
		</label>
		<label class="labelcontainerforadd">
  		<input type="checkbox" name="permission" value="PL02">商品管理
  		<span class="checkmark"></span>
		</label>
		<label class="labelcontainerforadd">
  		<input type="checkbox" name="permission" value="PL01">公告管理
  		<span class="checkmark"></span>
		</label>
		<label class="labelcontainerforadd">
  		<input type="checkbox" name="permission" value="PL07">常見問題管理
  		<span class="checkmark"></span>
		</label>
		<label class="labelcontainerforadd">
  		<input type="checkbox" name="permission" value="PL04">會員管理
  		<span class="checkmark"></span>
		</label>
		<label class="labelcontainerforadd">
  		<input type="checkbox" name="permission" value="PL08">管理員管理
  		<span class="checkmark"></span>
		</label>
		</td>
	</tr>
</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增" id="check">
</FORM>

</div>
</div>
<% }
}
} else { %>
<%
}
%>
</body>

<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<% 
  java.sql.Timestamp creationDate = null;
  try {
		creationDate = administratorVO.getCreation_date();
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