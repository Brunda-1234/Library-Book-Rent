package com.te.librarybookrent.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ReturnBookDto {
	
	
	private Integer bookId;
	private Integer rentId;
	private Integer userId;
	

}
