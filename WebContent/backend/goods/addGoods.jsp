<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page import="com.goods.model.*"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>新增商品</title>
    <!-- Basic -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <!-- datetimepicker -->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/vendor/datetimepicker/jquery.datetimepicker.css" />
    
    <style>
        .evetit_poster_area img {
	        width: 100%;
	    }
    	body{
body {
	font-family: 微軟正黑體 !important;
}
</style>
<script>
function readURL(input){
  if(input.files && input.files[0]){
    var imageID = input.getAttribute("targetID");
    var reader = new FileReader();
    reader.onload = function (e) {
       var img = document.getElementById(imageID);
       img.setAttribute("src", e.target.result)
    }
    reader.readAsDataURL(input.files[0]);
  }
}
</script>

</head>

<body>



		<jsp:include page="/backend/navbar_back-end.jsp" flush="true" />



	<div class="container">
		<span class="text-danger">${goodsErrorMsgs.Exception}</span>
	</div>

	<div class="container" style="margin-bottom: 30px;">
		<form method="post" enctype="multipart/form-data"
			action="<%=request.getContextPath()%>/goods/GoodsServlet.do">

			<div class="form-group">
				<label for="goods_name">商品名稱</label> <span class="text-danger">${goodsErrorMsgs.goods_name}</span>
				<input type="text" name="goods_name" id="goods_name"
					class="form-control" value="${param.goods_name}">
			</div>


	            <div class="row">
	                <div class="col-xs-12 col-sm-6">
	                     <div class="form-group">
	                         <label for="launchdate">上架日期</label>
	                         <span class="text-danger">${goodsErrorMsgs.launchdate}</span>
	                         <input type="text" id="launchdate" name="launchdate" class="form-control" value="${param.launchdate}">
	                     </div>
	                </div>
	                <div class="col-xs-12 col-sm-6">
	                     <div class="form-group">
	                         <label for="offdate">下架日期</label>
	                         <span class="text-danger">${goodsErrorMsgs.offdate}</span>
	                         <span class="text-danger">${goodsErrorMsgs.offdate_BiggerThanToday}</span>
	                         <span class="text-danger">${goodsErrorMsgs.offdate_BiggerThanLaunchdate}</span>
	                         <input type="text" id="offdate" name="offdate" class="form-control" value="${param.offdate}">
	                     </div>
	                </div>
	            </div>

			<div class="row">
				<div class="col-xs-12 col-sm-3">
					<jsp:useBean id="EventTitleService" scope="page"
						class="com.event_title.model.EventTitleService" />
					<div class="form-group">
						<label>活動編號</label> <select class="form-control" name="evetit_no">
							<c:forEach var="eventTitleVO" items="${EventTitleService.all}">
								<option value="${eventTitleVO.evetit_no}"
									${(eventTitleVO.evetit_no == goodsVO.evetit_no) ? 'selected' : '' }>
									${eventTitleVO.evetit_name}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="col-xs-12 col-sm-3">
					<div class="form-group">
						<label>商品售價</label> <input type="text" name="goods_price"
							id="goods_price" class="form-control"
							value="${param.goods_price}">
					</div>
				</div>
				<div class="col-xs-12 col-sm-3">
					<div class="form-group">
						<label>優惠價</label> <input type="text" name="forsales_a"
							id="forsales_a" class="form-control" value="${param.forsales_a}">
					</div>
				</div>

				<div class="col-xs-12 col-sm-3">
					<div class="form-group">
						<label>商品狀態</label> <select class="form-control" name=goods_status>
							<option value="confirmed"
								${(goodsVO.goods_status == 'confirmed') ? 'selected' : '' }>已上架</option>
							<option value="temporary"
								${(goodsVO.goods_status == 'temporary') ? 'selected' : '' }>未上架</option>
							<option value="cancel"
								${(goodsVO.goods_status == 'cancel') ? 'selected' : '' }>已下架</option>
						</select>
					</div>
				</div>

			</div>
	
		<input type="file" accept="image/jpeg, image/png" name="goods_picture1" onchange="readURL(this)" targetID="goods_picture1preview">
		<img id="goods_picture1preview" src="<%=request.getContextPath()%>/goods/goodsImg1.do?action=add&scaleSize=850&goods_no=${goodsVO.goods_no}" height="200" width="200">
	
		
		<input type="file" accept="image/jpeg, image/png" name="goods_picture2" onchange="readURL(this)" targetID="goods_picture2preview">
		<img id="goods_picture2preview" src="<%=request.getContextPath()%>/goods/goodsImg2.do?action=add&scaleSize=850&goods_no=${goodsVO.goods_no}" height="200" width="200">
	
		
		<input type="file" accept="image/jpeg, image/png" name="goods_picture3" onchange="readURL(this)" targetID="goods_picture3preview">
		<img id="goods_picture3preview" src="<%=request.getContextPath()%>/goods/goodsImg3.do?action=add&scaleSize=850&goods_no=${goodsVO.goods_no}" height="200" width="200">
	

			<div class="tabbable">
				<!-- 標籤面板：標籤區 -->
				<ul class="nav nav-tabs">
					<li class="active"><a href="#infoTab" data-toggle="tab">商品介紹</a></li>

				</ul>
				<!-- 標籤面板：內容區 -->
				<div class="tab-content">
					<div class="tab-pane active" id="infoTab">
						<textarea name="goods_introduction" id="infoEditor">
		                		${param.goods_introduction}
		               		</textarea>
					</div>

				</div>
			</div>
	<span class="form-group">
					<button type="submit" class="btn btn-success" name="action" value="insertGoods" style="margin-top:15px;">新增</button>
					<a class="btn btn-info" href="<%=request.getContextPath()%>/backend/goods/listAllGoods.jsp" style="margin-top:15px;">回商品總攬</a>
					<button type="button" class="btn btn-default" style="margin-top:15px;" id="magicButton">點我</button>
				</span>			
			</form>
	</div>


    <!-- Basic -->
    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <!-- ckEditor JS -->
    <script src="<%=request.getContextPath()%>/vendor/ckeditor_easyImage_final/ckeditor.js"></script>
    <!-- datetimepicker -->
    <script src="<%=request.getContextPath()%>/vendor/datetimepicker/jquery.js"></script>
    <script src="<%=request.getContextPath()%>/vendor/datetimepicker/jquery.datetimepicker.full.js"></script>
    <!-- JavaScript in File -->
    <script src="<%=request.getContextPath()%>/backend/goods/js/goodsCKEditor.js"></script>
    <script src="<%=request.getContextPath()%>/backend/goods/js/addGoods.js"></script>
    <!-- JavaScript in HTML -->
    <script>
    
    $("#magicButton").click(function(){
		$("#goods_name").val("BLACKPINK 2019 WORLD TOUR TAIPEI with KIA");
		$('#launchdate').val("2019-01-17");
		$("#offdate").val("2019-01-28");
		$("#goods_price").val("1000");
		$("#forsales_a").val("500");
		$("#infoEditor").val("BLACKPINK 由 JISOO，JENNIE，ROSÉ 和LISA 一共4名成員組成，2016年以單曲專輯《SQUARE ONE》出道已經獲得極大迴響。繼成功的出道後，她們先後推出1張單曲和3張EP，她們的歌曲不僅攻佔韓國流行榜，亦在國際音樂榜如Billboard等佔一席位。NK歌迷的稱號）帶來只能在YG演唱會中體驗的豐富耀目音樂表演。");
    });
   
	
	
	
// 		$(function() {
// 			initInfoEditor();
			//         initNoticesEditor();
			//         initEticpurchaserulesEditor();
			//         initEticrulesEditor();
			//         initRefundrulesEditor();

// 			$("#goods_picture1").change(function() {
// 				imagesPreview(this);
// 				$("#goods_picture1_status").attr("value", "yesUpload");
// 			});
// 			$("#goods_picture2").change(function() {
// 				imagesPreview(this);
// 				$("#goods_picture2_status").attr("value", "yesUpload");
// 			});
// 			$("#goods_picture3").change(function() {
// 				imagesPreview(this);
// 				$("#goods_picture3_status").attr("value", "yesUpload");
// 			});

// 			$(".text-danger")
// 					.each(
// 							function() {
// 								var errorMsg = $(this).text();
// 								if (errorMsg.trim() != "") {
// 									$(this)
// 											.prepend(
// 													"<i class='glyphicon glyphicon-triangle-left'></i>");
// 								}
// 							});
// 			localStorage.removeItem("DataTables_goodsTable");
// 		});
	</script>
</body>

</html>