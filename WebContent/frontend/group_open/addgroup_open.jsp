<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.group_open.model.*"%>  

<% 
	Group_openVO group_openVO = (Group_openVO) request.getAttribute("group_openVO");

%>

 
<!DOCTYPE html>
<html >
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">  
		<title>Title Page</title>
		<script src="https://code.jquery.com/jquery.js"></script>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" /> 
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath()%>/vendor/datetimepicker/jquery.js"></script>
		<script src="<%=request.getContextPath()%>/vendor/datetimepicker/jquery.datetimepicker.full.js"></script>
		<!-- <script>CKEDITOR.replace('content', {});</script> -->
		<script src="<%=request.getContextPath()%>/vendor/ckeditor_full/ckeditor.js"></script>

		<style>
  		.xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
 						 }
  		.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
 						 }
		</style>

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
		.groupimages{
			border-radius: 50px;
			margin-top:20px;
			
		}
		.contain{
			width: 20px;
			height:20px;
		}
		.img-responsive{
			width: 100%;
			max-width: none;
		}
		</style>
		
<script>
$.datetimepicker.setLocale('zh'); // kr ko ja en
$(function(){
	 $('#start_dateTime').datetimepicker({
	  format:'Y-m-d H:i:s',
	  onShow:function(){
	   this.setOptions({
	    maxDate:$('#end_dateTime').val()?$('#end_dateTime').val():false
	   })
	  },
	  timepicker:true,
	  step: 1
	 });
	 
	 $('#end_dateTime').datetimepicker({
	  format:'Y-m-d H:i:s',
	  onShow:function(){
	   this.setOptions({
	    minDate:$('#start_dateTime').val()?$('#start_dateTime').val():false
	   })
	  },
	  timepicker:true,
	  step: 1
	 });
	 
	 $('#group_dateTime').datetimepicker({
		  format:'Y-m-d H:i:s',
		  onShow:function(){
		   this.setOptions({
		    minDate:$('#end_dateTime').val()?$('#end_dateTime').val():false
		   })
		  },
		  timepicker:true,
		  step: 1
		 }); 
	     }); 
		var loadFile = function(event) {
		var output = document.getElementById('output');
		output.src = URL.createObjectURL(event.target.files[0]);
		};

		var loadFile2 = function(event) {
		var output = document.getElementById('output1');
		output.src = URL.createObjectURL(event.target.files[0]);
		};
		function codeAddress(){

			var add = document.getElementById("group_address");

			var Longitude = document.getElementById("longitude");

			var Latitude = document.getElementById("latitude");

			var geocoder = new google.maps.Geocoder();

			geocoder.geocode( {
								 address: add.value}, 
								function(results, status) 
							{

							if (status == google.maps.GeocoderStatus.OK) {

						Longitude.value = results[0].geometry.location.lng();

						Latitude.value = results[0].geometry.location.lat();	

						} else {

						alert("Geocode was not successful for the following reason: " + status);
								}
								});
								}
			
