<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.group_open.model.*"%>

<%

	Group_openVO group_openVO = (Group_openVO)request.getAttribute("group_openVO"); //取得查詢完成的VO 從servlet過來的VO
	pageContext.setAttribute("group_openVO", group_openVO);

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>開團資料 - listOnegroup_open.jsp</title>

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
	width:100%; 
	
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
<body bgcolor='white'>

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>開團資料 - listOnegroup_open.jsp</h3>
		 <h4><a href="group_open.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
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
	    <td><img src ="<%=request.getContextPath()%>/group_open/Group_openImg1.do?group_no=${group_openVO.group_no}"/></td>
	    <td><img src ="<%=request.getContextPath()%>/group_open/Group_openImg2.do?group_no=${group_openVO.group_no}"/></td> 
	    <td>${group_openVO.group_status}</td>
	    <td>${group_openVO.group_address}</td>
	    <td>${group_openVO.latitude}</td>
	    <td>${group_openVO.longitude}</td>
	    <td>${group_openVO.group_time}</td>
	    <td>${group_openVO.group_price}</td><!-- 為何這邊的VO是小寫  -->
	</tr>

</body>
</html>