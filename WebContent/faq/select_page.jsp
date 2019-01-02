<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>ETIckeTs Faq</title>
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
		<font size="6px">ETIckeTse常見問題管理頁面</font>
	</div>
</div>
<div class="container">
	<div class="col-xs-12 col-sm-12">
		<font size="5px">常見問題查詢</font>

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
				<FORM METHOD="post" ACTION="faq.do">
					輸入常見問題編號 <input type="text" placeholder="例 : FAQ001" name="faq_no">
					<input type="hidden" name="action" value="getOne_For_Display">
					<input type="submit" value="送出">
				</FORM>
				<a href='listAllFaq.jsp'>列出所有常見問題</a>
			</font>

			<jsp:useBean id="FaqService" scope="page"
				class="com.faq.model.FaqService" />

			<!--   <li> -->
			<!--      <FORM METHOD="post" ACTION="faqClassification.do" > -->
			<!--        選擇常見問題編號 -->
			<!--        <select size="1" name="faqClassificationNo"> -->
			<%--          <c:forEach var="member" items="${MemberService.all}" >  --%>
			<%--           <option value="${member.memberno}">${member.memberno} --%>
			<%--          </c:forEach>    --%>
			<!--        </select> -->
			<!--        <input type="hidden" name="action" value="getOne_For_Display"> -->
			<!--        <input type="submit" value="送出"> -->
			<!--     </FORM> -->
			<!--   </li> -->

			<!--   <li> -->
			<!--      <FORM METHOD="post" ACTION="faqClassification.do" > -->
			<!--        <b>選擇員工姓名:</b> -->
			<!--        <select size="1" name="faqClassificationNo"> -->
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
		<font size="5px">常見問題管理</font>
		<ul>
			<font size="4px"> <a href='addFaq.jsp'>新增常見問題</a>
			</font>
		</ul>
	</div>
</div>
</body>
</html>