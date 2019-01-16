<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<html>
<head>
<title>ETIckeTs - 個人電子錢包</title>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script>
	$(document).ready(function() {
		$(".wallethref").hover(function() {
			$(this).css("text-decoration","none");
		});
	});
</script>
<style>
.wallethref {
	color: black;
	font-weight: bold;
}
body{
	font-family:微軟正黑體!important;
}
.walletcolumn {
    display: inline-block;
    margin-right: 15px;
    margin-bottom: 5px;
    vertical-align: top;
}
.walletcolumn-2 {
    width: calc((100% - 19px * 3) / 2);
}
.wallettabs {
    position: relative;
    cursor: pointer;
}
.walletblock {
    position: relative;
    background-color: #f6f6f6;
    margin-bottom: 24px;
    padding: 50px;
}
.walletblock.walletblock-m {
    min-height: 50px;
    padding: 10px 10px;
}
.walletimage {
    display:block;
    margin:auto;
    width: 80px;
    height: 80px;
}
</style>
</head>

<body>

<jsp:include page="/frontend/navbar_front-end.jsp" flush="true"/>

<div class="container">
<div class="row">
	<div class="walletcolumn walletcolumn-2">
		<div class="wallettabs">
		<a href="deposit.jsp" class="wallethref">
			<div class="walletblock walletblock-m">
				<img src="<%=request.getContextPath()%>/frontend/ewallet/images/儲值.png" class="walletimage">
				<h3 class="heading" align="center">儲值</h3>
			</div>
		</a>
		</div>
	</div>
	<div class="walletcolumn walletcolumn-2">
		<div class="wallettabs">
		<a href="withdrawal.jsp" class="wallethref">
			<div class="walletblock walletblock-m">
				<img src="<%=request.getContextPath()%>/frontend/ewallet/images/提領.png" class="walletimage">
				<h3 class="heading" align="center">提領</h3>
			</div>
		</a>
		</div>
	</div>
</div>
</div>

</body>
<jsp:include page="/frontend/footer_front-end.jsp" flush="true"/> 
</html>