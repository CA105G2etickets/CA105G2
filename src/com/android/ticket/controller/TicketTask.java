package com.android.ticket.controller;

import java.util.TimerTask;

import com.ticketorder.model.TicketOrderService;
import com.ticketorder.model.TicketOrderVO;

public class TicketTask extends TimerTask{
	private TicketOrderVO ticketOrderVO = null;
	private TicketOrderService ticketOrderService = new TicketOrderService();
	
	public TicketTask(TicketOrderVO ticketOrderVO) {
		this.ticketOrderVO = ticketOrderVO;
		System.out.println(ticketOrderVO.getTicket_order_no()+"15分鐘開始");
	}

	@Override
	public void run() {
		ticketOrderService.cancelTicketOrderByServlet(ticketOrderVO.getTicket_order_no());
		System.out.println(ticketOrderVO.getTicket_order_no()+"15分鐘結束");
	}
}
