package com.forum.model;

import java.io.IOException;
import java.util.List;



public interface ForumDAO_interface {

	//新增一則留言
	void add(ForumVO forumVO);
	//修改一則留言
	void update(ForumVO forumVO);
	//刪除一則留言
	void delete(String forum_no);
	//根據留言編號尋找一則留言
	ForumVO findByPK(String forum_no);
	//尋找全部留言
	List <ForumVO> getAll();
	//根據開團編號找尋開團留言
	public List<ForumVO> getall_forum_by_group(String group_no);
	//取得所有討論區開團編號
	public List<ForumVO> getallgroup();
	
}
