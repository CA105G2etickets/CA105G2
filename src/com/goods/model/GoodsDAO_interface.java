package com.goods.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

 
public interface GoodsDAO_interface {

	public void insert(GoodsVO goodsVO);

	public void update(GoodsVO goodsVO);

	public void delete(String goods_no);

	public GoodsVO findByPrimarykey(String goods_no);

	public List<GoodsVO> getAll();

	public List<GoodsVO> getAllLaunched(Map<String, String[]> map);

	public List<GoodsVO> getAllLaunched();

	public Set<GoodsVO> getEventsByEventTitle(String evetit_no);



}
