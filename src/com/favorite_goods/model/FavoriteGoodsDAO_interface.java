package com.favorite_goods.model;

import java.util.*;

public interface FavoriteGoodsDAO_interface {
	public void insert(FavoriteGoodsVO favoriteGoodsVO);
    public void update(FavoriteGoodsVO favoriteGoodsVO);
    public void delete(String member_no, String goods_no);
    public FavoriteGoodsVO findByPrimaryKey(String member_no);
    public List<FavoriteGoodsVO> getAll();
    public List<String> getAllMemberNo();
    public List<FavoriteGoodsVO> findByMemberNo(String member_no);
    public List<String> getAllGoodsNo();
    public List<FavoriteGoodsVO> findByGoodsNo(String goods_no);
	public boolean getOneFavoriteGoods(String member_no, String goods_no);
}
