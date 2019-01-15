package com.group_open.controller;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.security.acl.Group;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.catalina.connector.Request;
import org.json.JSONArray;

import com.android.member.model.MemberService;
import com.goods.model.GoodsService;
import com.goods.model.GoodsVO;
import com.group_member.model.Group_memberDAO;
import com.group_member.model.Group_memberService;
import com.group_member.model.Group_memberVO;
import com.group_open.model.Group_openService;
import com.group_open.model.Group_openVO;
import com.mysql.fabric.xmlrpc.base.Member;
import com.sun.org.apache.bcel.internal.generic.NEW;

@MultipartConfig
public class Group_openServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		// 查詢單一欄位
		if ("getOne_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {

				String groupno = req.getParameter("group_no");
				if (groupno == null || (groupno.trim().length() == 0)) {
					errorMsgs.add("開團編號不可為空");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/group_open/group_open.jsp");

					failureView.forward(req, res); //// ??????
					return;
				}

				Group_openService groupSvc = new Group_openService();

				Group_openVO group_openVO = groupSvc.getOneGroup_open(groupno);

				if (groupno == null) {
					errorMsgs.add("開團編號不可為空");
				}
				if (!errorMsgs.isEmpty()) {

					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/group_open/group_open.jsp");

					failureView.forward(req, res); //// ??????
					return;//
				}
				//
				req.setAttribute("group_openVO", group_openVO);
				String url = "/frontend/group_open/listOnegroup_open.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("無法取得資料" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/group_open/group_open.jsp");

				failureView.forward(req, res);

			}

		}

		if ("getOne_For_Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {

				String group_no = req.getParameter("group_no");

				Group_openService grpSvc = new Group_openService();
				Group_openVO group_openVO = grpSvc.getOneGroup_open(group_no);

				req.setAttribute("group_openVO", group_openVO);

				String url = "/frontend/group_open/update_group_open.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/group_open/update_group_open.jsp");
				failureView.forward(req, res);
			}

		}

		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String group_no = req.getParameter("group_no");

				String member_no = req.getParameter("member_no");
				if (member_no == null || member_no.trim().length() == 0) {
					errorMsgs.add("會員編號不可為空");
				}
				String goods_no = req.getParameter("goods_no");
				if (goods_no == null || goods_no.trim().length() == 0) {
					errorMsgs.add("商品編號不可為空");
				}
				String group_name = req.getParameter("group_name");
				String group_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,20}$";
				if (group_name == null || group_name.trim().length() == 0) {
					errorMsgs.add("開團編號不可為空");
				} else if (!group_name.trim().matches(group_nameReg)) {
					errorMsgs.add("開團名稱: 不可為空 , 不可為中文英文或其他字");
				}

				Integer group_limit = null;
				try {
					group_limit = new Integer(req.getParameter("group_limit"));
				} catch (NumberFormatException e) {
					group_limit = 0;
					errorMsgs.add("下限不可為空");
				}

				String group_introduction = req.getParameter("group_introduction");

				String group_mind = req.getParameter("group_mind");

				java.sql.Timestamp group_start_date = null;
				try {
//					group_start_date = java.sql.Timestamp.valueOf(req.getParameter("group_start_date").trim());
					group_start_date = new java.sql.Timestamp(System.currentTimeMillis());
				} catch (IllegalArgumentException e) {
//					group_start_date = new java.sql.Timestamp(System.currentTimeMillis());
//					errorMsgs.add("開團時間不可為空!");
				}
				java.sql.Timestamp group_close_date = null;
				try {
					group_close_date = java.sql.Timestamp.valueOf(req.getParameter("group_close_date").trim());
//				      		
				} catch (IllegalArgumentException e) {
					group_close_date = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("結束時間不可為空!");
				}

				Part filepart = req.getPart("group_banner_1");

				byte[] group_banner_1 = null;

				if (getFileNameFromPart(filepart) != null) {

					InputStream fileContent = filepart.getInputStream();
					group_banner_1 = getPictureByteArray(fileContent);
//				System.out.println("banner_1 write out");

				} else {
					Group_openService group_openService = new Group_openService();

					Group_openVO group_openVO = new Group_openVO();

					group_openVO = group_openService.getOneGroup_open(group_no);

					group_banner_1 = group_openVO.getGroup_banner_1();
//						System.out.println("banner_1 write in");

				}

				byte[] group_banner_2 = null;

				Part filepart2 = req.getPart("group_banner_2");

				if (getFileNameFromPart(filepart2) != null) {

					InputStream fileContent = filepart2.getInputStream();
					group_banner_2 = getPictureByteArray(fileContent);

				} else {
					Group_openService group_openService = new Group_openService();

					Group_openVO group_openVO = new Group_openVO();

					group_openVO = group_openService.getOneGroup_open(group_no);

					group_banner_2 = group_openVO.getGroup_banner_2();

				}

				String group_status = req.getParameter("group_status");

				String group_address = req.getParameter("group_address");

				Double latitude = null;
				try {
					latitude = new Double(req.getParameter("latitude").trim());
				} catch (NumberFormatException e) {
					latitude = 0.0;
					errorMsgs.add("緯度不可為空");
				}
				Double longitude = null;
				try {
					longitude = new Double(req.getParameter("longitude").trim());
				} catch (NumberFormatException e) {
					longitude = 0.0;
					errorMsgs.add("經度不可為空");
				}
				java.sql.Timestamp group_time = null;
				try {
					group_time = java.sql.Timestamp.valueOf(req.getParameter("group_time").trim());
//				      	
				} catch (IllegalArgumentException e) {
					group_time = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("時間格式錯誤!");
				}

				Integer group_price = null;
				try {
					group_price = new Integer(req.getParameter("group_price"));
				} catch (NumberFormatException e) {
					group_price = 0;
					errorMsgs.add("商品價格不可為空");
				}

				Group_openVO group_openVO = new Group_openVO();

				group_openVO.setGroup_no(group_no);
				group_openVO.setMember_no(member_no);
				group_openVO.setGoods_no(goods_no);
				group_openVO.setGroup_name(group_name);
				group_openVO.setGroup_limit(group_limit);
				group_openVO.setGroup_introduction(group_introduction);
				group_openVO.setGroup_mind(group_mind);
				group_openVO.setGroup_start_date(group_start_date);
				group_openVO.setGroup_close_date(group_close_date);
				group_openVO.setGroup_banner_1(group_banner_1);
				group_openVO.setGroup_banner_2(group_banner_2);
				group_openVO.setGroup_status(group_status);
				group_openVO.setGroup_address(group_address);
				group_openVO.setLatitude(latitude);
				group_openVO.setLongitude(longitude);
				group_openVO.setGroup_time(group_time);
				group_openVO.setGroup_price(group_price);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("group_openVO", group_openVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/group_open/update_group_open.jsp");
					failureView.forward(req, res);
					return;
				}

				Group_openService grpSvc = new Group_openService();

				group_openVO = grpSvc.updateGroup_open(group_no, member_no, goods_no, group_name, group_limit,
						group_introduction, group_mind, group_start_date, group_close_date, group_banner_1,
						group_banner_2, group_status, group_address, latitude, longitude, group_time, group_price);

				String url = "/frontend/group_open/listAllgroup_open.jsp";

				RequestDispatcher successView = req.getRequestDispatcher(url);

				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/group_open/update_group_open.jsp");
				failureView.forward(req, res);
			}

		}

		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String member_no = req.getParameter("member_no");
				if (member_no == null || member_no.trim().length() == 0) {
					errorMsgs.add("會員編號不可為空");
				}
				String goods_no = req.getParameter("goods_no");
				if (goods_no == null || goods_no.trim().length() == 0) {
					errorMsgs.add("商品編號不可為空");
				}
				String group_name = req.getParameter("group_name");
				String group_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,20}$";
				if (group_name == null || group_name.trim().length() == 0) {
					errorMsgs.add("開團名稱不可為空");
				} else if (!group_name.trim().matches(group_nameReg)) {
					errorMsgs.add("開團名稱不可有空白");
				}

				Integer group_limit = null;
				try {
					group_limit = new Integer(req.getParameter("group_limit"));
				} catch (NumberFormatException e) {
					group_limit = 0;
					errorMsgs.add("開團下限");
				}

				String group_introduction = req.getParameter("group_introduction");

				String group_mind = req.getParameter("group_mind");

				java.sql.Timestamp group_start_date = null;
				try {
					group_start_date = java.sql.Timestamp.valueOf(req.getParameter("group_start_date").trim());
//					
				} catch (IllegalArgumentException e) {
					group_start_date = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("時間格式錯誤!");
				}
				java.sql.Timestamp group_close_date = null;
				try {
					group_close_date = java.sql.Timestamp.valueOf(req.getParameter("group_close_date").trim());

				} catch (IllegalArgumentException e) {
					group_close_date = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("時間格式錯誤");
				}
				Part filePart = req.getPart("group_banner_1");
				byte[] group_banner_1 = null;

				InputStream fileContent = filePart.getInputStream();
				group_banner_1 = getPictureByteArray(fileContent);

				Part filePart2 = req.getPart("group_banner_2");
				byte[] group_banner_2 = null;

				InputStream fileContent2 = filePart2.getInputStream();
				group_banner_2 = getPictureByteArray(fileContent2);

				String group_status = req.getParameter("group_status");

				String group_address = req.getParameter("group_address");

				Double latitude = null;
				try {
					latitude = new Double(req.getParameter("latitude").trim());
				} catch (NumberFormatException e) {
					latitude = 0.0;
					errorMsgs.add("格式錯誤");
				}
				Double longitude = null;
				try {
					longitude = new Double(req.getParameter("longitude").trim());
				} catch (NumberFormatException e) {
					longitude = 0.0;
					errorMsgs.add("格式錯誤");
				}
				java.sql.Timestamp group_time = null;
				try {
					group_time = java.sql.Timestamp.valueOf(req.getParameter("group_time").trim());
				} catch (IllegalArgumentException e) {
					group_time = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("格式錯誤");
				}

				Integer group_price = null;
				try {
					group_price = new Integer(req.getParameter("group_price"));
				} catch (NumberFormatException e) {
					group_price = 0;
					errorMsgs.add("價格錯誤");
				}
				Group_openVO group_openVO = new Group_openVO();
				group_openVO.setMember_no(member_no);
				group_openVO.setGoods_no(goods_no);
				group_openVO.setGroup_name(group_name);
				group_openVO.setGroup_limit(group_limit);
				group_openVO.setGroup_introduction(group_introduction);
				group_openVO.setGroup_mind(group_mind);
				group_openVO.setGroup_start_date(group_start_date);
				group_openVO.setGroup_close_date(group_close_date);
				group_openVO.setGroup_banner_1(group_banner_1);
				group_openVO.setGroup_banner_2(group_banner_2);
				group_openVO.setGroup_status(group_status);
				group_openVO.setGroup_address(group_address);
				group_openVO.setLatitude(latitude);
				group_openVO.setLongitude(longitude);
				group_openVO.setGroup_time(group_time);
				group_openVO.setGroup_price(group_price);
				System.out.println("加入成功");

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("group_openVO", group_openVO); //
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/group_open/addgroup_open.jsp");
					failureView.forward(req, res);
					System.out.println("加入失敗");
					return;
				}

				Group_openService grpSvc = new Group_openService();

				Group_memberService group_memberSvc = new Group_memberService();

				group_openVO = grpSvc.addGroup_open(member_no, goods_no, group_name, group_limit, group_introduction,
						group_mind, group_start_date, group_close_date, group_banner_1, group_banner_2, group_status,
						group_address, latitude, longitude, group_time, group_price);

				String url = "/frontend/group_open/listAllgroup_open.jsp";

				RequestDispatcher successView = req.getRequestDispatcher(url);

				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/group_open/addgroup_open.jsp");
				failureView.forward(req, res);
				System.out.println("新增不成功");
				System.out.println(e);

			}

		}

		if ("delete".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {

				String group_no = req.getParameter("group_no");

				Group_openService grpSvc = new Group_openService();

				grpSvc.deleteGroup_open(group_no);

				String url = "/frontend/group_open/listAllgroup_open.jsp";

				RequestDispatcher successView = req.getRequestDispatcher(url);

				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("新增資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/group_open/listAllgroup_open.jsp");
				failureView.forward(req, res);

			}

		}
		if ("get_group_open_Bymember_no".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String member_no = req.getParameter("member_no");
				if (member_no == null || (member_no.trim().length() == 0)) {
					errorMsgs.add("會員編號不可為空");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/group_open/group_open_index.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/************************ 開始查詢資料 *****************************/
				Group_openService group_openSvc = new Group_openService();

				Group_memberService group_memberSvc = new Group_memberService();

				List<Group_openVO> list = group_openSvc.getgroup_openBymember_no(member_no);

				// 如何取的裡面所有的member_no物件 才可以去查

				req.setAttribute("group_openBymember_no", list);

				String url = "/frontend/group_open/listGroup_memberBygroup_no.jsp";

				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("查詢資料失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/group_open/group_open_index.jsp");
				failureView.forward(req, res);
			}

		}

		if ("insert2".equals(action)) {
			System.out.println(action+"526");

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String member_no = req.getParameter("member_no");
				if (member_no == null || member_no.trim().length() == 0) {
					errorMsgs.add("會員編號不可為空");
				}
				String goods_no = req.getParameter("goods_no");
				if (goods_no == null || goods_no.trim().length() == 0) {
					errorMsgs.add("商品編號不可為空");
				}
				String group_name = req.getParameter("group_name");
				String group_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,20}$";
				if (group_name == null || group_name.trim().length() == 0) {
					errorMsgs.add("開團名稱不可為空");
				} else if (!group_name.trim().matches(group_nameReg)) {
					errorMsgs.add("開團名稱不可有空白");
				}

				Integer group_limit = null;
				try {
					group_limit = new Integer(req.getParameter("group_limit"));
				} catch (NumberFormatException e) {
					group_limit = 0;
					errorMsgs.add("開團下限");
				}

				String group_introduction = req.getParameter("group_introduction");

				String group_mind = req.getParameter("group_mind");

				java.sql.Timestamp group_start_date = null;
				try {
//					group_start_date = java.sql.Timestamp.valueOf(req.getParameter("group_start_date").trim());
					group_start_date = new java.sql.Timestamp(System.currentTimeMillis());
				} catch (IllegalArgumentException e) {
					group_start_date = new java.sql.Timestamp(System.currentTimeMillis());
//					errorMsgs.add("時間格式錯誤!");
				}
				java.sql.Timestamp group_close_date = null;
				try {
					group_close_date = java.sql.Timestamp.valueOf(req.getParameter("group_close_date").trim());
					if (group_close_date.after(group_start_date)) {
						group_close_date = java.sql.Timestamp.valueOf(req.getParameter("group_close_date").trim());
					} else {
						errorMsgs.add("關團時間不能低於現在時間喔");
					}
				} catch (IllegalArgumentException e) {
					group_close_date = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("時間格式錯誤");
				}
				Part filePart = req.getPart("group_banner_1");
				byte[] group_banner_1 = null;

				InputStream fileContent = filePart.getInputStream();
				group_banner_1 = getPictureByteArray(fileContent);

				Part filePart2 = req.getPart("group_banner_2");
				byte[] group_banner_2 = null;

				InputStream fileContent2 = filePart2.getInputStream();
				group_banner_2 = getPictureByteArray(fileContent2);

				String group_status = req.getParameter("group_status");

				String group_address = req.getParameter("group_address");

				Double latitude = null;
				try {
					latitude = new Double(req.getParameter("latitude").trim());
				} catch (NumberFormatException e) {
					latitude = 0.0;
					errorMsgs.add("格式錯誤");
				}
				Double longitude = null;
				try {
					longitude = new Double(req.getParameter("longitude").trim());
				} catch (NumberFormatException e) {
					longitude = 0.0;
					errorMsgs.add("格式錯誤");
				}
				java.sql.Timestamp group_time = null;
				try {
					group_time = java.sql.Timestamp.valueOf(req.getParameter("group_time").trim());
				} catch (IllegalArgumentException e) {
					group_time = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("格式錯誤");
				}

