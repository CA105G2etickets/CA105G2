package com.favorite_goods.model;

public class FavoriteGoodsVO implements java.io.Serializable {

	private static final long serialVersionUID = -5775565293428691932L;
	
	private String member_no;
	private String goods_no;
	
	public FavoriteGoodsVO() {
		
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
	
}
