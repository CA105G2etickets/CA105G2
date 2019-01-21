<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.goods_qa.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  GoodsQaVO goodsqaVO = (GoodsQaVO) request.getAttribute("goodsQaVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>員工資料 - listOneEmp.jsp</title>

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
	width: 600px;
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
<body bgcolor='white'>

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>員工資料 - ListOneEmp.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/backend/goodsqa/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>問題編號</th>
		<th>商品編號</th>
		<th>會員編號</th>
		<th>管理員編號</th>
		<th>發問內容</th>
		<th>回答內容</th>
		<th>發問時間</th>
		<th>回答時間</th>
	</tr>
	<tr>
		<td><%=goodsqaVO.getGfaq_no()%></td>
		<td><%=goodsqaVO.getGoods_no()%></td>
		<td><%=goodsqaVO.getMember_no()%></td>
		<td><%=goodsqaVO.getAdministrator_no()%></td>
		<td><%=goodsqaVO.getQuestions_content()%></td>
		<td><%=goodsqaVO.getAnswer_content()%></td>
		<td><%=goodsqaVO.getQuestions_date()%></td>
		<td><%=goodsqaVO.getAnswer_date()%></td>
	</tr>
</table>

</body>
</html>