package com.android.news.model;

import java.util.List;


public class NewsService {
	
	private NewsDAO_interface dao = new NewsDAO();
	
	public List<NewsVO> findByClassification(String news_classification){
		return dao.findByClassification(news_classification);
	}
	
	public List<NewsVO> getAll(String str) {
		return dao.getAll(str);
	}

}
