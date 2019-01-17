<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.group_open.model.*"%>
<%@ page import="com.group_member.model.*"%>
<%@ page import="com.member.model.*"%>

<% 
			 String product =session.getAttribute("producttotal").toString();
			 String total =session.getAttribute("total").toString();
			Group_openVO group_openVO = (Group_openVO) session.getAttribute("group_openVOorder");
			
			MemberService memberSvc = new MemberService();
			List<MemberVO> list2 =  memberSvc.getAll();
			pageContext.setAttribute("list2", list2);
			
			MemberVO memberVOsession = (MemberVO)session.getAttribute("member"); 
			
			
			
%>

<jsp:useBean id="group_memberSvc" scope="page" class="com.group_member.model.Group_memberService" />
<jsp:useBean id="group_openSvc" scope="page" class="com.group_open.model.Group_openService" />

<!DOCTYPE html>
<html lang="">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/TWzipcode/jquery.twzipcode.min.js"></script>
		<title>Title Page</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
		<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
		<style>
		.memberphoto {
			border-radius: 50px;
			margin-top:20px;
			}
		.membermenu {
			margin-top:100px;
			margin-left: 200px;
			}
		.member{
			width:50px;
		}
		.img-thumbnail{
			width:80%;
			height: 80%;

		}
		.finish{
			margin-left:80%;	



		}
		</style>
	</head>
	<body>
			<jsp:include page="/frontend/navbar_front-end.jsp" flush="true" />
			<div class="container">
								<div class="row">
									
										<!-- <%-- 錯誤表列 --%> -->
							<c:if test="${not empty errorMsgs}">
								<font style="color:red">請修正以下錯誤:</font>
									<ul>
	    							<c:forEach var="message" items="${errorMsgs}">
									<li style="color:red">${message}</li>
									</c:forEach>
									</ul>
							</c:if>
							</div><!-- <div class="row"> -->
							</div><!-- <div class="container"> -->


		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-1">
					

				</div>
				<div class="col-xs-12 col-sm-10">
					<div class="col-xs-12 col-sm-6">
						
						<img src="<%=request.getContextPath()%>/frontend/group_open/Group_openImg1.do?group_no=${group_openVO.group_no}" class="img-thumbnail">



					</div><!-- <div class="col-xs-12 col-sm-6"> -->
					<div class="col-xs-12 col-sm-6">
						



					</div><!-- <div class="col-xs-12 col-sm-6"> -->
				<div class="col-xs-12 col-sm-1">
					

				</div><!-- <div class="col-xs-12 col-sm-1"> -->
			</div><!-- <div class="row"> -->
		</div><!-- <div class="container"> -->
			<br><br>

		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-1">
					





				</div>
				<div class="col-xs-12 col-sm-10">
											<div class="panel-group" id="accordion2" role="tablist" aria-multiselectable="true">
					  <!-- 區塊1 -->
					  <div class="panel panel-default">
					    <div class="panel-heading" role="tab" id="panel1">
					      <h4 class="panel-title">
					        <a href="#aaa" data-parent="#accordion2" data-toggle="collapse" role="button" aria-expanded="true" aria-controls="aaa">
					          團購訂單明細
					        </a>
					      </h4>
					    </div><!-- <div class="panel-heading" role="tab" id="panel1"> -->
					    <div id="aaa" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="panel1">
					      <div class="panel-body">
					      		<table class="table table-hover">
						<caption>開團購物訂單明細</caption>
						<thead class="tabletitle">
							<tr>
								<th>購買人名稱</th>
								<th>購買數量</th>
								<th>折扣價格</th>
								<th>總計</th>
							</tr>
						</thead>			
						<tbody>
						<c:forEach var="group_memberVO" items="${groupsucesslist}" >
							<tr>
								<td>
							<c:forEach var="memberVO" items="${list2}">
								<c:if test="${group_memberVO.member_no==memberVO.memberNo}">
									${memberVO.memberFullname}
								</c:if>	
							</c:forEach>
								</td>
								<td>${group_memberVO.product_quantity}</td>
								<td>
							<c:forEach var="group_openVO" items="${group_opengetall}">
								<c:if test="${group_openVO.group_no==group_memberVO.group_no}">
								${group_openVO.group_price}
								</c:if>
							</c:forEach>
							</td>
								<td>
								<c:forEach var="group_openVO" items="${group_opengetall}">
								<c:if test="${group_openVO.group_no==group_memberVO.group_no}">
								${group_openVO.group_price*group_memberVO.product_quantity}
								</c:if>
							</c:forEach>
								</td>
							</tr>
							</c:forEach>
							<tr>
							<td>總計數量&nbsp;&nbsp;&nbsp;<%=product%></td>
							</tr>
							<tr>
							<td><h4><font color="red">付款金額:&nbsp;&nbsp;&nbsp;$<%=total%></font></h4></td>
							</tr>
						</tbody>
					</table>


					       
					      </div><!-- <div class="panel-body"> -->
					    </div><!-- <div id="aaa" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="panel1"> -->
					  </div><!-- <div class="panel panel-default"> -->	
					</div><!-- <div class="panel-group" id="accordion2" role="tablist" aria-multiselectable="true"> -->
					
					





				</div><!-- <div class="col-xs-12 col-sm-10"> -->
				<div class="col-xs-12 col-sm-1">
					





				</div><!-- <div class="col-xs-12 col-sm-1"> -->
				


			</div><!-- <div class="row"> -->
		</div><!-- <div class="container"> -->
			<div class="container">
				<div class="row">
					<div class="col-xs-12 col-sm-1">
						


					</div>
					<div class="col-xs-12 col-sm-10">
						<div class="col-xs-12 col-sm-6">
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/frontend/group_open/group_open.do" name="form1">
						<h3>團購訂單</h3>
							<hr>
							 <div class="form-group">
  								<label for="usr">收件人名稱</label>
  								<input type="text" class="form-control" id="usr" name="receiver_name">
							</div><!-- <div class="form-group"> -->
					<!-- 	    <div class="form-group">
  								<label for="usr">收件人地址</label>
  								<input type="text" class="form-control" id="usr" name="receiver_add">
							</div><div class="form-group"> -->
							<div class="form-group" id="zipcode2" style="width:100%">
							<label>收件人地址：</label>
							<script>
								$("#zipcode2").twzipcode({
									countySel: "臺北市", // 城市預設值, 字串一定要用繁體的 "臺", 否則抓不到資料
									districtSel: "大安區", // 地區預設值
									zipcodeIntoDistrict: true, // 郵遞區號自動顯示在地區
									css: ["city form-control", "town form-control"], // 自訂 "城市"、"地區" class 名稱 
									countyName: "city", // 自訂城市 select 標籤的 name 值
									districtName: "town" // 自訂地區 select 標籤的 name 值
								});
							</script>	
							<input type="text" class="form-control" id="usr" name="receiver_add">
						</div>							 
						       <div class="form-group">
  									<label for="usr">收件人電話</label>
  									<input type="text" class="form-control" id="usr" name="receiver_tel">
								</div><!-- <div class="form-group"> -->
