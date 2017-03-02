package com.monster.app.bean;

import com.alibaba.fastjson.JSONObject;

public class Book {
	private Long id;
	private String barcode;
	private String bookName;
	private String author;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public static Book transfer(JSONObject jsonObject) {
		Book book = new Book();
		book.setBarcode(jsonObject.getString("barcode"));
		book.setBookName(jsonObject.getString("bookname"));
		book.setAuthor(jsonObject.getString("author"));

		return book;
	}
}
