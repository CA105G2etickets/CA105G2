<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>ETIckeTs PermissionList</title>
</head>

<jsp:include page="/backend/navbar_back-end.jsp" flush="true"/>  

<%-- import進導覽列 --%>
<!-- <div>                    -->
<%-- 	<c:import url="/navbar_back-end.html" charEncoding="UTF-8"> --%>
<%-- 	</c:import> --%>
<!-- </div> -->
<%----%>

<div class="container">
	<div class="col-xs-12 col-sm-12">
		<font size="6px">ETIckeTse功能清單管理頁面</font>
	</div>
</div>
<div class="container">
	<div class="col-xs-12 col-sm-12">
		<font size="5px">功能清單分類查詢</font>

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

		<ul>
			<font size="4px">
				<FORM METHOD="post" ACTION="permissionList.do">
					輸入功能清單分類編號 <input type="text" placeholder="例 : PL01" name="permission_list_no">
					<input type="hidden" name="action" value="getOne_For_Display">
					<input type="submit" value="送出">
				</FORM>
				<a href='listAllPermissionList.jsp'>列出所有功能清單分類</a>
			</font>

			<jsp:useBean id="PermissionListService" scope="page"
				class="com.permission_list.model.PermissionListService" />

			<!--   <li> -->
			<!--      <FORM METHOD="post" ACTION="newsClassification.do" > -->
			<!--        選擇功能清單分類編號 -->
			<!--        <select size="1" name="newsClassificationNo"> -->
			<%--          <c:forEach var="member" items="${MemberService.all}" >  --%>
			<%--           <option value="${member.memberno}">${member.memberno} --%>
			<%--          </c:forEach>    --%>
			<!--        </select> -->
			<!--        <input type="hidden" name="action" value="getOne_For_Display"> -->
			<!--        <input type="submit" value="送出"> -->
			<!--     </FORM> -->
			<!--   </li> -->

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
		<font size="5px">功能清單分類管理</font>
		<ul>
			<font size="4px"> <a href='addPermissionList.jsp'>新增功能清單分類</a>
			</font>
		</ul>
	</div>
</div>
</body>
</html>