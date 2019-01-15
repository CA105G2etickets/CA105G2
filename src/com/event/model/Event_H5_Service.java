package com.event.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Event_H5_Service {

	private Event_H5_DAO_interface dao;

	public Event_H5_Service() {
		dao = new Event_H5_HibernateDAO();
	}

	//disable cause Bear might don't need it
//	public Event_H5_VO add(String evetit_no, String venue_no, String eve_sessionname,
//			Double sal, Double comm, Integer deptno) {
//		
//		EmpVO empVO = new EmpVO();
//		empVO.setEname(ename);
//		empVO.setJob(job);
//		empVO.setHiredate(hiredate);
//		empVO.setSal(sal);
//		empVO.setComm(comm);
//		DeptVO deptVO = new DeptVO();
//		deptVO.setDeptno(deptno);
//		empVO.setDeptVO(deptVO);
//
//		dao.insert(empVO);
//
//		return empVO;
//	}

	//disable cause Bear might don't need it
//	public EmpVO updateEmp(Integer empno, String ename, String job,
//			java.sql.Date hiredate, Double sal, Double comm, Integer deptno) {
//
//		EmpVO empVO = new EmpVO();
//		empVO.setEmpno(empno);
//		empVO.setEname(ename);
//		empVO.setJob(job);
//		empVO.setHiredate(hiredate);
//		empVO.setSal(sal);
//		empVO.setComm(comm);
//		DeptVO deptVO = new DeptVO();
//		deptVO.setDeptno(deptno);
//		empVO.setDeptVO(deptVO);
//
//		dao.update(empVO);
//
//		return empVO;
//	}

	//disable cause Bear might don't need it
//	public void deleteEmp(Integer empno) {
//		dao.delete(empno);
//	}

	public Event_H5_VO getOneEvent_H5(String eve_no) {
		return dao.findByPrimaryKey(eve_no);
	}

	public List<Event_H5_VO> getAll() {
		return dao.getAll();
	}
	
	public List<Event_H5_VO> getAll(Map<String, String[]> map, String strOrderByTargetColumnName) {
		return dao.getAll(map, strOrderByTargetColumnName);
	}
	
	public List<Event_H5_VO> getTargetVOsbyColumnNameOnlyStringAndSQLINcondition(String columnName,Set<String> javaUtilSet_SQLin_Conditions){
		return dao.getTargetVOsbyColumnNameOnlyStringAndSQLINcondition(columnName, javaUtilSet_SQLin_Conditions);
	}
}
