<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.group_open.model.*"%>
<%@ page import="com.group_member.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.order_history.model.*"%>

<% 
			 String product =request.getAttribute("producttotal").toString();
			 String total =request.getAttribute("total").toString();
			Group_openVO group_openVO = (Group_openVO) request.getAttribute("group_openVO");
			
			OrderHistoryVO orderHistoryVO = (OrderHistoryVO)request.getAttribute("orderHistoryVO");
			
			MemberService memberSvc = new MemberService();
			List<MemberVO> list2 =  memberSvc.getAll();
			pageContext.setAttribute("list2", list2);
%>

<jsp:useBean id="group_memberSvc" scope="page" class="com.group_member.model.Group_memberService" />
<jsp:useBean id="group_openSvc" scope="page" class="com.group_open.model.Group_openService" />
<!DOCTYPE html>
<html lang="">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
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
		.tabletitle{
			background-color: #CCEEFF;

		}
		.img-thumbnail{
			width:80%;
			height: 80%;

		}
		</style>
	</head>
	<body>
			<nav class="navbar navbar-default" role="navigation">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
						<span class="sr-only">選單切換</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<img src="https://i.imgur.com/T0YnkK9.png"  href="#" alt="LOGO" width="202.25px" height="165.5px">
				</div>
				
				<!-- 手機隱藏選單區 -->
				
				<div class="collapse navbar-collapse navbar-ex1-collapse">
					<!-- 右選單 -->
					<img src="https://scontent-hkg3-1.xx.fbcdn.net/v/t1.0-1/c53.53.662.662a/s160x160/996812_623306544360262_513913499_n.jpg?_nc_cat=109&_nc_eui2=AeEvi_vj3AZ5wk2s31mtunvrLPbVPtJK2jf7uWRYtFCuPw_M1yTd23yuh2AGeVu5aGSm_1aLOh_81tqazaXh-ECnpuFl77aq8E38y3WIOxRGcA&_nc_ht=scontent-hkg3-1.xx&oh=c8b216f2429b70114bdb941b525f73cf&oe=5CA0CFE7" class="memberphoto" href="#"  alt="LOGO" style="float:right" width="80px" height="80px">
				
					<ul class="nav navbar-nav navbar-right membermenu">
						<li><a href="#">登出</a></li>
						<li><a href="#">個人設定</a></li>
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">繁體中文 <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="#">繁體中文</a></li>
								<li><a href="#">English</a></li>
								<li><a href="#">日本語</a></li>
							</ul>
						</li>
					</ul>
				</div>
				<!-- 手機隱藏選單區結束 -->
			</div>
		</nav>

		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-1">
										<%-- 錯誤表列 --%>
							<c:if test="${not empty errorMsgs}">
								<font style="color:red">請修正以下錯誤:</font>
									<ul>
	    							<c:forEach var="message" items="${errorMsgs}">
									<li style="color:red">${message}</li>
									</c:forEach>
									</ul>
							</c:if>

				</div><!-- <div class="col-xs-12 col-sm-1"> -->
				<div class="col-xs-12 col-sm-10">
					<div class="col-xs-12 col-sm-6">
						<img src="<%=request.getContextPath()%>/frontend/group_open/Group_openImg1.do?group_no=${group_openVO.group_no}" class="img-thumbnail">


					</div><!-- <div class="col-xs-12 col-sm-6"> -->
					<div class="col-xs-12 col-sm-6">
						


					</div><!-- <div class="col-xs-12 col-sm-6"> -->			
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
						</tbody>
					</table>


					       
					      </div><!-- <div class="panel-body"> -->
					    </div><!-- <div id="aaa" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="panel1"> -->
					  </div><!-- <div class="panel panel-default"> -->	
					</div><!-- <div class="panel-group" id="accordion2" role="tablist" aria-multiselectable="true"> -->
					<br>
					<br>
					<br>
					
					<div class="col-xs-12 col-sm-6">

						<div class="panel panel-info">
						  <div class="panel-heading">
						    <h3 class="panel-title">團購訂單</h3>
						  </div>
						  <div class="panel-body">
						    <div class="form-group">
  								<label for="usr">收件人地址</label>
  								<input type="text" class="form-control" id="usr">
							</div><!-- <div class="form-group"> -->
						  </div>
						  <div class="panel-body">
						       <div class="form-group">
  									<label for="usr">收件人電話</label>
  									<input type="text" class="form-control" id="usr">
								</div><!-- <div class="form-group"> -->
						  </div>
						  <div class="panel-body">
						   <div class="form-group">
 							 <label for="sel1"></label>
  								<select class="form-control" id="sel1">
   									 <option>超商取貨</option>
    								 <option>宅配</option>
  								</select>
						   </div>
						  </div><!-- <div class="panel-body"> -->
						</div><!-- <div class="panel panel-info"> -->
						
					</div><!-- <div class="col-xs-12 col-sm-6"> -->
					<div class="col-xs-12 col-sm-6">
						<table class="table table-hover">
							<caption>訂單總計</caption>
							<tbody>
								<tr>
									<td>總計數量</td>
									<td>
									<%=product%>		
									</td>
								</tr>
								<tr>
									<td>總計價格</td>
									<td><%=total%></td>
								</tr>
								<tr> 
									<td></td>
									<td>
									<button type="button" class="btn btn-info">開團成功</button>
									</td>
								</tr>
							</tbody>
						</table>
						
					</div><!-- <div class="col-xs-12 col-sm-3"> -->
				






				</div><!-- <div class="col-xs-12 col-sm-10"> -->
				<div class="col-xs-12 col-sm-1">
					




				</div><!-- <div class="col-xs-12 col-sm-1"> -->
				


			</div><!-- <div class="row"> -->
		</div><!-- <div class="container"> -->
		
		
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	</body>
</html>