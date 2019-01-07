package com.android.event_title.model;

import java.util.List;

public interface Event_titleDAO_interface {
	public List<Event_titleVO> getAll(String str,String className);
	public byte[] getImage(String evetit_no);
	public List<String> getclass();
	public List<Event_titleVO> getFavr(String memberNo);
	public List<Event_titleVO> getAllByClass(String str);
	public List<Event_titleVO> getNow();
}
