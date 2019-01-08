package com.group_member.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.group_open.model.Group_openService;
import com.group_open.model.Group_openVO;

public class Group_memberService {
	
	private Group_memberDAO_interface dao;
	
	public Group_memberService() {
		
		dao = new Group_memberDAO();
		
	}
	
	public Group_memberVO addGroup_member(String member_no, String group_no, Timestamp join_time, Integer product_quantity,
			String pay_status, String group_member_status, String log_out_reason, String order_phone,
			String pay_method) {
		
		Group_memberVO group_memberVO = new Group_memberVO();

		group_memberVO.setMember_no(member_no);
		group_memberVO.setGroup_no(group_no);
		group_memberVO.setJoin_time(join_time);
		group_memberVO.setProduct_quantity(product_quantity);
		group_memberVO.setPay_status(pay_status);
		group_memberVO.setGroup_member_status(group_member_status);
		group_memberVO.setLog_out_reason(log_out_reason);
		group_memberVO.setOrder_phone(order_phone);
		group_memberVO.setPay_method(pay_method);
		
		dao.add(group_memberVO);
		
			
		return group_memberVO;
		
	}
	
	public Group_memberVO updateGroup_member(String member_no, String group_no, Timestamp join_time, Integer product_quantity,
			String pay_status, String group_member_status, String log_out_reason, String order_phone,
			String pay_method) {
		
				Group_memberVO groupmemberVO = new Group_memberVO();
				
				groupmemberVO.setMember_no(member_no);
				groupmemberVO.setGroup_no(group_no);
				groupmemberVO.setJoin_time(join_time);
				groupmemberVO.setProduct_quantity(product_quantity);
				groupmemberVO.setPay_status(pay_status);
				groupmemberVO.setGroup_member_status(group_member_status);
				groupmemberVO.setLog_out_reason(log_out_reason);
				groupmemberVO.setOrder_phone(order_phone);
				groupmemberVO.setPay_method(pay_method);
				
				dao.update(groupmemberVO);	
		
				return groupmemberVO;
				
			}
	
			public void deleteGroup_member(String member_no) {
				dao.delete(member_no);
			}
			//查詢跟團人所跟的單團
			public Group_memberVO findByPrimaryKey(String member_no,String group_no) {
				return dao.findByPrimaryKey(member_no,group_no);
			}
			//取得所有跟團人
			public List <Group_memberVO> getAll(){
				return dao.getAll();
			}
		   //查詢該團所購買的數量
		   public String getproductquantity(String group_no) {
			   return dao.getproductquantity(group_no);
		   }
		   //查詢所有跟團者
		   public List<Group_memberVO> getmember_BY_group_member(){
			   return dao.getmember_BY_group_member();
		   }
		   //根據跟團者編號取得開團
		   public List<Group_memberVO> getgroup_BY_member_no(String member_no){
			   return dao.getgroup_BY_member_no(member_no);
		   }
		   //取得所有開團數量
		   public Map <String,Integer> getgroup_quantity(){
			   return dao.getgroup_quantity();
		   }
		   //同事新增開團者跟跟團者
		   public void add2(Group_memberVO group_memberVO , java.sql.Connection con) {
			   dao.add2(group_memberVO, con);
		   }
		   //退團修改
		   public void change_quit(String member_no,String group_no, String pay_status,String group_member_status,String log_out_reason) {
			   Group_memberVO group_memberVO = new Group_memberVO();	
			   	group_memberVO.setMember_no(member_no);
				group_memberVO.setGroup_no(group_no);
				group_memberVO.setPay_status(pay_status);
				group_memberVO.setGroup_member_status(group_member_status);
				group_memberVO.setLog_out_reason(log_out_reason);
			   dao.change_quit(group_memberVO);
		   }
		   //查詢email
		   public String getemail(String member_no) {
			return dao.getemail(member_no);
		   }
		   //email
		   public void sendMail(String to, String messageText){
			   dao.sendMail(to, messageText);
		   }
		   
}
