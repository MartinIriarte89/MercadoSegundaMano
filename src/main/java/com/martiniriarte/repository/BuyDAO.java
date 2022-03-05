package com.martiniriarte.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.martiniriarte.model.Buy;
import com.martiniriarte.model.User;

public interface BuyDAO extends JpaRepository<Buy, Long>{

	List<Buy> findByOwner(User owner);
}
