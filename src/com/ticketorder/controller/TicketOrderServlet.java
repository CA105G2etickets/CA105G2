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
import com.seating_area.model.SeatingAreaService;
import com.seating_area.model.SeatingAreaVO;
import com.ticket.model.TicketVO;
import com.ticket_type.model.TicketTypeService;
import com.ticket_type.model.TicketTypeVO;
import com.ticketorder.model.TicketOrderService;
import com.ticketorder.model.TicketOrderVO;

/**
 * Servlet implementation class TicketOrderServlet
 */
//@WebServlet("/TicketOrderServlet")
public class TicketOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
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
				toVO.setTicarea_no(ticarea_no);
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
				toVO.setTicarea_no(ticarea_no);
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
							.getRequestDispatcher("/frontend/ticketorder/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				//驗證錯誤結束，準備開始call service
				SeatingAreaService sSvc = new SeatingAreaService();
				List<SeatingAreaVO> list = sSvc.getAllSeatingAreaByEveNo(eve_no);
				if (list == null) {
					errorMsgs.add("查無資料");
				}
				// Send the user back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/ticketorder/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				req.setAttribute("slist", list);
				req.setAttribute("eve_no", eve_no); // make sure when ticketNoEnough there can select again for .jsp
				
//				String url = "/frontend/ticketorder/buyTicketsSimTwo.jsp";
				
				String url = "/frontend/ticketorder/eve_no_SelectedReady2Next.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
				
			}catch(Exception e) {
				errorMsgs.add("從資料庫get List<SeatingAreaVO>失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/frontend/ticketorder/select_page.jsp");
				failureView.forward(req, res);
			}
        }
        if("buyTickets".equals(action)) {
        	List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			//create a list for HttpSessionBindingListener
			List<String> listForListener = new ArrayList<String>();
			
			try {
				String ticarea_no = req.getParameter("ticarea_no");
				String ticketsNum = req.getParameter("ticketsNum");
				String tictype_no = req.getParameter("tictype_no");
				
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
					errorMsgs.add("請輸入ticketsNum編號");
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
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/ticketorder/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
//				listForListener.add(ticarea_no);
//				listForListener.add(ticketsNum);
				
				//start to create ticket_order
				String member_no = "M000001"; //member still NOT implemented yet
//				Integer total_price = 9990; //disable test statement, use amount and service.
				
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
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/ticketorder/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer total_price = null;
				try {
					TicketTypeService ttySvc = new TicketTypeService();
					TicketTypeVO ttyVO = ttySvc.getOneTicketType(tictype_no);
					Integer oneTicketPrice = new Integer(ttyVO.getTictype_price());
					total_price = oneTicketPrice*total_amount;
					
					if(total_amount > 10100100 || total_amount <0) {
						errorMsgs.add("總張數請勿亂填數字.");
					}
				} catch (NumberFormatException e) {
					total_amount = 0;
					errorMsgs.add("總張數請填數字.");
				}
				
				java.sql.Timestamp ticket_order_time = new java.sql.Timestamp(System.currentTimeMillis());
				String payment_method = "No";
				String ticket_order_status = "WAITTOPAY";
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/ticketorder/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				TicketOrderVO toVO = new TicketOrderVO();
				toVO.setMember_no(member_no);
				toVO.setTicarea_no(ticarea_no);
				toVO.setTotal_price(total_price);
				toVO.setTotal_amount(total_amount);
				toVO.setTicket_order_time(ticket_order_time);
				toVO.setPayment_method(payment_method);
				toVO.setTicket_order_status(ticket_order_status);
				
				//find target tic_area then update its bookednumber
				SeatingAreaService seatingAreaSvc = new SeatingAreaService();
				SeatingAreaVO sVO = seatingAreaSvc.getOneSeatingArea(ticarea_no);
				Integer original_ticbookednumber = sVO.getTicbookednumber();
				Integer update_ticbookednumber = original_ticbookednumber+total_amount;
				sVO.setTicbookednumber(update_ticbookednumber);
				
				//prepared toVO and sVO done, now call transaction function
				TicketOrderService toSvc = new TicketOrderService();
				
//				session.setAttribute("toVO", toVO);
//				session.setAttribute("sVO", sVO);
				
				String CreatedTicketOrderNo = toSvc.insertTicketOrderAndUpdateTicArea(toVO, sVO);
				System.out.println("CreatedTicketOrderNo="+CreatedTicketOrderNo);
				
				//set sessionAttribute for Listener Bound to use.
				session.setAttribute("CreatedTicketOrderNo", CreatedTicketOrderNo);
				
				//init Listener
				TicketOrderWithTicArea_HSB_Listener ticketOrderListener = new TicketOrderWithTicArea_HSB_Listener();
				session.setAttribute(CreatedTicketOrderNo, ticketOrderListener);
				
				req.setAttribute("CreatedTicketOrderNo", CreatedTicketOrderNo);
				String url = "/frontend/ticketorder/selectPayment.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			}catch(Exception e) {
				errorMsgs.add("從資料庫失敗:"+e.getMessage());
				
				String eve_no = req.getParameter("eve_no");
				SeatingAreaService sSvc = new SeatingAreaService();
				List<SeatingAreaVO> list = sSvc.getAllSeatingAreaByEveNo(eve_no);
				
				req.setAttribute("slist", list);
				req.setAttribute("eve_no", eve_no);
				
				System.out.println("從資料庫失敗 eve_no="+eve_no);
				RequestDispatcher failureView = req
						.getRequestDispatcher("/frontend/ticketorder/eve_no_SelectedReady2Next.jsp");
				failureView.forward(req, res);
			}
        }
        if("userWantToPay".equals(action)) {
        	List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			System.out.println("userWantToPay"+session.getAttributeNames());
			
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
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/ticketorder/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				TicketOrderService toSvc = new TicketOrderService();
				TicketOrderVO toVO = toSvc.getOneTicketOrder(ticket_order_no);
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
								
				req.setAttribute("toVO", toVO);
				String url = "/frontend/ticketorder/userStartPaying.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("databaseError:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/frontend/ticketorder/selectPayment.jsp");
				failureView.forward(req, res);
			}
        }
        if("userPaying".equals(action)) {
        	List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			System.out.println("userPaying"+session.getAttributeNames());
			
			try {
				String ticket_order_no = req.getParameter("ticket_order_no");
				String creditCardNumber = req.getParameter("creditCardNumber");
				String creditCardVerificationNumber = req.getParameter("creditCardVerificationNumber");
				
				//only check out the number of credit contains chinese or not, the verification not done yet
				if(this.containsHanScript(creditCardVerificationNumber) && this.containsHanScript(creditCardNumber)) {
					errorMsgs.add("credit card numbers不可包含中文");
					// do other credit card money functions here
					// might use a boolean to contain the payment is successful or not.
					// if verification fail, return and dont let toVO.status turn to COMPLETE2
					//return;
				}
				
				//creditCardVerificationNumber
				if (creditCardVerificationNumber == null || (creditCardVerificationNumber.trim()).length() == 0) {
					errorMsgs.add("請輸入creditCardVerificationNumber編號");
				}
				if (creditCardVerificationNumber.length()>18) {
					errorMsgs.add("creditCardVerificationNumber格式不正確");
				}
				
				//creditCardNumber
				if (creditCardNumber == null || (creditCardNumber.trim()).length() == 0) {
					errorMsgs.add("請輸入creditCardNumber編號");
				}
				if (creditCardVerificationNumber.length()>18) {
					errorMsgs.add("creditCardNumber格式不正確");
				}
				
				
				TicketOrderService toSvc = new TicketOrderService();
				TicketOrderVO toVO = toSvc.getOneTicketOrder(ticket_order_no);
				if (toVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/ticketorder/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				//make sure toVO exist then start to update toVO.status
				toVO.setPayment_method("CREDITCARD");
				toVO.setTicket_order_status("COMPLETE2");
				
				//create and add tickets to list
				List<TicketVO> tlist = new ArrayList<TicketVO>();
				for(int i = 1; i<=toVO.getTotal_amount();i++) {
					
					String ticket_status = "ACTIVE1";
					java.sql.Timestamp ticket_create_time = new java.sql.Timestamp(System.currentTimeMillis());;
					String ticket_resale_status = "NONE1";
					Integer ticket_resale_price = 0;
					String is_from_resale = "NO";
					
					TicketVO tVO = new TicketVO();
					tVO.setTicarea_no(toVO.getTicarea_no());
					tVO.setMember_no(toVO.getMember_no());
					tVO.setTicket_status(ticket_status);
					tVO.setTicket_create_time(ticket_create_time);
					tVO.setTicket_resale_status(ticket_resale_status);
					tVO.setTicket_resale_price(ticket_resale_price);
					tVO.setIs_from_resale(is_from_resale);
					tlist.add(tVO);
				}
				//call transaction function
				toSvc.updateTicketOrderAndInsertTickets(toVO, tlist);
				
				String url = "/frontend/ticketorder/listAllTicketOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("資料庫出錯:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/frontend/ticketorder/userStartPaying.jsp");
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
