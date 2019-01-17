<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.faq.model.*"%>

<%
	FaqVO faqVO = (FaqVO) request.getAttribute("faqVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>ETIckeTs後台 - 常見問題資料修改</title>

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
<h3 align="center">修改常見問題</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="faq.do" name="form1">
<table class="table">
	<tr>
		<td>常見問題編號</td>
		<td><%=faqVO.getFaq_no()%></td>
	</tr>
	<tr>
		<td>問題</td>
		<td><input type="TEXT" name="question" size="45" value="<%=faqVO.getQuestion()%>"></td>
	</tr>
	<tr>
		<td>答案</td>
		<td><input type="TEXT" name="answer" size="45" value="<%=faqVO.getAnswer()%>"></td>
	</tr>
	<tr>
		<td>常見問題分類</td>
		<td><input type="TEXT" name="faq_classification" value="<%=faqVO.getFaq_classification()%>" size="45" maxlength="6"></td>
	</tr>
</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="faq_no" value="<%=faqVO.getFaq_no()%>">
<input type="submit" value="送出修改"></FORM>
<div class="col-xs-12 col-sm-12">
<a href="select_page.jsp"><button type="button" class="btn btn-primary btn-lg btn-block">返回</button></a>
<br>
</div>
</div>
</body>
</html>