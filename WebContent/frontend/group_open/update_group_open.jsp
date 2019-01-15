<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.group_open.model.*"%>

<% 
	Group_openVO group_openVO = (Group_openVO) request.getAttribute("group_openVO");
%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>開團資料修改</title>
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
<script src="https://code.jquery.com/jquery.js"></script>
<link   rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
<script src="<%=request.getContextPath()%>/ckeditor/ckeditor.js"></script>
<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
$.datetimepicker.setLocale('zh'); // kr ko ja en
$(function(){
	 $('#start_dateTime').datetimepicker({
	  format:'Y-m-d H:i:s',
	  onShow:function(){
	   this.setOptions({
	    maxDate:$('#end_dateTime').val()?$('#end_dateTime').val():false
	   })
	  },
	  timepicker:true,
	  step: 1
	 });
	 
	 $('#end_dateTime').datetimepicker({
	  format:'Y-m-d H:i:s',
	  onShow:function(){
	   this.setOptions({
	    minDate:$('#start_dateTime').val()?$('#start_dateTime').val():false
	   })
	  },
	  timepicker:true,
	  step: 1
	 });
	 
	 $('#group_dateTime').datetimepicker({
		  format:'Y-m-d H:i:s',
		  onShow:function(){
		   this.setOptions({
		    minDate:$('#end_dateTime').val()?$('#end_dateTime').val():false
		   })
		  },
		  timepicker:true,
		  step: 1
		 });
});
	var loadFile = function(event) {
	var output = document.getElementById('output');
	output.src = URL.createObjectURL(event.target.files[0]);
	};

	var loadFile2 = function(event) {
	var output = document.getElementById('output1');
	output.src = URL.createObjectURL(event.target.files[0]);
	};
</script>


</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>資料修改 - update_group_open.jsp</h3>
		 <h4><a href="group_open.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>
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
<Form  METHOD="post" ACTION="<%=request.getContextPath()%>/group_open/group_open.do" name="form1" enctype="multipart/form-data">
<table>


	<tr>
		<td>會員編號</td>
		<td><input type="TEXT" name="member_no" size="45" value="<%=group_openVO.getMember_no()%>" readonly="readonly"/></td>
	</tr>
	<tr>
		<td>商品編號</td>
		<td><input type="TEXT" name="goods_no" size="45"
			 value="<%=group_openVO.getGoods_no()%>" /></td>
	</tr>
	<tr>
		<td>開團名稱</td>
		<td><input type="TEXT" name="group_name" size="45"
			 value="<%=group_openVO.getGroup_name()%>" /></td>
	</tr>
	<tr>
		<td>開團下限</td>
		<td><input type="TEXT" name="group_limit" size="45"
			 value="<%=group_openVO.getGroup_limit()%>" /></td>
	</tr>
	<tr>
		<td>開團介紹</td>
		<td><textarea name="group_introduction" size="45" value="<%=group_openVO.getGroup_introduction()%>"></textarea></td>
	</tr>
	<tr>
		<td>開團注意事項</td>
		<td><textarea name="group_mind" size="45" value="<%=group_openVO.getGroup_mind()%>"></textarea></td>
	</tr>
	
	<tr>
		<td>開團開始時間</td>
		<td><input name="group_start_date" id="start_dateTime" type="text" value="<%=group_openVO.getGroup_start_date()%>" readonly="readonly"></td>
	</tr>
	<tr>
		<td>開團結束時間</td>
		<td><input name="group_close_date" id="end_dateTime" type="text" value="<%=group_openVO.getGroup_close_date()%>"></td>
	</tr>
	<tr>
		<td>開團封面1</td>
		<td><input type="file" name="group_banner_1" size="45" onchange="loadFile(event)"/></td>
	</tr>
	 <div><img src ="<%=request.getContextPath()%>/group_open/Group_openImg1.do?group_no=${group_openVO.group_no}" id="output" height="200" width="200" /></div>
	<tr>
		<td>開團封面2</td>
		<td><input type="file" name="group_banner_2" size="45"  onchange="loadFile2(event)"/></td>
	</tr>
	 <div><img src ="<%=request.getContextPath()%>/group_open/Group_openImg2.do?group_no=${group_openVO.group_no}" id="output1" height="200" width="200" /></div>
	<tr>
		<td>選擇開團狀態</td>
		<td>
		<input type="TEXT" name="group_status" size="45" value="<%=group_openVO.getGroup_status()%>" />
		</td>
	</tr>
	<tr>
		<td>面交地址</td>
		<td><input type="TEXT" name="group_address" size="45" value="<%=group_openVO.getGroup_address()%>" /></td>
	</tr>
	<tr>
		<td>緯度</td>
		<td><input type="TEXT" name="latitude" size="45" 
		value="<%=group_openVO.getLatitude()%>" /></td>
	</tr>
	<tr>
		<td>經度</td>
		<td><input type="TEXT" name="longitude" size="45"  
		value="<%=group_openVO.getLongitude()%>" /></td>
	</tr>
	<tr>
		<td>開團結束時間</td>
		<td><input name="group_time" id="group_dateTime" type="text" value="<%=group_openVO.getGroup_time()%>" ></td>
	</tr>
	<tr>
		<td>商品促銷價格</td>
		<td><input type="TEXT" name="group_price" size="45"
			 value="<%=group_openVO.getGroup_price()%>" /></td>
	</tr>
</table>
<input type="hidden" name="action" value="update">
<input type="hidden" name="group_no" value="<%=group_openVO.getGroup_no()%>"> 
<input type="submit" value="送出修改">
</FORM>
<jsp:include page="/frontend/footer_front-end.jsp" flush="true" />

</body>
</html>