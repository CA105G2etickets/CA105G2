<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page import="java.util.*"%>
<%@ page import="com.event_title.model.*"%>
<%@ page import="com.event_title.controller.*"%>

<%@ page import="com.favorite_event.model.*"%>

<%@ page import="com.member.model.*"%>

<%
	String member_no = null;

	if (session.getAttribute("member") == null) {  
		session.setAttribute("location", request.getRequestURI()); 
		response.sendRedirect(request.getContextPath()+"/frontend/login_front-end.jsp"); 
		return;
	}

	if(session.getAttribute("member") != null){
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		member_no = memberVO.getMemberNo();
	}

	FavoriteEventService favoriteEventService = new FavoriteEventService();
	List<FavoriteEventVO> list = favoriteEventService.findFavoriteEventByMember(member_no);
	pageContext.setAttribute("listFavoriteEventTitle_ByMember", list); 
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>瀏覽最愛活動</title>
    <!-- Basic -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <style>
	    @media only screen and (min-width : 481px) {
		    .flex-row.row {
		        display: flex;
		        flex-wrap: wrap;
		    }
		    .flex-row.row>[class*='col-'] {
		        display: flex;
		        flex-direction: column;
		    }
		    .flex-row.row:after, .flex-row.row:before {
		        display: flex;
		    }
		}
		.flex-row .thumbnail, .flex-row .caption {
		    flex: 1 0 auto;
		    flex-direction: column;
		}
		.flex-text {
		    flex-grow: 1
		}
		.flex-row img {
		    height: auto;
		    width: 100%
		}
		.clickFavoriteEvent{
			cursor: pointer;
		}
		body{
			font-family:微軟正黑體!important;
		}
	</style>
	<!-- Basic -->
    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>



	<jsp:include page="/frontend/navbar_front-end.jsp" flush="true" />
	
	
	
	<input type="hidden" name="member_no" id="member_no" value="${member.memberNo}">
	<input type="hidden" name="projectName" id="projectName" value="<%=request.getContextPath() %>">

    <div class="container">
        <ol class="breadcrumb">
            <li>
                <a href="<%= request.getContextPath()%>/frontend/index.jsp">首頁</a>
            </li>
            <li>
                <a href="<%= request.getContextPath()%>/frontend/favorite/selectFavorite.jsp">我的最愛</a>
            </li>
            <li class="active">最愛活動</li>
        </ol>
    </div>

    <div class="container">
        <div class="row flex-row">
            <c:forEach var="favoriteEventVO" items="${listFavoriteEventTitle_ByMember}">
                <div class="col-xs-12 col-sm-4">
                    <div class="thumbnail">
                    	<jsp:useBean id="eventTitleService" scope="page" class="com.event_title.model.EventTitleService" />
                        <a href="<%= request.getContextPath()%>/frontend/event_title/listOneEventTitle.jsp?evetit_no=${favoriteEventVO.evetit_no}">
                            <img src="<%= request.getContextPath()%>/event_title/EventTitleGifReader?scaleSize=425&evetit_no=${favoriteEventVO.evetit_no}" alt="">
                        </a>                       
                        <div class="caption">
                            <h4 style="height:25px;"><a href="<%= request.getContextPath()%>/frontend/event_title/listOneEventTitle.jsp?evetit_no=${favoriteEventVO.evetit_no}">
                            	${eventTitleService.getOneEventTitle(favoriteEventVO.evetit_no).evetit_name}                   
                            </a></h4>
                            <input type="hidden" name="evetit_no" value="${favoriteEventVO.evetit_no}">
							<div style="color:red;float:right;" class="clickFavoriteEvent">
								<h4><i class="glyphicon glyphicon-heart"></i>取消最愛</h4>
			             	</div>
                        </div>                   
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>



	<script>
		$(document).ready(function(){
			$(".clickFavoriteEvent").click(function(){
				var member_no = $("#member_no").val();
	        	var evetit_no = $(this).prev().val();
	        	
        		var url = $("#projectName").val();
                url += '/favorite_event/FavoriteEventServlet.do';
                var data = '';
              	data += 'member_no=';
               	data += member_no;
               	data += '&evetit_no=';
               	data += evetit_no;
               	data += '&';
                data += 'action=deleteFavoriteEvent';
                console.log(data);
                $.ajax({
                    type: 'post',
                    url: url,
                    data: data,
                    success: function(data) {              	
                    	if(data.indexOf("成") != -1){
                    		location.reload();
                    	} else {
                    		window.alert(data);
                    	}
                    }
                });	
			});
		});
	</script>



</body>
</html>