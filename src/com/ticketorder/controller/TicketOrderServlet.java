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
import com.seating_area.model.SeatingAreaService;
import com.seating_area.model.SeatingAreaVO;
import com.seating_area.model.SeatingArea_H5_Service;
import com.seating_area.model.SeatingArea_H5_VO;
import com.ticket.model.ShowTicketVO;
import com.ticket.model.TicketService;
import com.ticket.model.TicketVO;
import com.ticket.model.TicketVO2;
import com.ticket_type.model.TicketTypeService;
//import com.ticket_type.model.TicketTypeVO_temp;
import com.ticket_type.model.TicketType_H5_Service;
import com.ticket_type.model.TicketType_H5_VO;
import com.ticketorder.model.TicketOrderService;
import com.ticketorder.model.TicketOrderVO;
import com.ticketorder.model.TicketOrderVO2;

/**
 * Servlet implementation class TicketOrderServlet
 */
//@WebServlet("/TicketOrderServlet")
public class TicketOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public String aho = "asdf";
//	public Map<String, String> DB2Usermap = new HashMap<String, String>(){{
//	    put("a", "apple"); 
//	    put("b", "bear"); 
//	    put("c", "ccat");
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
		
		if("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("ticket_order_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入訂票訂單編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/ticketorder/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				if ((!str.contains("_")) || str.length()>18) {
					errorMsgs.add("訂票訂單編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/ticketorder/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				if (this.containsHanScript(str)) {
					errorMsgs.add("訂票訂單編號不可包含中文");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/ticketorder/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				TicketOrderService toSvc = new TicketOrderService();
				TicketOrderVO toVO = toSvc.getOneTicketOrder(str);
				if (toVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/ticketorder/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("toVO", toVO); 
				String url = "/frontend/ticketorder/listOneTicketOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/frontend/ticketorder/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOne_For_Update".equals(action)) { // 來自listAllTicketOrder.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String ticket_order_no = req.getParameter("ticket_order_no");
				
				/***************************2.開始查詢資料****************************************/
				TicketOrderService toSvc = new TicketOrderService();
				TicketOrderVO toVO = toSvc.getOneTicketOrder(ticket_order_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("toVO", toVO);
				String url = "/frontend/ticketorder/update_ticketorder_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/frontend/ticketorder/listAllTicketOrder.jsp");
				failureView.forward(req, res);
			}
		}
		if ("update".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String ticket_order_no = req.getParameter("ticket_order_no").trim();
				
				String member_no = req.getParameter("member_no");
				if (member_no == null || member_no.trim().length() == 0) {
					errorMsgs.add("會員編號: 請勿空白");
				} else if(!member_no.trim().equals("M000001")) { 
					errorMsgs.add("會員編號: 目前只能是M000001");
	            }
				
				String ticarea_no = req.getParameter("ticarea_no");
				if (ticarea_no == null || ticarea_no.trim().length() == 0) {
					errorMsgs.add("座位區編號: 請勿空白");
				} else if(!ticarea_no.trim().equals("ES00000001")) { 
					errorMsgs.add("座位區編號: 目前只能是ES00000001");
	            }
				
				Integer total_price = null;
				try {
					total_price = new Integer(req.getParameter("total_price").trim());
					if(total_price > 100100100 || total_price <0) {
						errorMsgs.add("總價請勿亂填數字.");
					}
				} catch (NumberFormatException e) {
					total_price = 0;
					errorMsgs.add("總價請填數字.");
				}
				
				Integer total_amount = null;
				try {
					total_amount = new Integer(req.getParameter("total_amount").trim());
					if(total_amount > 100100 || total_amount <0) {
						errorMsgs.add("總張數請勿亂填數字.");
					}
				} catch (NumberFormatException e) {
					total_amount = 0;
					errorMsgs.add("總張數請填數字.");
				}
				
				java.sql.Timestamp ticket_order_time = null;
				try {
					ticket_order_time = java.sql.Timestamp.valueOf(req.getParameter("ticket_order_time").trim());
				} catch (IllegalArgumentException e) {
					ticket_order_time=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入訂票訂單成立時間!");
				}
				
				String payment_method = req.getParameter("payment_method").trim();
				if (payment_method == null || payment_method.trim().length() == 0) {
					errorMsgs.add("付款方式請勿空白");
				} else if(payment_method.length()>12) {
					errorMsgs.add("付款方式請勿超過12碼");
				} else if(this.containsHanScript(payment_method)) {
					errorMsgs.add("付款方式請勿使用中文");
				}
				
				String ticket_order_status = req.getParameter("ticket_order_status").trim();
				if (ticket_order_status == null || ticket_order_status.trim().length() == 0) {
					errorMsgs.add("訂票訂單狀態請勿空白");
				} else if(ticket_order_status.length()>12) {
					errorMsgs.add("訂票訂單狀態請勿超過12碼");
				} else if(this.containsHanScript(ticket_order_status)) {
					errorMsgs.add("訂票訂單狀態請勿使用中文");
				}		
				
				TicketOrderVO toVO = new TicketOrderVO();
				toVO.setTicket_order_no(ticket_order_no);
				toVO.setMember_no(member_no);
//				toVO.setTicarea_no(ticarea_no);
				toVO.setTotal_price(total_price);
				toVO.setTotal_amount(total_amount);
				toVO.setTicket_order_time(ticket_order_time);
				toVO.setPayment_method(payment_method);
				toVO.setTicket_order_status(ticket_order_status);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("toVO", toVO); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/ticketorder/update_ticketorder_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				TicketOrderService toSvc = new TicketOrderService();
				toVO = toSvc.updateTicketOrder(ticket_order_no, member_no, ticarea_no, total_price, total_amount, ticket_order_time, payment_method, ticket_order_status);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("toVO", toVO); 
				String url = "/frontend/ticketorder/listOneTicketOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/frontend/ticketorder/update_ticketorder_input.jsp");
				failureView.forward(req, res);
			}
		}
        if ("insert".equals(action)) {   
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String member_no = req.getParameter("member_no");
				if (member_no == null || member_no.trim().length() == 0) {
					errorMsgs.add("會員編號: 請勿空白");
				} else if(!member_no.trim().equals("M000001")) { 
					errorMsgs.add("會員編號: 目前只能是M000001");
	            }
				
				String ticarea_no = req.getParameter("ticarea_no");
				if (ticarea_no == null || ticarea_no.trim().length() == 0) {
					errorMsgs.add("座位區編號: 請勿空白");
				} else if(!ticarea_no.trim().equals("ES00000001")) { 
					errorMsgs.add("座位區編號: 目前只能是ES00000001");
	            }
				
				Integer total_price = null;
				try {
					total_price = new Integer(req.getParameter("total_price").trim());
					if(total_price > 100100100 || total_price <0) {
						errorMsgs.add("總價請勿亂填數字.");
					}
				} catch (NumberFormatException e) {
					total_price = 0;
					errorMsgs.add("總價請填數字.");
				}
				
				Integer total_amount = null;
				try {
					total_amount = new Integer(req.getParameter("total_amount").trim());
					if(total_amount > 100100 || total_amount <0) {
						errorMsgs.add("總張數請勿亂填數字.");
					}
				} catch (NumberFormatException e) {
					total_amount = 0;
					errorMsgs.add("總張數請填數字.");
				}
				
				java.sql.Timestamp ticket_order_time = null;
				try {
					ticket_order_time = java.sql.Timestamp.valueOf(req.getParameter("ticket_order_time").trim());
				} catch (IllegalArgumentException e) {
					ticket_order_time=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入訂票訂單成立時間!");
				}
				
				String payment_method = req.getParameter("payment_method").trim();
				if (payment_method == null || payment_method.trim().length() == 0) {
					errorMsgs.add("付款方式請勿空白");
				} else if(payment_method.length()>12) {
					errorMsgs.add("付款方式請勿超過12碼");
				} else if(this.containsHanScript(payment_method)) {
					errorMsgs.add("付款方式請勿使用中文");
				}
				
				String ticket_order_status = req.getParameter("ticket_order_status").trim();
				if (ticket_order_status == null || ticket_order_status.trim().length() == 0) {
					errorMsgs.add("訂票訂單狀態請勿空白");
				} else if(ticket_order_status.length()>12) {
					errorMsgs.add("訂票訂單狀態請勿超過12碼");
				} else if(this.containsHanScript(ticket_order_status)) {
					errorMsgs.add("訂票訂單狀態請勿使用中文");
				}			
				
				TicketOrderVO toVO = new TicketOrderVO();
				toVO.setMember_no(member_no);
//				toVO.setTicarea_no(ticarea_no);
				toVO.setTotal_price(total_price);
				toVO.setTotal_amount(total_amount);
				toVO.setTicket_order_time(ticket_order_time);
				toVO.setPayment_method(payment_method);
				toVO.setTicket_order_status(ticket_order_status);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("toVO", toVO); // 含有輸入格式錯誤的toVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/ticketorder/addTicketOrder.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				TicketOrderService toSvc = new TicketOrderService();
				toVO = toSvc.addTicketOrder(member_no, ticarea_no, total_price, total_amount, ticket_order_time, payment_method, ticket_order_status);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/frontend/ticketorder/listAllTicketOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/frontend/ticketorder/addTicketOrder.jsp");
				failureView.forward(req, res);
			}
		}
        if ("delete".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String ticket_order_no = req.getParameter("ticket_order_no");
				
				/***************************2.開始刪除資料***************************************/
				TicketOrderService toSvc = new TicketOrderService();
				toSvc.deleteTicketOrder(ticket_order_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/frontend/ticketorder/listAllTicketOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/frontend/ticketorder/listAllTicketOrder.jsp");
				failureView.forward(req, res);
			}
		}
        if("get_EventTitleVO_InfoToBuyTickets".equals(action)) {
        	List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String evetit_no = req.getParameter("evetit_no");
				if (evetit_no == null || (evetit_no.trim()).length() == 0) {
					errorMsgs.add("請輸入evetit_no編號");
				}
				if (evetit_no.length()>18) {
					errorMsgs.add("evetit_no格式不正確");
				}
				if (this.containsHanScript(evetit_no)) {
					errorMsgs.add("evetit_no不可包含中文");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/ticketorder/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				//驗證錯誤結束，準備開始call service
				EventService eSvc = new EventService();
				List<EventVO> list = eSvc.findByEveTit_no(evetit_no);
				if (list == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/ticketorder/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				req.setAttribute("elist", list);
				
//				String url = "/frontend/ticketorder/buyTicketsSim.jsp";
				
				String url = "/frontend/ticketorder/evetit_no_SelectedReady2Next.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
				
			}catch(Exception e) {
				errorMsgs.add("從資料庫取list<EventVO>失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/frontend/ticketorder/select_page.jsp");
				failureView.forward(req, res);
			}
        }
        // get eve_no to next buyticket.jsp ,after this line,co-operate with event programmer
        if("select_EVE_NO_toBuyTickets".equals(action)) {
        	
        	List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String eve_no = req.getParameter("eve_no");
				if (eve_no == null || (eve_no.trim()).length() == 0) {
					errorMsgs.add("請輸入eve_no編號");
				}
				if (eve_no.length()>18) {
					errorMsgs.add("eve_no格式不正確");
				}
				if (this.containsHanScript(eve_no)) {
					errorMsgs.add("eve_no不可包含中文");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/ticketorder/evetit_no_SelectedReady2Next.jsp"); //set back to event.jsp
					failureView.forward(req, res);
					return;//程式中斷
				}
				
//				String strRequestFrom = req.getParameter("requestURL");
//				System.out.println("strRequestFrom="+strRequestFrom);
				
				//驗證錯誤結束，準備開始call service
				Map<String, String[]> map = new TreeMap<String, String[]>();
				map.put("eve_no", new String[] {eve_no});
				SeatingArea_H5_Service sh5Svc = new SeatingArea_H5_Service();
				List<SeatingArea_H5_VO> list = sh5Svc.getAll(map, "ticarea_no");
				
				Event_H5_Service eh5Svc = new Event_H5_Service();
				Event_H5_VO eh5vo = eh5Svc.getOneEvent_H5(eve_no);
				
				if (list == null) {
					errorMsgs.add("查無資料");
				}
				// Send the user back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/ticketorder/evetit_no_SelectedReady2Next.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				req.setAttribute("slist", list);
				req.setAttribute("eh5vo", eh5vo); 
				
//				session.setAttribute("slist", list);
//				session.setAttribute("eh5vo", eh5vo);
				
//				String url = "/frontend/ticketorder/buyTicketsSimTwo.jsp";
				
				String url = "/frontend/ticketorder/eve_no_SelectedReady2Next.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
				
			}catch(Exception e) {
				errorMsgs.add("從資料庫複合查詢失敗,at evetit_no_SelectedReady2Next.jsp:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/frontend/ticketorder/evetit_no_SelectedReady2Next.jsp");
				failureView.forward(req, res);
			}
        }
        if("ticketNumSelected_buyTickets".equals(action)) {
        	
        	//set every session who selected ticketnums, maybe set when successful request to select payment.jsp
//        	session.setMaxInactiveInterval(60);
        	
        	List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			//一個空的List跟ehtVO是為了輸出錯誤或exception發生時，導回上一頁仍取的到直
			List<SeatingArea_H5_VO> list = new LinkedList<SeatingArea_H5_VO>();
			Event_H5_VO eh5vo = new Event_H5_VO();
			List<String> list4PassValue = new LinkedList<String>();
			
			//set eve_no to req to get data user want to see.
			String eve_no = req.getParameter("eve_no");
			
			try {
				String ticarea_no = req.getParameter("ticarea_no");
				String ticketsNum = req.getParameter("ticketsNum");
				String tictype_no = req.getParameter("tictype_no");
				
				
				list4PassValue.add(req.getParameter("evetit_nameForShow"));
				list4PassValue.add(req.getParameter("eve_sessionnameForShow"));
				list4PassValue.add(req.getParameter("eve_startdateForShow"));
				list4PassValue.add(req.getParameter("eve_enddateForShow"));
				list4PassValue.add(req.getParameter("venue_nameForShow"));
				list4PassValue.add(req.getParameter("addressForShow"));
				list4PassValue.add(req.getParameter("tictype_nameForShow"));
				
				//用很笨的方式讓使用者不至於因為控制器擋下錯誤的要求而重刷葉面沒有資料
				Map<String, String[]> map = new TreeMap<String, String[]>();
				map.put("eve_no", new String[] {eve_no});
				SeatingArea_H5_Service sh5Svc = new SeatingArea_H5_Service();
				list = sh5Svc.getAll(map, "ticarea_no");
				Event_H5_Service eh5Svc = new Event_H5_Service();
				eh5vo = eh5Svc.getOneEvent_H5(eve_no);
				req.setAttribute("slist", list);
				req.setAttribute("eh5vo", eh5vo);
				req.setAttribute("eve_no", eve_no);
				req.setAttribute("list4PassValue", list4PassValue);
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
				
				//ticarea_no
				if (ticarea_no == null || (ticarea_no.trim()).length() == 0) {
					errorMsgs.add("請輸入ticarea_no編號");
				}
				if (ticarea_no.length()>18) {
					errorMsgs.add("ticarea_no格式不正確");
				}
				if (this.containsHanScript(ticarea_no)) {
					errorMsgs.add("ticarea_no不可包含中文");
				}
				
				//ticketsNum
				if (ticketsNum == null || (ticketsNum.trim()).length() == 0) {
					errorMsgs.add("請輸入ticketsNum");
				}
				if (ticketsNum.length()>18) {
					errorMsgs.add("ticketsNum格式不正確");
				}
				if (this.containsHanScript(ticketsNum)) {
					errorMsgs.add("ticketsNum不可包含中文");
				}
				
				//tictype_no
				if (tictype_no == null || (tictype_no.trim()).length() == 0) {
					errorMsgs.add("請輸入tictype_no編號");
				}
				if (tictype_no.length()>18) {
					errorMsgs.add("tictype_no格式不正確");
				}
				if (this.containsHanScript(tictype_no)) {
					errorMsgs.add("tictype_no不可包含中文");
				}
				
				//start to create ticket_order
				String member_no = "M000001"; //member still NOT implemented yet
				
				Integer total_amount = null;
				try {
					total_amount = new Integer(Integer.parseInt(ticketsNum));
					
					if(total_amount > 100100 || total_amount <0) {
						errorMsgs.add("總張數請勿亂填數字.");
					}
				} catch (NumberFormatException e) {
					total_amount = 0;
					errorMsgs.add("總張數請填數字.");
				}
				
				Integer total_price = null;
				try {
					TicketType_H5_Service ttyh5Svc = new TicketType_H5_Service();
					TicketType_H5_VO ttyh5VO = ttyh5Svc.findByPrimaryKey(tictype_no);
					Integer oneTicketPrice = new Integer(ttyh5VO.getTictype_price());
					total_price = oneTicketPrice*total_amount;
					
					if(total_amount > 10100100 || total_amount <0) {
						errorMsgs.add("總張數請勿亂填數字.");
					}
				} catch (NumberFormatException e) {
					total_amount = 0;
					errorMsgs.add("總張數請填數字.");
				}
				
				java.sql.Timestamp ticket_order_time = new java.sql.Timestamp(System.currentTimeMillis());
				String payment_method = "NOTYET";
				String ticket_order_status = "WAITTOPAY1";
				
				//錯誤處理並導回前頁
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/ticketorder/eve_no_SelectedReady2Next.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
//				SeatingArea_H5_Service sh5Svc = new SeatingArea_H5_Service();
				SeatingArea_H5_VO svo = sh5Svc.getOneSeatingArea_H5(ticarea_no); //此if判斷式的開頭有用過sh5Svc了 不用再宣告
				
				//these line use controller to deal tickets chasing
				if(total_amount+svo.getTicbookednumber() > svo.getTictotalnumber()) {
					errorMsgs.add("選取的目標活動的剩餘票數不足，請重新購票");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/ticketorder/eve_no_SelectedReady2Next.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				} 
				
				//錯誤處理完成，開始呼叫Service
				TicketOrderVO toVO = new TicketOrderVO();
				toVO.setMember_no(member_no);
				toVO.setTotal_price(total_price);
				toVO.setTotal_amount(total_amount);
				toVO.setTicket_order_time(ticket_order_time);
				toVO.setPayment_method(payment_method);
				toVO.setTicket_order_status(ticket_order_status);
				
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
				
//				toVO.setSeatingarea_h5VO(svolocal);
				toVO.setSeatingarea_h5VO(svo);
				
				//prepared toVO and sVO done, now call transaction function
				TicketOrderService toSvc = new TicketOrderService();
				String createdTicketOrderNo = toSvc.insertThenGetLatestToNoWithCondition(toVO);
				
				//maybe doesnt need it if you send a ticketordervo. dont need it cause key is different with listener's key
//				session.setAttribute("createdTicketOrderNo", createdTicketOrderNo); 
				
//				session.setAttribute("toVO", toVO);
//				session.setAttribute("sVO", sVO);
				
//				String CreatedTicketOrderNo = toSvc.insertTicketOrderAndUpdateTicArea(toVO, sVO);
				
				//set sessionAttribute for Listener Bound to use. NOTE:its not set for listener,but without it next page.jsp might fail
				session.setAttribute("createdTicketOrderNo", createdTicketOrderNo);
//				
//				//init HttpSessionBindingListener
				TicketOrderWithTicArea_HSB_Listener ticketOrderListener = new TicketOrderWithTicArea_HSB_Listener();
				session.setAttribute(createdTicketOrderNo, ticketOrderListener);
//				
				//maybe doesnt need it if you send a ticketordervo.
//				req.setAttribute("createdTicketOrderNo", createdTicketOrderNo);
				
				//set sessionLiveTime for payment deadline 
				session.setMaxInactiveInterval(300);
				TicketOrderVO toVO_sendTo_selectPaymentjsp = toSvc.getOneTicketOrder(createdTicketOrderNo);
				req.setAttribute("toVO", toVO_sendTo_selectPaymentjsp);
				
				//set visited value into session, if user wants to refresh the live time of session, call .invalidate()
//				session.setAttribute("isvisited", "yes");
				String url = "/frontend/ticketorder/selectPaymentAndPay.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch(NullPointerException ne) {
				errorMsgs.add("錯誤訊息如下:"+ne.getMessage());
				req.setAttribute("slist", list);
				req.setAttribute("eh5vo", eh5vo);
				req.setAttribute("eve_no", eve_no);
				req.setAttribute("list4PassValue", list4PassValue);
				RequestDispatcher failureView = req
						.getRequestDispatcher("/frontend/ticketorder/eve_no_SelectedReady2Next.jsp");
				failureView.forward(req, res);
				
			} catch(Exception e) {
				errorMsgs.add("資料庫更新該座位區已訂位數量錯誤，剩餘票數不足，錯誤訊息如下:"+e.getMessage());
				
//				String eve_no = (String) req.getAttribute("eve_no");
//				SeatingAreaService sSvc = new SeatingAreaService();
//				List<SeatingAreaVO> list = sSvc.getAllSeatingAreaByEveNo(eve_no);
//				
//				req.setAttribute("slist", list);
				
//				req.setAttribute("eve_no", eve_no);
//				System.out.println("從資料庫失敗 eve_no="+eve_no);
				
				req.setAttribute("slist", list);
				req.setAttribute("eh5vo", eh5vo);
				req.setAttribute("eve_no", eve_no);
				req.setAttribute("list4PassValue", list4PassValue);
				RequestDispatcher failureView = req
						.getRequestDispatcher("/frontend/ticketorder/eve_no_SelectedReady2Next.jsp");
				failureView.forward(req, res);
			}
        }
        if("userPaying".equals(action)) {
        	List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
//			List<String> list4PassValue = new LinkedList<String>();
			String eve_no = req.getParameter("eve_no");
			
			try {
				String ticket_order_no = req.getParameter("ticket_order_no");
				String creditCardNumber = req.getParameter("creditCardNumber");
				String creditCardVerificationNumber = req.getParameter("creditCardVerificationNumber");
				
//				//lsit4showInfo
//				list4PassValue.add(req.getParameter("evetit_nameForShow"));
//				list4PassValue.add(req.getParameter("eve_sessionnameForShow"));
//				list4PassValue.add(req.getParameter("eve_startdateForShow"));
//				list4PassValue.add(req.getParameter("eve_enddateForShow"));
//				list4PassValue.add(req.getParameter("venue_nameForShow"));
//				list4PassValue.add(req.getParameter("addressForShow"));
//				list4PassValue.add(req.getParameter("tictype_nameForShow"));
				
				//ticket_order_no
				if (ticket_order_no == null || (ticket_order_no.trim()).length() == 0) {
					errorMsgs.add("請輸入ticket_order_no編號");
				}
				if (ticket_order_no.length()>18) {
					errorMsgs.add("ticket_order_no格式不正確");
				}
				if (this.containsHanScript(ticket_order_no)) {
					errorMsgs.add("ticket_order_no不可包含中文");
				}
				//creditCardNumber
				if (creditCardNumber == null || (creditCardNumber.trim()).length() == 0) {
					errorMsgs.add("請輸入creditCardNumber編號");
				}
				if (creditCardNumber.length()>16) {
					errorMsgs.add("creditCardNumber格式不正確  <=16");
				}
				if (this.containsHanScript(creditCardNumber)) {
					errorMsgs.add("creditCardNumber不可包含中文");
				}
				//creditCardVerificationNumber
				if (creditCardVerificationNumber == null || (creditCardVerificationNumber.trim()).length() == 0) {
					errorMsgs.add("請輸入creditCardVerificationNumber編號");
				}
				if (creditCardVerificationNumber.length()>3) {
					errorMsgs.add("creditCardVerificationNumber格式不正確 <=3");
				}
				if (this.containsHanScript(creditCardVerificationNumber)) {
					errorMsgs.add("creditCardVerificationNumber不可包含中文");
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
//				req.setAttribute("list4PassValue", list4PassValue);
				
				//check toVO's status to let user cant update the status when it's OUTDATE4
				String targetToVoStatus = toVO.getTicket_order_status();
				if("OUTDATE4".equals(targetToVoStatus)) {
					errorMsgs.add("This TicketOrder is been canceled by server because of payment time-limit.");
				}
				// Send the user back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/ticketorder/selectPaymentAndPay.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				//transfer hibernate tovo into tovo2 for JNDI to use
				TicketOrderVO2 tovo2 = new TicketOrderVO2();
				tovo2.setTicket_order_no(ticket_order_no);
				tovo2.setMember_no(toVO.getMember_no());
				tovo2.setTicarea_no(toVO.getSeatingarea_h5VO().getTicarea_no());
				tovo2.setTotal_price(toVO.getTotal_price());
				tovo2.setTotal_amount(toVO.getTotal_amount());
				tovo2.setTicket_order_time(toVO.getTicket_order_time());
				tovo2.setPayment_method("CREDITCARD");
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
				req.setAttribute("updated_tovo", updated_tovo);
				
				//try add tickets after this transaction into req
				TicketService tSvc = new TicketService();
				Map<String, String[]> map = new TreeMap<String, String[]>();
				map.put("ticket_order_no", new String[] {ticket_order_no});
				map.put("member_no", new String[] {"M000001"});
				List<TicketVO> memberListTVO = tSvc.getAll_map(map, "ticket_create_time");
//				req.setAttribute("memberListTVO", memberListTVO); //maybe useless while listShow work on
				
//				String[] strasdfasdef = memberListTVO.toArray(new String[memberListTVO.size()]);
//				System.out.println(strasdfasdef);
				
				map.clear();
//				map.put("eve_no", new String[] {eve_no});
				SeatingArea_H5_Service sh5Svc = new SeatingArea_H5_Service();
//				List<SeatingArea_H5_VO> slist = sh5Svc.getAll(map, "ticarea_no");
//				Event_H5_Service eh5Svc = new Event_H5_Service();
//				Event_H5_VO eh5vo = eh5Svc.getOneEvent_H5(eve_no);
//				
//				req.setAttribute("slist", slist);
//				req.setAttribute("eh5vo", eh5vo);
				
				Event_H5_Service eh5Svc = new Event_H5_Service();
				List<ShowTicketVO> listShow = new LinkedList<ShowTicketVO>();
				
				for(TicketVO at:memberListTVO) {
					ShowTicketVO stvo = new ShowTicketVO();
					stvo.setTicket_no(at.getTicket_no());
					stvo.setMember_no(at.getMember_no());
					stvo.setTicket_status(at.getTicket_status());
					
//					System.out.println("at="+at.getSeatingarea_h5VO().getTicarea_no());
					SeatingArea_H5_VO sh5VO = sh5Svc.getOneSeatingArea_H5(at.getSeatingarea_h5VO().getTicarea_no());
					stvo.setTicarea_name(sh5VO.getTicarea_name());
					stvo.setTicarea_color(sh5VO.getTicarea_color());
					stvo.setTictype_name(sh5VO.getTickettype_h5VO().getTictype_name());
					stvo.setTictype_price(sh5VO.getTickettype_h5VO().getTictype_price());
					
//					System.out.println("sh5VO.get="+sh5VO.getEve_h5VO().getEve_no());
					Event_H5_VO eh5VO = eh5Svc.getOneEvent_H5(sh5VO.getEve_h5VO().getEve_no());
//					System.out.println("eh5VO.get="+eh5VO.getVenue_h5VO().getAddress());
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
				
//				List<String> listTest = new LinkedList<String>();
				
//				for(TicketVO at:memberListTVO) {
//					for(SeatingArea_H5_VO as:slist) {
//						if(at.getSeatingarea_h5VO().getTicarea_no().equals(as.getTicarea_no())) {
//							listTest.add(as.getTicarea_no());
//						}
//					}
//				}
				
				
				
				
				
				//use List<ticketvo>'s ticarea_no to find event
//				map.clear();
//				for(TicketVO avo:memberListTVO) {
//					
//				}
				
				String url = "/frontend/ticketorder/paymentDoneShowInfos.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("databaseError:from selectPaymentAndPay.jsp, might be rollback" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/frontend/ticketorder/selectPaymentAndPay.jsp");
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
	public List<EventVO> checkListByEvetitNo(String evetit_no,List<EventVO> list){
		for(EventVO aEVO :list) {
			System.out.println(aEVO.toString());
			if(!evetit_no.equals(aEVO.getEvetit_no())) {
				System.out.println(aEVO.toString());		
			}
		}
		return null;
	}
}
