package com.GOODS_QA.model;

import java.util.List;

public interface GoodsFaq_interface {
	
	public void insert(GoodsFaqVO faqVO);
	   public void update(GoodsFaqVO faqVO);
	    public void delete(String gfaq_no);
	    public GoodsFaqVO findByPrimaryKey(String gfaq_no);
	    public List<GoodsFaqVO> getAll();

}
