package com.ticketorder.model;
import java.util.*;

public interface TicketOrderDAO_interface {
	public void insert(TicketOrderVO ticketorderVO);
    public void update(TicketOrderVO ticketorderVO);
    public void delete(String ticket_order_no);
    public TicketOrderVO findByPrimaryKey(String ticket_order_no);
    public List<TicketOrderVO> getAll();
}
