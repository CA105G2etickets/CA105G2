package com.ticket.model;

/*
 Hibernate is providing a factory.getCurrentSession() method for retrieving the current session. A
 new session is opened for the first time of calling this method, and closed when the transaction is
 finished, no matter commit or rollback. But what does it mean by the “current session”? We need to
 tell Hibernate that it should be the session bound with the current thread.

 <hibernate-configuration>
 <session-factory>
 ...
 <property name="current_session_context_class">thread</property>
 ...
 </session-factory>
 </hibernate-configuration>

 */

import org.hibernate.*;
import org.hibernate.query.Query; //Hibernate 5.2 開始 取代原 org.hibernate.Query 介面

import com.resaleorder.model.ResaleOrderVO;

import hibernate.util.HibernateUtil;
import hibernate.util.CompositeQuery.HibernateUtil_CompositeQuery_Ticket;

import java.util.*;

public class TicketHibernateDAO implements TicketDAO_interface {

	private static final String GET_ALL_STMT = "from TicketVO order by ticket_no";
	private static final String GET_ALL_BY_MEMBERNO_STMT = "from TicketVO where member_no=?0 order by ticket_no";
	private static final String GET_ALL_BY_TONO_AND_MEMBERNO_STMT = "from TicketVO where ticket_order_no=?0 and member_no=?1 order by ticket_no";
	
