package com.resaleorder.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.event.model.Event_H5_Service;
import com.event.model.Event_H5_VO;
import com.resaleorder.model.ShowResaleTicketVO;
import com.resaleorder.model.ResaleOrderService;
import com.resaleorder.model.ResaleOrderVO;
import com.seating_area.model.SeatingArea_H5_Service;
import com.seating_area.model.SeatingArea_H5_VO;
import com.ticket.model.TicketService;
import com.ticket.model.TicketVO;

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
		res.setHeader("Cache-Control", "no-store");
		res.setHeader("Pragma", "no-cache");
		res.setDateHeader("Expires", 0);
	
		if ("listResaleOrder_ByCompositeQuery".equals(action)) { // 來自select_page.jsp的複合查詢請求
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
//				String userSelectedItem = req.getParameter("selectBar_Choice");
				//目前用最蠢的作法，把所有正在轉讓中的票券全部列出來 
				//its a trap you have to search ticket.ticket_resale_status NOT resaleOrder
				Map<String, String[]> map = new TreeMap<String, String[]>();
				TicketService tSvc = new TicketService(); 
				map.put("ticket_resale_status", new String[] { "SELL" });
				List<TicketVO> list = tSvc.getAll_map(map, "ticket_create_time");
				
				if(list.size()==0) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/resaleorder/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				//maybe here can sort by any columnname to fit user input information.
				SeatingArea_H5_Service sh5Svc = new SeatingArea_H5_Service();
				Event_H5_Service eh5Svc = new Event_H5_Service();
				
				List<ShowResaleTicketVO> listShow = new LinkedList<ShowResaleTicketVO>();
				for(TicketVO avo:list) {
					ShowResaleTicketVO svo = new ShowResaleTicketVO();
					//there's still no resaleorders, so showResaleTicketVO has multiple empty attributes
					//after test, without those attribute might get 500, 
					svo.setResale_ordno("");
					svo.setMember_seller_no("");
					svo.setMember_buyer_no("");
					svo.setResale_ordprice(0);
					svo.setResale_ordstatus("");
					svo.setResale_ord_createtime(null);
					svo.setResale_ord_completetime(null);
					svo.setPayment_method("");
					
					svo.setTicket_no(avo.getTicket_no());
					
					svo.setTicarea_no(avo.getSeatingarea_h5VO().getTicarea_no());
					svo.setTicket_status(avo.getTicket_status());
					svo.setTicket_resale_status(avo.getTicket_resale_status());
					svo.setTicket_resale_price(avo.getTicket_resale_price());
					svo.setIs_from_resale(avo.getIs_from_resale());
					
					SeatingArea_H5_VO sh5VO = sh5Svc.getOneSeatingArea_H5(avo.getSeatingarea_h5VO().getTicarea_no());
					svo.setEve_no(sh5VO.getEve_h5VO().getEve_no());
					svo.setTictype_no(sh5VO.getTickettype_h5VO().getTictype_no());
					svo.setTicarea_name(sh5VO.getTicarea_name());
					svo.setTicarea_color(sh5VO.getTicarea_color());
					svo.setTictype_name(sh5VO.getTickettype_h5VO().getTictype_name());
					svo.setTictype_price(sh5VO.getTickettype_h5VO().getTictype_price());
					
					Event_H5_VO eh5VO = eh5Svc.getOneEvent_H5(sh5VO.getEve_h5VO().getEve_no());
					svo.setEvetit_no(eh5VO.getEventtitle_h5VO().getEvetit_no());
					svo.setVenue_no(eh5VO.getVenue_h5VO().getVenue_no());
					svo.setEve_startdate(eh5VO.getEve_startdate());
					svo.setEve_sessionname(eh5VO.getEve_sessionname());
					svo.setVenue_name(eh5VO.getVenue_h5VO().getVenue_name());
					svo.setAddress(eh5VO.getVenue_h5VO().getAddress());
					
					svo.setEvetit_name(eh5VO.getEventtitle_h5VO().getEvetit_name());
					
					listShow.add(svo);
				}
				req.setAttribute("listShow", listShow);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				RequestDispatcher successView = req.getRequestDispatcher("/frontend/resaleorder/listResaleOrdSelling_ByCompositeQuery.jsp");
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("frontend/resaleorder/select_page.jsp");
				failureView.forward(req, res);
			}
		}    
		if ("selected_targetResaleTicketToBuy".equals(action)) {
			
			//insert resale_ord and update target ticket
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String ticket_no = req.getParameter("ticket_no");
				
				//ticket_no
				if (ticket_no == null || (ticket_no.trim()).length() == 0) {
					errorMsgs.add("請輸入ticket_no編號");
				}
				if (ticket_no.length()>18) {
					errorMsgs.add("ticket_no格式不正確");
				}
				if (this.containsHanScript(ticket_no)) {
					errorMsgs.add("ticket_no不可包含中文");
				}
				
				Integer ticket_resale_price = null;
				try {
					ticket_resale_price = new Integer(req.getParameter("ticket_resale_price").trim());
					if(ticket_resale_price > 100100100 || ticket_resale_price <0) {
						errorMsgs.add("ticket_resale_price請勿亂填數字.");
					}
				} catch (NumberFormatException e) {
					ticket_resale_price = 0;
					errorMsgs.add("ticket_resale_price請填數字.");
				}
				
				/*after this line is select all tickets on resale and package as showresaleticketvo*/
				Map<String, String[]> map = new TreeMap<String, String[]>();
				TicketService tSvc = new TicketService(); 
				map.put("ticket_resale_status", new String[] { "SELL" });
				List<TicketVO> list = tSvc.getAll_map(map, "ticket_create_time");
				
				if(list.size()==0) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/resaleorder/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				SeatingArea_H5_Service sh5Svc = new SeatingArea_H5_Service();
				Event_H5_Service eh5Svc = new Event_H5_Service();
				
				List<ShowResaleTicketVO> listShow = new LinkedList<ShowResaleTicketVO>();
				for(TicketVO avo:list) {
					ShowResaleTicketVO svo = new ShowResaleTicketVO();
					//there's still no resaleorders, so showResaleTicketVO has multiple empty attributes
					//after test, without those attribute might get 500, 
					svo.setResale_ordno("");
					svo.setMember_seller_no("");
					svo.setMember_buyer_no("");
					svo.setResale_ordprice(0);
					svo.setResale_ordstatus("");
					svo.setResale_ord_createtime(null);
					svo.setResale_ord_completetime(null);
					svo.setPayment_method("");
					
					svo.setTicket_no(avo.getTicket_no());
					
					svo.setTicarea_no(avo.getSeatingarea_h5VO().getTicarea_no());
					svo.setTicket_status(avo.getTicket_status());
					svo.setTicket_resale_status(avo.getTicket_resale_status());
					svo.setTicket_resale_price(avo.getTicket_resale_price());
					svo.setIs_from_resale(avo.getIs_from_resale());
					
					SeatingArea_H5_VO sh5VO = sh5Svc.getOneSeatingArea_H5(avo.getSeatingarea_h5VO().getTicarea_no());
					svo.setEve_no(sh5VO.getEve_h5VO().getEve_no());
					svo.setTictype_no(sh5VO.getTickettype_h5VO().getTictype_no());
					svo.setTicarea_name(sh5VO.getTicarea_name());
					svo.setTicarea_color(sh5VO.getTicarea_color());
					svo.setTictype_name(sh5VO.getTickettype_h5VO().getTictype_name());
					svo.setTictype_price(sh5VO.getTickettype_h5VO().getTictype_price());
					
					Event_H5_VO eh5VO = eh5Svc.getOneEvent_H5(sh5VO.getEve_h5VO().getEve_no());
					svo.setEvetit_no(eh5VO.getEventtitle_h5VO().getEvetit_no());
					svo.setVenue_no(eh5VO.getVenue_h5VO().getVenue_no());
					svo.setEve_startdate(eh5VO.getEve_startdate());
					svo.setEve_sessionname(eh5VO.getEve_sessionname());
					svo.setVenue_name(eh5VO.getVenue_h5VO().getVenue_name());
					svo.setAddress(eh5VO.getVenue_h5VO().getAddress());
					
					svo.setEvetit_name(eh5VO.getEventtitle_h5VO().getEvetit_name());
					
					listShow.add(svo);
				}
				req.setAttribute("listShow", listShow);
				/*above is select all tickets on resale and package as showresaleticketvo*/
				
				// Send the use back to the form, if there were errors
				// make sure user still can see information , ,means some req.setAttribute should above this
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/resaleorder/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				//start to insert resaleorder and update target ticket
				ResaleOrderVO rvo = new ResaleOrderVO();
				TicketVO tvo = tSvc.getOneTicket(ticket_no);
				tvo.setTicket_resale_status("CHECKING2");
				
				//simulate member2, M000002 is buying resale tickets, it's add so forget pk
				rvo.setMember_buyer_no("M000002");
				rvo.setMember_seller_no(tvo.getMember_no());
				rvo.setResale_ordprice(tvo.getTicket_resale_price());
				rvo.setResale_ordstatus("WAITFORPAY1");
				rvo.setResale_ord_createtime(new java.sql.Timestamp(System.currentTimeMillis()));
				rvo.setResale_ord_completetime(null);
				rvo.setPayment_method("NOTYET");
				rvo.setTicketVO(tvo);
				ResaleOrderService rSvc = new ResaleOrderService();
				String createdResaleOrdNo = rSvc.insertAndGetReturnPK(rvo);
				
				/*start to get multiple database's vo to fit showResaleTicketvo*/
				ResaleOrderVO rvoc =  rSvc.getOneResaleOrd(createdResaleOrdNo);
				ShowResaleTicketVO svo = new ShowResaleTicketVO();
				svo.setResale_ordno(createdResaleOrdNo);
				svo.setMember_seller_no(tvo.getMember_no()); //line221's ticket's owner
				svo.setMember_buyer_no(rvoc.getMember_buyer_no());
				svo.setResale_ordprice(rvoc.getResale_ordprice());
				svo.setResale_ordstatus(rvoc.getResale_ordstatus());
				svo.setResale_ord_createtime(rvoc.getResale_ord_createtime());
				svo.setResale_ord_completetime(null);
				svo.setPayment_method("");
				svo.setTicket_no(tvo.getTicket_no());
				
				svo.setTicarea_no(tvo.getSeatingarea_h5VO().getTicarea_no());
				svo.setTicket_status(tvo.getTicket_status());
				svo.setTicket_resale_status(tvo.getTicket_resale_status());
				svo.setTicket_resale_price(tvo.getTicket_resale_price());
				svo.setIs_from_resale(tvo.getIs_from_resale());
				
				SeatingArea_H5_VO sh5VO = sh5Svc.getOneSeatingArea_H5(tvo.getSeatingarea_h5VO().getTicarea_no());
				svo.setEve_no(sh5VO.getEve_h5VO().getEve_no());
				svo.setTictype_no(sh5VO.getTickettype_h5VO().getTictype_no());
				svo.setTicarea_name(sh5VO.getTicarea_name());
				svo.setTicarea_color(sh5VO.getTicarea_color());
				svo.setTictype_name(sh5VO.getTickettype_h5VO().getTictype_name());
				svo.setTictype_price(sh5VO.getTickettype_h5VO().getTictype_price());
				
				Event_H5_VO eh5VO = eh5Svc.getOneEvent_H5(sh5VO.getEve_h5VO().getEve_no());
				svo.setEvetit_no(eh5VO.getEventtitle_h5VO().getEvetit_no());
				svo.setVenue_no(eh5VO.getVenue_h5VO().getVenue_no());
				svo.setEve_startdate(eh5VO.getEve_startdate());
				svo.setEve_sessionname(eh5VO.getEve_sessionname());
				svo.setVenue_name(eh5VO.getVenue_h5VO().getVenue_name());
				svo.setAddress(eh5VO.getVenue_h5VO().getAddress());
				
				svo.setEvetit_name(eh5VO.getEventtitle_h5VO().getEvetit_name());
				
				//after make sure target showResaleTicketVO exist, disable listShow. set req for next jsp
//				listShow.clear();
//				req.removeAttribute("listShow");
				req.setAttribute("svo", svo);
				
				String url = "/frontend/resaleorder/selectPaymentAndPay.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("database, listShow or .insertAndGetReturnPK or showRVO error, Emessage:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("frontend/resaleorder/select_page.jsp");
				failureView.forward(req, res);
			}	
		}
		if ("userPaying_from_resaleorder_phase3".equals(action)) {
			//insert resale_ord and update target ticket
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String resale_ordno = req.getParameter("resale_ordno");
				String member_buyer_no = req.getParameter("member_buyer_no");
				
				//resale_ordno
				if (resale_ordno == null || (resale_ordno.trim()).length() == 0) {
					errorMsgs.add("請輸入ticket_no編號");
				}
				if (resale_ordno.length()>18) {
					errorMsgs.add("resale_ordno格式不正確");
				}
				if (this.containsHanScript(resale_ordno)) {
					errorMsgs.add("resale_ordno不可包含中文");
				}
				//member_buyer_no
				if (member_buyer_no == null || (member_buyer_no.trim()).length() == 0) {
					errorMsgs.add("請輸入member_buyer_no編號");
				}
				if (member_buyer_no.length()>18) {
					errorMsgs.add("member_buyer_no格式不正確");
				}
				if (this.containsHanScript(member_buyer_no)) {
					errorMsgs.add("member_buyer_no不可包含中文");
				}
				
				//check resaleordervo from user.input.resale_ordno exist and waiting for pay or not.
				ResaleOrderService rSvc = new ResaleOrderService();
				ResaleOrderVO rvo = rSvc.getOneResaleOrd(resale_ordno);
				if(rvo == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("frontend/resaleorder/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
//				if(!("WAITFORPAY1".equals(rvo.getResale_ordstatus()))) {
//					errorMsgs.add("this ResaleOrderVO isn't wait to pay. cant update its status");
//				}
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("frontend/resaleorder/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
				
				//start update target ticket and resaleorder.
				TicketService tSvc = new TicketService(); 
				TicketVO tvo = tSvc.getOneTicket(rvo.getTicketVO().getTicket_no());
				tvo.setTicket_resale_status("NONE0");
				tvo.setTicket_resale_price(0);
				tvo.setIs_from_resale("YES");
				tvo.setMember_no(rvo.getMember_buyer_no()); // change target ticket's owner as rvo's buyer
				
				rvo.setResale_ord_completetime(new java.sql.Timestamp(System.currentTimeMillis()));
				rvo.setPayment_method("CREDITCARD");
				rvo.setResale_ordstatus("COMPLETE2");
				rvo.setTicketVO(tvo);
				rSvc.insert(rvo); // rvo has pk so it update.
				
				/*start to get multiple database's vo to fit showResaleTicketvo*/
//				ResaleOrderVO rvo2send =  rSvc.getOneResaleOrd(rvo.getResale_ordno());
//				TicketVO tvo4rvo2send = tSvc.getOneTicket(rvo2send.getTicketVO().getTicket_no());
				ShowResaleTicketVO svo = new ShowResaleTicketVO();
				svo.setResale_ordno(rvo.getResale_ordno());
				svo.setMember_seller_no(rvo.getMember_seller_no()); 
				svo.setMember_buyer_no(rvo.getMember_buyer_no());
				svo.setResale_ordprice(rvo.getResale_ordprice());
				svo.setResale_ordstatus(rvo.getResale_ordstatus());
				svo.setResale_ord_createtime(rvo.getResale_ord_createtime());
				svo.setResale_ord_completetime(rvo.getResale_ord_completetime());
				svo.setPayment_method(rvo.getPayment_method());
				svo.setTicket_no(rvo.getTicketVO().getTicket_no());
				
				svo.setTicarea_no(tvo.getSeatingarea_h5VO().getTicarea_no());
				svo.setTicket_status(tvo.getTicket_status());
				svo.setTicket_resale_status(tvo.getTicket_resale_status());
				svo.setTicket_resale_price(tvo.getTicket_resale_price());
				svo.setIs_from_resale(tvo.getIs_from_resale());
				
				SeatingArea_H5_Service sh5Svc = new SeatingArea_H5_Service();
				SeatingArea_H5_VO sh5VO = sh5Svc.getOneSeatingArea_H5(tvo.getSeatingarea_h5VO().getTicarea_no());
				svo.setEve_no(sh5VO.getEve_h5VO().getEve_no());
				svo.setTictype_no(sh5VO.getTickettype_h5VO().getTictype_no());
				svo.setTicarea_name(sh5VO.getTicarea_name());
				svo.setTicarea_color(sh5VO.getTicarea_color());
				svo.setTictype_name(sh5VO.getTickettype_h5VO().getTictype_name());
				svo.setTictype_price(sh5VO.getTickettype_h5VO().getTictype_price());
				
				Event_H5_Service eh5Svc = new Event_H5_Service();
				Event_H5_VO eh5VO = eh5Svc.getOneEvent_H5(sh5VO.getEve_h5VO().getEve_no());
				svo.setEvetit_no(eh5VO.getEventtitle_h5VO().getEvetit_no());
				svo.setVenue_no(eh5VO.getVenue_h5VO().getVenue_no());
				svo.setEve_startdate(eh5VO.getEve_startdate());
				svo.setEve_sessionname(eh5VO.getEve_sessionname());
				svo.setVenue_name(eh5VO.getVenue_h5VO().getVenue_name());
				svo.setAddress(eh5VO.getVenue_h5VO().getAddress());
				
				svo.setEvetit_name(eh5VO.getEventtitle_h5VO().getEvetit_name());
				
				req.setAttribute("svo", svo);
				
				String url = "/frontend/resaleorder/paymentDoneShowInfos.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("database, listShow or .insertAndGetReturnPK or showRVO error, Emessage:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("frontend/resaleorder/select_page.jsp");
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
