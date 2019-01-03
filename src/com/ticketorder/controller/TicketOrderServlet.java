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

import com.event.model.EventService;
import com.event.model.EventVO;
import com.seating_area.model.SeatingAreaService;
import com.seating_area.model.SeatingAreaVO;
import com.ticket.model.TicketVO;
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
							.getRequestDispatcher("/ticketorder/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				if ((!str.contains("_")) || str.length()>18) {
					errorMsgs.add("訂票訂單編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/ticketorder/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				if (this.containsHanScript(str)) {
					errorMsgs.add("訂票訂單編號不可包含中文");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/ticketorder/select_page.jsp");
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
							.getRequestDispatcher("/ticketorder/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("toVO", toVO); 
				String url = "/ticketorder/listOneTicketOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/ticketorder/select_page.jsp");
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
				String url = "/ticketorder/update_ticketorder_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/ticketorder/listAllTicketOrder.jsp");
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
				} else if(!ticarea_no.trim().equals("E000101A01")) { 
					errorMsgs.add("座位區編號: 目前只能是E000101A01");
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
							.getRequestDispatcher("/ticketorder/update_ticketorder_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				TicketOrderService toSvc = new TicketOrderService();
				toVO = toSvc.updateTicketOrder(ticket_order_no, member_no, ticarea_no, total_price, total_amount, ticket_order_time, payment_method, ticket_order_status);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("toVO", toVO); 
				String url = "/ticketorder/listOneTicketOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/ticketorder/update_ticketorder_input.jsp");
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
				} else if(!ticarea_no.trim().equals("E000101A01")) { 
					errorMsgs.add("座位區編號: 目前只能是E000101A01");
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
					req.setAttribute("toVO", toVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/ticketorder/addTicketOrder.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				TicketOrderService toSvc = new TicketOrderService();
				toVO = toSvc.addTicketOrder(member_no, ticarea_no, total_price, total_amount, ticket_order_time, payment_method, ticket_order_status);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/ticketorder/listAllTicketOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/ticketorder/addTicketOrder.jsp");
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
				String url = "/ticketorder/listAllTicketOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/ticketorder/listAllTicketOrder.jsp");
				failureView.forward(req, res);
			}
		}
        if("get_EventTitleVO_InfoToBuyTickets".equals(action)) {
        	List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String evetit_no = req.getParameter("evetit_no");
				System.out.println("sys_evetit_no="+evetit_no);
				EventService eSvc = new EventService();
				List<EventVO> list = eSvc.findByEveTit_no(evetit_no);
//				System.out.println(list);
				if (list == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/ticketorder/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				req.setAttribute("elist", list);
				String url = "/ticketorder/buyTicketsSim.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
				
			}catch(Exception e) {
				errorMsgs.add("從資料庫失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/ticketorder/select_page.jsp");
				failureView.forward(req, res);
			}
        }
        if("chooseOne_EventTitle".equals(action)) {
        	List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String eve_no = req.getParameter("eve_no");
				System.out.println("controller+sysout_eve_no="+eve_no);
				SeatingAreaService sSvc = new SeatingAreaService();
				List<SeatingAreaVO> list = sSvc.getAllSeatingAreaByEveNo(eve_no);
				if (list == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/ticketorder/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				req.setAttribute("slist", list);
				String url = "/ticketorder/buyTicketsSimTwo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
				
			}catch(Exception e) {
				errorMsgs.add("從資料庫失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/ticketorder/select_page.jsp");
				failureView.forward(req, res);
			}
        }
        if("buyTickets".equals(action)) {
        	List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String ticarea_no = req.getParameter("ticarea_no");
				String ticketsNum = req.getParameter("ticketsNum");
				System.out.println("controller+sysout_ticarea_no="+ticarea_no);
				System.out.println("controller+sysout_ticketsNum="+ticketsNum);
				
				String member_no = "M000001"; 
				
				Integer total_price = 9990;
				
				Integer total_amount = Integer.parseInt(ticketsNum);
				try {
					total_amount = new Integer(Integer.parseInt(ticketsNum));
					if(total_amount > 100100 || total_amount <0) {
						errorMsgs.add("總張數請勿亂填數字.");
					}
				} catch (NumberFormatException e) {
					total_amount = 0;
					errorMsgs.add("總張數請填數字.");
				}
				
				java.sql.Timestamp ticket_order_time = new java.sql.Timestamp(System.currentTimeMillis());
				
//				String payment_method = req.getParameter("payment_method").trim();
				String payment_method = "EWALLET";
				if (payment_method == null || payment_method.trim().length() == 0) {
					errorMsgs.add("付款方式請勿空白");
				} else if(payment_method.length()>12) {
					errorMsgs.add("付款方式請勿超過12碼");
				} else if(this.containsHanScript(payment_method)) {
					errorMsgs.add("付款方式請勿使用中文");
				}
				
//				String ticket_order_status = req.getParameter("ticket_order_status").trim();
				String ticket_order_status = "COMPLETE2";
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
								
				//add tickets to list
				List<TicketVO> tlist = new ArrayList<TicketVO>();
				for(int i = 1; i<=total_amount;i++) {
					String ticket_status = "ACTIVE1";
					if (ticket_status == null || ticket_status.trim().length() == 0) {
						errorMsgs.add("票券狀態請勿空白");
					} else if(ticket_status.length()>9) {
						errorMsgs.add("票券狀態請勿超過9碼");
					} else if(this.containsHanScript(ticket_status)) {
						errorMsgs.add("票券狀態請勿使用中文");
					}
					
					java.sql.Timestamp ticket_create_time = new java.sql.Timestamp(System.currentTimeMillis());;
					
					String ticket_resale_status = "NONE1";
					if (ticket_resale_status == null || ticket_resale_status.trim().length() == 0) {
						errorMsgs.add("票券轉讓狀態請勿空白");
					} else if(ticket_resale_status.length()>12) {
						errorMsgs.add("票券轉讓狀態請勿超過12碼");
					} else if(this.containsHanScript(ticket_resale_status)) {
						errorMsgs.add("票券轉讓狀態請勿使用中文");
					}
					
					Integer ticket_resale_price = 0;
					
					String is_from_resale = "NO";
					if (is_from_resale == null || is_from_resale.trim().length() == 0) {
						errorMsgs.add("是否來自轉讓請勿空白");
					} else if(is_from_resale.length()>3) {
						errorMsgs.add("是否來自轉讓請勿超過3碼");
					} else if(this.containsHanScript(is_from_resale)) {
						errorMsgs.add("是否來自轉讓請勿使用中文");
					}
					
					TicketVO tVO = new TicketVO();
					tVO.setTicarea_no(ticarea_no);
					tVO.setMember_no(member_no);
					tVO.setTicket_status(ticket_status);
					tVO.setTicket_create_time(ticket_create_time);
					tVO.setTicket_resale_status(ticket_resale_status);
					tVO.setTicket_resale_price(ticket_resale_price);
					tVO.setIs_from_resale(is_from_resale);
					tlist.add(tVO);
				}
				
				TicketOrderService toSvc = new TicketOrderService();
				toSvc.insertWithTickets(toVO, tlist);
				
				String url = "/ticket/listAllTicket.jsp"; // forwatd to ticket/selectpage
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
//				if (list == null) {
//					errorMsgs.add("查無資料");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/ticketorder/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				req.setAttribute("slist", list);
//				String url = "/ticketorder/buyTicketsSimTwo.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); 
//				successView.forward(req, res);
				
			}catch(Exception e) {
				errorMsgs.add("從資料庫失敗or上一頁傳遞參數過來的失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/ticketorder/select_page.jsp");
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
