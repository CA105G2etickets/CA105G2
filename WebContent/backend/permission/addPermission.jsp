<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.permission.model.*"%>
 
<%
	PermissionVO permissionVO = (PermissionVO) request.getAttribute("permissionVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>新增管理員權限</title>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">

</head>

<jsp:include page="/backend/navbar_back-end.jsp" flush="true"/>  

<div class="container table-responsive-md">
<h3>新增管理員權限資料:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="permission.do" name="form1">
<table class="table">
	<tr>
		<td>權限編號</td>
		<td><input type="TEXT" placeholder="PL01" name="permission_list_no" size="45" maxlength="4"></td>
	</tr>
	<tr>
		<td>管理員編號</td>
		<td><input type="TEXT" placeholder="A001" name="administrator_no" size="45" maxlength="4"></td>
	</tr>
</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增" >
</FORM>
<div class="col-xs-12 col-sm-12">
<a href="select_page.jsp"><button type="button" class="btn btn-primary btn-lg btn-block">返回</button></a>
<br>
</div>
</div>
</body>
</html>