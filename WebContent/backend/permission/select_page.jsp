<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>ETIckeTs Permission</title>
</head>

<jsp:include page="/backend/navbar_back-end.html" flush="true"/> 

<div class="container">
	<div class="col-xs-12 col-sm-12">
		<font size="6px">ETIckeTse權限管理頁面</font>
	</div>
</div>
<div class="container">
	<div class="col-xs-12 col-sm-12">
		<font size="5px">管理員權限清單查詢</font>

		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<br>
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<p style="color: red">${message}</p>
				</c:forEach>
			</ul>
		</c:if>
		
		<jsp:useBean id="PermissionService" scope="page" class="com.permission.model.PermissionService"></jsp:useBean>

		<ul>
			<font size="4px">
				<FORM METHOD="post" ACTION="permission.do">
					選擇權限編號 
					<select size="1" name="permission_list_no">
			         <c:forEach var="permissionVO" items="${PermissionService.all}" > 
			          <option value="${permissionVO.permission_list_no}">${permissionVO.permission_list_no}
			         </c:forEach>   
			       </select>
			       <input type="hidden" name="action" value="getOne_For_Display_By_Permission_List_No">
			       <input type="submit" value="送出">
				</FORM>
				<FORM METHOD="post" ACTION="permission.do">
					選擇管理員編號
					<select size="1" name="administrator_no">
			         <c:forEach var="permissionVO" items="${PermissionService.all}" > 
			          <option value="${permissionVO.administrator_no}">${permissionVO.administrator_no}
			         </c:forEach>   
			       </select>
			       <input type="hidden" name="action" value="getOne_For_Display_By_Administrator_No">
			       <input type="submit" value="送出">
				</FORM>
				<a href='listAllPermission.jsp'>列出所有權限清單</a>
			</font>

			<jsp:useBean id="PermissionListService" scope="page"
				class="com.permission_list.model.PermissionListService" />

<!-- 			<font size="4px"> -->
<!-- 			     <FORM METHOD="post" ACTION="permission.do" > -->
<%-- 			       選擇功能清單編號${Permissionlist.permission_list_no} --%>
<!-- 			       <select size="1" name="permission_list_no"> -->
<%-- 			         <c:forEach var="permissionList" items="${PermissionListService.all}" >  --%>
<%-- 			          <option value="${permission_list.permission_list_no}">${permission_list.permission_list_no} --%>
<%-- 			         </c:forEach>    --%>
<!-- 			       </select> -->
<!-- 			       <input type="hidden" name="action" value="getOne_For_Display"> -->
<!-- 			       <input type="submit" value="送出"> -->
<!-- 			    </FORM> -->
<!-- 			</font> -->

			<!--   <li> -->
			<!--      <FORM METHOD="post" ACTION="newsClassification.do" > -->
			<!--        <b>選擇員工姓名:</b> -->
			<!--        <select size="1" name="newsClassificationNo"> -->
			<%--          <c:forEach var="member" items="${MemberService.all}" >  --%>
			<%--           <option value="${member.memberno}">${member.memberno} --%>
			<%--          </c:forEach>    --%>
			<!--        </select> -->
			<!--        <input type="hidden" name="action" value="getOne_For_Display"> -->
			<!--        <input type="submit" value="送出"> -->
			<!--      </FORM> -->
			<!--   </li> -->
		</ul>
	</div>
</div>
<div class="container">
	<div class="col-xs-12 col-sm-12">
		<font size="5px">權限清單管理</font>
		<ul>
			<font size="4px"> <a href='addPermission.jsp'>新增管理員權限</a>
			</font>
		</ul>
	</div>
</div>
</body>
</html>