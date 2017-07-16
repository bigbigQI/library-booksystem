package com.zhang.test;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zhang.entity.User;
import com.zhang.service.UserService;
import com.zhang.service.impl.UserServiceImpl;

public class TestLogin {
	private static Logger logger = Logger.getLogger(TestMybatis.class);

	private ApplicationContext ac = null;
	@Resource
	private UserService userService = null;

	@Before
	public void before() {
		ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		userService = (UserService) ac.getBean("userService");
	}

	@Test
	public void test() {
		User user = new User();
		user.setName("admin");
		user.setPass("123456");
		User existUser = userService.login(user);
		logger.info(existUser.getId());
	}
}
