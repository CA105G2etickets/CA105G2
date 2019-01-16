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
<title>ETIckeTs - 所有公告</title>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>

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
.newstag {
    background-color: #3399ff;
    color: #fff;
    padding: 3px 10px;
    font-size: 12px;
    border-radius: 5px;
    vertical-align: middle;
    display: inline-block;
}
.allnewshr {
	margin-top: 20px;
    margin-bottom: 20px;
    border: 0;
    border-top: 1px solid #eee;
    height: 0;
    box-sizing: content-box;
    display: block;
    unicode-bidi: isolate;
    margin-block-start: 0.5em;
    margin-block-end: 0.5em;
    margin-inline-start: auto;
    margin-inline-end: auto;
    overflow: hidden;
}
</style>

</head>

<jsp:include page="/frontend/navbar_front-end.jsp" flush="true"/>

<div class="container">

<jsp:useBean id="newsClassification" scope="page" class="com.news_classification.model.NewsClassificationService" />

	<c:forEach var="news" items="${list}">
<font size="3" style="font-weight:bold">
		<ul>
			<font class="newstag">
				${newsClassification.getOneNewsClassification(news.news_classification_no).newsClassification}
			</font>
				<a href="<%=request.getContextPath()%>/frontend/news/news.do?action=getOne_For_Display_front&news_no=${news.news_no}" class="newsTitle">${news.news_title}</a>
		</ul>
</font>
		<ul>
			<font size="2" style="cursor: none;color: #6c6c6c">${news.announce_date}</font>
		</ul>
		<hr class="allnewshr">
	</c:forEach>
</div>

<script>
$(document).ready(function() {
		$(".newsTitle").hover(function() {
			$(this).css("text-decoration","none");
		});
});
</script>

</body>
<jsp:include page="/frontend/footer_front-end.jsp" flush="true"/> 
</html>