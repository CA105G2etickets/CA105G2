package com.resaleorder.model;
import java.io.Serializable;
import java.sql.Timestamp;

import com.ticket.model.TicketVO;

public class DataUserWantsVO implements Serializable{
	
	//for combine use
	private String ticket_no;
	private String ticarea_no;
	private String eve_no;
	
	private String owner_member_no;
	private Integer price_ticket_resale_price;
	
	private String seatinglocname_ticarea_name;
	private Integer originalprice_tictype_price;
	private String tictypename_tictype_name;
	
	private String eventinfo_evetit_name;
	private Timestamp eventstarttime_eve_startdate;
	private Timestamp eventsellingstoptime_eve_offsaledate;
	private String location_address;
	private String locName_venue_name;
	
	
	public String getLocName_venue_name() {
		return locName_venue_name;
	}
	public void setLocName_venue_name(String locName_venue_name) {
		this.locName_venue_name = locName_venue_name;
	}
	public String getOwner_member_no() {
		return owner_member_no;
	}
	public void setOwner_member_no(String owner_member_no) {
		this.owner_member_no = owner_member_no;
	}
	public String getSeatinglocname_ticarea_name() {
		return seatinglocname_ticarea_name;
	}
	public void setSeatinglocname_ticarea_name(String seatinglocname_ticarea_name) {
		this.seatinglocname_ticarea_name = seatinglocname_ticarea_name;
	}
	public String getTictypename_tictype_name() {
		return tictypename_tictype_name;
	}
	public void setTictypename_tictype_name(String tictypename_tictype_name) {
		this.tictypename_tictype_name = tictypename_tictype_name;
	}
	public Integer getPrice_ticket_resale_price() {
		return price_ticket_resale_price;
	}
	public void setPrice_ticket_resale_price(Integer price_ticket_resale_price) {
		this.price_ticket_resale_price = price_ticket_resale_price;
	}
	public Integer getOriginalprice_tictype_price() {
		return originalprice_tictype_price;
	}
	public void setOriginalprice_tictype_price(Integer originalprice_tictype_price) {
		this.originalprice_tictype_price = originalprice_tictype_price;
	}
	public String getEventinfo_evetit_name() {
		return eventinfo_evetit_name;
	}
	public void setEventinfo_evetit_name(String eventinfo_evetit_name) {
		this.eventinfo_evetit_name = eventinfo_evetit_name;
	}
	public String getLocation_address() {
		return location_address;
	}
	public void setLocation_address(String location_address) {
		this.location_address = location_address;
	}
	public Timestamp getEventstarttime_eve_startdate() {
		return eventstarttime_eve_startdate;
	}
	public void setEventstarttime_eve_startdate(Timestamp eventstarttime_eve_startdate) {
		this.eventstarttime_eve_startdate = eventstarttime_eve_startdate;
	}
	public Timestamp getEventsellingstoptime_eve_offsaledate() {
		return eventsellingstoptime_eve_offsaledate;
	}
	public void setEventsellingstoptime_eve_offsaledate(Timestamp eventsellingstoptime_eve_offsaledate) {
		this.eventsellingstoptime_eve_offsaledate = eventsellingstoptime_eve_offsaledate;
	}
	public String getTicket_no() {
		return ticket_no;
	}
	public void setTicket_no(String ticket_no) {
		this.ticket_no = ticket_no;
	}
	public String getTicarea_no() {
		return ticarea_no;
	}
	public void setTicarea_no(String ticarea_no) {
		this.ticarea_no = ticarea_no;
	}
	public String getEve_no() {
		return eve_no;
	}
	public void setEve_no(String eve_no) {
		this.eve_no = eve_no;
	}
	
	//先不做 賣家賣出此票的日期時間
	
	
	
	
}
