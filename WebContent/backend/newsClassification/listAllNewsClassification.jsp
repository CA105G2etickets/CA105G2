<%@page import="com.news_classification.model.*"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	NewsClassificationService newsClassificationService = new NewsClassificationService();
	List<NewsClassificationVO> list = newsClassificationService.getAll();
	pageContext.setAttribute("list", list);
%>


<html>
<head>
<title>所有公告分類資料</title>

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
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

<%-- import進導覽列 --%>
<!-- <div>                    -->
<%-- 	<c:import url="/navbar_back-end.html" charEncoding="UTF-8"> --%>
<%-- 	</c:import> --%>
<!-- </div> -->
<%----%>

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
		<th>公告分類編號代碼</th>
		<th>公告分類內容描述</th>
	</tr>
	<%@ include file="page1.file"%>
	<c:forEach var="newsClassification" items="${list}" begin="<%=pageIndex%>"
		end="<%=pageIndex+rowsPerPage-1%>">

		<tr>
			<td>${newsClassification.newsClassificationNo}</td>
			<td>${newsClassification.newsClassification}</td>
			<td>
				<FORM METHOD="post"
					ACTION="newsClassification.do"
					style="margin-bottom: 0px;">
					<input type="submit" value="修改"> <input type="hidden"
						name="newsClassificationNo" value="${newsClassification.newsClassificationNo}"> <input
						type="hidden" name="action" value="getOne_For_Update">
				</FORM>
			</td>
<!-- 			<td> -->
<!-- 				<FORM METHOD="post" -->
<%-- 					ACTION="<%=request.getContextPath()%>/newsClassification/newsClassification.do" --%>
<!-- 					style="margin-bottom: 0px;"> -->
<!-- 					<input type="submit" value="刪除"> <input type="hidden" -->
<%-- 						name="newsClassificationNo" value="${newsClass.newsClassificationNo}"> <input --%>
<!-- 						type="hidden" name="action" value="delete"> -->
<!-- 				</FORM> -->
<!-- 			</td> -->
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