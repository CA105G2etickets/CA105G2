<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>

<%
  MemberVO member = (MemberVO) session.getAttribute("member"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>ETIckeTs - 會員個人資料</title>
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
  img {
  	border-radius: 90%;
}
</style>
</head>

<jsp:include page="/frontend/navbar_front-end.jsp" flush="true"/> 

<div class="container">
<table class="table">
	<tr>
		<th>會員姓名</th>
		<th>會員Email</th>
		<th>會員電話</th>
		<th>會員身分證字號</th>
		<th>會員帳號</th>
		<th>會員密碼</th>
		<th>會員帳號建立日期</th>
		<th>會員大頭貼</th>
<!-- 		<th>會員第三方登入UID</th> -->
	</tr>
	<tr>
		<td><%=member.getMemberFullname()%></td>
		<td><%=member.getEmail()%></td>
		<td><%=member.getPhone()%></td>
		<td><%=member.getIdcard()%></td>
		<td><%=member.getMemberAccount()%></td>
		<td><%=member.getMemberPassword()%></td>
		<td><%=member.getCreationDate()%></td>
		<td><img src="<%=request.getContextPath()%>/member/memberImg.do?memberno=${member.memberNo}" height="50" width="50"></td>
<%-- 		<td><%=member.getThirduid()%></td> --%>
	</tr>
</table>
</div>
</body>
<jsp:include page="/frontend/footer_front-end.jsp" flush="true"/> 
</html>