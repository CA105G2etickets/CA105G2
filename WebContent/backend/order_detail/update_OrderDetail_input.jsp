<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.order_history.model.*"%>
<%@ page import="com.order_detail.model.*"%>
<%@ page import="com.goods.model.*"%>
<%
	OrderHistoryVO orderHistoryVO = (OrderHistoryVO) request.getAttribute("orderHistoryVO");
    OrderHistoryService orderHistorySvc = new OrderHistoryService();
    List<OrderHistoryVO> listHistory = orderHistorySvc.getAll();
    pageContext.setAttribute("listHistory",listHistory);
    
	OrderDetailVO orderDetailVO = (OrderDetailVO) request.getAttribute("orderDetailVO");
    OrderDetailService orderDetailSvc = new OrderDetailService();
    List<OrderDetailVO> listDetail = orderDetailSvc.getAll();
    pageContext.setAttribute("listDetail",listDetail);
%>
<jsp:useBean id="goodsSvc" scope="page" class="com.goods.model.GoodsService" />

<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">	
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
	<title>ETIckeTs - 訂單管理</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
</head>
<div><c:import url="/backend/navbar_back-end.jsp" charEncoding="UTF-8"/></div>

<body>

<div class="container-fluid">
	<div class="row">
		<div class="col-xs-12 col-sm-2"></div>
		<div class="col-xs-12 col-sm-8">
			<ol class="breadcrumb">
				<li>
					<a href="#">首頁</a>
				</li>
				<li>
					<a href="#">商品管理</a>
				</li>
				<li class="active">商品訂單管理</li>
			</ol>

			<div role="tabpanel">
			    <!-- 標籤面板：標籤區 -->
			    <ul class="nav nav-tabs" role="tablist">
			        <li role="presentation">
			            <a href="#history" aria-controls="history" role="tab" data-toggle="tab">訂單紀錄</a>
			        </li>
			        <li role="presentation" class="active">
			            <a href="#detail" aria-controls="detail" role="tab" data-toggle="tab">訂單明細</a>
			        </li>

			    </ul>
			
			    <!-- 標籤面板：內容區 -->
			    <div class="tab-content">
				<!-- 標籤面板：訂單紀錄 -->
			        <div role="tabpanel" class="tab-pane" id="history">
					
