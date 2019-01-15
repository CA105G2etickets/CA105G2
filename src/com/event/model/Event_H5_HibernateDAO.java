package com.event.model;

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

import com.seating_area.model.SeatingAreaVO;
import com.seating_area.model.SeatingArea_H5_VO;

import hibernate.util.HibernateUtil;
import hibernate.util.CompositeQuery.HibernateUtil_CompositeQuery_Event;
import hibernate.util.CompositeQuery.HibernateUtil_CompositeQuery_Resale_ord;

import java.util.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class Event_H5_HibernateDAO implements Event_H5_DAO_interface {

	private static final String GET_ALL_STMT = "from Event_H5_VO order by eve_no";

	@Override
	public void insert(Event_H5_VO eve_h5VO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(eve_h5VO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void update(Event_H5_VO eve_h5VO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(eve_h5VO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void delete(String eve_no) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

//        【此時多方(宜)可採用HQL刪除】
//			Query query = session.createQuery("delete EmpVO where empno=?0");
//			query.setParameter(0, empno);
//			System.out.println("刪除的筆數=" + query.executeUpdate());

//        【或此時多方(也)可採用去除關聯關係後，再刪除的方式】
			Event_H5_VO eve_h5VO = new Event_H5_VO();
			eve_h5VO.setEve_no(eve_no);;
			session.delete(eve_h5VO);

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
	public Event_H5_VO findByPrimaryKey(String eve_no) {
		Event_H5_VO eve_h5VO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			eve_h5VO = (Event_H5_VO) session.get(Event_H5_VO.class, eve_no);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return eve_h5VO;
	}

	@Override
	public List<Event_H5_VO> getAll() {
		List<Event_H5_VO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query<Event_H5_VO> query = session.createQuery(GET_ALL_STMT, Event_H5_VO.class);
			list = query.getResultList();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}
	
	@Override
	public Set<SeatingArea_H5_VO> getSeatingAreasByEventNo(String eve_no) {
		Set<SeatingArea_H5_VO> set = this.findByPrimaryKey(eve_no).getSeatingarea_h5s();
		return set;
	}
	
	@Override
	public List<Event_H5_VO> getAll(Map<String, String[]> map, String strOrderByTargetColumnName) {
		List<Event_H5_VO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
//			list = HibernateUtil_CompositeQuery_Emp2.getAllC(map);
			list = HibernateUtil_CompositeQuery_Event.getAllC(map, strOrderByTargetColumnName);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}
	
	public List<Event_H5_VO> getTargetVOsbyColumnNameOnlyStringAndSQLINcondition(String columnName,Set<String> javaUtilSet_SQLin_Conditions) {
		List<Event_H5_VO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			
			//【●創建 CriteriaBuilder】
			   CriteriaBuilder builder = session.getCriteriaBuilder();
			//【●創建 CriteriaQuery】
			   CriteriaQuery<Event_H5_VO> criteriaQuery = builder.createQuery(Event_H5_VO.class);
			//【●創建 Root】
			   Root<Event_H5_VO> root = criteriaQuery.from(Event_H5_VO.class);
			   
			   Expression<String> ex_SeatingAreaH5_columnName = root.get(columnName);
			   Predicate predicate = ex_SeatingAreaH5_columnName.in(javaUtilSet_SQLin_Conditions);
			   criteriaQuery.where(predicate);
			   //criteriaQuery.orderBy(arg0) //add order by contidtion here
			   javax.persistence.Query jpQuery = session.createQuery(criteriaQuery);
			   list = jpQuery.getResultList();
			
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}
	
	public static void main(String[] args) {

		Event_H5_HibernateDAO dao = new Event_H5_HibernateDAO();
		
		com.venue.model.Venue_H5_VO vvo = new com.venue.model.Venue_H5_VO();
		vvo.setVenue_no("V001");
		
		com.event_title.model.EventTitle_H5_VO etvo = new com.event_title.model.EventTitle_H5_VO();
		etvo.setEvetit_no("E0001");
		
		com.ticket_type.model.TicketType_H5_VO ttyvo = new com.ticket_type.model.TicketType_H5_VO();
		ttyvo.setTictype_no("ET000002");
		
		Event_H5_VO evo = new Event_H5_VO();
		Set<SeatingArea_H5_VO> sset = new LinkedHashSet<SeatingArea_H5_VO>();
		
		SeatingArea_H5_VO svo = new SeatingArea_H5_VO();
		svo.setTicarea_color("colorT");
		svo.setTicarea_name("nameT");
		svo.setTictotalnumber(777);
		svo.setTicbookednumber(488);
		svo.setEve_h5VO(evo);
		svo.setTickettype_h5VO(ttyvo);
		
		SeatingArea_H5_VO svo2 = new SeatingArea_H5_VO();
		svo2.setTicarea_color("colorT2");
		svo2.setTicarea_name("nameT2");
		svo2.setTictotalnumber(277);
		svo2.setTicbookednumber(288);
		svo2.setEve_h5VO(evo);
		svo2.setTickettype_h5VO(ttyvo);
		
		sset.add(svo);
		sset.add(svo2);
		
		
		evo.setEve_sessionname("sessionName");
		evo.setEve_seatmap(null);
		evo.setEve_startdate(java.sql.Timestamp.valueOf("2005-01-01 01:01:01"));
		evo.setEve_enddate(java.sql.Timestamp.valueOf("2005-01-01 01:01:10"));
		evo.setEve_onsaledate(java.sql.Timestamp.valueOf("2005-01-01 01:01:02"));
		evo.setEve_offsaledate(java.sql.Timestamp.valueOf("2005-01-01 01:01:09"));
		evo.setTiclimit(7);
		evo.setFullrefundenddate(java.sql.Timestamp.valueOf("2005-01-01 01:01:09"));
		evo.setEve_status("test");
		evo.setEventtitle_h5VO(etvo);
		evo.setVenue_h5VO(vvo);
		dao.insert(evo); //success
		
		
		

		//● 修改
//		ResaleOrderVO roVO_4update = new ResaleOrderVO();
//		roVO_4update.setResale_ordno("R_20181226_000002");
//		roVO_4update.setTicketVO(ticketVO);
//		roVO_4update.setMember_seller_no(member_seller_no);; //fail		


		//● 刪除(小心cascade - 多方emp2.hbm.xml如果設為 cascade="all"或
		// cascade="delete"將會刪除所有相關資料-包括所屬部門與同部門的其它員工將會一併被刪除)
//		dao.delete("R_20181226_000002"); //success


		//● 查詢-findByPrimaryKey (多方emp2.hbm.xml必須設為lazy="false")(優!)
//		Event_H5_VO eVO_find = dao.findByPrimaryKey("EV00001"); 
//		System.out.print(eVO_find.getEve_no() + ",");
//		System.out.print(eVO_find.getEve_startdate() + ",");
//		System.out.print(eVO_find.getTiclimit() + ",");//success
//		
//		
//		System.out.print(eVO_find.getSeatingarea_h5s()+ ","); //empty
//		System.out.println("\n---------------------findbypk_done");
//		
//		//after findByPk try update
//		eVO_find.setTiclimit(7);
//		dao.update(eVO_find); //success
		
//		List<Event_H5_VO> list2 = dao.getAll();
//		for(Event_H5_VO aEventH5VO :list2) {
//			System.out.print(aEventH5VO.getEve_no() + ",");
////			System.out.print(aEventH5VO.getEve_seatmap().length + ","); //Hibernate dies here, java.lang.NullPointerException
//			System.out.println("\n-----------------");
//			Set<SeatingArea_H5_VO> set2 = aEventH5VO.getSeatingarea_h5s();
//			for(SeatingArea_H5_VO aSAH5VO : set2) {
//				System.out.print(aSAH5VO.getTicarea_no() + ",");
//				System.out.print(aSAH5VO.getEve_h5VO().getEve_no() + ",");
//				System.out.print(aSAH5VO.getEve_h5VO().getEve_status() + ",");
//				System.out.print(aSAH5VO.getTictotalnumber() + ",");
//				System.out.print(aSAH5VO.getTicbookednumber() + ",");
//			}
//		} //success
	}

	
}
