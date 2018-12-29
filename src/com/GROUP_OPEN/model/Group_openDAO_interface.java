package com.GROUP_OPEN.model;

import java.util.List;

public interface Group_openDAO_interface {
	
	public void add(Group_openVO Group_open);
	public void update(Group_openVO Group_open);
	public void delete(String group_no);
	public Group_openVO findByPrimaryKey(String group_no);
	public List <Group_openVO> getAll();

}
