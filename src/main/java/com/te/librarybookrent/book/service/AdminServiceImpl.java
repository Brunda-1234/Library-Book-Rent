package com.te.librarybookrent.book.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.te.librarybookrent.book.repository.BookRepository;
import com.te.librarybookrent.book.repository.RentRepository;
import com.te.librarybookrent.book.repository.UserRepository;
import com.te.librarybookrent.dto.BookDto;
import com.te.librarybookrent.dto.BookImageDto;
import com.te.librarybookrent.dto.RentDto;
import com.te.librarybookrent.entity.Book;
import com.te.librarybookrent.exception.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

	private final BookRepository bookRepository;

	@SuppressWarnings("unused")
	private final UserRepository userRepository;

	private final RentRepository rentRepository;

	@Transactional
	@Override
	public BookDto addBook(BookDto bookDto) {
		try {
			if (bookDto.getBookId() != null) {
				Book book = bookRepository.findByBookId(bookDto.getBookId()).get();
				book.setBookCount(book.getBookCount() + bookDto.getBookCount());
				BeanUtils.copyProperties(book, bookDto);
//				BeanUtils.copyProperties(bookRepository.save(book), bookDto);
				return bookDto;
			}
			Book book = new Book();
			BeanUtils.copyProperties(bookDto, book);
			Book save = bookRepository.save(book);
			BeanUtils.copyProperties(save, bookDto);
			return bookDto;

		} catch (Exception e) {
			e.printStackTrace();
			throw new DataNotFoundException(e.getMessage());
		}
	}

	@Override
	public List<BookDto> getBooks() {
		try {
			List<Book> findAll = bookRepository.findAll();
			return findAll.stream().map(book -> {
				BookDto bookDto = new BookDto();
				BeanUtils.copyProperties(book, bookDto);
                book.getRentBook().stream().forEach(rent->{
                	bookDto.setRentId(rent.getRentId());
                });
                Integer userId = book.getUser().getUserId();
                bookDto.setUserId(userId);
				return bookDto;
			}).collect(Collectors.toList());

		} catch (Exception e) {
			e.printStackTrace();
			throw new DataNotFoundException("SomeThing Went Wrong");
		}

	}

	@Override
	public BookDto deleteBook(Integer bookId) {
		try {
			Book book = bookRepository.findByBookId(bookId).get();

			BookDto bookDto = new BookDto();
			BeanUtils.copyProperties(bookRepository.save(book), bookDto);
			return bookDto;

		} catch (Exception e) {
			e.printStackTrace();
			throw new DataNotFoundException("SomeThing Went Wrong");
		}

	}

	@Override
	public BookImageDto updateImage(BookImageDto bookImageDto) {
		try {
			Book book = bookRepository.findByBookId(bookImageDto.getBookId()).get();
			BeanUtils.copyProperties(bookImageDto, book);
			BeanUtils.copyProperties(bookRepository.save(book), bookImageDto);
			return bookImageDto;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataNotFoundException("Something Went Wrong");
		}

	}

	@Override
	public List<RentDto> rentDetails() {
		try {
		return	rentRepository.findAll().stream().map(rent->{
				Integer userId = rent.getUser().getUserId();
				RentDto rentDto = new RentDto();
				BeanUtils.copyProperties(rent, rentDto);
				rentDto.setUserId(userId);
				Integer bookId = rent.getBook().getBookId();
				rentDto.setBookId(bookId);
				return rentDto;
			}).collect(Collectors.toList());
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataNotFoundException(e.getMessage());
		}

	}

}



