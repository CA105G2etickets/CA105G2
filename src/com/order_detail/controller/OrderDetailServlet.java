package com.order_detail.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.order_detail.model.*;
import com.order_history.model.OrderHistoryService;
import com.order_history.model.OrderHistoryVO;

public class OrderDetailServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getAll_OrderDetail_For_A_OrderNo".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String str = req.getParameter("order_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入訂單編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/order_detail/listAllOrderDetail.jsp");
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
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/order_detail/listAllOrderDetail.jsp");
					failureView.forward(req, res);
					return;
				}
				
				OrderDetailService orderDetailSvc = new OrderDetailService();
				List<OrderDetailVO> orderDetailVO = (List<OrderDetailVO>) orderDetailSvc.findByOrderNo(order_no);

				if (orderDetailVO == null) {
					errorMsgs.add("查無資料");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/order_detail/listAllOrderDetail.jsp");
					failureView.forward(req, res);
					return;
				}
				
				req.setAttribute("orderDetailVO", orderDetailVO);
				String url = "/backend/order_detail/AllOrderDetailOfAOrderNo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			}  catch (Exception e) {
				errorMsgs.add("無法取得資料：" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/order_detail/listAllOrderDetail.jsp");
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
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/order_detail/listAllOrderDetail.jsp");
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
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/order_detail/listAllOrderDetail.jsp");
					failureView.forward(req, res);
					return;
				}
				
				String goods_no = null;
				try {
					goods_no = new String(str);				
				} catch (Exception e) {
					errorMsgs.add("商品編號格式不正確");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/order_detail/listAllOrderDetail.jsp");
					failureView.forward(req, res);
					return;
				}
				OrderDetailService orderDetailSvc = new OrderDetailService();
				OrderDetailVO orderDetailVO = orderDetailSvc.getOneOrderDetail(order_no, goods_no);
				if (orderDetailVO == null) {
					errorMsgs.add("查無資料");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/order_detail/listAllOrderDetail.jsp");
					failureView.forward(req, res);
					return;
				}
				
				req.setAttribute("orderDetailVO", orderDetailVO);
				String url = "/backend/order_detail/listOneOrderDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			}  catch (Exception e) {
				errorMsgs.add("無法取得資料：" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/order_detail/listAllOrderDetail.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String order_no = new  String(req.getParameter("order_no"));
				String goods_no = new  String(req.getParameter("goods_no"));
				
				OrderDetailService orderDetailSvc = new OrderDetailService();
				OrderDetailVO orderDetailVO = orderDetailSvc.getOneOrderDetail(order_no, goods_no);
				
				req.setAttribute("orderDetailVO", orderDetailVO);
				String url = "/backend/order_detail/update_OrderDetail_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料：" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/order_detail/listAllOrderDetail.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String order_no = new String(req.getParameter("order_no").trim());
				String goods_no = new String(req.getParameter("goods_no").trim());
				Double goods_bonus = null;
				try {
					goods_bonus = new Double(req.getParameter("goods_bonus").trim());
				} catch (NumberFormatException e) {
					goods_bonus = 0.0;
					errorMsgs.add("請填入實際交易金額。");
				}
				Double goods_pc = null;
				try {
					goods_pc = new Double(req.getParameter("goods_pc").trim());
				} catch (NumberFormatException e) {
					goods_pc = 0.0;
					errorMsgs.add("請填入商品數量。");
				}
				
								
				OrderDetailVO orderDetailVO = new OrderDetailVO();
				orderDetailVO.setOrder_no(order_no);
				orderDetailVO.setGoods_no(goods_no);
				orderDetailVO.setGoods_bonus(goods_bonus);
				orderDetailVO.setGoods_pc(goods_pc);
	
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("orderDetailVO", orderDetailVO); 
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/order_detail/update_OrderDetail_input.jsp");
					failureView.forward(req, res);
					return;
				}
				
				OrderDetailService orderDetailSvc = new OrderDetailService();
				orderDetailVO = orderDetailSvc.updateOrderDetail(goods_bonus, goods_pc, order_no, goods_no);
			
				req.setAttribute("orderDetailVO", orderDetailVO); 
				String url = "/backend/order_detail/listAllOrderDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗："+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/order_detail/update_OrderDetail_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String order_no = new String(req.getParameter("order_no").trim());
				String goods_no = new String(req.getParameter("goods_no").trim());
				
				Double goods_bonus = null;
				try {
					goods_bonus = new Double(req.getParameter("goods_bonus").trim());
				} catch (NumberFormatException e) {
					goods_bonus = 0.0;
					errorMsgs.add("請填入實際交易金額。");
				}
				Double goods_pc = null;
				try {
					goods_pc = new Double(req.getParameter("goods_pc").trim());
				} catch (NumberFormatException e) {
					goods_pc = 0.0;
					errorMsgs.add("請填入商品數量。");
				}
				
				OrderDetailVO orderDetailVO = new OrderDetailVO();
				orderDetailVO.setGoods_no(goods_no);
				orderDetailVO.setGoods_bonus(goods_bonus);
				orderDetailVO.setGoods_pc(goods_pc);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("orderDetailVO", orderDetailVO); 
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/order_detail/addOrderDetail.jsp");
					failureView.forward(req, res);
					return;
				}
				
				OrderDetailService orderDetailSvc = new OrderDetailService();
				orderDetailVO = orderDetailSvc.addOrderDetail(order_no, goods_no, goods_bonus, goods_pc);
				
				
				String url = "/backend/order_detail/listAllOrderDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/order_detail/addOrderDetail.jsp");
				failureView.forward(req, res);
			}		
		}
		
		if ("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				String order_no = new String(req.getParameter("order_no"));
				String goods_no = new String(req.getParameter("goods_no"));
				OrderDetailService orderDetailSvc = new OrderDetailService();
				orderDetailSvc.deleteOrderDetail(order_no, goods_no);
				
				String url = "/backend/order_detail/listAllOrderDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/order_detail/listAllOrderDetail.jsp");
				failureView.forward(req, res);
			}
		}
		
	}
}
