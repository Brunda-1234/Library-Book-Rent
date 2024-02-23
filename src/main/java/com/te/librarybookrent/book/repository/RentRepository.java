package com.te.librarybookrent.book.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.librarybookrent.entity.RentBook;

public interface RentRepository extends JpaRepository<RentBook,Integer >{

	Optional<RentBook> findByRentId(Integer rentId);
}
