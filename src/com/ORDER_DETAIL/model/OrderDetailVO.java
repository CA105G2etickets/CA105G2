package com.ORDER_DETAIL.model;

public class OrderDetailVO implements java.io.Serializable {

	private static final long serialVersionUID = 7973064175273608162L;
	
	private String order_no;
	private String goods_no;
	private Double goods_bonus;
	private Double goods_pc;
	
	public OrderDetailVO() {
		
	}
	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}

	public String getGoods_no() {
		return goods_no;
	}

	public void setGoods_no(String goods_no) {
		this.goods_no = goods_no;
	}

	public Double getGoods_bonus() {
		return goods_bonus;
	}

	public void setGoods_bonus(Double goods_bonus) {
		this.goods_bonus = goods_bonus;
	}

	public Double getGoods_pc() {
		return goods_pc;
	}

	public void setGoods_pc(Double goods_pc) {
		this.goods_pc = goods_pc;
	}	


}
