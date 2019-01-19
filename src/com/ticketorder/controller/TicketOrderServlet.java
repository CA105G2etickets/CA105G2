package com.ticketorder.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.event.model.EventService;
import com.event.model.EventVO;
import com.event.model.Event_H5_Service;
import com.event.model.Event_H5_VO;
import com.resaleorder.model.ResaleOrderService;
import com.resaleorder.model.ResaleOrderVO;
import com.seating_area.model.SeatingArea_H5_Service;
import com.seating_area.model.SeatingArea_H5_VO;
import com.ticket.model.ShowTicketVO;
import com.ticket.model.TicketService;
import com.ticket.model.TicketVO;
import com.ticket.model.TicketVO2;

//import com.ticket_type.model.TicketTypeVO_temp;
import com.ticket_type.model.TicketType_H5_Service;
import com.ticket_type.model.TicketType_H5_VO;
import com.ticketorder.model.ShowTicketOrderVO;
import com.ticketorder.model.TicketOrderService;
import com.ticketorder.model.TicketOrderVO;
import com.ticketorder.model.TicketOrderVO2;
import com.member.model.*;

/**
 * Servlet implementation class TicketOrderServlet
 */
//@WebServlet("/TicketOrderServlet")
public class TicketOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	public Map<String, String> mapCheckList = new HashMap<String, String>(){{
//	    put("ticket_resale_status_0", "NONE0"); 
//	    put("ticket_resale_status_1", "SELLING1"); 
//	    put("ticket_resale_status_2", "CHECKING2");
//	    put("d", "dcat");
//	    put("e", "ecat");
//	    put("f", "fcat");
//	    put("g", "gcat");
//	    put("h", "chat");
//	    }};

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
//		System.out.println(this.DB2Usermap.get("a"));
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		String action = req.getParameter("action");
		
		//no cache setting
//		res.setHeader("Cache-Control", "no-store");
//		res.setHeader("Pragma", "no-cache");
//		res.setDateHeader("Expires", 0);
		
