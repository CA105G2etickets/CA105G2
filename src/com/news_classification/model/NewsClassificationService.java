package com.news_classification.model;

import java.util.List;

public class NewsClassificationService {

	private NewsClassificationDAO_interface dao;

	public NewsClassificationService() {
		dao = new NewsClassificationDAO();
	}

	public NewsClassificationVO addNewsClassification(String newsClassificationNo, String newsClassification) {

		NewsClassificationVO newsClass = new NewsClassificationVO();

		newsClass.setNewsClassificationNo(newsClassificationNo);
		newsClass.setNewsClassification(newsClassification);
		dao.insert(newsClass);

		return newsClass;
	}

	public NewsClassificationVO updateNewsClassification(String newsClassificationNo, String newsClassification) {

		NewsClassificationVO newsClass = new NewsClassificationVO();

		newsClass.setNewsClassificationNo(newsClassificationNo);
		newsClass.setNewsClassification(newsClassification);
		dao.update(newsClass);

		return newsClass;
	}

	public void deleteNewsClassification(String newsClassificationNo) {
		dao.delete(newsClassificationNo);
	}

	public NewsClassificationVO getOneNewsClassification(String newsClassificationNo) {
		return dao.findByPrimaryKey(newsClassificationNo);
	}

	public List<NewsClassificationVO> getAll() {
		return dao.getAll();
	}
	
}
