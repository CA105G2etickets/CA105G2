package com.faq.controller;

import java.io.*;
import java.sql.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.faq.model.*;


public class FaqServlet extends HttpServlet {

//	public void doGet(HttpServletRequest req, HttpServletResponse res)
//			throws ServletException, IOException {
//		doPost(req, res);
//	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("faq_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入常見問題編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/faq/allFaq.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String faq_no = null;
				try {
					faq_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("常見問題編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/faq/allFaq.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				FaqService faqService = new FaqService();
				FaqVO faqVO = faqService.getOneFaq(faq_no);
				if (faqVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/faq/allFaq.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("faqVO", faqVO); // 資料庫取出的empVO物件,存入req
				String url = "/backend/faq/listOneFaq.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/faq/allFaq.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String faq_no = new String(req.getParameter("faq_no"));
				
				/***************************2.開始查詢資料****************************************/
				FaqService faqService = new FaqService();
				FaqVO faqVO = faqService.getOneFaq(faq_no);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("faqVO", faqVO);         // 資料庫取出的empVO物件,存入req
				String url = "/backend/faq/update_faq_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/faq/allFaq.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String faq_no = new String(req.getParameter("faq_no").trim());
				
				String question = req.getParameter("question");
				if (question == null || question.trim().length() == 0) {
					errorMsgs.add("問題內容請勿空白");
				}
				
				String answer = req.getParameter("answer").trim();
				if (answer == null || answer.trim().length() == 0) {
					errorMsgs.add("答案內容請勿空白");
				}	
				
				String faq_classification = req.getParameter("faq_classification").trim();
				if (faq_classification == null || faq_classification.trim().length() == 0) {
					errorMsgs.add("常見問題分類請勿空白");
				}	
				
				
				FaqVO faqVO = new FaqVO();
				faqVO.setFaq_no(faq_no);
				faqVO.setQuestion(question);
				faqVO.setAnswer(answer);
				faqVO.setFaq_classification(faq_classification);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("faqVO", faqVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/faq/update_faq_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				FaqService faqService = new FaqService();
				faqService.updateFaq(faq_no, question, answer, faq_classification);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("faqVO", faqVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/backend/faq/listOneFaq.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/faq/update_faq_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String question = req.getParameter("question");
				if (question == null || question.trim().length() == 0) {
					errorMsgs.add("問題內容請勿空白");
				}
				
				String answer = req.getParameter("answer").trim();
				if (answer == null || answer.trim().length() == 0) {
					errorMsgs.add("答案內容請勿空白");
				}	
				
				String faq_classification = req.getParameter("faq_classification").trim();
				if (faq_classification == null || faq_classification.trim().length() == 0) {
					errorMsgs.add("常見問題分類請勿空白");
				}

				FaqVO faqVO = new FaqVO();
				faqVO.setQuestion(question);
				faqVO.setAnswer(answer);
				faqVO.setFaq_classification(faq_classification);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("faqVO", faqVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/faq/addFaq.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				FaqService faqService = new FaqService();
				faqVO = faqService.addFaq(question, answer, faq_classification);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/backend/faq/allFaq.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/faq/addFaq.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String faq_no = new String(req.getParameter("faq_no"));
				
				/***************************2.開始刪除資料***************************************/
				FaqService faqService = new FaqService();
				faqService.deleteFaq(faq_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/backend/faq/allFaq.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/faq/allFaq.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
