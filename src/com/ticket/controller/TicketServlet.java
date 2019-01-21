package com.ticket.controller;

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
import com.resaleorder.model.ResaleOrderService;
import com.seating_area.model.SeatingArea_H5_Service;
import com.seating_area.model.SeatingArea_H5_VO;
import com.ticket.model.*;
import com.ticketorder.model.TicketOrderService;
import com.ticketorder.model.TicketOrderVO;

/**
 * Servlet implementation class TicketOrderServlet
 */
//@WebServlet("/TicketOrderServlet")
public class TicketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	private static boolean checkInputValueAtAddTicketNo = false;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
//		HttpSession session = req.getSession();
		
//		if("getOne_For_Display".equals(action)) {
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//			try {
//				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//				String str = req.getParameter("ticket_no");
//				if (str == null || (str.trim()).length() == 0) {
//					errorMsgs.add("請輸入票券編號");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/frontend/ticket/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				if ((!str.contains("_")) || str.length()>18) {
//					errorMsgs.add("票券編號格式不正確");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/frontend/ticket/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				if (this.containsHanScript(str)) {
//					errorMsgs.add("票券編號格式不能有中文");
//				}
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/frontend/ticket/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				/***************************2.開始查詢資料*****************************************/
//				TicketService tSvc = new TicketService();
//				TicketVO tVO = tSvc.getOneTicket(str);
//				if (tVO == null) {
//					errorMsgs.add("查無資料");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/frontend/ticket/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("tVO", tVO); 
//				String url = "/frontend/ticket/listOneTicket.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); 
//				successView.forward(req, res);
//
//				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/frontend/ticket/select_page.jsp");
//				failureView.forward(req, res);
//			}
//		}
//		if ("getOne_For_Update".equals(action)) { // 來自listAllTicketOrder.jsp的請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//			
//			try {
//				/***************************1.接收請求參數****************************************/
//				String ticket_no = req.getParameter("ticket_no");
//				
//				/***************************2.開始查詢資料****************************************/
//				TicketService tSvc = new TicketService();
//				TicketVO tVO = tSvc.getOneTicket(ticket_no);
//								
//				/***************************3.查詢完成,準備轉交(Send the Success view)************/
//				req.setAttribute("tVO", tVO);
//				String url = "/frontend/ticket/update_ticket_input.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);
//
//				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/frontend/ticket/listAllTicket.jsp");
//				failureView.forward(req, res);
//			}
//		}
//		if ("update".equals(action)) { 
//			
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//		
//			try {
//				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//				String ticket_no = req.getParameter("ticket_no").trim();
//				
//				String ticarea_no = req.getParameter("ticarea_no");
//				if (ticarea_no == null || ticarea_no.trim().length() == 0) {
//					errorMsgs.add("座位區編號: 請勿空白");
//				} else if(!ticarea_no.trim().equals("ES00000001")) { 
//					errorMsgs.add("座位區編號: 目前只能是ES00000001");
//	            }
//				
//				String ticket_order_no = req.getParameter("ticket_order_no");
//				if (ticket_order_no == null || ticket_order_no.trim().length() == 0) {
//					errorMsgs.add("訂票訂單編號: 請勿空白");
//				} else if(!ticket_order_no.trim().contains("TO_20181225_00000") || ticket_order_no.trim().length()>18) {
//	            	errorMsgs.add("訂票訂單編號格式不正確");
//	            } else if(!this.checkInputTicketOrderNo(ticket_order_no)) {
//	            	errorMsgs.add("訂票訂單編號 目前只能是TO_20181225_000001 or TO_20181225_000002 or TO_20181225_000003");
//	            }
//				
//				String member_no = req.getParameter("member_no");
//				if (member_no == null || member_no.trim().length() == 0) {
//					errorMsgs.add("會員編號: 請勿空白");
//				} else if(!member_no.trim().equals("M000001")) { 
//					errorMsgs.add("會員編號: 目前只能是M000001");
//	            }
//				
//				String ticket_status = req.getParameter("ticket_status").trim();
//				if (ticket_status == null || ticket_status.trim().length() == 0) {
//					errorMsgs.add("票券狀態請勿空白");
//				} else if(ticket_status.length()>9) {
//					errorMsgs.add("票券狀態請勿超過9碼");
//				} else if(this.containsHanScript(ticket_status)) {
//					errorMsgs.add("票券狀態請勿使用中文");
//				} 
//				
//				java.sql.Timestamp ticket_create_time = null;
//				try {
//					ticket_create_time = java.sql.Timestamp.valueOf(req.getParameter("ticket_create_time").trim());
//				} catch (IllegalArgumentException e) {
//					ticket_create_time=new java.sql.Timestamp(System.currentTimeMillis());
//					errorMsgs.add("請輸入票券成立時間!");
//				}
//				
//				String ticket_resale_status = req.getParameter("ticket_resale_status").trim();
//				if (ticket_resale_status == null || ticket_resale_status.trim().length() == 0) {
//					errorMsgs.add("票券轉讓狀態請勿空白");
//				} else if(ticket_resale_status.length()>12) {
//					errorMsgs.add("票券轉讓狀態請勿超過12碼");
//				} else if(this.containsHanScript(ticket_resale_status)) {
//					errorMsgs.add("票券轉讓狀態請勿使用中文");
//				}
//				
//				Integer ticket_resale_price = null;
//				try {
//					ticket_resale_price = new Integer(req.getParameter("ticket_resale_price").trim());
//					if(ticket_resale_price > 100100100 || ticket_resale_price <0) {
//						errorMsgs.add("轉讓價格請勿亂填數字.");
//					}
//				} catch (NumberFormatException e) {
//					ticket_resale_price = 0;
//					errorMsgs.add("轉讓價格請填數字.");
//				}
//				
//				String is_from_resale = req.getParameter("is_from_resale").trim();
//				if (is_from_resale == null || is_from_resale.trim().length() == 0) {
//					errorMsgs.add("是否來自轉讓請勿空白");
//				} else if(is_from_resale.length()>3) {
//					errorMsgs.add("是否來自轉讓請勿超過3碼");
//				} else if(this.containsHanScript(is_from_resale)) {
//					errorMsgs.add("是否來自轉讓請勿使用中文");
//				}
//				
//				TicketVO tVO = new TicketVO();
//				tVO.setTicket_no(ticket_no);
////				tVO.setTicarea_no(ticarea_no);
////				tVO.setTicket_order_no(ticket_order_no);
//				tVO.setMember_no(member_no);
//				tVO.setTicket_status(ticket_status);
//				tVO.setTicket_create_time(ticket_create_time);
//				tVO.setTicket_resale_status(ticket_resale_status);
//				tVO.setTicket_resale_price(ticket_resale_price);
//				tVO.setIs_from_resale(is_from_resale);
//
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("tVO", tVO); 
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/frontend/ticket/update_ticket_input.jsp");
//					failureView.forward(req, res);
//					return; //程式中斷
//				}
//				
//				/***************************2.開始修改資料*****************************************/
//				TicketService tSvc = new TicketService();
//				tVO = tSvc.updateTicket(ticket_no, ticarea_no, ticket_order_no, member_no, ticket_status, ticket_create_time, ticket_resale_status, ticket_resale_price, is_from_resale);
//				
//				/***************************3.修改完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("tVO", tVO); 
//				String url = "/frontend/ticket/listOneTicket.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
//				successView.forward(req, res);
//
//				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("修改資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/frontend/ticket/update_ticket_input.jsp");
//				failureView.forward(req, res);
//			}
//		}
//        if ("insert".equals(action)) {   
//			
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
//				String ticarea_no = req.getParameter("ticarea_no");
//				if (ticarea_no == null || ticarea_no.trim().length() == 0) {
//					errorMsgs.add("座位區編號: 請勿空白");
//				} else if(!ticarea_no.trim().equals("ES00000001")) { 
//					errorMsgs.add("座位區編號: 目前只能是ES00000001");
//	            }
//				
//				String ticket_order_no = req.getParameter("ticket_order_no");
//				if (ticket_order_no == null || ticket_order_no.trim().length() == 0) {
//					errorMsgs.add("訂票訂單編號: 請勿空白");
//				} else if(!ticket_order_no.trim().contains("TO_20181225_00000") || ticket_order_no.trim().length()>18) {
//	            	errorMsgs.add("訂票訂單編號格式不正確");
//	            } else if(!this.checkInputTicketOrderNo(ticket_order_no)) {
//	            	errorMsgs.add("訂票訂單編號 目前只能是TO_20181225_000001 or TO_20181225_000002 or TO_20181225_000003");
//	            }
//				
//				String member_no = req.getParameter("member_no");
//				if (member_no == null || member_no.trim().length() == 0) {
//					errorMsgs.add("會員編號: 請勿空白");
//				} else if(!member_no.trim().equals("M000001")) { 
//					errorMsgs.add("會員編號: 目前只能是M000001");
//	            }
//				
//				String ticket_status = req.getParameter("ticket_status").trim();
//				if (ticket_status == null || ticket_status.trim().length() == 0) {
//					errorMsgs.add("票券狀態請勿空白");
//				} else if(ticket_status.length()>9) {
//					errorMsgs.add("票券狀態請勿超過9碼");
//				} else if(this.containsHanScript(ticket_status)) {
//					errorMsgs.add("票券狀態請勿使用中文");
//				}
//				
//				java.sql.Timestamp ticket_create_time = null;
//				try {
//					ticket_create_time = java.sql.Timestamp.valueOf(req.getParameter("ticket_create_time").trim());
//				} catch (IllegalArgumentException e) {
//					ticket_create_time=new java.sql.Timestamp(System.currentTimeMillis());
//					errorMsgs.add("請輸入票券成立時間!");
//				}
//				
//				String ticket_resale_status = req.getParameter("ticket_resale_status").trim();
//				if (ticket_resale_status == null || ticket_resale_status.trim().length() == 0) {
//					errorMsgs.add("票券轉讓狀態請勿空白");
//				} else if(ticket_resale_status.length()>12) {
//					errorMsgs.add("票券轉讓狀態請勿超過12碼");
//				} else if(this.containsHanScript(ticket_resale_status)) {
//					errorMsgs.add("票券轉讓狀態請勿使用中文");
//				}
//				
//				Integer ticket_resale_price = null;
//				try {
//					ticket_resale_price = new Integer(req.getParameter("ticket_resale_price").trim());
//					if(ticket_resale_price > 100100100 || ticket_resale_price <0) {
//						errorMsgs.add("轉讓價格請勿亂填數字.");
//					}
//				} catch (NumberFormatException e) {
//					ticket_resale_price = 0;
//					errorMsgs.add("轉讓價格請填數字.");
//				}
//				
//				String is_from_resale = req.getParameter("is_from_resale").trim();
//				if (is_from_resale == null || is_from_resale.trim().length() == 0) {
//					errorMsgs.add("是否來自轉讓請勿空白");
//				} else if(is_from_resale.length()>3) {
//					errorMsgs.add("是否來自轉讓請勿超過3碼");
//				} else if(this.containsHanScript(is_from_resale)) {
//					errorMsgs.add("是否來自轉讓請勿使用中文");
//				}
//				
//				TicketVO tVO = new TicketVO();
////				tVO.setTicarea_no(ticarea_no);
////				tVO.setTicket_order_no(ticket_order_no);
//				tVO.setMember_no(member_no);
//				tVO.setTicket_status(ticket_status);
//				tVO.setTicket_create_time(ticket_create_time);
//				tVO.setTicket_resale_status(ticket_resale_status);
//				tVO.setTicket_resale_price(ticket_resale_price);
//				tVO.setIs_from_resale(is_from_resale);
//
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("tVO", tVO); 
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/frontend/ticket/addTicket.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//				
//				/***************************2.開始新增資料***************************************/
//				TicketService tSvc = new TicketService();
//				tVO = tSvc.addTicket(ticarea_no, ticket_order_no, member_no, ticket_status, ticket_create_time, ticket_resale_status, ticket_resale_price, is_from_resale);
//				
//				/***************************3.新增完成,準備轉交(Send the Success view)***********/
//				String url = "/frontend/ticket/listAllTicket.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);				
//				
//				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/frontend/ticket/addTicket.jsp");
//				failureView.forward(req, res);
//			}
//		}
//        if ("delete".equals(action)) { 
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//	
//			try {
//				/***************************1.接收請求參數***************************************/
//				String ticket_no = req.getParameter("ticket_no");
//				
//				/***************************2.開始刪除資料***************************************/
//				TicketService tSvc = new TicketService();
//				tSvc.deleteTicket(ticket_no);
//				
//				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
//				String url = "/frontend/ticket/listAllTicket.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//				successView.forward(req, res);
//				
//				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("刪除資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/frontend/ticket/listAllTicket.jsp");
//				failureView.forward(req, res);
//			}
//		}
		
		if ("ticket_select_by_member_no".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
				
			try {
				String member_no = req.getParameter("member_no");
				
				TicketService tSvc = new TicketService();
				Map<String, String[]> map = new TreeMap<String, String[]>();
				map.put("member_no", new String[] {member_no});						
				List<TicketVO> memberListTVO = tSvc.getAll_map(map, "ticket_create_time");
				
				SeatingArea_H5_Service sh5Svc = new SeatingArea_H5_Service();
				Event_H5_Service eh5Svc = new Event_H5_Service();
				List<ShowTicketVO> listShow = new LinkedList<ShowTicketVO>();
				 
				for(TicketVO at:memberListTVO) {
					ShowTicketVO stvo = new ShowTicketVO();
					
					stvo.setTicket_no(at.getTicket_no());
					stvo.setMember_no(at.getMember_no());
					stvo.setTicket_status(at.getTicket_status());
					
					stvo.setTicket_resale_status(at.getTicket_resale_status());
					stvo.setTicket_resale_price(at.getTicket_resale_price());
					stvo.setIs_from_resale(at.getIs_from_resale());
					stvo.setTicket_order_no(at.getTicketorderVO().getTicket_order_no());
					
					SeatingArea_H5_VO sh5VO = sh5Svc.getOneSeatingArea_H5(at.getSeatingarea_h5VO().getTicarea_no());
					stvo.setTicarea_name(sh5VO.getTicarea_name());
					stvo.setTicarea_color(sh5VO.getTicarea_color());
					stvo.setTictype_name(sh5VO.getTickettype_h5VO().getTictype_name());
					stvo.setTictype_price(sh5VO.getTickettype_h5VO().getTictype_price());
					
					Event_H5_VO eh5VO = eh5Svc.getOneEvent_H5(sh5VO.getEve_h5VO().getEve_no());
					stvo.setEve_sessionname(eh5VO.getEve_sessionname());
					stvo.setEve_startdate(eh5VO.getEve_startdate());
					stvo.setEve_enddate(eh5VO.getEve_enddate());
					stvo.setEve_offsaledate(eh5VO.getEve_offsaledate());
					stvo.setEvetit_name(eh5VO.getEventtitle_h5VO().getEvetit_name());
					
					stvo.setVenue_name(eh5VO.getVenue_h5VO().getVenue_name());
					stvo.setAddress(eh5VO.getVenue_h5VO().getAddress());
					listShow.add(stvo);
				}
				req.setAttribute("listShow", listShow);
				
				String url = "/frontend/ticket/member_select_tickets.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("failed ticket_select_by_member_no:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/frontend/index.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("member_sell_targetTicket".equals(action)) {
			
			String ticket_resale_price = req.getParameter("ticket_resale_price");
			String ticket_no = req.getParameter("ticket_no");
			String member_no = req.getParameter("member_no");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				TicketService tSvc = new TicketService();
				TicketVO tvoFromDB = tSvc.getOneTicket(ticket_no);
				if(tvoFromDB == null) {
					errorMsgs.add("不存在此票券");
				}
				if("SELLING2".equals(tvoFromDB.getTicket_resale_status()) || "CHECKING3".equals(tvoFromDB.getTicket_resale_status())) {
					errorMsgs.add("此票已販賣中或是正在等待結帳，不可再度販賣或更動狀態");
				}
				//create listShow for error-forward
				Map<String, String[]> map = new TreeMap<String, String[]>();
				map.put("member_no", new String[] {member_no});						
				List<TicketVO> memberListTVO = tSvc.getAll_map(map, "ticket_create_time");
				
				SeatingArea_H5_Service sh5Svc = new SeatingArea_H5_Service();
				Event_H5_Service eh5Svc = new Event_H5_Service();
				List<ShowTicketVO> listShow = new LinkedList<ShowTicketVO>();
				 
				for(TicketVO at:memberListTVO) {
					ShowTicketVO stvo = new ShowTicketVO();
					
					stvo.setTicket_no(at.getTicket_no());
					stvo.setMember_no(at.getMember_no());
					stvo.setTicket_status(at.getTicket_status());
					
					stvo.setTicket_resale_status(at.getTicket_resale_status());
					stvo.setTicket_resale_price(at.getTicket_resale_price());
					stvo.setIs_from_resale(at.getIs_from_resale());
					stvo.setTicket_order_no(at.getTicketorderVO().getTicket_order_no());
					
					SeatingArea_H5_VO sh5VO = sh5Svc.getOneSeatingArea_H5(at.getSeatingarea_h5VO().getTicarea_no());
					stvo.setTicarea_name(sh5VO.getTicarea_name());
					stvo.setTicarea_color(sh5VO.getTicarea_color());
					stvo.setTictype_name(sh5VO.getTickettype_h5VO().getTictype_name());
					stvo.setTictype_price(sh5VO.getTickettype_h5VO().getTictype_price());
					
					Event_H5_VO eh5VO = eh5Svc.getOneEvent_H5(sh5VO.getEve_h5VO().getEve_no());
					stvo.setEve_sessionname(eh5VO.getEve_sessionname());
					stvo.setEve_startdate(eh5VO.getEve_startdate());
					stvo.setEve_enddate(eh5VO.getEve_enddate());
					stvo.setEve_offsaledate(eh5VO.getEve_offsaledate());
					stvo.setEvetit_name(eh5VO.getEventtitle_h5VO().getEvetit_name());
					
					stvo.setVenue_name(eh5VO.getVenue_h5VO().getVenue_name());
					stvo.setAddress(eh5VO.getVenue_h5VO().getAddress());
					listShow.add(stvo);
				}
				req.setAttribute("listShow", listShow);
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/ticketorder/member_select_tickets.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
			}catch(Exception e) {
				
			}
			
		} 
		
		if ("listTicketsByTicketOrderNoAndMemberNo".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String member_no = req.getParameter("member_no");
				String ticket_order_no = req.getParameter("ticket_order_no");
				
				req.setAttribute("member_no", member_no);
				req.setAttribute("ticket_order_no", ticket_order_no);
				
				//block when member_no == null
				if (member_no == null || (member_no.trim()).length() == 0) {
					errorMsgs.add("請先登入");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/login_front-end.jsp"); 
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String url = "/frontend/ticket/listAllTicketsBymember_noAndticket_order_no.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("failed at TicketServlet listTicketsByTicketOrderNoAndMemberNo:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/frontend/index.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("member_sell_One_ticket".equals(action)) { 
			
			//注意不要給原票券的訂票訂單持有人從票券明細進來後把這張他已轉賣的票給再度轉賣或是取消掉
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				System.out.println("11111111111");
				String member_no = req.getParameter("member_no");
				//block when member_no == null
				if (member_no == null || (member_no.trim()).length() == 0) {
					errorMsgs.add("請先登入");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/login_front-end.jsp"); 
					failureView.forward(req, res);
					return;//程式中斷
				}
				System.out.println("2222222222222");
				String ticket_no = req.getParameter("ticket_no");
				String ticket_order_no = req.getParameter("ticket_order_no");
				String str_ticket_resale_price = req.getParameter("ticket_resale_price");
				
				Integer ticket_resale_price = null;
				try {
					ticket_resale_price = new Integer(str_ticket_resale_price.trim());
					if(ticket_resale_price <1 || ticket_resale_price>65530) {
						errorMsgs.add("轉售價錢不正確");
					}
				} catch (NumberFormatException e) {
					errorMsgs.add("轉售價錢不正確");
				}
				System.out.println("333333333333333");
				req.setAttribute("member_no", member_no);
//				req.setAttribute("ticket_no", ticket_no);
//				req.setAttribute("ticket_resale_price", ticket_resale_price);
				req.setAttribute("ticket_order_no", ticket_order_no);
				System.out.println("4444444444444444");
				// Send the user back to the former page, if there were errors
				if (!errorMsgs.isEmpty()) {
					String url = "/frontend/ticket/listAllTicketsBymember_noAndticket_order_no.jsp";
					RequestDispatcher failureView = req
							.getRequestDispatcher(url);
					failureView.forward(req, res);
					return;//程式中斷
				}
				System.out.println("5555555555555555");
				String ticket_resale_status = "SELLING2";
				String is_from_resale = "NO";
				
				//prepare to update target ticket with two attribute: .ticket_resale_status to SELLING2 and .ticket_resale_price to ticket_resale_price
//				ResaleOrderService roSvc = new ResaleOrderService();
//				String updateTicketNo = roSvc.updateTargetTicketResaleAttributesAndMaybeInsertOneResaleOrder("", "", "", 0, "", null, null, "", true, false
//						, ticket_resale_status, ticket_resale_price, is_from_resale, ticket_no);
				
				TicketService tSvc = new TicketService();
				tSvc.getOneTicketAndUpdateItsResaleValues(ticket_no, ticket_resale_status, ticket_resale_price, is_from_resale);
				System.out.println("666666666666666");
				String url = "/frontend/ticket/listAllTicketsBymember_noAndticket_order_no.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("failed at member_sell_One_ticket :"+e.getMessage());
				System.out.println("failed listTicketsByTicketOrderNoAndMemberNo");
				RequestDispatcher failureView = req
						.getRequestDispatcher("/frontend/index.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("member_cancel_One_resale_ticket".equals(action)) 
		{ 
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				
				String member_no = req.getParameter("member_no");
				//block when member_no == null
				if (member_no == null || (member_no.trim()).length() == 0) {
					errorMsgs.add("請先登入");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/login_front-end.jsp"); 
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String ticket_no = req.getParameter("ticket_no");
				String ticket_order_no = req.getParameter("ticket_order_no");
				
				req.setAttribute("member_no", member_no);
				req.setAttribute("ticket_order_no", ticket_order_no);
				
				String ticket_resale_status = "NONE1";
				String is_from_resale = "NO";
				Integer ticket_resale_price = 0;
				
				//prepare to update target ticket with two attribute: .ticket_resale_status to SELLING2 and .ticket_resale_price to ticket_resale_price
//				ResaleOrderService roSvc = new ResaleOrderService();
//				String updateTicketNo = roSvc.updateTargetTicketResaleAttributesAndMaybeInsertOneResaleOrder("", "", "", 0, "", null, null, "", true, false
//						, ticket_resale_status, ticket_resale_price, is_from_resale, ticket_no);
				TicketService tSvc = new TicketService();
				tSvc.getOneTicketAndUpdateItsResaleValues(ticket_no, ticket_resale_status, ticket_resale_price, is_from_resale);
				
				String url = "/frontend/ticket/listAllTicketsBymember_noAndticket_order_no.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("failed member_cancel_One_resale_ticket:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/frontend/index.jsp");
				failureView.forward(req, res);
			}
		}
		
//		if ("member_buy_One_Resale_ticket".equals(action)) { 
//			
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//			
//			try {
//				
//				String member_no = req.getParameter("member_no");
//				//block when member_no == null
//				if (member_no == null || (member_no.trim()).length() == 0) {
//					errorMsgs.add("請先登入");
//				}
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/frontend/login_front-end.jsp"); 
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				String ticket_no = req.getParameter("ticket_no");
//				
//				req.setAttribute("member_no", member_no);
//				
//				// Send the user back to the former page, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					String url = "/frontend/ticket/listAllResaleTicketsByTicketStatus.jsp";
//					RequestDispatcher failureView = req
//							.getRequestDispatcher(url);
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				String ticket_resale_status = "NONE1";
//				String is_from_resale = "NO";
//				
//				//prepare to update target ticket with two attribute: .ticket_resale_status to SELLING2 and .ticket_resale_price to ticket_resale_price
//				TicketService tSvc = new TicketService();
////				tSvc.getOneTicketAndUpdateItsResaleValues(ticket_no, ticket_resale_status, 0, is_from_resale, member_no);
//				
//				
//				String url = "/frontend/ticket/listAllResaleTicketsByTicketStatus.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);
//				
//			} catch (Exception e) {
//				errorMsgs.add("failed listTicketsByTicketOrderNoAndMemberNo:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/frontend/index.jsp");
//				failureView.forward(req, res);
//			}
//		}
		
		//code below is for backend/ticket to search every event's seatingarea situation.
//		if("".equals(action)) {
//			
//			
//			try {
//				
//			} catch(Exception e) {
//				
//			}
//		}

		if ("listEventTitle_ByCompositeQuery_from_backend_ticket".equals(action)) {
			
			Map<String, String> eventTitleErrorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("eventTitleErrorMsgs", eventTitleErrorMsgs);
			
			try {
				
//				String member_no = req.getParameter("member_no");
//				MemberVO member = (MemberVO) session.getAttribute("member");
//				if(member==null ) {
//					eventTitleErrorMsgs.put("Exception", "請先登入");
//				}
//				if (!eventTitleErrorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req.getRequestDispatcher("/backend/ticket/select_page.jsp"); 
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				String member_no = member.getMemberNo();
//				req.setAttribute("member_no", member_no);

				/****************************** 1.將輸入資料轉為Map **************************************************/ 
				Map<String, String[]> map = req.getParameterMap();

				/****************************** 2.開始複合查詢 **************************************************/
				EventTitleService eventTitleService = new EventTitleService();
				List<EventTitleVO> list  = eventTitleService.getAllLaunched(map);

				List<String> listEveTitNo = new LinkedList<String>();
				for(EventTitleVO a_etvo :list) {
					String evetit_no = a_etvo.getEvetit_no();
					listEveTitNo.add(evetit_no);
				}
				System.out.println("listSizeOfEventTitleSearch="+listEveTitNo.size());
				if(listEveTitNo.size()==0) {
					eventTitleErrorMsgs.put("Exception", "無法取得資料");
					System.out.println("這可能是代表查無目前還有在賣或是還有座位區剩餘票樹的活動");
				}
				
				if (!eventTitleErrorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/ticket/select_page.jsp"); 
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String [] strArrayEveTitNo = listEveTitNo.toArray(new String[listEveTitNo.size()]);
				System.out.println("strArrayEveTitNo created,="+strArrayEveTitNo);
				System.out.println("0000");
				for(int i = 0;i<strArrayEveTitNo.length;i++) {
					System.out.println("strArrayEveTitNo["+i+"]="+strArrayEveTitNo[i]);
				}
				System.out.println("1111");
				
				
				Event_H5_Service eh5Svc = new Event_H5_Service();
				
				Map<String, String[]> map_Event_H5_VO = new TreeMap<String, String[]>();
				map_Event_H5_VO.put("evetit_no", strArrayEveTitNo);
				List<Event_H5_VO> listEvent_H5_VO = eh5Svc.getAll(map_Event_H5_VO, "eve_no");
				System.out.println("222");
				List<String> listEveNo = new LinkedList<String>();
				for(Event_H5_VO a_eh5vo:listEvent_H5_VO) {
					listEveNo.add(a_eh5vo.getEve_no());
				}
				
				req.setAttribute("listEveNo", listEveNo);
				
				System.out.println("listEveNo.size="+listEveNo.size());
				for(String a_eve_no :listEveNo) {
					System.out.println("a liseEveNo's eve_no="+a_eve_no);
				}
				
				/****************************** 3.查詢完成,準備轉交 **************************************************/
															//WebContent\backend\ticket\listAllEvevoByCompositeQueryWithListEveNo.jsp
				RequestDispatcher successView = req.getRequestDispatcher("/backend/ticket/listAllEvevoByCompositeQueryWithListEveNo.jsp");
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
//				eventTitleErrorMsgs.put("Exception", "無法取得資料 : " + e.getMessage());
				eventTitleErrorMsgs.put("Exception", "無法取得資料 : " + e.getMessage());
				System.out.println("error at ticketServlet.java action=listEventTitle_ByCompositeQuery_from_backend_ticket");
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/ticket/select_page.jsp");
				failureView.forward(req, res);
			}
			
		}
		
			
		if("member_select_One_Evevo_From_listAllEvevoByCompositeQueryWithListEveNo".equals(action)) {
        	List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			System.out.println("bear111");
			
        	try {
        		
//        		String member_no = req.getParameter("member_no");
//        		System.out.println("member_no="+member_no);
//        		if (member_no == null || (member_no.trim()).length() == 0) {        			
//					errorMsgs.add("請先登入");
//				}
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/backend/ticket/select_page.jsp"); 
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
				
				String eve_no = req.getParameter("eve_no");
				System.out.println("bear222");
				
//        		req.setAttribute("member_no", member_no);
        		req.setAttribute("eve_no", eve_no);
        		System.out.println("bear333");
        			//WebContent\backend\ticket\listAllSeatingAreaByEveNo.jsp
        	//CA105G2\WebContent\backend\ticket\listAllSeatingAreaByEveNo.jsp
        		String url = "/backend/ticket/listAllSeatingAreaByEveNo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
				
        	} catch(Exception e) {
        		System.out.println("bear444");
        		errorMsgs.add("error at controller ticketServlet.java action=member_select_One_Evevo_From_listAllEvevoByCompositeQueryWithListEveNo"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/ticket/select_page.jsp");
				failureView.forward(req, res);
        	}
        }
		
	}
	public boolean checkInputTicketOrderNo(String str) {
		String strTest = str.substring(str.length()-1, str.length());
		Integer iTest = Integer.parseInt(strTest);
		if(iTest<0) {
			return false;
		}else if (iTest >3) {
			return false;
		}else {
			return true;
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
