package com.android.event.model;

import java.util.List;

public interface EventDAO_interface {
	public List<EventVO> getEvents(String evetit_no);
	public byte[] getEventImage(String eve_no);
}
