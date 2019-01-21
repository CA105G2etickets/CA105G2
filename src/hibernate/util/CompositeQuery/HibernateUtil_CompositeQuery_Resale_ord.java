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
import com.event.model.*;
import com.event_title.model.*;
import com.resaleorder.model.ResaleOrderVO;

public class HibernateUtil_CompositeQuery_Resale_ord {

	public static Predicate get_aPredicate_For_AnyDB(CriteriaBuilder builder, Root<ResaleOrderVO> root, String columnName, String value) {

		Predicate predicate = null;
		System.out.println("value="+value);
		if ("resale_ordprice".equals(columnName)) {// for Integer
//			predicate = builder.equal(root.get(columnName), new Integer(value));
//			System.out.println("asdf_asdf_value="+value+"_new Integervalue="+(new Integer(value)));
			
//		predicate = builder.equal(root.get(columnName), new Integer(value)); //success
		
		
//			if(new Integer(value)==null || ) {
//				value=(new Integer(0)).toString();
//				predicate = builder.ge(root.get(columnName), new Integer(value));
//			}else {
//				predicate = builder.ge(root.get(columnName), new Integer(value));
//			}
		predicate = builder.ge(root.get(columnName), new Integer(value));
		}
		else if ("sal".equals(columnName) || "comm".equals(columnName)) //original use to Double, not used this time.
			{
			predicate = builder.equal(root.get(columnName), new Double(value));
			}
		else if ("resale_ordno".equals(columnName) || "member_seller_no".equals(columnName) 
				|| "member_buyer_no".equals(columnName) || "resale_ordstatus".equals(columnName) 
				|| "payment_method".equals(columnName)) // for varchar
			{
			predicate = builder.like(root.get(columnName), "%" + value + "%");
			}
		else if ("resale_ord_createtime".equals(columnName) || "resale_ord_completetime".equals(columnName)) // change to Timestamp
//			predicate = builder.equal(root.get(columnName), java.sql.Date.valueOf(value));
			{
			predicate = builder.equal(root.get(columnName), java.sql.Timestamp.valueOf(value));
			}
		else if ("ticket_no".equals(columnName)) {
			TicketVO ticketVO = new TicketVO();
			ticketVO.setTicket_no(value);
			
			//memo: watch out the .eq and .like parameters insides ISNT SAME
//			predicate = builder.like(root.get("ticketVO"), "%" + ticketVO.getTicket_no() + "%"); //error
			
//			predicate = builder.like(root.get("ticketVO"),"%"+ticketVO.getTicket_no()+"%"); //error  
			
			predicate = builder.equal(root.get("ticketVO"),ticketVO); //success
			
		}
		return predicate;
	}

	@SuppressWarnings("unchecked")
	public static List<ResaleOrderVO> getAllC(Map<String, String[]> map, String strOrderByTargetColumnName) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		List<ResaleOrderVO> list = null;
		try {
			// �i���Ы� CriteriaBuilder�j
			CriteriaBuilder builder = session.getCriteriaBuilder();
			// �i���Ы� CriteriaQuery�j
			CriteriaQuery<ResaleOrderVO> criteriaQuery = builder.createQuery(ResaleOrderVO.class);
			// �i���Ы� Root�j
			Root<ResaleOrderVO> root = criteriaQuery.from(ResaleOrderVO.class);

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

//		map.put("ticket_no", new String[] { "T_20190120_000154","T_20190120_000156" });
		
		map.put("resale_ordstatus", new String[] { "CA","CO" });
		
//		map.put("resale_ordprice", new String[] { "1900" });


		List<ResaleOrderVO> list = getAllC(map,"resale_ordno");
		for (ResaleOrderVO aROVO : list) {
			System.out.println("------------for each at List<ResaleOrderVO> STARTS");
			System.out.print(aROVO.getResale_ordno() + ",");
			System.out.print(aROVO.getMember_buyer_no() + ",");
			System.out.print(aROVO.getPayment_method() + ",");
			
			// 注意以下三行的寫法 (優!)
			System.out.print(aROVO.getTicketVO().getTicket_no()+",");
			System.out.print(aROVO.getTicketVO().getTicket_status()+ ",");
			System.out.println();
		}

	}
}
