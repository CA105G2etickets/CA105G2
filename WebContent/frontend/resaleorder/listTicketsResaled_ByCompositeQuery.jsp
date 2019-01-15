<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.resaleorder.model.*"%>

<%-- �U�νƦX�d��-�i�ѫȤ��select_page.jsp�H�N�W�����Q�d�ߪ���� --%>
<%-- �����u�@���ƦX�d�߮ɤ����G�m�ߡA�i���ݭn�A�W�[�����B�e�X�ק�B�R�����\��--%>

<%-- <jsp:useBean id="listEmps_ByCompositeQuery" scope="request" type="java.util.List<EmpVO>" /> --%> <!-- ��EL����i�ٲ� -->
<%-- <jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" /> --%>                <!-- ��Hibernate���Ҧ���w�i�ٲ� -->

<%--maybe add javaBean here, add SeatingAreaH5 and Event_H5_Service --%>
<jsp:useBean id="eh5Svc" scope="page" class="com.event.model.Event_H5_Service" />
<jsp:useBean id="sh5Svc" scope="page" class="com.seating_area.model.SeatingArea_H5_Service" />

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

<h4>
���U�νƦX�d��  - �i�ѫȤ�� select_page.jsp �H�N�W�����Q�d�ߪ����<br>
�������u�@���ƦX�d�߮ɤ����G�m�ߡA�i���ݭn�A�W�[�����B�e�X�ק�B�R�����\��</h4>

<table>
	<tr>
		<th>�°⦹�����|��</th>
		<!-- <th>��a��X����������ɶ�</th> -->
		<th>�y��ϦW��</th>
		<th>�X�����</th>
		
		<%-- 
		<th>�쥻����</th>
		<th>�����s���쪺���ʭ���T</th>
		<th>�����s���쪺���ʦa�I</th>
		<th>�����s���쪺���ʪ��|��ɶ�</th>
		<th>�����c��I����</th>
		--%>
		<th>�ʶR</th>
		
	</tr>
	<c:forEach var="ticketVO" items="${listTicketsResaled_ByCompositeQuery}">
		<tr>
			<td>${ticketVO.member_no}</td>
			  
			<%-- <td><font color = orange>
				<c:forEach var="resaleorderVO" items="${ticketVO.resaleords}">
					<td>${resaleorderVO.resale_ord_createtime}</td> 
					maybe add if condition to get the latest created resaleord
				</c:forEach></font></td> --%>
				
			<td><font color = orange>
			${ticketVO.seatingarea_h5VO.ticarea_name}</font></td>
			
			<td>${ticketVO.ticket_resale_price}</td>
			
			<%-- <td><c:forEach var=seatingareaVO items="${sh5Svc.all}">
				<c:if test="${ticketVO.seatingarea_h5VO.ticarea_no==seatingareaVO.ticarea_no}">
					 ${seatingareaVO.tickettype_h5VO.tictype_price} 
				</c:if>
			</c:forEach></td> //failed, this should be error,cause the pattern is implemented by teacher's example
			�γ\�ڥ����ݭn��javabean���A����ݥέ�l�Ĥ@��VO�����~�ӫإh���O�itable����ƧY�i --%>
			
			
			
			<td>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/frontend/resaleorder/resaleorder.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�ʶR">
			     <input type="hidden" name="ticket_no"  value="${ticketVO.ticket_no}">
			     <input type="hidden" name="action"	value="getOne_To_Buy"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>

</body>
</html>