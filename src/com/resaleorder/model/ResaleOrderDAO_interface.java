package com.resaleorder.model;
import java.util.List;
import java.util.Map;

import com.ticket.model.TicketVO;

public interface ResaleOrderDAO_interface {
	public void insert(ResaleOrderVO resaleorderVO);
    public void update(ResaleOrderVO resaleorderVO);
    public void delete(String resale_ordno);
    public ResaleOrderVO findByPrimaryKey(String resale_ordno);
    public List<ResaleOrderVO> getAll();
    
    public List<ResaleOrderVO> getAll(Map<String, String[]> map, String strOrderByTargetColumnName);
    public ResaleOrderVO getLatestAndSellingROByTicketNo(String ticket_no);
    public void updateBothRoAndTo(ResaleOrderVO resaleorderVO, TicketVO ticketVO);
    public String insertAndGetReturnPK(ResaleOrderVO resaleorderVO);
}
