package com.NEWS.model;

import java.util.List;

public interface NewsDAO_interface {
	
	public void insert(NewsVO news);
    public void update(NewsVO news);
    public void delete(String newsNo);
    public NewsVO findByPrimaryKey(String newsNo);
    public List<NewsVO> getAll();

}
