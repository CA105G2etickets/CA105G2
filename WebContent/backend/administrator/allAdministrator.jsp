<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.administrator.model.*"%>

<%
	AdministratorService administratorService = new AdministratorService();
	List<AdministratorVO> list = administratorService.getAll();
	pageContext.setAttribute("list", list);
%>

<html>
<head>

<title>ETIckeTs後台 - 所有管理員</title>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

<style>
.Administratorphoto {
	border-radius: 50px;
	margin-top: 20px;
}

.Administratormenu {
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
		<th>管理員編號</th>
		<th>管理員姓名</th>
		<th>帳號</th>
		<th>密碼</th>
		<th>管理員帳號建立日期</th>
		<th>大頭貼</th>
		<th>管理員狀態</th>
	</tr>
	<%@ include file="page1.file"%>
	<c:forEach var="administrator" items="${list}" begin="<%=pageIndex%>"
		end="<%=pageIndex+rowsPerPage-1%>">

		<tr>
			<td>${administrator.administrator_no}</td>
			<td>${administrator.administrator_name}</td>
			<td>${administrator.administrator_account}</td>
			<td>${administrator.administrator_password}</td>
			<td><fmt:formatDate value="${administrator.creation_date}"
					pattern="yyyy-MM-dd HH:mm:ss" /></td>
			<td><img class="img-circle" src="<%=request.getContextPath()%>/backend/administrator/administratorImg.do?administrator_no=${administrator.administrator_no}" height="50" width="50"></td>
			<td>${administrator.administrator_status}</td> 
			<td>
				<FORM METHOD="post"
					ACTION="administrator.do"
					style="margin-bottom: 0px;">
					<input type="submit" value="修改"> <input type="hidden"
						name="administrator_no" value="${administrator.administrator_no}"> <input
						type="hidden" name="action" value="getOne_For_Update">
				</FORM>
			</td>
			<td>
				<FORM METHOD="post"
					ACTION="administrator.do"
					style="margin-bottom: 0px;">
					<input type="submit" value="刪除"> <input type="hidden"
						name="administrator_no" value="${administrator.administrator_no}"> <input
						type="hidden" name="action" value="delete">
				</FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file"%>

</div>

</div>
</body>
</html>