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
<jsp:useBean id="OrderHistorySvc" scope="page" class="com.order_history.model.OrderHistoryService" />
<jsp:useBean id="OrderDetailSvc" scope="page" class="com.order_detail.model.OrderDetailService" />
<jsp:useBean id="goodsSvc" scope="page" class="com.goods.model.GoodsService" />
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">	
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
	<title>新增訂單紀錄</title>
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
			        <li role="presentation" >
			            <a href="#detail" aria-controls="detail" role="tab" data-toggle="tab">訂單明細</a>
			        </li>

			    </ul>
			
			    <!-- 標籤面板：內容區 -->
			    <div class="tab-content">
					<!-- 標籤面板：訂單紀錄 -->
			        <div role="tabpanel" class="tab-pane active" id="history">
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
							<div class="container">
								<div class="row">
										
									<div class="col-xs-12 col-sm-6">
										<h1>訂單紀錄</h1>
										<hr>
										<div class="form-group">
											<label>會員編號：</label>
											<input type="TEXT" name="member_no" id="order_price" placeholder="請輸入會員編號" class="form-control" value="<%=(orderHistoryVO==null)? "M000001" : orderHistoryVO.getMember_no()%>" style="width:30%" >
										</div>
										<div class="form-group">
											<label>訂單總金額：</label>
											<input type="TEXT" name="order_price" id="order_price" placeholder="請輸入訂單總金額" class="form-control" value="<%=(orderHistoryVO==null)? "1000000" : orderHistoryVO.getOrder_price()%>" style="width:30%" >
										</div>
										<div class="form-group" style="width:20%">
											<label>付款方式：</label>
												<select class="form-control" size="1" name="pay_methods">
													<option value="CREDITCARD" selected>電子錢包</option>
													<option value="EWALLET">信用卡</option>
												</select>
										</div>
											
										<div class="form-group" style="width:20%">
											<label>出貨方式：</label>
												<select class="form-control" size="1" name="shipping_methods">
													<option value="STOREPICKUP" selected>超商取貨</option>
													<option value="HOMEDELIVERY">宅配</option>
												</select>
										</div>
											
										<div class="form-group">
											<label>訂購日期：</label>
											<input name="order_date" id="f_date1" class="form-control" style="width:30%">
										</div>
										<div class="form-group">
											<label>出貨日期：</label>
											<input name="order_etd" id="f_date2" class="form-control" style="width:30%">
										</div>
										<div class="form-group">
											<label>取貨日期：</label>
											<input name="pickup_date" id="f_date3" class="form-control" style="width:30%">
										</div>
										<div class="form-group">
											<label>收件人地址：</label>
											<input type="TEXT" name="receiver_add" id="receiver_add" placeholder="請輸入收件人地址" class="form-control" value="<%= (orderHistoryVO==null)? "320桃園市中壢區福德一路177巷60弄2號" : orderHistoryVO.getReceiver_add()%>" style="width:60%" >
										</div>
										<div class="form-group">
											<label>收件人名稱：</label>
											<input type="TEXT" name="receiver_name" id="receiver_name" placeholder="請輸入收件人名稱" class="form-control" value="<%= (orderHistoryVO==null)? "Peter1" : orderHistoryVO.getReceiver_name()%>" style="width:20%">
										</div>
										<div class="form-group">
											<label>收件人電話：</label>
											<input type="TEXT" name="receiver_tel" id="receiver_tel" placeholder="請輸入收件人名稱" class="form-control" value="<%= (orderHistoryVO==null)? "0912345678" : orderHistoryVO.getReceiver_tel()%>" style="width:20%">
										</div>
										
										<div class="form-group" style="width:20%">
											<label>訂單狀態：</label>
												<select class="form-control" size="1" name="order_status">
													<option value="PAYMENT1" selected>已付款</option>
													<option value="SHIPPING2">出貨中</option>
													<option value="SHIPMENT3">已出貨</option>
													<option value="COMPLETE4">已完成</option>
													<option value="CANCEL5">已取消</option>
												</select>
										</div>
									</div>
										
									<div class="col-xs-12 col-sm-6">
										<h1>訂單明細</h1>
										<hr>
										<div class="form-group" style="width:20%">
											<label>商品編號：</label>
											<select class="form-control" size="1" name="goods_no">
												<c:forEach var="GoodsVO" items="${GoodsSvc.all}" > 
													<option value="${GoodsVO.goods_no}">${GoodsVO.goods_no}
												</c:forEach>   
											</select>
										</div>
										<div class="form-group">
											<label>實際交易單價：</label>
											<input type="TEXT" name="goods_bonus" id="goods_bonus" placeholder="請輸入實際交易金際" class="form-control" value="<%=(orderDetailVO==null)? "0.0" : orderDetailVO.getGoods_bonus()%>" style="width:30%" >
										</div>
										<div class="form-group">
											<label>商品數量：</label>
											<input type="TEXT" name="goods_pc" id="goods_pc" placeholder="請輸入商品數量" class="form-control" value="<%=(orderDetailVO==null)? "0.0" : orderDetailVO.getGoods_pc()%>" style="width:30%" >
										</div>
										<div id="newDetail"></div>
									</div>					
								</div>
								<hr>
								<input type="hidden" name="action" value="insert">
								<input type="button" value="新增一筆明細" class="btn btn-success" id="addDetail">
								<input type="button" value="刪除一筆明細" class="btn btn-danger" id="delDetail">
								<input type="button" value="返回 " class="btn btn-default" onclick="location.href='<%=request.getContextPath()%>/backend/order_history/selectOrder.jsp'" >
								<input type="submit" value="送出新增" class="btn btn-primary">		
							</div>

							
								
								
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
									<th>修改</th>
									<th>刪除</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="orderDetailVO" items="${listDetail}">							
									<tr>
										<td>${orderDetailVO.order_no}</td>
										<td>${orderDetailVO.goods_no}</td>
										<td>	
											<c:forEach var="goodsVO" items="${goodsSvc.all}">
												<c:if test="${orderDetailVO.goods_no == goodsVO.goods_no}">${goodsVO.goods_name}</c:if>
											</c:forEach>
										</td>
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
			    </div>
			</div>
		</div>
	</div>
