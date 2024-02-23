package com.te.librarybookrent.book.service;

import com.te.librarybookrent.dto.BookDto;
import com.te.librarybookrent.dto.RentDto;
import com.te.librarybookrent.dto.ReturnBookDto;
import com.te.librarybookrent.dto.UserDto;

public interface UserService {

	public UserDto addUser(UserDto userDto);
	
	public BookDto getBook(String bookName);
	
	public RentDto rentBook(RentDto rentDto);
	
	public ReturnBookDto bookReturn(ReturnBookDto returnBookDto);
}
