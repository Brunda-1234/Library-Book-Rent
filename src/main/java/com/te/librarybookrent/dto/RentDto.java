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
public class RentDto {
	
	private Integer rentId;
	
	
	private Integer bookId;

	private Integer userId;
	
	private Integer rentHours;
	private Double totalAmount; 
	private boolean isAvailable;


	
}
