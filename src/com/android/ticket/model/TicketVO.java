package com.android.ticket.model;

import java.sql.Timestamp;

public class TicketVO {
	private String evetitName;
	private String eveClassName;
	private String evetitNo;
	private String venueName;
	private String memberNo;
	private String ticketStatus;
	private String eveNo;
	private String ticareaName;
	private String ticketNo;
	private Integer ticlimit;
	private Integer remaining;
	private Timestamp eveStartDate;
		
	public String getTicketNo() {
		return ticketNo;
	}
	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}
	public String getEvetitName() {
		return evetitName;
	}
	public void setEvetitName(String evetitName) {
		this.evetitName = evetitName;
	}
	public String getEveClassName() {
		return eveClassName;
	}
	public void setEveClassName(String eveClassName) {
		this.eveClassName = eveClassName;
	}
	public String getEvetitNo() {
		return evetitNo;
	}
	public void setEvetitNo(String evetitNo) {
		this.evetitNo = evetitNo;
	}
	public String getVenueName() {
		return venueName;
	}
	public void setVenueName(String venueName) {
		this.venueName = venueName;
	}
	public String getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}
	public String getTicketStatus() {
		return ticketStatus;
	}
	public void setTicketStatus(String ticketStatus) {
		this.ticketStatus = ticketStatus;
	}
	public String getEveNo() {
		return eveNo;
	}
	public void setEveNo(String eveNo) {
		this.eveNo = eveNo;
	}
	public String getTicareaName() {
		return ticareaName;
	}
	public void setTicareaName(String ticareaName) {
		this.ticareaName = ticareaName;
	}
	public Integer getTiclimit() {
		return ticlimit;
	}
	public void setTiclimit(Integer ticlimit) {
		this.ticlimit = ticlimit;
	}
	public Integer getRemaining() {
		return remaining;
	}
	public void setRemaining(Integer remaining) {
		this.remaining = remaining;
	}
	public Timestamp getEveStartDate() {
		return eveStartDate;
	}
	public void setEveStartDate(Timestamp eveStartDate) {
		this.eveStartDate = eveStartDate;
	}
}
