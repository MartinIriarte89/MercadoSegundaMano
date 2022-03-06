package com.martiniriarte.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.martiniriarte.model.User;
import com.martiniriarte.repository.UserDAO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserDAO userDAO;
	private final BCryptPasswordEncoder passwordEncoder;
	
	public User registrer(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userDAO.save(user);
	}
	
	public User findById(long id) {
		return userDAO.findById(id).orElse(null);
	}
	
	public User findByEmail(String email) {
		return userDAO.findByEmail(email);
	}
}
