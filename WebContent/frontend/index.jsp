<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>ETIckeTs Member</title>
</head>
<jsp:include page="/frontend/navbar_front-end.jsp" flush="true"/>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>ETIckeTs娛樂</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<!-- Owl Stylesheets -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/vendor/OwlCarousel2-2.3.4/docs/assets/owlcarousel/assets/owl.carousel.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/vendor/OwlCarousel2-2.3.4/docs/assets/owlcarousel/assets/owl.theme.default.min.css">
<style>
.memberphoto {
	border-radius: 50px;
	margin-top: 20px;
}

.membermenu {
	margin-top: 100px;
	margin-left: 200px;
}

.topnav {
	list-style-type: none;
	margin: 0;
	padding: 0;
	overflow: hidden;
	display: block;
	color: black;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
}
</style>
<body>
		<div class="container">
		<div id="carousel-id" class="carousel slide" data-ride="carousel">
		    <!-- 幻燈片小圓點區 -->
		    <ol class="carousel-indicators">
		        <li data-target="#carousel-id" data-slide-to="0" class=""></li>
		        <li data-target="#carousel-id" data-slide-to="1" class=""></li>
		        <li data-target="#carousel-id" data-slide-to="2" class="active"></li>
		    </ol>
		    <!-- 幻燈片主圖區 -->
		    <div class="carousel-inner">
		        <div class="item">
		            <img src="https://static.tixcraft.com/images/activity/19_MrC_440ebd20d4c44ae6dd24f3aa0928b931.jpg" alt="" style="display:block; margin:auto">
		        </div>
		        <div class="item">
		            <img src="https://static.tixcraft.com/images/activity/18_LisAni_90af209a6c8ac5d0e42f534f061c6ab0.jpg" alt="" style="display:block; margin:auto">
		        </div>
		        <div class="item active">
		            <img src="https://static.tixcraft.com/images/activity/18_IU_612d92aa08abcad087c1f2b82117a02a.png" alt="" style="display:block; margin:auto">
		        </div>
		    </div>
		    <!-- 上下頁控制區 -->
		    <a class="left carousel-control" href="#carousel-id" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a>
		    <a class="right carousel-control" href="#carousel-id" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a>
		</div>
		</div>
		<br>

		<div class="container">

		<div>
				<div>
					<div class="row">
						<div class="col-xs-12 col-sm-4">
							<div class="thumbnail">
								<img src="http://www.fangoods.com.tw/UMG/Uploads/Product/O_20180402164850_0.jpg" alt="" width="30%" height="30%">
								<div class="caption">
									<h2>商品</h2>
									<p>簡短介紹</p>
								</div>
							</div>
						</div>
						<div class="col-xs-12 col-sm-4">
							<div class="thumbnail">
								<img src="https://shoplineimg.com/594c83239f9a4ff00f00631d/5af178b510abb9e38100357b/400x.webp?source_format=jpg" alt="" width="30%" height="30%">
								<div class="caption">
									<h2>商品</h2>
									<p>簡短介紹</p>
								</div>
							</div>
						</div>
						<div class="col-xs-12 col-sm-4">
							<div class="thumbnail">
								<img src="http://www.fangoods.com.tw/UMG/Uploads/Product/O_20161202182430_0.jpg" alt="" width="30%" height="30%">
								<div class="caption">
									<h2>商品</h2>
									<p>簡短介紹</p>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div>
				<div>
					<div class="row">
						<div class="col-xs-12 col-sm-4">
							<div class="thumbnail">
								<img src="https://shoplineimg.com/594c83239f9a4ff00f00631d/5af178b70e64fe332900190a/400x.webp?source_format=jpg" alt="" width="30%" height="30%">
								<div class="caption">
									<h2>商品</h2>
									<p>簡短介紹</p>
								</div>
							</div>
						</div>
						<div class="col-xs-12 col-sm-4">
							<div class="thumbnail">
								<img src="https://shoplineimg.com/594c83239f9a4ff00f00631d/5af178c70e64fe068300332a/400x.webp?source_format=jpg" alt="" width="30%" height="30%">
								<div class="caption">
									<h2>商品</h2>
									<p>簡短介紹</p>
								</div>
							</div>
						</div>
						<div class="col-xs-12 col-sm-4">
							<div class="thumbnail">
								<img src="http://www.fangoods.com.tw/UMG/Uploads/Product/O_20161205180205_0.jpg" alt="" width="30%" height="30%">
								<div class="caption">
									<h2>商品</h2>
									<p>簡短介紹</p>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div>
				<div>
					<div class="row">
						<div class="col-xs-12 col-sm-4">
							<div class="thumbnail">
								<img src="http://www.fangoods.com.tw/UMG/Uploads/Product/O_20161205183921_0.jpg" alt="" width="30%" height="30%">
								<div class="caption">
									<h2>商品</h2>
									<p>簡短介紹</p>
								</div>
							</div>
						</div>
						<div class="col-xs-12 col-sm-4">
							<div class="thumbnail">
								<img src="http://www.fangoods.com.tw/UMG/Uploads/Product/O_20161205190011_0.jpg" alt="" width="30%" height="30%">
								<div class="caption">
									<h2>商品</h2>
									<p>簡短介紹</p>
								</div>
							</div>
						</div>
						<div class="col-xs-12 col-sm-4">
							<div class="thumbnail">
								<img src="http://www.fangoods.com.tw/UMG/Uploads/Product/O_20161205182128_0.jpg" alt="" width="30%" height="30%">
								<div class="caption">
									<h2>商品</h2>
									<p>簡短介紹</p>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div>
				<div>
					<div class="row">
						<div class="col-xs-12 col-sm-4">
							<div class="thumbnail">
								<img src="https://www.rockmall.com.tw/public/files/product/thumb/N56716-64495R.png" alt="" width="30%" height="30%">
								<div class="caption">
									<h2>商品</h2>
									<p>簡短介紹</p>
								</div>
							</div>
						</div>
						<div class="col-xs-12 col-sm-4">
							<div class="thumbnail">
								<img src="https://www.rockmall.com.tw/public/files/product/thumb/N04311-50205R.jpg" alt="" width="30%" height="30%">
								<div class="caption">
									<h2>商品</h2>
									<p>簡短介紹</p>
								</div>
							</div>
						</div>
						<div class="col-xs-12 col-sm-4">
							<div class="thumbnail">
								<img src="https://www.rockmall.com.tw/public/files/product/thumb/N77730-53359R.jpg" alt="" width="30%" height="30%">
								<div class="caption">
									<h2>商品</h2>
									<p>簡短介紹</p>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div>
				<div>
					<div class="row">
						<div class="col-xs-12 col-sm-4">
							<div class="thumbnail">
								<img src="https://www.rockmall.com.tw/public/files/product/thumb/N17407-49085R.jpg" alt="" width="30%" height="30%">
								<div class="caption">
									<h2>商品</h2>
									<p>簡短介紹</p>
								</div>
							</div>
						</div>
						<div class="col-xs-12 col-sm-4">
							<div class="thumbnail">
								<img src="https://www.rockmall.com.tw/public/files/product/thumb/N22991-86992R.jpg" alt="" width="30%" height="30%">
								<div class="caption">
									<h2>商品</h2>
									<p>簡短介紹</p>
								</div>
							</div>
						</div>
						<div class="col-xs-12 col-sm-4">
							<div class="thumbnail">
								<img src="https://www.rockmall.com.tw/public/files/product/thumb/N39705-24419R.jpg" alt="" width="30%" height="30%">
								<div class="caption">
									<h2>商品</h2>
									<p>簡短介紹</p>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	</body>
<jsp:include page="/frontend/footer_front-end.html" flush="true"/> 
</html>