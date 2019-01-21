package com.goods_qa.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.*;

import javax.servlet.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goods_qa.model.*;

public class GoodsQaServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("gfaq_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入問題編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/goodsqa/select_page.jsp");
					failureView.forward(req, res);
					return;
				}

				String gfaq_no = null;
				try {
					gfaq_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("問題編號格式不正確");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/goodsqa/select_page.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始查詢資料 *****************************************/
				GoodsQaService goodsqaSvc = new GoodsQaService();
				GoodsQaVO goodsQaVO = goodsqaSvc.getOneGoodsQa(gfaq_no);
				if (goodsQaVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/goodsqa/select_page.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("goodsQaVO", goodsQaVO); 
				String url = "/backend/goodsqa/listOneGoodsqa.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/goodsqa/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String gfaq_no = new String(req.getParameter("gfaq_no"));

				/*************************** 2.開始查詢資料 ****************************************/
				GoodsQaService goodsqaSvc = new GoodsQaService();
				GoodsQaVO goodsQaVO = goodsqaSvc.getOneGoodsQa(gfaq_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("goodsQaVO", goodsQaVO);
				String url = "/backend/goodsqa/update_goodsqa_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/goodsqa/listAllGoodsqa.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String gfaq_no = new String(req.getParameter("gfaq_no").trim());
				String goods_no = new String(req.getParameter("goods_no").trim());
				String member_no = new String(req.getParameter("member_no").trim());
				String administrator_no = new String(req.getParameter("administrator_no").trim());
				String questions_content = req.getParameter("questions_content").trim();
				if (questions_content == null || questions_content.trim().length() == 0) {
					errorMsgs.add("問題請勿空白");
				}
				String answer_content = req.getParameter("answer_content").trim();
				if (answer_content == null || answer_content.trim().length() == 0) {
					errorMsgs.add("回答請勿空白");
				}
				java.sql.Timestamp questions_date = null;
				try {
					questions_date = java.sql.Timestamp.valueOf(req.getParameter("questions_date").trim());
				} catch (IllegalArgumentException e) {
					questions_date = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入發問日期。");
				}

				java.sql.Timestamp answer_date = null;
				try {
					answer_date = java.sql.Timestamp.valueOf(req.getParameter("answer_date").trim());
				} catch (IllegalArgumentException e) {
					answer_date = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入回答日期。");
				}

				GoodsQaVO goodsQaVO = new GoodsQaVO();
				goodsQaVO.setGfaq_no(gfaq_no);
				goodsQaVO.setGoods_no(goods_no);
				goodsQaVO.setMember_no(member_no);
				goodsQaVO.setAdministrator_no(administrator_no);
				goodsQaVO.setQuestions_content(questions_content);
				goodsQaVO.setAnswer_content(answer_content);
				goodsQaVO.setQuestions_date(questions_date);
				goodsQaVO.setAnswer_date(answer_date);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("goodsQaVO", goodsQaVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/goodsqa/update_goodsqa_input.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始修改資料 *****************************************/
				GoodsQaService goodsQaSvc = new GoodsQaService();
				goodsQaVO = goodsQaSvc.updateGoodsQa(gfaq_no, goods_no, member_no, administrator_no, questions_content, answer_content, questions_date, answer_date);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("goodsQaVO", goodsQaVO);
				String url = "/backend/goodsqa/listOneGoodsqa.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/goodsqa/update_goodsqa_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String goods_no = new String(req.getParameter("goods_no").trim());
				String member_no = new String(req.getParameter("member_no").trim());
				String administrator_no = new String(req.getParameter("administrator_no").trim());
				String questions_content = req.getParameter("questions_content").trim();
				if (questions_content == null || questions_content.trim().length() == 0) {
					errorMsgs.add("問題請勿空白");
				}
				String answer_content = req.getParameter("answer_content").trim();
				if (answer_content == null || answer_content.trim().length() == 0) {
					errorMsgs.add("回答請勿空白");
				}
				java.sql.Timestamp questions_date = null;
				try {
					questions_date = java.sql.Timestamp.valueOf(req.getParameter("questions_date").trim());
				} catch (IllegalArgumentException e) {
					questions_date = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入發問日期。");
				}

				java.sql.Timestamp answer_date = null;
				try {
					answer_date = java.sql.Timestamp.valueOf(req.getParameter("answer_date").trim());
				} catch (IllegalArgumentException e) {
					answer_date = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入回答日期。");
				}

				GoodsQaVO goodsQaVO = new GoodsQaVO();
				goodsQaVO.setGoods_no(goods_no);
				goodsQaVO.setMember_no(member_no);
				goodsQaVO.setAdministrator_no(administrator_no);
				goodsQaVO.setQuestions_content(questions_content);
				goodsQaVO.setAnswer_content(answer_content);
				goodsQaVO.setQuestions_date(questions_date);
				goodsQaVO.setAnswer_date(answer_date);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("goodsQaVO", goodsQaVO); 
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/goodsqa/addGoodsqa.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				GoodsQaService goodsqaSvc = new GoodsQaService();
				goodsQaVO = goodsqaSvc.addGoodsQa(goods_no, member_no, administrator_no, questions_content,	answer_content, questions_date, answer_date);
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/frontend/goods/listOneGoods.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/goodsqa/addGoodsqa.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String gfaq_no = new String(req.getParameter("gfaq_no"));

				/*************************** 2.開始刪除資料 ***************************************/
				GoodsQaService goodsqaSvc = new GoodsQaService();
				goodsqaSvc.deleteGoodsQa(gfaq_no);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/backend/goodsqa/listAllGoodsqa.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/goodsqa/listAllGoodsqa.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
