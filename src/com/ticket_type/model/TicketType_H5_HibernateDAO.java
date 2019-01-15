package com.ticket_type.model;

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
import com.seating_area.model.SeatingArea_H5_VO;

public class TicketType_H5_HibernateDAO implements TicketType_H5_DAO_interface {

	private static final String GET_ALL_STMT = "from TicketType_H5_VO order by tictype_no";

	@Override
	public void insert(TicketType_H5_VO tickettype_h5VO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(tickettype_h5VO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void update(TicketType_H5_VO tickettype_h5VO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(tickettype_h5VO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void delete(String tictype_no) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
//			TicketType_H5_VO deptVO = (TicketType_H5_VO) session.get(TicketType_H5_VO.class, tictype_no);
//			session.delete(deptVO);
			TicketType_H5_VO ttyh5VO = new TicketType_H5_VO();
			ttyh5VO.setTictype_no(tictype_no);
			session.delete(ttyh5VO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public TicketType_H5_VO findByPrimaryKey(String tictype_no) {
		TicketType_H5_VO tickettype_h5VO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			tickettype_h5VO = (TicketType_H5_VO) session.get(TicketType_H5_VO.class, tictype_no);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return tickettype_h5VO;
	}

	@Override
	public List<TicketType_H5_VO> getAll() {
		List<TicketType_H5_VO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query<TicketType_H5_VO> query = session.createQuery(GET_ALL_STMT, TicketType_H5_VO.class);
			list = query.getResultList();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}
	@Override
	public Set<SeatingArea_H5_VO> getSeatingAreah5sByTicketTypeNo(String tictype_no) {
		Set<SeatingArea_H5_VO> set = this.findByPrimaryKey(tictype_no).getSeatingarea_h5s();
		return set;
	}
	
	public static void main(String[] args) {

		TicketType_H5_HibernateDAO dao = new TicketType_H5_HibernateDAO();

		TicketType_H5_VO ttyvo = new TicketType_H5_VO();
//		Set<SeatingArea_H5_VO> set = new LinkedHashSet<SeatingArea_H5_VO>();
		
//		SeatingArea_H5_VO svo = new SeatingArea_H5_VO();
//		svo.setTicarea_no("");
//		svo.setEve_h5VO(eve_h5VO);
		
		ttyvo.setEve_no("EV00001");
		ttyvo.setTictype_color("#777777");
		ttyvo.setTictype_name("tictypeN");
		ttyvo.setTictype_price(999);
		dao.insert(ttyvo);
		
		
//		TicketType_H5_VO ttyVO_find = dao.findByPrimaryKey("ES00000002");
////		System.out.println(ttyVO_find.getEve_no());
//		System.out.println(ttyVO_find.getTictype_name());
//		System.out.println(ttyVO_find.getTictype_price());
//		
//		Set<SeatingArea_H5_VO> set_find = ttyVO_find.getSeatingarea_h5s();
//		for(SeatingArea_H5_VO asah5 : set_find) {
//			System.out.println(asah5.getTictotalnumber());
//			System.out.println(asah5.getTicarea_name());
//			System.out.println(asah5.getTickettype_h5VO().getTictype_name());
//		}
		
//		TicketType_H5_VO ttyVO = new TicketType_H5_VO(); // ����POJO
//		Set<SeatingArea_H5_VO> set = new LinkedHashSet<SeatingArea_H5_VO>();
//
//		SeatingArea_H5_VO sag5VO01 = new SeatingArea_H5_VO();
//		sag5VO01.setEve_no("EV00001");
//		sag5VO01.setTickettype_h5VO(ttyVO);
//		sag5VO01.setTicarea_color("#EE7700");
//		sag5VO01.setTicarea_name("name01");
//		sag5VO01.setTictotalnumber(150);
//		sag5VO01.setTicbookednumber(5);
//		
//
//		SeatingArea_H5_VO sag5VO02 = new SeatingArea_H5_VO();
//		sag5VO02.setEve_no("EV00001");
//		sag5VO02.setTickettype_h5VO(ttyVO);
//		sag5VO02.setTicarea_color("#EE7700");
//		sag5VO02.setTicarea_name("name02");
//		sag5VO02.setTictotalnumber(130);
//		sag5VO02.setTicbookednumber(15);
//
//		set.add(sag5VO01);
//		set.add(sag5VO02);
//		
//		ttyVO.setEve_no("EV00001");
//		ttyVO.setTictype_color("EE7700");
//		ttyVO.setTictype_name("ttyname");
//		ttyVO.setTictype_price(7770);
//		dao.insert(ttyVO);
		
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



		//�� �d��-findByPrimaryKey (�u�q!) (�@��dept2.hbm.xml�����]��lazy="false")
//		DeptVO deptVO3 = dao.findByPrimaryKey(30);
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



		//�� �d��-getAll-2 (�u�q!!!) (�@��dept2.hbm.xml�����]��lazy="false")
//		List<TicketType_H5_VO> list2 = dao.getAll();
//		for (TicketType_H5_VO aTty : list2) {
//			System.out.print(aTty.getTictype_no() + ",");
//			System.out.print(aTty.getEve_no() + ",");
//			System.out.print(aTty.getTictype_color() + ",");
//			System.out.print(aTty.getTictype_name() + ",");
//			System.out.print(aTty.getTictype_price() + ",");
//			System.out.println("\n-----------------");
//			Set<SeatingArea_H5_VO> set2 = aTty.getSeatingarea_h5s();
//			for (SeatingArea_H5_VO aSAH5 : set2) {
//				System.out.print(aSAH5.getTicarea_no() + ",");
//				System.out.print(aSAH5.getTickettype_h5VO().getTictype_no() + ",");
//				System.out.print(aSAH5.getTickettype_h5VO().getTictype_name() + ",");
//				System.out.print(aSAH5.getTictotalnumber() + ",");
//				System.out.print(aSAH5.getTicbookednumber() + ",");
//				System.out.println("every asah5 done");
//			}
//			System.out.println();
//		} //success
		
	}
}
