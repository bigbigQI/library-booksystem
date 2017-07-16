package com.zhang.dao;

import java.util.List;
import java.util.Map;

import com.zhang.entity.User;

/**
 * 
 * @author Mr.Zhang
 * @describe 用户信息访问层
 */
public interface UserDao {

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
