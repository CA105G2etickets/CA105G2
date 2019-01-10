<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page import="java.util.*"%>
<%@ page import="com.goods.model.*"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">


<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>活動管理</title>
<!-- Basic -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<!-- dataTables -->
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
</head>

<style>
	.actionForm{
		display: inline;
	}
	body{
		font-family:微軟正黑體!important;
	}
</style>

<body>



	<jsp:include page="/backend/navbar_back-end.jsp" flush="true" />



	<div class="container">
		<span class="text-danger">${eventTitleErrorMsgs.evetit_no}</span>
		<span class="text-danger">${eventTitleErrorMsgs.Exception}</span>
		<span class="text-danger">${eventErrorMsgs.Exception}</span>
	</div>
		
		
		
		
		
		
		
	
	
	<!-- <table id="table-1"> -->
	<!--    <tr><td><h3>ETIckeTs Member</h3><h4>( MVC )</h4></td></tr> -->
	<!-- </table> -->
	<div class="container">
	<div class="col-xs-12 col-sm-12">
		<p>ETIckeTs Member管理頁面</p>
	</div>
	</div>
	<div class="container">
	<div class="col-xs-12 col-sm-12">
		會員查詢

		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}"><br>
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<p style="color: red">${message}</p>
				</c:forEach>
			</ul>
		</c:if>

		<ul>

			<li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/goods/GoodsServlet.do" >
        <b>輸入商品編號 (如P0000001):</b>
        <input type="text" name="goods_no">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>
<a href='listAllGoods.jsp'>列出所有商品</a>
  <jsp:useBean id="goodsSvc" scope="page" class="com.goods.model.GoodsService" />
 
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/goods/GoodsServlet.do" >
       <b>選擇商品編號:</b>
       <select size="1" name="goods_no">
       
         <c:forEach var="goodsVO"  items="${goodsSvc.all}" > 
          <option value="${goodsVO.goods_no}">${goodsVO.goods_no}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/goods/GoodsServlet.do" >
       <b>選擇商品名稱:</b>
       <select size="1" name=goods_no>
         <c:forEach var="goodsVO" items="${goodsSvc.all}" > 
          <option value="${goodsVO.goods_no}">${goodsVO.goods_name}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
	</ul>
		</div>
	</div>
	<div class="container">
	<div class="col-xs-12 col-sm-12">
		會員管理

		<ul>
			<li><a href="<%=request.getContextPath()%>/backend/goods/addGoods.jsp" >新增商品</a></li>
		</ul>
	</div>
	</div>
	</body>
</html>