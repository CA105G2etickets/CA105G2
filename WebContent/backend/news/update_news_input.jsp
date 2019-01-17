<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.news.model.*"%>

<%
	NewsVO newsVO = (NewsVO) request.getAttribute("newsVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>ETIckeTs後台 - 公告資料修改</title>

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
</style>

</head>

<jsp:include page="/backend/navbar_back-end.jsp" flush="true"/> 

<div class="container table-responsive-md">
<h3 align="center">修改公告</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="news.do" name="form1">
<table class="table">
	<tr>
		<td>公告編號</td>
		<td><%=newsVO.getNews_no()%></td>
	</tr>
	<tr>
		<td>公告分類代碼</td>
		<td><input type="TEXT" name="news_classification_no" size="45" value="<%=newsVO.getNews_classification_no()%>" maxlength="1"></td>
	</tr>
	<tr>
		<td>標題</td>
		<td><input type="TEXT" name="news_title" size="45" value="<%=newsVO.getNews_title()%>" maxlength="20"></td>
	</tr>
	<tr>
		<td>內容</td>
		<td><input type="TEXT" name="news_content" value="<%=newsVO.getNews_content()%>" size="45"></td>
	</tr>
	<tr>
		<td>發布日期</td>
		<td><%=newsVO.getAnnounce_date()%></td>
	</tr>
	<tr>
		<td>發布管理員編號</td>
		<td><input type="TEXT" name="administrator_no" size="45" value="<%=newsVO.getAdministrator_no()%>" maxlength="4"></td>
	</tr>
</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="announce_date" value="<%=newsVO.getAnnounce_date()%>">
<input type="hidden" name="news_no" value="<%=newsVO.getNews_no()%>">
<input type="submit" value="送出修改"></FORM>
<div class="col-xs-12 col-sm-12">
<a href="select_page.jsp"><button type="button" class="btn btn-primary btn-lg btn-block">返回</button></a>
<br>
</div>
</div>
</body>
</html>