package com.lacerda.toDoList.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lacerda.toDoList.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByEmail(String email);
}
