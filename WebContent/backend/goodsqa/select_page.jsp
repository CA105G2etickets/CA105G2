<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>ETIckeTs QA</title>
</head>

<jsp:include page="/backend/navbar_back-end.jsp" flush="true"/> 


<div class="container">
	<div class="col-xs-12 col-sm-12">
		<font size="6px">ETIckeTs問答管理頁面</font>
	</div>
</div>
<div class="container">
	<div class="col-xs-12 col-sm-12">
		<font size="5px">問題查詢</font>

	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>


<a href='listAllGoodsqa.jsp'>List</a> 所有問題  <br><br>
  
  

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
  
  



<h3>員工管理</h3>

<ul>
  <li><a href='addGoodsqa.jsp'>Add</a> a new Emp.</li>
</ul>
<script src="https://code.jquery.com/jquery.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>

</html>