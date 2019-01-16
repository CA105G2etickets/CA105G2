<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.group_open.model.*"%>
<%@ page import="com.group_member.model.*"%>
<%@ page import="com.member.model.*"%>

<%
	if(request.getAttribute("list")==null){
	Group_openService grpSvc = new Group_openService();
	List<Group_openVO> list = grpSvc.getAll();
	pageContext.setAttribute("list", list);
	}else{
		
	}
	MemberService memberSvc = new MemberService();
	List<MemberVO> list2 =  memberSvc.getAll();
	pageContext.setAttribute("list2", list2);
	
	MemberVO memberVOsession = (MemberVO)session.getAttribute("member"); 
				
%>



<!DOCTYPE html>
<html lang="">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>ETIckeTs娛樂</title>
<script src="http://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<script
	src="http://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="http://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
	
	<!-- Owl Stylesheets -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.0.0-beta.2.4/assets/owl.carousel.min.css"></link>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.0.0-beta.2.4/assets/owl.theme.default.min.css"></link>

<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
<style>
.memberphoto {
	border-radius: 50px;
	margin-top: 20px;
}

.membermenu {
	margin-top: 100px;
	margin-left: 200px;
}

.banner {
	margin-left: 100px;
}

.hot {
	margin: 0px auto;
	vertical-align: middle;
}

.top {
	background-color: #FFCC22;
}

.member_picture {
	width: 100px;
	height: 100px;
}

.shop {
	width: 60px;
	height: 60px;
}

.title {
	background-color: #FFAA33;
}

.images {
	width: 200px;
	height: 200px;
}
.flash{
	width: 100%;
	height: 100%;
}
</style>


