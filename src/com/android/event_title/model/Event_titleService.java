package com.android.event_title.model;

import java.util.List;

public class Event_titleService {

	Event_titleDAO_interface dao = new Event_titleDAO();
	
	public List<Event_titleVO> getAll(String str){
		return dao.getAll(str);
	}
	
	public byte[] getImage(String evetit_no) {
		return dao.getImage(evetit_no);
	}
	
	public List<String> getclass() {
		return dao.getclass();
	}
	
	public List<Event_titleVO> getFavr(String memberNo) {
		return dao.getFavr(memberNo);
	}
	
	public List<Event_titleVO> getAllByClass(String str) {
		return dao.getAllByClass(str);
	}
}
