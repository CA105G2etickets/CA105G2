package com.ticketorder.model;
import java.sql.Timestamp;
import java.util.List;

import com.seating_area.model.SeatingAreaVO;
import com.ticket.model.TicketVO;
import com.ticket.model.TicketVO2;

public class TicketOrderService {
	private TicketOrderDAO_interface dao;
	
	public TicketOrderService() {
//		dao = new TicketOrderDAOJDBC();
//		dao2 = new TicketOrderDAO();
		dao = new TicketOrderHibernateDAO();
	}
	public TicketOrderVO addTicketOrder(String member_no, String ticarea_no, Integer total_price, 
			Integer total_amount,Timestamp ticket_order_time,String payment_method,
			String ticket_order_status) {
		
		TicketOrderVO toVO = new TicketOrderVO();
		toVO.setMember_no(member_no);
		toVO.setTotal_price(total_price);
		toVO.setTotal_amount(total_amount);
		toVO.setTicket_order_time(ticket_order_time);
		toVO.setPayment_method(payment_method);
		toVO.setTicket_order_status(ticket_order_status);
		
		dao.insert(toVO);
		
		return toVO;
	}
	public TicketOrderVO updateTicketOrder(String ticket_order_no,String member_no, String ticarea_no, 
			Integer total_price, Integer total_amount,Timestamp ticket_order_time,
			String payment_method,String ticket_order_status) {
		TicketOrderVO toVO = new TicketOrderVO();
		toVO.setTicket_order_no(ticket_order_no);
		toVO.setMember_no(member_no);
//		toVO.setTicarea_no(ticarea_no);
		toVO.setTotal_price(total_price);
		toVO.setTotal_amount(total_amount);
		toVO.setTicket_order_time(ticket_order_time);
		toVO.setPayment_method(payment_method);
		toVO.setTicket_order_status(ticket_order_status);
		dao.update(toVO);
		
		return toVO;
	}
	
	public void deleteTicketOrder(String ticket_order_no) {
		dao.delete(ticket_order_no);
	}
	
	public TicketOrderVO getOneTicketOrder(String ticket_order_no) {
		return dao.findByPrimaryKey(ticket_order_no);
	}
	
	public List<TicketOrderVO> getAll(){
		return dao.getAll();
	}
	
	public String insertThenGetLatestToNoWithCondition(TicketOrderVO ticketorderVO) {
		return dao.insertThenGetLatestToNoWithCondition(ticketorderVO);
	}
	
	
//	public void insertWithTickets(TicketOrderVO ticketorderVO, List<TicketVO> list) {
//		dao.insertWithTickets(ticketorderVO, list);
//	}
//	
//	public String insertTicketOrderAndUpdateTicArea(TicketOrderVO ticketorderVO, SeatingAreaVO seatingareaVO) {
//		return dao.insertTicketOrderAndUpdateTicArea(ticketorderVO, seatingareaVO);
//	}
//	
	public void updateTicketOrderAndInsertTickets(TicketOrderVO2 ticketorderVO2, List<TicketVO2> ticket2List) {
		com.ticketorder.model.TicketOrderDAO dao2 = new com.ticketorder.model.TicketOrderDAO();
		dao2.updateTicketOrderAndInsertTickets(ticketorderVO2, ticket2List);
	}
//	
//	public String cancelTicketOrderByServlet(String ticket_order_no) {
//		return dao.cancelTicketOrderByServlet(ticket_order_no);
//	}
}
