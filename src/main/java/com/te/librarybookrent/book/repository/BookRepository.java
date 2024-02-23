package com.te.librarybookrent.book.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.librarybookrent.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

	Optional<Book> findByBookId(Integer bookId);
	
	Optional<Book> findByBookIdAndBookName(Integer bookId,String bookName);
	
	Optional<Book> findByBookName(String bookName);
	
	List<Book> findByBookAuthor(String bookAuthor);

	
}
