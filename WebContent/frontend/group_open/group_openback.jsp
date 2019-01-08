<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.group_open.model.*"%>
<%@ page import="com.group_member.model.*"%>
<jsp:useBean id="group_openSvc" scope="page" class="com.group_open.model.Group_openService" /> 
<jsp:useBean id="group_memberSvc" scope="page" class="com.group_member.model.Group_memberService" /> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css"> <!-- datable -->
<!-- jQuery -->
<script type="text/javascript" charset="utf8" src="http://code.jquery.com/jquery-1.10.2.min.js"></script> <!-- datable -->
<script type="text/javascript" charset="utf8" src="http://cdn.datatables.net/1.10.15/js/jquery.dataTables.js"></script> <!-- datable -->
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
	background-color: #333;
	display: block;
	color: white;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
}
</style>
</head>
<body>
	<nav class="navbar navbar-inverse" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-ex1-collapse">
					<span class="sr-only">選單切換</span> <span class="icon-bar"></span> <span
						class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<img src="images/LOGO_back-end.png" href="#" alt="LOGO"
					width="202.25px" height="165.5px">
			</div>

			<!-- 手機隱藏選單區  -->

			<div class="collapse navbar-collapse navbar-ex1-collapse">
				<!-- 右選單 -->
				<img
					src="https://scontent-hkg3-1.xx.fbcdn.net/v/t1.0-1/c53.53.662.662a/s160x160/996812_623306544360262_513913499_n.jpg?_nc_cat=109&_nc_eui2=AeEvi_vj3AZ5wk2s31mtunvrLPbVPtJK2jf7uWRYtFCuPw_M1yTd23yuh2AGeVu5aGSm_1aLOh_81tqazaXh-ECnpuFl77aq8E38y3WIOxRGcA&_nc_ht=scontent-hkg3-1.xx&oh=c8b216f2429b70114bdb941b525f73cf&oe=5CA0CFE7"
					class="memberphoto" href="#" alt="LOGO" style="float: right"
					width="80px" height="80px">

				<ul class="nav navbar-nav navbar-right membermenu">
					<li><a href="#">管理員登出</a></li>
					<li><a href="#">設定</a></li>
					<!-- 				<li class="dropdown"><a href="#" class="dropdown-toggle" -->
					<!-- 					data-toggle="dropdown">繁體中文 <b class="caret"></b></a> -->
					<!-- 					<ul class="dropdown-menu"> -->
					<!-- 						<li><a href="#">繁體中文</a></li> -->
					<!-- 						<li><a href="#">English</a></li> -->
					<!-- 						<li><a href="#">日本語</a></li> -->
					<!-- 					</ul> -->
					<!-- 				</li> -->
				</ul>
			</div>
			<!-- 手機隱藏選單區結束 -->
		</div>
		<div class="topnav">
			<div class="col-xs-12 col-sm-6 col-md-4 col-lg-2">
				<div>
					<a href="#" class="topnav" align="center">活動管理</a>
				</div>
			</div>
			<div class="col-xs-12 col-sm-6 col-md-4 col-lg-2">
				<div>
					<a href="#" class="topnav" align="center">票券管理</a>
				</div>
			</div>
			<div class="col-xs-12 col-sm-6 col-md-4 col-lg-2">
				<div>
					<a href="#" class="topnav" align="center">商品管理</a>
				</div>
			</div>
			<div class="col-xs-12 col-sm-6 col-md-4 col-lg-2">
				<div>
					<a href="#" class="topnav" align="center">團購管理</a>
				</div>
			</div>
			<div class="col-xs-12 col-sm-6 col-md-4 col-lg-2">
				<div>
					<a href="#" class="topnav" align="center">常見問題管理</a>
				</div>
			</div>
			<div class="col-xs-12 col-sm-6 col-md-4 col-lg-2">
				<div>
					<a href="#" class="topnav" align="center">公告管理</a>
				</div>
			</div>
		</div>
	</nav>
	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-2">
				<div class="panel panel-default">
				  <div class="panel-heading">
				    <h3 class="panel-title">開團管理</h3>
				  </div>
				  <div class="panel-body">
				    瀏覽所有跟團
				  </div>
				  <div class="panel-body">
				    瀏覽所有開團
				  </div>
				</div>
			</div><!-- <div class="col-xs-12 col-sm-2"> -->
			<div class="col-xs-12 col-sm-10">
		

	<table id="example" class="display" style="width:100%">
        <thead>
            <tr>
                <th>開團編號</th>
                <th>會員編號</th>
                <th>現有數量</th>
                <th>現有人數</th>
                <th>開團狀態</th>
                <th>修改該團</th>
                <th>刪除該團</th>
            </tr>
        </thead>
        <tbody>
        <!-- foreach開始 -->
        <c:forEach var="group_openVO" items= "${group_openSvc.all}" >
            <tr>
                <td>${group_openVO.group_no}</td>
                <td>${group_openVO.member_no}</td>
                <td>
                	<c:forEach var="group_membermap" items="${group_memberSvc.group_quantity}">
						<c:if test="${group_openVO.group_no==group_membermap.key}">
	                   			${group_membermap.value}
                    	</c:if>	
					</c:forEach>	 
                </td>
                <td>11</td>
                <td>${group_open.group_status}</td>
                <td>
                
                </td>
                <td>
                
                
                </td>
                
            </tr>
        </c:forEach>
        <!-- foreach結束 -->
        </tbody>
    </table>




			</div><!-- <div class="col-xs-12 col-sm-10"> -->
		</div><!-- <div class="row"> -->
	</div><!-- <div class="container"> -->
<!-- 	datable	 -->
<script type="text/javascript"> 
	$(document).ready(function() {
    $('#example').DataTable( {
        columnDefs: [ {
            targets: [ 0 ],
            orderData: [ 0, 1 ]
        }, {
            targets: [ 1 ],
            orderData: [ 1, 0 ]
        }, {
            targets: [ 4 ],
            orderData: [ 4, 0 ]
        } ]
    } );
} );
</script>
</body>
</html>