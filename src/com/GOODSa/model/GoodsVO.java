package com.GOODSa.model;

import java.sql.*;

public class GoodsVO implements java.io.Serializable{

	private static final long serialVersionUID = 3095677796845677385L;
	private String goods_no;
	private String evetit_no;
	private String goods_name;
	private Integer goods_price;
	private byte[] goods_picture1;
	private byte[] goods_picture2;
	private byte[] goods_picture3;
	private String goods_introduction;
	private Integer forsales_a;
	private Integer favorite_count;
	private String goods_status;
	private Timestamp launchdate;
	private Timestamp offdate;
	private Integer goods_group_count;
	private Integer goods_want_count;
	private Integer goods_sales_count;
	
	public GoodsVO() {
		super();
	}
	
	public GoodsVO(String goods_no, String evetit_no, String goods_name,
			Integer goods_price, byte[] goods_picture1, byte[] goods_picture2, byte[] goods_picture3,
			String goods_introduction, Integer forsales_a,Integer favorite_count,String goods_status,
			Timestamp launchdate, Timestamp offdate, Integer goods_group_count, Integer goods_want_count, Integer goods_sales_count) {
		super();
		this.goods_no = goods_no;
		this.evetit_no = evetit_no;
		this.goods_name = goods_name;
		this.goods_price = goods_price;
		this.goods_picture1 = goods_picture1;
		this.goods_picture2 = goods_picture2;
		this.goods_picture3 = goods_picture3;
		this.goods_introduction = goods_introduction;
		this.forsales_a = forsales_a;
		this.favorite_count = favorite_count;
		this.goods_status = goods_status;
		this.launchdate = launchdate;
		this.offdate = offdate;
		this.goods_group_count = goods_group_count;
		this.goods_want_count = goods_want_count;
		this.goods_sales_count = goods_sales_count;
	}

	public String getGoods_no() {
		return goods_no;
	}

	public void setGoods_no(String goods_no) {
		this.goods_no = goods_no;
	}

	public String getEvetit_no() {
		return evetit_no;
	}

	public void setEvetit_no(String evetit_no) {
		this.evetit_no = evetit_no;
	}

	public String getGoods_name() {
		return goods_name;
	}

	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}

	public Integer getGoods_price() {
		return goods_price;
	}

	public void setGoods_price(Integer goods_price) {
		this.goods_price = goods_price;
	}

	public byte[] getGoods_picture1() {
		return goods_picture1;
	}

	public void setGoods_picture1(byte[] goods_picture1) {
		this.goods_picture1 = goods_picture1;
	}

	public byte[] getGoods_picture2() {
		return goods_picture2;
	}

	public void setGoods_picture2(byte[] goods_picture2) {
		this.goods_picture2 = goods_picture2;
	}

	public byte[] getGoods_picture3() {
		return goods_picture3;
	}

	public void setGoods_picture3(byte[] goods_picture3) {
		this.goods_picture3 = goods_picture3;
	}

	public String getGoods_introduction() {
		return goods_introduction;
	}

	public void setGoods_introduction(String goods_introduction) {
		this.goods_introduction = goods_introduction;
	}

	public Integer getForsales_a() {
		return forsales_a;
	}

	public void setForsales_a(Integer forsales_a) {
		this.forsales_a = forsales_a;
	}

	public Integer getFavorite_count() {
		return favorite_count;
	}

	public void setFavorite_count(Integer favorite_count) {
		this.favorite_count = favorite_count;
	}

	public String getGoods_status() {
		return goods_status;
	}

	public void setGoods_status(String goods_status) {
		this.goods_status = goods_status;
	}

	public Timestamp getLaunchdate() {
		return launchdate;
	}

	public void setLaunchdate(Timestamp launchdate) {
		this.launchdate = launchdate;
	}

	public Timestamp getOffdate() {
		return offdate;
	}

	public void setOffdate(Timestamp offdate) {
		this.offdate = offdate;
	}

	public Integer getGoods_group_count() {
		return goods_group_count;
	}

	public void setGoods_group_count(Integer goods_group_count) {
		this.goods_group_count = goods_group_count;
	}

	public Integer getGoods_want_count() {
		return goods_want_count;
	}

	public void setGoods_want_count(Integer goods_want_count) {
		this.goods_want_count = goods_want_count;
	}

	public Integer getGoods_sales_count() {
		return goods_sales_count;
	}

	public void setGoods_sales_count(Integer goods_sales_count) {
		this.goods_sales_count = goods_sales_count;
	}

	
	
}
