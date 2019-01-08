<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.faq.model.*"%>

<html>
<head>
<title>ETIckeTs - 常見問題</title>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script>
	$(document).ready(function() {
		$(".faqClass").hover(function() {
			$(this).css("text-decoration","none");
		});
		$("center").click(function() {
			$(".faq").toggle(1000);
		});
		$("center").hover(function() {
			$(this).css("color","#3399ff");
		},function() {
			$(this).css("color","");
		});
		$(".question").click(function() {
			$(".answer").hide("slow"),
			$(this).next().toggle("slow");
		});
		$(".question").hover(function() {
			$(this).css("color","#3399ff");
			$(this).css("text-decoration","none");
		});
	});
</script>
<style>
.question {
 color: #3399ff;
 font-weight: bold;
}
.faqClass {
 font-weight: bold;
}
</style>
</head>

<jsp:include page="/frontend/navbar_front-end.html" flush="true"/>  

<jsp:useBean id="faqService" scope="page" class="com.faq.model.FaqService" />

	<div class="container">
	<div class="row">
	<c:forEach var="faqService" items="${faqService.allForNotRepeat}">
	<div class="col-xs-12 col-sm-6 col-md-4 col-lg-2 nav nav-pills">
		<center><a href="#" class="faqClass">
				<font size="5" face="DFKai-sb">
				<div>${faqService}</div>
				</font>
				<font size="4">
				<i class="glyphicon glyphicon-menu-down"></i>
				</font>
		</a></center>
	</div>
	</c:forEach>
	<div class="container"></div>
	<br>
	<br>
	<br>
<%
	FaqService faqService1 = new FaqService();
	List<FaqVO> list = faqService1.getAll();
	pageContext.setAttribute("list", list);
%>
	<div class="faq">
	<c:forEach var="faq" items="${list}">
	<table class="table">
	<div class="container" style="background-color:#F8F8F8">
				<font size="4" face="DFKai-sb">
				<ul>
				<a href="#faq" class="question"><li>${faq.question}</li></a>
				<span class="answer" style="display:none">${faq.answer}</span>
				</ul>
				</font>
	</div>
	</table>
	</c:forEach>
	</div>
</div>
</div>
</body>
<jsp:include page="/frontend/footer_front-end.html" flush="true"/> 
</html>