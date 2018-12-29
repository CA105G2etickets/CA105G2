package com.android.member.model;

import java.util.List;

public interface MemberDAO_interface {
	
	public void insert(MemberVO member);
    public void update(MemberVO member);
    public void delete(String memberNo);
    public MemberVO findByPrimaryKey(String memberNo);
    public List<MemberVO> getAll();
//    public boolean isMember(String userName, String userPassword);
    public String isMember(String userName, String userPassword,String thirdUID);
    public byte[] getImage(String memberNo);
    
}
