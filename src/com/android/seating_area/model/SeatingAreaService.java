package com.android.seating_area.model;

import java.util.List;

public class SeatingAreaService {

	private SeatingAreaDAO_interface seatingAreaDAO;

	public SeatingAreaService() {
		seatingAreaDAO = new SeatingAreaDAO();
	}
	
	public List<SeatingAreaVO> findByPrimaryKey(String eventNo) {
		return seatingAreaDAO.findByPrimaryKey(eventNo);
	}
	
	public String getEvent(String eventNo) {
		return seatingAreaDAO.getEvent(eventNo);
	}
}
