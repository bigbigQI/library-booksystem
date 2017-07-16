package com.zhang.service;

import java.util.List;
import java.util.Map;

import com.zhang.entity.Book;

/**
 * 
 * @author Mr.Zhang
 * @describe 图书服务层
 */
public interface BookService {

	public List<Book> getAllBook(Map<String, Object> map);

	public int countNum(Map<String, Object> map);

	public List<Book> searchBookByName(String name);

	public int addBook(Book book);

	public Book searchBookByID(int id);

	public int updateBook(Book book);

}
