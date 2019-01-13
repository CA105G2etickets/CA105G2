package com.shopping_cart.model;

public class ShoppingCart implements java.io.Serializable {

	public ShoppingCart() {
		goods_no = "";
		evetit_no = "";
		goods_name = "";
		goods_price = 0;
		goods_quantity = 0;
		forsales_a = 0;
		goods_status = "";
	}
	
	private String goods_no;
	private String evetit_no;
	private String goods_name;
	private Integer goods_price;
	private Integer goods_quantity;	
	private Integer forsales_a;
	private String goods_status;
	
	
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
	public Integer getGoods_quantity() {
		return goods_quantity;
	}
	public void setGoods_quantity(Integer goods_quantity) {
		this.goods_quantity = goods_quantity;
	}
	public Integer getForsales_a() {
		return forsales_a;
	}
	public void setForsales_a(Integer forsales_a) {
		this.forsales_a = forsales_a;
	}
	public String getGoods_status() {
		return goods_status;
	}
	public void setGoods_status(String goods_status) {
		this.goods_status = goods_status;
	}
	
}
