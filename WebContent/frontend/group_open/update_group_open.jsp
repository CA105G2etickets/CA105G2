<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.group_open.model.*"%>  
<%@ page import="com.group_member.model.*"%>  
<%@ page import="com.member.model.*"%>

<% 
	Group_openVO group_openVO = (Group_openVO) request.getAttribute("group_openVO");
	Group_memberVO group_memberVO = (Group_memberVO) request.getAttribute("group_memberVO");
	MemberVO memberVOsession = (MemberVO)session.getAttribute("member");
%>
<jsp:useBean id="eventtitleSvc" scope="page" class="com.event_title.model.EventTitleService" />
<jsp:useBean id="group_openSvc" scope="page" class="com.group_open.model.Group_openService" />
<jsp:useBean id="goodsSvc" scope="page" class="com.goods.model.GoodsService" />
 
<!DOCTYPE html>
<html >
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">  
		<title>修改開團紀錄</title>
		<script src="https://code.jquery.com/jquery.js"></script>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
		<script src="<%=request.getContextPath()%>/vendor/ckeditor_easyImage_final/ckeditor.js"></script>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" /> 
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
 		<script src="<%=request.getContextPath()%>/vendor/datetimepicker/jquery.js"></script>
		<script src="<%=request.getContextPath()%>/vendor/datetimepicker/jquery.datetimepicker.full.js"></script> 
		<!-- <script>CKEDITOR.replace('content', {});</script> -->

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
				#map {
  height: 430px;
  position: relative;
  width: 100%;
}

.maps-frame {
  height: 430px;
  width: 100%;
}
      #floating-panel {
        position: absolute;
        top: 10px;
        left: 25%;
        z-index: 5;
        background-color: #fff;
        padding: 5px;
        border: 1px solid #999;
        text-align: center;
        font-family: 'Roboto','sans-serif';
        line-height: 30px;
        padding-left: 10px;
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
			var reader = new FileReader();
			reader.readAsDataURL(event.target.files[0]);
			reader.onload = function () {
				output.src = this.result;
				document.getElementById('url1').value = this.result;
			};
			
