package com.ORDER_DETAIL.model;

import java.util.*;

public interface OrderDetailDAO_interface {
	public void insert(OrderDetailVO orderDetailVO);
    public void update(OrderDetailVO orderDetailVO);
    public void delete(String order_no);
    public OrderDetailVO findByPrimaryKey(String order_no);
    public List<OrderDetailVO> getAll();
}
