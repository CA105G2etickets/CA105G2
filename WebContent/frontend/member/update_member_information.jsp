<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.member.model.*"%>

<%
  MemberVO member = (MemberVO) session.getAttribute("member");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>ETIckeTs - 會員個人資料修改</title>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>

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
  .memberprofilebtn {
  	width: calc((100% - 4px) / 2);
  }
  .frontmemberbutton {
  	margin-bottom: 40px;
  	float: none;
  	position: fixed;
  	align-content: center;
  }
  body{
	font-family:微軟正黑體!important;
}
/****************************************** 通知部分 ******************************************/
.badge-pill {
    padding: 0.2em;
    display: inline-block;
    padding: 0.25em 0.4em;
    font-size: 75%;
    font-weight: 700;
    line-height: 1;
    text-align: center;
    white-space: nowrap;
    vertical-align: baseline;
}
.badge-primary {
    color: white;
    background-color: #3399ff;
}
/****************************************** 通知結束 ******************************************/
</style>

<script>
function readURL(input){
  if(input.files && input.files[0]){
    var imageID = input.getAttribute("targetID");
    var reader = new FileReader();
    reader.onload = function (e) {
       var img = document.getElementById(imageID);
       img.setAttribute("src", e.target.result)
    }
    reader.readAsDataURL(input.files[0]);
  }
}
</script>

</head>

<body>

<jsp:include page="/frontend/navbar_front-end.jsp" flush="true"/>

<div class="container">
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="/CA105G2/member/member.do" name="form1" enctype="multipart/form-data">
<table class="table">
	<tr>
		<td>會員編號:<font color=red></font></td>
		<td><%=member.getMemberNo()%></td>
	</tr>
	<tr>
		<td>會員大頭貼</td>
		<td><img src="<%=request.getContextPath()%>/member/memberImg.do?memberno=${member.memberNo}" height="50" width="50" class="memberimg"></td>
	</tr>
	<tr>
		<td>會員姓名:</td>
		<td><input type="TEXT" name="memberFullname" size="45" value="<%=member.getMemberFullname()%>" /></td>
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
		<td><input type="TEXT" name="memberAccount" size="45"	value="<%=member.getMemberAccount()%>" /></td>
	</tr>
	<tr>
		<td>會員密碼:</td>
		<td><input type="TEXT" name="memberPassword" size="45" value="<%=member.getMemberPassword()%>" /></td>
	</tr>
	<tr>
		<td>帳號建立日期:</td>
		<td><fmt:formatDate value="${member.creationDate}" pattern="yyyy-MM-dd"/></td>
	</tr>
	<tr>
		<td>會員狀態:</td>
		<td><%=member.getMemberStatus()%></td>
	</tr>
</table>
<br>
<input type="hidden" name="action" value="update_front">
<input type="hidden" name="idcard" value="<%=member.getIdcard()%>">
<input type="hidden" name="ewalletBalance" value="<%=member.getEwalletBalance()%>">
<input type="hidden" name="creationDate" value="<%=member.getCreationDate()%>">
<input type="hidden" name="memberStatus" value="<%=member.getMemberStatus()%>">
<input type="hidden" name="thirduid" value="<%=member.getThirduid()%>">
<input type="hidden" name="memberno" value="<%=member.getMemberNo()%>">
<div class="col-xs-12 col-sm-12 frontmemberbutton">
<input type="submit" value="送出修改" class="btn btn-primary btn-lg memberprofilebtn">
<a href="<%=request.getContextPath()%>/frontend/member/member_profile.jsp"><button type="button" class="btn btn-primary btn-lg memberprofilebtn">取消</button></a>
</div>
</FORM>
</div>

</body>

<jsp:include page="/frontend/footer_front-end.jsp" flush="true"/>

</html>