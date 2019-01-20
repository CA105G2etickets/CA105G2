package com.administrator.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.administrator.model.*;
import com.permission.model.PermissionVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class AdministratorServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("find_By_Account".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			HttpSession session = req.getSession();
			try {
				//***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String administrator_account = req.getParameter("administrator_account");
				String administrator_password = req.getParameter("administrator_password");
				if (administrator_account == null || (administrator_account.trim()).length() == 0) {
					errorMsgs.add("請輸入管理員帳號");
				}
				if (administrator_password == null || (administrator_password.trim()).length() == 0) {
					errorMsgs.add("請輸入管理員密碼");
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/login_back-end.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料,並準備轉交(Send the Success view)*****************************************/
				AdministratorService administratorService = new AdministratorService();
				AdministratorVO administratorVO = administratorService.findByAccount(administrator_account);
				if (administratorVO == null) {
					errorMsgs.add("無此帳號");
				}
				// Send the use back to the form, if there were errors
				if(administrator_password.equals(administratorVO.getAdministrator_password())){
					session.setAttribute("administratorVO", administratorVO);
				} else {
					errorMsgs.add("輸入的密碼有誤");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/login_back-end.jsp");
					failureView.forward(req, res);
					return;
				}
				
				String url = "/backend/index.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/login_back-end.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if("administrator_Logout".equals(action)) {
			try {
				//***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				req.getSession().invalidate();
				
				/***************************2.開始查詢資料,並準備轉交(Send the Success view)*****************************************/
				String url = "/CA105G2/backend/index.jsp";
				res.sendRedirect(url);
				
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if ("getOne_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("administrator_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入管理員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/administrator/allAdministrator.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String administrator_no = null;
				try {
					administrator_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("管理員編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/administrator/allAdministrator.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				AdministratorService administratorService = new AdministratorService();
				AdministratorVO administratorVO = administratorService.getOneAdministrator(administrator_no);
				if (administratorVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/administrator/allAdministrator.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("administratorVO", administratorVO); // 資料庫取出的empVO物件,存入req
				String url = "/backend/administrator/listOneAdministrator.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/administrator/allAdministrator.jsp");
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
				String administrator_no = new String(req.getParameter("administrator_no"));
				
				/***************************2.開始查詢資料****************************************/
				AdministratorService administratorService = new AdministratorService();
				AdministratorVO administratorVO = administratorService.getOneAdministrator(administrator_no);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("administratorVO", administratorVO);         // 資料庫取出的empVO物件,存入req
				String url = "/backend/administrator/update_administrator_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/administrator/allAdministrator.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String administrator_no = new String(req.getParameter("administrator_no").trim());
				
				String administrator_name = req.getParameter("administrator_name");
				if (administrator_name == null || administrator_name.trim().length() == 0) {
					errorMsgs.add("姓名請勿空白");
				}
				
				String administrator_account = req.getParameter("administrator_account").trim();
				if (administrator_account == null || administrator_account.trim().length() == 0) {
					errorMsgs.add("帳號請勿空白");
				}	
				
				String administrator_password = req.getParameter("administrator_password").trim();
				if (administrator_password == null || administrator_password.trim().length() == 0) {
					errorMsgs.add("密碼請勿空白");
				}	
				
				Timestamp creation_date = Timestamp.valueOf(req.getParameter("creation_date"));
				
				byte[] administrator_picture = null;
				Part part = req.getPart("administrator_picture");
				
				if (part.getSize() != 0) {
					try {
						String uploadFileName = part.getSubmittedFileName();
						if (uploadFileName != null && part.getContentType() != null) {
							InputStream in = part.getInputStream();
							administrator_picture = new byte[in.available()];
							in.read(administrator_picture);
							in.close();
						}
					} catch (FileNotFoundException e) {
						errorMsgs.add("找不到檔案");
					}
				}
				
				String administrator_status = req.getParameter("administrator_status").trim();
				if (administrator_status == null || administrator_status.trim().length() == 0) {
					errorMsgs.add("狀態請勿空白");
				}
				
				AdministratorVO administratorVO = new AdministratorVO();
				administratorVO.setAdministrator_no(administrator_no);
				administratorVO.setAdministrator_name(administrator_name);
				administratorVO.setAdministrator_account(administrator_account);
				administratorVO.setAdministrator_password(administrator_password);
				administratorVO.setCreation_date(creation_date);
				administratorVO.setAdministrator_picture(administrator_picture);
				administratorVO.setAdministrator_status(administrator_status);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("administratorVO", administratorVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/administrator/update_administrator_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				AdministratorService administratorService = new AdministratorService();
				administratorService.updateAdministrator(administrator_no, administrator_name, administrator_account, administrator_password, creation_date, administrator_picture, administrator_status);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("administratorVO", administratorVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/backend/administrator/listOneAdministrator.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/administrator/update_administrator_input.jsp");
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
				String administrator_name = req.getParameter("administrator_name");
				if (administrator_name == null || administrator_name.trim().length() == 0) {
					errorMsgs.add("姓名請勿空白");
				}
				
				String administrator_account = req.getParameter("administrator_account").trim();
				if (administrator_account == null || administrator_account.trim().length() == 0) {
					errorMsgs.add("帳號請勿空白");
				}	
				
				String administrator_password = req.getParameter("administrator_password").trim();
				if (administrator_password == null || administrator_password.trim().length() == 0) {
					errorMsgs.add("密碼請勿空白");
				}	
				
				byte[] administrator_picture = null;
				Part part = req.getPart("administrator_picture");
				try {
					String uploadFileName = part.getSubmittedFileName();
					if (uploadFileName != null && part.getContentType() != null) {
						InputStream in = part.getInputStream();
						administrator_picture = new byte[in.available()];
						in.read(administrator_picture);
						in.close();
					}
				} catch (FileNotFoundException e) {
					errorMsgs.add("找不到檔案");
				}
				if (part.getSize() == 0) {
					errorMsgs.add("無圖片檔案");
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
				}
				
				String administrator_status = "normal";
				
				AdministratorVO administratorVO = new AdministratorVO();
				administratorVO.setAdministrator_name(administrator_name);
				administratorVO.setAdministrator_account(administrator_account);
				administratorVO.setAdministrator_password(administrator_password);
//				administratorVO.setCreation_date(creation_date);
				administratorVO.setAdministrator_picture(administrator_picture);
				administratorVO.setAdministrator_status(administrator_status);

				/****************************接收權限請求參數******************************/
				String[] permission = req.getParameterValues("permission");
				
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("administratorVO", administratorVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/administrator/addAdministrator.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				AdministratorService administratorService = new AdministratorService();
				administratorVO = administratorService.addAdministrator(administrator_name, administrator_account, administrator_password, administrator_picture, administrator_status);				
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/backend/administrator/allAdministrator.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);
				for(int i = 0; i < permission.length; i++) {
		            System.out.print(permission[i] + " "); 
				PermissionVO permissionVO = new PermissionVO();
				permissionVO.setAdministrator_no(administratorVO.getAdministrator_no());
				permissionVO.setPermission_list_no(permission[i]);
				}
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/administrator/addAdministrator.jsp");
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
				String administrator_no = new String(req.getParameter("administrator_no"));
				
				/***************************2.開始刪除資料***************************************/
				AdministratorService administratorService = new AdministratorService();
				administratorService.deleteAdministrator(administrator_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/backend/administrator/allAdministrator.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/administrator/allAdministrator.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
