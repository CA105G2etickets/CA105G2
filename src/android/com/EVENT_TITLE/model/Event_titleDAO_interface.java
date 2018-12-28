package android.com.EVENT_TITLE.model;

import java.util.List;

public interface Event_titleDAO_interface {
	public List<Event_titleVO> getAll();
	public byte[] getImage(String evetit_no);
}
