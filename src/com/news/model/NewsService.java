package com.news.model;

public class NewsService {
	
	private NewsDAO_interface dao;
	
	public NewsService() {
		dao = new NewsDAO();
		
	}

}
