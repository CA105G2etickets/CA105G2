package com.event.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.event.model.EventService;
import com.event.model.EventVO;
import com.event_title.model.EventTitleService;
import com.member.model.MemberService;
import com.ticket.model.TicketVO;
import com.ticket.model.TicketVO2;
import com.ticket_type.model.TicketTypeVO;
import com.ticketorder.model.TicketOrderVO;

@WebServlet("/event/EventServlet.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class EventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		// 基本款
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();

		String action = request.getParameter("action");


			
		


		
		
		
		
		// 請求來源 : backend -> listAllEventTitleRelatives.jsp
		if ("getOneEvent_For_Display".equals(action)) {
			
			String requestURL = request.getParameter("requestURL");

			Map<String, String> eventErrorMsgs = new LinkedHashMap<String, String>();
			request.setAttribute("eventErrorMsgs", eventErrorMsgs);

			try {
				/****************************** 1.接收請求參數 **************************************************/
				String eve_no = request.getParameter("eve_no");

				/****************************** 2.開始查詢資料 **************************************************/
				EventService eventService = new EventService();
				EventVO eventVO = eventService.getOneEvent(eve_no);
				Set<TicketTypeVO> listTicketTypes_ByEvent = eventService.getTicketTypesByEvent(eve_no);
				
				/****************************** 3.查詢完成,準備轉交 **************************************************/
				request.setAttribute("eventVO", eventVO);
				request.setAttribute("listTicketTypes_ByEvent", listTicketTypes_ByEvent);				
				RequestDispatcher successView = request.getRequestDispatcher("/backend/event/listOneEvent.jsp");
				successView.forward(request, response);

				/****************************** 其他可能的錯誤處理 ******************************/
			} catch (Exception e) {
				eventErrorMsgs.put("Exception", "無法取得資料 : " + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher(requestURL);
				failureView.forward(request, response);
			}
			return;
		}

		
		
		
		
		
		
		

		
		// 請求來源 : backend -> listAllEventTitleRelatives.jsp / listOneEvent.jsp
		else if ("getOneEvent_For_Update".equals(action)) {

			String requestURL = request.getParameter("requestURL");

			Map<String, String> eventErrorMsgs = new LinkedHashMap<String, String>();
			request.setAttribute("eventErrorMsgs", eventErrorMsgs);

			try {
				/****************************** 1.接收請求參數 **************************************************/
				String eve_no = request.getParameter("eve_no");

				/****************************** 2.開始查詢資料 **************************************************/
				EventService eventService = new EventService();
				EventVO eventVO = eventService.getOneEvent(eve_no);

				/******************************* 3.查詢完成,準備轉交 **************************************************/
				request.setAttribute("eventVO", eventVO);
				RequestDispatcher successView = request.getRequestDispatcher("/backend/event/updateEvent.jsp");
				successView.forward(request, response);

				/****************************** 其他可能的錯誤處理 **************************************************/
			} catch (Exception e) {
				eventErrorMsgs.put("Exception", "無法取得資料 : " + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher(requestURL);
				failureView.forward(request, response);
			}
			return;
		}

		
		
		
		
		
		
		

		
		// 請求來源 : backend -> updateEvent.jsp
		else if ("updateEvent".equals(action)) {

			String requestURL = request.getParameter("requestURL");

			Map<String, String> eventErrorMsgs = new LinkedHashMap<String, String>();
			request.setAttribute("eventErrorMsgs", eventErrorMsgs);

			try {
				/****************************** 1.接收請求參數 - 輸入格式的錯誤處理 **************************************************/
				String evetit_no = request.getParameter("evetit_no");	
				String eve_no = request.getParameter("eve_no");				
				
				String eve_sessionname = request.getParameter("eve_sessionname");
				if (eve_sessionname == null || eve_sessionname.trim().length() == 0) {
					eventErrorMsgs.put("eve_sessionname", "請輸入場次名稱");
				}
				
				String venue_no = request.getParameter("venue_no");
					
				java.sql.Timestamp today = new java.sql.Timestamp(System.currentTimeMillis());
				java.sql.Timestamp eve_startdate = null;
				try {
					eve_startdate = java.sql.Timestamp.valueOf(request.getParameter("eve_startdate")+":00");					
				} catch (IllegalArgumentException e) {
					eventErrorMsgs.put("eve_startdate", "請輸入活動開始日期時間");
				}
				java.sql.Timestamp eve_enddate = null;
				try {
					eve_enddate = java.sql.Timestamp.valueOf(request.getParameter("eve_enddate")+":00");
//					if (today.compareTo(eve_enddate) > 0) {
//						eventErrorMsgs.put("eve_enddate_BiggerThanToday", "不得早於現在");
//					} 				
					if (eve_startdate.compareTo(eve_enddate) > 0) {
						eventErrorMsgs.put("eve_enddate_BiggerThanEveStartdate", "不得早於活動開始日期時間");
					} 
				} catch (IllegalArgumentException e) {
					eventErrorMsgs.put("eve_enddate", "請輸入活動結束日期時間");
				} catch (NullPointerException e){
					
				}
						
				java.sql.Timestamp eve_onsaledate = null;
				try {
					eve_onsaledate = java.sql.Timestamp.valueOf(request.getParameter("eve_onsaledate")+":00");
				} catch (IllegalArgumentException e) {
					eventErrorMsgs.put("eve_onsaledate", "請輸入開始售票日期時間");
				}
				java.sql.Timestamp eve_offsaledate = null;
				try {
					eve_offsaledate = java.sql.Timestamp.valueOf(request.getParameter("eve_offsaledate")+":00");
//					if (today.compareTo(eve_offsaledate) > 0) {
//						eventErrorMsgs.put("eve_offsaledate_BiggerThanToday", "不得早於現在");
//					} 				
					if (eve_onsaledate.compareTo(eve_offsaledate) > 0) {
						eventErrorMsgs.put("eve_offsaledate_BiggerThanEve_onsaledate", "不得早於售票開始日期時間");
					} 
				} catch (IllegalArgumentException e) {
					eventErrorMsgs.put("eve_offsaledate", "請輸入結束售票日期時間");
				} catch (NullPointerException e){
					
				}
				
				Integer ticlimit = new Integer(request.getParameter("ticlimit"));
				String eve_status = request.getParameter("eve_status");
				
				java.sql.Timestamp fullrefundenddate = null;
				try {
					fullrefundenddate = java.sql.Timestamp.valueOf(request.getParameter("fullrefundenddate")+":00");
//					if (today.compareTo(fullrefundenddate) > 0) {
//						eventErrorMsgs.put("fullrefundenddate_BiggerThanToday", "不得早於現在");
//					} 				
					if (eve_startdate.compareTo(fullrefundenddate) < 0) {
						eventErrorMsgs.put("fullrefundenddate_BiggerThanEve_startdate", "不得晚於活動開始日期時間");
					} 
				} catch (IllegalArgumentException e) {
					
				} catch (NullPointerException e){
					
				}
				
				
				byte[] eve_seatmap = null;
				String eve_seatmap_status = request.getParameter("eve_seatmap_status");
				if("noUpload".equals(eve_seatmap_status)) {
					if("init".equals(request.getParameter("eve_seatmap_init"))) {
						eventErrorMsgs.put("eve_seatmap", "請上傳座位圖");	
					}
					request.setAttribute("eve_seatmap_status", "noUpload");					
				} else if ("yesUpload".equals(eve_seatmap_status)){
					String saveDirectory = "/tempEvent";
					String realPath = getServletContext().getRealPath(saveDirectory);
					File fileSaveDirectory = new File(realPath);		
					if(!fileSaveDirectory.exists()) {
						fileSaveDirectory.mkdirs();
					}
					Part part = request.getPart("eve_seatmap");
					DateFormat dateFormat = new SimpleDateFormat("yyyymmdd_hhmmss_");  
					String strToday = dateFormat.format(today); 
					String submittedFileName = strToday + part.getSubmittedFileName();

					if(submittedFileName.length() != 0 && part.getContentType() != null) {
						File fileHere = new File(fileSaveDirectory, submittedFileName);
						part.write(fileHere.toString());								
					}			
					
					request.setAttribute("eve_seatmap_status", "alreadyUpload");						
					request.getSession().setAttribute("eve_seatmap_path", request.getContextPath() + saveDirectory + "/" + submittedFileName);									
				} else if ("alreadyUpload".equals(eve_seatmap_status)){
					request.setAttribute("eve_seatmap_status", "alreadyUpload");	
				}
				
				//====================================================================================================
				
				EventVO eventVO = new EventVO();			
				eventVO.setEve_no(eve_no);
				eventVO.setEvetit_no(evetit_no);
				eventVO.setVenue_no(venue_no);
				eventVO.setEve_sessionname(eve_sessionname);											
				eventVO.setEve_seatmap(eve_seatmap);					
				eventVO.setEve_startdate(eve_startdate);							
				eventVO.setEve_enddate(eve_enddate);		
				eventVO.setEve_onsaledate(eve_onsaledate); 				
				eventVO.setEve_offsaledate(eve_offsaledate);				
				eventVO.setTiclimit(ticlimit);			
				eventVO.setFullrefundenddate(fullrefundenddate);
				eventVO.setEve_status(eve_status);
				
				if (!eventErrorMsgs.isEmpty()) {
					request.setAttribute("eventVO", eventVO);
					RequestDispatcher failureView = request.getRequestDispatcher(requestURL);
					failureView.forward(request, response);
					return;
				}
				
				//====================================================================================================
				
				if(!"noUpload".equals(eve_seatmap_status)) {
					String eve_seatmap_path = (String) request.getSession().getAttribute("eve_seatmap_path");				
					String eve_seatmap_path_forUse = eve_seatmap_path.replace(request.getContextPath(), "").replace("/", "\\");
					String realPath = getServletContext().getRealPath("/") + eve_seatmap_path_forUse.substring(1);
					InputStream in = new FileInputStream(realPath);
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					int i;
					while ((i = in.read()) != -1)
						baos.write(i);
					eve_seatmap = baos.toByteArray();
					in.close();
					baos.close();
					
				}
				eventVO.setEve_seatmap(eve_seatmap);
				
				/****************************** 2.開始修改資料 **************************************************/
				EventService eventService = new EventService();
				eventService.updateEvent(eve_no, venue_no, eve_sessionname, eve_seatmap,
						eve_startdate, eve_enddate, eve_onsaledate, eve_offsaledate,
						ticlimit, fullrefundenddate, eve_status);
				
				request.getSession().removeAttribute("eve_seatmap_path");

				/****************************** 3.修改完成,準備轉交 ***************************************************/
				request.setAttribute("eventVO", eventVO);
				RequestDispatcher successView = request.getRequestDispatcher("/backend/event/listOneEvent.jsp");
				successView.forward(request, response);

				/****************************** 其他可能的錯誤處理 **************************************************/
			} catch (Exception e) {
				eventErrorMsgs.put("Exception", "修改資料失敗 : " + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher(requestURL);
				failureView.forward(request, response);
			}

		} 
		
		

		
		
		
		
		
		
		
		// 請求來源 : backend -> listAllEventTitleRelatives.jsp / listOneEvent.jsp
		else if ("addEvent".equals(action)) {

			String requestURL = request.getParameter("requestURL");

			Map<String, String> eventErrorMsgs = new LinkedHashMap<String, String>();
			request.setAttribute("eventErrorMsgs", eventErrorMsgs);

			try {
				/****************************** 1.接收請求參數 **************************************************/
				String evetit_no = request.getParameter("evetit_no");					
				
				/****************************** 2.開始新增資料 **************************************************/
				EventService eventService = new EventService();
				EventVO eventVO = eventService.addEvent(evetit_no);

				/****************************** 3.新增完成,準備轉交 ***************************************************/
				request.setAttribute("eventVO", eventVO);
				RequestDispatcher successView = request.getRequestDispatcher("/backend/event/updateEvent.jsp");
				successView.forward(request, response);

				/****************************** 其他可能的錯誤處理 **************************************************/
			} catch (Exception e) {
				eventErrorMsgs.put("Exception", "修改資料失敗 : " + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher(requestURL);
				failureView.forward(request, response);
			}

		} 
		
		
		
		
		
		
		
		
		
		
		// 請求來源 : back-end -> listAllEventTitleRelatives.jsp
		else if ("deleteEvent".equals(action)) {

			String requestURL = request.getParameter("requestURL");

			Map<String, String> eventErrorMsgs = new LinkedHashMap<String, String>();
			request.setAttribute("eventErrorMsgs", eventErrorMsgs);

			try {
				/****************************** 1.接收請求參數 **************************************************/
				String eve_no = request.getParameter("eve_no");
				String evetit_no = request.getParameter("evetit_no");
			
				/****************************** 2.開始刪除資料 **************************************************/
				EventService eventService = new EventService();
				eventService.deleteEvent(eve_no, evetit_no);

				/****************************** 3.刪除完成,準備轉交 **************************************************/
				RequestDispatcher successView = request.getRequestDispatcher(requestURL);
				successView.forward(request, response);

				/****************************** 其他可能的錯誤處理 **************************************************/
			} catch (Exception e) {
				eventErrorMsgs.put("Exception", "無法刪除資料 : " + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher(requestURL);
				failureView.forward(request, response);
			}
		} 
		
		
		
		
		
		
		
		
		

		
		// 請求來源 : back-end -> updateEvent.jsp
		else if ("copyEvent".equals(action)) {

			String requestURL = request.getParameter("requestURL");

			Map<String, String> eventErrorMsgs = new LinkedHashMap<String, String>();
			request.setAttribute("eventErrorMsgs", eventErrorMsgs);

			try {
				/****************************** 1.接收請求參數 **************************************************/
				String eve_no = request.getParameter("eve_no"); //本人
				String eve_no_forCopy = request.getParameter("eve_no_forCopy"); //要套用的複製
			
				/****************************** 2.開始複製資料 **************************************************/
				EventService eventService = new EventService();
				EventVO eventVO = eventService.copyEvent_withTicketTypeAndSeatingArea(eve_no, eve_no_forCopy);

				/****************************** 3.複製完成,準備轉交 **************************************************/
				request.setAttribute("eventVO", eventVO);
				RequestDispatcher successView = request.getRequestDispatcher(requestURL);
				successView.forward(request, response);

				/****************************** 其他可能的錯誤處理 **************************************************/
			} catch (Exception e) {
				eventErrorMsgs.put("Exception", "無法複製資料 : " + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher(requestURL);
				failureView.forward(request, response);
			}
		}
		
		
		
		
		
		
		
		
		

		
		// 請求來源 : back-end -> changeNotice.jsp
		else if ("getTicketOrders_byEvent".equals(action)) {

			String requestURL = request.getParameter("requestURL");

			Map<String, String> eventErrorMsgs = new LinkedHashMap<String, String>();
			request.setAttribute("eventErrorMsgs", eventErrorMsgs);

			try {
				/****************************** 1.接收請求參數 **************************************************/
				String eve_no = request.getParameter("eve_no");
				String evetit_no = request.getParameter("evetit_no");
			
				/****************************** 2.開始查詢資料 **************************************************/
				EventService eventService = new EventService();
				EventTitleService eventTitleService = new EventTitleService();
				Set<TicketVO2> ticketVOset = null;
				if("allEvents".equals(eve_no)) {
					ticketVOset = eventTitleService.getTicketsByEventTitle(evetit_no);
				} else {
					ticketVOset = eventService.getTicketsByEvent(eve_no);
				}
				
				/****************************** 3.查詢完成,準備轉交 **************************************************/
				request.setAttribute("ticketVOset", ticketVOset);
				RequestDispatcher successView = request.getRequestDispatcher(requestURL);
				successView.forward(request, response);

				/****************************** 其他可能的錯誤處理 **************************************************/
			} catch (Exception e) {
				eventErrorMsgs.put("Exception", "無法查詢資料 : " + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher(requestURL);
				failureView.forward(request, response);
			}
		}
		
		
		
		
		
		
		
		
		

		
		// 請求來源 : back-end -> changeNotice.jsp
		else if ("changeNoticeSendNotices".equals(action)) {

			String requestURL = request.getParameter("requestURL");

			Map<String, String> eventErrorMsgs = new LinkedHashMap<String, String>();
			request.setAttribute("eventErrorMsgs", eventErrorMsgs);

			try {
				/****************************** 1.接收請求參數 **************************************************/
				String phoneMessageText = request.getParameter("phoneMessageText");
				String subject = request.getParameter("subject");
				String messageText = request.getParameter("messageText");
				String eve_no = request.getParameter("eve_no");
				String evetit_no = request.getParameter("evetit_no");
				
				/****************************** 2.開始查詢資料 **************************************************/
				EventService eventService = new EventService();
				EventTitleService eventTitleService = new EventTitleService();
				Set<TicketVO2> ticketVOset = null;
				
				if("allEvents".equals(eve_no)) {
					ticketVOset = eventTitleService.getTicketsByEventTitle(evetit_no);
				} else {
					ticketVOset = eventService.getTicketsByEvent(eve_no);
				}
				
				/****************************** 3.發送通知 **************************************************/
				NoticeChangeMailService noticeChangeMailService = new NoticeChangeMailService();
				NoticeChangeMessageSend noticeChangeMessageSend = new NoticeChangeMessageSend();
				MemberService memberService = new MemberService();
				
				Set<String> emailAddressSet = new HashSet<>();
				Set<String> phoneNumberSet = new HashSet<>();
				
				for(TicketVO2 aTicketVO : ticketVOset) {
					String emailAddress = memberService.getOneMember(aTicketVO.getMember_no()).getEmail();
					emailAddressSet.add(emailAddress);
					String phoneNumber = memberService.getOneMember(aTicketVO.getMember_no()).getPhone().replace("-", "");
					phoneNumberSet.add(phoneNumber);			
				}
				for(String emailAddress : emailAddressSet ) {
					noticeChangeMailService.sendMail(emailAddress, subject, messageText);
				}
				
				noticeChangeMessageSend.sendMessage(phoneNumberSet.toArray(new String[phoneNumberSet.size()]), phoneMessageText);
				
				/****************************** 4.發送完成,準備轉交 **************************************************/
				request.setAttribute("ticketVOset", ticketVOset);
				request.setAttribute("changeNoticeSuccess", "changeNoticeSuccess");
				RequestDispatcher successView = request.getRequestDispatcher(requestURL);
				successView.forward(request, response);

				/****************************** 其他可能的錯誤處理 **************************************************/
			} catch (Exception e) {
				eventErrorMsgs.put("Exception", "無法發送資料 : " + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher(requestURL);
				failureView.forward(request, response);
			}
		}


		
		
	}

}
