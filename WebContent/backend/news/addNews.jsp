<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.news.model.*"%>
 
<%
	NewsVO newsVO = (NewsVO) request.getAttribute("newsVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>新增公告</title>

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
  img {
  border-radius: 90%;
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
<h3>新增公告資料:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="news.do" name="form1">
<table class="table">
	<tr>
		<td>公告分類代碼</td>
		<td><input type="TEXT" placeholder="N" name="news_classification_no" size="45" maxlength="1"></td>
	</tr>
	<tr>
		<td>標題</td>
		<td><input type="TEXT" placeholder="會員加入辦法"name="news_title" size="45" maxlength="20"></td>
	</tr>
	<tr>
		<td>內容</td>
		<td><input type="TEXT" placeholder="會員加入辦法如下......"name="news_content" size="45"></td>
	</tr>
	<tr>
		<td>發布管理員編號</td>
		<td><input type="TEXT" placeholder="A001"name="administrator_no" size="45" maxlength="4"></td>
	</tr>
</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增" >
</FORM>
<div class="col-xs-12 col-sm-12">
<a href="select_page.jsp"><button type="button" class="btn btn-primary btn-lg btn-block">返回</button></a>
<br>
</div>
</div>
</body>
</html>