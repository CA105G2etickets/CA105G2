package com.ticket_type.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.seating_area.model.SeatingAreaService;
import com.seating_area.model.SeatingAreaVO;
import com.ticket_type.model.TicketTypeService;
import com.ticket_type.model.TicketTypeVO;

@WebServlet("/ticket_type/TicketTypeServlet.do")
public class TicketTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TicketTypeServlet() {
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
		
		
		
		
		
		// 請求來源 : backend -> updateEvent.jsp
		if ("updateTicketType".equals(action)) {
			
			String tictype_name = null;
			try {
				/****************************** 1.接收請求參數 - 輸入格式的錯誤處理 **************************************************/				
				String tictype_no = request.getParameter("tictype_no");
				String tictype_color = request.getParameter("tictype_color");
				tictype_name = request.getParameter("tictype_name");
				Integer tictype_price = new Integer(request.getParameter("tictype_price"));
				 
				/****************************** 2.開始修改資料 **************************************************/
				TicketTypeService ticketTypeService = new TicketTypeService();
				ticketTypeService.updateTicketType(tictype_no, tictype_color, tictype_name, tictype_price);

				/****************************** 3.修改完成,準備轉交 ***************************************************/
				out.println("  ### " + tictype_name + " 更新成功 !  ");

				/****************************** 其他可能的錯誤處理 **************************************************/
			} catch (Exception e) {
				out.println("  ### " + tictype_name + " 更新失敗 : " +  e.getMessage());
			}
			
		}
	

		
		
		
		// 請求來源 : backend -> updateEvent.jsp
		if ("deleteTicketType".equals(action)) {
			
			String tictype_name = null;
			try {
				/****************************** 1.接收請求參數 - 輸入格式的錯誤處理 **************************************************/				
				String tictype_no = request.getParameter("tictype_no");
				tictype_name = request.getParameter("tictype_name");
				 
				/****************************** 2.開始修改資料 **************************************************/
				TicketTypeService ticketTypeService = new TicketTypeService();
				ticketTypeService.deleteTicketType(tictype_no);

				/****************************** 3.修改完成,準備轉交 ***************************************************/
				out.println("  ### " + tictype_name + " 刪除成功 !  ");

				/****************************** 其他可能的錯誤處理 **************************************************/
			} catch (Exception e) {
				out.println("  ### " + tictype_name + " 刪除失敗 : " +  e.getMessage());
			}
			
		}
		
		
		
		
		
		// 請求來源 : backend -> updateEvent.jsp
		if ("addTicketType".equals(action)) {
			
			try {
				/****************************** 1.接收請求參數 - 輸入格式的錯誤處理 **************************************************/				
				String eve_no = request.getParameter("eve_no");
				 
				/****************************** 2.開始修改資料 **************************************************/
				TicketTypeService ticketTypeService = new TicketTypeService();
				TicketTypeVO ticketTypeVO = ticketTypeService.addTicketType(eve_no);
				Gson gson = new Gson();				
				String ticketTypeVOjsonStr = gson.toJson(ticketTypeVO);
				
				/****************************** 3.修改完成,準備轉交 ***************************************************/
				out.println(ticketTypeVOjsonStr);
				
				/****************************** 其他可能的錯誤處理 **************************************************/
			} catch (Exception e) {
				out.println("  ###" + " 新增失敗 : " +  e.getMessage());
			}
			
		}
		
		
		
		
		
		// 請求來源 : backend -> updateEvent.jsp
		if ("copyTicketType".equals(action)) {
			
			try {
				/****************************** 1.接收請求參數 - 輸入格式的錯誤處理 **************************************************/				
				String tictype_no_forCopy = request.getParameter("tictype_no_forCopy");
				 
				/****************************** 2.開始修改資料 **************************************************/
				TicketTypeService ticketTypeService = new TicketTypeService();
				String tictype_no = ticketTypeService.copyInsertWithSeatingArea(tictype_no_forCopy);
				List<Object> list = new ArrayList<>();
				TicketTypeVO ticketTypeVO = ticketTypeService.getOneTicketType(tictype_no);
				list.add(ticketTypeVO);
				Set<SeatingAreaVO> seatingAreaVoSet = ticketTypeService.getSeatingAreasByTicketType(tictype_no);
				for(SeatingAreaVO aSeatingAreaVO : seatingAreaVoSet) {
					list.add(aSeatingAreaVO);
				}
				
				Gson gson = new Gson();				
				String listStr = gson.toJson(list);
				
				/****************************** 3.修改完成,準備轉交 ***************************************************/
				out.println(listStr);
				
				/****************************** 其他可能的錯誤處理 **************************************************/
			} catch (Exception e) {
				out.println("  ###" + " 複製失敗 : " +  e.getMessage());
			}
			
		}
		
		
		
		
		
