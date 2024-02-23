package com.te.librarybookrent.book.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.te.librarybookrent.book.service.AdminService;
import com.te.librarybookrent.dto.BookDto;
import com.te.librarybookrent.dto.BookImageDto;
import com.te.librarybookrent.dto.Response;

import lombok.RequiredArgsConstructor;

@RestController("admin_controller")
@RequiredArgsConstructor
@RequestMapping("api/v1")
@CrossOrigin("*")
public class AdminController {

	private final AdminService adminService;

	ObjectMapper mapper = new ObjectMapper();

	@PostMapping("book")
	public ResponseEntity<Response> saveBook(@Valid @RequestBody BookDto bookDto) {

		return ResponseEntity.accepted()
				.body(Response.builder().error(false)
						.message(bookDto.getBookId() != null ? "Updated Successfully" : "Added Successfully")
						.data(adminService.addBook(bookDto)).build());

	}

	@GetMapping("all/books")
	public ResponseEntity<Response> getBook() {
		return ResponseEntity.accepted().body(Response.builder().error(false).message("Data Fetched Successfully")
				.data(adminService.getBooks()).build());
	}

	@DeleteMapping("delete/Books/{bookId}")
	public ResponseEntity<Response> deleteBook(@Valid @PathVariable Integer bookId) {
		return ResponseEntity.accepted().body(Response.builder().error(false).message("Book Deleted Successffully")
				.data(adminService.deleteBook(bookId)).build());
	}

	@PutMapping("update/bookImage")
	public ResponseEntity<Response> updateBookImage(@Valid @RequestBody BookImageDto bookImageDto) {
		return ResponseEntity.accepted()
				.body(Response.builder().error(false)
						.message(bookImageDto.getBookId() == null ? "Data Not Found" : "Updated SUccesssfully")
						.data(adminService.updateImage(bookImageDto)).build());

	}

	@GetMapping("all/rentDetails")
	public ResponseEntity<Response> fetchRentBook(){
		return ResponseEntity.accepted().body(Response.builder().error(false)
				.message("Rent data Fetched Successfully").data(adminService.rentDetails()).build());
		
	}

}
