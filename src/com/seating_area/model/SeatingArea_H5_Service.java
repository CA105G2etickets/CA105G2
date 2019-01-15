package com.seating_area.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.event.model.Event_H5_VO;

public class SeatingArea_H5_Service {

	private SeatingArea_H5_DAO_interface dao;

	public SeatingArea_H5_Service() {
		dao = new SeatingArea_H5_HibernateDAO();
	}
	public SeatingArea_H5_VO getOneSeatingArea_H5(String ticarea_no) {
		return dao.findByPrimaryKey(ticarea_no);
	}

	public List<SeatingArea_H5_VO> getAll() {
		return dao.getAll();
	}
	
	public List<SeatingArea_H5_VO> getAll(Map<String, String[]> map, String strOrderByTargetColumnName) {
		return dao.getAll(map, strOrderByTargetColumnName);
	}
	
	public List<SeatingArea_H5_VO> getTargetVOsbyColumnNameOnlyStringAndSQLINcondition(String columnName,Set<String> javaUtilSet_SQLin_Conditions) {
		return dao.getTargetVOsbyColumnNameOnlyStringAndSQLINcondition(columnName, javaUtilSet_SQLin_Conditions);
		
	}
	
}