		//get eve_no to next buyticket.jsp ,after this line,co-operate with event programmer
		//try to catch TAI's support view,
        if("select_EVE_NO_toBuyTickets".equals(action)) {
        	
        	List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			MemberVO member = (MemberVO)session.getAttribute("member");
			
			try {
				String eve_no = req.getParameter("eve_no");
				String member_no = member.getMemberNo();
        		if (member_no == null || (member_no.trim()).length() == 0) {
					errorMsgs.add("購票前請登入");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/login_front-end.jsp"); 
					failureView.forward(req, res);
					return;//程式中斷
				}
				
//				Event_H5_Service eh5Svc = new Event_H5_Service();
//				Event_H5_VO eh5vo = eh5Svc.getOneEvent_H5(eve_no);
				
//				Map<String, String[]> map = new TreeMap<String, String[]>();
//				map.put("eve_no", new String[] {eve_no});
//				SeatingArea_H5_Service sh5Svc = new SeatingArea_H5_Service();
//				List<SeatingArea_H5_VO> list = sh5Svc.getAll(map, "ticarea_no");
				
//				if (list == null) {
//					errorMsgs.add("查無資料");
//				}
//				// Send the user back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/frontend/index.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				req.setAttribute("slist", list);
//				req.setAttribute("eh5vo", eh5vo);
				req.setAttribute("eve_no", eve_no);
				req.setAttribute("member_no", member_no);
				
				String url = "/frontend/ticketorder/eve_no_SelectedReady2Next.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
				
			}catch(Exception e) {
				errorMsgs.add("Error at controller.select_EVE_NO_toBuyTickets:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/frontend/index.jsp");
				failureView.forward(req, res);
			}
        }
        if("ticketNumSelected_buyTickets".equals(action)) {
        	
        	//set every session who selected ticketnums, maybe set when successful request to select payment.jsp
//        	session.setMaxInactiveInterval(60);
        	
        	List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			//一個空的List跟ehtVO是為了輸出錯誤或exception發生時，導回上一頁仍取的到直
//			List<SeatingArea_H5_VO> list = new LinkedList<SeatingArea_H5_VO>();
//			Event_H5_VO eh5vo = new Event_H5_VO();
//			List<String> list4PassValue = new LinkedList<String>();
			
			try {
				//set eve_no to req to get data user want to see.
				String eve_no = req.getParameter("eve_no");
				String ticarea_no = req.getParameter("ticarea_no");
				String ticketsNum = req.getParameter("ticketsNum");
				String tictype_no = req.getParameter("tictype_no");
				String member_no = req.getParameter("member_no");
				System.out.println("controller.ticketnumselected_buytickets="+eve_no
						+ticarea_no+ticketsNum+tictype_no+member_no);
				
				
//				list4PassValue.add(req.getParameter("evetit_nameForShow"));
//				list4PassValue.add(req.getParameter("eve_sessionnameForShow"));
//				list4PassValue.add(req.getParameter("eve_startdateForShow"));
//				list4PassValue.add(req.getParameter("eve_enddateForShow"));
//				list4PassValue.add(req.getParameter("venue_nameForShow"));
//				list4PassValue.add(req.getParameter("addressForShow"));
//				list4PassValue.add(req.getParameter("tictype_nameForShow"));
				
				//用很笨的方式讓使用者不至於因為控制器擋下錯誤的要求而重刷葉面沒有資料
//				Map<String, String[]> map = new TreeMap<String, String[]>();
//				map.put("eve_no", new String[] {eve_no});
//				SeatingArea_H5_Service sh5Svc = new SeatingArea_H5_Service();
//				list = sh5Svc.getAll(map, "ticarea_no");
//				Event_H5_Service eh5Svc = new Event_H5_Service();
//				eh5vo = eh5Svc.getOneEvent_H5(eve_no);
//				req.setAttribute("slist", list);
//				req.setAttribute("eh5vo", eh5vo);
				req.setAttribute("eve_no", eve_no);
				
//				req.setAttribute("list4PassValue", list4PassValue);
//				req.setAttribute("list4PassValue", list4PassValue);
				
				//check user visited it or not, if he try to refresh the session live time, .invalidate()
//	        	if (!(session.getAttribute("createdTicketOrderNo") == null || session.getAttribute("createdTicketOrderNo").equals(""))) {
//	        		//detect user's session had a bound sessionAttribute ,then remove it to call.unbound()
////	        		session.removeAttribute("createdTicketOrderNo");
//	        		Enumeration enumSessionName = session.getAttributeNames();
//	        		while(enumSessionName.hasMoreElements()) {
//	        			String name = (String)enumSessionName.nextElement();
//	        			if(name.contains("TO")) {
//	        				System.out.println("sessionBinding="+name);
//	        				session.removeAttribute(name);
//	        				break;
//	        			}
//	        		} //fail
//
////	        		session.setAttribute(createdTicketOrderNo,"againSoRemove");
//	        		errorMsgs.add("user refresh session live time,so disable the ticketorder");
//	        	}
//				Enumeration<String> enumSname = session.getAttributeNames();
//				while(enumSname.hasMoreElements()) {
//        			String name = (String)enumSname.nextElement();
//        			if(name.contains("TO")) {
//        				System.out.println("sessionBinding="+name);
//        				session.setAttribute(name, "replaced");;
//        				break;
//        			}
//        		} //fail
				
				//start to create ticket_order //temperarily disable errorMsg
				Integer total_amount = null;
				try {
					total_amount = new Integer(Integer.parseInt(ticketsNum));
					
					if(total_amount > 100100 || total_amount <0) {
//						errorMsgs.add("總張數請勿亂填數字.");
					}
				} catch (NumberFormatException e) {
					total_amount = 0;
//					errorMsgs.add("總張數請填數字.");
				}
				
				Integer total_price = null;
				try {
					TicketType_H5_Service ttyh5Svc = new TicketType_H5_Service();
					TicketType_H5_VO ttyh5VO = ttyh5Svc.findByPrimaryKey(tictype_no);
					Integer oneTicketPrice = new Integer(ttyh5VO.getTictype_price());
					total_price = oneTicketPrice*total_amount;
					
					if(total_amount > 10100100 || total_amount <0) {
//						errorMsgs.add("總張數請勿亂填數字.");
					}
				} catch (NumberFormatException e) {
					total_amount = 0;
//					errorMsgs.add("總張數請填數字.");
				}
				
				java.sql.Timestamp ticket_order_time = new java.sql.Timestamp(System.currentTimeMillis());
				String payment_method = "NOTYET";
				String ticket_order_status = "WAITTOPAY1"; //create a ticket_order and prepare to update seatingarea.
				
//				//錯誤處理並導回前頁
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/frontend/ticketorder/eve_no_SelectedReady2Next.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
				
				SeatingArea_H5_Service sh5Svc = new SeatingArea_H5_Service();
				SeatingArea_H5_VO svo = sh5Svc.getOneSeatingArea_H5(ticarea_no); //此if判斷式的開頭有用過sh5Svc了 不用再宣告
				
				//comment these because eve_no_SelectedReady2Next.jsp already doing checking booked number by TAI.
//				if(total_amount+svo.getTicbookednumber() > svo.getTictotalnumber()) {
//					errorMsgs.add("選取的目標活動的剩餘票數不足，請重新購票");	
//				}
				
				req.setAttribute("member_no", member_no);
				
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/frontend/ticketorder/eve_no_SelectedReady2Next.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				} 
				
				//watch out this ticketorderVO CANT have pk, or it will be update.
				TicketOrderVO toVO = new TicketOrderVO();
				toVO.setMember_no(member_no);
				toVO.setTotal_price(total_price);
				toVO.setTotal_amount(total_amount);
				toVO.setTicket_order_time(ticket_order_time);
				toVO.setPayment_method(payment_method);
				toVO.setTicket_order_status(ticket_order_status);
//				String ticarea_no = ticarea_no;
				
//				SeatingArea_H5_VO svolocal = new SeatingArea_H5_VO();
//				svolocal.setTicarea_no(svo.getTicarea_no());
//				svolocal.setTicarea_color(svo.getTicarea_color());
//				svolocal.setTicarea_name(svo.getTicarea_name());
//				svolocal.setTictotalnumber(svo.getTictotalnumber());
//				svolocal.setTicbookednumber(svo.getTicbookednumber());
//				svolocal.setEve_h5VO(svo.getEve_h5VO());
//				svolocal.setTickettype_h5VO(svo.getTickettype_h5VO());
				
				Integer original_ticbookednumber = svo.getTicbookednumber();
				Integer update_ticbookednumber = original_ticbookednumber+total_amount;
				
//				svolocal.setTicbookednumber(update_ticbookednumber);
				svo.setTicbookednumber(update_ticbookednumber);
				
				//add svo at line281 which contains pk, then insertThenGetLatest... can work.
				toVO.setSeatingarea_h5VO(svo);
				
				//prepared toVO and sVO done, now call transaction function
				TicketOrderService toSvc = new TicketOrderService();
				
				//this parameter contain tovo which without pk and seatingareaVO
//				TicketOrderVO TVOASDF = toSvc.addTicketOrderWithSaVO(member_no, svo, total_price, total_amount, ticket_order_time, payment_method, ticket_order_status);
//				String createdTicketOrderNo = TVOASDF.getTicket_order_no();
				String createdTicketOrderNo = toSvc.insertThenGetLatestToNoWithCondition(toVO);
				
				//maybe doesnt need it if you send a ticketordervo. dont need it cause key is different with listener's key
//				session.setAttribute("createdTicketOrderNo", createdTicketOrderNo); 
				
//				session.setAttribute("toVO", toVO);
//				session.setAttribute("sVO", sVO);
				
//				String CreatedTicketOrderNo = toSvc.insertTicketOrderAndUpdateTicArea(toVO, sVO);
				
				//set sessionAttribute for Listener Bound to use. NOTE:its not set for listener,but without it next page.jsp might fail
//				session.setAttribute("createdTicketOrderNo", createdTicketOrderNo);
//				
//				//init HttpSessionBindingListener and binding that latest ticket_order_no
				TicketOrderWithTicArea_HSB_Listener ticketOrderListener = new TicketOrderWithTicArea_HSB_Listener();
				session.setAttribute(createdTicketOrderNo, ticketOrderListener);
//				
				//maybe doesnt need it if you send a ticketordervo.
//				req.setAttribute("createdTicketOrderNo", createdTicketOrderNo);
				
				//set sessionLiveTime for payment deadline 
//				session.setMaxInactiveInterval(60);
				TicketOrderVO toVO_sendTo_selectPaymentAndPayjsp = toSvc.getOneTicketOrder(createdTicketOrderNo);
				req.setAttribute("toVO", toVO_sendTo_selectPaymentAndPayjsp);
				
				//set visited value into session, if user wants to refresh the live time of session, call .invalidate()
//				session.setAttribute("isvisited", "yes");
				String url = "/frontend/ticketorder/selectPaymentAndPay.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch(NullPointerException ne) {
				System.out.println("nullporinter");
				errorMsgs.add("錯誤訊息如下:"+ne.getMessage());
//				req.setAttribute("slist", list);
//				req.setAttribute("eh5vo", eh5vo);
//				req.setAttribute("eve_no", eve_no);
//				req.setAttribute("list4PassValue", list4PassValue);
				RequestDispatcher failureView = req
//						.getRequestDispatcher("/frontend/ticketorder/eve_no_SelectedReady2Next.jsp");
						.getRequestDispatcher("/frontend/index.jsp");
				failureView.forward(req, res);
				
			} catch(Exception e) {
				errorMsgs.add("資料庫更新該座位區已訂位數量錯誤，剩餘票數不足，錯誤訊息如下:"+e.getMessage());
//				System.out.println("catch excetpitonjt");
//				String eve_no = (String) req.getAttribute("eve_no");
//				SeatingAreaService sSvc = new SeatingAreaService();
//				List<SeatingAreaVO> list = sSvc.getAllSeatingAreaByEveNo(eve_no);
//				
//				req.setAttribute("slist", list);
				
//				req.setAttribute("eve_no", eve_no);
//				System.out.println("從資料庫失敗 eve_no="+eve_no);
				
//				req.setAttribute("slist", list);
//				req.setAttribute("eh5vo", eh5vo);
//				req.setAttribute("eve_no", eve_no);
//				req.setAttribute("list4PassValue", list4PassValue);
				RequestDispatcher failureView = req
//						.getRequestDispatcher("/frontend/ticketorder/eve_no_SelectedReady2Next.jsp");
						.getRequestDispatcher("/frontend/index.jsp");
				failureView.forward(req, res);
			}
        }
        if("userPaying".equals(action)) {
        	List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String eve_no = req.getParameter("eve_no");
			
			try {
				String ticket_order_no = req.getParameter("ticket_order_no");
				String creditCardNumber = req.getParameter("creditCardNumber");
				String creditCardVerificationNumber = req.getParameter("creditCardVerificationNumber");
				//watch out the different on month and year.
				String creditCardYear = req.getParameter("creditCardMonth");
				String creditCardMonth = req.getParameter("creditCardYear");
				
				if(creditCardNumber.length() !=16 ) {
					errorMsgs.add("信用卡卡號不正確，應為16碼數字");
				}
				if(creditCardVerificationNumber.length()!=3) {
					errorMsgs.add("信用卡卡片背面後三碼不正確");
				}
//				if(this.containsHanScript(creditCardNumber)) {
//					errorMsgs.add("信用卡卡號不正確，應為16碼數字");
//				}
//				if(this.containsHanScript(creditCardVerificationNumber)) {
//					errorMsgs.add("信用卡卡片背面後三碼不正確");
//				}
				
//				if(creditCardMonth.length()>2 || creditCardMonth.length()<1) {
//					errorMsgs.add("卡片到期月份不正確");
//				}
//				if(creditCardYear.length()>4 || creditCardYear.length()<3) {
//					errorMsgs.add("卡片到期年份不正確");
//				}
				
				Integer cardmonth = null;
				try {
					cardmonth = new Integer(creditCardMonth.trim());
					if(cardmonth <1 || cardmonth>12) {
						errorMsgs.add("卡片到期月份不正確");
					}
				} catch (NumberFormatException e) {
					errorMsgs.add("卡片到期月份不正確.");
				}
				
				Integer cardyear = null;
				try {
					cardyear = new Integer(creditCardYear.trim());
					if(cardyear <2019 || cardyear>2030) {
						errorMsgs.add("卡片到期年份不正確");
					}
				} catch (NumberFormatException e) {
					errorMsgs.add("卡片到期年份不正確.");
				}
				
				
				//基本錯誤驗證完成，開始呼叫service
				TicketOrderService toSvc = new TicketOrderService();
				TicketOrderVO toVO = toSvc.getOneTicketOrder(ticket_order_no);
				if (toVO == null) {
					errorMsgs.add("查無資料");
				}
				
				//set toVO for userStartPaying.jsp and to use
				req.setAttribute("toVO", toVO);
				req.setAttribute("eve_no", eve_no);
				String member_no = toVO.getMember_no();
				req.setAttribute("member_no", member_no);
				
				//check toVO's status to let user cant update the status when it's OUTDATE4
				String targetToVoStatus = toVO.getTicket_order_status();
				if("OUTDATE4".equals(targetToVoStatus)) {
					errorMsgs.add("此訂票訂單已逾期，因超過繳費期限而被系統取消，請重新購票");
				}
				// Send the user back to the former page, if there were errors
				if (!errorMsgs.isEmpty()) {
					String url = "/frontend/ticketorder/selectPaymentAndPay.jsp";
					RequestDispatcher failureView = req
							.getRequestDispatcher(url);
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				//Start to contact db,transfer hibernate tovo into tovo2 for JNDI to use
				TicketOrderVO2 tovo2 = new TicketOrderVO2();
				tovo2.setTicket_order_no(ticket_order_no);
				tovo2.setMember_no(toVO.getMember_no());
				tovo2.setTicarea_no(toVO.getSeatingarea_h5VO().getTicarea_no());
				tovo2.setTotal_price(toVO.getTotal_price());
				tovo2.setTotal_amount(toVO.getTotal_amount());
				tovo2.setTicket_order_time(toVO.getTicket_order_time());
				tovo2.setPayment_method("CREDITCARD"); //that's wht tickets only can pay by CreditCard.
				tovo2.setTicket_order_status("COMPLETE2");
				
				List<TicketVO2> t2list = new ArrayList<TicketVO2>();
				for(int i = 1; i<=tovo2.getTotal_amount();i++) {
					
					String ticket_status = "ACTIVE1";
					java.sql.Timestamp ticket_create_time = new java.sql.Timestamp(System.currentTimeMillis());;
					String ticket_resale_status = "NONE1";
					Integer ticket_resale_price = 0;
					String is_from_resale = "NO";
					
					TicketVO2 tVO2 = new TicketVO2();
					tVO2.setTicarea_no(tovo2.getTicarea_no());
					tVO2.setMember_no(tovo2.getMember_no());
					tVO2.setTicket_status(ticket_status);
					tVO2.setTicket_create_time(ticket_create_time);
					tVO2.setTicket_resale_status(ticket_resale_status);
					tVO2.setTicket_resale_price(ticket_resale_price);
					tVO2.setIs_from_resale(is_from_resale);
					t2list.add(tVO2);
				}
				
				//call transaction function in JNDI.
				toSvc.updateTicketOrderAndInsertTickets(tovo2, t2list);
				
				//after transaction success, set lastest tovo into req
				TicketOrderVO updated_tovo = toSvc.getOneTicketOrder(ticket_order_no);
				req.setAttribute("toVO", updated_tovo);
				
				//try add tickets after this transaction into req
//				TicketService tSvc = new TicketService();
//				Map<String, String[]> map = new TreeMap<String, String[]>();
//				map.put("ticket_order_no", new String[] {ticket_order_no});
//				map.put("member_no", new String[] {updated_tovo.getMember_no()});
//				List<TicketVO> memberListTVO = tSvc.getAll_map(map, "ticket_create_time");
////				req.setAttribute("memberListTVO", memberListTVO); //maybe useless while listShow work on
//				
////				String[] strasdfasdef = memberListTVO.toArray(new String[memberListTVO.size()]);
////				System.out.println(strasdfasdef);
//				
////				map.clear();
////				map.put("eve_no", new String[] {eve_no});
//				SeatingArea_H5_Service sh5Svc = new SeatingArea_H5_Service();
////				List<SeatingArea_H5_VO> slist = sh5Svc.getAll(map, "ticarea_no");
////				Event_H5_Service eh5Svc = new Event_H5_Service();
////				Event_H5_VO eh5vo = eh5Svc.getOneEvent_H5(eve_no);
////				
////				req.setAttribute("slist", slist);
////				req.setAttribute("eh5vo", eh5vo);
//				
//				Event_H5_Service eh5Svc = new Event_H5_Service();
//				Event_H5_VO eh5VO = eh5Svc.getOneEvent_H5(eve_no);
//				req.setAttribute("eh5VO", eh5VO);
//				List<ShowTicketVO> listShow = new LinkedList<ShowTicketVO>();
//				
//				for(TicketVO at:memberListTVO) {
//					ShowTicketVO stvo = new ShowTicketVO();
//					stvo.setTicket_no(at.getTicket_no());
//					stvo.setMember_no(at.getMember_no());
//					stvo.setTicket_status(at.getTicket_status());
//					
////					System.out.println("at="+at.getSeatingarea_h5VO().getTicarea_no());
//					SeatingArea_H5_VO sh5VO = sh5Svc.getOneSeatingArea_H5(at.getSeatingarea_h5VO().getTicarea_no());
//					stvo.setTicarea_name(sh5VO.getTicarea_name());
//					stvo.setTicarea_color(sh5VO.getTicarea_color());
//					stvo.setTictype_name(sh5VO.getTickettype_h5VO().getTictype_name());
//					stvo.setTictype_price(sh5VO.getTickettype_h5VO().getTictype_price());
//					
////					System.out.println("sh5VO.get="+sh5VO.getEve_h5VO().getEve_no());
//					Event_H5_VO eh5VO2 = eh5Svc.getOneEvent_H5(sh5VO.getEve_h5VO().getEve_no());
////					System.out.println("eh5VO.get="+eh5VO.getVenue_h5VO().getAddress());
//					stvo.setEve_sessionname(eh5VO2.getEve_sessionname());
//					stvo.setEve_startdate(eh5VO2.getEve_startdate());
//					stvo.setEve_enddate(eh5VO2.getEve_enddate());
//					stvo.setEve_offsaledate(eh5VO2.getEve_offsaledate());
//					stvo.setEvetit_name(eh5VO2.getEventtitle_h5VO().getEvetit_name());
//					stvo.setVenue_name(eh5VO2.getVenue_h5VO().getVenue_name());
//					stvo.setAddress(eh5VO2.getVenue_h5VO().getAddress());
//					listShow.add(stvo);
//				}
//				req.setAttribute("listShow", listShow);
				
				String url = "/frontend/ticketorder/paymentDoneShowInfos.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("controller.userPaying" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/frontend/index.jsp");
				failureView.forward(req, res);
			}
        }
        
