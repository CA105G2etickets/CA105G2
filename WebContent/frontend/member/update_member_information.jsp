<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>

<%
  MemberVO member = (MemberVO) session.getAttribute("member");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>ETIckeTs - 會員個人資料修改</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
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

<FORM METHOD="post" ACTION="member.do" name="form1" enctype="multipart/form-data">
<table class="table">
	<tr>
		<td>會員編號:<font color=red></font></td>
		<td><%=member.getMemberNo()%></td>
	</tr>
	<tr height="145">
		<td>會員大頭貼:</td>
		<td>
		<input type="file" accept="image/jpeg, image/png" name="picture" onchange="readURL(this)" targetID="previewImg">
		<img id="previewImg" src="<%=request.getContextPath()%>/member/memberImg.do?memberno=${member.memberNo}" height="100" width="100">
		</td>
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
		<td><%=member.getCreationDate()%></td>
	</tr>
	<tr>
		<td>會員狀態:</td>
		<td>
			<select name="states">
				<option value="normal" ${(member.memberStatus eq "normal")? 'selected':'' }>正常</option>
				<option value="abnormal" ${(member.memberStatus eq "abnormal")? 'selected':'' }>異常</option>
			</select>
		</td>
	</tr>
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
<div class="col-xs-12 col-sm-12">
<a href="select_page.jsp"><button type="button" class="btn btn-primary btn-lg btn-block">返回</button></a>
<a href="select_page.jsp"><button type="button" class="btn btn-primary btn-lg btn-block">返回</button></a>
<br>
</div>
</div>
</body>
<jsp:include page="/frontend/footer_front-end.jsp" flush="true"/>
</html>