<!-- 						<br><input type="button" class="btn btn-primary" value="查詢全部" onclick="location.href='listAllOrderHistory.jsp'"> -->
<%-- 						<hr><input type="button" class="btn btn-primary" value="新增一筆訂單" onclick="location.href='<%=request.getContextPath()%>/backend/order_history/addOrderHistoryAndOrderDetail.jsp'"> --%>
						<hr>
				        <table id="example1" class="display" style="width:100%; font-size:8px">
				            <thead>
				                <tr>
				                    <th>訂單編號</th>
				                    <th>會員編號</th>
				                    <th>訂單總金額</th>
				                    <th>付款方式</th>
				                    <th>出貨方式</th>
				                    <th>訂購日期</th>
				                    <th>出貨日期</th>
				                    <th>取貨日期</th>
				                    <th>送貨地址</th>
				                    <th>收件人名稱</th>
				                    <th>收件人電話</th>
				                    <th>訂單狀態</th>
				                    <th>修改</th>
				                    <th>刪除</th>
				                </tr>
				            </thead>
				            <tbody>
				                <c:forEach var="orderHistoryVO" items="${listHistory}">                          
				                    <tr>
				                        <td>${orderHistoryVO.order_no}</td>
				                        <td>${orderHistoryVO.member_no}</td>
				                        <td>${orderHistoryVO.order_price}</td>
				                        <td>
				                            ${(orderHistoryVO.pay_methods == "CREDITCARD") ? '電子錢包' : '' }
				                            ${(orderHistoryVO.pay_methods == "EWALLET") ? '信用卡' : '' }
				                        </td>
				                        <td>
				                            ${(orderHistoryVO.shipping_methods == "STOREPICKUP") ? '超商取貨' : '' }
				                            ${(orderHistoryVO.shipping_methods == "HOMEDELIVERY") ? '宅配' : '' }
				                        </td>
				                        <td><fmt:formatDate value="${orderHistoryVO.order_date}" pattern="yyyy-MM-dd"/></td>
				                        <td><fmt:formatDate value="${orderHistoryVO.order_etd}" pattern="yyyy-MM-dd"/></td>
				                        <td><fmt:formatDate value="${orderHistoryVO.pickup_date}" pattern="yyyy-MM-dd"/></td>
				                        <td>${orderHistoryVO.receiver_add}</td>
				                        <td>${orderHistoryVO.receiver_name}</td> 
				                        <td>${orderHistoryVO.receiver_tel}</td>
				                        <td>
				                            ${(orderHistoryVO.order_status == "PAYMENT1") ? '已付款' : '' }
				                            ${(orderHistoryVO.order_status == "SHIPPING2") ? '出貨中' : '' }
				                            ${(orderHistoryVO.order_status == "SHIPMENT3") ? '已出貨' : '' }
				                            ${(orderHistoryVO.order_status == "COMPLETE4") ? '已完成' : '' }
				                            ${(orderHistoryVO.order_status == "CANCEL5") ? '已取消' : '' }
				                        </td>
				                        
				                        <td>
				                          <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/order_history/OrderHistory.do" style="margin-bottom: 0px;">
				                             <input type="submit" value="修改" class="btn btn-warning">
				                             <input type="hidden" name="order_no"  value="${orderHistoryVO.order_no}">
				                             <input type="hidden" name="action" value="getOne_For_Update"></FORM>
				                        </td>
				                        <td>
				                          <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/order_history/OrderHistory.do" style="margin-bottom: 0px;">
				                             <input type="submit" value="刪除" class="btn btn-danger">
				                             <input type="hidden" name="order_no"  value="${orderHistoryVO.order_no}">
				                             <input type="hidden" name="action" value="delete_OrderHistory"></FORM>
				                        </td>
				                    </tr>
				                </c:forEach>
				            </tbody>
				        </table>

			        </div>
					
				<!-- 標籤面板：訂單明細 -->
			        <div role="tabpanel" class="tab-pane active" id="detail">
	
						<c:if test="${not empty errorMsgs}">
							<font style="color:red">請修正以下錯誤：</font>
							<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color:red">${message}</li>
							</c:forEach>
							</ul>
						</c:if>

						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/order_detail/OrderDetail.do" name="form1">
								<hr>
								<div class="form-group">
									<label>訂單編號：<%=orderDetailVO.getOrder_no()%></label>
								</div>
								<div class="form-group">
									<label>商品編號：<%=orderDetailVO.getGoods_no()%></label>
								</div>
								<div class="form-group">
										<label>實際交易單價：</label>
										<input type="TEXT" name="goods_bonus" id="goods_bonus" placeholder="請輸入實際交易單價" class="form-control" value="<%=orderDetailVO.getGoods_bonus()%>" style="width:15%" >
								</div>
								<div class="form-group">
										<label>商品數量：</label>
										<input type="TEXT" name="goods_pc" id="goods_pc" placeholder="請輸入商品數量" class="form-control" value="<%=orderDetailVO.getGoods_pc()%>" style="width:15%" >
								</div>
							<br>
							<input type="hidden" name="action" value="update">
								<input type="hidden" name="order_no" value="<%=orderDetailVO.getOrder_no()%>">
								<input type="hidden" name="goods_no" value="<%=orderDetailVO.getGoods_no()%>">
							<input type="button" value="返回 " class="btn btn-default" onclick="location.href='<%=request.getContextPath()%>/backend/order_detail/listAllOrderDetail.jsp'" >
							<input type="submit" value="送出修改" class="btn btn-default">
						</FORM>      
			        

					</div>
			    </div>
			</div>
		</div>
	</div>
</div>

	<script src="https://code.jquery.com/jquery.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
	<script>
        var table;
        var info;
        $(document).ready(function() {
        	var table = $('#example1').DataTable();
        	var table = $('#example2').DataTable();
        });
    </script>
</body>
</html>