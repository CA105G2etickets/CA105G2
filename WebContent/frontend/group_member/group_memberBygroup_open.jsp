<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.group_member.model.*"%>  
<%@ page import="com.group_open.model.*"%>
<%@ page import="com.member.model.*"%>
 <!-- 我的跟團紀錄 -->
 <%-- <jsp:useBean id="group_openBymember_no" scope="request" type="java.util.List<Group_memberVO>"/>    <!-- insert2 -->  --%>
  
<jsp:useBean id="group_openSvc" scope="page" class="com.group_open.model.Group_openService" /> 
<jsp:useBean id="group_memberSvc" scope="page" class="com.group_member.model.Group_memberService"/>
<% MemberVO memberVOsession = (MemberVO)session.getAttribute("member"); %>

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
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"><!-- bootstrapjavascript -->
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script><!-- bootstrapjavascript -->
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script><!-- bootstrapjavascript -->
		
		<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
	<script>
		/* $(document).ready(function(){
 	    $("#myBtn").click(function(){
        $("#myModal").modal();
         });
         }); */
         </script>
	</head>
	<body>
			<jsp:include page="/frontend/navbar_front-end.jsp" flush="true" />
		<div class="container-fluid" style ="margin-bottom: 200px">
			<div class="row">
				<div class="col-xs-12 col-sm-2">
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
						<!-- 跟團人所有跟團 -->
						<c:forEach var="group_memberVO" items= "${group_openBymember_no}" >
					<c:if test="${group_memberVO.group_member_status=='withgroup'||group_memberVO.group_member_status=='quit'}">
						<!--EL自動去找page request session   -->
						<%--  <c:if test="${group_memberVO.group_member_status=='withgroup'||group_memberVO.group_member_status=='quit'}"> --%><!-- 顯示自己有的跟團 -->
						<c:forEach var="group_openVO" items="${group_openSvc.all}">
                   					    <c:if test="${group_memberVO.group_no==group_openVO.group_no}">
                   					    <c:if test="${group_openVO.group_status=='sucess3'}">
	                   					<!--開團成功開始  -->
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
									${group_memberVO.group_member_status=='withgroup'?"跟團中":"退團"}
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
	                   						 ${group_openVO.group_status=='sucess3'?"成功":""} 
                    					</c:if>
               						</c:forEach>								
								</td>
								<td>
								<%--  <FORM METHOD="post" ACTION="group_member.do" style="margin-bottom: 0px;">
			     					<input type="submit" value="修改" class="btn-warning btn-sm">
			     					<input type="hidden" name="group_no"  value="${group_memberVO.group_no}">
			     					<input type="hidden" name="member_no"  value="${group_memberVO.member_no}">
			     					<input type="hidden" name="action"	value="getOne_For_Update">
			 					 </FORM> --%>		
								</td>
								<td> 
			  						 <%-- <!-- Trigger the modal with a button -->
									<button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#myModal_${group_memberVO.group_no}">退團</button>
								     	<!-- Modal -->
										<div id="myModal_${group_memberVO.group_no}" class="modal fade" role="dialog">
  											<div class="modal-dialog">
    									<!-- Modal content-->
   										<div class="modal-content">
      										<div class="modal-header">
        										<button type="button" class="close" data-dismiss="modal">&times;</button>
        											<h4 class="modal-title">退團就像是你的女朋友等不到你過聖誕節一樣的感覺</h4>
      										</div><!-- <div class="modal-header"> -->
     									<div class="modal-body">
											<FORM METHOD="post" ACTION="group_member.do" style="margin-bottom: 0px;">
					     					 <input type="hidden" name="group_no"  value="${group_memberVO.group_no}">
					     					 <input type="hidden" name="member_no"  value="${group_memberVO.member_no}">
					     					 <input type="hidden" name="group_member_status"  value="quit">
					     					 <input type="hidden" name="pay_status"  value="CANCEL3">	     					 
					     					 <div class="form-group">
												<label for="aa">退團原因</label>
												<input type="text" name="log_out_reason" id="aa" class="form-control">
											</div>
					    					 <input type="hidden" name="action" value="quit">
					    					  <input type="submit" value="退團" class="btn btn-danger btn-sm">
					  						 </FORM>  	
      									</div><!-- <div class="modal-body"> -->
      								<div class="modal-footer">
        								<!-- <button type="submit" class="btn btn-default" data-dismiss="modal">退團</button> -->
      								</div><!-- <div class="modal-footer"> -->
    								</div><!-- <div class="modal-content"> -->		
  									</div><!-- <div class="modal-dialog"> -->
									</div><!-- <div id="myModal" class="modal fade" role="dialog"> --> --%>
								</td>			
							</tr>
		   					<!--開團成功結束  -->
	                   					</c:if>
                    					</c:if>
               						</c:forEach>
               						<c:forEach var="group_openVO" items="${group_openSvc.all}">
                   					    <c:if test="${group_memberVO.group_no==group_openVO.group_no}">
                   					    <c:if test="${group_openVO.group_status=='process1'}">
	                   					<!--開團進行中開始  -->
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
									${group_memberVO.group_member_status=='withgroup'?"跟團中":"退團"}
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
	                   						 ${group_openVO.group_status=='process1'?"進行中":"進行中這欄有錯"} 
                    					</c:if>
               						</c:forEach>								
								</td>
								<td>
								 <FORM METHOD="post" ACTION="group_member.do" style="margin-bottom: 0px;">
			     					<input type="submit" value="修改" class="btn-warning btn-sm">
			     					<input type="hidden" name="group_no"  value="${group_memberVO.group_no}">
			     					<input type="hidden" name="member_no"  value="${group_memberVO.member_no}">
			     					<input type="hidden" name="action"	value="getOne_For_Update">
			 					 </FORM>		
								</td>
								<td> 
			  						 <!-- Trigger the modal with a button -->
									<button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#myModal_${group_memberVO.group_no}">退團</button>
								     	<!-- Modal -->
										<div id="myModal_${group_memberVO.group_no}" class="modal fade" role="dialog">
  											<div class="modal-dialog">
    									<!-- Modal content-->
   										<div class="modal-content">
      										<div class="modal-header">
        										<button type="button" class="close" data-dismiss="modal">&times;</button>
        											<h4 class="modal-title">退團就像是你的女朋友等不到你過聖誕節一樣的感覺</h4>
      										</div><!-- <div class="modal-header"> -->
     									<div class="modal-body">
											<FORM METHOD="post" ACTION="group_member.do" style="margin-bottom: 0px;">
					     					 <input type="hidden" name="group_no"  value="${group_memberVO.group_no}">
					     					 <input type="hidden" name="member_no"  value="${group_memberVO.member_no}">
					     					 <input type="hidden" name="group_member_status"  value="quit">
					     					 <input type="hidden" name="pay_status"  value="CANCEL3">	     					 
					     					 <div class="form-group">
												<label for="aa">退團原因</label>
												<input type="text" name="log_out_reason" id="aa" class="form-control">
											</div>
					    					 <input type="hidden" name="action" value="quit">
					    					  <input type="submit" value="退團" class="btn btn-danger btn-sm">
					  						 </FORM>  	
      									</div><!-- <div class="modal-body"> -->
      								<div class="modal-footer">
        								<!-- <button type="submit" class="btn btn-default" data-dismiss="modal">退團</button> -->
      								</div><!-- <div class="modal-footer"> -->
    								</div><!-- <div class="modal-content"> -->		
  									</div><!-- <div class="modal-dialog"> -->
									</div><!-- <div id="myModal" class="modal fade" role="dialog"> -->
								</td>			
							</tr>	
						
						<!--開團進行中結束  -->
	                   					</c:if>
                    					</c:if>
               						</c:forEach>
               							<c:forEach var="group_openVO" items="${group_openSvc.all}">
                   					    <c:if test="${group_memberVO.group_no==group_openVO.group_no}">
                   					    <c:if test="${group_openVO.group_status=='finish4'}">
	                   					<!--開團進行中開始  -->
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
									${group_memberVO.group_member_status=='withgroup'?"跟團中":"退團"}
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
	                   						 ${group_openVO.group_status=='finish4'?"完成":"完成這邊有錯"} 
                    					</c:if>
               						</c:forEach>								
								</td>
								<td>
								<%--  <FORM METHOD="post" ACTION="group_member.do" style="margin-bottom: 0px;">
			     					<input type="submit" value="修改" class="btn-warning btn-sm">
			     					<input type="hidden" name="group_no"  value="${group_memberVO.group_no}">
			     					<input type="hidden" name="member_no"  value="${group_memberVO.member_no}">
			     					<input type="hidden" name="action"	value="getOne_For_Update">
			 					 </FORM>		 --%>
								</td>
								<td> 
			  						<%--  <!-- Trigger the modal with a button -->
									<button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#myModal_${group_memberVO.group_no}">退團</button>
								     	<!-- Modal -->
										<div id="myModal_${group_memberVO.group_no}" class="modal fade" role="dialog">
  											<div class="modal-dialog">
    									<!-- Modal content-->
   										<div class="modal-content">
      										<div class="modal-header">
        										<button type="button" class="close" data-dismiss="modal">&times;</button>
        											<h4 class="modal-title">退團就像是你的女朋友等不到你過聖誕節一樣的感覺</h4>
      										</div><!-- <div class="modal-header"> -->
     									<div class="modal-body">
											<FORM METHOD="post" ACTION="group_member.do" style="margin-bottom: 0px;">
					     					 <input type="hidden" name="group_no"  value="${group_memberVO.group_no}">
					     					 <input type="hidden" name="member_no"  value="${group_memberVO.member_no}">
					     					 <input type="hidden" name="group_member_status"  value="quit">
					     					 <input type="hidden" name="pay_status"  value="CANCEL3">	     					 
					     					 <div class="form-group">
												<label for="aa">退團原因</label>
												<input type="text" name="log_out_reason" id="aa" class="form-control">
											</div>
					    					 <input type="hidden" name="action" value="quit">
					    					  <input type="submit" value="退團" class="btn btn-danger btn-sm">
					  						 </FORM>  	
      									</div><!-- <div class="modal-body"> -->
      								<div class="modal-footer">
        								<!-- <button type="submit" class="btn btn-default" data-dismiss="modal">退團</button> -->
      								</div><!-- <div class="modal-footer"> -->
    								</div><!-- <div class="modal-content"> -->		
  									</div><!-- <div class="modal-dialog"> -->
									</div><!-- <div id="myModal" class="modal fade" role="dialog"> --> --%>
								</td>			
							</tr>		
						<!--開團進行中結束  -->
	                   					</c:if>
                    					</c:if>
               						</c:forEach>
               							<c:forEach var="group_openVO" items="${group_openSvc.all}">
                   					    <c:if test="${group_memberVO.group_no==group_openVO.group_no}">
                   					    <c:if test="${group_openVO.group_status=='fail2'}">
	                   					<!--開團失敗開始  -->
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
									${group_memberVO.group_member_status=='withgroup'?"跟團中":"退團"}
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
	                   						 ${group_openVO.group_status=='fail2'?"失敗":""} 
                    					</c:if>
               						</c:forEach>								
								</td>
								<td>
								<%--  <FORM METHOD="post" ACTION="group_member.do" style="margin-bottom: 0px;">
			     					<input type="submit" value="修改" class="btn-warning btn-sm">
			     					<input type="hidden" name="group_no"  value="${group_memberVO.group_no}">
			     					<input type="hidden" name="member_no"  value="${group_memberVO.member_no}">
			     					<input type="hidden" name="action"	value="getOne_For_Update">
			 					 </FORM>		 --%>
								</td>
								<td> 
			  						<%--  <!-- Trigger the modal with a button -->
									<button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#myModal_${group_memberVO.group_no}">退團</button>
								     	<!-- Modal -->
										<div id="myModal_${group_memberVO.group_no}" class="modal fade" role="dialog">
  											<div class="modal-dialog">
    									<!-- Modal content-->
   										<div class="modal-content">
      										<div class="modal-header">
        										<button type="button" class="close" data-dismiss="modal">&times;</button>
        											<h4 class="modal-title">退團就像是你的女朋友等不到你過聖誕節一樣的感覺</h4>
      										</div><!-- <div class="modal-header"> -->
     									<div class="modal-body">
											<FORM METHOD="post" ACTION="group_member.do" style="margin-bottom: 0px;">
					     					 <input type="hidden" name="group_no"  value="${group_memberVO.group_no}">
					     					 <input type="hidden" name="member_no"  value="${group_memberVO.member_no}">
					     					 <input type="hidden" name="group_member_status"  value="quit">
					     					 <input type="hidden" name="pay_status"  value="CANCEL3">	     					 
					     					 <div class="form-group">
												<label for="aa">退團原因</label>
												<input type="text" name="log_out_reason" id="aa" class="form-control">
											</div>
					    					 <input type="hidden" name="action" value="quit">
					    					  <input type="submit" value="退團" class="btn btn-danger btn-sm">
					  						 </FORM>  	
      									</div><!-- <div class="modal-body"> -->
      								<div class="modal-footer">
        								<!-- <button type="submit" class="btn btn-default" data-dismiss="modal">退團</button> -->
      								</div><!-- <div class="modal-footer"> -->
    								</div><!-- <div class="modal-content"> -->		
  									</div><!-- <div class="modal-dialog"> -->
									</div><!-- <div id="myModal" class="modal fade" role="dialog"> --> --%>
								</td>			
							</tr>
	
						<!--開團失敗結束  -->
	                   					</c:if>
                    					</c:if>
               						</c:forEach><%-- <c:forEach var="group_openVO" items="${group_openSvc.all}"> --%>
               					</c:if>
							</c:forEach><%-- <c:forEach var="group_memberVO" items= "${group_openBymember_no}" > --%>
						</tbody>
					</table>
				</div><!-- <div class="col-xs-12 col-sm-10"> -->
			</div> <!-- <div class="row"> -->
		</div>	<!-- <div class="container"> -->

		
	 <jsp:include page="/frontend/footer_front-end.jsp" flush="true" />	
	</body>
</html>