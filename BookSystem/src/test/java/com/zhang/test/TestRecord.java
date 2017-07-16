package com.zhang.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zhang.entity.Record;
import com.zhang.service.RecordService;
import com.zhang.util.Constants;

public class TestRecord {
	private static Logger logger = Logger.getLogger(TestMybatis.class);

	private ApplicationContext ac = null;
	@Resource
	private RecordService recordService = null;

	@Before
	public void before() {
		ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		recordService = (RecordService) ac.getBean("recordService");
	}

	@Test
	public void test() {
		String page = "1";
		int pageSize = Constants.PAGE_SIZE;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", (Integer.parseInt(page) - 1) * pageSize);
		map.put("size", pageSize);
		map.put("status", 0);
		List<Record> records = recordService.getAllRecord(map);
		logger.info(records.get(2));
	}
}
