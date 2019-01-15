package com.ticket_type.model;

import java.util.HashSet;
import java.util.Set;

import com.seating_area.model.SeatingArea_H5_VO;

public class TicketType_H5_VO implements java.io.Serializable{
	
	private String tictype_no;
	private String eve_no;
	private String tictype_color;
	private String tictype_name;
	private Integer tictype_price;
	
	private Set<SeatingArea_H5_VO> seatingarea_h5s = new HashSet<SeatingArea_H5_VO>();

	public String getTictype_no() {
		return tictype_no;
	}

	public void setTictype_no(String tictype_no) {
		this.tictype_no = tictype_no;
	}

	public String getEve_no() {
		return eve_no;
	}

	public void setEve_no(String eve_no) {
		this.eve_no = eve_no;
	}

	public String getTictype_color() {
		return tictype_color;
	}

	public void setTictype_color(String tictype_color) {
		this.tictype_color = tictype_color;
	}

	public String getTictype_name() {
		return tictype_name;
	}

	public void setTictype_name(String tictype_name) {
		this.tictype_name = tictype_name;
	}

	public Integer getTictype_price() {
		return tictype_price;
	}

	public void setTictype_price(Integer tictype_price) {
		this.tictype_price = tictype_price;
	}

	public Set<SeatingArea_H5_VO> getSeatingarea_h5s() {
		return seatingarea_h5s;
	}

	public void setSeatingarea_h5s(Set<SeatingArea_H5_VO> seatingarea_h5s) {
		this.seatingarea_h5s = seatingarea_h5s;
	}
	
	
}
