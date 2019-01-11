<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.group_member.model.*"%>  
<%@ page import="com.group_open.model.*"%>
 <!-- 我的跟團紀錄 -->
 <%-- <jsp:useBean id="group_openBymember_no" scope="request" type="java.util.List<Group_memberVO>"/>    <!-- insert2 -->  --%>
  
<jsp:useBean id="group_openSvc" scope="page" class="com.group_open.model.Group_openService" /> 
<jsp:useBean id="group_memberSvc" scope="page" class="com.group_member.model.Group_memberService"/>

<%-- <% List <Group_memberVO> group_BY_member_no = (List<Group_memberVO>) request.getAttribute("group_BY_member_no"); %> --%>


 
<!DOCTYPE html>
<html >
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<title>Title Page</title>
			<script src="https://code.jquery.com/jquery.js"></script>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
		
		<style>
		.memberphoto {
			border-radius: 50px;
			margin-top:20px;
			}
		.membermenu {
			margin-top:100px;
			margin-left: 200px;
			}
		.member{
			width:50px;
		}
		</style>
		<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
		
		
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
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">繁體中文 <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="#">繁體中文</a></li>
								<li><a href="#">English</a></li>
								<li><a href="#">日本語</a></li>
							</ul>
						</li>
					</ul>
				</div>
				<!-- 手機隱藏選單區結束 -->
			</div>
		</nav>
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12 col-sm-2">
					<div class="panel panel-default">
					 		 <div class="panel-heading">
					 		   <h3 class="panel-title">標題</h3>
					 			 </div>
								  <div class="panel-body">
								    我的開團紀錄
								  </div>
								    <div class="panel-body">
								    我的跟團紀錄
								  </div>
						</div><!-- <div class="panel panel-default"> -->
				</div><!-- <div class="col-xs-12 col-sm-2"> -->
				<div class="col-xs-12 col-sm-10">
					<table class="table table-hover">
						<caption>我的跟團記錄</caption>
						<thead>
							<tr>
								<th>開團封面(開)</th>
								<th>開團名稱(開)</th>
								<th>跟團人購買數量(跟)</th>
								<th>商品折扣後價格(開)</th>
								<th>跟團人狀態(跟)</th>
								<th>現有數量(跟)</th>
								<th>付款狀態(跟)</th>
								<th>結束時間(開)</th>
								<th>面交地址(開)</th>
								<th>面交時間(開)</th>
								<th>團購狀態(開)</th>
								<th>修改跟團</th>
								<th>退團</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach var="group_memberVO" items= "${group_openBymember_no}" >
						<!--EL自動去找page request session   -->
						 <c:if test="${group_memberVO.group_member_status=='withgroup'||group_memberVO.group_member_status=='quit'}"><!-- 顯示自己有的跟團 -->
							<tr>
								<td>
									<c:forEach var="group_openVO" items="${group_openSvc.all}">
                   						 <c:if test="${group_memberVO.group_no==group_openVO.group_no}">
	                   					<img src ="<%=request.getContextPath()%>/frontend/group_open/Group_openImg1.do?group_no=${group_openVO.group_no}" height="80" width="80"/>
                    					</c:if>
               						</c:forEach>
								</td>
								<td>
									<c:forEach var="group_openVO" items="${group_openSvc.all}">
                   						 <c:if test="${group_memberVO.group_no==group_openVO.group_no}">
	                   				${group_openVO.group_name}
                    				</c:if>
               						</c:forEach>					
								</td>
							    <td>
							    	${group_memberVO.product_quantity}
							    </td>
								<td>
				
								</td>
								<td>
									${group_memberVO.group_member_status}
								</td>
								<td>
									<c:forEach var="group_membermap" items="${group_memberSvc.group_quantity}">
										<c:if test="${group_memberVO.group_no==group_membermap.key}">
	                   						${group_membermap.value}
                    					</c:if>	
									</c:forEach>	 			
								</td>
								<td>
								${group_memberVO.pay_status}
								</td>
								<td> 
									<c:forEach var="group_openVO" items="${group_openSvc.all}">
                   						 <c:if test="${group_memberVO.group_no==group_openVO.group_no}">
	                   				<fmt:formatDate value="${group_openVO.group_close_date}" pattern="yyyy-MM-dd HH:mm"/>                   
	                   					</c:if>
               						</c:forEach>																
								</td>
								<td>
									<c:forEach var="group_openVO" items="${group_openSvc.all}">
                   						 <c:if test="${group_memberVO.group_no==group_openVO.group_no}">
	                   						${group_openVO.group_address} 
                    					</c:if>
               						</c:forEach>
									</td>
								<td>
									<c:forEach var="group_openVO" items="${group_openSvc.all}">
                   					 <c:if test="${group_memberVO.group_no==group_openVO.group_no}">
                   					 <fmt:formatDate value="${group_openVO.group_time}" pattern="yyyy-MM-dd HH:mm"/> 
	                   					<%-- 	${group_openVO.group_time}  --%>
                    					</c:if>
               						</c:forEach>								
								</td>
								<td>
									<c:forEach var="group_openVO" items="${group_openSvc.all}">
                   					    <c:if test="${group_memberVO.group_no==group_openVO.group_no}">
	                   						 ${group_openVO.group_status} 
                    					</c:if>
               						</c:forEach>								
								</td>
								<td>
								 <FORM METHOD="post" ACTION="group_member.do" style="margin-bottom: 0px;">
			     					<input type="submit" value="修改">
			     					<input type="hidden" name="group_no"  value="${group_memberVO.group_no}">
			     					<input type="hidden" name="member_no"  value="${group_memberVO.member_no}">
			     					<input type="hidden" name="action"	value="getOne_For_Update">
			 					 </FORM>		
								</td>
								<td>
									<FORM METHOD="post" ACTION="group_member.do" style="margin-bottom: 0px;">
			    					 <input type="submit" value="退團">
			     					 <input type="hidden" name="group_no"  value="${group_memberVO.group_no}">
			     					 <input type="hidden" name="member_no"  value="${group_memberVO.member_no}">
			     					 <input type="hidden" name="group_member_status"  value="quit">
			     					 <input type="hidden" name="pay_status"  value="CANCEL3">
			     					 <input type="hidden" name="log_out_reason"  value="太麻煩了要等很久">
			    					 <input type="hidden" name="action" value="quit">
			  						 </FORM>					
								</td>
								
							</tr>
							</c:if>
							</c:forEach>
						</tbody>
					</table>
				</div><!-- <div class="col-xs-12 col-sm-10"> -->
			</div> <!-- <div class="row"> -->
		</div>	<!-- <div class="container"> -->

		
		
	
		
		 
		
		

	</body>
</html>