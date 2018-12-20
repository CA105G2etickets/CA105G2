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
import com.ticket.model.*;

/**
 * Servlet implementation class TicketOrderServlet
 */
//@WebServlet("/TicketOrderServlet")
public class TicketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		if("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("ticket_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入票券編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/ticket/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				if ((!str.contains("_")) || str.length()>18) {
					errorMsgs.add("票券編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/ticket/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				TicketService tSvc = new TicketService();
				TicketVO tVO = tSvc.getOneTicket(str);
				if (tVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/ticket/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("tVO", tVO); 
				String url = "/ticket/listOneTicket.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/ticket/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOne_For_Update".equals(action)) { // 來自listAllTicketOrder.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String ticket_no = req.getParameter("ticket_no");
				
				/***************************2.開始查詢資料****************************************/
				TicketService tSvc = new TicketService();
				TicketVO tVO = tSvc.getOneTicket(ticket_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("tVO", tVO);
				String url = "/ticket/update_ticket_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/ticket/listAllTicket.jsp");
				failureView.forward(req, res);
			}
		}
		if ("update".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String ticket_no = req.getParameter("ticket_no").trim();
				
				String ticarea_no = req.getParameter("ticarea_no");
				if (ticarea_no == null || ticarea_no.trim().length() == 0) {
					errorMsgs.add("座位區編號: 請勿空白");
				} else if(!ticarea_no.trim().equals("E000101A01")) { 
					errorMsgs.add("座位區編號: 目前只能是E000101A01");
	            }
				
				String ticket_order_no = req.getParameter("ticket_order_no");
				if (ticket_order_no == null || ticket_order_no.trim().length() == 0) {
					errorMsgs.add("訂票訂單編號: 請勿空白");
				} else if(!ticket_order_no.trim().equals("TO_20181225_000001")) { 
					errorMsgs.add("訂票訂單編號 目前只能是TO_20181225_000001");
	            }
				
				String member_no = req.getParameter("member_no");
				if (member_no == null || member_no.trim().length() == 0) {
					errorMsgs.add("會員編號: 請勿空白");
				} else if(!member_no.trim().equals("M000001")) { 
					errorMsgs.add("會員編號: 目前只能是M000001");
	            }
				
				String ticket_status = req.getParameter("ticket_status").trim();
				if (ticket_status == null || ticket_status.trim().length() == 0) {
					errorMsgs.add("票券狀態請勿空白");
				}else if(ticket_status.length()>9) {
					errorMsgs.add("票券狀態請勿超過9碼");
				}
				
				java.sql.Timestamp ticket_create_time = null;
				try {
					ticket_create_time = java.sql.Timestamp.valueOf(req.getParameter("ticket_create_time").trim());
				} catch (IllegalArgumentException e) {
					ticket_create_time=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入票券成立時間!");
				}
				
				String ticket_resale_status = req.getParameter("ticket_resale_status").trim();
				if (ticket_resale_status == null || ticket_resale_status.trim().length() == 0) {
					errorMsgs.add("票券轉讓狀態請勿空白");
				}else if(ticket_resale_status.length()>12) {
					errorMsgs.add("票券轉讓狀態請勿超過12碼");
				}
				
				Integer ticket_resale_price = null;
				try {
					ticket_resale_price = new Integer(req.getParameter("ticket_resale_price").trim());
				} catch (NumberFormatException e) {
					ticket_resale_price = 0;
					errorMsgs.add("轉讓價格請填數字.");
				}
				
				String is_from_resale = req.getParameter("is_from_resale").trim();
				if (is_from_resale == null || is_from_resale.trim().length() == 0) {
					errorMsgs.add("是否來自轉讓請勿空白");
				}else if(is_from_resale.length()>3) {
					errorMsgs.add("是否來自轉讓請勿超過3碼");
				}
				
				TicketVO tVO = new TicketVO();
				tVO.setTicket_no(ticket_no);
				tVO.setTicarea_no(ticarea_no);
				tVO.setTicket_order_no(ticket_order_no);
				tVO.setMember_no(member_no);
				tVO.setTicket_status(ticket_status);
				tVO.setTicket_create_time(ticket_create_time);
				tVO.setTicket_resale_status(ticket_resale_status);
				tVO.setTicket_resale_price(ticket_resale_price);
				tVO.setIs_from_resale(is_from_resale);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("tVO", tVO); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/ticket/update_ticket_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				TicketService tSvc = new TicketService();
				tVO = tSvc.updateTicket(ticket_no, ticarea_no, ticket_order_no, member_no, ticket_status, ticket_create_time, ticket_resale_status, ticket_resale_price, is_from_resale);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("tVO", tVO); 
				String url = "/ticket/listOneTicket.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/ticket/update_ticket_input.jsp");
				failureView.forward(req, res);
			}
		}
        if ("insert".equals(action)) {   
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String ticarea_no = req.getParameter("ticarea_no");
				if (ticarea_no == null || ticarea_no.trim().length() == 0) {
					errorMsgs.add("座位區編號: 請勿空白");
				} else if(!ticarea_no.trim().equals("E000101A01")) { 
					errorMsgs.add("座位區編號: 目前只能是E000101A01");
	            }
				
				String ticket_order_no = req.getParameter("ticket_order_no");
				if (ticket_order_no == null || ticket_order_no.trim().length() == 0) {
					errorMsgs.add("訂票訂單編號: 請勿空白");
				} else if(!ticket_order_no.trim().equals("TO_20181225_000001")) { 
					errorMsgs.add("訂票訂單編號 目前只能是TO_20181225_000001");
	            }
				
				String member_no = req.getParameter("member_no");
				if (member_no == null || member_no.trim().length() == 0) {
					errorMsgs.add("會員編號: 請勿空白");
				} else if(!member_no.trim().equals("M000001")) { 
					errorMsgs.add("會員編號: 目前只能是M000001");
	            }
				
				String ticket_status = req.getParameter("ticket_status").trim();
				if (ticket_status == null || ticket_status.trim().length() == 0) {
					errorMsgs.add("票券狀態請勿空白");
				}else if(ticket_status.length()>9) {
					errorMsgs.add("票券狀態請勿超過9碼");
				}
				
				java.sql.Timestamp ticket_create_time = null;
				try {
					ticket_create_time = java.sql.Timestamp.valueOf(req.getParameter("ticket_create_time").trim());
				} catch (IllegalArgumentException e) {
					ticket_create_time=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入票券成立時間!");
				}
				
				String ticket_resale_status = req.getParameter("ticket_resale_status").trim();
				if (ticket_resale_status == null || ticket_resale_status.trim().length() == 0) {
					errorMsgs.add("票券轉讓狀態請勿空白");
				}else if(ticket_resale_status.length()>12) {
					errorMsgs.add("票券轉讓狀態請勿超過12碼");
				}
				
				Integer ticket_resale_price = null;
				try {
					ticket_resale_price = new Integer(req.getParameter("ticket_resale_price").trim());
				} catch (NumberFormatException e) {
					ticket_resale_price = 0;
					errorMsgs.add("轉讓價格請填數字.");
				}
				
				String is_from_resale = req.getParameter("is_from_resale").trim();
				if (is_from_resale == null || is_from_resale.trim().length() == 0) {
					errorMsgs.add("是否來自轉讓請勿空白");
				}else if(is_from_resale.length()>3) {
					errorMsgs.add("是否來自轉讓請勿超過3碼");
				}
				
				TicketVO tVO = new TicketVO();
				tVO.setTicarea_no(ticarea_no);
				tVO.setTicket_order_no(ticket_order_no);
				tVO.setMember_no(member_no);
				tVO.setTicket_status(ticket_status);
				tVO.setTicket_create_time(ticket_create_time);
				tVO.setTicket_resale_status(ticket_resale_status);
				tVO.setTicket_resale_price(ticket_resale_price);
				tVO.setIs_from_resale(is_from_resale);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("tVO", tVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/ticket/addTicket.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				TicketService tSvc = new TicketService();
				tVO = tSvc.addTicket(ticarea_no, ticket_order_no, member_no, ticket_status, ticket_create_time, ticket_resale_status, ticket_resale_price, is_from_resale);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/ticket/listAllTicket.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/ticket/addTicket.jsp");
				failureView.forward(req, res);
			}
		}
        if ("delete".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String ticket_no = req.getParameter("ticket_no");
				
				/***************************2.開始刪除資料***************************************/
				TicketService tSvc = new TicketService();
				tSvc.deleteTicket(ticket_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/ticket/listAllTicket.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/ticket/listAllTicket.jsp");
				failureView.forward(req, res);
			}
		}
		
		
	}

}
