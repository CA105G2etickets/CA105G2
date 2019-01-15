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
import com.venue.model.Venue_H5_VO;
import com.event.model.*;
import com.event_title.model.*;
import com.resaleorder.model.ResaleOrderVO;

public class HibernateUtil_CompositeQuery_Seating_Area {

	public static Predicate get_aPredicate_For_AnyDB(CriteriaBuilder builder, Root<SeatingArea_H5_VO> root, 
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
		if ("tictotalnumber".equals(columnName) || "ticbookednumber".equals(columnName)) // for Integer
			{
//			predicate = builder.equal(root.get(columnName), new Integer(value));
			predicate = builder.le(root.get(columnName), new Integer(value)); 
			}
//		else if ("sal".equals(columnName) || "comm".equals(columnName)) //original use to fit Double, not used this time.
//			{
//			predicate = builder.equal(root.get(columnName), new Double(value));
//			}
		else if ("ticarea_no".equals(columnName) || "ticarea_color".equals(columnName) 
				|| "ticarea_name".equals(columnName)) // for varchar2
			{
			predicate = builder.like(root.get(columnName), "%" + value + "%");
//			predicate = builder.in(root.get(columnName));	
			}
		
//		else if ("eve_startdate".equals(columnName) || "eve_enddate".equals(columnName) || 
//				"eve_onsaledate".equals(columnName) || "eve_offsaledate".equals(columnName) ||
//				"fullrefundenddate".equals(columnName)) // change to Timestamp //original use to fit Timestamp, not used this time.
////			predicate = builder.equal(root.get(columnName), java.sql.Date.valueOf(value));
//			{
//			predicate = builder.equal(root.get(columnName), java.sql.Timestamp.valueOf(value));
//			}
		else if ("eve_no".equals(columnName)) {
			Event_H5_VO eve_h5VO = new Event_H5_VO();
			eve_h5VO.setEve_no(value);  
			predicate = builder.equal(root.get("eve_h5VO"),eve_h5VO); //success
		}
		else if ("tictype_no".equals(columnName)) {
			TicketType_H5_VO tickettype_h5VO = new TicketType_H5_VO();
			tickettype_h5VO.setTictype_no(value);  
			predicate = builder.equal(root.get("tickettype_h5VO"),tickettype_h5VO); //success
		}
		return predicate;
	}

	@SuppressWarnings("unchecked")
	public static List<SeatingArea_H5_VO> getAllC(Map<String, String[]> map, String strOrderByTargetColumnName) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		List<SeatingArea_H5_VO> list = null;
		try {
			// �i���Ы� CriteriaBuilder�j
			CriteriaBuilder builder = session.getCriteriaBuilder();
			// �i���Ы� CriteriaQuery�j
			CriteriaQuery<SeatingArea_H5_VO> criteriaQuery = builder.createQuery(SeatingArea_H5_VO.class);
			// �i���Ы� Root�j
			Root<SeatingArea_H5_VO> root = criteriaQuery.from(SeatingArea_H5_VO.class);

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

//		map.put("eve_no", new String[] { "01" });
		
//		map.put("resale_ordstatus", new String[] { "CO" });
		
//		map.put("resale_ordprice", new String[] { "1900" });


		List<SeatingArea_H5_VO> list = getAllC(map, "ticarea_no"); 
		for (SeatingArea_H5_VO aVO : list) {
			
			System.out.print(aVO.getTicarea_name() + ",");
			System.out.print(aVO.getTicarea_no() + ",");
			
			// 注意以下三行的寫法 (優!)
			System.out.print(aVO.getEve_h5VO().getEve_no()+ ",");
			System.out.print(aVO.getEve_h5VO().getEve_sessionname()+ ",");
			
			System.out.print(aVO.getTickettype_h5VO().getTictype_price() + ",");
			System.out.print(aVO.getTickettype_h5VO().getTictype_name()+ ",");
			System.out.println();
		}

	}
}
