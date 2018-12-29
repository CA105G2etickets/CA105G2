package com.FAVORITE_GOODS.model;

import java.util.*;

public interface FavoriteGoodsDAO_interface {
	public void insert(FavoriteGoodsVO orderDetailVO);
    public void update(FavoriteGoodsVO orderDetailVO);
    public void delete(String order_no);
    public FavoriteGoodsVO findByPrimaryKey(String member_no);
    public List<FavoriteGoodsVO> getAll();
}
