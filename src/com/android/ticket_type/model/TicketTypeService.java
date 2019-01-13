package com.android.ticket_type.model;

public class TicketTypeService {

	private TicketTypeDAO_interface dao = null;
	
	public TicketTypeService() {
		dao = new TicketTypeDAO();
	}
	
	public String getPrice(String tictype_no) {
		return dao.getPrice(tictype_no);
	}
}
