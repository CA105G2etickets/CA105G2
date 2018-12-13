package com.GROUP_MEMBER.model;

import java.util.List;

public interface Group_MemberDAO_interface {
	 public void insert(Group_MemberVO Group_MemberVO);
     public void update(Group_MemberVO Group_MemberVO);
     public void delete(String member_no);
     public Group_MemberVO findByPrimaryKey(String member_no);
     public List<Group_MemberVO> getAll();
	

}
