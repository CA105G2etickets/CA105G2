package com.event_title.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import com.event.model.Event_H5_VO;

public class EventTitle_H5_VO implements java.io.Serializable{
	
	private String evetit_no;
	private String eveclass_no;
	private String ticrefpolicy_no;
	private String evetit_name;
	private Date evetit_startdate;
	private Date evetit_enddate;
	private byte[] evetit_poster;
	private String info;
	private String notices;
	private String eticpurchaserules;
	private String eticrules;
	private String refundrules;
	private Integer evetit_sessions;
	private String evetit_status;
	private Date launchdate;
	private Date offdate;
	private Integer promotionranking;	
	private Set<Event_H5_VO> event_h5s = new HashSet<Event_H5_VO>();
	public String getEvetit_no() {
		return evetit_no;
	}
	public void setEvetit_no(String evetit_no) {
		this.evetit_no = evetit_no;
	}
	public String getEveclass_no() {
		return eveclass_no;
	}
	public void setEveclass_no(String eveclass_no) {
		this.eveclass_no = eveclass_no;
	}
	public String getTicrefpolicy_no() {
		return ticrefpolicy_no;
	}
	public void setTicrefpolicy_no(String ticrefpolicy_no) {
		this.ticrefpolicy_no = ticrefpolicy_no;
	}
	public String getEvetit_name() {
		return evetit_name;
	}
	public void setEvetit_name(String evetit_name) {
		this.evetit_name = evetit_name;
	}
	public Date getEvetit_startdate() {
		return evetit_startdate;
	}
	public void setEvetit_startdate(Date evetit_startdate) {
		this.evetit_startdate = evetit_startdate;
	}
	public Date getEvetit_enddate() {
		return evetit_enddate;
	}
	public void setEvetit_enddate(Date evetit_enddate) {
		this.evetit_enddate = evetit_enddate;
	}
	public byte[] getEvetit_poster() {
		return evetit_poster;
	}
	public void setEvetit_poster(byte[] evetit_poster) {
		this.evetit_poster = evetit_poster;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getNotices() {
		return notices;
	}
	public void setNotices(String notices) {
		this.notices = notices;
	}
	public String getEticpurchaserules() {
		return eticpurchaserules;
	}
	public void setEticpurchaserules(String eticpurchaserules) {
		this.eticpurchaserules = eticpurchaserules;
	}
	public String getEticrules() {
		return eticrules;
	}
	public void setEticrules(String eticrules) {
		this.eticrules = eticrules;
	}
	public String getRefundrules() {
		return refundrules;
	}
	public void setRefundrules(String refundrules) {
		this.refundrules = refundrules;
	}
	public Integer getEvetit_sessions() {
		return evetit_sessions;
	}
	public void setEvetit_sessions(Integer evetit_sessions) {
		this.evetit_sessions = evetit_sessions;
	}
	public String getEvetit_status() {
		return evetit_status;
	}
	public void setEvetit_status(String evetit_status) {
		this.evetit_status = evetit_status;
	}
	public Date getLaunchdate() {
		return launchdate;
	}
	public void setLaunchdate(Date launchdate) {
		this.launchdate = launchdate;
	}
	public Date getOffdate() {
		return offdate;
	}
	public void setOffdate(Date offdate) {
		this.offdate = offdate;
	}
	public Integer getPromotionranking() {
		return promotionranking;
	}
	public void setPromotionranking(Integer promotionranking) {
		this.promotionranking = promotionranking;
	}
	public Set<Event_H5_VO> getEvent_h5s() {
		return event_h5s;
	}
	public void setEvent_h5s(Set<Event_H5_VO> event_h5s) {
		this.event_h5s = event_h5s;
	}
	
}