        if("member_select_ticketorders".equals(action)) {
        	List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String member_no = req.getParameter("member_no");
				if (member_no == null || (member_no.trim()).length() == 0) {
					MemberVO member = (MemberVO) session.getAttribute("member");
					member_no = member.getMemberNo();
					if(member_no == null || (member_no.trim()).length() == 0) {
						errorMsgs.add("查無資料請先登入");
					}
				}
				//member_no = null, means without login so forward to login.
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/login_front-end.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				//get member_no, 
//				TicketOrderService toSvc = new TicketOrderService();
//				SeatingArea_H5_Service sh5Svc = new SeatingArea_H5_Service();
//				Event_H5_Service eh5Svc = new Event_H5_Service();
//				List<TicketOrderVO> tolist = toSvc.getTicketOrdersByMemberNo(member_no);
//				if(tolist.size()==0) {
//					errorMsgs.add("此會員查無訂票訂單查無資料");
//				}
				// this time maybe forward back to login.jsp or sendRedirect to it.
				req.setAttribute("member_no", member_no);
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/index.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				//use javaBean on .jsp so these doesnt have to add.
//				List<ShowTicketOrderVO> listShow = new LinkedList<ShowTicketOrderVO>();
//				for(TicketOrderVO atovo:tolist) {
//					ShowTicketOrderVO stovo = new ShowTicketOrderVO();
//					stovo.setTicket_order_no(atovo.getTicket_order_no());
//					stovo.setMember_no(atovo.getMember_no());
//					stovo.setTotal_price(atovo.getTotal_price());
//					stovo.setTotal_amount(atovo.getTotal_amount());
//					stovo.setTicket_order_time(atovo.getTicket_order_time());
//					stovo.setPayment_method(atovo.getPayment_method());
//					stovo.setTicket_order_status(atovo.getTicket_order_status());
//					
//					SeatingArea_H5_VO sh5VO = sh5Svc.getOneSeatingArea_H5(atovo.getSeatingarea_h5VO().getTicarea_no());
//					Event_H5_VO eh5VO = eh5Svc.getOneEvent_H5(sh5VO.getEve_h5VO().getEve_no());
//					stovo.setEve_sessionname(eh5VO.getEve_sessionname());
//					stovo.setEve_startdate(eh5VO.getEve_startdate());
//					stovo.setEve_enddate(eh5VO.getEve_enddate());
//					stovo.setEvetit_name(eh5VO.getEventtitle_h5VO().getEvetit_name());
//					stovo.setVenue_name(eh5VO.getVenue_h5VO().getVenue_name());
//					stovo.setAddress(eh5VO.getVenue_h5VO().getAddress());
//					listShow.add(stovo);
//				}
//				req.setAttribute("listShow", listShow);
				
				String url = "/frontend/ticketorder/listAllTicketOrderBymember_no.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
				
			}catch(Exception e) {
				//this time maybe forward back to login.jsp or sendRedirect to it.
				errorMsgs.add("從資料庫查詢失敗,at controller:action=member_select_ticketorders:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/frontend/index.jsp");
				failureView.forward(req, res);
			}
        	
        	
        }//selected_targetTicketOrder_showTickets
        if("selected_targetTicketOrder_showTickets".equals(action)) {
        	List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String ticket_order_no = req.getParameter("ticket_order_no");
				if (ticket_order_no == null || (ticket_order_no.trim()).length() == 0) {
					errorMsgs.add("請輸入ticket_order_no編號");
				}
				if (ticket_order_no.length()>18) {
					errorMsgs.add("ticket_order_no格式不正確");
				}
				if (this.containsHanScript(ticket_order_no)) {
					errorMsgs.add("ticket_order_no不可包含中文");
				}
				String member_no = req.getParameter("member_no");
				if (member_no == null || (member_no.trim()).length() == 0) {
					errorMsgs.add("請輸入member_no編號");
				}
				if (member_no.length()>18) {
					errorMsgs.add("member_no格式不正確");
				}
				if (this.containsHanScript(member_no)) {
					errorMsgs.add("member_no不可包含中文");
				}
				
				// this time maybe forward back to login.jsp or sendRedirect to it.
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/ticketorder/member_select_ticketorders.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				//驗證錯誤結束，準備開始call service
				TicketService tSvc = new TicketService();
				Map<String, String[]> map = new TreeMap<String, String[]>();
				map.put("ticket_order_no", new String[] {ticket_order_no});
				map.put("member_no", new String[] {member_no});
				
				//might add logic on 'member check from member-table-owner-tickets and target-tickets-member' here
				
				List<TicketVO> memberListTVO = tSvc.getAll_map(map, "ticket_create_time");
				
				SeatingArea_H5_Service sh5Svc = new SeatingArea_H5_Service();			
				Event_H5_Service eh5Svc = new Event_H5_Service();
				List<ShowTicketVO> listShow = new LinkedList<ShowTicketVO>();
				
				for(TicketVO at:memberListTVO) {
					ShowTicketVO stvo = new ShowTicketVO();
					stvo.setTicket_no(at.getTicket_no());
					stvo.setMember_no(at.getMember_no());
					stvo.setTicket_status(at.getTicket_status());
					
					stvo.setTicket_resale_status(at.getTicket_resale_status());
					stvo.setTicket_resale_price(at.getTicket_resale_price());
					stvo.setIs_from_resale(at.getIs_from_resale());
					stvo.setTicket_order_no(at.getTicketorderVO().getTicket_order_no());
					
					SeatingArea_H5_VO sh5VO = sh5Svc.getOneSeatingArea_H5(at.getSeatingarea_h5VO().getTicarea_no());
					stvo.setTicarea_name(sh5VO.getTicarea_name());
					stvo.setTicarea_color(sh5VO.getTicarea_color());
					stvo.setTictype_name(sh5VO.getTickettype_h5VO().getTictype_name());
					stvo.setTictype_price(sh5VO.getTickettype_h5VO().getTictype_price());
					
					Event_H5_VO eh5VO = eh5Svc.getOneEvent_H5(sh5VO.getEve_h5VO().getEve_no());
					stvo.setEve_sessionname(eh5VO.getEve_sessionname());
					stvo.setEve_startdate(eh5VO.getEve_startdate());
					stvo.setEve_enddate(eh5VO.getEve_enddate());
					stvo.setEve_offsaledate(eh5VO.getEve_offsaledate());
					stvo.setEvetit_name(eh5VO.getEventtitle_h5VO().getEvetit_name());
					stvo.setVenue_name(eh5VO.getVenue_h5VO().getVenue_name());
					stvo.setAddress(eh5VO.getVenue_h5VO().getAddress());
					listShow.add(stvo);
				}
				req.setAttribute("listShow", listShow);
				
				String url = "/frontend/ticketorder/member_select_tickets.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
				
			}catch(Exception e) {
				//this time maybe forward back to login.jsp or sendRedirect to it.
				errorMsgs.add("從資料庫查詢失敗,at controller:member_select_ticketorders:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/frontend/ticketorder/evetit_no_SelectedReady2Next.jsp");
				failureView.forward(req, res);
			}
        	
        	
        }
        
        if("member_sell_targetTicket".equals(action)) {
        	
        	//add logic to prevent member who's not owner this tickets but can change those tickets
        	List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				//
				String ticket_no = req.getParameter("ticket_no");
				if (ticket_no == null || (ticket_no.trim()).length() == 0) {
					errorMsgs.add("請輸入ticket_no編號");
				}
				if (ticket_no.length()>18) {
					errorMsgs.add("ticket_no格式不正確");
				}
				if (this.containsHanScript(ticket_no)) {
					errorMsgs.add("ticket_no不可包含中文");
				}
				
				//member_no
				String member_no = req.getParameter("member_no");
				if (member_no == null || (member_no.trim()).length() == 0) {
					errorMsgs.add("請輸入member_no編號");
				}
				if (member_no.length()>18) {
					errorMsgs.add("member_no格式不正確");
				}
				if (this.containsHanScript(member_no)) {
					errorMsgs.add("member_no不可包含中文");
				}
				
				//ticket_order_no
				String ticket_order_no = req.getParameter("ticket_order_no");
				if (ticket_order_no == null || (ticket_order_no.trim()).length() == 0) {
					errorMsgs.add("請輸入ticket_order_no編號");
				}
				if (ticket_order_no.length()>18) {
					errorMsgs.add("ticket_order_no格式不正確");
				}
				if (this.containsHanScript(ticket_order_no)) {
					errorMsgs.add("ticket_order_no不可包含中文");
				}
				
				//check the status is selling or not, should from db , also add check the member owns this ticket or not
				TicketService tSvc = new TicketService();
				TicketVO tvoFromDB = tSvc.getOneTicket(ticket_no);
				if(tvoFromDB == null) {
					errorMsgs.add("ticket_no不存在");
				}
				if("SELLING2".equals(tvoFromDB.getTicket_resale_status()) || "CHECKING3".equals(tvoFromDB.getTicket_resale_status())) {
					errorMsgs.add("此票已販賣中或是正在等待結帳，不可再度販賣或更動狀態");
				}else {
					
				}
				if(!tvoFromDB.getMember_no().equals(member_no)) {
					errorMsgs.add("該票券持有人與輸入資料的會員不同，無法變更或販賣");
				}
				
				//ticket_resale_price
				Integer ticket_resale_price = null;
				try {
					ticket_resale_price = new Integer(req.getParameter("ticket_resale_price").trim());
					if(ticket_resale_price > 10100100 || ticket_resale_price <0) {
						errorMsgs.add("ticket_resale_price請勿亂填數字.");
					}
					
					//此行邏輯有誤，從資料庫取出來的第一次新增的轉售訂單，原訂價一錠是0
//					else if(ticket_resale_price > tvoFromDB.getTicket_resale_price()) {
//						errorMsgs.add("ticket_resale_price請勿超過原價.");
//					}
					
				} catch (NumberFormatException e) {
					ticket_resale_price = tvoFromDB.getTicket_resale_price();
					errorMsgs.add("ticket_resale_price請填數字.");
				}
				
				/* 被此行包圍的是為了讓listShow可以重複被 member_selectT_ticets.jsp利用並顯示 */
				// use target ticket_order_no and member_no to search all tickets
				Map<String, String[]> map = new TreeMap<String, String[]>();
				map.put("ticket_order_no", new String[] {ticket_order_no});
				map.put("member_no", new String[] {member_no}); 
				List<TicketVO> memberListTVO = tSvc.getAll_map(map, "ticket_create_time");
				
				SeatingArea_H5_Service sh5Svc = new SeatingArea_H5_Service();				
				Event_H5_Service eh5Svc = new Event_H5_Service();
				List<ShowTicketVO> listShow = new LinkedList<ShowTicketVO>();
				
				for(TicketVO at:memberListTVO) {
					ShowTicketVO stvo = new ShowTicketVO();
					stvo.setTicket_no(at.getTicket_no());
					stvo.setMember_no(at.getMember_no());
					stvo.setTicket_status(at.getTicket_status());
					
					stvo.setTicket_resale_status(at.getTicket_resale_status());
					stvo.setTicket_resale_price(at.getTicket_resale_price());
					stvo.setIs_from_resale(at.getIs_from_resale());
					stvo.setTicket_order_no(at.getTicketorderVO().getTicket_order_no());
					
					SeatingArea_H5_VO sh5VO = sh5Svc.getOneSeatingArea_H5(at.getSeatingarea_h5VO().getTicarea_no());
					stvo.setTicarea_name(sh5VO.getTicarea_name());
					stvo.setTicarea_color(sh5VO.getTicarea_color());
					stvo.setTictype_name(sh5VO.getTickettype_h5VO().getTictype_name());
					stvo.setTictype_price(sh5VO.getTickettype_h5VO().getTictype_price());
					
					Event_H5_VO eh5VO = eh5Svc.getOneEvent_H5(sh5VO.getEve_h5VO().getEve_no());
					stvo.setEve_sessionname(eh5VO.getEve_sessionname());
					stvo.setEve_startdate(eh5VO.getEve_startdate());
					stvo.setEve_enddate(eh5VO.getEve_enddate());
					stvo.setEve_offsaledate(eh5VO.getEve_offsaledate());
					stvo.setEvetit_name(eh5VO.getEventtitle_h5VO().getEvetit_name());
					stvo.setVenue_name(eh5VO.getVenue_h5VO().getVenue_name());
					stvo.setAddress(eh5VO.getVenue_h5VO().getAddress());
					listShow.add(stvo);
				}
				req.setAttribute("listShow", listShow);
				/* 被此行包圍的是為了讓listShow可以重複被 member_selectT_ticets.jsp利用並顯示 */
				
				// this time maybe forward back to login.jsp or sendRedirect to it.
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/ticketorder/member_select_tickets.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				//理論上應該要產生一筆轉售訂單 並更新此筆傳受訂單對應到的票券的狀態跟轉售狀態
				//its a trap, tickets selling depends on ticket.ticket_resale_order.
				TicketVO tvo4update = new TicketVO();
				tvo4update = tSvc.getOneTicket(ticket_no);
				tvo4update.setTicket_resale_price(ticket_resale_price);
				tvo4update.setTicket_resale_status("SELLING2");
				tSvc.update(tvo4update);
				
//				ResaleOrderService rSvc = new ResaleOrderService();
//				ResaleOrderVO resaleorderVO = new ResaleOrderVO();
//				resaleorderVO.setMember_buyer_no("");
//				resaleorderVO.setMember_seller_no(member_no);
//				resaleorderVO.setPayment_method("NOTYET");
//				resaleorderVO.setResale_ord_completetime(null);
//				resaleorderVO.setResale_ord_createtime(new java.sql.Timestamp(System.currentTimeMillis()));
//				resaleorderVO.setResale_ordprice(0);
//				resaleorderVO.setResale_ordstatus("SELLING1");
//				resaleorderVO.setTicketVO(tvo4update);
//				rSvc.insert(resaleorderVO);
//				dao.insert(resaleorderVO);
				
				/* 超蠢，因為怕更新後仍是回傳舊的東西，就再跑一次，因此先把上面錯誤處理的註解 */
				map.clear();
				map.put("ticket_order_no", new String[] {ticket_order_no});
				map.put("member_no", new String[] {member_no});
				memberListTVO = tSvc.getAll_map(map, "ticket_create_time");
				listShow.clear();
				
				for(TicketVO at:memberListTVO) {
					ShowTicketVO stvo = new ShowTicketVO();
					stvo.setTicket_no(at.getTicket_no());
					stvo.setMember_no(at.getMember_no());
					stvo.setTicket_status(at.getTicket_status());
					
					stvo.setTicket_resale_status(at.getTicket_resale_status());
					stvo.setTicket_resale_price(at.getTicket_resale_price());
					stvo.setIs_from_resale(at.getIs_from_resale());
					stvo.setTicket_order_no(at.getTicketorderVO().getTicket_order_no());
					
					SeatingArea_H5_VO sh5VO = sh5Svc.getOneSeatingArea_H5(at.getSeatingarea_h5VO().getTicarea_no());
					stvo.setTicarea_name(sh5VO.getTicarea_name());
					stvo.setTicarea_color(sh5VO.getTicarea_color());
					stvo.setTictype_name(sh5VO.getTickettype_h5VO().getTictype_name());
					stvo.setTictype_price(sh5VO.getTickettype_h5VO().getTictype_price());
					
					Event_H5_VO eh5VO = eh5Svc.getOneEvent_H5(sh5VO.getEve_h5VO().getEve_no());
					stvo.setEve_sessionname(eh5VO.getEve_sessionname());
					stvo.setEve_startdate(eh5VO.getEve_startdate());
					stvo.setEve_enddate(eh5VO.getEve_enddate());
					stvo.setEve_offsaledate(eh5VO.getEve_offsaledate());
					stvo.setEvetit_name(eh5VO.getEventtitle_h5VO().getEvetit_name());
					stvo.setVenue_name(eh5VO.getVenue_h5VO().getVenue_name());
					stvo.setAddress(eh5VO.getVenue_h5VO().getAddress());
					listShow.add(stvo);
				}
				req.setAttribute("listShow", listShow);
				/* 被此行包圍的是為了讓listShow可以重複被 member_selectT_ticets.jsp利用並顯示 */
				
				String url = "/frontend/ticketorder/member_select_tickets.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
				
			}catch(Exception e) {
				//this time maybe forward back to login.jsp or sendRedirect to it.
				errorMsgs.add("從資料庫查詢失敗,at controller:member_select_ticketorders:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/frontend/ticketorder/evetit_no_SelectedReady2Next.jsp");
				failureView.forward(req, res);
			}
        	
			
        }
        
