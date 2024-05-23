package com.lacerda.toDoList.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lacerda.toDoList.model.Days;
import com.lacerda.toDoList.model.Tasks;
import com.lacerda.toDoList.service.DayService;
import com.lacerda.toDoList.service.TasksService;

@RestController
@RequestMapping("/tasks")
public class TasksController {

	@Autowired
	TasksService taksService;

	@Autowired
	DayService dayService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Tasks> findAll() {
		return taksService.findAll();
	}

	@PostMapping
	public Tasks crate(@RequestBody Tasks tasks) {

		List<Tasks> ListTasks = findAll();

		LocalDate data = LocalDate.now();
		
		if (ListTasks.size() > 0) {
			Tasks lastTask = ListTasks.get(ListTasks.size() - 1);

			if (lastTask.getDays().getDays().equals(data)) {
				
				tasks.setDays(lastTask.getDays());
				taksService.create(tasks);
			}
			else {
				
				tasks.setDays(dayService.create(new Days(null, data)));
				taksService.create(tasks);
			}
			
		}
		else {
			tasks.setDays(dayService.create(new Days(null, data)));
			taksService.create(tasks);
		}
		
		return tasks;
	}
}
