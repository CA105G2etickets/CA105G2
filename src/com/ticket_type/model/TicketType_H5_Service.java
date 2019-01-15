package com.ticket_type.model;

import java.util.List;
import java.util.Set;

import com.seating_area.model.SeatingAreaVO;

public class TicketType_H5_Service {

	private TicketType_H5_DAO_interface dao;

	public TicketType_H5_Service() {
		dao = new TicketType_H5_HibernateDAO();
	}
	
	public TicketType_H5_VO add(String eve_no, String tictype_color,
			String tictype_name,Integer tictype_price) {
		
		TicketType_H5_VO ttyVO = new TicketType_H5_VO();
		ttyVO.setEve_no(eve_no);;
		ttyVO.setTictype_color(tictype_color);;
		ttyVO.setTictype_name(tictype_name);
		ttyVO.setTictype_price(tictype_price);
		return null;
	}
	
	public TicketType_H5_VO update(String tictype_no, String eve_no, String tictype_color,
			String tictype_name,Integer tictype_price) {
		TicketType_H5_VO ttyVO = new TicketType_H5_VO();
		ttyVO.setTictype_no(tictype_no);
		ttyVO.setEve_no(eve_no);;
		ttyVO.setTictype_color(tictype_color);;
		ttyVO.setTictype_name(tictype_name);
		ttyVO.setTictype_price(tictype_price);
		
		dao.update(ttyVO);
		
		return ttyVO;
	}
	
	public TicketType_H5_VO findByPrimaryKey(String tictype_no) {
		return dao.findByPrimaryKey(tictype_no);
	}
	
}
