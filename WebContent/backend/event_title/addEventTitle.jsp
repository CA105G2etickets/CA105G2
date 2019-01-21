<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page import="com.event_title.model.*"%>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>新增活動主題</title>
    <!-- Basic -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <!-- datetimepicker -->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/vendor/datetimepicker/jquery.datetimepicker.css" />
    
    <style>
        .evetit_poster_area img {
	        width: 100%;
	    }
    	body{
			font-family:微軟正黑體!important;
		}
    </style>
    <!-- Basic -->
    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>



		<jsp:include page="/backend/navbar_back-end.jsp" flush="true" />



		<div class="container">
	        <ol class="breadcrumb">
	            <li>
	                <a href="<%= request.getContextPath()%>/backend/index.jsp">首頁</a>
	            </li>
	            <li>
	                <a href="<%= request.getContextPath()%>/backend/event_title/listAllEventTitleRelatives.jsp">活動管理</a>
	            </li>
	            <li class="active">新增活動主題</li>
	        </ol>
	    </div>	



		<div class="container">
			<span class="text-danger">${eventTitleErrorMsgs.Exception}</span>
		</div>

    	<div class="container" style="margin-bottom:30px;">    	
	        <form method="post" enctype="multipart/form-data" action="<%=request.getContextPath()%>/event_title/EventTitleServlet.do">        
	           
	            <div class="form-group">
	                <label for="evetit_name">活動主題名稱</label>
	                <span class="text-danger">${eventTitleErrorMsgs.evetit_name}</span>
	                <input type="text" name="evetit_name" id="evetit_name" class="form-control" value="${param.evetit_name}">	                
	            </div>
	            
	            <jsp:useBean id="ticketRefundPolicyService" scope="page" class="com.ticket_refund_policy.model.TicketRefundPolicyService" />            
	            <div class="form-group">
	                <label>退款政策</label>
	                <select class="form-control" name="ticrefpolicy_no" id="ticrefpolicy_no">
	                    <c:forEach var="ticketRefundPolicyVO" items="${ticketRefundPolicyService.all}">
	                        <option value="${ticketRefundPolicyVO.ticRefPolicy_no}" ${(ticketRefundPolicyVO.ticRefPolicy_no == param.ticrefpolicy_no) ? 'selected' : '' }>
	                            ${ticketRefundPolicyVO.ticRefPolicy_name} : ${ticketRefundPolicyVO.ticRefPolicy_content}
	                        </option>
	                    </c:forEach>
	                </select>
	            </div>
	                        
	            <div class="row">
	                <div class="col-xs-12 col-sm-6">
	                    <div class="form-group">
	                        <label for="evetit_startdate">開始日期</label>
	                        <span class="text-danger">${eventTitleErrorMsgs.evetit_startdate}</span>
	                        <input type="text" id="evetit_startdate" name="evetit_startdate" class="form-control" value="${param.evetit_startdate}">	                        
	                    </div>
	                </div>
	                <div class="col-xs-12 col-sm-6">
	                    <div class="form-group">
	                        <label for="evetit_enddate">結束日期</label>
	                        <span class="text-danger">${eventTitleErrorMsgs.evetit_enddate}</span>
	                        <span class="text-danger">${eventTitleErrorMsgs.evetit_enddate_BiggerThanToday}</span>
	                        <span class="text-danger">${eventTitleErrorMsgs.evetit_enddate_BiggerThanEvetitStartdate}</span>
	                        <input type="text" id="evetit_enddate" name="evetit_enddate" class="form-control" value="${param.evetit_enddate}"> 
	                	                    	
	                    </div>
	                </div>
	            </div>           
	            
	            <div class="row">
	                <div class="col-xs-12 col-sm-6">
	                     <div class="form-group">
	                         <label for="launchdate">上架日期</label>
	                         <span class="text-danger">${eventTitleErrorMsgs.launchdate}</span>
	                         <input type="text" id="launchdate" name="launchdate" class="form-control" value="${param.launchdate}">
	                     </div>
	                </div>
	                <div class="col-xs-12 col-sm-6">
	                     <div class="form-group">
	                         <label for="offdate">下架日期</label>
	                         <span class="text-danger">${eventTitleErrorMsgs.offdate}</span>
	                         <span class="text-danger">${eventTitleErrorMsgs.offdate_BiggerThanToday}</span>
	                         <span class="text-danger">${eventTitleErrorMsgs.offdate_BiggerThanLaunchdate}</span>
	                         <input type="text" id="offdate" name="offdate" class="form-control" value="${param.offdate}">
	                     </div>
	                </div>
	            </div>
	            
	            <div class="row">           
	                <div class="col-xs-12 col-sm-3">
	                    <jsp:useBean id="eventClassificationService" scope="page" class="com.event_classification.model.EventClassificationService" />              
	                    <div class="form-group">
	                        <label>分類</label>
	                        <select class="form-control" name="eveclass_no" id="eveclass_no">
	                            <c:forEach var="eventClassificationVO" items="${eventClassificationService.all}">
	                                <option value="${eventClassificationVO.eveclass_no}" ${(eventClassificationVO.eveclass_no == param.eveclass_no) ? 'selected' : '' }>
	                                    ${eventClassificationVO.eveclass_name}
	                                </option>
	                            </c:forEach>
	                        </select>
	                    </div>
	                </div>           
	                <div class="col-xs-12 col-sm-3">
	                     <div class="form-group">
	                         <label>推銷熱度</label>                         
	                         <select class="form-control" name="promotionranking" id="promotionranking">
	                         	<option value="1" ${(param.promotionranking == "1") ? 'selected' : '' }>1</option>
	                         	<option value="2" ${(param.promotionranking == "2") ? 'selected' : '' }>2</option>
	                         	<option value="3" ${(param.promotionranking == "3") ? 'selected' : '' }>3</option>
	                         	<option value="4" ${(param.promotionranking == "4") ? 'selected' : '' }>4</option>
	                         	<option value="5" ${(param.promotionranking == "5") ? 'selected' : '' }>5</option>
	                         </select>                      
	                     </div>
	                </div>             
	                <div class="col-xs-12 col-sm-3">
	                    <div class="form-group">
	                         <label>狀態</label>	                                                 
	                         <select class="form-control" name="evetit_status" readonly>
	                         	<option value="temporary">暫存</option>
	                         </select>                					
	                    </div>
	                </div>
	                <div class="col-xs-12 col-sm-3">
	                	<div class="form-group">
	                         <label>場次數量</label>                         
	                         <input type="text" name="evetit_sessions" id="evetit_sessions" class="form-control" value="0" readonly>                      
	                     </div>
	                </div>
	            </div>
	            
				<div class="form-group evetit_poster_area">
					<label for="evetit_poster">主海報</label>
					<span class="text-danger">${eventTitleErrorMsgs.evetit_poster}</span>
					<input type="file" id="evetit_poster" name="evetit_poster" class="form-control" accept="image/*">
					<input type="hidden" id="evetit_poster_status" name="evetit_poster_status" value="${(evetit_poster_status == 'alreadyUpload') ? 'alreadyUpload' : 'noUpload'}">
					<img src="${evetit_poster_path}" id="evetit_poster_preview">
				</div>
	            
	            <div class="tabbable">
	                <!-- 標籤面板：標籤區 -->
	                <ul class="nav nav-tabs">
	                    <li class="active"><a href="#infoTab" data-toggle="tab">活動介紹</a></li>
	                    <li><a href="#noticeTab" data-toggle="tab">注意事項</a></li>
	                    <li><a href="#eticpurchaserulesTab" data-toggle="tab">購票提醒</a></li>
	                    <li><a href="#eticrulesTab" data-toggle="tab">用票提醒</a></li>
	                    <li><a href="#refundrulesTab" data-toggle="tab">退票說明</a></li>
	                </ul>
	                <!-- 標籤面板：內容區 -->
	                <div class="tab-content">
	                    <div class="tab-pane active" id="infoTab">
		                	<textarea name="info" id="infoEditor">
		                		${param.info}
		               		</textarea>
	                    </div>
	                    <div class="tab-pane" id="noticeTab">
	                    	<textarea name="notices" id="noticesEditor">
	                    		${param.notices}
	                 		</textarea>
	                    </div>
	                    <div class="tab-pane" id="eticpurchaserulesTab">
	                        <textarea name="eticpurchaserules" id="eticpurchaserulesEditor">
	                        	${param.eticpurchaserules}
	                        </textarea>
	                    </div>
	                    <div class="tab-pane" id="eticrulesTab">
	                        <textarea name="eticrules" id="eticrulesEditor">
	                        	${param.eticrules}
	                        </textarea>
	                    </div>
	                    <div class="tab-pane" id="refundrulesTab">
	                    	<textarea name="refundrules" id="refundrulesEditor">
	                            ${param.refundrules}
	                    	</textarea>
	                    </div>
	                </div>
	            </div>
				<span class="form-group">
					<button type="submit" class="btn btn-success" name="action" value="insertEventTitle" style="margin-top:15px;">新增</button>
					<a class="btn btn-info" href="<%=request.getContextPath()%>/backend/event_title/listAllEventTitleRelatives.jsp" style="margin-top:15px;">回活動總覽</a>
					<button type="button" class="btn btn-default" id="magicButton" style="margin-top:15px;">Magic!</button>
				</span>			
			</form>
        </div>




    <!-- ckEditor JS -->
    <script src="<%=request.getContextPath()%>/vendor/ckeditor_easyImage_final/ckeditor.js"></script>
    <script src="<%=request.getContextPath()%>/vendor/ckeditor_easyImage_final/adapters/jquery.js"></script>
    <!-- datetimepicker -->
    <script src="<%=request.getContextPath()%>/vendor/datetimepicker/jquery.js"></script>
    <script src="<%=request.getContextPath()%>/vendor/datetimepicker/jquery.datetimepicker.full.js"></script>
    <!-- JavaScript in File -->
    <script src="<%=request.getContextPath()%>/backend/event_title/js/eventTitleCKEditor.js"></script>
    <script src="<%=request.getContextPath()%>/backend/event_title/js/addEventTitle.js"></script>
    <!-- JavaScript in HTML -->
    <script type="text/javascript">
    $(function() {
        initInfoEditor();
        initNoticesEditor();
        initEticpurchaserulesEditor();
        initEticrulesEditor();
        initRefundrulesEditor();

        $("#evetit_poster").change(function() {
            imagesPreview(this);
            $("#evetit_poster_status").attr("value", "yesUpload");
        });
        
        $(".text-danger").each(function(){
        	var errorMsg = $(this).text();
        	if( errorMsg.trim() != "" ){
        		$(this).prepend("<i class='glyphicon glyphicon-triangle-left'></i>");
        	}        	
        });
               
    	localStorage.removeItem("DataTables_eventTitleListTable");  
    	
    	$("#magicButton").click(function(){
    		console.log("123");
    		$("#evetit_name").val("BLACKPINK 2019 WORLD TOUR TAIPEI with KIA");
    		$('#ticrefpolicy_no').val("TRP2").change();
    		$("#evetit_startdate").val("2019-03-03");
    		$("#evetit_enddate").val("2019-03-03");
    		$("#launchdate").val("2019-01-20");
    		$("#offdate").val("2019-03-04");
    		$("#eveclass_no").val("A").change();
    		$("#promotionranking").val("5").change();

			var infoEditorData = '<!-- start: 節目介紹 --><div class="tab-pane" id="intro"><!-- start: 影片 --><div style="text-align:center"><span style="color:#c0392b"><span style="font-size:18px"><strong>引領世界潮流、歐美歌手相繼邀約合作的韓國女團-BLACKPINK</strong></span></span></div><div style="text-align:center"><span style="color:#c0392b"><span style="font-size:18px"><strong>壓倒性的舞台魅力與音樂成績蔚為話題 三月首度登台開唱</strong></span></span><br />&nbsp;</div><div style="text-align:left">韓國三大經紀公司YG娛樂相隔七年推出的女子團體 - BLACKPINK，出道即以亮麗的外表和不可忽視的實力迅速從新生代韓國女團中脫穎而出，引起全球矚目，由JISOO、JENNIE，ROS&Eacute;和LISA所組成，四人四色，成為次世代時尚指標。以單曲和出道，出道14天即贏得首座音樂節目一位獎盃，大獲成功。後續相繼發行、、等歌曲，在國內外都獲得廣大迴響，更被富士比雜誌讚賞為「全美最具影響力的K-POP女子團體」。<br /><br />【BLACKPINK 2019 WORLD TOUR [IN YOUR AREA] TAIPEI with KIA】<br />🖤 演出日期：2019/3/3 (日)<br />🖤 演出時間：6PM (實際演出時間以現場公告為準)&nbsp;<br />🖤 演出地點：國立體育大學綜合體育館 (林口體育館)<br />🖤 票價：NT$5800 (搖滾站區) / NT$4800/ NT$3800/ NT$3300/ NT$2800/ NT$2300<br />🎫 售票時間：2019/1/20 (日) 2PM 拓元售票系統開賣 (*購票時不會有相關問題驗證)<br />🎁 凡購買NT$5800票價並於2019/2/19前(含)完成訂單者，就有機會抽中Sound Check Party參加資格（每場1000位幸運兒）</div><p>📍 搖滾站區請依序號排隊入場，若有消費者未結帳或退票，空出之序號有可能會被比較晚買的消費者購買，請同筆訂單可能會有序號不連號情形發生，敬請了解。<br />📍 人身安全起見，七歲以下與身高未滿110公分及孕婦請勿購買搖滾站區。<br />📍 入場須配合嚴格安檢且現場無置物櫃，詳細辦法請關注LIVE NATION TAIWAN官網與LIVE NATION TAIWAN臉書。<br />📍 以上活動內容，主辦單位保留異動之權力。<br /><br />※座位排號說明：</p><ul style="list-style-type:disc"><li>座位號：各區域的同一排中，座位號為【連續單號】或【連續雙號】即為相臨之座位，A層的各區域中間為單雙號交界處，電腦配位時若有單雙混號，其必為相臨座位。</li><li>排號：<ol style="list-style-type:lower-alpha"><li>A層&mdash;從第0排開始往上遞增，即最靠近舞台排數(最前排)為第0排，之後依序為第1排、第2排，依此類推至該區最後一排。</li><li>B層&mdash;從第15排開始往上遞減，即最靠近舞台排數(最前排)為第15排，之後依序為第14排、第13排，依此類推至該區平台處為第1排。</li></ol></li><li>A層、B層座位區每區最前方皆有欄杆(場館固定之安全設施)，將會有部分視線受阻情況，若在意欄杆影響視線，請三思確認後再購票，謝謝。</li><li>黃2B單區15排21,23號、黃2B雙區15排2,4號、黃1B雙區15排24,26號 前方牆邊低處有場館設施但不影響視線。</li></ul></div><!-- end: 節目介紹 -->';
			CKEDITOR.instances.infoEditor.setData(infoEditorData);
			
			var noticesEditorData = '<!-- start: 注意事項 --><div class="tab-pane" id="note"><p>購票前請詳閱注意事項：</p><ol><li><strong><span style="color:#ff0000">購票後(不含購票當日)三日內得退票，第四日起即不接受退票申請</span></strong>，退票需酌收票面價5%手續費，換票等同於退票。購買各種優惠票者，需於購票與使用時依規定出示相關證明文件。每人每場退票總張數，不得超出該次演唱會公告之限購張數，分次寄送者亦同。</li><li>演唱會現場嚴禁攝錄影，不得攜帶相攝影機、Gopro與平板電腦等器材入場，<span style="color:#ff0000"><strong>現場需經安檢程序方能入場，且現場無置物櫃</strong></span>，開演前請關注主辦單位瞭解入場辦法與安檢規定，並請提早到場進行安檢以免耽誤觀賞演出的時間，主辦單位保留修改或終止本活動之權力。</li><li>一人一票入場，孩童也需購票。 搖滾站席為人身安全起見，孕婦及身高未滿110公分或未滿七歲者孩童請勿購票入場。</li><li>請確實核對訂購內容，本票券一經售出，表示台端同意支付本次交易的內容與價格，台端不得以任何理由拒付本次交易費用。</li><li>票券屬無記名的有價證券，如發生遺失、破損、燒毀或無法辨識等狀況，恕不補發或退換。任意塗改、影印或套印、掃描複製票劵，均屬無效票。</li><li>請支持杜絕黃牛票以及二手票券買賣，台灣演出請上官方售票網站購票，請千萬不要購買非官方管道售出的票券。若因購買非官方售票網站售出的票券引起相關權益受損致無法入場，將無法保障自身權益，本售票系統恕不負責，主辦單位保留認定票券合法性之權力。</li><li>若有任何形式非供自用而加價轉售之票券，無論加價名目為何。經查屬實者，將取消入場權利並得不予退票，且得依社會秩序維護法第64條第2款逕向警方檢舉並提出告訴。</li><li>各表演場館各有其入場規定，請持票觀眾務必遵守主辦單位及場館各項規定，如有干擾演出秩序之行為，主辦單位有權請出場外並且不予退還票款。活動若因故延期或取消，主辦單位不負交通及住宿費之補償。</li><li>活動現場嚴禁以下行為：<ul style="list-style-type:disc"><li>使用相機、攝影機或任何器材攝錄影、錄音等與直播行為。</li><li>如經查獲，將由工作人員請出場外，情節嚴重者不予再次進場，主辦單位有權強制曝光底片或刪除記憶卡中所拍攝/錄製之內容以維護藝人肖像權與智慧財產權，若有損壞與遺失概不負責，並保留法律追訴權。</li></ul></li><li>演出期間：<ul style="list-style-type:disc"><li>全場禁止站立於椅子上觀賞。</li><li>請勿攜帶並高舉看板或手機以免阻擾後方觀眾視線。</li><li>請勿丟擲螢光棒、寶特瓶、鐵鋁罐等物品。</li><li>經勸告未果，工作人員有權請違規者離場。</li></ul></li><li>其他購票相關問題請電洽客服或EMAIL，由客服人員為您服務。</li></ol></div><!-- end: 注意事項 -->';
			CKEDITOR.instances.noticesEditor.setData(noticesEditorData);
			
			var eticpurchaserulesEditorData = '<!-- start: 購票提醒 --><div class="tab-pane" id="buy-note"><ol><li>欲購票者，請參考【會員加入辦法】，需進行手機驗證，驗證通過24小時後，才可開始購票，未完成驗證者，恕無法購票。</li><li>單筆訂單限購<strong>4</strong>張，每人限購<strong>4</strong>張，可支援行動裝置購票。</li><li>付款方式可選擇【電子錢包】或【信用卡付款】，虛擬帳號需在一小時內結帳完畢。</li><li>訂購者帳號中若有未完成付款或刷卡失敗的訂單，該帳號之購票額度需等待系統自動清除訂單後才會釋出，並請重新訂購。故售票日之後，如釋出較為前面座位之票券屬正常情形。</li><li>付款完成之訂單代表已售出，表示台端同意支付本次交易的內容與價格，請至【訂單查詢】核對訂購內容，一旦訂單成立，台端不得以任何理由拒付本次交易費用。(如需退票，請另依退票規則辦理。)</li><li>不論付款成功與否，請務必登入並到【訂單查詢】頁面，確認其【訂單狀態】，依訂單查詢顯示為主。</li><li>如果有退票票券，本系統將會自動釋出於系統上販售。</li><li>有鑒於網路拍賣風氣盛行，有許多來路不明的票券在拍賣網站出售競標，但這些票券來源不明、真偽難辨，票務糾紛層出不窮，為確保您的權益，懇請勿於拍賣網站或是其他非正式授權售票之管道購票，如發生無法進場或其他損害個人權益之事宜，本售票系統恕不負責。</li></ol></div><!-- end: ibon機台購票提醒 -->';
			CKEDITOR.instances.eticpurchaserulesEditor.setData(eticpurchaserulesEditorData);
			
			var eticrulesEditorData = '<!-- start: 用票提醒 --><div class="tab-pane" id="eticrulesTab">﻿<p><span style="color:#0000ff"><strong>用票方式僅限【電子票券</strong><strong>】</strong></span></p><ol><li>請下載我們的APP，以其出示電子票券，工作人員將掃描該電子票券。</li></ol><p>&nbsp;</p></div><!-- end: 用票提醒 -->';
			CKEDITOR.instances.eticrulesEditor.setData(eticrulesEditorData);
			
			var refundrulesEditorData = '<!-- start: 退票說明 --><div class="tab-pane" id="refundrulesTab">﻿<ol><li><span style="color:#000000">依文化部於中華民國107年5月16日文藝字第10710128232號公告修定之『藝文表演票券定型化契約應記載及不得記載事項』第六條退換票機制如<a href="https://tixcraft.com/faq/faq/26" style="color: #000000;">常見問題</a>說明所示。本節目退票方案訂定如下說明。</span></li><li><span style="color:#000000">每人每場退票總張數，不得超出該次演唱會公告之限購張數。</span></li><li><span style="color:#000000">個人因素退票者，每張票券須酌收票面金額5%手續費。相關服務費用與寄回郵資非屬票價部分不在退費範圍之內。</span></li><li><span style="color:#000000">購票後(不含購票當日)三日內得退票，第四日起即不接受退票申請，演出前二日內恕不接受退票。購票後(不含購票當日)之第三日若遇假日，則順延至下一個工作日截止收件，換票視同退票處理。</span></li><li>在有退票釋出座位之前提下，本公司得於完成退票作業後，陸續將該座位釋出並重新上架販售，於退票截止日後亦有可能釋出座位。&nbsp; &nbsp;</li></ol><p>&nbsp;</p></div><!-- end: 退票說明 -->';
			CKEDITOR.instances.refundrulesEditor.setData(refundrulesEditorData);

    	});
    });
    
    
    </script>
</body>

</html>