//        if("buyticket_EVE_NO_picked_phaseOne".equals(action)) {
//        	System.out.println("bear_controller_phaseone called");
//        	List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
////			String requestURL = req.getParameter("requestURL");
//			MemberVO member = (MemberVO)session.getAttribute("member");
////			System.out.println("bear session.getmember="+member.getMemberNo());
//			
//			try {
//				String eve_no = req.getParameter("eve_no");
////				if (eve_no == null || (eve_no.trim()).length() == 0) {
////					errorMsgs.add("請輸入eve_no編號");
////				}
////				if (eve_no.length()>7) {
////					errorMsgs.add("eve_no格式不正確");
////				}
////				if (this.containsHanScript(eve_no)) {
////					errorMsgs.add("eve_no不可包含中文");
////				}
//				
//				String member_no = member.getMemberNo();
////				System.out.println("bear member.getmemberno="+member);
//				if (member_no == null || (member_no.trim()).length() == 0) {
//					errorMsgs.add("購票前請登入");
//				}
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/frontend/login_front-end.jsp"); 
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				//db contact starts
//				Event_H5_Service eh5Svc = new Event_H5_Service();
//				Event_H5_VO eh5vo = eh5Svc.getOneEvent_H5(eve_no);
//				
////				if(eh5vo == null) {
////					errorMsgs.add("No data after searching with this eve_no");
////				}
//				
//				//error return make sure which parameters I should send.
////				if (!errorMsgs.isEmpty()) {
////					RequestDispatcher failureView = req
////							.getRequestDispatcher("/frontend/ticketorder/select_page.jsp"); 
////					failureView.forward(req, res);
////					return;//程式中斷
////				}
//				
//				//start query
////				SeatingArea_H5_Service sah5Svc = new SeatingArea_H5_Service();
////				List<SeatingArea_H5_VO> list = sah5Svc.get_SeatingArea_H5_VOs_ByEveNo(eve_no);
//				
////				System.out.println("bear after login, get member_no="+member_no);
////				req.setAttribute("slist", list);
//				req.setAttribute("eh5vo", eh5vo);
//				req.setAttribute("member_no", member_no);
//				
////				System.out.println("Before request.forward, member_no="+member_no);
//				String url = "/frontend/ticketorder/buyticket_EVE_NO_picked_phaseOne.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); 
//				successView.forward(req, res);
//				
//				
//			}catch(Exception e) {
//				errorMsgs.add("errorAtTicketOrderServlet.java HereMsg:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/frontend/index.jsp");
//				failureView.forward(req, res);
//			}
//        }
//        
//        if("buyticket_EVE_NO_picked_phaseOne".equals(action)) {
//        	List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//			String member_no = req.getParameter("member_no");
//			System.out.println("buyticket_EVE_NO_picked_phaseOne_bear_req.getparameter member_no="+member_no);
//			
//			try {
//				String ticarea_no = req.getParameter("ticarea_no");
//				String ticketsNum = req.getParameter("ticketsNum");
//				String tictype_no = req.getParameter("tictype_no");
//				String eve_no = req.getParameter("eve_no");
//				
//				//ticarea_no
//				if (ticarea_no == null || (ticarea_no.trim()).length() == 0) {
//					errorMsgs.add("請輸入ticarea_no編號");
//				}
//				if (ticarea_no.length()>10) {
//					errorMsgs.add("ticarea_no格式不正確");
//				}
//				if (this.containsHanScript(ticarea_no)) {
//					errorMsgs.add("ticarea_no不可包含中文");
//				}
//				
//				//ticketsNum
//				if (ticketsNum == null || (ticketsNum.trim()).length() == 0) {
//					errorMsgs.add("請輸入ticketsNum");
//				}
//				if (ticketsNum.length()>11) {
//					errorMsgs.add("ticketsNum格式不正確");
//				}
//				if (this.containsHanScript(ticketsNum)) {
//					errorMsgs.add("ticketsNum不可包含中文");
//				}
//				
//				//tictype_no
//				if (tictype_no == null || (tictype_no.trim()).length() == 0) {
//					errorMsgs.add("請輸入tictype_no編號");
//				}
//				if (tictype_no.length()>8) {
//					errorMsgs.add("tictype_no格式不正確");
//				}
//				if (this.containsHanScript(tictype_no)) {
//					errorMsgs.add("tictype_no不可包含中文");
//				}
//				
//				//these two req.set is used iat buytickets_phaseOne, so add them before errorMsg failview forwarding.
//				req.setAttribute("member_no", member_no);
//				
//				//db contact
//				Event_H5_Service eh5Svc = new Event_H5_Service();
//				Event_H5_VO eh5vo = eh5Svc.getOneEvent_H5(eve_no);
//				req.setAttribute("eh5vo", eh5vo);
//				
//				//controller's error-condition happens, forward to buy 
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/frontend/ticketorder/buyticket_EVE_NO_picked_phaseOne.jsp"); 
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				//start to inserTicketOrder and update target seatingarea.
//				Integer total_amount = null;
//				try {
//					total_amount = new Integer(Integer.parseInt(ticketsNum));
//					if(total_amount > 100100 || total_amount <0) {
//						errorMsgs.add("總張數請勿亂填數字.");
//					}
//				} catch (NumberFormatException e) {
//					total_amount = 0;
//					errorMsgs.add("總張數請填數字.");
//				}
//				
//				Integer total_price = null;
//				try {
//					TicketType_H5_Service ttyh5Svc = new TicketType_H5_Service();
//					TicketType_H5_VO ttyh5VO = ttyh5Svc.findByPrimaryKey(tictype_no);
//					Integer oneTicketPrice = new Integer(ttyh5VO.getTictype_price());
//					total_price = oneTicketPrice*total_amount;
//					if(total_amount > 10100100 || total_amount <0) {
//						errorMsgs.add("總價請勿亂填數字.");
//					}
//				} catch (NumberFormatException e) {
//					total_amount = 0;
//					errorMsgs.add("總價請填數字.");
//				}
//				
//				java.sql.Timestamp ticket_order_time = new java.sql.Timestamp(System.currentTimeMillis());
//				String payment_method = "NOTYET";
//				String ticket_order_status = "WAITTOPAY1";
//				
//				//controller's error-condition happens, forward to buy 
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/frontend/ticketorder/buyticket_EVE_NO_picked_phaseOne.jsp"); 
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				//db contact
//				SeatingArea_H5_Service sh5Svc = new SeatingArea_H5_Service();
//				SeatingArea_H5_VO svo = sh5Svc.getOneSeatingArea_H5(ticarea_no);
//				
//				//these line use controller to deal tickets chasing
//				if(total_amount+svo.getTicbookednumber() > svo.getTictotalnumber()) {
//					errorMsgs.add("選取的目標活動的剩餘票數已不足，請重新購票");
//				}
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/frontend/ticketorder/buyticket_EVE_NO_picked_phaseOne.jsp"); 
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				System.out.println("dbonce");
//				
//				TicketOrderVO toVO = new TicketOrderVO();
//				toVO.setMember_no(member_no);
//				toVO.setTotal_price(total_price);
//				toVO.setTotal_amount(total_amount);
//				toVO.setTicket_order_time(ticket_order_time);
//				toVO.setPayment_method(payment_method);
//				toVO.setTicket_order_status(ticket_order_status);
//				
//				System.out.println("dbonce2");
//				
//				Integer original_ticbookednumber = svo.getTicbookednumber();
//				Integer update_ticbookednumber = original_ticbookednumber+total_amount;
//				svo.setTicbookednumber(update_ticbookednumber);
//				
//				System.out.println("dbonce3");
//				
//				toVO.setSeatingarea_h5VO(svo);
//				System.out.println("dbonce4");
//				TicketOrderService toSvc = new TicketOrderService();
//				//session.setAttribute("createdTicketOrderNo", createdTicketOrderNo);
//				String createdTicketOrderNo = toSvc.insertThenGetLatestToNoWithCondition(toVO);
//				System.out.println("dbonce5");
//				
//				TicketOrderWithTicArea_HSB_Listener ticketOrderListener = new TicketOrderWithTicArea_HSB_Listener();
//				session.setAttribute(createdTicketOrderNo, ticketOrderListener);
//				System.out.println("dbonce6");
//				
//				TicketOrderVO toVO_sendTo_jsp = toSvc.getOneTicketOrder(createdTicketOrderNo);
//				System.out.println("dbonce7");
//				req.setAttribute("toVO", toVO_sendTo_jsp);
//				System.out.println("dbonce8");
//				
////				SeatingArea_H5_VO sh5VO = sh5Svc.getOneSeatingArea_H5(ticarea_no);
////				req.setAttribute("sh5VO", sh5VO);
//				
//				//buyticket_TICKET_ORDER_created_phaseTwo
//				String url = "/frontend/ticketorder/buyticket_TICKET_ORDER_created_phaseTwo.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);
//				
//			}catch(Exception e) {
//				errorMsgs.add("errorAtTicketOrderServlet.java:buyticket_EVE_NO_picked_phaseOne,HereMsg:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/frontend/ticketorder/buyticket_EVE_NO_picked_phaseOne.jsp");
//				failureView.forward(req, res);
//			}
//        }
        
        //under this line is controller for 20190118 pre-demo
