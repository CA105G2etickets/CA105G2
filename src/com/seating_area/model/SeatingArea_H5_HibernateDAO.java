package com.seating_area.model;

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
import org.hibernate.criterion.Order;
import org.hibernate.query.Query; //Hibernate 5.2 �}�l ���N�� org.hibernate.Query ����

import com.event.model.Event_H5_VO;
import com.seating_area.model.SeatingAreaVO;
import com.ticket.model.TicketVO;
import com.ticketorder.model.TicketOrderVO;

import hibernate.util.HibernateUtil;
import hibernate.util.CompositeQuery.HibernateUtil_CompositeQuery_Event;
import hibernate.util.CompositeQuery.HibernateUtil_CompositeQuery_Seating_Area;

import java.util.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class SeatingArea_H5_HibernateDAO implements SeatingArea_H5_DAO_interface {

	private static final String GET_ALL_STMT = "from SeatingArea_H5_VO order by ticarea_no";
	private static final String GET_ALL_BY_EVE_NO_STMT = "from SeatingArea_H5_VO where eve_no=?0 order by ticarea_no";

	@Override
	public void insert(SeatingArea_H5_VO seatingarea_h5VO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(seatingarea_h5VO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void update(SeatingArea_H5_VO seatingarea_h5VO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(seatingarea_h5VO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void delete(String ticarea_no) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
//			TicketOrderVO ticketorderVO = (TicketOrderVO) session.get(TicketOrderVO.class, ticket_order_no);
//			session.delete(ticketorderVO);
			SeatingArea_H5_VO sah5VO = new SeatingArea_H5_VO();
			sah5VO.setTicarea_no(ticarea_no);
			session.delete(sah5VO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public SeatingArea_H5_VO findByPrimaryKey(String ticarea_no) {
		SeatingArea_H5_VO seatingarea_h5VO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			seatingarea_h5VO = (SeatingArea_H5_VO) session.get(SeatingArea_H5_VO.class, ticarea_no);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return seatingarea_h5VO;
	}

	@Override
	public List<SeatingArea_H5_VO> getAll() {
		List<SeatingArea_H5_VO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query<SeatingArea_H5_VO> query = session.createQuery(GET_ALL_STMT, SeatingArea_H5_VO.class);
			list = query.getResultList();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}

	@Override
	public List<SeatingArea_H5_VO> getAll(Map<String, String[]> map, String strOrderByTargetColumnName) {
		List<SeatingArea_H5_VO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
//			list = HibernateUtil_CompositeQuery_Emp2.getAllC(map);
			list = HibernateUtil_CompositeQuery_Seating_Area.getAllC(map, strOrderByTargetColumnName);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}
	
	public List<SeatingArea_H5_VO> getTargetVOsbyColumnNameOnlyStringAndSQLINcondition(String columnName,Set<String> javaUtilSet_SQLin_Conditions) {
		List<SeatingArea_H5_VO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
//			Query<SeatingArea_H5_VO> query = session.createQuery(GET_ALL_STMT, SeatingArea_H5_VO.class);
//			list = query.getResultList();
			
			//【●創建 CriteriaBuilder】
			   CriteriaBuilder builder = session.getCriteriaBuilder();
			//【●創建 CriteriaQuery】
			   CriteriaQuery<SeatingArea_H5_VO> criteriaQuery = builder.createQuery(SeatingArea_H5_VO.class);
			//【●創建 Root】
			   Root<SeatingArea_H5_VO> root = criteriaQuery.from(SeatingArea_H5_VO.class);
			   
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
	
//	@Override
//	public Set<TicketVO> getTicketsByTicAreaNo(String ticarea_no) {
//		Set<TicketVO> set = findByPrimaryKey(ticarea_no).getTickets();
//		return set;
//	}

	@Override
	public Set<TicketOrderVO> getTicketOrdersByTicAreaNo(String ticarea_no) {
		Set<TicketOrderVO> set = findByPrimaryKey(ticarea_no).getTicketorders();
		return set;
	}
	
	@Override
	public List<SeatingArea_H5_VO> get_SeatingArea_H5_VOs_ByEveNo(String eve_no) {
		List<SeatingArea_H5_VO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query<SeatingArea_H5_VO> query = session.createQuery(GET_ALL_BY_EVE_NO_STMT, SeatingArea_H5_VO.class);
			query.setParameter("eve_no", eve_no);
			list = query.getResultList();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	} 

	public static void main(String[] args) {

		SeatingArea_H5_HibernateDAO dao = new SeatingArea_H5_HibernateDAO();

		
		com.event.model.Event_H5_VO evo = new com.event.model.Event_H5_VO();
		evo.setEve_no("EV00001");
		com.ticket_type.model.TicketType_H5_VO ttyvo = new com.ticket_type.model.TicketType_H5_VO();
		ttyvo.setTictype_no("ET000002");
		
		SeatingArea_H5_VO svo = new SeatingArea_H5_VO();
		Set<TicketOrderVO> setto = new LinkedHashSet<TicketOrderVO>();
		
//		com.ticket.model.TicketVO tvo = new com.ticket.model.TicketVO();
//		tvo.setTicket_no("T_20181225_000001");
		
		TicketOrderVO tovo = new TicketOrderVO();
		tovo.setMember_no("M000001");
		tovo.setPayment_method("payT");
		tovo.setTicket_order_status("statusT");
		tovo.setTicket_order_time(java.sql.Timestamp.valueOf("2005-01-01 01:01:01"));
		tovo.setTotal_amount(7);
		tovo.setTotal_price(7777);
		tovo.setSeatingarea_h5VO(svo); //this line might be the problem of all insert.
		
		TicketOrderVO tovo2 = new TicketOrderVO();
		tovo2.setMember_no("M000002");
		tovo2.setPayment_method("pay2");
		tovo2.setTicket_order_status("status2");
		tovo2.setTicket_order_time(java.sql.Timestamp.valueOf("2002-01-01 01:01:01"));
		tovo2.setTotal_amount(2);
		tovo2.setTotal_price(2777);
		tovo.setSeatingarea_h5VO(svo); //this line might be the problem of all insert.
		setto.add(tovo);
		setto.add(tovo2);
		
		svo.setTicarea_color("colorT");
		svo.setTicarea_name("nameT");
		svo.setTictotalnumber(777);
		svo.setTicbookednumber(488);
		svo.setEve_h5VO(evo);
		svo.setTickettype_h5VO(ttyvo);
		dao.insert(svo); //success
		
		
		//�� �s�W-1(�@��dept2.hbm.xml������cascade="save-update" ��cascade="all"���]�w)(���M�j�j,���L��ȤW�ä��`��)(��,�i�Φb�q��D�ɻP�����ɤ@���s�W���\)
//		DeptVO deptVO = new DeptVO(); // ����POJO
//		Set<EmpVO> set = new LinkedHashSet<EmpVO>();// �ǳƸm�J���u�ƤH,�H�Kcascade="save-update"������
//
//		EmpVO empXX = new EmpVO();   // ���uPOJO1
//		empXX.setEname("�d15");
//		empXX.setJob("MANAGER15");
//		empXX.setHiredate(java.sql.Date.valueOf("2001-01-15"));
//		empXX.setSal(new Double(15000));
//		empXX.setComm(new Double(150));
//		empXX.setDeptVO(deptVO);
//
//		EmpVO empYY = new EmpVO();   // ���uPOJO2
//		empYY.setEname("�d16");
//		empYY.setJob("MANAGER16");
//		empYY.setHiredate(java.sql.Date.valueOf("2001-01-16"));
//		empYY.setSal(new Double(16000));
//		empYY.setComm(new Double(160));
//		empYY.setDeptVO(deptVO);
//
//		set.add(empXX);
//		set.add(empYY);
//
//		deptVO.setDname("�s�y��");
//		deptVO.setLoc("���ꦿ��");
//		deptVO.setEmps(set);
//		dao.insert(deptVO);



		//�� �ק�-1(�@��dept2.hbm.xml������cascade="save-update" �� cascade="all"���]�w)(���M�j�j,���L��ȤW�ä��`��)(��,�i�����p�ϥΤ�)
//		DeptVO deptVO = new DeptVO(); // ����POJO
//		Set<EmpVO> set = new LinkedHashSet<EmpVO>(); // �ǳƸm�J���u�ƤH,�H�Kcascade="save-update"������
//
//		EmpVO empXX = new EmpVO(); // ���uPOJO1
//		empXX.setEmpno(7015); // �i�p�G�W�[ empXX.setEmpno(7015); �h�ܦ�update�j
//		empXX.setEname("�d��15");
//		empXX.setJob("MANAGER15");
//		empXX.setHiredate(java.sql.Date.valueOf("2001-01-15"));
//		empXX.setSal(new Double(15555));
//		empXX.setComm(new Double(155));
//		empXX.setDeptVO(deptVO);
//
//		EmpVO empYY = new EmpVO(); // ���uPOJO2
//		empYY.setEmpno(7016); // �i�p�G�W�[ empXX.setEmpno(7016); �h�ܦ�update�j
//		empYY.setEname("�d��16");
//		empYY.setJob("MANAGER16");
//		empYY.setHiredate(java.sql.Date.valueOf("2001-01-16"));
//		empYY.setSal(new Double(16666));
//		empYY.setComm(new Double(166));
//		empYY.setDeptVO(deptVO);
//
//		set.add(empXX);
//		set.add(empYY);
//
//		deptVO.setDeptno(50); // �i�p�G�W�[deptVO.setDeptno(50); �h�ܦ�update�j
//		deptVO.setDname("�s�y��1");
//		deptVO.setLoc("���ꦿ��1");
//		deptVO.setEmps(set);
//		dao.update(deptVO);



		//�� �ק�-2(���ݳ]�wcascade="save-update" �� cascade="all")(�o�O�g�`�n�Ψ쪺�@��ק�)
//		DeptVO deptVO2 = new DeptVO(); // ����POJO
//		deptVO2.setDeptno(50); // �i�p�G�W�[deptVO.setDeptno(50); �h�ܦ�update�j
//		deptVO2.setDname("�s�y��2");
//		deptVO2.setLoc("���ꦿ��2");
//		dao.update(deptVO2);



		//���R�� (�W�űj�j!�p�ߨϥ�!)(�@��dept2.hbm.xml������cascade="delete" �� cascade="all"���]�w, �A�[�Winverse="true"�]�w)
//		dao.delete(50);



		//�� �s�W-2(���ݭncascade="save-update" �� cascade="all"���]�w)(�o�O�g�`�n�Ψ쪺�@��s�W)
//		DeptVO deptVO = new DeptVO(); // ����POJO
//		deptVO.setDname("�s�y��s");
//		deptVO.setLoc("���ꦿ��s");
//		dao.insert(deptVO);



//		List<SeatingArea_H5_VO> list2 = dao.getAll();
//		for (SeatingArea_H5_VO aSH5 : list2) {
//			System.out.print(aSH5.getTicarea_no() + ",");
//			System.out.print(aSH5.getEve_h5VO().getEve_sessionname() + ",");
//			System.out.print(aSH5.getTickettype_h5VO().getTictype_price() + ",");
//			System.out.println("\n-----------------");
//		}

		//�� �d��-getAll-2 (�u�q!!!) (�@��dept2.hbm.xml�����]��lazy="false")
//		List<SeatingArea_H5_VO> list2 = dao.getAll();
//		for (SeatingArea_H5_VO aSH5 : list2) {
//			System.out.print(aSH5.getTicarea_no() + ",");
//			System.out.print(aSH5.getTicbookednumber() + ",");
//			System.out.println("\n-----------------");
////			System.out.print(aSH5.getEve_h5VO().getEve_sessionname()+ ",");
////			System.out.print(aSH5.getTickettype_h5VO().getTictype_name()+ ",");
//			
//			//ticket
//			Set<TicketVO> set2 = aSH5.getTickets();
//			for (TicketVO aTicket : set2) {
//				System.out.print(aTicket.getTicket_no() + ",");
//				System.out.print(aTicket.getTicket_status()+ ",");
//				System.out.print(aTicket.getSeatingarea_h5VO().getTicarea_no() + ",");
//				System.out.print(aTicket.getSeatingarea_h5VO().getTicarea_name() + ",");
//				System.out.println();
//			}
//			System.out.println();
//			
//			//ticketorder
//			Set<TicketOrderVO> set3 = aSH5.getTicketorders();
//			for (TicketOrderVO aTO : set3) {
//				System.out.print(aTO.getTicket_order_no() + ",");
//				System.out.print(aTO.getSeatingarea_h5VO().getTicarea_no() + ",");
//				System.out.print(aTO.getSeatingarea_h5VO().getTicarea_name() + ",");
//				System.out.println();
//			}
//			System.out.println();
//		} //success
		
	}

	
	
}
