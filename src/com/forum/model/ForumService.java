package com.forum.model;

import java.sql.Timestamp;
import java.util.List;

public class ForumService {

	private ForumDAO_interface dao;

	public ForumService() {
		dao = new ForumDAO();
	}
	//增加一則留言
	public ForumVO addForum(String group_no, String member_no, String forum_content, Timestamp forum_time) {

		ForumVO forumVO = new ForumVO();

		forumVO.setGroup_no(group_no);
		forumVO.setMember_no(member_no);
		forumVO.setForum_content(forum_content);
		forumVO.setForum_time(forum_time);

		dao.add(forumVO);

		return forumVO;

	}
	//修改一則留言
	public ForumVO updateForumVO(String forum_no, String group_no, String member_no, String forum_content,
			Timestamp forum_time) {
		ForumVO forumVO = new ForumVO();

		forumVO.setForum_no(forum_no);
		forumVO.setGroup_no(group_no);
		forumVO.setMember_no(member_no);
		forumVO.setForum_content(forum_content);
		forumVO.setForum_time(forum_time);
		dao.update(forumVO);

		return forumVO;

	}
	//根據討論區編號刪除一則留言
	public void deleteForum(String forum_no) {
		dao.delete(forum_no);
	}
	//根據留言編號尋找一則留言
	public ForumVO getOneForum(String forum_no) {
		return dao.findByPK(forum_no);
	}
	//取得全部留言
	public List<ForumVO> getAll() {
		return dao.getAll();
	}
	//取得該團所有討論
	public List<ForumVO> getall_forum_by_group(String group_no){
		return dao.getall_forum_by_group(group_no);
	}
	//取得所有有討論區的開團編號
	public List<ForumVO> getallgroup(){
		return dao.getallgroup();
	}
	
	
	

}
