<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.group_open.model.*"%>
<%@ page import="com.group_member.model.*"%>
<%@ page import="com.member.model.*"%>
<%-- <jsp:useBean id= "group_openBymember_no" scope="request" type="java.util.List<Group_openVO>" />  --%>
<!-- 用這個方法寫重導會掛 不確定 -->
<jsp:useBean id="group_memberSvc" scope="page" class="com.group_member.model.Group_memberService" />
<jsp:useBean id="group_openSvc" scope="page" class="com.group_open.model.Group_openService" />
<%--  <% List<Group_openVO> group_openBymember_no = (List<Group_openVO>) request.getAttribute("group_openBymember_no"); %>  --%>
<% MemberVO memberVOsession = (MemberVO)session.getAttribute("member"); %>


<!DOCTYPE html>
<html lang="">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<title>ETIckeTs娛樂</title>
			<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.css" />
	<script src="js/jquery-1.12.3.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.js" type="text/javascript"></script>
		<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
	</head>
	<body>
	<jsp:include page="/frontend/navbar_front-end.jsp" flush="true" />
	<div class="container-fluid" style ="margin-bottom: 200px">
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
					 		   <h3 class="panel-title">我的團購管理</h3>
					 			 </div>
								  <div class="panel-body">					 
								   <a href="<%=request.getContextPath()%>/frontend/group_open/group_open.do?action=get_group_open_Bymember_no&member_no=<%=memberVOsession.getMemberNo()%>">我的開團紀錄</a>
								  </div>
								    <div class="panel-body">
								   <a href="<%=request.getContextPath()%>/frontend/group_member/group_member.do?action=getOne_For_Display&member_no=<%=memberVOsession.getMemberNo()%>">我的跟團紀錄</a>
								  </div>
						</div><!-- <div class="panel panel-default"> -->
					</div><!-- <div class="col-xs-12 col-sm-2"> -->
					<div class="col-xs-12 col-sm-10">
							<table class="table table-hover">
								<caption>我的開團紀錄</caption>
									<thead>
										<tr>
											<th>開團封面</th>
											<th>開團名稱</th>
											<th>目前人數</th>
											<th>現有數量</th>
											<th>結束時間</th>
											<th>面交地址</th>
											<th>面交時間</th>
											<th>團購狀態</th>
											<th>解散</th>
											<th>開團成功</th>
											<th>修改開團</th>
											</tr>
									</thead>
								<c:forEach var="group_openVO" items="${group_openBymember_no}" >
								
								
								
								<c:if test="${group_openVO.group_status=='process1'}">
								  <!--開團進行中 開始  -->
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
										<td><fmt:formatDate value="${group_openVO.group_time}" pattern="yyyy-MM-dd HH:mm"/></td> 
										<%-- <td>${group_openVO.group_time}</td> --%> 
										<td>${group_openVO.group_status=='process1'?"進行中":"失敗"}</td>
						
										<td>
