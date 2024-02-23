package com.te.librarybookrent.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AdminDto {

	private String adminId;
	
	@Pattern(regexp = "^[a-zA-Z]{3,} $")
	@NotBlank(message = "Invalid Admin Name")
	private String adminName;
	
	@Email
	@NotBlank(message = "Invalid Email")
	private String adminEmail;
	
	@Digits(message = "Invalid phone Number", fraction = 0, integer = 10)
	@NotBlank
	private long adminPhoneNumber;
	
}
