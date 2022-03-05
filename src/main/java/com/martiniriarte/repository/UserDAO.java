package com.martiniriarte.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.martiniriarte.model.User;

public interface UserDAO extends JpaRepository<User, Long> {

	User findByEmail(String email);
}
