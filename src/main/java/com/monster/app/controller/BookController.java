package com.monster.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.monster.app.bean.Book;
import com.monster.app.service.BookService;

@RestController
@RequestMapping("/data/jdbc/book")
public class BookController {

	@Autowired
	private BookService bookService;

	@RequestMapping(method = RequestMethod.GET)
	public Map<String, Object> getBookList(HttpServletRequest request) {
		List<Book> bookList = this.bookService.findBookList();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("total", bookList.size());
		param.put("rows", bookList);

		return param;
	}

	@RequestMapping(value = "/{barcode:\\d+}", method = RequestMethod.GET)
	public Book getBook(@PathVariable String barcode, HttpServletRequest request) {
		Book book = this.bookService.findBook(barcode);
		if (book == null) {
			throw new RuntimeException("Query Error");
		}

		return book;
	}

	@RequestMapping(method = RequestMethod.POST)
	public void add(@RequestBody JSONObject jsonObject) {
		Book book = Book.transfer(jsonObject);
		try {
			this.bookService.add(book);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Inert Error");
		}
	}

	@RequestMapping(value = "/{barcode:\\d+}", method = RequestMethod.PUT)
	public void update(@PathVariable String barcode, @RequestBody JSONObject jsonObject) {
		Book book = this.bookService.findBook(barcode);
		String bookname = jsonObject.getString("bookname");
		String author = jsonObject.getString("author");
		book.setBookName(bookname);
		book.setAuthor(author);
		try {
			this.bookService.update(book);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("update Error");
		}
	}

	@RequestMapping(value = "/{barcode:\\d+}", method = RequestMethod.DELETE)
	public void delete(@PathVariable String barcode) {
		try {
			this.bookService.delete(barcode);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Delete Error");
		}
	}
}
