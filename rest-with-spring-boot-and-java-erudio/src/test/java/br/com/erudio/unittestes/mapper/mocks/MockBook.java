package br.com.erudio.unittestes.mapper.mocks;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.erudio.data.vo.v1.BookVO;
import br.com.erudio.model.Book;

public class MockBook {
	
	public Book mockBook() {
		return mockBook(0);
	}
	
	public BookVO mockBookVO() {
		return mockBookVO(0);
	}
	
	public Book mockBook(int n) {
		Book book = new Book();
		book.setId(Long.valueOf(n));
		book.setAuthor("Author number: " + n);
		book.setLaunchDate(LocalDateTime.now());
		book.setPrice(Double.valueOf(n));
		book.setTitle("Title number: " + n);
		return book;
	}
	
	public BookVO mockBookVO(int n) {
		BookVO book = new BookVO();
		book.setId(Long.valueOf(n));
		book.setAuthor("Author number: " + n);
		book.setLaunchDate(LocalDateTime.now());
		book.setPrice(Double.valueOf(n));
		book.setTitle("Title number: " + n);
		return book;
	}
	
	public List<Book> mockBookList(){
		List<Book> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			list.add(mockBook(i));
		}
		return list;
	}
	
	public List<BookVO> mockBookVOList(){
		List<BookVO> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			list.add(mockBookVO(i));
		}
		return list;
	}

}
