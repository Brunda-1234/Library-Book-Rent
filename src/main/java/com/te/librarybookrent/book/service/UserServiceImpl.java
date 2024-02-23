package com.te.librarybookrent.book.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.te.librarybookrent.book.exception.SameBookException;
import com.te.librarybookrent.book.repository.BookRepository;
import com.te.librarybookrent.book.repository.RentRepository;
import com.te.librarybookrent.book.repository.UserRepository;
import com.te.librarybookrent.dto.BookDto;
import com.te.librarybookrent.dto.RentDto;
import com.te.librarybookrent.dto.ReturnBookDto;
import com.te.librarybookrent.dto.UserDto;
import com.te.librarybookrent.entity.Book;
import com.te.librarybookrent.entity.RentBook;
import com.te.librarybookrent.entity.User;
import com.te.librarybookrent.exception.DataNotFoundException;
import com.te.librarybookrent.generator.BeanCopy;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final BookRepository bookRepository;
	private final RentRepository rentRepository;

	public UserDto addUser(UserDto userDto) {
		try {
			if (userDto.getUserId() != null) {
				User user = userRepository.findByUserId(userDto.getUserId()).get();
				BeanUtils.copyProperties(userDto, user);
				List<Book> booklist = BeanCopy.copy(userDto.getBookDtos(), Book.class);
				user.setBooks(booklist);
				BeanUtils.copyProperties(userRepository.save(user), userDto);
				return userDto;
			} else {
				User user = new User();
				BeanUtils.copyProperties(userDto, user);
				BeanUtils.copyProperties(userRepository.save(user), userDto);
				return userDto;
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new DataNotFoundException("Data Not Found");
		}

	}

	@Override
	public BookDto getBook(String bookName) {
		try {

			Optional<Book> book = bookRepository.findByBookName(bookName);
			BookDto bookDto = new BookDto();
			book.ifPresent(b -> BeanUtils.copyProperties(b, bookDto));
			return bookDto;

		} catch (Exception e) {
			e.printStackTrace();
			throw new DataNotFoundException("Book Not Present");
		}

	}

	@Transactional
	@Override
	public RentDto rentBook(RentDto rentDto) {
		try {

//			RentBook rentBook = new RentBook();
//			Book book = bookRepository.findByBookId(rentDto.getBookId()).get();
//			
//			Integer bookCount = book.getBookCount();
//			System.err.println(bookCount);
//			if(bookCount != 0) {
//				rentBook.setAvailable(false);
//				int reduceCount = bookCount-1;
//				book.setBookCount(reduceCount);
//				
//			}
//			double amount = book.getBookRentPerHour() * rentDto.getRentHours();
//			User user = userRepository.findByUserId(rentDto.getUserId()).get();
//			book.setUser(user);
//			rentBook.setBook(book);
//			book.getRentBook().stream().map(rentbook->{
//			 RentDto rentDto1 =  new RentDto();
//			 BeanUtils.copyProperties(rentbook, rentDto1);
//			 return rentDto1;
//			 
//			}).collect(Collectors.toList());
//			
//			rentBook.setUser(user);
//            rentBook.setRentHours(rentDto.getRentHours());
//		    rentBook.setTotalAmount(amount);
//			BeanUtils.copyProperties(rentRepository.save(rentBook), rentDto);
//			return rentDto;

			RentBook rentBook = new RentBook();
			Optional<Book> findByBookId = bookRepository.findByBookId(rentDto.getBookId());
			findByBookId.ifPresent(book -> rentBook.setBook(book));
			
			double amount = findByBookId.get().getBookRentPerHour() * rentDto.getRentHours();
			rentBook.setTotalAmount(amount);
			rentBook.setRentHours(rentDto.getRentHours());
			
			if (findByBookId.get().getBookCount() != null) {
				rentBook.setAvailable(false);
				Integer newCount = findByBookId.get().getBookCount() - 1;
				findByBookId.ifPresent(book -> book.setBookCount(newCount));
			}
			Optional<User> userOp = userRepository.findByUserId(rentDto.getUserId());
			userOp.ifPresent(user -> rentBook.setUser(user));
			findByBookId.ifPresent(book->book.setUser(userOp.get()));
			User user = userOp.get();
			user.getBooks().stream().forEach(book->{
				if(book.getBookId() == rentDto.getBookId()) {
					throw new SameBookException("User Already rented This Book");
				}
			});
			BeanUtils.copyProperties(rentRepository.save(rentBook), rentDto);
			return rentDto;

		} catch (DataNotFoundException e) {
			e.printStackTrace();
			throw new DataNotFoundException("Something Went Wrong");
		}

	}

	@Transactional
	@Override
	public ReturnBookDto bookReturn(ReturnBookDto returnBookDto) {
		try {
			Optional<RentBook> findByRentId = rentRepository.findByRentId(returnBookDto.getRentId());
			Book book = findByRentId.get().getBook();
			findByRentId.get().setAvailable(true);
			Integer bookCount = book.getBookCount();
			bookCount = bookCount + 1;
			book.setBookCount(bookCount);

			return returnBookDto;

		} catch (Exception e) {
			e.printStackTrace();
			throw new DataNotFoundException("SomeThing Went Wrong");
		}

	}

}

//if (book.isAvaialble() == false) {
//User user = book.getUser();
//UserDto userDto = new UserDto();
//BeanUtils.copyProperties(user,userDto);
//
//List<RentDto> rentDtolIst = BeanCopy.copy(book.getRentBook(), RentDto.class);
//
//BookDto bookDto1 = new BookDto();
//bookDto1.setUserDto(userDto);
//bookDto1.setRentBookDto(rentDtolIst);
//BeanUtils.copyProperties(book, bookDto1);
//return bookDto1;
//}

//Optional<Book> book = bookRepository.findByBookName(bookName);
//if(book.isPresent()) {
//	Book book2 = book.get();
//BookDto bookDto = new BookDto();
//book2.getRentBook().stream().forEach(rent->{
//	bookDto.setRentId(rent.getRentId());
//});
//Integer userId = book2.getUser().getUserId();
//bookDto.setUserId(userId);
//BeanUtils.copyProperties(book2, bookDto);
//return bookDto;
//}else {
//	return null;
//}
