package com.forum.model;

import java.io.Serializable;
import java.sql.Clob;
import java.sql.Time;
import java.sql.Timestamp;

import oracle.sql.CLOB;

public class ForumVO implements Serializable {

	private String  forum_no;
	private String  group_no;
	private String  member_no;
	private String  forum_content;
	private Timestamp forum_time;

	public ForumVO() {
	}
	
	

	public ForumVO(String forum_no, String group_no, String member_no, String forum_content, Timestamp forum_time) {
		super();
		this.forum_no = forum_no;
		this.group_no = group_no;
		this.member_no = member_no;
		this.forum_content = forum_content;
		this.forum_time = forum_time;
	}



	public String getForum_no() {
		return forum_no;
	}

	public void setForum_no(String forum_no) {
		this.forum_no = forum_no;
	}

	public String getGroup_no() {
		return group_no;
	}

	public void setGroup_no(String group_no) {
		this.group_no = group_no;
	}

	public String getMember_no() {
		return member_no;
	}

	public void setMember_no(String member_no) {
		this.member_no = member_no;
	}

	public String getForum_content() {
		return forum_content;
	}

	public void setForum_content(String forum_content) {
		this.forum_content = forum_content;
	}

	public Timestamp getForum_time() {
		return forum_time;
	}

	public void setForum_time(Timestamp forum_time) {
		this.forum_time = forum_time;
	}

	
	
	

	
}
