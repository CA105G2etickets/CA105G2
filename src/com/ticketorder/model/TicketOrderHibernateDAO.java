package com.ticketorder.model;

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

import com.seating_area.model.SeatingAreaVO;
import com.seating_area.model.SeatingArea_H5_Service;
import com.seating_area.model.SeatingArea_H5_VO;
import com.ticket.model.TicketVO;

import hibernate.util.HibernateUtil;
import java.util.*;

public class TicketOrderHibernateDAO implements TicketOrderDAO_interface {

	private static final String GET_ALL_STMT = "from TicketOrderVO order by ticket_order_no";
	private static final String GET_ALL_BY_MEMBERNO_STMT = "from TicketOrderVO where member_no=?0 order by ticket_order_time desc";
	

	@Override
	public void insert(TicketOrderVO ticketorderVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(ticketorderVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void update(TicketOrderVO ticketorderVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(ticketorderVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}
	
	public String updateWithCondition(TicketOrderVO ticketorderVO) {
		String str_ticketorder_no_create = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			
			session.beginTransaction();
			if(ticketorderVO.getSeatingarea_h5VO().getTictotalnumber() < ticketorderVO.getSeatingarea_h5VO().getTicbookednumber() ) {
				throw new RuntimeException();
			}
			
//			session.beginTransaction();
			session.saveOrUpdate(ticketorderVO);
			str_ticketorder_no_create = ticketorderVO.getTicket_order_no();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return str_ticketorder_no_create;
	}
//	public String insertThenGetLatestToNoWithCondition(TicketOrderVO ticketorderVO) {
//		String strtemp = null;
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		try {
//			session.beginTransaction();
//			
//			//check here, not in controller, 
//			//use ticketorderVO.getSeatingarea_h5VO().getTicarea_no() to find bookednumber and check
//			SeatingArea_H5_Service saSvc = new SeatingArea_H5_Service();
//			SeatingArea_H5_VO svo = saSvc.getOneSeatingArea_H5(ticketorderVO.getSeatingarea_h5VO().getTicarea_no());
//			svo.setTicbookednumber(svo.getTicbookednumber()+ticketorderVO.getTotal_amount());
//			
//			//check if bookednum > totalnum
//			Integer limit = svo.getTictotalnumber();
//			if(svo.getTicbookednumber() > limit) {
//				throw new RuntimeException("TicketsNotEnough");
//			}
//			ticketorderVO.setSeatingarea_h5VO(svo);
//			session.saveOrUpdate(ticketorderVO);
//			strtemp = ticketorderVO.getTicket_order_no();
//			session.getTransaction().commit();
//		} catch (RuntimeException ex) {
//			session.getTransaction().rollback();
//			throw ex;
//		}
//		return strtemp;
//	}
	
	public String insertThenGetLatestToNoWithCondition(TicketOrderVO ticketorderVO) {
		String strtemp = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			
			//check here, not in controller, 
			//use ticketorderVO.getSeatingarea_h5VO().getTicarea_no() to find bookednumber and check
//			SeatingArea_H5_Service saSvc = new SeatingArea_H5_Service();
//			SeatingArea_H5_VO svo = saSvc.getOneSeatingArea_H5(ticketorderVO.getSeatingarea_h5VO().getTicarea_no());
//			svo.setTicbookednumber(svo.getTicbookednumber()+ticketorderVO.getTotal_amount());
			
			//check if bookednum > totalnum
			Integer limit = ticketorderVO.getSeatingarea_h5VO().getTicbookednumber();
			if(ticketorderVO.getSeatingarea_h5VO().getTicbookednumber() > limit) {
				throw new RuntimeException("TicketsNotEnough");
			}
//			ticketorderVO.setSeatingarea_h5VO(svo);
			session.saveOrUpdate(ticketorderVO);
			strtemp = ticketorderVO.getTicket_order_no();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return strtemp;
	}
	
	
//	public boolean updateTargetTicketOrderAndInsertTickets(String ticket_order_no,List<TicketVO> list) {
//		TicketOrderVO ticketorderVO = null;
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		try {
//			session.beginTransaction();
//			ticketorderVO = (TicketOrderVO) session.get(TicketOrderVO.class, ticket_order_no);
//			
//			session.getTransaction().commit();
//		} catch (RuntimeException ex) {
//			session.getTransaction().rollback();
//			throw ex;
//		}
//		return false;
//	}
	

	@Override
	public void delete(String ticket_order_no) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
//			TicketOrderVO ticketorderVO = (TicketOrderVO) session.get(TicketOrderVO.class, ticket_order_no);
//			session.delete(ticketorderVO);
			TicketOrderVO toVO = new TicketOrderVO();
			toVO.setTicket_order_no(ticket_order_no);
			session.delete(toVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public TicketOrderVO findByPrimaryKey(String ticket_order_no) {
		TicketOrderVO ticketorderVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			ticketorderVO = (TicketOrderVO) session.get(TicketOrderVO.class, ticket_order_no);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return ticketorderVO;
	}

	@Override
	public List<TicketOrderVO> getAll() {
		List<TicketOrderVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query<TicketOrderVO> query = session.createQuery(GET_ALL_STMT, TicketOrderVO.class);
			list = query.getResultList();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}

	@Override
	public Set<TicketVO> getTicketsByTicketOrderNo(String ticket_order_no) {		
		Set<TicketVO>	set = findByPrimaryKey(ticket_order_no).getTickets();
		return set;
	}
	
	@Override
	public List<TicketOrderVO> getTicketOrdersByMemberNo(String member_no) {
		List<TicketOrderVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query<TicketOrderVO> query = session.createQuery(GET_ALL_BY_MEMBERNO_STMT, TicketOrderVO.class);
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
	public void cancelTargetTicketOrderByServletDueToOutDatedWithConditions(String ticket_order_no) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
//			session.beginTransaction();
			
			System.out.println("before this.findbypk");
			
			TicketOrderVO tovo = this.findByPrimaryKey(ticket_order_no);
			System.out.println("tovo address="+tovo);
			
			System.out.println("after this.findbypk,beore session.begin...");

			session.beginTransaction(); //this method die at this line
			
			System.out.println("after session.begin... before if(tovo==null)");
			
			if(tovo == null) {
				System.out.println("tovo = null");
				throw new RuntimeException("target ticket_order isn't exist");
			}
			
			System.out.println("tovo NOT NULL,before if(status check)");
			
			if(!tovo.getTicket_order_status().equals("WAITTOPAY1")) {
				System.out.println("ticketorder status is NOT waittopay");
				throw new RuntimeException("target ticket_order ISNT WAITFORPAY");
			}
			
			System.out.println("ticketorder status is waittopay");
			
			SeatingArea_H5_VO svo = tovo.getSeatingarea_h5VO();
			Integer latestSeatingAreaBookedNumber = svo.getTicbookednumber() - tovo.getTotal_amount();
			svo.setTicbookednumber(latestSeatingAreaBookedNumber);
			tovo.setTicket_order_status("OUTDATE4");
			tovo.setSeatingarea_h5VO(svo);
			session.beginTransaction();
			
			System.out.println("before .update()");
			session.update(tovo);
			
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	public static void main(String[] args) {

		TicketOrderHibernateDAO dao = new TicketOrderHibernateDAO();
		
		//sim buyTickets and add ticketorder with update seatingbookednumber.
		SeatingArea_H5_Service sSvc = new SeatingArea_H5_Service();
		SeatingArea_H5_VO svoFind = sSvc.getOneSeatingArea_H5("ES00000002");
		TicketOrderVO tovoi = new TicketOrderVO();
		tovoi.setMember_no("M000007");
		tovoi.setTotal_price(7700);
		tovoi.setTotal_amount(2);
		tovoi.setTicket_order_time(java.sql.Timestamp.valueOf("2005-01-01 01:01:01"));
		tovoi.setPayment_method("NOTYET");
		tovoi.setTicket_order_status("WAITTOPAY1");
		
		Integer originalNum = svoFind.getTicbookednumber();
		svoFind.setTicbookednumber(originalNum+2);
		tovoi.setSeatingarea_h5VO(svoFind);
		String temp=dao.updateWithCondition(tovoi);
		System.out.println("asdfasdfasdfasdfasdf"+temp);
//		dao.update(tovoi);
		
		
		//sim buyTickets update targetTicketorder and add tickets.
//		TicketOrderVO tovo1 = new TicketOrderVO();
//		com.ticketorder.model.TicketOrderService toSvc = new com.ticketorder.model.TicketOrderService();
//		tovo1 = toSvc.getOneTicketOrder("TO_20181225_000003");
//		
//		com.seating_area.model.SeatingArea_H5_VO svoNew = new com.seating_area.model.SeatingArea_H5_VO();
//		com.seating_area.model.SeatingArea_H5_Service sh5Svc = new com.seating_area.model.SeatingArea_H5_Service();
//		svoNew = sh5Svc.getOneSeatingArea_H5(tovo1.getSeatingarea_h5VO().getTicarea_no());
//		Set<TicketVO> tset = new LinkedHashSet<TicketVO>();
//		
//		TicketVO tvo1 = new TicketVO();
//		tvo1.setMember_no(tovo1.getMember_no());
//		tvo1.setTicket_status("ACTIVE1");
//		tvo1.setTicket_create_time(new java.sql.Timestamp(System.currentTimeMillis()));
//		tvo1.setTicket_resale_status("NONE1");
//		tvo1.setTicket_resale_price(0);
//		tvo1.setIs_from_resale("no");
//		tvo1.setSeatingarea_h5VO(svoNew);
//		tvo1.setTicketorderVO(tovo1);
//		
//		TicketVO tvo2 = new TicketVO();
//		tvo2.setMember_no("M000007");
//		tvo2.setTicket_status("ACTIVE1");
//		tvo2.setTicket_create_time(new java.sql.Timestamp(System.currentTimeMillis()));
//		tvo2.setTicket_resale_status("NONE1");
//		tvo2.setTicket_resale_price(0);
//		tvo2.setIs_from_resale("no");
//		tvo2.setSeatingarea_h5VO(svoNew);
//		tvo2.setTicketorderVO(tovo1);
//		
//		tset.add(tvo1);
//		tset.add(tvo2);
//		
//		tovo1.setTicket_order_status("UPDATES");
//		tovo1.setTickets(tset);
//		dao.update(tovo1);
		
//		List<TicketOrderVO> list77 = dao.getAll();
//		for(TicketOrderVO ato :list77) {
//			System.out.print(ato.getTicket_order_no() + ",");
//			System.out.println("\n----------------");
//			Set<TicketVO> set77 = ato.getTickets();
//			for(TicketVO at:set77) {
//				System.out.print(at.getTicket_no() + ",");
//				System.out.print(at.getTicket_create_time() + ",");
//				System.out.println("\n----------------");
//			}
//		}
		
		//getall
//		List<TicketOrderVO> list = dao.getAll();
//		for (TicketOrderVO aTO : list) {
//			System.out.print(aTO.getTicket_order_no() + ",");
//			System.out.print(aTO.getTicket_order_status() + ",");
//			System.out.print(aTO.getSeatingarea_h5VO().getTicarea_name() + ",");
//			System.out.println("\n-----------------");
//		}
		
		//ordinary add ticketorder with tickets success
//		TicketOrderVO tvof = dao.findByPrimaryKey("TO_20181225_000003");
//		com.seating_area.model.SeatingArea_H5_VO svo = new com.seating_area.model.SeatingArea_H5_VO();
//		svo.setTicarea_no(tvof.getSeatingarea_h5VO().getTicarea_no());
//		
//		TicketOrderVO tovo = new TicketOrderVO();
//		Set<TicketVO> set = new LinkedHashSet<TicketVO>();
//		
//		TicketVO tvo1 = new TicketVO();
//		tvo1.setMember_no("M000001");
//		tvo1.setTicket_status("ACTIVE1");
//		tvo1.setTicket_create_time(java.sql.Timestamp.valueOf("2005-01-01 01:01:01"));
//		tvo1.setTicket_resale_status("NONE1");
//		tvo1.setTicket_resale_price(0);
//		tvo1.setIs_from_resale("NO");
//		tvo1.setSeatingarea_h5VO(svo);
//		tvo1.setTicketorderVO(tovo);
//		
//		TicketVO tvo2 = new TicketVO();
//		tvo2.setMember_no("M000001");
//		tvo2.setTicket_status("ACTIVE1");
//		tvo2.setTicket_create_time(java.sql.Timestamp.valueOf("2005-01-01 01:01:01"));
//		tvo2.setTicket_resale_status("NONE1");
//		tvo2.setTicket_resale_price(0);
//		tvo2.setIs_from_resale("NO");
//		tvo2.setSeatingarea_h5VO(svo);
//		tvo2.setTicketorderVO(tovo);
//		
//		set.add(tvo1);
//		set.add(tvo2);
//		
////		tovo.setTicket_order_no("TO_20181225_000003"); //add this line to become update
//		tovo.setMember_no("M000001");
//		tovo.setTotal_price(12000);
//		tovo.setTotal_amount(2);
//		tovo.setTicket_order_time(java.sql.Timestamp.valueOf("2005-01-01 01:01:01"));
//		tovo.setPayment_method("NOTYET");
//		tovo.setTicket_order_status("WAITTOPAY1");
//		tovo.setSeatingarea_h5VO(svo);
//		tovo.setTickets(set);
//		dao.insert(tovo);
		
		
//		com.seating_area.model.SeatingArea_H5_VO sah5VO = new com.seating_area.model.SeatingArea_H5_VO();
//		sah5VO.setTicarea_no("ES00000002");
////		
//		TicketOrderVO toVO = new TicketOrderVO();
//		toVO.setMember_no("M000009");
//		toVO.setSeatingarea_h5VO(sah5VO);
//		toVO.setTotal_price(999);
//		toVO.setTotal_amount(5);
//		toVO.setTicket_order_time(java.sql.Timestamp.valueOf("2011-10-02 18:48:05.123456"));
//		toVO.setPayment_method("payment");
//		toVO.setTicket_order_status("status1");
//		dao.insert(toVO);
//		
//		List<TicketOrderVO> list2 = dao.getAll();
//		for (TicketOrderVO aTO : list2) {
//			System.out.print(aTO.getTicket_order_no() + ",");
//			System.out.print(aTO.getTicket_order_status() + ",");
//			System.out.print(aTO.getSeatingarea_h5VO().getTicarea_name() + ",");
//			System.out.println("\n-----------------");
//		}
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
		
//		TicketOrderVO toVO_find = dao.findByPrimaryKey("TO_20181225_000003");
//		System.out.print(toVO_find.getMember_no() + ",");
//		System.out.print(toVO_find.getTicket_order_status() + ",");
//		System.out.println("\n-----------------");
//		Set<TicketVO> set3 = toVO_find.getTickets(); //it's empty, why?
//		for (TicketVO aTicket : set3) {
//			System.out.print(aTicket.getTicket_no() + ",");
//			System.out.print(aTicket.getTicket_status() + ",");
//			System.out.print(aTicket.getSeatingarea_h5VO().getTickettype_h5VO().getTictype_name() + ",");
//			System.out.print(aTicket.getSeatingarea_h5VO().getTickettype_h5VO().getTictype_price() + ",");
//			System.out.println(); //success
//		}
//		
//		//after findByPk, test update.
//		toVO_find.setTicket_order_status("SetByH5");
//		dao.update(toVO_find); //success


//		List<TicketOrderVO> list2 = dao.getAll();
//		for (TicketOrderVO aTO : list2) {
//			System.out.print(aTO.getTicket_order_no() + ",");
//			System.out.print(aTO.getTicket_order_status() + ",");
//			System.out.print(aTO.getSeatingarea_h5VO().getTicarea_name() + ",");
//			System.out.println("\n-----------------");
//			Set<TicketVO> set2 = aTO.getTickets();
//			for (TicketVO aTicket : set2) {
//				System.out.print(aTicket.getTicket_no() + ",");
//				System.out.print(aTicket.getTicket_status()+ ",");
//				System.out.print(aTicket.getTicketorderVO().getTicket_order_no() + ",");
//				System.out.print(aTicket.getResaleords() + ","); // empty, but sysout shows
//				//out that it has value: [[com.resaleorder.model.ResaleOrderVO@75181b50, 
//				//com.resaleorder.model.ResaleOrderVO@2d64160c, 
//				//com.resaleorder.model.ResaleOrderVO@5f254608],]
//				
//				System.out.println(); //success
//			}
//			System.out.println();
//		}
	}

	

	
}
