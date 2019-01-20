<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.administrator.model.*"%>

<%
	AdministratorVO administratorVO = (AdministratorVO) request.getAttribute("administratorVO");
%>

<html>
<head>

<title>ETIckeTs後台 - 管理員資料</title>

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
  img {
  border-radius: 90%;
}
</style>

</head>

<jsp:include page="/backend/navbar_back-end.jsp" flush="true"/>  

<div class="container table-responsive-md">
<div class="row">

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
	<tr>
		<td><%=administratorVO.getAdministrator_no()%></td>
		<td><%=administratorVO.getAdministrator_name()%></td>
		<td><%=administratorVO.getAdministrator_account()%></td>
		<td><%=administratorVO.getAdministrator_password()%></td>
		<td><%=administratorVO.getCreation_date()%></td>
		<td><img src="<%=request.getContextPath()%>/backend/administrator/administratorImg.do?administrator_no=${administratorVO.administrator_no}" height="50" width="50"></td>
		<td><%=administratorVO.getAdministrator_status()%></td>
	</tr>
</table>
<div class="col-xs-12 col-sm-12">
<a href="allAdministrator.jsp"><button type="button" class="btn btn-primary btn-lg btn-block">返回</button></a>
<br>
</div>

</div>
</div>
</body>
</html>