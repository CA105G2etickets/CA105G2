package com.android.ticket.controller;

import java.util.TimerTask;

import com.ticketorder.model.TicketOrderService;
import com.ticketorder.model.TicketOrderVO;

public class TicketTask extends TimerTask{
	private TicketOrderVO ticketOrderVO = null;
	private TicketOrderService ticketOrderService = new TicketOrderService();
	
	public TicketTask(TicketOrderVO ticketOrderVO) {
		this.ticketOrderVO = ticketOrderVO;
	}

	@Override
	public void run() {
		ticketOrderService.cancelTicketOrderByServlet(ticketOrderVO.getTicket_order_no());
		this.cancel();
	}
}