</script>	
		




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
						<li><a href="group_open.jsp">回首頁</a></li>
					</ul>
				</div>
				<!-- 手機隱藏選單區結束 -->
			</div>
		</nav>


		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-2">
		<c:if test="${not empty errorMsgs}">
			<font style="color:red">請修正以下錯誤:</font>
				<ul>
				<c:forEach var="message" items="${errorMsgs}">
				<li style="color:red">${message}</li>
				</c:forEach>
				</ul>
		</c:if>
		
				
				
					
				</div>
				<div class="container">
				<div class="row">
				<div class="col-xs-12 col-sm-8">
		      
		      
		      
		      
		      <div class="panel panel-default">
		       <div class="panel-heading">
		   		 <h3 class="panel-title">我要開團</h3>
				  </div><!-- <div class="panel-heading"> -->
			  <div class="panel-body">
			 
			 	 <div class="col-xs-12 col-sm-6">
					商品封面
				<div><img id="output" height="150" width="300" /></div>
				</div><!-- <div class="col-xs-12 col-sm-6"> -->
				<div class="col-xs-12 col-sm-6">
				
					商品圖片
			    <div><img id="output1" height="200" width="200"/></div> 
				</div><!-- <div class="col-xs-12 col-sm-6"> -->	 
		
		       <Form METHOD="post" ACTION="<%=request.getContextPath()%>/frontend/group_open/group_open.do" name="form1" enctype="multipart/form-data">
				<!-- 驗證到這邊 -->
				<div class="form-group">
					<i class="glyphicon glyphicon-user"></i><label for="member_no">會員編號</label>
					<input type="text" name="member_no" id="member_no" class="form-control"
					value="<%= (group_openVO==null)? "M000004" : group_openVO.getMember_no()%>"/>
				</div>
				<div class="form-group">
					<i class="glyphicon glyphicon-gift"></i><label for="goods_no">商品編號</label>
					<input type="text" name="goods_no" id="goods_no" class="form-control"
					value="<%= (group_openVO==null)? "P0000006" : group_openVO.getGoods_no()%>"/>
				</div>
				<div class="form-group">
					<label for="group_name">開團名稱</label>
					<input type="text" name="group_name" id="group_name" class="form-control"
					value="<%= (group_openVO==null)? "五月天螢光棒" : group_openVO.getGroup_name()%>"/>
				</div>
				<div class="form-group">
					<label for="group_limit">開團下限</label>
					<input type="text" name="group_limit" id="group_limit" class="form-control"
					value="<%= (group_openVO==null)? 10 : group_openVO.getGroup_limit()%>"/>
				</div>
				<div class="form-group">
					<label for="group_introduction">開團介紹</label>
					<!-- <textarea name="group_introduction" id="group_introduction" class="form-control"> -->	
					<textarea name="group_introduction" id="group_introduction" rows="10" cols="80"></textarea>
					<!-- </textarea> -->
				</div>
				<div class="form-group">
					<label for="group_mind">開團注意事項</label>
					<!-- <textarea name="group_mind" id="group_mind" class="form-control"> -->
					<textarea name="group_mind" id="group_mind" rows="10" cols="80"></textarea>	
					<!-- </textarea> -->
				</div>
				<div class="form-group">
					<i class="glyphicon glyphicon-time"></i><label for="start_dateTime">開團開始時間</label>
					<input type="text" name="group_start_date" id="start_dateTime" class="form-control"/>
				</div>
				<div class="form-group">
					<i class="glyphicon glyphicon-time"></i><label for="end_dateTime">開團結束時間</label>
					<input type="text" name="group_close_date" id="end_dateTime" class="form-control"/>
				</div>
				<div class="form-group">
   					 <label for="group_banner_1">開團封面1</label>
    					<input type="file" name="group_banner_1" class="form-control-file" id="group_banner_1"  onchange="loadFile(event)"/>
  				</div>
  				<div class="form-group">
   					 <label for="group_banner_2">開團封面2</label>
    					<input type="file" name="group_banner_2" class="form-control-file" id="group_banner_2"  onchange="loadFile2(event)"/>
  				</div>
  				
  			   <label for="group_status">開團狀態</label>
  			<select class="form-control" name="group_status" id="group_status">
  				<option value="process1">process1</option>
  				<option value="fail2">fail2</option>
  				<option value="sucess3">sucess3</option>
			</select>
			<div class="form-group">
					<label for="group_address">面交地址</label>
					<input name="group_address" type="text" id="group_address" size="50" maxlength="50" onchange="codeAddress()"class="form-control"/>
		<!-- 			<input type="text" name="group_address" id="group_address" class="form-control"/> -->
			</div>
			<div class="form-group">
					<label for="latitude">緯度</label>
					<input name="latitude" type="text" id="latitude" value="<%=(group_openVO==null)? "25.0177684": group_openVO.getLatitude()%>" class="form-control"/>
					<%-- <input type="text" name="latitude" id="latitude" class="form-control"
					value="<%=(group_openVO==null)? "25.0177684": group_openVO.getLatitude()%>"/> --%>
			</div>
				<div class="form-group">
					<label for="longitude">經度</label>
					<input name="longitude" type="text" id="longitude" value="<%=(group_openVO==null)? "121.2998": group_openVO.getLongitude()%>" class="form-control"/>
					<%-- <input type="text" name="longitude" id="longitude" class="form-control"
					value="<%=(group_openVO==null)? "121.2998": group_openVO.getLongitude()%>"/> --%>
			</div>
			<div class="form-group">
				<i class="glyphicon glyphicon-time"></i><label for="group_time">面交時間</label>
				<input type="text" name="group_time" id="group_dateTime" class="form-control"/>
			</div>
			<div class="form-group">
				<i class="glyphicon glyphicon-piggy-bank"></i><label for="group_price">商品促銷價格</label>
				<input type="text" name="group_price" id="group_price" class="form-control"/>
			</div>
			<div class="form-group">
				<i class="glyphicon glyphicon-piggy-bank"></i><label for="group_quantity">商品購買數量</label>
				<input type="text" name="group_quantity" id="group_quantity" class="form-control"/>
			</div>
			
			
			<input type="hidden" name="action" value="insert2">
			<input type="hidden" name="group_member_status" value="grouplead">
			<input type="submit" value="送出新增">
			

			
			</Form>
				</div><!-- <div class="panel-body"> -->
			</div><!-- <div class="panel panel-default"> -->
				</div>
				<div class="col-xs-12 col-sm-2">
				<img id="output1" />
				<img id="output"/>
				
				
				
				
			  </div>
			</div>
		   </div>
		   <br>
		  <br>
		</div>
		<script>
			CKEDITOR.replace( 'group_mind', {});
			CKEDITOR.replace( 'group_introduction', {});
		</script>
		<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAb2lDof7yMn-TTXwt2hwVm4y92t1AqvyU&callback=initMap&libraries=places" async defer></script>
	</body>
</html>


<!-- 	<div class="panel panel-default">
		  <div class="panel-heading">
		    <h3 class="panel-title">標題</h3>
		  </div><div class="panel-heading">
		  <div class="panel-body">
		    內容文字
		  </div><div class="panel-body">
		</div><div class="panel panel-default"> -->
