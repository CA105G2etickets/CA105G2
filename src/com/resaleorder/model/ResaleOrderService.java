package com.resaleorder.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.ticket.model.TicketHibernateDAO;
import com.ticket.model.TicketService;
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
		
		//this logic might fail because update ticketvo with only content of ticket_no.
//		TicketVO ticketVO = new TicketVO();
//		ticketVO.setTicket_no(ticket_no);
		TicketService tSvc = new TicketService();
		TicketVO ticketVO = tSvc.getOneTicket(ticket_no);
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
	
	public String addResaleOrdAndUpdateTicketVOResaleAttributes(String ticket_no, String member_seller_no, String member_buyer_no,
			Integer resale_ordprice, String resale_ordstatus, java.sql.Timestamp resale_ord_createtime, 
			java.sql.Timestamp resale_ord_completetime, String payment_method,
			String ticketOwnerMember_no, String changeTo_ticket_resale_status, Integer changeTo_ticket_resale_price,String changeTo_is_from_resale ) 
	{
		
		ResaleOrderVO resaleorderVO = new ResaleOrderVO();
		
		TicketService tSvc = new TicketService();
		TicketVO ticketVO = tSvc.getOneTicket(ticket_no);
		
		//先取出目標票券物件內的三個轉讓屬性的值並設為參數準備送到DAO方法內進行判斷
		String original_ticket_resale_status = ticketVO.getTicket_resale_status();
		Integer original_ticket_resale_price = ticketVO.getTicket_resale_price();
		String original_is_from_resale = ticketVO.getIs_from_resale();
		
		//get a latest ticketvo as I could and set in resaleorder for dao to compare.
		//要改的票券物件要放在轉讓訂單物件內然後用傳值的方式來比較因此上面那行註解應該是錯的
		ticketVO.setMember_no(ticketOwnerMember_no);
		ticketVO.setTicket_resale_status(changeTo_ticket_resale_status);
		ticketVO.setTicket_resale_price(changeTo_ticket_resale_price);
		ticketVO.setIs_from_resale(changeTo_is_from_resale);
		
		resaleorderVO.setTicketVO(ticketVO);
		
		resaleorderVO.setMember_seller_no(member_seller_no);
		resaleorderVO.setMember_buyer_no(member_buyer_no);
		resaleorderVO.setResale_ordprice(resale_ordprice);
		resaleorderVO.setResale_ordstatus(resale_ordstatus);
		resaleorderVO.setResale_ord_createtime(resale_ord_createtime);
		resaleorderVO.setResale_ord_completetime(resale_ord_completetime);
		resaleorderVO.setPayment_method(payment_method);

		String resale_ordno = dao.insertResaleOrderWithCondition(resaleorderVO, original_ticket_resale_status , original_ticket_resale_price, original_is_from_resale, ticketOwnerMember_no);

		return resale_ordno;
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
	
	public String insertAndGetReturnPK(ResaleOrderVO resaleorderVO) {
		String strReturn = null;
		strReturn = dao.insertAndGetReturnPK(resaleorderVO);
		return strReturn;
	}
	
	public String insertOneResaleOrderAndUpdateTargetTicketToBuying( String member_seller_no, String member_buyer_no, 
			Integer resale_ordprice, String resale_ordstatus, java.sql.Timestamp resale_ord_createtime, 
			java.sql.Timestamp resale_ord_completetime, String payment_method, String ticket_no) {
		
		ResaleOrderVO resaleorderVO = new ResaleOrderVO();
		
		resaleorderVO.setMember_seller_no(member_seller_no);
		resaleorderVO.setMember_buyer_no(member_buyer_no);
		resaleorderVO.setResale_ordprice(resale_ordprice);
		resaleorderVO.setResale_ordstatus(resale_ordstatus);
		resaleorderVO.setResale_ord_createtime(resale_ord_createtime);
		resaleorderVO.setResale_ord_completetime(resale_ord_completetime);
		resaleorderVO.setPayment_method(payment_method);
		dao.insertOneResaleOrderAndUpdateTargetTicketToBuying(resaleorderVO, ticket_no);

		return resaleorderVO.getResale_ordno();
	}
	
	public String updateTargetTicketResaleAttributesAndMaybeInsertOneResaleOrder(String resale_ordno, String member_seller_no, 
			String member_buyer_no, Integer resale_ordprice, String resale_ordstatus, java.sql.Timestamp resale_ord_createtime, 
			java.sql.Timestamp resale_ord_completetime, String payment_method, boolean ticketUpdateOnly, boolean updateResaleOrNot, 
			String ticket_resale_status, Integer ticket_resale_price,String is_from_resale, String ticket_no) {
		
		String StrForReturn = null;
		if(ticketUpdateOnly) {
			TicketHibernateDAO th5DAO = new TicketHibernateDAO();
			TicketVO tvo = th5DAO.findByPrimaryKey(ticket_no);
			tvo.setTicket_resale_status(ticket_resale_status);
	    	tvo.setTicket_resale_price(ticket_resale_price);
	    	tvo.setIs_from_resale(is_from_resale);
	    	th5DAO.update(tvo);
	    	StrForReturn=tvo.getTicket_no();
		}else {
			if(updateResaleOrNot) {
				
				TicketHibernateDAO th5DAO = new TicketHibernateDAO();
				TicketVO tvo = th5DAO.findByPrimaryKey(ticket_no);
				tvo.setTicket_resale_status(ticket_resale_status);
		    	tvo.setTicket_resale_price(ticket_resale_price);
		    	tvo.setIs_from_resale(is_from_resale);
				
				ResaleOrderVO resaleorderVO = new ResaleOrderVO();
				//make sure new ticketvo empty only with resale_attributes can update or not, nope cant you have to ticketSvc.findbypk
				resaleorderVO.setResale_ordno(resale_ordno);
				resaleorderVO.setMember_seller_no(member_seller_no);
				resaleorderVO.setMember_buyer_no(member_buyer_no);
				resaleorderVO.setResale_ordprice(resale_ordprice);
				resaleorderVO.setResale_ordstatus(resale_ordstatus);
				resaleorderVO.setResale_ord_createtime(resale_ord_createtime);
				resaleorderVO.setResale_ord_completetime(resale_ord_completetime);
				resaleorderVO.setPayment_method(payment_method);
				resaleorderVO.setTicketVO(tvo);
				dao.update(resaleorderVO);

				StrForReturn= resaleorderVO.getResale_ordno();
				
			}else {
				ResaleOrderVO resaleorderVO = new ResaleOrderVO();
				
				resaleorderVO.setMember_seller_no(member_seller_no);
				resaleorderVO.setMember_buyer_no(member_buyer_no);
				resaleorderVO.setResale_ordprice(resale_ordprice);
				resaleorderVO.setResale_ordstatus(resale_ordstatus);
				resaleorderVO.setResale_ord_createtime(resale_ord_createtime);
				resaleorderVO.setResale_ord_completetime(resale_ord_completetime);
				resaleorderVO.setPayment_method(payment_method);
				dao.insertOneResaleOrderAndUpdateTargetTicketToBuying(resaleorderVO, ticket_no);

				StrForReturn= resaleorderVO.getResale_ordno();
			}
		}
		
		//return resale_ordno or ticket_no, only useful at resale_ordno because its a latest seq.
		return StrForReturn;
		}
	
	
	public List<ResaleOrderVO> getAll(Map<String, String[]> map, String strOrderByTargetColumnName) {
		return dao.getAll(map,strOrderByTargetColumnName);
	}
	public void updateBothRoAndTo(ResaleOrderVO resaleorderVO, TicketVO ticketVO) {
		dao.updateBothRoAndTo(resaleorderVO, ticketVO);
	}
}
