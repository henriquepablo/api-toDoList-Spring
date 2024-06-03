package com.lacerda.toDoList.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lacerda.toDoList.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
