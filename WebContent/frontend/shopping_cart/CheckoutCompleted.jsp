<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*" %>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
	<title>ETIckeTs - 結帳完成</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<jsp:include page="/frontend/navbar_front-end.jsp" flush="true"/>
<style>
body{
	font-family:微軟正黑體!important;
}
</style>
<body>

<div class="container-fluid" style="margin-bottom: 400px">
    <div class="row">
        <div class="col-xs-12 col-sm-4"></div>
        <div class="col-xs-12 col-sm-4">
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="panel1">
                    <h4 class="panel-title">訂單完成</h4>
                </div>
                <div class="panel-body">
                   <h2 style="text-align:center">您已完成一筆訂單</h2>
                   <div style="text-align:center">
	                   <a href="<%=request.getContextPath()%>/order_history/OrderHistory.do?action=getOne_For_MemAllOrd_Front" class="btn btn-default"><b>查看訂單</b></a>
	                   <a href="<%=request.getContextPath()%>/frontend/index.jsp" class="btn btn-default"><b>返回首頁</b></a>
                   </div>
                </div>
            </div>

        </div>
    </div>
</div>


</center>
</body>
	<jsp:include page="/frontend/footer_front-end.jsp" flush="true"/> 
<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</html>
