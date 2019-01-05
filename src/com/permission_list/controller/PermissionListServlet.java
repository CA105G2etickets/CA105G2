package com.permission_list.controller;

import java.io.*;
import java.sql.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.permission_list.model.*;

public class PermissionListServlet extends HttpServlet {

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
				String str = req.getParameter("permission_list_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入功能清單編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/permissionList/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String permission_list_no = null;
				try {
					permission_list_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("功能清單編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/permissionList/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				PermissionListService permissionListService = new PermissionListService();
				PermissionListVO permissionListVO = permissionListService.getOnePermissionList(permission_list_no);
				if (permissionListVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/permissionList/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("permissionListVO", permissionListVO); // 資料庫取出的empVO物件,存入req
				String url = "/backend/permissionList/listOnePermissionList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/permissionList/select_page.jsp");
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
				PermissionListService permissionListService = new PermissionListService();
				PermissionListVO permissionListVO = permissionListService.getOnePermissionList(permission_list_no);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("permissionListVO", permissionListVO);         // 資料庫取出的empVO物件,存入req
				String url = "/backend/permissionList/update_permissionList_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/permissionList/listAllPermissionList.jsp");
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
				String permission_list_no = new String(req.getParameter("permission_list_no").trim());
				
				String permission = req.getParameter("permission");
				if (permission == null || permission.trim().length() == 0) {
					errorMsgs.add("功能清單描述請勿空白");
				}
				
				PermissionListVO permissionListVO = new PermissionListVO();
				permissionListVO.setPermission_list_no(permission_list_no);
				permissionListVO.setPermission(permission);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("permissionListVO", permissionListVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/permissionList/update_permissionList_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				PermissionListService permissionListService = new PermissionListService();
				permissionListService.updatePermissionList(permission_list_no, permission);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("permissionListVO", permissionListVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/backend/permissionList/listOnePermissionList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/permissionList/update_permissionList_input.jsp");
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
				String permission = req.getParameter("permission");
				if (permission == null || permission.trim().length() == 0) {
					errorMsgs.add("功能清單描述請勿空白");
				}
				
				PermissionListVO permissionListVO = new PermissionListVO();
				permissionListVO.setPermission(permission);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("permissionListVO", permissionListVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/permissionList/addPermissionList.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				PermissionListService permissionListService = new PermissionListService();
				permissionListVO = permissionListService.addPermissionList(permission);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/backend/permissionList/listAllPermissionList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/permissionList/addPermissionList.jsp");
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
				String permission_list_no = new String(req.getParameter("permission_list_no"));
				
				/***************************2.開始刪除資料***************************************/
				PermissionListService permissionListService = new PermissionListService();
				permissionListService.deletePermissionList(permission_list_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/backend/permissionList/listAllPermissionList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/permissionList/listAllPermissionList.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