//				Integer group_price = null;
//				try {
//					group_price = new Integer(req.getParameter("group_price"));
//				} catch (NumberFormatException e) {
//					group_price = 0;
//					errorMsgs.add("價格錯誤");
//				}
				Integer group_quatity = null;
				try {
					group_quatity = new Integer(req.getParameter("group_quantity"));
				} catch (NumberFormatException e) {
					group_quatity = 0;
					errorMsgs.add("價格錯誤");
				}

				String group_member_status = req.getParameter("group_member_status");
				if (group_member_status == null || group_member_status.trim().length() == 0) {
					errorMsgs.add("隱藏的會員狀態不可為空");
				} else if (!group_name.trim().matches(group_nameReg)) {
					errorMsgs.add("隱藏的會員狀態不可為空");
				}

				Group_openVO group_openVO = new Group_openVO();
				group_openVO.setMember_no(member_no);
				group_openVO.setGoods_no(goods_no);
				group_openVO.setGroup_name(group_name);
				group_openVO.setGroup_limit(group_limit);
				group_openVO.setGroup_introduction(group_introduction);
				group_openVO.setGroup_mind(group_mind);
				group_openVO.setGroup_start_date(group_start_date);
				group_openVO.setGroup_close_date(group_close_date);
				group_openVO.setGroup_banner_1(group_banner_1);
				group_openVO.setGroup_banner_2(group_banner_2);
				group_openVO.setGroup_status(group_status);
				group_openVO.setGroup_address(group_address);
				group_openVO.setLatitude(latitude);
				group_openVO.setLongitude(longitude);
				group_openVO.setGroup_time(group_time);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("group_openVO", group_openVO); //
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/group_open/addgroup_open.jsp");
					failureView.forward(req, res);
					System.out.println("加入失敗");
					return;
				}
				/*************************** 2.開始新增資料 **********************/

				Group_openService group_openSvc = new Group_openService();

				Group_memberService group_memberSvc = new Group_memberService();
				
				GoodsService goodsSvc = new GoodsService();

				Group_memberVO group_memberVO = new Group_memberVO();
				
				MemberService memberSvc = new MemberService();
				
				GoodsVO goodsVO = new GoodsVO();
				
				/*************************** 3.開始扣款資料 **********************/
				//取的購買人購買數量	
