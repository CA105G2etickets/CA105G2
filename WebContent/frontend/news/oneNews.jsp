<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="com.news.model.*"%>

<%
	NewsVO newsVO = (NewsVO) request.getAttribute("newsVO");
%>

<html>
<head>
<title>ETIckeTs - 公告詳情</title>

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
  .oneNewsHead {
    width: 100px;
  }
</style>

</head>

<jsp:include page="/frontend/navbar_front-end.jsp" flush="true"/>

<div class="container table-responsive-md">

<table class="table">
	<tr>
		<th class="oneNewsHead">標題</th>
		<td><%=newsVO.getNews_title()%></td>
	</tr>
	<tr>
		<th class="oneNewsHead">內容</th>
		<td><%=newsVO.getNews_content()%></td>
	</tr>
	<tr>
		<th class="oneNewsHead">發布日期</th>
		<td><%=newsVO.getAnnounce_date()%></td>
	</tr>
</table>

</div>
</body>
<jsp:include page="/frontend/footer_front-end.jsp" flush="true"/> 
</html>