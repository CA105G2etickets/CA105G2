package com.order_detail.model;

import java.util.*;

public interface OrderDetailDAO_interface {
	public void insert(OrderDetailVO orderDetailVO);
    public void update(OrderDetailVO orderDetailVO);
    public void delete(String order_no, String goods_no);
    public OrderDetailVO findByPrimaryKey(String order_no, String goods_no);
    public List<OrderDetailVO> getAll();
    public List<String> getAllOrderNo();
    public List<OrderDetailVO> findByOrderNo(String order_no);
    public void insertToOrderHistory (OrderDetailVO orderDetailVO, java.sql.Connection con);
    
}
