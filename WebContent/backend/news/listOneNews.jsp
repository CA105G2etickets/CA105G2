<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.news.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	NewsVO newsVO = (NewsVO) request.getAttribute("newsVO");
%>

<html>
<head>
<title>公告資料</title>

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

<jsp:include page="/backend/navbar_back-end.html" flush="true"/> 

<%-- import進導覽列 --%>
<!-- <div>                    -->
<%-- 	<c:import url="/navbar_back-end.html" charEncoding="UTF-8"> --%>
<%-- 	</c:import> --%>
<!-- </div> -->
<%----%>

<table class="table">
	<tr>
		<th>公告編號</th>
		<th>公告分類代碼</th>
		<th>標題</th>
		<th>內容</th>
		<th>發布日期</th>
		<th>發布管理員編號</th>
	</tr>
	<tr>
		<td><%=newsVO.getNews_no()%></td>
		<td><%=newsVO.getNews_classification_no()%></td>
		<td><%=newsVO.getNews_title()%></td>
		<td><%=newsVO.getNews_content()%></td>
		<td><%=newsVO.getAnnounce_date()%></td>
		<td><%=newsVO.getAdministrator_no()%></td>
	</tr>
</table>
<div class="col-xs-12 col-sm-12">
<a href="select_page.jsp"><button type="button" class="btn btn-primary btn-lg btn-block">返回</button></a>
<br>
</div>
</body>
</html>