package com.ORDER_HISTORY.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class OrderHistoryService {

	private OrderHistoryDAO_interface dao;
	
	public OrderHistoryService() {
		dao = new OrderHistoryDAO();
	}
	
	public OrderHistoryVO addOrderHistory(String member_no, Double order_price, String pay_methods,
			String shipping_methods, Timestamp order_date, Timestamp order_etd, Timestamp pickup_date, String receiver_add,
			String receiver_name, String receiver_tel, String order_status) {
		
		OrderHistoryVO orderHistoryVO = new OrderHistoryVO();
		
		orderHistoryVO.setMember_no(member_no);
		orderHistoryVO.setOrder_price(order_price);
		orderHistoryVO.setPay_methods(pay_methods);
		orderHistoryVO.setShipping_methods(shipping_methods);
		orderHistoryVO.setOrder_date(order_date);
		orderHistoryVO.setOrder_etd(order_etd);
		orderHistoryVO.setPickup_date(pickup_date);
		orderHistoryVO.setReceiver_add(receiver_add);
		orderHistoryVO.setReceiver_name(receiver_name);
		orderHistoryVO.setReceiver_tel(receiver_tel);
		orderHistoryVO.setOrder_status(order_status);
		dao.insert(orderHistoryVO);
		
		return orderHistoryVO;
	}
	
	public OrderHistoryVO updateOrderHistory(String member_no, Double order_price, String pay_methods,
			String shipping_methods, Timestamp order_date, Timestamp order_etd, Timestamp pickup_date, String receiver_add,
			String receiver_name, String receiver_tel, String order_status, String order_no) {
	
		OrderHistoryVO orderHistoryVO = new OrderHistoryVO();
		
		orderHistoryVO.setOrder_no(order_no);
		orderHistoryVO.setMember_no(member_no);
		orderHistoryVO.setOrder_price(order_price);
		orderHistoryVO.setPay_methods(pay_methods);
		orderHistoryVO.setShipping_methods(shipping_methods);
		orderHistoryVO.setOrder_date(order_date);
		orderHistoryVO.setOrder_etd(order_etd);
		orderHistoryVO.setPickup_date(pickup_date);
		orderHistoryVO.setReceiver_add(receiver_add);
		orderHistoryVO.setReceiver_name(receiver_name);
		orderHistoryVO.setReceiver_tel(receiver_tel);
		orderHistoryVO.setOrder_status(order_status);
		dao.update(orderHistoryVO);
		
		return orderHistoryVO;
	}
	
	public void deleteOrderHistory(String order_no) {
		dao.delete(order_no);
	}
	
	public OrderHistoryVO getOneOrderHistory(String order_no) {
		return dao.findByPrimaryKey(order_no);
	}
	
	public List<OrderHistoryVO> getAll() {
		return dao.getAll();
	}
	
	public List<String> getAllMemberNo() {
		return dao.getAllMemberNo();
	}
	
	public List<OrderHistoryVO> findByMemberNo(String member_no) {
		return dao.findByMemberNo(member_no);
	}

	public List<OrderHistoryVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}
	
}
