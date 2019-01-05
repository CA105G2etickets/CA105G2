<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.order_detail.model.*"%>

<%
List<OrderDetailVO> list = (List<OrderDetailVO>)request.getAttribute("orderDetailVO");
pageContext.setAttribute("list",list);
%>

<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<title>訂單紀錄新增</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
		<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
		
		<style>
			table {
	 			margin-top: 1px;
				margin-bottom: 1px;
				font-size: 10px;
			}

		</style>	
	<div><c:import url="/backend/navbar_back-end.html" charEncoding="UTF-8"/></div>
	</head>
	<body>
	
		<c:if test="${not empty errorMsgs}">
		<font style="color:red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color:red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>

		<div class="container-fluid">
			<div class="col-xs-12 col-sm-1"></div>
			<div class="row">
				<div class="col-xs-12 col-sm-10">
<!-- 					<h4><a href="select_page.jsp"><img src="images/LOGO1.png" width="70" height="50" border="0"><b>首頁</b></a></h4> -->
					<div class="panel panel-info">
						<div class="panel-heading">
				  		
					  		<h3 class="panel-title">訂單紀錄查詢</h3><%@ include file="pages/page1.file" %>
						</div>
						<table class="table table-bordered table-striped table-hover">
							<thead>
								<tr>
									<th>訂單編號</th>
									<th>商品編號</th>
									<th>實際交易單價</th>
									<th>商品數量</th>
									<th>修改</th>
									<th>刪除</th>
								</tr>
							</thead>
											
							<tbody>
								<c:forEach var="orderDetailVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
									<tr>
										<td>${orderDetailVO.order_no}</td>
										<td>${orderDetailVO.goods_no}</td>
										<td>${orderDetailVO.goods_bonus}</td>
										<td>${orderDetailVO.goods_pc}</td>
										<td>
										  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/order_detail/OrderDetail.do" style="margin-bottom: 0px;">
										     <input type="submit" value="修改" class="btn btn-warning">
										     <input type="hidden" name="order_no"  value="${orderDetailVO.order_no}">
										     <input type="hidden" name="goods_no"  value="${orderDetailVO.goods_no}">
										     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
										</td>
										<td>
										  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/order_detail/OrderDetail.do" style="margin-bottom: 0px;">
										     <input type="submit" value="刪除" class="btn btn-danger">
										     <input type="hidden" name="order_no"  value="${orderDetailVO.order_no}">
										     <input type="hidden" name="goods_no"  value="${orderDetailVO.goods_no}">
										     <input type="hidden" name="action" value="delete"></FORM>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<%@ include file="pages/page2.file" %>
				</div>
			</div>
		</div>
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	</body>
</html>