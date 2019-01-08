<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CA105G2_Group_open</title>
<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

</head>
<body bgcolor = 'white'>

<table id="table-1">
   <tr><td><h3>CA105G2 group_open: Home</h3><h4>( MVC )</h4></td></tr>
</table>
<p>This is home for CA105G2 : home</p>
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
		<ul>
		<li><a href='listAllgroup_open.jsp'>List</a> all group_open.  <br><br></li>
		
		
	 <li>
     	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/group_open/group_open.do" >
        <b>輸入開團編號 (如G0001):</b>
        <input type="text" name="group_no">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
     	</FORM>
   </li>

        <jsp:useBean id="group_openSvc" scope="page" class="com.group_open.model.Group_openService" />
        
        <li>
    		 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/group_open/group_open.do" >
      		 <b>選擇編號:</b>
       		<select size="1" name="group_no">
       		  <c:forEach var="group_openVO" items="${group_openSvc.all}" > 
       		   <option value="${group_openVO.group_no}">${group_openVO.group_no}   
       		  </c:forEach>   
     	    </select>
       			<input type="hidden" name="action" value="getOne_For_Display">
       			<input type="submit" value="送出">
   			 </FORM>
       </li>
</ul>
			<%-- 萬用複合查詢-以下欄位-可隨意增減 --%>
			<ul>
			<li>
			 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/group_member/group_member.do" name="form1">
			 <b><font color=blue>萬用複合查詢:</font></b> <br>
			 <b>輸入開團編號:</b>
        	 <input type="text" name="group_no" value="G00001"><br>
        	 <b>輸入開團者會員編號:</b>
       		 <input type="text" name="member_no" value="M000002"><br>
       		 <b>輸入商品編號:</b>
       		 <input type="text" name="goods_no" value="P000002"><br>
       		 <b>輸入團購結束時間:</b>
       		 <input type="text" name="group_close_date" value=""><br>
       		 <b>輸入團購狀態:</b>
       		 <input type="text" name="group_status" value=""><br>
       		 <b>輸入面交地點:</b>
       		 <input type="text" name="group_address" value=""><br>
       		 <b>輸入面交時間:</b>
       		 <input type="text" name="group_time" value=""><br>
       		 <b>選擇開團名稱:</b>
       		<select size="1" name="group_name" >
         	<option value="">
         	<c:forEach var="group_openVO" items="${group_openSvc.all}" > 
          	<option value="${group_openVO.group_no}">${group_openVO.group_name}
         	</c:forEach>   
       		</select><br> 
       		<%--  b>選擇商品名稱:</b>
       		<select size="1" name="deptno" >
         	<option value="">
         	<c:forEach var="deptVO" items="${deptSvc.all}" > 
          	<option value="${deptVO.deptno}">${deptVO.dname}
         	</c:forEach>   
       		</select><br> --%>
       		<input type="submit" value="送出">
       		<input type="hidden" name="action" value="listgroup_open_ByCompositeQuery">
			</FORM>
			</li>	
			</ul>
			<h4>萬用複合查詢 根據跟團人資訊</h4>
			
			
			
			
			
			
			
<h3>開團管理</h3>

<ul>
  <li><a href='addgroup_open.jsp'>Add</a> a new Group.</li>
</ul>



</body>
</html>