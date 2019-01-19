<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.member.model.*"%>

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>ETIckeTs - 會員登入</title>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
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
    
<jsp:include page="/frontend/navbar_front-end.jsp" flush="true"/>
    
<body>
	<div class="wrapper login-wrapper">
            
    <div class="tab-block">
        <nav>
    <ul>
    <li class="first active"><a href="/users/sign_in">登入</a></li>
<!--     <li class="last"><a href="/users/sign_up">註冊</a></li> -->
    </ul>
</nav>


<!-- 第三方登入區 -->
<div class="tab-block-content">
<div class="oauth">
<p>您可以透過下列帳號登入：</p>
<div class="fb btn-login" style="background-color: #3b5998;">Facebook</div>
<div class="fb btn-login" style="background-color: #e9644a;">Google</div>
</div>



<div class="normal">
<p>或使用 ETIckeTs 帳號登入：</p>
<form novalidate="novalidate" class="simple_form new_user" id="new_user" action="/users/sign_in" accept-charset="UTF-8" method="post"><input name="utf8" type="hidden" value="&#x2713;" /><input type="hidden" name="authenticity_token" value="4Un7jfM5YJ+Fa8pIPeZ8vkFgzAJ5uffxqrfaUG8RqqBmRzvCR0MICI9me8+Zh2m65RnGmTF1yVSbNSVV0a1OAA==" />
     <div class="control-group string required user_login">
     <div class="controls">
     	<input data-email-suggestion="你要用的是 {{suggestion}} 嗎?" style="text-transform:lowercase;" class="string required" placeholder="使用者名稱或 Email" type="text" name="user[login]" id="user_login" />
     	</div>
     	</div>
     <div class="control-group password required user_password">
     <div class="controls">
     	<input class="password required" placeholder="密碼" type="password" name="user[password]" id="user_password" />
     	</div>
     	</div>
     <input type="submit" name="commit" value="登入"/>
</form>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/frontend/footer_front-end.jsp" flush="true"/> 

</body>
</html>