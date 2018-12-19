package com.GOODS_QA.model;

import java.util.List;

public interface GoodsQaDAO_interface {
	
	public void insert(GoodsQaVO goodsQaVO);
	   public void update(GoodsQaVO goodsQaVO);
	    public void delete(String gfaq_no);
	    public GoodsQaVO findByPrimaryKey(String gfaq_no);
	    public List<GoodsQaVO> getAll();

}
