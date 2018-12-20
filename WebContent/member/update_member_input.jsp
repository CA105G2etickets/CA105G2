<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>

<%
	MemberVO member = (MemberVO) request.getAttribute("member"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>會員資料修改 - update_emp_input.jsp</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
</style>
<style>
.memberphoto {
	border-radius: 50px;
	margin-top: 20px;
}

.membermenu {
	margin-top: 100px;
	margin-left: 200px;
}

.topnav {
	list-style-type: none;
	margin: 0;
	padding: 0;
	overflow: hidden;
	background-color: #333;
}

.topnav {
	display: block;
	color: white;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
}

</style>
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
<nav class="navbar navbar-inverse" role="navigation">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-ex1-collapse">
				<span class="sr-only">選單切換</span> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
			</button>
			<img src="images/LOGO_back-end.png" href="#" alt="LOGO"
				width="202.25px" height="165.5px">
		</div>

		<!-- 手機隱藏選單區  -->

		<div class="collapse navbar-collapse navbar-ex1-collapse">
			<!-- 右選單 -->
			<img
				src="https://scontent-hkg3-1.xx.fbcdn.net/v/t1.0-1/c53.53.662.662a/s160x160/996812_623306544360262_513913499_n.jpg?_nc_cat=109&_nc_eui2=AeEvi_vj3AZ5wk2s31mtunvrLPbVPtJK2jf7uWRYtFCuPw_M1yTd23yuh2AGeVu5aGSm_1aLOh_81tqazaXh-ECnpuFl77aq8E38y3WIOxRGcA&_nc_ht=scontent-hkg3-1.xx&oh=c8b216f2429b70114bdb941b525f73cf&oe=5CA0CFE7"
				class="memberphoto" href="#" alt="LOGO" style="float: right"
				width="80px" height="80px">

			<ul class="nav navbar-nav navbar-right membermenu">
				<li><a href="#">管理員登出</a></li>
				<li><a href="#">設定</a></li>
<!-- 				<li class="dropdown"><a href="#" class="dropdown-toggle" -->
<!-- 					data-toggle="dropdown">繁體中文 <b class="caret"></b></a> -->
<!-- 					<ul class="dropdown-menu"> -->
<!-- 						<li><a href="#">繁體中文</a></li> -->
<!-- 						<li><a href="#">English</a></li> -->
<!-- 						<li><a href="#">日本語</a></li> -->
<!-- 					</ul> -->
<!-- 				</li> -->
			</ul>
		</div>
		<!-- 手機隱藏選單區結束 -->
	</div>
<div class="topnav">
	<div class="col-xs-12 col-sm-2">
		<div>
			<a href="#" class="topnav" align="center">活動管理</a>
		</div>
	</div>
	<div class="col-xs-12 col-sm-2">
		<div>
			<a href="#" class="topnav" align="center">票券管理</a>
		</div>
	</div>
	<div class="col-xs-12 col-sm-2">
		<div>
			<a href="#" class="topnav" align="center">商品管理</a>
		</div>
	</div>
	<div class="col-xs-12 col-sm-2">
		<div>
			<a href="#" class="topnav" align="center">團購管理</a>
		</div>
	</div>
	<div class="col-xs-12 col-sm-2">
		<div>
			<a href="#" class="topnav" align="center">常見問題管理</a>
		</div>
	</div>
	<div class="col-xs-12 col-sm-2">
		<div>
			<a href="#" class="topnav" align="center">公告管理</a>
		</div>
	</div>
</div>
</nav>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="member.do" name="form1">
<table class="table">
	<tr>
		<td>會員編號:<font color=red></font></td>
		<td><%=member.getMemberNo()%></td>
	</tr>
	<tr>
		<td>會員姓名:</td>
		<td><input type="TEXT" name="name" size="45" value="<%=member.getMemberFullname()%>" /></td>
	</tr>
	<tr>
		<td>會員電子郵件:</td>
		<td><input type="email" name="email" size="45"	value="<%=member.getEmail()%>" /></td>
	</tr>
	<tr>
		<td>會員手機號碼:</td>
		<td><input type="TEXT" name="phone" size="45"	value="<%=member.getPhone()%>" /></td>
	</tr>
	<tr>
		<td>會員身分證字號:</td>
		<td><%=member.getIdcard()%></td>
	</tr>
	<tr>
		<td>會員帳號:</td>
		<td><input type="TEXT" name="account" size="45"	value="<%=member.getMemberAccount()%>" /></td>
	</tr>
	<tr>
		<td>會員密碼:</td>
		<td><input type="TEXT" name="password" size="45" value="<%=member.getMemberPassword()%>" /></td>
	</tr>
	<tr>
		<td>會員電子錢包餘額:</td>
		<td><%=member.getEwalletBalance()%></td>
	</tr>
	<tr>
		<td>帳號建立日期:</td>
		<td><span id="f_date1"><%=member.getCreationDate()%></span></td>
	</tr>
	<tr>
		<td>會員大頭貼:</td>
		<td><input type="file" accept="image/jpeg, image/png" name="picture" size="45"	value="<%=member.getProfilePicture()%>" /></td>
	</tr>
	<tr>
		<td>會員狀態:</td>
		<td><input type="TEXT" name="states" size="45" value="<%=member.getMemberStatus()%>" /></td>
	</tr>
<!-- 	<tr> -->
<!-- 		<td>會員狀態:</td> -->
<!-- 		<td> -->
<!-- 		<select size="1" name="states"> -->
<%-- 			<c:forEach var="state" items="${memberService.all}"> --%>
<%-- 				<option value="${member.memberno}" ${(member.memberStatus==member.memberStatus)? 'selected':'' } > --%>
<%-- 			</c:forEach> --%>
<!-- 		</select> -->
<!-- 		</td> -->
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>會員狀態:</td> -->
<!-- 		<td> -->
<!-- 		<select size="1" name="states"> -->
<!-- 			<option value="normal" >normal</option> -->
<!-- 			<option value="abnormal">abnormal</option> -->
<!-- 		</select> -->
<!-- 		</td> -->
<!-- 	</tr> -->
	<tr>
		<td>會員第三方登入UID:</td>
		<td><%=member.getThirduid()%></td>
<%-- 		<td><%=(member.getThirduid().isEmpty() ? "" : member.getThirduid())%></td> --%>
	</tr>

<%-- 	<jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" /> --%>
<!-- 	<tr> -->
<!-- 		<td>部門:<font color=red><b>*</b></font></td> -->
<!-- 		<td><select size="1" name="deptno"> -->
<%-- 			<c:forEach var="deptVO" items="${deptSvc.all}"> --%>
<%-- 				<option value="${deptVO.deptno}" ${(empVO.deptno==deptVO.deptno)?'selected':'' } >${deptVO.dname} --%>
<%-- 			</c:forEach> --%>
<!-- 		</select></td> -->
<!-- 	</tr> -->

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="memberno" value="<%=member.getMemberNo()%>">
<input type="submit" value="送出修改"></FORM>
<a href="listAllMember.jsp"><img src="images/back1.png" width="186" height="81" border="0"></a>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

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
 	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
 		   value: '<%=member.getCreationDate()%>', // value:   new Date(),
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