<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.order_history.model.*"%>

<%
    OrderHistoryService orderHistorySvc = new OrderHistoryService();
    List<OrderHistoryVO> list = orderHistorySvc.getAll();
    pageContext.setAttribute("list",list);
%>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
        <title>ETIckeTs - 訂單紀錄新增</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
    </head>
	<jsp:include page="/backend/navbar_back-end.jsp" flush="true"/>
	<style>
		body{
			font-family:微軟正黑體!important;
		}
	</style>
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
			        	
					    <c:if test="${not empty errorMsgs}">
				            <font style="color:red">請修正以下錯誤:</font>
				            <ul>
				                <c:forEach var="message" items="${errorMsgs}">
				                    <li style="color:red">${message}</li>
				                </c:forEach>
				            </ul>
				        </c:if>
						<hr>
				        <table id="example" class="display" style="width:100%; font-size:8px">
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
				                <c:forEach var="orderHistoryVO" items="${list}">                          
				                    <tr>
				                        <td>${orderHistoryVO.order_no}</td>
				                        <td>${orderHistoryVO.member_no}</td>
				                        <td>${orderHistoryVO.order_price}</td>
				                        <td>
				                            ${(orderHistoryVO.pay_methods == "CREDITCARD") ? '信用卡' : '' }
				                            ${(orderHistoryVO.pay_methods == "EWALLET") ? '電子錢包' : '' }
				                        </td>
				                        <td>
				                            ${(orderHistoryVO.shipping_methods == "STOREPICKUP") ? '超商取貨' : '' }
				                            ${(orderHistoryVO.shipping_methods == "HOMEDELIVERY") ? '宅配' : '' }
				                        </td>
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
				        <hr>
				        <input type="button" value="返回 " onclick="location.href='selectOrder.jsp'" style="float:right" >
				        
			        </div>
					
					<!-- 標籤面板：訂單明細 -->
			        <div role="tabpanel" class="tab-pane" id="detail">
			        	
			        	<c:if test="${not empty errorMsgs}">
							<font style="color:red">請修正以下錯誤:</font>
								<ul>
								    <c:forEach var="message" items="${errorMsgs}">
										<li style="color:red">${message}</li>
									</c:forEach>
								</ul>
						</c:if>

						<a href='<%=request.getContextPath()%>/backend/order_detail/listAllOrderDetail.jsp'>查詢全部訂單明細</a><br><br>  						

						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/order_detail/OrderDetail.do" >
						    <b>輸入訂單編號 (如O2018121710001):</b>
						    <input type="text" name="order_no">
						    <input type="hidden" name="action" value="getOne_For_Display">
						    <input type="submit" value="送出" class="btn btn-info">
						</FORM>
			
						<jsp:useBean id="OrderDetailSvc" scope="page" class="com.order_detail.model.OrderDetailService" />
						
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/order_detail/OrderDetail.do" >
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
	</div>
</div>

        <script src="https://code.jquery.com/jquery.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
    	<!-- <script src="https://cdn.datatables.net/plug-ins/1.10.19/api/page.jumpToData().js"></script> -->

        <script>
        var table;
        var info;
        
        $(document).ready(function() {

        	var table = $('#example').DataTable();
        	
        });
        </script>
    </body>

</html>