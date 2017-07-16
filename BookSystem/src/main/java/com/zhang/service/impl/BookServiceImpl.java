package com.zhang.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhang.dao.BookDao;
import com.zhang.entity.Book;
import com.zhang.service.BookService;

@Service
@Transactional
public class BookServiceImpl implements BookService {
	@Resource
	private BookDao bookDao;

	@Override
	public List<Book> getAllBook(Map<String, Object> map) {
		return bookDao.getAllBook(map);
	}

	@Override
	public int countNum(Map<String, Object> map) {
		return bookDao.countNum(map);
	}

	@Override
	public List<Book> searchBookByName(String name) {
		return bookDao.searchBookByName(name);
	}

	@Override
	public int addBook(Book book) {
		return bookDao.addBook(book);
	}

	@Override
	public Book searchBookByID(int id) {
		return bookDao.searchBookByID(id);
	}

	@Override
	public int updateBook(Book book) {
		return bookDao.updateBook(book);
	}

}
