package com.venue.model;

import java.util.HashSet;
import java.util.Set;

import com.event.model.*; 

public class Venue_H5_VO implements java.io.Serializable{
	
	private String venue_no;
	private String venue_name;
	private String address;
	private Double latitude;
	private Double longitude;
	private String venue_info;
	private byte[] venue_locationPic;
	
	private Set<Event_H5_VO> event_h5s = new HashSet<Event_H5_VO>();

	public String getVenue_no() {
		return venue_no;
	}
	public void setVenue_no(String venue_no) {
		this.venue_no = venue_no;
	}
	public String getVenue_name() {
		return venue_name;
	}
	public void setVenue_name(String venue_name) {
		this.venue_name = venue_name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public String getVenue_info() {
		return venue_info;
	}
	public void setVenue_info(String venue_info) {
		this.venue_info = venue_info;
	}
	public byte[] getVenue_locationPic() {
		return venue_locationPic;
	}
	public void setVenue_locationPic(byte[] venue_locationPic) {
		this.venue_locationPic = venue_locationPic;
	}
	public Set<Event_H5_VO> getEvent_h5s() {
		return event_h5s;
	}
	public void setEvent_h5s(Set<Event_H5_VO> event_h5s) {
		this.event_h5s = event_h5s;
	}
	
}
