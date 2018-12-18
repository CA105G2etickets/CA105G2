package com.ticketorder.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ticketorder.model.TicketOrderService;
import com.ticketorder.model.TicketOrderVO;

/**
 * Servlet implementation class TicketOrderServlet
 */
//@WebServlet("/TicketOrderServlet")
public class TicketOrderServlet extends HttpServlet {
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
				String str = req.getParameter("ticket_order_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入訂票訂單編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/ticketorder/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				if ((!str.contains("_")) || str.length()>18) {
					errorMsgs.add("訂票訂單編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/ticketorder/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				TicketOrderService toSvc = new TicketOrderService();
				TicketOrderVO toVO = toSvc.getOneTicketOrder(str);
				if (toVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/ticketorder/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("TicketOrderVO", toVO); // 資料庫取出的empVO物件,存入req
				String url = "/ticketorder/listOneTicketOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/ticketorder/select_page.jsp");
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
				Integer empno = new Integer(req.getParameter("empno"));
				String ticket_order_no = req.getParameter("ticket_order_no");
				
				/***************************2.開始查詢資料****************************************/
				TicketOrderService toSvc = new TicketOrderService();
				TicketOrderVO toVO = toSvc.getOneTicketOrder(ticket_order_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("toVO", toVO);
				String url = "/ticketorder/update_ticketorder_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/ticketorder/listAllTicketOrder.jsp");
				failureView.forward(req, res);
			}
		}
//		if ("update".equals(action)) { 
//			
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//		
//			try {
//				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//				String ticket_order_no = req.getParameter("ticket_order_no").trim();
//				
//				String ename = req.getParameter("ename");
//				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				if (ename == null || ename.trim().length() == 0) {
//					errorMsgs.add("員工姓名: 請勿空白");
//				} else if(!ename.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//	            }
//				
//				String job = req.getParameter("job").trim();
//				if (job == null || job.trim().length() == 0) {
//					errorMsgs.add("職位請勿空白");
//				}	
//				
//				java.sql.Date hiredate = null;
//				try {
//					hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
//				} catch (IllegalArgumentException e) {
//					hiredate=new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.add("請輸入日期!");
//				}
//
//				Double sal = null;
//				try {
//					sal = new Double(req.getParameter("sal").trim());
//				} catch (NumberFormatException e) {
//					sal = 0.0;
//					errorMsgs.add("薪水請填數字.");
//				}
//
//				Double comm = null;
//				try {
//					comm = new Double(req.getParameter("comm").trim());
//				} catch (NumberFormatException e) {
//					comm = 0.0;
//					errorMsgs.add("獎金請填數字.");
//				}
//
//				Integer deptno = new Integer(req.getParameter("deptno").trim());
//
//				EmpVO empVO = new EmpVO();
//				empVO.setEmpno(empno);
//				empVO.setEname(ename);
//				empVO.setJob(job);
//				empVO.setHiredate(hiredate);
//				empVO.setSal(sal);
//				empVO.setComm(comm);
//				empVO.setDeptno(deptno);
//
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("empVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/emp/update_emp_input.jsp");
//					failureView.forward(req, res);
//					return; //程式中斷
//				}
//				
//				/***************************2.開始修改資料*****************************************/
//				EmpService empSvc = new EmpService();
//				empVO = empSvc.updateEmp(empno, ename, job, hiredate, sal,comm, deptno);
//				
//				/***************************3.修改完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("empVO", empVO); // 資料庫update成功後,正確的的empVO物件,存入req
//				String url = "/emp/listOneEmp.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
//				successView.forward(req, res);
//
//				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("修改資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/emp/update_emp_input.jsp");
//				failureView.forward(req, res);
//				
//				java.sql.Timestamp asdf = new java.sql.Timestamp(System.currentTimeMillis());
//			}
//		}
        if ("insert".equals(action)) {   
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String member_no = req.getParameter("member_no");
				if (member_no == null || member_no.trim().length() == 0) {
					errorMsgs.add("會員編號: 請勿空白");
				} else if(!member_no.trim().equals("M000001")) { 
					errorMsgs.add("會員編號: 目前只能是M000001");
	            }
				
				String ticarea_no = req.getParameter("ticarea_no");
				if (ticarea_no == null || ticarea_no.trim().length() == 0) {
					errorMsgs.add("座位區編號: 請勿空白");
				} else if(!ticarea_no.trim().equals("E000101A01")) { 
					errorMsgs.add("座位區編號: 目前只能是E000101A01");
	            }
				
				Integer total_price = null;
				try {
					total_price = new Integer(req.getParameter("total_price").trim());
				} catch (NumberFormatException e) {
					total_price = 0;
					errorMsgs.add("總價請填數字.");
				}
				
				Integer total_amount = null;
				try {
					total_amount = new Integer(req.getParameter("total_amount").trim());
				} catch (NumberFormatException e) {
					total_amount = 0;
					errorMsgs.add("總張數請填數字.");
				}
				
				java.sql.Timestamp ticket_order_time = null;
				try {
					ticket_order_time = java.sql.Timestamp.valueOf(req.getParameter("ticket_order_time").trim());
				} catch (IllegalArgumentException e) {
					ticket_order_time=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入訂票訂單成立時間!");
				}
				
				String payment_method = req.getParameter("payment_method").trim();
				if (payment_method == null || payment_method.trim().length() == 0) {
					errorMsgs.add("付款方式請勿空白");
				}
				
				String ticket_order_status = req.getParameter("ticket_order_status").trim();
				if (ticket_order_status == null || ticket_order_status.trim().length() == 0) {
					errorMsgs.add("訂票訂單狀態請勿空白");
				}				
				
				TicketOrderVO ticketorderVO = new TicketOrderVO();
				ticketorderVO.setMember_no(member_no);
				ticketorderVO.setTicarea_no(ticarea_no);
				ticketorderVO.setTotal_price(total_price);
				ticketorderVO.setTotal_amount(total_amount);
				ticketorderVO.setTicket_order_time(ticket_order_time);
				ticketorderVO.setPayment_method(payment_method);
				ticketorderVO.setTicket_order_status(ticket_order_status);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("toVO", ticketorderVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/ticketorder/addTicketOrder.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				TicketOrderService toSvc = new TicketOrderService();
				ticketorderVO = toSvc.addTicketOrder(member_no, ticarea_no, total_price, total_amount, ticket_order_time, payment_method, ticket_order_status);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/ticketorder/listAllTicketOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/ticketorder/addTicketOrder.jsp");
				failureView.forward(req, res);
			}
		}		
		
		
	}

}
