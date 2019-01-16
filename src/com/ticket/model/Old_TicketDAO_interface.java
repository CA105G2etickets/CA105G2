package com.ticket.model;
import java.util.*;

public interface Old_TicketDAO_interface {
	public void insert(Old_TicketVO ticketVO);
    public void update(Old_TicketVO ticketVO);
    public void delete(String ticket_no);
    public Old_TicketVO findByPrimaryKey(String ticket_no);
    
//    public List<TicketVO> findByTicket_order_status(String ticket_order_status);
    
    public List<Old_TicketVO> getAll();	
    public void insertTickets (Old_TicketVO ticketVO , java.sql.Connection con);
}
