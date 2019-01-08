package com.forum.model;

import java.io.IOException;
import java.util.List;



public interface ForumDAO_interface {

	
	void add(ForumVO forumVO);
	void update(ForumVO forumVO);
	void delete(String forum_no);
	ForumVO findByPK(String forum_no);
	List <ForumVO> getAll();
	
}
