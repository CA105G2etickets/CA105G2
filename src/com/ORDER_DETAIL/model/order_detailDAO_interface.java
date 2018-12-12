package com.ORDER_DETAIL.model;

import java.util.*;

public interface order_detailDAO_interface {
	public void insert(order_detailVO orderDetailVO);
    public void update(order_detailVO orderDetailVO);
    public void delete(String orderNo);
    public order_detailVO findByPrimaryKey(String orderNo);
    public List<order_detailVO> getAll();
}
