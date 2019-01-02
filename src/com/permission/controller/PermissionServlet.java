package com.permission.controller;

import java.io.*;
import java.sql.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.news.model.NewsService;
import com.news.model.NewsVO;

public class PermissionServlet extends HttpServlet {

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
				String str = req.getParameter("news_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入公告編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/news/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String news_no = null;
				try {
					news_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("公告編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/news/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				NewsService newsService = new NewsService();
				NewsVO newsVO = newsService.getOneNews(news_no);
				if (newsVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/news/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("newsVO", newsVO); // 資料庫取出的empVO物件,存入req
				String url = "/news/listOneNews.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/news/select_page.jsp");
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
				String permission_list_no = new String(req.getParameter("permission_list_no"));
				
				/***************************2.開始查詢資料****************************************/
				NewsService newsService = new NewsService();
				NewsVO newsVO = newsService.getOneNews(permission_list_no);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("newsVO", newsVO);         // 資料庫取出的empVO物件,存入req
				String url = "/news/update_news_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/news/listAllNews.jsp");
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
				String news_no = new String(req.getParameter("news_no").trim());
				
				String news_classification_no = req.getParameter("news_classification_no");
				if (news_classification_no == null || news_classification_no.trim().length() == 0) {
					errorMsgs.add("公告編號代碼請勿空白");
				}
				
				String news_title = req.getParameter("news_title").trim();
				if (news_title == null || news_title.trim().length() == 0) {
					errorMsgs.add("公告標題請勿空白");
				}	
				
				String news_content = req.getParameter("news_content").trim();
				if (news_content == null || news_content.trim().length() == 0) {
					errorMsgs.add("公告內容請勿空白");
				}	
				
				String administrator_no = req.getParameter("administrator_no").trim();
				if (administrator_no == null || administrator_no.trim().length() == 0) {
					errorMsgs.add("管理員編號請勿空白");
				}	
				
				
				NewsVO newsVO = new NewsVO();
				newsVO.setNews_no(news_no);
				newsVO.setNews_classification_no(news_classification_no);
				newsVO.setNews_title(news_title);
				newsVO.setNews_content(news_content);
//				newsVO.setAnnounce_date(ANNOUNCE_DATE);
				newsVO.setAdministrator_no(administrator_no);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("newsVO", newsVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/news/update_news_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				NewsService newsService = new NewsService();
				newsService.updateNews(news_no, news_classification_no, news_title, news_content, administrator_no);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("newsVO", newsVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/news/listOneNews.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/news/update_news_input.jsp");
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
				String news_classification_no = req.getParameter("news_classification_no");
				if (news_classification_no == null || news_classification_no.trim().length() == 0) {
					errorMsgs.add("公告編號代碼請勿空白");
				}
				
				String news_title = req.getParameter("news_title").trim();
				if (news_title == null || news_title.trim().length() == 0) {
					errorMsgs.add("公告標題請勿空白");
				}	
				
				String news_content = req.getParameter("news_content").trim();
				if (news_content == null || news_content.trim().length() == 0) {
					errorMsgs.add("公告內容請勿空白");
				}	
				
				String administrator_no = req.getParameter("administrator_no").trim();
				if (administrator_no == null || administrator_no.trim().length() == 0) {
					errorMsgs.add("管理員編號請勿空白");
				}

				NewsVO newsVO = new NewsVO();
				newsVO.setNews_classification_no(news_classification_no);
				newsVO.setNews_title(news_title);
				newsVO.setNews_content(news_content);
//				newsVO.setAnnounce_date(announce_date);
				newsVO.setAdministrator_no(administrator_no);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("newsVO", newsVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/news/addNews.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				NewsService newsService = new NewsService();
				newsVO = newsService.addNews(news_classification_no, news_title, news_content, administrator_no);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/news/listAllNews.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/news/addNews.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String news_no = new String(req.getParameter("news_no"));
				
				/***************************2.開始刪除資料***************************************/
				NewsService newsService = new NewsService();
				newsService.deleteNews(news_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/news/listAllNews.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/news/listAllNews.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
