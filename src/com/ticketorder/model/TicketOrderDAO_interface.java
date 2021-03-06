package com.ticketorder.model;
import java.util.*;

import com.seating_area.model.SeatingAreaVO;
import com.ticket.model.TicketVO;

public interface TicketOrderDAO_interface {
	public void insert(TicketOrderVO ticketorderVO);
//	public TicketOrderVO insertWithTOVOreturn(TicketOrderVO ticketorderVO);
	
	public String insertThenGetLatestToNoWithCondition(TicketOrderVO ticketorderVO);
	
    public void update(TicketOrderVO ticketorderVO);
    public void delete(String ticket_order_no);
    public TicketOrderVO findByPrimaryKey(String ticket_order_no);
    public List<TicketOrderVO> getAll();
    
    public Set<TicketVO> getTicketsByTicketOrderNo(String ticket_order_no);
    
    public List<TicketOrderVO> getTicketOrdersByMemberNo(String member_no);
    
    public void cancelTargetTicketOrderByServletDueToOutDatedWithConditions(String ticket_order_no);    
//    public void insertWithTickets(TicketOrderVO ticketorderVO, List<TicketVO> list);
    
    //functions to implements Listener
//    public String insertTicketOrderAndUpdateTicArea(TicketOrderVO ticketorderVO, SeatingAreaVO seatingareaVO);
//    public String cancelTicketOrderByServlet(String ticket_order_no);
//    public void updateTicketOrderAndInsertTickets(TicketOrderVO ticketorderVO, List<TicketVO> list);
    public String updateWithCondition(TicketOrderVO ticketorderVO);
}
