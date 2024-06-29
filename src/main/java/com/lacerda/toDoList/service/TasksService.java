package com.lacerda.toDoList.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lacerda.toDoList.model.Tasks;
import com.lacerda.toDoList.repositories.TasksRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Service
public class TasksService {

	@Autowired
	TasksRepository tasksRepository;
	
	public List<Tasks> findAll(Long id) {
		
		return tasksRepository.findTaskByUserId(id);

	}
	
	public List<Tasks> findTaskByDate(LocalDate date) {
		return tasksRepository.findTaskByData(date);
	}
	
	public Tasks create(Tasks tasks) {
		
		return tasksRepository.save(tasks);
	}
	
	
	public Tasks update(Tasks tasks, Long id) throws Exception {
		Tasks entity = tasksRepository.findById(id).orElseThrow(() -> new Exception("Não encontrado"));
		entity.setTasks(tasks.getTasks());
		return tasksRepository.save(entity);		
	}
	
	public void delete(Long id) throws Exception {
		Tasks tasks = tasksRepository.findById(id).orElseThrow(() -> new Exception("Não encontrado"));
		
		tasksRepository.delete(tasks);
	}
	
}
