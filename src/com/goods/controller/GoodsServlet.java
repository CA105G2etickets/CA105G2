package com.goods.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.goods.model.*;
import com.member.model.MemberVO;

public class GoodsServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("goods_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入商品編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/goods/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String goods_no = null;
				try {
					goods_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("商品編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/goods/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				GoodsService goodsSvc = new GoodsService();
				GoodsVO goodsVO = goodsSvc.getOneGoods(goods_no);
				if (goodsVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/goods/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("goodsVO", goodsVO); // 資料庫取出的empVO物件,存入req
				String url = "/goods/listOneGoods.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/goods/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String goods_no = new String(req.getParameter("goods_no"));
				
				/*************************** 2.開始查詢資料 ****************************************/
				GoodsService goodsSvc = new GoodsService();
				GoodsVO goodsVO = goodsSvc.getOneGoods(goods_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("goodsVO", goodsVO); // 資料庫取出的empVO物件,存入req
				String url = "/goods/update_goods_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/goods/listAllGoods.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
//				String goods_no = new String(req.getParameter("goods_no").trim());
//				String evetit_no = new String(req.getParameter("evetit_no").trim());
								String evetit_no = req.getParameter("evetit_no").trim();
				if (evetit_no == null || evetit_no.trim().length() == 0) {
					errorMsgs.add("商品介紹請勿空白");
				}
				
				String goods_name = req.getParameter("goods_name");
				String goods_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,70}$";
				if (goods_name == null || goods_name.trim().length() == 0) {
					errorMsgs.add("商品名稱: 請勿空白");
				} else if (!goods_name.trim().matches(goods_nameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("商品名稱: 只能是中、英文字母、數字");
				}

				String goods_introduction = req.getParameter("goods_introduction").trim();
				if (goods_introduction == null || goods_introduction.trim().length() == 0) {
					errorMsgs.add("商品介紹請勿空白");
				}	

				Integer goods_price = null;
				try {
					goods_price = new Integer(req.getParameter("goods_price").trim());
				} catch (NumberFormatException e) {
					goods_price = 0;
					errorMsgs.add("請輸入售價");
				}

//				Integer forsales_a = null;
//				try {
//					goods_price = new Integer(req.getParameter("forsales_a").trim());
//				} catch (NumberFormatException e) {
//					goods_price = 0;
//					errorMsgs.add("折扣價填數字.");
//				}
//
//				Integer favorite_count = null;
//				try {
//					favorite_count = new Integer(req.getParameter("favorite_count").trim());
//				} catch (NumberFormatException e) {
//					favorite_count = 0;
//					errorMsgs.add("喜愛人數");
//				}
//				
//				String goods_status = req.getParameter("goods_status").trim();
//				if (goods_status == null || goods_status.trim().length() == 0) {
//					errorMsgs.add("商品狀態請勿空白");
//				}	

//				java.sql.Timestamp launchdate = null;
//				try {
//					launchdate = java.sql.Timestamp.valueOf(req.getParameter("launchdate").trim());
//				} catch (IllegalArgumentException e) {
//					launchdate = new java.sql.Timestamp(System.currentTimeMillis());
//					errorMsgs.add("請輸入上架日期!");
//				}
//
//				java.sql.Timestamp offdate = null;
//				try {
//					offdate = java.sql.Timestamp.valueOf(req.getParameter("offdate").trim());
//				} catch (IllegalArgumentException e) {
//					offdate = new java.sql.Timestamp(System.currentTimeMillis());
//					errorMsgs.add("請輸入下架日期!");
//				}

//				Integer goods_group_count = null;
//				try {
//					goods_group_count = new Integer(req.getParameter("goods_group_count").trim());
//				} catch (NumberFormatException e) {
//					goods_group_count = 0;
//					errorMsgs.add("開團統計");
//				}
//				
//				Integer goods_want_count = null;
//				try {
//					goods_want_count = new Integer(req.getParameter("goods_want_count").trim());
//				} catch (NumberFormatException e) {
//					goods_want_count = 0;
//					errorMsgs.add("許願人數");
//				}
//				
//				Integer goods_sales_count = null;
//				try {
//					goods_sales_count = new Integer(req.getParameter("goods_sales_count").trim());
//				} catch (NumberFormatException e) {
//					goods_sales_count = 0;
//					errorMsgs.add("銷售量");
//				}

				GoodsVO goodsVO = new GoodsVO();
				goodsVO.setEvetit_no(evetit_no);
				goodsVO.setGoods_name(goods_name);
				goodsVO.setGoods_price(goods_price);
//				goodsVO.setGoods_picture1(goods_picture1);
//				goodsVO.setGoods_picture2(goods_picture2);
//				goodsVO.setGoods_picture3(goods_picture3);
//				goodsVO.setGoods_introduction(goods_introduction);
//				goodsVO.setForsales_a(forsales_a);
//				goodsVO.setFavorite_count(favorite_count);
//				goodsVO.setGoods_status(goods_status);
//				goodsVO.setLaunchdate(launchdate);
//				goodsVO.setOffdate(offdate);
//				goodsVO.setGoods_group_count(goods_group_count);
//				goodsVO.setGoods_want_count(goods_want_count);
//				goodsVO.setGoods_sales_count(goods_sales_count);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("goodsVO", goodsVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/goods/update_goods_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				GoodsService goodsSvc = new GoodsService();
//				goodsVO = goodsSvc.updateGoods(goods_name, goods_nameReg, goods_nameReg, goods_price, null, null, null,
//						goods_nameReg, goods_price, goods_price, goods_nameReg, launchdate, offdate, goods_price,
//						goods_price, goods_price);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("goodsVO", goodsVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/goods/listOneGoods.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/goods/update_goods_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String goods_no = new String(req.getParameter("goods_no").trim());
				String evetit_no = req.getParameter("evetit_no");
				if (evetit_no == null || evetit_no.trim().length() == 0) {
					errorMsgs.add("活動編號 請勿空白");
				}
				String goods_name = req.getParameter("goods_name");
				
				if (goods_name == null || goods_name.trim().length() == 0)
					errorMsgs.add("商品名稱: 請勿空白");

				Integer goods_price = null;
				try {
					goods_price = new Integer(req.getParameter("goods_price").trim());
				} catch (NumberFormatException e) {
					goods_price = 0;
					errorMsgs.add("請輸入售價.");
				}
				String goods_introduction = req.getParameter("goods_introduction").trim();
				if (goods_introduction == null || goods_introduction.trim().length() == 0) {
					errorMsgs.add("商品介紹請勿空白");
				}

//				Integer forsales_a = null;
//				try {
//					goods_price = new Integer(req.getParameter("forsales_a").trim());
//				} catch (NumberFormatException e) {
//					goods_price = 0;
//					errorMsgs.add("折扣價填數字.");
//				}
//
//				Integer favorite_count = null;
//				try {
//					favorite_count = new Integer(req.getParameter("favorite_count").trim());
//				} catch (NumberFormatException e) {
//					favorite_count = 0;
//					errorMsgs.add("喜愛人數");
//				}
		

//				java.sql.Timestamp launchdate = null;
//				try {
//					launchdate = java.sql.Timestamp.valueOf(req.getParameter("launchdate").trim());
//				} catch (IllegalArgumentException e) {
//					launchdate = new java.sql.Timestamp(System.currentTimeMillis());
//					errorMsgs.add("請輸入開始日期!");
//				}
//
//				java.sql.Timestamp offdate = null;
//				try {
//					offdate = java.sql.Timestamp.valueOf(req.getParameter("offdate").trim());
//				} catch (IllegalArgumentException e) {
//					offdate = new java.sql.Timestamp(System.currentTimeMillis());
//					errorMsgs.add("請輸入結束日期!");
//				}

//				Integer goods_group_count = null;
//				try {
//					goods_group_count = new Integer(req.getParameter("goods_group_count").trim());
//				} catch (NumberFormatException e) {
//					goods_group_count = 0;
//					errorMsgs.add("開團統計");
//				}
//				
//				Integer goods_want_count = null;
//				try {
//					goods_want_count = new Integer(req.getParameter("goods_want_count").trim());
//				} catch (NumberFormatException e) {
//					goods_want_count = 0;
//					errorMsgs.add("許願人數");
//				}
//				
//				Integer goods_sales_count = null;
//				try {
//					goods_sales_count = new Integer(req.getParameter("goods_sales_count").trim());
//				} catch (NumberFormatException e) {
//					goods_sales_count = 0;
//					errorMsgs.add("銷售量");
//				}

				GoodsVO goodsVO = new GoodsVO();
				goodsVO.setGoods_no(goods_no);
				goodsVO.setEvetit_no(evetit_no);
				goodsVO.setGoods_name(goods_name);
				goodsVO.setGoods_price(goods_price);
//				goodsVO.setGoods_picture1(goods_picture1);
//				goodsVO.setGoods_picture2(goods_picture2);
//				goodsVO.setGoods_picture3(goods_picture3);
				goodsVO.setGoods_introduction(goods_introduction);
//				goodsVO.setForsales_a(forsales_a);
//				goodsVO.setFavorite_count(favorite_count);
//				goodsVO.setGoods_status(goods_status);
//				goodsVO.setLaunchdate(launchdate);
//				goodsVO.setOffdate(offdate);
//				goodsVO.setGoods_group_count(goods_group_count);
//				goodsVO.setGoods_want_count(goods_want_count);
//				goodsVO.setGoods_sales_count(goods_sales_count);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("goodsVO", goodsVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/goods/addGoods.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				GoodsService goodsSvc = new GoodsService();
//				goodsVO = goodsSvc.addGoods(goods_name,  goods_introduction, goods_price, null, null, null,
//						goods_introduction, goods_price, goods_price, goods_introduction, null, null, goods_price, goods_price,
//						goods_price);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
//				req.setAttribute("goodsVO", goodsVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/goods/listAllGoods.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/goods/addGoods.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String goods_no = new String(req.getParameter("goods_no"));

				/*************************** 2.開始刪除資料 ***************************************/
				GoodsService goodsSvc = new GoodsService();
				goodsSvc.deleteGoods(goods_no);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/goods/listAllGoods.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/goods/listAllGoods.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
