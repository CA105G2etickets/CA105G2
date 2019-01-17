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
			        <li role="presentation" class="active">
			            <a href="#history" aria-controls="history" role="tab" data-toggle="tab">訂單紀錄</a>
			        </li>
			        <li role="presentation">
			            <a href="#detail" aria-controls="detail" role="tab" data-toggle="tab">訂單明細</a>
			        </li>

			    </ul>
			
			    <!-- 標籤面板：內容區 -->
			    <div class="tab-content">
					<!-- 標籤面板：訂單紀錄 -->
			        <div role="tabpanel" class="tab-pane active" id="history">
					
<!-- 						<br><input type="button" class="btn btn-primary" value="查詢全部" onclick="location.href='listAllOrderHistory.jsp'"> -->
			
						<c:if test="${not empty errorMsgs}">
							<font style="color:red">請修正以下錯誤：</font>
							<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color:red">${message}</li>
							</c:forEach>
							</ul>
						</c:if>

							<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/order_history/OrderHistory.do" name="form1">
									<hr>
									<div class="form-group">
										<label>訂單編號：<%=orderHistoryVO.getOrder_no()%></label>
									</div>
									<div class="form-group">
										<label>會員編號：<%=orderHistoryVO.getMember_no()%></label>
									</div>
									<div class="form-group">
											<label>訂單總金額：</label>
											<input type="TEXT" name="order_price" id="order_price" placeholder="請輸入訂單總金額" class="form-control" value="<%=orderHistoryVO.getOrder_price()%>" style="width:15%" readonly="readonly">
									</div>
									<div class="form-group" style="width:10%">
										<label>付款方式：</label>
											<select class="form-control" size="1" name="pay_methods" readonly="readonly">
												<option value="CREDITCARD" selected>電子錢包</option>
												<option value="EWALLET">信用卡</option>
											</select>
									</div>
									
									<div class="form-group" style="width:10%">
										<label>出貨方式：</label>
											<select class="form-control" size="1" name="shipping_methods" readonly="readonly">
												<option value="STOREPICKUP" selected>超商取貨</option>
												<option value="HOMEDELIVERY">宅配</option>
											</select>
									</div>
									
									<div class="form-group">
										<label>訂購日期：</label>
										<input name="order_date" id="f_date1" class="form-control" style="width:15%"  readonly="readonly" >
									</div>
									<div class="form-group">
										<label>出貨日期：</label>
										<input name="order_etd" id="f_date2" class="form-control" style="width:15%" readonly="readonly">
									</div>
									<div class="form-group">
										<label>取貨日期：</label>
										<input name="pickup_date" id="f_date3" class="form-control" style="width:15%" readonly="readonly">
									</div> 
									<div class="form-group">
										<label>收件人地址：</label>
										<input type="TEXT" name="receiver_add" id="receiver_add" placeholder="請輸入收件人地址" class="form-control" value="<%=orderHistoryVO.getReceiver_add()%>" style="width:30%" readonly="readonly">
									</div>
									<div class="form-group">
										<label>收件人名稱：</label>
										<input type="TEXT" name="receiver_name" id="receiver_name" placeholder="請輸入收件人名稱" class="form-control" value="<%=orderHistoryVO.getReceiver_name()%>" style="width:10%" readonly="readonly">
									</div>
									<div class="form-group">
										<label>收件人電話：</label>
										<input type="TEXT" name="receiver_tel" id="receiver_tel" placeholder="請輸入收件人電話" class="form-control" value="<%=orderHistoryVO.getReceiver_tel()%>" style="width:10%" readonly="readonly">
									</div>
									
									<div class="form-group" style="width:10%">
										<label>訂單狀態：</label>
											<%if(!"COMPLETE4".equals(orderHistoryVO.getOrder_status())){%>
												<select class="form-control" size="1" name="order_status">
													<option value="PAYMENT1" selected>已付款</option>
													<option value="SHIPPING2">出貨中</option>
													<option value="SHIPMENT3">已出貨</option>
													<option value="COMPLETE4">已完成</option>
													<option value="CANCEL5">已取消</option>
												</select>
											<%} else {%>
												<input class="form-control" value="已完成" readonly="readonly">
												<input type="hidden" name="order_status" value="COMPLETE4">
												
											<%} %>
									</div>
								<br>
								<input type="hidden" name="action" value="update">
								<input type="hidden" name="order_no" value="<%=orderHistoryVO.getOrder_no()%>">
								<input type="hidden" name="member_no" value="<%=orderHistoryVO.getMember_no()%>">
								<input type="button" value="返回 " class="btn btn-default" onclick="location.href='<%=request.getContextPath()%>/backend/order_history/selectOrder.jsp'" >
								<input type="submit" value="送出修改" class="btn btn-default">
							</FORM>
			        </div>
					
					<!-- 標籤面板：訂單明細 -->
			        <div role="tabpanel" class="tab-pane" id="detail">
						<hr>
				        <table id="example2" class="display" style="width:100%; font-size:8px">
							<thead>
								<tr>
									<th>訂單編號</th>
									<th>商品編號</th>
									<th>商品名稱</th>
									<th>實際交易金額</th>
									<th>商品數量</th>
