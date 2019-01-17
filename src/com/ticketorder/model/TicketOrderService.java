package com.ticketorder.model;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import com.seating_area.model.SeatingAreaVO;
import com.seating_area.model.SeatingArea_H5_VO;
import com.ticket.model.Old_TicketVO;
import com.ticket.model.TicketVO;
import com.ticket.model.TicketVO2;

public class TicketOrderService {
	private TicketOrderDAO_interface dao;
	private Old_TicketOrderDAO_interface old_dao;
	
	public TicketOrderService() {
//		dao = new TicketOrderDAOJDBC();
//		dao2 = new TicketOrderDAO();
		dao = new TicketOrderHibernateDAO();
		old_dao = new Old_TicketOrderDAO();
	}
	
	public TicketOrderVO addTicketOrderWithSaVO(String member_no, SeatingArea_H5_VO savo, 
			Integer total_price, Integer total_amount,Timestamp ticket_order_time,
			String payment_method, String ticket_order_status) {
		
		TicketOrderVO toVO = new TicketOrderVO();
		toVO.setMember_no(member_no);
		toVO.setTotal_price(total_price);
		toVO.setTotal_amount(total_amount);
		toVO.setTicket_order_time(ticket_order_time);
		toVO.setPayment_method(payment_method);
		toVO.setTicket_order_status(ticket_order_status);
		toVO.setSeatingarea_h5VO(savo);
		dao.insert(toVO);
		
		return toVO;
	}
	public TicketOrderVO updateTicketOrder(String ticket_order_no,String member_no, SeatingArea_H5_VO savo, 
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
		toVO.setSeatingarea_h5VO(savo);
		
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
	
	public String insert(TicketOrderVO ticketorderVO) {
		return dao.insertThenGetLatestToNoWithCondition(ticketorderVO);
	}
	
	public List<TicketOrderVO> getTicketOrdersByMemberNo(String member_no){
		return dao.getTicketOrdersByMemberNo(member_no);
	}
	public Set<TicketVO> getTicketsByTicketOrderNo(String ticket_order_no){
		return dao.getTicketsByTicketOrderNo(ticket_order_no);
	}
	
	//for servlet to update ticketorder to OUTDATE4
	public void cancelTargetTicketOrderByServletDueToOutDatedWithConditions(String ticket_order_no) {
		dao.cancelTargetTicketOrderByServletDueToOutDatedWithConditions(ticket_order_no);
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
	
	
	
	//================================================== 與舊版本相容 ==================================================
	public void insertWithTickets(Old_TicketOrderVO ticketorderVO, List<Old_TicketVO> list) {
		old_dao.insertWithTickets(ticketorderVO, list);
	}
	public String insertTicketOrderAndUpdateTicArea(Old_TicketOrderVO ticketorderVO, SeatingAreaVO seatingareaVO) {
		return old_dao.insertTicketOrderAndUpdateTicArea(ticketorderVO, seatingareaVO);
	}
	public String cancelTicketOrderByServlet(String ticket_order_no) {
		return old_dao.cancelTicketOrderByServlet(ticket_order_no);
	}
	public void updateTicketOrderAndInsertTickets(Old_TicketOrderVO ticketorderVO2, List<Old_TicketVO> ticket2List) {
		com.ticketorder.model.Old_TicketOrderDAO dao2 = new com.ticketorder.model.Old_TicketOrderDAO();
		dao2.updateTicketOrderAndInsertTickets(ticketorderVO2, ticket2List);
	}
	//================================================== 與舊版本相容 ==================================================
	
}
