<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.member.model.*"%>

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>ETIckeTs - 會員註冊</title>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery.js"></script>
<link rel="stylesheet" media="screen" href="<%=request.getContextPath()%>/frontend/member/Signup.css" />
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
    
<jsp:include page="/frontend/navbar_front-end.jsp" flush="true"/>
    
<body>
	<div class="wrapper login-wrapper">
            
    <div class="tab-block">
        <nav>
    <ul>
    <a href="<%=request.getContextPath()%>/frontend/login_front-end.jsp"><li class="last" style="width: 10%; height: 100%; text-align: center; background-color: #f8f8f8;"><font color="black">登入</font></li></a>
    <a href="<%=request.getContextPath()%>/frontend/signup_front-end.jsp" class="signupforsigninpage"><li class="first active" style="width: 10%; height: 100%; text-align: center;"><font color="white" class="signupforsigninpage">註冊</font></li></a>
    </ul>
</nav>


<!-- 第三方登入區 -->
<div class="tab-block-content">
<div class="oauth">
<p>您可以直接透過以下帳號登入</p>
<div class="fb btn-login" style="background-color: #3b5998;"><font color="white">Facebook</font></div>
<div class="fb btn-login" style="background-color: #e9644a;"><font color="white">Google</font></div>
</div>



<div class="normal">
<p>或註冊新的ETIckeTs帳號</p>
<form method="post" action="<%=request.getContextPath()%>/member/member.do" role="form">
     <div class="control-group">
     <div class="controls">
     	<input style="text-transform:lowercase;" id="account" class="string required" placeholder="Email" type="text" name="member_account" size="30"/>
     	</div>
     	</div>
     <div class="control-group">
     <div class="controls">
     	<input placeholder="密碼" type="password" id="password" name="member_password" size="30"/>
     	</div>
     	</div>
     <div class="control-group">
     <div class="controls">
     	<input placeholder="再次輸入密碼" type="password" id="password" name="member_password" size="30"/>
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
     <input type="submit" value="註冊" class="btn btn-block" style="border-color: #f2f2f2; text-align: center; background-color: #f8f8f8;" disabled>
</form>

            </div>
        </div>
    </div>
</div>

<jsp:include page="/frontend/footer_front-end.jsp" flush="true"/> 

<script>
			$(document).ready(function(){
				$('#userlist').prepend(new Option('account,password','', true));
				$('#userlist')[0].selectedIndex = 0;
				$('#userlist').on('change',function(){
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