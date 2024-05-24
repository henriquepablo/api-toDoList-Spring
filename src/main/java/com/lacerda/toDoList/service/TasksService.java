package com.lacerda.toDoList.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lacerda.toDoList.model.Tasks;
import com.lacerda.toDoList.repositories.TasksRepository;

@Service
public class TasksService {

	@Autowired
	TasksRepository tasksRepository;
	
	public List<Tasks> findAll() {

		return tasksRepository.findAll();

	}
	
	public Tasks create(Tasks tasks) {

		return tasksRepository.save(tasks);
	}
	
	
}
