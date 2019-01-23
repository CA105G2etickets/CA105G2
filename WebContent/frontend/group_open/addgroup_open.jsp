<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.group_open.model.*"%>  
<%@ page import="com.member.model.*"%>
<%@ page import="com.goods.model.*"%>

<% 
	Group_openVO group_openVO = (Group_openVO) request.getAttribute("group_openVO");
	MemberVO memberVOsession = (MemberVO)session.getAttribute("member");
	String url1 = (String) request.getAttribute("url1");
	String url2 = (String) request.getAttribute("url2");
	GoodsVO goodsVO = (GoodsVO) request.getAttribute("goodsVO");
%>
<jsp:useBean id="eventtitleSvc" scope="page" class="com.event_title.model.EventTitleService" />
<jsp:useBean id="group_openSvc" scope="page" class="com.group_open.model.Group_openService" />
 
<!DOCTYPE html>
<html >
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">  
		<title>我要開團</title>
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
var date = new Date();
$(function(){
	 $('#start_dateTime').datetimepicker({
	  format:'Y-m-d H:i:s',
	  onShow:function(){
	   this.setOptions({
		 /*   minDate:0, */
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
		  minDate:0,
	  /*   minDate:$('#start_dateTime').val()?$('#start_dateTime').val():false */
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
			
			console.log("經緯度166行")

			geocoder.geocode( {
								 address: add.value}, 
								function(results, status) 
								
								
							{

							if (status == google.maps.GeocoderStatus.OK) {

						Longitude.value = results[0].geometry.location.lng();

						Latitude.value = results[0].geometry.location.lat();	
						console.log("經緯度180行")

						} else {

						alert("Geocode was not successful for the following reason: " + status);
								}
								});
								}
			
</script>	
		<script type="text/javascript">
	$(document).ready(function(){
		 $('#evetit').change(function(){
			 $.ajax({
				 type: "POST",
				 url: "group_open.do",
				 data: creatQueryString($(this).val()),
				 dataType: "json",
				 success: function (data){
					clearSelect();
					$.each(data, function(i,item){
						$('#goods').append("<option value='"+item.goods_no+"'>"+item.goods_name+"</option>");
						/* console.log(item);/* object */ 
						console.log(item.goods_no);
						console.log(item.goods_name);
					});
			     },
	             error: function(){alert("AJAX-goods發生錯誤囉!")}
	         })
		 })
		 //設定預覽照片
		  $('#goods').change(function(){
			 $.ajax({
				 type: "POST",
				 url: "group_open.do",
				 data:{action:"getimages",
					  goods_no:$('#goods').val()
					 	},
				 dataType: 'json',
				 success: function (data){
					$.each(data, function(i,item){
					/* 	$('#goods').append("<option value='"+item.goods_no+"'>"+item.goods_name+"</option>"); */
						$('#output').attr("src","<%=request.getContextPath()%>"+"/goods/goodsImg1.do?goods_no="+item.goods_no);
						$('#output1').attr("src","<%=request.getContextPath()%>"+"/goods/goodsImg2.do?goods_no="+item.goods_no);
						/* console.log(item);/* object */ 
						console.log(item.goods_no);
					});
			     },
	             error: function(){alert("AJAX-goods發生錯誤囉!")}
	         })
		 })
	})
	
	function creatQueryString(paramEvetit){
		console.log("paramEvetit:"+paramEvetit);/* 三年級 */
		var queryString= {"action":"getSelectajax", "evetit":paramEvetit};
		return queryString;
	}
 	function clearSelect(){
		$('#goods').empty();
		$('#goods').append("<option value='-1'>請選擇</option>");

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
		      
		      
		      
		      
		      <div class="panel panel-default">
		       <div class="panel-heading">
		   		 <h3 class="panel-title">我要開團</h3>
				  </div><!-- <div class="panel-heading"> -->
			  <div class="panel-body">
			 
			 	 <div class="col-xs-12 col-sm-6">
					商品封面
				<div>
				<c:if test="${goodsVO.goods_no==null}">
					<img id="output" height="150" width="300"  src="<%= (group_openVO==null)? request.getContextPath()+"/NoData/imageComingSoon.jpg" : request.getContextPath()+"/frontend/group_open/Group_openImg1.do?group_no="+group_openVO.getGroup_no()%>"/>
				<%-- <img id="output" height="150" width="300"  src="<%= (url1 == null)? null : url1 %>"/> --%>
				 </c:if>
				 <c:if test="${goodsVO.goods_no!=null}">
					 <img id="output1" height="150" width="300" src="<%=request.getContextPath()%>/goods/goodsImg1.do?goods_no=${goodsVO.goods_no}"/>
				 </c:if>
				
				</div>
				</div><!-- <div class="col-xs-12 col-sm-6"> -->
				<div class="col-xs-12 col-sm-6">
					商品圖片
			    <div>
			    <c:if test="${goodsVO.goods_no==null}">
		    <img id="output1" height="200" width="200" src="<%= (group_openVO==null)? request.getContextPath()+"/NoData/imageComingSoon.jpg" : request.getContextPath()+"/frontend/group_open/Group_openImg2.do?group_no="+group_openVO.getGroup_no()%>"/> 
			   <!--  <img id="output1" height="200" width="200"/> -->
			    </c:if>
			     <c:if test="${goodsVO.goods_no!=null}">
					 <img id="output1" height="200" width="200" src="<%=request.getContextPath()%>/goods/goodsImg2.do?goods_no=${goodsVO.goods_no}"/>
				 </c:if>
			    
			    </div>
				</div><!-- <div class="col-xs-12 col-sm-6"> -->	 
				<br>
				<%-- <Form METHOD="post" ACTION="<%=request.getContextPath()%>/frontend/group_open/group_open.do" id="form1" > --%>
				<div class="form-group">
				  <c:if test="${goodsVO.goods_no==null}">
				 <label for="evetit">活動主題</label>	
					<select name="evetit" id="evetit" class="form-control">
					<option value="-1">請選擇</option>
					<c:forEach var="eventtitleVO" items="${group_openSvc.geteventitle()}">
						 <option value="${eventtitleVO.evetit_no}"${eventtitleVO.evetit_no==param.evetit?'selected':''}>${eventtitleVO.evetit_name}</option>
					</c:forEach>  
					</select>
					</c:if>
					<input type="hidden" name="url1" id="url1" value="">
					<input type="hidden" name="url2" id="url2" value="">
					<input type="hidden" name="action" value="getSelect">
					</div><!-- <div class="form-group"> -->
				<!--</Form> -->
		
		       <Form METHOD="post" ACTION="<%=request.getContextPath()%>/frontend/group_open/group_open.do" name="form1" enctype="multipart/form-data">
				<!-- 驗證到這邊 -->
				<div class="form-group">
   					 <label for="group_banner_1">開團封面1</label>
    					<input type="file" name="group_banner_1" class="form-control-file" id="group_banner_1"  onchange="loadFile(event)"/>
  				</div>
  				<div class="form-group">
   					 <label for="group_banner_2">開團封面2</label>
    					<input type="file" name="group_banner_2" class="form-control-file" id="group_banner_2"  onchange="loadFile2(event)"/>
  				</div>
  				<!-- 商品 -->
				<div class="form-group">			
				 <label for="evetit">商品名稱</label>	
			
			<select id="goods" class="form-control" name="goods_no">
			 		<c:if test="${goodsVO.goods_no==null}">
						<option value="-1">請選擇</option>
					</c:if>	
<%-- 				<c:forEach var="eventtitlemap" items="${evetitle_goods}">
				<option value="${eventtitlemap.key}">${eventtitlemap.value}${eventtitlemap.key}</option>${eventtitlemap.key}
				</c:forEach>--%>
					 <c:if test="${goodsVO.goods_no!=null}">
					<option value="${goodsVO.goods_no}">${goodsVO.goods_name}</option>
					 </c:if>
			</select>			
				</div><!-- <div class="form-group"> -->	
				<div class="form-group">
					<label for="group_name">開團名稱</label>
					<input type="text" name="group_name" id="group_name" class="form-control"
					value="<%= (group_openVO==null)? " " : group_openVO.getGroup_name()%>"/>
				</div>
				<div class="form-group">
					<!-- <label for="group_limit">開團下限</label> -->
					<input type="hidden" name="group_limit" id="group_limit" class="form-control"
					value="<%= (group_openVO==null)? 10 : group_openVO.getGroup_limit()%>"/>
				</div>
				<div class="form-group">
					<label for="group_introduction">開團介紹</label>
					<!-- <textarea name="group_introduction" id="group_introduction" class="form-control"> -->	
					<textarea name="group_introduction" id="group_introduction" rows="10" cols="80">${param.group_mind}</textarea>
					<!-- </textarea> -->
				</div>
				<div class="form-group">
					<label for="group_mind">開團注意事項</label>
					<!-- <textarea name="group_mind" id="group_mind" class="form-control"> -->
					<textarea name="group_mind" id="group_mind" rows="10" cols="80">${param.group_mind}</textarea>	
					<!-- </textarea> -->
				</div>
				<div class="form-group">
				<!-- 	<i class="glyphicon glyphicon-time"></i><label for="start_dateTime">開團開始時間</label> -->
					<input type="hidden" name="group_start_date" id="start_dateTime" class="form-control"/>
				</div>
				<div class="form-group">
					<i class="glyphicon glyphicon-time"></i><label for="end_dateTime">開團結束時間</label>
					<input type="text" name="group_close_date" id="end_dateTime" class="form-control"
					 value="<%= (group_openVO==null) ? " " : group_openVO.getGroup_close_date()%>"/>
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
					<input id="address"  name="group_address" type="textbox"  size="50" maxlength="50" onchange="codeAddress()"class="form-control" value="<%= (group_openVO==null) ? " " : group_openVO.getGroup_address()%>"/>
					<!-- <input type="text" name="group_address" id="group_address" class="form-control"/>  -->
					<input id="submit" type="button" value="預視面交地點">
			</div>
			<!-- <div class="form-group"> -->
			<div class="form-group">
				<!--  <div id="floating-panel"> -->
      			<!-- 	<input id="address" type="textbox" value="Sydney, NSW">
     				 <input id="submit" type="button" value="Geocode"> -->
    		<!-- 	</div> -->
    				<div id="map"></div>
    			</div>
    		<!-- 	<div id="map"></div> -->
					<!-- <label for="latitude">緯度</label> -->
					<input name="latitude" type="hidden" id="latitude" value="<%=(group_openVO==null)? " ": group_openVO.getLatitude()%>" class="form-control"/>
					<%-- <input type="text" name="latitude" id="latitude" class="form-control"
					value="<%=(group_openVO==null)? "25.0177684": group_openVO.getLatitude()%>"/> --%>
			<!-- </div> -->
			<!-- 	<div class="form-group"> -->
					<!-- <label for="longitude">經度</label> -->
					<input name="longitude" type="hidden" id="longitude" value="<%=(group_openVO==null)? " ": group_openVO.getLongitude()%>" class="form-control"/>
					<%-- <input type="text" name="longitude" id="longitude" class="form-control"
					value="<%=(group_openVO==null)? "121.2998": group_openVO.getLongitude()%>"/> --%>
				<!--</div> -->
			<div class="form-group">
				<i class="glyphicon glyphicon-time"></i><label for="group_time">面交時間</label>
				<input type="text" name="group_time" id="group_dateTime" class="form-control" value="<%= (group_openVO==null) ? " " : group_openVO.getGroup_time()%>"/>
			</div>
			<!-- <div class="form-group">
				<i class="glyphicon glyphicon-piggy-bank"></i><label for="group_price">商品促銷價格</label>
				<input type="text" name="group_price" id="group_price" class="form-control"/>
			</div> -->
			<div class="form-group">
				<i class="glyphicon glyphicon-piggy-bank"></i><label for="group_quantity">商品購買數量</label>
				<input type="text" name="group_quantity" id="group_quantity" class="form-control"/>
			</div>	
						<!-- 選項開始 -->
			 <div class="form-group">
     			<label for="sel1">Select list (select one):</label>
      			<select name="pay_method" class="form-control" id="sel1">
       			<option value="EWALLET">EWALLET電子錢包</option>
      			<option value="CREDITCARD">CREDITCARD信用卡</option>   							 
        		</select>
						<br>
        	</div>
								<!-- 選項結束 -->
			
			
			
			 <c:if test="${goodsVO.goods_no!=null}">
					<input type="hidden" name="action" value="insert3">
					<input type="submit" value="送出新增" class="btn btn-success">
					<button id="magicproduct" type="button" class="btn btn-info">magicproduct</button>
			</c:if>
			 <c:if test="${goodsVO.goods_no==null}">
					<input type="hidden" name="action" value="insert2">
					<input type="submit" value="送出新增" class="btn btn-success">
					<button id="magic" type="button" class="btn btn-info">magic</button>
			</c:if>	
			<input type="hidden" name="member_no" value="<%=memberVOsession.getMemberNo()%>">
			<input type="hidden" name="group_member_status" value="grouplead">
			<input type="hidden" name="url1" id="url1" value="">
			<input type="hidden" name="url2" id="url2" value="">
			
		<!-- 	<input type="submit" value="送出新增"> -->
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
		/* 	CKEDITOR.replace( 'group_mind', {});
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
          center: {lat: 24.9680014,
        	  
        	  	   lng: 121.1900142
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
            console.log("經緯度")
          } else {
            alert('Geocode was not successful for the following reason: ' + status);
          }
        });
      }
    </script>
    <script>
  		$('#magicproduct').click(function(){
  			$('#group_name').val('五月天人生巡迴演唱會一起打卡不下班');
  			$('#address').val('中壢市中壢區中大路100號')
  			$('#latitude').val('24.9682162')
  			$('#longitude').val('121.20020850000003') 			
  			$('#end_dateTime').val('2019-01-29 09:30:38')
  			$('#group_quantity').val('10')
  			$('#group_dateTime').val('2019-02-09 09:30:38')
  			var group_introduction = '<p style="text-align:center"><span style="font-size:26px"><span style="color:#e74c3c">五月天人生無限公司</span></span></p>'

  			+'<p><span style="color:#d35400"><span style="font-size:16px">故事起點：</span></span></p>'

  			+'<p>2016年12月31日，五月天在台北的演唱會上公布了他們將在2017年3月18日於高雄開跑名為「人生無限公司」的全新巡迴演唱會。高雄場門票開賣後即完售，因此加場演出一場。另外，五月天也公布其他地區的巡迴場次。</p>'

  			+'<p>2017年5月31日，五月天公布7場於美加地區的場次。10月底，五月天公布於巴黎及倫敦的場次。2018年3月5日，五月天公布墨爾本及雪梨的場次。2018年9月22日，五月天公布自2018年12月22日開始共10場於台中舉行的最終回演出，巡演於2019年1月6日結束。</p>'

  			+'<p>由於觀眾反應熱烈，在高雄、新加坡、桃園、澳門、蘇州、長沙、香港、天津、常州、佛山及昆明等城市額外加場，而香港、新加坡、上海、北京於2018年再次造訪</p>'

  			+'<p><span style="font-size:16px"><span style="color:#d35400">開團緣由：</span></span></p>'

  			+'<p>&nbsp;</p>'

  			+'<div style="-webkit-text-stroke-width:0px; padding:0px; text-align:left; text-indent:0px"><span style="font-size:15px"><span style="color:#333333"><span style="font-family:&quot;Helvetica Neue&quot;,Helvetica,Roboto,Arial,&quot;Lucida Grande&quot;,&quot;PingFang TC&quot;,蘋果儷中黑,&quot;Apple LiGothic Medium&quot;,sans-serif"><span style="background-color:#ffffff"><span style="color:#333333"><strong><span style="font-size:medium">五月天的五個人都不是帥哥，不是你看一眼就會喜歡的，</span></strong></span></span></span></span></span></div>'

  			+'<div style="-webkit-text-stroke-width:0px; padding:0px; text-align:left; text-indent:0px"><span style="font-size:15px"><span style="color:#333333"><span style="font-family:&quot;Helvetica Neue&quot;,Helvetica,Roboto,Arial,&quot;Lucida Grande&quot;,&quot;PingFang TC&quot;,蘋果儷中黑,&quot;Apple LiGothic Medium&quot;,sans-serif"><span style="background-color:#ffffff"><span style="color:#333333"><strong><span style="font-size:medium">所以我們就到處跑，讓大家聽到我們的音樂，</span></strong></span></span></span></span></span></div>'

  			+'<div style="-webkit-text-stroke-width:0px; padding:0px; text-align:left; text-indent:0px"><span style="font-size:15px"><span style="color:#333333"><span style="font-family:&quot;Helvetica Neue&quot;,Helvetica,Roboto,Arial,&quot;Lucida Grande&quot;,&quot;PingFang TC&quot;,蘋果儷中黑,&quot;Apple LiGothic Medium&quot;,sans-serif"><span style="background-color:#ffffff"><span style="color:#333333"><strong><span style="font-size:medium">場地小沒關係，我們多跑幾場，就會有更多的人聽到了。</span></strong></span></span></span></span></span></div>'

  			+'<div style="-webkit-text-stroke-width:0px; padding:0px; text-align:left; text-indent:0px">&nbsp;</div>'

  			+'<div style="-webkit-text-stroke-width:0px; padding:0px; text-align:left; text-indent:0px"><span style="font-size:15px"><span style="color:#333333"><span style="font-family:&quot;Helvetica Neue&quot;,Helvetica,Roboto,Arial,&quot;Lucida Grande&quot;,&quot;PingFang TC&quot;,蘋果儷中黑,&quot;Apple LiGothic Medium&quot;,sans-serif"><span style="background-color:#ffffff"><span style="font-size:small"><span style="color:#333333">聽完這段話，眼眶真的濕了，想到有場演唱會，</span></span></span></span></span></span></div>'

  			+'<div style="-webkit-text-stroke-width:0px; padding:0px; text-align:left; text-indent:0px">&nbsp;</div>'

  			+'<div style="-webkit-text-stroke-width:0px; padding:0px; text-align:left; text-indent:0px"><span style="font-size:15px"><span style="color:#333333"><span style="font-family:&quot;Helvetica Neue&quot;,Helvetica,Roboto,Arial,&quot;Lucida Grande&quot;,&quot;PingFang TC&quot;,蘋果儷中黑,&quot;Apple LiGothic Medium&quot;,sans-serif"><span style="background-color:#ffffff"><span style="font-size:small"><span style="color:#333333">阿信坐在地上撒嬌：變了心的她們，不是去喜歡了5566就是去喜歡了飛輪海。</span></span></span></span></span></span></div>'

  			+'<div style="-webkit-text-stroke-width:0px; padding:0px; text-align:left; text-indent:0px">&nbsp;</div>'

  			+'<div style="-webkit-text-stroke-width:0px; padding:0px; text-align:left; text-indent:0px"><span style="font-size:15px"><span style="color:#333333"><span style="font-family:&quot;Helvetica Neue&quot;,Helvetica,Roboto,Arial,&quot;Lucida Grande&quot;,&quot;PingFang TC&quot;,蘋果儷中黑,&quot;Apple LiGothic Medium&quot;,sans-serif"><span style="background-color:#ffffff"><span style="font-size:small"><span style="color:#333333">現在呢，5566很久沒見到了吧？飛輪海也單飛了，可你們五個憨人還在啊！</span></span></span></span></span></span></div>'

  			+'<div style="-webkit-text-stroke-width:0px; padding:0px; text-align:left; text-indent:0px">&nbsp;</div>'

  			+'<div style="-webkit-text-stroke-width:0px; padding:0px; text-align:left; text-indent:0px"><span style="font-size:15px"><span style="color:#333333"><span style="font-family:&quot;Helvetica Neue&quot;,Helvetica,Roboto,Arial,&quot;Lucida Grande&quot;,&quot;PingFang TC&quot;,蘋果儷中黑,&quot;Apple LiGothic Medium&quot;,sans-serif"><span style="background-color:#ffffff"><span style="font-size:small"><span style="color:#333333">怪獸說：五月天不是唱片公司為了組一個組合而拉來五個人湊起來的，</span></span></span></span></span></span></div>'

  			+'<div style="-webkit-text-stroke-width:0px; padding:0px; text-align:left; text-indent:0px">&nbsp;</div>'

  			+'<div style="-webkit-text-stroke-width:0px; padding:0px; text-align:left; text-indent:0px"><span style="font-size:15px"><span style="color:#333333"><span style="font-family:&quot;Helvetica Neue&quot;,Helvetica,Roboto,Arial,&quot;Lucida Grande&quot;,&quot;PingFang TC&quot;,蘋果儷中黑,&quot;Apple LiGothic Medium&quot;,sans-serif"><span style="background-color:#ffffff"><span style="font-size:small"><span style="color:#333333"><strong>我們從高中就混在一起了，志同道合才在一起這麼多年。</strong></span></span></span></span></span></span></div>'

  			+'<div style="-webkit-text-stroke-width:0px; padding:0px; text-align:left; text-indent:0px">&nbsp;</div>'

  			+'<div style="-webkit-text-stroke-width:0px; padding:0px; text-align:left; text-indent:0px"><span style="font-size:15px"><span style="color:#333333"><span style="font-family:&quot;Helvetica Neue&quot;,Helvetica,Roboto,Arial,&quot;Lucida Grande&quot;,&quot;PingFang TC&quot;,蘋果儷中黑,&quot;Apple LiGothic Medium&quot;,sans-serif"><span style="background-color:#ffffff"><span style="font-size:small"><span style="color:#333333">對於主場阿信的搶眼，石頭說：</span></span></span></span></span></span></div>'

  			+'<div style="-webkit-text-stroke-width:0px; padding:0px; text-align:left; text-indent:0px">&nbsp;</div>'

  			+'<div style="-webkit-text-stroke-width:0px; padding:0px; text-align:left; text-indent:0px"><span style="font-size:15px"><span style="color:#333333"><span style="font-family:&quot;Helvetica Neue&quot;,Helvetica,Roboto,Arial,&quot;Lucida Grande&quot;,&quot;PingFang TC&quot;,蘋果儷中黑,&quot;Apple LiGothic Medium&quot;,sans-serif"><span style="background-color:#ffffff"><span style="font-size:small"><strong><span style="color:#3366ff">五月天就像一隻手，阿信就是大拇指，大拇指很重要，</span></strong></span></span></span></span></span></div>'

  			+'<div style="-webkit-text-stroke-width:0px; padding:0px; text-align:left; text-indent:0px">&nbsp;</div>'

  			+'<div style="-webkit-text-stroke-width:0px; padding:0px; text-align:left; text-indent:0px"><span style="font-size:15px"><span style="color:#333333"><span style="font-family:&quot;Helvetica Neue&quot;,Helvetica,Roboto,Arial,&quot;Lucida Grande&quot;,&quot;PingFang TC&quot;,蘋果儷中黑,&quot;Apple LiGothic Medium&quot;,sans-serif"><span style="background-color:#ffffff"><span style="font-size:small"><strong><span style="color:#3366ff">但要跟其他四個一起才能比一個贊。</span></strong></span></span></span></span></span></div>'

  			+'<div style="-webkit-text-stroke-width:0px; padding:0px; text-align:left; text-indent:0px">&nbsp;</div>'

  			+'<div style="-webkit-text-stroke-width:0px; padding:0px; text-align:left; text-indent:0px"><span style="font-size:15px"><span style="color:#333333"><span style="font-family:&quot;Helvetica Neue&quot;,Helvetica,Roboto,Arial,&quot;Lucida Grande&quot;,&quot;PingFang TC&quot;,蘋果儷中黑,&quot;Apple LiGothic Medium&quot;,sans-serif"><span style="background-color:#ffffff"><span style="font-size:small"><span style="color:#333333">而他們的專輯，從最初幾張阿信包辦所有詞曲到現在其他四個人的創作量大大增加，</span></span></span></span></span></span></div>'

  			+'<div style="-webkit-text-stroke-width:0px; padding:0px; text-align:left; text-indent:0px">&nbsp;</div>'

  			+'<div style="-webkit-text-stroke-width:0px; padding:0px; text-align:left; text-indent:0px"><span style="font-size:15px"><span style="color:#333333"><span style="font-family:&quot;Helvetica Neue&quot;,Helvetica,Roboto,Arial,&quot;Lucida Grande&quot;,&quot;PingFang TC&quot;,蘋果儷中黑,&quot;Apple LiGothic Medium&quot;,sans-serif"><span style="background-color:#ffffff"><span style="font-size:small"><span style="color:#333333">阿信說這麼多年五月天的變化中這是他最開心的，</span></span></span></span></span></span></div>'

  			+'<div style="-webkit-text-stroke-width:0px; padding:0px; text-align:left; text-indent:0px">&nbsp;</div>'

  			+'<div style="-webkit-text-stroke-width:0px; padding:0px; text-align:left; text-indent:0px"><span style="font-size:15px"><span style="color:#333333"><span style="font-family:&quot;Helvetica Neue&quot;,Helvetica,Roboto,Arial,&quot;Lucida Grande&quot;,&quot;PingFang TC&quot;,蘋果儷中黑,&quot;Apple LiGothic Medium&quot;,sans-serif"><span style="background-color:#ffffff"><span style="color:#333333"><strong><span style="font-size:medium">可是這樣力求隊員間的平衡竟然成為不少人批評阿信江郎才盡的理由。</span></strong></span></span></span></span></span></div>'

  			+'<div style="-webkit-text-stroke-width:0px; padding:0px; text-align:left; text-indent:0px">&nbsp;</div>'

  			+'<div style="-webkit-text-stroke-width:0px; padding:0px; text-align:left; text-indent:0px">&nbsp;</div>'

  			+'<div style="-webkit-text-stroke-width:0px; padding:0px; text-align:left; text-indent:0px">&nbsp;</div>'

  			+'<div style="-webkit-text-stroke-width:0px; padding:0px; text-align:left; text-indent:0px">&nbsp;</div>'

  			+'<div style="-webkit-text-stroke-width:0px; padding:0px; text-align:left; text-indent:0px"><span style="font-size:15px"><span style="color:#333333"><span style="font-family:&quot;Helvetica Neue&quot;,Helvetica,Roboto,Arial,&quot;Lucida Grande&quot;,&quot;PingFang TC&quot;,蘋果儷中黑,&quot;Apple LiGothic Medium&quot;,sans-serif"><span style="background-color:#ffffff"><span style="font-size:small"><span style="color:#333333">五月天的一切，絶不是幾首口水歌就可以代表的，也不是我說的這些能代表的。</span></span></span></span></span></span></div>'

  			+'<div style="-webkit-text-stroke-width:0px; padding:0px; text-align:left; text-indent:0px">&nbsp;</div>'

  			+'<div style="-webkit-text-stroke-width:0px; padding:0px; text-align:left; text-indent:0px"><span style="font-size:15px"><span style="color:#333333"><span style="font-family:&quot;Helvetica Neue&quot;,Helvetica,Roboto,Arial,&quot;Lucida Grande&quot;,&quot;PingFang TC&quot;,蘋果儷中黑,&quot;Apple LiGothic Medium&quot;,sans-serif"><span style="background-color:#ffffff"><span style="font-size:small"><span style="color:#333333">對於說他們偽搖的人，我也不知道怎麼跟你們辯論，我不知道搖滾的真正定義，</span></span></span></span></span></span></div>'

  			+'<div style="-webkit-text-stroke-width:0px; padding:0px; text-align:left; text-indent:0px">&nbsp;</div>'

  			+'<div style="-webkit-text-stroke-width:0px; padding:0px; text-align:left; text-indent:0px"><span style="font-size:15px"><span style="color:#333333"><span style="font-family:&quot;Helvetica Neue&quot;,Helvetica,Roboto,Arial,&quot;Lucida Grande&quot;,&quot;PingFang TC&quot;,蘋果儷中黑,&quot;Apple LiGothic Medium&quot;,sans-serif"><span style="background-color:#ffffff"><span style="font-size:small"><span style="color:#333333">我只知道：</span></span></span></span></span></span></div>'

  			+'<div style="-webkit-text-stroke-width:0px; padding:0px; text-align:left; text-indent:0px"><span style="font-size:15px"><span style="color:#333333"><span style="font-family:&quot;Helvetica Neue&quot;,Helvetica,Roboto,Arial,&quot;Lucida Grande&quot;,&quot;PingFang TC&quot;,蘋果儷中黑,&quot;Apple LiGothic Medium&quot;,sans-serif"><span style="background-color:#ffffff"><span style="font-size:small"><span style="color:#333333"><strong>「五月天，唱青春，唱愛情，唱友情，唱親情，唱社會，唱他們所有想唱的東西。」</strong></span></span></span></span></span></span></div>'

  			+'<div style="-webkit-text-stroke-width:0px; padding:0px; text-align:left; text-indent:0px">&nbsp;</div>'

  			+'<div style="-webkit-text-stroke-width:0px; padding:0px; text-align:left; text-indent:0px"><span style="font-size:15px"><span style="color:#333333"><span style="font-family:&quot;Helvetica Neue&quot;,Helvetica,Roboto,Arial,&quot;Lucida Grande&quot;,&quot;PingFang TC&quot;,蘋果儷中黑,&quot;Apple LiGothic Medium&quot;,sans-serif"><span style="background-color:#ffffff"><span style="font-size:small"><span style="color:#333333">真正懂搖滾的人所在乎的，不是每首歌都用鼓點與吉他，</span></span></span></span></span></span></div>'

  			+'<div style="-webkit-text-stroke-width:0px; padding:0px; text-align:left; text-indent:0px">&nbsp;</div>'

  			+'<div style="-webkit-text-stroke-width:0px; padding:0px; text-align:left; text-indent:0px"><span style="font-size:15px"><span style="color:#333333"><span style="font-family:&quot;Helvetica Neue&quot;,Helvetica,Roboto,Arial,&quot;Lucida Grande&quot;,&quot;PingFang TC&quot;,蘋果儷中黑,&quot;Apple LiGothic Medium&quot;,sans-serif"><span style="background-color:#ffffff"><span style="font-size:small"><span style="color:#333333">不是在舞台上跳來跳去砸吉他，而是一顆搖滾的心。</span></span></span></span></span></span></div>'

  			+'<div style="-webkit-text-stroke-width:0px; padding:0px; text-align:left; text-indent:0px">&nbsp;</div>'

  			+'<div style="-webkit-text-stroke-width:0px; padding:0px; text-align:left; text-indent:0px"><span style="font-size:15px"><span style="color:#333333"><span style="font-family:&quot;Helvetica Neue&quot;,Helvetica,Roboto,Arial,&quot;Lucida Grande&quot;,&quot;PingFang TC&quot;,蘋果儷中黑,&quot;Apple LiGothic Medium&quot;,sans-serif"><span style="background-color:#ffffff"><span style="font-size:small"><span style="color:#333333">他們可以不叫演唱會之王，</span></span></span></span></span></span></div>'

  			+'<div style="-webkit-text-stroke-width:0px; padding:0px; text-align:left; text-indent:0px">&nbsp;</div>'

  			+'<div style="-webkit-text-stroke-width:0px; padding:0px; text-align:left; text-indent:0px"><span style="font-size:15px"><span style="color:#333333"><span style="font-family:&quot;Helvetica Neue&quot;,Helvetica,Roboto,Arial,&quot;Lucida Grande&quot;,&quot;PingFang TC&quot;,蘋果儷中黑,&quot;Apple LiGothic Medium&quot;,sans-serif"><span style="background-color:#ffffff"><span style="font-size:small"><span style="color:#333333">可以不叫亞洲天團，</span></span></span></span></span></span></div>'

  			+'<div style="-webkit-text-stroke-width:0px; padding:0px; text-align:left; text-indent:0px">&nbsp;</div>'

  			+'<div style="-webkit-text-stroke-width:0px; padding:0px; text-align:left; text-indent:0px"><span style="font-size:15px"><span style="color:#333333"><span style="font-family:&quot;Helvetica Neue&quot;,Helvetica,Roboto,Arial,&quot;Lucida Grande&quot;,&quot;PingFang TC&quot;,蘋果儷中黑,&quot;Apple LiGothic Medium&quot;,sans-serif"><span style="background-color:#ffffff"><span style="font-size:small"><span style="color:#333333">可以長得不好看，</span></span></span></span></span></span></div>'

  			+'<div style="-webkit-text-stroke-width:0px; padding:0px; text-align:left; text-indent:0px">&nbsp;</div>'

  			+'<div style="-webkit-text-stroke-width:0px; padding:0px; text-align:left; text-indent:0px"><span style="font-size:15px"><span style="color:#333333"><span style="font-family:&quot;Helvetica Neue&quot;,Helvetica,Roboto,Arial,&quot;Lucida Grande&quot;,&quot;PingFang TC&quot;,蘋果儷中黑,&quot;Apple LiGothic Medium&quot;,sans-serif"><span style="background-color:#ffffff"><span style="font-size:small"><span style="color:#333333">可以不被人喜歡。</span></span></span></span></span></span></div>'

  			+'<div style="-webkit-text-stroke-width:0px; padding:0px; text-align:left; text-indent:0px">&nbsp;</div>'

  			+'<div style="-webkit-text-stroke-width:0px; padding:0px; text-align:left; text-indent:0px"><span style="font-size:15px"><span style="color:#333333"><span style="font-family:&quot;Helvetica Neue&quot;,Helvetica,Roboto,Arial,&quot;Lucida Grande&quot;,&quot;PingFang TC&quot;,蘋果儷中黑,&quot;Apple LiGothic Medium&quot;,sans-serif"><span style="background-color:#ffffff"><span style="font-size:small"><span style="color:#333333"><strong>但是，他們是用心在唱歌的五個人。</strong></span></span></span></span></span></span></div>'

  			+'<div style="-webkit-text-stroke-width:0px; padding:0px; text-align:left; text-indent:0px">&nbsp;</div>'

  			+'<div style="-webkit-text-stroke-width:0px; padding:0px; text-align:left; text-indent:0px"><span style="font-size:15px"><span style="color:#333333"><span style="font-family:&quot;Helvetica Neue&quot;,Helvetica,Roboto,Arial,&quot;Lucida Grande&quot;,&quot;PingFang TC&quot;,蘋果儷中黑,&quot;Apple LiGothic Medium&quot;,sans-serif"><span style="background-color:#ffffff"><span style="font-size:small"><span style="color:#333333">你可以不喜歡五月天，可以不聽他們的歌，</span></span></span></span></span></span></div>'

  			+'<div style="-webkit-text-stroke-width:0px; padding:0px; text-align:left; text-indent:0px">&nbsp;</div>'

  			+'<div style="-webkit-text-stroke-width:0px; padding:0px; text-align:left; text-indent:0px"><span style="font-size:15px"><span style="color:#333333"><span style="font-family:&quot;Helvetica Neue&quot;,Helvetica,Roboto,Arial,&quot;Lucida Grande&quot;,&quot;PingFang TC&quot;,蘋果儷中黑,&quot;Apple LiGothic Medium&quot;,sans-serif"><span style="background-color:#ffffff"><span style="color:#333333"><span style="font-size:x-large"><strong>但是，再也不要不懂裝懂胡言亂語。</strong></span></span></span></span></span></span></div>'

  			+'<div style="-webkit-text-stroke-width:0px; padding:0px; text-align:left; text-indent:0px">'
  			+'<figure class="easyimage easyimage-side"><img alt="" sizes="100vw" src="https://36762.cdn.cke-cs.com/HncHCGiDJbUeaigrEncD/images/bb5c0f5d1218b51a50845dea8d419d212bafb8c7ab0f8c4a_124443.jpeg" srcset="https://36762.cdn.cke-cs.com/HncHCGiDJbUeaigrEncD/images/bb5c0f5d1218b51a50845dea8d419d212bafb8c7ab0f8c4a_124443.jpeg/w_100 100w, https://36762.cdn.cke-cs.com/HncHCGiDJbUeaigrEncD/images/bb5c0f5d1218b51a50845dea8d419d212bafb8c7ab0f8c4a_124443.jpeg/w_200 200w, https://36762.cdn.cke-cs.com/HncHCGiDJbUeaigrEncD/images/bb5c0f5d1218b51a50845dea8d419d212bafb8c7ab0f8c4a_124443.jpeg/w_300 300w, https://36762.cdn.cke-cs.com/HncHCGiDJbUeaigrEncD/images/bb5c0f5d1218b51a50845dea8d419d212bafb8c7ab0f8c4a_124443.jpeg/w_400 400w, https://36762.cdn.cke-cs.com/HncHCGiDJbUeaigrEncD/images/bb5c0f5d1218b51a50845dea8d419d212bafb8c7ab0f8c4a_124443.jpeg/w_500 500w, https://36762.cdn.cke-cs.com/HncHCGiDJbUeaigrEncD/images/bb5c0f5d1218b51a50845dea8d419d212bafb8c7ab0f8c4a_124443.jpeg/w_600 600w, https://36762.cdn.cke-cs.com/HncHCGiDJbUeaigrEncD/images/bb5c0f5d1218b51a50845dea8d419d212bafb8c7ab0f8c4a_124443.jpeg/w_700 700w, https://36762.cdn.cke-cs.com/HncHCGiDJbUeaigrEncD/images/bb5c0f5d1218b51a50845dea8d419d212bafb8c7ab0f8c4a_124443.jpeg/w_800 800w, https://36762.cdn.cke-cs.com/HncHCGiDJbUeaigrEncD/images/bb5c0f5d1218b51a50845dea8d419d212bafb8c7ab0f8c4a_124443.jpeg/w_900 900w, https://36762.cdn.cke-cs.com/HncHCGiDJbUeaigrEncD/images/bb5c0f5d1218b51a50845dea8d419d212bafb8c7ab0f8c4a_124443.jpeg/w_1000 1000w" width="500" />'
  			+'<figcaption></figcaption>'
  			+'</figure>'
  			+'</div>';

			CKEDITOR.instances.group_introduction.setData(group_introduction);
			
			var group_mind = <!-- start: 注意事項 -->
			'<div class="tab-pane" id="note">'
			+'<p><span style="color:#f39c12"><span style="font-size:18px"><strong>團員參與合購有什麼要特別注意的嗎？</strong></span></span></p>'

			+'<p><strong>1.</strong></p>'

			+'<p><span style="font-size:16px"><span style="color:#e74c3c"><strong>合購時的禮貌</strong></span></span></p>'

			+'<p>大多數的主購並不是賣家，不是以營利的心情來找人合購喔。找人合購通常是為了要讓大家都能用更便宜的價格買到相同品質的物品，所以合購最重要的一件事就是禮貌，大家要感恩常互相說謝謝喔。</p>'

			+'<p>&nbsp;</p>'

			+'<p><strong>2.</strong></p>'

			+'<p><span style="font-size:16px"><span style="color:#e74c3c"><strong>合購的安全性</strong></span></span></p>'

			+'<p>一起合購的人常常有可能是不認識的，所以的確有一些風險存在，在 ETicket 每一個會員都有自己的個人檔案，個人檔案會有合購評比。如果主購的合購評比不足以判斷合購信用，合購時最好是採用面交付款。 另外如果主購有很多合購都尚未結團也要特別注意，以免遇到詐騙。<br />'
			+'（提醒您面交時最好約在人多的地方）</p>'

			+'<p>&nbsp;</p>'

			+'<p><strong>3.</strong></p>'

			+'<p><span style="font-size:16px"><span style="color:#e74c3c"><strong>確實讀過合購資訊，面交時請準時</strong></span></span></p>'

			+'<p>團員要參與合購時，千萬要確實的讀完合購資訊，了解主購所徵求的合購內容是您可以參與的。另外在面交時請準時，記得跟主購說謝謝喔。</p>'

			+'<p>&nbsp;</p>'

			+'<p><strong>4.</strong></p>'

			+'<p><span style="font-size:16px"><span style="color:#e74c3c"><strong>合購順利完成，別忘了道討論區留言喔</strong></span></span></p>'

			+'<p>討論區留言是 ETicket 非常重要的一個制度，並會以此來決定合購 是否有結團。所以請大家在順利拿到合購物品時記得要互相給評比喔</p>'
			+'</div>'
			/* <!-- end: 注意事項 —> */;
			CKEDITOR.instances.group_mind.setData(group_mind);
  			
  			
  		})  
  		$('#magic').click(function(){
  			$('#group_name').val('韓國人氣女團BLACKPINK,一起支持BLACKPINK衣服!!!')
  			$('#address').val('台北小巨蛋')
  			$('#latitude').val('25.0513848')
  			$('#longitude').val('121.54974140000002') 			
  			$('#end_dateTime').val('2019-01-28 09:28:38')
  			$('#group_quantity').val('2')
  			$('#group_dateTime').val('2019-02-01 09:30:38')
  			
  			
  			var group_introduction = '<div><em><span style="font-size:16px"><span style="color:#d35400"><strong>讓我們全力支持BLACKPINK!!!</strong></span></span></em><u><em><span style="font-size:16px"><span style="color:#d35400"><strong><img alt="heart" height="23" src="http://localhost:8081/CA105G2/vendor/ckeditor_easyImage_final/plugins/smiley/images/heart.png" title="heart" width="23" /><img alt="heart" height="23" src="http://localhost:8081/CA105G2/vendor/ckeditor_easyImage_final/plugins/smiley/images/heart.png" title="heart" width="23" /><img alt="heart" height="23" src="http://localhost:8081/CA105G2/vendor/ckeditor_easyImage_final/plugins/smiley/images/heart.png" title="heart" width="23" /></strong></span></span></em></u></div><div>&nbsp;</div><div>韓國三大經紀公司YG娛樂相隔七年推出的女子團體 - BLACKPINK，出道即以亮麗的外表和不可忽視的實力迅速從新生代韓國女團中脫穎而出，引起全球矚目，由JISOO、JENNIE，ROS&Eacute;和LISA所組成，四人四色，成為次世代時尚指標。以單曲和出道，出道14天即贏得首座音樂節目一位獎盃，大獲成功。後續相繼發行、、等歌曲，在國內外都獲得廣大迴響，更被富士比雜誌讚賞為「<span style="color:#c0392b"><strong>全美最具影響力的K-POP女子團體</strong></span>」。<br />&nbsp;</div>';
			CKEDITOR.instances.group_introduction.setData(group_introduction);
  			
  			
  			var group_mind = <!-- start: 注意事項 -->
			'<div class="tab-pane" id="note">'
			+'<p><span style="color:#f39c12"><span style="font-size:18px"><strong>團員參與合購有什麼要特別注意的嗎？</strong></span></span></p>'

			+'<p><strong>1.</strong></p>'

			+'<p><span style="font-size:16px"><span style="color:#e74c3c"><strong>合購時的禮貌</strong></span></span></p>'

			+'<p>大多數的主購並不是賣家，不是以營利的心情來找人合購喔。找人合購通常是為了要讓大家都能用更便宜的價格買到相同品質的物品，所以合購最重要的一件事就是禮貌，大家要感恩常互相說謝謝喔。</p>'

			+'<p>&nbsp;</p>'

			+'<p><strong>2.</strong></p>'

			+'<p><span style="font-size:16px"><span style="color:#e74c3c"><strong>合購的安全性</strong></span></span></p>'

			+'<p>一起合購的人常常有可能是不認識的，所以的確有一些風險存在，在 ETicket 每一個會員都有自己的個人檔案，個人檔案會有合購評比。如果主購的合購評比不足以判斷合購信用，合購時最好是採用面交付款。 另外如果主購有很多合購都尚未結團也要特別注意，以免遇到詐騙。<br />'
			+'（提醒您面交時最好約在人多的地方）</p>'

			+'<p>&nbsp;</p>'

			+'<p><strong>3.</strong></p>'

			+'<p><span style="font-size:16px"><span style="color:#e74c3c"><strong>確實讀過合購資訊，面交時請準時</strong></span></span></p>'

			+'<p>團員要參與合購時，千萬要確實的讀完合購資訊，了解主購所徵求的合購內容是您可以參與的。另外在面交時請準時，記得跟主購說謝謝喔。</p>'

			+'<p>&nbsp;</p>'

			+'<p><strong>4.</strong></p>'

			+'<p><span style="font-size:16px"><span style="color:#e74c3c"><strong>合購順利完成，別忘了道討論區留言喔</strong></span></span></p>'

			+'<p>討論區留言是 ETicket 非常重要的一個制度，並會以此來決定合購 是否有結團。所以請大家在順利拿到合購物品時記得要互相給評比喔</p>'
			+'</div>'
			/* <!-- end: 注意事項 —> */;
			CKEDITOR.instances.group_mind.setData(group_mind);
	
  		})
  		
    
    
    
    </script>
		<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAb2lDof7yMn-TTXwt2hwVm4y92t1AqvyU&callback=initMap&libraries=places" async defer></script>
		<jsp:include page="/frontend/footer_front-end.jsp" flush="true" />
	</body>
</html>


