package com.ORDER_HISTORY.model;

import java.util.List;

public interface OrderHistoryDAO_interface {
	public void insert(OrderHistoryVO orderHistoryVO);
    public void update(OrderHistoryVO orderHistoryVO);
    public void delete(String orderNo);
    public OrderHistoryVO findByPrimaryKey(String orderNo);
    public List<OrderHistoryVO> getAll();
}
