package com.group_open.model;

import java.sql.Timestamp;
import java.util.Arrays;

public class Group_openVO implements java.io.Serializable {

	private String group_no;
	private String member_no;
	private String goods_no;
	private String group_name;
	private Integer group_limit;
	private String group_introduction;
	private String group_mind;
	private Timestamp group_start_date;
	private Timestamp group_close_date;
	private byte[] group_banner_1;
	private byte[] group_banner_2;
	private String group_status;
	private String group_address;
	private Double latitude;
	private Double longitude;
	private Timestamp group_time;
	private Integer group_price;
	

	public Group_openVO() {
	}
	
	public Group_openVO(String group_no, String member_no, String goods_no, String group_name, Integer group_limit,
			String group_introduction, String group_mind, Timestamp group_start_date, Timestamp group_close_date,
			byte[] group_banner_1, byte[] group_banner_2, String group_status, String group_address, Double latitude,
			Double longitude, Timestamp group_time, Integer group_price) {
		super();
		this.group_no = group_no;
		this.member_no = member_no;
		this.goods_no = goods_no;
		this.group_name = group_name;
		this.group_limit = group_limit;
		this.group_introduction = group_introduction;
		this.group_mind = group_mind;
		this.group_start_date = group_start_date;
		this.group_close_date = group_close_date;
		this.group_banner_1 = group_banner_1;
		this.group_banner_2 = group_banner_2;
		this.group_status = group_status;
		this.group_address = group_address;
		this.latitude = latitude;
		this.longitude = longitude;
		this.group_time = group_time;
		this.group_price = group_price;
	}

	public String getGroup_no() {
		return group_no;
	}

	public void setGroup_no(String group_no) {
		this.group_no = group_no;
	}

	public String getMember_no() {
		return member_no;
	}

	public void setMember_no(String member_no) {
		this.member_no = member_no;
	}

	public String getGoods_no() {
		return goods_no;
	}

	public void setGoods_no(String goods_no) {
		this.goods_no = goods_no;
	}

	public String getGroup_name() {
		return group_name;
	}

	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}

	public Integer getGroup_limit() {
		return group_limit;
	}

	public void setGroup_limit(Integer group_limit) {
		this.group_limit = group_limit;
	}

	public String getGroup_introduction() {
		return group_introduction;
	}

	public void setGroup_introduction(String group_introduction) {
		this.group_introduction = group_introduction;
	}

	public String getGroup_mind() {
		return group_mind;
	}

	public void setGroup_mind(String group_mind) {
		this.group_mind = group_mind;
	}

	public Timestamp getGroup_start_date() {
		return group_start_date;
	}

	public void setGroup_start_date(Timestamp group_start_date) {
		this.group_start_date = group_start_date;
	}

	public Timestamp getGroup_close_date() {
		return group_close_date;
	}

	public void setGroup_close_date(Timestamp group_close_date) {
		this.group_close_date = group_close_date;
	}

	public byte[] getGroup_banner_1() {
		return group_banner_1;
	}

	public void setGroup_banner_1(byte[] group_banner_1) {
		this.group_banner_1 = group_banner_1;
	}

	public byte[] getGroup_banner_2() {
		return group_banner_2;
	}

	public void setGroup_banner_2(byte[] group_banner_2) {
		this.group_banner_2 = group_banner_2;
	}

	public String getGroup_status() {
		return group_status;
	}

	public void setGroup_status(String group_status) {
		this.group_status = group_status;
	}

	public String getGroup_address() {
		return group_address;
	}

	public void setGroup_address(String group_address) {
		this.group_address = group_address;
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

	public Timestamp getGroup_time() {
		return group_time;
	}

	public void setGroup_time(Timestamp group_time) {
		this.group_time = group_time;
	}

	public Integer getGroup_price() {
		return group_price;
	}

	public void setGroup_price(Integer group_price) {
		this.group_price = group_price;
	};
	

}
