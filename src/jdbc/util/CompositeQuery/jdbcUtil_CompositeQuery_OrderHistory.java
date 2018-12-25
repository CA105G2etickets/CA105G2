/*
 *  1. 萬用複合查詢-可由客戶端隨意增減任何想查詢的欄位
 *  2. 為了避免影響效能:
 *        所以動態產生萬用SQL的部份,本範例無意採用MetaData的方式,也只針對個別的Table自行視需要而個別製作之
 * */

package jdbc.util.CompositeQuery;

import java.util.*;

public class jdbcUtil_CompositeQuery_OrderHistory {

	public static String get_aCondition_For_Oracle(String columnName, String value) {

		String aCondition = null;

		if ("order_price".equals(columnName)) // 用於其他
			aCondition = columnName + "=" + value;
		else if ("order_no".equals(columnName) || "member_no".equals(columnName) || "pay_methods".equals(columnName) 
			|| "shipping_methods".equals(columnName) || "receiver_add".equals(columnName) || "receiver_name".equals(columnName) 
			|| "receiver_tel".equals(columnName) || "order_status".equals(columnName)) // 用於varchar
			aCondition = columnName + " like '%" + value + "%'";
		else if ("order_date".equals(columnName) || "order_etd".equals(columnName) || "pickup_date".equals(columnName))	// 用於Oracle的date
			aCondition = "TO_CHAR(" + columnName + ",'yyyy-mm-dd')='" + value + "'";
		
		System.out.println(aCondition);
		return aCondition + " ";
	}

	public static String get_WhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (String key : keys) {
			String value = map.get(key)[0];
			if (value != null && value.trim().length() != 0	&& !"action".equals(key)) {
				count++;
				String aCondition = get_aCondition_For_Oracle(key, value.trim());

				if (count == 1)
					whereCondition.append("where " + aCondition);
				else
					whereCondition.append("and " + aCondition);

				System.out.println("有送出查詢資料的欄位數count = " + count);
			}
		}
		
		return whereCondition.toString();
	}

	public static void main(String argv[]) {

		// 配合 req.getParameterMap()方法 回傳 java.util.Map<java.lang.String,java.lang.String[]> 之測試
		Map<String, String[]> map = new TreeMap<String, String[]>();
		map.put("ORDER_NO", new String[] { "O2018122410001" });
		map.put("MEMBER_NO", new String[] { "M000001" });
		map.put("ORDER_PRICE", new String[] { "2250" });
		map.put("PAY_METHODS", new String[] { "EWALLET" });
		map.put("SHIPPING_METHODS", new String[] { "STOPERPICKUP" });
		map.put("ORDER_DATE", new String[] { "2018-12-24" });
		map.put("ORDER_ETD", new String[] { "2018-12-24" });
		map.put("PICKUP_DATE", new String[] { "2018-12-24" });
		map.put("RECEIVER_ADD", new String[] { "320桃園市中壢區福德一路177巷60弄2號" });
		map.put("RECEIVER_NAME", new String[] { "Peter" });
		map.put("RECEIVER_TEL", new String[] { "0912345678" });
		map.put("ORDER_STATUS", new String[] { "COMPLETE4" });
		// 注意Map裡面會含有action的key

		String finalSQL = "SELECT * FROM ORDER_HISTORY "
				          + jdbcUtil_CompositeQuery_OrderHistory.get_WhereCondition(map)
				          + " ORDER BY ORDER_NO";
		System.out.println("●●finalSQL = " + finalSQL);

	}
}
