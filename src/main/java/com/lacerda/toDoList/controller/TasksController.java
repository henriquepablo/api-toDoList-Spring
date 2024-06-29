package com.lacerda.toDoList.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lacerda.toDoList.model.Date;
import com.lacerda.toDoList.model.Tasks;
import com.lacerda.toDoList.repositories.UserRepository;
import com.lacerda.toDoList.service.TasksService;

@RestController
@RequestMapping("/tasks")
public class TasksController {

	@Autowired
	TasksService taksService;
	
	@Autowired
	UserRepository userRepository;

	@GetMapping("/{id}")
	public List<Tasks> findAllTasksOfUser(@PathVariable Long id) {
		return taksService.findAll(id);
	}
	
	@PostMapping("/register/task")
	public Tasks create(@RequestBody Tasks tasks) {
		
		  LocalDateTime dataAtual = LocalDateTime.now();
		  
		  tasks.setTaskCreatedAt(dataAtual);
		  
		  taksService.create(tasks);
		  
		return tasks;
	}
	
	@PostMapping("/find/task/date")
	public List<Tasks> findTaskByDate(@RequestBody Date date) {
		return taksService.findTaskByDate(date.getDate());
		 
	}

	@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Tasks update(@PathVariable(value = "id") Long id, @RequestBody Tasks tasks) throws Exception {
		return taksService.update(tasks, id);
	}

	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable(value = "id") Long id) throws Exception {
		taksService.delete(id);
	}
}
