package com.faq.model;

import java.util.List;

public interface FaqDAO_interface {
	
	public void insert(FaqVO faq);
    public void update(FaqVO faq);
    public void delete(String faqNo);
    public FaqVO findByPrimaryKey(String faqNo);
    public List<FaqVO> getAll();

}