package com.event_title.model;

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

public class EventTitle_H5_HibernateDAO implements EventTitle_H5_DAO_interface {

	private static final String GET_ALL_STMT = "from EventTitle_H5_VO order by evetit_no";

	@Override
	public void insert(EventTitle_H5_VO eventtitle_h5VO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(eventtitle_h5VO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void update(EventTitle_H5_VO eventtitle_h5VO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(eventtitle_h5VO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void delete(String evetit_no) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
//			EventTitle_H5_VO eventtitle_h5VO = (EventTitle_H5_VO) session.get(EventTitle_H5_VO.class, evetit_no);
//			session.delete(eventtitle_h5VO);
			EventTitle_H5_VO etVO = new EventTitle_H5_VO();
			session.delete(etVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public EventTitle_H5_VO findByPrimaryKey(String evetit_no) {
		EventTitle_H5_VO eventtitle_h5VO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			eventtitle_h5VO = (EventTitle_H5_VO) session.get(EventTitle_H5_VO.class, evetit_no);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return eventtitle_h5VO;
	}

	@Override
	public List<EventTitle_H5_VO> getAll() {
		List<EventTitle_H5_VO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query<EventTitle_H5_VO> query = session.createQuery(GET_ALL_STMT, EventTitle_H5_VO.class);
			list = query.getResultList();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}

	@Override
	public Set<Event_H5_VO> getEventH5sByEvetitNo(String evetit_no) {		
		Set<Event_H5_VO>	set = findByPrimaryKey(evetit_no).getEvent_h5s();
		return set;
	}

	public static void main(String[] args) {

		EventTitle_H5_HibernateDAO dao = new EventTitle_H5_HibernateDAO();

//		//�� �s�W-1(�@��dept2.hbm.xml������cascade="save-update" ��cascade="all"���]�w)(���M�j�j,���L��ȤW�ä��`��)(��,�i�Φb�q��D�ɻP�����ɤ@���s�W���\)
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



		//
//		dao.delete("E0004"); //fail



		//�� �s�W-2(���ݭncascade="save-update" �� cascade="all"���]�w)(�o�O�g�`�n�Ψ쪺�@��s�W)
//		DeptVO deptVO = new DeptVO(); // ����POJO
//		deptVO.setDname("�s�y��s");
//		deptVO.setLoc("���ꦿ��s");
//		dao.insert(deptVO);



		//�� �d��-findByPrimaryKey (�u�q!) (�@��dept2.hbm.xml�����]��lazy="false")
//		EventTitle_H5_VO etVO3 = dao.findByPrimaryKey("E0002");
//		System.out.print(etVO3.getEvetit_name() + ",");
//		System.out.print(etVO3.getEvetit_status() + ",");
//		System.out.print(etVO3.getEvetit_startdate() + ",");
//		System.out.println("\n-----------------");
//		Set<Event_H5_VO> set3 = etVO3.getEvent_h5s();
//		for (Event_H5_VO aE : set3) {
//			System.out.print(aE.getEve_no() + ",");
//			System.out.print(aE.getEve_sessionname() + ",");
//			System.out.print(aE.getTiclimit() + ",");
//			System.out.println();
//		}// success
//		
//		//use etVO3 to update
//		etVO3.setEvetit_status("setByH5");
//		dao.update(etVO3); //success



		//�� �d��-getAll-1 (�@��dept2.hbm.xml���γ]��lazy="false",�]���S�Ψ�h�誺����)
//		List<DeptVO> list1 = dao.getAll();
//		for (DeptVO aDept : list1) {
//			System.out.print(aDept.getDeptno() + ",");
//			System.out.print(aDept.getDname() + ",");
//			System.out.print(aDept.getLoc());
//			System.out.println();
//		}



		//�� �d��-getAll-2 (�u�q!!!) (�@��dept2.hbm.xml�����]��lazy="false")
		List<EventTitle_H5_VO> list2 = dao.getAll();
		for (EventTitle_H5_VO aET : list2) {
			System.out.print(aET.getEvetit_name() + ",");
			System.out.print(aET.getEvetit_status() + ",");
			System.out.println("\n-----------------");
			Set<Event_H5_VO> set2 = aET.getEvent_h5s();
			for (Event_H5_VO aE : set2) {
				System.out.print(aE.getEve_no() + ",");
				System.out.print(aE.getEve_startdate() + ",");
				System.out.print(aE.getEventtitle_h5VO().getEvetit_name() + ",");
				System.out.println();
			}
			System.out.println();
		}//success
		//Hibernate: select eventtitle0_.evetit_no as evetit_no1_1_, eventtitle0_.eveclass_no as eveclass_no2_1_, eventtitle0_.ticrefpolicy_no as ticrefpolicy_no3_1_, eventtitle0_.evetit_name as evetit_name4_1_, eventtitle0_.evetit_startdate as evetit_startdate5_1_, eventtitle0_.evetit_enddate as evetit_enddate6_1_, eventtitle0_.evetit_poster as evetit_poster7_1_, eventtitle0_.info as info8_1_, eventtitle0_.notices as notices9_1_, eventtitle0_.eticpurchaserules as eticpurchaserules10_1_, eventtitle0_.eticrules as eticrules11_1_, eventtitle0_.refundrules as refundrules12_1_, eventtitle0_.evetit_sessions as evetit_sessions13_1_, eventtitle0_.refunevetit_statusdrules as refunevetit_statu14_1_, eventtitle0_.launchdate as launchdate15_1_, eventtitle0_.offdate as offdate16_1_, eventtitle0_.promotionranking as promotionranking17_1_ from event_title eventtitle0_ order by eventtitle0_.evetit_no
		//22:19:53,578 ERROR SqlExceptionHelper:131 - ORA-00904: "EVENTTITLE0_"."REFUNEVETIT_STATUSDRULES": invalid identifier
	}
}
