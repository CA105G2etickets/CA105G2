package com.resaleorder.model;

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

import com.ticket.model.TicketVO;

import hibernate.util.HibernateUtil;
import hibernate.util.CompositeQuery.HibernateUtil_CompositeQuery_Resale_ord;

import java.util.*;

public class ResaleOrderHibernateDAO implements ResaleOrderDAO_interface {

	private static final String GET_ALL_STMT = "from ResaleOrderVO order by resale_ordno";

	@Override
	public void insert(ResaleOrderVO resaleorderVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			
			session.beginTransaction();
			session.saveOrUpdate(resaleorderVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void update(ResaleOrderVO resaleorderVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(resaleorderVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}
	
	@Override												//要改的票券物件要放在轉讓訂單物件內然後用傳值的方式來比較
	public String insertResaleOrderWithCondition(ResaleOrderVO resaleorderVO, String original_ticket_resale_status
			,Integer original_ticket_resale_price, String original_is_from_resale, 
			String wantToChangeTo_member_no) 
	{
		String strForReturn = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			if(!original_ticket_resale_status.equals("SELLING2")) {
				throw new RuntimeException("此票已非轉讓票，可能被取消轉讓，或是其他使用者已購買付款中");
			}
			
			
			session.saveOrUpdate(resaleorderVO);
			strForReturn = resaleorderVO.getResale_ordno();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return strForReturn;
	}

	@Override
	public String updateResaleOrderWithCondition(ResaleOrderVO resaleorderVO, 
			String original_ticket_resale_status,Integer original_ticket_resale_price, String original_is_from_resale, 
			String wantToChangeTo_member_no)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			
			session.beginTransaction();
			session.saveOrUpdate(resaleorderVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return null;
	}
	
	public void updateBothRoAndTo(ResaleOrderVO resaleorderVO, TicketVO ticketVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(resaleorderVO);
			session.saveOrUpdate(ticketVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void delete(String resale_ordno) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

//        【此時多方(宜)可採用HQL刪除】
//			Query query = session.createQuery("delete EmpVO where empno=?0");
//			query.setParameter(0, empno);
//			System.out.println("刪除的筆數=" + query.executeUpdate());

//        【或此時多方(也)可採用去除關聯關係後，再刪除的方式】
			ResaleOrderVO roVO = new ResaleOrderVO();
			roVO.setResale_ordno(resale_ordno);;
			session.delete(roVO);

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
	public ResaleOrderVO findByPrimaryKey(String resale_ordno) {
		ResaleOrderVO resaleorderVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			resaleorderVO = (ResaleOrderVO) session.get(ResaleOrderVO.class, resale_ordno);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return resaleorderVO;
	}

	@Override
	public List<ResaleOrderVO> getAll() {
		List<ResaleOrderVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query<ResaleOrderVO> query = session.createQuery(GET_ALL_STMT, ResaleOrderVO.class);
			list = query.getResultList();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}

	@Override
	public List<ResaleOrderVO> getAll(Map<String, String[]> map, String strOrderByTargetColumnName) {
		List<ResaleOrderVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			list = HibernateUtil_CompositeQuery_Resale_ord.getAllC(map,strOrderByTargetColumnName);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}
	
	@Override
	public String insertOneResaleOrderAndUpdateTargetTicketToBuying(ResaleOrderVO resaleorderVO, String ticket_no) {
		String str = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			com.ticket.model.TicketService tSvc = new com.ticket.model.TicketService();
			TicketVO ticketVO = tSvc.getOneTicket(ticket_no);
			if("NONE1".equals(ticketVO.getTicket_resale_status())) {
				throw new RuntimeException("賣家已取消此轉售票，因此您的操作失敗");
			}
			if("CHECKING3".equals(ticketVO.getTicket_resale_status())) {
				throw new RuntimeException("已有人買走並繳費中，因此您的操作失敗");
			}
			
			session.beginTransaction();
			ticketVO.setTicket_resale_status("CHECKING3");
			resaleorderVO.setTicketVO(ticketVO);
			
			session.saveOrUpdate(resaleorderVO);
			str = resaleorderVO.getResale_ordno();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return str;
	}
	
	@Override
	//wrong logic, resaleticket comes from tickets, resaleorder is like order not product
	public ResaleOrderVO getLatestAndSellingROByTicketNo(String ticket_no) {
		List<ResaleOrderVO> list = null;
		ResaleOrderVO rvo = new ResaleOrderVO();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query<ResaleOrderVO> query = session.createQuery("from ResaleOrderVO where ticket_no="+ticket_no+" order by resale_ord_createtime", ResaleOrderVO.class);
			list = query.getResultList();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		
		return list.get(1);
	}
	
	@Override
	public String insertAndGetReturnPK(ResaleOrderVO resaleorderVO) {
		String str = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(resaleorderVO);
			str = resaleorderVO.getResale_ordno();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return str;
	}
	
	public static void main(String[] args) {

		ResaleOrderHibernateDAO dao = new ResaleOrderHibernateDAO();
		
		//● 新增
//		com.dept.model.DeptVO deptVO = new com.dept.model.DeptVO(); // 部門POJO
//		deptVO.setDeptno(10);

//		EmpVO empVO1 = new EmpVO();
//		empVO1.setEname("吳永志1");
//		empVO1.setJob("MANAGER");
//		empVO1.setHiredate(java.sql.Date.valueOf("2005-01-01"));
//		empVO1.setSal(new Double(50000));
//		empVO1.setComm(new Double(500));
//		empVO1.setDeptVO(deptVO);
//		dao.insert(empVO1);
		
//		ResaleOrderVO rvo = dao.findByPrimaryKey("R_20181226_000001");
//		ResaleOrderVO rvo = dao.findByPrimaryKey("asdfasdfasdf");
//		if(rvo == null) {
//			System.out.println(rvo);
//		}else {
//			System.out.println("hsdfasdfjlaisdjflisjeflijselfhjil");
//		}
		
		
		
		com.ticket.model.TicketVO ticketVO = new com.ticket.model.TicketVO();
		com.ticket.model.TicketService tSvc = new com.ticket.model.TicketService();
		ticketVO = tSvc.getOneTicket("T_20181225_000002");
		ticketVO.setTicket_resale_status("updateS");
		
		ResaleOrderVO rvo = new ResaleOrderVO();
		rvo.setMember_buyer_no("M000009");
		rvo.setMember_seller_no("M000001");
		rvo.setResale_ordprice(777);
		rvo.setResale_ordstatus("WAITFORPAY1");
		rvo.setResale_ord_createtime(new java.sql.Timestamp(System.currentTimeMillis()));
		rvo.setResale_ord_completetime(null);
		rvo.setPayment_method("NOTYET");
		rvo.setTicketVO(ticketVO);
		dao.update(rvo);
		
//		com.ticketorder.model.TicketOrderVO tovo = new com.ticketorder.model.TicketOrderVO();
//		tovo.setTicket_order_no("TO_20181225_000002");
		
//		com.seating_area.model.SeatingArea_H5_VO svo = new com.seating_area.model.SeatingArea_H5_VO();
//		svo.setTicarea_no("ES00000002");
		
//		ticketVO.setSeatingarea_h5VO(svo);
//		ticketVO.setTicketorderVO(tovo);
		
//		ResaleOrderVO resaleorderVO = new ResaleOrderVO();
//		resaleorderVO.setMember_buyer_no("");
//		resaleorderVO.setMember_seller_no("M000001");
//		resaleorderVO.setPayment_method("TEST");
//		resaleorderVO.setResale_ord_completetime(null);
//		resaleorderVO.setResale_ord_createtime(java.sql.Timestamp.valueOf("2005-01-01 01:01:01"));
//		resaleorderVO.setResale_ordprice(0);
//		resaleorderVO.setResale_ordstatus("SELLING1");
//		resaleorderVO.setTicketVO(ticketVO);
//		dao.insert(resaleorderVO); //success
		

		//● 修改
//		ResaleOrderVO roVO_4update = new ResaleOrderVO();
//		roVO_4update.setResale_ordno("R_20181226_000002");
//		roVO_4update.setTicketVO(ticketVO);
//		roVO_4update.setMember_seller_no(member_seller_no);; //fail		


		//● 刪除(小心cascade - 多方emp2.hbm.xml如果設為 cascade="all"或
		// cascade="delete"將會刪除所有相關資料-包括所屬部門與同部門的其它員工將會一併被刪除)
//		dao.delete("R_20181226_000002"); //success


		//● 查詢-findByPrimaryKey (多方emp2.hbm.xml必須設為lazy="false")(優!)
//		ResaleOrderVO roVO_find = dao.findByPrimaryKey("R_20181226_000003"); //success
//		System.out.print(roVO_find.getResale_ordno() + ",");
//		System.out.print(roVO_find.getMember_seller_no() + ",");
//		System.out.print(roVO_find.getResale_ord_createtime()+ ",");
//		
//		System.out.print(roVO_find.getTicketVO().getTicket_no()+ ",");
//		System.out.print(roVO_find.getTicketVO().getTicket_resale_price()+ ",");
//		System.out.println("\n---------------------findbypk_done");
		
		//after findByPk try update
//		roVO_find.setResale_ordstatus("changedByH5");
//		dao.update(roVO_find); //success


//		Map<String, String[]> map = new TreeMap<String, String[]>(); //success
////		map.put("resale_ordstatus", new String[] { "CA" });
//		//● 查詢-getAll (多方emp2.hbm.xml必須設為lazy="false")(優!)
////		List<ResaleOrderVO> list = dao.getAll();
//		List<ResaleOrderVO> list = dao.getAll(map);
//		for (ResaleOrderVO aRO : list) {
//			System.out.print(aRO.getResale_ordno() + ",");
//			System.out.print(aRO.getMember_seller_no() + ",");
//			System.out.print(aRO.getMember_buyer_no() + ",");
//			System.out.print(aRO.getResale_ordprice() + ",");
//			System.out.print(aRO.getResale_ordstatus() + ",");
//			System.out.print(aRO.getResale_ord_createtime() + ",");
//			System.out.print(aRO.getResale_ord_completetime() + ",");
//			System.out.print(aRO.getPayment_method() + ",");
//			
//			// 注意以下三行的寫法 (優!)
//			System.out.print(aRO.getTicketVO().getTicket_no()+",");
//			System.out.print(aRO.getTicketVO().getTicket_status()+ ",");
//			System.out.println();
//		}
		
		
		//Hibernate: select resaleorde0_.resale_ordno as resale_ordno1_0_, resaleorde0_.ticket_no as ticket_no2_0_, resaleorde0_.member_seller_no as member_seller_no3_0_, resaleorde0_.member_buyer_no as member_buyer_no4_0_,resaleorde0_.resale_ordprice as resale_ordprice5_0_, resaleorde0_.resale_ordstatus as resale_ordstatus6_0_, resaleorde0_.resale_ord_createtime as resale_ord_createt7_0_, resaleorde0_.resale_ord_completetime as resale_ord_complet8_0_, resaleorde0_.payment_method as payment_method9_0_ from resale_ord resaleorde0_ where resaleorde0_.ticket_no like ? order by resaleorde0_.resale_ordno asc
		//Hibernate: select resaleorde0_.resale_ordno as resale_ordno1_0_, resaleorde0_.ticket_no as ticket_no2_0_, resaleorde0_.member_seller_no as member_seller_no3_0_, resaleorde0_.member_buyer_no as member_buyer_no4_0_,resaleorde0_.resale_ordprice as resale_ordprice5_0_, resaleorde0_.resale_ordstatus as resale_ordstatus6_0_, resaleorde0_.resale_ord_createtime as resale_ord_createt7_0_, resaleorde0_.resale_ord_completetime as resale_ord_complet8_0_, resaleorde0_.payment_method as payment_method9_0_ from resale_ord resaleorde0_ where resaleorde0_.resale_ordstatus like ? order by resaleorde0_.resale_ordno asc
		
	}

	
}
