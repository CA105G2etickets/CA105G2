<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.news.model.*"%>
<%@ page import="com.goods.model.*"%>


<!-- --------------------------------------------------DAI ::: advertisement relative ::: begin-------------------------------------------------- -->
<%@ page import="com.event_title.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.advertisement.model.*"%>
<%
	AdvertisementService advertisementService = new AdvertisementService();
	AdvertisementVO theOneAdvertisementVO = advertisementService.getOneAdvertisementRandom();
	pageContext.setAttribute("theOneAdvertisementVO", theOneAdvertisementVO);
%>
<%
	List<AdvertisementVO> advertisementList = advertisementService.getLaunched();
	pageContext.setAttribute("advertisementList", advertisementList);
%>
<%
	if(request.getAttribute("listGoods_ByCompositeQuery") == null){
		GoodsService goodsService = new GoodsService();
		List<GoodsVO> list = goodsService.getAllLaunched();
		pageContext.setAttribute("listGoods_ByCompositeQuery", list); 
	}
%>
<!-- --------------------------------------------------DAI ::: advertisement relative ::: end-------------------------------------------------- -->



<html>
<head>
<title>ETIckeTs - 首頁</title>
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
	position: absolute;
	margin-top: 30;
	z-index:1;
}
.membermenu {
	margin-top: 8em;
	margin-left: 20%;
}
.navbarmemberbtn {
	position: absolute;
	margin-bottom: 0;
	padding: 1px;
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
.shoppingC {
	font-family:微軟正黑體!important;
}
.newstag {
    background-color: #3399ff;
    color: #fff;
    padding: 3px 10px;
    font-size: 12px;
    border-radius: 5px;
    vertical-align: middle;
    display: inline-block;
}
.indexultachi {
	padding: 0;
}
hr {
 	height: 2px;
	background-color: #9d9d9d;
 	width: 100%;
 	border: none;
 	margin: 3;
}
.paragraph {
	margin: 5px 0;
    padding: 0;
}
.listAllNews {
	display: inline-block;
    float: right;
    font-size: 14px;
}
body{
	font-family:微軟正黑體!important;
}

/* --------------------------------------------------DAI ::: advertisement relative ::: begin-------------------------------------------------- */
.adImgModal img {
	width: 100%;
}
.adImgModalHeader {
	background-color: #ff3547;
	border-top-left-radius: 6px;
	border-top-right-radius: 6px;
}
.adImgModalFooter {
	background-color: #ff3547;
	border-bottom-left-radius: 6px;
	border-bottom-right-radius: 6px;
}
.owl-item:not(.center) img {
	opacity: 0.2;
}
.owl-item img:hover {
	opacity: 1;
}
#eventAdvertiseModal {
	background-color: rgba(0, 0, 0, 0.5);
}
/* --------------------------------------------------DAI ::: advertisement relative ::: end-------------------------------------------------- */

/****************************************** 通知部分 ******************************************/
.badge-pill {
    padding: 0.2em;
    display: inline-block;
    padding: 0.25em 0.4em;
    font-size: 75%;
    font-weight: 700;
    line-height: 1;
    text-align: center;
    white-space: nowrap;
    vertical-align: baseline;
}
.badge-primary {
    color: white;
    background-color: #3399ff;
}
/****************************************** 通知結束 ******************************************/
</style>
<body>



<!-- --------------------------------------------------advertisement carousel ::: begin-------------------------------------------------- -->
<div class="container-fluid">
	<div class="row">
		<div class="owl-carousel owl-theme" id="advertisementCarousel">
			<c:forEach var="advertisementVO" items="${advertisementList}">
				<div class="item">
					<a href="<%= request.getContextPath()%>/frontend/event_title/listOneEventTitle.jsp?evetit_no=${advertisementVO.evetit_no}" target="_blank">
						<img src="<%= request.getContextPath()%>/event_title/EventTitleGifReader?scaleSize=450&evetit_no=${advertisementVO.evetit_no}">
					</a>
				</div>
			</c:forEach>
		</div>
	</div>
</div>
<!-- --------------------------------------------------advertisement carousel ::: end-------------------------------------------------- -->



<!-- 		<div class="container"> -->
<!-- 		<div id="carousel-id" class="carousel slide" data-ride="carousel"> -->
<!-- 		    幻燈片小圓點區 -->
<!-- 		    <ol class="carousel-indicators"> -->
<!-- 		        <li data-target="#carousel-id" data-slide-to="0" class=""></li> -->
<!-- 		        <li data-target="#carousel-id" data-slide-to="1" class=""></li> -->
<!-- 		        <li data-target="#carousel-id" data-slide-to="2" class="active"></li> -->
<!-- 		    </ol> -->
<!-- 		    幻燈片主圖區 -->
<!-- 		    <div class="carousel-inner"> -->
<!-- 		        <div class="item"> -->
<!-- 		            <img src="https://static.tixcraft.com/images/activity/19_MrC_440ebd20d4c44ae6dd24f3aa0928b931.jpg" alt="" style="display:block; margin:auto"> -->
<!-- 		        </div> -->
<!-- 		        <div class="item"> -->
<!-- 		            <img src="https://static.tixcraft.com/images/activity/18_LisAni_90af209a6c8ac5d0e42f534f061c6ab0.jpg" alt="" style="display:block; margin:auto"> -->
<!-- 		        </div> -->
<!-- 		        <div class="item active"> -->
<!-- 		            <img src="https://static.tixcraft.com/images/activity/18_IU_612d92aa08abcad087c1f2b82117a02a.png" alt="" style="display:block; margin:auto"> -->
<!-- 		        </div> -->
<!-- 		    </div> -->
<!-- 		    上下頁控制區 -->
<!-- 		    <a class="left carousel-control" href="#carousel-id" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a> -->
<!-- 		    <a class="right carousel-control" href="#carousel-id" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a> -->
<!-- 		</div> -->
<!-- 		</div> -->
		<br>
		
		<div class="container">
		