// 			output.src = URL.createObjectURL(event.target.files[0]);
		};

		var loadFile2 = function(event) {
			var output = document.getElementById('output1');
// 		output.src = URL.createObjectURL(event.target.files[0]);
			var reader = new FileReader();
			reader.readAsDataURL(event.target.files[0]);
			reader.onload = function () {
				output.src = this.result;
				document.getElementById('url2').value = this.result;
			};
		};
		function codeAddress(){

			var add = document.getElementById("address");

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
		<jsp:include page="/frontend/navbar_front-end.jsp" flush="true" />
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
		      
		      
		      
		      
		      <div class="panel panel-warning">
		       <div class="panel-heading">
		   		 <h3 class="panel-title">我要修改團購資訊</h3>
				  </div><!-- <div class="panel-heading"> -->
			  <div class="panel-body">
			 
			 	 <div class="col-xs-12 col-sm-6">
					商品封面
				<div><img src="<%= (group_openVO==null)? null : request.getContextPath()+"/frontend/group_open/Group_openImg1.do?group_no="+group_openVO.getGroup_no()%>" id="output" height="150" width="300" /></div>
				</div><!-- <div class="col-xs-12 col-sm-6"> -->
				<div class="col-xs-12 col-sm-6">
				
					商品圖片
			    <div><img src="<%= (group_openVO==null)? null : request.getContextPath()+"/frontend/group_open/Group_openImg2.do?group_no="+group_openVO.getGroup_no()%>"id="output1" height="200" width="200"/></div>
				</div><!-- <div class="col-xs-12 col-sm-6"> -->	 
				<br>
<%-- 				<Form METHOD="post" ACTION="<%=request.getContextPath()%>/frontend/group_open/group_open.do" id="form1">
				<div class="form-group">
				 <label for="evetit">活動主題</label>	
					<select  name="evetit" id="evetit" class="form-control">
					<option value="-1">請選擇</option>
					<c:forEach var="eventtitleVO" items="${group_openSvc.geteventitle()}">
						 <option value="${eventtitleVO.evetit_no}"${eventtitleVO.evetit_no==param.evetit?'selected':''}>${eventtitleVO.evetit_name}</option>
					</c:forEach>
					</select> 
					<input type="hidden" name="action" value="getSelect2">
					<input type="hidden" name="member_no" value="<%=memberVOsession.getMemberNo()%>">
					<input type="hidden" name="group_no" value="<%=group_openVO.getGroup_no()%>">
					</div><!-- <div class="form-group"> -->
				</Form> --%>
		
		       <Form METHOD="post" ACTION="<%=request.getContextPath()%>/frontend/group_open/group_open.do" name="form1" enctype="multipart/form-data">
				<!-- 驗證到這邊 -->
			<%-- 	<div class="form-group">
					<i class="glyphicon glyphicon-user"></i><label for="member_no">會員編號</label>
					<input type="text" name="member_no" id="member_no" class="form-control"
					value="<%= (group_openVO==null)? "M000004" : group_openVO.getMember_no()%>"/>
				</div> --%>
				<%-- <div class="form-group">
					<i class="glyphicon glyphicon-gift"></i><label for="goods_no">商品編號</label>
					<input type="text" name="goods_no" id="goods_no" class="form-control"
					value="<%= (group_openVO==null)? "P0000006" : group_openVO.getGoods_no()%>"/>
				</div> --%>
				<div class="form-group">			
				 <label for="evetit">商品名稱</label>	
			<select id="goods" class="form-control" name="goods_no">
				<%-- <c:forEach var="eventtitlemap" items="${evetitle_goods}"> --%>
				<option value="<%=group_openVO.getGoods_no()%>"><%=goodsSvc.getOneGoods(group_openVO.getGoods_no()).getGoods_name()%></option>
				<%-- </c:forEach> --%>
			</select>			
				</div><!-- <div class="form-group"> -->	
				<div class="form-group">
					<label for="group_name">開團名稱</label>
					<input type="text" name="group_name" id="group_name" class="form-control"
					value="<%= (group_openVO==null)? "請輸入開團名稱" : group_openVO.getGroup_name()%>"/>
				</div>
				<div class="form-group">
					<!-- <label for="group_limit">開團下限</label> -->
					<input type="hidden" name="group_limit" id="group_limit" class="form-control"
					value="<%= (group_openVO==null)? 10 : group_openVO.getGroup_limit()%>"/>
				</div>
				<div class="form-group">
					<label for="group_introduction">開團介紹</label>
					<!-- <textarea name="group_introduction" id="group_introduction" class="form-control"> -->	
					<textarea name="group_introduction" id="group_introduction" rows="10" cols="80" <%= (group_openVO==null)? "請輸入開團介紹" : group_openVO.getGroup_introduction()%>></textarea>
					<!-- </textarea> -->
				</div>
				<div class="form-group">
					<label for="group_mind">開團注意事項</label>
					<!-- <textarea name="group_mind" id="group_mind" class="form-control"> -->
					<textarea name="group_mind" id="group_mind" rows="10" cols="80"<%= (group_openVO==null)? "請輸入注意事項" : group_openVO.getGroup_mind()%>></textarea>	
					<!-- </textarea> -->
				</div>
				<div class="form-group">
				<!-- 	<i class="glyphicon glyphicon-time"></i><label for="start_dateTime">開團開始時間</label> -->
					<input type="hidden" name="group_start_date" id="start_dateTime" class="form-control"/>
				</div>
				<div class="form-group">
					<i class="glyphicon glyphicon-time"></i><label for="end_dateTime">開團結束時間</label>
					<input type="text" name="group_close_date" id="end_dateTime" class="form-control"
					value="<%= (group_openVO==null)? "請輸入開團時間" : group_openVO.getGroup_close_date()%>"/>
				</div>
				<div class="form-group">
   					 <label for="group_banner_1">開團封面1</label>
    					<input type="file" name="group_banner_1" class="form-control-file" id="group_banner_1"  onchange="loadFile(event)"/>
  				</div>
  				<div class="form-group">
   					 <label for="group_banner_2">開團封面2</label>
    					<input type="file" name="group_banner_2" class="form-control-file" id="group_banner_2"  onchange="loadFile2(event)"/>
  				</div>
  				<div class="form-group">
   					<!--  <label for="group_status">開團狀態</label> -->
    					<input type="hidden" name="group_status" class="form-control-file" id="group_status" value="process1"/>
  				</div>			
  			<!--    <label for="group_status">開團狀態</label>
  			<select  class="form-control" name="group_status" id="group_status">
  				<option value="process1">process1</option>
  				<option value="fail2">fail2</option>
  				<option value="sucess3">sucess3</option>
			</select> -->
		<div class="form-group">
					<label for="group_address">面交地址</label>
					<input id="address"  name="group_address" type="textbox"  size="50" maxlength="50" onchange="codeAddress()"class="form-control" value="<%= (group_openVO==null) ? "請輸入面交地址" : group_openVO.getGroup_address()%>"/>
					<!-- <input type="text" name="group_address" id="group_address" class="form-control"/>  -->
					<input id="submit" type="button" value="Geocode">
			</div>
				<div class="form-group">
				<!--  <div id="floating-panel"> -->
      			<!-- 	<input id="address" type="textbox" value="Sydney, NSW">
     				 <input id="submit" type="button" value="Geocode"> -->
    		<!-- 	</div> -->
    				<div id="map"></div>
    			</div>
			<!-- <div class="form-group"> -->
					<!-- <label for="latitude">緯度</label> -->
					<input name="latitude" type="hidden" id="latitude" value="<%=(group_openVO==null)? "25.0177684": group_openVO.getLatitude()%>" class="form-control"/>
					<%-- <input type="text" name="latitude" id="latitude" class="form-control"
					value="<%=(group_openVO==null)? "25.0177684": group_openVO.getLatitude()%>"/> --%>
			<!-- </div> -->
			<!-- 	<div class="form-group"> -->
					<!-- <label for="longitude">經度</label> -->
					<input name="longitude" type="hidden" id="longitude" value="<%=(group_openVO==null)? "121.2998": group_openVO.getLongitude()%>" class="form-control"/>
					<%-- <input type="text" name="longitude" id="longitude" class="form-control"
					value="<%=(group_openVO==null)? "121.2998": group_openVO.getLongitude()%>"/> --%>
				<!--</div> -->
			<div class="form-group">
				<i class="glyphicon glyphicon-time"></i><label for="group_time">面交時間</label>
				<input type="text" name="group_time" id="group_dateTime" class="form-control" value="<%= (group_openVO==null)? "請輸入時間" : group_openVO.getGroup_time()%>"/>
			</div>
			<!-- <div class="form-group">
				<i class="glyphicon glyphicon-piggy-bank"></i><label for="group_price">商品促銷價格</label>
				<input type="text" name="group_price" id="group_price" class="form-control"/>
			</div> -->
			<div class="form-group">
				<i class="glyphicon glyphicon-piggy-bank"></i><label for="group_quantity">商品購買數量</label><%=group_openVO.getGroup_price()%>
				<input type="text" name="group_quantity" id="group_quantity" class="form-control" value="<%= (group_memberVO==null)? "請輸入購買數量" : group_memberVO.getProduct_quantity()%>"/>
			</div>	
			<input type="hidden" name="group_no" value="<%=group_openVO.getGroup_no()%>">
			<input type="hidden" name="member_no" value="<%=memberVOsession.getMemberNo()%>">
			<input type="hidden" name="group_member_status" value="grouplead">
			<input type="hidden" name="action" value="update">
			<input type="hidden" name="group_price" value="<%=group_openVO.getGroup_price()%>">
			<input type="submit" value="送出修改">
			</Form>
				</div><!-- <div class="panel-body"> -->
			</div><!-- <div class="panel panel-default"> -->
				</div>
				<div class="col-xs-12 col-sm-2">
	

			  </div>
			</div>
		   </div>
		   <br>
		  <br>
		</div>
		<script>
/* 			CKEDITOR.replace( 'group_mind', {});
			CKEDITOR.replace( 'group_introduction', {}); */
			CKEDITOR.replace( 'group_mind', {
				removePlugins: 'image',
				extraPlugins: 'easyimage',
				cloudServices_tokenUrl: 'https://36762.cke-cs.com/token/dev/UM6e5GmaKWwu89mPGLfo7csdkFHnd6pThBTSG6KZ3PZIOnotQZZOpXzO92Lu',
				cloudServices_uploadUrl: 'https://36762.cke-cs.com/easyimage/upload/'
			});
			CKEDITOR.replace( 'group_introduction', {
				removePlugins: 'image',
				extraPlugins: 'easyimage',
				cloudServices_tokenUrl: 'https://36762.cke-cs.com/token/dev/UM6e5GmaKWwu89mPGLfo7csdkFHnd6pThBTSG6KZ3PZIOnotQZZOpXzO92Lu',
				cloudServices_uploadUrl: 'https://36762.cke-cs.com/easyimage/upload/'
			});
		</script>
		<script type="text/javascript">
		$(document).ready(function(){
			 $('#evetit').change(function(){
				 $('#form1').submit();
			 })
		})

</script>
<script>
      function initMap() {
        var map = new google.maps.Map(document.getElementById('map'), {
          zoom: 14,
          center: {
        	  lat: ${group_openVO.latitude},
        	  
        	  lng: ${group_openVO.longitude}
        	  }
        });
        var geocoder = new google.maps.Geocoder();

        document.getElementById('submit').addEventListener('click', function() {
          geocodeAddress(geocoder, map);
        });
      }

      function geocodeAddress(geocoder, resultsMap) {
        var address = document.getElementById('address').value;
        geocoder.geocode({'address': address}, function(results, status) {
          if (status === 'OK') {
            resultsMap.setCenter(results[0].geometry.location);
            var marker = new google.maps.Marker({
              map: resultsMap,
              position: results[0].geometry.location
            });
          } else {
            alert('Geocode was not successful for the following reason: ' + status);
          }
        });
      }
    </script>
		<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAb2lDof7yMn-TTXwt2hwVm4y92t1AqvyU&callback=initMap&libraries=places" async defer></script>
		<jsp:include page="/frontend/footer_front-end.jsp" flush="true" />
	</body>
</html>