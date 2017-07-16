package com.zhang.test;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zhang.entity.User;
import com.zhang.service.UserService;



public class TestMybatis {

	private static Logger logger = Logger.getLogger(TestMybatis.class);

	private ApplicationContext ac = null;
	@Resource
	private UserService userService = null;

	@Before
	public void before() {
		ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		userService = (UserService)ac.getBean("userService");
	}

	@Test
	public void test() {

		User user = userService.searchUserByID(1);
		logger.info(user.getGrade());
	}
}