		// 請求來源 : backend -> updateEvent.jsp
		if ("demo".equals(action)) {

			Map<String, String> eventErrorMsgs = new LinkedHashMap<String, String>();
			request.setAttribute("eventErrorMsgs", eventErrorMsgs);
			String eve_no = null;
			try {
				/****************************** 1.接收請求參數 **************************************************/	
				
				eve_no = request.getParameter("eve_no");
				
				Map<TicketTypeVO, List<SeatingAreaVO>> ticketTypeVOMap = new LinkedHashMap<>();
				
				// -----5800區-----
				TicketTypeVO ticketTypeVO_A = new TicketTypeVO();
				ticketTypeVO_A.setEve_no(eve_no);
				ticketTypeVO_A.setTictype_color("#ff007f");
				ticketTypeVO_A.setTictype_name("5800區");
				ticketTypeVO_A.setTictype_price(new Integer(5800));
				List<SeatingAreaVO> seatingAreaList_A = new ArrayList<>();
				SeatingAreaVO seatingareaVO_A01 = new SeatingAreaVO();	
				seatingareaVO_A01.setEve_no(eve_no);
				seatingareaVO_A01.setTicarea_color("#ff007f");
				seatingareaVO_A01.setTicarea_name("搖滾A區5800");
				seatingareaVO_A01.setTictotalnumber(new Integer(2000));
				seatingareaVO_A01.setTicbookednumber(new Integer(0));
				seatingAreaList_A.add(seatingareaVO_A01);
				SeatingAreaVO seatingareaVO_A02 = new SeatingAreaVO();	
				seatingareaVO_A02.setEve_no(eve_no);
				seatingareaVO_A02.setTicarea_color("#ff007f");
				seatingareaVO_A02.setTicarea_name("搖滾B區5800");
				seatingareaVO_A02.setTictotalnumber(new Integer(2000));
				seatingareaVO_A02.setTicbookednumber(new Integer(0));
				seatingAreaList_A.add(seatingareaVO_A02);
				SeatingAreaVO seatingareaVO_A03 = new SeatingAreaVO();	
				seatingareaVO_A03.setEve_no(eve_no);
				seatingareaVO_A03.setTicarea_color("#ff007f");
				seatingareaVO_A03.setTicarea_name("搖滾C區5800");
				seatingareaVO_A03.setTictotalnumber(new Integer(2000));
				seatingareaVO_A03.setTicbookednumber(new Integer(0));
				seatingAreaList_A.add(seatingareaVO_A03);
				ticketTypeVOMap.put(ticketTypeVO_A, seatingAreaList_A);
				
				// -----4800區-----
				TicketTypeVO ticketTypeVO_B = new TicketTypeVO();
				ticketTypeVO_B.setEve_no(eve_no);
				ticketTypeVO_B.setTictype_color("#ffaaff");
				ticketTypeVO_B.setTictype_name("4800區");
				ticketTypeVO_B.setTictype_price(new Integer(4800));
				List<SeatingAreaVO> seatingAreaList_B = new ArrayList<>();
				SeatingAreaVO seatingareaVO_B01 = new SeatingAreaVO();	
				seatingareaVO_B01.setEve_no(eve_no);
				seatingareaVO_B01.setTicarea_color("#ffaaff");
				seatingareaVO_B01.setTicarea_name("橙一B雙區4800");
				seatingareaVO_B01.setTictotalnumber(new Integer(2000));
				seatingareaVO_B01.setTicbookednumber(new Integer(0));
				seatingAreaList_B.add(seatingareaVO_B01);
				SeatingAreaVO seatingareaVO_B02 = new SeatingAreaVO();	
				seatingareaVO_B02.setEve_no(eve_no);
				seatingareaVO_B02.setTicarea_color("#ffaaff");
				seatingareaVO_B02.setTicarea_name("橙二B單區4800");
				seatingareaVO_B02.setTictotalnumber(new Integer(2000));
				seatingareaVO_B02.setTicbookednumber(new Integer(0));
				seatingAreaList_B.add(seatingareaVO_B02);
				SeatingAreaVO seatingareaVO_B03 = new SeatingAreaVO();	
				seatingareaVO_B03.setEve_no(eve_no);
				seatingareaVO_B03.setTicarea_color("#ffaaff");
				seatingareaVO_B03.setTicarea_name("橙二B雙區4800");
				seatingareaVO_B03.setTictotalnumber(new Integer(2000));
				seatingareaVO_B03.setTicbookednumber(new Integer(0));
				seatingAreaList_B.add(seatingareaVO_B03);
				SeatingAreaVO seatingareaVO_B04 = new SeatingAreaVO();	
				seatingareaVO_B04.setEve_no(eve_no);
				seatingareaVO_B04.setTicarea_color("#ffaaff");
				seatingareaVO_B04.setTicarea_name("橙四B單區4800");
				seatingareaVO_B04.setTictotalnumber(new Integer(2000));
				seatingareaVO_B04.setTicbookednumber(new Integer(0));
				seatingAreaList_B.add(seatingareaVO_B04);
				SeatingAreaVO seatingareaVO_B05 = new SeatingAreaVO();	
				seatingareaVO_B05.setEve_no(eve_no);
				seatingareaVO_B05.setTicarea_color("#ffaaff");
				seatingareaVO_B05.setTicarea_name("橙四B雙區4800");
				seatingareaVO_B05.setTictotalnumber(new Integer(2000));
				seatingareaVO_B05.setTicbookednumber(new Integer(0));
				seatingAreaList_B.add(seatingareaVO_B05);
				SeatingAreaVO seatingareaVO_B06 = new SeatingAreaVO();	
				seatingareaVO_B06.setEve_no(eve_no);
				seatingareaVO_B06.setTicarea_color("#ffaaff");
				seatingareaVO_B06.setTicarea_name("藍一B單區4800");
				seatingareaVO_B06.setTictotalnumber(new Integer(2000));
				seatingareaVO_B06.setTicbookednumber(new Integer(0));
				seatingAreaList_B.add(seatingareaVO_B06);
				SeatingAreaVO seatingareaVO_B07 = new SeatingAreaVO();	
				seatingareaVO_B07.setEve_no(eve_no);
				seatingareaVO_B07.setTicarea_color("#ffaaff");
				seatingareaVO_B07.setTicarea_name("藍一B雙區4800");
				seatingareaVO_B07.setTictotalnumber(new Integer(2000));
				seatingareaVO_B07.setTicbookednumber(new Integer(0));
				seatingAreaList_B.add(seatingareaVO_B07);
				SeatingAreaVO seatingareaVO_B08 = new SeatingAreaVO();	
				seatingareaVO_B08.setEve_no(eve_no);
				seatingareaVO_B08.setTicarea_color("#ffaaff");
				seatingareaVO_B08.setTicarea_name("藍二B單區4800");
				seatingareaVO_B08.setTictotalnumber(new Integer(2000));
				seatingareaVO_B08.setTicbookednumber(new Integer(0));
				seatingAreaList_B.add(seatingareaVO_B08);
				SeatingAreaVO seatingareaVO_B09 = new SeatingAreaVO();	
				seatingareaVO_B09.setEve_no(eve_no);
				seatingareaVO_B09.setTicarea_color("#ffaaff");
				seatingareaVO_B09.setTicarea_name("藍二B雙區4800");
				seatingareaVO_B09.setTictotalnumber(new Integer(2000));
				seatingareaVO_B09.setTicbookednumber(new Integer(0));
				seatingAreaList_B.add(seatingareaVO_B09);
				SeatingAreaVO seatingareaVO_B10 = new SeatingAreaVO();	
				seatingareaVO_B10.setEve_no(eve_no);
				seatingareaVO_B10.setTicarea_color("#ffaaff");
				seatingareaVO_B10.setTicarea_name("藍三B單區4800");
				seatingareaVO_B10.setTictotalnumber(new Integer(2000));
				seatingareaVO_B10.setTicbookednumber(new Integer(0));
				seatingAreaList_B.add(seatingareaVO_B10);
				SeatingAreaVO seatingareaVO_B11 = new SeatingAreaVO();	
				seatingareaVO_B11.setEve_no(eve_no);
				seatingareaVO_B11.setTicarea_color("#ffaaff");
				seatingareaVO_B11.setTicarea_name("藍三B雙區4800");
				seatingareaVO_B11.setTictotalnumber(new Integer(2000));
				seatingareaVO_B11.setTicbookednumber(new Integer(0));
				seatingAreaList_B.add(seatingareaVO_B11);
				SeatingAreaVO seatingareaVO_B12 = new SeatingAreaVO();	
				seatingareaVO_B12.setEve_no(eve_no);
				seatingareaVO_B12.setTicarea_color("#ffaaff");
				seatingareaVO_B12.setTicarea_name("藍四B單區4800");
				seatingareaVO_B12.setTictotalnumber(new Integer(2000));
				seatingareaVO_B12.setTicbookednumber(new Integer(0));
				seatingAreaList_B.add(seatingareaVO_B12);
				SeatingAreaVO seatingareaVO_B13 = new SeatingAreaVO();	
				seatingareaVO_B13.setEve_no(eve_no);
				seatingareaVO_B13.setTicarea_color("#ffaaff");
				seatingareaVO_B13.setTicarea_name("藍四B雙區4800");
				seatingareaVO_B13.setTictotalnumber(new Integer(2000));
				seatingareaVO_B13.setTicbookednumber(new Integer(0));
				seatingAreaList_B.add(seatingareaVO_B13);
				SeatingAreaVO seatingareaVO_B14 = new SeatingAreaVO();	
				seatingareaVO_B14.setEve_no(eve_no);
				seatingareaVO_B14.setTicarea_color("#ffaaff");
				seatingareaVO_B14.setTicarea_name("藍五B單區4800");
				seatingareaVO_B14.setTictotalnumber(new Integer(2000));
				seatingareaVO_B14.setTicbookednumber(new Integer(0));
				seatingAreaList_B.add(seatingareaVO_B14);
				SeatingAreaVO seatingareaVO_B15 = new SeatingAreaVO();	
				seatingareaVO_B15.setEve_no(eve_no);
				seatingareaVO_B15.setTicarea_color("#ffaaff");
				seatingareaVO_B15.setTicarea_name("藍五B雙區4800");
				seatingareaVO_B15.setTictotalnumber(new Integer(2000));
				seatingareaVO_B15.setTicbookednumber(new Integer(0));
				seatingAreaList_B.add(seatingareaVO_B15);
				SeatingAreaVO seatingareaVO_B16 = new SeatingAreaVO();	
				seatingareaVO_B16.setEve_no(eve_no);
				seatingareaVO_B16.setTicarea_color("#ffaaff");
				seatingareaVO_B16.setTicarea_name("黃四B單區4800");
				seatingareaVO_B16.setTictotalnumber(new Integer(2000));
				seatingareaVO_B16.setTicbookednumber(new Integer(0));
				seatingAreaList_B.add(seatingareaVO_B16);
				SeatingAreaVO seatingareaVO_B17 = new SeatingAreaVO();	
				seatingareaVO_B17.setEve_no(eve_no);
				seatingareaVO_B17.setTicarea_color("#ffaaff");
				seatingareaVO_B17.setTicarea_name("黃四B雙區4800");
				seatingareaVO_B17.setTictotalnumber(new Integer(2000));
				seatingareaVO_B17.setTicbookednumber(new Integer(0));
				seatingAreaList_B.add(seatingareaVO_B17);
				SeatingAreaVO seatingareaVO_B18 = new SeatingAreaVO();	
				seatingareaVO_B18.setEve_no(eve_no);
				seatingareaVO_B18.setTicarea_color("#ffaaff");
				seatingareaVO_B18.setTicarea_name("黃二B單區4800");
				seatingareaVO_B18.setTictotalnumber(new Integer(2000));
				seatingareaVO_B18.setTicbookednumber(new Integer(0));
				seatingAreaList_B.add(seatingareaVO_B18);
				SeatingAreaVO seatingareaVO_B19 = new SeatingAreaVO();	
				seatingareaVO_B19.setEve_no(eve_no);
				seatingareaVO_B19.setTicarea_color("#ffaaff");
				seatingareaVO_B19.setTicarea_name("黃二B雙區4800");
				seatingareaVO_B19.setTictotalnumber(new Integer(2000));
				seatingareaVO_B19.setTicbookednumber(new Integer(0));
				seatingAreaList_B.add(seatingareaVO_B19);
				SeatingAreaVO seatingareaVO_B20 = new SeatingAreaVO();	
				seatingareaVO_B20.setEve_no(eve_no);
				seatingareaVO_B20.setTicarea_color("#ffaaff");
				seatingareaVO_B20.setTicarea_name("黃一B單區4800");
				seatingareaVO_B20.setTictotalnumber(new Integer(2000));
				seatingareaVO_B20.setTicbookednumber(new Integer(0));
				seatingAreaList_B.add(seatingareaVO_B20);
				ticketTypeVOMap.put(ticketTypeVO_B, seatingAreaList_B);
				
				// -----3800區-----
				TicketTypeVO ticketTypeVO_C = new TicketTypeVO();
				ticketTypeVO_C.setEve_no(eve_no);
				ticketTypeVO_C.setTictype_color("#ffe556");
				ticketTypeVO_C.setTictype_name("3800區");
				ticketTypeVO_C.setTictype_price(new Integer(3800));
				List<SeatingAreaVO> seatingAreaList_C = new ArrayList<>();
				SeatingAreaVO seatingareaVO_C01 = new SeatingAreaVO();	
				seatingareaVO_C01.setEve_no(eve_no);
				seatingareaVO_C01.setTicarea_color("#ffe556");
				seatingareaVO_C01.setTicarea_name("橙一B雙區3800");
				seatingareaVO_C01.setTictotalnumber(new Integer(2000));
				seatingareaVO_C01.setTicbookednumber(new Integer(0));
				seatingAreaList_C.add(seatingareaVO_C01);
				SeatingAreaVO seatingareaVO_C02 = new SeatingAreaVO();	
				seatingareaVO_C02.setEve_no(eve_no);
				seatingareaVO_C02.setTicarea_color("#ffe556");
				seatingareaVO_C02.setTicarea_name("橙二B單區3800");
				seatingareaVO_C02.setTictotalnumber(new Integer(2000));
				seatingareaVO_C02.setTicbookednumber(new Integer(0));
				seatingAreaList_C.add(seatingareaVO_C02);
				SeatingAreaVO seatingareaVO_C03 = new SeatingAreaVO();	
				seatingareaVO_C03.setEve_no(eve_no);
				seatingareaVO_C03.setTicarea_color("#ffe556");
				seatingareaVO_C03.setTicarea_name("橙二B雙區3800");
				seatingareaVO_C03.setTictotalnumber(new Integer(2000));
				seatingareaVO_C03.setTicbookednumber(new Integer(0));
				seatingAreaList_C.add(seatingareaVO_C03);
				SeatingAreaVO seatingareaVO_C04 = new SeatingAreaVO();	
				seatingareaVO_C04.setEve_no(eve_no);
				seatingareaVO_C04.setTicarea_color("#ffe556");
				seatingareaVO_C04.setTicarea_name("橙四B單區3800");
				seatingareaVO_C04.setTictotalnumber(new Integer(2000));
				seatingareaVO_C04.setTicbookednumber(new Integer(0));
				seatingAreaList_C.add(seatingareaVO_C04);
				SeatingAreaVO seatingareaVO_C05 = new SeatingAreaVO();	
				seatingareaVO_C05.setEve_no(eve_no);
				seatingareaVO_C05.setTicarea_color("#ffe556");
				seatingareaVO_C05.setTicarea_name("橙四B雙區3800");
				seatingareaVO_C05.setTictotalnumber(new Integer(2000));
				seatingareaVO_C05.setTicbookednumber(new Integer(0));
				seatingAreaList_C.add(seatingareaVO_C05);
				SeatingAreaVO seatingareaVO_C06 = new SeatingAreaVO();	
				seatingareaVO_C06.setEve_no(eve_no);
				seatingareaVO_C06.setTicarea_color("#ffe556");
				seatingareaVO_C06.setTicarea_name("藍一B單區3800");
				seatingareaVO_C06.setTictotalnumber(new Integer(2000));
				seatingareaVO_C06.setTicbookednumber(new Integer(0));
				seatingAreaList_C.add(seatingareaVO_C06);
				SeatingAreaVO seatingareaVO_C07 = new SeatingAreaVO();	
				seatingareaVO_C07.setEve_no(eve_no);
				seatingareaVO_C07.setTicarea_color("#ffe556");
				seatingareaVO_C07.setTicarea_name("藍一B雙區3800");
				seatingareaVO_C07.setTictotalnumber(new Integer(2000));
				seatingareaVO_C07.setTicbookednumber(new Integer(0));
				seatingAreaList_C.add(seatingareaVO_C07);
				SeatingAreaVO seatingareaVO_C08 = new SeatingAreaVO();	
				seatingareaVO_C08.setEve_no(eve_no);
				seatingareaVO_C08.setTicarea_color("#ffe556");
				seatingareaVO_C08.setTicarea_name("藍二B單區3800");
				seatingareaVO_C08.setTictotalnumber(new Integer(2000));
				seatingareaVO_C08.setTicbookednumber(new Integer(0));
				seatingAreaList_C.add(seatingareaVO_C08);
				SeatingAreaVO seatingareaVO_C09 = new SeatingAreaVO();	
				seatingareaVO_C09.setEve_no(eve_no);
				seatingareaVO_C09.setTicarea_color("#ffe556");
				seatingareaVO_C09.setTicarea_name("藍二B雙區3800");
				seatingareaVO_C09.setTictotalnumber(new Integer(2000));
				seatingareaVO_C09.setTicbookednumber(new Integer(0));
				seatingAreaList_C.add(seatingareaVO_C09);
				SeatingAreaVO seatingareaVO_C10 = new SeatingAreaVO();	
				seatingareaVO_C10.setEve_no(eve_no);
				seatingareaVO_C10.setTicarea_color("#ffe556");
				seatingareaVO_C10.setTicarea_name("藍三B單區3800");
				seatingareaVO_C10.setTictotalnumber(new Integer(2000));
				seatingareaVO_C10.setTicbookednumber(new Integer(0));
				seatingAreaList_C.add(seatingareaVO_C10);
				SeatingAreaVO seatingareaVO_C11 = new SeatingAreaVO();	
				seatingareaVO_C11.setEve_no(eve_no);
				seatingareaVO_C11.setTicarea_color("#ffe556");
				seatingareaVO_C11.setTicarea_name("藍三B雙區3800");
				seatingareaVO_C11.setTictotalnumber(new Integer(2000));
				seatingareaVO_C11.setTicbookednumber(new Integer(0));
				seatingAreaList_C.add(seatingareaVO_C11);
				SeatingAreaVO seatingareaVO_C12 = new SeatingAreaVO();	
				seatingareaVO_C12.setEve_no(eve_no);
				seatingareaVO_C12.setTicarea_color("#ffe556");
				seatingareaVO_C12.setTicarea_name("藍四B單區3800");
				seatingareaVO_C12.setTictotalnumber(new Integer(2000));
				seatingareaVO_C12.setTicbookednumber(new Integer(0));
				seatingAreaList_C.add(seatingareaVO_C12);
				SeatingAreaVO seatingareaVO_C13 = new SeatingAreaVO();	
				seatingareaVO_C13.setEve_no(eve_no);
				seatingareaVO_C13.setTicarea_color("#ffe556");
				seatingareaVO_C13.setTicarea_name("藍四B雙區3800");
				seatingareaVO_C13.setTictotalnumber(new Integer(2000));
				seatingareaVO_C13.setTicbookednumber(new Integer(0));
				seatingAreaList_C.add(seatingareaVO_C13);
				SeatingAreaVO seatingareaVO_C14 = new SeatingAreaVO();	
				seatingareaVO_C14.setEve_no(eve_no);
				seatingareaVO_C14.setTicarea_color("#ffe556");
				seatingareaVO_C14.setTicarea_name("藍五B單區3800");
				seatingareaVO_C14.setTictotalnumber(new Integer(2000));
				seatingareaVO_C14.setTicbookednumber(new Integer(0));
				seatingAreaList_C.add(seatingareaVO_C14);
				SeatingAreaVO seatingareaVO_C15 = new SeatingAreaVO();	
				seatingareaVO_C15.setEve_no(eve_no);
				seatingareaVO_C15.setTicarea_color("#ffe556");
				seatingareaVO_C15.setTicarea_name("藍五B雙區3800");
				seatingareaVO_C15.setTictotalnumber(new Integer(2000));
				seatingareaVO_C15.setTicbookednumber(new Integer(0));
				seatingAreaList_C.add(seatingareaVO_C15);
				SeatingAreaVO seatingareaVO_C16 = new SeatingAreaVO();	
				seatingareaVO_C16.setEve_no(eve_no);
				seatingareaVO_C16.setTicarea_color("#ffe556");
				seatingareaVO_C16.setTicarea_name("黃四B單區3800");
				seatingareaVO_C16.setTictotalnumber(new Integer(2000));
				seatingareaVO_C16.setTicbookednumber(new Integer(0));
				seatingAreaList_C.add(seatingareaVO_C16);
				SeatingAreaVO seatingareaVO_C17 = new SeatingAreaVO();	
				seatingareaVO_C17.setEve_no(eve_no);
				seatingareaVO_C17.setTicarea_color("#ffe556");
				seatingareaVO_C17.setTicarea_name("黃四B雙區3800");
				seatingareaVO_C17.setTictotalnumber(new Integer(2000));
				seatingareaVO_C17.setTicbookednumber(new Integer(0));
				seatingAreaList_C.add(seatingareaVO_C17);
				SeatingAreaVO seatingareaVO_C18 = new SeatingAreaVO();	
				seatingareaVO_C18.setEve_no(eve_no);
				seatingareaVO_C18.setTicarea_color("#ffe556");
				seatingareaVO_C18.setTicarea_name("黃二B單區3800");
				seatingareaVO_C18.setTictotalnumber(new Integer(2000));
				seatingareaVO_C18.setTicbookednumber(new Integer(0));
				seatingAreaList_C.add(seatingareaVO_C18);
				SeatingAreaVO seatingareaVO_C19 = new SeatingAreaVO();	
				seatingareaVO_C19.setEve_no(eve_no);
				seatingareaVO_C19.setTicarea_color("#ffe556");
				seatingareaVO_C19.setTicarea_name("黃二B雙區3800");
				seatingareaVO_C19.setTictotalnumber(new Integer(2000));
				seatingareaVO_C19.setTicbookednumber(new Integer(0));
				seatingAreaList_C.add(seatingareaVO_C19);
				SeatingAreaVO seatingareaVO_C20 = new SeatingAreaVO();	
				seatingareaVO_C20.setEve_no(eve_no);
				seatingareaVO_C20.setTicarea_color("#ffe556");
				seatingareaVO_C20.setTicarea_name("黃一B單區3800");
				seatingareaVO_C20.setTictotalnumber(new Integer(2000));
				seatingareaVO_C20.setTicbookednumber(new Integer(0));
				seatingAreaList_C.add(seatingareaVO_C20);
				ticketTypeVOMap.put(ticketTypeVO_C, seatingAreaList_C);
				
				// -----3300區-----
				TicketTypeVO ticketTypeVO_D = new TicketTypeVO();
				ticketTypeVO_D.setEve_no(eve_no);
				ticketTypeVO_D.setTictype_color("#aad3ff");
				ticketTypeVO_D.setTictype_name("3300區");
				ticketTypeVO_D.setTictype_price(new Integer(3300));
				List<SeatingAreaVO> seatingAreaList_D = new ArrayList<>();
				SeatingAreaVO seatingareaVO_D01 = new SeatingAreaVO();	
				seatingareaVO_D01.setEve_no(eve_no);
				seatingareaVO_D01.setTicarea_color("#aad3ff");
				seatingareaVO_D01.setTicarea_name("橙2A-1區3300");
				seatingareaVO_D01.setTictotalnumber(new Integer(2000));
				seatingareaVO_D01.setTicbookednumber(new Integer(0));
				seatingAreaList_D.add(seatingareaVO_D01);
				SeatingAreaVO seatingareaVO_D02 = new SeatingAreaVO();	
				seatingareaVO_D02.setEve_no(eve_no);
				seatingareaVO_D02.setTicarea_color("#aad3ff");
				seatingareaVO_D02.setTicarea_name("橙2A-2區3300");
				seatingareaVO_D02.setTictotalnumber(new Integer(2000));
				seatingareaVO_D02.setTicbookednumber(new Integer(0));
				seatingAreaList_D.add(seatingareaVO_D02);
				SeatingAreaVO seatingareaVO_D03 = new SeatingAreaVO();	
				seatingareaVO_D03.setEve_no(eve_no);
				seatingareaVO_D03.setTicarea_color("#aad3ff");
				seatingareaVO_D03.setTicarea_name("橙4A-1區3300");
				seatingareaVO_D03.setTictotalnumber(new Integer(2000));
				seatingareaVO_D03.setTicbookednumber(new Integer(0));
				seatingAreaList_D.add(seatingareaVO_D03);
				SeatingAreaVO seatingareaVO_D04 = new SeatingAreaVO();	
				seatingareaVO_D04.setEve_no(eve_no);
				seatingareaVO_D04.setTicarea_color("#aad3ff");
				seatingareaVO_D04.setTicarea_name("橙4A-2區3300");
				seatingareaVO_D04.setTictotalnumber(new Integer(2000));
				seatingareaVO_D04.setTicbookednumber(new Integer(0));
				seatingAreaList_D.add(seatingareaVO_D04);
				SeatingAreaVO seatingareaVO_D05 = new SeatingAreaVO();	
				seatingareaVO_D05.setEve_no(eve_no);
				seatingareaVO_D05.setTicarea_color("#aad3ff");
				seatingareaVO_D05.setTicarea_name("藍1A-1區3300");
				seatingareaVO_D05.setTictotalnumber(new Integer(2000));
				seatingareaVO_D05.setTicbookednumber(new Integer(0));
				seatingAreaList_D.add(seatingareaVO_D05);
				SeatingAreaVO seatingareaVO_D06 = new SeatingAreaVO();	
				seatingareaVO_D06.setEve_no(eve_no);
				seatingareaVO_D06.setTicarea_color("#aad3ff");
				seatingareaVO_D06.setTicarea_name("藍1A-2區3300");
				seatingareaVO_D06.setTictotalnumber(new Integer(2000));
				seatingareaVO_D06.setTicbookednumber(new Integer(0));
				seatingAreaList_D.add(seatingareaVO_D06);
				SeatingAreaVO seatingareaVO_D07 = new SeatingAreaVO();	
				seatingareaVO_D07.setEve_no(eve_no);
				seatingareaVO_D07.setTicarea_color("#aad3ff");
				seatingareaVO_D07.setTicarea_name("藍2A-1區3300");
				seatingareaVO_D07.setTictotalnumber(new Integer(2000));
				seatingareaVO_D07.setTicbookednumber(new Integer(0));
				seatingAreaList_D.add(seatingareaVO_D07);
				SeatingAreaVO seatingareaVO_D08 = new SeatingAreaVO();	
				seatingareaVO_D08.setEve_no(eve_no);
				seatingareaVO_D08.setTicarea_color("#aad3ff");
				seatingareaVO_D08.setTicarea_name("藍2A-2區3300");
				seatingareaVO_D08.setTictotalnumber(new Integer(2000));
				seatingareaVO_D08.setTicbookednumber(new Integer(0));
				seatingAreaList_D.add(seatingareaVO_D08);
				SeatingAreaVO seatingareaVO_D09 = new SeatingAreaVO();	
				seatingareaVO_D09.setEve_no(eve_no);
				seatingareaVO_D09.setTicarea_color("#aad3ff");
				seatingareaVO_D09.setTicarea_name("藍3A-1區3300");
				seatingareaVO_D09.setTictotalnumber(new Integer(2000));
				seatingareaVO_D09.setTicbookednumber(new Integer(0));
				seatingAreaList_D.add(seatingareaVO_D09);
				SeatingAreaVO seatingareaVO_D10 = new SeatingAreaVO();	
				seatingareaVO_D10.setEve_no(eve_no);
				seatingareaVO_D10.setTicarea_color("#aad3ff");
				seatingareaVO_D10.setTicarea_name("藍4A-1區3300");
				seatingareaVO_D10.setTictotalnumber(new Integer(2000));
				seatingareaVO_D10.setTicbookednumber(new Integer(0));
				seatingAreaList_D.add(seatingareaVO_D10);
				SeatingAreaVO seatingareaVO_D11 = new SeatingAreaVO();	
				seatingareaVO_D11.setEve_no(eve_no);
				seatingareaVO_D11.setTicarea_color("#aad3ff");
				seatingareaVO_D11.setTicarea_name("藍4A-2區3300");
				seatingareaVO_D11.setTictotalnumber(new Integer(2000));
				seatingareaVO_D11.setTicbookednumber(new Integer(0));
				seatingAreaList_D.add(seatingareaVO_D11);
				SeatingAreaVO seatingareaVO_D12 = new SeatingAreaVO();	
				seatingareaVO_D12.setEve_no(eve_no);
				seatingareaVO_D12.setTicarea_color("#aad3ff");
				seatingareaVO_D12.setTicarea_name("藍5A-1區3300");
				seatingareaVO_D12.setTictotalnumber(new Integer(2000));
				seatingareaVO_D12.setTicbookednumber(new Integer(0));
				seatingAreaList_D.add(seatingareaVO_D12);
				SeatingAreaVO seatingareaVO_D13 = new SeatingAreaVO();	
				seatingareaVO_D13.setEve_no(eve_no);
				seatingareaVO_D13.setTicarea_color("#aad3ff");
				seatingareaVO_D13.setTicarea_name("藍5A-2區3300");
				seatingareaVO_D13.setTictotalnumber(new Integer(2000));
				seatingareaVO_D13.setTicbookednumber(new Integer(0));
				seatingAreaList_D.add(seatingareaVO_D13);
				SeatingAreaVO seatingareaVO_D14 = new SeatingAreaVO();	
				seatingareaVO_D14.setEve_no(eve_no);
				seatingareaVO_D14.setTicarea_color("#aad3ff");
				seatingareaVO_D14.setTicarea_name("藍5A-3區3300");
				seatingareaVO_D14.setTictotalnumber(new Integer(2000));
				seatingareaVO_D14.setTicbookednumber(new Integer(0));
				seatingAreaList_D.add(seatingareaVO_D14);
				SeatingAreaVO seatingareaVO_D15 = new SeatingAreaVO();	
				seatingareaVO_D15.setEve_no(eve_no);
				seatingareaVO_D15.setTicarea_color("#aad3ff");
				seatingareaVO_D15.setTicarea_name("黃4A-1區3300");
				seatingareaVO_D15.setTictotalnumber(new Integer(2000));
				seatingareaVO_D15.setTicbookednumber(new Integer(0));
				seatingAreaList_D.add(seatingareaVO_D15);
				SeatingAreaVO seatingareaVO_D16 = new SeatingAreaVO();	
				seatingareaVO_D16.setEve_no(eve_no);
				seatingareaVO_D16.setTicarea_color("#aad3ff");
				seatingareaVO_D16.setTicarea_name("黃4A-2區3300");
				seatingareaVO_D16.setTictotalnumber(new Integer(2000));
				seatingareaVO_D16.setTicbookednumber(new Integer(0));
				seatingAreaList_D.add(seatingareaVO_D16);
				SeatingAreaVO seatingareaVO_D17 = new SeatingAreaVO();	
				seatingareaVO_D17.setEve_no(eve_no);
				seatingareaVO_D17.setTicarea_color("#aad3ff");
				seatingareaVO_D17.setTicarea_name("黃2A-1區3300");
				seatingareaVO_D17.setTictotalnumber(new Integer(2000));
				seatingareaVO_D17.setTicbookednumber(new Integer(0));
				seatingAreaList_D.add(seatingareaVO_D17);
				SeatingAreaVO seatingareaVO_D18 = new SeatingAreaVO();	
				seatingareaVO_D18.setEve_no(eve_no);
				seatingareaVO_D18.setTicarea_color("#aad3ff");
				seatingareaVO_D18.setTicarea_name("黃2A-2區3300");
				seatingareaVO_D18.setTictotalnumber(new Integer(2000));
				seatingareaVO_D18.setTicbookednumber(new Integer(0));
				seatingAreaList_D.add(seatingareaVO_D18);
				ticketTypeVOMap.put(ticketTypeVO_D, seatingAreaList_D);
				
				// -----2800區-----
				TicketTypeVO ticketTypeVO_E = new TicketTypeVO();
				ticketTypeVO_E.setEve_no(eve_no);
				ticketTypeVO_E.setTictype_color("#56efa0");
				ticketTypeVO_E.setTictype_name("2800區");
				ticketTypeVO_E.setTictype_price(new Integer(2800));
				List<SeatingAreaVO> seatingAreaList_E = new ArrayList<>();
				SeatingAreaVO seatingareaVO_E01 = new SeatingAreaVO();	
				seatingareaVO_E01.setEve_no(eve_no);
				seatingareaVO_E01.setTicarea_color("#56efa0");
				seatingareaVO_E01.setTicarea_name("橙2A-1區2800");
				seatingareaVO_E01.setTictotalnumber(new Integer(2000));
				seatingareaVO_E01.setTicbookednumber(new Integer(0));
				seatingAreaList_E.add(seatingareaVO_E01);
				SeatingAreaVO seatingareaVO_E02 = new SeatingAreaVO();	
				seatingareaVO_E02.setEve_no(eve_no);
				seatingareaVO_E02.setTicarea_color("#56efa0");
				seatingareaVO_E02.setTicarea_name("橙2A-2區2800");
				seatingareaVO_E02.setTictotalnumber(new Integer(2000));
				seatingareaVO_E02.setTicbookednumber(new Integer(0));
				seatingAreaList_E.add(seatingareaVO_E02);
				SeatingAreaVO seatingareaVO_E03 = new SeatingAreaVO();	
				seatingareaVO_E03.setEve_no(eve_no);
				seatingareaVO_E03.setTicarea_color("#56efa0");
				seatingareaVO_E03.setTicarea_name("橙4A-1區2800");
				seatingareaVO_E03.setTictotalnumber(new Integer(2000));
				seatingareaVO_E03.setTicbookednumber(new Integer(0));
				seatingAreaList_E.add(seatingareaVO_E03);
				SeatingAreaVO seatingareaVO_E04 = new SeatingAreaVO();	
				seatingareaVO_E04.setEve_no(eve_no);
				seatingareaVO_E04.setTicarea_color("#56efa0");
				seatingareaVO_E04.setTicarea_name("橙4A-2區2800");
				seatingareaVO_E04.setTictotalnumber(new Integer(2000));
				seatingareaVO_E04.setTicbookednumber(new Integer(0));
				seatingAreaList_E.add(seatingareaVO_E04);
				SeatingAreaVO seatingareaVO_E05 = new SeatingAreaVO();	
				seatingareaVO_E05.setEve_no(eve_no);
				seatingareaVO_E05.setTicarea_color("#56efa0");
				seatingareaVO_E05.setTicarea_name("藍1A-1區2800");
				seatingareaVO_E05.setTictotalnumber(new Integer(2000));
				seatingareaVO_E05.setTicbookednumber(new Integer(0));
				seatingAreaList_E.add(seatingareaVO_E05);
				SeatingAreaVO seatingareaVO_E06 = new SeatingAreaVO();	
				seatingareaVO_E06.setEve_no(eve_no);
				seatingareaVO_E06.setTicarea_color("#56efa0");
				seatingareaVO_E06.setTicarea_name("藍5A-3區2800");
				seatingareaVO_E06.setTictotalnumber(new Integer(2000));
				seatingareaVO_E06.setTicbookednumber(new Integer(0));
				seatingAreaList_E.add(seatingareaVO_E06);
				SeatingAreaVO seatingareaVO_E07 = new SeatingAreaVO();	
				seatingareaVO_E07.setEve_no(eve_no);
				seatingareaVO_E07.setTicarea_color("#56efa0");
				seatingareaVO_E07.setTicarea_name("黃4A-1區2800");
				seatingareaVO_E07.setTictotalnumber(new Integer(2000));
				seatingareaVO_E07.setTicbookednumber(new Integer(0));
				seatingAreaList_E.add(seatingareaVO_E07);
				SeatingAreaVO seatingareaVO_E08 = new SeatingAreaVO();	
				seatingareaVO_E08.setEve_no(eve_no);
				seatingareaVO_E08.setTicarea_color("#56efa0");
				seatingareaVO_E08.setTicarea_name("黃4A-2區2800");
				seatingareaVO_E08.setTictotalnumber(new Integer(2000));
				seatingareaVO_E08.setTicbookednumber(new Integer(0));
				seatingAreaList_E.add(seatingareaVO_E08);
				SeatingAreaVO seatingareaVO_E09 = new SeatingAreaVO();	
				seatingareaVO_E09.setEve_no(eve_no);
				seatingareaVO_E09.setTicarea_color("#56efa0");
				seatingareaVO_E09.setTicarea_name("黃2A-1區2800");
				seatingareaVO_E09.setTictotalnumber(new Integer(2000));
				seatingareaVO_E09.setTicbookednumber(new Integer(0));
				seatingAreaList_E.add(seatingareaVO_E09);
				SeatingAreaVO seatingareaVO_E10 = new SeatingAreaVO();	
				seatingareaVO_E10.setEve_no(eve_no);
				seatingareaVO_E10.setTicarea_color("#56efa0");
				seatingareaVO_E10.setTicarea_name("黃4A-2區2800");
				seatingareaVO_E10.setTictotalnumber(new Integer(2000));
				seatingareaVO_E10.setTicbookednumber(new Integer(0));
				seatingAreaList_E.add(seatingareaVO_E10);
				ticketTypeVOMap.put(ticketTypeVO_E, seatingAreaList_E);
				
				
				
				// -----2300區-----
				TicketTypeVO ticketTypeVO_F = new TicketTypeVO();
				ticketTypeVO_F.setEve_no(eve_no);
				ticketTypeVO_F.setTictype_color("#c695f9");
				ticketTypeVO_F.setTictype_name("2300區");
				ticketTypeVO_F.setTictype_price(new Integer(2300));
				List<SeatingAreaVO> seatingAreaList_F = new ArrayList<>();
				SeatingAreaVO seatingareaVO_F01 = new SeatingAreaVO();	
				seatingareaVO_F01.setEve_no(eve_no);
				seatingareaVO_F01.setTicarea_color("#c695f9");
				seatingareaVO_F01.setTicarea_name("橙2A-1區2300");
				seatingareaVO_F01.setTictotalnumber(new Integer(2000));
				seatingareaVO_F01.setTicbookednumber(new Integer(0));
				seatingAreaList_F.add(seatingareaVO_F01);
				SeatingAreaVO seatingareaVO_F02 = new SeatingAreaVO();	
				seatingareaVO_F02.setEve_no(eve_no);
				seatingareaVO_F02.setTicarea_color("#c695f9");
				seatingareaVO_F02.setTicarea_name("橙2A-2區2300");
				seatingareaVO_F02.setTictotalnumber(new Integer(2000));
				seatingareaVO_F02.setTicbookednumber(new Integer(0));
				seatingAreaList_F.add(seatingareaVO_F02);
				SeatingAreaVO seatingareaVO_F03 = new SeatingAreaVO();	
				seatingareaVO_F03.setEve_no(eve_no);
				seatingareaVO_F03.setTicarea_color("#c695f9");
				seatingareaVO_F03.setTicarea_name("橙4A-2區2300");
				seatingareaVO_F03.setTictotalnumber(new Integer(2000));
				seatingareaVO_F03.setTicbookednumber(new Integer(0));
				seatingAreaList_F.add(seatingareaVO_F03);
				SeatingAreaVO seatingareaVO_F04 = new SeatingAreaVO();	
				seatingareaVO_F04.setEve_no(eve_no);
				seatingareaVO_F04.setTicarea_color("#c695f9");
				seatingareaVO_F04.setTicarea_name("黃4A-2區2300");
				seatingareaVO_F04.setTictotalnumber(new Integer(2000));
				seatingareaVO_F04.setTicbookednumber(new Integer(0));
				seatingAreaList_F.add(seatingareaVO_F04);
				SeatingAreaVO seatingareaVO_F05 = new SeatingAreaVO();	
				seatingareaVO_F05.setEve_no(eve_no);
				seatingareaVO_F05.setTicarea_color("#c695f9");
				seatingareaVO_F05.setTicarea_name("黃2A-1區2300");
				seatingareaVO_F05.setTictotalnumber(new Integer(2000));
				seatingareaVO_F05.setTicbookednumber(new Integer(0));
				seatingAreaList_F.add(seatingareaVO_F05);
				SeatingAreaVO seatingareaVO_F06 = new SeatingAreaVO();	
				seatingareaVO_F06.setEve_no(eve_no);
				seatingareaVO_F06.setTicarea_color("#c695f9");
				seatingareaVO_F06.setTicarea_name("黃2A-2區2300");
				seatingareaVO_F06.setTictotalnumber(new Integer(2000));
				seatingareaVO_F06.setTicbookednumber(new Integer(0));
				seatingAreaList_F.add(seatingareaVO_F06);
				ticketTypeVOMap.put(ticketTypeVO_F, seatingAreaList_F);
				 
				/****************************** 2.開始新增資料 **************************************************/
				TicketTypeService ticketTypeService = new TicketTypeService();
				Set<TicketTypeVO> ticketTypeVOSet = ticketTypeVOMap.keySet();
				for(TicketTypeVO aTicketTypeVO : ticketTypeVOSet) {
					ticketTypeService.insertWithSeatingArea(aTicketTypeVO, ticketTypeVOMap.get(aTicketTypeVO));
				}

				/****************************** 3.新增完成,準備轉交 ***************************************************/
				RequestDispatcher successView = request.getRequestDispatcher("/event/EventServlet.do?action=getOneEvent_For_Update&eve_no" + eve_no);
				successView.forward(request, response);

				/****************************** 其他可能的錯誤處理 **************************************************/
			} catch (Exception e) {
				eventErrorMsgs.put("Exception", "無法取得資料 : " + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/event/EventServlet.do?action=getOneEvent_For_Update&eve_no" + eve_no);
				failureView.forward(request, response);
			}
		}
		
		
	}

}
