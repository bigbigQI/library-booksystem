package com.zhang.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * 
 * 类说明
 * 
 * @简述： Timestamp 处理器
 * @版本： 1.0
 * @邮箱：
 * @修改时间：2014-3-27 下午12:16:24
 */
public class DateJsonValueProcessor implements JsonValueProcessor {

	/**
	 * 字母 日期或时间元素 表示 示例 <br>
	 * G Era 标志符 Text AD <br>
	 * y 年 Year 1996; 96 <br>
	 * M 年中的月份 Month July; Jul; 07 <br>
	 * w 年中的周数 Number 27 <br>
	 * W 月份中的周数 Number 2 <br>
	 * D 年中的天数 Number 189 <br>
	 * d 月份中的天数 Number 10 <br>
	 * F 月份中的星期 Number 2 <br>
	 * E 星期中的天数 Text Tuesday; Tue<br>
	 * a Am/pm 标记 Text PM <br>
	 * H 一天中的小时数（0-23） Number 0 <br>
	 * k 一天中的小时数（1-24） Number 24<br>
	 * K am/pm 中的小时数（0-11） Number 0 <br>
	 * h am/pm 中的小时数（1-12） Number 12 <br>
	 * m 小时中的分钟数 Number 30 <br>
	 * s 分钟中的秒数 Number 55 <br>
	 * S 毫秒数 Number 978 <br>
	 * z 时区 General time zone Pacific Standard Time; PST; GMT-08:00 <br>
	 * Z 时区 RFC 822 time zone -0800 <br>
	 */
	public static final String Default_DATE_PATTERN = "yyyy-MM-dd";
	private DateFormat dateFormat;

	/** 
     *  
     */
	public DateJsonValueProcessor(String datePattern) {
		try {
			dateFormat = new SimpleDateFormat(datePattern);
		} catch (Exception e) {
			dateFormat = new SimpleDateFormat(Default_DATE_PATTERN);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sf.json.processors.JsonValueProcessor#processArrayValue(java.lang
	 * .Object, net.sf.json.JsonConfig)
	 */
	public Object processArrayValue(Object value, JsonConfig jsonConfig) {
		return process(value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sf.json.processors.JsonValueProcessor#processObjectValue(java.lang
	 * .String, java.lang.Object, net.sf.json.JsonConfig)
	 */
	public Object processObjectValue(String key, Object value,
			JsonConfig jsonConfig) {
		return process(value);
	}

	private Object process(Object value) {
		if (value == null) {
			return "";
		} else {
			return dateFormat.format(value);
		}
	}

}
