package com.group_open.model;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.event_title.model.EventTitleVO;
import com.group_member.model.Group_memberDAO;
import com.group_member.model.Group_memberDAO_interface;
import com.group_member.model.Group_memberVO;
import com.sun.org.apache.bcel.internal.generic.RETURN;

public class Group_openService {

	private Group_openDAO_interface dao;

	public Group_openService() {
		dao = new Group_openDAO();
	}

	// 新增開團者
	public Group_openVO addGroup_open(String member_no, String goods_no, String group_name, Integer group_limit,
			String group_introduction, String group_mind, Timestamp group_start_date, Timestamp group_close_date,
			byte[] group_banner_1, byte[] group_banner_2, String group_status, String group_address, Double latitude,
			Double longitude, Timestamp group_time, Integer group_price) {

		Group_openVO group_openVO = new Group_openVO();

		group_openVO.setMember_no(member_no);
		group_openVO.setGoods_no(goods_no);
		group_openVO.setGroup_name(group_name);
		group_openVO.setGroup_limit(group_limit);
		group_openVO.setGroup_introduction(group_introduction);
		group_openVO.setGroup_mind(group_mind);
		group_openVO.setGroup_start_date(group_start_date);
		group_openVO.setGroup_close_date(group_close_date);
		group_openVO.setGroup_banner_1(group_banner_1);
		group_openVO.setGroup_banner_2(group_banner_2);
		group_openVO.setGroup_status(group_status);
		group_openVO.setGroup_address(group_address);
		group_openVO.setLatitude(latitude);
		group_openVO.setLongitude(longitude);
		group_openVO.setGroup_time(group_time);
		group_openVO.setGroup_price(group_price);
		System.out.println("group_openServlet>addGroup_open");

		dao.add(group_openVO);

		return group_openVO;

	}

	public Group_openVO updateGroup_open(String group_no, String member_no, String goods_no, String group_name,
			Integer group_limit, String group_introduction, String group_mind, Timestamp group_start_date,
			Timestamp group_close_date, byte[] group_banner_1, byte[] group_banner_2, String group_status,
			String group_address, Double latitude, Double longitude, Timestamp group_time, Integer group_price) {

		Group_openVO group_openVO = new Group_openVO();

		group_openVO.setGroup_no(group_no);
		group_openVO.setMember_no(member_no);
		group_openVO.setGoods_no(goods_no);
		group_openVO.setGroup_name(group_name);
		group_openVO.setGroup_limit(group_limit);
		group_openVO.setGroup_introduction(group_introduction);
		group_openVO.setGroup_mind(group_mind);
		group_openVO.setGroup_start_date(group_start_date);
		group_openVO.setGroup_close_date(group_close_date);
		group_openVO.setGroup_banner_1(group_banner_1);
		group_openVO.setGroup_banner_2(group_banner_2);
		group_openVO.setGroup_status(group_status);
		group_openVO.setGroup_address(group_address);
		group_openVO.setLatitude(latitude);
		group_openVO.setLongitude(longitude);
		group_openVO.setGroup_time(group_time);
		group_openVO.setGroup_price(group_price);
		dao.update(group_openVO);
		return group_openVO;
	}

	// 刪除開團資訊
	public void deleteGroup_open(String group_no) {
		dao.delete(group_no);
	}

	// 根據開團編號查詢團
	public Group_openVO getOneGroup_open(String group_no) {
		return dao.findByPrimaryKey(group_no);
	}

	// 查詢所有現有的團
	public List<Group_openVO> getAll() {
		return dao.getAll();
	}

	// 模糊查詢
	public Set<Group_memberVO> getGroup_memberByGroup_no(String group_no) {
		return dao.getGroup_memberByGroup_no(group_no);
	}

	// 查詢該會員有的開團
	public List<Group_openVO> getgroup_openBymember_no(String member_no) {
		return dao.getgroup_openBymember_no(member_no);
	}

	// 查詢所有開團者會員編號
	public List<Group_openVO> getall_member_BYgroup_open() {
		return dao.getall_member_BYgroup_open();
	}

	// 取得開團總人數
	public Map<String, Integer> getmember_count() {
		return dao.getmember_count();
	}

	// 同時新增開團者與跟團者
	public void add2(Group_openVO group_openVO, Group_memberVO group_memberVO) {
		dao.add2(group_openVO, group_memberVO);
	}

	// 同時刪除開團人跟跟團人
	public void delete2(String group_no) {
		dao.delete2(group_no);
	}
	// 尋找開團人
	 public String getgroup_open_member_no(String group_no) {
		return dao.getgroup_open_member_no(group_no);
	 }
	 //開團失敗
	 public void group_open_quit(String group_no) {
		 dao.group_open_quit(group_no);
	 }
	 //開團成功
	 public void group_open_sucess(String group_no) {
		 dao.group_open_sucess(group_no);
	 }
	 //取得折扣價格
	 public String getgroup_price(String goods_no) {
		return dao.getgroup_price(goods_no);
	}
	 //複合查詢
	 public List<Group_openVO> getcompoundsearch(Map<String,String[]>map){
		 return dao.getcompoundsearch(map);
	 }
	 //二階段選單 查詢
	 public Map<String,String> getevetitle_goods(String evetit_no){
		 return dao.getevetitle_goods(evetit_no);
	 }
	 //查詢活動主題
	 public List <EventTitleVO> geteventitle(){
		 return dao.geteventitle();
	 }
	 	
}