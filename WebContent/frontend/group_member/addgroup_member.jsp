<%@page import="com.forum.model.ForumService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.group_member.model.*"%>  
<%@ page import="com.group_open.model.*"%>  
<%@ page import="com.forum.model.*"%>
<%@ page import="com.member.model.*"%>
<%

	Group_memberVO group_memberVO = (Group_memberVO) request.getAttribute("group_memberVO");
	Group_openVO group_openVO = (Group_openVO) request.getAttribute("group_openVO");
	ForumService forumSvc = new ForumService();
	
	 List <ForumVO> list = forumSvc.getall_forum_by_group(group_openVO.getGroup_no());
	 pageContext.setAttribute("list", list);
	 
	 MemberVO memberVOsession = (MemberVO)session.getAttribute("member");
	 
		MemberService memberSvc = new MemberService();
		List<MemberVO> list2 =  memberSvc.getAll();
		pageContext.setAttribute("list2", list2);
%>
<jsp:useBean id="group_memberSvc" scope="page" class="com.group_member.model.Group_memberService" />
<jsp:useBean id="group_openSvc" scope="page" class="com.group_open.model.Group_openService" />
<jsp:useBean id="goodsSvc" scope="page" class="com.goods.model.GoodsService" />

<!DOCTYPE html>
<html lang="">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<title>Title Page</title>
		<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
		<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
		<style>
		.custom_class > .custom_class {
    		background-image: none;
    		background-color: #ADD8E6;
    		color: black;

		}
		.people{
			width:100px;
			height:100px;
		}
		.int{
			margin-right:40px;
		}
		#map {
        height: 400px;
        width: 100%;
       }
       	#messageform{
				background-color:#F5F5F5;
				width: 400px;
				height: 100px;
				border-radius: 10px;
			}
			.img-circle{
				width:60px;
				height:60px;
			}
			.btn{
				float: right;

			}
			.message{

			}
			#responsearea{
				background-color:#F5F5F5;
				border-radius: 10px;
				width:auto;
				height:auto;
			
			}
			.res{
				width:50px;
				height:50px;
			}
			.forum{
				height:500px;
				overflow:scroll;
			}
			#responseareax{
				background-color:#F5F5F5;
				border-radius: 10px;
				width:auto;
				height:auto;
				direction: rtl;
			
			}
		</style>
		<script>
		$(function(){
			$('#btn').on('click', function(){
			/* 	 var content = $('#message').val(); 
				var group_no = $('#group_no').val();
				var member_no = $('#member_no').val(); */
				$.ajax({
					url: '<%=request.getContextPath()%>/frontend/forum/forum.do',
					type: "get",
					data: { 
						action: 'ask',
						content: $('#message').val() ,
						group_no: $('#group_noms').val(),
						member_no:$('#member_noms').val()
						  },
					dataType: 'json',
					success: function(res){
						console.log(res);
						$('.forum').append("<div class='panel-body'>"+
					     "<div id='responseareax'>"+
							"<img src='<%=request.getContextPath()%>/member/memberImg.do?memberno=<%=memberVOsession.getMemberNo()%>' class='img-circle res' style='text-align:right';>"+
								"<span style='text-align:right'>"+res.answer+"</span> &nbsp;"+
								"<span><%=memberVOsession.getMemberFullname()%></span>"+
								"<h5><span>"+res.time+"</span></h5>"+
						 "</div>"+"</div>");	
					},
					error: function(res){
						$('#showmessage').text('Error! Could not reach the API. ');
					}
				});
			
			});
		});
		</script>
	</head>
	<body>
			<jsp:include page="/frontend/navbar_front-end.jsp" flush="true" />	
							<div class="container">
								<div class="row">
										<%-- 錯誤表列 --%>
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
					</div><!-- <div class="col-xs-12 col-sm-1"> -->
					<div class="col-xs-12 col-sm-10">
						<div class="col-xs-12 col-sm-6">
						<%-- 	<img src="Group_openImg1.do?group_no=${group_openVO.group_no}" width="400" height="200"> --%>
							<img src="<%= (group_openVO==null)? null : request.getContextPath()+"/frontend/group_open/Group_openImg1.do?group_no="+group_openVO.getGroup_no()%>" width="400" height="200">

						</div><!-- <div class="col-xs-12 col-sm-6"> -->
						<div class="col-xs-12 col-sm-6">
								<br>
								<br>
								<br>
							<div><span> 開團名稱</span>:${group_openVO.group_name}</div>	
							<div><span> 商品名稱</span>
											<c:forEach var="goodsVO" items="${goodsSvc.getAll()}">
												<c:if test="${group_openVO.goods_no==goodsVO.goods_no}">
	                   									${goodsVO.goods_name}
                    							</c:if>	
											</c:forEach>				
							</div>	
							<div><span> 商品價格</span>
											<c:forEach var="goodsVO" items="${goodsSvc.getAll()}">		
												<c:if test="${group_openVO.goods_no==goodsVO.goods_no}">
	                   									${goodsVO.goods_price}
                    							</c:if>	
											</c:forEach>	
							</div>	
							<div><span> 折扣後商品價格</span>:${group_openVO.group_price}</div>	
							<div><span> 目前跟團人數</span>:
											<c:forEach var="group_openmap" items="${group_openSvc.member_count}">
												<c:if test="${group_openVO.group_no==group_openmap.key}">
	                   							${group_openmap.value}
                    							</c:if>	
											</c:forEach>												
							</div>	
							<div>				<span> 售出數量</span>:
												<c:forEach var="group_membermap" items="${group_memberSvc.group_quantity}">
												<c:if test="${group_openVO.group_no==group_membermap.key}">
	                   							${group_membermap.value}
                    							</c:if>	
                    							</c:forEach>
							</div>
							
						</div><!-- <div class="col-xs-12 col-sm-6"> -->
						

					</div><!-- <div class="col-xs-12 col-sm-10"> -->
					<div class="col-xs-12 col-sm-1">
						
					</div><!-- <div class="col-xs-12 col-sm-1"> -->
				</div><!-- <div class="row"> -->
					</div><!-- <div class="container"> -->
			
				<br>
				<br>
		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-2">
				</div><!-- <div class="col-xs-12 col-sm-2"> -->
				<div class="col-xs-12 col-sm-9">

					<div class="panel panel-default custom_class">
					  		<div class="panel-heading custom_class">
					   			 <h3 class="panel-title">合購介紹</h3>
					  		</div><!--  <div class="panel-heading custom_class"> -->
					    <div class="panel-body">
					      	<div class="row">					      
								<div class="col-xs-12 col-sm-2">					
								<%-- 	<img src="Group_openImg2.do?group_no=${group_openVO.group_no}" class="img-circle people"> --%>
									<img src="<%= (group_openVO==null)? null : request.getContextPath()+"/frontend/group_open/Group_openImg2.do?group_no="+group_openVO.getGroup_no()%>" class="img-circle people" >
								</div><!-- <div class="col-xs-12 col-sm-2"> -->
								<div class="col-xs-12 col-sm-10">					
										<div class="well well-sm">
											${group_openVO.group_name}
										</div><!-- <div class="well well-sm"> -->	
								</div><!-- <div class="col-xs-12 col-sm-10"> -->	
							</div><!-- <div class="row"> -->
							<br>
							<br>
							<div role="tabpanel">
								    <!-- 標籤面板：標籤區 -->
								    <ul class="nav nav-tabs" role="tablist">
								        <li role="presentation" class="active">
								            <a href="#tab1" aria-controls="tab1" role="tab" data-toggle="tab">合購說明</a>
								        </li>
								        <li role="presentation">
								            <a href="#tab2" aria-controls="tab2" role="tab" data-toggle="tab">注意事項</a>
								        </li>
								        <li role="presentation">
								            <a href="#tab3" aria-controls="tab3" role="tab" data-toggle="tab">面交地點</a>
								        </li>
								    </ul>
								
								    <!-- 標籤面板：內容區 -->
								    <div class="tab-content">
								        <div role="tabpanel" class="tab-pane active" id="tab1">${group_openVO.group_introduction}
								        </div>
								        <div role="tabpanel" class="tab-pane" id="tab2">${group_openVO.group_mind}
								        </div>
								        <div role="tabpanel" class="tab-pane" id="tab3"><h3>${group_openVO.group_address}</h3><div id="map"></div>
								        </div>
								    </div><!-- <div class="tab-content"> -->
								</div><!-- <div role="tabpanel"> -->
					  </div><!-- <div class="panel-body"> -->
					</div><!-- <div class="panel panel-default"> -->
				</div>
				<div class="col-xs-12 col-sm-1">
				</div><!-- <div class="col-xs-12 col-sm-1"> -->
			</div><!-- <div class="row"> -->
		</div><!-- <div class="container"> -->
			<br>
			<div class="container">
				<div class="row">
					<div class="col-xs-12 col-sm-2">
						


					</div><!-- <div class="col-xs-12 col-sm-2"> -->
					<div class="col-xs-12 col-sm-9">
						<div class="panel panel-default">
						  	<div class="panel-heading">
						    <h3 class="panel-title">跟團訂購單</h3>
						  	</div><!-- <div class="panel-heading"> -->
						<div class="panel-body">						
