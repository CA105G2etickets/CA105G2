<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.resaleorder.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%-- �U�νƦX�d��-�i�ѫȤ��select_page.jsp�H�N�W�����Q�d�ߪ���� --%>
<%-- �����u�@���ƦX�d�߮ɤ����G�m�ߡA�i���ݭn�A�W�[�����B�e�X�ק�B�R�����\��--%>

<%-- <jsp:useBean id="listEmps_ByCompositeQuery" scope="request" type="java.util.List<EmpVO>" /> --%> <!-- ��EL����i�ٲ� -->
<%-- <jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" /> --%>                <!-- ��Hibernate���Ҧ���w�i�ٲ� -->

<%--maybe add javaBean here, add SeatingAreaH5 and Event_H5_Service --%>
<%-- <jsp:useBean id="eh5Svc" scope="page" class="com.event.model.Event_H5_Service" />
<jsp:useBean id="sh5Svc" scope="page" class="com.seating_area.model.SeatingArea_H5_Service" /> --%>

<%
List<ShowResaleTicketVO> listShow = (List<ShowResaleTicketVO>)request.getAttribute("listShow");
pageContext.setAttribute("listShow", listShow);
%>

<html>
<head><title>�ƦX�d�ߪ����G: - listSellingTickets_ByCompositeQuery.jsp</title>


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
<h3>���ʶR��Ⲽ���y�{���A���U�ʶR��Ⲽ�A�|���g���R�a�OM000002�A�󱱨���A�h�RM00001�b�L���C�X���魶���ҽ�X�����A�]���o���ʶR�y�{�C�@���ʶR���\��A�η�e��"�s���ڪ�����"�h�ݴN�|�֤@�i</h3>
<table>
	<tr>
		<th>����s��</th>
		<!-- <th>�c�檺�|���s��</th> -->
		<th>���</th>
		<!-- <th>�������檺�Ыخɶ�</th> -->
		<th>�y��ϦW��</th>
		<!-- <th>�y��Ϫ��C���X</th> -->
		
		<th>���ئW��</th>
		<th>���</th>
		
		<th>�����W��</th>
		<th>���a�W��</th>
		<th>���a�a�}</th>
		
		<th>���ʶ}�l���</th>
		
		<th>���ʥD�D�W��</th>
		<th>�ʶR</th>
	</tr> 
	<c:forEach var="ShowResaleTicketVO" items="${listShow}">
		
	<tr>
		<td>${ShowResaleTicketVO.ticket_no}</td>
		
		<td>${ShowResaleTicketVO.ticket_resale_price}</td>
		<td>${ShowResaleTicketVO.ticarea_name}</td>
		<td>${ShowResaleTicketVO.tictype_name}</td>
		<td>${ShowResaleTicketVO.tictype_price}</td>
		
		<td>${ShowResaleTicketVO.eve_sessionname}</td>
		<td>${ShowResaleTicketVO.venue_name}</td>
		<td>${ShowResaleTicketVO.address}</td>
		<td><fmt:formatDate value="${ShowResaleTicketVO.eve_startdate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		<td>${ShowResaleTicketVO.evetit_name}</td>
		<td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/frontend/resaleorder/resaleorder.do">
			<input type="submit" value="�ʶR">
			
			<input type="hidden" name="ticket_no"  value="${ShowResaleTicketVO.ticket_no}">
			<input type="hidden" name="ticket_resale_price"  value="${ShowResaleTicketVO.ticket_resale_price}">
			<input type="hidden" name="action" value="selected_targetResaleTicketToBuy"></FORM>
		</td>
	</tr>
	</c:forEach>
</table>

</body>
</html>