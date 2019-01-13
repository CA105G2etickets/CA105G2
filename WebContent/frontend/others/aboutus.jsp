<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<html>
<head>
<title>ETIckeTs - 關於我們</title>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>

<style>
body{
	font-family:微軟正黑體!important;
}
</style>

</head>

<body>
<jsp:include page="/frontend/navbar_front-end.jsp" flush="true"/>  
<div class="container">
    <div class="container doc">
        <h1 class="content-title">關於ETIckeTs</h1>
        <h2>緣起</h2>
        <p>
        	音樂會、展覽、戲劇、舞蹈、演唱會、講座，活動的種類形形色色，而相關的網站更是五花八門，一個活動從釋出舉辦的消息、周邊商品的販售、票券的購買到揪團共襄盛舉乃至於臨時不克前往時需要的票券轉讓交易平台，我們運用實際參與活動的經驗與想像，打造了一個整合活動相關所需功能的網站，讓您在參加活動放鬆之餘能無後顧之憂的簡單搞定大小事!
			而如果您也有實體紙本票券不易保存且容易搞丟的煩惱，我們在手機APP做了虛擬化的票券系統，讓您的行動裝置成為您隨身的電子票夾!
		</p>
    </div>
</div>

<div class="service">
    <div class="container" align="right"><address>ETIckeTs娛樂</address></div>
</div>

</body>

<jsp:include page="/frontend/footer_front-end.jsp" flush="true"/> 
</html>