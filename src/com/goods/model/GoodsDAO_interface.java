package com.GOODS.model;

import java.util.List;

public interface GoodsDAO_interface {
	
	public void insert(GoodsVO goodsVO);
	public void update(GoodsVO goodsVO);
	public void delete(String goods_no);
	public GoodsVO findByPrimarykey(String goods_no);
	public List <GoodsVO> getAll();
}
