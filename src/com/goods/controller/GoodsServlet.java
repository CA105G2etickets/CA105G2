package com.goods.controller;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.event_title.model.EventTitleService;
import com.event_title.model.EventTitleVO;
import com.goods.model.*;



@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class GoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
	public GoodsServlet() {
		super();
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { 

			String requestURL = req.getParameter("requestURL");
			
			Map<String, String> goodsErrorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("goodsErrorMsgs", goodsErrorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String goods_no = req.getParameter("goods_no");
			
				/*************************** 2.開始查詢資料 *****************************************/
				GoodsService goodsSvc = new GoodsService();
				GoodsVO goodsVO = goodsSvc.getOneGoods(goods_no);
				if (goodsVO == null) {
					goodsErrorMsgs.put("goods_no", "查無資料");
				}
				if (!goodsErrorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("goodsVO", goodsVO); 
				RequestDispatcher successView = req.getRequestDispatcher("/backend/goods/listOneGoods.jsp");
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				goodsErrorMsgs.put("Exception","無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
			return;
		}
		
		
		
		

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求
			
			String requestURL = req.getParameter("requestURL");
			Map<String, String> goodsErrorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("goodsErrorMsgs", goodsErrorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String goods_no = req.getParameter("goods_no");

				/*************************** 2.開始查詢資料 ****************************************/
				GoodsService goodsSvc = new GoodsService();
				GoodsVO goodsVO = goodsSvc.getOneGoods(goods_no);
				
				if (goodsVO == null) {
					goodsErrorMsgs.put("goods_no", "查無資料");
				}
				
				if (!goodsErrorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
					failureView.forward(req, res);
					return;
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("goodsVO", goodsVO); // 資料庫取出的empVO物件,存入req
				RequestDispatcher successView = req.getRequestDispatcher("/backend/goods/updateGoods.jsp");// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				goodsErrorMsgs.put("Exception","無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
			return;
		}

		
		
		
			else if ("updateGoods".equals(action)) { 

			Map<String, String> goodsErrorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("goodsErrorMsgs", goodsErrorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String goods_no = req.getParameter("goods_no");
				String goods_name = req.getParameter("goods_name");
				if (goods_name == null || goods_name.trim().length() == 0) {
					goodsErrorMsgs.put("goods_name", "請輸入商品名稱");
				}
				String evetit_no = req.getParameter("evetit_no");
				Integer goods_price = new Integer (req.getParameter("goods_price"));
				Integer forsales_a = new Integer (req.getParameter("forsales_a"));
				
				java.sql.Timestamp today = new java.sql.Timestamp(System.currentTimeMillis());
				java.sql.Timestamp launchdate = null;
				try {
					launchdate = java.sql.Timestamp.valueOf(req.getParameter("launchdate").trim());
				} catch (IllegalArgumentException e) {
					goodsErrorMsgs.put("launchdate", "請輸入上架日期。");
				}
				
				java.sql.Timestamp offdate = null;
				try {
					offdate = java.sql.Timestamp.valueOf(req.getParameter("offdate").trim());
				} catch (IllegalArgumentException e) {
					goodsErrorMsgs.put("offdate", "請輸入下架日期。");
				}
				String goods_status = req.getParameter("goods_status");
				String goods_introduction = req.getParameter("goods_introduction");
				
				byte[] goods_picture1 = null;
				String goods_picture1_status = req.getParameter("goods_picture1_status");
				if("noUpload".equals(goods_picture1_status)) {
						
					req.setAttribute("goods_picture1_status", "noUpload");					
				} else if ("yesUpload".equals(goods_picture1_status)){
					String saveDirectory1 = "/tempGoods";
					String realPath1 = getServletContext().getRealPath(saveDirectory1);
					File fileSaveDirectory1 = new File(realPath1);		
					if(!fileSaveDirectory1.exists()) {
						fileSaveDirectory1.mkdirs();
					}
					Part part1 = req.getPart("goods_picture1");
					DateFormat dateFormat1 = new SimpleDateFormat("yyyymmdd_hhmmss_");  
					String strToday1 = dateFormat1.format(today); 
					String submittedFileName1 = strToday1 + part1.getSubmittedFileName();

					if(submittedFileName1.length() != 0 && part1.getContentType() != null) {
						File fileHere1 = new File(fileSaveDirectory1, submittedFileName1);
						part1.write(fileHere1.toString());								
					}			
					
					req.setAttribute("goods_picture1_status", "alreadyUpload");						
					req.getSession().setAttribute("goods_picture1_path", req.getContextPath() + saveDirectory1 + "/" + submittedFileName1);									
				} else if ("alreadyUpload".equals(goods_picture1_status)){
					req.setAttribute("goods_picture1_status", "alreadyUpload");	
				}
			
				
				byte[] goods_picture2 = null;
				String goods_picture2_status = req.getParameter("goods_picture2_status");
				if("noUpload".equals(goods_picture2_status)) {
						
					req.setAttribute("goods_picture2_status", "noUpload");					
				} else if ("yesUpload".equals(goods_picture2_status)){
					String saveDirectory2 = "/tempGoods";
					String realPath2 = getServletContext().getRealPath(saveDirectory2);
					File fileSaveDirectory2 = new File(realPath2);		
					if(!fileSaveDirectory2.exists()) {
						fileSaveDirectory2.mkdirs();
					}
					Part part2 = req.getPart("goods_picture2");
					DateFormat dateFormat2 = new SimpleDateFormat("yyyymmdd_hhmmss_");  
					String strToday2 = dateFormat2.format(today); 
					String submittedFileName2 = strToday2 + part2.getSubmittedFileName();

					if(submittedFileName2.length() != 0 && part2.getContentType() != null) {
						File fileHere2 = new File(fileSaveDirectory2, submittedFileName2);
						part2.write(fileHere2.toString());								
					}			
					
					req.setAttribute("goods_picture2_status", "alreadyUpload");						
					req.getSession().setAttribute("goods_picture2_path", req.getContextPath() + saveDirectory2 + "/" + submittedFileName2);									
				} else if ("alreadyUpload".equals(goods_picture2_status)){
					req.setAttribute("goods_picture2_status", "alreadyUpload");	
				}

				byte[] goods_picture3 = null;
				String goods_picture3_status = req.getParameter("goods_picture3_status");
				if("noUpload".equals(goods_picture3_status)) {
						
					req.setAttribute("goods_picture3_status", "noUpload");					
				} else if ("yesUpload".equals(goods_picture3_status)){
					String saveDirectory3 = "/tempGoods";
					String realPath3 = getServletContext().getRealPath(saveDirectory3);
					File fileSaveDirectory3 = new File(realPath3);		
					if(!fileSaveDirectory3.exists()) {
						fileSaveDirectory3.mkdirs();
					}
					Part part3 = req.getPart("goods_picture3");
					DateFormat dateFormat3 = new SimpleDateFormat("yyyymmdd_hhmmss_");  
					String strToday3 = dateFormat3.format(today); 
					String submittedFileName3 = strToday3 + part3.getSubmittedFileName();

					if(submittedFileName3.length() != 0 && part3.getContentType() != null) {
						File fileHere3 = new File(fileSaveDirectory3, submittedFileName3);
						part3.write(fileHere3.toString());								
					}			
					
					req.setAttribute("goods_picture3_status", "alreadyUpload");						
					req.getSession().setAttribute("goods_picture3_path", req.getContextPath() + saveDirectory3 + "/" + submittedFileName3);									
				} else if ("alreadyUpload".equals(goods_picture3_status)){
					req.setAttribute("goods_picture3_status", "alreadyUpload");	
				}
				Integer favorite_count = 0;
				Integer goods_group_count = 0;
				Integer goods_want_count = 0;
				Integer goods_sales_count = 0;

//				String goods_group_count = req.getParameter("goods_group_count");
//				String setGoods_want_count = req.getParameter("setGoods_want_count");
//				String setGoods_sales_count = req.getParameter("setGoods_sales_count");

				GoodsVO goodsVO = new GoodsVO();
				goodsVO.setGoods_no(goods_no);
				goodsVO.setEvetit_no(evetit_no);
				goodsVO.setGoods_name(goods_name);
				goodsVO.setGoods_price(goods_price);
				goodsVO.setGoods_picture1(goods_picture1);
				goodsVO.setGoods_picture2(goods_picture2);
				goodsVO.setGoods_picture3(goods_picture3);
				goodsVO.setGoods_introduction(goods_introduction);
				goodsVO.setForsales_a(forsales_a);
				goodsVO.setFavorite_count(favorite_count);
				goodsVO.setGoods_status(goods_status);
				goodsVO.setLaunchdate(launchdate);
				goodsVO.setOffdate(offdate);
				goodsVO.setGoods_group_count(goods_group_count);
				goodsVO.setGoods_want_count(goods_want_count);
				goodsVO.setGoods_sales_count(goods_sales_count);

				if (!goodsErrorMsgs.isEmpty()) {
					req.setAttribute("goodsVO", goodsVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/goods/updateGoods.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				if(!"noUpload".equals(goods_picture1_status)) {
					String goods_picture1_path = (String) req.getSession().getAttribute("goods_picture1_path");				
					String goods_picture1_path_forUse = goods_picture1_path.replace(req.getContextPath(), "").replace("/", "\\");
					String realPath1 = getServletContext().getRealPath("/") + goods_picture1_path_forUse.substring(1);
					InputStream in = new FileInputStream(realPath1);
					ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
					int i;
					while ((i = in.read()) != -1)
						baos1.write(i);
					goods_picture1 = baos1.toByteArray();
					in.close();
					baos1.close();
					
				}
				goodsVO.setGoods_picture1(goods_picture1);
				
				if(!"noUpload".equals(goods_picture2_status)) {
					String goods_picture2_path = (String) req.getSession().getAttribute("goods_picture2_path");				
					String goods_picture2_path_forUse = goods_picture2_path.replace(req.getContextPath(), "").replace("/", "\\");
					String realPath2 = getServletContext().getRealPath("/") + goods_picture2_path_forUse.substring(1);
					InputStream in = new FileInputStream(realPath2);
					ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
					int i;
					while ((i = in.read()) != -1)
						baos2.write(i);
					goods_picture1 = baos2.toByteArray();
					in.close();
					baos2.close();
					
				}
				goodsVO.setGoods_picture2(goods_picture2);
				
				if(!"noUpload".equals(goods_picture3_status)) {
					String goods_picture3_path = (String) req.getSession().getAttribute("goods_picture3_path");				
					String goods_picture3_path_forUse = goods_picture3_path.replace(req.getContextPath(), "").replace("/", "\\");
					String realPath3 = getServletContext().getRealPath("/") + goods_picture3_path_forUse.substring(1);
					InputStream in = new FileInputStream(realPath3);
					ByteArrayOutputStream baos3 = new ByteArrayOutputStream();
					int i;
					while ((i = in.read()) != -1)
						baos3.write(i);
					goods_picture3 = baos3.toByteArray();
					in.close();
					baos3.close();
					
				}
				goodsVO.setGoods_picture3(goods_picture3);
				
				
				GoodsService goodsSvc = new GoodsService();
				goodsVO = goodsSvc.updateGoods(goods_no, evetit_no, goods_name, goods_price, goods_picture1,
						goods_picture2, goods_picture3, goods_introduction, forsales_a, favorite_count, goods_status, launchdate,
						offdate,goods_group_count,goods_group_count,goods_group_count); 
				req.setAttribute("goodsVO", goodsVO); 
				RequestDispatcher successView = req.getRequestDispatcher("/backend/goods/listAllGoods.jsp"); 
				successView.forward(req, res);

				req.getSession().removeAttribute("goods_picture1_path");
				req.getSession().removeAttribute("goods_picture2_path");
				req.getSession().removeAttribute("goods_picture3_path");
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				goodsErrorMsgs.put("Exception","修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/goods/updateGoods.jsp");
				failureView.forward(req, res);
			}
		}
 
		if ("insertGoods".equals(action)) { 

			Map<String, String> goodsErrorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("goodsErrorMsgs", goodsErrorMsgs);

			
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String evetit_no = req.getParameter("evetit_no");
				
				String goods_name = req.getParameter("goods_name");
				if (goods_name == null || goods_name.trim().length() == 0) {
					goodsErrorMsgs.put("goods_name", "請輸入商品名稱");
				}
				
				
				Integer goods_price = null;
				try {
					goods_price = new Integer(req.getParameter("goods_price"));
				} catch (NumberFormatException e) {
					goodsErrorMsgs.put("goods_price", "請輸入售價");
				}
//				Integer goods_price = new Integer (req.getParameter("goods_price"));
				
				 java.sql.Timestamp today = new  java.sql.Timestamp(System.currentTimeMillis());
				 
				
				 byte[] goods_picture1 = null;
					String goods_picture1_status = req.getParameter("goods_picture1_status");
					if("noUpload".equals(goods_picture1_status)) {
						
						req.setAttribute("goods_picture1_status", "noUpload");					
					} else if ("yesUpload".equals(goods_picture1_status)){
						String saveDirectory1 = "/tempGoods";
						String realPath1 = getServletContext().getRealPath(saveDirectory1);
						File fileSaveDirectory1 = new File(realPath1);		
						if(!fileSaveDirectory1.exists()) {
							fileSaveDirectory1.mkdirs();
						}
						Part part1 = req.getPart("goods_picture1");
						DateFormat dateFormat1 = new SimpleDateFormat("yyyymmdd_hhmmss_");  
						String strToday1 = dateFormat1.format(today); 
						String submittedFileName1 = strToday1 + part1.getSubmittedFileName();
						
						if(submittedFileName1.length() != 0 && part1.getContentType() != null) {
							File fileHere1 = new File(fileSaveDirectory1, submittedFileName1);
							part1.write(fileHere1.toString());								
						}			
						
						req.setAttribute("goods_picture1_status", "alreadyUpload");						
						req.getSession().setAttribute("goods_picture1_path", req.getContextPath() + saveDirectory1 + "/" + submittedFileName1);									
					} else if ("alreadyUpload".equals(goods_picture1_status)){
						req.setAttribute("goods_picture1_status", "alreadyUpload");	
					}
				 
				 
				
				byte[] goods_picture2 = null;
				String goods_picture2_status = req.getParameter("goods_picture2_status");
				if("noUpload".equals(goods_picture2_status)) {
					
					req.setAttribute("goods_picture2_status", "noUpload");					
				} else if ("yesUpload".equals(goods_picture2_status)){
					String saveDirectory2 = "/tempGoods";
					String realPath2 = getServletContext().getRealPath(saveDirectory2);
					File fileSaveDirectory2 = new File(realPath2);		
					if(!fileSaveDirectory2.exists()) {
						fileSaveDirectory2.mkdirs();
					}
					Part part2 = req.getPart("goods_picture2");
					DateFormat dateFormat2 = new SimpleDateFormat("yyyymmdd_hhmmss_");  
					String strToday2 = dateFormat2.format(today); 
					String submittedFileName2 = strToday2 + part2.getSubmittedFileName();
					
					if(submittedFileName2.length() != 0 && part2.getContentType() != null) {
						File fileHere2 = new File(fileSaveDirectory2, submittedFileName2);
						part2.write(fileHere2.toString());								
					}			
					
					req.setAttribute("goods_picture2_status", "alreadyUpload");						
					req.getSession().setAttribute("goods_picture2_path", req.getContextPath() + saveDirectory2 + "/" + submittedFileName2);									
				} else if ("alreadyUpload".equals(goods_picture2_status)){
					req.setAttribute("goods_picture2_status", "alreadyUpload");	
				}
				
				byte[] goods_picture3 = null;
				String goods_picture3_status = req.getParameter("goods_picture3_status");
				if("noUpload".equals(goods_picture3_status)) {
					
					req.setAttribute("goods_picture3_status", "noUpload");					
				} else if ("yesUpload".equals(goods_picture3_status)){
					String saveDirectory3 = "/tempGoods";
					String realPath3 = getServletContext().getRealPath(saveDirectory3);
					File fileSaveDirectory3 = new File(realPath3);		
					if(!fileSaveDirectory3.exists()) {
						fileSaveDirectory3.mkdirs();
					}
					Part part3 = req.getPart("goods_picture3");
					DateFormat dateFormat3 = new SimpleDateFormat("yyyymmdd_hhmmss_");  
					String strToday3 = dateFormat3.format(today); 
					String submittedFileName3 = strToday3 + part3.getSubmittedFileName();
					
					if(submittedFileName3.length() != 0 && part3.getContentType() != null) {
						File fileHere3 = new File(fileSaveDirectory3, submittedFileName3);
						part3.write(fileHere3.toString());								
					}			
					
					req.setAttribute("goods_picture3_status", "alreadyUpload");						
					req.getSession().setAttribute("goods_picture3_path", req.getContextPath() + saveDirectory3 + "/" + submittedFileName3);									
				} else if ("alreadyUpload".equals(goods_picture3_status)){
					req.setAttribute("goods_picture3_status", "alreadyUpload");	
				}
				String goods_introduction = req.getParameter("goods_introduction");
				Integer forsales_a = new Integer (req.getParameter("forsales_a"));
				String goods_status = req.getParameter("goods_status");
				

				java.sql.Timestamp launchdate = null;
				try {
					launchdate = java.sql.Timestamp.valueOf(req.getParameter("launchdate").trim());					
				} catch (IllegalArgumentException e) {
					launchdate = new java.sql.Timestamp(System.currentTimeMillis());
					goodsErrorMsgs.put("launchdate", "請輸入上架日期");
				}
				
				 java.sql.Timestamp offdate = null;
					try {
						offdate = java.sql.Timestamp.valueOf(req.getParameter("launchdate").trim());					
					} catch (IllegalArgumentException e) {
						offdate = new java.sql.Timestamp(System.currentTimeMillis());
						goodsErrorMsgs.put("offdate", "請輸入下架日期");
					}
				
			
//				Integer favorite_count = 0;
//				Integer goods_sales_count = 0;
//				Integer goods_group_count = 0;
//				
//				Integer goods_want_count = 0;
			
				if (!goodsErrorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/goods/addGoods.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
				
				String goods_picture1_path = (String) req.getSession().getAttribute("goods_picture1_path");				
				String goods_picture1_path_forUse = goods_picture1_path.replace(req.getContextPath(), "").replace("/", "\\");
				String realPath1 = getServletContext().getRealPath("/") + goods_picture1_path_forUse.substring(1);
		
				InputStream in = new FileInputStream(realPath1);
				ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
				int i;
				while ((i = in.read()) != -1)
					baos1.write(i);
				goods_picture1 = baos1.toByteArray();
				in.close();
				baos1.close();
				
				String goods_picture2_path = (String) req.getSession().getAttribute("goods_picture2_path");				
				String goods_picture2_path_forUse = goods_picture2_path.replace(req.getContextPath(), "").replace("/", "\\");
				String realPath2 = getServletContext().getRealPath("/") + goods_picture2_path_forUse.substring(1);
		
				InputStream in2 = new FileInputStream(realPath2);
				ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
				int x;
				while ((x = in.read()) != -1)
					baos2.write(x);
				goods_picture2 = baos2.toByteArray();
				in.close();
				baos2.close();
				
				String goods_picture3_path = (String) req.getSession().getAttribute("goods_picture3_path");				
				String goods_picture3_path_forUse = goods_picture3_path.replace(req.getContextPath(), "").replace("/", "\\");
				String realPath3 = getServletContext().getRealPath("/") + goods_picture3_path_forUse.substring(1);
		
				InputStream in3 = new FileInputStream(realPath3);
				ByteArrayOutputStream baos3 = new ByteArrayOutputStream();
				int z;
				while ((z = in.read()) != -1)
					baos3.write(z);
				goods_picture3 = baos3.toByteArray();
				in.close();
				baos3.close();

				/*************************** 2.開始修改資料 *****************************************/
				GoodsService goodsSvc = new GoodsService();
				GoodsVO goodsVO = new GoodsVO();
				goodsVO = goodsSvc.addGoods( evetit_no, goods_name, goods_price, goods_picture1,
						goods_picture2, goods_picture3, goods_introduction, forsales_a, goods_status, launchdate,
						offdate);
				req.getSession().removeAttribute("goods_picture1_path");
				req.getSession().removeAttribute("goods_picture2_path");
				req.getSession().removeAttribute("goods_picture3_path");

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("goodsVO", goodsVO); // 資料庫update成功後,正確的的empVO物件,存入req
				RequestDispatcher successView = req.getRequestDispatcher("/backend/goods/listAllGoods.jsp"); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
		
				goodsErrorMsgs.put("Exception","修改資料失敗:" );
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/goods/addGoods.jsp");
				failureView.forward(req, res);

		}

		if ("deleteGoods".equals(action)) { 
			
			String requestURL = req.getParameter("requestURL");
			Map<String, String> goodsErrorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("goodsErrorMsgs", goodsErrorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String goods_no = req.getParameter("goods_no");
				if (goods_no == null || goods_no.trim().length() == 0) {
					goodsErrorMsgs.put("goods_no", "找不到活動主題編號");
				}

				/*************************** 2.開始刪除資料 ***************************************/
				GoodsService goodsSvc = new GoodsService();
				goodsSvc.deleteGoods(goods_no);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				
				List<GoodsVO> goodsList = goodsSvc.getAll();
				req.setAttribute("goodsList", goodsList);
				RequestDispatcher successView = req.getRequestDispatcher(requestURL);
				successView.forward(req, res);
				
				
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				goodsErrorMsgs.put("Exception","刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
			return;
		}
		
		if ("listGoods_ByCompositeQuery".equals(action)) {
			
			Map<String, String> goodsErrorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("goodsErrorMsgs", goodsErrorMsgs);
			
			try {				
				/****************************** 1.將輸入資料轉為Map **************************************************/ 
				Map<String, String[]> map = req.getParameterMap();
				
				/****************************** 2.開始複合查詢 **************************************************/
				GoodsService goodsService = new GoodsService();
				List<GoodsVO> list  = goodsService.getAllLaunched(map);
				
				/****************************** 3.查詢完成,準備轉交 **************************************************/
				req.setAttribute("listGoods_ByCompositeQuery", list);
				
				RequestDispatcher successView = req.getRequestDispatcher("/frontend/goods/selectGoods.jsp");
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				goodsErrorMsgs.put("Exception", "無法取得資料 : " + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/goods/selectGoods.jsp");
				failureView.forward(req, res);
			}
			
		}
	
	}
}
