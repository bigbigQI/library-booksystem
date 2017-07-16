package com.zhang.dao;

import java.util.List;
import java.util.Map;

import com.zhang.entity.Book;

/**
 * 
 * @author Mr.Zhang
 * @describe 图书信息访问层
 */
public interface BookDao {

	public List<Book> getAllBook(Map<String, Object> map);

	public int countNum(Map<String, Object> map);

	public List<Book> searchBookByName(String name);

	public int addBook(Book book);

	public Book searchBookByID(int id);

	public int updateBook(Book book);

}