<%
	NewsService newsService = new NewsService();
	List<NewsVO> list = newsService.getAll();
	pageContext.setAttribute("list", list);
%>

<jsp:useBean id="newsClassification" scope="page" class="com.news_classification.model.NewsClassificationService" />
<font size="3" style="font-weight:bold">
<ul class="indexultachi">
<div class="paragraph">
網站公告<a href="<%=request.getContextPath()%>/frontend/news/listAllNews.jsp" class="listAllNews"><i class="glyphicon glyphicon-chevron-right"></i>更多</a>
</div>
<hr>
</ul>
	<c:forEach var="news" items="${list}" end="4">
		<ul class="indexultachi">
			<font class="newstag">
				${newsClassification.getOneNewsClassification(news.news_classification_no).newsClassification}
			</font>
				<a href="<%=request.getContextPath()%>/frontend/news/news.do?action=getOne_For_Display_front&news_no=${news.news_no}" class="newsTitle">${news.news_title}</a>
		</ul>
	</c:forEach>
<ul class="indexultachi">
<div class="paragraph">
相關周邊商品
</div>
<hr>
</ul>
</font>
		<div>
			<div class="row">
				<c:forEach var="goodsVO" items="${listGoods_ByCompositeQuery}" end="8">
				
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
						<div class="row">
						
						
						
							<div class="thumbnail" style="border: 0">
								<img src="<%= request.getContextPath()%>/goods/GoodsGifReader?scaleSize=425&goods_no=${goodsVO.goods_no}" width="200px" height="200px">
								<div class="caption">
									<p style="font-size: 1.2em;">${goodsVO.goods_name}</p>
								</div>
							</div>
							
							
							
						</div>	
					</div>
					
				</c:forEach>
			</div>
		</div>
	</div>



<!-- --------------------------------------------------randomOneEventAdvertiseModal ::: begin-------------------------------------------------- -->
<div class="container">
	<div class="modal fade" id="eventAdvertiseModal">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header adImgModalHeader">
					<font size="5" color="white"> <i
						class="glyphicon glyphicon-bullhorn"></i> <i
						class="glyphicon glyphicon-bullhorn"></i> <i
						class="glyphicon glyphicon-bullhorn"></i> 現正熱賣中
					</font> <i style="color: white;" class="glyphicon glyphicon-menu-right"></i><i
						style="color: white;" class="glyphicon glyphicon-menu-right"></i><i
						style="color: white;" class="glyphicon glyphicon-menu-right"></i>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body adImgModal">
					<a
						href="<%= request.getContextPath()%>/frontend/event_title/listOneEventTitle.jsp?evetit_no=${theOneAdvertisementVO.getEvetit_no()}"
						target="_blank"> <img
						src="<%= request.getContextPath()%>/event_title/EventTitleGifReader?scaleSize=450&evetit_no=${theOneAdvertisementVO.getEvetit_no()}">
					</a>
				</div>
				<div class="modal-footer adImgModalFooter">
					<h4 class="modal-title">
						<jsp:useBean id="eventTitleService" scope="page"
							class="com.event_title.model.EventTitleService" />
						<a
							href="<%= request.getContextPath()%>/frontend/event_title/listOneEventTitle.jsp?evetit_no=${theOneAdvertisementVO.getEvetit_no()}"
							target="_blank"> <font color="white">${eventTitleService.getOneEventTitle(theOneAdvertisementVO.getEvetit_no()).evetit_name}</font>
						</a>
					</h4>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- --------------------------------------------------randomOneEventAdvertiseModal ::: end-------------------------------------------------- -->



	<!-- Basic -->
	<script src="https://code.jquery.com/jquery.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<!-- Owl Stylesheets -->
	<script src="<%=request.getContextPath()%>/vendor/OwlCarousel2-2.3.4/docs/assets/owlcarousel/owl.carousel.js"></script>
	<script>
	$(document).ready(function() {
		
		
		
		<!-- --------------------------------------------------advertisement relative ::: begin-------------------------------------------------- -->
		localStorage.removeItem("searchStatus");
		$('#eventAdvertiseModal').modal('show');
		var advertisementCarousel = $('#advertisementCarousel');
		advertisementCarousel.owlCarousel({
			center : true,
			margin : 5,
			nav : true,
			loop : true,
			autoplay : true,
			autoplayTimeout : 5000,
			responsive : {
				0 : {
					items : 1,
					stagePadding : 50
				},
				800 : {
					items : 1,
					stagePadding : 150
				},
				1300 : {
					items : 1,
					stagePadding : 300
				}
			}
		})
		<!-- --------------------------------------------------advertisement relative ::: end-------------------------------------------------- -->
		
		
		<!-- --------------------------------------------------------------公告部分------------------------------------------------------------ -->
			$(".newsTitle").hover(function() {
				$(this).css("text-decoration","none");
			});
			$(".listAllNews").hover(function() {
				$(this).css("text-decoration","none");
			});
		<!-- --------------------------------------------------------------公告結束------------------------------------------------------------ -->
		
	});
	</script>
	</body>
<jsp:include page="/frontend/footer_front-end.jsp" flush="true"/> 
</html>