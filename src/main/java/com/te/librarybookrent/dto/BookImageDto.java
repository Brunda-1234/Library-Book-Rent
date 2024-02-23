package com.te.librarybookrent.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookImageDto {


	private Integer bookId;
	
    @NotBlank(message = "bookImageUrl not be Blank")
	private String bookImageUrl;
	

}
