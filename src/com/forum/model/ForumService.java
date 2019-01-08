package com.forum.model;

import java.sql.Timestamp;
import java.util.List;

public class ForumService {
	
	 private ForumDAO_interface dao;
	 
	 public ForumService() {
		 dao = new ForumDAO();
	 }
	 
	 public ForumVO addForum(String group_no, String member_no, String forum_content, Timestamp forum_time) {
		 
		 ForumVO forumVO = new ForumVO();
		 
		 forumVO.setGroup_no(group_no);
		 forumVO.setMember_no(member_no);
		 forumVO.setForum_content(forum_content);
		 forumVO.setForum_time(forum_time);
		 
		 dao.add(forumVO);
		 
		 
		return forumVO;
		 
	 }
	 
	 public ForumVO updateForumVO(String forum_no, String group_no, String member_no, 
			 String forum_content, Timestamp forum_time){
		 	ForumVO forumVO = new ForumVO();
		 
		 forumVO.setForum_no(forum_no);	
		 forumVO.setGroup_no(group_no);
		 forumVO.setMember_no(member_no);
		 forumVO.setForum_content(forum_content);
		 forumVO.setForum_time(forum_time);
		 dao.update(forumVO);
		 
		 return forumVO;
		 
	 }
	 
	 public void deleteForum(String forum_no) {
		     dao.delete(forum_no);
	 }
	 
	 public ForumVO getOneForum(String forum_no) {
		 return dao.findByPK(forum_no);
	 }
	 
	 public List <ForumVO>getAll(){
		 return dao.getAll();
	 }
	 
	 
	 
	 
	 
	 
	 
    
}
