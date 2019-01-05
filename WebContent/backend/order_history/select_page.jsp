<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">	
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
	<title>訂單紀錄查詢</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
</head>

<div>                   
	<c:import url="/navbar_back-end.html" charEncoding="UTF-8"/>
</div>

<body>

	<div class="container-fluid">
		<div class="col-xs-12 col-sm-1"></div>
		<div class="row">
			<div class="col-xs-12 col-sm-10">
<!-- 			<h4><a href="select_page.jsp"><img src="images/LOGO1.png" width="70" height="50" border="0"><b>首頁</b></a></h4> -->
				<div class="panel panel-info">
					<div class="panel-heading">
						<h3 class="panel-title">訂單紀錄查詢</h3>
					</div>
				<div class="panel-body">
				<c:if test="${not empty errorMsgs}">
					<font style="color:red">請修正以下錯誤:</font>
						<ul>
						    <c:forEach var="message" items="${errorMsgs}">
								<li style="color:red">${message}</li>
							</c:forEach>
						</ul>
				</c:if>
	
						<a href='listAllOrderHistory.jsp'>查詢全部訂單紀錄</a><br><br>  						
	
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/order_history/OrderHistory.do" >
				    <b>輸入訂單編號 (如O2018121710001):</b>
				    <input type="text" name="order_no">
				    <input type="hidden" name="action" value="getOne_For_Display">
				    <input type="submit" value="送出" class="btn btn-info">
				</FORM>
	
				<jsp:useBean id="OrderHistorySvc" scope="page" class="com.order_history.model.OrderHistoryService" />
				
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/order_history/OrderHistory.do" >
				  <b>選擇訂單編號:</b>
					<select size="1" name="order_no">
						<c:forEach var="OrderHistoryVO" items="${OrderHistorySvc.all}" > 
							<option value="${OrderHistoryVO.order_no}">${OrderHistoryVO.order_no}
						</c:forEach>   
					</select>
					<input type="hidden" name="action" value="getOne_For_Display">
					<input type="submit" value="送出" class="btn btn-info">
				</FORM>
	
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/order_history/OrderHistory.do" >
					<b>選擇會員編號:</b>
					<select size="1" name="member_no">
						<c:forEach var="OrderHistoryVO" items="${OrderHistorySvc.allMemberNo}" > 
							<option value="${OrderHistoryVO}">${OrderHistoryVO}
						</c:forEach>   
					</select>
					<input type="hidden" name="action" value="getOne_For_MemAllOrd">
					<input type="submit" value="送出" class="btn btn-info">
				</FORM>
				
				<%-- 萬用複合查詢-以下欄位-可隨意增減 --%>
 
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/order_history/OrderHistory.do" name="form1">
					<b><font color=blue>萬用複合查詢：</font></b><br>
					<b>輸入訂單編號：</b>
					<input type="text" name="order_no" value="" placehoder="O2018123010001"><br><br>
				    
				    <b>選擇會員編號：</b>
					<input type="text" name="member_no" value="" placehoder="M000001"><br><br>
				    
					<b>訂購日期：</b>
					<input name="order_date" id="f_date1" type="text" size="10"><br><br>
				    
					<b>出貨日期：</b>
					<input name="order_etd" id="f_date2" type="text" size="10"><br><br>
			
					<b>取貨日期：</b>
					<input name="pickup_date" id="f_date3" type="text" size="10"><br><br>
					
				   	<b>輸入送貨地址：</b>
					<input type="text" name="receiver_add" size="40" value="" placehoder="320桃園市中壢區福德一路177巷60弄2號"><br><br>
				    
					<b>輸入收件人名稱：</b>
					<input type="text" name="receiver_name" size="8" value="" placehoder="Peter"><br><br>
				
					<b>輸入收件人電話：</b>
					<input type="text" name="receiver_tel" size="8" value="" placehoder="0912345678"><br><br>
				      
				    <input type="submit" value="送出" class="btn btn-info">
				    <input type="hidden" name="action" value="listOrderHistory_ByCompositeQuery">
				 </FORM>
				
				</div>
			</div>
			</div>		
		</div>
		
		<div class="col-xs-12 col-sm-1"></div>
		<div class="row">	
			<div class="col-xs-12 col-sm-10">				
				<div class="panel panel-info">
					<div class="panel-heading">
						<h3 class="panel-title">訂單管理</h3>
					</div>
					<div class="panel-body">
						<a href='addOrderHistoryAndOrderDetail.jsp'>新增一筆訂單紀錄</a>
					</div>
				</div>
			</div>
		</div>
		
		
		<div class="col-xs-12 col-sm-1"></div>
		<div class="row">
			<div class="col-xs-12 col-sm-10">
<!-- 			<h4><a href="select_page.jsp"><img src="images/LOGO1.png" width="70" height="50" border="0"><b>首頁</b></a></h4> -->
				<div class="panel panel-info">
					<div class="panel-heading">
						<h3 class="panel-title">訂單明細查詢</h3>
					</div>
				<div class="panel-body">
				<c:if test="${not empty errorMsgs}">
					<font style="color:red">請修正以下錯誤:</font>
						<ul>
						    <c:forEach var="message" items="${errorMsgs}">
								<li style="color:red">${message}</li>
							</c:forEach>
						</ul>
				</c:if>
	
				<a href='<%=request.getContextPath()%>/order_detail/listAllOrderDetail.jsp'>查詢全部訂單明細</a><br><br>  						
	
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/OrderDetail/OrderDetail.do" >
				    <b>輸入訂單編號 (如O2018121710001):</b>
				    <input type="text" name="order_no">
				    <input type="hidden" name="action" value="getOne_For_Display">
				    <input type="submit" value="送出" class="btn btn-info">
				</FORM>
	
				<jsp:useBean id="OrderDetailSvc" scope="page" class="com.order_detail.model.OrderDetailService" />
				
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/OrderDetail/OrderDetail.do" >
				  <b>選擇訂單編號:</b>
					<select size="1" name="order_no">
						<c:forEach var="OrderDetailVO" items="${OrderDetailSvc.allOrderNo}" > 
							<option value="${OrderDetailVO}">${OrderDetailVO}
						</c:forEach>   
					</select>
					<input type="hidden" name="action" value="getAll_OrderDetail_For_A_OrderNo">
					<input type="submit" value="送出" class="btn btn-info">
				</FORM>
			
				</div>
			</div>
			</div>
		</div>
		
		<div class="col-xs-12 col-sm-1"></div>
		<div class="row">	
			<div class="col-xs-12 col-sm-10">				
				<div class="panel panel-info">
					<div class="panel-heading">
						<h3 class="panel-title">訂單明細管理</h3>
					</div>
					<div class="panel-body">
						<a href='<%=request.getContextPath()%>/order_detail/addOrderDetail.jsp'>新增一筆訂單明細</a>
					</div>
				</div>
			</div>
		</div>	
		
		
		
		
	</div>

	<script src="https://code.jquery.com/jquery.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>

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
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: '', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });

        $('#f_date2').datetimepicker({
           theme: '',              //theme: 'dark',
           timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: '', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        $('#f_date3').datetimepicker({
           theme: '',              //theme: 'dark',
           timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: '', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
    
        
        
</script>
</html>