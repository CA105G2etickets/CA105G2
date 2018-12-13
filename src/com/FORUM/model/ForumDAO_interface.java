package com.FORUM.model;

import java.io.IOException;
import java.util.List;



public interface ForumDAO_interface {

	
	void add(ForumVO forum);
	void update(ForumVO forum);
	void delete(String fORUM_NO);
	ForumVO findByPK(String fORUM_NO);
	List <ForumVO> getAll();
	
}
