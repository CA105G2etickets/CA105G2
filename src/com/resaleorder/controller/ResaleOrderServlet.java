package com.resaleorder.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.event.model.Event_H5_Service;
import com.event.model.Event_H5_VO;
import com.event_title.model.EventTitleService;
import com.event_title.model.EventTitleVO;
import com.member.model.MemberVO;
import com.resaleorder.model.ShowResaleTicketVO;
import com.resaleorder.model.ResaleOrderService;
import com.resaleorder.model.ResaleOrderVO;
import com.seating_area.model.SeatingArea_H5_Service;
import com.seating_area.model.SeatingArea_H5_VO;
import com.ticket.model.TicketService;
import com.ticket.model.TicketVO;
import com.ticket.model.TicketVO2;
import com.ticketorder.model.ShowTicketOrderVO;
import com.ticketorder.model.TicketOrderService;
import com.ticketorder.model.TicketOrderVO;
import com.ticketorder.model.TicketOrderVO2;

//@WebServlet("/ResaleOrderServlet")
public class ResaleOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ResaleOrderServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
//		res.setHeader("Cache-Control", "no-store");
//		res.setHeader("Pragma", "no-cache");
//		res.setDateHeader("Expires", 0);
	
		//20190121 disable line 54 to 516 because of illegalStateException, Servlet might enter into wrong action.