<%-- 						<%request.getContextPath();%>/frontend/group_member/ --%>
						<Form METHOD="post" ACTION="<%=request.getContextPath()%>/frontend/group_member/group_member.do" name="form1"><!-- 表單開始 -->
								<!-- 選項開始 -->
							<div class="form-group">
  								<!-- <label for="member_no">
  									會員編號
  								</label> -->
  									<input name="member_no" type="hidden" class="form-control" id="member_no"
  									value="<%=memberVOsession.getMemberNo()%>"/>
							</div><!-- <div class="form-group"> -->	
								<!-- 選項結束 -->
								<!-- 選項開始 -->
							<div class="form-group">
  							<!-- 	<label for="usr">
  									開團編號
  								</label> -->
  									<input name="group_no" type="hidden" class="form-control" id="group_no"
  									value="${group_openVO.group_no}">
							</div><!-- <div class="form-group"> -->	
								<!-- 選項結束 -->
								<!-- 選項開始 -->
							<div class="form-group">
  								<label for="product_quantity">
  									購買數量
  								</label>
  									<input name="product_quantity" type="text" class="form-control" id="product_quantity"
  									value="<%= (group_memberVO==null) ? "請輸入購買數量" : group_memberVO.getProduct_quantity()%>">
							</div><!-- <div class="form-group"> -->	
								<!-- 選項結束 -->

								<!-- 選項開始 -->
							<div class="form-group">
  								<label for="usr">
  									聯絡電話
  								</label>
  									<input name="order_phone" type="text" class="form-control" id="usr"
  									value="<%= (group_memberVO==null) ? "請輸入聯絡電話" : group_memberVO.getOrder_phone()%>">
							</div><!-- <div class="form-group"> -->	
								<!-- 選項結束 -->

								<!-- 選項開始 -->
						 <div class="form-group">
     						 <label for="sel1">Select list (select one):</label>
      							<select name="pay_method" class="form-control" id="sel1">
       							  <option value="EWALLET">EWALLET電子錢包</option>
      							  <option value="CREDITCARD">CREDITCARD信用卡</option>   							 
        						</select>
        						<br>
								<!-- 選項結束 -->
							<input type="hidden" name="group_member_status" value="withgroup">
							<input type="hidden" name="pay_status" value="COMPLETE2">
							<input type="hidden" name="action" value="insert2">
							<input type="hidden" name="goods_no" value="${group_openVO.goods_no}">
							<input type="submit" value="送出跟團單" class="btn btn-success">
						</FORM><!-- 表單結束 -->
						</div><!-- <div class="panel-body"> -->
					</div><!-- <div class="panel panel-default"> -->
					
					</div><!-- <div class="col-xs-12 col-sm-9"> -->
					
					<div class="col-xs-12 col-sm-1">
						


					</div><!-- <div class="col-xs-12 col-sm-1"> -->
					
						<br>
						<div class="panel panel-success forum">
					  <div class="panel-heading">
					    <h3 class="panel-title">討論區</h3>
					  </div><!-- <div class="panel-heading"> -->
				 <c:forEach var="forumVO" items= "${list}" > 
				 				 		
				 		<c:if test="${forumVO.member_no==sessionScope.member.getMemberNo()}">
    					     <div class="panel-body"><!-- foreach開始 -->
					     <div id="responseareax">
					     <c:forEach var="memberVO" items="${list2}">
					        <c:if test="${forumVO.member_no==memberVO.memberNo}">
							<img src="<%=request.getContextPath()%>/member/memberImg.do?memberno=${forumVO.member_no}" class="img-circle res" style="text-align:right;">
							</c:if>	
						 </c:forEach>		
						 <span style="text-align:right;">${forumVO.forum_content}</span>			 
							<span>
							  <c:forEach var="memberVO" items="${list2}" >
					        <c:if test="${forumVO.member_no==memberVO.memberNo}">
								${memberVO.memberFullname}
							</c:if>	
						 	   </c:forEach>
							</span>
						 </div><!-- <div id="responsearea"> -->
					  </div><!-- <div class="panel-body"> --><!-- foreach結束 -->     
						</c:if> 
						<!--跟來這頁的跟團人做比對 不來這頁跟團人  -->
						<c:if test="${forumVO.member_no!=sessionScope.member.getMemberNo()}"> 
   					  			<div class="panel-body"><!-- foreach開始 -->
					     <div id="responsearea">
					     <c:forEach var="memberVO" items="${list2}">
					        <c:if test="${forumVO.member_no==memberVO.memberNo}">
							<img src="<%=request.getContextPath()%>/member/memberImg.do?memberno=${forumVO.member_no}" class="img-circle res">
							</c:if>	
						 </c:forEach>					 
							<span>
							  <c:forEach var="memberVO" items="${list2}">
					        <c:if test="${forumVO.member_no==memberVO.memberNo}">
								${memberVO.memberFullname}
							</c:if>	
						 	   </c:forEach>
							</span>
								<span>${forumVO.forum_content}</span>
								<h5><span>${forumVO.forum_time}</span></h5>
						 </div><!-- <div id="responsearea"> -->
					  </div><!-- <div class="panel-body"> --><!-- foreach結束 -->
						</c:if> 			 
					  </c:forEach> 
				</div><!-- <div class="panel panel-success"> -->
					<div id="messageform">
					
					
							<img src="<%=request.getContextPath()%>/member/memberImg.do?memberno=<%=memberVOsession.getMemberNo()%>" class="img-circle"><%=memberVOsession.getMemberFullname()%>
																				
							
							<br>
						<input type="text" class="form-control" placeholder="回覆" id="message">
						<input type="hidden" class="form-control" id="group_noms" value="${group_openVO.group_no}">         
						<input type="hidden" class="form-control" id="member_noms" value="<%=memberVOsession.getMemberNo()%>">
						<button type="button" class="btn btn-info" id="btn">留言</button>
					</div><!-- <div id="messageform"> -->

					
				</div><!-- <div class="row"> -->
			</div><!-- <div class="container"> -->
	
	
		
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAb2lDof7yMn-TTXwt2hwVm4y92t1AqvyU&callback=initMap" async defer></script>
		<script type="text/javascript">
		function initMap() {
    		var uluru = {
    						lat: <%=group_openVO.getLatitude()%>, 
    						lng: <%=group_openVO.getLongitude()%>
    					};
    		var map = new google.maps.Map(document.getElementById('map'), {
    			  zoom: 12,
     			  center: uluru
   				 });
   			 var marker = new google.maps.Marker({
		          position: uluru,
		          map: map,
  				  });
 				 }

		</script>
			
	</body>
	<jsp:include page="/frontend/footer_front-end.jsp" flush="true" />
</html>