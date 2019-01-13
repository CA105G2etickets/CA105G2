<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>ETIckeTs後台 - 公告管理</title>
</head>

<jsp:include page="/backend/navbar_back-end.jsp" flush="true"/> 

<div class="container">
	<div class="col-xs-12 col-sm-12">
		<font size="5px">公告查詢</font>

		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<br>
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<p style="color: red">${message}</p>
				</c:forEach>
			</ul>
		</c:if>

	<ul>
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
				<a href='listAllNews.jsp'>列出所有公告</a>
			</font>
	</ul>
	</div>
</div>
<div class="container">
	<div class="col-xs-12 col-sm-12">
		<font size="5px">公告管理</font>
		<ul>
			<font size="4px"> <a href='addNews.jsp'>新增公告</a>
			</font>
		</ul>
	</div>
</div>
</body>
</html>