//		if ("listResaleOrder_ByCompositeQuery".equals(action)) { // 來自select_page.jsp的複合查詢請求
//			
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//			String member_no = req.getParameter("member_no");
//
//			try {
////				String userSelectedItem = req.getParameter("selectBar_Choice");
//				//目前用最蠢的作法，把所有正在轉讓中的票券全部列出來 
//				//its a trap you have to search ticket.ticket_resale_status NOT resaleOrder
//				Map<String, String[]> map = new TreeMap<String, String[]>();
//				TicketService tSvc = new TicketService(); 
//				map.put("ticket_resale_status", new String[] { "SELL" });
//				List<TicketVO> list = tSvc.getAll_map(map, "ticket_create_time");
//				
//				//save member_no for F5
//				req.setAttribute("member_no", member_no);
//				
//				if(list.size()==0) {
//					errorMsgs.add("查無資料");
//				}
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/frontend/resaleorder/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				//maybe here can sort by any columnname to fit user input information.
//				SeatingArea_H5_Service sh5Svc = new SeatingArea_H5_Service();
//				Event_H5_Service eh5Svc = new Event_H5_Service();
//				
//				List<ShowResaleTicketVO> listShow = new LinkedList<ShowResaleTicketVO>();
//				for(TicketVO avo:list) {
//					ShowResaleTicketVO svo = new ShowResaleTicketVO();
//					//there's still no resaleorders, so showResaleTicketVO has multiple empty attributes
//					//after test, without those attribute might get 500, 
//					svo.setResale_ordno("");
//					svo.setMember_seller_no(avo.getMember_no());
//					svo.setMember_buyer_no("");
//					svo.setResale_ordprice(0);
//					svo.setResale_ordstatus("");
//					svo.setResale_ord_createtime(null);
//					svo.setResale_ord_completetime(null);
//					svo.setPayment_method("");
//					
//					svo.setTicket_no(avo.getTicket_no());
//					
//					svo.setTicarea_no(avo.getSeatingarea_h5VO().getTicarea_no());
//					svo.setTicket_status(avo.getTicket_status());
//					svo.setTicket_resale_status(avo.getTicket_resale_status());
//					svo.setTicket_resale_price(avo.getTicket_resale_price());
//					svo.setIs_from_resale(avo.getIs_from_resale());
//					
//					SeatingArea_H5_VO sh5VO = sh5Svc.getOneSeatingArea_H5(avo.getSeatingarea_h5VO().getTicarea_no());
//					svo.setEve_no(sh5VO.getEve_h5VO().getEve_no());
//					svo.setTictype_no(sh5VO.getTickettype_h5VO().getTictype_no());
//					svo.setTicarea_name(sh5VO.getTicarea_name());
//					svo.setTicarea_color(sh5VO.getTicarea_color());
//					svo.setTictype_name(sh5VO.getTickettype_h5VO().getTictype_name());
//					svo.setTictype_price(sh5VO.getTickettype_h5VO().getTictype_price());
//					
//					Event_H5_VO eh5VO = eh5Svc.getOneEvent_H5(sh5VO.getEve_h5VO().getEve_no());
//					svo.setEvetit_no(eh5VO.getEventtitle_h5VO().getEvetit_no());
//					svo.setVenue_no(eh5VO.getVenue_h5VO().getVenue_no());
//					svo.setEve_startdate(eh5VO.getEve_startdate());
//					svo.setEve_sessionname(eh5VO.getEve_sessionname());
//					svo.setVenue_name(eh5VO.getVenue_h5VO().getVenue_name());
//					svo.setAddress(eh5VO.getVenue_h5VO().getAddress());
//					
//					svo.setEvetit_name(eh5VO.getEventtitle_h5VO().getEvetit_name());
//					
//					listShow.add(svo);
//				}
//				req.setAttribute("listShow", listShow);
//				
//				/***************************3.查詢完成,準備轉交(Send the Success view)************/
//				RequestDispatcher successView = req.getRequestDispatcher("/frontend/resaleorder/listResaleOrdSelling_ByCompositeQuery.jsp");
//				successView.forward(req, res);
//				
//				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("frontend/resaleorder/select_page.jsp");
//				failureView.forward(req, res);
//			}
//		}    
//		if ("selected_targetResaleTicketToBuy".equals(action)) {
//			
//			//insert resale_ord and update target ticket
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//			String member_no = req.getParameter("member_no");
//			try {
//				String ticket_no = req.getParameter("ticket_no");
//				
//				//ticket_no
//				if (ticket_no == null || (ticket_no.trim()).length() == 0) {
//					errorMsgs.add("請輸入ticket_no編號");
//				}
//				if (ticket_no.length()>18) {
//					errorMsgs.add("ticket_no格式不正確");
//				}
//				if (this.containsHanScript(ticket_no)) {
//					errorMsgs.add("ticket_no不可包含中文");
//				}
//				
//				Integer ticket_resale_price = null;
//				try {
//					ticket_resale_price = new Integer(req.getParameter("ticket_resale_price").trim());
//					if(ticket_resale_price > 100100100 || ticket_resale_price <0) {
//						errorMsgs.add("ticket_resale_price請勿亂填數字.");
//					}
//				} catch (NumberFormatException e) {
//					ticket_resale_price = 0;
//					errorMsgs.add("ticket_resale_price請填數字.");
//				}
//				
//				/*after this line is select all tickets on resale and package as showresaleticketvo*/
//				Map<String, String[]> map = new TreeMap<String, String[]>();
//				TicketService tSvc = new TicketService(); 
//				map.put("ticket_resale_status", new String[] { "SELL" });
//				List<TicketVO> list = tSvc.getAll_map(map, "ticket_create_time");
//				
//				if(list.size()==0) {
//					errorMsgs.add("查無資料");
//				}
//				
//				req.setAttribute("member_no", member_no);
//				
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/frontend/resaleorder/listResaleOrdSelling_ByCompositeQuery.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				
//				
////				List<ShowResaleTicketVO> listShow = new LinkedList<ShowResaleTicketVO>();
////				for(TicketVO avo:list) {
////					ShowResaleTicketVO svo = new ShowResaleTicketVO();
////					//there's still no resaleorders, so showResaleTicketVO has multiple empty attributes
////					//after test, without those attribute might get 500, 
////					svo.setResale_ordno("");
////					svo.setMember_seller_no(avo.getMember_no());
////					svo.setMember_buyer_no(member_no);
////					svo.setResale_ordprice(0);
////					svo.setResale_ordstatus("");
////					svo.setResale_ord_createtime(null);
////					svo.setResale_ord_completetime(null);
////					svo.setPayment_method("");
////					
////					svo.setTicket_no(avo.getTicket_no());
////					
////					svo.setTicarea_no(avo.getSeatingarea_h5VO().getTicarea_no());
////					svo.setTicket_status(avo.getTicket_status());
////					svo.setTicket_resale_status(avo.getTicket_resale_status());
////					svo.setTicket_resale_price(avo.getTicket_resale_price());
////					svo.setIs_from_resale(avo.getIs_from_resale());
////					
////					SeatingArea_H5_VO sh5VO = sh5Svc.getOneSeatingArea_H5(avo.getSeatingarea_h5VO().getTicarea_no());
////					svo.setEve_no(sh5VO.getEve_h5VO().getEve_no());
////					svo.setTictype_no(sh5VO.getTickettype_h5VO().getTictype_no());
////					svo.setTicarea_name(sh5VO.getTicarea_name());
////					svo.setTicarea_color(sh5VO.getTicarea_color());
////					svo.setTictype_name(sh5VO.getTickettype_h5VO().getTictype_name());
////					svo.setTictype_price(sh5VO.getTickettype_h5VO().getTictype_price());
////					
////					Event_H5_VO eh5VO = eh5Svc.getOneEvent_H5(sh5VO.getEve_h5VO().getEve_no());
////					svo.setEvetit_no(eh5VO.getEventtitle_h5VO().getEvetit_no());
////					svo.setVenue_no(eh5VO.getVenue_h5VO().getVenue_no());
////					svo.setEve_startdate(eh5VO.getEve_startdate());
////					svo.setEve_sessionname(eh5VO.getEve_sessionname());
////					svo.setVenue_name(eh5VO.getVenue_h5VO().getVenue_name());
////					svo.setAddress(eh5VO.getVenue_h5VO().getAddress());
////					
////					svo.setEvetit_name(eh5VO.getEventtitle_h5VO().getEvetit_name());
////					
////					listShow.add(svo);
////				}
////				req.setAttribute("listShow", listShow);
//				/*above is select all tickets on resale and package as showresaleticketvo*/
//				
//				// Send the use back to the form, if there were errors
//				// make sure user still can see information , ,means some req.setAttribute should above this
////				if (!errorMsgs.isEmpty()) {
////					RequestDispatcher failureView = req
////							.getRequestDispatcher("/frontend/resaleorder/listResaleOrdSelling_ByCompositeQuery.jsp");
////					failureView.forward(req, res);
////					return;//程式中斷
////				}
//				
//				SeatingArea_H5_Service sh5Svc = new SeatingArea_H5_Service();
//				Event_H5_Service eh5Svc = new Event_H5_Service();
//				//start to insert resaleorder and update target ticket
//				ResaleOrderVO rvo = new ResaleOrderVO();
//				TicketVO tvo = tSvc.getOneTicket(ticket_no);
//				tvo.setTicket_resale_status("CHECKING2");
//				
//				//simulate member2, M000002 is buying resale tickets, it's add so forget pk
//				rvo.setMember_buyer_no(member_no);
//				rvo.setMember_seller_no(tvo.getMember_no());
//				rvo.setResale_ordprice(tvo.getTicket_resale_price());
//				rvo.setResale_ordstatus("WAITFORPAY1");
//				rvo.setResale_ord_createtime(new java.sql.Timestamp(System.currentTimeMillis()));
//				rvo.setResale_ord_completetime(null);
//				rvo.setPayment_method("NOTYET");
//				rvo.setTicketVO(tvo);
//				ResaleOrderService rSvc = new ResaleOrderService();
//				String createdResaleOrdNo = rSvc.insertAndGetReturnPK(rvo);
//				
//				/*start to get multiple database's vo to fit showResaleTicketvo*/
//				ResaleOrderVO rvoc =  rSvc.getOneResaleOrd(createdResaleOrdNo);
//				ShowResaleTicketVO svo = new ShowResaleTicketVO();
//				svo.setResale_ordno(createdResaleOrdNo);
//				svo.setMember_seller_no(tvo.getMember_no()); //line221's ticket's owner
//				svo.setMember_buyer_no(rvoc.getMember_buyer_no());
//				svo.setResale_ordprice(rvoc.getResale_ordprice());
//				svo.setResale_ordstatus(rvoc.getResale_ordstatus());
//				svo.setResale_ord_createtime(rvoc.getResale_ord_createtime());
//				svo.setResale_ord_completetime(null);
//				svo.setPayment_method("NOTYET");
//				svo.setTicket_no(tvo.getTicket_no());
//				
//				svo.setTicarea_no(tvo.getSeatingarea_h5VO().getTicarea_no());
//				svo.setTicket_status(tvo.getTicket_status());
//				svo.setTicket_resale_status(tvo.getTicket_resale_status());
//				svo.setTicket_resale_price(tvo.getTicket_resale_price());
//				svo.setIs_from_resale(tvo.getIs_from_resale());
//				
//				SeatingArea_H5_VO sh5VO = sh5Svc.getOneSeatingArea_H5(tvo.getSeatingarea_h5VO().getTicarea_no());
//				svo.setEve_no(sh5VO.getEve_h5VO().getEve_no());
//				svo.setTictype_no(sh5VO.getTickettype_h5VO().getTictype_no());
//				svo.setTicarea_name(sh5VO.getTicarea_name());
//				svo.setTicarea_color(sh5VO.getTicarea_color());
//				svo.setTictype_name(sh5VO.getTickettype_h5VO().getTictype_name());
//				svo.setTictype_price(sh5VO.getTickettype_h5VO().getTictype_price());
//				
//				Event_H5_VO eh5VO = eh5Svc.getOneEvent_H5(sh5VO.getEve_h5VO().getEve_no());
//				svo.setEvetit_no(eh5VO.getEventtitle_h5VO().getEvetit_no());
//				svo.setVenue_no(eh5VO.getVenue_h5VO().getVenue_no());
//				svo.setEve_startdate(eh5VO.getEve_startdate());
//				svo.setEve_sessionname(eh5VO.getEve_sessionname());
//				svo.setVenue_name(eh5VO.getVenue_h5VO().getVenue_name());
//				svo.setAddress(eh5VO.getVenue_h5VO().getAddress());
//				
//				svo.setEvetit_name(eh5VO.getEventtitle_h5VO().getEvetit_name());
//				
//				//after make sure target showResaleTicketVO exist, disable listShow. set req for next jsp
////				listShow.clear();
////				req.removeAttribute("listShow");
//				req.setAttribute("svo", svo);
//				
//				String url = "/frontend/resaleorder/selectPaymentAndPay.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);
//				
//				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("listResaleOrdSelling_ByCompositeQuery error, Emessage:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("frontend/resaleorder/listResaleOrdSelling_ByCompositeQuery.jsp");
//				failureView.forward(req, res);
//			}	
//		}
//		if ("userPaying_from_resaleorder_phase3".equals(action)) {
//			//insert resale_ord and update target ticket
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				String resale_ordno = req.getParameter("resale_ordno");
//				String member_buyer_no = req.getParameter("member_buyer_no");
//				
//				//resale_ordno
//				if (resale_ordno == null || (resale_ordno.trim()).length() == 0) {
//					errorMsgs.add("請輸入ticket_no編號");
//				}
//				if (resale_ordno.length()>18) {
//					errorMsgs.add("resale_ordno格式不正確");
//				}
//				if (this.containsHanScript(resale_ordno)) {
//					errorMsgs.add("resale_ordno不可包含中文");
//				}
//				//member_buyer_no
//				if (member_buyer_no == null || (member_buyer_no.trim()).length() == 0) {
//					errorMsgs.add("請輸入member_buyer_no編號");
//				}
//				if (member_buyer_no.length()>18) {
//					errorMsgs.add("member_buyer_no格式不正確");
//				}
//				if (this.containsHanScript(member_buyer_no)) {
//					errorMsgs.add("member_buyer_no不可包含中文");
//				}
//				
//				
//				ResaleOrderService rSvc = new ResaleOrderService();
//				ResaleOrderVO rvoc =  rSvc.getOneResaleOrd(resale_ordno);
//				ShowResaleTicketVO svo = new ShowResaleTicketVO();
//				svo.setResale_ordno(resale_ordno);
//				svo.setMember_seller_no(rvoc.getMember_seller_no()); //line221's ticket's owner
//				svo.setMember_buyer_no(member_buyer_no);
//				svo.setResale_ordprice(rvoc.getResale_ordprice());
//				svo.setResale_ordstatus(rvoc.getResale_ordstatus());
//				svo.setResale_ord_createtime(rvoc.getResale_ord_createtime());
//				svo.setResale_ord_completetime(null);
//				svo.setPayment_method("NOTYET");
//				svo.setTicket_no(rvoc.getTicketVO().getTicket_no());
//				
//				TicketService tSvc = new TicketService();
//				TicketVO tvo = tSvc.getOneTicket(rvoc.getTicketVO().getTicket_no());
//				svo.setTicarea_no(tvo.getSeatingarea_h5VO().getTicarea_no());
//				svo.setTicket_status(tvo.getTicket_status());
//				svo.setTicket_resale_status(tvo.getTicket_resale_status());
//				svo.setTicket_resale_price(tvo.getTicket_resale_price());
//				svo.setIs_from_resale(tvo.getIs_from_resale());
//				
//				SeatingArea_H5_Service sh5Svc = new SeatingArea_H5_Service();
//				Event_H5_Service eh5Svc = new Event_H5_Service();
//				SeatingArea_H5_VO sh5VO = sh5Svc.getOneSeatingArea_H5(tvo.getSeatingarea_h5VO().getTicarea_no());
//				svo.setEve_no(sh5VO.getEve_h5VO().getEve_no());
//				svo.setTictype_no(sh5VO.getTickettype_h5VO().getTictype_no());
//				svo.setTicarea_name(sh5VO.getTicarea_name());
//				svo.setTicarea_color(sh5VO.getTicarea_color());
//				svo.setTictype_name(sh5VO.getTickettype_h5VO().getTictype_name());
//				svo.setTictype_price(sh5VO.getTickettype_h5VO().getTictype_price());
//				
//				Event_H5_VO eh5VO = eh5Svc.getOneEvent_H5(sh5VO.getEve_h5VO().getEve_no());
//				svo.setEvetit_no(eh5VO.getEventtitle_h5VO().getEvetit_no());
//				svo.setVenue_no(eh5VO.getVenue_h5VO().getVenue_no());
//				svo.setEve_startdate(eh5VO.getEve_startdate());
//				svo.setEve_sessionname(eh5VO.getEve_sessionname());
//				svo.setVenue_name(eh5VO.getVenue_h5VO().getVenue_name());
//				svo.setAddress(eh5VO.getVenue_h5VO().getAddress());
//				
//				svo.setEvetit_name(eh5VO.getEventtitle_h5VO().getEvetit_name());
//				
//				//after make sure target showResaleTicketVO exist, disable listShow. set req for next jsp
////				listShow.clear();
////				req.removeAttribute("listShow");
//				req.setAttribute("svo", svo);
//				
//				
//				
//				//check resaleordervo from user.input.resale_ordno exist and waiting for pay or not.
//				ResaleOrderVO rvo = rSvc.getOneResaleOrd(resale_ordno);
//				if(rvo == null) {
//					errorMsgs.add("查無資料");
//				}
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("frontend/resaleorder/selectPaymentAndPay.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
////				if(!("WAITFORPAY1".equals(rvo.getResale_ordstatus()))) {
////					errorMsgs.add("this ResaleOrderVO isn't wait to pay. cant update its status");
////				}
////				if (!errorMsgs.isEmpty()) {
////					RequestDispatcher failureView = req
////							.getRequestDispatcher("frontend/resaleorder/select_page.jsp");
////					failureView.forward(req, res);
////					return;//程式中斷
////				}
//				
//				//start update target ticket and resaleorder.
//				TicketVO tvo2 = tSvc.getOneTicket(rvo.getTicketVO().getTicket_no());
//				tvo2.setTicket_resale_status("NONE0");
//				tvo2.setTicket_resale_price(0);
//				tvo2.setIs_from_resale("YES");
//				tvo2.setMember_no(rvo.getMember_buyer_no()); // change target ticket's owner as rvo's buyer
//				
//				rvo.setResale_ord_completetime(new java.sql.Timestamp(System.currentTimeMillis()));
//				rvo.setPayment_method("CREDITCARD");
//				rvo.setResale_ordstatus("COMPLETE2");
//				rvo.setTicketVO(tvo2);
//				rSvc.insert(rvo); // rvo has pk so it update.
//				
//				/*start to get multiple database's vo to fit showResaleTicketvo*/
////				ResaleOrderVO rvo2send =  rSvc.getOneResaleOrd(rvo.getResale_ordno());
////				TicketVO tvo4rvo2send = tSvc.getOneTicket(rvo2send.getTicketVO().getTicket_no());
//				ShowResaleTicketVO svo2 = new ShowResaleTicketVO();
//				svo2.setResale_ordno(rvo.getResale_ordno());
//				svo2.setMember_seller_no(rvo.getMember_seller_no()); 
//				svo2.setMember_buyer_no(rvo.getMember_buyer_no());
//				svo2.setResale_ordprice(rvo.getResale_ordprice());
//				svo2.setResale_ordstatus(rvo.getResale_ordstatus());
//				svo2.setResale_ord_createtime(rvo.getResale_ord_createtime());
//				svo2.setResale_ord_completetime(rvo.getResale_ord_completetime());
//				svo2.setPayment_method(rvo.getPayment_method());
//				svo2.setTicket_no(rvo.getTicketVO().getTicket_no());
//				
//				svo2.setTicarea_no(tvo2.getSeatingarea_h5VO().getTicarea_no());
//				svo2.setTicket_status(tvo2.getTicket_status());
//				svo2.setTicket_resale_status(tvo2.getTicket_resale_status());
//				svo2.setTicket_resale_price(tvo2.getTicket_resale_price());
//				svo2.setIs_from_resale(tvo2.getIs_from_resale());
//				
//				
//				SeatingArea_H5_VO sh5VO2 = sh5Svc.getOneSeatingArea_H5(tvo.getSeatingarea_h5VO().getTicarea_no());
//				svo2.setEve_no(sh5VO2.getEve_h5VO().getEve_no());
//				svo2.setTictype_no(sh5VO2.getTickettype_h5VO().getTictype_no());
//				svo2.setTicarea_name(sh5VO2.getTicarea_name());
//				svo2.setTicarea_color(sh5VO2.getTicarea_color());
//				svo2.setTictype_name(sh5VO2.getTickettype_h5VO().getTictype_name());
//				svo2.setTictype_price(sh5VO2.getTickettype_h5VO().getTictype_price());
//				
//				
//				Event_H5_VO eh5VO2 = eh5Svc.getOneEvent_H5(sh5VO.getEve_h5VO().getEve_no());
//				svo2.setEvetit_no(eh5VO2.getEventtitle_h5VO().getEvetit_no());
//				svo2.setVenue_no(eh5VO2.getVenue_h5VO().getVenue_no());
//				svo2.setEve_startdate(eh5VO2.getEve_startdate());
//				svo2.setEve_sessionname(eh5VO2.getEve_sessionname());
//				svo2.setVenue_name(eh5VO2.getVenue_h5VO().getVenue_name());
//				svo2.setAddress(eh5VO2.getVenue_h5VO().getAddress());
//				
//				svo2.setEvetit_name(eh5VO2.getEventtitle_h5VO().getEvetit_name());
//				
//				req.setAttribute("svo", svo2);
//				
//				String url = "/frontend/resaleorder/paymentDoneShowInfos.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);
//				
//				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("userPaying_from_resaleorder_phase3 error, Emessage:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("frontend/resaleorder/selectPaymentAndPay.jsp");
//				failureView.forward(req, res);
//			}	
//		}
//		
//		if("resaleorder_select_by_member_no".equals(action)) {
//        	List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//			
//        	try {
//        		String member_no = req.getParameter("member_no");
//        		if (member_no == null || (member_no.trim()).length() == 0) {
//					errorMsgs.add("購票前請登入");
//				}
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/frontend/login_front-end.jsp"); 
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//        		req.setAttribute("member_no", member_no);
//				
//        		String url = "/frontend/resaleorder/select_page.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); 
//				successView.forward(req, res);
//        	} catch(Exception e) {
//        		System.out.println("failed at resaleorder_select_by_member_no.jsp");
//        		errorMsgs.add("failed at resaleorder_select_by_member_no.jsp ErrorMsg:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/frontend/index.jsp");
//				failureView.forward(req, res);
//        	}
//        }
		
		//20190114 new way of coding starts.
		if("member_select_resaleorders".equals(action)) {
        	List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
        	try {
//        		String member_no = req.getParameter("member_no");
        		MemberVO member = (MemberVO) session.getAttribute("member");
        		String member_no = member.getMemberNo();
        		if (member_no == null || (member_no.trim()).length() == 0) {
        			
					errorMsgs.add("瀏覽轉讓平台前，請先登入");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/login_front-end.jsp"); 
					failureView.forward(req, res);
					return;//程式中斷
				}
        		req.setAttribute("member_no", member_no);

        		String url = "/frontend/resaleorder/listAllResaleTicketsByTicketStatus.jsp";

				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
        	} catch(Exception e) {
        		errorMsgs.add("failed at resaleorder_select_by_member_no.jsp ErrorMsg:"+e.getMessage());
        		System.out.println("failed at resaleorder_select_by_member_no.jsp ErrorMsg:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/frontend/index.jsp");
				failureView.forward(req, res);
        	}
        }
		
		if("member_buy_One_Resale_ticket".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				
				String member_buyer_no = req.getParameter("member_buyer_no");
				//block when member_no == null
				if (member_buyer_no == null || (member_buyer_no.trim()).length() == 0) {
					errorMsgs.add("請先登入");
					System.out.println("no member_no at ResaleOrderServlet.java, action = member_buy_One_Resale_ticket");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/login_front-end.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				//set req.setAttribute for error to send back
				req.setAttribute("member_no", member_buyer_no);
				
				//getParameter ordprice first for controller debugging
				String str_resale_ordprice = req.getParameter("resale_ordprice");
				
				Integer resale_ordprice = null;
				try {
					resale_ordprice = new Integer(Integer.parseInt(str_resale_ordprice));
					
					if(resale_ordprice > 1001001 || resale_ordprice <0) {
						errorMsgs.add("轉讓票價請勿亂填數字.");
					}
				} catch (NumberFormatException e) {
					resale_ordprice = 0;
					errorMsgs.add("轉讓票價請填數字.");
				}
				
				// Send the user back to the former page, if there were errors
				if (!errorMsgs.isEmpty()) {
					System.out.println("error at ResaleOrderServlet.java, action = member_buy_One_Resale_ticket");
					String url = "/frontend/resaleorder/listAllResaleTicketsByTicketStatus.jsp";
					RequestDispatcher failureView = req
							.getRequestDispatcher(url);
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String ticket_no = req.getParameter("ticket_no");
				
				//get ticketvo for insert resaleorder
//				TicketService tSvc = new TicketService();
				
				String member_seller_no = req.getParameter("member_seller_no");
				
				//decide resaleorderVO
				String resale_ordstatus = "WAITTOPAY1";
				Timestamp resale_ord_createtime = new java.sql.Timestamp(System.currentTimeMillis());
				Timestamp resale_ord_completetime = null;
				String payment_method = "NOTYET";
				
				//set for ticketvo
				String ticket_resale_status = "CHECKING3";
				Integer ticket_resale_price = resale_ordprice;
				String is_from_resale = "NO";
				
				//prepare to add new resaleorder and update target ticket with two attribute: .ticket_resale_status to SELLING2 and .ticket_resale_price to ticket_resale_price
				ResaleOrderService roSvc = new ResaleOrderService();
				String resale_ordno = roSvc.addResaleOrdAndUpdateTicketVOResaleAttributes(
						ticket_no, member_seller_no, member_buyer_no, 
						resale_ordprice, resale_ordstatus, resale_ord_createtime, 
						resale_ord_completetime, payment_method, member_seller_no, 
						ticket_resale_status, ticket_resale_price, is_from_resale);
				
				
//				String resale_ordno = roSvc.updateTargetTicketResaleAttributesAndMaybeInsertOneResaleOrder("", member_seller_no, member_buyer_no, 
//						resale_ordprice, resale_ordstatus, resale_ord_createtime, resale_ord_completetime, payment_method, 
//						false, false, "", 0, "", ticket_no);
				
//				String resale_ordno = roSvc.insertOneResaleOrderAndUpdateTargetTicketToBuying(member_seller_no, member_buyer_no, 
//						resale_ordprice, resale_ordstatus, resale_ord_createtime, resale_ord_completetime, payment_method, ticket_no);
				
				req.setAttribute("resale_ordno", resale_ordno);
				ResaleOrderVO rvoToSend = roSvc.getOneResaleOrd(resale_ordno);
				TicketService tSvc = new TicketService();
				TicketVO tvoForSelectPaymentAndPay = tSvc.getOneTicket(rvoToSend.getTicketVO().getTicket_no());
				req.setAttribute("ticketVO", tvoForSelectPaymentAndPay);
				
//				String url = "/frontend/resaleorder/listOneResaleOrderToPay.jsp";
				String url = "/frontend/resaleorder/selectPaymentAndPay.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
//				errorMsgs.add("出錯，因此您的操作無效");
				System.out.println("error at ResaleOrderServlet.java action=member_buy_One_Resale_ticket");
				RequestDispatcher failureView = req
						.getRequestDispatcher("/frontend/resaleorder/listAllResaleTicketsByTicketStatus.jsp");
				failureView.forward(req, res);
			}
        	
        }
		
		//update_One_resaleorder_by_member
		if("update_One_resaleorder_by_member".equals(action)) {
        	List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			//only missing payment and completetime.
			
        	try {
        		String member_no = req.getParameter("member_no");
        		String resale_ordno = req.getParameter("resale_ordno");
        		
        		String creditCardNumber = req.getParameter("creditCardNumber");
				String creditCardVerificationNumber = req.getParameter("creditCardVerificationNumber");
				String creditCardYear = req.getParameter("creditCardMonth");
				String creditCardMonth = req.getParameter("creditCardYear");
				if(creditCardNumber.length() !=16 ) {
					errorMsgs.add("信用卡卡號不正確，應為16碼數字");
				}
				if(creditCardVerificationNumber.length()!=3) {
					errorMsgs.add("信用卡卡片背面後三碼不正確");
				}
				Integer cardmonth = null;
				try {
					cardmonth = new Integer(creditCardMonth.trim());
					if(cardmonth <1 || cardmonth>12) {
						errorMsgs.add("卡片到期月份不正確");
					}
				} catch (NumberFormatException e) {
					errorMsgs.add("卡片到期月份不正確.");
				}
				
				Integer cardyear = null;
				try {
					cardyear = new Integer(creditCardYear.trim());
					if(cardyear <2019 || cardyear>2030) {
						errorMsgs.add("卡片到期年份不正確");
					}
				} catch (NumberFormatException e) {
					errorMsgs.add("卡片到期年份不正確.");
				}
				
				req.setAttribute("member_no", member_no);
        		req.setAttribute("resale_ordno", resale_ordno);
        		
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/resaleorder/selectPaymentAndPay.jsp"); 
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				//debug done, start to contact db
				ResaleOrderService roSvc = new ResaleOrderService();
				ResaleOrderVO revo = roSvc.getOneResaleOrd(resale_ordno);
				revo.setResale_ordstatus("COMPLETE2");
				revo.setPayment_method("CREDITCARD");
				revo.setResale_ord_completetime(new java.sql.Timestamp(System.currentTimeMillis()));
				
				//disable these codes because want to do it in service or dao.
//				TicketService tSvc = new TicketService();
//				TicketVO tvo_getOneToSeeinfo = tSvc.getOneTicket(revo.getTicketVO().getTicket_no());
				
				//set TicketVO for update resaleordervo
//				String ticket_no = revo.getTicketVO().getTicket_no();
				TicketVO tvoToUpdate = revo.getTicketVO();
				String new_member_no = member_no;
				String ticket_resale_status = "NONE1";
				Integer ticket_resale_price = 0;
				String is_from_resale = "YES"; 
				
				tvoToUpdate.setMember_no(new_member_no);
				tvoToUpdate.setTicket_resale_status(ticket_resale_status);
				tvoToUpdate.setTicket_resale_price(ticket_resale_price);
				tvoToUpdate.setIs_from_resale(is_from_resale);
				revo.setTicketVO(tvoToUpdate);
				
				//revo has pk so it become update and also update target ticketvo too.
				roSvc.insert(revo);
				
				req.setAttribute("revo", revo);
				
				
        		String url = "/frontend/resaleorder/paymentDoneShowInfos.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
        	} catch(Exception e) {
        		errorMsgs.add(e.getMessage());
        		System.out.println("error at ResaleOrderServlet.java action=update_One_resaleorder_by_member");
				RequestDispatcher failureView = req
						.getRequestDispatcher("/frontend/resaleorder/listOneResaleOrderToPay.jsp");
				failureView.forward(req, res);
        	}
        }
		
		//method create by Hannah Tai, disable at 201901211808
//		if ("listEventTitle_ByCompositeQuery".equals(action)) {
//			
//			Map<String, String> eventTitleErrorMsgs = new LinkedHashMap<String, String>();
//			req.setAttribute("eventTitleErrorMsgs", eventTitleErrorMsgs);
//			
//			try {
//				
//				String member_no = req.getParameter("member_no");
//				req.setAttribute("member_no", member_no);
//
//				/****************************** 1.將輸入資料轉為Map **************************************************/ 
//				Map<String, String[]> map = req.getParameterMap();
//
//				/****************************** 2.開始複合查詢 **************************************************/
//				EventTitleService eventTitleService = new EventTitleService();
//				List<EventTitleVO> list  = eventTitleService.getAllLaunched(map);
//
//				List<String> listEveTitNo = new LinkedList<String>();
//				for(EventTitleVO a_etvo :list) {
//					String evetit_no = a_etvo.getEvetit_no();
//					listEveTitNo.add(evetit_no);
//				}
//				System.out.println("listSizeOfEventTitleSearch="+listEveTitNo.size());
//				if(listEveTitNo.size()==0) {
//					eventTitleErrorMsgs.put("Exception", "無法取得資料");
//					System.out.println("這可能是代表查無目前還有在賣或是還有座位區剩餘票樹的活動");
//				}
//				
//				if (!eventTitleErrorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/resaleorder/select_page.jsp"); 
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				String [] strArrayEveTitNo = listEveTitNo.toArray(new String[listEveTitNo.size()]);
//				System.out.println("strArrayEveTitNo created,="+strArrayEveTitNo);
//				System.out.println("0000");
//				for(int i = 0;i<strArrayEveTitNo.length;i++) {
//					System.out.println("strArrayEveTitNo["+i+"]="+strArrayEveTitNo[i]);
//				}
//				System.out.println("1111");
//				
//				
//				Event_H5_Service eh5Svc = new Event_H5_Service();
//				
//				Map<String, String[]> map_Event_H5_VO = new TreeMap<String, String[]>();
//				map_Event_H5_VO.put("evetit_no", strArrayEveTitNo);
//				List<Event_H5_VO> listEvent_H5_VO = eh5Svc.getAll(map_Event_H5_VO, "eve_no");
//				System.out.println("222");
//				List<String> listEveNo = new LinkedList<String>();
//				for(Event_H5_VO a_eh5vo:listEvent_H5_VO) {
//					listEveNo.add(a_eh5vo.getEve_no());
//				}
//				
//				req.setAttribute("listEveNo", listEveNo);
//				
//				System.out.println("listEveNo.size="+listEveNo.size());
//				for(String a_eve_no :listEveNo) {
//					System.out.println("a liseEveNo's eve_no="+a_eve_no);
//				}
//				
//				/****************************** 3.查詢完成,準備轉交 **************************************************/
////				
////				RequestDispatcher successView = req.getRequestDispatcher("/frontend/index.jsp");
//				RequestDispatcher successView = req.getRequestDispatcher("/frontend/resaleorder/listAllResaleTicketsByTicketStatusAndListEveNo.jsp");
//				successView.forward(req, res);
//				
//				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
////				eventTitleErrorMsgs.put("Exception", "無法取得資料 : " + e.getMessage());
//				eventTitleErrorMsgs.put("Exception", "無法取得資料 : " + e.getMessage());
//				System.out.println("error at ResaleOrderServlet action=listEventTitle_ByCompositeQuery");
//				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/index.jsp");
//				failureView.forward(req, res);
//			}
//			
//		}
		
		//disable at 201901211808
//		if("member_select_One_SeatingArea_From_listAllResaleTicketsByTicketStatusAndListEveNo".equals(action)) {
//        	List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//			
//        	try {
//        		
//        		String member_no = req.getParameter("member_no");
//        		System.out.println("member_no="+member_no);
//        		if (member_no == null || (member_no.trim()).length() == 0) {        			
//					errorMsgs.add("查詢個人轉售訂單時請先登入");
//				}
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/frontend/login_front-end.jsp"); 
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				String eve_no = req.getParameter("eve_no");
//				
//				
//        		req.setAttribute("member_no", member_no);
//        		req.setAttribute("eve_no", eve_no);
//        		
//        		System.out.println(member_no);
//        		System.out.println(eve_no);
//        		System.out.println("member_select_One_SeatingArea_From_listAllResaleTicketsByTicketStatusAndListEveNo");
//        		
//
//        		String url = "/frontend/resaleorder/listAllSeatingAreaByEveNo.jsp";
////        		String url = "/frontend/index.jsp";
//
////        		String url = "/backend/ticket/listAllSeatingAreaByListEveNo.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); 
//				successView.forward(req, res);
//				
//        	} catch(Exception e) {
//        		errorMsgs.add("failed at ResaleOrderServlet.java action=member_select_One_SeatingArea_From_listAllResaleTicketsByTicketStatusAndListEveNo ErrorMsg:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/frontend/index.jsp");
//				failureView.forward(req, res);
//        	}
//        }
		
		//member_select_resaleorders_by_member_no
		if("member_select_resaleorders_by_member_no".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String member_no = req.getParameter("member_no");
				String resale_ordno = req.getParameter("resale_ordno");
				String ticket_no = req.getParameter("ticket_no");
				
				System.out.println("valueSendToController at ResaleOrderServlet, action =member_select_resaleorders_by_member_no:"
									+member_no+resale_ordno+ticket_no);
				
				//code below disable because ResaleOrderService.getResaleOrderVOsByTicketno sometimes send back wrong info. so use criteria function to replace
//				TicketService tSvc = new TicketService();
//				List<TicketVO> list_tvo = tSvc.getTicketsByMemberNo(member_no);
//				List<String> list_ticket_no = new LinkedList<String>();
//				for(TicketVO atvo:list_tvo) {
//					System.out.println("every ticket_no in ticketvo from ticketvosByMemberNo="+atvo.getTicket_no());
//					list_ticket_no.add(atvo.getTicket_no());
//				}
//				
//				String[] strArrayTicketNos = list_ticket_no.toArray(new String[list_ticket_no.size()]);
//				ResaleOrderService reSvc = new ResaleOrderService();
//				Map<String, String[]> mapForResaleOrder = new TreeMap<String, String[]>();
//				mapForResaleOrder.put("ticket_no", strArrayTicketNos);
//				List<ResaleOrderVO> list_revos = reSvc.getAll(mapForResaleOrder, "resale_ordno");
				
				ResaleOrderService reSvc = new ResaleOrderService();
				System.out.println("bear201901220106="+member_no);
				String[] strArray_member_buyer_no = {member_no};
				List<ResaleOrderVO> list_revos = reSvc.getResaleOrderVOsByMemberBuyer(strArray_member_buyer_no);
				System.out.println("bear201901220106_2.size="+list_revos.size());
				req.setAttribute("list_revos", list_revos);
				
				RequestDispatcher successView = req.getRequestDispatcher("/frontend/resaleorder/listAllResaleOrdersByMemberNo.jsp");
				successView.forward(req, res);
				
			} catch(Exception e) {
				errorMsgs.add(e.getMessage());
				System.out.println("error at ResaleOrderServlet.java action=member_select_resaleorders_by_member_no");
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/index.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("member_select_One_resaleorder_fromListAll".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);;
			
//			String member_buyer_no = req.getParameter("member_buyer_no");
			String resale_ordno = req.getParameter("resale_ordno");
			System.out.println("member_select_One_resaleorder at ResaleOrderServlet.java");
			
			String ticket_no = req.getParameter("ticket_no");
			
			try {
				TicketService tSvc = new TicketService();
				TicketVO tvo = tSvc.getOneTicket(ticket_no);
				
				req.setAttribute("tvo", tvo);
				req.setAttribute("resale_ordno", resale_ordno);
				System.out.println("resale_ordno at member_select_One_resaleorder_fromListAll= "+resale_ordno);
				
				RequestDispatcher successView = req.getRequestDispatcher("/frontend/resaleorder/listOneTicketByResaleOrder.jsp");
				successView.forward(req, res);
				
			} catch(Exception e) {
				errorMsgs.add(e.getMessage());
				System.out.println("error at ResaleOrderServlet.java action=member_select_One_resaleorder");
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/index.jsp");
				failureView.forward(req, res);
			}
		}
		
		
	}
	
	public boolean containsHanScript(String s) {
	    for (int i = 0; i < s.length(); ) {
	        int codepoint = s.codePointAt(i);
	        i += Character.charCount(codepoint);
	        if (Character.UnicodeScript.of(codepoint) == Character.UnicodeScript.HAN) {
	            return true;
	        }
	    }
	    return false;
	}

}
