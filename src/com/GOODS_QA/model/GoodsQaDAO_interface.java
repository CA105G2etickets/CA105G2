package com.GOODS_QA.model;

import java.util.List;

public interface GoodsQaDAO_interface {
	
	public void insert(GoodsQaVO faqVO);
	   public void update(GoodsQaVO faqVO);
	    public void delete(String gfaq_no);
	    public GoodsQaVO findByPrimaryKey(String gfaq_no);
	    public List<GoodsQaVO> getAll();

}