<%-- 										 <Form METHOD="post" ACTION="<%=request.getContextPath()%>/frontend/group_open/group_open.do">
										<input type="hidden" name="dimiss_reason" value="當天我剛好有事">
										<input type="hidden" name="action" value="quitall">
										<input type="hidden" name="group_no" value="${group_openVO.group_no}">${group_openVO.group_no}
										<input type="hidden" name="member_no" value="${group_openVO.member_no}">${group_openVO.member_no}
										<input type="submit" value="解散該團" >
										 </FORM>	 --%>
										 	  <!-- Trigger the modal with a button -->
									<button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#myModal_${group_openVO.group_no}">退團</button>
								     	<!-- Modal -->
										<div id="myModal_${group_openVO.group_no}" class="modal fade" role="dialog">
  											<div class="modal-dialog">
    									<!-- Modal content-->
   										<div class="modal-content">
      										<div class="modal-header">
        										<button type="button" class="close" data-dismiss="modal">&times;</button>
        											<h4 class="modal-title">退團就像是你的女朋友等不到你過聖誕節一樣的感覺</h4>
      										</div><!-- <div class="modal-header"> -->
     									<div class="modal-body">
											<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/frontend/group_open/group_open.do" style="margin-bottom: 0px;">
					     					 <input type="hidden" name="group_no"  value="${group_openVO.group_no}">
					     					 <input type="hidden" name="member_no"  value="${group_openVO.member_no}">
			  					 
					     					 <div class="form-group">
												<label for="aa">解散該團原因</label>
												<input type="text" name="dimiss_reason" id="aa" class="form-control">
											</div>
					    					 <input type="hidden" name="action" value="quitall">
					    					  <input type="submit" value="解散" class="btn btn-danger btn-sm">
					  						 </FORM>  	
      									</div><!-- <div class="modal-body"> -->
      								<div class="modal-footer">
        								<!-- <button type="submit" class="btn btn-default" data-dismiss="modal">退團</button> -->
      								</div><!-- <div class="modal-footer"> -->
    								</div><!-- <div class="modal-content"> -->		
  									</div><!-- <div class="modal-dialog"> -->
									</div><!-- <div id="myModal" class="modal fade" role="dialog"> -->
										</td>
										<td>
						 				<%-- <Form METHOD="post" ACTION="<%=request.getContextPath()%>/frontend/group_open/group_open.do">
										<input type="hidden" name="action" value="makeorder">
										<input type="hidden" name="group_no" value="${group_openVO.group_no}">${group_openVO.group_no}
										<input type="hidden" name="member_no" value="${group_openVO.member_no}">${group_openVO.member_no}
										<input type="submit" value="進入訂單頁面" class="btn btn-success" >
										 </FORM>	 --%>
										</td>
										<td>
										 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/frontend/group_open/group_open.do" style="margin-bottom: 0px;">
			    							<input type="hidden" name="group_no"  value="${group_openVO.group_no}">
			    							<input type="hidden" name="member_no"  value="${group_openVO.member_no}">
			     							<input type="hidden" name="action"	value="getOne_For_Update">
			     							<input type="submit" value="修改" class="btn-warning btn-sm">
			  							 </FORM>
										</td>
										</tr>
									</c:if>
								  <!--開團進行中 結束  -->
								  <c:if test="${group_openVO.group_status=='sucess3'}">
										<!--開團成功  -->
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
										<td><fmt:formatDate value="${group_openVO.group_time}" pattern="yyyy-MM-dd HH:mm"/></td> 
										<%-- <td>${group_openVO.group_time}</td> --%> 
										<td>${group_openVO.group_status=='sucess3'?"成功":""}</td>
						
										<td>
