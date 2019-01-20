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
		<td><img src="<%=request.getContextPath()%>/administrator/administratorImg.do?administrator_no=${administratorVO.administrator_no}" height="50" width="50"></td>
		<td><%=administratorVO.getAdministrator_status()%></td>
		<td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/administrator/administrator.do" style="margin-bottom: 0px;">
				<input type="submit" value="修改">
				<input type="hidden" name="administrator_no" value="${administrator.administrator_no}">
				<input type="hidden" name="action" value="getOne_For_Update">
			</FORM>
		</td>
<!-- 		<td> -->
<!-- 			<FORM METHOD="post" ACTION="administrator.do" style="margin-bottom: 0px;"> -->
<!-- 				<input type="submit" value="刪除"> -->
<%-- 				<input type="hidden" name="administrator_no" value="${administrator.administrator_no}"> --%>
<!-- 				<input type="hidden" name="action" value="delete"> -->
<!-- 			</FORM> -->
<!-- 		</td> -->
	</tr>
</table>
<div class="col-xs-12 col-sm-12">
<a href="<%=request.getContextPath()%>/backend/administrator/allAdministrator.jsp"><button type="button" class="btn btn-primary btn-lg btn-block">返回</button></a>
<br>
</div>

</div>
</div>
</body>
</html>