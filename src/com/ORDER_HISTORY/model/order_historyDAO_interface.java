package com.ORDER_HISTORY.model;

import java.util.List;

public interface order_historyDAO_interface {
	public void insert(order_historyVO orderHistoryVO);
    public void update(order_historyVO orderHistoryVO);
    public void delete(String orderNo);
    public order_historyVO findByPrimaryKey(String orderNo);
    public List<order_historyVO> getAll();
}
