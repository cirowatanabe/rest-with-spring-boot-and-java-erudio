package br.com.erudio.mapper.custom;

import org.springframework.stereotype.Service;

import br.com.erudio.data.vo.v1.BookVO;
import br.com.erudio.model.Book;

@Service
public class BookMapper {
	
	public BookVO convertEntityToVO(Book book) {
		BookVO vo = new BookVO();
		vo.setId(book.getId());
		vo.setAuthor(book.getAuthor());
		vo.setLaunchDate(book.getLaunchDate());
		vo.setPrice(book.getPrice());
		vo.setTitle(book.getTitle());
		
		return vo;
	}
	
	public Book convertVOToEntity(BookVO vo) {
		Book book = new Book();
		book.setId(vo.getId());
		book.setAuthor(vo.getAuthor());
		book.setLaunchDate(vo.getLaunchDate());
		book.setPrice(vo.getPrice());
		book.setTitle(vo.getTitle());
		
		return book;
	}
}
