package com.group_member.model;

import java.util.List;
import java.util.Map;

public interface Group_memberDAO_interface {
	 //新增一個跟團者
	 public void add(Group_memberVO group_memberVO);
     //刪除跟團資訊
     public void delete(String member_no);
     //查詢一個跟團資訊
     public Group_memberVO findByPrimaryKey(String member_no,String group_no);
     //取得所有跟團資訊
     public List<Group_memberVO> getAll();
     //查詢該團所購買的數量
     public String getproductquantity(String group_no);
     //查詢所有跟團者
     public List<Group_memberVO> getmember_BY_group_member();
     //查詢該會員所跟的團
     public List<Group_memberVO> getgroup_BY_member_no(String member_no);
     //同時新增開團者與跟團者
     public void add2(Group_memberVO group_memberVO , java.sql.Connection con);
     //取得所有開團數量
     public Map <String,Integer> getgroup_quantity();
     //修改一個跟團資訊
     public void update(Group_memberVO group_memberVO);
     //退團修改
     public void change_quit(Group_memberVO group_memberVO);
     //email查詢
     public String getemail(String member_no);
     //寄出email
     public void sendMail(String to, String messageText);

}
