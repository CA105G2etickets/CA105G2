package com.GOODS.model;

import java.util.List;



public class GoodsService {
	private GoodsDAO_interface dao;

	public GoodsService() {
		dao = new GoodsDAO();
	}

	public GoodsVO addGoods(String ename, String job, java.sql.Date hiredate, Double sal, Double comm, Integer deptno) {

		GoodsVO GoodsVO = new GoodsVO();

		GoodsVO.setGoods_no(goods_no);
		GoodsVO.setJob(job);
		GoodsVO.setHiredate(hiredate);
		GoodsVO.setSal(sal);
		GoodsVO.setComm(comm);
		GoodsVO.setDeptno(deptno);
		dao.insert(GoodsVO);

		return GoodsVO;
	}

	// 預留給 Struts 2 用的
	public void addEmp(GoodsVO GoodsVO) {
		dao.insert(GoodsVO);
	}

	public GoodsVO updateEmp(Integer empno, String ename, String job, java.sql.Date hiredate, Double sal, Double comm,
			Integer deptno) {

		GoodsVO GoodsVO = new GoodsVO();

		GoodsVO.setEmpno(empno);
		GoodsVO.setEname(ename);
		GoodsVO.setJob(job);
		GoodsVO.setHiredate(hiredate);
		GoodsVO.setSal(sal);
		GoodsVO.setComm(comm);
		GoodsVO.setDeptno(deptno);
		dao.update(GoodsVO);

		return dao.findByPrimarykey(empno);
	}

	// 預留給 Struts 2 用的
	public void updateGoods(GoodsVO GoodsVO) {
		dao.update(GoodsVO);
	}

	public void deleteGoods(Integer empno) {
		dao.delete(empno);
	}

	public GoodsVO getOneEmp(Integer empno) {
		return dao.findByPrimarykey(empno);
	}

	public List<GoodsVO> getAll() {
		return dao.getAll();
	}
}
