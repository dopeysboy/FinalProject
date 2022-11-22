package com.skilldistillery.rotahu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.rotahu.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUsername(String username);
}
