<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.news_classification.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	NewsClassificationVO newsClass = (NewsClassificationVO) request.getAttribute("newsClass");
%>

<html>
<head>
<title>公告分類資料</title>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
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

<%-- import進導覽列 --%>
<div>                   
	<c:import url="/navbar_back-end.html" charEncoding="UTF-8">
	</c:import>
</div>
<%----%>

<table class="table">
	<tr>
		<th>公告分類編號代碼</th>
		<th>公告分類內容描述</th>
	</tr>
	<tr>
		<td><%=newsClass.getNewsClassificationNo()%></td>
		<td><%=newsClass.getNewsClassification()%></td>
	</tr>
</table>
<div class="col-xs-12 col-sm-12">
<a href="select_page.jsp"><button type="button" class="btn btn-primary btn-lg btn-block">返回</button></a>
<br>
</div>
</body>
</html>