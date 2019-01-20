package com.order_history.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.*;
import javax.servlet.http.*;

import com.order_detail.model.*;
import com.order_history.model.*;
import com.shopping_cart.model.ShoppingCart;
import com.favorite_goods.model.FavoriteGoodsService;
import com.goods.model.GoodsDAO;
import com.goods.model.GoodsVO;
import com.member.model.*;


public class OrderHistoryServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_MemAllOrd".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String str = req.getParameter("member_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/order_history/selectOrder.jsp");
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
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/order_history/selectOrder.jsp");
					failureView.forward(req, res);
					return;
				}
				
				OrderHistoryService orderHistorySvc = new OrderHistoryService();
				List<OrderHistoryVO> orderHistoryVO = (List<OrderHistoryVO>) orderHistorySvc.findByMemberNo(member_no);

				if (orderHistoryVO == null) {
					errorMsgs.add("查無資料");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/order_history/selectOrder.jsp");
					failureView.forward(req, res);
					return;
				}
				
				req.setAttribute("orderHistoryVO", orderHistoryVO);
				String url = "/backend/order_history/oneMemberIsOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			}  catch (Exception e) {
				errorMsgs.add("無法取得資料：" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/order_history/selectOrder.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String str = req.getParameter("order_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入訂單編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/order_history/selectOrder.jsp");
					failureView.forward(req, res);
					return;
				}
				String order_no = null;
				try {
					order_no = new String(str);				
				} catch (Exception e) {
					errorMsgs.add("訂單編號格式不正確");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/order_history/selectOrder.jsp");
					failureView.forward(req, res);
					return;
				}
				
				OrderHistoryService orderHistorySvc = new OrderHistoryService();
				OrderHistoryVO orderHistoryVO = orderHistorySvc.getOneOrderHistory(order_no);
				if (orderHistoryVO == null) {
					errorMsgs.add("查無資料");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/order_history/selectOrder.jsp");
					failureView.forward(req, res);
					return;
				}
				
				req.setAttribute("orderHistoryVO", orderHistoryVO);
				String url = "/backend/order_history/listOneOrderHistory.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			}  catch (Exception e) {
				errorMsgs.add("無法取得資料：" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/order_history/selectOrder.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String order_no = new  String(req.getParameter("order_no"));
				
				OrderHistoryService orderHistorySvc = new OrderHistoryService();
				OrderHistoryVO orderHistoryVO = orderHistorySvc.getOneOrderHistory(order_no);
				
				req.setAttribute("orderHistoryVO", orderHistoryVO);
				String url = "/backend/order_history/update_OrderHistory_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料：" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/order_history/selectOrder.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String order_no = new String(req.getParameter("order_no").trim());
				String member_no = new String(req.getParameter("member_no").trim());
				Double order_price = new Double(req.getParameter("order_price").trim());
				String pay_methods = new String(req.getParameter("pay_methods").trim());
				String shipping_methods = new String(req.getParameter("shipping_methods").trim());
				
				java.sql.Timestamp order_date = java.sql.Timestamp.valueOf(req.getParameter("order_date").trim());
				java.sql.Timestamp order_etd = null;
				java.sql.Timestamp pickup_date = null;
				String order_status = new String(req.getParameter("order_status").trim());
				if("SHIPMENT3".equals(order_status)) {
					order_etd = new java.sql.Timestamp(System.currentTimeMillis());
					pickup_date = null;
				}else if("COMPLETE4".equals(order_status)){
					try {
						order_etd = java.sql.Timestamp.valueOf(req.getParameter("order_etd").trim());
					} catch (IllegalArgumentException e) {
						order_etd = new java.sql.Timestamp(System.currentTimeMillis());
					}
					pickup_date = new java.sql.Timestamp(System.currentTimeMillis());
				}else {
					order_etd = null;
					pickup_date = null;
				}
				
				String receiver_add = req.getParameter("receiver_add");
				String receiver_name = req.getParameter("receiver_name");
				String receiver_tel = req.getParameter("receiver_tel");
				
				
				OrderHistoryVO orderHistoryVO = new OrderHistoryVO();
				orderHistoryVO.setOrder_no(order_no);
				orderHistoryVO.setMember_no(member_no);
				orderHistoryVO.setOrder_price(order_price);
				orderHistoryVO.setPay_methods(pay_methods);;
				orderHistoryVO.setShipping_methods(shipping_methods);
				orderHistoryVO.setOrder_date(order_date);
				orderHistoryVO.setOrder_etd(order_etd);
				orderHistoryVO.setPickup_date(pickup_date);
				orderHistoryVO.setReceiver_add(receiver_add);
				orderHistoryVO.setReceiver_name(receiver_name);
				orderHistoryVO.setReceiver_tel(receiver_tel);
				orderHistoryVO.setOrder_status(order_status);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("orderHistoryVO", orderHistoryVO); 
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/order_history/update_OrderHistory_input.jsp");
					failureView.forward(req, res);
					return;
				}
				
				OrderHistoryService orderHistorySvc = new OrderHistoryService();
				orderHistoryVO = orderHistorySvc.updateOrderHistory(member_no, order_price, 
						pay_methods, shipping_methods, order_date, order_etd, pickup_date, receiver_add, 
						receiver_name, receiver_tel, order_status, order_no);
			
				req.setAttribute("orderHistoryVO", orderHistoryVO); 
				String url = "/backend/order_history/selectOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗："+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/order_history/update_OrderHistory_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String member_no = new String(req.getParameter("member_no").trim());
				
				Double order_price = null;
				try {
					order_price = new Double(req.getParameter("order_price").trim());
				} catch (NumberFormatException e) {
					order_price = 0.0;
					errorMsgs.add("訂單總金額請填金額。");
				}
				
				String pay_methods = new String(req.getParameter("pay_methods").trim());
				String shipping_methods = new String(req.getParameter("shipping_methods").trim());
				
				java.sql.Timestamp order_date = null;
				try {
					order_date = java.sql.Timestamp.valueOf(req.getParameter("order_date").trim());
				} catch (IllegalArgumentException e) {
					order_date = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入訂購日期。");
				}
				
				java.sql.Timestamp order_etd = null;
				try {
					order_etd = java.sql.Timestamp.valueOf(req.getParameter("order_etd").trim());
				} catch (IllegalArgumentException e) {
					order_etd = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入出貨日期。");
				}
				
				java.sql.Timestamp pickup_date = null;
				try {
					pickup_date = java.sql.Timestamp.valueOf(req.getParameter("pickup_date").trim());
				} catch (IllegalArgumentException e) {
					pickup_date = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入取貨日期。");
				}
				
				String receiver_add = req.getParameter("receiver_add");
				if (receiver_add == null || receiver_add.trim().length() == 0) {
					errorMsgs.add("送貨地址請勿空白。");
				}
				
				String receiver_name = req.getParameter("receiver_name");
				if (receiver_name == null || receiver_name.trim().length() == 0) {
					errorMsgs.add("收件人名稱請勿空白。");
				}
				
				String receiver_tel = req.getParameter("receiver_tel");
				if (receiver_tel == null || receiver_tel.trim().length() == 0) {
					errorMsgs.add("收件人電話請勿空白。");
				}
				
				String order_status = new String(req.getParameter("order_status").trim());
				String goods_no = new String(req.getParameter("goods_no").trim());
				Double goods_bonus = null;
				try {
					goods_bonus = new Double(req.getParameter("goods_bonus").trim());
				} catch (NumberFormatException e) {
					goods_bonus = 0.0;
					errorMsgs.add("請填入實際交易金額。");
				}
				Double goods_pc = null;
				try {
					goods_pc = new Double(req.getParameter("goods_pc").trim());
				} catch (NumberFormatException e) {
					goods_pc = 0.0;
					errorMsgs.add("請填入商品數量。");
				}
				
				OrderHistoryVO orderHistoryVO = new OrderHistoryVO();
				orderHistoryVO.setMember_no(member_no);
				orderHistoryVO.setOrder_price(order_price);
				orderHistoryVO.setPay_methods(pay_methods);
				orderHistoryVO.setShipping_methods(shipping_methods);
				orderHistoryVO.setOrder_date(order_date);
				orderHistoryVO.setOrder_etd(order_etd);
				orderHistoryVO.setPickup_date(pickup_date);
				orderHistoryVO.setReceiver_add(receiver_add);
				orderHistoryVO.setReceiver_name(receiver_name);
				orderHistoryVO.setReceiver_tel(receiver_tel);
				orderHistoryVO.setOrder_status(order_status);
		
				String goodsno[] = req.getParameterValues("goods_no");
				String goodsbonus[] = req.getParameterValues("goods_bonus");
				String goodspc[] = req.getParameterValues("goods_pc");

				List<OrderDetailVO> list = new ArrayList<OrderDetailVO>(); 			
				if (goodsno != null) { 
					
					for (int i=0; i<goodsno.length; i++) { 
						OrderDetailVO orderDetailVO = new OrderDetailVO();
						orderDetailVO.setGoods_no(goodsno[i]);
						orderDetailVO.setGoods_bonus(new Double(goodsbonus[i]));
						orderDetailVO.setGoods_pc(new Double(goodspc[i]));
						list.add(orderDetailVO);
					} 
				} 
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("orderHistoryVO", orderHistoryVO); 
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/order_history/addOrderHistoryAndOrderDetail.jsp");
					failureView.forward(req, res);
					return;
				}
				
				OrderHistoryService orderHistorySvc = new OrderHistoryService();
				orderHistorySvc.insertWithDetail(orderHistoryVO, list);
				
				String url = "/backend/order_history/selectOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/order_history/addOrderHistoryAndOrderDetail.jsp");
				failureView.forward(req, res);
			}		
		}
		
		if ("delete_OrderHistory".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				String order_no = new String(req.getParameter("order_no"));
				OrderHistoryService orderHistorySvc = new OrderHistoryService();
				orderHistorySvc.deleteOrderHistory(order_no);
				
				String url = "/backend/order_history/selectOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/order_history/selectOrder.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("listOrderHistory_ByCompositeQuery".equals(action)) { // 來自select_page.jsp的複合查詢請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				
				/***************************1.將輸入資料轉為Map**********************************/ 
				//採用Map<String,String[]> getParameterMap()的方法 
				//注意:an immutable java.util.Map 
				//Map<String, String[]> map = req.getParameterMap();
				HttpSession session = req.getSession();
				Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
				if (req.getParameter("whichPage") == null){
					HashMap<String, String[]> map1 = new HashMap<String, String[]>(req.getParameterMap());
					session.setAttribute("map",map1);
					map = map1;
				} 
				
				/***************************2.開始複合查詢***************************************/
				OrderHistoryService orderHistorySvc = new OrderHistoryService();
				List<OrderHistoryVO> list  = orderHistorySvc.getAll(map);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("listOrderHistory_ByCompositeQuery", list); // 資料庫取出的list物件,存入request
				RequestDispatcher successView = req.getRequestDispatcher("/backend/order_history/listOrderHistory_ByCompositeQuery.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/order_history/selectOrder.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_MemAllOrd_Front".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			HttpSession session = req.getSession();
			MemberVO memberVO = (MemberVO) session.getAttribute("member");
			MemberVO member = new MemberVO();
			try {
				OrderHistoryService orderHistorySvc = new OrderHistoryService();
				List<OrderHistoryVO> orderHistoryVO = (List<OrderHistoryVO>) orderHistorySvc.findByMemberNo(memberVO.getMemberNo());
				req.setAttribute("orderHistoryVO", orderHistoryVO);
				String url = "/frontend/order_history/oneMemberIsOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			}  catch (Exception e) {
//				errorMsgs.add("無法取得資料：" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/login_front-end.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("insert_Front".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			HttpSession session = req.getSession();
			MemberVO memberVO = (MemberVO) session.getAttribute("member");
			Vector<ShoppingCart> buylist = (Vector<ShoppingCart>) session.getAttribute("shoppingcart");
					
			try {
				String member_no = new String(req.getParameter("member_no").trim());
				Double order_price = new Double(req.getParameter("order_price").trim());
				String pay_methods = new String(req.getParameter("pay_methods").trim());
				String shipping_methods = new String(req.getParameter("shipping_methods").trim());
				
				java.sql.Timestamp order_date = java.sql.Timestamp.valueOf(req.getParameter("order_date").trim());
				java.sql.Timestamp order_etd =  null;
				java.sql.Timestamp pickup_date = null;
				
				String receiver_add = req.getParameter("receiver_add");
				if (receiver_add == null || receiver_add.trim().length() == 0) {
					errorMsgs.add("送貨地址請勿空白。");
				}
				String street = req.getParameter("street");
				if (street == null || street.trim().length() == 0) {
					errorMsgs.add("送貨地址請勿空白。");
				}
				
				String receiver_name = req.getParameter("receiver_name");
				if (receiver_name == null || receiver_name.trim().length() == 0) {
					errorMsgs.add("收件人名稱請勿空白。");
				}
				
				String receiver_tel = req.getParameter("receiver_tel");
				if (receiver_tel == null || receiver_tel.trim().length() == 0) {
					errorMsgs.add("收件人電話請勿空白。");
				}
				String order_status = new String(req.getParameter("order_status").trim());
				String goods_no = new String(req.getParameter("goods_no").trim());
				Double goods_bonus = new Double(req.getParameter("goods_bonus").trim());
				Double goods_pc = new Double(req.getParameter("goods_pc").trim());
				if ("CREDITCARD".equals(pay_methods)) {
					String creditcard_no = req.getParameter("creditcard_no");
					if (creditcard_no == null || creditcard_no.trim().length() == 0) {
						errorMsgs.add("信用卡卡號請勿空白。");
					}else if(creditcard_no.trim().length() < 16){
						errorMsgs.add("請填入正確信用卡卡號16碼。");
					}
					String creditcard_no_safe = req.getParameter("creditcard_no_safe");
					if (creditcard_no_safe == null || creditcard_no_safe.trim().length() == 0) {
						errorMsgs.add("信用卡安全碼請勿空白。");
					}else if(creditcard_no_safe.trim().length() < 3){
						errorMsgs.add("請填入正確信用卡安全碼3碼。");
					}
				}
				
				OrderHistoryVO orderHistoryVO = new OrderHistoryVO();
				orderHistoryVO.setMember_no(member_no);
				orderHistoryVO.setOrder_price(order_price);
				orderHistoryVO.setPay_methods(pay_methods);
				orderHistoryVO.setShipping_methods(shipping_methods);
				orderHistoryVO.setOrder_date(order_date);
				orderHistoryVO.setOrder_etd(order_etd);
				orderHistoryVO.setPickup_date(pickup_date);
				orderHistoryVO.setReceiver_add(receiver_add);
				orderHistoryVO.setReceiver_name(receiver_name);
				orderHistoryVO.setReceiver_tel(receiver_tel);
				orderHistoryVO.setOrder_status(order_status);
		
				String goodsno[] = req.getParameterValues("goods_no");
				String goodsbonus[] = req.getParameterValues("goods_bonus");
				String goodspc[] = req.getParameterValues("goods_pc");
				
				List<OrderDetailVO> list = new ArrayList<OrderDetailVO>(); 	

				if (goodsno != null) { 
					
					for (int i=0; i<goodsno.length; i++) { 
						OrderDetailVO orderDetailVO = new OrderDetailVO();
						orderDetailVO.setGoods_no(goodsno[i]);
						orderDetailVO.setGoods_bonus(new Double(goodsbonus[i]));
						orderDetailVO.setGoods_pc(new Double(goodspc[i]));
						list.add(orderDetailVO);
					} 
				}
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("orderHistoryVO", orderHistoryVO); 
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/shopping_cart/Checkout.jsp");
					failureView.forward(req, res);
					return;
				}
				
				//判斷是付款方式為電子錢包則進行扣款
				if ("EWALLET".equals(pay_methods)) {
					String memberNo = req.getParameter("memberno").trim();
					String memberFullname = req.getParameter("memberFullname");
					String email = req.getParameter("email").trim();
					String phone = req.getParameter("phone").trim();
					String idcard = req.getParameter("idcard").trim();
					String memberAccount = req.getParameter("memberAccount").trim();
					String memberPassword = req.getParameter("memberPassword").trim();
					Integer newEwalletBalance = null;
//					try {
						order_price = new Double(req.getParameter("order_price").trim());
						newEwalletBalance = memberVO.getEwalletBalance() - order_price.intValue();
						if (newEwalletBalance < 0) {
//						List<String> errorMsgs = new LinkedList<String>();
//						req.setAttribute("errorMsgs", errorMsgs);
//							throw new Exception();
							newEwalletBalance = memberVO.getEwalletBalance();
							errorMsgs.add("電子錢包餘額不足");
//							RequestDispatcher failureView = req.getRequestDispatcher("/frontend/shopping_cart/Checkout.jsp");
//							failureView.forward(req, res);
							
							HttpSession error = req.getSession();
							error.setAttribute("errorMsgs", errorMsgs);
//							req.setAttribute("errorMsgs", errorMsgs);
							res.sendRedirect(req.getContextPath()+"/frontend/shopping_cart/Checkout.jsp");
							return;
						}
//					} catch (Exception e) {
//						newEwalletBalance = memberVO.getEwalletBalance();
//						errorMsgs.add("電子錢包餘額不足");
//						RequestDispatcher failureView = req.getRequestDispatcher("/frontend/shopping_cart/Checkout.jsp");
//						failureView.forward(req, res);
//						PrintWriter out = res.getWriter();
//						res.sendRedirect(req.getContextPath()+"/frontend/shopping_cart/Checkout.jsp");
//						return;
//					}
					Timestamp creationDate = Timestamp.valueOf(req.getParameter("creationDate").trim());
					byte[] profilePicture = memberVO.getProfilePicture();
					String memberStatus = req.getParameter("memberStatus").trim();
					String thirduid = req.getParameter("thirduid").trim();
					
					memberVO.setMemberNo(memberNo);
					memberVO.setMemberFullname(memberFullname);
					memberVO.setEmail(email);
					memberVO.setPhone(phone);
					memberVO.setIdcard(idcard);
					memberVO.setMemberAccount(memberAccount);
					memberVO.setMemberPassword(memberPassword);
					memberVO.setEwalletBalance(newEwalletBalance);
					memberVO.setCreationDate(creationDate);
					memberVO.setProfilePicture(profilePicture);
					memberVO.setMemberStatus(memberStatus);
					memberVO.setThirduid(thirduid);
					MemberService memberSvc = new MemberService();
					memberSvc.memberWithdrawal(memberNo, memberFullname, email, phone, idcard, memberAccount, memberPassword, newEwalletBalance, creationDate, profilePicture, memberStatus, thirduid);
				}
				
				
				OrderHistoryService orderHistorySvc = new OrderHistoryService();
				orderHistorySvc.insertWithDetail(orderHistoryVO, list);
				//新增商品累計銷售量開始
				GoodsDAO goodsVO = new GoodsDAO();
				goodsVO.updateGOODS_SALES_COUNT(list);

				//完成結帳後寄E-mail通知
//				try {
//					// 設定使用SSL連線至 Gmail smtp Server
//				   Properties props = new Properties();
//				   props.put("mail.smtp.host", "smtp.gmail.com");
//				   props.put("mail.smtp.socketFactory.port", "465");
//				   props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
//				   props.put("mail.smtp.auth", "true");
//				   props.put("mail.smtp.port", "465");
//				
//				   // ●設定 gmail 的帳號 & 密碼 (將藉由你的Gmail來傳送Email)
//				   // ●須將myGmail的【安全性較低的應用程式存取權】打開
//				final String myGmail = "ixlogic.wu@gmail.com";
//				final String myGmail_password = "BBB45678";
//				String subject = "安安你好";
//				String messageText = "安安你好";
////				String to = "shou198175aa@gmail.com";
//				String to = memberVO.getEmail(); 
//				Session session_mail = Session.getInstance(props, new Authenticator() {
//					protected PasswordAuthentication getPasswordAuthentication() {
//						return new PasswordAuthentication(myGmail, myGmail_password);
//					}
//				});
//				
//				   Message message = new MimeMessage(session_mail);
//				   message.setFrom(new InternetAddress(myGmail));
//				   message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
//				  
//
//				   //設定信中的主旨  
//				   message.setSubject(subject);
//				   //設定信中的內容 
//				   message.setText(messageText);
//				
//				   Transport.send(message);
//				   System.out.println("傳送成功!");
//				}catch (MessagingException e){
//					System.out.println("傳送失敗!");
//					e.printStackTrace();
//				}
				
				
//				//結帳完成後寄簡訊通知
//				try {
//					String server = "203.66.172.131"; //Socket to Air Gateway IP
//					int port = 8000;            //Socket to Air Gateway Port
//
//					String user    = "85559671"; //帳號
//					String passwd  = "2irioiai"; //密碼
//					String messageBig5 = new String(message.getBytes(),"big5"); //簡訊內容
//
//				      //----建立連線 and 檢查帳號密碼是否錯誤
//					sock2air mysms = new sock2air();
//					int ret_code = mysms.create_conn(server,port,user,passwd) ;
//					if( ret_code == 0 ) {
//						System.out.println("帳號密碼Login OK!");
//					} else {
//						System.out.println("帳號密碼Login Fail!");
//						System.out.println("ret_code="+ret_code + ",ret_content=" + mysms.get_message());
//						//結束連線
//						mysms.close_conn();
//						return ;
//					}
//
//					//傳送文字簡訊
//					//如需同時傳送多筆簡訊，請多次呼叫send_message()即可。
//					for(int i=0 ; i<tel.length ; i++){  
//						ret_code=mysms.send_message(tel[i],messageBig5);
//						if( ret_code == 0 ) {
//							System.out.println("簡訊已送到簡訊中心!");
//							System.out.println("MessageID="+mysms.get_message()); //取得MessageID
//						} else {
//							System.out.println("簡訊傳送發生錯誤!");
//							System.out.print("ret_code="+ret_code+",");
//							System.out.println("ret_content="+mysms.get_message());//取得錯誤的訊息
//							//結束連線
//							mysms.close_conn();
//							return ;
//						}
//					}
//
//					//結束連線
//					mysms.close_conn();
//
//				}catch (Exception e)  {
//
//					System.out.println("I/O Exception : " + e);
//				}
				
			
				
				session.setAttribute("member", memberVO);
				req.setAttribute("orderHistoryVO", orderHistoryVO);
				session.removeAttribute("shoppingcart");
				String url = "/frontend/shopping_cart/CheckoutCompleted.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/shopping_cart/Checkout.jsp");
				failureView.forward(req, res);
			}		
		}
	}
}
