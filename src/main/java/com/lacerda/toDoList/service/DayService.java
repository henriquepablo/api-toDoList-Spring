package com.lacerda.toDoList.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lacerda.toDoList.model.Days;
import com.lacerda.toDoList.repositories.DayRepository;

@Service
public class DayService {
	
	@Autowired
	DayRepository dayRepository;
	
	public Days create(Days days) {

		return dayRepository.save(days);
	}
}
