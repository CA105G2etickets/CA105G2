<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.administrator.model.*"%>

<%
  AdministratorVO administratorVO = (AdministratorVO) session.getAttribute("administratorVO");
%>

<html>
<head>
<title>ETIckeTs後台 - 管理員個人資料</title>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

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
  .administratorimg {
  	border-radius: 90%;
}
body{
	font-family:微軟正黑體!important;
}
</style>

</head>

<jsp:include page="/backend/navbar_back-end.jsp" flush="true"/> 

<div class="container table-responsive">
<div class="row">
<table class="table table-hover">
	<tr>
		<td>管理員編號</td>
		<td><%=administratorVO.getAdministrator_no()%></td>
	</tr>
	<tr>
		<td>管理員大頭貼</td>
		<td><img src="<%=request.getContextPath()%>/administrator/administratorImg.do?administrator_no=${administratorVO.administrator_no}" height="50" width="50" class="administratorimg"></td>
	</tr>
	<tr>
		<td>管理員姓名</td>
		<td><%=administratorVO.getAdministrator_name()%></td>
	</tr>
	<tr>
		<td>帳號</td>
		<td><%=administratorVO.getAdministrator_account()%></td>
	</tr>
	<tr>
		<td>密碼</td>
		<td><%=administratorVO.getAdministrator_password()%></td>
	</tr>
	<tr>
		<td>管理員狀態</td>
		<td><%=administratorVO.getAdministrator_status()%></td>
	</tr>
	<tr>
		<td>管理員帳號建立日期</td>
		<td>
		<fmt:formatDate value="${administratorVO.creation_date}" pattern="yyyy-MM-dd"/>
		</td>
	</tr>
</table>

<!-- <div class="col-xs-12 col-sm-12"> -->
<%-- <a href="<%=request.getContextPath()%>/frontend/member/update_member_information.jsp"><button type="button" class="btn btn-primary btn-lg btn-block">編輯</button></a> --%>
<!-- <br> -->
<!-- </div> -->

</div>

</div>
</body>
</html>