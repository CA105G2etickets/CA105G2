package com.FAVORITE_GOODS.model;

import java.util.*;

public interface FavoriteGoodsDAO_interface {
	public void insert(FavoriteGoodsVO orderDetailVO);
    public void update(FavoriteGoodsVO orderDetailVO);
    public void delete(String orderNo);
    public FavoriteGoodsVO findByPrimaryKey(String memberNo);
    public List<FavoriteGoodsVO> getAll();
}
