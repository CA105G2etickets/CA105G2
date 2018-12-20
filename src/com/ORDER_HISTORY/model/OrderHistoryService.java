package com.ORDER_HISTORY.model;

import java.sql.Timestamp;
import java.util.List;

public class OrderHistoryService {

	private OrderHistoryDAO_interface dao;
	
	public OrderHistoryService() {
		dao = new OrderHistoryDAO();
	}
	
	public OrderHistoryVO addOrderHistory(String memberNo, Double orderPrice, String payMethods,
			String shippingMethods, Timestamp orderDate, Timestamp orderEtd, Timestamp pickupDate, String receiverAdd,
			String receiverName, String receiverTel, String orderStatus) {
		
		OrderHistoryVO orderHistoryVO = new OrderHistoryVO();
		
		orderHistoryVO.setMemberNo(memberNo);
		orderHistoryVO.setOrderPrice(orderPrice);
		orderHistoryVO.setPayMethods(payMethods);
		orderHistoryVO.setShippingMethods(shippingMethods);
		orderHistoryVO.setOrderDate(orderDate);
		orderHistoryVO.setOrderEtd(orderEtd);
		orderHistoryVO.setPickupDate(pickupDate);
		orderHistoryVO.setReceiverAdd(receiverAdd);
		orderHistoryVO.setReceiverName(receiverName);
		orderHistoryVO.setReceiverTel(receiverTel);
		orderHistoryVO.setOrderStatus(orderStatus);
		dao.insert(orderHistoryVO);
		
		return orderHistoryVO;
	}
	
	public OrderHistoryVO updateOrderHistory(String memberNo, Double orderPrice, String payMethods,
			String shippingMethods, Timestamp orderDate, Timestamp orderEtd, Timestamp pickupDate, String receiverAdd,
			String receiverName, String receiverTel, String orderStatus, String orderNo) {
	
		OrderHistoryVO orderHistoryVO = new OrderHistoryVO();
		
		orderHistoryVO.setOrderNo(orderNo);
		orderHistoryVO.setMemberNo(memberNo);
		orderHistoryVO.setOrderPrice(orderPrice);
		orderHistoryVO.setPayMethods(payMethods);
		orderHistoryVO.setShippingMethods(shippingMethods);
		orderHistoryVO.setOrderDate(orderDate);
		orderHistoryVO.setOrderEtd(orderEtd);
		orderHistoryVO.setPickupDate(pickupDate);
		orderHistoryVO.setReceiverAdd(receiverAdd);
		orderHistoryVO.setReceiverName(receiverName);
		orderHistoryVO.setReceiverTel(receiverTel);
		orderHistoryVO.setOrderStatus(orderStatus);
		dao.update(orderHistoryVO);
		
		return orderHistoryVO;
	}
	
	public void deleteOrderHistory(String orderNo) {
		dao.delete(orderNo);
	}
	
	public OrderHistoryVO getOneOrderHistory(String orderNo) {
		return dao.findByPrimaryKey(orderNo);
	}
	
	public List<OrderHistoryVO> getAll() {
		return dao.getAll();
	}
	
}
