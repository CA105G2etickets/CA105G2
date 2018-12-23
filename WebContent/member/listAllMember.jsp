<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	MemberService memberService = new MemberService();
	List<MemberVO> list = memberService.getAll();
	pageContext.setAttribute("list", list);
%>


<html>
<head>
<title>所有會員資料 - listAllMember.jsp</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
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

img {
  border-radius: 90%;
}
</style>

</head>

<%-- import進導覽列 --%>
<div>                   
	<c:import url="/navbar_back-end.html" charEncoding="UTF-8">
	</c:import>
</div>
<%----%>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color: red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color: red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

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
	<%@ include file="page1.file"%>
	<c:forEach var="member" items="${list}" begin="<%=pageIndex%>"
		end="<%=pageIndex+rowsPerPage-1%>">

		<tr>
			<td>${member.memberNo}</td>
			<td>${member.memberFullname}</td>
			<td>${member.email}</td>
			<td>${member.phone}</td>
			<td>${member.idcard}</td>
			<td>${member.memberAccount}</td>
			<td>${member.memberPassword}</td>
			<td>${member.ewalletBalance}</td>
			<td><fmt:formatDate value="${member.creationDate}"
					pattern="yyyy-MM-dd HH:mm:ss" /></td>
			<td><img src="<%=request.getContextPath()%>/member/memberImg.do?memberno=${member.memberNo}" height="50" width="50"></td>
			<td>${member.memberStatus}</td> 
			<td>${member.thirduid}</td> 
			<td>
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/member/member.do"
					style="margin-bottom: 0px;">
					<input type="submit" value="修改"> <input type="hidden"
						name="memberno" value="${member.memberNo}"> <input
						type="hidden" name="action" value="getOne_For_Update">
				</FORM>
			</td>
			<td>
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/member/member.do"
					style="margin-bottom: 0px;">
					<input type="submit" value="刪除"> <input type="hidden"
						name="memberno" value="${member.memberNo}"> <input
						type="hidden" name="action" value="delete">
				</FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file"%>
<div class="col-xs-12 col-sm-12">
<a href="select_page.jsp"><button type="button" class="btn btn-primary btn-lg btn-block">返回</button></a>
<br>
</div>
</body>
</html>