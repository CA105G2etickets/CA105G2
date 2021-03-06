<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.member.model.*"%>

<%
  MemberVO member = (MemberVO) session.getAttribute("member"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>ETIckeTs - 會員個人資料</title>
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
  .memberimg {
  	border-radius: 90%;
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
</head>

<jsp:include page="/frontend/navbar_front-end.jsp" flush="true"/> 

<div class="container table-responsive">
<table class="table table-hover">
	<tr>
		<td>會員大頭貼</td>
		<td><img src="<%=request.getContextPath()%>/member/memberImg.do?memberno=${member.memberNo}" height="50" width="50" class="memberimg"></td>
	</tr>
	<tr>
		<td>會員姓名</td>
		<td><%=member.getMemberFullname()%></td>
	</tr>
	<tr>
		<td>Email</td>
		<td><%=member.getEmail()%></td>
	</tr>
	<tr>
		<td>電話</td>
		<td><%=member.getPhone()%></td>
	</tr>
	<tr>
		<td>身分證字號</td>
		<td><%=member.getIdcard()%></td>
	</tr>
	<tr>
		<td>帳號</td>
		<td><%=member.getMemberAccount()%></td>
	</tr>
	<tr>
		<td>密碼</td>
		<td><%=member.getMemberPassword()%></td>
	</tr>
	<tr>
		<td>帳號建立日期</td>
		<td>
		<fmt:formatDate value="${member.creationDate}" pattern="yyyy-MM-dd"/>
		</td>
	</tr>
<!-- 	<tr> -->
<!-- 		<td>會員第三方登入UID</td> -->
<%-- 		<td><%=member.getThirduid()%></td> --%>
<!-- 	</tr> -->
</table>
<div class="col-xs-12 col-sm-12">
<a href="<%=request.getContextPath()%>/frontend/member/update_member_information.jsp"><button type="button" class="btn btn-primary btn-lg btn-block">編輯</button></a>
<br>
</div>
</div>
</body>
<jsp:include page="/frontend/footer_front-end.jsp" flush="true"/>
</html>