package com.te.librarybookrent.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.librarybookrent.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, String>{

}
