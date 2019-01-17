package com.seating_area.model;
import java.util.*;

import com.event.model.Event_H5_VO;
import com.ticket.model.TicketVO;
import com.ticketorder.model.TicketOrderVO;

public interface SeatingArea_H5_DAO_interface {
	
	public void insert(SeatingArea_H5_VO seatingarea_h5VO);
    public void update(SeatingArea_H5_VO seatingarea_h5VO);
    public void delete(String ticarea_no);
    public SeatingArea_H5_VO findByPrimaryKey(String ticarea_no);
    
    public List<SeatingArea_H5_VO> getAll();
    public List<SeatingArea_H5_VO> get_SeatingArea_H5_VOs_ByEveNo(String eve_no);
    public List<SeatingArea_H5_VO> getAll(Map<String, String[]> map, String strOrderByTargetColumnName);
    
//    public Set<TicketVO> getTicketsByTicAreaNo(String ticarea_no);
    public Set<TicketOrderVO> getTicketOrdersByTicAreaNo(String ticarea_no);
    
    public List<SeatingArea_H5_VO> getTargetVOsbyColumnNameOnlyStringAndSQLINcondition(String columnName,Set<String> javaUtilSet_SQLin_Conditions);
}
