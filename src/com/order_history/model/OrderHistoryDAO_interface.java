package com.order_history.model;

import java.util.*;

import com.order_detail.model.OrderDetailVO;

public interface OrderHistoryDAO_interface {
	public void insert(OrderHistoryVO orderHistoryVO);
    public void update(OrderHistoryVO orderHistoryVO);
    public void delete(String order_no);
    public OrderHistoryVO findByPrimaryKey(String order_no);
    public List<OrderHistoryVO> getAll();
    public List<String> getAllMemberNo();
    public List<OrderHistoryVO> findByMemberNo(String member_no);
    public List<OrderHistoryVO> getAll(Map<String, String[]> map); 
    
    public Set<OrderDetailVO> getOrdersDetailByOrderHistory(String order_no);
    public void insertWithDetail (OrderHistoryVO orderHistoryVO, List<OrderDetailVO> list);
    
}
