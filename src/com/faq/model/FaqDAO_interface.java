package com.faq.model;

import java.util.List;

public interface FaqDAO_interface {
	
	public void insert(FaqVO faqVO);
    public void update(FaqVO faqVO);
    public void delete(String faq_no);
    public FaqVO findByPrimaryKey(String faq_no);
    public List<FaqVO> getAll();

}