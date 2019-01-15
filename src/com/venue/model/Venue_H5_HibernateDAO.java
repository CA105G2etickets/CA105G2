package com.venue.model;

/*
 Hibernate is providing a factory.getCurrentSession() method for retrieving the current session. A
 new session is opened for the first time of calling this method, and closed when the transaction is
 finished, no matter commit or rollback. But what does it mean by the ��current session��? We need to
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
import org.hibernate.query.Query; //Hibernate 5.2 �}�l ���N�� org.hibernate.Query ����
import hibernate.util.HibernateUtil;
import java.util.*;
import com.event.model.Event_H5_VO;

public class Venue_H5_HibernateDAO implements Venue_H5_DAO_interface {

	private static final String GET_ALL_STMT = "from Venue_H5_VO order by venue_no";

	@Override
	public void insert(Venue_H5_VO venue_h5VO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(venue_h5VO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void update(Venue_H5_VO venue_h5VO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(venue_h5VO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void delete(String venue_no) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Venue_H5_VO venueVO = (Venue_H5_VO) session.get(Venue_H5_VO.class, venue_no);
			session.delete(venueVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public Venue_H5_VO findByPrimaryKey(String venue_no) {
		Venue_H5_VO venueVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			venueVO = (Venue_H5_VO) session.get(Venue_H5_VO.class, venue_no);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return venueVO;
	}

	@Override
	public List<Venue_H5_VO> getAll() {
		List<Venue_H5_VO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query<Venue_H5_VO> query = session.createQuery(GET_ALL_STMT, Venue_H5_VO.class);
			list = query.getResultList();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}

	@Override
	public Set<Event_H5_VO> getEventsByVenueNo(String venue_no) {		
		Set<Event_H5_VO>	set = findByPrimaryKey(venue_no).getEvent_h5s();
		return set;
	}

	public static void main(String[] args) {

		Venue_H5_HibernateDAO dao = new Venue_H5_HibernateDAO();

		Venue_H5_VO vVO = new Venue_H5_VO();
		Set<Event_H5_VO> set = new LinkedHashSet<Event_H5_VO>();
		
		
//		Event_H5_VO evo = new Event_H5_VO();
//		evo.setEventtitle_h5VO(eventtitle_h5VO);
		
		
		
//		vVO.setVenue_name("venueName");
//		vVO.setAddress("address");
//		vVO.setLatitude(20.25);
//		vVO.setLongitude(10.25);
//		vVO.setVenue_info("venue info");
//		dao.insert(vVO); //success
		
		
		
		//�� �d��-findByPrimaryKey (�u�q!) (�@��dept2.hbm.xml�����]��lazy="false")
//		Venue_H5_VO deptVO3 = dao.findByPrimaryKey("V001")
//		System.out.print(deptVO3.getDeptno() + ",");
//		System.out.print(deptVO3.getDname() + ",");
//		System.out.print(deptVO3.getLoc());
//		System.out.println("\n-----------------");
//		Set<EmpVO> set3 = deptVO3.getEmps();
//		for (EmpVO aEmp : set3) {
//			System.out.print(aEmp.getEmpno() + ",");
//			System.out.print(aEmp.getEname() + ",");
//			System.out.print(aEmp.getJob() + ",");
//			System.out.print(aEmp.getHiredate() + ",");
//			System.out.print(aEmp.getSal() + ",");
//			System.out.print(aEmp.getComm() + ",");
//			System.out.print(aEmp.getDeptVO().getDeptno() + ",");
//			System.out.print(aEmp.getDeptVO().getDname() + ",");
//			System.out.print(aEmp.getDeptVO().getLoc());
//			System.out.println();
//		}



		//�� �d��-getAll-1 (�@��dept2.hbm.xml���γ]��lazy="false",�]���S�Ψ�h�誺����)
//		List<DeptVO> list1 = dao.getAll();
//		for (DeptVO aDept : list1) {
//			System.out.print(aDept.getDeptno() + ",");
//			System.out.print(aDept.getDname() + ",");
//			System.out.print(aDept.getLoc());
//			System.out.println();
//		}


		List<Venue_H5_VO> list = dao.getAll();
		for(Venue_H5_VO aV:list) {
			System.out.println(aV.getAddress());
			System.out.println(aV.getVenue_name());
			System.out.println(aV.getVenue_no());
		}

		//�� �d��-getAll-2 (�u�q!!!) (�@��dept2.hbm.xml�����]��lazy="false")
//		List<Venue_H5_VO> list2 = dao.getAll();
//		for (Venue_H5_VO aVH5VO : list2) {
//			System.out.print(aVH5VO.getAddress() + ",");
//			System.out.print(aVH5VO.getVenue_name() + ",");
//			System.out.println("\n-----------------");
//			Set<Event_H5_VO> set2 = aVH5VO.getEvent_h5s();
//			
//			//there's 2 events in db, so though the venue only have one, these codes output 2 results.
//			for (Event_H5_VO aEH5VO : set2) {
//				System.out.print(aEH5VO.getEve_no() + ",");
//				System.out.print(aEH5VO.getEve_offsaledate() + ",");
//				System.out.print(aEH5VO.getTiclimit() + ",");
//				System.out.print(aEH5VO.getVenue_h5VO().getAddress() + ",");
//				System.out.print(aEH5VO.getVenue_h5VO().getVenue_no() + ",");
////				System.out.print(aEH5VO.getEventtitle_h5VO() + ",");
//				System.out.println();
//			}
//			System.out.println();
//		}

	}
}
