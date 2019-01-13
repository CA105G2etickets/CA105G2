package com.android.seating_area.model;
import java.util.*;

public interface SeatingAreaDAO_interface {
	
    public List<SeatingAreaVO> findByPrimaryKey(String eventNo);
    public String getEvent(String eventNo);
    public int getSeat(String ticarea_no);
    
}
