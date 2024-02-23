package com.te.librarybookrent.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "book_table")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_id")
	private Integer bookId;
	
	@Column(name = "book_name")
	private String bookName;
	
	@Column(name = "book_author")
	private String bookAuthor;
	
	@Column(name = "published_by")
	private String publishedBy;
	
	@Column(name = "book_count")
	private Integer bookCount;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "Asia/Kolkata")
	@Column(name = "published_date")
	private LocalDateTime publishedDate;
	
	@Column(name = "book_rent_per_hr")
	private double bookRentPerHour;
	
	@Column(name = "book_url")
	private String bookImageUrl;
	
	@Column(name = "book_description")
	private String bookDescription;
	

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "book",fetch = FetchType.LAZY)
	private List<RentBook> rentBook;
	
	 
	
}
