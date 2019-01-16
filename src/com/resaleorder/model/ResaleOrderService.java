package com.resaleorder.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.ticket.model.TicketVO;

public class ResaleOrderService {
	
	private ResaleOrderDAO_interface dao;
	
	public ResaleOrderService() {
		dao = new ResaleOrderHibernateDAO();
	}
	public ResaleOrderVO addResaleOrd(String ticket_no, String member_seller_no, String member_buyer_no,
			Integer resale_ordprice, String resale_ordstatus, java.sql.Timestamp resale_ord_createtime, 
			java.sql.Timestamp resale_ord_completetime, String payment_method) {
		
		ResaleOrderVO resaleorderVO = new ResaleOrderVO();
		
		TicketVO ticketVO = new TicketVO();
		ticketVO.setTicket_no(ticket_no);
		resaleorderVO.setTicketVO(ticketVO);
		
		resaleorderVO.setMember_seller_no(member_seller_no);
		resaleorderVO.setMember_buyer_no(member_buyer_no);
		resaleorderVO.setResale_ordprice(resale_ordprice);
		resaleorderVO.setResale_ordstatus(resale_ordstatus);
		resaleorderVO.setResale_ord_createtime(resale_ord_createtime);
		resaleorderVO.setResale_ord_completetime(resale_ord_completetime);
		resaleorderVO.setPayment_method(payment_method);

		dao.insert(resaleorderVO);

		return resaleorderVO;
	}

	public ResaleOrderVO updateResaleOrd(String resale_ordno, String ticket_no, String member_seller_no, String member_buyer_no,
			Integer resale_ordprice, String resale_ordstatus, java.sql.Timestamp resale_ord_createtime, 
			java.sql.Timestamp resale_ord_completetime, String payment_method) {

		ResaleOrderVO resaleorderVO = new ResaleOrderVO();
		
		resaleorderVO.setResale_ordno(resale_ordno);
		
		TicketVO ticketVO = new TicketVO();
		ticketVO.setTicket_no(ticket_no);
		resaleorderVO.setTicketVO(ticketVO);
		
		resaleorderVO.setMember_seller_no(member_seller_no);
		resaleorderVO.setMember_buyer_no(member_buyer_no);
		resaleorderVO.setResale_ordprice(resale_ordprice);
		resaleorderVO.setResale_ordstatus(resale_ordstatus);
		resaleorderVO.setResale_ord_createtime(resale_ord_createtime);
		resaleorderVO.setResale_ord_completetime(resale_ord_completetime);
		resaleorderVO.setPayment_method(payment_method);

		dao.update(resaleorderVO);

		return resaleorderVO;
	}

	public void deleteResaleOrd(String resale_ordno) {
		dao.delete(resale_ordno);
	}

	public ResaleOrderVO getOneResaleOrd(String resale_ordno) {
		return dao.findByPrimaryKey(resale_ordno);
	}

	public List<ResaleOrderVO> getAll() {
		return dao.getAll();
	}
	
	public void insert(ResaleOrderVO resaleorderVO) {
		dao.insert(resaleorderVO);
	}
	
	public List<ResaleOrderVO> getAll(Map<String, String[]> map, String strOrderByTargetColumnName) {
		return dao.getAll(map,strOrderByTargetColumnName);
	}
}
