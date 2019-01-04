<%@page import="com.news_classification.model.*"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	NewsClassificationVO newsClass = (NewsClassificationVO) request.getAttribute("newsClass");
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>公告分類資料修改</title>
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

<div class="container table-responsive-md">
<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="newsClassification.do" name="form1">
<table class="table">
	<tr>
		<td>公告分類編號代碼</td>
		<td><input type="TEXT" name="newsClassificationNo" size="45" value="<%=newsClass.getNewsClassificationNo()%>" /></td>
	</tr>
	<tr>
		<td>公告分類內容描述</td>
		<td><input type="TEXT" name="newsClassification" size="45" value="<%=newsClass.getNewsClassification()%>" /></td>
	</tr>
</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="newsClassificationNo" value="<%=newsClass.getNewsClassificationNo()%>">
<input type="submit" value="送出修改"></FORM>
<div class="col-xs-12 col-sm-12">
<a href="select_page.jsp"><button type="button" class="btn btn-primary btn-lg btn-block">返回</button></a>
<br>
</div>
</div>
</body>
</html>