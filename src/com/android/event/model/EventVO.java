package com.android.event.model;

import java.sql.Timestamp;

public class EventVO implements java.io.Serializable{
	private static final long serialVersionUID = -5468464951341681154L;
	
	private String eve_no;
	private String eve_sessionname;
	private Integer ticlimit;
	
	
	public EventVO() {
		super();
	}


	public String getEve_no() {
		return eve_no;
	}

	public void setEve_no(String eve_no) {
		this.eve_no = eve_no;
	}

	public Integer getTiclimit() {
		return ticlimit;
	}


	public void setTiclimit(Integer ticlimit) {
		this.ticlimit = ticlimit;
	}


	public String getEve_sessionname() {
		return eve_sessionname;
	}

	public void setEve_sessionname(String eve_sessionname) {
		this.eve_sessionname = eve_sessionname;
	}
}
