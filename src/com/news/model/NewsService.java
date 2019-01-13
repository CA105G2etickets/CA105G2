package com.news.model;

import java.sql.Date;
import java.util.List;

public class NewsService {

	private NewsDAO_interface dao;

	public NewsService() {
		dao = new NewsDAO();
	}

	public NewsVO addNews(String news_classification_no, String news_title, String news_content, String administrator_no) {

		NewsVO newsVO = new NewsVO();

		newsVO.setNews_classification_no(news_classification_no);
		newsVO.setNews_title(news_title);
		newsVO.setNews_content(news_content);
		newsVO.setAdministrator_no(administrator_no);
		dao.insert(newsVO);

		return newsVO;
	}

	public NewsVO updateNews(String news_no, String news_classification_no, String news_title, String news_content, Date announce_date, String administrator_no) {

		NewsVO newsVO = new NewsVO();
		
		newsVO.setNews_no(news_no);
		newsVO.setNews_classification_no(news_classification_no);
		newsVO.setNews_title(news_title);
		newsVO.setNews_content(news_content);
		newsVO.setAnnounce_date(announce_date);
		newsVO.setAdministrator_no(administrator_no);
		dao.update(newsVO);

		return newsVO;
	}

	public void deleteNews(String news_no) {
		dao.delete(news_no);
	}

	public NewsVO getOneNews(String news_no) {
		return dao.findByPrimaryKey(news_no);
	}

	public List<NewsVO> getAll() {
		return dao.getAll();
	}
	
}
