<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>ETIckeTs後台 - 常見問題管理</title>
</head>

<jsp:include page="/backend/navbar_back-end.jsp" flush="true"/> 

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
		<jsp:useBean id="faqservice" scope="page" class="com.faq.model.FaqService" />	
			<font size="4px">
				<FORM METHOD="post" ACTION="faq.do">選擇常見問題編號
					<select name="faq_no" id="faqlist">
			        	<c:forEach var="faq" items="${faqservice.all}" > 
			          		<option value="${faq.faq_no}">${faq.faq_no}
			         	</c:forEach>
			       	</select>
			       	<input type="hidden" name="action" value="getOne_For_Display">
					<input type="submit" value="送出">
			   	 </FORM>
				<a href='listAllFaq.jsp'>列出所有常見問題</a>
			</font>

			<jsp:useBean id="FaqService" scope="page"
				class="com.faq.model.FaqService" />

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