//				group_quatity  
				//取得開團折扣
				goodsVO = goodsSvc.getOneGoods(goods_no);
				Integer group_price = goodsVO.getForsales_a();			
				//退款總數
				Integer total = group_quatity*group_price;
				System.out.println("group_memberServlet退款總數"+total);
//				//呼叫會員電子錢包
				Integer ewallet = group_memberSvc.getewallet(member_no);
				System.out.println("group_memberServlet電子錢包"+ewallet);
//				//退款
				ewallet-=total;
				System.out.println("group_memberServlet退款後電子錢包"+ewallet);
//				//更改
				group_memberSvc.updateewallet(ewallet, member_no);
		
				System.out.println("退款完成");

//				group_openVO = grpSvc.addGroup_open(member_no, goods_no, group_name, group_limit, group_introduction,
//						group_mind, group_start_date, group_close_date, group_banner_1, group_banner_2, group_status,
//						group_address, latitude, longitude, group_time, group_price);
//				String group_pricest = group_openSvc.getgroup_price(goods_no);
//				Integer group_price = Integer.valueOf(group_pricest);
				group_openVO.setGroup_price(group_price);
				group_memberVO.setProduct_quantity(group_quatity);
				group_memberVO.setGroup_member_status(group_member_status);

				group_openSvc.add2(group_openVO, group_memberVO);

				List<Group_openVO> group_openBymember_no = group_openSvc.getgroup_openBymember_no(member_no);
				
		
				
				/*************************** 2.轉交顯示資料 **********************/
				req.setAttribute("group_openBymember_no", group_openBymember_no);
				String url = "/frontend/group_open/listGroup_memberBygroup_no.jsp";

				RequestDispatcher successView = req.getRequestDispatcher(url);

				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/group_open/addgroup_open.jsp");
				failureView.forward(req, res);
				System.out.println(e);

			}

		}

		// 顯示跟團資訊 已更改檔案名稱及路徑
		if ("getgroup_for_display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {

				String groupno = req.getParameter("group_no");
				if (groupno == null || (groupno.trim().length() == 0)) {
					errorMsgs.add("開團編號不可為空");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/group_open/group_open_index.jsp");

					failureView.forward(req, res);
					return;
				}

				Group_openService groupSvc = new Group_openService();

				Group_openVO group_openVO = groupSvc.getOneGroup_open(groupno);

				if (groupno == null) {
					errorMsgs.add("開團編號不可為空");
				}
				if (!errorMsgs.isEmpty()) {

					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/group_open/group_open_index.jsp");

					failureView.forward(req, res);
					return;//
				}
				//
				req.setAttribute("group_openVO", group_openVO);
				String url = "/frontend/group_member/addgroup_member.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("無法取得資料" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/group_open/group_open_index.jsp");

				failureView.forward(req, res);

			}

		}
		// 同時刪除開團人及跟團人
		if ("delete2".equals(action)) {
			System.out.println(action);
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
//			*****************取得開團編號**********************
				Group_openService group_openSvc = new Group_openService();
				String group_no = req.getParameter("group_no");
				String member_no = req.getParameter("member_no");
				List<Group_openVO> group_openBymember_no = group_openSvc.getgroup_openBymember_no(member_no);
				System.out.println(group_openBymember_no.get(0).getGroup_name());
				System.out.println(group_openBymember_no.get(0).getGoods_no());
				System.out.println("list的長度" + group_openBymember_no.size());
				System.out.println(group_no);
				System.out.println(member_no);
//			*****************刪除開始**********************

				System.out.println("刪除之前的" + member_no);
				group_openSvc.delete2(group_no);
				System.out.println("刪除之後的" + member_no);
				System.out.println("list的長度" + group_openBymember_no.size());

//			*****************刪除完成準備轉移頁面**********************
				req.setAttribute("group_openBymember_no", group_openBymember_no);
				String url = "/frontend/group_open/listGroup_memberBygroup_no.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("刪除失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/frontend/group_open/listGroup_memberBygroup_no.jsp");
				failureView.forward(req, res);

			}

		}
		if ("listEmps_ByCompositeQuery".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.將輸入資料轉為Map **********************************/

				HttpSession session = req.getSession();

				Map<String, String[]> map = (Map<String, String[]>) session.getAttribute("map");

				if (req.getParameter("whichPage") == null) {
					HashMap<String, String[]> map1 = new HashMap<String, String[]>(req.getParameterMap());
					Set set = map1.keySet();
					Iterator it = set.iterator();
					while (it.hasNext()) {
						Object myket = it.next();
						System.out.println(map1.get(myket));
						System.out.println("陣列取值" + map1.get(myket)[0]);
					}
					session.setAttribute("map", map1);
					map = map1;
				}
				/*************************** 2.開始複合查詢 ***************************************/

				Group_openService group_openSvc = new Group_openService();
				List<Group_openVO> list = group_openSvc.getcompoundsearch(map);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("list", list);
				RequestDispatcher successView = req.getRequestDispatcher("/frontend/group_open/group_open_index.jsp"); 
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/group_open/group_open_index.jsp");
				failureView.forward(req, res);
			}

		}

		if ("quitall".equals(action)) {

			System.out.println("Group_openServlet" + action);
			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);
