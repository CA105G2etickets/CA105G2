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
import com.resaleorder.model.DataUserWantsVO;
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
//		res.setHeader("Cache-Control", "no-store");
//		res.setHeader("Pragma", "no-cache");
//		res.setDateHeader("Expires", 0);
	
		if ("listResaleOrder_ByCompositeQuery".equals(action)) { // 來自select_page.jsp的複合查詢請求
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				Map<String, String[]> map = req.getParameterMap();

				TicketService tSvc = new TicketService();
				List<TicketVO> list = tSvc.getAll_map(map, "ticket_no");
				
				
				
				
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("listTicketsResaled_ByCompositeQuery", list); // 資料庫取出的list物件,存入request
				RequestDispatcher successView = req.getRequestDispatcher("/frontend/resaleorder/listTicketsResaled_ByCompositeQuery.jsp");
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("frontend/resaleorder/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOne_To_Buy".equals(action)) { // 來自select_page.jsp的複合查詢請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				
//				String resale_ordno = req.getParameter("resale_ordno");
				String ticket_no = req.getParameter("ticket_no");
				if (ticket_no == null || (ticket_no.trim()).length() == 0) {
					errorMsgs.add("請輸入ticket_no編號");
				}
				if (ticket_no.length()>18) {
					errorMsgs.add("ticket_no格式不正確");
				}
				if (this.containsHanScript(ticket_no)) {
					errorMsgs.add("ticket_no不可包含中文");
				}
				
//				ResaleOrderVO rvo = new ResaleOrderVO();
//				rvo.setTicketVO(ticketVO);
				
				Map<String, String[]> map = new TreeMap<String, String[]>();
				
				map.put("ticket_no", new String[] { ticket_no });
				map.put("ticket_no", new String[] { "SELLING1" });
				
				ResaleOrderService roSvc = new ResaleOrderService();
//				ResaleOrderVO roVO = roSvc.getOneResaleOrd(resale_ordno);
				req.setAttribute("roVO", roVO);
				System.out.println("roVO length="+roVO.toString());
				
				RequestDispatcher successView = req.getRequestDispatcher("/frontend/resaleorder/newPage.jsp");
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
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