<!-- 									<th>修改</th> -->
<!-- 									<th>刪除</th> -->
								</tr>
							</thead>
							<tbody>
								<c:forEach var="orderDetailVO" items="${listDetail}">							
									<tr>
										<td>${orderDetailVO.order_no}</td>
										<td>
											<a href="<%=request.getContextPath()%>/frontend/goods/listOneGoods.jsp?goods_no=${orderDetailVO.goods_no}">${orderDetailVO.goods_no}</a>
										</td>
										<td>	
											<c:forEach var="goodsVO" items="${goodsSvc.all}">
												<c:if test="${orderDetailVO.goods_no == goodsVO.goods_no}">
													<a href="<%=request.getContextPath()%>/frontend/goods/listOneGoods.jsp?goods_no=${goodsVO.goods_no}">${goodsVO.goods_name}</a>
												</c:if>
											</c:forEach>
										</td>
										<td>${orderDetailVO.goods_bonus}</td>
										<td>${orderDetailVO.goods_pc}</td>
<!-- 										<td> -->
<%-- 										  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/order_detail/OrderDetail.do" style="margin-bottom: 0px;"> --%>
<!-- 										     <input type="submit" value="修改" class="btn btn-warning"> -->
<%-- 										     <input type="hidden" name="order_no"  value="${orderDetailVO.order_no}"> --%>
<%-- 										     <input type="hidden" name="goods_no"  value="${orderDetailVO.goods_no}"> --%>
<!-- 										     <input type="hidden" name="action"	value="getOne_For_Update"></FORM> -->
<!-- 										</td> -->
<!-- 										<td> -->
<%-- 										  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/order_detail/OrderDetail.do" style="margin-bottom: 0px;"> --%>
<!-- 										     <input type="submit" value="刪除" class="btn btn-danger"> -->
<%-- 										     <input type="hidden" name="order_no"  value="${orderDetailVO.order_no}"> --%>
<%-- 										     <input type="hidden" name="goods_no"  value="${orderDetailVO.goods_no}"> --%>
<!-- 										     <input type="hidden" name="action" value="delete"></FORM> -->
<!-- 										</td> -->
									</tr>
								</c:forEach>
							</tbody>
						</table>
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

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
		$('#f_date1').datetimepicker({
		    theme: '',              //theme: 'dark',
		    timepicker:true,       //timepicker:true,
		     step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
		     format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
			   value: '<%=orderHistoryVO.getOrder_date()%>', // value:   new Date(),
		 });
        $('#f_date2').datetimepicker({
           theme: '',              //theme: 'dark',
           timepicker:true,       //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
 		   value: '<%=orderHistoryVO.getOrder_etd()%>', // value:   new Date(),
        });
        
        $('#f_date3').datetimepicker({
           theme: '',              //theme: 'dark',
           timepicker:true,     //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
 		   value: '<%=orderHistoryVO.getPickup_date()%>', // value:   new Date(),
        });
</script>
</html>