package com.ticket.model;
import java.util.*;

import com.resaleorder.model.ResaleOrderVO;

public interface TicketDAO_interface {
	public void insert(TicketVO ticketVO);
    public void update(TicketVO ticketVO);
    public void delete(String ticket_no);
    public TicketVO findByPrimaryKey(String ticket_no);
    
//    public List<TicketVO> findByTicket_order_status(String ticket_order_status);
    
    public List<TicketVO> getAll();
    public Set<ResaleOrderVO> getResaleOrderByTicketsNo(String ticket_no);
    
    public List<TicketVO> getAll_map(Map<String, String[]> map,String strOrderByTargetColumnName);
    
    public List<TicketVO> getTicketsByTicketOrderNo(String ticket_order_no);
//    public void InsertTicketsAndUpdateTargetTO(List<TicketVO> list, String ticket_order_no);
//    public void insertTickets (TicketVO ticketVO , java.sql.Connection con);
}
