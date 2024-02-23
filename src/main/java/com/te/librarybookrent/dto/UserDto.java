package com.te.librarybookrent.dto;

import java.util.List;

import javax.persistence.Convert;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.te.librarybookrent.converter.StringListConverter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDto {

	private Integer userId;

	@Pattern(regexp = "^[a-zA-Z]{3,}$", message = "user Name starts with Alphabets")
	@Length(min = 3)
	@NotBlank(message = "userName should not be Empty")
	private String usetName;

//	@NotBlank(message = "userAge Should not be empty")
	@Min(value= 15,message = "userAge Should be greater than 15")
	private Integer userAge;

	@Email(message = "format of email id should be abc@gmail.com")
	@NotBlank(message = "userEmail should not  be empty")
	private String userEmail;

////	@Pattern(regexp = "[6789][0-9]{9}", message = "mobile number should have 10 digits ")
//	@Digits(integer = 10, fraction = 0, message = "Mobile number should have 10 digits")
	@NotBlank(message = "User Phone Number should not be empty")
    @Pattern(regexp = "\\d{10}", message = "Mobile number should have exactly 10 digits")
	private String userPhoneNumber;

	@Convert(converter = StringListConverter.class)
	private List<String> userAddress;

	private List<BookDto> bookDtos;
	private List<RentDto> rentBooksDto;
}
