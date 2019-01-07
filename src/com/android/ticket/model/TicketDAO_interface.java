package com.android.ticket.model;

import java.util.List;

public interface TicketDAO_interface {
	public List<TicketVO> getAll(String memberNo,String status,String className);
	public boolean isTicket(String ticketNo,String eventNo);
}
