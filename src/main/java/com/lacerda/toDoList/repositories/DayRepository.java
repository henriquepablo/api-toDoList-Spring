package com.lacerda.toDoList.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lacerda.toDoList.model.Days;

@Repository
public interface DayRepository extends JpaRepository<Days, Long>{

}
