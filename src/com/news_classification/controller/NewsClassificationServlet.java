package com.news_classification.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.news_classification.model.NewsClassificationService;
import com.news_classification.model.NewsClassificationVO;

public class NewsClassificationServlet extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
//		if ("getOne_For_Display".equals(action)) {
//
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				String str = req.getParameter("newsClassificationNo");
//				if (str == null || (str.trim()).length() == 0) {
//					errorMsgs.add("請輸入公告分類編號");
//				}
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/backend/newsClassification/select_page.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//				
//				String newsClassificationNo = null;
//				try {
//					newsClassificationNo = new String(str);
//				} catch (Exception e) {
//					errorMsgs.add("公告分類編號格式不正確");
//				}
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/backend/newsClassification/select_page.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//				
//				NewsClassificationService newsClassificationService = new NewsClassificationService();
//				NewsClassificationVO newsClass = newsClassificationService.getOneNewsClassification(newsClassificationNo);
//				if (newsClass == null) {
//					errorMsgs.add("查無資料");
//				}
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/backend/newsClassification/select_page.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//				
//				req.setAttribute("newsClass", newsClass);
//				String url = "/backend/newsClassification/listOneNewsClassification.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);
//
//			} catch (Exception e) {
//				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/backend/newsClassification/select_page.jsp");
//				failureView.forward(req, res);
//			}
//		}
		
		
		if ("getOne_For_Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String newsClassificationNo = new String(req.getParameter("newsClassificationNo"));
				
				NewsClassificationService newsClassificationService = new NewsClassificationService();
				NewsClassificationVO newsClass = newsClassificationService.getOneNewsClassification(newsClassificationNo);
				
				req.setAttribute("newsClass", newsClass);
				String url = "/backend/newsClassification/update_newsClassification_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/newsClassification/listAllNewsClassification.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				String newsClassificationNo = req.getParameter("newsClassificationNo");
				if (newsClassificationNo == null || newsClassificationNo.trim().length() == 0) {
					errorMsgs.add("請填寫公告分類代碼");
				}
				
				String newsClassification = req.getParameter("newsClassification").trim();
				if (newsClassification == null || newsClassification.trim().length() == 0) {
					errorMsgs.add("請填寫公告分類內容");
				}	
				
				NewsClassificationVO newsClass = new NewsClassificationVO();
				newsClass.setNewsClassificationNo(newsClassificationNo);
				newsClass.setNewsClassification(newsClassification);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("newsClass", newsClass);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/newsClassification/update_newsClassification_input.jsp");
					failureView.forward(req, res);
					return;
				}
				
				NewsClassificationService newsClassificationService = new NewsClassificationService();
				newsClassificationService.updateNewsClassification(newsClassificationNo, newsClassification);
				
				req.setAttribute("newsClass", newsClass);
				String url = "/backend/newsClassification/listOneNewsClassification.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/newsClassification/update_newsClassification_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String newsClassificationNo = req.getParameter("newsClassificationNo");
				if (newsClassificationNo == null || newsClassificationNo.trim().length() == 0) {
					errorMsgs.add("請填寫公告分類代碼");
				}
				
				String newsClassification = req.getParameter("newsClassification").trim();
				if (newsClassification == null || newsClassification.trim().length() == 0) {
					errorMsgs.add("請填寫公告分類內容");
				}
				
				NewsClassificationVO newsClass = new NewsClassificationVO();
				newsClass.setNewsClassificationNo(newsClassificationNo);
				newsClass.setNewsClassification(newsClassification);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("newsClass", newsClass);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/newsClassification/update_newsClassification_input.jsp");
					failureView.forward(req, res);
					return;
				}
				
				NewsClassificationService newsClassificationService = new NewsClassificationService();
				newsClass = newsClassificationService.addNewsClassification(newsClassificationNo, newsClassification);
				
				String url = "/backend/newsClassification/listAllNewsClassification.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);				
				
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/newsClassification/addNewsClassification.jsp");
				failureView.forward(req, res);
			}
		}
		
		
//		if ("delete".equals(action)) {
//
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//	
//			try {
//				String newsClassificationNo = new String(req.getParameter("newsClassificationNo"));
//				
//				NewsClassificationService newsClassificationService = new NewsClassificationService();
//				newsClassificationService.deleteNewsClassification(newsClassificationNo);
//				
//				String url = "/backend/newsClassification/listAllNewsClassification.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);
//				
//			} catch (Exception e) {
//				errorMsgs.add("刪除資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/backend/newsClassification/listAllNewsClassification.jsp");
//				failureView.forward(req, res);
//			}
//		}
	}
}
