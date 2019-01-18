package com.ticketorder.model;
import java.util.*;

import com.seating_area.model.SeatingAreaVO;
import com.ticket.model.Old_TicketVO;

public interface Old_TicketOrderDAO_interface {
	public void insert(Old_TicketOrderVO ticketorderVO);
    public void update(Old_TicketOrderVO ticketorderVO);
    public void delete(String ticket_order_no);
    public Old_TicketOrderVO findByPrimaryKey(String ticket_order_no);
    public List<Old_TicketOrderVO> getAll();
    
    public Set<Old_TicketVO> getTicketsByTicketOrder(String ticket_order_no);
    public void insertWithTickets(Old_TicketOrderVO ticketorderVO, List<Old_TicketVO> list);
    
    //functions to implements Listener
    public String insertTicketOrderAndUpdateTicArea(Old_TicketOrderVO ticketorderVO, SeatingAreaVO seatingareaVO);
    public String cancelTicketOrderByServlet(String ticket_order_no);
    public void updateTicketOrderAndInsertTickets(Old_TicketOrderVO ticketorderVO, List<Old_TicketVO> list);
    public String cancelTicketOrderByUser(String ticket_order_no);
}
