<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.group_open.model.*"%>
<%@ page import="com.group_member.model.*"%>

<%-- <jsp:useBean id= "group_openBymember_no" scope="request" type="java.util.List<Group_openVO>" />  --%>
<!-- �γo�Ӥ�k�g���ɷ|�� ���T�w -->
<jsp:useBean id="group_memberSvc" scope="page" class="com.group_member.model.Group_memberService" />
<jsp:useBean id="group_openSvc" scope="page" class="com.group_open.model.Group_openService" />
<%--  <% List<Group_openVO> group_openBymember_no = (List<Group_openVO>) request.getAttribute("group_openBymember_no"); %>  --%>


<!DOCTYPE html>
<html lang="">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<title>ETIckeTs�T��</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
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
		.member{
			width:50px;
		}
		</style>

	</head>
	<body>
	<nav class="navbar navbar-default" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-ex1-collapse">
					<span class="sr-only">������</span> <span class="icon-bar"></span> <span
						class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<img src="https://i.imgur.com/T0YnkK9.png" href="#" alt="LOGO"
					width="202.25px" height="165.5px">
			</div>

			<!-- ������ÿ��� -->

			<div class="collapse navbar-collapse navbar-ex1-collapse">
				<!-- �k��� -->
				<img
					src="https://scontent-hkg3-1.xx.fbcdn.net/v/t1.0-1/c53.53.662.662a/s160x160/996812_623306544360262_513913499_n.jpg?_nc_cat=109&_nc_eui2=AeEvi_vj3AZ5wk2s31mtunvrLPbVPtJK2jf7uWRYtFCuPw_M1yTd23yuh2AGeVu5aGSm_1aLOh_81tqazaXh-ECnpuFl77aq8E38y3WIOxRGcA&_nc_ht=scontent-hkg3-1.xx&oh=c8b216f2429b70114bdb941b525f73cf&oe=5CA0CFE7"
					class="memberphoto" href="#" alt="LOGO" style="float: right"
					width="80px" height="80px">

				<ul class="nav navbar-nav navbar-right membermenu">
					<li><a href="#">�n�X</a></li>
					<li><a href="#">�ӤH�]�w</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">�c�餤�� <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="#">�c�餤��</a></li>
							<li><a href="#">English</a></li>
							<li><a href="#">�饻�y</a></li>
						</ul></li>
				</ul>
			</div>
			<!-- ������ÿ��ϵ��� -->
		</div>
	</nav>
	<div class="container-fluid">
			<div class="row">
				
					<div class="col-xs-12 col-sm-2">
					
					
					
						<c:forEach var="memberVO" items="${list2}">
										<c:if test="${group_openVO.member_no==memberVO.memberNo}">
										<img class="card-img-top img-circle member_picture"
											src="<%=request.getContextPath()%>/member/memberImg.do?memberno=${memberVO.memberNo}"
											alt="Card image cap">
										</c:if>	
									</c:forEach>
					
					
					
						<div class="panel panel-default">
					 		 <div class="panel-heading">
					 		   <h3 class="panel-title">���D</h3>
					 			 </div>
								  <div class="panel-body">					 
								   <a href="<%=request.getContextPath()%>/frontend/group_open/group_open.do?action=get_group_open_Bymember_no&member_no=${group_openVO.member_no}">�ڪ��}�ά���</a>
								  </div>
								    <div class="panel-body">
								   <a href="<%=request.getContextPath()%>/frontend/group_member/group_member.do?action=getOne_For_Display&member_no=${group_openVO.member_no}">�ڪ���ά���</a>
								  </div>
						</div><!-- <div class="panel panel-default"> -->
					</div><!-- <div class="col-xs-12 col-sm-2"> -->
					<div class="col-xs-12 col-sm-10">
							<table class="table table-hover">
								<caption>�ڪ��}�ά���</caption>
									<thead>
										<tr>
											<th>�}�Ϋʭ�(�})</th>
											<th>�}�ΦW��(�})</th>
											<th>�ثe�H��(��)</th>
											<th>�{���ƶq(��)</th>
											<th>�����ɶ�(�})</th>
											<th>����a�}(�})</th>
											<th>����ɶ�(�})</th>
											<th>���ʪ��A(�})</th>
											<th>�Ѵ�</th>
											<th>�}�Φ��\</th>
											</tr>
									</thead>
								<c:forEach var="group_openVO" items="${group_openBymember_no}" >
										<tr>
										<td>
										<img src ="<%=request.getContextPath()%>/frontend/group_open/Group_openImg1.do?group_no=${group_openVO.group_no}" height="80" width="80"/>
										</td>
										<td>
										${group_openVO.group_name}
										</td>
										<td>
											<c:forEach var="group_openmap" items="${group_openSvc.member_count}">
												<c:if test="${group_openVO.group_no==group_openmap.key}">
	                   							${group_openmap.value}
                    							</c:if>	
											</c:forEach>								
										</td>
										<td>
											<c:forEach var="group_membermap" items="${group_memberSvc.group_quantity}">
												<c:if test="${group_openVO.group_no==group_membermap.key}">
	                   							${group_membermap.value}
                    							</c:if>	
											</c:forEach>	 
										</td>	
										 <%--<td>${group_openVO.group_close_date}</td> --%>
										<%-- <fmt:formatDate value="${eventVO.eve_startdate}" pattern="yyyy-MM-dd HH:mm"/> --%>
										<%-- <fmt:formatDate value="${group_openVO.group_close_date}" pattern="yyyy-MM-dd HH:mm"/> --%>												
									<td><fmt:formatDate value="${group_openVO.group_close_date}" pattern="yyyy-MM-dd HH:mm"/></td> 
										<td>${group_openVO.group_address}</td>
										<td>${group_openVO.group_time}</td>
										<td>${group_openVO.group_status}</td>
						
										<td>
										 <Form METHOD="post" ACTION="<%=request.getContextPath()%>/frontend/group_open/group_open.do">
										<input type="hidden" name="dimiss_reason" value="��ѧڭ�n����">
										<input type="hidden" name="action" value="quitall">
										<input type="hidden" name="group_no" value="${group_openVO.group_no}">${group_openVO.group_no}
										<input type="hidden" name="member_no" value="${group_openVO.member_no}">${group_openVO.member_no}
										<input type="submit" value="�Ѵ��ӹ�" >
										 </FORM>	
										</td>
										<td>
										 <Form METHOD="post" ACTION="<%=request.getContextPath()%>/frontend/group_open/group_open.do">
										<input type="hidden" name="action" value="makeorder">
										<input type="hidden" name="group_no" value="${group_openVO.group_no}">${group_openVO.group_no}
										<input type="hidden" name="member_no" value="${group_openVO.member_no}">${group_openVO.member_no}
										<input type="submit" value="�}�Φ��\�i�J�q�歶��" >
										 </FORM>	
										</td>
										</tr>
								</c:forEach>
								</tbody>
					
							</table>
					</div><!-- <div class="col-xs-12 col-sm-10"> -->
			</div><!-- <div class="row"> -->
		</div><!-- <div class="container-fluid"> -->		
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	</body>
</html>