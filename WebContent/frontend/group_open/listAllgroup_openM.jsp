<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.group_member.model.*"%>
<%
	Group_memberService group_memberSvc = new Group_memberService();
	List<Group_memberVO> list = group_memberSvc.getAll();
	pageContext.setAttribute("list",list);
%>
<jsp:useBean id="group_openSvc" scope="page" class="com.group_member.model.Group_memberService" />  <!-- id �ܼƦW�� -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>�Ҧ���ΤH���</title>
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
<table id="table-1">
	<tr><td>
		 <h3>�Ҧ����u��� - listAllgroup_openM.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/group_open"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>
<%-- ���~��C --%>
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
		<th>���u�s��</th>
		<th>���u�m�W</th>
		<th>¾��</th>
		<th>���Τ��</th>
		<th>�~��</th>
		<th>����</th>
		<th>����</th>
		<th>�ק�</th>
		<th>�R��</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="group_memberVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr ${(group_memberVO.membe_no==param.member_no) ? 'bgcolor=#CCCCFF':''}><!--�N�ק諸���@���[�J����Ӥw-->
		<td>${group_memberVO.member_no}</td>
		<td>${group_memberVO.group_no}</td>
		<td>${group_memberVO.join_time}</td>
		<td>${group_memberVO.product_quantity}</td>
		<td>${group_memberVO.pay_status}</td>
		<td>${group_memberVO.group_member_status}</td>
		<td>${group_memberVO.log_out_reason}</td>
		<td>${group_memberVO.order_phone}</td>
		<td>${group_memberVO.pay_method}</td>
		<td><c:forEach var="group_openVO" items="${group_openSvc.all}">
                    <c:if test="${empVO.deptno==deptVO.deptno}">
	                    ${group_memberVO.member_no}�i${group_openVO.group_name}�j
                    </c:if>
                </c:forEach>
		</td>
		<td>
			 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/group_member/group_member.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�ק�"> 
			     <input type="hidden" name="member_no"      value="${group_memberVO.member_no}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller-->
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--�e�X��e�O�ĴX����Controller-->
			     <input type="hidden" name="action"	    value="getOne_For_Update">
			 </FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/group_member/group_member.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
			     <input type="hidden" name="member_no"      value="${group_memberVO.member_no}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller-->
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--�e�X��e�O�ĴX����Controller-->
			     <input type="hidden" name="action"     value="delete"></FORM>
			</td>
		</tr>	
	</c:forEach>
</table>
<%@ include file="page2.file" %> <!--  -->
<br>�����������|:<br><b>
   <font color=blue>request.getServletPath():</font> <%=request.getServletPath()%><br>
   <font color=blue>request.getRequestURI(): </font> <%=request.getRequestURI()%> </b>



</body>
</html>