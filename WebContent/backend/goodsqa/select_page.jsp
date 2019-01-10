<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">	
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
	<title>問答紀錄查詢</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
</head>

<body >

<table id="table-1">
   <tr><td><h3>IBM Emp: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Emp: Home</p>

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>


<a href='listAllGoodsqa.jsp'>List</a> all Emps.  <br><br>
  
  

    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/goodsqa/GoodsQaServlet.do" >
        <b>輸入問題編號 (如7001):</b>
        <input type="text" name="gfaq_no">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  

  <jsp:useBean id="goodsqaSvc" scope="page" class="com.goods_qa.model.GoodsQaService" />
   
  
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/goodsqa/GoodsQaServlet.do" >
       <b>選擇問題編號:</b>
       <select size="1" name="gfaq_no">
         <c:forEach var="goodsqaVO" items="${goodsqaSvc.all}" > 
          <option value="${goodsqaVO.gfaq_no}">${goodsqaVO.gfaq_no}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出" class="btn btn-info">
    </FORM>
  
  

     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/goodsqa/GoodsQaServlet.do" >
       <b>選擇商品編號:</b>
       <select size="1" name="gfaq_no">
         <c:forEach var="goodsqaVO" items="${goodsqaSvc.all}" > 
          <option value="${goodsqaVO.goods_no}">${goodsqaVO.goods_no}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出"class="btn btn-info">
     </FORM>



<h3>員工管理</h3>

<ul>
  <li><a href='addGoodsqa.jsp'>Add</a> a new Emp.</li>
</ul>
<script src="https://code.jquery.com/jquery.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>

</html>