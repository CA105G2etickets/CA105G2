package com.android.ticket.model;

import java.util.List;

public class TicketService {

	private TicketDAO_interface dao = null;
	
	public TicketService() {
		dao = new TicketDAO();
	}
	
	public List<TicketVO> getAll(String memberNo,String status,String className) {
		return dao.getAll(memberNo,status,className);
	}
	
	public boolean isTicket(String ticketNo,String eventNo) {
		return dao.isTicket(ticketNo,eventNo);
	}
}
