package com.ORDER_HISTORY.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.ORDER_HISTORY.model.*;

public class OrderHistoryServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_MemAllOrd".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String str = req.getParameter("member_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/OrderHistory/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				String member_no = null;
				try {
					member_no = new String(str);				
				} catch (Exception e) {
					errorMsgs.add("會員編號格式不正確");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/OrderHistory/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				OrderHistoryService orderHistorySvc = new OrderHistoryService();
				List<OrderHistoryVO> orderHistoryVO = (List<OrderHistoryVO>) orderHistorySvc.findByMemberNo(member_no);

				if (orderHistoryVO == null) {
					errorMsgs.add("查無資料");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/OrderHistory/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				req.setAttribute("orderHistoryVO", orderHistoryVO);
				String url = "/OrderHistory/oneMemberIsOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			}  catch (Exception e) {
				errorMsgs.add("無法取得資料：" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/OrderHistory/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
				
		if ("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String str = req.getParameter("order_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入訂單編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/OrderHistory/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				String order_no = null;
				try {
					order_no = new String(str);				
				} catch (Exception e) {
					errorMsgs.add("訂單編號格式不正確");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/OrderHistory/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				OrderHistoryService orderHistorySvc = new OrderHistoryService();
				OrderHistoryVO orderHistoryVO = orderHistorySvc.getOneOrderHistory(order_no);
				if (orderHistoryVO == null) {
					errorMsgs.add("查無資料");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/OrderHistory/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				req.setAttribute("orderHistoryVO", orderHistoryVO);
				String url = "/OrderHistory/listOneOrderHistory.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			}  catch (Exception e) {
				errorMsgs.add("無法取得資料：" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/OrderHistory/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String order_no = new  String(req.getParameter("order_no"));
				
				OrderHistoryService orderHistorySvc = new OrderHistoryService();
				OrderHistoryVO orderHistoryVO = orderHistorySvc.getOneOrderHistory(order_no);
				
				req.setAttribute("orderHistoryVO", orderHistoryVO);
				String url = "/OrderHistory/update_OrderHistory_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料：" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/OrderHistory/listAllOrderHistory.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String order_no = new String(req.getParameter("order_no").trim());
				String member_no = new String(req.getParameter("member_no").trim());
				Double order_price = null;
				
				try {
					order_price = new Double(req.getParameter("order_price").trim());
				} catch (NumberFormatException e) {
					order_price = 0.0;
					errorMsgs.add("訂單總金額請填金額。");
				}
				
				String pay_methods = new String(req.getParameter("pay_methods").trim());
				String shipping_methods = new String(req.getParameter("shipping_methods").trim());
				
				java.sql.Timestamp order_date = null;
				try {
					order_date = java.sql.Timestamp.valueOf(req.getParameter("order_date").trim());
				} catch (IllegalArgumentException e) {
					order_date = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入訂購日期。");
				}
				
				java.sql.Timestamp order_etd = null;
				try {
					order_etd = java.sql.Timestamp.valueOf(req.getParameter("order_etd").trim());
				} catch (IllegalArgumentException e) {
					order_etd = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入出貨日期。");
				}
				
				java.sql.Timestamp pickup_date = null;
				try {
					pickup_date = java.sql.Timestamp.valueOf(req.getParameter("pickup_date").trim());
				} catch (IllegalArgumentException e) {
					pickup_date = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入取貨日期。");
				}
				
				String receiver_add = req.getParameter("receiver_add");
				if (receiver_add == null || receiver_add.trim().length() == 0) {
					errorMsgs.add("送貨地址請勿空白。");
				}
				
				String receiver_name = req.getParameter("receiver_name");
				if (receiver_name == null || receiver_name.trim().length() == 0) {
					errorMsgs.add("收件人名稱請勿空白。");
				}
				
				String receiver_tel = req.getParameter("receiver_tel");
				if (receiver_tel == null || receiver_tel.trim().length() == 0) {
					errorMsgs.add("收件人電話請勿空白。");
				}
				
				String order_status = new String(req.getParameter("order_status").trim());
				
				OrderHistoryVO orderHistoryVO = new OrderHistoryVO();
				orderHistoryVO.setOrder_no(order_no);
				orderHistoryVO.setMember_no(member_no);
				orderHistoryVO.setOrder_price(order_price);
				orderHistoryVO.setPay_methods(pay_methods);;
				orderHistoryVO.setShipping_methods(shipping_methods);
				orderHistoryVO.setOrder_date(order_date);
				orderHistoryVO.setOrder_etd(order_etd);
				orderHistoryVO.setPickup_date(pickup_date);
				orderHistoryVO.setReceiver_add(receiver_add);
				orderHistoryVO.setReceiver_name(receiver_name);
				orderHistoryVO.setReceiver_tel(receiver_tel);
				orderHistoryVO.setOrder_status(order_status);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("orderHistoryVO", orderHistoryVO); 
					RequestDispatcher failureView = req.getRequestDispatcher("/OrderHistory/update_OrderHistory_input.jsp");
					failureView.forward(req, res);
					return;
				}
				
				OrderHistoryService orderHistorySvc = new OrderHistoryService();
				orderHistoryVO = orderHistorySvc.updateOrderHistory(member_no, order_price, 
						pay_methods, shipping_methods, order_date, order_etd, pickup_date, receiver_add, 
						receiver_name, receiver_tel, order_status, order_no);
			
				req.setAttribute("orderHistoryVO", orderHistoryVO); 
				String url = "/OrderHistory/listOneOrderHistory.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗："+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/OrderHistory/update_OrderHistory_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String member_no = new String(req.getParameter("member_no").trim());
				
				Double order_price = null;
				try {
					order_price = new Double(req.getParameter("order_price").trim());
				} catch (NumberFormatException e) {
					order_price = 0.0;
					errorMsgs.add("訂單總金額請填金額。");
				}
				
				String pay_methods = new String(req.getParameter("pay_methods").trim());
				String shipping_methods = new String(req.getParameter("shipping_methods").trim());
				
				java.sql.Timestamp order_date = null;
				try {
					order_date = java.sql.Timestamp.valueOf(req.getParameter("order_date").trim());
				} catch (IllegalArgumentException e) {
					order_date = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入訂購日期。");
				}
				
				java.sql.Timestamp order_etd = null;
				try {
					order_etd = java.sql.Timestamp.valueOf(req.getParameter("order_etd").trim());
				} catch (IllegalArgumentException e) {
					order_etd = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入出貨日期。");
				}
				
				java.sql.Timestamp pickup_date = null;
				try {
					pickup_date = java.sql.Timestamp.valueOf(req.getParameter("pickup_date").trim());
				} catch (IllegalArgumentException e) {
					pickup_date = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入取貨日期。");
				}
				
				String receiver_add = req.getParameter("receiver_add");
				if (receiver_add == null || receiver_add.trim().length() == 0) {
					errorMsgs.add("送貨地址請勿空白。");
				}
				
				String receiver_name = req.getParameter("receiver_name");
				if (receiver_name == null || receiver_name.trim().length() == 0) {
					errorMsgs.add("收件人名稱請勿空白。");
				}
				
				String receiver_tel = req.getParameter("receiver_tel");
				if (receiver_tel == null || receiver_tel.trim().length() == 0) {
					errorMsgs.add("收件人電話請勿空白。");
				}
				
				String order_status = new String(req.getParameter("order_status").trim());
				
				OrderHistoryVO orderHistoryVO = new OrderHistoryVO();
				orderHistoryVO.setMember_no(member_no);
				orderHistoryVO.setOrder_price(order_price);
				orderHistoryVO.setPay_methods(pay_methods);
				orderHistoryVO.setShipping_methods(shipping_methods);
				orderHistoryVO.setOrder_date(order_date);
				orderHistoryVO.setOrder_etd(order_etd);
				orderHistoryVO.setPickup_date(pickup_date);
				orderHistoryVO.setReceiver_add(receiver_add);
				orderHistoryVO.setReceiver_name(receiver_name);
				orderHistoryVO.setReceiver_tel(receiver_tel);
				orderHistoryVO.setOrder_status(order_status);
				

				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("orderHistoryVO", orderHistoryVO); 
					RequestDispatcher failureView = req.getRequestDispatcher("/OrderHistory/addOrderHistory.jsp");
					failureView.forward(req, res);
					return;
				}
				
				OrderHistoryService orderHistorySvc = new OrderHistoryService();
				orderHistoryVO = orderHistorySvc.addOrderHistory(member_no, order_price, pay_methods,
						shipping_methods, order_date, order_etd, pickup_date, receiver_add, receiver_name,
						receiver_tel, order_status);
				
				String url = "/OrderHistory/listAllOrderHistory.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/OrderHistory/addOrderHistory.jsp");
				failureView.forward(req, res);
			}		
		}
		
		if ("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				String order_no = new String(req.getParameter("order_no"));
				OrderHistoryService orderHistorySvc = new OrderHistoryService();
				orderHistorySvc.deleteOrderHistory(order_no);
				
				String url = "/OrderHistory/listAllOrderHistory.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/OrderHistory/listAllOrderHistory.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("listOrderHistory_ByCompositeQuery".equals(action)) { // 來自select_page.jsp的複合查詢請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				
				/***************************1.將輸入資料轉為Map**********************************/ 
				//採用Map<String,String[]> getParameterMap()的方法 
				//注意:an immutable java.util.Map 
				//Map<String, String[]> map = req.getParameterMap();
				HttpSession session = req.getSession();
				Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
				if (req.getParameter("whichPage") == null){
					HashMap<String, String[]> map1 = new HashMap<String, String[]>(req.getParameterMap());
					session.setAttribute("map",map1);
					map = map1;
				} 
				
				/***************************2.開始複合查詢***************************************/
				OrderHistoryService orderHistorySvc = new OrderHistoryService();
				List<OrderHistoryVO> list  = orderHistorySvc.getAll(map);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("listOrderHistory_ByCompositeQuery", list); // 資料庫取出的list物件,存入request
				RequestDispatcher successView = req.getRequestDispatcher("/OrderHistory/listOrderHistory_ByCompositeQuery.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/OrderHistory/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
	}
}