</div>

</body>

<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
		
<script> 
		$(document).ready( function () {
 			$("#example2").DataTable();
		});
</script>
<script>
	
	$(document).ready(function(){
		$("#addDetail").click(function(){
			var content ='<hr>' + 
						'<div class="form-group" style="width:20%">' +
							'<label>商品編號：</label>' +
							'<select class="form-control" size="1" name="goods_no">' +
								'<c:forEach var="GoodsVO" items="${GoodsSvc.all}" >' +
									'<option value="${GoodsVO.goods_no}">${GoodsVO.goods_no}' +
								'</c:forEach>' +   
							'</select>' +
						'</div>' +
						'<div class="form-group">' +
							'<label>實際交易單價：</label>' +
							'<input type="TEXT" name="goods_bonus" id="goods_bonus" placeholder="請輸入實際交易金際" class="form-control" value="<%=(orderDetailVO==null)? "0.0" : orderDetailVO.getGoods_bonus()%>" style="width:30%" >' +
						'</div>' +
						'<div class="form-group">' +
							'<label>商品數量：</label>' +
							'<input type="TEXT" name="goods_pc" id="goods_pc" placeholder="請輸入商品數量" class="form-control" value="<%=(orderDetailVO==null)? "0.0" : orderDetailVO.getGoods_pc()%>" style="width:30%" >' +
						'</div>';
			$("#newDetail").append(content);
		});
	});
	$(document).ready(function(){
		$('#delDetail').click(function(){
			$('#newDetail div:last-child').remove();
			$('#newDetail div:last-child').remove();
			$('#newDetail div:last-child').remove();	
			$('#newDetail hr:last-child').remove();
		});
	});
	
</script>


<% 
  java.sql.Timestamp order_date = null;
  try {
		order_date = orderHistoryVO.getOrder_date();
   } catch (Exception e) {
		order_date = new java.sql.Timestamp(System.currentTimeMillis());
   }
  
  java.sql.Timestamp order_etd = null;
  try {
		order_etd = orderHistoryVO.getOrder_etd();
   } catch (Exception e) {
		order_etd = new java.sql.Timestamp(System.currentTimeMillis());
   }
  
  java.sql.Timestamp pickup_date = null;
  try {
		pickup_date = orderHistoryVO.getPickup_date();
   } catch (Exception e) {
		pickup_date = new java.sql.Timestamp(System.currentTimeMillis());
   }
%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
   .xdsoft_datetimepicker .xdsoft_datepicker { 
            width:  300px;    width:  300px; 
   } 
   .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
            height: 151px;    height:  151px; 
   } 
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
           theme: '',              //theme: 'dark',
           timepicker:true,       //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
 		   value: new Date(), // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });

        $('#f_date2').datetimepicker({
           theme: '',              //theme: 'dark',
           timepicker:true,       //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
 		   value: new Date(), // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        $('#f_date3').datetimepicker({
           theme: '',              //theme: 'dark',
           timepicker:true,     //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
 		   value: new Date(), // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
</script>
</html>