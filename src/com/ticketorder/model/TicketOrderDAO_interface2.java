package com.ticketorder.model;
import java.util.*;

import com.seating_area.model.SeatingAreaVO;
import com.ticket.model.TicketVO2;

public interface TicketOrderDAO_interface2 {
	public void updateTicketOrderAndInsertTickets(TicketOrderVO2 ticketorderVO2, List<TicketVO2> list);
//    public void updateTicketOrderAndInsertTickets(TicketOrderVO ticketorderVO, List<TicketVO> list);
}
