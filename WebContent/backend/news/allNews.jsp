<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@page import="com.news.model.*"%>

<%
	NewsService newsService = new NewsService();
	List<NewsVO> list = newsService.getAll();
	pageContext.setAttribute("list", list);
%>

<html>
<head>
<title>ETIckeTs後台 - 查詢公告</title>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

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

<c:if test="${not empty errorMsgs}">
	<font style="color: red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color: red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<div class="container table-responsive-md">
<div class="row">

<jsp:useBean id="newsservice" scope="page" class="com.news.model.NewsService" />
<font size="4px">
	<FORM METHOD="post" ACTION="news.do">選擇公告編號
		<select name="news_no" id="newslist">
			<c:forEach var="news" items="${newsservice.all}" > 
				<option value="${news.news_no}">${news.news_no}
			</c:forEach>
		</select>
		<input type="hidden" name="action" value="getOne_For_Display">
		<input type="submit" value="送出">
	</FORM>
</font>

<table class="table">
	<tr>
		<th>公告編號</th>
		<th>公告分類代碼</th>
		<th>標題</th>
		<th>內容</th>
		<th>發布日期</th>
		<th>發布管理員編號</th>
	</tr>
	<%@ include file="page1.file"%>
	<c:forEach var="news" items="${list}" begin="<%=pageIndex%>"
		end="<%=pageIndex+rowsPerPage-1%>">

		<tr>
			<td>${news.news_no}</td>
			<td>${news.news_classification_no}</td>
			<td>${news.news_title}</td>
			<td>${news.news_content}</td>
			<td>${news.announce_date}</td>
			<td>${news.administrator_no}</td>
			<td>
				<FORM METHOD="post" ACTION="news.do" style="margin-bottom: 0px;">
					<input type="submit" value="修改">
					<input type="hidden" name="news_no" value="${news.news_no}">
					<input type="hidden" name="action" value="getOne_For_Update">
				</FORM>
			</td>
			<td>
				<FORM METHOD="post" ACTION="news.do" style="margin-bottom: 0px;">
					<input type="submit" value="刪除">
					<input type="hidden" name="news_no" value="${news.news_no}">
					<input type="hidden" name="action" value="delete">
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