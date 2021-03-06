package com.group_open.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.event_title.model.EventTitleVO;
import com.goods.model.GoodsVO;
import com.group_member.model.Group_memberVO;

public interface Group_openDAO_interface {
	//新增開團者
	public void add(Group_openVO group_openVO);
	//同時新增開團者與跟團者
	public void add2(Group_openVO group_openVO,Group_memberVO group_memberVO);
	//修改開團資訊
	public void update(Group_openVO group_openVO);
	//刪除開團資訊
	public void delete(String group_no);
	//根據開團編號查詢團
	public Group_openVO findByPrimaryKey(String group_no);
	//查詢所有現有的團
	public List <Group_openVO> getAll();
	//模糊查詢
	public Set <Group_memberVO> getGroup_memberByGroup_no(String  group_no);
	//查詢該會員有的開團
	public List<Group_openVO> getgroup_openBymember_no(String member_no);
	//查詢所有開團者會員編號
	public List<Group_openVO> getall_member_BYgroup_open();
	//取得開團總人數
	  public Map<String, Integer> getmember_count();
	  //同時刪除跟團人跟開團人
	  public void delete2(String group_no);
	  //尋找開團人
	  public String getgroup_open_member_no(String group_no);
	  //解散開團
	  public void group_open_quit(String group_no);
	  //開團成功
	  public void group_open_sucess(String group_no);
	  //取得折扣價格
	  public String getgroup_price(String goods_no);
	  //複合查詢
	  public List<Group_openVO> getcompoundsearch(Map<String,String[]>map);
	  //查詢活動主題跟商品名稱
	  public Map<String,String> getevetitle_goods(String evetit_no);
	  //查詢活動主題
	  public List <EventTitleVO> geteventitle();
	  //開團完成
	  public void group_complete(String group_no);
	  //人氣商品前三
	  public Map<String, Integer> getfavorite();
	  //需開團商品(二階層式選單)
	  public List<GoodsVO> getgroup_goods(String evetit_no);
	  
		
}


