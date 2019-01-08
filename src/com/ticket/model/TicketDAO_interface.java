package com.ticket.model;
import java.util.*;

public interface TicketDAO_interface {
	public void insert(TicketVO ticketVO);
    public void update(TicketVO ticketVO);
    public void delete(String ticket_no);
    public TicketVO findByPrimaryKey(String ticket_no);
    public List<TicketVO> getAll();	
}
