package com.android.ticket.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONObject;

import com.android.ticket.model.TicketService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.member.model.MemberDAO;
import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.seating_area.model.SeatingAreaService;
import com.seating_area.model.SeatingAreaVO;
import com.ticket.model.Old_TicketVO;
import com.ticket.model.TicketVO;
import com.ticketorder.model.Old_TicketOrderVO;
import com.ticketorder.model.TicketOrderService;
import com.ticketorder.model.TicketOrderVO;

@ServerEndpoint("/TicketWebSocket/{userName}")
public class TicketWebSocket {
	private static java.util.Map<String, Session> sessionsMap = new ConcurrentHashMap<>();
	Gson gson = new Gson();
	private static java.util.Map<String, Timer> timersessions = new ConcurrentHashMap<>();
	private static java.util.Map<String, Old_TicketOrderVO> ordersessions = new ConcurrentHashMap<>();
	
	@OnOpen
	public void onOpen(@PathParam("userName") String userName, Session userSession) throws IOException {
		// 設定成500KB為了配合Android bundle傳送大小
				int maxBufferSize = 500 * 1024;
				userSession.setMaxTextMessageBufferSize(maxBufferSize);
				userSession.setMaxBinaryMessageBufferSize(maxBufferSize);
				/* save the new user in the map */
				sessionsMap.put(userName, userSession);
				System.out.println(userName);
	}
	
	@OnMessage
	public void onMessage(Session userSession,String message) {
		Gson gson = new Gson();
		JsonObject jsin = gson.fromJson(message,JsonObject.class);
		String action = jsin.get("action").getAsString();
		System.out.println(jsin.toString());
		if("insert".equals(action)) {
			String member_no = jsin.get("memberNo").getAsString();
			String ticarea_no = jsin.get("ticarea_no").getAsString();
			Integer total_price = Integer.valueOf(jsin.get("total_price").getAsString());
			Integer total_amount = Integer.valueOf(jsin.get("total_amount").getAsString());	
			java.sql.Timestamp ticket_order_time = new java.sql.Timestamp(System.currentTimeMillis());
			Old_TicketOrderVO ticketOrderVO = new Old_TicketOrderVO();
			ticketOrderVO.setMember_no(member_no);
			ticketOrderVO.setTicarea_no(ticarea_no);
			ticketOrderVO.setTotal_price(total_price);
			ticketOrderVO.setTotal_amount(total_amount);
			ticketOrderVO.setTicket_order_time(ticket_order_time);
			ticketOrderVO.setTicket_order_status("WAITFORPAY1");
			
			SeatingAreaService sAreaService = new SeatingAreaService();
			SeatingAreaVO sVo = sAreaService.getOneSeatingArea(ticarea_no);

			Integer booked = sVo.getTicbookednumber();
			sVo.setTicbookednumber(booked+total_amount);
			 
			TicketOrderService ticketOrderService = new TicketOrderService();
			String orderNo = ticketOrderService.insertTicketOrderAndUpdateTicArea(ticketOrderVO, sVo);
			ticketOrderVO.setTicket_order_no(orderNo);
			Timer timer = new Timer();
			timer.schedule(new TicketTask(ticketOrderVO), ticket_order_time.getTime()+30*1000);
			timersessions.put(member_no, timer);
			ordersessions.put(member_no, ticketOrderVO);
			Collection<Session> list = sessionsMap.values();
			for(Session session : list) {
				session.getAsyncRemote().sendText("update");
			}
		}
		if("pay".equals(action)) {
			List<Old_TicketVO> tlist = new ArrayList<Old_TicketVO>();
			String payType = jsin.get("payType").getAsString();
			String memberNo = jsin.get("memberNo").getAsString();
			String totalPrice = jsin.get("totalPrice").getAsString();
			Old_TicketOrderVO toVO = ordersessions.get(memberNo);
			if("wallet".equals(payType)) {
				MemberDAO memberDAO = new MemberDAO();
				MemberVO memberVO = memberDAO.findByPrimaryKey(memberNo);
				memberVO.setEwalletBalance(memberVO.getEwalletBalance()-Integer.valueOf(totalPrice));
				memberDAO.withdrawal(memberVO);
			}
			for(int i = 1; i<=toVO.getTotal_amount();i++) {
				
				String ticket_status = "ACTIVE1";
				java.sql.Timestamp ticket_create_time = new java.sql.Timestamp(System.currentTimeMillis());;
				String ticket_resale_status = "NONE1";
				Integer ticket_resale_price = 0;
				String is_from_resale = "NO";
				
				Old_TicketVO tVO = new Old_TicketVO();
				tVO.setTicarea_no(toVO.getTicarea_no());
				tVO.setMember_no(toVO.getMember_no());
				tVO.setTicket_status(ticket_status);
				tVO.setTicket_create_time(ticket_create_time);
				tVO.setTicket_resale_status(ticket_resale_status);
				tVO.setTicket_resale_price(ticket_resale_price);
				tVO.setIs_from_resale(is_from_resale);
				tlist.add(tVO);
			}
			
			TicketOrderService ticketOrderService = new TicketOrderService();
			toVO.setTicket_order_status("COMPLETE2");
			ticketOrderService.updateTicketOrderAndInsertTickets(toVO, tlist);
			timersessions.get(memberNo).cancel();
			timersessions.remove(memberNo);
			ordersessions.remove(memberNo);
		}
		if("cancel".equals(action)) {
			String memberNo = jsin.get("memberNo").getAsString();
			timersessions.get(memberNo).cancel();
			Old_TicketOrderVO ticketOrderVO = ordersessions.get(memberNo);
			TicketOrderService ticketOrderService = new TicketOrderService();
			ticketOrderService.cancelTicketOrderByServlet(ticketOrderVO.getTicket_order_no());
		}
		 
	}
	
	@OnError
	public void onError(Session userSession, Throwable e) {
		e.printStackTrace();
	}
	
	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		Set<String> userNames = sessionsMap.keySet();
		for (String userName : userNames) {
			if (sessionsMap.get(userName).equals(userSession)) {
				sessionsMap.remove(userName);
				break;
			}
		}

		String text = String.format("session ID = %s, disconnected; close code = %d%nusers: %s", userSession.getId(),
				reason.getCloseCode().getCode(), userNames);
		System.out.println(text);
	}
}
