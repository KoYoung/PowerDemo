package org.powerSystem.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class JsonConfigUtil implements JsonValueProcessor {

	//可配置日期格式
	private String dateformat = "yyyy-MM-dd HH:mm:ss";		// 默认的日期格式
	public Object processArrayValue(Object value, JsonConfig jsonConfig) {
		String[] obj = {};
		if(value==null){
			return obj;
		}
		// 判断数组对象中的时间对象，格式化时间对象到指定的格式
		if(value instanceof Date[]){
			SimpleDateFormat  sdf = new SimpleDateFormat(dateformat);
			Date[] dates = (Date[])value;
			obj = new String[dates.length];
			for (int i = 0; i < dates.length; i++) {
				obj[i] = sdf.format(dates[i]);
			}
 		}
		return obj;
	}
	public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
		//判断日期对象，格式化日期
		if(value==null){
			return "";
		}
		if(value instanceof Date){
			String str = new SimpleDateFormat(dateformat).format((Date)value);
			return str;
		}
		return value.toString();
	}
	
	
	/**
	 * @return 返回JsonConfig配置 ，对日期进行格式化预处理，默认（yyyy-MM-dd HH:mm:ss）
	 */
	
	public static JsonConfig getJsonConfig(){
		JsonConfig  jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new JsonConfigUtil());
		return jsonConfig;
	}
	public static JsonConfig getJsonConfigInstanceByFormatDate(){
		JsonConfig  jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new JsonConfigUtil());
		return jsonConfig;
	}
	/**
	 * @param dateformat 默认（yyyy-MM-dd HH:mm:ss）, 可自定义日期格式，注意要符合日期格式的模式
	 * @return 返回JsonConfig配置 ，对日期进行格式化预处理，
	 */
	public static JsonConfig getJsonConfig(String dateformat){
		JsonConfig  jsonConfig = new JsonConfig();
		JsonConfigUtil jsonConfigUtil = new JsonConfigUtil();
		//当日期格式不是空时，重新设置日期格式
		if(dateformat != null &&dateformat.trim().length() != 0){
			jsonConfigUtil.dateformat = dateformat;
		}
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, jsonConfigUtil);
		return jsonConfig;
	}
	
	
}