</head>
<body>
	<jsp:include page="/frontend/navbar_front-end.jsp" flush="true" />
		<div class="container-fluid">
			<div class="row">
				
				<div class="col-xs-12 col-sm-4">
				</div><!-- <div class="col-xs-12 col-sm-2"> -->
				<div class="col-xs-12 col-sm-6">
					<form class="navbar-form navbar-left" role="search" METHOD="post" ACTION="group_open.do" >
						<div class="form-group">
							<input type="text" name="EVETIT_NAME" class="form-control" placeholder="請輸入關鍵字">
						</div>
						<button type="submit" class="btn btn-default">活動主題搜尋</button>
						<div class="form-group">
							<input type="text" name="GOODS_NAME" class="form-control" placeholder="請輸入關鍵字">
						</div>
						<button type="submit" class="btn btn-default">商品搜尋</button>
						<input type="hidden" name="action" value="listEmps_ByCompositeQuery">
					</form><!-- <form class="navbar-form navbar-left" role="search"> -->
					<%-- <a href="addgroup_open.jsp"><button type="button" class="btn btn-info .btn-md">我要開團</button></a>
					<a href="<%=request.getContextPath()%>/frontend/group_open/group_open.do?action=get_group_open_Bymember_no&member_no=<%=memberVOsession.getMemberNo()%>"><button type="button" class="btn btn-info .btn-success">我的開團管理</button></a>
					  <a href="<%=request.getContextPath()%>/frontend/group_member/group_member.do?action=getOne_For_Display&member_no=<%=memberVOsession.getMemberNo()%>"><button type="button" class="btn btn-info .btn-success">我的跟團管理</button></a> --%>
				</div><!-- <div class="col-xs-12 col-sm-8"> -->
				<div class="col-xs-12 col-sm-2">
				</div><!-- <div class="col-xs-12 col-sm-2"> -->
			</div><!-- <div class="row"> -->
		</div><!-- <div class="container"> -->
	
	
	<div class="container-fluid">
		<div class="row">
		</div>
	</div>
	<div class="container-fluid">
		<div class="row">
			<div class="col-xs-12 col-sm-3">
				<div class="panel panel-info" id='example'>
					<div class="panel-heading">
						<h3 class="panel-title">我的團購管理</h3>
					</div>
				<div class="panel-body">
						<a href="addgroup_open.jsp">我要開團</a></li>
					</div>
					<!-- <div class="panel-body"> -->
					<div class="panel-body">
						<a href=<%=(memberVOsession!=null)?request.getContextPath()+"/frontend/group_open/group_open.do?action=get_group_open_Bymember_no&member_no="+memberVOsession.getMemberNo():request.getContextPath()+"/frontend/index.jsp" %> >開團管理</a>
					</div>
					<!-- <div class="panel-body"> -->
					<div class="panel-body">
						<a href=<%=(memberVOsession!=null)?request.getContextPath()+"/frontend/group_member/group_member.do?action=getOne_For_Display&member_no="+memberVOsession.getMemberNo():request.getContextPath()+"/frontend/index.jsp" %> >我的跟團管理</a>
					</div>
					<!-- <div class="panel-body"> -->
				</div>
				<!-- <div class="panel panel-info" id='example'> -->
			</div>
			<!-- <div class="col-xs-12 col-sm-3"> -->
			<div class="col-xs-12 col-sm-6">
				
				
				<div class="owl-carousel owl-theme">
				<c:forEach var="group_openVO" items="${list}">
    		<div class="item flash"><h4><img src="Group_openImg1.do?group_no=${group_openVO.group_no}"></h4></div>
    			</c:forEach>  
				</div><!-- <div class="owl-carousel owl-theme"> -->
				
				
				
				
				
			</div>
			<!-- <div class="col-xs-12 col-sm-6"> -->
			<div class="col-xs-12 col-sm-3">
				
				<br>
				<table class="table table-hover">
					<div class="title" style="text-align: center">
						<span style="color: white;"><h3>人氣團購</h3></span>
					</div>
					<thead>
						<tr>
							<th>圖片</th>
							<th>開團名稱</th>
							<th>最愛商品次數</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><img
								src="<%=request.getContextPath()%>/images/G0002_GROUP_BANNER_1.jpg"
								class="shop"></td>
							<td>Bangalore</td>
							<td>Bangalore</td>
						</tr>
						<tr>
							<td><img
								src="<%=request.getContextPath()%>/images/G0002_GROUP_BANNER_1.jpg"
								class="shop"></td>
							<td>Mumbai</td>
							<td>Mumbai</td>
						</tr>
						<tr>
							<td><img
								src="<%=request.getContextPath()%>/images/G0002_GROUP_BANNER_1.jpg"
								class="shop"></td>
							<td>Pune</td>
							<td>Pune</td>
						</tr>
					</tbody>
				</table>
			</div>
			<!-- <div class="col-xs-12 col-sm-3"> -->
		</div>
		<!-- <div class="row"> -->
	</div>
	<!-- <div class="container"> -->
	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-2">
				<c:if test="${not empty errorMsgs}">
					<font color='red'>請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>


			</div>
			<!-- <div class="col-xs-12 col-sm-2"> -->
			<div class="col-xs-12 col-sm-8">
				<div class="container">
					<c:forEach var="group_openVO" items="${list}">
						<c:if test="${group_openVO.group_status=='process1'}">
							<!-- 只能顯示進行中的團 -->
							<div class="row">
								<div class="col-xs-12 col-sm-6">
									<!-- 封面開始 -->
									<div class="card mb-3 banner">
										<img class="card-img-top"
											src="Group_openImg1.do?group_no=${group_openVO.group_no}"
											height="200px">
										<div class="card-body">
											<h4 class="card-title ">${group_openVO.group_name}</h4>
											<p class="card-text bg-warning">
											<h3>開團結束倒數計時</h3>
												<div class="timeback" style="background-color:#D3D3D3;"><p class="bg-warning"><h3><span id="pad_${group_openVO.group_no}"  style="color:#FF0000;">${group_openVO.group_no}</span></h3></div>	
											</p>
											<p class="card-text">
										<!-- 	開始時間：<span id="start_pad"></span><br>
											結束時間：<p class="bg-danger"><span class="end_pad"></span></p><br> -->
						
										<%-- 	剩餘時間：<div class="timeback"><p class="bg-warning"><h3><span id="pad_${group_openVO.group_no}">${group_openVO.group_no}</span></h3></p></div> --%>
											<!-- ------------------------------change------------------------------ -->
												<script>
													var startDate = new Date();
													var endDate = new Date('${group_openVO.group_close_date}');
													var spantime_${group_openVO.group_no} = (endDate - startDate) / 1000;
	 
													cal_${group_openVO.group_no}();
													var spantime_${group_openVO.group_no};
													function cal_${group_openVO.group_no}(){														
														spantime_${group_openVO.group_no}--;
														if(spantime_${group_openVO.group_no} <= 0){
															clearInterval(time_${group_openVO.group_no});
															$("#pad_${group_openVO.group_no}").html("揪團已結束");
															
															readonly="readonly"
														} else { 
															console.log(spantime_${group_openVO.group_no});
															var d = Math.floor(spantime_${group_openVO.group_no} / (24 * 3600));
															var h = Math.floor((spantime_${group_openVO.group_no} % (24 * 3600)) / 3600);
															var m = Math.floor((spantime_${group_openVO.group_no} % 3600) / (60));
															var s = Math.floor(spantime_${group_openVO.group_no} % 60);
															str = d + "天 " + h + "时 " + m + "分 " + s + "秒 ";
															console.log('${group_openVO.group_no}' + "----------" + str);
															console.log("here");
															$("#pad_${group_openVO.group_no}").html(str);
														}
													}
													var time_${group_openVO.group_no} = setInterval(cal_${group_openVO.group_no}, 1000);
												</script>
											<!-- ------------------------------change------------------------------ -->
												
												
											
										</div>
									</div>
									<!-- <div class="card mb-3 banner"> -->
									<!-- 封面結束 -->
								</div>
								<!-- <div class="col-xs-12 col-sm-6"> -->
								<div class="col-xs-12 col-sm-6">
									<!-- 大頭貼開始 -->
									<div class="card" style="width: 18rem;">
									<c:forEach var="memberVO" items="${list2}">
										<c:if test="${group_openVO.member_no==memberVO.memberNo}">
										<img class="card-img-top img-circle member_picture"
											src="<%=request.getContextPath()%>/member/memberImg.do?memberno=${memberVO.memberNo}"
											alt="Card image cap">
										</c:if>	
									</c:forEach>
										<div class="card-body">
										<c:forEach var="memberVO" items="${list2}">
										<c:if test="${group_openVO.member_no==memberVO.memberNo}">
											<h5 class="card-title">${memberVO.memberFullname}</h5>
											</c:if>	
											</c:forEach>
											<FORM METHOD="post" ACTION="group_open.do">
												<input type="submit" value="我要跟團" class="btn btn-primary">                      
												<input type="hidden" name="action" value="getgroup_for_display"> <input type="hidden"
													name="group_no" value="${group_openVO.group_no}"
													class="btn btn-primary">
											</Form>

										</div>
										<!-- <div class="card" style="width: 18rem;"> -->
									</div>
									<!-- <div class="col-xs-12 col-sm-6"> -->
									<!-- 大頭貼結束 -->
								</div>
								<!-- <div class="col-xs-12 col-sm-6"> -->
							</div>
							<!-- <div class="row"> -->
						</c:if>
					</c:forEach>
				</div>
				<!-- <div class="container"> -->



			</div>
			<!-- <div class="col-xs-12 col-sm-8"> -->
			<div class="col-xs-12 col-sm-2">
				
			</div>
			<!-- <div class="col-xs-12 col-sm-2"> -->
		</div>
		<!-- <div class="row"> -->
	</div>
	<!-- <div class="container"> -->
	<br>
	<br>
	<jsp:include page="/frontend/footer_front-end.jsp" flush="true" />

	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
				<script src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.0.0-beta.2.4/owl.carousel.min.js"></script>
		
		<script>
		
	var owl = $('.owl-carousel');
owl.owlCarousel({
    items:1,
    loop:true,
    animateOut: 'fadeOut',
    margin:10,
    autoplay:true,
    autoplayTimeout:1000,
    autoplayHoverPause:true
});

$('.play').on('click',function(){
    owl.trigger('play.owl.autoplay',[1000])
})
$('.stop').on('click',function(){
    owl.trigger('stop.owl.autoplay')
})


</script>
</body>
</html>