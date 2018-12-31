package com.android.event_title.model;

import java.util.List;

public interface Event_titleDAO_interface {
	public List<Event_titleVO> getAll(String str);
	public byte[] getImage(String evetit_no);
	public List<String> getclass();
	public List<Event_titleVO> getFavr(String memberNo);
}
