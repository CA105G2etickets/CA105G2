package com.ORDER_DETAIL.model;

import java.sql.Timestamp;
import java.util.List;

public class OrderDetailService {

	private OrderDetailDAO_interface dao;
	
	public OrderDetailService() {
		dao = new OrderDetailDAO();
	}
	
	public OrderDetailVO addOrderDetail(String order_no, String goods_no, Double goods_bonus,	Double goods_pc ) {
		
		OrderDetailVO orderDetailVO = new OrderDetailVO();
		
		orderDetailVO.setOrder_no(order_no);
		orderDetailVO.setGoods_no(goods_no);
		orderDetailVO.setGoods_bonus(goods_bonus);
		orderDetailVO.setGoods_pc(goods_pc);

		dao.insert(orderDetailVO);
		
		return orderDetailVO;
	}
	
	public OrderDetailVO updateOrderDetail(String goods_no, Double goods_bonus,	Double goods_pc ) {
		
		OrderDetailVO orderDetailVO = new OrderDetailVO();
		
//		orderDetailVO.setOrder_no(order_no);
		orderDetailVO.setGoods_no(goods_no);
		orderDetailVO.setGoods_bonus(goods_bonus);
		orderDetailVO.setGoods_pc(goods_pc);
		dao.update(orderDetailVO);
		
		return orderDetailVO;
	}
	
	public void deleteOrderDetail(String order_no) {
		dao.delete(order_no);
	}
	
	public OrderDetailVO getOneOrderDetail(String order_no) {
		return dao.findByPrimaryKey(order_no);
	}
	
	public List<OrderDetailVO> getAll() {
		return dao.getAll();
	}
	
}
