package com.ticketorder.model;
import java.util.*;

import com.ticket.model.TicketVO;

public interface TicketOrderDAO_interface {
	public void insert(TicketOrderVO ticketorderVO);
    public void update(TicketOrderVO ticketorderVO);
    public void delete(String ticket_order_no);
    public TicketOrderVO findByPrimaryKey(String ticket_order_no);
    public List<TicketOrderVO> getAll();
    
    public Set<TicketVO> getTicketsByTicketOrder(String ticket_order_no);
    public void insertWithTickets(TicketOrderVO ticketorderVO, List<TicketVO> list);
}