\		
						   <div class="form-group">
 							 <label for="sel1">出貨方式</label>
  								<select class="form-control" id="sel1" name="shipping_methods">
   									 <option>超商取貨</option>
    								 <option>宅配</option>
  								</select>
						   </div>
							<hr>
								<input type="hidden" name="pay_methods" value="EWALLET1" >
								<input type="hidden" name="group_no" value="${group_openVO.group_no}" >
								<input type="hidden" name="goods_bonus" value="${group_openVO.group_price}" >
								<input type="hidden" name="goods_no" value="${group_openVO.goods_no}">
								<input type="hidden" name="goods_pc" value="<%=product%>">
								<input type="hidden" name="order_price" value="<%=total%>">
								<input type="hidden" name="member_no" value="<%=memberVOsession.getMemberNo()%>">
								<input type="hidden" name="order_status" value="PAYMENT1" >
								<input type="hidden" name="order_date" id="f_date1" class="form-control" style="width:30%">
								<input type="hidden" name="order_etd" id="f_date2" class="form-control" style="width:30%">
								<input type="hidden" name="pickup_date" id="f_date3" class="form-control" style="width:30%">
								<input type="hidden" name="action" value="insert_Front">		
								<button type="submit" class="btn btn-primary finish">產生訂單</button>
							
						</div><!-- <div class="col-xs-12 col-sm-6"> -->
							<br>
						<div class="col-xs-12 col-sm-6">
							


						</div><!-- <div class="col-xs-12 col-sm-6"> -->
					</div>
					<div class="col-xs-12 col-sm-1">
				<%-- 				<c:if test="${not empty errorMsgs}">
					<font style="color:red">請修正以下錯誤：</font>
					<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color:red">${message}</li>
					</c:forEach>
					</ul>
				</c:if> --%>


					</div>
				</div><!-- <div class="row"> -->
			</div><!-- <div class="container"> -->

<jsp:include page="/frontend/footer_front-end.jsp" flush="true"/>
		<script type="text/javascript">
		$(document).ready(function(){
		
		$("#completeOrderBtn").click(function(){
	        	var city = $(".city").val();
	        	var town = $(".town option:selected").text();
	        	var street = $("#street").val();
	        	$("#receiver_add").val(town.substring(0,3) + city + town.substring(4) + street);
	        	$("#orderForm").submit();
	        });
		
		
		  });
		</script>
		
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	</body>
</html>





