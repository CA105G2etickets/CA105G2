<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.faq.model.*"%>

<%
	FaqVO faqVO = (FaqVO) request.getAttribute("faqVO");
%>

<html>
<head>
<title>ETIckeTs後台 - 常見問題資料</title>

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
<div class="row">

<table class="table">
	<tr>
		<th>常見問題編號</th>
		<th>問題</th>
		<th>答案</th>
		<th>常見問題分類</th>
	</tr>
	<tr>
		<td><%=faqVO.getFaq_no()%></td>
		<td><%=faqVO.getQuestion()%></td>
		<td><%=faqVO.getAnswer()%></td>
		<td><%=faqVO.getFaq_classification()%></td>
		<td>
			<FORM METHOD="post" ACTION="faq.do" style="margin-bottom: 0px;">
				<input type="submit" value="修改"> <input type="hidden" name="faq_no" value="${faqVO.faq_no}">
				<input type="hidden" name="action" value="getOne_For_Update">
			</FORM>
		</td>
		<td>
			<FORM METHOD="post" ACTION="faq.do" style="margin-bottom: 0px;">
				<input type="submit" value="刪除"> <input type="hidden" name="faq_no" value="${faqVO.faq_no}"> 
				<input type="hidden" name="action" value="delete">
			</FORM>
		</td>
	</tr>
</table>
<div class="col-xs-12 col-sm-12">
<a href="<%=request.getContextPath()%>/backend/faq/allFaq.jsp"><button type="button" class="btn btn-primary btn-lg btn-block">返回</button></a>
<br>
</div>

</div>
</div>

</body>
</html>