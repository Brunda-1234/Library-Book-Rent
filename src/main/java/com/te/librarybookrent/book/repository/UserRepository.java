package com.te.librarybookrent.book.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.librarybookrent.entity.User;

public interface UserRepository extends JpaRepository<User,Integer>{

	Optional<User> findByUserId(Integer userId);
}
