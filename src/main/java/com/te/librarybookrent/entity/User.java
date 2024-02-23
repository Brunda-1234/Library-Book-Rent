package com.te.librarybookrent.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.te.librarybookrent.converter.StringListConverter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name= "user_table")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer userId;
	
	@Column(name = "user_name")
	private String usetName;
	
	@Column(name = "user_age")
	private Integer userAge;
	
	@Column(name = "user_email")
	private String userEmail;
	
	@Column(name = "user_phone_number")
	private String userPhoneNumber;
	
	@Column(name = "user_Address",length = 250)
	@Convert(converter = StringListConverter.class)
	private List<String> userAddress;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "user",fetch = FetchType.LAZY)
	private List<Book> books;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "user",fetch = FetchType.LAZY)
	private List<RentBook> rentBooks;
	
//	@OneToMany(cascade =CascadeType.ALL,mappedBy = "user",fetch = FetchType.LAZY)
//	private List<ReturnBook> returnBooks;
//	
	
}
