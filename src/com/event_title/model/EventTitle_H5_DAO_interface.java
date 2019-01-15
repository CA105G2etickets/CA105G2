package com.event_title.model;

import java.util.*;
import com.event.model.Event_H5_VO;

public interface EventTitle_H5_DAO_interface {
	      public void insert(EventTitle_H5_VO eventtitle_h5VO);
          public void update(EventTitle_H5_VO eventtitle_h5VO);
          public void delete(String evetit_no);
          public EventTitle_H5_VO findByPrimaryKey(String evetit_no);
	      public List<EventTitle_H5_VO> getAll();
	      
	      public Set<Event_H5_VO> getEventH5sByEvetitNo(String evetit_no);
}
