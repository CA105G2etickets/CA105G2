package com.android.news_classification.model;

import java.util.List;

public class NewsClassificationService {

	private NewsClassificationDAO_interface dao;

	public NewsClassificationService() {
		dao = new NewsClassificationDAO();
	}



	public List<String> getAll() {
		return dao.getAll();
	}
	
}
