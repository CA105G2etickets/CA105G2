<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>

<%
  MemberVO member = (MemberVO) request.getAttribute("member");
%>

<html>
<head>

<title>ETIckeTs後台 - 會員資料</title>

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

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}
</style>

</head>

<jsp:include page="/backend/navbar_back-end.jsp" flush="true"/> 

<div class="container table-responsive-md">
<div class="row">

<table class="table">
	<tr>
		<th>會員編號</th>
		<th>會員姓名</th>
		<th>會員Email</th>
		<th>會員電話</th>
		<th>會員身分證字號</th>
		<th>會員帳號</th>
		<th>會員密碼</th>
		<th>會員電子錢包餘額</th>
		<th>會員帳號建立日期</th>
		<th>會員大頭貼</th>
		<th>會員狀態</th>
		<th>會員第三方登入UID</th>
	</tr>
	<tr>
		<td><%=member.getMemberNo()%></td>
		<td><%=member.getMemberFullname()%></td>
		<td><%=member.getEmail()%></td>
		<td><%=member.getPhone()%></td>
		<td><%=member.getIdcard()%></td>
		<td><%=member.getMemberAccount()%></td>
		<td><%=member.getMemberPassword()%></td>
		<td><%=member.getEwalletBalance()%></td>
		<td><%=member.getCreationDate()%></td>
		<td><img src="<%=request.getContextPath()%>/member/memberImg.do?memberno=${member.memberNo}" height="50" width="50"></td>
		<td><%=member.getMemberStatus()%></td>
		<td><%=member.getThirduid()%></td>
		<td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/member.do" style="margin-bottom: 0px;">
				<input type="submit" value="修改">
				<input type="hidden" name="memberno" value="${member.memberNo}">
				<input type="hidden" name="action" value="getOne_For_Update">
			</FORM>
		</td>
<!-- 		<td> -->
<%-- 			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/member.do" style="margin-bottom: 0px;"> --%>
<!-- 				<input type="submit" value="刪除"> -->
<%-- 				<input type="hidden" name="memberno" value="${member.memberNo}"> --%>
<!-- 				<input type="hidden" name="action" value="delete"> -->
<!-- 			</FORM> -->
<!-- 		</td> -->
	</tr>
</table>
<div class="col-xs-12 col-sm-12">
<a href="allMember.jsp"><button type="button" class="btn btn-primary btn-lg btn-block">返回</button></a>
<br>
</div>

</div>
</div>
</body>
</html>