<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>ETIckeTs Administrator</title>
</head>

<jsp:include page="/navbar_back-end.html" flush="true"/> 

<%-- import進導覽列 --%>
<!-- <div>                    -->
<%-- 	<c:import url="/navbar_back-end.html" charEncoding="UTF-8"> --%>
<%-- 	</c:import> --%>
<!-- </div> -->
<%----%>

<div class="container">
	<div class="col-xs-12 col-sm-12">
		<font size="6px">ETIckeTs管理員管理頁面</font>
	</div>
</div>
<div class="container">
	<div class="col-xs-12 col-sm-12">
		<font size="5px">管理員查詢</font>

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
				<FORM METHOD="post" ACTION="administrator.do">
					輸入管理員編號 <input type="text" placeholder="例 : A001" name="administrator_no">
					<input type="hidden" name="action" value="getOne_For_Display">
					<input type="submit" value="送出">
				</FORM>
				<a href='listAllAdministrator.jsp'>列出所有管理員</a>
			</font>

			<jsp:useBean id="AdministratorService" scope="page"
				class="com.administrator.model.AdministratorService" />

			<!--   <li> -->
			<!--      <FORM METHOD="post" ACTION="member.do" > -->
			<!--        選擇會員編號 -->
			<!--        <select size="1" name="memberno"> -->
			<%--          <c:forEach var="member" items="${MemberService.all}" >  --%>
			<%--           <option value="${member.memberno}">${member.memberno} --%>
			<%--          </c:forEach>    --%>
			<!--        </select> -->
			<!--        <input type="hidden" name="action" value="getOne_For_Display"> -->
			<!--        <input type="submit" value="送出"> -->
			<!--     </FORM> -->
			<!--   </li> -->

			<!--   <li> -->
			<!--      <FORM METHOD="post" ACTION="member.do" > -->
			<!--        <b>選擇員工姓名:</b> -->
			<!--        <select size="1" name="memberno"> -->
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
		<font size="5px">管理員管理</font>
		<ul>
			<font size="4px"> <a href='addAdministrator.jsp'>新增管理員</a>
			</font>
		</ul>
	</div>
</div>
</body>
</html>