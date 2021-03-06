package com.monster.app.service;

import java.util.List;

import com.monster.app.bean.Book;

public interface BookService {

	int add(Book book);

	int update(Book book);

	int delete(String barcode);

	Book findBook(String barcode);

	List<Book> findBookList();
}
