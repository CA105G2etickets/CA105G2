package com.event.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.event_title.model.EventTitleVO;
import com.seating_area.model.SeatingAreaVO;
import com.seating_area.model.SeatingArea_H5_VO;
//import com.ticket_type.model.TicketTypeVO_temp;

public interface Event_H5_DAO_interface {
	
	public void insert(Event_H5_VO eventVO);
    public void update(Event_H5_VO eventVO);
    public void delete(String eve_no);
    public Event_H5_VO findByPrimaryKey(String eve_no);
    public List<Event_H5_VO> getAll();
    
	public Set<SeatingArea_H5_VO> getSeatingAreasByEventNo(String eve_no);

	public List<Event_H5_VO> getAll(Map<String, String[]> map, String strOrderByTargetColumnName);
	public List<Event_H5_VO> getTargetVOsbyColumnNameOnlyStringAndSQLINcondition(String columnName,Set<String> javaUtilSet_SQLin_Conditions);
}
