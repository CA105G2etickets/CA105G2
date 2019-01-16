<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.group_open.model.*"%>

<% 
		Group_openService grpSvc = new Group_openService();
		List <Group_openVO> list = grpSvc.getAll();
		pageContext.setAttribute("list", list);
%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>所有開團資料</title>
<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	
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
  .img{
     width: 100px;
     height:100px;
}
</style>

</head>
<body bgcolor = 'white'>
<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有開團資料 - listAllgroup_open.jsp</h3>
		 <h4><a href="group_open.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table >
	<tr>
		<th>開團編號</th>
		<th>會員編號</th>
		<th>商品編號</th>
		<th>開團名稱</th>
		<th>成團下限</th>
		<th>開團介紹</th>
		<th>注意事項</th>
		<th>開團時間</th>
		<th>結束時間</th>
		<th>開團Banner1</th>
		<th>開團Banner2</th>
		<th>開團狀態</th>
		<th>開團地址</th>
		<th>開團緯度</th>
		<th>開團經度</th>
		<th>面交時間</th>
		<th>商品折扣後價格</th>
	</tr>
	<%@ include file="page1.file" %> 
 <c:forEach var="group_openVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>"> 
	<tr>
		<td>${group_openVO.group_no}</td>
	    <td>${group_openVO.member_no}</td>
	    <td>${group_openVO.goods_no}</td>
	    <td>${group_openVO.group_name}</td>
	    <td>${group_openVO.group_limit}</td>
	    <td width="70%">${group_openVO.group_introduction}</td>
	    <td>${group_openVO.group_mind}</td>
	    <td>${group_openVO.group_start_date}</td>
		<td>${group_openVO.group_close_date}</td>
	    <td><img src ="<%=request.getContextPath()%>/group_open/Group_openImg1.do?group_no=${group_openVO.group_no}" height="200" width="200"/></td>
	    <td><img src ="<%=request.getContextPath()%>/group_open/Group_openImg2.do?group_no=${group_openVO.group_no}" height="200" width="200"/></td> 
	    <td>${group_openVO.group_status}</td>
	    <td>${group_openVO.group_address}</td>
	    <td>${group_openVO.latitude}</td>
	    <td>${group_openVO.longitude}</td>
	    <td>${group_openVO.group_time}</td>
	    <td>${group_openVO.group_price}</td><!-- 為何這邊的VO是小寫  -->
		<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/frontend/group_open/group_open.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="group_no"  value="${group_openVO.group_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update">
			  </FORM>
		</td>
		<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/group_open/group_open.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="group_no"  value="${group_openVO.group_no}">
			     <input type="hidden" name="action" value="delete">
			  </FORM>
			</td>	    
	</tr>
		</c:forEach>
</table>
<%@ include file="page2.file" %>



</body>
</html>