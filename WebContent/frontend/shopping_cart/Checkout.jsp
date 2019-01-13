<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*" %>
<%@ page import="com.shopping_cart.model.ShoppingCart" %>
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
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
	<title>購物車結帳頁面</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
	<div><c:import url="/frontend/navbar_front-end.jsp" charEncoding="UTF-8"/></div>
</head>

<style>
	/*隱藏區塊用*/
	.pay_methods{display: none;}
	/*秀出區塊用*/
	.creditcard{}
	.ewallet{}
	/*區塊範圍指定*/
	.bBW{}
</style>

<body>


<div class="container-fluid" style="margin-bottom: 400px">
    <div class="row">
        <div class="col-xs-12 col-sm-3"></div>
        <div class="col-xs-12 col-sm-6">
            
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="panel1">
                    <h4 class="panel-title">
                        <a href="#orderdetail" data-parent="#accordion" data-toggle="collapse" role="button" aria-expanded="true" aria-controls="orderdetail">購物車明細</a>
                    </h4>
                </div>
                <div id="orderdetail" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="panel1">
                    <div class="list-group">
                        <table class="table table-bordered table-striped table-hover"> 
                            <thead>
                                <tr>
                                    <th>商品編號</th>
                                    <th>商品名稱</th>
                                    <th>商品單價</th>
                                    <th>商品數量</th>
                                </tr>
                            </thead>


                            <%
                                Vector<ShoppingCart> buylist = (Vector<ShoppingCart>) session.getAttribute("shoppingcart");
                                String amount =  (String) request.getAttribute("amount");
                            %>  

                            <%  for (int i = 0; i < buylist.size(); i++) {
                                ShoppingCart order = buylist.get(i);
                                String goods_no = order.getGoods_no();
                                String goods_name = order.getGoods_name();
                                Integer goods_price = order.getGoods_price();
                                Integer goods_quantity = order.getGoods_quantity(); 
                            %>
                            <tbody>
                                <tr>
                                    <td><div align="center"><b><%=goods_no%></b></div></td>
                                    <td><div align="center"><b><%=goods_name%></b></div></td>
                                    <td><div align="center"><b><%=goods_price%></b></div></td>
                                    <td><div align="center"><b><%=goods_quantity%></b></div></td>
                                </tr>
                            </tbody>
                            <%
                                }
                            %>
           
                        </table>
                        <h4> 付款金額：<font color="red">$<%=amount%></font></h4>  

                    </div>
                </div>
            </div>

                <input type="button" value="返回上一頁 " class="btn btn-default" onclick="location.href='<%=request.getContextPath()%>/frontend/shopping_cart/ShoppingCart.jsp'" >
				<input type="button" value="繼續購物" class="btn btn-default" onclick="location.href='<%=request.getContextPath()%>/frontend/shopping_cart/EShop.jsp'" >
						
			
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


								<h1>訂單紀錄</h1>
								<hr>
								<div class="form-group">
									<label>會員編號：</label>
									<input type="TEXT" name="member_no" placeholder="請輸入會員編號" class="form-control" value="<%=(orderHistoryVO==null)? "M000001" : orderHistoryVO.getMember_no()%>" style="width:30%" >
								</div>
								<div class="form-group">
									<label>訂單總金額：$<%=amount%></label>
									<input type="hidden" name="order_price" value="<%=amount%>" >
								</div>
								
<!-- 								<div class="form-group" style="width:20%"> -->
<!-- 									<label>付款方式：</label> -->
<!-- 										<select class="form-control" size="1" name="pay_methods"> -->
<!-- 											<option value="CREDITCARD" selected>電子錢包</option> -->
<!-- 											<option value="EWALLET">信用卡</option> -->
<!-- 										</select> -->
<!-- 								</div> -->
								
									<input name="pay_methods" type="radio" value="CREDITCARD" checked="checked"><b>信用卡</b>
									<input name="pay_methods" type="radio" value="EWALLET"><b>電子錢包</b><br>
							
									<!-- 如果開始要顯示要加入 style="display:block;" 不顯示則加入  style="display:none;"-->
									<div class="creditcard pay_methods bBW" style="display:block;">
										<input type="TEXT" name="creditcard_no" placeholder="請輸入信用卡卡號" class="form-control" value="" style="width:20%"><font color="#fff">-</font><input type="TEXT" name="creditcard_no" placeholder="未3碼" class="form-control" value="" style="width:8%" >
									</div>
							
									<div class="ewallet pay_methods bBW">
										<label>電子錢包餘額：$</label>
									</div>
								
								<br>	
								<div class="form-group" style="width:20%">
									<label>出貨方式：</label>
										<select class="form-control" size="1" name="shipping_methods">
											<option value="STOREPICKUP" selected>超商取貨</option>
											<option value="HOMEDELIVERY">宅配</option>
										</select>
								</div>
									
						
								<!--訂購日期 -->
								<input type="hidden" name="order_date" id="f_date1" class="form-control" style="width:30%">
								<!--出貨日期 -->
								<input type="hidden" name="order_etd" id="f_date2" class="form-control" style="width:30%">
								<!--取貨日期 -->
								<input type="hidden" name="pickup_date" id="f_date3" class="form-control" style="width:30%">
			
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
								
<!-- 								<div class="form-group" style="width:20%"> -->
								<input type="hidden" name="order_status" value="PAYMENT1" >
<!-- 									<label>訂單狀態：</label> -->
<!-- 										<select class="form-control" size="1" name="order_status"> -->
<!-- 											<option value="PAYMENT1" selected>已付款</option> -->
<!-- 											<option value="SHIPPING2">出貨中</option> -->
<!-- 											<option value="SHIPMENT3">已出貨</option> -->
<!-- 											<option value="COMPLETE4">已完成</option> -->
<!-- 											<option value="CANCEL5">已取消</option> -->
<!-- 										</select> -->
		
							</div>
								
							<%	for (int i = 0; i < buylist.size(); i++) {
									ShoppingCart order = buylist.get(i);
									String goods_no = order.getGoods_no();
									Integer goods_price = order.getGoods_price();
									Integer goods_quantity = order.getGoods_quantity();	
									
							%>
								<!--商品編號 -->
								<input type="hidden" name="goods_no" value=<%=goods_no%>>
								<!--商品價錢 -->
								<input type="hidden" name="goods_bonus" value="<%=goods_price%>">
								<!--商品數量 -->
								<input type="hidden" name="goods_pc" value="<%=goods_quantity%>">
							<%}%>								
							

						<hr>
						<input type="hidden" name="action" value="insert_Front">
						<input type="submit" value="完成訂單" class="btn btn-primary" style="">
						
				</FORM>
			
			</div>	
			
			
				
        </div>
    </div>
</div>





</center>
</body>
	<div><c:import url="/frontend/footer_front-end.jsp" charEncoding="UTF-8"/></div>
<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>	
	
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

<script>
$(document).ready(function(){
        $('input[type="radio"]').click(function(){
            if($(this).attr("value")=="CREDITCARD"){
                $(".pay_methods").hide();
                $(".creditcard").show();
            }
            if($(this).attr("value")=="EWALLET"){
                $(".pay_methods").hide();
                $(".ewallet").show();
            }

        });
    });
</script>	
	
</html>
