package com.te.librarybookrent.dto;

import java.time.LocalDateTime;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookDto {

	
	    private Integer bookId;
	  
	    @Pattern(regexp = "^[a-zA-Z]{3,}$",message="Book Name starts with Alphabets")
	    @Length(min = 3)
	    private String bookName;

	   
	    @Pattern(regexp = "^[a-zA-Z]{3,}$",message="Book Name starts with Alphabets")
	    @Length(min = 3)
	    private String bookAuthor;

	   
	   
	    @Pattern(regexp = "^[a-zA-Z]{3,}$",message="Book Name starts with Alphabets")
	    @Length(min = 3)
	    private String publishedBy;

	  
	    private String bookDescription;

	    private String bookImageUrl;

	    @NotNull(message = "Book Count cannot be empty")
	    @Min(value = 0)
	    private Integer bookCount;

	    
	    private Integer userId;

	    private Integer rentId;

	    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "Asia/Kolkata")
	    private LocalDateTime publishedDate;

	    @NotNull(message = "Book Rent per Hour is required")
	    @Min(value = 0, message = "Book Rent per Hour must be a positive number")
	    private double bookRentPerHour;

//	private UserDto userDto;
	
//	private List<RentDto> rentBookDto;
	
}
