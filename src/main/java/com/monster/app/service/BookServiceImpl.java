package com.monster.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monster.app.bean.Book;
import com.monster.app.dao.BookDao;

@Service("bookService")
public class BookServiceImpl implements BookService {

	@Autowired
	private BookDao bookDao;

	@Override
	public int add(Book book) {
		return bookDao.add(book);
	}

	@Override
	public int update(Book book) {
		return bookDao.update(book);
	}

	@Override
	public int delete(String barcode) {
		return bookDao.delete(barcode);
	}

	@Override
	public Book findBook(String barcode) {
		return bookDao.findBook(barcode);
	}

	@Override
	public List<Book> findBookList() {
		return bookDao.findBookList();
	}

}
