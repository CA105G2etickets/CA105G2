<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>ETIckeTs NewsClassification</title>
</head>

<%-- import進導覽列 --%>
<div>                   
	<c:import url="/navbar_back-end.html" charEncoding="UTF-8">
	</c:import>
</div>
<%----%>

<div class="container">
	<div class="col-xs-12 col-sm-12">
		<font size="6px">ETIckeTse公告分類管理頁面</font>
	</div>
</div>
<div class="container">
	<div class="col-xs-12 col-sm-12">
		<font size="5px">公告分類查詢</font>

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
<!-- 				<FORM METHOD="post" ACTION="newsClassification.do"> -->
<!-- 					輸入公告分類編號 <input type="text" placeholder="例 : S" name="newsClassificationNo"> -->
<!-- 					<input type="hidden" name="action" value="getOne_For_Display"> -->
<!-- 					<input type="submit" value="送出"> -->
<!-- 				</FORM> -->
				<a href='listAllNewsClassification.jsp'>列出所有公告分類</a>
			</font>

			<jsp:useBean id="NewsClassificationService" scope="page"
				class="com.news_classification.model.NewsClassificationService" />

			<!--   <li> -->
			<!--      <FORM METHOD="post" ACTION="newsClassification.do" > -->
			<!--        選擇公告分類編號 -->
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
		<font size="5px">公告分類管理</font>
		<ul>
			<font size="4px"> <a href='addNewsClassification.jsp'>新增公告分類</a>
			</font>
		</ul>
	</div>
</div>
</body>
</html>