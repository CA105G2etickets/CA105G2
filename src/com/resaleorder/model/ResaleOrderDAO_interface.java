package com.resaleorder.model;
import java.util.List;

public interface ResaleOrderDAO_interface {
	public void insert(ResaleOrderVO resaleorderVO);
    public void update(ResaleOrderVO resaleorderVO);
    public void delete(String resale_ordno);
    public ResaleOrderVO findByPrimaryKey(String resale_ordno);
    public List<ResaleOrderVO> getAll();	

}
