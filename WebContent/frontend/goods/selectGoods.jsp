<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page import="java.util.*"%>
<%@ page import="com.goods.model.*"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>所有商品</title>
    <!-- Basic -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <!-- datetimepicker -->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/vendor/datetimepicker/jquery.datetimepicker.css" />
	<style>
		.checkboxCusStyle {
			padding: 3px;
		    border: 1px solid transparent;
		    border-radius: 4px;
		    color: #31708f;
		    background-color: #d9edf7;
		    border-color: #bce8f1;
		    width: 80px;
		    text-align: center;
		}
		
		body{
			font-family:微軟正黑體!important;
		}
	</style>
</head>
<body>

 

	<jsp:include page="/frontend/navbar_front-end.jsp" flush="true" />




	<div class="container">
		<span class="text-danger">${goodsErrorMsgs.Exception}</span>
	</div>

    <div class="container">
		<form method="post" action="<%=request.getContextPath()%>/goods/GoodsServlet.do">
            <div class="form-group">
                <label for="goods_name">商品名稱</label>
                <input type="text" name="goods_name" id="goods_name" placeholder="請輸入商品名稱" class="form-control" value="">
            </div>
            <div class="row">
                <div class="col-xs-12 col-sm-6">
                    <div class="form-group">
                        <label for="launchdate">開始日期</label>
                        <input type="text" name="launchdate" id="launchdate" placeholder="請選擇開始日期" class="form-control" value="">
                    </div>
                </div>
                <div class="col-xs-12 col-sm-6">
                    <div class="form-group">
                        <label for="offdate">結束日期</label>
                        <input type="text" name="offdate" id="offdate" placeholder="請選擇結束日期" class="form-control" value="">
                    </div>
                </div>
            </div>
            <div class="row">
          
                <div class="col-xs-12 col-sm-12 col-md-1">
                    <div class="form-group text-right">
                    	<input type="hidden" name="action" value="listGoods_ByCompositeQuery">
                        <button type="submit" class="btn btn-primary" id="search">查詢</button>
                    </div>
                </div>
            </div>
		</form>
    </div>
    
    <jsp:include page="/frontend/goods/listGoods_ByCompositeQuery.jsp" flush="true" />
    
	<div class="container" style="margin-bottom:30px;"></div>
	
	

	<jsp:include page="/frontend/footer_front-end.jsp" flush="true" />


    
    <!-- Basic -->
    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <!-- datetimepicker -->
    <script src="<%=request.getContextPath()%>/vendor/datetimepicker/jquery.js"></script>
    <script src="<%=request.getContextPath()%>/vendor/datetimepicker/jquery.datetimepicker.full.js"></script>
    <!-- JavaScript in File -->
    <script src="<%=request.getContextPath()%>/frontend/goods/js/selectGoods.js"></script>

    
</body>
</html>