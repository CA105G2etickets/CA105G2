<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.administrator.model.*"%>

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>ETIckeTs後台 - 管理員登入</title>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery.js"></script>
<link rel="stylesheet" media="screen" href="<%=request.getContextPath()%>/frontend/member/Login.css" />
<!--[if lt IE 9]>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->

<style>
.wrapper.login-wrapper {
    background: none;
    position: relative;
    min-height: 30%;
}
.btn-login {
    display: block;
    width: 220px;
    height: 40px;
    line-height: 40px;
    text-align: center;
    margin-bottom: 10px;
    font-size: 18px;
    border-radius: 2px;
    background: white;
    box-shadow: white 0 0 0 inset;
    text-decoration: none;
}
ul, menu, dir {
    display: block;
    list-style-type: disc;
    margin-block-start: 1em;
    margin-block-end: 1em;
    margin-inline-start: 0px;
    margin-inline-end: 0px;
    padding-inline-start: 40px;
}
body{
	font-family:微軟正黑體!important;
}
</style>

</head>
    
<jsp:include page="/backend/navbar_back-end.jsp" flush="true"/>
    
<body>
	<div class="wrapper login-wrapper">
            
    <div class="tab-block" style="width: 432px; height: 264px;">
        <nav>
    <ul>
    <a href="<%=request.getContextPath()%>/backend/login_back-end.jsp"><li class="first active" style="width: calc((100% * 2) / 3); height: 100%; text-align: center; background: #4f4f4f;"><font color="white">登入</font></li></a>
    </ul>
</nav>

<div class="normal">
<p>請使用ETIckeTs管理員帳號登入</p>
<form method="post" action="<%=request.getContextPath()%>/administrator/administrator.do" role="form">
     <div class="control-group">
     <div class="controls">
     	<input id="account" class="string required" placeholder="管理員帳號" type="text" name="administrator_account" size="38"/>
     	</div>
     	</div>
     <div class="control-group">
     <div class="controls">
     	<input placeholder="管理員密碼" type="password" id="password" name="administrator_password" size="38"/>
     	</div>
     	</div>
     <input type="hidden" name="action" value="find_By_Account">
     <c:if test="${not empty errorMsgs}">
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<p style="color: red">${message}</p>
			</c:forEach>
		</ul>
	</c:if>
     <input type="submit" value="登入" class="btn btn-block" style="border-color: #f2f2f2; text-align: center; background-color: #f8f8f8; width: 288px;">
</form>

<jsp:useBean id="administratorservice" scope="page" class="com.administrator.model.AdministratorService" />
			  <li>
			      選擇管理員帳號
			       <select size="1" name="administratorA" onchange="changeA" id="administratorlist" autofocus>
			         <c:forEach var="administrator" items="${administratorservice.all}" > 
			          <option value="${administrator.administrator_account},${administrator.administrator_password}">${administrator.administrator_name}
			         </c:forEach>
			       </select>
			  </li>

        </div>
    </div>
</div>

<script>
			$(document).ready(function(){
				$('#administratorlist').prepend(new Option('選擇管理員輸入帳密','', true));
				$('#administratorlist')[0].selectedIndex = 0;
				$('#administratorlist').on('change',function(){
					var str = $(this).val();
					$('#account').val(str.substring(0,str.indexOf(',',1)));
					$('#password').val(str.substring(str.indexOf(',',1)+1,str.lastIndexOf('')));
				});
				$('li.last').hover(function() {
					$(this).css("background-color","#2d89e5");
					$('.signupforsigninpage').css("color","white");
				},function() {
					$(this).css("background-color","#f8f8f8");
					$('.signupforsigninpage').css("color","black");
				});
			})
</script>

</body>
</html>