	@Override
	public void insert(TicketVO ticketVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(ticketVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void update(TicketVO ticketVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(ticketVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void delete(String ticket_no) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

//        【此時多方(宜)可採用HQL刪除】
//			Query query = session.createQuery("delete EmpVO where empno=?0");
//			query.setParameter(0, empno);
//			System.out.println("刪除的筆數=" + query.executeUpdate());

//        【或此時多方(也)可採用去除關聯關係後，再刪除的方式】
			TicketVO tVO = new TicketVO();
			tVO.setTicket_no(ticket_no);
			session.delete(tVO);

//        【此時多方不可(不宜)採用cascade聯級刪除】
//        【多方emp2.hbm.xml如果設為 cascade="all"或 cascade="delete"將會刪除所有相關資料-包括所屬部門與同部門的其它員工將會一併被刪除】
//			EmpVO empVO = (EmpVO) session.get(EmpVO.class, empno);
//			session.delete(empVO);

			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public TicketVO findByPrimaryKey(String ticket_no) {
		TicketVO ticketVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			ticketVO = (TicketVO) session.get(TicketVO.class, ticket_no);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return ticketVO;
	}

	@Override
	public List<TicketVO> getAll() {
		List<TicketVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query<TicketVO> query = session.createQuery(GET_ALL_STMT, TicketVO.class);
			list = query.getResultList();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}
	
	@Override
	public List<TicketVO> getTicketsByMemberNo(String member_no) {
		List<TicketVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query<TicketVO> query = session.createQuery(GET_ALL_BY_MEMBERNO_STMT, TicketVO.class);
			query.setParameter(0, member_no);
			list = query.getResultList();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}
	
	@Override
	public List<TicketVO> getTicketsByTicketOrderNoAndMemberNo(String ticket_order_no, String member_no) {
		List<TicketVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query<TicketVO> query = session.createQuery(GET_ALL_BY_TONO_AND_MEMBERNO_STMT, TicketVO.class);
			query.setParameter(0, ticket_order_no);
			query.setParameter(1, member_no);
			list = query.getResultList();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}


	
//	public TicketVO findOneAndUpdateItsStatusToUsed(String ticket_no) {
//		TicketVO ticketVO = null;
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		try {
//			session.beginTransaction();
//			ticketVO = (TicketVO) session.get(TicketVO.class, ticket_no);
//			ticketVO.setTicket_status("USED2");
////			session.getTransaction().commit();
//		} catch (RuntimeException ex) {
//			session.getTransaction().rollback();
//			throw ex;
//		}
//		return ticketVO;
//	}
	
	@Override
	public Set<ResaleOrderVO> getResaleOrderByTicketsNo(String ticket_no) {
		Set<ResaleOrderVO> set = this.findByPrimaryKey(ticket_no).getResaleords();
		return set;
	}
	
	//compositiom search
	public List<TicketVO> getAll_map(Map<String, String[]> map,String strOrderByTargetColumnName) {
		List<TicketVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			list = HibernateUtil_CompositeQuery_Ticket.getAllC(map,strOrderByTargetColumnName);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}
	
//	@Override
//	public void InsertTicketsAndUpdateTargetTO(List<TicketVO> list, String ticket_order_no) {
////		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
////		try {
////			session.beginTransaction();
////			
////			com.ticketorder.model.TicketOrderService toSvc = new com.ticketorder.model.TicketOrderService();
////			com.ticketorder.model.TicketOrderVO tovo = new com.ticketorder.model.TicketOrderVO();
////			com.seating_area.model.SeatingArea_H5_VO svo = new com.seating_area.model.SeatingArea_H5_VO();
////			svo.setTicarea_no(tovo.getSeatingarea_h5VO().getTicarea_no());
////			tovo = toSvc.getOneTicketOrder(ticket_order_no);
////			for(TicketVO atvo:list) {
//////				TicketVO tvo = new TicketVO();
//////				tvo.setMember_no(tovo.getMember_no());
//////				tvo.setTicket_status("ACTIVE1");
//////				tvo.setTicket_create_time(new java.sql.Timestamp(System.currentTimeMillis()));
//////				tvo.setTicket_resale_status("NONE1");
//////				tvo.setTicket_resale_price(0);
//////				tvo.setIs_from_resale("NO");
//////				tvo.setTicketorderVO(tovo);
//////				tvo.setSeatingarea_h5VO(svo);
////				session.saveOrUpdate(atvo);
////			}
////			
////			
////			
////			session.getTransaction().commit();
////		} catch (RuntimeException ex) {
////			session.getTransaction().rollback();
////			throw ex;
////		}
////		
//	}
	
	//fail
	public void InsertTicketsAndUpdateTargetTOVO(Integer ticketsCount, String ticket_order_no) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			
			com.ticketorder.model.TicketOrderService toSvc = new com.ticketorder.model.TicketOrderService();
			com.ticketorder.model.TicketOrderVO tovo = new com.ticketorder.model.TicketOrderVO();
			com.seating_area.model.SeatingArea_H5_VO svo = new com.seating_area.model.SeatingArea_H5_VO();
			svo.setTicarea_no(tovo.getSeatingarea_h5VO().getTicarea_no());
			tovo = toSvc.getOneTicketOrder(ticket_order_no);
			tovo.setTicket_order_status("COMPLETE2");
			for(int i = 1;i<=ticketsCount;i++) {
				TicketVO tvo = new TicketVO();
				tvo.setMember_no(tovo.getMember_no());
				tvo.setTicket_status("ACTIVE1");
				tvo.setTicket_create_time(new java.sql.Timestamp(System.currentTimeMillis()));
				tvo.setTicket_resale_status("NONE1");
				tvo.setTicket_resale_price(0);
				tvo.setIs_from_resale("NO");
				tvo.setTicketorderVO(tovo);
				tvo.setSeatingarea_h5VO(svo);
				session.saveOrUpdate(tvo);
			}
			
			
			
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		
	}

	public static void main(String[] args) {

		TicketHibernateDAO dao = new TicketHibernateDAO();
		
//		dao.InsertTicketsAndUpdateTargetTOVO(3, "TO_20181225_000003");
		
		com.ticketorder.model.TicketOrderVO tovo = new com.ticketorder.model.TicketOrderVO();
		com.ticketorder.model.TicketOrderService toSvc = new com.ticketorder.model.TicketOrderService();
		tovo = toSvc.getOneTicketOrder("TO_20181225_000003");
		tovo.setTicket_order_status("up_sta");
		
		com.seating_area.model.SeatingArea_H5_VO svo = new com.seating_area.model.SeatingArea_H5_VO();
		svo.setTicarea_no(tovo.getSeatingarea_h5VO().getTicarea_no());
		
		TicketVO tvo = new TicketVO();
		tvo.setMember_no(tovo.getMember_no());
		tvo.setTicket_status("T1");
		tvo.setTicket_create_time(java.sql.Timestamp.valueOf("2007-09-23 10:10:10.0"));
		tvo.setTicket_resale_status("rSta");
		tvo.setTicket_resale_price(10);
		tvo.setIs_from_resale("no");
		tvo.setTicketorderVO(tovo);
		tvo.setSeatingarea_h5VO(svo);
		
		dao.insert(tvo); //success
		
//		TicketVO tvo2 = new TicketVO();
//		tvo2.setMember_no("M000007");
//		tvo2.setTicket_status("T1");
//		tvo2.setTicket_create_time(java.sql.Timestamp.valueOf("2007-09-23 10:10:10.0"));
//		tvo2.setTicket_resale_status("rSta");
//		tvo2.setTicket_resale_price(10);
//		tvo2.setIs_from_resale("no");
//		tvo2.setTicketorderVO(tovo);
//		tvo2.setSeatingarea_h5VO(svo);
		
//		List<TicketVO> list = new LinkedList<TicketVO>();
//		list.add(tvo);
//		list.add(tvo2);
//		
//		for(list)
		
		
		
		
		
		//● 修改
//		TicketVO tVO_update = new TicketVO();
//		tVO_update.setTicket_no("T_20181225_000003");
//		tVO_update.setSeatingarea_h5VO(seatingarea_h5VO);
//		tVO_update.setTicketorderVO(ticketorderVO);
//		
//		
//		empVO2.setEmpno(7001);
//		empVO2.setEname("吳永志2");
//		empVO2.setJob("MANAGER2");
//		empVO2.setHiredate(java.sql.Date.valueOf("2002-01-01"));
//		empVO2.setSal(new Double(20000));
//		empVO2.setComm(new Double(200));
//		empVO2.setDeptVO(deptVO);
//		dao.update(empVO2); //fail


		//● 刪除(小心cascade - 多方emp2.hbm.xml如果設為 cascade="all"或
		// cascade="delete"將會刪除所有相關資料-包括所屬部門與同部門的其它員工將會一併被刪除)
//		dao.delete("T_20181225_000001"); //success


		
//		TicketVO tVO_find = dao.findByPrimaryKey("T_20181225_000002");
//		System.out.println(tVO_find.getTicket_no());
//		System.out.println(tVO_find.getTicketorderVO().getTicket_order_no());

		//● 查詢-findByPrimaryKey (多方emp2.hbm.xml必須設為lazy="false")(優!)
//		TicketVO tVO = dao.findByPrimaryKey("T_20181225_000002");
//		System.out.print(tVO.getTicket_no());
//		System.out.print(tVO.getTicket_status());
//		// 注意以下三行的寫法 (優!)
//		System.out.print(tVO.getSeatingarea_h5VO().getTickettype_h5VO().getTictype_name() + ",");
//		System.out.print(tVO.getTicketorderVO().getTotal_price() + ","); //
//		System.out.print(tVO.getResaleords()); // success this line, but it's '[]' empty
//		System.out.println("\n---------------------");
		
//		tVO.setTicket_status("SetbyH5");
//		dao.update(tVO); //success



		//● 查詢-getAll (多方emp2.hbm.xml必須設為lazy="false")(優!)
//		List<TicketVO> list = dao.getAll();
//		for (TicketVO aTicket : list) {
//			System.out.print(aTicket.getTicket_no() + ",");
//			System.out.print(aTicket.getTicket_status() + ",");
//			
//			// 注意以下三行的寫法 (優!)
//			System.out.print(aTicket.getTicketorderVO().getTicket_order_no()+ ",");
//			System.out.print(aTicket.getTicketorderVO().getTicket_order_status()+ ",");
//			System.out.print(aTicket.getSeatingarea_h5VO().getTicbookednumber()+ ",");
//			System.out.print(list.size()+"---"+list.size());
//			System.out.println(); // success
//			
//		}
		
	}

	
	
	
}
