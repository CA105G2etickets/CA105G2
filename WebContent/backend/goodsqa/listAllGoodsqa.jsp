<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.goods_qa.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
    GoodsQaService goodsqaSvc = new GoodsQaService();
    List<GoodsQaVO> list = goodsqaSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>�Ҧ����u��� - listAllEmp.jsp</title>

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
	width: 800px;
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

<h4>�����m�߱ĥ� EL ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>�Ҧ����u��� - listAllEmp.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<%-- ���~���C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

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
		<th>�ק�</th>
		<th>�R��</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="goodsqaVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${goodsqaVO.gfaq_no}</td>
			<td>${goodsqaVO.goods_no}</td>
			<td>${goodsqaVO.member_no}</td>
			<td>${goodsqaVO.administrator_no}</td>
			<td>${goodsqaVO.questions_content}</td>
			<td>${goodsqaVO.answer_content}</td> 
			<td>${goodsqaVO.questions_date}</td>
			<td>${goodsqaVO.answer_date}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/goodsqa/GoodsQaServlet.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="gfaq_no"  value="${goodsqaVO.gfaq_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/goodsqa/GoodsQaServlet.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
			     <input type="hidden" name=gfaq_no  value="${goodsqaVO.gfaq_no}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>