package com.zhang.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhang.dao.UserDao;
import com.zhang.entity.User;
import com.zhang.service.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;

	@Override
	public User login(User user) {
		return userDao.login(user);
	}

	@Override
	public List<User> getAllUser(Map<String, Object> map) {
		return userDao.getAllUser(map);
	}

	@Override
	public int countNum(Map<String, Object> map) {
		return userDao.countNum(map);
	}

	@Override
	public List<User> searchUserByName(String name) {
		return userDao.searchUserByName(name);
	}

	@Override
	public int addUser(User user) {
		return userDao.addUser(user);
	}

	@Override
	public User searchUserByID(int id) {
		return userDao.searchUserByID(id);
	}

	@Override
	public int updateUser(User user) {
		return userDao.updateUser(user);
	}

	@Override
	public List<User> getDeleteUsers() {
		return userDao.getDeleteUsers();
	}

	@Override
	public int lastDeleteUser(int id) {
		return userDao.lastDeleteUser(id);
	}

}
