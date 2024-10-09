package br.com.erudio.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.controllers.BookController;
import br.com.erudio.data.vo.v1.BookVO;
import br.com.erudio.exceptions.RequiredObjectIsNullException;
import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.mapper.DozerMapper;
import br.com.erudio.mapper.custom.BookMapper;
import br.com.erudio.model.Book;
import br.com.erudio.repositories.BookRepository;

@Service
public class BookServices {
	
	private final String BOOK_NOT_FOUND = "No records found for this id";
	
	@Autowired
	private BookRepository repository;
	
	@Autowired
	private BookMapper mapper;
	
	public List<BookVO> findAll(){
		List<BookVO> vo = DozerMapper.parseListObjects(repository.findAll(), BookVO.class);
		vo.stream().forEach(b -> b.add(linkTo(methodOn(BookController.class).findById(b.getId())).withSelfRel()));
		return vo;
	}
	
	public BookVO findById(Long id) {
		Book book = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(BOOK_NOT_FOUND));
		BookVO vo = DozerMapper.parseObject(book, BookVO.class);
		vo.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
		return vo;
	}
	
	public BookVO create(BookVO vo) {
		if (vo == null) {
			throw new RequiredObjectIsNullException();
		}
		Book book = repository.save(mapper.convertVOToEntity(vo));
		BookVO vo2 = mapper.convertEntityToVO(book);
		vo2.add(linkTo(methodOn(BookController.class).findById(vo2.getId())).withSelfRel());
		return vo2;
	}
	
	public BookVO update(BookVO vo) {
		if (vo == null) {
			throw new RequiredObjectIsNullException();
		}
		Book book = repository.findById(vo.getId()).orElseThrow(() -> new ResourceNotFoundException(BOOK_NOT_FOUND));
		book.setAuthor(vo.getAuthor());
		book.setLaunchDate(vo.getLaunchDate());
		book.setPrice(vo.getPrice());
		book.setTitle(vo.getTitle());
		BookVO bookVO = mapper.convertEntityToVO(repository.save(book));
		vo.add(linkTo(methodOn(BookController.class).findById(bookVO.getId())).withSelfRel());
		return bookVO;
	}
	
	public void delete(Long id) {
		Book book = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(BOOK_NOT_FOUND));
		repository.delete(book);
	}

}
