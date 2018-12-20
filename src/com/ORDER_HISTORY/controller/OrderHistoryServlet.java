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
		
		if ("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String str = req.getParameter("orderNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入訂單編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/OrderHistory/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				String orderNo = null;
				try {
					orderNo = new String(str);				
				} catch (Exception e) {
					errorMsgs.add("訂單編號格式不正確");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/OrderHistory/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				OrderHistoryService orderHistorySvc = new OrderHistoryService();
				OrderHistoryVO orderHistoryVO = orderHistorySvc.getOneOrderHistory(orderNo);
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
				String orderNo = new  String(req.getParameter("orderNo"));
				
				OrderHistoryService orderHistorySvc = new OrderHistoryService();
				OrderHistoryVO orderHistoryVO = orderHistorySvc.getOneOrderHistory(orderNo);
				
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
				String orderNo = new String(req.getParameter("orderNo").trim());
				String memberNo = new String(req.getParameter("memberNo").trim());
				Double orderPrice = null;
				
				try {
					orderPrice = new Double(req.getParameter("orderPrice").trim());
				} catch (NumberFormatException e) {
					orderPrice = 0.0;
					errorMsgs.add("訂單總金額請填金額。");
				}
				
				String payMethods = new String(req.getParameter("payMethods").trim());
				String shippingMethods = new String(req.getParameter("shippingMethods").trim());
				
				java.sql.Timestamp orderDate = null;
				try {
					orderDate = java.sql.Timestamp.valueOf(req.getParameter("orderDate").trim());
				} catch (IllegalArgumentException e) {
					orderDate = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入訂購日期。");
				}
				
				java.sql.Timestamp orderEtd = null;
				try {
					orderEtd = java.sql.Timestamp.valueOf(req.getParameter("orderEtd").trim());
				} catch (IllegalArgumentException e) {
					orderEtd = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入出貨日期。");
				}
				
				java.sql.Timestamp pickupDate = null;
				try {
					pickupDate = java.sql.Timestamp.valueOf(req.getParameter("pickupDate").trim());
				} catch (IllegalArgumentException e) {
					pickupDate = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入取貨日期。");
				}
				
				String receiverAdd = req.getParameter("receiverAdd");
				if (receiverAdd == null || receiverAdd.trim().length() == 0) {
					errorMsgs.add("送貨地址請勿空白。");
				}
				
				String receiverName = req.getParameter("receiverName");
				if (receiverName == null || receiverName.trim().length() == 0) {
					errorMsgs.add("收件人名稱請勿空白。");
				}
				
				String receiverTel = req.getParameter("receiverTel");
				if (receiverTel == null || receiverTel.trim().length() == 0) {
					errorMsgs.add("收件人電話請勿空白。");
				}
				
				String orderStatus = new String(req.getParameter("orderStatus").trim());
				
				OrderHistoryVO orderHistoryVO = new OrderHistoryVO();
				orderHistoryVO.setOrderNo(orderNo);
				orderHistoryVO.setMemberNo(memberNo);
				orderHistoryVO.setOrderPrice(orderPrice);
				orderHistoryVO.setPayMethods(payMethods);;
				orderHistoryVO.setShippingMethods(shippingMethods);
				orderHistoryVO.setOrderDate(orderDate);
				orderHistoryVO.setOrderEtd(orderEtd);
				orderHistoryVO.setPickupDate(pickupDate);
				orderHistoryVO.setReceiverAdd(receiverAdd);
				orderHistoryVO.setReceiverName(receiverName);
				orderHistoryVO.setReceiverTel(receiverTel);
				orderHistoryVO.setOrderStatus(orderStatus);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("orderHistoryVO", orderHistoryVO); 
					RequestDispatcher failureView = req.getRequestDispatcher("/OrderHistory/update_OrderHistory_input.jsp");
					failureView.forward(req, res);
					return;
				}
				
				OrderHistoryService orderHistorySvc = new OrderHistoryService();
				orderHistoryVO = orderHistorySvc.updateOrderHistory(memberNo, orderPrice, 
						payMethods, shippingMethods, orderDate, orderEtd, pickupDate, receiverAdd, 
						receiverName, receiverTel, orderStatus, orderNo);
			
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
				String memberNo = new String(req.getParameter("memberNo").trim());
				
				Double orderPrice = null;
				try {
					orderPrice = new Double(req.getParameter("orderPrice").trim());
				} catch (NumberFormatException e) {
					orderPrice = 0.0;
					errorMsgs.add("訂單總金額請填金額。");
				}
				
				String payMethods = new String(req.getParameter("payMethods").trim());
				String shippingMethods = new String(req.getParameter("shippingMethods").trim());
				
				java.sql.Timestamp orderDate = null;
				try {
					orderDate = java.sql.Timestamp.valueOf(req.getParameter("orderDate").trim());
				} catch (IllegalArgumentException e) {
					orderDate = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入訂購日期。");
				}
				
				java.sql.Timestamp orderEtd = null;
				try {
					orderEtd = java.sql.Timestamp.valueOf(req.getParameter("orderEtd").trim());
				} catch (IllegalArgumentException e) {
					orderEtd = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入出貨日期。");
				}
				
				java.sql.Timestamp pickupDate = null;
				try {
					pickupDate = java.sql.Timestamp.valueOf(req.getParameter("pickupDate").trim());
				} catch (IllegalArgumentException e) {
					pickupDate = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入取貨日期。");
				}
				
				String receiverAdd = req.getParameter("receiverAdd");
				if (receiverAdd == null || receiverAdd.trim().length() == 0) {
					errorMsgs.add("送貨地址請勿空白。");
				}
				
				String receiverName = req.getParameter("receiverName");
				if (receiverName == null || receiverName.trim().length() == 0) {
					errorMsgs.add("收件人名稱請勿空白。");
				}
				
				String receiverTel = req.getParameter("receiverTel");
				if (receiverTel == null || receiverTel.trim().length() == 0) {
					errorMsgs.add("收件人電話請勿空白。");
				}
				
				String orderStatus = new String(req.getParameter("orderStatus").trim());
				
				OrderHistoryVO orderHistoryVO = new OrderHistoryVO();
				orderHistoryVO.setMemberNo(memberNo);
				orderHistoryVO.setOrderPrice(orderPrice);
				orderHistoryVO.setPayMethods(payMethods);;
				orderHistoryVO.setShippingMethods(shippingMethods);
				orderHistoryVO.setOrderDate(orderDate);
				orderHistoryVO.setOrderEtd(orderEtd);
				orderHistoryVO.setPickupDate(pickupDate);
				orderHistoryVO.setReceiverAdd(receiverAdd);
				orderHistoryVO.setReceiverName(receiverName);
				orderHistoryVO.setReceiverTel(receiverTel);
				orderHistoryVO.setOrderStatus(orderStatus);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("orderHistoryVO", orderHistoryVO); 
					RequestDispatcher failureView = req.getRequestDispatcher("/OrderHistory/addOrderHistory.jsp");
					failureView.forward(req, res);
					return;
				}
				
				OrderHistoryService orderHistorySvc = new OrderHistoryService();
				orderHistoryVO = orderHistorySvc.addOrderHistory(memberNo, orderPrice, payMethods,
						shippingMethods, orderDate, orderEtd, pickupDate, receiverAdd, receiverName,
						receiverTel, orderStatus);
				
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
				String orderNo = new String(req.getParameter("orderNo"));
				OrderHistoryService orderHistorySvc = new OrderHistoryService();
				orderHistorySvc.deleteOrderHistory(orderNo);
				
				String url = "/OrderHistory/listAllOrderHistory.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/OrderHistory/listAllOrderHistory.jsp");
				failureView.forward(req, res);
			}
		}
		
	}
}