<%-- 										 <Form METHOD="post" ACTION="<%=request.getContextPath()%>/frontend/group_open/group_open.do">
										<input type="hidden" name="dimiss_reason" value="當天我剛好有事">
										<input type="hidden" name="action" value="quitall">
										<input type="hidden" name="group_no" value="${group_openVO.group_no}">${group_openVO.group_no}
										<input type="hidden" name="member_no" value="${group_openVO.member_no}">${group_openVO.member_no}
										<input type="submit" value="解散該團" >
										 </FORM>	 --%>
										 	  <!-- Trigger the modal with a button -->
									<button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#myModal_${group_openVO.group_no}">退團</button>
								     	<!-- Modal -->
										<div id="myModal_${group_openVO.group_no}" class="modal fade" role="dialog">
  											<div class="modal-dialog">
    									<!-- Modal content-->
   										<div class="modal-content">
      										<div class="modal-header">
        										<button type="button" class="close" data-dismiss="modal">&times;</button>
        											<h4 class="modal-title">退團就像是你的女朋友等不到你過聖誕節一樣的感覺</h4>
      										</div><!-- <div class="modal-header"> -->
     									<div class="modal-body">
											<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/frontend/group_open/group_open.do" style="margin-bottom: 0px;">
					     					 <input type="hidden" name="group_no"  value="${group_openVO.group_no}">
					     					 <input type="hidden" name="member_no"  value="${group_openVO.member_no}">
			  					 
					     					 <div class="form-group">
												<label for="aa">解散該團原因</label>
												<input type="text" name="dimiss_reason" id="aa" class="form-control">
											</div>
					    					 <input type="hidden" name="action" value="quitall">
					    					  <input type="submit" value="解散" class="btn btn-danger btn-sm">
					  						 </FORM>  	
      									</div><!-- <div class="modal-body"> -->
      								<div class="modal-footer">
        								<!-- <button type="submit" class="btn btn-default" data-dismiss="modal">退團</button> -->
      								</div><!-- <div class="modal-footer"> -->
    								</div><!-- <div class="modal-content"> -->		
  									</div><!-- <div class="modal-dialog"> -->
									</div><!-- <div id="myModal" class="modal fade" role="dialog"> -->
										</td>
										<td>
						 				<Form METHOD="post" ACTION="<%=request.getContextPath()%>/frontend/group_open/group_open.do">
										<input type="hidden" name="action" value="makeorder">
										<input type="hidden" name="group_no" value="${group_openVO.group_no}"><%-- ${group_openVO.group_no} --%>
										<input type="hidden" name="member_no" value="${group_openVO.member_no}"><%-- ${group_openVO.member_no} --%>
										<input type="submit" value="進入訂單頁面" class="btn btn-success" >
										 </FORM>	
										</td>
										</tr>
										</c:if>
										
										<c:if test="${group_openVO.group_status=='finish4'}">
								  <!--開團進行中 開始  -->
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
										<td><fmt:formatDate value="${group_openVO.group_time}" pattern="yyyy-MM-dd HH:mm"/></td> 
										<%-- <td>${group_openVO.group_time}</td> --%> 
										<td>${group_openVO.group_status=='finish4'?"完成":""}</td>
						
										<td>
<%-- 										 <Form METHOD="post" ACTION="<%=request.getContextPath()%>/frontend/group_open/group_open.do">
										<input type="hidden" name="dimiss_reason" value="當天我剛好有事">
										<input type="hidden" name="action" value="quitall">
										<input type="hidden" name="group_no" value="${group_openVO.group_no}">${group_openVO.group_no}
										<input type="hidden" name="member_no" value="${group_openVO.member_no}">${group_openVO.member_no}
										<input type="submit" value="解散該團" >
										 </FORM>	 --%>
										 	<%--   <!-- Trigger the modal with a button -->
									<button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#myModal_${group_openVO.group_no}">退團</button>
								     	<!-- Modal -->
										<div id="myModal_${group_openVO.group_no}" class="modal fade" role="dialog">
  											<div class="modal-dialog">
    									<!-- Modal content-->
   										<div class="modal-content">
      										<div class="modal-header">
        										<button type="button" class="close" data-dismiss="modal">&times;</button>
        											<h4 class="modal-title">退團就像是你的女朋友等不到你過聖誕節一樣的感覺</h4>
      										</div><!-- <div class="modal-header"> -->
     									<div class="modal-body">
											<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/frontend/group_open/group_open.do" style="margin-bottom: 0px;">
					     					 <input type="hidden" name="group_no"  value="${group_openVO.group_no}">
					     					 <input type="hidden" name="member_no"  value="${group_openVO.member_no}">
			  					 
					     					 <div class="form-group">
												<label for="aa">解散該團原因</label>
												<input type="text" name="dimiss_reason" id="aa" class="form-control">
											</div>
					    					 <input type="hidden" name="action" value="quitall">
					    					  <input type="submit" value="解散" class="btn btn-danger btn-sm">
					  						 </FORM>  	
      									</div><!-- <div class="modal-body"> -->
      								<div class="modal-footer">
        								<!-- <button type="submit" class="btn btn-default" data-dismiss="modal">退團</button> -->
      								</div><!-- <div class="modal-footer"> -->
    								</div><!-- <div class="modal-content"> -->		
  									</div><!-- <div class="modal-dialog"> -->
									</div><!-- <div id="myModal" class="modal fade" role="dialog"> --> --%>
										</td>
										<td>
						 				<%-- <Form METHOD="post" ACTION="<%=request.getContextPath()%>/frontend/group_open/group_open.do">
										<input type="hidden" name="action" value="makeorder">
										<input type="hidden" name="group_no" value="${group_openVO.group_no}">${group_openVO.group_no}
										<input type="hidden" name="member_no" value="${group_openVO.member_no}">${group_openVO.member_no}
										<input type="submit" value="進入訂單頁面" class="btn btn-success" >
										 </FORM>	 --%>
										</td>
										</tr>
									</c:if>
										
										
										
										
										
										
										
										
										
										
										
										
										
										
										
										
								<!--開團失敗   --> 	
								
								 <c:if test="${group_openVO.group_status=='fail2'}">
										<!--開團成功  -->
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
										<td><fmt:formatDate value="${group_openVO.group_time}" pattern="yyyy-MM-dd HH:mm"/></td> 
										<%-- <td>${group_openVO.group_time}</td> --%> 
										<td>${group_openVO.group_status=='process1'?"進行中":"失敗"}</td>
						
										<td>
