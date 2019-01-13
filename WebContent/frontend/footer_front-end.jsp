<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script>
	$(document).ready(function() {
		$(".footer").hover(function() {
			$(this).css("text-decoration","none");
		});
	});
</script>
<style>
.footer {
	color: black;
}
</style>
<footer style="background-color: rgb(51, 153, 255);">
<div class="container-fluid">
	<div align="center" class="row">
	<br> | <a href="<%=request.getContextPath()%>/frontend/others/aboutus.jsp" class="footer">關於我們</a> | <a href="<%=request.getContextPath()%>/frontend/others/servicepolicy.jsp" class="footer">會員服務條款</a> | <a href="<%=request.getContextPath()%>/frontend/others/privacy.jsp" class="footer">隱私權政策</a> | <br>
	<br>
	<br> ETIckeTs娛樂客服信箱
	<p>ca105.java.002@gmail.com</p>
	<br>
	</div>
</div>
</footer>
</html>