package com.FAVORITE_GOODS.model;

import java.util.*;

public interface favorite_goodsDAO_interface {
	public void insert(favorite_goodsVO orderDetailVO);
    public void update(favorite_goodsVO orderDetailVO);
    public void delete(String orderNo);
    public favorite_goodsVO findByPrimaryKey(String memberNo);
    public List<favorite_goodsVO> getAll();
}
