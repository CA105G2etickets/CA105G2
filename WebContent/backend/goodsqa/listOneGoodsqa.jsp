<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.goods_qa.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
  GoodsQaVO goodsqaVO = (GoodsQaVO) request.getAttribute("goodsQaVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
%>

<html>
<head>
<title>���u��� - listOneEmp.jsp</title>

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

<h4>�����Ƚm�߱ĥ� Script ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>���u��� - ListOneEmp.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/backend/goodsqa/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>���D�s��</th>
		<th>�ӫ~�s��</th>
		<th>�|���s��</th>
		<th>�޲z���s��</th>
		<th>�o�ݤ��e</th>
		<th>�^�����e</th>
		<th>�o�ݮɶ�</th>
		<th>�^���ɶ�</th>
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