//        if("buyticket_NoImg_EVE_NO_picked_phaseOne".equals(action)) {
//        	List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//        	MemberVO member = (MemberVO)session.getAttribute("member");
//        	System.out.println("buyticket_NoImg_EVE_NO_picked_phaseOne.member="+member);
//        	
//        	try {
//        		String eve_no = req.getParameter("eve_no");
//        		String member_no = member.getMemberNo();
//        		if (member_no == null || (member_no.trim()).length() == 0) {
//					errorMsgs.add("購票前請登入");
//				}
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/frontend/login_front-end.jsp"); 
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//        		
//				Event_H5_Service eh5Svc = new Event_H5_Service();
//				Event_H5_VO eh5vo = eh5Svc.getOneEvent_H5(eve_no);
//				
//				Map<String, String[]> map = new TreeMap<String, String[]>();
//				map.put("eve_no", new String[] {eve_no});
//				SeatingArea_H5_Service sh5Svc = new SeatingArea_H5_Service();
//				List<SeatingArea_H5_VO> list = sh5Svc.getAll(map, "ticarea_no");
//				
//				System.out.println("before.setAttrivute");
//				req.setAttribute("eh5vo", eh5vo);
//				req.setAttribute("slist", list);
//				req.setAttribute("member_no", member_no);
//				
//				System.out.println("before suvvessView.forward");
//        		String url = "/frontend/ticketorder/buyticket_NoImg_EVE_NO_picked_phaseOne.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); 
//				successView.forward(req, res);
//        	} catch(Exception e) {
//        		errorMsgs.add("Failed at c.action.buyticket_NoImg_EVE_NO_picked_phaseOne, Msg:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/frontend/ticketorder/buyticket_NoImg_EVE_NO_picked_phaseOne.jsp");
//				failureView.forward(req, res);
//        	}
//        	
//        }
//        
//        if("buyticket_NoImg_TICKET_ORDER_created_phaseTwo".equals(action)) {
//        	List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//			
//			String eve_no = req.getParameter("eve_no");
//			String ticarea_no = req.getParameter("ticarea_no");
//			String ticketsNum = req.getParameter("ticketsNum");
//			String tictype_no = req.getParameter("tictype_no");
//			String member_no = req.getParameter("member_no");
//			System.out.println("buyticket_NoImg_TICKET_ORDER_created_phaseTwo.getparameter member_no="+member_no);
//			
//        	try {
//        		
//        		//member_no
//				if (member_no == null || (member_no.trim()).length() == 0) {
//					errorMsgs.add("member_no is null");
//				}
//        		req.setAttribute("member_no", member_no); //might cause problem because add null into req?
//        		//db contact
//				Event_H5_Service eh5Svc = new Event_H5_Service();
//				Event_H5_VO eh5vo = eh5Svc.getOneEvent_H5(eve_no);
//				Map<String, String[]> map = new TreeMap<String, String[]>();
//				map.put("eve_no", new String[] {eve_no});
//				SeatingArea_H5_Service sh5Svc = new SeatingArea_H5_Service();
//				List<SeatingArea_H5_VO> list = sh5Svc.getAll(map, "ticarea_no");
//				req.setAttribute("eh5vo", eh5vo);
//				req.setAttribute("slist", list);
//				
//				//controller's error-condition happens, forward to buy 
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/frontend/ticketorder/buyticket_NoImg_EVE_NO_picked_phaseOne.jsp"); 
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				//start to inserTicketOrder and update target seatingarea.
//				Integer total_amount = null;
//				try {
//					total_amount = new Integer(Integer.parseInt(ticketsNum));
//					if(total_amount > 100100 || total_amount <0) {
//						errorMsgs.add("總張數請勿亂填數字.");
//					}
//				} catch (NumberFormatException e) {
//					total_amount = 0;
//					errorMsgs.add("總張數請填數字.");
//				}
//				
//				Integer total_price = null;
//				try {
//					TicketType_H5_Service ttyh5Svc = new TicketType_H5_Service();
//					TicketType_H5_VO ttyh5VO = ttyh5Svc.findByPrimaryKey(tictype_no);
//					Integer oneTicketPrice = new Integer(ttyh5VO.getTictype_price());
//					total_price = oneTicketPrice*total_amount;
//					if(total_amount > 10100100 || total_amount <0) {
//						errorMsgs.add("總價 請勿亂填數字.");
//					}
//				} catch (NumberFormatException e) {
//					total_amount = 0;
//					errorMsgs.add("總價 請填數字.");
//				}
//				
//				java.sql.Timestamp ticket_order_time = new java.sql.Timestamp(System.currentTimeMillis());
//				String payment_method = "NOTYET";
//				String ticket_order_status = "WAITTOPAY1";
//				
//				
//				
//				//db contact
//				SeatingArea_H5_VO svo = sh5Svc.getOneSeatingArea_H5(ticarea_no);
//				
//				//these line use controller to deal tickets chasing
//				if(total_amount+svo.getTicbookednumber() > svo.getTictotalnumber()) {
//					errorMsgs.add("選取的目標活動的剩餘票數已不足，請重新購票");
//				}
//				//controller's error-condition happens, forward to buy 
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/frontend/ticketorder/buyticket_NoImg_EVE_NO_picked_phaseOne.jsp"); 
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				//dbcontact with transaction function
//				TicketOrderVO toVO = new TicketOrderVO();
//				toVO.setMember_no(member_no);
//				toVO.setTotal_price(total_price);
//				toVO.setTotal_amount(total_amount);
//				toVO.setTicket_order_time(ticket_order_time);
//				toVO.setPayment_method(payment_method);
//				toVO.setTicket_order_status(ticket_order_status);
//				
//				Integer original_ticbookednumber = svo.getTicbookednumber();
//				Integer update_ticbookednumber = original_ticbookednumber+total_amount;
//				svo.setTicbookednumber(update_ticbookednumber);
//				
//				toVO.setSeatingarea_h5VO(svo);
//				TicketOrderService toSvc = new TicketOrderService();
//				//session.setAttribute("createdTicketOrderNo", createdTicketOrderNo);
//				String createdTicketOrderNo = toSvc.insertThenGetLatestToNoWithCondition(toVO);
//				
//				TicketOrderWithTicArea_HSB_Listener ticketOrderListener = new TicketOrderWithTicArea_HSB_Listener();
//				session.setAttribute(createdTicketOrderNo, ticketOrderListener);
//				
//				TicketOrderVO tvo = toSvc.getOneTicketOrder(createdTicketOrderNo);
//        		
//				//start fit ShowTicketOrderVO
//				ShowTicketOrderVO shtovo = new ShowTicketOrderVO();
//				shtovo.setTicket_order_no(createdTicketOrderNo);
//				shtovo.setMember_no(member_no);
//				shtovo.setTotal_price(tvo.getTotal_price());
//				shtovo.setTotal_amount(tvo.getTotal_amount());
//				shtovo.setTicket_order_time(tvo.getTicket_order_time());
//				shtovo.setPayment_method(tvo.getPayment_method());
//				shtovo.setTicket_order_status(tvo.getTicket_order_status());
//				
//				shtovo.setTicarea_no(tvo.getSeatingarea_h5VO().getTicarea_no());
//				shtovo.setTicarea_name(tvo.getSeatingarea_h5VO().getTicarea_name());
//				shtovo.setTicarea_color(tvo.getSeatingarea_h5VO().getTicarea_color());
//				
//				SeatingArea_H5_VO sh5VO = sh5Svc.getOneSeatingArea_H5(tvo.getSeatingarea_h5VO().getTicarea_no());
//				shtovo.setTictype_no(sh5VO.getTickettype_h5VO().getTictype_no());
//				shtovo.setTictype_price(sh5VO.getTickettype_h5VO().getTictype_price());
//				
//				Event_H5_VO eh5VO = eh5Svc.getOneEvent_H5(sh5VO.getEve_h5VO().getEve_no());
//				shtovo.setEve_no(eh5VO.getEve_no());
//				shtovo.setEve_sessionname(eh5VO.getEve_sessionname());
//				
//				shtovo.setEve_startdate(eh5VO.getEve_startdate());
//				shtovo.setEve_enddate(eh5VO.getEve_enddate());
//				shtovo.setEve_offsaledate(eh5VO.getEve_offsaledate());
//				
//				shtovo.setVenue_name(eh5VO.getVenue_h5VO().getVenue_name());
//				shtovo.setAddress(eh5VO.getVenue_h5VO().getAddress());
//				
//				shtovo.setEvetit_name(eh5VO.getEventtitle_h5VO().getEvetit_name());
//				
//				req.setAttribute("shtovo", shtovo);
//				
//        		String url = "/frontend/ticketorder/buyticket_NoImg_TICKET_ORDER_created_phaseTwo.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); 
//				successView.forward(req, res);
//        	} catch(Exception e) {
//        		errorMsgs.add("從資料庫複合查詢失敗,at buyticket_NoImg_EVE_NO_picked_phaseOne.jsp:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/frontend/ticketorder/buyticket_NoImg_EVE_NO_picked_phaseOne.jsp");
//				failureView.forward(req, res);
//        	}
//        	
//        }
//        
//        if("buyticket_NoImg_done_phaseThree".equals(action)) {
//        	List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//        	
//        	try {
//        		String ticket_order_no = req.getParameter("ticket_order_no");
//        		String member_no = req.getParameter("member_no");
//        		
//				String creditCardNumber = req.getParameter("creditCardNumber");
//				String creditCardVerificationNumber = req.getParameter("creditCardVerificationNumber");
//        		
//				//creditCardNumber
//				if (creditCardNumber == null || (creditCardNumber.trim()).length() == 0) {
//					errorMsgs.add("請輸入信用卡編號16碼");
//				}
//				if (creditCardNumber.length()>16) {
//					errorMsgs.add("信用卡格式不正確，應為16碼的數字");
//				}
//				if (this.containsHanScript(creditCardNumber)) {
//					errorMsgs.add("信用卡編號不可包含中文");
//				}
//				//creditCardVerificationNumber
//				if (creditCardVerificationNumber == null || (creditCardVerificationNumber.trim()).length() == 0) {
//					errorMsgs.add("請輸入信用卡驗證碼3碼");
//				}
//				if (creditCardVerificationNumber.length()>3) {
//					errorMsgs.add("信用卡驗證碼格式不正確");
//				}
//				if (this.containsHanScript(creditCardVerificationNumber)) {
//					errorMsgs.add("信用卡驗證碼不可包含中文");
//				}
//				
//				//get ShowTicketOrderVO for controller-error-detect forward
//				TicketOrderService toSvc = new TicketOrderService();
//				TicketOrderVO tvo = toSvc.getOneTicketOrder(ticket_order_no);
//        		
//				//start fit ShowTicketOrderVO
//				ShowTicketOrderVO shtovo = new ShowTicketOrderVO();
//				shtovo.setTicket_order_no(ticket_order_no);
//				shtovo.setMember_no(member_no);
//				shtovo.setTotal_price(tvo.getTotal_price());
//				shtovo.setTotal_amount(tvo.getTotal_amount());
//				shtovo.setTicket_order_time(tvo.getTicket_order_time());
//				shtovo.setPayment_method(tvo.getPayment_method());
//				shtovo.setTicket_order_status(tvo.getTicket_order_status());
//				
//				shtovo.setTicarea_no(tvo.getSeatingarea_h5VO().getTicarea_no());
//				shtovo.setTicarea_name(tvo.getSeatingarea_h5VO().getTicarea_name());
//				shtovo.setTicarea_color(tvo.getSeatingarea_h5VO().getTicarea_color());
//				
//				SeatingArea_H5_Service sh5Svc = new SeatingArea_H5_Service();
//				SeatingArea_H5_VO sh5VO = sh5Svc.getOneSeatingArea_H5(tvo.getSeatingarea_h5VO().getTicarea_no());
//				shtovo.setTictype_no(sh5VO.getTickettype_h5VO().getTictype_no());
//				shtovo.setTictype_price(sh5VO.getTickettype_h5VO().getTictype_price());
//				
//				Event_H5_Service eh5Svc = new Event_H5_Service();
//				Event_H5_VO eh5VO = eh5Svc.getOneEvent_H5(sh5VO.getEve_h5VO().getEve_no());
//				shtovo.setEve_no(eh5VO.getEve_no());
//				shtovo.setEve_sessionname(eh5VO.getEve_sessionname());
//				
//				shtovo.setEve_startdate(eh5VO.getEve_startdate());
//				shtovo.setEve_enddate(eh5VO.getEve_enddate());
//				shtovo.setEve_offsaledate(eh5VO.getEve_offsaledate());
//				
//				shtovo.setVenue_name(eh5VO.getVenue_h5VO().getVenue_name());
//				shtovo.setAddress(eh5VO.getVenue_h5VO().getAddress());
//				
//				shtovo.setEvetit_name(eh5VO.getEventtitle_h5VO().getEvetit_name());
//				req.setAttribute("shtovo", shtovo);
//				
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/frontend/ticketorder/buyticket_NoImg_TICKET_ORDER_created_phaseTwo.jsp"); 
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				TicketOrderVO toVOForUpdate = toSvc.getOneTicketOrder(ticket_order_no);
//				if (toVOForUpdate == null) {
//					errorMsgs.add("查無資料");
//				}
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/frontend/ticketorder/buyticket_NoImg_TICKET_ORDER_created_phaseTwo.jsp"); 
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				String targetToVoStatus = toVOForUpdate.getTicket_order_status();
//				if("OUTDATE4".equals(targetToVoStatus)) {
//					errorMsgs.add("This TicketOrder is been canceled by server because of payment time-limit.");
//				}
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/frontend/ticketorder/buyticket_NoImg_TICKET_ORDER_created_phaseTwo.jsp"); 
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				//transfer hibernate tovo into tovo2 for JNDI to use
//				TicketOrderVO2 tovo2 = new TicketOrderVO2();
//				tovo2.setTicket_order_no(ticket_order_no);
//				tovo2.setMember_no(toVOForUpdate.getMember_no());
//				tovo2.setTicarea_no(toVOForUpdate.getSeatingarea_h5VO().getTicarea_no());
//				tovo2.setTotal_price(toVOForUpdate.getTotal_price());
//				tovo2.setTotal_amount(toVOForUpdate.getTotal_amount());
//				tovo2.setTicket_order_time(toVOForUpdate.getTicket_order_time());
//				tovo2.setPayment_method("CREDITCARD");
//				tovo2.setTicket_order_status("COMPLETE2");
//				
//				List<TicketVO2> t2list = new ArrayList<TicketVO2>();
//				for(int i = 1; i<=tovo2.getTotal_amount();i++) {
//					
//					String ticket_status = "ACTIVE1";
//					java.sql.Timestamp ticket_create_time = new java.sql.Timestamp(System.currentTimeMillis());;
//					String ticket_resale_status = "NONE1";
//					Integer ticket_resale_price = 0;
//					String is_from_resale = "NO";
//					
//					TicketVO2 tVO2 = new TicketVO2();
//					tVO2.setTicarea_no(tovo2.getTicarea_no());
//					tVO2.setMember_no(tovo2.getMember_no());
//					tVO2.setTicket_status(ticket_status);
//					tVO2.setTicket_create_time(ticket_create_time);
//					tVO2.setTicket_resale_status(ticket_resale_status);
//					tVO2.setTicket_resale_price(ticket_resale_price);
//					tVO2.setIs_from_resale(is_from_resale);
//					t2list.add(tVO2);
//				}
//				
//				//call transaction function in JNDI.
//				toSvc.updateTicketOrderAndInsertTickets(tovo2, t2list);
//				
//				TicketOrderVO updated_tovo = toSvc.getOneTicketOrder(ticket_order_no);
//				req.setAttribute("updated_tovo", updated_tovo);
//				
//        		String url = "/frontend/ticketorder/buyticket_NoImg_done_phaseThree.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); 
//				successView.forward(req, res);
//        	} catch(Exception e) {
//        		errorMsgs.add("failed at buyticket_NoImg_TICKET_ORDER_created_phaseTwo.jsp ErrorMsg:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/frontend/ticketorder/buyticket_NoImg_TICKET_ORDER_created_phaseTwo.jsp");
//				failureView.forward(req, res);
//        	}
//        	
//        	
//        	
//        }
        
        if("member_cancel_target_ticketorder".equals(action)) {
        	List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
        	String ticket_order_no = req.getParameter("ticket_order_no");
        	String memer_no = req.getParameter("memer_no");
        	req.setAttribute("memer_no", memer_no);
        	
        	
        	try {
            	TicketOrderService toSvc = new TicketOrderService();
            	String str = toSvc.cancelTicketOrderByUser(ticket_order_no);
            	System.out.println("the target of cancel success, the ticket_order_no="+str);
            	
            	String url = "/frontend/ticketorder/listAllTicketOrderBymember_no.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
				
        	}catch(Exception e) {
        		errorMsgs.add("取消該訂票訂單失敗，錯誤訊息:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/frontend/ticketorder/listAllTicketOrderBymember_no.jsp");
				failureView.forward(req, res);
        	}
        }
        
        

	}
	
	public boolean containsHanScript(String s) {
	    for (int i = 0; i < s.length(); ) {
	        int codepoint = s.codePointAt(i);
	        i += Character.charCount(codepoint);
	        if (Character.UnicodeScript.of(codepoint) == Character.UnicodeScript.HAN) {
	            return true;
	        }
	    }
	    return false;
	}
}
