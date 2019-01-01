package com.android.news.model;

import java.util.List;

public interface NewsDAO_interface { 
	
    public List<NewsVO> findByClassification(String news_classification);
    public List<NewsVO> getAll(String str);   
}
