package com.news_classification.model;

import java.util.List;

public interface NewsClassificationDAO_interface {
	
	public void insert(NewsClassificationVO newsClassification);
    public void update(NewsClassificationVO newsClassification);
    public void delete(String newsClassificationNo);
    public NewsClassificationVO findByPrimaryKey(String newsClassificationNo);
    public List<NewsClassificationVO> getAll();
    
}
