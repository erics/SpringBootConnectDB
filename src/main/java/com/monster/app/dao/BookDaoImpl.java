package com.monster.app.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.monster.app.bean.Book;

@Repository
public class BookDaoImpl implements BookDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int add(Book book) {
		return jdbcTemplate.update("insert into book(barcode,bookname,author) values(?,?,?)",
				book.getBarcode(), book.getBookName(), book.getAuthor());
	}

	@Override
	public int update(Book book) {
		return jdbcTemplate.update("update book set bookname=?, author=? where barcode=?",
				new Object[]{book.getBookName(), book.getAuthor(), book.getBarcode()});
	}

	@Override
	public int delete(String barcode) {
		return jdbcTemplate.update("delete from book where barcode=?", barcode);
	}

	@Override
	public Book findBook(String barcode) {
		List<Book> list = jdbcTemplate.query("select * from book where barcode = ?",
				new Object[]{barcode}, new BeanPropertyRowMapper(Book.class));
		if (null != list && list.size() > 0) {
			Book book = list.get(0);
			return book;
		} else {
			return null;
		}
	}

	@Override
	public List<Book> findBookList() {
		List<Book> list = jdbcTemplate.query("select * from book", new Object[]{},
				new BeanPropertyRowMapper<Book>(Book.class));
		return list;
	}

}
