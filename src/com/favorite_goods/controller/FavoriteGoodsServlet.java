package com.favorite_goods.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.favorite_goods.model.FavoriteGoodsService;
import com.favorite_goods.model.FavoriteGoodsVO;

public class FavoriteGoodsServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getAll_Goods_Of_A_Member".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String str = req.getParameter("member_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/favorite_goods/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				String member_no = null;
				try {
					member_no = new String(str);				
				} catch (Exception e) {
					errorMsgs.add("會員編號格式不正確");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/favorite_goods/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				FavoriteGoodsService favoriteGoodsSvc = new FavoriteGoodsService();
				List<FavoriteGoodsVO> favoriteGoodsVO = (List<FavoriteGoodsVO>) favoriteGoodsSvc.findByMemberNo(member_no);

				if (favoriteGoodsVO == null) {
					errorMsgs.add("查無資料");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/favorite_goods/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				req.setAttribute("favoriteGoodsVO", favoriteGoodsVO);
				String url = "/backend/favorite_goods/AllGoodsOfAMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			}  catch (Exception e) {
				errorMsgs.add("無法取得資料：" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/favorite_goods/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getAll_Member_Of_A_Goods".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String str = req.getParameter("goods_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入商品編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/favorite_goods/select_page.jsp");
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
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/favorite_goods/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				FavoriteGoodsService favoriteGoodsSvc = new FavoriteGoodsService();
				List<FavoriteGoodsVO> favoriteGoodsVO = (List<FavoriteGoodsVO>) favoriteGoodsSvc.findByGoodsNo(goods_no);

				if (favoriteGoodsVO == null) {
					errorMsgs.add("查無資料");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/favorite_goods/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				req.setAttribute("favoriteGoodsVO", favoriteGoodsVO);
				String url = "/backend/favorite_goods/AllMemberOfAGoods.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			}  catch (Exception e) {
				errorMsgs.add("無法取得資料：" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/favorite_goods/select_page.jsp");
				failureView.forward(req, res);
			}
		}
				
		if ("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String str = req.getParameter("member_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/favorite_goods/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				String member_no = null;
				try {
					member_no = new String(str);				
				} catch (Exception e) {
					errorMsgs.add("會員編號格式不正確");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/favorite_goods/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				FavoriteGoodsService favoriteGoodsSvc = new FavoriteGoodsService();
				FavoriteGoodsVO favoriteGoodsVO = favoriteGoodsSvc.getOneFavoriteGoods(member_no);
				if (favoriteGoodsVO == null) {
					errorMsgs.add("查無資料");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/favorite_goods/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				req.setAttribute("favoriteGoodsVO", favoriteGoodsVO);
				String url = "/backend/favorite_goods/listOneFavoriteGoods.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			}  catch (Exception e) {
				errorMsgs.add("無法取得資料：" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/favorite_goods/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String member_no = new  String(req.getParameter("member_no"));
				
				FavoriteGoodsService favoriteGoodsSvc = new FavoriteGoodsService();
				FavoriteGoodsVO favoriteGoodsVO = favoriteGoodsSvc.getOneFavoriteGoods(member_no);
				
				req.setAttribute("favoriteGoodsVO", favoriteGoodsVO);
				String url = "/backend/favorite_goods/update_FavoriteGoods_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料：" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/favorite_goods/listAllFavoriteGoods.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {

				String member_no = new String(req.getParameter("member_no").trim());
				String goods_no = new String(req.getParameter("goods_no").trim());
			
				FavoriteGoodsVO favoriteGoodsVO = new FavoriteGoodsVO();
				favoriteGoodsVO.setMember_no(member_no);
				favoriteGoodsVO.setGoods_no(goods_no);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("favoriteGoodsVO", favoriteGoodsVO); 
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/favorite_goods/update_FavoriteGoods_input.jsp");
					failureView.forward(req, res);
					return;
				}
				
				FavoriteGoodsService favoriteGoodsSvc = new FavoriteGoodsService();
				favoriteGoodsVO = favoriteGoodsSvc.updateFavoriteGoods(member_no, goods_no);
			
				req.setAttribute("favoriteGoodsVO", favoriteGoodsVO); 
				String url = "/backend/favorite_goods/listOneFavoriteGoods.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗："+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/favorite_goods/update_FavoriteGoods_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String member_no = new String(req.getParameter("member_no").trim());
				String goods_no = new String(req.getParameter("goods_no").trim());			
			
				FavoriteGoodsVO favoriteGoodsVO = new FavoriteGoodsVO();
				favoriteGoodsVO.setMember_no(member_no);
				favoriteGoodsVO.setGoods_no(goods_no);
			
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("favoriteGoodsVO", favoriteGoodsVO); 
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/favorite_goods/addFavoriteGoods.jsp");
					failureView.forward(req, res);
					return;
				}
				
				FavoriteGoodsService favoriteGoodsSvc = new FavoriteGoodsService();
				favoriteGoodsVO = favoriteGoodsSvc.addFavoriteGoods(member_no, goods_no);
				
				String url = "/backend/favorite_goods/listAllFavoriteGoods.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/favorite_goods/addFavoriteGoods.jsp");
				failureView.forward(req, res);
			}		
		}
		
		if ("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				String member_no = new String(req.getParameter("member_no"));
				String goods_no = new String(req.getParameter("goods_no"));
				FavoriteGoodsService favoriteGoodsSvc = new FavoriteGoodsService();
				favoriteGoodsSvc.deleteFavoriteGoods(member_no, goods_no);
				
				String url = "/backend/favorite_goods/listAllFavoriteGoods.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/favorite_goods/listAllFavoriteGoods.jsp");
				failureView.forward(req, res);
			}
		}
	
	}
}
