package com.ORDER_HISTORY.model;

import java.util.*;

public interface OrderHistoryDAO_interface {
	public void insert(OrderHistoryVO orderHistoryVO);
    public void update(OrderHistoryVO orderHistoryVO);
    public void delete(String order_no);
    public OrderHistoryVO findByPrimaryKey(String order_no);
    public List<OrderHistoryVO> getAll();
    public List<String> getAllMemberNo();
    public List<OrderHistoryVO> findByMemberNo(String member_no);
    public List<OrderHistoryVO> getAll(Map<String, String[]> map);    
}
