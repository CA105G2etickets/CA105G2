<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">	
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
	<title>ETIckeTs - 訂單查詢</title>
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
body{
	font-family:微軟正黑體!important;
}
</style>
</head>

<body>

<jsp:include page="/frontend/navbar_front-end.jsp" flush="true"/>

<div class="container">
	<div class="walletcolumn walletcolumn-2">
		<div class="wallettabs">
		<a href="<%=request.getContextPath()%>/frontend/ticketorder/ticketorder.do?action=member_select_ticketorders" class="wallethref">
			<div class="walletblock walletblock-m">
				<img src="<%=request.getContextPath()%>/frontend/orderImages/訂票訂單.png" class="walletimage">
				<h3 class="heading" align="center">訂票訂單查詢</h3>
			</div>
		</a>
		</div>
	</div>
	<div class="walletcolumn walletcolumn-2">
		<div class="wallettabs">
		<a href="<%=request.getContextPath()%>/order_history/OrderHistory.do?action=getOne_For_MemAllOrd_Front" class="wallethref">
			<div class="walletblock walletblock-m">
				<img src="<%=request.getContextPath()%>/frontend/orderImages/商品訂單.png" class="walletimage">
				<h3 class="heading" align="center">商品訂單查詢</h3>
			</div>
		</a>
		</div>
	</div>
</div>

</body>
<jsp:include page="/frontend/footer_front-end.jsp" flush="true"/> 
</html>