<%-- 										 <Form METHOD="post" ACTION="<%=request.getContextPath()%>/frontend/group_open/group_open.do">
										<input type="hidden" name="dimiss_reason" value="當天我剛好有事">
										<input type="hidden" name="action" value="quitall">
										<input type="hidden" name="group_no" value="${group_openVO.group_no}">${group_openVO.group_no}
										<input type="hidden" name="member_no" value="${group_openVO.member_no}">${group_openVO.member_no}
										<input type="submit" value="解散該團" >
										 </FORM>	 --%>
										 	  <%-- <!-- Trigger the modal with a button -->
									<button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#myModal_${group_openVO.group_no}">退團</button>
								     	<!-- Modal -->
										<div id="myModal_${group_openVO.group_no}" class="modal fade" role="dialog">
  											<div class="modal-dialog">
    									<!-- Modal content-->
   										<div class="modal-content">
      										<div class="modal-header">
        										<button type="button" class="close" data-dismiss="modal">&times;</button>
        											<h4 class="modal-title">退團就像是你的女朋友等不到你過聖誕節一樣的感覺</h4>
      										</div><!-- <div class="modal-header"> -->
     									<div class="modal-body">
											<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/frontend/group_open/group_open.do" style="margin-bottom: 0px;">
					     					 <input type="hidden" name="group_no"  value="${group_openVO.group_no}">
					     					 <input type="hidden" name="member_no"  value="${group_openVO.member_no}">
			  					 
					     					 <div class="form-group">
												<label for="aa">解散該團原因</label>
												<input type="text" name="dimiss_reason" id="aa" class="form-control">
											</div>
					    					 <input type="hidden" name="action" value="quitall">
					    					  <input type="submit" value="解散" class="btn btn-danger btn-sm">
					  						 </FORM>  	
      									</div><!-- <div class="modal-body"> -->
      								<div class="modal-footer">
        								<!-- <button type="submit" class="btn btn-default" data-dismiss="modal">退團</button> -->
      								</div><!-- <div class="modal-footer"> -->
    								</div><!-- <div class="modal-content"> -->		
  									</div><!-- <div class="modal-dialog"> -->
									</div><!-- <div id="myModal" class="modal fade" role="dialog"> --> --%>
										</td>
										<td>
		<%-- 				 				<Form METHOD="post" ACTION="<%=request.getContextPath()%>/frontend/group_open/group_open.do">
										<input type="hidden" name="action" value="makeorder">
										<input type="hidden" name="group_no" value="${group_openVO.group_no}">${group_openVO.group_no}
										<input type="hidden" name="member_no" value="${group_openVO.member_no}">${group_openVO.member_no}
										<input type="submit" value="進入訂單頁面" class="btn btn-success" >
										 </FORM>	 --%>
										</td>
										</tr>
										</c:if>
										
										
	
										
								<!--開團失敗   --> 			
								</c:forEach>
								</tbody>
						
					
							</table>
					</div><!-- <div class="col-xs-12 col-sm-10"> -->
			</div><!-- <div class="row"> -->
		</div><!-- <div class="container-fluid"> -->		
		
		<script>		
		</script>
		<jsp:include page="/frontend/footer_front-end.jsp" flush="true" />
	</body>
</html>