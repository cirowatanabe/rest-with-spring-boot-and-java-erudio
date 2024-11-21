package br.com.erudio.unittests.mockito.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.erudio.data.vo.v1.BookVO;
import br.com.erudio.exceptions.RequiredObjectIsNullException;
import br.com.erudio.mapper.custom.BookMapper;
import br.com.erudio.model.Book;
import br.com.erudio.repositories.BookRepository;
import br.com.erudio.services.BookServices;
import br.com.erudio.unittestes.mapper.mocks.MockBook;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class BookServicesTest {
	
	MockBook input;
	
	@InjectMocks
	private BookServices service;
	
	@Mock
	BookRepository repository;
	
	@Mock
	private BookMapper mapper;
	
	@BeforeEach
	void setUpMoccks() throws Exception{
		input = new MockBook();
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void testFindAll() {
		List<Book> list = input.mockBookList();
		when(repository.findAll()).thenReturn(list);
		
		var result = service.findAll();
		assertNotNull(result);
		assertTrue(result.size() == 10);
		
		var bookOne = result.get(0);
		assertNotNull(bookOne.getId());
		assertNotNull(bookOne.getLinks());
		assertEquals("Author number: 0", bookOne.getAuthor());
		assertEquals(Double.valueOf(0), bookOne.getPrice());
		assertEquals("Title number: 0", bookOne.getTitle());
	}
	
	@Test
	void findById() {
		Book entity = input.mockBook();
		when(repository.findById(entity.getId())).thenReturn(Optional.of(entity));
		
		var result = service.findById(0L);
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getLinks());
		assertEquals("Author number: 0", result.getAuthor());
		assertEquals(Double.valueOf(0), result.getPrice());
		assertEquals("Title number: 0", result.getTitle());
	}
	
	@Test
	void testCreate() {
		Book entity = input.mockBook();
		Book persisted = entity;
		BookVO vo = input.mockBookVO();
		when(repository.save(entity)).thenReturn(persisted);
		
		var result = service.create(vo);
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getLinks());
		assertEquals("Author number: 0", result.getAuthor());
		assertEquals(Double.valueOf(0), result.getPrice());
		assertEquals("Title number: 0", result.getTitle());
	}
	
	@Test
	void testCreateWithNullBook() {
		Exception ex = assertThrows(RequiredObjectIsNullException.class, () -> {
			service.create(null);
		});
		String expectedMessage = "Null objects are not allowed";
		String actualMessage = ex.getMessage();
		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	void testUpdate() {
		Book entity = input.mockBook();
		Book savedEntity = entity;
		BookVO vo = input.mockBookVO();
		when(repository.findById(0L)).thenReturn(Optional.of(entity));
		when(repository.save(entity)).thenReturn(savedEntity);
		when(mapper.convertEntityToVO(any(Book.class))).thenReturn(vo);
		

		var result = service.update(vo);
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getLinks());
		assertEquals("Author number: 0", result.getAuthor());
		assertEquals(Double.valueOf(0), result.getPrice());
		assertEquals("Title number: 0", result.getTitle());
	}

	@Test
	void testUpdateWithNullPerson() {
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			service.update(null);
		});

		String expectedMessage = "Null objects are not allowed";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void testDelete() {
		Book entity = input.mockBook();
		when(repository.findById(0L)).thenReturn(Optional.of(entity));

		service.delete(0L);
	}
}
