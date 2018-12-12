package com.EVENT_TITLE.model;

import java.util.List;
import java.util.Set;

public interface EventTitleDAO_interface {
	
	 public void insert(EventTitleVO evetitVO);
     public void update(EventTitleVO evetitVO);
     public void delete(String evetit_no);
     public EventTitleVO findByPrimaryKey(String evetit_no);
     public List<EventTitleVO> getAll();

}
