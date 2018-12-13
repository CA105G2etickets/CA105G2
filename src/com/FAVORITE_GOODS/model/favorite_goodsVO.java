package com.FAVORITE_GOODS.model;

public class favorite_goodsVO implements java.io.Serializable {

	private static final long serialVersionUID = -5775565293428691932L;
	
	private String memberNo;
	private String goodsNo;
	
	public favorite_goodsVO() {
		
	}
	public String getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}
	public String getGoodsNo() {
		return goodsNo;
	}
	public void setGoodsNo(String goodsNo) {
		this.goodsNo = goodsNo;
	}
	
}