//		*************************取得請求參數*****************************
			try {
				String group_no = req.getParameter("group_no");
				if (group_no == null || (group_no.trim().length() == 0)) {
					errorMsgs.add("開團編號不可為空");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/group_open/group_open.jsp");

					failureView.forward(req, res);
					return;
				}
				String member_no = req.getParameter("member_no");
				if (group_no == null || (group_no.trim().length() == 0)) {
					errorMsgs.add("開團編號不可為空");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/group_open/listGroup_memberBygroup_no.jsp");

					failureView.forward(req, res);
					return;
				}
				String dimiss_reason = req.getParameter("dimiss_reason");
				System.out.println("Group_openServlet" + dimiss_reason);
//			*************************退團開始動作*****************************

				Group_openService group_openSvc = new Group_openService();

				Group_memberService group_memberSvc = new Group_memberService();
				
				List<Group_memberVO> quitlist = group_memberSvc.getquitgroup_member(group_no);
				//退款
				for(Group_memberVO group_memberVO:quitlist) {
					System.out.println(group_memberVO.getMember_no());
					//取的購買人購買數量
					Integer quantity = group_memberVO.getProduct_quantity();
					System.out.println(quantity);
					//取得開團折扣
					Integer group_price = group_openSvc.getOneGroup_open(group_no).getGroup_price();
					System.out.println(group_price);
					//退款總數
					Integer total = quantity*group_price;
					System.out.println("group_memberServlet退款總數"+total);
					//呼叫會員電子錢包
					Integer ewallet = group_memberSvc.getewallet(group_memberVO.getMember_no());
					System.out.println("group_memberServlet電子錢包"+group_memberVO.getMember_no()+ewallet);
					//退款
					ewallet+=total;
					System.out.println("group_memberServlet退款後電子錢包"+ewallet);
					//更改
					group_memberSvc.updateewallet(ewallet, group_memberVO.getMember_no());
					System.out.println(group_memberVO.getMember_no()+"退款完成");
					
				}
				
				
				
				group_openSvc.getGroup_memberByGroup_no(group_no);

				List<Group_memberVO> list = group_memberSvc.getall_member_dimiss(group_no);
				
				System.out.println("Group_openServlet"+list.size());
				//寄信件
//				for (Group_memberVO group_memberVO : list) {
//					String member_noemail = group_memberVO.getMember_no();
//					String eamil = group_memberSvc.getemail(member_noemail);
//					group_memberSvc.sendMail(eamil, dimiss_reason);
//					System.out.println("Group_openServlet" + member_noemail);
//
//				}
				
				group_openSvc.group_open_quit(group_no);

				group_memberSvc.allgroup_member_quit(group_no);

				List<Group_openVO> lists = group_openSvc.getgroup_openBymember_no(member_no);
//			*************************開始轉交動作*****************************

				req.setAttribute("group_openBymember_no", lists);

				String url = "/frontend/group_open/listGroup_memberBygroup_no.jsp";

				RequestDispatcher successView = req.getRequestDispatcher(url);

				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/frontend/group_open/listGroup_memberBygroup_no.jsp");
				failureView.forward(req, res);
				System.out.println("新增不成功");
				System.out.println(e);

			}
		}
		if ("makeorder".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String group_no = req.getParameter("group_no");
				if (group_no == null || (group_no.trim().length() == 0)) {
					errorMsgs.add("請輸入開團編號");
				}
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
				/************************ 開始取得資料 *****************************/

				Group_memberService group_memberSvc = new Group_memberService();

				Group_openService group_openSvc = new Group_openService();

				List<Group_memberVO> list = group_memberSvc.getgroupsucesslist(group_no);

				Map<String, Integer> map = group_memberSvc.getgroup_quantity();

				List<Group_openVO> list2 = group_openSvc.getAll();

				String producttotal = group_memberSvc.getgroup_member_product(group_no);

				Group_openVO group_openVO = group_openSvc.getOneGroup_open(group_no);

				int group_price = group_openVO.getGroup_price();

				int productquantity = Integer.valueOf(group_memberSvc.getproductquantity(group_no));

				int totali = group_price * productquantity;

				String total = String.valueOf(totali);

				System.out.println(total);

				// 寫到這裡 每個跟團人的明細 包括開團人
				// 要去取得開團總數量 折扣價格
				// 要確定豐森的表格 我都有 然後在jsp呈現
				/************************ 開始轉交資料 *****************************/
				req.setAttribute("groupsucesslist", list);
				req.setAttribute("group_quantity", map);
				req.setAttribute("group_opengetall", list2);
				req.setAttribute("producttotal", producttotal);
				req.setAttribute("total", total);
				req.setAttribute("group_openVO", group_openVO);

				String url = "/frontend/group_open/order.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("無法取得資料" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/frontend/group_open/listGroup_memberBygroup_no.jsp");
				failureView.forward(req, res);
			}

		}
		if("getSelect".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);
			try {
//			******************取得參數*********************
				String evetit_no = req.getParameter("evetit");
				
//			******************查詢資料*********************
				Group_openService group_openSvc = new Group_openService();
				
				Map <String,String> map = group_openSvc.getevetitle_goods(evetit_no);
			
//			******************轉交資料*********************
				
				req.setAttribute("evetitle_goods", map);
				String url = "/frontend/group_open/addgroup_open.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				
				
			}catch (Exception e) {
				errorMsgs.add("無法取得資料" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/frontend/group_open/addgroup_open.jsp");
				failureView.forward(req, res);
			}
			
			
			
			
			
				
		
		
			
		}
		

	}

	public static byte[] getPictureByteArray(InputStream fis) throws IOException {

		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}

	public static String readString(Reader reader) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(reader);
		String str;
		while ((str = br.readLine()) != null) {
			sb.append(str);
			sb.append("\n");
		}
		br.close();

		return sb.toString();
	}

	public static String readString(InputStream fis) throws IOException {
		InputStreamReader isr = new InputStreamReader(fis);
		StringBuilder sb = new StringBuilder();
		int i;
		while ((i = isr.read()) != -1) {
			sb.append((char) i);
		}
		isr.close();
		fis.close();
		String str = sb.toString();
		return str;
	}

	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition"); // �ҥ�78��
		System.out.println("header=" + header); // ���ե�
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
//		System.out.println("filename=" + filename); // ���ե�
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}

	public static byte[] getPictureByteArray(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int i;
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		baos.close();
		fis.close();

		return baos.toByteArray();
	}

}
