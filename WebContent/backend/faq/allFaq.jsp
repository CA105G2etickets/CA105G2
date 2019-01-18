<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@page import="com.faq.model.*"%>

<%
	FaqService faqService = new FaqService();
	List<FaqVO> list = faqService.getAll();
	pageContext.setAttribute("list", list);
%>

<html>
<head>
<title>ETIckeTs後台 - 常見問題管理</title>

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
<div class="row">

<li>
	<font size="4px"> <a href='addFaq.jsp'>新增常見問題</a>
	</font>
</li>

<jsp:useBean id="faqservice" scope="page" class="com.faq.model.FaqService" />	
<font size="4px">
	<FORM METHOD="post" ACTION="faq.do">選擇常見問題編號
		<select name="faq_no" id="faqlist">
			   <c:forEach var="faq" items="${faqservice.all}" > 
			        <option value="${faq.faq_no}">${faq.faq_no}
			   </c:forEach>
			</select>
			<input type="hidden" name="action" value="getOne_For_Display">
		<input type="submit" value="送出">
	</FORM>
</font>

<table class="table">
	<tr>
		<th>常見問題編號</th>
		<th>問題</th>
		<th>答案</th>
		<th>常見問題分類</th>
	</tr>
	<%@ include file="page1.file"%>
	<c:forEach var="faq" items="${list}" begin="<%=pageIndex%>"
		end="<%=pageIndex+rowsPerPage-1%>">

		<tr>
			<td>${faq.faq_no}</td>
			<td>${faq.question}</td>
			<td>${faq.answer}</td>
			<td>${faq.faq_classification}</td>
			<td>
				<FORM METHOD="post"
					ACTION="faq.do"
					style="margin-bottom: 0px;">
					<input type="submit" value="修改"> <input type="hidden"
						name="faq_no" value="${faq.faq_no}"> <input
						type="hidden" name="action" value="getOne_For_Update">
				</FORM>
			</td>
			<td>
				<FORM METHOD="post"
					ACTION="faq.do"
					style="margin-bottom: 0px;">
					<input type="submit" value="刪除"> <input type="hidden"
						name="faq_no" value="${faq.faq_no}"> 
						<input type="hidden" name="action" value="delete">
				</FORM>
			</td>
		</tr>
	</c:forEach>
</table>
</div>
<%@ include file="page2.file"%>

</div>
</body>
</html>