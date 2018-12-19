package com.goods.model;

import java.sql.Timestamp;
import java.util.List;


public class GoodsService {

	private GoodsDAO_interface goodsDao;

	public GoodsService() {
		goodsDao = new GoodsDAO();

	}

	public GoodsVO addGoods(String evetit_no, String goods_name, Integer goods_price, byte[] goods_picture1,
			byte[] goods_picture2, byte[] goods_picture3, String goods_introduction, Integer forsales_a,
			Integer favorite_count, String goods_status, Timestamp launchdate, Timestamp offdate,
			Integer goods_group_count, Integer goods_want_count, Integer goods_sales_count) {

		GoodsVO goodsVO = new GoodsVO();

		goodsVO.setEvetit_no(evetit_no);
		goodsVO.setGoods_name(goods_name);
		goodsVO.setGoods_price(goods_price);
		goodsVO.setGoods_picture1(goods_picture1);
		goodsVO.setGoods_picture2(goods_picture2);
		goodsVO.setGoods_picture3(goods_picture3);
		goodsVO.setGoods_introduction(goods_introduction);
		goodsVO.setForsales_a(forsales_a);
		goodsVO.setFavorite_count(favorite_count);
		goodsVO.setGoods_status(goods_status);
		goodsVO.setLaunchdate(launchdate);
		goodsVO.setOffdate(offdate);
		goodsVO.setGoods_group_count(goods_group_count);
		goodsVO.setGoods_want_count(goods_want_count);
		goodsVO.setGoods_sales_count(goods_sales_count);

		goodsDao.insert(goodsVO);

		return goodsVO;
	}

	public GoodsVO updateGoods(String goods_no, String evetit_no, String goods_name, Integer goods_price,
			byte[] goods_picture1, byte[] goods_picture2, byte[] goods_picture3, String goods_introduction,
			Integer forsales_a, Integer favorite_count, String goods_status, Timestamp launchdate, Timestamp offdate,
			Integer goods_group_count, Integer goods_want_count, Integer goods_sales_count) {

		GoodsVO goodsVO = new GoodsVO();

		goodsVO.setGoods_no(goods_no);
		goodsVO.setEvetit_no(evetit_no);
		goodsVO.setGoods_name(goods_name);
		goodsVO.setGoods_price(goods_price);
		goodsVO.setGoods_picture1(goods_picture1);
		goodsVO.setGoods_picture2(goods_picture2);
		goodsVO.setGoods_picture3(goods_picture3);
		goodsVO.setGoods_introduction(goods_introduction);
		goodsVO.setForsales_a(forsales_a);
		goodsVO.setFavorite_count(favorite_count);
		goodsVO.setGoods_status(goods_status);
		goodsVO.setLaunchdate(launchdate);
		goodsVO.setOffdate(offdate);
		goodsVO.setGoods_group_count(goods_group_count);
		goodsVO.setGoods_want_count(goods_want_count);
		goodsVO.setGoods_sales_count(goods_sales_count);

		goodsDao.update(goodsVO);

		return goodsVO;

	}

	public void deleteGoods(String goods_no) {
		goodsDao.delete(goods_no);
	}

	public GoodsVO getOneGoods(String goods_no) {
		return goodsDao.findByPrimarykey(goods_no);
	}

	public List<GoodsVO> getAll() {
		return goodsDao.getAll();
	}
}