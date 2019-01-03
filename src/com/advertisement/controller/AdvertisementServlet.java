package com.advertisement.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.advertisement.model.AdvertisementService;
import com.advertisement.model.AdvertisementVO;
import com.google.gson.Gson;


@WebServlet("/advertisement/AdvertisementServlet.do")
public class AdvertisementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdvertisementServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		// 基本款
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();

		String action = request.getParameter("action");
		
		
		
		
		
//		// 請求來源 : backend -> updateEvent.jsp
//		if ("updateAdvertisement".equals(action)) {
//			
//			String tictype_name = null;
//			try {
//				/****************************** 1.接收請求參數 - 輸入格式的錯誤處理 **************************************************/				
//				String tictype_no = request.getParameter("tictype_no");
//				String tictype_color = request.getParameter("tictype_color");
//				tictype_name = request.getParameter("tictype_name");
//				Integer tictype_price = new Integer(request.getParameter("tictype_price"));
//				 
//				/****************************** 2.開始修改資料 **************************************************/
//				TicketTypeService ticketTypeService = new TicketTypeService();
//				ticketTypeService.updateTicketType(tictype_no, tictype_color, tictype_name, tictype_price);
//
//				/****************************** 3.修改完成,準備轉交 ***************************************************/
//				out.println("  ### " + tictype_name + " 更新成功 !  ");
//
//				/****************************** 其他可能的錯誤處理 **************************************************/
//			} catch (Exception e) {
//				out.println("  ### " + tictype_name + " 更新失敗 : " +  e.getMessage());
//			}
//			
//		}
	

		
		
		// 請求來源 : backend -> listAllAdvertisement.jsp
		if ("deleteAdvertisement".equals(action)) {
			try {
				/****************************** 1.接收請求參數 - 輸入格式的錯誤處理 **************************************************/				
				String ad_no = request.getParameter("ad_no");

				/****************************** 2.開始修改資料 **************************************************/
				AdvertisementService advertisementService = new AdvertisementService();
				advertisementService.deleteAdvertisement(ad_no);
			
				/****************************** 3.修改完成,準備轉交 ***************************************************/
				out.println("  ###" + " 刪除成功");
				
				/****************************** 其他可能的錯誤處理 **************************************************/
			} catch (Exception e) {
				out.println("  ###" + " 刪除失敗 : " +  e.getMessage());
			}
		}
		
		
		
		// 請求來源 : backend -> listAllAdvertisement.jsp
		if ("addAdvertisement".equals(action)) {
			try {
				/****************************** 1.接收請求參數 - 輸入格式的錯誤處理 **************************************************/				
				String evetit_no = request.getParameter("evetit_no");
				java.sql.Date ad_startdate = java.sql.Date.valueOf(request.getParameter("ad_startdate").trim());
				java.sql.Date ad_enddate = java.sql.Date.valueOf(request.getParameter("ad_enddate").trim());

				/****************************** 2.開始修改資料 **************************************************/
				AdvertisementService advertisementService = new AdvertisementService();
				AdvertisementVO advertisementVO = advertisementService.addAdvertisement(evetit_no, ad_startdate, ad_enddate);
				String ad_no = advertisementVO.getAd_no();
				
				/****************************** 3.修改完成,準備轉交 ***************************************************/
				out.println(ad_no);
				
				/****************************** 其他可能的錯誤處理 **************************************************/
			} catch (Exception e) {
				out.println("  ###" + " 新增失敗 : " +  e.getMessage());
			}
		}
		
		
		
	}

}
