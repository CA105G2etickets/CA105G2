package com.goods.model;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

 
public class CompositeQuery_Goods_Launched {
	
public static String get_WhereCondition(Map<String, String[]> map) {
		
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
				
		for (String key : keys) {
			String[] values = map.get(key);
			
			
			String value = map.get(key)[0];
			if (value != null && value.trim().length() != 0	&& !"action".equals(key)) {

				if ("goods_name".equals(key)) {
					whereCondition.append("AND (" + key + " LIKE upper('%" + values[0] + "%') ");
					whereCondition.append("or " + key + " LIKE lower('%" + values[0] + "%')) ");
				}					
				


				else if ("launchdate".equals(key)) {
					whereCondition.append("AND to_char(" + key + ",'yyyy-mm-dd') > '").append(values[0]).append("' ");
				}
				
				else if ("offdate".equals(key)) {
					whereCondition.append("AND to_char(" + key + ",'yyyy-mm-dd') < '").append(values[0]).append("' ");
				}
			}			
		}
		return whereCondition.toString();
	}
	

	
	

	
}
