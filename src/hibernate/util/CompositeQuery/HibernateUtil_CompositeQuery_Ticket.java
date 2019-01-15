/*
 *  1. �U�νƦX�d��-�i�ѫȤ���H�N�W�����Q�d�ߪ����
 *  2. ���F�קK�v�T�į�:
 *        �ҥH�ʺA���͸U��SQL������,���d�ҵL�N�ĥ�MetaData���覡,�]�u�w��ӧO��Table�ۦ���ݭn�ӭӧO�s�@��
 * */

package hibernate.util.CompositeQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import hibernate.util.HibernateUtil;
import java.util.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery; //Hibernate 5.2 �}�l ���N�� org.hibernate.Criteria ����
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Predicate;
import javax.persistence.Query; //Hibernate 5 �}�l ���N�� org.hibernate.Query ����

import com.ticket.model.*;
import com.seating_area.model.*;
import com.ticket_type.model.*;
import com.ticketorder.model.TicketOrderVO;
import com.event.model.*;
import com.event_title.model.*;
import com.resaleorder.model.ResaleOrderVO;

public class HibernateUtil_CompositeQuery_Ticket {

	public static Predicate get_aPredicate_For_AnyDB(CriteriaBuilder builder, Root<TicketVO> root, 
			String columnName, String value) {

		/* SQL COMMAND
		 * Restrictions.eq:=
		 * Restrictions.gt:>
		 * Restrictions.ge:>=
		 * Restrictions.le:<=
		 * Restrictions.between:SQL's BETWEEN
		 * Restrictions.like:SQL's LIKE
		 * Restrictions.in:SQL's IN
		 * */
		
		Predicate predicate = null;
		if ("ticket_resale_price".equals(columnName)) // for Integer
			{
//			predicate = builder.equal(root.get(columnName), new Integer(value));
			predicate = builder.le(root.get(columnName), new Integer(value));
			}
		else if ("sal".equals(columnName) || "comm".equals(columnName)) //original use to fit Double, not used this time.
			{
			predicate = builder.equal(root.get(columnName), new Double(value));
			}
		else if ("is_from_resale".equals(columnName) || "ticket_resale_status".equals(columnName) 
				|| "ticket_status".equals(columnName) || "member_no".equals(columnName) 
				|| "ticket_no".equals(columnName)) // for varchar2
			{
			predicate = builder.like(root.get(columnName), "%" + value + "%");
			}
		else if ("ticket_create_time".equals(columnName)) // change to Timestamp
//			predicate = builder.equal(root.get(columnName), java.sql.Date.valueOf(value));
			{
			predicate = builder.equal(root.get(columnName), java.sql.Timestamp.valueOf(value));
			}
		else if ("ticarea_no".equals(columnName)) {
			TicketVO ticketVO = new TicketVO();
			ticketVO.setTicket_no(value);  
			predicate = builder.equal(root.get("ticketVO"),ticketVO); //success
		}
		else if ("ticket_order_no".equals(columnName)) {
			TicketOrderVO ticketorderVO = new TicketOrderVO();
			ticketorderVO.setTicket_order_no(value);;  
			predicate = builder.equal(root.get("ticketorderVO"),ticketorderVO); //success
		}
		return predicate;
	}

	@SuppressWarnings("unchecked")
	public static List<TicketVO> getAllC(Map<String, String[]> map, String strOrderByTargetColumnName) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		List<TicketVO> list = null;
		try {
			// �i���Ы� CriteriaBuilder�j
			CriteriaBuilder builder = session.getCriteriaBuilder();
			// �i���Ы� CriteriaQuery�j
			CriteriaQuery<TicketVO> criteriaQuery = builder.createQuery(TicketVO.class);
			// �i���Ы� Root�j
			Root<TicketVO> root = criteriaQuery.from(TicketVO.class);

			List<Predicate> predicateList = new ArrayList<Predicate>();
			
			Set<String> keys = map.keySet();
			int count = 0;
			for (String key : keys) {
				String value = map.get(key)[0];
				if (value != null && value.trim().length() != 0 && !"action".equals(key)) {
					count++;
					predicateList.add(get_aPredicate_For_AnyDB(builder, root, key, value));
					System.out.println("有送出查詢資料的欄位數count" + count);
				}
			}
			System.out.println("predicateList.size()="+predicateList.size());
			criteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
			
			//ORDER BY
			criteriaQuery.orderBy(builder.asc(root.get(strOrderByTargetColumnName)));
			
			Query query = session.createQuery(criteriaQuery); 
			list = query.getResultList();

			//COMMIT HERE
			tx.commit();
			//這裡不要commit, 然後把查到的resaleordVO物件中的 ticket_no拉去做
			
		} catch (RuntimeException ex) {
			if (tx != null)
				tx.rollback();
			throw ex; // System.out.println(ex.getMessage());
		} finally {
			session.close();
			// HibernateUtil.getSessionFactory().close();
		}

		return list;

	}

	public static void main(String argv[]) {

		// �t�X req.getParameterMap()��k �^��
		// java.util.Map<java.lang.String,java.lang.String[]> ������
		Map<String, String[]> map = new TreeMap<String, String[]>();

//		map.put("ticket_no", new String[] { "T_20181225_000003" });
		
//		map.put("resale_ordstatus", new String[] { "CO" });
		
//		map.put("resale_ordprice", new String[] { "1900" });


		List<TicketVO> list = getAllC(map, "ticket_create_time"); 
		for (TicketVO aVO : list) {
			
			System.out.print(aVO.getTicket_no() + ",");
			System.out.print(aVO.getTicket_resale_price() + ",");
			System.out.print(aVO.getTicket_resale_status() + ",");
			
			// 注意以下三行的寫法 (優!)
//			System.out.print(aVO.getSeatingarea_h5VO().getTicbookednumber()+",");
//			System.out.print(aVO.getSeatingarea_h5VO().getTickettype_h5VO().getTictype_price()+ ",");
			System.out.println();
		}

	}
}
