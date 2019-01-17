package com.group_member.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group_member.model.Group_memberService;
import com.group_member.model.Group_memberVO;
import com.group_open.model.Group_openService;
import com.group_open.model.Group_openVO;

public class Group_memberServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		req.setCharacterEncoding("UTF-8");
		// 取得請求
		String action = req.getParameter("action");

		/************************ 查詢跟團人有那些團 *****************************/
		if ("getOne_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			// 格式錯誤
			try {
				String member_no = req.getParameter("member_no");

				if (member_no == null || (member_no.trim().length() == 0)) {
					errorMsgs.add("請輸入會員編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/group_open/group_open_index.jsp");

					failureView.forward(req, res);
					return;// 程式中斷 這裡沒有寫輸入格式錯誤
				}
				// 沒有做會員編號格式驗證
				/************************ 開始查詢資料 *****************************/

				// 取得跟團資料庫表格方法
				Group_memberService group_memberSvc = new Group_memberService();
				// 取得根據會員編號取得跟團資料表
				List<Group_memberVO> list = group_memberSvc.getgroup_BY_member_no(member_no);

				if (member_no == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {

					RequestDispatcher failureView = req.getRequestDispatcher("/group_open_index.jsp");

					failureView.forward(req, res);
					return;// 程式中斷 這裡沒有寫輸入格式錯誤
				}
				/************************ 查詢完成 *****************************/
				req.setAttribute("group_openBymember_no", list);

				String url = "/frontend/group_member/group_memberBygroup_open.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得資料" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/group_open/group_open_index.jsp");
				failureView.forward(req, res);
			}
		}
		
		

		if ("getOne_For_Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String member_no = req.getParameter("member_no");
				if (member_no == null || member_no.trim().length() == 0) {
					errorMsgs.add("跟團會員編號請勿空白");
				}
				String group_no = req.getParameter("group_no");
				if (group_no == null || group_no.trim().length() == 0) {
					errorMsgs.add("開團編號請勿空白");
				}
				/*************************** 2.開始查詢資料 ****************************************/

				Group_memberService group_memberSvc = new Group_memberService();
				Group_memberVO group_memberVO = new Group_memberVO();
				Group_openService group_openSvc = new Group_openService();
				Group_openVO group_openVO = new Group_openVO();
				group_memberVO = group_memberSvc.findByPrimaryKey(member_no, group_no);
				group_openVO = group_openSvc.getOneGroup_open(group_no);
				
//				String product = group_memberSvc.getproductquantity("G0001");	
//							System.out.println("Group_memberServlet113"+product);
//				Integer ewallet = group_memberSvc.getewallet(member_no);
//				System.out.println("取得電子錢包價格"+ewallet);
//				group_memberSvc.updateewallet(50000, member_no);
//				System.out.println("修改電子錢包成功group_memberServlet117");
				
			
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("group_memberVO", group_memberVO);
				req.setAttribute("group_openVO", group_openVO);
				String url = "/frontend/group_member/update_group_member.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("修改資料取出時失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/group_open/group_open_index.jsp");
				failureView.forward(req, res);

			}
		}

		if ("update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String member_no = req.getParameter("member_no");
				if (member_no == null || member_no.trim().length() == 0) {
					errorMsgs.add("跟團會員編號請勿空白");
				}
				String group_no = req.getParameter("group_no");
				if (group_no == null || group_no.trim().length() == 0) {
					errorMsgs.add("開團編號請勿空白");
				}
				// 跟團時間修改戳記
				Timestamp join_time = new Timestamp(System.currentTimeMillis());

				Integer product_quantity = null;
				try{
					String product_quantitys = req.getParameter("product_quantity");
					product_quantity = Integer.valueOf(product_quantitys);
				}catch(NumberFormatException e) {
					errorMsgs.add("購買數量格式錯誤");
				}
				if (product_quantity == null) {
					errorMsgs.add("購買數量請勿空白");
				}

				String pay_status = req.getParameter("pay_status");
//				if (pay_status == null || pay_status.trim().length() == 0) {
//					errorMsgs.add("付款狀態請勿空白");
//				}
				String group_member_status = req.getParameter("group_member_status");
//				if (group_member_status == null || group_member_status.trim().length() == 0) {
//					errorMsgs.add("會員狀態請勿空白");
//				}
				// 退團裡有可以為空
				String log_out_reason = req.getParameter("log_out_reason");
				if ("quit".equals(group_member_status)) {
					if (log_out_reason == null || log_out_reason.trim().length() == 0) {
						errorMsgs.add("退團理由請勿空白");
					}
				}
				
				String order_phone = req.getParameter("order_phone");
				if(order_phone.length()!=10) {
					errorMsgs.add("聯絡電話格式錯誤");
				}
//				product_quantity = Integer.valueOf(product_quantitys);
				try {
				  Integer  order_phoneint =  Integer.valueOf(order_phone);
				}catch(Exception e) {
					errorMsgs.add("聯絡電話格式錯誤，不能有符號");
				}
				if (order_phone == null || order_phone.trim().length() == 0) {
					errorMsgs.add("聯絡電話請勿空白");
				}
		
				
				String pay_method = req.getParameter("pay_method");
				if (pay_method == null || pay_method.trim().length() == 0) {
					errorMsgs.add("付款方法請勿空白");
				}

				Group_memberVO group_memberVO = new Group_memberVO();
				group_memberVO.setMember_no(member_no);
				group_memberVO.setGroup_no(group_no);
				group_memberVO.setJoin_time(join_time);
				group_memberVO.setProduct_quantity(product_quantity);
				group_memberVO.setPay_status(pay_status);
				group_memberVO.setGroup_member_status(group_member_status);
				group_memberVO.setLog_out_reason(log_out_reason);
				group_memberVO.setOrder_phone(order_phone);
				group_memberVO.setOrder_phone(order_phone);
				group_memberVO.setPay_method(pay_method);
				
				Group_openService group_openSvc = new Group_openService();
				Group_openVO group_openVO = group_openSvc.getOneGroup_open(group_no);

				if (!errorMsgs.isEmpty()) {				
					req.setAttribute("group_openVO", group_openVO);
					req.setAttribute("group_memberVO", group_memberVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/group_member/update_group_member.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始修改資料 ************/
				Group_memberService group_memberSvc = new Group_memberService();
				group_memberVO = group_memberSvc.updateGroup_member(member_no, group_no, join_time, product_quantity,
						pay_status, group_member_status, log_out_reason, order_phone, pay_method);

				/*************************** 3.修改完成準備轉交 ************/
				List<Group_memberVO> group_openBymember_no = group_memberSvc.getgroup_BY_member_no(member_no);

//				req.setAttribute("listGroup_member_ByGroup_no", group_openSvc.getGroup_memberByGroup_no(group_no));

				req.setAttribute("group_openBymember_no",group_openBymember_no);
				String url = "/frontend/group_member/group_memberBygroup_open.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("資料有錯:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/frontend/group_member/update_group_member.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				// 接受請求參數
				String member_no = req.getParameter("member_no");
				if (member_no == null || member_no.trim().length() == 0) {
					errorMsgs.add("會員編號請勿空白");
				}
				String group_no = req.getParameter("group_no");
				if (group_no == null || group_no.trim().length() == 0) {
					errorMsgs.add("開團編號請勿空白");
				}
				// 入團時間
				Timestamp join_time = new Timestamp(System.currentTimeMillis());

				String product_quantitys = req.getParameter("product_quantity");
				Integer product_quantity = Integer.valueOf(product_quantitys);

				if (product_quantity == null) {
					errorMsgs.add("購買數量請勿空白");
				}

				String pay_status = req.getParameter("pay_status");
//				if (pay_status == null || pay_status.trim().length() == 0) {
//					errorMsgs.add("付款狀態請勿空白");
//				}
				String group_member_status = req.getParameter("group_member_status");
//				if (group_member_status == null || group_member_status.trim().length() == 0) {
//					errorMsgs.add("會員狀態請勿空白");
//				}
				//
				String log_out_reason = req.getParameter("log_out_reason");
				if ("quit".equals(group_member_status)) {
					if (log_out_reason == null || log_out_reason.trim().length() == 0) {
						errorMsgs.add("退團理由請勿空白");
					}
				}
				String order_phone = req.getParameter("order_phone");
				String pay_method = req.getParameter("pay_method");
				if (pay_method == null || pay_method.trim().length() == 0) {
					errorMsgs.add("付款方法請勿空白");
				}

				Group_memberVO group_memberVO = new Group_memberVO();

				group_memberVO.setMember_no(member_no);
				group_memberVO.setGroup_no(group_no);
				group_memberVO.setJoin_time(join_time);
				group_memberVO.setProduct_quantity(product_quantity);
				group_memberVO.setPay_status(pay_status);
				group_memberVO.setGroup_member_status(group_member_status);
				group_memberVO.setLog_out_reason(log_out_reason);
				group_memberVO.setOrder_phone(order_phone);
				group_memberVO.setOrder_phone(order_phone);
				group_memberVO.setPay_method(pay_method);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("group_memberVO", group_memberVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/group_member/addgroup_member.jsp");
					failureView.forward(req, res);
					return;
				}

				Group_memberService group_memberSvc = new Group_memberService();

				group_memberVO = group_memberSvc.addGroup_member(member_no, group_no, join_time, product_quantity,
						pay_status, group_member_status, log_out_reason, order_phone, pay_method);

				String url = "/frontend/group_member/listAllgroup_member.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/group_member/addgroup_member.jsp");
				failureView.forward(req, res);
			}

		}
		if ("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL");
			try {

				String member_no = req.getParameter("member_no");

				String group_no = req.getParameter("group_no");

				Group_memberService group_memberSvc = new Group_memberService();

				Group_memberVO group_memberVO = group_memberSvc.findByPrimaryKey(member_no, group_no);

				group_memberSvc.deleteGroup_member(member_no);

				Group_openService group_openSvc = new Group_openService();
				if (requestURL.equals("/frontend/group_open/listGroup_memberBygroup_no.jsp")
						|| requestURL.equals("/frontend/group_open/listAllgroup_openM.jsp"))
					req.setAttribute("listGroup_memberByGroup_open",
							group_openSvc.getGroup_memberByGroup_no(group_memberVO.getGroup_no()));

				String url = requestURL;

				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("轉交失敗有錯:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);

			}
		}

		// 加入跟團之後到自己有的跟團訂單
		if ("insert2".equals(action)) {
			System.out.println(action+"group_memberServlet348");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				// 接受請求參數
				String member_no = req.getParameter("member_no");
				if (member_no == null || member_no.trim().length() == 0) {
					errorMsgs.add("會員編號請勿空白");
				}
						
				String group_no = req.getParameter("group_no");
				if (group_no == null || group_no.trim().length() == 0) {
					errorMsgs.add("開團編號請勿空白");
				}
				// 入團時間
				Timestamp join_time = new Timestamp(System.currentTimeMillis());
				
				Integer product_quantity = null;
				try{
					String product_quantitys = req.getParameter("product_quantity");
					product_quantity = Integer.valueOf(product_quantitys);
				}catch(NumberFormatException e) {
					errorMsgs.add("購買數量格式錯誤");
				}
				if (product_quantity == null) {
					errorMsgs.add("購買數量請勿空白");
				}

				String pay_status = req.getParameter("pay_status");
//				if (pay_status == null || pay_status.trim().length() == 0) {
//					errorMsgs.add("付款狀態請勿空白");
//				}
				String group_member_status = req.getParameter("group_member_status");
//				if (group_member_status == null || group_member_status.trim().length() == 0) {
//					errorMsgs.add("會員狀態請勿空白");
//				}
				//
				String log_out_reason = req.getParameter("log_out_reason");
				if ("quit".equals(group_member_status)) {
					if (log_out_reason == null || log_out_reason.trim().length() == 0) {
						errorMsgs.add("退團理由請勿空白");
					}
				}
				
				
				String order_phone = req.getParameter("order_phone");
				if(order_phone.length()!=10) {
					errorMsgs.add("聯絡電話格式錯誤");
				}
//				product_quantity = Integer.valueOf(product_quantitys);
				try {
				  Integer  order_phoneint =  Integer.valueOf(order_phone);
				}catch(Exception e) {
					errorMsgs.add("聯絡電話格式錯誤，不能有符號");
				}
				if (order_phone == null || order_phone.trim().length() == 0) {
					errorMsgs.add("聯絡電話請勿空白");
				}
				
				
				
				String pay_method = req.getParameter("pay_method");
				if (pay_method == null || pay_method.trim().length() == 0) {
					errorMsgs.add("付款方法請勿空白");
				}
				
				

				Group_memberVO group_memberVO = new Group_memberVO();
					
				
				group_memberVO.setMember_no(member_no);
				group_memberVO.setGroup_no(group_no);
				group_memberVO.setJoin_time(join_time);
				group_memberVO.setProduct_quantity(product_quantity);
				group_memberVO.setPay_status(pay_status);
				group_memberVO.setGroup_member_status(group_member_status);
				group_memberVO.setLog_out_reason(log_out_reason);
				group_memberVO.setOrder_phone(order_phone);
				group_memberVO.setPay_method(pay_method);
				
				
				Group_openService group_openSvc = new Group_openService();
				Group_openVO group_openVO = group_openSvc.getOneGroup_open(group_no);
				
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("group_openVO", group_openVO);
					req.setAttribute("group_memberVO", group_memberVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/group_member/addgroup_member.jsp");
					failureView.forward(req, res);
					return;
				}
				
//				************************錯誤驗證2**************************
				Group_memberService group_memberSvc = new Group_memberService();
//				Group_openService group_openSvc = new Group_openService();
				
				
				
			
//				Group_openVO group_openVO = group_openSvc.getOneGroup_open(group_no);
				group_memberVO = group_memberSvc.addGroup_member(member_no, group_no, join_time, product_quantity,
						pay_status, group_member_status, log_out_reason, order_phone, pay_method);

				
				List<Group_memberVO> group_openBymember_no = group_memberSvc.getgroup_BY_member_no(member_no);
				
//				for(Group_memberVO str : group_openBymember_no) {
//					System.out.println(str.getGroup_no());
//				}
			
			
				String url = "/frontend/group_member/group_memberBygroup_open.jsp";

				req.setAttribute("group_openBymember_no", group_openBymember_no);
				req.setAttribute("group_openVO", group_openVO);

				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/group_member/addgroup_member.jsp");
//				failureView.forward(req, res);
//			}

		}
		// 退團
		if ("quit".equals(action)) {
//			System.out.println("Group_memberServlet440quit"+"有進來嗎");
			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String member_no = req.getParameter("member_no");
				if (member_no == null || member_no.trim().length() == 0) {
					errorMsgs.add("跟團會員編號請勿空白");
				}
				String group_no = req.getParameter("group_no");
				if (group_no == null || group_no.trim().length() == 0) {
					errorMsgs.add("開團編號請勿空白");
				}

				String pay_status = req.getParameter("pay_status");
//				if (pay_status == null || pay_status.trim().length() == 0) {
//					errorMsgs.add("付款狀態請勿空白");
//				}
				String group_member_status = req.getParameter("group_member_status");
//				if (group_member_status == null || group_member_status.trim().length() == 0) {
//					errorMsgs.add("會員狀態請勿空白");
//				}
				// 退團裡有可以為空
				String log_out_reason = req.getParameter("log_out_reason");
			
		

				Group_memberVO group_memberVO = new Group_memberVO();
				group_memberVO.setMember_no(member_no);
				group_memberVO.setGroup_no(group_no);
				group_memberVO.setPay_status(pay_status);
				group_memberVO.setGroup_member_status(group_member_status);
				group_memberVO.setLog_out_reason(log_out_reason);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("group_memberVO", group_memberVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/group_member/group_memberBygroup_open.jsp");
					failureView.forward(req, res);
					return; // 中斷
				}

				/*************************** 2.開始修改資料 ************/
				Group_memberService group_memberSvc = new Group_memberService();
				group_memberSvc.change_quit(member_no, group_no, pay_status, group_member_status, log_out_reason);
//	
				/*************************** 3.寄出退團通知 ************/
				Group_openService group_openSvc = new Group_openService();
				String group_open_member_no = group_openSvc.getgroup_open_member_no(group_no);
				
				
	
//				String email = group_memberSvc.getemail(group_open_member_no);
//				group_memberSvc.sendMail(email, log_out_reason);
				/***************************退款 ************/
				//取的購買人購買數量	
				Integer quantity =  group_memberSvc.findByPrimaryKey(member_no, group_no).getProduct_quantity();
				//取得開團折扣
				Integer group_price = group_openSvc.getOneGroup_open(group_no).getGroup_price();
				//退款總數
				Integer total = quantity*group_price;
				System.out.println("group_memberServlet退款總數"+total);
				//呼叫會員電子錢包
				Integer ewallet = group_memberSvc.getewallet(member_no);
				System.out.println("group_memberServlet電子錢包"+ewallet);
				//退款
				ewallet+=total;
				System.out.println("group_memberServlet退款後電子錢包"+ewallet);
				//更改
				group_memberSvc.updateewallet(ewallet, member_no);
		
				System.out.println("退款完成");
				
		
				/*************************** 4.修改完成準備轉交 ************/
				List<Group_memberVO> group_openBymember_no = group_memberSvc.getgroup_BY_member_no(member_no);
				System.out.println(group_openBymember_no.isEmpty());
				for(int i = 0 ; i<group_openBymember_no.size();i++) {
					
					
				System.out.println("從servlet過來的"+group_openBymember_no.get(0).getMember_no());
				}
//				req.setAttribute("listGroup_member_ByGroup_no", group_openSvc.getGroup_memberByGroup_no(group_no));

				req.setAttribute("group_openBymember_no", group_openBymember_no);
				String url = "/frontend/group_member/group_memberBygroup_open.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("資料有錯:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/frontend/group_member/group_memberBygroup_open.jsp");
				failureView.forward(req, res);
			}
		}
		
		
			
			
			
			
			
			
			
			
			
			
		
	
		
		
		

	}

}
