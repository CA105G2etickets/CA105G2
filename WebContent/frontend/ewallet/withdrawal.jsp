<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>

<%
  MemberVO member = (MemberVO) session.getAttribute("member");
%>

<html>
<head>
<title>ETIckeTs - 個人電子錢包提領</title>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script>
</script>
<style>
.wallethref {
	color: black;
	font-weight: bold;
}
.walletcolumn {
    display: inline-block;
    margin-right: 15px;
    margin-bottom: 5px;
    vertical-align: top;
}
.walletcolumn-2 {
    width: calc((100% - 19px * 3) / 1);
}
.wallettabs {
    position: relative;
}
.walletblock {
    position: relative;
    background-color: #f6f6f6;
    margin-bottom: 24px;
    padding: 50px;
}
.walletblock.walletblock-m {
    min-height: 50px;
    padding: 10px 10px;
}
.walletimage {
    display:block;
    margin:auto;
    width: 80px;
    height: 80px;
}
body{
	font-family:微軟正黑體!important;
}
</style>
</head>

<body>

<jsp:include page="/frontend/navbar_front-end.jsp" flush="true"/>

<div class="container">
	<div class="walletcolumn walletcolumn-2">
		<div class="wallettabs">
			<div class="walletblock walletblock-m">
				<img src="<%=request.getContextPath()%>/frontend/ewallet/images/提領.png" class="walletimage">
				<h3 class="heading" align="center">提領</h3>
			</div>
		</div>
	</div>
</div>

<div class="container">
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="/CA105G2/member/member.do" name="form1" enctype="multipart/form-data">
<table class="table">
	<tr>
		<td>會員編號:<font color=red></font></td>
		<td><%=member.getMemberNo()%></td>
	</tr>
	<tr>
		<td>姓名</td>
		<td><%=member.getMemberFullname()%></td>
	</tr>
	<tr>
		<td>電子郵件</td>
		<td><%=member.getEmail()%></td>
	</tr>
	<tr>
		<td>手機號碼</td>
		<td><%=member.getPhone()%></td>
	</tr>
	<tr>
		<td>身分證字號</td>
		<td><%=member.getIdcard()%></td>
	</tr>
	<tr>
		<td><font style="color:red">請確認以上資訊並輸入提領金額與銀行帳號</font></td>
		<td></td>
	</tr>
	<tr>
		<td>提領金額</td>
		<td><input type="TEXT" name="ewalletBalance" size="45" placeholder="目前餘額<%=member.getEwalletBalance()%>" /></td>
	</tr>
	<tr>
		<td>銀行帳號</td>
		<td><input type="TEXT" name="bankAccount" size="45"/></td>
	</tr>
</table>
<br>
<input type="hidden" name="memberno" value="<%=member.getMemberNo()%>">
<input type="hidden" name="memberFullname" value="<%=member.getMemberFullname()%>">
<input type="hidden" name="email" value="<%=member.getEmail()%>">
<input type="hidden" name="phone" value="<%=member.getPhone()%>">
<input type="hidden" name="idcard" value="<%=member.getIdcard()%>">
<input type="hidden" name="memberAccount" value="<%=member.getMemberAccount()%>">
<input type="hidden" name="memberPassword" value="<%=member.getMemberPassword()%>">
<input type="hidden" name="creationDate" value="<%=member.getCreationDate()%>">
<input type="hidden" name="memberStatus" value="<%=member.getMemberStatus()%>">
<input type="hidden" name="thirduid" value="<%=member.getThirduid()%>">
<input type="hidden" name="action" value="update_withdrawal">
<input type="submit" value="申請提領"></FORM>

</div>

</body>
<jsp:include page="/frontend/footer_front-end.jsp" flush="true"/> 
</html>