<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@page import="com.permission.model.*"%>

<%
	PermissionService permissionService = new PermissionService();
	List<PermissionVO> list = permissionService.getAll();
	pageContext.setAttribute("list", list);
%>

<html>
<head>
<title>所有管理員權限清單資料</title>

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">

</head>

<jsp:include page="/backend/navbar_back-end.jsp" flush="true"/> 

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color: red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color: red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<div class="container table-responsive-md">
<table class="table">
	<tr>
		<th>權限編號</th>
		<th>管理員編號</th>
	</tr>
	<%@ include file="page1.file"%>
	<c:forEach var="permission" items="${list}" begin="<%=pageIndex%>"
		end="<%=pageIndex+rowsPerPage-1%>">

		<tr>
			<td>${permission.administrator_no}</td>
			<td>${permission.permission_list_no}</td>
			<td>
				<FORM METHOD="post"
					ACTION="permission.do"
					style="margin-bottom: 0px;">
					<input type="submit" value="修改"> <input type="hidden"
						name="administrator_no" value="${permission.administrator_no}"> <input
						type="hidden" name="action" value="getOne_For_Update">
				</FORM>
			</td>
			<td>
				<FORM METHOD="post"
					ACTION="permission.do"
					style="margin-bottom: 0px;">
					<input type="submit" value="刪除"> <input type="hidden"
						name="permission_list_no" value="${permission.permission_list_no}"> <input
						type="hidden" name="action" value="delete">
				</FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<div class="col-xs-12 col-sm-12">
<a href="select_page.jsp"><button type="button" class="btn btn-primary btn-lg btn-block">返回</button></a>
<br>
</div>
</div>
<%@ include file="page2.file"%>
</body>
</html>