package com.ticket_type.model;
import java.util.*;

import com.seating_area.model.SeatingArea_H5_VO;

public interface TicketType_H5_DAO_interface {
	
	public void insert(TicketType_H5_VO tickettype_h5VO);
    public void update(TicketType_H5_VO tickettype_h5VO);
    public void delete(String tictype_no);
    public TicketType_H5_VO findByPrimaryKey(String tictype_no);
    
    public List<TicketType_H5_VO> getAll();
    
    public Set<SeatingArea_H5_VO> getSeatingAreah5sByTicketTypeNo(String tictype_no);
}
