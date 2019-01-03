<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page import="java.util.*"%>
<%@ page import="com.advertisement.model.*"%>
<%@ page import="com.event_title.model.*"%>

<% 
	AdvertisementService advertisementService = new AdvertisementService();
	List<AdvertisementVO> list = advertisementService.getAll();
	pageContext.setAttribute("advertisementList", list); 
%>
<% 
	EventTitleService eventTitleService = new EventTitleService();
	List<EventTitleVO> getNotInTheAdvertisementList = eventTitleService.getNotInTheAdvertisement();
	pageContext.setAttribute("getNotInTheAdvertisementList", getNotInTheAdvertisementList); 
%>

<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>廣告管理</title>
<!-- Basic -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<!-- datetimepicker -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/vendor/datetimepicker/jquery.datetimepicker.css" />
<!-- dataTables -->
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
</head>

<style>
.actionForm{
	display: inline;
}
</style>

<body>
	
	<div class="container">
		<input type="hidden" id="projectName" name="projectName" value="<%=request.getContextPath() %>">
		<input type="hidden" id="return_ad_no" name="return_ad_no" value="${param.ad_no}">
		<input type="hidden" id="adWhichPage" name="adWhichPage" value="${param.adWhichPage}">
	</div>
	
	<div class="container">
		<button class="btn btn-primary" id="wantToAddAdvertisement" data-toggle="modal" data-target="#addAdvertisementModal" style="margin-bottom:5px;">新增廣告</button>
	</div>

	<div class="container">		
		<table id="advertisementListTable" class="display" style="width:100%;">
			<thead>
				<tr>
					<th>編號</th>
					<th>廣告主題名稱</th>
					<th>開始日期</th>
					<th>結束日期</th>
					<th>動作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="advertisementVO" items="${advertisementList}">
					<tr class="${(advertisementVO.ad_no==param.ad_no) ? 'selected':''}">	
						<td>
							${advertisementVO.ad_no}
						</td>
						<td>
							<jsp:useBean id="eventTitleService2" scope="page" class="com.event_title.model.EventTitleService" />	
							${eventTitleService2.getOneEventTitle(advertisementVO.evetit_no).evetit_name}
						</td>
						<td>${advertisementVO.ad_startdate}</td>
						<td>${advertisementVO.ad_enddate}</td>
						<td>
							<button class="btn btn-warning btn-sm getOneAdvertisement_For_Update" data-toggle="modal" data-target="#updateAdvertisementModal" value="${advertisementVO.ad_no}">修改</button>
							<button class="btn btn-danger btn-sm deleteAdvertisement" value="${advertisementVO.ad_no}">刪除</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>		
	</div>
	
	
	
	
	
<!-- -------------------------------------------------- wantToAddAdvertisement -------------------------------------------------- -->
<div class="container">
	<div class="modal fade" id="addAdvertisementModal" role="dialog" data-backdrop="false" >
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">
						新增廣告
					</h4>
				</div>
				<div class="modal-body">
		            <div class="form-group">
		                <label>廣告主題名稱</label>
		                <select class="form-control evetit_no" name="evetit_no">
		                    <c:forEach var="eventTitleVO" items="${getNotInTheAdvertisementList}">
		                        <option value="${eventTitleVO.evetit_no}">
		                            ${eventTitleVO.evetit_name}
		                        </option>
		                    </c:forEach>
		                </select>
		            </div>
		            <div class="row">
	                	<div class="col-xs-12 col-sm-6">
				            <div class="form-group">
		                        <label for="ad_startdate">開始日期</label>
		                        <input type="text" name="ad_startdate" class="form-control ad_startdate" value="">
			                </div>
						</div>
						<div class="col-xs-12 col-sm-6">
			                <div class="form-group">
		                        <label for="ad_enddate">結束日期</label>
		                        <input type="text" name="ad_enddate" class="form-control ad_enddate" value="">
			                </div>
			            </div>
					</div>
				</div>
				<div class="modal-footer">
        			<button type="button" class="btn btn-primary" id="addAdvertisement">儲存新增</button>
        			<button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>	
</div>





<!-- -------------------------------------------------- getOneAdvertisement_For_Update -------------------------------------------------- -->
<div class="container">
	<div class="modal fade" id="updateAdvertisementModal" role="dialog" data-backdrop="false" >
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">
						修改廣告
					</h4>
				</div>
				<div class="modal-body">
		            <div class="form-group">
		                <label>廣告主題名稱</label>
		                <select class="form-control evetit_no" name="evetit_no">
		                    <c:forEach var="eventTitleVO" items="${getNotInTheAdvertisementList}">
		                        <option value="${eventTitleVO.evetit_no}">
		                            ${eventTitleVO.evetit_name}
		                        </option>
		                    </c:forEach>
		                </select>
		            </div>
		            <div class="row">
	                	<div class="col-xs-12 col-sm-6">
				            <div class="form-group">
		                        <label for="ad_startdate">開始日期</label>
		                        <input type="text" name="ad_startdate" class="form-control ad_startdate" value="">
			                </div>
						</div>
						<div class="col-xs-12 col-sm-6">
			                <div class="form-group">
		                        <label for="ad_enddate">結束日期</label>
		                        <input type="text" name="ad_enddate" class="form-control ad_enddate" value="">
			                </div>
			            </div>
					</div>
				</div>
				<div class="modal-footer">
        			<button type="button" class="btn btn-primary" id="updateAdvertisement">儲存新增</button>
        			<button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>	
