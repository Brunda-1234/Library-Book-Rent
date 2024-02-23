package com.te.librarybookrent.book.service;

import java.util.List;

import com.te.librarybookrent.dto.BookDto;
import com.te.librarybookrent.dto.BookImageDto;
import com.te.librarybookrent.dto.RentDto;

public interface AdminService {

	public BookDto addBook(BookDto bookDto);

	public List<BookDto> getBooks();

	public BookDto deleteBook(Integer bookId);
	
	public BookImageDto updateImage(BookImageDto bookImageDto);
	
	public List<RentDto> rentDetails();
}
