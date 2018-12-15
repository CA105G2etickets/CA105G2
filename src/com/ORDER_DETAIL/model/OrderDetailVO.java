package com.ORDER_DETAIL.model;

public class order_detailVO implements java.io.Serializable {

	private static final long serialVersionUID = 7973064175273608162L;
	
	private String orderNo;
	private String goodsNo;
	private Double goodsBonus;
	private Double goodsPc;
	
	public order_detailVO() {
		
	}
	
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getGoodsNo() {
		return goodsNo;
	}
	public void setGoodsNo(String goodsNo) {
		this.goodsNo = goodsNo;
	}
	public Double getGoodsBonus() {
		return goodsBonus;
	}
	public void setGoodsBonus(Double goodsBonus) {
		this.goodsBonus = goodsBonus;
	}
	public Double getGoodsPc() {
		return goodsPc;
	}
	public void setGoodsPc(Double goodsPc) {
		this.goodsPc = goodsPc;
	}	

}
