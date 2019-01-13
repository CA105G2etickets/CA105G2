<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.member.model.*"%>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>ETIckeTs後台 - 管理員登入</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
</head>

<body>
    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Please Sign In</h3>
                    </div>
                    <div class="panel-body">
						<c:if test="${not empty errorMsgs}">
							<ul>
								<c:forEach var="message" items="${errorMsgs}">
									<p style="color: red">${message}</p>
								</c:forEach>
							</ul>
						</c:if>
						<form  METHOD="post" ACTION="/CA105G2/administrator/administrator.do" role="form">
                            <fieldset>
                                <div class="form-group">
                                    <input class="form-control" id="account" placeholder="account" name="administrator_account" type="text">
                                </div>
                                <div class="form-group">
                                    <input class="form-control" id="password" placeholder="password" name="administrator_password" type="text">
                                </div>
                                <input type="hidden" name="action" value="find_By_Account">
								<input type="submit" value="登入">
<!--                                 <a href="index.html" class="btn btn-info btn-block">Login</a> -->
                            </fieldset>
                        </form>
<!--                         <select size="1" name="memberNo"> -->
<%-- 						<c:forEach var="memberNo" items="member" >  --%>
<%-- 							<option value="${memberNo}">${member.memberNo} --%>
<%-- 						</c:forEach>    --%>
<!-- 						</select> -->
						<jsp:useBean id="administratorservice" scope="page"
				class="com.administrator.model.AdministratorService" />

			  <li>
			       選擇管理員帳號
			       <select size="1" name="administratorA" onchange="changeA" id="administratorlist" autofocus>
			         <c:forEach var="administrator" items="${administratorservice.all}" > 
			          <option value="${administrator.administrator_account},${administrator.administrator_password}">${administrator.administrator_account},${administrator.administrator_password}
			         </c:forEach>
			       </select>
			  </li>
					</div>
                </div>
            </div>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
			$(document).ready(function(){
				$('#administratorlist').prepend(new Option('','', true));
				$('#administratorlist')[0].selectedIndex = 0;
				$('#administratorlist').on('change',function(){
					var str = $(this).val();
					$('#account').val(str.substring(0,str.indexOf(',',1)));
					$('#password').val(str.substring(str.indexOf(',',1)+1,str.lastIndexOf('')));
				});
			})
	</script>
</body>

</html>