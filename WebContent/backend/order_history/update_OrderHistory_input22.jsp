<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.order_history.model.*"%>

<%
	OrderHistoryVO orderHistoryVO = (OrderHistoryVO) request.getAttribute("orderHistoryVO");
%>

<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<title>修改訂單紀錄</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
		<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
		
	<div><c:import url="/backend/navbar_back-end.jsp" charEncoding="UTF-8"/></div>
	</head>
	<body>
	<div class="container-fluid">
		<div class="col-xs-12 col-sm-1"></div>
			<div class="row">
				<div class="col-xs-12 col-sm-10">
<!-- 					<h4><a href="select_page.jsp"><img src="images/LOGO1.png" width="70" height="50" border="0"><b>首頁</b></a></h4> -->
					<div class="panel panel-info">
						
						<div class="panel-heading">
							<h3 class="panel-title">修改訂單紀錄</h3>
						</div>

						<c:if test="${not empty errorMsgs}">
							<font style="color:red">請修正以下錯誤：</font>
							<ul>
								<c:forEach var="message" items="${errorMsgs}">
									<li style="color:red">${message}</li>
								</c:forEach>
							</ul>
						</c:if>

						<div class="panel-body">
							<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/order_history/OrderHistory.do" name="form1">
								<table>
									<tr>
										<td>訂單編號：</td>
										<td><%=orderHistoryVO.getOrder_no()%></td>
									</tr>
									<tr>
										<td>會員編號：</td>
										<td><%=orderHistoryVO.getMember_no()%></td>
									</tr>
									<tr>
										<td>訂單總金額：</td>
										<td><input type="TEXT" name="order_price" size="15"	value="<%=orderHistoryVO.getOrder_price()%>" /></td>
									</tr>
									
									<jsp:useBean id="orderHistorySvc" scope="page" class="com.order_history.model.OrderHistoryService" />
									
									<tr>
										<td>付款方式：</td>
										<td>
											<select size="1" name="pay_methods">
												<option value="CREDITCARD" selected>電子錢包</option>
												<option value="EWALLET">信用卡</option>
											</select>
										</td>
									</tr>
									<tr>
										<td>出貨方式：</td>
										<td>
											<select size="1" name="shipping_methods">
												<option value="STOREPICKUP" selected>超商取貨</option>
												<option value="HOMEDELIVERY">宅配</option>
											</select>
										</td>
									</tr>
									<tr>
										<td>訂購日期：</td>
										<td><input name="order_date" id="f_date1" type="text" ></td>
									</tr>
									<tr>
										<td>出貨日期：</td>
										<td><input name="order_etd" id="f_date2" type="text" ></td>
									</tr>
									<tr>
										<td>取貨日期：</td>
										<td><input name="pickup_date" id="f_date3" type="text" ></td>
									</tr>
									<tr>
										<td>收件人地址：</td>
										<td><input type="TEXT" name="receiver_add" size="45" value="<%=orderHistoryVO.getReceiver_add()%>" /></td>
									</tr>
									<tr>
										<td>收件人名稱：</td>
										<td><input type="TEXT" name="receiver_name" size="15" value="<%=orderHistoryVO.getReceiver_name()%>" /></td>
									</tr>
									<tr>
										<td>收件人電話：</td>
										<td><input type="TEXT" name="receiver_tel" size="15" value="<%=orderHistoryVO.getReceiver_tel()%>" /></td>
									</tr>
									<tr>
										<td>訂單狀態：</td>
										<td>
											<select size="1" name="order_status">
												<option value="PAYMENT1" selected>已付款</option>
												<option value="SHIPPING2">出貨中</option>
												<option value="SHIPMENT3">已出貨</option>
												<option value="COMPLETE4">已完成</option>
												<option value="CANCEL5">已取消</option>
											</select>
										</td>
									</tr>

								</table>
								<br>
								<input type="hidden" name="action" value="update">
								<input type="hidden" name="order_no" value="<%=orderHistoryVO.getOrder_no()%>">
								<input type="hidden" name="member_no" value="<%=orderHistoryVO.getMember_no()%>">
								<input type="submit" value="送出修改" class="btn btn-primary">
							</FORM>
						</div>
					</div>

				</div>
			</div>
		</div>

		
		
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	</body>

<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
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
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
           theme: '',              //theme: 'dark',
 	       timepicker:true,       //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
 		   value: '<%=orderHistoryVO.getOrder_date()%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        $.datetimepicker.setLocale('zh');
        $('#f_date2').datetimepicker({
           theme: '',              //theme: 'dark',
 	       timepicker:true,       //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
 		   value: '<%=orderHistoryVO.getOrder_etd()%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        $.datetimepicker.setLocale('zh');
        $('#f_date3').datetimepicker({
           theme: '',              //theme: 'dark',
 	       timepicker:true,       //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
 		   value: '<%=orderHistoryVO.getPickup_date()%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
   
</script>
</html>