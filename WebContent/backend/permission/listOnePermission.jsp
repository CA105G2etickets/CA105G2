<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.permission.model.*"%>

<%
	PermissionVO permissionVO = (PermissionVO) request.getAttribute("permissionVO");
%>

<html>
<head>
<title>管理員權限資料</title>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">

</head>

<jsp:include page="/backend/navbar_back-end.jsp" flush="true"/> 

<div class="container table-responsive-md">
<table class="table">
	<tr>
		<th>權限編號</th>
		<th>管理員編號</th>
	</tr>
	<tr>
		<td><%=permissionVO.getPermission_list_no()%></td>
		<td><%=permissionVO.getAdministrator_no()%></td>
	</tr>
</table>
<div class="col-xs-12 col-sm-12">
<a href="select_page.jsp"><button type="button" class="btn btn-primary btn-lg btn-block">返回</button></a>
<br>
</div>
</div>
</body>
</html>