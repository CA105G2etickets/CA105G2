<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page import="com.goods.model.*"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>新增商品</title>
<!-- Basic -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<style>
.actionForm {
	display: inline;
}

#map {
	height: 500px;
	width: 100%;
	margin-top: 15px;
	border-radius: 25px;
}

body {
	font-family: 微軟正黑體 !important;
}
</style>
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

			<div class="form-group goods_picture">
				<label for="goods_picture1">圖片1</label> <span class="text-danger">${goodsErrorMsgs.goods_picture1}</span>
				<input type="file" id="goods_picture1" name="goods_picture1"
					class="form-control" accept="image/*"> <input type="hidden"
					id="goods_picture1_status" name="goods_picture1_status"
					value="${(goods_picture1_status == 'alreadyUpload') ? 'alreadyUpload' : 'noUpload'}">
				<img src="${goods_picture1_path}" id="goods_picture1_preview">


				<label for="goods_picture2">圖片2</label> <span class="text-danger">${goodsErrorMsgs.goods_picture2}</span>
				<input type="file" id="goods_picture2" name="goods_picture2"
					class="form-control" accept="image/*"> <input type="hidden"
					id="goods_picture2_status" name="goods_picture2_status"
					value="${(goods_picture2_status == 'alreadyUpload') ? 'alreadyUpload' : 'noUpload'}">
				<img src="${goods_picture2_path}" id="goods_picture2_preview">


				<label for="goods_picture3">圖片3</label> <span class="text-danger">${goodsErrorMsgs.goods_picture3}</span>
				<input type="file" id="goods_picture3" name="goods_picture3"
					class="form-control" accept="image/*"> <input type="hidden"
					id="goods_picture3_status" name="goods_picture3_status"
					value="${(goods_picture3_status == 'alreadyUpload') ? 'alreadyUpload' : 'noUpload'}">
				<img src="${goods_picture3_path}" id="goods_picture3_preview">
			</div>

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
				<button type="submit" class="btn btn-success" name="action"
					value="insertGoods">新增</button>
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
    <script type="text/javascript">

	<script>
	
		$('#f_date1').datetimepicker({
			theme : '', //theme: 'dark',
			timepicker : true, //timepicker:true,
			step : 1, //step: 60 (這是timepicker的預設間隔60分鐘)
			format : 'Y-m-d H:i:s', //format:'Y-m-d H:i:s',
			value : new Date(), // value:   new Date(),
		});
		$('#f_date2').datetimepicker({
			theme : '', //theme: 'dark',
			timepicker : true, //timepicker:true,
			step : 1, //step: 60 (這是timepicker的預設間隔60分鐘)
			format : 'Y-m-d H:i:s', //format:'Y-m-d H:i:s',
			value : new Date(), // value:   new Date(),
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
			localStorage.removeItem("DataTables_goodsTable");
		});
	</script>
</body>

</html>