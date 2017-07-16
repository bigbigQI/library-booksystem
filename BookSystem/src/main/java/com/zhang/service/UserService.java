package com.zhang.service;

import java.util.List;
import java.util.Map;

import com.zhang.entity.User;

public interface UserService {

	public User login(User user);

	public List<User> getAllUser(Map<String, Object> map);

	public int countNum(Map<String, Object> map);

	public List<User> searchUserByName(String name);

	public int addUser(User user);

	public User searchUserByID(int id);

	public int updateUser(User user);

	public List<User> getDeleteUsers();

	public int lastDeleteUser(int id);
}
