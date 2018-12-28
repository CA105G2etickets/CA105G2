package android.com.EVENT_TITLE.model;

import java.util.List;

public class Event_titleService {

	Event_titleDAO_interface dao = new Event_titleDAO();
	
	public List<Event_titleVO> getAll(){
		return dao.getAll();
	}
	
	public byte[] getImage(String evetit_no) {
		return dao.getImage(evetit_no);
	}
}
