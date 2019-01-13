package com.android.event.model;

import java.util.List;

public class EventService {

	private EventDAO_interface dao = null;
	
	public EventService() {
		dao = new EventDAO();
	}
	
	public List<EventVO> getEvents(String evetit_no) {
		return dao.getEvents(evetit_no);
	}
	
	public byte[] getEventImage(String eve_no) {
		return dao.getEventImage(eve_no);
	}
}
