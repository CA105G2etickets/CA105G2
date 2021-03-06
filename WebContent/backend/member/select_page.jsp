<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>ETIckeTs後台 - 會員</title>
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
		<font size="6px">ETIckeTs會員管理頁面</font>
	</div>
</div>
<div class="container">
	<div class="col-xs-12 col-sm-12">
		<font size="5px">會員查詢</font>

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
				<FORM METHOD="post" ACTION="member.do">
					輸入會員編號 <input type="text" placeholder="例 : M000001" name="memberno">
					<input type="hidden" name="action" value="getOne_For_Display">
					<input type="submit" value="送出">
				</FORM>
				<a href='listAllMember.jsp'>列出所有會員</a>
			</font>

			<jsp:useBean id="memberservice" scope="page"
				class="com.member.model.MemberService" />

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
		<font size="5px">會員管理</font>
		<ul>
			<font size="4px"> <a href='addMember.jsp'>新增會員</a>
			</font>
		</ul>
	</div>
</div>
</body>
</html>