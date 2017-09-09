package org.powerSystem.dao;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParamAnalysis {

	public static Map<String,Object> analysis(Map<String,Object> map,Field[] Fields){
		Map<String, Object> finalMap = new HashMap<String, Object>();
		/**
		 * map<key,value>
		 * 
		 * value不允许为空 key必须是类的属性名 如果key不是类的属性名 那么 key
		 * 必须是属性名后加（<,>,<=,>=,!=）其中一个，
		 * 并且value不允许是string类型
		 * 
		 */
		if (map != null) {
			List<String> romveList = new ArrayList<String>();
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				String key = entry.getKey();
				Object value = entry.getValue();
				if (entry.getValue() == null) {
					romveList.add(key);
					continue;
				} else if (!validateKey(key, value,Fields)) {
					romveList.add(key);
					continue;
				}

				String f = "=";
				if (value.toString().indexOf("%") >= 0) {
					f = " like ";
				}
				if (value instanceof java.lang.String) {
					value = "'" + value + "'";
				} else {
					if (key.indexOf("<") == (key.length() - 1)
							|| key.indexOf(">") == (key.length() - 1)) {
						f = key.substring(key.length() - 1);
						key = key.substring(0, key.length() - 1);
					} else if (key.indexOf("=") == (key.length() - 1)) {
						f = key.substring(key.length() - 2);
						key = key.substring(0, key.length() - 2);
					} 
				}
				if (value instanceof java.util.Date) {
					//mysql   oracle to_date("2011-12-12 12:00:00","yyyy-MM-dd hh24:mm:ss")
					//sqlserver: convert(datatime,"2011-12-12 12:00:00")
					
					value = " date_format('"
							+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
									.format(value) + "','%Y-%m-%d %T')";
				}
				finalMap.put(key + f, value);
			}
		}
		return finalMap;
	}
	private static boolean validateKey(String key, Object value,Field[] Fields) {
		if(key.indexOf(".")>0)key=key.substring(0,key.indexOf("."));
		System.out.println("key::"+key);
		if (value instanceof java.lang.String) {
			for (int i = 0; i < Fields.length; i++) {
				if (key.equals(Fields[i].getName())) {
					return true;
				}
			}
		} else {
			for (int i = 0; i < Fields.length; i++) {
				if (key.equals(Fields[i].getName())
						|| key.substring(0, (key.length() - 1)).equals(Fields[i].getName())
						|| key.substring(0, (key.length() - 2)).equals(Fields[i].getName())) {
					return true;
				}
			}
		}
		return false;
	}
}
