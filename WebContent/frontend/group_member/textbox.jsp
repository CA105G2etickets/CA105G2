<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>留言板</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<style type="text/css">
			#messageform{
				background-color:#F5F5F5;
				width: 400px;
				height: 100px;
				border-radius: 10px;
			}
			.img-circle{
				width:60px;
				height:60px;
			}
			.btn{
				float: right;

			}
			.message{

			}
			#responsearea{
				background-color:#F5F5F5;
				border-radius: 10px;
				width:auto;
				height:auto;
			
			}
			.res{
				width:50px;
				height:50px;
			}
			.forum{
				height:300px;
				overflow:scroll;
			}

	

		</style>
<script>
			
		$(function(){
			$('#messagebtn').on('click', function(){
				$.ajax({
					url: '<%=request.getContextPath()%>/frontend/forum/forum.do',
					type: "get",
					data: { 
						action: 'ask',
						question: $('#msg').val() 
						  },
					dataType: 'json',
					success: function(res){
						console.log(res);
						$('.messagebox').append("<div>"+res.answer+"</div>");
						
					},
					error: function(res){
						$('#showmessage').text('Error! Could not reach the API. ');
					}
				});
			
			});
		});
	/* 	============================================================   */
		$(function(){
			$('#btn').on('click', function(){
				
				$.ajax({
					url: '<%=request.getContextPath()%>/frontend/forum/forum.do',
					type: "get",
					data: { 
						action: 'ask',
						question: $('#message').val() 
						  },
					dataType: 'json',
					success: function(res){
						console.log(res);
						$('.forum').append("<div class='panel-body'>"+
					     "<div id='responsearea'>"+
							"<img src='<%=request.getContextPath()%>/images/peoplephoto.jpg' class='img-circle res'>"+
								"<span>人物名稱</span>"+
								"<span>"+res.answer+"</span>"+
						 "</div>"+"</div>");	
					},
					error: function(res){
						$('#showmessage').text('Error! Could not reach the API. ');
					}
				});
			
			});
		});
	/* 	============================================================   */
	
		
	</script>
</head>
<body>
	<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-3">
					



				</div>
				<div class="col-xs-12 col-sm-6">
				<div class="panel panel-success forum">
					  <div class="panel-heading">
					    <h3 class="panel-title">討論區</h3>
					  </div><!-- <div class="panel-heading"> -->
					<div class="panel-body"><!-- foreach開始 -->
					     <div id="responsearea">
							<img src="<%=request.getContextPath()%>/images/peoplephoto.jpg" class="img-circle res">
								<span>人物名稱</span>
								<span>留言內容</span>
						 </div><!-- <div id="responsearea"> -->
					  </div><!-- <div class="panel-body"> --><!-- foreach結束 -->
					  <div class="panel-body"><!-- foreach開始 -->
					     <div id="responsearea">
							<img src="<%=request.getContextPath()%>/images/peoplephoto.jpg" class="img-circle res">
								<span>人物名稱</span>
								<span>留言內容</span>
						 </div><!-- <div id="responsearea"> -->
					  </div><!-- <div class="panel-body"> --><!-- foreach結束 -->
					  <div class="panel-body"><!-- foreach開始 -->
					     <div id="responsearea">
							<img src="<%=request.getContextPath()%>/images/peoplephoto.jpg" class="img-circle res">
								<span>人物名稱</span>
								<span>留言內容</span>
						 </div><!-- <div id="responsearea"> -->
					  </div><!-- <div class="panel-body"> --><!-- foreach結束 -->
				</div><!-- <div class="panel panel-success"> -->
					<div id="messageform">
							<img src="<%=request.getContextPath()%>/images/peoplephoto.jpg" class="img-circle"> wilson
							<br>
						<input type="text" class="form-control" placeholder="回覆" id="message">
						<button type="button" class="btn btn-info" id="btn">留言</button>
					</div><!-- <div id="messageform"> -->
				</div>
				<div class="col-xs-12 col-sm-3">
				<h3>留言訊息單</h3>
		
		 <input type="text" id="msg">
		 <input type="button" id="messagebtn" value="送出">
		
		
		 <div class="messagebox">
		 <div id = "showmessage"></div>
		
		</div>



				</div>
			</div>
		</div>
		
		
		
		



</body>
</html>