<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">	
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
	<title>訂單查詢</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
</head>

<div><c:import url="/frontend/navbar_front-end.jsp" charEncoding="UTF-8"/></div>

<body>

	<div class="container-fluid" style="margin-bottom: 400px">
		<div class="row">
		<div class="col-xs-12 col-sm-3"></div>		
			<div class="col-xs-12 col-sm-6">
				<div clsss="container" style="margin-bottom: 400px">
					<div class="col-xs-12 col-sm-6">
						<form METHOD="post" ACTION="" >
							<input type="hidden" name="action" value="">
							<input type="submit" class="btn btn-default" href="" value="票卷訂單查詢"></a>
						</form>
					</div>
					<div class="col-xs-12 col-sm-6">
						<form METHOD="post" ACTION="<%=request.getContextPath()%>/order_history/OrderHistory.do" >
							<input type="hidden" name="action" value="getOne_For_MemAllOrd_Front">
							<input type="submit" class="btn btn-default" href="<%=request.getContextPath()%>/frontend/order_history/oneMemberIsOrder.jsp" value="商品訂單查詢"></a>
						</form>
					</div>	
				</div>
			</div>
		
	</div>

	<script src="https://code.jquery.com/jquery.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
<div><c:import url="/frontend/footer_front-end.jsp" charEncoding="UTF-8"/></div>
</html>