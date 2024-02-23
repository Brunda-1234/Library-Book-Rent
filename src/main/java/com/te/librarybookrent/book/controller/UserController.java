package com.te.librarybookrent.book.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.librarybookrent.book.service.UserService;
import com.te.librarybookrent.dto.RentDto;
import com.te.librarybookrent.dto.Response;
import com.te.librarybookrent.dto.ReturnBookDto;
import com.te.librarybookrent.dto.UserDto;

import lombok.RequiredArgsConstructor;

@RestController("user_controller")
@RequiredArgsConstructor
@RequestMapping("api/v1")
@CrossOrigin("*")
public class UserController {
	
	private final UserService userService;
	
	@PostMapping("save/user")
	public ResponseEntity<Response> saveUser(@Valid @RequestBody UserDto userDto){
		return ResponseEntity.accepted().body(Response.builder().error(false).message(userDto.getUserId() != null 
				? "User Updated Successfully" :"User Registered Successfully").data(userService.addUser(userDto)).build());
		
	}
	
	@GetMapping("get/book/{bookName}")
	public ResponseEntity<Response> getBook(@PathVariable String bookName){
		return ResponseEntity.accepted().body(Response.builder().error(false).message("Data Fetched Successfully").data(userService.getBook(bookName)).build());
	}
	
	@PostMapping("book/rent")
	public ResponseEntity<Response> rentBook(@Valid @RequestBody RentDto rentDto){
		
		return ResponseEntity.accepted().body(Response.builder().error(false).message("Rented Successfylly").data(userService.rentBook(rentDto)).build());
	}
	
	@PostMapping("return/book")
	public ResponseEntity<Response> bookreturn(@Valid @RequestBody ReturnBookDto returnBookDto){
		return ResponseEntity.accepted().body(Response.builder().error(false).data(userService.bookReturn(returnBookDto)).message("book Returned Successfully").build());
	}
	
	

}