</div>
	
	
	
	
	
	
	
	
	
	
	
	
	<!-- Basic -->
	<script src="https://code.jquery.com/jquery.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<!-- datetimepicker -->
    <script src="<%=request.getContextPath()%>/vendor/datetimepicker/jquery.js"></script>
    <script src="<%=request.getContextPath()%>/vendor/datetimepicker/jquery.datetimepicker.full.js"></script>
	<!-- dataTables -->
	<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
	<script src="https://cdn.datatables.net/plug-ins/1.10.19/api/page.jumpToData().js"></script>

	<script>
		var advertisementListTable;
		
		$(document).ready(function() {
			
			// -----datatable---------------------------------------------
			advertisementListTable = $("#advertisementListTable").DataTable({
				stateSave: true,
				stateSaveCallback: function(settings,data) {
					localStorage.setItem( 'DataTables_' + settings.sInstance, JSON.stringify(data) )
				},
				stateLoadCallback: function(settings) {
					return JSON.parse( localStorage.getItem( 'DataTables_' + settings.sInstance ) )					
				}
			});	
			var adWhichPageGet = parseInt($("input[name=adWhichPage]").val());
			if(advertisementListTable.page.info() != null){
				var pages = advertisementListTable.page.info().pages;
			}
			if(adWhichPageGet === pages){
				advertisementListTable.page( adWhichPageGet - 1 ).draw( 'page' );
			} else {
				if($("#return_ad_no").val() != null){
					advertisementListTable.page.jumpToData( $("#return_ad_no").val(), 0 );					
				}		
			}	
			
			
			
			// -----datetimepicker---------------------------------------------
			var endDateInit = new Date();
			endDateInit.setMonth(endDateInit.getMonth() + 3);
			$.datetimepicker.setLocale('zh');
	        $(".ad_startdate").datetimepicker({
	        	format: 'Y-m-d',
	        	timepicker: false,
	        	minDate: 0,
	        	value: new Date(),
	            changeMonth: true,
	            changeYear: true,	
	        	onShow: function(){
	        		this.setOptions({maxDate: $("#ad_enddate").val() ? $("#ad_enddate").val() : false})}
	        });
	        $(".ad_enddate").datetimepicker({
	        	format: 'Y-m-d',
	        	timepicker: false,
	        	value: endDateInit,
	            changeMonth: true,
	            changeYear: true,	
	        	onShow: function(){
	        		this.setOptions({minDate: $("#ad_startdate").val() ? $("#ad_startdate").val() : false})}
	        });
			
	        
			
			// -----advertisement---------------------------------------------
			$(".getOneAdvertisement_For_Update").click(function (e){
				console.log(e.target);
				console.log($(e.target).val());
				//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
								//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
												//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
																//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
																				//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
																								//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
																												//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
																																//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
																																				//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
																																								//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			});
			
			$(".deleteAdvertisement").click(function (e){
				var adWhichPage = advertisementListTable.page.info().page;
				var url = $("#projectName").val();
	            url += '/advertisement/AdvertisementServlet.do';
	            var data = '';
	           	data += 'ad_no=';
	           	data += $(e.target).val();       	
	            data += '&action=deleteAdvertisement';
	            $.ajax({
	                type: 'post',
	                url: url,
	                data: data,
	                success: function(data) {
	                	if(data.indexOf("失") != -1){
	                		window.alert(data);
	                	} else {
	                		window.location = $("#projectName").val() + '/backend/advertisement/listAllAdvertisement.jsp?adWhichPage=' + adWhichPage;
	                	}
	                }
	            });
			});
			
			$("#addAdvertisement").click(function(){
				var CurrentDate = new Date();
				CurrentDate.setDate(CurrentDate.getDate() - 1);
				var evetit_no = $("#addAdvertisementModal").children().find(".evetit_no").val();
				var ad_startdate = $("#addAdvertisementModal").children().find("input[name=ad_startdate]").val();
				var ad_enddate = $("#addAdvertisementModal").children().find("input[name=ad_enddate]").val();
				if(Date.parse(ad_startdate) < CurrentDate) {
					window.alert("開始日期不得早於今天");
					return;					
				}
				if(Date.parse(ad_enddate) < CurrentDate) {
					window.alert("結束日期不得早於今天");
					return;
				}
				if(Date.parse(ad_enddate) < Date.parse(ad_startdate)) {
					window.alert("結束日期不得早於開始日期");
					return;
				}
				var url = $("#projectName").val();
	            url += '/advertisement/AdvertisementServlet.do';
	            var data = '';
	           	data += 'evetit_no=';
	           	data += evetit_no;
	           	data += '&ad_startdate=';
	           	data += ad_startdate;
	           	data += '&ad_enddate=';
	           	data += ad_enddate;           	
	            data += '&action=addAdvertisement';
	            $.ajax({
	                type: 'post',
	                url: url,
	                data: data,
	                success: function(data) {
	                	if(data.indexOf("失") != -1){
	                		window.alert(data);
	                	} else {
	                		window.location = $("#projectName").val() + '/backend/advertisement/listAllAdvertisement.jsp?ad_no=' + data;
	                	}
	                }
	            });
			});
			
		});
	</script>
</body>

</html>