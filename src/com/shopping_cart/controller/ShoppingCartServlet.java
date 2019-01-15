package com.shopping_cart.controller;
import java.util.*;
import java.io.*;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.*;
import javax.servlet.http.*;
import com.shopping_cart.model.ShoppingCart;
import com.goods.model.GoodsVO;

public class ShoppingCartServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)	throws ServletException, IOException {
		doPost(req,res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res)	throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");	
//		res.setHeader("Cache-Control", "no-store");
//		res.setHeader("Pragma", "no-cache");
//		res.setDateHeader("Expires", 0);

		HttpSession session = req.getSession();
		Vector<ShoppingCart> buylist = (Vector<ShoppingCart>) session.getAttribute("shoppingcart");
		String action = req.getParameter("action");

		if (!"CHECKOUT".equals(action)) {

			// 刪除購物車中的商品
			if ("DELETE".equals(action)) {
				String del = req.getParameter("del");
				int d = Integer.parseInt(del);
				buylist.removeElementAt(d);
			}
			else if ("CLEAN".equals(action)){
				buylist.removeAllElements();
//				session.invalidate();
			}
			// 新增商品至購物車中
			else if ("ADD".equals(action)) {
				
				System.out.println("===" + req.getParameter("goods_quantity"));
				
				boolean match = false;
				// 取得後來新增的商品
				ShoppingCart agoods = getShoppingCart(req);
				// 新增第一項商品至購物車時
				if (buylist == null) {
					buylist = new Vector<ShoppingCart>();
					buylist.add(agoods);
				} else {
					for (int i = 0; i < buylist.size(); i++) {
						ShoppingCart goods = buylist.get(i);
						// 假若新增的商品和購物車的商品一樣時
						if (goods.getGoods_no().equals(agoods.getGoods_no())) {
							goods.setGoods_quantity(goods.getGoods_quantity() + agoods.getGoods_quantity());
							buylist.setElementAt(goods, i);
							match = true;
						} 
					}
					// 假若新增的商品和購物車的商品不一樣時
					if (!match)
						buylist.add(agoods);
				}
			}
			session.setAttribute("shoppingcart", buylist);
			String url = "/frontend/shopping_cart/ShoppingCart.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
		}			
	
		else if ("CHECKOUT".equals(action)) {
			// 取得後來修改的商品數量
			ShoppingCart agoods = getShoppingCart(req);
			String [] quantity = req.getParameterValues("goods_quantity");
			for (int i = 0; i < buylist.size(); i++) {
				ShoppingCart goods = buylist.get(i);
				goods.setGoods_quantity(Integer.parseInt(quantity[i]));
//				oldprice = goods.getGoods_price();
				if ((Integer.parseInt(quantity[i])) >= 10 ) {
					goods.setGoods_price(goods.getForsales_a());
				}else {
					goods.setGoods_price(goods.getOld_price());
				}
			} 
			session.setAttribute("shoppingcart", buylist);
			float total = 0;
			for (int i = 0; i < buylist.size(); i++) {
				ShoppingCart order = buylist.get(i);
				int goods_price = order.getGoods_price();
				int goods_quantity = order.getGoods_quantity();
				total += (goods_price * goods_quantity);
			}

			String amount = String.valueOf(total);
			req.setAttribute("amount", amount);
			String url = "/frontend/shopping_cart/Checkout.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
		}
	}

	private ShoppingCart getShoppingCart(HttpServletRequest req) {
		
		String goods_no = req.getParameter("goods_no");
		String evetit_no = req.getParameter("evetit_no");
		String goods_name = req.getParameter("goods_name");
		String goods_price = req.getParameter("goods_price");
		String goods_quantity = req.getParameter("goods_quantity");	
		String forsales_a = req.getParameter("forsales_a");
		String goods_status = req.getParameter("goods_status");
		String old_price = req.getParameter("old_price");
		
		ShoppingCart shoppingCart = new ShoppingCart();
		
		shoppingCart.setGoods_no(goods_no);
		shoppingCart.setEvetit_no(evetit_no);
		shoppingCart.setGoods_name(goods_name);
		shoppingCart.setGoods_price(Integer.parseInt(goods_price));
		shoppingCart.setGoods_quantity(Integer.parseInt(goods_quantity));
		shoppingCart.setForsales_a(Integer.parseInt(forsales_a));
		shoppingCart.setGoods_status(goods_status);
		shoppingCart.setOld_price(Integer.parseInt(old_price));
		return shoppingCart;
	}

}
