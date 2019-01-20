<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page import="com.goods.model.*"%>
<%
	GoodsVO goodsVO = (GoodsVO) request.getAttribute("goodsVO");
%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>${goodsVO.goods_name}</title>
<!-- Basic -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<!-- datetimepicker -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/vendor/datetimepicker/jquery.datetimepicker.css" />

<style>
.goods_picture1 img {
	width: 100%;
}

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
				<label for="goods_no">商品編號</label><br> ${goodsVO.goods_no} <input
					type="hidden" name="goods_no" id="goods_no" class="form-control"
					value="${goodsVO.goods_no}" readonly>
			</div>
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

			<div class="form-group">
				<label for="goods_name">商品名稱</label> <span class="text-danger">${goodsErrorMsgs.goods_name}</span>
				<input type="text" name="goods_name" id="goods_name"
					class="form-control" value="${goodsVO.goods_name}">
			</div>


			<div class="row">
				<div class="col-xs-12 col-sm-6">
					<div class="form-group">
						<label for="launchdate">上架日期</label> <span class="text-danger">${goodsErrorMsgs.launchdate}</span>
						<input type="text" id="f_date1" name="launchdate"
							class="form-control" value="${goodsVO.launchdate}">
					</div>


				</div>
				<div class="col-xs-12 col-sm-6">
					<div class="form-group">
						<label for="offdate">下架日期</label> <span class="text-danger">${goodsErrorMsgs.offdate}</span>
						<span class="text-danger">${goodsErrorMsgs.offdate_BiggerThanToday}</span>
						<span class="text-danger">${goodsTitleErrorMsgs.offdate_BiggerThanLaunchdate}</span>
						<input type="text" id="f_date2" name="offdate"
							class="form-control" value="${goodsVO.offdate}">

					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-xs-12 col-sm-4">
					<div class="form-group">
						<label>商品售價</label> <input type="text" name="goods_price"
							id="goods_price" class="form-control"
							value="${goodsVO.goods_price}">
					</div>
				</div>
				<div class="col-xs-12 col-sm-4">
					<div class="form-group">
						<label>優惠價</label> <input type="text" name="forsales_a"
							id="forsales_a" class="form-control"
							value="${goodsVO.forsales_a}">
					</div>
				</div>

				<div class="col-xs-12 col-sm-4">
					<div class="form-group">
						<label>商品狀態</label> <select class="form-control" name=goods_status>
							<option value="temporary"
								${(goodsVO.goods_status == 'temporary') ? 'selected' : '' }>未上架</option>
							<option value="confirmed"
								${(goodsVO.goods_status == 'confirmed') ? 'selected' : '' }>已上架</option>
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
						<textarea name="info" id="infoEditor">
		                		${goodsVO.goods_introduction}
		               		</textarea>
					</div>

				</div>
			</div>
			<span class="form-group"> <input type="hidden" name="action"
				value="updateGoods"> <input type="hidden" name="goods_no"
				value="<%=goodsVO.getGoods_no()%>"> <input type="submit"
				value="送出修改" class="btn btn-primary">
			</span>
		</form>
	</div>



	<!-- Basic -->
	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	ckEditor JS
	<script
		src="<%=request.getContextPath()%>/vendor/ckeditor_full/ckeditor.js"></script>
	<script
		src="<%=request.getContextPath()%>/backend/goods/js/goodsCKEditor.js"></script>
	datetimepicker
	<script
		src="<%=request.getContextPath()%>/vendor/datetimepicker/jquery.js"></script>
	<%--     <script src="<%=request.getContextPath()%>/vendor/datetimepicker/jquery.datetimepicker.full.js"></script> --%>
	<!--     JavaScript in File -->
	<script
		src="<%=request.getContextPath()%>/backend/goods/js/update_goods_input.js"></script>
	JavaScript in HTML ink rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css"
	/>
	<script
		src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

	<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

	<script>
		$('#f_date1').datetimepicker({
		    theme: '',              //theme: 'dark',
		    timepicker:true,       //timepicker:true,
		     step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
		     format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
			   value: '<%=goodsVO.getLaunchdate()%>', // value:   new Date(),
		 });
        $('#f_date2').datetimepicker({
           theme: '',              //theme: 'dark',
           timepicker:true,       //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
 		   value: '<%=goodsVO.getOffdate()%>', // value:   new Date(),

		});
	</script>
	<script type="text/javascript">
		$(function() {
			initInfoEditor();
			//         initNoticesEditor();
			//         initEticpurchaserulesEditor();
			//         initEticrulesEditor();
			//         initRefundrulesEditor();

			$("#goods_picture1").change(function() {
				imagesPreview(this);
				$("#goods_picture1_status").attr("value", "yesUpload");
			});
			$("#goods_picture2").change(function() {
				imagesPreview(this);
				$("#goods_picture2_status").attr("value", "yesUpload");
			});
			$("#goods_picture3").change(function() {
				imagesPreview(this);
				$("#goods_picture3_status").attr("value", "yesUpload");
			});

			$(".text-danger")
					.each(
							function() {
								var errorMsg = $(this).text();
								if (errorMsg.trim() != "") {
									$(this)
											.prepend(
													"<i class='glyphicon glyphicon-triangle-left'></i>");
								}
							});

		});
	</script>
</body>

</html>